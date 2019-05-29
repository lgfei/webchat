package com.lgfei.tool.webchat.dao;

import org.apache.ibatis.annotations.Param;

import com.lgfei.tool.webchat.vo.GuestVO;

public interface GuestDao extends BaseDao<GuestVO>
{
    int updateGuestStatus(@Param("guestId") String guestId, @Param("guestStatus") Integer guestStatus);
}
