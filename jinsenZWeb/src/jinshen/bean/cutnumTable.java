package jinshen.bean;
//采伐证信息
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class cutnumTable {
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
	
	private double volume;//设计出材量
	private String totalDesign;//设计出材量杉木
	private String shanTreeDesign;//设计出材量杉木
	private String songTreeDesign;//设计松木出材量
	private String zaTreeDesign;//设计杂木出材量
	
	private String totalProduced;//合计已生产量
	private String shamu;//杉木
	private String songmu;//松木
	private String zamu;//杂木
	private String totalActual;//实际出材量合计
	private String shanTreeActual;//实际出材量杉木
	private String songTreeActual;//实际松木出材量
	private String zaTreeActual;//实际杂木出材量
	private String totalSurplus;//剩余出材量合计
	private String shanSurplus;//剩余出材量杉木
	private String songSurplus;//剩余松木出材量
	private String zaSurplus;//剩余杂木出材量
	
	private String totalImplement;//工程包执行出材量合计
	private String shanTreeImplement;//工程包执行出材量杉木
	private String songTreeImplement;//工程包执行松木出材量
	private String zaTreeImplement;//工程包执行杂木出材量

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
	private int projectid;//工程包id
	private String cutnumfile;//上传文件
	private String creatcutDate;//创建表的时间
	
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
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
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
	public String getTotalDesign() {
		return totalDesign;
	}
	public void setTotalDesign(String totalDesign) {
		this.totalDesign = totalDesign;
	}
	public String getShanTreeDesign() {
		return shanTreeDesign;
	}
	public void setShanTreeDesign(String shanTreeDesign) {
		this.shanTreeDesign = shanTreeDesign;
	}
	public String getSongTreeDesign() {
		return songTreeDesign;
	}
	public void setSongTreeDesign(String songTreeDesign) {
		this.songTreeDesign = songTreeDesign;
	}
	public String getZaTreeDesign() {
		return zaTreeDesign;
	}
	public void setZaTreeDesign(String zaTreeDesign) {
		this.zaTreeDesign = zaTreeDesign;
	}
	public String getTotalProduced() {
		return totalProduced;
	}
	public void setTotalProduced(String totalProduced) {
		this.totalProduced = totalProduced;
	}
	public String getShamu() {
		return shamu;
	}
	public void setShamu(String shamu) {
		this.shamu = shamu;
	}
	public String getSongmu() {
		return songmu;
	}
	public void setSongmu(String songmu) {
		this.songmu = songmu;
	}
	public String getZamu() {
		return zamu;
	}
	public void setZamu(String zamu) {
		this.zamu = zamu;
	}
	public String getTotalActual() {
		return totalActual;
	}
	public void setTotalActual(String totalActual) {
		this.totalActual = totalActual;
	}
	public String getShanTreeActual() {
		return shanTreeActual;
	}
	public void setShanTreeActual(String shanTreeActual) {
		this.shanTreeActual = shanTreeActual;
	}
	public String getSongTreeActual() {
		return songTreeActual;
	}
	public void setSongTreeActual(String songTreeActual) {
		this.songTreeActual = songTreeActual;
	}
	public String getZaTreeActual() {
		return zaTreeActual;
	}
	public void setZaTreeActual(String zaTreeActual) {
		this.zaTreeActual = zaTreeActual;
	}
	public String getTotalSurplus() {
		return totalSurplus;
	}
	public void setTotalSurplus(String totalSurplus) {
		this.totalSurplus = totalSurplus;
	}
	public String getShanSurplus() {
		return shanSurplus;
	}
	public void setShanSurplus(String shanSurplus) {
		this.shanSurplus = shanSurplus;
	}
	public String getSongSurplus() {
		return songSurplus;
	}
	public void setSongSurplus(String songSurplus) {
		this.songSurplus = songSurplus;
	}
	public String getZaSurplus() {
		return zaSurplus;
	}
	public void setZaSurplus(String zaSurplus) {
		this.zaSurplus = zaSurplus;
	}
	public String getTotalImplement() {
		return totalImplement;
	}
	public void setTotalImplement(String totalImplement) {
		this.totalImplement = totalImplement;
	}
	public String getShanTreeImplement() {
		return shanTreeImplement;
	}
	public void setShanTreeImplement(String shanTreeImplement) {
		this.shanTreeImplement = shanTreeImplement;
	}
	public String getSongTreeImplement() {
		return songTreeImplement;
	}
	public void setSongTreeImplement(String songTreeImplement) {
		this.songTreeImplement = songTreeImplement;
	}
	public String getZaTreeImplement() {
		return zaTreeImplement;
	}
	public void setZaTreeImplement(String zaTreeImplement) {
		this.zaTreeImplement = zaTreeImplement;
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
	public int getProjectid() {
		return projectid;
	}
	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}
	public String getCutnumfile() {
		return cutnumfile;
	}
	public void setCutnumfile(String cutnumfile) {
		this.cutnumfile = cutnumfile;
	}
	public String getCreatcutDate() {
		return creatcutDate;
	}
	public void setCreatcutDate(String creatcutDate) {
		this.creatcutDate = creatcutDate;
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
	}
	
	

}
