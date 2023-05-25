package com.lgfei.tool.webchat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

import com.alibaba.fastjson2.JSONObject;
import com.lgfei.tool.webchat.exception.SystemException;
import com.lgfei.tool.webchat.service.GuestService;
import com.lgfei.tool.webchat.service.MsgService;
import com.lgfei.tool.webchat.util.SpringContextUtil;
import com.lgfei.tool.webchat.vo.MsgVO;

@ServerEndpoint("/chat")
public class ChatServer
{
    private static Logger log = Logger.getLogger(ChatServer.class);
    
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    private static Vector<Session> room = new Vector<Session>();
    
    /**
     * 用户接入
     * @param session 可选
     */
    @OnOpen
    public void onOpen(Session session)
    {
        String roomId = session.getRequestParameterMap().get("roomId").get(0);
        String guestId = session.getRequestParameterMap().get("guestId").get(0);
        log.info("加入聊天室，roomId=" + roomId + ",guestId=" + guestId);
        room.addElement(session);
        // 上线
        GuestService guestService = SpringContextUtil.getBean(GuestService.class);
        guestService.onLine(guestId);
        // 发送用户上线通知
        for (Session se : room)
        {
            se.getAsyncRemote().sendText("CMD~ONLINE");
        }
    }
    
    /**
     * 接收到来自用户的消息
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session)
    {
        String roomId = session.getRequestParameterMap().get("roomId").get(0);
        String guestId = session.getRequestParameterMap().get("guestId").get(0);
        //把用户发来的消息解析为JSON对象
        JSONObject obj = JSONObject.parseObject(message);
        //向JSON对象中添加发送时间
        obj.put("createTime", sdf.format(new Date()));
        //遍历聊天室中的所有会话
        for (Session se : room)
        {
            obj.put("guestId", guestId);
            //发送消息给远程用户
            se.getAsyncRemote().sendText(obj.toString());
        }
        String msgContent = obj.getString("msgContent");
        // 记录聊天消息
        MsgVO msgVO = new MsgVO();
        msgVO.setRoomId(roomId);
        msgVO.setGuestId(guestId);
        msgVO.setMsgContent(msgContent);
        try
        {
            MsgService msgService = SpringContextUtil.getBean(MsgService.class);
            msgService.create(msgVO);
        }
        catch (SystemException e)
        {
            log.error("记录历史消息发生异常：" + e.getMessage());
        }
    }
    
    /**
     * 用户断开
     * @param session
     */
    @OnClose
    public void onClose(Session session)
    {
        String roomId = session.getRequestParameterMap().get("roomId").get(0);
        String guestId = session.getRequestParameterMap().get("guestId").get(0);
        log.info("退出聊天室，roomId=" + roomId + ",guestId=" + guestId);
        room.remove(session);
        // 下线
        GuestService guestService = SpringContextUtil.getBean(GuestService.class);
        guestService.offLine(guestId);
        // 发送用户下线通知
        for (Session se : room)
        {
            se.getAsyncRemote().sendText("CMD~OFFLINE");
        }
    }
    
    /**
     * 用户连接异常
     * @param t
     */
    @OnError
    public void onError(Throwable t)
    {
        log.error("用户连接发生异常：" + t.getMessage());
    }
    
}
