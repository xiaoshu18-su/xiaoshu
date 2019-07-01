package cn.sujunhua.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.sujunhua.dao.StateDao;
import cn.sujunhua.pojo.State;
import cn.sujunhua.service.StateService;

@Service
@Scope("prototype")
public class StateServiceImpl implements StateService{
    
	@Autowired
	private StateDao stateDao;
	@Override
	public List<State> selectStateLikeName(String state_name) {
		return stateDao.selectStateLikeName(state_name);
	}
	@Override
	public State selectStateByName(String state_name) {
		return stateDao.selectStateByName(state_name);
	}
	@Override
	public List<State> selectAllState() {
		return stateDao.selectAllState();
	}

}
