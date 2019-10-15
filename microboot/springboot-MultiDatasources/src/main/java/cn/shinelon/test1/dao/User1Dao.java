package cn.shinelon.test1.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import cn.shinelon.entity.User;
@CacheConfig(cacheNames="ehcache")
public interface User1Dao {
	@Insert("insert into user values(null,#{username},#{age})")
	public void insert(@Param("username")String username,@Param("age")int age);
	
	//使用ehcache缓存技术
	@Select("select * from user where id=#{id}")
	@Cacheable
	public User select(@Param("id")int id);
}
