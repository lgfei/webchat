package com.lgfei.tool.webchat.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lgfei.tool.webchat.dao.GuestDao;
import com.lgfei.tool.webchat.enums.GuestStatusEnum;
import com.lgfei.tool.webchat.enums.ResultCodeEnum;
import com.lgfei.tool.webchat.exception.SystemException;
import com.lgfei.tool.webchat.service.GuestService;
import com.lgfei.tool.webchat.vo.GuestVO;

@Service
public class GuestServiceImpl implements GuestService
{
    private static Logger log = Logger.getLogger(GuestServiceImpl.class);
    
    @Autowired
    private GuestDao guestDao;
    
    @Override
    public void create(GuestVO vo)
        throws SystemException
    {
        if (null == vo)
        {
            return;
        }
        try
        {
            guestDao.insert(vo);
        }
        catch (DuplicateKeyException e)
        {
            StringBuilder errMsg = new StringBuilder();
            errMsg.append("房间：").append(vo.getRoomId()).append("已存在：").append(vo.getGuestName());
            log.error(errMsg.toString());
            throw new SystemException(ResultCodeEnum.DUPLICATE_KEY.getCode(), errMsg.toString());
        }
    }
    
    @Override
    public void delete(GuestVO vo)
        throws SystemException
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void update(GuestVO vo)
        throws SystemException
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void disable(GuestVO vo)
        throws SystemException
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public GuestVO findById(String id)
        throws SystemException
    {
        if (StringUtils.isEmpty(id))
        {
            return null;
        }
        return guestDao.queryById(id);
    }
    
    @Override
    public List<GuestVO> findList(GuestVO vo)
        throws SystemException
    {
        if (null == vo)
        {
            vo = new GuestVO();
        }
        return guestDao.queryList(vo);
    }
    
    @Override
    public Integer count(GuestVO vo)
        throws SystemException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public void onLine(String guestId)
    {
        guestDao.updateGuestStatus(guestId, GuestStatusEnum.ONLINE.getCode());
    }
    
    @Override
    public void offLine(String guestId)
    {
        guestDao.updateGuestStatus(guestId, GuestStatusEnum.OFFLINE.getCode());
    }
    
}
