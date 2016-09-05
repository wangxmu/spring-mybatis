package com.zte.wangyong.service;

import java.util.List;

import com.jcraft.jsch.JSchException;
import com.zte.wangyong.pojo.DiskInfo;

public interface DiskInfoService {
	public void connect(String user, String passwd, String host) throws JSchException;
	public List<DiskInfo> execCmd(String command, String user, String passwd, String host) throws JSchException;
}
