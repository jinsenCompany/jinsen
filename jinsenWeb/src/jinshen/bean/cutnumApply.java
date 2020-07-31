package jinshen.bean;
//采伐砍伐申请书
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class cutnumApply {
	private double applyid;
	private String designum;
	private String cutreason;
	private String cutaddress;
	private String cutvillage;
	private String quartel;
	private String largeblock;
	private String smallblock;
	private double smallblackarea;
	private String origin;
	private String foresttype;
	private String typeconsist;
	private String managetype;
	private double forestage;
	private double cutarea;
	private String cuttype;
	private String cutway;
	private String cutstrength;
	private String treetype;
	private double cutvolume;
	private int cutnumer;
	private double total;
	private double cutintermediate;
	private double total2;
	private String sizewood;
	private String smalltimber;
	private String shorttimber;
	private String firewood;
	private String cutpath;
	private Date applaydate;
	public double getapplyid() {
		return applyid;
	}
	public void setapplyid(double applyid) {
		this.applyid=applyid;
	}
	public String getDesignum() {
		return designum;
	}
	public void setDesignum(String designum) {
		this.designum = designum;
	}
	public String getCutreason() {
		return cutreason;
	}
	public void setCutreason(String cutreason) {
		this.cutreason = cutreason;
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
	public double getSmallblackarea() {
		return smallblackarea;
	}
	public void setSmallblackarea(double smallblackarea) {
		this.smallblackarea = smallblackarea;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getForesttype() {
		return foresttype;
	}
	public void setForesttype(String foresttype) {
		this.foresttype = foresttype;
	}
	public String getTypeconsist() {
		return typeconsist;
	}
	public void setTypeconsist(String typeconsist) {
		this.typeconsist = typeconsist;
	}
	public String getManagetype() {
		return managetype;
	}
	public void setManagetype(String managetype) {
		this.managetype = managetype;
	}
	public double getForestage() {
		return forestage;
	}
	public void setForestage(double forestage) {
		this.forestage = forestage;
	}
	public double getCutarea() {
		return cutarea;
	}
	public void setCutarea(double cutarea) {
		this.cutarea = cutarea;
	}
	public String getCuttype() {
		return cuttype;
	}
	public void setCuttype(String cuttype) {
		this.cuttype = cuttype;
	}
	public String getCutway() {
		return cutway;
	}
	public void setCutway(String cutway) {
		this.cutway = cutway;
	}
	public String getCutstrength() {
		return cutstrength;
	}
	public void setCutstrength(String cutstrength) {
		this.cutstrength = cutstrength;
	}
	public String getTreetype() {
		return treetype;
	}
	public void setTreetype(String treetype) {
		this.treetype = treetype;
	}
	public double getCutvolume() {
		return cutvolume;
	}
	public void setCutvolume(double cutvolume) {
		this.cutvolume = cutvolume;
	}
	public int getCutnumer() {
		return cutnumer;
	}
	public void setCutnumer(int cutnumer) {
		this.cutnumer = cutnumer;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getCutintermediate() {
		return cutintermediate;
	}
	public void setCutintermediate(double cutintermediate) {
		this.cutintermediate = cutintermediate;
	}
	public double getTotal2() {
		return total2;
	}
	public void setTotal2(double total2) {
		this.total2 = total2;
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
	public String getCutpath() {
		return cutpath;
	}
	public void setCutpath(String cutpath) {
		this.cutpath = cutpath;
	}
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getApplaydate() {
		return applaydate;
	}
	public void setApplaydate(Date applaydate) {
		this.applaydate = applaydate;
	}


}
