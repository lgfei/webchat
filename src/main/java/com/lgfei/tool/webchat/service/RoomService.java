package com.lgfei.tool.webchat.service;

import com.lgfei.tool.webchat.vo.PageResult;
import com.lgfei.tool.webchat.vo.RoomVO;

public interface RoomService extends BaseService<RoomVO>
{
    PageResult<RoomVO> findPageList(RoomVO vo, Integer start, Integer pageSize);
}
