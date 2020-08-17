package jinshen.bean;
//用于把销售的木材信息显示在销售管理员页面上
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class salesman {
	private String provider;
	private String demander;
	private String saleCalloutOrder;
	private String contractnum;
	private Date   yarddate;
	private String carNumber;
	private String yard;
	private String surveyor;
   public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getDemander() {
		return demander;
	}
	public void setDemander(String demander) {
		this.demander = demander;
	}
	public String getSaleCalloutOrder() {
		return saleCalloutOrder;
	}
	public void setSaleCalloutOrder(String saleCalloutOrder) {
		this.saleCalloutOrder = saleCalloutOrder;
	}
	public String getContractnum() {
		return contractnum;
	}
	public void setContractnum(String contractnum) {
		this.contractnum = contractnum;
	}
@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getYarddate() {
    	return yarddate;
    }
    public void setYarddate(Date yarddate) {
    	this.yarddate = yarddate;
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
   public String getSurveyor() {
 	     return surveyor;
    }
    public void setSurveyor(String surveyor) {
 	    this.surveyor = surveyor;
    }
}
