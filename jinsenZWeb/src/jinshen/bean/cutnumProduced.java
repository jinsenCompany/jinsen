package jinshen.bean;
//采伐证信息
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class cutnumProduced {
	private String cutnum;//采伐证编号
	private String certificatenum;//采伐证号
	private String shamu;//杉木
	private String songmu;//松木
	private String zamu;//杂木
	private String totalProduced;//合计已生产量
	private String faquManager;//伐区管理员
	private Date creatDateProduced;//创建表的时间
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
	public String getTotalProduced() {
		return totalProduced;
	}
	public void setTotalProduced(String totalProduced) {
		this.totalProduced = totalProduced;
	}
	public String getFaquManager() {
		return faquManager;
	}
	public void setFaquManager(String faquManager) {
		this.faquManager = faquManager;
	}
	public Date getCreatDateProduced() {
		return creatDateProduced;
	}
	public void setCreatDateProduced(Date creatDateProduced) {
		this.creatDateProduced = creatDateProduced;
	}


}
