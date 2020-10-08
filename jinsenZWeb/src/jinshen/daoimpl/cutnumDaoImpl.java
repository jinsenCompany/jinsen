package jinshen.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jinshen.bean.cuntnumproj;
import jinshen.bean.cutnum;
import jinshen.bean.cutnumApply;
import jinshen.bean.cutnumCheck;
import jinshen.bean.cutnumProduced;
import jinshen.bean.cutnumProgress;
import jinshen.bean.cutnumStatus;
import jinshen.bean.cutnumTable;
import jinshen.bean.cutnumWatch;
import jinshen.bean.cutnumfeedback;
import jinshen.bean.projectPackTable;
import jinshen.bean.projectpackage;
import jinshen.dao.cutnumDao;
import jinshen.db.DBcon;

public class cutnumDaoImpl implements cutnumDao{
	private DBcon dbc=new DBcon();
    Connection connection = null;
    PreparedStatement pStatement = null;
    
    @Override
    public int addCutnum(cutnum cp) {
    	String sql="insert into cutnum values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getCutnumid(),cp.getCutnum(),cp.getCertificatenum(),cp.getCompany(),cp.getCutaddress(),cp.getCutvillage(),cp.getQuartel(),cp.getLargeblock(),cp.getSmallblock(),cp.getCuttype(),cp.getCutmethod(),
					cp.getCutqiang(),cp.getCutarea(),cp.getForestid(),cp.getStarttime(),cp.getEndtime(),cp.getVolume(),cp.getSizewoodAll(),cp.getSmalltimberAll(),cp.getShorttimberAll(),cp.getFirewoodAll(),cp.getProjectid(),cp.getCutnumfile(),cp.getCreatcutDate()});
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
    public int addCutnumTree(cutnum cp) {
    	String sql="insert into cutnum_tree values(?,?,?,?,?,?,?)";
    	int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getCutnumid(),cp.getTreetype(),cp.getTotal(),cp.getSizewood(),cp.getSmalltimber(),cp.getShorttimber(),cp.getFirewood()});
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
    public int updateCutnum(cutnum cp,int id) {
    	int res=0;
    	try {   		
    		String sql="update cutnum set cutnum=?,certificatenum=?,company=?,cutaddress=?,cutvillage=?,quartel=?,largeblock=?,smallblock=?,cuttype=?,cutmethod=?,cutqiang=?,cutarea=?,forestid=?,starttime=?,endtime=?,volume=?,sizewoodAll=?,smalltimberAll=?,shorttimberAll=?,firewoodAll=?,creatcutDate=? where cutnumid='"+id+"'";
    		res=dbc.doUpdate(sql, new Object[] {cp.getCutnum(),cp.getCertificatenum(),cp.getCompany(),cp.getCutaddress(),cp.getCutvillage(),cp.getQuartel(),cp.getLargeblock(),cp.getSmallblock(),cp.getCuttype(),cp.getCutmethod(),cp.getCutqiang(),cp.getCutarea(),cp.getForestid(),cp.getStarttime(),cp.getEndtime(),cp.getVolume(),cp.getSizewoodAll(),cp.getSmalltimberAll(),cp.getShorttimberAll(),cp.getFirewoodAll(),cp.getCreatcutDate()});
    	}
    	catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;

    }
    
    @Override
    public List<cutnum> findCutnum(){
    	String sql="SELECT cutnumid,cutnum,number,company,cutsite,sizhi,treeorigin,foresttype,treetype,ownership,forestid,cuttype,cutmethod,cutqiang,cutarea,treenum,cutstore,volume,starttime,endtime,certifier,updatedate,updatevolume,updatenum from cutnum";
    	ResultSet rs = null;
    	List<cutnum> addrList=new ArrayList<cutnum>();
    	try {
    		 connection = DBcon.getConnection();
 	        pStatement = connection.prepareStatement(sql);
 	        rs = pStatement.executeQuery();
			while(rs.next()) {
				cutnum cn=new cutnum();
				cn.setCutnumid(rs.getInt(1));
				cn.setCutnum(rs.getString(2));
				cn.setNumber(rs.getDouble(3));
				cn.setCompany(rs.getString(4));
				cn.setCutsite(rs.getString(5));
				cn.setSizhi(rs.getString(6));
				cn.setTreeorigin(rs.getString(7));
				cn.setForesttype(rs.getString(8));
				cn.setTreetype(rs.getString(9));
				cn.setOwnership(rs.getString(10));
				cn.setForestid(rs.getString(11));
				cn.setCuttype(rs.getString(12));
				cn.setCutmethod(rs.getString(13));
				cn.setCutqiang(rs.getString(14));
				cn.setCutarea(rs.getString(15));
				cn.setTreenum(rs.getDouble(16));
				cn.setCutstore(rs.getDouble(17));
				cn.setVolume(rs.getDouble(18));
				cn.setStarttime(rs.getDate(19));
				cn.setEndtime(rs.getDate(20));
				cn.setCertifier(rs.getString(21));
				cn.setUpdatedate(rs.getDate(22));
				cn.setUpdatevolume(rs.getDouble(23));
				cn.setUpdatenum(rs.getDouble(24));
				addrList.add(cn);
			}
			return addrList;
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
    public cutnum findCodeSingle(String sql) {
    	cutnum cn =new cutnum();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		if(rs.next()) {
    			cn.setCutnumid(rs.getInt(1));
    			cn.setCutnum(rs.getString(2));
    			cn.setCertificatenum(rs.getString(3));
    			cn.setCompany(rs.getString(4));
    			cn.setCutaddress(rs.getString(5));
    			cn.setCutvillage(rs.getString(6));
    			cn.setQuartel(rs.getString(7));
    			cn.setLargeblock(rs.getString(8));
    			cn.setSmallblock(rs.getString(9));
    			cn.setCuttype(rs.getString(10));
    			cn.setCutmethod(rs.getString(11));
    			cn.setCutqiang(rs.getString(12));
    			cn.setCutarea(rs.getString(13));
    			cn.setForestid(rs.getString(14));
    			cn.setStarttime(rs.getDate(15));
    			cn.setEndtime(rs.getDate(16));
    			cn.setVolume(rs.getDouble(17));
    			cn.setSizewoodAll(rs.getString(18));
    			cn.setSmalltimberAll(rs.getString(19));
    			cn.setShorttimberAll(rs.getString(20));
    			cn.setFirewoodAll(rs.getString(21));
    			cn.setProjectPackageName(rs.getString(22));
				cn.setCutnumfile(rs.getString(23));
				cn.setCreatcutDate(rs.getTimestamp(24));
    		}
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return cn;
    }
    //原版
    /*@Override
    public cutnum findCodeSingle(String sql) {
    	cutnum cn =new cutnum();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		if(rs.next()) {
    			cn.setCutnum(rs.getString(1));
    			cn.setCertificatenum(rs.getString(2));
				cn.setNumber(rs.getDouble(3));
				cn.setCompany(rs.getString(4));
				cn.setCutsite(rs.getString(5));
				cn.setSizhi(rs.getString(6));
				cn.setGpsinfo(rs.getString(7));
				cn.setTreeorigin(rs.getString(8));
				cn.setForesttype(rs.getString(9));
				cn.setTreetype(rs.getString(10));
				cn.setOwnership(rs.getString(11));
				cn.setForestid(rs.getString(12));
				cn.setCuttype(rs.getString(13));
				cn.setCutmethod(rs.getString(14));
				cn.setCutqiang(rs.getString(15));
				cn.setCutarea(rs.getString(16));
				cn.setTreenum(rs.getDouble(17));
				cn.setCutstore(rs.getDouble(18));
				cn.setVolume(rs.getDouble(19));
				cn.setStarttime(rs.getDate(20));
				cn.setEndtime(rs.getDate(21));
				cn.setCertifier(rs.getString(22));
				cn.setUpdatedate(rs.getDate(23));
				cn.setUpdatevolume(rs.getDouble(24));
				cn.setUpdatenum(rs.getDouble(25));
				cn.setCutnumfile(rs.getString(26));
    		}
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return cn;
    }*/
    
    @Override
    public cutnum printCutnum() {
    	String sql="SELECT cutnumid,cutnum,number,company,cutsite,sizhi,treeorigin,foresttype,treetype,ownership,forestid,cuttype,cutmethod,cutqiang,cutarea,treenum,cutstore,volume,starttime,endtime,certifier,updatedate,updatevolume,updatenum from cutnum";
    	ResultSet rs = null;
    	cutnum cn=new cutnum();
    	try {
    		connection = DBcon.getConnection();
    		pStatement = connection.prepareStatement(sql);
    		rs = pStatement.executeQuery();
    		while (rs.next()) {
    			cn.setCutnumid(rs.getInt(1));
				cn.setCutnum(rs.getString(2));
				cn.setNumber(rs.getDouble(3));
				cn.setCompany(rs.getString(4));
				cn.setCutsite(rs.getString(5));
				cn.setSizhi(rs.getString(6));
				cn.setTreeorigin(rs.getString(7));
				cn.setForesttype(rs.getString(8));
				cn.setTreetype(rs.getString(9));
				cn.setOwnership(rs.getString(10));
				cn.setForestid(rs.getString(11));
				cn.setCuttype(rs.getString(12));
				cn.setCutmethod(rs.getString(13));
				cn.setCutqiang(rs.getString(14));
				cn.setCutarea(rs.getString(15));
				cn.setTreenum(rs.getDouble(16));
				cn.setCutstore(rs.getDouble(17));
				cn.setVolume(rs.getDouble(18));
				cn.setStarttime(rs.getDate(19));
				cn.setEndtime(rs.getDate(20));
				cn.setCertifier(rs.getString(21));
				cn.setUpdatedate(rs.getDate(22));
				cn.setUpdatevolume(rs.getDouble(23));
				cn.setUpdatenum(rs.getDouble(24));
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
    public List<cutnum> findCutnumC(String sql){
    	List<cutnum> cnw =new ArrayList<cutnum>();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		while(rs.next()) {
    			cutnum cn = new cutnum();	
    			cn.setCompany(rs.getString(1));
    			cn.setCertificatenum(rs.getString(2));
    			cn.setCutnum(rs.getString(3));
    			cn.setCutaddress(rs.getString(4));
    			cn.setCutvillage(rs.getString(5));
    			cn.setQuartel(rs.getString(6));
    			cn.setLargeblock(rs.getString(7));
    			cn.setSmallblock(rs.getString(8));
    			cn.setVolume(rs.getDouble(9));
    			cn.setCreatcutDate(rs.getTimestamp(10));
				cnw.add(cn);
    		}
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return cnw;
    }
    //含有工程包
    @Override
    public List<cutnum> findCutnumPid(String sql){
    	List<cutnum> cnw =new ArrayList<cutnum>();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		while(rs.next()) {
    			cutnum cn = new cutnum();	
    			cn.setCompany(rs.getString(1));
    			cn.setCertificatenum(rs.getString(2));
    			cn.setCutnum(rs.getString(3));
    			cn.setCutaddress(rs.getString(4));
    			cn.setCutvillage(rs.getString(5));
    			cn.setQuartel(rs.getString(6));
    			cn.setLargeblock(rs.getString(7));
    			cn.setSmallblock(rs.getString(8));
    			cn.setVolume(rs.getDouble(9));
    			cn.setProjectPackageName(rs.getString(10));
				cnw.add(cn);
    		}
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return cnw;
    }
    
    //砍伐证审核界面
    @Override
    public List<cutnumWatch> findCuteSingle(String sql) {
    	List<cutnumWatch> cnw =new ArrayList<cutnumWatch>();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		while(rs.next()) {
    			cutnumWatch cn = new cutnumWatch();
    			cn.setCutnumid(rs.getDouble(1));
    			cn.setCutnum(rs.getString(2));
				cn.setNumber(rs.getDouble(3));
				cn.setCompany(rs.getString(4));
				cn.setcutsite(rs.getString(5));
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
    public List<cutnum> findCutnumproject(String sql){
    	List<cutnum> cnw =new ArrayList<cutnum>();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		while(rs.next()) {
    			cutnum cn = new cutnum();
    			cn.setCutnum(rs.getString(1));
    			cn.setCertificatenum(rs.getString(2));
    			cn.setStarttime(rs.getDate(3));
    			cn.setEndtime(rs.getDate(4));
				cn.setCompany(rs.getString(5));
				cn.setCutarea(rs.getString(6));
				cn.setVolume(rs.getDouble(7));
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
    public List<cutnum> findCutnumprojectStatus(String sql){
    	List<cutnum> cnw =new ArrayList<cutnum>();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		while(rs.next()) {
    			cutnum cn = new cutnum();
    			cn.setCutnum(rs.getString(1));
    			cn.setCertificatenum(rs.getString(2));
    			cn.setStarttime(rs.getDate(3));
    			cn.setEndtime(rs.getDate(4));
				cn.setCompany(rs.getString(5));
				cn.setCutarea(rs.getString(6));
				cn.setVolume(rs.getDouble(7));
				cn.setcStatus(rs.getString(8));
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
    public int addprojectid(String each,cutnum cn) {
    	String sql="update cutnum set proj_package_Name=? WHERE cutnum='"+each+"'";
    	int res = 0;
    	try {
    		res=dbc.doUpdate(sql, new Object[] {cn.getProjectPackageName()});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	dbc.close();
        }
    	return res;
    }
    
   /* @Override
    public int addprojectpackage(projectpackage cp) {
    	String sql="insert into proj_package values(?,?,?)";
    	int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getprojectPackageid(),cp.getprojectPackagename(),cp.getmanagerPhone()});
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally {
			dbc.close();
		}
		return res;
    }*/
    @Override
    public int addprojectpackage(projectpackage cp) {
    String sql="insert into proj_package values(?,?,?,?,?,?,?,?)";
	int res=0;
	try {
		res=dbc.doUpdate(sql, new Object[] {cp.getprojectPackageid(),cp.getProjectPackageName(),cp.getProjpackageStarttime(),cp.getContractNum(),cp.getContractionSide(),cp.getCuttime(),cp.getForester(),cp.getAccessory()});
	}catch (Exception e) 
	{
		e.printStackTrace();
	}
	finally {
		dbc.close();
	}
	return res;
    }
    
    /*@Override
    public int updateprojectpackage(projectpackage cp) {
    	int res=0;
    	try {
    		String sql="update proj_package set proj_package_id,proj_package_manage=?,manager_phone=? where proj_package_id=?";
    		res=dbc.doUpdate(sql, new Object[] {cp.getprojectPackageid(),cp.getprojectPackagename(),cp.getmanagerPhone()});
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
    }*/
    @Override
    public int updateprojectpackage(projectpackage cp) {
    	int res=0;
    	try {
    		String sql="update proj_package set proj_package_Name=?,projpackageStarttime=?,contractNum=?,contractionSide=?,cuttime=?,forester=? proj_package_Name=?";
    		res=dbc.doUpdate(sql, new Object[] {cp.getProjectPackageName(),cp.getProjpackageStarttime(),cp.getContractNum(),cp.getContractionSide(),cp.getCuttime(),cp.getForester()});
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
    }
    
    @Override
    public projectpackage findprojectpackage(String sql) {
    	projectpackage  pt= new projectpackage();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		if(rs.next()) {
    			pt.setprojectPackageid(rs.getInt(1));
    			pt.setProjectPackageName(rs.getString(2));
    			pt.setProjpackageStarttime(rs.getDate(3));
    			pt.setContractNum(rs.getString(4));
    			pt.setContractionSide(rs.getString(5));
    			pt.setCuttime(rs.getString(6));
    			pt.setForester(rs.getString(7));
    			pt.setAccessory(rs.getString(8));
    		}
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return pt;
    }
    
    @Override
    public List<projectpackage> seeprojectpackag(String sql){
    	List<projectpackage> ptp = new ArrayList<projectpackage>();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		while(rs.next()) {
    			projectpackage  pt= new projectpackage();
    			pt.setprojectPackageid(rs.getInt(1));
    			pt.setProjectPackageName(rs.getString(2));
    			pt.setProjpackageStarttime(rs.getTimestamp(3));
    			pt.setContractNum(rs.getString(4));
    			pt.setContractionSide(rs.getString(5));
    			pt.setCuttime(rs.getString(6));
    			pt.setForester(rs.getString(7));
    			pt.setAccessory(rs.getString(8));
    			ptp.add(pt);
    		}
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return ptp;
    }

	@Override
	public int addCutnumApply(cutnumApply cp) {
		String sql="insert into cutnum_application values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getapplyid(),cp.getDesignum(),cp.getCutaddress(),cp.getCutaddress(),cp.getCutvillage(),cp.getQuartel(),cp.getLargeblock(),cp.getSmallblock(),cp.getDesignbook(),cp.getOrigin(),
					cp.getForestage(),cp.getTypeconsist(),cp.getManagetype(),cp.getForestage(),cp.getCutarea(),cp.getCuttype(),cp.getCuttype(),cp.getCutstrength(),cp.getTreetype(),cp.getCutvolume(),cp.getCutnumer(),cp.getTotal(),
					cp.getCutintermediate(),cp.getTotal2(),cp.getSizewood(),cp.getSmalltimber(),cp.getShorttimber(),cp.getFirewood(),cp.getCutpath(),cp.getApplaydate()});
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
	public int updateCutnumApply(double apid,cutnumApply cp) {
		int res=0;
    	try {
    		String sql="update cutnum_application set designum=?,cut_reson=?,cut_adress=?,cut_village=?,quartel=?,larg_block=?,small_block=?,designbook=?,origin=?,forest_type=?,type_consist=?,mange_type=?,forest_age=?,cut_area=?,cut_type=?,cut_way=?,"
    				+ "cut_strength=?,tree_type=?,cut_volume=?,cut_num=?,total=?,cut_intermediate=?,total2=?,size_wood=?,small_timber=?,short_timber=?,firewood=?,cutpath=?,applay_date=? WHERE apply_id="+apid+" ";
    		res=dbc.doUpdate(sql, new Object[] {cp.getDesignum(),cp.getCutaddress(),cp.getCutaddress(),cp.getCutvillage(),cp.getQuartel(),cp.getLargeblock(),cp.getSmallblock(),cp.getDesignbook(),cp.getOrigin(),
					cp.getForestage(),cp.getTypeconsist(),cp.getManagetype(),cp.getForestage(),cp.getCutarea(),cp.getCuttype(),cp.getCuttype(),cp.getCutstrength(),cp.getTreetype(),cp.getCutvolume(),cp.getCutnumer(),cp.getTotal(),
					cp.getCutintermediate(),cp.getTotal2(),cp.getSizewood(),cp.getSmalltimber(),cp.getShorttimber(),cp.getFirewood(),cp.getCutpath(),cp.getApplaydate()});
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public int addcutumapplystatus(cutnumfeedback cn) {
		String sql="insert into feedback_application values(?,?,?,?)";
    	int res = 0;
    	try {
    		res=dbc.doUpdate(sql, new Object[] {cn.getApplyid(),cn.getStatus(),cn.getUnablereason(),cn.getReplenishpath()});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	dbc.close();
        }
    	return res;
	}

	@Override
	public cutnumfeedback findApplyid(String sql) {
		cutnumfeedback  cf= new cutnumfeedback();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		if(rs.next()) {
    			cf.setApplyid(rs.getDouble(1));
    		}
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return cf;
	}

	@Override
	public List<cutnumApply> seecutnumApply(String sql) {
		List<cutnumApply> cp = new ArrayList<cutnumApply>();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		while(rs.next()) {
    			cutnumApply  pt= new cutnumApply();
    			pt.setapplyid(rs.getDouble(1));
    			pt.setApplaydate(rs.getDate(2));
    			pt.setDesignum(rs.getString(3));
    			pt.setCutaddress(rs.getString(4));
    			pt.setTreetype(rs.getString(5));
    			pt.setCutvolume(rs.getDouble(6));
    			cp.add(pt);
    		}
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return cp;
	}

	@Override
	public cutnumApply findCutnumApply(String sql) {
		cutnumApply  ca= new cutnumApply();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		if(rs.next()) {
    			//cp.setapplyid(applyid);
    			ca.setDesignum(rs.getString(1));
    			ca.setCutreason(rs.getString(2));
    			ca.setCutaddress(rs.getString(3));
    			ca.setCutvillage(rs.getString(4));
    			ca.setQuartel(rs.getString(5));
    			ca.setLargeblock(rs.getString(6));
    			ca.setSmallblock(rs.getString(7));
    			ca.setDesignbook(rs.getString(8));;
    			ca.setOrigin(rs.getString(9));
    			ca.setForesttype(rs.getString(10));
    			ca.setTypeconsist(rs.getString(11));
    			ca.setManagetype(rs.getString(12));
    			ca.setForestage(rs.getDouble(13));
    			ca.setCutarea(rs.getDouble(14));
    			ca.setCuttype(rs.getString(15));
    			ca.setCutway(rs.getString(16));
    			ca.setCutstrength(rs.getString(17));
    			ca.setTreetype(rs.getString(18));
    			ca.setCutvolume(rs.getDouble(19));
    			ca.setCutnumer(rs.getInt(20));
    			ca.setTotal(rs.getDouble(21));
    			ca.setCutintermediate(rs.getDouble(22));
    			ca.setTotal2(rs.getDouble(23));
    			ca.setSizewood(rs.getString(24));
    			ca.setSmalltimber(rs.getString(25));
    			ca.setShorttimber(rs.getString(26));
    			ca.setFirewood(rs.getString(27));
    			ca.setCutpath(rs.getString(28));
    			ca.setApplaydate(rs.getDate(29));
    		}
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return ca;
	}

	@Override
	public int updatecutumapplystatus(double apid,cutnumfeedback cn) {
		String sql="update feedback_application set status=? WHERE apply_id="+apid+"";
    	int res = 0;
    	try {
    		res=dbc.doUpdate(sql, new Object[] {cn.getStatus()});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	dbc.close();
        }
    	return res;
	}
	
	@Override
	public int updatecutumapplystatusN(double apid,cutnumfeedback cn) {
		String sql="update feedback_application set status=?,unable_reson=? WHERE apply_id="+apid+"";
    	int res = 0;
    	try {
    		res=dbc.doUpdate(sql, new Object[] {cn.getStatus(),cn.getUnablereason()});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	dbc.close();
        }
    	return res;
	}

	@Override
	public cutnumfeedback findCutfeedback(String sql) {
		cutnumfeedback  cf= new cutnumfeedback();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		if(rs.next()) {
    			cf.setUnablereason(rs.getString(1));
    		}
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return cf;
	}

	@Override
	public int updateCutnumApplypath(double apid,cutnumfeedback cn) {
		String sql="update feedback_application set status=?,replenish_path=? WHERE apply_id="+apid+"";
    	int res = 0;
    	try {
    		res=dbc.doUpdate(sql, new Object[] {cn.getStatus(),cn.getReplenishpath()});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	dbc.close();
        }
    	return res;
	}
	@Override
	public cutnumfeedback findCutfeedbackP(String sql) {
		cutnumfeedback  cf= new cutnumfeedback();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		if(rs.next()) {
    			cf.setUnablereason(rs.getString(1));
    			cf.setReplenishpath(rs.getString(2));
    		}
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return cf;
	}

	@Override
	public cutnumStatus findCutnumStatus(String sql) {
		cutnumStatus  cf= new cutnumStatus();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		if(rs.next()) {
    			cf.setCutnumid(rs.getInt(1));
    		}
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return cf;
	}

	@Override
	public int addCutnumStatus(cutnumStatus cp) {
		String sql="insert into cutnum_status values(?,?,?)";
    	int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getCutnumid(),cp.getStatus(),cp.getCutnumVolume()});
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
	public int updateCutnumStatus(cutnumStatus cp,double cutnumid) {
		int res=0;
    	try {
    		String sql="update cutnum_status set status=? WHERE cutnumid="+cutnumid+"";
    		res=dbc.doUpdate(sql, new Object[] {cp.getStatus()});
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}
	@Override
	public List<cutnum> findCutnumF(String sql) {
		List<cutnum> cnd=new ArrayList<cutnum>();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		while(rs.next()) {
    			cutnum  cn= new cutnum();
    			cn.setCutnumid(rs.getInt(1));
				cn.setCutnum(rs.getString(2));
				cn.setCertificatenum(rs.getString(3));
				cn.setNumber(rs.getDouble(4));
				cn.setCompany(rs.getString(5));
				cn.setCutsite(rs.getString(6));
				cn.setSizhi(rs.getString(7));
				cn.setGpsinfo(rs.getString(8));
				cn.setTreeorigin(rs.getString(9));
				cn.setForesttype(rs.getString(10));
				cn.setTreetype(rs.getString(11));
				cn.setOwnership(rs.getString(12));
				cn.setForestid(rs.getString(13));
				cn.setCuttype(rs.getString(14));
				cn.setCutmethod(rs.getString(15));
				cn.setCutqiang(rs.getString(16));
				cn.setCutarea(rs.getString(17));
				cn.setTreenum(rs.getDouble(18));
				cn.setCutstore(rs.getDouble(19));
				cn.setVolume(rs.getDouble(20));
				cn.setStarttime(rs.getDate(21));
				cn.setEndtime(rs.getDate(22));
				cn.setCertifier(rs.getString(23));
				cn.setUpdatedate(rs.getDate(24));
				cn.setUpdatevolume(rs.getDouble(25));
				cn.setUpdatenum(rs.getDouble(26));
				cnd.add(cn);
    		}
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return cnd;
	}

	@Override
	public List<cuntnumproj> findCutnumproject1(String sql) {
		List<cuntnumproj> cnw =new ArrayList<cuntnumproj>();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		while(rs.next()) {
    			cuntnumproj cn = new cuntnumproj();
    			cn.setUpdatedate(rs.getDate(1));;
    			cn.setProjectPackagename(rs.getString(2));
    			cn.setCutnum(rs.getString(3));
    			cn.setCertificatenum(rs.getString(4));
    			cn.setCutarea(rs.getDouble(5));
    			cn.setProjectPackagename(rs.getString(6));
    			cn.setManagerPhone(rs.getString(7));
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
	public List<cutnumCheck> findCutnumCheck(String sql) {
		List<cutnumCheck> cnw =new ArrayList<cutnumCheck>();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		while(rs.next()) {
    			cutnumCheck cn = new cutnumCheck();
    			cn.setCertificatenum(rs.getString(1));
    			cn.setCutnum(rs.getString(2));
    			cn.setStarttime(rs.getDate(3));
    			cn.setEndtime(rs.getDate(4));
				cn.setStatus(rs.getString(5));
				cn.setCutaddess(rs.getString(6));
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
	public int addcutnumCheck(cutnumCheck cp) {
		String sql="insert into cutnum_check values(?,?,?,?,?,?,?,?)";
    	int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getCheckid(),cp.getCutnum(),cp.getCheckdate(),cp.getCheckcontend(),cp.getCheckresult(),
					cp.getCheckpeople(),cp.getCheckfileO(),cp.getCheckfileT()});
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
	public cutnumProgress findProgress(String sql) {
		cutnumProgress cn =new cutnumProgress();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		if(rs.next()) {
    			cn.setCutnum(rs.getString(1));
    			cn.setProjectPackageid(rs.getDouble(2));
    			cn.setCutsite(rs.getString(3));
    			cn.setCutarea(rs.getDouble(4));
    			cn.setCutstore(rs.getDouble(5));
    			cn.setStarttime(rs.getDate(6));
    			cn.setEndtime(rs.getDate(7));
    			cn.setStatus(rs.getInt(8));
    		}
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return cn;
	}

	@Override
	public List<cutnum> findCutnumtree(String sql) {
		List<cutnum> cnw =new ArrayList<cutnum>();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		while(rs.next()) {
    			cutnum cn = new cutnum();
    			cn.setCutnumid(rs.getInt(1));
    			cn.setTreetype(rs.getString(2));
    			cn.setTotal(rs.getString(3));
    			cn.setSizewood(rs.getString(4));
    			cn.setSmalltimber(rs.getString(5));
    			cn.setShorttimber(rs.getString(6));
    			cn.setFirewood(rs.getString(7));
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
	public int updateCutnumtree(cutnum cp, int id, String n) {
		int res=0;
    	try {   		
    		String sql="update cutnum_tree set total=?,sizewood=?,smalltimber=?,shorttimber=?,firewood=? where cutnumid='"+id+"' and treetype='"+n+"'";
    		res=dbc.doUpdate(sql, new Object[] {cp.getTotal(),cp.getSizewood(),cp.getSmalltimber(),cp.getShorttimber(),cp.getFirewood()});
    	}
    	catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public int updateCutnumvolume(cutnumStatus cp, double cutnumid) {
		int res=0;
    	try {
    		String sql="update cutnum_status set statusVolume=? WHERE cutnumid="+cutnumid+"";
    		res=dbc.doUpdate(sql, new Object[] {cp.getCutnumVolume()});
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public List<projectPackTable> findProjectDetails(String sql) {
		List<projectPackTable> ptp = new ArrayList<projectPackTable>();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		while(rs.next()) {
    			projectPackTable  pt= new projectPackTable();
    			pt.setProjpackageStarttime(rs.getString(1));
    			pt.setProjectPackageName(rs.getString(2));
    			pt.setCertificatenum(rs.getString(3));
    			pt.setCutarea(rs.getString(4));
    			pt.setContractNum(rs.getString(5));
    			pt.setContractionSide(rs.getString(6));
    			pt.setCuttime(rs.getString(7));
    			pt.setForester(rs.getString(8));
    			pt.setAccessory(rs.getString(9));
    			pt.setCutnum(rs.getString(10));
    			pt.setTotalDesign(rs.getString(11));
    			pt.setShanTreeDesign(rs.getString(12));
    			pt.setSongTreeDesign(rs.getString(13));
    			pt.setZaTreeDesign(rs.getString(14));
    			pt.setTotalActual(rs.getString(15));
    			pt.setShanTreeActual(rs.getString(16));
    			pt.setSongTreeActual(rs.getString(17));
    			pt.setZaTreeActual(rs.getString(18));
    			ptp.add(pt);
    		}
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return ptp;
	}

	@Override
	public List<projectPackTable> findDesigntree(String sql) {
		List<projectPackTable> ptp = new ArrayList<projectPackTable>();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		while(rs.next()) {
    			projectPackTable  pt= new projectPackTable();
    			pt.setCutnumid(rs.getString(1));
    			pt.setTotalDesign(rs.getString(2));
    			pt.setShanTreeDesign(rs.getString(3));
    			pt.setSongTreeDesign(rs.getString(4));
    			pt.setZaTreeDesign(rs.getString(5));
    			ptp.add(pt);
    		}
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return ptp;
	}

	@Override
	public List<projectPackTable> findActualtree(String sql) {
		List<projectPackTable> ptp = new ArrayList<projectPackTable>();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		while(rs.next()) {
    			projectPackTable  pt= new projectPackTable();
    			pt.setTotalActual(rs.getString(1));
    			pt.setShanTreeActual(rs.getString(2));
    			pt.setSongTreeActual(rs.getString(3));
    			pt.setZaTreeActual(rs.getString(4));
    			ptp.add(pt);
    		}
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return ptp;
	}
	
	@Override
    public List<cutnumTable> findCutnumT(String sql){
    	List<cutnumTable> cnw =new ArrayList<cutnumTable>();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		while(rs.next()) {
    			cutnumTable cn = new cutnumTable();	
    			cn.setCompany(rs.getString(1));
    			cn.setCertificatenum(rs.getString(2));
    			cn.setCutnum(rs.getString(3));
    			cn.setCutaddress(rs.getString(4));
    			cn.setCutvillage(rs.getString(5));
    			cn.setQuartel(rs.getString(6));
    			cn.setLargeblock(rs.getString(7));
    			cn.setSmallblock(rs.getString(8));
    			cn.setCreatcutDate(rs.getString(9));   
   			
    			cn.setTotalDesign(rs.getString(10));
				cn.setShanTreeDesign(rs.getString(11));
				cn.setSongTreeDesign(rs.getString(12));
				cn.setZaTreeDesign(rs.getString(13));
	           /*			
				cn.setTotalProduced(rs.getString(14)); 
				cn.setShamu(rs.getString(15));
				cn.setSongmu(rs.getString(16)); 
				cn.setZamu(rs.getString(17));
				*/
				cn.setTotalActual(rs.getString(14));
				cn.setShanTreeActual(rs.getString(15));
				cn.setSongTreeActual(rs.getString(16));
				cn.setZaTreeActual(rs.getString(17));
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
    public int addCutnumProduced(cutnumProduced cp) {
    	String sql="insert into cutnum_produced values(?,?,?,?,?,?,?,?)";
    	int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getCutnum(),cp.getCertificatenum(),cp.getShamu(),cp.getSongmu(),cp.getZamu(),cp.getTotalProduced(),cp.getFaquManager(),cp.getCreatDateProduced()});
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
	public List<cutnum> findCutnumCOn(String sql) {
		List<cutnum> cnw =new ArrayList<cutnum>();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		while(rs.next()) {
    			cutnum cn = new cutnum();	
    			cn.setCompany(rs.getString(1));
    			cn.setCertificatenum(rs.getString(2));
    			cn.setCutnum(rs.getString(3));
    			cn.setCutaddress(rs.getString(4));
    			cn.setCutvillage(rs.getString(5));
    			cn.setQuartel(rs.getString(6));
    			cn.setLargeblock(rs.getString(7));
    			cn.setSmallblock(rs.getString(8));
    			cn.setVolume(rs.getDouble(9));
    			cn.setCreatcutDate(rs.getTimestamp(10));
    			cn.setShorttimber(rs.getString(11));
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
	public List<cutnum> findCutnumTotal(String sql) {
		List<cutnum> cnw =new ArrayList<cutnum>();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		while(rs.next()) {
    			cutnum cn = new cutnum();	
    			cn.setCutarea(rs.getString(1));
    			cn.setVolume(rs.getDouble(2));
    			cn.setTreetype(rs.getString(3));
    			cn.setTotal(rs.getString(4));
    			cn.setProjectid(rs.getInt(5));//采伐证数量
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
	public List<projectPackTable> findDaotree(String sql) {
		List<projectPackTable> ptp = new ArrayList<projectPackTable>();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		while(rs.next()) {
    			projectPackTable  pt= new projectPackTable();
    			pt.setTotalProduced(rs.getString(1)); 
				pt.setShamu(rs.getString(2));
				pt.setSongmu(rs.getString(3)); 
				pt.setZamu(rs.getString(4));
    			ptp.add(pt);
    		}
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return ptp;
	}

	@Override
	public int deletCutProjec(cutnum cp, double cutnumid) {
		int res=0;
    	try {
    		String sql="update cutnum set proj_package_Name=? WHERE cutnumid='"+cutnumid+"'";
    		res=dbc.doUpdate(sql, new Object[] {cp.getProjectPackageName()});
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}



}
