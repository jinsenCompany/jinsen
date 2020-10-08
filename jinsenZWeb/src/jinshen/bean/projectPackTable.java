package jinshen.bean;
//工程包台账
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class projectPackTable {
	private int projectPackageid;
	private String projectPackageName;
	private String projpackageStarttime;
	private String contractNum;
	private String contractionSide;
	private String cuttime;
	private String forester;
	private String accessory;//合同路径
	private String certificatenum;//采伐证号
	private String cutarea;//采伐面积
	private String numCutnum;//采伐证数量
	private String totalDesign;//设计出材量合计
	private String shanTreeDesign;//设计出材量杉木
	private String songTreeDesign;//设计松木出材量
	private String zaTreeDesign;//设计杂木出材量
	private String cutnum;//采伐证编号
	private String cutnumid;//采伐证id
	
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
	private String zaiTreeImplement;//工程包执行杂木出材量
	
	private String totalProduced;//合计已生产量
	private String shamu;//杉木
	private String songmu;//松木
	private String zamu;//杂木
	public int getProjectPackageid() {
		return projectPackageid;
	}
	public void setProjectPackageid(int projectPackageid) {
		this.projectPackageid = projectPackageid;
	}
	
	public String getProjectPackageName() {
		return projectPackageName;
	}
	public void setProjectPackageName(String projectPackageName) {
		this.projectPackageName = projectPackageName;
	}

	/*
	 * @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") public Date
	 * getProjpackageStarttime() { return projpackageStarttime; } public void
	 * setProjpackageStarttime(Date projpackageStarttime) {
	 * this.projpackageStarttime = projpackageStarttime; }
	 */
	public String getContractNum() {
		return contractNum;
	}
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	public String getContractionSide() {
		return contractionSide;
	}
	public void setContractionSide(String contractionSide) {
		this.contractionSide = contractionSide;
	}
	public String getCuttime() {
		return cuttime;
	}
	public void setCuttime(String cuttime) {
		this.cuttime = cuttime;
	}
	public String getForester() {
		return forester;
	}
	public void setForester(String forester) {
		this.forester = forester;
	}
	public String getAccessory() {
		return accessory;
	}
	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}
	public String getCertificatenum() {
		return certificatenum;
	}
	public void setCertificatenum(String certificatenum) {
		this.certificatenum = certificatenum;
	}
	public String getCutarea() {
		return cutarea;
	}
	public void setCutarea(String cutarea) {
		this.cutarea = cutarea;
	}
	public String getNumCutnum() {
		return numCutnum;
	}
	public void setNumCutnum(String numCutnum) {
		this.numCutnum = numCutnum;
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
	public String getZaiTreeImplement() {
		return zaiTreeImplement;
	}
	public void setZaiTreeImplement(String zaiTreeImplement) {
		this.zaiTreeImplement = zaiTreeImplement;
	}
	public String getCutnum() {
		return cutnum;
	}
	public void setCutnum(String cutnum) {
		this.cutnum = cutnum;
	}
	public String getCutnumid() {
		return cutnumid;
	}
	public void setCutnumid(String cutnumid) {
		this.cutnumid = cutnumid;
	}
	public String getProjpackageStarttime() {
		return projpackageStarttime;
	}
	public void setProjpackageStarttime(String projpackageStarttime) {
		this.projpackageStarttime = projpackageStarttime;
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
	

}
