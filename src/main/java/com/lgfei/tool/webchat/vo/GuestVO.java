package com.lgfei.tool.webchat.vo;

public class GuestVO extends BaseVO
{
    /**
     * 序列
     */
    private static final long serialVersionUID = -627828250538877323L;
    
    private String roomId;
    
    private String guestId;
    
    private String guestName;
    
    private Integer guestType;
    
    private Integer guestStatus;
    
    private String guestIp;
    
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
    
    public String getGuestName()
    {
        return guestName;
    }
    
    public void setGuestName(String guestName)
    {
        this.guestName = guestName;
    }
    
    public Integer getGuestType()
    {
        return guestType;
    }
    
    public void setGuestType(Integer guestType)
    {
        this.guestType = guestType;
    }
    
    public Integer getGuestStatus()
    {
        return guestStatus;
    }
    
    public void setGuestStatus(Integer guestStatus)
    {
        this.guestStatus = guestStatus;
    }
    
    public String getGuestIp()
    {
        return guestIp;
    }
    
    public void setGuestIp(String guestIp)
    {
        this.guestIp = guestIp;
    }
    
}
