package com.shiyanlou.shinelon.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shiyanlou.shinelon.movie.dao.CityTimesDao;
import com.shiyanlou.shinelon.movie.dao.HourTimesDao;
import com.shiyanlou.shinelon.movie.domain.CityTimes;
import com.shiyanlou.shinelon.movie.domain.HourTimes;

@Controller
public class LogsController {

    @Autowired
    public CityTimesDao cityTimesDao;
    @Autowired
    public HourTimesDao hourTimesDao;


    @RequestMapping("/cityTimes")
    public ModelAndView cityTimes(){
        return new ModelAndView("cityTimes");
    }

    @RequestMapping("/hourTimes")
    public ModelAndView hourTimes(){
        return new ModelAndView("hourTimes");
    }


    @RequestMapping("/showCity")
    @ResponseBody
    public List<CityTimes> showCity() throws Exception{
        return cityTimesDao.selectCityTimes();
    }

    @RequestMapping("/showHour")
    @ResponseBody
    public List<HourTimes> showHour() throws Exception{
        return hourTimesDao.selectCityTimes();
    }

    @RequestMapping("/second")
    public String second(){
        return "cityTimes";
    }
    
    
    @RequestMapping("/get")
    public ModelAndView get() {
    	return new ModelAndView("get");
    }


}