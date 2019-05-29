package com.lgfei.tool.webchat.exception;

public class SystemException extends Exception
{
    /**
     * 序列
     */
    private static final long serialVersionUID = 6701572329304474179L;
    
    private int errCode;
    
    private String errMsg;
    
    public SystemException(int retCode, String retMsg)
    {
        super(retMsg);
        this.errCode = retCode;
        this.errMsg = retMsg;
    }
    
    public SystemException(String retMsg)
    {
        super(retMsg);
        this.errMsg = retMsg;
    }
    
    public SystemException(int retCode, String retMsg, Throwable e)
    {
        super(retMsg, e);
        this.errCode = retCode;
        this.errMsg = retMsg;
    }
    
    public int getErrCode()
    {
        return errCode;
    }
    
    public void setErrCode(int errCode)
    {
        this.errCode = errCode;
    }
    
    public String getErrMsg()
    {
        return errMsg;
    }
    
    public void setErrMsg(String errMsg)
    {
        this.errMsg = errMsg;
    }
    
}
