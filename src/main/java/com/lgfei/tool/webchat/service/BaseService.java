package com.lgfei.tool.webchat.service;

import java.util.List;

import com.lgfei.tool.webchat.exception.SystemException;

public interface BaseService<VO>
{
    void create(VO vo)
        throws SystemException;
    
    void delete(VO vo)
        throws SystemException;
    
    void update(VO vo)
        throws SystemException;
    
    void disable(VO vo)
        throws SystemException;
    
    VO findById(String id)
        throws SystemException;
    
    List<VO> findList(VO vo)
        throws SystemException;
    
    Integer count(VO vo)
        throws SystemException;
}
