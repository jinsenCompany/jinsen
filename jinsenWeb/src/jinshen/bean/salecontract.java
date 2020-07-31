package jinshen.bean;
//销售合同表
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class salecontract {
	private int contractid;
	private String contractnum;//销售合同编号
	private String provider;
	private String demander;
	private String treetype;
	private String tlong;
	private String tradius;
	private double unitprice;
	private String deliveryplace;
	private double treenumber;
	private String cost;
	private String measurestard;//检测标准
	private String settlemethod;
	private Date starttime;
	private Date endtime;
	private String margin;
	private String contractfile;
	public int getContractid() {
		return contractid;
	}
	public void setContractid(int contractid) {
		this.contractid = contractid;
	}
	public String getContractnum() {
		return contractnum;
	}
	public void setContractnum(String contractnum) {
		this.contractnum = contractnum;
	}
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
	public String getTreetype() {
		return treetype;
	}
	public void setTreetype(String treetype) {
		this.treetype = treetype;
	}
	public String getTlong() {
		return tlong;
	}
	public void setTlong(String tlong) {
		this.tlong = tlong;
	}
	public String getTradius() {
		return tradius;
	}
	public void setTradius(String tradius) {
		this.tradius = tradius;
	}
	public double getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(double unitprice) {
		this.unitprice = unitprice;
	}
	public String getDeliveryplace() {
		return deliveryplace;
	}
	public void setDeliveryplace(String deliveryplace) {
		this.deliveryplace = deliveryplace;
	}
	public double getTreenumber() {
		return treenumber;
	}
	public void setTreenumber(double treenumber) {
		this.treenumber = treenumber;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getMeasurestard() {
		return measurestard;
	}
	public void setMeasurestard(String measurestard) {
		this.measurestard = measurestard;
	}
	public String getSettlemethod() {
		return settlemethod;
	}
	public void setSettlemethod(String settlemethod) {
		this.settlemethod = settlemethod;
	}
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getMargin() {
		return margin;
	}
	public void setMargin(String margin) {
		this.margin = margin;
	}
	public String getContractfile() {
		return contractfile;
	}
	public void setContractfile(String contractfile) {
		this.contractfile = contractfile;
	}
	

}
