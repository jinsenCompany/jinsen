package jinshen.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class cutnumProgress {
	private String cutnum;
	private double projectPackageid;
	private String cutsite;
	private double cutarea;
	private double cutstore;
	private Date starttime;
	private Date endtime;
	private int status;
	public String getCutnum() {
		return cutnum;
	}
	public void setCutnum(String cutnum) {
		this.cutnum = cutnum;
	}
	public double getProjectPackageid() {
		return projectPackageid;
	}
	public void setProjectPackageid(double projectPackageid) {
		this.projectPackageid = projectPackageid;
	}
	public String getCutsite() {
		return cutsite;
	}
	public void setCutsite(String cutsite) {
		this.cutsite = cutsite;
	}
	public double getCutarea() {
		return cutarea;
	}
	public void setCutarea(double cutarea) {
		this.cutarea = cutarea;
	}
	public double getCutstore() {
		return cutstore;
	}
	public void setCutstore(double cutstore) {
		this.cutstore = cutstore;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	

}
