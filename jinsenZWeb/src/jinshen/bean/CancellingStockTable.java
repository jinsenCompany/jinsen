package jinshen.bean;

import java.sql.Date;

public class CancellingStockTable {
	private int id;
	
	private Date cancellingStockDate;
	private String cancellingStockSite;
	private String treetype;
	private String tlong;
	private String tradius;
	private int num;
	private String tvolume;
	private String cancellingStockReason;
	private String path;
	public Date getCancellingStockDate() {
		return cancellingStockDate;
	}
	public void setCancellingStockDate(Date cancellingStockDate) {
		this.cancellingStockDate = cancellingStockDate;
	}
	public String getCancellingStockSite() {
		return cancellingStockSite;
	}
	public void setCancellingStockSite(String cancellingStockSite) {
		this.cancellingStockSite = cancellingStockSite;
	}
	public String getTreetype() {
		return treetype;
	}
	public void setTreetype(String treetype) {
		this.treetype = treetype;
	}
	public String getTlong() {
		return tlong;
	}
	public void setTlong(String tlong) {
		this.tlong = tlong;
	}
	public String getTradius() {
		return tradius;
	}
	public void setTradius(String tradius) {
		this.tradius = tradius;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTvolume() {
		return tvolume;
	}
	public void setTvolume(String tvolume) {
		this.tvolume = tvolume;
	}
	public String getCancellingStockReason() {
		return cancellingStockReason;
	}
	public void setCancellingStockReason(String cancellingStockReason) {
		this.cancellingStockReason = cancellingStockReason;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
