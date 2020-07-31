package jinshen.bean;
//用于销售部门把销售木材信息存到saleman数据库中
public class salemansql {
	private double workid;
	private String treetype;
	private double unitprice;
	private double price;
	private String saleman;
	public double getWorkid() {
		return workid;
	}
	public void setWorkid(double workid) {
		this.workid = workid;
	}
	public String getTreetype() {
		return treetype;
	}
	public void setTreetype(String treetype) {
		this.treetype = treetype;
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
		this.price=price;
	}
	public String getSaleman() {
    	return saleman;
    }
    public void setSaleman(String saleman) {
    	this.saleman = saleman;
    }
    

}
