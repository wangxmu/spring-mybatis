package com.zte.wangyong.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.zte.wangyong.pojo.MemInfo;
import com.zte.wangyong.dao.DiskInfoDao;
import com.zte.wangyong.dao.MemInfoDao;
import com.zte.wangyong.pojo.DiskInfo;
import com.zte.wangyong.service.CollectInfoService;

@Service("collectInfoService")
public class CollectInfoServiceImpl implements CollectInfoService {
	@Resource
	public DiskInfoDao diskInfoDao;
	@Resource
	public MemInfoDao memInfoDao;
	private JSch jsch;
	private Session session;
	private int minIndex;

	public void connect(String user, String passwd, String host) throws JSchException {
		jsch = new JSch();
		session = jsch.getSession(user, host, 22);
		session.setPassword(passwd);

		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);

		session.connect();
	}

	public MemInfo execMemCmd(String command, String user, String passwd, String host) throws JSchException {
		connect(user, passwd, host);
		BufferedReader reader = null;
		Channel channel = null;
		BigDecimal memUseage = null;

		try {
			channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);

			channel.setInputStream(null);
			((ChannelExec) channel).setErrStream(System.err);

			channel.connect();
			InputStream input = channel.getInputStream();
			reader = new BufferedReader(new InputStreamReader(input));
			String buf = null;
			BigDecimal totalMem = null;
			BigDecimal freeMem = null;
			BigDecimal useMem = null;
			while ((buf = reader.readLine()) != null) {
				System.out.println(buf);
				String[] meminfo = buf.split("\\s+");
				if (meminfo[0].startsWith("Mem")) {
					totalMem = new BigDecimal(meminfo[1]);
					useMem = new BigDecimal(meminfo[2]);
					freeMem = new BigDecimal(meminfo[3]);
					memUseage = useMem.divide(totalMem, 2, BigDecimal.ROUND_HALF_EVEN);
				}
			}

			List<MemInfo> MemInfoLists = this.memInfoDao.selectAll();
			if (MemInfoLists.size() == 0) {
				MemInfo memInfo = new MemInfo();
				memInfo.setId(1);
				memInfo.setTotalmem(totalMem);
				memInfo.setUsedmem(useMem);
				memInfo.setFreemem(freeMem);
				memInfo.setUseage(memUseage);
				memInfo.setOperatingtime(new Timestamp(System.currentTimeMillis()));

				this.memInfoDao.insert(memInfo);
			} else {
				List<Integer> mylist = new ArrayList<Integer>();
				for (MemInfo memInfo : MemInfoLists) {
					mylist.add(memInfo.getId());
				}
				minIndex = Collections.min(mylist);
				MemInfo memInfo = new MemInfo();
				memInfo.setId(minIndex);
				memInfo.setTotalmem(totalMem);
				memInfo.setUsedmem(useMem);
				memInfo.setFreemem(freeMem);
				memInfo.setUseage(memUseage);
				memInfo.setOperatingtime(new Timestamp(System.currentTimeMillis()));
				this.memInfoDao.updateByPrimaryKey(memInfo);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			channel.disconnect();
			session.disconnect();

		}
		return this.memInfoDao.selectByPrimaryKey(minIndex);
	}

	public List<DiskInfo> execDiskCmd(String command, String user, String passwd, String host) throws JSchException {
		connect(user, passwd, host);
		Channel channel = null;
		BufferedReader reader = null;

		try {
			channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);

			channel.setInputStream(null);
			((ChannelExec) channel).setErrStream(System.err);

			channel.connect();
			InputStream input = channel.getInputStream();
			reader = new BufferedReader(new InputStreamReader(input));
			String buf = null;
			String filesystem = null;
			String size = null;
			String used = null;
			String avail = null;
			String useage = null;
			String mountedon = null;
			Date operatingtime = null;
			int count = 0;
			this.diskInfoDao.truncateAll();
			while ((buf = reader.readLine()) != null) {
				System.out.println(buf);
				String[] diskinfo = buf.split("\\s+");
				if (!(diskinfo[0].equals("Filesystem"))) {
					filesystem = diskinfo[0];
					size = diskinfo[1];
					used = diskinfo[2];
					avail = diskinfo[3];
					useage = diskinfo[4];
					mountedon = diskinfo[5];
					operatingtime = new Date();

					DiskInfo diskInfo = new DiskInfo();
					count++;
					diskInfo.setId(count);
					diskInfo.setFilesystem(filesystem);
					diskInfo.setSize(size);
					diskInfo.setUsed(used);
					diskInfo.setAvail(avail);
					diskInfo.setUseage(useage);
					diskInfo.setMountedon(mountedon);
					diskInfo.setOperatingtime(operatingtime);

					this.diskInfoDao.insert(diskInfo);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			channel.disconnect();
			session.disconnect();
		}
		return this.diskInfoDao.selectAll();
	}
}
