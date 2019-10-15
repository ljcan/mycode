package cu.just.spark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cu.just.spark.dao.LocationDao;
import cu.just.spark.domain.Location;

@RestController
public class MapController {
	
	@Autowired
	public LocationDao locationDao;
	
	@RequestMapping("/storm")
	public ModelAndView storm() {
		return new ModelAndView("map");
	}
	
	@RequestMapping("/get_map")
	@ResponseBody
	public List<Location> getMap() throws Exception{
		return locationDao.map();
	}

}
