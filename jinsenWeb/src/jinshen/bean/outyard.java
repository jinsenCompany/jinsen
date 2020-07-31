package jinshen.bean;
//用于出厂木材数据保存到数据库中
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class outyard {
	private double workid;
	private Date   yarddate;
	private String carNumber;
	private String yard;
	private String batchNum;//检尺标准
	private String surveyor;
	private double toltree;
	private double tolstere;
	private String Contractnum;//销售合同号
	private String saleCalloutorderid;//销售调令
	private String section;
	public double getWorkid() {
		return workid;
	}
	public void setWorkid(double workid) {
		this.workid = workid;
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
	public String getBatchNum() {
		return batchNum;
	}
	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}
	public String getSurveyor() {
		return surveyor;
	}
	public void setSurveyor(String surveyor) {
		this.surveyor = surveyor;
	}
	public double getToltree() {
		return toltree;
	}
	public void setToltree(double toltree) {
		this.toltree = toltree;
	}
	public double getTolstere() {
		return tolstere;
	}
	public void setTolstere(double tolstere) {
		this.tolstere = tolstere;
	}
	public String getContractnum() {
		return Contractnum;
	}
	public void setContractnum(String contractnum) {
		Contractnum = contractnum;
	}
	public String getSaleCalloutorderid() {
		return saleCalloutorderid;
	}
	public void setSaleCalloutorderid(String saleCalloutorderid) {
		this.saleCalloutorderid = saleCalloutorderid;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	
	

}
