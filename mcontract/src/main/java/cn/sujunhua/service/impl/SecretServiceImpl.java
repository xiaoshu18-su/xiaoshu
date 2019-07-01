package cn.sujunhua.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.sujunhua.dao.SecretDao;
import cn.sujunhua.pojo.Secret;
import cn.sujunhua.service.SecretService;

@Service
@Scope("prototype")
public class SecretServiceImpl implements SecretService {
    
	@Autowired
	private SecretDao secretDao;
	
	@Override
	public List<Secret> selectSecret() {
		return secretDao.selectSecret();
	}

}
