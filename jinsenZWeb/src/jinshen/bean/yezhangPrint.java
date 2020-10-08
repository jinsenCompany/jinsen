 package jinshen.bean;



import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

//检尺野账打印
public class yezhangPrint {

	private double workid;
	private String cutnum;
	private String company;
	private String proj_package_Name;
	private double toltree;
	private double tolstere;
	private String yard;
	private String section;
	public double getWorkid() {
		return workid;
	}
	public void setWorkid(double workid) {
		this.workid = workid;
	}
	public String getCutnum() {
		return cutnum;
	}
	public void setCutnum(String cutnum) {
		this.cutnum = cutnum;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getProj_package_Name() {
		return proj_package_Name;
	}
	public void setProj_package_Name(String proj_package_Name) {
		this.proj_package_Name = proj_package_Name;
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
	
}
