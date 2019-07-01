package cn.sujunhua.service;

import java.util.List;

import cn.sujunhua.pojo.Secret;

public interface SecretService {
   //查找全部密保问题
   public List<Secret> selectSecret();
}
