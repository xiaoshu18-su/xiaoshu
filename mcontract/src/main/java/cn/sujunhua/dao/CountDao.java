package cn.sujunhua.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.sujunhua.pojo.Count;

public interface CountDao {
   //添加用户登录记录
   public Integer addCountRecord(Count count);
   
   //查询用户登录记录
   public List<Count> selectAllCount();
   
   //查询7天或者30天的用户登录记录
   public List<Count> selectCountByDay(@Param("day")Integer day);
   
   //查询本月或上个月用户访问的情况
   public List<Count> selectCountByMonth(@Param("month")Integer month);
   
   //查询登录记录 not  in（id）
   public List<Count> selectCountNotInId(@Param("countids")List<Integer> countids);
   
   //删除登录记录 not  in（id）
   public Integer deleteCountNotInId(@Param("countids")List<Integer> countids);
   
   //清除所有登录记录
   public Integer deleteAllCount();
}
