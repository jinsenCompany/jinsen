package jinshen.bean;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

//用于工单审核页面显示部分工单信息
public class codejson {

	private double workid;
	private String cutnum;
	private Date   cutdate;
	private String cutsite;
	private String forester;
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
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getCutdate() {
    	return cutdate;
    }
    public void setCutdate(Date cutdate) {
    	this.cutdate = cutdate;
    }
	public String getCutsite() {
		return cutsite;
	}
	public void setCutsite(String cutsite) {
		this.cutsite = cutsite;
	}
	public String getForester() {
		return forester;
	}
	public void setForester(String forester) {
		this.forester = forester;
	}
	
}
