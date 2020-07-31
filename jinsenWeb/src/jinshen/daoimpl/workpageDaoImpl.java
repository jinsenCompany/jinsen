package jinshen.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


import java.util.ArrayList;

import jinshen.bean.codejson;
import jinshen.bean.compareTree;
import jinshen.bean.managesdatecard;
import jinshen.bean.tree;
import jinshen.bean.workpage;
import jinshen.bean.workpageStatus;
import jinshen.bean.yardManage;
import jinshen.bean.treebuy;
import jinshen.dao.workpageDao;
import jinshen.db.DBcon;

public class workpageDaoImpl implements workpageDao {

	private DBcon dbc=new DBcon();
    Connection connection = null;
    PreparedStatement pStatement = null;
    
	@Override
	public int addWorkPage(workpage cp) {
		// TODO Auto-generated method stub
		String sql = "insert into workpage values(?,?,?,?,?,?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getWorkid(),cp.getCutNum(),cp.getCutdate(), cp.getCutSite(),
					cp.getCheckSite(), cp.getCarNumber(),cp.getForester(),cp.getBatchNum()});
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public int updateWorkPage(workpage cp) {
		// TODO Auto-generated method stub
		int res=0;
		try {
			String sql="update workpage set workid=?,cutNum=?,cutdate=?,cutSite=?,checkSite=?,carNumber=?,forester=?,batchNum=? where workid=?";
		    res=dbc.doUpdate(sql, new Object[] {cp.getWorkid(),cp.getCutNum(),cp.getCutdate(), cp.getCutSite(),
					cp.getCheckSite(), cp.getCarNumber(),cp.getForester(),cp.getBatchNum()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public List<workpage> findWorkPage(String sql) {
		// TODO Auto-generated method stub
		List<workpage> addrList=new ArrayList<workpage>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				workpage addr=new workpage();
				addr.setWorkid(rs.getDouble(1));
				addr.setCutNum(rs.getString(2));
				addr.setCutdate(rs.getDate(3));
				addr.setCutSite(rs.getString(4));
				addr.setCheckSite(rs.getString(5));
				addr.setCarNumber(rs.getString(6));
				//addr.setGpsinfo(rs.getString(6));
				//addr.setLoadphoto(rs.getString(7));
				addr.setForester(rs.getString(7));
				addr.setBatchNum(rs.getDouble(8));
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
	public int delWorkPage(String sql) {
		// TODO Auto-generated method stub
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public workpage findCodeSingle(String sql) {
		// TODO Auto-generated method stub
		workpage addr = new workpage();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			if(rs.next()) {
				addr.setWorkid(rs.getDouble(1));
				addr.setCutNum(rs.getString(2));
				addr.setCutdate(rs.getDate(3));
				addr.setCutSite(rs.getString(4));
				addr.setCheckSite(rs.getString(5));
				addr.setCarNumber(rs.getString(6));
				//addr.setGpsinfo(rs.getString(7));
				//addr.setLoadphoto(rs.getString(8));
				addr.setForester(rs.getString(7));
				addr.setBatchNum(rs.getDouble(8));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return addr;
	}

	@Override
	public int updateWork(String sql) {
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {});
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

	@Override
	public int addCodeStatus(String sql) {
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}
	
	@Override
	public List<codejson> findcodeJson(String sql) {
		List<codejson> work=new ArrayList<codejson>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()){
				codejson w=new codejson();
				w.setWorkid(rs.getDouble(1));
				w.setCutnum(rs.getString(2));
				w.setCutdate(rs.getDate(3));
                w.setCutsite(rs.getString(4));
                w.setForester(rs.getString(5));
				work.add(w);	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return work;
	}

	@Override
	public boolean delete_work(double workid) {
		// TODO Auto-generated method stub
			String st="delete from workpage where workid=?";
			Connection con=DBcon.getConnection();
			try {
				PreparedStatement pst=con.prepareStatement(st);	
				pst.setDouble(1, workid);
				int i=pst.executeUpdate();
				if(i>0)
				  return true;
				else
					return false;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return false;
			}
			finally
			{
				
			}
	}

	@Override
	public int addWorkpageStatus(workpageStatus cp) {
		String sql = "insert into workpage_status values(?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getWorkid(),cp.getWorkidstatus(),cp.getRejectreason()});
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally {
			dbc.close();
		}
		return res;
	}
	@Override
	public int insertcompareTree(compareTree t) {
		String sql = "insert into compareTree values(?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {t.getWorkid(),t.getPic(),t.getFile()});
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public int updateWorkpagestatus(double workid,workpageStatus ws) {
		int res=0;
		try {
			String sql="update workpage_status set workid_status=? where workid="+workid+"";
		    res=dbc.doUpdate(sql, new Object[] {ws.getWorkidstatus()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}
	
	@Override
	public int updateWorkpagestatusN(double workid,workpageStatus ws) {
		int res=0;
		try {
			String sql="update workpage_status set workid_status=?,reject_reason=? where workid="+workid+"";
		    res=dbc.doUpdate(sql, new Object[] {ws.getWorkidstatus(),ws.getRejectreason()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public workpageStatus findWapStatus(String sql) {
		workpageStatus addr = new workpageStatus();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			if(rs.next()) {
				addr.setRejectreason(rs.getString(1));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return addr;
	}
	
	//更新工单状态
	@Override
	public int updateWorkpagestatus1(double workid,workpageStatus ws) {
		int res=0;
		try {
			String sql="update workpage_status set workid_status=? WHERE workid="+workid+"";
		    res=dbc.doUpdate(sql, new Object[] {ws.getWorkidstatus()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}
	
	@Override
	public int updateWorkPageFa(workpage cp,double workid) {
		// TODO Auto-generated method stub
		int res=0;
		try {
			String sql="update workpage set cutNum=?,cutdate=?,cutSite=?,checkSite=?,carNumber=?,forester=?,batchNum=? where workid="+workid+"";
		    res=dbc.doUpdate(sql, new Object[] {cp.getCutNum(),cp.getCutdate(), cp.getCutSite(),
					cp.getCheckSite(), cp.getCarNumber(),cp.getForester(),cp.getBatchNum()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public List<compareTree> findcomparet(String sql) {
		List<compareTree> addrList=new ArrayList<compareTree>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				compareTree addr=new compareTree();
				addr.setWorkid(rs.getDouble(1));
				addr.setPic(rs.getString(2));
				addr.setFile(rs.getString(3));
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
	public compareTree findcompares(String sql) {
		compareTree addr = new compareTree();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			if(rs.next()) {
				addr.setWorkid(rs.getDouble(1));
				addr.setPic(rs.getString(2));
				addr.setFile(rs.getString(3));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return addr;
	}

	@Override
	public int addyardmanage(yardManage cp) {
		String sql = "insert into yardmanage values(?,?,?,?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getYardid(),cp.getYardname(),cp.getYardvolume(),cp.getYardmanager(),cp.getManagephone(),cp.getRemarks()});
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public List<yardManage> findyardmanage(String sql) {
		List<yardManage> addrList=new ArrayList<yardManage>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				yardManage addr=new yardManage();
				addr.setYardid(rs.getInt(1));
				addr.setYardname(rs.getString(2));
				addr.setYardvolume(rs.getDouble(3));
				addr.setYardmanager(rs.getString(4));
				addr.setManagephone(rs.getString(5));
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
	public int addmanagesdatecard(managesdatecard cp) {
		String sql = "insert into managesdatecard values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getCardid(),cp.getOwnername(),cp.getSex(),cp.getBorndate(),cp.getIdnumber(),
					cp.getBornplace(),cp.getEnterprisename(),cp.getBusinesslicense(),cp.getAddress(),cp.getContact(),
					cp.getEngineeringbag(),cp.getCutnum(),cp.getQuartel(),cp.getLargeblock(),cp.getSmallblock(),
					cp.getAllarea(),cp.getTotaloutwood(),cp.getAreapiece(),cp.getSigningtime(),cp.getCooperationyear(),cp.getTeam(),cp.getYeji()});
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public List<managesdatecard> findmanagesdatecard(String sql) {
		List<managesdatecard> addrList=new ArrayList<managesdatecard>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				managesdatecard addr=new managesdatecard();
				addr.setCardid(rs.getInt(1));
				addr.setOwnername(rs.getString(2));
				addr.setSex(rs.getString(3));
				addr.setBorndate(rs.getString(4));
				addr.setIdnumber(rs.getString(5));
				addr.setBornplace(rs.getString(6));
				addr.setEnterprisename(rs.getString(7));
				addr.setBusinesslicense(rs.getString(8));
				addr.setAddress(rs.getString(9));
				addr.setContact(rs.getString(10));
				addr.setEngineeringbag(rs.getString(11));
				addr.setCutnum(rs.getString(12));
				addr.setQuartel(rs.getString(13));
				addr.setLargeblock(rs.getString(14));
				addr.setSmallblock(rs.getString(15));
				addr.setAllarea(rs.getDouble(16));
				addr.setTotaloutwood(rs.getDouble(17));
				addr.setAreapiece(rs.getInt(18));
				addr.setSigningtime(rs.getDate(19));
				addr.setCooperationyear(rs.getString(20));
				addr.setTeam(rs.getString(21));
				addr.setYeji(rs.getString(22));
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
    public workpage printworkpage() {
    	String sql="SELECT workid,cutNum,cutdate,cutSite from workpage";
    	ResultSet rs = null;
    	workpage cn=new workpage();
    	try {
    		connection = DBcon.getConnection();
    		pStatement = connection.prepareStatement(sql);
    		rs = pStatement.executeQuery();
    		while (rs.next()) {
                cn.setWorkid(rs.getDouble(1));
                cn.setCutNum(rs.getString(2));
                cn.setCutdate(rs.getDate(3));
                cn.setCutSite(rs.getString(4)); 
    		}
    		return cn;
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
	    public List<workpage> findWorkpageC(String sql){
	    	List<workpage> cnw =new ArrayList<workpage>();
	    	try {
	    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
	    		while(rs.next()) {
	    			workpage cn = new workpage();
	    			  cn.setWorkid(rs.getDouble(1));
	                  cn.setCutNum(rs.getString(2));
	                  cn.setCutdate(rs.getDate(3));
	                  cn.setCutSite(rs.getString(4)); 
					cnw.add(cn);
	    		}
	    	}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbc.close();
			}
			return cnw;
	    }
	
	@Override
	public List<tree> findTree(String sql) {
		List<tree> addrList=new ArrayList<tree>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				tree addr=new tree();
				addr.setWorkid(rs.getDouble(1));
				addr.setTreetype(rs.getString(2));
				addr.setTlong(rs.getDouble(3));
				addr.setTradius(rs.getDouble(4));
				addr.setNum(rs.getDouble(5));
				addr.setTvolume(rs.getDouble(6));
				//addr.setUnitprice(rs.getDouble(7));
				//addr.setTotalnum(rs.getDouble(8));
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
	public workpage findCodeSingle1(String sql) {
		// TODO Auto-generated method stub
		workpage addr = new workpage();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			if(rs.next()) {
				addr.setWorkid(rs.getDouble(1));
				addr.setCutNum(rs.getString(2));
				addr.setCutdate(rs.getDate(3));
				addr.setCutSite(rs.getString(4));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return addr;
	}
	
}
