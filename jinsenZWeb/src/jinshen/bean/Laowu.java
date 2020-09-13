package jinshen.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Laowu {
	private String cutnum;
	//private double projectPackageid;//工程包号
	private String projectPackageName;//工程包名称
	private String forperson;
	private String manageUnit;
	private String treetype;
	private double unitprice;
	private double price;
	private String person;
	private double ttvolume;
	private double tprice;
	
	private String tvolume;
	private String remarks;//备注
	private String freightNum;
	private String unitfreight;
	private String freight;
	private String toprice;
	private String premiumNum;
	private String unitpremium;
	private String premium;
	private String taxationNum;
	private String taxation;
	private String totalAll;
	private Date creatTime;
	
	//木材销售结算
	private String tlong;
	private String num;
	private String tradius;
	private String company;
	private String consignee;//收货单位
	private String tnum;//合计棵树
	private String tworkid;//工单合计
	private String contractnum;
	private String saleCalloutOrder;
	private String yard;
	
	public String getCutnum() {
		return cutnum;
	}
	public void setCutnum(String cutnum) {
		this.cutnum=cutnum;
	}
	/*public double getProjectPackageid() {
		return projectPackageid;
	}
	public void setProjectPackageid(double projectPackageid) {
		this.projectPackageid = projectPackageid;
	}*/
	public String getForperson() {
		return forperson;
	}
	public void setForperson(String forperson) {
		this.forperson = forperson;
	}
	public String getManageUnit() {
		return manageUnit;
	}
	public void setManageUnit(String manageUnit) {
		this.manageUnit = manageUnit;
	}
	public String getTreetype() {
		return treetype;
	}
	public void setTreetype(String treetype) {
		this.treetype = treetype;
	}
	public double getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(double unitprice) {
		this.unitprice = unitprice;
	}
	public double getprice() {
		return price;
	}
	public void setprice(double price) {
		this.price = price;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person=person;
	}
	public double getttvolume() {
		return ttvolume;
	}
	public void setttvolume(double ttvolume) {
		this.ttvolume=ttvolume;
	}
	public double getTprice() {
		return tprice;
	}
	public void setTprice(double tprice) {
		this.tprice = tprice;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getFreightNum() {
		return freightNum;
	}
	public void setFreightNum(String freightNum) {
		this.freightNum = freightNum;
	}
	public String getUnitfreight() {
		return unitfreight;
	}
	public void setUnitfreight(String unitfreight) {
		this.unitfreight = unitfreight;
	}
	public String getFreight() {
		return freight;
	}
	public void setFreight(String freight) {
		this.freight = freight;
	}
	public String getToprice() {
		return toprice;
	}
	public void setToprice(String toprice) {
		this.toprice = toprice;
	}
	public String getPremiumNum() {
		return premiumNum;
	}
	public void setPremiumNum(String premiumNum) {
		this.premiumNum = premiumNum;
	}
	public String getUnitpremium() {
		return unitpremium;
	}
	public void setUnitpremium(String unitpremium) {
		this.unitpremium = unitpremium;
	}
	public String getPremium() {
		return premium;
	}
	public void setPremium(String premium) {
		this.premium = premium;
	}
	public String getTaxationNum() {
		return taxationNum;
	}
	public void setTaxationNum(String taxationNum) {
		this.taxationNum = taxationNum;
	}
	public String getTaxation() {
		return taxation;
	}
	public void setTaxation(String taxation) {
		this.taxation = taxation;
	}
	public String getTotalAll() {
		return totalAll;
	}
	public void setTotalAll(String totalAll) {
		this.totalAll = totalAll;
	}
	public String getTvolume() {
		return tvolume;
	}
	public void setTvolume(String tvolume) {
		this.tvolume = tvolume;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public String getTlong() {
		return tlong;
	}
	public void setTlong(String tlong) {
		this.tlong = tlong;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getTradius() {
		return tradius;
	}
	public void setTradius(String tradius) {
		this.tradius = tradius;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getTnum() {
		return tnum;
	}
	public void setTnum(String tnum) {
		this.tnum = tnum;
	}
	public String getTworkid() {
		return tworkid;
	}
	public void setTworkid(String tworkid) {
		this.tworkid = tworkid;
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
	public String getYard() {
		return yard;
	}
	public void setYard(String yard) {
		this.yard = yard;
	}
	public String getProjectPackageName() {
		return projectPackageName;
	}
	public void setProjectPackageName(String projectPackageName) {
		this.projectPackageName = projectPackageName;
	}
	

}
