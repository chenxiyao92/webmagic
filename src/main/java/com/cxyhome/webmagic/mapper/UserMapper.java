package com.cxyhome.webmagic.mapper;

import com.cxyhome.webmagic.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

//  @Select("SELECT * FROM tm_user WHERE id = #{id}")
  User getUserById(int id);
}