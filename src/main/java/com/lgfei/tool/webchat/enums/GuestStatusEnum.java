package com.lgfei.tool.webchat.enums;

public enum GuestStatusEnum
{
    /**
    * 
    */
    OFFLINE(0, "下线"),
    /**
     * 
     */
    ONLINE(1, "上线");
    
    private Integer code;
    
    private String desc;
    
    private GuestStatusEnum(Integer code, String desc)
    {
        this.code = code;
        this.desc = desc;
    }
    
    public Integer getCode()
    {
        return code;
    }
    
    public void setCode(Integer code)
    {
        this.code = code;
    }
    
    public String getDesc()
    {
        return desc;
    }
    
    public void setDesc(String desc)
    {
        this.desc = desc;
    }
    
}
