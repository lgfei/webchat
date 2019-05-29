package com.lgfei.tool.webchat.vo;

public class RoomVO extends BaseVO
{
    /**
     * 序列
     */
    private static final long serialVersionUID = -2976288118132191352L;
    
    private String roomId;
    
    private String roomName;
    
    private Integer roomLimit;
    
    private Integer roomGuestNum;
    
    private Integer roomOnlineNum;
    
    private String roomPassword;
    
    private String roomDesc;
    
    private String roomIp;
    
    private Integer roomPort;
    
    public String getRoomId()
    {
        return roomId;
    }
    
    public void setRoomId(String roomId)
    {
        this.roomId = roomId;
    }
    
    public String getRoomName()
    {
        return roomName;
    }
    
    public void setRoomName(String roomName)
    {
        this.roomName = roomName;
    }
    
    public Integer getRoomLimit()
    {
        return roomLimit;
    }
    
    public void setRoomLimit(Integer roomLimit)
    {
        this.roomLimit = roomLimit;
    }
    
    public Integer getRoomGuestNum()
    {
        return roomGuestNum;
    }
    
    public void setRoomGuestNum(Integer roomGuestNum)
    {
        this.roomGuestNum = roomGuestNum;
    }
    
    public Integer getRoomOnlineNum()
    {
        return roomOnlineNum;
    }
    
    public void setRoomOnlineNum(Integer roomOnlineNum)
    {
        this.roomOnlineNum = roomOnlineNum;
    }
    
    public String getRoomPassword()
    {
        return roomPassword;
    }
    
    public void setRoomPassword(String roomPassword)
    {
        this.roomPassword = roomPassword;
    }
    
    public String getRoomDesc()
    {
        return roomDesc;
    }
    
    public void setRoomDesc(String roomDesc)
    {
        this.roomDesc = roomDesc;
    }
    
    public String getRoomIp()
    {
        return roomIp;
    }
    
    public void setRoomIp(String roomIp)
    {
        this.roomIp = roomIp;
    }
    
    public Integer getRoomPort()
    {
        return roomPort;
    }
    
    public void setRoomPort(Integer roomPort)
    {
        this.roomPort = roomPort;
    }
    
}
