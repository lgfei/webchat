package com.lgfei.tool.webchat.enums;

public enum TablesEnum
{
    /**
     * 
     */
    T_ROOM("t_room", "room", "房间信息表"),
    /**
     * 
     */
    T_GUEST("t_guest", "guest", "房客表"),
    /**
     * 
     */
    T_MSG("t_msg", "msg", "消息表");
    
    private TablesEnum(String name, String module, String desc)
    {
        this.name = name;
        this.module = module;
        this.desc = desc;
    }
    
    /**
     * 表名
     */
    private String name;
    
    /**
     * 模块名
     */
    private String module;
    
    /**
     * 描述
     */
    private String desc;
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getModule()
    {
        return module;
    }
    
    public void setModule(String module)
    {
        this.module = module;
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
