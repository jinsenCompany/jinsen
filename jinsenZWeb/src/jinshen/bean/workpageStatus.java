package jinshen.bean;

public class workpageStatus {
	private double workid;
	private int workidstatus;
	private String rejectreason;//审核被退回原因
	public double getWorkid() {
		return workid;
	}
	public void setWorkid(double workid) {
		this.workid = workid;
	}
	public int getWorkidstatus() {
		return workidstatus;
	}
	public void setWorkidstatus(int workidstatus) {
		this.workidstatus = workidstatus;
	}
	public String getRejectreason() {
		return rejectreason;
	}
	public void setRejectreason(String rejectreason) {
		this.rejectreason = rejectreason;
	}
	

}
