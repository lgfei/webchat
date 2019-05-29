package com.lgfei.tool.webchat.vo;

public class PageVO
{
    public static final int DEFAULT_START = 0;
    
    public static final int DEFAULT_PAGESIZE = 10;
    
    private Integer start;
    
    private Integer offset;
    
    public PageVO(Integer start, Integer offset)
    {
        this.start = start;
        this.offset = offset;
    }
    
    public Integer getStart()
    {
        return start;
    }
    
    public void setStart(Integer start)
    {
        this.start = start;
    }
    
    public Integer getOffset()
    {
        return offset;
    }
    
    public void setOffset(Integer offset)
    {
        this.offset = offset;
    }
    
}
