package cn.sujunhua.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.sujunhua.dao.UsertypeDao;
import cn.sujunhua.pojo.Usertype;
import cn.sujunhua.service.UsertypeService;

@Service
@Scope("prototype")
public class UsertypeServiceImpl implements UsertypeService {
    
	@Autowired
	private UsertypeDao usertypeDao;
	
	@Override
	public List<Usertype> selectUsertype(String usertype_name) {
		return usertypeDao.selectUsertype(usertype_name);
	}

	@Override
	public Usertype selectNameByid(Integer usertype_id) {
		return usertypeDao.selectNameByid(usertype_id);
	}

	@Override
	public Usertype selectUserByName(String usertype_name) {
		return usertypeDao.selectUserByName(usertype_name);
	}
}
