package jinshen.bean;
//显示再treePrice.jsp页面木材类型，材积等信息，对象
public class worktree {
	private String type;
	private double tvolume;
	private double unitprice;
	private double price;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getTvolume() {
		return tvolume;
	}
	public void setTvolume(double tvolume) {
		this.tvolume = tvolume;
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
}
