package com.zte.wangyong.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.interceptor.AroundInvoke;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jcraft.jsch.JSchException;
import com.zte.wangyong.pojo.DiskInfo;
import com.zte.wangyong.pojo.MemInfo;
import com.zte.wangyong.service.CollectInfoService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CollectInfoController {
	@Resource
	public CollectInfoService collectInfoService;
	
	private static final Logger logger = LoggerFactory.getLogger(CollectInfoController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("/showInfo")
	public String toIndex(HttpServletRequest request,Model model) throws JSchException {
		MemInfo memInfo = this.collectInfoService.execMemCmd("free -m", "sx", "sx", "10.45.44.208");
		List<DiskInfo> diskInfo = this.collectInfoService.execDiskCmd("df -h","sx","sx","10.45.44.208");
		model.addAttribute("diskInfo", diskInfo);
		model.addAttribute("memInfo", memInfo);		
		return "showInfo";
	}	
}