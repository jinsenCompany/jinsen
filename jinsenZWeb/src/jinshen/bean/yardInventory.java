package jinshen.bean;


//库存盘点
public class yardInventory {
	private String yardname;
	private double involume;//进场材积
	private double outvolume;//出场材积
	private String yeart;
	private String montht;
	private double totalnum;
	public String getYardname() {
		return yardname;
	}
	public void setYardname(String yardname) {
		this.yardname = yardname;
	}
	public double getInvolume() {
		return involume;
	}
	public void setInvolume(double involume) {
		this.involume = involume;
	}
	public double getOutvolume() {
		return outvolume;
	}
	public void setOutvolume(double outvolume) {
		this.outvolume = outvolume;
	}
	public String getYeart() {
		return yeart;
	}
	public void setYeart(String yeart) {
		this.yeart = yeart;
	}
	public String getMontht() {
		return montht;
	}
	public void setMontht(String montht) {
		this.montht = montht;
	}
	public double getTotalnum() {
		return totalnum;
	}
	public void setTotalnum(double totalnum) {
		this.totalnum = totalnum;
	}
	
}
