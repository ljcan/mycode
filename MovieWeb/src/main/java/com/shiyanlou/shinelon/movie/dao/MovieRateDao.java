package com.shiyanlou.shinelon.movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.shiyanlou.shinelon.movie.domain.MovieRate;
import com.shiyanlou.shinelon.movie.utils.MysqlUtil;
@Component
public class MovieRateDao {
	
	public List<MovieRate> getMovieRate(){
		List<MovieRate> list = new ArrayList<MovieRate>();
		Connection conn=null;
		PreparedStatement psmt = null;
		ResultSet resultSet=null;
		String sql="select * from movie_rate";
		try {
			conn=MysqlUtil.getConnection();
			psmt=conn.prepareStatement(sql);
			resultSet=psmt.executeQuery();
			while(resultSet.next()) {
				MovieRate movieRate=new MovieRate();
				movieRate.setMovieId(resultSet.getInt(1));
				movieRate.setRate(resultSet.getInt(2));
				list.add(movieRate);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MysqlUtil.release();
		}
		return list;
	}
	
//	public static void main(String[] args) {
//	List<MovieRate> list = new ArrayList<MovieRate>();
//	list=getMovieRate();
//	for(MovieRate w:list) {
//		System.out.println(w.toString());
//	}
//}

}
