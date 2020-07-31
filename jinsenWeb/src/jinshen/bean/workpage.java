package jinshen.bean;

import java.sql.Date;

public class workpage {
	private double workid;
	private String cutNum;
	private Date   cutdate;
	private String cutSite;
	private String checkSite;
	private String carNumber;
	private String gpsinfo;//Gps信息
	private String loadphoto;//装车情况
	private String forester;
	
	private double batchNum;
	private double tolTree;
	private double tolStere;
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
    
    public Date getCutdate() {
    	return cutdate;
    }
    public void setCutdate(Date cutdate) {
    	this.cutdate = cutdate;
    }
    
    public String getCutSite() {
    	return cutSite;
    }
    public void setCutSite(String cutSite) {
    	this.cutSite = cutSite;
    }
    
    public String getCheckSite() {
    	return checkSite;
    }
    public void setCheckSite(String checkSite) {
    	this.checkSite = checkSite;
    }
    
	public String getCarNumber() {
	     return carNumber;
    }
    public void setCarNumber(String carNumber) {
   	    this.carNumber = carNumber;
    }
   
    public String getGpsinfo() {
    	return gpsinfo;
    }
    public void setGpsinfo(String gpsinfo) {
    	this.gpsinfo = gpsinfo;
    }
    
    public String getForester() {
    	return forester;
    }
    public void setForester(String forester) {
    	this.forester = forester;
    }
    
    public String getLoadphoto() {
    	return loadphoto;
    }
    public void setLoadphoto(String loadphoto) {
    	this.loadphoto = loadphoto;
    }
    
	public double getBatchNum() {
	     return batchNum;
   }
   public void setBatchNum(double batchNum) {
   	this.batchNum = batchNum;
   }
	public double getTolTree() {
		return tolTree;
	}
	public void setTolTree(double tolTree) {
		this.tolTree = tolTree;
	}
	public double getTolStere() {
		return tolStere;
	}
	public void setTolStere(double tolStere) {
		this.tolStere = tolStere;
	}
}
    