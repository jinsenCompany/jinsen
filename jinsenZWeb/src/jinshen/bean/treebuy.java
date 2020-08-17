package jinshen.bean;

import java.sql.Date;

//用于进场是保存木材信息数据到数据库tree中
public class treebuy {
	private String cutNum;
	private Date   cutdate;
	private String cutSite;
	private String treetype;
	private double workid;
	private double tlong;
	private double tradius;
	private double num;
	private double tvolume;

	public String getTreetype() {
		return treetype;
	}
	public void setTreetype(String treetype) {
		this.treetype = treetype;
	}
	public double getWorkid() {
		return workid;
	}
	public void setWorkid(double workid) {
		this.workid = workid;
	}
	public double getTlong() {
		return tlong;
	}
	public void setTlong(double tlong) {
		this.tlong = tlong;
	}
	public double getTradius() {
		return tradius;
	}
	public void setTradius(double tradius) {
		this.tradius = tradius;
	}
	public double getNum() {
		return num;
	}
	public void setNum(double num) {
		this.num = num;
	}
	public double getTvolume() {
		return tvolume;
	}
	public void setTvolume(double tvolume) {
		this.tvolume = tvolume;
	}
	public String getCutNum() {
		return cutNum;
	}
	public void setCutNum(String cutNum) {
		this.cutNum = cutNum;
	}
	public Date getCutdate() {
		return cutdate;
	}
	public void setCutdate(Date cutdate) {
		this.cutdate = cutdate;
	}
	public String getCutSite() {
		return cutSite;
	}
	public void setCutSite(String cutSite) {
		this.cutSite = cutSite;
	}
	
}
