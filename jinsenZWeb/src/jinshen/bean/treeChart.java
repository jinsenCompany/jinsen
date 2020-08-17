package jinshen.bean;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
//在超级管理员页面进行木材销售数据画图
public class treeChart {
	private Date   yarddate;
	private double price;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getYarddate() {
    	return yarddate;
    }
    public void setYarddate(Date yarddate) {
    	this.yarddate = yarddate;
    }
    public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price=price;
	}
}
