package com.zte.wangyong.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import com.zte.wangyong.dao.DiskInfoDao;
import com.zte.wangyong.pojo.DiskInfo;
import com.zte.wangyong.service.DiskInfoService;

@Service("diskInfoService")
public class DiskInfoServiceImpl implements DiskInfoService {
	@Resource(name = "diskInfoDao")
	public DiskInfoDao diskInfoDao;
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

	public List<DiskInfo> execCmd(String command, String user, String passwd, String host) throws JSchException {
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

					// } else {
					// List<Integer> mylist = new ArrayList<Integer>();
					// for (DiskInfo diskInfo : diskInfoLists) {
					// mylist.add(diskInfo.getId());
					// }
					// minIndex = Collections.min(mylist);
					// DiskInfo diskInfo = new DiskInfo();
					// diskInfo.setId(minIndex + count);
					// diskInfo.setFilesystem(filesystem);
					// diskInfo.setSize(size);
					// diskInfo.setUsed(used);
					// diskInfo.setAvail(avail);
					// diskInfo.setUseage(useage);
					// diskInfo.setMountedon(mountedon);
					// diskInfo.setOperatingtime(operatingtime);
					// count++;
					// this.diskInfoDao.updateByPrimaryKey(diskInfo);
					// }
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
