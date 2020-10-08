package jinshen.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

//销售调令表
public class saleCalloutOrder {
	private int saleCallid;
	private String saleCalloutOrder;
	private String contractnum;
	private String demander;
	private String yard;
	private String section;
	private String Paymentnum;
	private String Paymentamount;
	private String Signer;
	private String totalnum;//合计数量
	private String treetype;
	private String tlong;
	private String tradius;
	private int tnum;
	private int salestatus;//销售调令状态销售调令状态，0：表示添加未审核，1：审核通过，2审核未通过，3：审核未通过补交材料，4：调令完成
	private Date callidtime;//调运时间
	private Date creattime;//开调令时间
	private String surveyor;//检尺员
	
	private String cutnum;//采伐证号
	private String useTime;//无实际意义，只是用来在botstrap显示时间
	public int getSaleCallid() {
		return saleCallid;
	}
	public void setSaleCallid(int saleCallid) {
		this.saleCallid = saleCallid;
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
	public String getDemander() {
		return demander;
	}
	public void setDemander(String demander) {
		this.demander = demander;
	}
	public String getYard() {
		return yard;
	}
	public void setYard(String yard) {
		this.yard = yard;
	}
	
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getPaymentnum() {
		return Paymentnum;
	}
	public void setPaymentnum(String paymentnum) {
		Paymentnum = paymentnum;
	}
	public String getPaymentamount() {
		return Paymentamount;
	}
	public void setPaymentamount(String paymentamount) {
		Paymentamount = paymentamount;
	}
	public String getSigner() {
		return Signer;
	}
	public void setSigner(String signer) {
		Signer = signer;
	}
	public String getTotalnum() {
		return totalnum;
	}
	public void setTotalnum(String totalnum) {
		this.totalnum = totalnum;
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
	public int getTnum() {
		return tnum;
	}
	public void setTnum(int tnum) {
		this.tnum = tnum;
	}
	public int getSalestatus() {
		return salestatus;
	}
	public void setSalestatus(int salestatus) {
		this.salestatus = salestatus;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getCallidtime() {
		return callidtime;
	}
	public void setCallidtime(Date callidtime) {
		this.callidtime = callidtime;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getCreattime() {
		return creattime;
	}
	public void setCreattime(Date creattime) {
		this.creattime = creattime;
	}
	public String getSurveyor() {
		return surveyor;
	}
	public void setSurveyor(String surveyor) {
		this.surveyor = surveyor;
	}
	public String getUseTime() {
		return useTime;
	}
	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}
	public String getCutnum() {
		return cutnum;
	}
	public void setCutnum(String cutnum) {
		this.cutnum = cutnum;
	}
	
	

}
