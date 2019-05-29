package com.lgfei.tool.webchat.enums;

public enum ResultCodeEnum
{
    /**
     * 
     */
    SUCCESS(0, "Success"),
    /**
    * 
    */
    INVALID_PARAM(1, "invalid param"),
    /**
     * 
     */
    REQUEST_INVALID(2, "invalid resuest"),
    /**
    * 
    */
    SYSTEM_ERROR(3, "server busy"),
    /**
    * 
    */
    BUSINESS_ERROR(4, "business error"),
    /**
     * 
     */
    DATA_NOT_EXIST(1000, "data not exist"),
    /**
    * 
    */
    DUPLICATE_KEY(1001, "duplicate key");
    
    private Integer code;
    
    private String msg;
    
    private ResultCodeEnum(Integer code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }
    
    public Integer getCode()
    {
        return code;
    }
    
    public void setCode(Integer code)
    {
        this.code = code;
    }
    
    public String getMsg()
    {
        return msg;
    }
    
    public void setMsg(String msg)
    {
        this.msg = msg;
    }
    
}
