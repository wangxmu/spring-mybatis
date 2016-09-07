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
import com.zte.wangyong.service.DiskInfoService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CollectInfoController {
	@Resource
	public DiskInfoService diskInfoService;
	
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
	
	@RequestMapping("/showMemInfo")
	public String toIndex(HttpServletRequest request,Model model) throws JSchException {
		List<DiskInfo> diskInfo = this.diskInfoService.execCmd("df -h","sx","sx","10.45.44.208");
		model.addAttribute("diskInfo", diskInfo);
		return "showDiskInfo";
	}
	
}