package cn.sujunhua.dao;

import java.util.List;

import cn.sujunhua.pojo.Secret;

public interface SecretDao {
   //查找全部密保问题
   public List<Secret> selectSecret();
   
}
