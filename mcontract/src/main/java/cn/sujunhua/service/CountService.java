package cn.sujunhua.service;

import java.util.List;

import cn.sujunhua.common.utils.CountNumer;
import cn.sujunhua.pojo.Count;

public interface CountService {
	
	//查询所有用户登录信息
	public List<Count> selectCountUser();
	
	//按天数查询最近用户登录情况
	public List<CountNumer> selectCountNumByDay(Integer day);
	
	//按月查找用户登录情况
	public List<CountNumer> selectCountNumByMonth(Integer month);
	 
	//删除用户登录记录  选择保留7天或者保留30天
	public String deleteCountByDay(Integer day);
	
    //清除所有登录记录
    public String deleteAllCount();
}
