package com.zte.wangyong;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.jcraft.jsch.JSchException;
import com.zte.wangyong.pojo.DiskInfo;
import com.zte.wangyong.service.DiskInfoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class DiskInfoTest {
	private static Logger logger = Logger.getLogger(DiskInfoTest.class);
	// private ApplicationContext ac = null;
	@Resource(name = "diskInfoService")
	public DiskInfoService diskInfoService;

	// @Before
	// public void before() {
	// ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	// userService = (IUserService) ac.getBean("userService");
	// }

	@Test
	public void test() {
		ArrayList<DiskInfo> diskInfolists = null;
		try {
			diskInfolists = (ArrayList<DiskInfo>) diskInfoService.execCmd("df -h", "sx", "sx", "10.45.44.208");
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(user.getUserName());
		// logger.info("ֵ��"+user.getUserName());
		logger.info(JSON.toJSONString(diskInfolists));
	}
}
