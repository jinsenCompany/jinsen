package jinshen.bean;
//货场 费用
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class goodsYardCost {
	private Date luRuDate;
	private double gouJiZD;
	private double renGongZD;
	private double zhuangCheGZ;
	private double changDiWH;
	private double changDiZJ;
	private double kanHuGZ;
	private double dianFei;
	private double sheBeiWH;
	private double yunFei;
	private double surveyorFee;
	private double totolCost;
	
	private String yard;
	private String luruperson;
	private String feetype;
	private int num;
	private String unit;
	private double unitprice;
	private double price;
	private String remarks;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getLuRuDate() {
		return luRuDate;
	}
	public void setLuRuDate(Date luRuDate) {
		this.luRuDate = luRuDate;
	}
	public double getGouJiZD() {
		return gouJiZD;
	}
	public void setGouJiZD(double gouJiZD) {
		this.gouJiZD = gouJiZD;
	}
	public double getRenGongZD() {
		return renGongZD;
	}
	public void setRenGongZD(double renGongZD) {
		this.renGongZD = renGongZD;
	}
	public double getZhuangCheGZ() {
		return zhuangCheGZ;
	}
	public void setZhuangCheGZ(double zhuangCheGZ) {
		this.zhuangCheGZ = zhuangCheGZ;
	}
	public double getChangDiWH() {
		return changDiWH;
	}
	public void setChangDiWH(double changDiWH) {
		this.changDiWH = changDiWH;
	}
	public double getKanHuGZ() {
		return kanHuGZ;
	}
	public void setKanHuGZ(double kanHuGZ) {
		this.kanHuGZ = kanHuGZ;
	}
	public double getDianFei() {
		return dianFei;
	}
	public void setDianFei(double dianFei) {
		this.dianFei = dianFei;
	}
	public double getSheBeiWH() {
		return sheBeiWH;
	}
	public void setSheBeiWH(double sheBeiWH) {
		this.sheBeiWH = sheBeiWH;
	}
	public double getYunFei() {
		return yunFei;
	}
	public void setYunFei(double yunFei) {
		this.yunFei = yunFei;
	}
	public double getSurveyorFee() {
		return surveyorFee;
	}
	public void setSurveyorFee(double surveyorFee) {
		this.surveyorFee = surveyorFee;
	}
	public double getTotolCost() {
		return totolCost;
	}
	public void setTotolCost(double totolCost) {
		this.totolCost = totolCost;
	}
	public double getChangDiZJ() {
		return changDiZJ;
	}
	public void setChangDiZJ(double changDiZJ) {
		this.changDiZJ = changDiZJ;
	}
	public String getYard() {
		return yard;
	}
	public void setYard(String yard) {
		this.yard = yard;
	}
	public String getLuruperson() {
		return luruperson;
	}
	public void setLuruperson(String luruperson) {
		this.luruperson = luruperson;
	}
	public String getFeetype() {
		return feetype;
	}
	public void setFeetype(String feetype) {
		this.feetype = feetype;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(double unitprice) {
		this.unitprice = unitprice;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	


}
