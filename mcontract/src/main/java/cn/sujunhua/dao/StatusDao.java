package cn.sujunhua.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.sujunhua.pojo.Status;

public interface StatusDao {
   public Status selectStatusByName(@Param("status_name")String status_name);
   
   public Status selectStatusByID(Integer status_id);
   
   public List<Status> selectAllStatus();
}
