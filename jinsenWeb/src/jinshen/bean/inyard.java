package jinshen.bean;
//用于木材进场数据保存至数据库
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class inyard {
	private double workid;
	private String cutNum;
	private Date   yarddate;
	private String cutSite;
	private String carNumber;
	private String yard;
	private double batchNum;
	private String section;//货场分区
	private String surveyor;
	private double toltree;
	private double tolstere;
	public double getWorkid() {
		return workid;
	}
	public void setWorkid(double workid) {
		this.workid = workid;
	}
	
	public String getCutNum() {
	     return cutNum;
    }
    public void setCutNum(String cutNum) {
    	this.cutNum = cutNum;
    }
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getYarddate() {
    	return yarddate;
    }
    public void setYarddate(Date yarddate) {
    	this.yarddate = yarddate;
    }
    
    public String getCutSite() {
    	return cutSite;
    }
    public void setCutSite(String cutSite) {
    	this.cutSite = cutSite;
    }
	public String getCarNumber() {
	     return carNumber;
   }
   public void setCarNumber(String carNumber) {
  	    this.carNumber = carNumber;
   }
   public String getYard() {
	   return yard;
   }
   public void setYard(String yard) {
	   this.yard = yard;
   }
   public double getBatchNum() {
	     return batchNum;
   }
   public void setBatchNum(double batchNum) {
 	   this.batchNum = batchNum;
   }
   public String getSurveyor() {
   	     return surveyor;
   }
   public void setSurveyor(String surveyor) {
   	this.surveyor = surveyor;
   }
   public double getTolTree() {
		return toltree;
	}
	public void setTolTree(double toltree) {
		this.toltree = toltree;
	}
	public double getTolStere() {
		return tolstere;
	}
	public void setTolStere(double tolstere) {
		this.tolstere = tolstere;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section=section;
	}

}
