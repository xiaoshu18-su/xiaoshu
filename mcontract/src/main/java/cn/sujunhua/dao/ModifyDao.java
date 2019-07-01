package cn.sujunhua.dao;

import org.apache.ibatis.annotations.Param;

import cn.sujunhua.pojo.Modify;

public interface ModifyDao {
   //精确查询
	public Modify selectModifyByName(@Param("modify_name")String modify_name);
}
