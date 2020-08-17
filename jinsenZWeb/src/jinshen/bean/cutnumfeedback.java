package jinshen.bean;
//木材采伐申请书反馈单
public class cutnumfeedback {
	private double applyid;
	private int status;
	private String unablereason;
	private String replenishpath;//补充材料路径
	public double getApplyid() {
		return applyid;
	}
	public void setApplyid(double applyid) {
		this.applyid = applyid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUnablereason() {
		return unablereason;
	}
	public void setUnablereason(String unablereason) {
		this.unablereason = unablereason;
	}
	public String getReplenishpath() {
		return replenishpath;
	}
	public void setReplenishpath(String replenishpath) {
		this.replenishpath = replenishpath;
	}

}
