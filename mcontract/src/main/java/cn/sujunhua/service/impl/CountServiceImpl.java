package cn.sujunhua.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.sujunhua.common.utils.CountNumer;
import cn.sujunhua.dao.CountDao;
import cn.sujunhua.pojo.Count;
import cn.sujunhua.service.CountService;

@Service
@Scope("prototype")
public class CountServiceImpl implements CountService{
    
	@Autowired
	private CountDao countDao;

	@Override
	public List<Count> selectCountUser() {
		return countDao.selectAllCount();
	}

	@Override
	public List<CountNumer> selectCountNumByDay(Integer day) {
		List<Count> list = countDao.selectCountByDay(day);
		List<CountNumer> countNumers=new ArrayList<>();
		String date="";
		CountNumer countNumer=null;
		for (Count count : list) {
			if (!date.equals(count.getCount_date().substring(0, 10))) {
				countNumer=new CountNumer();
				countNumer.setDate(count.getCount_date().substring(5,10));
				countNumer.setNumber(1);
				countNumers.add(countNumer);
			}
			if (date.equals(count.getCount_date().substring(0, 10))) {
				countNumer.setNumber(countNumer.getNumber()+1);
			}
			date=count.getCount_date().substring(0, 10);
		}
		return countNumers;
	}

	@Override
	public List<CountNumer> selectCountNumByMonth(Integer month) {
		List<Count> list = countDao.selectCountByMonth(month);
		List<CountNumer> countNumers=new ArrayList<>();
		String date="";
		CountNumer countNumer=null;
		for (Count count : list) {
			if (!date.equals(count.getCount_date().substring(0, 10))) {
				countNumer=new CountNumer();
				countNumer.setDate(count.getCount_date().substring(5,10));
				countNumer.setNumber(1);
				countNumers.add(countNumer);
			}
			if (date.equals(count.getCount_date().substring(0, 10))) {
				countNumer.setNumber(countNumer.getNumber()+1);
			}
			date=count.getCount_date().substring(0, 10);
		}
		return countNumers;
	}

	@Override
	public String deleteCountByDay(Integer day) {
		//创建保存用户登录记录的id
		List<Integer> countids = new ArrayList<Integer>();
		//查出近来7天或30天的记录
		List<Count> counts = countDao.selectCountByDay(50);
		if (counts.size()>0) {
			for (Count count : counts) {
				countids.add(count.getCount_id());
			}
		}
		Integer row = 0;
		if (countids.size()>0) {
			row = countDao.deleteCountNotInId(countids);
		}
		if (row>0) {
			return "yes";
		}
		else return "no";
	}

	@Override
	public String deleteAllCount() {
		Integer row = countDao.deleteAllCount();
		if (row>0) {
			return "yes";
		}
		else return "no";
	}
}
