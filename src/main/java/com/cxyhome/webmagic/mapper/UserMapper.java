package com.cxyhome.webmagic.mapper;

import com.cxyhome.webmagic.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

  User getUserById(@Param("id") int id);
}