package cn.shinelon.test2.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface User2Dao {
	@Insert("insert into user values(null,#{username},#{age})")
	public void insert(@Param("username")String username,@Param("age")int age);
}
