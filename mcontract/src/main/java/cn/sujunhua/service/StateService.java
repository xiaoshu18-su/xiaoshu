package cn.sujunhua.service;

import java.util.List;

import cn.sujunhua.pojo.State;

public interface StateService {
	//模糊查询
    public List<State> selectStateLikeName(String state_name);
    //精确查询
    public State selectStateByName(String state_name);
    public List<State> selectAllState();
}
