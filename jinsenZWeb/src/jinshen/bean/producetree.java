package jinshen.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

//砍伐木材进场表格
public class producetree {
	private double workid;
	private String cutNum;//采伐证号
	private double yeart;
	private double montht;
	private double dayt;
	private String cutSite;
	private String checkSite;
	private String carNumber;
	private String yard;
	private String treetype;
	private double tlong;
	private double tradius;
	private double num;
	private double tvolume;
	private String surveyor;
	private String forester;
	private String woodmanage;
	
	private String projectPackageName;//工程包
	private String contractionSide;//施工方
	
	private Date cutdate;
   
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
	
	public double  getYear() {
		return yeart;
	}
	public void setYear(double yeart) {
		this.yeart=yeart;
	}
	public double getMonth() {
		return montht;
	}
	public void setMonth(double montht) {
		this.montht=montht;
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
    public String getYard() {
 	   return yard;
    }
    public void setYard(String yard) {
 	   this.yard = yard;
    }
    public String getTreetype() {
		return treetype;
	}
	public void setTreetype(String treetype) {
		this.treetype = treetype;
	}
	public double getTlong() {
		return tlong;
	}
	public void setTlong(double tlong) {
		this.tlong = tlong;
	}
	public double getTradius() {
		return tradius;
	}
	public void setTradius(double tradius) {
		this.tradius = tradius;
	}
	public double getNum() {
		return num;
	}
	public void setNum(double num) {
		this.num = num;
	}
	public double getTvolume() {
		return tvolume;
	}
	public void setTvolume(double tvolume) {
		this.tvolume = tvolume;
	}
    public String getSurveyor() {
    	return surveyor;
    }
    public void setSurveyor(String surveyor) {
    	this.surveyor = surveyor;
    }
    
    public String getForester() {
    	return forester;
    }
    public void setForester(String forester) {
    	this.forester = forester;
    }
    
    public String getWoodmanage() {
    	return woodmanage;
    }
    public void setWoodmanage(String woodmanage) {
    	this.woodmanage = woodmanage;
    }
	public double getDayt() {
		return dayt;
	}
	public void setDayt(double dayt) {
		this.dayt = dayt;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getCutdate() {
		return cutdate;
	}
	public void setCutdate(Date cutdate) {
		this.cutdate = cutdate;
	}
	public String getProjectPackageName() {
		return projectPackageName;
	}
	public void setProjectPackageName(String projectPackageName) {
		this.projectPackageName = projectPackageName;
	}
	public String getContractionSide() {
		return contractionSide;
	}
	public void setContractionSide(String contractionSide) {
		this.contractionSide = contractionSide;
	}
    

}