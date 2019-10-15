package com.shiyanlou.shinelon.movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.shiyanlou.shinelon.movie.domain.WeekCnt;
import com.shiyanlou.shinelon.movie.utils.MysqlUtil;


@Component
public class WeekCntDao {
	
	public List<WeekCnt> getWeekCnt(){
		List<WeekCnt> list = new ArrayList<WeekCnt>();
		Connection conn=null;
		PreparedStatement psmt = null;
		ResultSet resultSet=null;
		String sql="select * from week_cnt";
		try {
			conn=MysqlUtil.getConnection();
			psmt=conn.prepareStatement(sql);
			resultSet=psmt.executeQuery();
			while(resultSet.next()) {
				WeekCnt weekCnt=new WeekCnt();
				weekCnt.setWeek(resultSet.getInt(1));
				weekCnt.setCnt(resultSet.getInt(2));
				list.add(weekCnt);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MysqlUtil.release();
		}
		return list;
	}
	
	
//	public static void main(String[] args) {
//		List<WeekCnt> list = new ArrayList<WeekCnt>();
//		list=getWeekCnt();
//		for(WeekCnt w:list) {
//			System.out.println(w.toString());
//		}
//	}

}
