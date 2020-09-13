package jinshen.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

//用于木材销售表格打印
public class treeoutPrint {
	private double workid;
	private double yeart;
	private double montht;
	private Date yarddate;
	private String carNumber;
	private String yard;
	private String batchNum;//检尺标准
	private String section;
	private String treetype;
	private double tlong;
	private double tradius;
	private double num;
	private double tvolume;
	private double unitprice;
	private double price;
	private String surveyor;
	private String saleman;
	
	private String contractnum;//销售合同
	private String saleCalloutOrder;//销售调令
	public double getWorkid() {
		return workid;
	}
	public void setWorkid(double workid) {
		this.workid = workid;
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
    public String getCheckstd() {
    	return batchNum;
    }
    public void setCheckstd(String batchNum) {
    	this.batchNum=batchNum;
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
	public double getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(double unitprice) {
		this.unitprice = unitprice;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getSurveyor() {
    	return surveyor;
    }
    public void setSurveyor(String surveyor) {
    	this.surveyor = surveyor;
    }
	public String getSaleman() {
    	return saleman;
    }
    public void setSaleman(String saleman) {
    	this.saleman = saleman;
    }
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getYarddate() {
		return yarddate;
	}
	public void setYarddate(Date yarddate) {
		this.yarddate = yarddate;
	}
	public String getContractnum() {
		return contractnum;
	}
	public void setContractnum(String contractnum) {
		this.contractnum = contractnum;
	}
	public String getSaleCalloutOrder() {
		return saleCalloutOrder;
	}
	public void setSaleCalloutOrder(String saleCalloutOrder) {
		this.saleCalloutOrder = saleCalloutOrder;
	}
    

}
