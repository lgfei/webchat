package com.lgfei.tool.webchat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lgfei.tool.webchat.vo.PageVO;
import com.lgfei.tool.webchat.vo.RoomVO;

public interface RoomDao extends BaseDao<RoomVO>
{
    List<RoomVO> queryPageList(@Param("vo") RoomVO vo, @Param("page") PageVO page);
}
