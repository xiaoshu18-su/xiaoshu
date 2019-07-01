package cn.sujunhua.service;

import java.util.List;

import cn.sujunhua.pojo.Usertype;

public interface UsertypeService {
	public List<Usertype> selectUsertype(String usertype_name);
	
	public Usertype selectNameByid(Integer usertype_id);
	
    //根据名字精准查询
    public Usertype selectUserByName(String usertype_name);
}
