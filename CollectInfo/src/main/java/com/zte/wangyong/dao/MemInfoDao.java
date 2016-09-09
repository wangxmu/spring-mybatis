package com.zte.wangyong.dao;

import java.util.List;

import com.zte.wangyong.pojo.MemInfo;

public interface MemInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MemInfo record);

    int insertSelective(MemInfo record);

    MemInfo selectByPrimaryKey(Integer id);
    
    List<MemInfo> selectAll();

    int updateByPrimaryKeySelective(MemInfo record);

    int updateByPrimaryKey(MemInfo record);   
}