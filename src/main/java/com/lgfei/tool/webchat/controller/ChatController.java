package com.lgfei.tool.webchat.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.lgfei.tool.webchat.constant.RoomConfig;
import com.lgfei.tool.webchat.enums.GuestTypeEnum;
import com.lgfei.tool.webchat.enums.ResultCodeEnum;
import com.lgfei.tool.webchat.enums.TablesEnum;
import com.lgfei.tool.webchat.exception.SystemException;
import com.lgfei.tool.webchat.service.GuestService;
import com.lgfei.tool.webchat.service.MsgService;
import com.lgfei.tool.webchat.service.RoomService;
import com.lgfei.tool.webchat.util.CreateIDUtil;
import com.lgfei.tool.webchat.util.WebChatUtil;
import com.lgfei.tool.webchat.vo.GuestVO;
import com.lgfei.tool.webchat.vo.MsgVO;
import com.lgfei.tool.webchat.vo.PageResult;
import com.lgfei.tool.webchat.vo.RoomVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/service")
public class ChatController
{
    private static Logger log = Logger.getLogger(ChatController.class);
    
    @Autowired
    private RoomConfig roomConfig;
    
    @Autowired
    private RoomService roomService;
    
    @Autowired
    private GuestService guestService;
    
    @Autowired
    private MsgService msgService;
    
    @RequestMapping(value = "/createroom", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject createRoom(HttpServletRequest request)
    {
        JSONObject resp = new JSONObject();
        
        String data = request.getParameter("data");
        if (StringUtils.isEmpty(data))
        {
            log.error("缺少必要的参数");
            resp.put("resultCode", ResultCodeEnum.INVALID_PARAM.getCode());
            return resp;
        }
        RoomVO vo = JSONObject.parseObject(data, RoomVO.class);
        if (!roomConfig.getPortList().contains(vo.getRoomPort()))
        {
            log.error("错误的房间端口号" + vo.getRoomPort());
            resp.put("resultCode", ResultCodeEnum.INVALID_PARAM.getCode());
            return resp;
        }
        vo.setRoomIp(roomConfig.getHost());
        try
        {
            roomService.create(vo);
        }
        catch (SystemException e)
        {
            e.printStackTrace();
        }
        return resp;
    }
    
    @RequestMapping(value = "/getroompagelist", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getRoomPageList(HttpServletRequest request)
    {
        JSONObject resp = new JSONObject();
        try
        {
            Integer start = Integer.parseInt(request.getParameter("offset"));
            Integer pageSize = Integer.parseInt(request.getParameter("limit"));
            String roomId = request.getParameter("roomId");
            String roomName = request.getParameter("roomName");
            
            RoomVO paramVO = new RoomVO();
            paramVO.setRoomId(roomId);
            paramVO.setRoomName(roomName);
            
            PageResult<RoomVO> result = roomService.findPageList(paramVO, start, pageSize);
            
            resp = JSONObject.parseObject(JSON.toJSONString(result));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return resp;
    }
    
    @RequestMapping(value = "/enterroom", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject enterRoom(HttpServletRequest request)
    {
        JSONObject resp = new JSONObject();
        
        String data = request.getParameter("data");
        if (StringUtils.isEmpty(data))
        {
            return resp;
        }
        
        JSONObject param = JSONObject.parseObject(data);
        try
        {
            String roomId = param.getString("roomId");
            RoomVO roomVO = roomService.findById(roomId);
            if (null != roomVO)
            {
                String reqIp = WebChatUtil.getIp(request);
                GuestVO paramVO = new GuestVO();
                paramVO.setRoomId(roomId);
                paramVO.setGuestIp(reqIp);
                List<GuestVO> guests = guestService.findList(paramVO);
                if (CollectionUtils.isEmpty(guests))
                {
                    if (reqIp.equals(roomVO.getRoomIp()))
                    {
                        resp.put("resultCode", ResultCodeEnum.BUSINESS_ERROR.getCode());
                        return resp;
                    }
                    String roomPassword = param.getString("roomPassword");
                    roomPassword = StringUtils.isEmpty(roomPassword) ? "" : roomPassword;
                    String dbRoomPassword = roomVO.getRoomPassword();
                    dbRoomPassword = StringUtils.isEmpty(dbRoomPassword) ? "" : dbRoomPassword;
                    if (!roomPassword.equals(dbRoomPassword))
                    {
                        // 口令不正确
                        resp.put("resultCode", ResultCodeEnum.INVALID_PARAM.getCode());
                        return resp;
                    }
                    
                    String nickname = param.getString("nickname");
                    if (StringUtils.isEmpty(nickname))
                    {
                        // 昵称不能为空
                        resp.put("resultCode", ResultCodeEnum.INVALID_PARAM.getCode());
                        return resp;
                    }

                    GuestVO guestQueryParams = new GuestVO();
                    guestQueryParams.setRoomId(roomId);
                    guestQueryParams.setGuestName(nickname);
                    List<GuestVO> dbGuestList = guestService.findList(guestQueryParams);
                    if(!CollectionUtils.isEmpty(dbGuestList)){
                        // 昵称已占用
                        resp.put("resultCode", ResultCodeEnum.DUPLICATE_KEY.getCode());
                        return resp;
                    }
                    
                    // 添加房客
                    GuestVO paramGuestVO = new GuestVO();
                    paramGuestVO.setRoomId(roomId);
                    paramGuestVO.setGuestId(CreateIDUtil.businessID(TablesEnum.T_GUEST.getModule()));
                    paramGuestVO.setGuestName(nickname);
                    paramGuestVO.setGuestType(GuestTypeEnum.GUEST.getCode());
                    paramGuestVO.setGuestIp(reqIp);
                    guestService.create(paramGuestVO);
                    
                    resp.put("resultCode", ResultCodeEnum.SUCCESS.getCode());
                    resp.put("guest", paramGuestVO);
                    resp.put("flag", "join");
                }
                else
                {
                    resp.put("resultCode", ResultCodeEnum.SUCCESS.getCode());
                    resp.put("guest", guests.get(0));
                    resp.put("flag", "open");
                    return resp;
                }
            }
        }
        catch (SystemException e)
        {
            e.printStackTrace();
            resp.put("resultCode", ResultCodeEnum.SYSTEM_ERROR.getCode());
        }
        return resp;
    }
    
    @RequestMapping(value = "/getroom", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getRoom(HttpServletRequest request)
    {
        JSONObject resp = new JSONObject();
        try
        {
            String roomId = request.getParameter("roomId");
            
            RoomVO result = roomService.findById(roomId);
            
            resp = JSONObject.parseObject(JSON.toJSONString(result));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return resp;
    }
    
    @RequestMapping(value = "/getguest", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getGuest(HttpServletRequest request)
    {
        JSONObject resp = new JSONObject();
        try
        {
            String guestId = request.getParameter("guestId");
            
            GuestVO result = guestService.findById(guestId);
            
            resp = JSONObject.parseObject(JSON.toJSONString(result));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return resp;
    }
    
    @RequestMapping(value = "/getguestlist", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray getGuestList(HttpServletRequest request)
    {
        JSONArray resp = new JSONArray();
        try
        {
            String roomId = request.getParameter("roomId");
            GuestVO paramVO = new GuestVO();
            paramVO.setRoomId(roomId);
            
            List<GuestVO> result = guestService.findList(paramVO);
            
            resp = JSONArray.parseArray(JSON.toJSONString(result));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return resp;
    }
    
    @RequestMapping(value = "/getports", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray getPorts(HttpServletRequest request)
    {
        JSONArray resp = new JSONArray();
        try
        {
            List<RoomVO> allRooms = roomService.findList(null);
            List<Integer> result = roomConfig.getPortList();
            for (RoomVO room : allRooms)
            {
                result.remove(room.getRoomPort());
            }
            
            resp = JSONArray.parseArray(JSON.toJSONString(result));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return resp;
    }
    
    @RequestMapping(value = "/getmsglist", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray getMsgList(HttpServletRequest request)
    {
        JSONArray resp = new JSONArray();
        try
        {
            String roomId = request.getParameter("roomId");
            MsgVO paramVO = new MsgVO();
            paramVO.setRoomId(roomId);
            List<MsgVO> result = msgService.findList(paramVO);
            
            resp = JSONArray.parseArray(JSON.toJSONString(result));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return resp;
    }
}
