package com.lgfei.tool.webchat.dao;

import java.util.Collection;
import java.util.List;

public interface BaseDao<VO>
{
    int insert(VO vo);
    
    int batchInsert(Collection<VO> vos);
    
    int delete(VO vo);
    
    int batchDelete(Collection<VO> vos);
    
    int update(VO vo);
    
    int batchUpdate(Collection<VO> vos);
    
    VO queryById(String id);
    
    List<VO> queryList(VO vo);
    
    Integer queryCount(VO vo);
}
