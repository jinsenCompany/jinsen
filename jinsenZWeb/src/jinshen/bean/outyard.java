package jinshen.bean;
//用于出厂木材数据保存到数据库中
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class outyard {
	private double workid;
	private Date   yarddate;
	private String carNumber;
	private String yard;
	private String cutnum;//采伐证号
	private String surveyor;
	private double toltree;
	private double tolstere;
	private String Contractnum;//销售合同号
	private String saleCalloutorderid;//销售调令
	private String section;
	private String batchNum;//检尺标准
	private String demander;//需求方
	private String provider;//供放
	
	private int outStatus;//销售码单状态；0：录入销售码单，1：录入销售部门销售结算单
	public double getWorkid() {
		return workid;
	}
	public void setWorkid(double workid) {
		this.workid = workid;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss ", timezone = "GMT+8")
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
	public String getCutnum() {
		return cutnum;
	}
	public void setCutnum(String cutnum) {
		this.cutnum = cutnum;
	}
	public String getBatchNum() {
		return batchNum;
	}
	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}
	public String getDemander() {
		return demander;
	}
	public void setDemander(String demander) {
		this.demander = demander;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public int getOutStatus() {
		return outStatus;
	}
	public void setOutStatus(int outStatus) {
		this.outStatus = outStatus;
	}
	
	

}