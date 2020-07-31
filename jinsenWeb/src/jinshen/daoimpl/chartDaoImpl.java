package jinshen.daoimpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jinshen.bean.producetree;
import jinshen.bean.salemansql;
import jinshen.bean.treeChart;
import jinshen.bean.treeoutChart;
import jinshen.bean.treeoutPrint;
import jinshen.bean.treetypeChart;
import jinshen.dao.chartDao;
import jinshen.db.DBcon;

public class chartDaoImpl implements chartDao{
	private DBcon db=new DBcon();
	Connection connection = null;
    PreparedStatement pStatement = null;
    @Override
    public List<treeChart> treechart(){
    	Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
    	String sql="select yarddate,sum(price) as price from outyard join saleman where outyard.workid=saleman.workid group by yarddate";
    	List<treeChart> addrlist=new ArrayList<treeChart>();
    	try {
    		 connection = DBcon.getConnection();
    		 pStatement = connection.prepareStatement(sql);
    		 rs = pStatement.executeQuery();
    		 while (rs.next()) {
    			 treeChart addr=new treeChart();
    			 addr.setYarddate(rs.getDate("yarddate"));
    			 addr.setPrice(rs.getDouble("price"));
    			 addrlist.add(addr);
    		 }
    		 }catch (Exception e) {
    	            e.printStackTrace();
    	            return null;
    	        } 
    	        finally 
    	        {
    	        	db.closeConnection(connection);
    	        	db.closePreparedStatement(pStatement);
    	        	
    	        }
    	return addrlist;
    } 
    
    @Override
    public List<treetypeChart> treetypechart(){
    	Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        String sql="SELECT treetype,sum(num) as num FROM tree group BY treetype";
        List<treetypeChart> addrlist = new ArrayList<treetypeChart>();
        try {
   		 connection = DBcon.getConnection();
   		 pStatement = connection.prepareStatement(sql);
   		 rs = pStatement.executeQuery();
   		 while (rs.next()) {
   			treetypeChart addr=new treetypeChart();
   			addr.setTreetype(rs.getString("treetype"));
   			addr.setNum(rs.getDouble("num"));
   			addrlist.add(addr);
   		 }
   		 }catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        } 
	        finally 
	        {
	        	db.closeConnection(connection);
	        	db.closePreparedStatement(pStatement);
	        	
	        }
	return addrlist;
        
    }
    
    @Override
    public List<treeoutChart> treeoutChart(){
    	Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        String sql="SELECT treetype,sum(num) as num ,sum(tvolume*unitprice) as price FROM treeout group BY treetype";
        List<treeoutChart> addrlist = new ArrayList<treeoutChart>();
        try {
   		 connection = DBcon.getConnection();
   		 pStatement = connection.prepareStatement(sql);
   		 rs = pStatement.executeQuery();
   		 while (rs.next()) {
   			treeoutChart addr=new treeoutChart();
   			addr.setTreetype(rs.getString("treetype"));
   			addr.setNum(rs.getDouble("num"));
   			addr.setPrice(rs.getDouble("price"));
   			addrlist.add(addr);
   		 }
   		 }catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        } 
	        finally 
	        {
	        	db.closeConnection(connection);
	        	db.closePreparedStatement(pStatement);
	        	
	        }
	return addrlist;
        
    }
    
    @Override
    public List<producetree> findProduce(){
    	String sql="select year(w.cutdate) as yeart,month(w.cutdate) as montht,day(w.cutdate) as dayt,w.cutNum,w.cutSite,w.checkSite,w.carNumber,i.yard,t.treetype,t.tlong,t.tradius,t.num,t.tvolume,i.surveyor,w.forester from workpage as w JOIN tree as t on w.workid=t.workid join inyard as i on w.workid=i.workid";
    	 ResultSet rs = null;
 	    List<producetree> ts = new ArrayList<producetree>();
 	   try {
	        connection = DBcon.getConnection();
	        pStatement = connection.prepareStatement(sql);
	        rs = pStatement.executeQuery();
	        while (rs.next()) {
	        	producetree s = new producetree();
	        	s.setYear(rs.getDouble(1));
	        	s.setMonth(rs.getDouble(2));
	        	s.setDayt(rs.getDouble(3));
	        	s.setCutNum(rs.getString(4));
	        	s.setCutSite(rs.getString(5));
	        	s.setCheckSite(rs.getString(6));
	        	s.setCarNumber(rs.getString(7));
	        	s.setYard(rs.getString(8));
	        	s.setTreetype(rs.getString(9));
	        	s.setTlong(rs.getDouble(10));
	        	s.setTradius(rs.getDouble(11));
	        	s.setNum(rs.getDouble(12));
	        	s.setTvolume(rs.getDouble(13));
	        	s.setSurveyor(rs.getString(14));
	        	s.setForester(rs.getString(15));
	        	ts.add(s);
	        }
	           return ts;
	        }catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        } finally {
	        	DBcon.closeResultSet(rs);
	        	DBcon.closePreparedStatement(pStatement);
	        	DBcon.closeConnection(connection);	
	       }
	 }
    @Override
    public producetree printProduce(int year,int month) {
    	String sql="select year(w.cutdate) as yeart,month(w.cutdate) as montht,day(w.cutdate) as dayt,w.cutNum,w.cutSite,w.checkSite,w.carNumber,i.yard,t.treetype,t.tlong,t.tradius,t.num,t.tvolume,i.surveyor,w.forester from workpage as w JOIN tree as t on w.workid=t.workid join inyard as i on w.workid=i.workid";
    	ResultSet rs = null;
    	producetree d = new producetree();
    	try {
    		connection = DBcon.getConnection();
    		pStatement = connection.prepareStatement(sql);
    		rs = pStatement.executeQuery();
    		while (rs.next()) {
    			d.setYear(rs.getDouble(1));
	        	d.setMonth(rs.getDouble(2));
	        	d.setDayt(rs.getDouble(3));
	        	d.setCutNum(rs.getString(4));
	        	d.setCutSite(rs.getString(5));
	        	d.setCheckSite(rs.getString(6));
	        	d.setCarNumber(rs.getString(7));
	        	d.setYard(rs.getString(8));
	        	d.setTreetype(rs.getString(9));
	        	d.setTlong(rs.getDouble(10));
	        	d.setTradius(rs.getDouble(11));
	        	d.setNum(rs.getDouble(12));
	        	d.setTvolume(rs.getDouble(13));
	        	d.setSurveyor(rs.getString(14));
	        	d.setForester(rs.getString(15));
    		}
    		return d;
    	}catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	DBcon.closeResultSet(rs);
        	DBcon.closePreparedStatement(pStatement);
        	DBcon.closeConnection(connection);	
    }
    }
    
    @Override
    public producetree printProduceY(int year) {
    	String sql="select year(w.cutdate) as yeart,month(w.cutdate) as montht,day(w.cutdate) as dayt,w.cutNum,w.cutSite,w.checkSite,w.carNumber,i.yard,t.treetype,t.tlong,t.tradius,t.num,t.tvolume,i.surveyor,w.forester from workpage as w JOIN tree as t on w.workid=t.workid join inyard as i on w.workid=i.workid";
    	ResultSet rs = null;
    	producetree d = new producetree();
    	try {
    		connection = DBcon.getConnection();
    		pStatement = connection.prepareStatement(sql);
    		rs = pStatement.executeQuery();
    		while (rs.next()) {
    			d.setYear(rs.getDouble(1));
	        	d.setMonth(rs.getDouble(2));
	        	d.setDayt(rs.getDouble(3));
	        	d.setCutNum(rs.getString(4));
	        	d.setCutSite(rs.getString(5));
	        	d.setCheckSite(rs.getString(6));
	        	d.setCarNumber(rs.getString(7));
	        	d.setYard(rs.getString(8));
	        	d.setTreetype(rs.getString(9));
	        	d.setTlong(rs.getDouble(10));
	        	d.setTradius(rs.getDouble(11));
	        	d.setNum(rs.getDouble(12));
	        	d.setTvolume(rs.getDouble(13));
	        	d.setSurveyor(rs.getString(14));
	        	d.setForester(rs.getString(15));
    		}
    		return d;
    	}catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	DBcon.closeResultSet(rs);
        	DBcon.closePreparedStatement(pStatement);
        	DBcon.closeConnection(connection);	
    }
    }
    
    @Override
    public producetree printProduceM(int year,int month,int month1,int month2) {
    	String sql="select year(w.cutdate) as yeart,month(w.cutdate) as montht,day(w.cutdate) as dayt,w.cutNum,w.cutSite,w.checkSite,w.carNumber,i.yard,t.treetype,t.tlong,t.tradius,t.num,t.tvolume,i.surveyor,w.forester from workpage as w JOIN tree as t on w.workid=t.workid join inyard as i on w.workid=i.workid";
    	ResultSet rs = null;
    	producetree d = new producetree();
    	try {
    		connection = DBcon.getConnection();
    		pStatement = connection.prepareStatement(sql);
    		rs = pStatement.executeQuery();
    		while (rs.next()) {
    			d.setYear(rs.getDouble(1));
	        	d.setMonth(rs.getDouble(2));
	        	d.setDayt(rs.getDouble(3));
	        	d.setCutNum(rs.getString(4));
	        	d.setCutSite(rs.getString(5));
	        	d.setCheckSite(rs.getString(6));
	        	d.setCarNumber(rs.getString(7));
	        	d.setYard(rs.getString(8));
	        	d.setTreetype(rs.getString(9));
	        	d.setTlong(rs.getDouble(10));
	        	d.setTradius(rs.getDouble(11));
	        	d.setNum(rs.getDouble(12));
	        	d.setTvolume(rs.getDouble(13));
	        	d.setSurveyor(rs.getString(14));
	        	d.setForester(rs.getString(15));
    		}
    		return d;
    	}catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	DBcon.closeResultSet(rs);
        	DBcon.closePreparedStatement(pStatement);
        	DBcon.closeConnection(connection);	
    }
    }
    
    @Override
    public List<treeoutPrint> findTreeout() {
    	String sql="select w.workid,year(w.yarddate) as yeart,month(w.yarddate) as montht,w.carNumber,w.yard,w.batchNum,t.treetype,t.tlong,t.tradius,t.num,t.tvolume,t.unitprice,t.totalnum,w.surveyor, s.salesman from outyard as w JOIN treeout as t join saleman as s on w.workid=t.workid=s.workid";
    	ResultSet rs = null;
    	List<treeoutPrint> ts = new ArrayList<treeoutPrint>();
    	try {
	        connection = DBcon.getConnection();
	        pStatement = connection.prepareStatement(sql);
	        rs = pStatement.executeQuery();
	        while (rs.next()) {
	        	treeoutPrint s=new treeoutPrint();
	        	s.setWorkid(rs.getDouble(1));
	        	s.setYear(rs.getDouble(2));
	        	s.setMonth(rs.getDouble(3));
	        	s.setCarNumber(rs.getString(4));
	        	s.setYard(rs.getString(5));
	        	s.setCheckstd(rs.getString(6));
	        	s.setTreetype(rs.getString(7));
	        	s.setTlong(rs.getDouble(8));
	        	s.setTradius(rs.getDouble(9));
	        	s.setNum(rs.getDouble(10));
	        	s.setTvolume(rs.getDouble(11));
	        	s.setUnitprice(rs.getDouble(12));
	        	s.setPrice(rs.getDouble(13));
	        	s.setSurveyor(rs.getString(14));
	        	s.setSaleman(rs.getString(15));
	        	ts.add(s);
	        }
	        return ts;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	DBcon.closeResultSet(rs);
        	DBcon.closePreparedStatement(pStatement);
        	DBcon.closeConnection(connection);	
       }
    }
    
     @Override
     public treeoutPrint printTreeout(int year,int month) {
    	 String sql="select w.workid,year(w.yarddate) as yeart,month(w.yarddate) as montht,w.carNumber,w.yard,w.batchNum,t.treetype,t.tlong,t.tradius,t.num,t.tvolume,t.unitprice,t.totalnum,w.surveyor, s.salesman from outyard as w JOIN treeout as t join saleman as s on w.workid=t.workid=s.workid";
     	 ResultSet rs = null;
     	treeoutPrint d=new treeoutPrint();
     	try {
    		connection = DBcon.getConnection();
    		pStatement = connection.prepareStatement(sql);
    		rs = pStatement.executeQuery();
    		while (rs.next()) {
    			d.setWorkid(rs.getDouble(1));
	        	d.setYear(rs.getDouble(2));
	        	d.setMonth(rs.getDouble(3));
	        	d.setCarNumber(rs.getString(4));
	        	d.setYard(rs.getString(5));
	        	d.setCheckstd(rs.getString(6));
	        	d.setTreetype(rs.getString(7));
	        	d.setTlong(rs.getDouble(8));
	        	d.setTradius(rs.getDouble(9));
	        	d.setNum(rs.getDouble(10));
	        	d.setTvolume(rs.getDouble(11));
	        	d.setUnitprice(rs.getDouble(12));
	        	d.setPrice(rs.getDouble(13));
	        	d.setSurveyor(rs.getString(14));
	        	d.setSaleman(rs.getString(15));
    		}
    		return d;
    	}catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	DBcon.closeResultSet(rs);
        	DBcon.closePreparedStatement(pStatement);
        	DBcon.closeConnection(connection);	
           }
    }
     
     @Override
     public treeoutPrint printTreeoutY(int year) {
    	 String sql="select w.workid,year(w.yarddate) as yeart,month(w.yarddate) as montht,w.carNumber,w.yard,w.batchNum,t.treetype,t.tlong,t.tradius,t.num,t.tvolume,t.unitprice,t.totalnum,w.surveyor, s.salesman from outyard as w JOIN treeout as t join saleman as s on w.workid=t.workid=s.workid";
     	 ResultSet rs = null;
     	treeoutPrint d=new treeoutPrint();
     	try {
    		connection = DBcon.getConnection();
    		pStatement = connection.prepareStatement(sql);
    		rs = pStatement.executeQuery();
    		while (rs.next()) {
    			d.setWorkid(rs.getDouble(1));
	        	d.setYear(rs.getDouble(2));
	        	d.setMonth(rs.getDouble(3));
	        	d.setCarNumber(rs.getString(4));
	        	d.setYard(rs.getString(5));
	        	d.setCheckstd(rs.getString(6));
	        	d.setTreetype(rs.getString(7));
	        	d.setTlong(rs.getDouble(8));
	        	d.setTradius(rs.getDouble(9));
	        	d.setNum(rs.getDouble(10));
	        	d.setTvolume(rs.getDouble(11));
	        	d.setUnitprice(rs.getDouble(12));
	        	d.setPrice(rs.getDouble(13));
	        	d.setSurveyor(rs.getString(14));
	        	d.setSaleman(rs.getString(15));
    		}
    		return d;
    	}catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	DBcon.closeResultSet(rs);
        	DBcon.closePreparedStatement(pStatement);
        	DBcon.closeConnection(connection);	
           }
    }
     
     @Override
     public List<salemansql> findsaleman(){
    	 String sql="select workid,treetype,price from saleman";
    	 ResultSet rs = null;
     	List<salemansql> ts = new ArrayList<salemansql>();
     	try {
	        connection = DBcon.getConnection();
	        pStatement = connection.prepareStatement(sql);
	        rs = pStatement.executeQuery();
	        while (rs.next()) {
	        	salemansql s=new salemansql();
	        	s.setWorkid(rs.getDouble(1));
	        	s.setTreetype(rs.getString(2));
	        	s.setPrice(rs.getDouble(3));
	        	ts.add(s);
	        }
	        return ts;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	DBcon.closeResultSet(rs);
        	DBcon.closePreparedStatement(pStatement);
        	DBcon.closeConnection(connection);	
       }
     }
     
     @Override
     public salemansql printsaleman() {
    	 String sql="select workid,treetype,price from saleman";
    	 ResultSet rs = null;
    	 salemansql d=new salemansql();
    	 try {
     		connection = DBcon.getConnection();
     		pStatement = connection.prepareStatement(sql);
     		rs = pStatement.executeQuery();
     		while (rs.next()) {
     			d.setWorkid(rs.getDouble(1));
	        	d.setTreetype(rs.getString(2));
	        	d.setPrice(rs.getDouble(3));
     		}
     		return d;
     	}catch (Exception e) {
             e.printStackTrace();
             return null;
         } finally {
         	DBcon.closeResultSet(rs);
         	DBcon.closePreparedStatement(pStatement);
         	DBcon.closeConnection(connection);	
            }
     }
     
     @Override
     public treeoutPrint printTreeoutM(int year,int month,int month1,int month2) {
    	 String sql="select w.workid,year(w.yarddate) as yeart,month(w.yarddate) as montht,w.carNumber,w.yard,w.batchNum,t.treetype,t.tlong,t.tradius,t.num,t.tvolume,t.unitprice,t.totalnum,w.surveyor, s.salesman from outyard as w JOIN treeout as t join saleman as s on w.workid=t.workid=s.workid";
     	 ResultSet rs = null;
     	treeoutPrint d=new treeoutPrint();
     	try {
    		connection = DBcon.getConnection();
    		pStatement = connection.prepareStatement(sql);
    		rs = pStatement.executeQuery();
    		while (rs.next()) {
    			d.setWorkid(rs.getDouble(1));
	        	d.setYear(rs.getDouble(2));
	        	d.setMonth(rs.getDouble(3));
	        	d.setCarNumber(rs.getString(4));
	        	d.setYard(rs.getString(5));
	        	d.setCheckstd(rs.getString(6));
	        	d.setTreetype(rs.getString(7));
	        	d.setTlong(rs.getDouble(8));
	        	d.setTradius(rs.getDouble(9));
	        	d.setNum(rs.getDouble(10));
	        	d.setTvolume(rs.getDouble(11));
	        	d.setUnitprice(rs.getDouble(12));
	        	d.setPrice(rs.getDouble(13));
	        	d.setSurveyor(rs.getString(14));
	        	d.setSaleman(rs.getString(15));
    		}
    		return d;
    	}catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	DBcon.closeResultSet(rs);
        	DBcon.closePreparedStatement(pStatement);
        	DBcon.closeConnection(connection);	
           }
    }

}
