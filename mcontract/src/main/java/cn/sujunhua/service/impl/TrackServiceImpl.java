package cn.sujunhua.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.sujunhua.dao.TrackDao;
import cn.sujunhua.pojo.Track;
import cn.sujunhua.service.TrackService;

@Service
@Scope("prototype")
public class TrackServiceImpl implements TrackService{
    @Autowired
    private TrackDao trackDao;
	@Override
	public List<Track> selectTrackByContractid(String track_contractid) {
		return trackDao.selectTrackByContractid(track_contractid);
	}
	@Override
	public Integer addTract(Track track) {
		return trackDao.addTract(track);
	}
}
