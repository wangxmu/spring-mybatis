package com.zte.wangyong.dao;

import java.util.List;

import com.zte.wangyong.pojo.DiskInfo;

public interface DiskInfoDao {
    int deleteByPrimaryKey(Integer id);
    
    void truncateAll();

    int insert(DiskInfo record);

    int insertSelective(DiskInfo record);

    DiskInfo selectByPrimaryKey(Integer id);
    
    List<DiskInfo> selectAll();

    int updateByPrimaryKeySelective(DiskInfo record);

    int updateByPrimaryKey(DiskInfo record);
}