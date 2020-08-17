package jinshen.bean;

import java.util.Date;

//生产业主信息
public class customer {
	private int cutstomerid;
	private String kname;
	private String contractnum;
	private String address;
	private String company;
	private String IDCard;//身份证号
	private String telephone;//联系电话
	private String shipaddress;//发货地址
	private Date creatdate;//建卡时间
	private String customernum;//客户编号
	private String customertype;//客户类型
	private String treenumber;//签订合同木材数量
	private String totaltree;//调运数量累计
	private String breakcontract;//违约历史记录
	public int getCutstomerid() {
		return cutstomerid;
	}
	public void setCutstomerid(int cutstomerid) {
		this.cutstomerid = cutstomerid;
	}
	public String getKname() {
		return kname;
	}
	public void setKname(String kname) {
		this.kname = kname;
	}
	public String getContractnum() {
		return contractnum;
	}
	public void setContractnum(String contractnum) {
		this.contractnum = contractnum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getShipaddress() {
		return shipaddress;
	}
	public void setShipaddress(String shipaddress) {
		this.shipaddress = shipaddress;
	}
	public Date getCreatdate() {
		return creatdate;
	}
	public void setCreatdate(Date creatdate) {
		this.creatdate = creatdate;
	}
	public String getCustomernum() {
		return customernum;
	}
	public void setCustomernum(String customernum) {
		this.customernum = customernum;
	}
	public String getCustomertype() {
		return customertype;
	}
	public void setCustomertype(String customertype) {
		this.customertype = customertype;
	}
	public String getTreenumber() {
		return treenumber;
	}
	public void setTreenumber(String treenumber) {
		this.treenumber = treenumber;
	}
	public String getTotaltree() {
		return totaltree;
	}
	public void setTotaltree(String totaltree) {
		this.totaltree = totaltree;
	}
	public String getBreakcontract() {
		return breakcontract;
	}
	public void setBreakcontract(String breakcontract) {
		this.breakcontract = breakcontract;
	}
	

}
