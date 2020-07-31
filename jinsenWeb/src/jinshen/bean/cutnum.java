package jinshen.bean;
//采伐证信息
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class cutnum {
	private double cutnumid;
	private String cutnum;
	private String certificatenum;
	private double number;
	private String company;
	private String cutsite;
	private String sizhi;
	private String gpsinfo;
	private String treeorigin;
	private String foresttype;
	private String treetype;
	private String ownership;
	private String forestid;
	private String cuttype;
	private String cutmethod;
	private String cutqiang;
	private double cutarea;
	private double treenum;
	private double cutstore;
	private double volume;
	private Date starttime;
	private Date endtime;
	private String certifier;
	private Date updatedate;//更新时间
	private double updatevolume;//更新面积
	private double updatenum;//株树
	private double projectid;//工程包id
	private String cutnumfile;
	public double getCutnumid() {
		return cutnumid;
	}
	public void setCutnumid(double cutnumid) {
		this.cutnumid=cutnumid;
	}
	public String getCutnum() {
		return cutnum;
	}
	public void setCutnum(String cutnum) {
		this.cutnum=cutnum;
	}
	public double getNumber() {
		return number;
	}
	public void setNumber(double number) {
		this.number=number;
	}
	public String getCertificatenum() {
		return certificatenum;
	}
	public void setCertificatenum(String certificatenum) {
		this.certificatenum=certificatenum;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company=company;
	}
	public String getcutsite() {
		return cutsite;
	}
	public void setcutsite(String cutsite) {
		this.cutsite=cutsite;
	}
	public String getSizhi() {
		return sizhi;
	}
	public void setSizhi(String sizhi) {
		this.sizhi=sizhi;
	}
	public String getGpsinfo() {
		return gpsinfo;
	}
	public void setGpsinfo(String gpsinfo) {
		this.gpsinfo=gpsinfo;
	}
	public String getTreeorigin() {
		return treeorigin;
	}
	public void setTreeorigin(String treeorigin) {
		this.treeorigin=treeorigin;
	}
	public String getforesttype() {
		return foresttype;
	}
	public void setforesttype(String foresttype) {
		this.foresttype=foresttype;
	}
	public String getTreetype() {
		return treetype;
	}
	public void setTreetype(String treetype) {
		this.treetype=treetype;
	}
	public String getOwnership() {
		return ownership;
	}
	public void setOwnership(String ownership) {
		this.ownership=ownership;
	}
	public String getForestid() {
		return forestid;
	}
	public void setForestid(String forestid) {
		this.forestid=forestid;
	}
	public String getcuttype() {
		return cuttype;
	}
	public void setcuttype(String cuttype) {
		this.cuttype=cuttype;
	}
	public String getcutmethod() {
		return cutmethod;
	}
	public void setcutmethod(String cutmethod) {
		this.cutmethod=cutmethod;
	}
	public String getcutqiang() {
		return cutqiang;
	}
	public void setcutqiang(String cutqiang) {
		this.cutqiang=cutqiang;
	}
	public double getcutarea() {
		return cutarea;
	}
	public void setcutarea(double cutarea) {
		this.cutarea=cutarea;
	}
	public double gettreenum() {
		return treenum;
	}
	public void settreenum(double treenum) {
		this.treenum=treenum;
	}
	public double getcutstore() {
		return cutstore;
	}
	public void setcutstore(double cutstore) {
		this.cutstore=cutstore;
	}
	public double getvolume() {
		return volume;
	}
	public void setvolume(double volume) {
		this.volume=volume;
	}
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime=starttime;
	}
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime=endtime;
	}
	public String getCertifier() {
		return certifier;
	}
	public void setCertifier(String certifier) {
		this.certifier=certifier;
	}
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate=updatedate;
	}
	public double getUpdatevolume() {
		return updatevolume;
	}
	public void setUpdatevolume(double updatevolume) {
		this.updatevolume=updatevolume;
	}
	public double getUpdatenum() {
		return updatenum;
	}
	public void setUpdatenum(double updatenum) {
		this.updatenum=updatenum;
	}
	public double getProjectid() {
		return projectid;
	}
	public void setProjectid(double projectid) {
		this.projectid=projectid;
	}
	public String getCutnumfile() {
		return cutnumfile;
	}
	public void setCutnumfile(String cutnumfile) {
		this.cutnumfile = cutnumfile;
	}
	


}
