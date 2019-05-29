package com.lgfei.tool.webchat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgfei.tool.webchat.dao.MsgDao;
import com.lgfei.tool.webchat.exception.SystemException;
import com.lgfei.tool.webchat.service.MsgService;
import com.lgfei.tool.webchat.vo.MsgVO;

@Service
public class MsgServiceImpl implements MsgService
{
    @Autowired
    private MsgDao msgDao;
    
    @Override
    public void create(MsgVO vo)
        throws SystemException
    {
        if (null == vo)
        {
            return;
        }
        msgDao.insert(vo);
    }
    
    @Override
    public void delete(MsgVO vo)
        throws SystemException
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void update(MsgVO vo)
        throws SystemException
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void disable(MsgVO vo)
        throws SystemException
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public MsgVO findById(String id)
        throws SystemException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public List<MsgVO> findList(MsgVO vo)
        throws SystemException
    {
        if (null == vo)
        {
            vo = new MsgVO();
        }
        return msgDao.queryList(vo);
    }
    
    @Override
    public Integer count(MsgVO vo)
        throws SystemException
    {
        // TODO Auto-generated method stub
        return null;
    }
}
