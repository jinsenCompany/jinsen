package jinshen.daoimpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jinshen.bean.gpsInfo;
import jinshen.dao.gpsDao;
import jinshen.db.DBcon;

public class gpsDaoImpl implements gpsDao {
	private DBcon dbc=new DBcon();
	@Override
	public List<gpsInfo> findgpsInfo(String cp){
		List<gpsInfo> addrList=new ArrayList<gpsInfo>();
		try {
			ResultSet rs=dbc.doQuery(cp, new Object[] {});
			while(rs.next()) {
				gpsInfo addr=new gpsInfo();
				addr.setErron(rs.getDouble(1));
				addr.setError(rs.getString(2));
				addr.setCreateTime(rs.getDate(3));
				addr.setCreateTimeV(rs.getDate(4));
				addr.setUpdateAt(rs.getDate(5));
				addr.setUpdateAtV(rs.getDate(6));
				addr.setId(rs.getString(7));
				addr.setIdv(rs.getString(8));
				addr.setUuid(rs.getString(9));
				addr.setUuidv(rs.getString(10));
				addr.setcurrentValue(rs.getString(11));
				addr.setcurrentValuev(rs.getString(12));
				addrList.add(addr);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return addrList;
	}
	
	@Override
	public int addgpsInfo(gpsInfo c) {
		String sql="insert into gps_infomation values(?,?,?,?,?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {c.getErron(),c.getError(),c.getCreateTime(),
					c.getUpdateAt(),c.getId(),c.getUuid(),c.getCurrentValue()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}
	
	@Override
	public double findcount(String sql) {
		double num=1;
		try {
			ResultSet rs=dbc.doQuery(sql,new Object[]{});
			while(rs.next()) {
				num=rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return num;
	}

}
