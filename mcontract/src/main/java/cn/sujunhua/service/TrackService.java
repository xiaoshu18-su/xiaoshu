package cn.sujunhua.service;

import java.util.List;

import cn.sujunhua.pojo.Track;

public interface TrackService {
   //根据合同id查询收款记录
   public List<Track> selectTrackByContractid(String track_contractid);
   
   //添加收款跟踪
   public Integer addTract(Track track);
}
