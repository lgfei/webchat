package com.lgfei.tool.webchat.vo;

import java.io.Serializable;
import java.util.Date;

public class BaseVO implements Serializable
{
    /**
     * 序列
     */
    private static final long serialVersionUID = 8987716845128619332L;
    
    private Long id;
    
    private Date createTime;
    
    private Date updateTime;
    
    private Integer enableFlag;
    
    public Long getId()
    {
        return id;
    }
    
    public void setId(Long id)
    {
        this.id = id;
    }
    
    public Date getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
    
    public Date getUpdateTime()
    {
        return updateTime;
    }
    
    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }
    
    public Integer getEnableFlag()
    {
        return enableFlag;
    }
    
    public void setEnableFlag(Integer enableFlag)
    {
        this.enableFlag = enableFlag;
    }
}
