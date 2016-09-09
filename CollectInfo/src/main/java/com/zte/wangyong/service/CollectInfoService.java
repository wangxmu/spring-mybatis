package com.zte.wangyong.service;

import java.util.List;

import com.jcraft.jsch.JSchException;
import com.zte.wangyong.pojo.MemInfo;
import com.zte.wangyong.pojo.DiskInfo;

public interface CollectInfoService {
	public void connect(String user, String passwd, String host) throws JSchException;
	public List<DiskInfo> execDiskCmd(String command, String user, String passwd, String host) throws JSchException;
	public MemInfo execMemCmd(String command, String user, String passwd, String host) throws JSchException;
}
