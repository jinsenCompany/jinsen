package jinshen.dao;

import java.util.List;

import jinshen.bean.gpsInfo;

public interface gpsDao {
	public List<gpsInfo> findgpsInfo(String cp);//发现GPS信息
	public int addgpsInfo(gpsInfo c);//
	public double findcount(String sql);//计数
  
}
