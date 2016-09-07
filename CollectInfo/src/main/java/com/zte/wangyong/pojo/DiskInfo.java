package com.zte.wangyong.pojo;

import java.util.Date;

public class DiskInfo {
	private Integer id;

	private String filesystem;

	private String size;

	private String used;

	private String avail;

	private String useage;

	private String mountedon;

	private Date operatingtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFilesystem() {
		return filesystem;
	}

	public void setFilesystem(String filesystem) {
		this.filesystem = filesystem;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}

	public String getAvail() {
		return avail;
	}

	public void setAvail(String avail) {
		this.avail = avail;
	}

	public String getUseage() {
		return useage;
	}

	public void setUseage(String useage) {
		this.useage = useage;
	}

	public String getMountedon() {
		return mountedon;
	}

	public void setMountedon(String mountedon) {
		this.mountedon = mountedon;
	}

	public Date getOperatingtime() {
		return operatingtime;
	}

	public void setOperatingtime(Date operatingtime) {
		this.operatingtime = operatingtime;
	}

}