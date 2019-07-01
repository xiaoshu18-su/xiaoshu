package cn.sujunhua.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.sujunhua.pojo.Usertype;

public interface UsertypeDao {
   //模糊查询
   public List<Usertype> selectUsertype(@Param("usertype_name")String usertype_name);
   
   //根据id查name
   public Usertype selectNameByid(Integer usertype_id);
   
   //根据名字精准查询
   public Usertype selectUserByName(@Param("usertype_name")String usertype_name);
}
