package cn.shinelon.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import cn.shinelon.vo.User;
public interface UserMapper {
	@Select("select * from user where id=#{id}")
	public User findname(int id);
	
	
	//测试事务
	@Insert("insert into user values(5,#{username},#{pwd},'user')")
	public void insert(@Param("username")String username,@Param("pwd")String pwd);
	
}
