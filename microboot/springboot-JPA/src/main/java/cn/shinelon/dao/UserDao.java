package cn.shinelon.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.shinelon.vo.User;
public interface UserDao extends JpaRepository<User, Integer>{

}
