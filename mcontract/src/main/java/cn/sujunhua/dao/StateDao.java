package cn.sujunhua.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.sujunhua.pojo.State;

public interface StateDao {
	//模糊查询
    public List<State> selectStateLikeName(@Param("state_name")String state_name);
    //精确查询
    public State selectStateByName(@Param("state_name")String state_name);
    public List<State> selectAllState();
}
