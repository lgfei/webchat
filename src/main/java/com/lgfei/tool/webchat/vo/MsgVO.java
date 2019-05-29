package com.lgfei.tool.webchat.vo;

public class MsgVO extends BaseVO
{
    /**
     * 序列
     */
    private static final long serialVersionUID = 6048598139925531139L;
    
    private String roomId;
    
    private String guestId;
    
    private String nickName;
    
    private String msgContent;
    
    public String getRoomId()
    {
        return roomId;
    }
    
    public void setRoomId(String roomId)
    {
        this.roomId = roomId;
    }
    
    public String getGuestId()
    {
        return guestId;
    }
    
    public void setGuestId(String guestId)
    {
        this.guestId = guestId;
    }
    
    public String getNickName()
    {
        return nickName;
    }
    
    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }
    
    public String getMsgContent()
    {
        return msgContent;
    }
    
    public void setMsgContent(String msgContent)
    {
        this.msgContent = msgContent;
    }
    
}
