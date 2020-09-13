package jinshen.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class projectpackage {
	private int projectPackageid;
	private Date projpackageStarttime;
	private String contractNum;
	private String contractionSide;
	private String cuttime;
	private String forester;
	private String accessory;
	private String projectPackageName;//工程包名称
	public int getprojectPackageid() {
		return projectPackageid;
	}
	public void setprojectPackageid(int projectPackageid) {
		this.projectPackageid=projectPackageid;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
	public String getProjectPackageName() {
		return projectPackageName;
	}
	public void setProjectPackageName(String projectPackageName) {
		this.projectPackageName = projectPackageName;
	}
	
}
