package com.lgfei.tool.webchat.enums;

public enum GuestTypeEnum
{
    /**
     * 
     */
    OWNER(1, "房主"),
    /**
     * 
     */
    GUEST(2, "房客");
    
    private Integer code;
    
    private String desc;
    
    private GuestTypeEnum(Integer code, String desc)
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
