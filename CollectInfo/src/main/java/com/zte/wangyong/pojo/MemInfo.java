package com.zte.wangyong.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class MemInfo {
    private Integer id;

    private BigDecimal totalmem;

    private BigDecimal usedmem;

    private BigDecimal freemem;

	private BigDecimal useage;
    
    private Timestamp operatingtime;

    public Timestamp getOperatingtime() {
		return operatingtime;
	}

	public void setOperatingtime(Timestamp operatingtime) {
		this.operatingtime = operatingtime;
	}
	
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getTotalmem() {
        return totalmem;
    }

    public void setTotalmem(BigDecimal totalmem) {
        this.totalmem = totalmem;
    }

    public BigDecimal getUsedmem() {
        return usedmem;
    }

    public void setUsedmem(BigDecimal usedmem) {
        this.usedmem = usedmem;
    }

    public BigDecimal getFreemem() {
        return freemem;
    }

    public void setFreemem(BigDecimal freemem) {
        this.freemem = freemem;
    }

    public BigDecimal getUseage() {
        return useage;
    }

    public void setUseage(BigDecimal useage) {
        this.useage = useage;
    }
}