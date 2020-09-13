package jinshen.bean;
//在树材工单结算页面显示工单号
public class singleworkid {
	
    private double workid;
    private String projectPackageName;
    private String forperson;
    private String manageUnit;
    private String yard;
    public double getWorkid() {
	    return workid;
    }
    public void setWorkid(double workid) {
	    this.workid = workid;
    }
	public String getForperson() {
		return forperson;
	}
	public void setForperson(String forperson) {
		this.forperson = forperson;
	}
	public String getManageUnit() {
		return manageUnit;
	}
	public void setManageUnit(String manageUnit) {
		this.manageUnit = manageUnit;
	}
	public String getYard() {
		return yard;
	}
	public void setYard(String yard) {
		this.yard = yard;
	}
	public String getProjectPackageName() {
		return projectPackageName;
	}
	public void setProjectPackageName(String projectPackageName) {
		this.projectPackageName = projectPackageName;
	}
    
}
