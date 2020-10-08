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
	private String deliveryplace;//交货地点（货场）
	private String section;//货场分区
	private String treenumber;//合计数量
	private String cost;//付费甲方
	private String costB;//付费乙方
	private String measurestard;//检测标准
	private String settlemethod;
	private Date starttime;
	private Date endtime;
	private String margin;
	private String contractfile;
	private String marknumber;//表品号
	private String contractsigner;//合同签订人
	private String creatTime;//创建时间
	private int contractstatus;//合同状态，0：未完成，1已完成
	private String cutnum;//包产包包产包销的采伐证
	
	private String useTreenum;//已经使用数量
	private String surplusTreenum;//剩余
	private String percentageTreenum;//使用百分比
	private String begin;
	private String end;
	
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
	public String getTreenumber() {
		return treenumber;
	}
	public void setTreenumber(String treenumber) {
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
	public String getMarknumber() {
		return marknumber;
	}
	public void setMarknumber(String marknumber) {
		this.marknumber = marknumber;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getCostB() {
		return costB;
	}
	public void setCostB(String costB) {
		this.costB = costB;
	}
	public String getContractsigner() {
		return contractsigner;
	}
	public void setContractsigner(String contractsigner) {
		this.contractsigner = contractsigner;
	}
	public String getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}
	public String getUseTreenum() {
		return useTreenum;
	}
	public void setUseTreenum(String useTreenum) {
		this.useTreenum = useTreenum;
	}
	public String getSurplusTreenum() {
		return surplusTreenum;
	}
	public void setSurplusTreenum(String surplusTreenum) {
		this.surplusTreenum = surplusTreenum;
	}
	public String getPercentageTreenum() {
		return percentageTreenum;
	}
	public void setPercentageTreenum(String percentageTreenum) {
		this.percentageTreenum = percentageTreenum;
	}
	public String getBegin() {
		return begin;
	}
	public void setBegin(String begin) {
		this.begin = begin;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public int getContractstatus() {
		return contractstatus;
	}
	public void setContractstatus(int contractstatus) {
		this.contractstatus = contractstatus;
	}
	public String getCutnum() {
		return cutnum;
	}
	public void setCutnum(String cutnum) {
		this.cutnum = cutnum;
	}
	
	
	

}
