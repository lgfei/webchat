package com.lgfei.tool.webchat.enums;

public enum CommandEnum
{
    /**
     * 
     */
    START("start", "启动"),
    /**
    * 
    */
    CLOSE("close", "关闭"),
    /**
    * 
    */
    CONNECT("connect", "连接"),
    /**
    * 
    */
    DISCONNECT("disconnect", "断开连接");
    
    private String command;
    
    private String desc;
    
    private CommandEnum(String command, String desc)
    {
        this.command = command;
        this.desc = desc;
    }
    
    public String getCommand()
    {
        return command;
    }
    
    public void setCommand(String command)
    {
        this.command = command;
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
