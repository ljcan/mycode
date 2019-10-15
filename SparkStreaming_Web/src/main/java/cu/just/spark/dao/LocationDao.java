package cu.just.spark.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.shiyanlou.shinelon.movie.domain.CityTimes;
import com.shiyanlou.shinelon.movie.utils.MysqlUtil;

import cu.just.spark.domain.CourseClickCount;
import cu.just.spark.domain.Location;
import cu.just.spark.utils.HBaseUtils;
import groovy.util.logging.Commons;

/**
 * @author shinelon
 *
 */
@Component
public class LocationDao {
	
	 private static MysqlUtil mysqlUtil;

	    public List<Location> map() throws Exception{
	        List<Location> list = new ArrayList<Location>();
	        Connection connection=null;
	        PreparedStatement psmt=null;
	        try {
	            connection = MysqlUtil.getConnection();
	            psmt = connection.prepareStatement("select longitude,latitude,count(*) from location where "
	            		+ "time>unix_timestamp(date_sub(current_timestamp(),interval 10 minute))*1000 "
	            		+ "group by longitude,latitude");
	            ResultSet resultSet = psmt.executeQuery();
	            while (resultSet.next()) {
	            	Location location = new Location();
	                location.setLongitude(resultSet.getDouble(1));
	                location.setLatitude(resultSet.getDouble(2));
	                location.setCount(resultSet.getInt(3));
	                list.add(location);
	            }
	        }catch (Exception e){
	            e.printStackTrace();
	        }finally {
	            MysqlUtil.release();
	        }
	        return list;
	    }

}
