package cn.sujunhua.service;

import java.util.List;

import cn.sujunhua.pojo.Status;

public interface StatusService {
   public Status selectStatusByName(String status_name);
   public Status selectStatusByID(Integer status_id);
   public List<Status> selectAllStatus();
}
