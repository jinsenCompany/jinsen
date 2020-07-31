package jinshen.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jinshen.bean.codejson;
import jinshen.bean.compareVolume;
import jinshen.bean.inyard;
import jinshen.bean.inyardStatus;
import jinshen.bean.outyard;
import jinshen.bean.surveyorFee;
import jinshen.bean.tree;
import jinshen.bean.treebuy;
import jinshen.bean.yardInventory;
import jinshen.dao.treeDao;
import jinshen.db.DBcon;

public class treeDaoImpl implements treeDao {
	private DBcon dbc=new DBcon();
	Connection connection = null;
    PreparedStatement pStatement = null;
	
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
	public int addTree(tree c) {
		String sql="insert into tree values(?,?,?,?,?,?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {c.getWorkid(),c.getTreetype(),c.getTlong(),c.getTradius(),c.getNum(),c.getTvolume(),c.getUnitprice(),c.getTotalnum()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public tree findTreeSingle(String sql) {
		tree addr=new tree();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			if(rs.next()) {
				addr.setWorkid(rs.getDouble(1));
				addr.setTreetype(rs.getString(2));
				addr.setTlong(rs.getDouble(3));
				addr.setTradius(rs.getDouble(4));
				addr.setNum(rs.getDouble(5));
				addr.setTvolume(rs.getDouble(6));
				addr.setUnitprice(rs.getDouble(7));
				addr.setTotalnum(rs.getDouble(8));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return addr;
	}

	@Override
	public int delById(String sql) {
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
	public int updateTree(tree c) {
		int res=0;
		try {
			String sql="update tree set treetype=?,tlong=?,tradius=?,num=?,tvolume=?,untiprice=?,totalnum=? where workid=?";
			res=dbc.doUpdate(sql, new Object[] {c.getTreetype(),c.getTlong(),c.getTradius(),c.getNum(),c.getTvolume(),c.getUnitprice(),c.getTotalnum(),c.getWorkid()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public int findMaxid() {
		String sql="select max(workid) from tree";
		int num=1;
		try {
			ResultSet rs=dbc.doQuery(sql,new Object[]{});
			while(rs.next()) {
				num=rs.getInt(1)+1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	@Override
	public int updateTree(String sql)
	{
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
	public int addTreeout(tree c) {
		String sql="insert into treeout values(?,?,?,?,?,?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {c.getWorkid(),c.getTreetype(),c.getTlong(),c.getTradius(),c.getNum(),c.getTvolume(),c.getUnitprice(),c.getTotalnum()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}
	
	@Override
	public int updateTreeout(tree c) {
		int res=0;
		try {
			String sql="update treeout set treetype=?,tlong=?,tradius=?,num=?,tvolume=?,untiprice=?,totalnum=? where workid=?";
			res=dbc.doUpdate(sql, new Object[] {c.getTreetype(),c.getTlong(),c.getTradius(),c.getNum(),c.getTvolume(),c.getUnitprice(),c.getTotalnum(),c.getWorkid()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}
	
	@Override
	public int updateTreeout(String sql)
	{
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
	public int addInyard(inyard cp) {
		String sql = "insert into inyard values(?,?,?,?,?,?,?,?,?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getWorkid(),cp.getCutNum(),cp.getYarddate(), cp.getCutSite(),
					cp.getCarNumber(),cp.getYard(), cp.getBatchNum(), cp.getSurveyor(),cp.getTolTree(),cp.getTolStere(),cp.getSection()});
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
	public int addOutyard(outyard cp) {
		String sql = "insert into outyard values(?,?,?,?,?,?,?,?,?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getWorkid(),cp.getYarddate(),
					cp.getCarNumber(),cp.getYard(), cp.getBatchNum(), cp.getSurveyor(),cp.getToltree(),cp.getTolstere(),cp.getContractnum(),cp.getSaleCalloutorderid(),cp.getSection()});
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
	public int updateInyard(inyard cp,double workid) {
		int res=0;
		try {
			String sql="update inyard set cutNum=?,yarddate=?,cutSite=?,carNumber=?,yard=?,batchNum=?,surveyor=?,toltree=?,tolstere=?,section=? where workid="+workid+"";
			res=dbc.doUpdate(sql, new Object[] {cp.getCutNum(),cp.getYarddate(), cp.getCutSite(),
					cp.getCarNumber(),cp.getYard(), cp.getBatchNum(), cp.getSurveyor(),cp.getTolTree(),cp.getTolStere(),cp.getSection()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}
	
	@Override
	public int updateOutyard(outyard cp) {
		int res=0;
		try {
			String sql="update outyard set workid=?,yarddate=?,carNumber=?,yard=?,batchNum=?,surveyor=?,toltree=?,tolstere=?,contractnum=?,sale_callout_orderid=?,section=?";
			res=dbc.doUpdate(sql, new Object[] {cp.getWorkid(),cp.getYarddate(),cp.getCarNumber(),cp.getYard(), cp.getBatchNum(), cp.getSurveyor(),
					cp.getToltree(),cp.getTolstere(),cp.getContractnum(),cp.getSaleCalloutorderid(),cp.getSection()});
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally {
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
	public inyard findInSingle(String sql) {
		inyard addr=new inyard();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			if(rs.next()) {
				addr.setWorkid(rs.getDouble(1));
				addr.setCutNum(rs.getString(2));
				addr.setYarddate(rs.getDate(3));
				addr.setCutSite(rs.getString(4));
				addr.setCarNumber(rs.getString(5));
				addr.setYard(rs.getString(6));
				//addr.setBatchNum(rs.getDouble(7));
				addr.setSurveyor(rs.getString(7));
				addr.setTolTree(rs.getDouble(8));
				addr.setTolStere(rs.getDouble(9));
				addr.setSection(rs.getString(10));
			}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbc.close();
			}
			return addr;
		}
	@Override
	public outyard findOutSingle(String sql) {
		outyard addr = new outyard();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			if(rs.next()) {
				addr.setWorkid(rs.getDouble(1));
				addr.setYarddate(rs.getDate(2));
				addr.setCarNumber(rs.getString(3));
				addr.setYard(rs.getString(4));
				addr.setBatchNum(rs.getString(5));
				addr.setSurveyor(rs.getString(6));
				addr.setToltree(rs.getDouble(7));
				addr.setTolstere(rs.getDouble(8));
				addr.setContractnum(rs.getString(9));
				addr.setSaleCalloutorderid(rs.getString(10));
				addr.setSection(rs.getString(11));
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
	public compareVolume findVolume(String sql)
	{
		compareVolume addr=new compareVolume();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				addr.setTolstere(rs.getDouble(1));
				//addr.setcutstore(rs.getDouble(2));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return addr;
	}

	@Override
	public int addinyardStatus(inyardStatus c) {
		String sql="insert into inyard_status values(?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {c.getWorkid(),c.getInyardstatus(),c.getInyardreason()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}
	
	@Override
	public List<inyard> findinyards(String sql) {
		List<inyard> work=new ArrayList<inyard>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()){
				inyard w=new inyard();
				w.setWorkid(rs.getDouble(1));
				w.setCutNum(rs.getString(2));
				w.setYarddate(rs.getDate(3));
				w.setCutSite(rs.getString(4));
				w.setCarNumber(rs.getString(5));
				w.setYard(rs.getString(6));
				w.setSurveyor(rs.getString(7));
				w.setSection(rs.getString(8));
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
	public yardInventory findhcpd(String sql) {
		yardInventory w=new yardInventory();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()){
				w.setYardname(rs.getString(1));
				w.setYeart(rs.getString(2));	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return w;
	}

	@Override
	public List<yardInventory> findhyard(String sql) {
		List<yardInventory> work=new ArrayList<yardInventory>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()){
				yardInventory w=new yardInventory();
				w.setYeart(rs.getString(1));
				w.setYardname(rs.getString(2));
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
	public List<yardInventory> findyardList(String sql) {
		List<yardInventory> work=new ArrayList<yardInventory>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()){
				yardInventory w=new yardInventory();
			    w.setYardname(rs.getString(1));
				w.setYeart(rs.getString(2));
				w.setMontht(rs.getString(3));
				w.setInvolume(rs.getDouble(4));
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
	public List<outyard> findOutyardC(String sql) {
		List<outyard> cnw =new ArrayList<outyard>();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		while(rs.next()) {
    			outyard cn = new outyard();
    			cn.setContractnum(rs.getString(1));
                cn.setWorkid(rs.getDouble(2));
                cn.setSaleCalloutorderid(rs.getString(3));
                cn.setYard(rs.getString(4));
                cn.setSection(rs.getString(5));
                cn.setBatchNum(rs.getString(6));
                cn.setYarddate(rs.getDate(7));
                cn.setCarNumber(rs.getString(8));
                cn.setSurveyor(rs.getString(9));
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
	public outyard searchoutyard() {
		String sql="select contractnum,workid,sale_callout_orderid,yard,section,batchNum,yarddate,carNumber,surveyor from outyard";
    	ResultSet rs = null;
    	outyard cn=new outyard();
    	try {
    		connection = DBcon.getConnection();
    		pStatement = connection.prepareStatement(sql);
    		rs = pStatement.executeQuery();
    		while (rs.next()) {
    			cn.setContractnum(rs.getString(1));
                cn.setWorkid(rs.getDouble(2));
                cn.setSaleCalloutorderid(rs.getString(3));              
                cn.setYard(rs.getString(4));
                cn.setSection(rs.getString(5));
                cn.setBatchNum(rs.getString(6));
                cn.setYarddate(rs.getDate(7));
                cn.setCarNumber(rs.getString(8));
                cn.setSurveyor(rs.getString(9));
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
	public treebuy findoutyardCostTable(String sql) {
		treebuy addr = new treebuy();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			if(rs.next()) {
				addr.setWorkid(rs.getDouble(1));
				addr.setCutNum(rs.getString(2));
				addr.setCutdate(rs.getDate(3));
				addr.setCutSite(rs.getString(4));
				addr.setTreetype(rs.getString(5));
				addr.setTlong(rs.getDouble(6));
				addr.setTradius(rs.getDouble(7));
				addr.setNum(rs.getDouble(8));
				addr.setTvolume(rs.getDouble(9));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return addr;
	}

	@Override
	public outyard findOutSingle1(String sql) {
		outyard addr = new outyard();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			if(rs.next()) {
				addr.setWorkid(rs.getDouble(1));
				addr.setContractnum(rs.getString(2));
				addr.setSaleCalloutorderid(rs.getString(3));
				addr.setYard(rs.getString(4));
				addr.setSection(rs.getString(5));
				addr.setBatchNum(rs.getString(6));
				addr.setYarddate(rs.getDate(7));
				addr.setCarNumber(rs.getString(8));
				addr.setSurveyor(rs.getString(9));	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return addr;
	}

	@Override
	public List<surveyorFee> findSurveyorFee(String sql) {
		List<surveyorFee> addrList=new ArrayList<surveyorFee>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				surveyorFee addr=new surveyorFee();
				addr.setWorkid(rs.getDouble(1));
				addr.setTreetype(rs.getString(2));
				addr.setTlong(rs.getDouble(3));
				addr.setTradius(rs.getDouble(4));
				addr.setNum(rs.getDouble(5));
				addr.setTvolume(rs.getDouble(6));
				//addr.setUnitprice(rs.getDouble(7));
				//addr.setPrice(rs.getDouble(8));
				//addr.setSurveyorFee(rs.getDouble(9));					
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
	
	//保存检尺费信息
			@Override
			public int saveSurveyorFee(surveyorFee cp) {
				String sql="insert into surveyor_fee values(?,?,?,?)";
				int res=0;
				try {
					res=dbc.doUpdate(sql, new Object[] {cp.getWorkid(),cp.getUnitprice(),cp.getPrice(),cp.getSurveyorFee()});
							}catch(Exception e) {
								e.printStackTrace();
							}finally {
								dbc.close();
							}
							return res;
			}

			@Override
			public List<surveyorFee> findSurveyorFeeList(String sql) {
				List<surveyorFee> addrList=new ArrayList<surveyorFee>();
				try {
					ResultSet rs=dbc.doQuery(sql, new Object[] {});
					while(rs.next()) {
						surveyorFee addr=new surveyorFee();
						addr.setWorkid(rs.getDouble(1));
						addr.setUnitprice(rs.getDouble(2));
						addr.setTvolume(rs.getDouble(3));
						addr.setSurveyorFee(rs.getDouble(4));					
						addrList.add(addr);
					}
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					dbc.close();
				}
				return addrList;
			}


}
