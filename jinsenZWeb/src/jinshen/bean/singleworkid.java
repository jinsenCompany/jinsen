package jinshen.bean;
//在树材工单结算页面显示工单号
public class singleworkid {
	
    private double workid;
    private double projectPackageid;
    private String forperson;
    private String manageUnit;
    public double getWorkid() {
	    return workid;
    }
    public void setWorkid(double workid) {
	    this.workid = workid;
    }
	public double getProjectPackageid() {
		return projectPackageid;
	}
	public void setProjectPackageid(double projectPackageid) {
		this.projectPackageid = projectPackageid;
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
    
}
