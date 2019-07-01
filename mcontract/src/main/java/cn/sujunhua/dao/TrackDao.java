package cn.sujunhua.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.sujunhua.pojo.Track;

public interface TrackDao {
   //根据合同id查询收款记录
   public List<Track> selectTrackByContractid(@Param("track_contractid")String track_contractid);
   
   //添加收款跟踪
   public Integer addTract(Track track);
}
