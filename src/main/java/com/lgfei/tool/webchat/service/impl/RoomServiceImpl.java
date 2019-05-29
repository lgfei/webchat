package com.lgfei.tool.webchat.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lgfei.tool.webchat.dao.GuestDao;
import com.lgfei.tool.webchat.dao.RoomDao;
import com.lgfei.tool.webchat.enums.GuestTypeEnum;
import com.lgfei.tool.webchat.enums.TablesEnum;
import com.lgfei.tool.webchat.exception.SystemException;
import com.lgfei.tool.webchat.service.RoomService;
import com.lgfei.tool.webchat.util.CreateIDUtil;
import com.lgfei.tool.webchat.vo.GuestVO;
import com.lgfei.tool.webchat.vo.PageResult;
import com.lgfei.tool.webchat.vo.PageVO;
import com.lgfei.tool.webchat.vo.RoomVO;

@Service
public class RoomServiceImpl implements RoomService
{
    private static Logger log = Logger.getLogger(RoomServiceImpl.class);
    
    @Autowired
    private RoomDao roomDao;
    
    @Autowired
    private GuestDao guestDao;
    
    @Override
    public void create(RoomVO vo)
        throws SystemException
    {
        log.info("开始创建房间");
        if (null == vo)
        {
            log.info("参数为空");
            return;
        }
        vo.setRoomId(CreateIDUtil.businessID(TablesEnum.T_ROOM.getModule()));
        roomDao.insert(vo);
        
        log.info("开始创建房主");
        GuestVO guestVO = new GuestVO();
        guestVO.setRoomId(vo.getRoomId());
        guestVO.setGuestId(CreateIDUtil.businessID(TablesEnum.T_GUEST.getModule()));
        guestVO.setGuestName(GuestTypeEnum.OWNER.getDesc());
        guestVO.setGuestType(GuestTypeEnum.OWNER.getCode());
        guestVO.setGuestIp(vo.getRoomIp());
        
        guestDao.insert(guestVO);
    }
    
    @Override
    public void delete(RoomVO vo)
        throws SystemException
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void update(RoomVO vo)
        throws SystemException
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void disable(RoomVO vo)
        throws SystemException
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public RoomVO findById(String id)
        throws SystemException
    {
        if (StringUtils.isEmpty(id))
        {
            return null;
        }
        return roomDao.queryById(id);
    }
    
    @Override
    public List<RoomVO> findList(RoomVO vo)
        throws SystemException
    {
        if (vo == null)
        {
            vo = new RoomVO();
        }
        return roomDao.queryList(vo);
    }
    
    @Override
    public Integer count(RoomVO vo)
        throws SystemException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public PageResult<RoomVO> findPageList(RoomVO vo, Integer start, Integer pageSize)
    {
        PageVO pageVO = parsePageParam(start, pageSize);
        List<RoomVO> rows = roomDao.queryPageList(vo, pageVO);
        Integer total = roomDao.queryCount(vo);
        
        PageResult<RoomVO> result = new PageResult<>();
        result.setRows(rows);
        result.setTotal(total);
        
        return result;
    }
    
    private PageVO parsePageParam(Integer start, Integer pageSize)
    {
        if (null == start)
        {
            start = PageVO.DEFAULT_START;
        }
        if (null == pageSize)
        {
            pageSize = PageVO.DEFAULT_PAGESIZE;
        }
        return new PageVO(start, pageSize);
        
    }
    
}
