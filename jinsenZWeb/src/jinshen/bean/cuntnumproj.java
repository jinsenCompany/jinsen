package jinshen.bean;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
public class cuntnumproj {

	private Date updatedate;//更新时间
	private double projectPackageid;
	private String cutnum;
	private String certificatenum;
	public String getCertificatenum() {
		return certificatenum;
	}
	public void setCertificatenum(String certificatenum) {
		this.certificatenum = certificatenum;
	}
	private double cutarea;
	private String projectPackagename;
	private String managerPhone;


	public String getCutnum() {
		return cutnum;
	}
	public void setCutnum(String cutnum) {
		this.cutnum = cutnum;
	}
@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	
	public double getProjectPackageid() {
		return projectPackageid;
	}
	public void setProjectPackageid(double projectPackageid) {
		this.projectPackageid = projectPackageid;
	}
	public String getProjectPackagename() {
		return projectPackagename;
	}
	public void setProjectPackagename(String projectPackagename) {
		this.projectPackagename = projectPackagename;
	}
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	public double getCutarea() {
		return cutarea;
	}
	public void setCutarea(double cutarea) {
		this.cutarea = cutarea;
	}
	
}
