package cn.sujunhua.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.sujunhua.dao.StatusDao;
import cn.sujunhua.pojo.Status;
import cn.sujunhua.service.StatusService;

@Service
@Scope("prototype")
public class StatusServiceImpl implements StatusService{
	@Autowired
    private StatusDao statusDao;

	@Override
	public Status selectStatusByName(String status_name) {
		return statusDao.selectStatusByName(status_name);
	}

	@Override
	public Status selectStatusByID(Integer status_id) {
		return statusDao.selectStatusByID(status_id);
	}

	@Override
	public List<Status> selectAllStatus() {
		return statusDao.selectAllStatus();
	}
}
