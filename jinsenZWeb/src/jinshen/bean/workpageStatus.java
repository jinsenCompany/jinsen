package jinshen.bean;

public class workpageStatus {
	private double workid;
	private int workidstatus;
	/*工单状态，0：工单已申请未审核，1：信息中心第一次审核工单通过(进入货场管理信息中心第二次审核)，
     *2：信息中心一次未通过，3：一次审核未通过伐区监管员补交材料，
     *4：货场管理员提交拍装车照片，5：信息中心审核照片通过，6：信息中心审核照片不通过
      *7：检尺员填写木材进场信息，8：信息中心审核木材数据通过，9：信息中心审核木材数据未通过，10：检尺员补交木材数据
      *23：已录入结算单号   ，20：已生产工资及其它费用结算清单，21：详细木材生产结算，22：按径极生产结算
    */
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
