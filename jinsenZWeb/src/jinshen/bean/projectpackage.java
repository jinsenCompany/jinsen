package jinshen.bean;

import java.sql.Date;

public class projectpackage {
	private Double projectPackageid;
	private Date projpackageStarttime;
	private String contractNum;
	private String contractionSide;
	private String cuttime;
	private String forester;
	private String accessory;
	public double getprojectPackageid() {
		return projectPackageid;
	}
	public void setprojectPackageid(double projectPackageid) {
		this.projectPackageid=projectPackageid;
	}
	public Date getProjpackageStarttime() {
		return projpackageStarttime;
	}
	public void setProjpackageStarttime(Date projpackageStarttime) {
		this.projpackageStarttime = projpackageStarttime;
	}
	public String getContractNum() {
		return contractNum;
	}
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	public String getContractionSide() {
		return contractionSide;
	}
	public void setContractionSide(String contractionSide) {
		this.contractionSide = contractionSide;
	}
	public String getCuttime() {
		return cuttime;
	}
	public void setCuttime(String cuttime) {
		this.cuttime = cuttime;
	}
	public String getForester() {
		return forester;
	}
	public void setForester(String forester) {
		this.forester = forester;
	}
	public String getAccessory() {
		return accessory;
	}
	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}
}
