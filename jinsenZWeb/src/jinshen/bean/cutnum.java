package jinshen.bean;
//采伐证信息
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class cutnum {
	private int cutnumid;
	private String cutnum;//采伐证编号
	private String certificatenum;//采伐证号
	private String company;//林权单位
	private String cutaddress;//乡镇
	private String cutvillage;//村
	private String quartel;//林班
	private String largeblock;//大班
	private String smallblock;//小班
	private String forestid;//林权证号
	private String cuttype;//采伐类型
	private String cutmethod;//采伐方式
	private String cutqiang;//采伐强度
	private String cutarea;//采伐面积
	private Date starttime;//开始时间
	private Date endtime;//结束时间
	private double volume;//出材量
	private String sizewoodAll;//规格材合计
	private String smalltimberAll;//非规格小径合计
	private String shorttimberAll;//非规格材短小材合计
	private String firewoodAll;//薪材合计
	
	private String treetype;//树种
	private String total;//小计
	private String sizewood;//规格材
	private String smalltimber;//非规格材小径
	private String shorttimber;//非规格材短小材
	private String firewood;//薪材
	private double projectid;//工程包id
	private String cutnumfile;//上传文件
	
	private double number;//编号
	private String cutsite;//
	private String sizhi;
	private String gpsinfo;
	private String treeorigin;
	private String foresttype;
	private String ownership;
	private double treenum;
	private double cutstore;
	private String certifier;
	private Date updatedate;//更新时间
	private double updatevolume;//更新面积
	private double updatenum;
	public int getCutnumid() {
		return cutnumid;
	}
	public void setCutnumid(int cutnumid) {
		this.cutnumid = cutnumid;
	}
	public String getCutnum() {
		return cutnum;
	}
	public void setCutnum(String cutnum) {
		this.cutnum = cutnum;
	}
	public String getCertificatenum() {
		return certificatenum;
	}
	public void setCertificatenum(String certificatenum) {
		this.certificatenum = certificatenum;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCutaddress() {
		return cutaddress;
	}
	public void setCutaddress(String cutaddress) {
		this.cutaddress = cutaddress;
	}
	public String getCutvillage() {
		return cutvillage;
	}
	public void setCutvillage(String cutvillage) {
		this.cutvillage = cutvillage;
	}
	public String getQuartel() {
		return quartel;
	}
	public void setQuartel(String quartel) {
		this.quartel = quartel;
	}
	public String getLargeblock() {
		return largeblock;
	}
	public void setLargeblock(String largeblock) {
		this.largeblock = largeblock;
	}
	public String getSmallblock() {
		return smallblock;
	}
	public void setSmallblock(String smallblock) {
		this.smallblock = smallblock;
	}
	public String getForestid() {
		return forestid;
	}
	public void setForestid(String forestid) {
		this.forestid = forestid;
	}
	public String getCuttype() {
		return cuttype;
	}
	public void setCuttype(String cuttype) {
		this.cuttype = cuttype;
	}
	public String getCutmethod() {
		return cutmethod;
	}
	public void setCutmethod(String cutmethod) {
		this.cutmethod = cutmethod;
	}
	public String getCutqiang() {
		return cutqiang;
	}
	public void setCutqiang(String cutqiang) {
		this.cutqiang = cutqiang;
	}
	public String getCutarea() {
		return cutarea;
	}
	public void setCutarea(String cutarea) {
		this.cutarea = cutarea;
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
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public String getSizewoodAll() {
		return sizewoodAll;
	}
	public void setSizewoodAll(String sizewoodAll) {
		this.sizewoodAll = sizewoodAll;
	}
	public String getSmalltimberAll() {
		return smalltimberAll;
	}
	public void setSmalltimberAll(String smalltimberAll) {
		this.smalltimberAll = smalltimberAll;
	}
	public String getShorttimberAll() {
		return shorttimberAll;
	}
	public void setShorttimberAll(String shorttimberAll) {
		this.shorttimberAll = shorttimberAll;
	}
	public String getFirewoodAll() {
		return firewoodAll;
	}
	public void setFirewoodAll(String firewoodAll) {
		this.firewoodAll = firewoodAll;
	}
	public String getTreetype() {
		return treetype;
	}
	public void setTreetype(String treetype) {
		this.treetype = treetype;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getSizewood() {
		return sizewood;
	}
	public void setSizewood(String sizewood) {
		this.sizewood = sizewood;
	}
	public String getSmalltimber() {
		return smalltimber;
	}
	public void setSmalltimber(String smalltimber) {
		this.smalltimber = smalltimber;
	}
	public String getShorttimber() {
		return shorttimber;
	}
	public void setShorttimber(String shorttimber) {
		this.shorttimber = shorttimber;
	}
	public String getFirewood() {
		return firewood;
	}
	public void setFirewood(String firewood) {
		this.firewood = firewood;
	}
	public double getProjectid() {
		return projectid;
	}
	public void setProjectid(double projectid) {
		this.projectid = projectid;
	}
	public String getCutnumfile() {
		return cutnumfile;
	}
	public void setCutnumfile(String cutnumfile) {
		this.cutnumfile = cutnumfile;
	}
	public double getNumber() {
		return number;
	}
	public void setNumber(double number) {
		this.number = number;
	}
	public String getCutsite() {
		return cutsite;
	}
	public void setCutsite(String cutsite) {
		this.cutsite = cutsite;
	}
	public String getSizhi() {
		return sizhi;
	}
	public void setSizhi(String sizhi) {
		this.sizhi = sizhi;
	}
	public String getGpsinfo() {
		return gpsinfo;
	}
	public void setGpsinfo(String gpsinfo) {
		this.gpsinfo = gpsinfo;
	}
	public String getTreeorigin() {
		return treeorigin;
	}
	public void setTreeorigin(String treeorigin) {
		this.treeorigin = treeorigin;
	}
	public String getForesttype() {
		return foresttype;
	}
	public void setForesttype(String foresttype) {
		this.foresttype = foresttype;
	}
	public String getOwnership() {
		return ownership;
	}
	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}
	public double getTreenum() {
		return treenum;
	}
	public void setTreenum(double treenum) {
		this.treenum = treenum;
	}
	public double getCutstore() {
		return cutstore;
	}
	public void setCutstore(double cutstore) {
		this.cutstore = cutstore;
	}
	public String getCertifier() {
		return certifier;
	}
	public void setCertifier(String certifier) {
		this.certifier = certifier;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	public double getUpdatevolume() {
		return updatevolume;
	}
	public void setUpdatevolume(double updatevolume) {
		this.updatevolume = updatevolume;
	}
	public double getUpdatenum() {
		return updatenum;
	}
	public void setUpdatenum(double updatenum) {
		this.updatenum = updatenum;
	}//株树
	
	
	
	


}
