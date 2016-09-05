package com.zte.wangyong.pojo;

import java.util.Date;

public class DiskInfo {
    private Integer id;

    private String filesystem;

    private Double size;

    private Double used;

    private Double avail;

    private Double useage;

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
        this.filesystem = filesystem == null ? null : filesystem.trim();
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Double getUsed() {
        return used;
    }

    public void setUsed(Double used) {
        this.used = used;
    }

    public Double getAvail() {
        return avail;
    }

    public void setAvail(Double avail) {
        this.avail = avail;
    }

    public Double getUseage() {
        return useage;
    }

    public void setUseage(Double useage) {
        this.useage = useage;
    }

    public String getMountedon() {
        return mountedon;
    }

    public void setMountedon(String mountedon) {
        this.mountedon = mountedon == null ? null : mountedon.trim();
    }

    public Date getOperatingtime() {
        return operatingtime;
    }

    public void setOperatingtime(Date operatingtime) {
        this.operatingtime = operatingtime;
    }
}