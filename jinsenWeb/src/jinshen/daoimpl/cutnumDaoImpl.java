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
import jinshen.bean.cutnumProgress;
import jinshen.bean.cutnumStatus;
import jinshen.bean.cutnumWatch;
import jinshen.bean.cutnumfeedback;
import jinshen.bean.projectpackage;
import jinshen.dao.cutnumDao;
import jinshen.db.DBcon;

public class cutnumDaoImpl implements cutnumDao{
	private DBcon dbc=new DBcon();
    Connection connection = null;
    PreparedStatement pStatement = null;
    
    @Override
    public int addCutnum(cutnum cp) {
    	String sql="insert into cutnum values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getCutnumid(),cp.getCutnum(),cp.getCertificatenum(),cp.getNumber(),cp.getCompany(),cp.getcutsite(),cp.getSizhi(),cp.getGpsinfo(),cp.getTreeorigin(),cp.getforesttype(),cp.getTreetype(),cp.getOwnership(),cp.getForestid(),
					cp.getcuttype(),cp.getcutmethod(),cp.getcutqiang(),cp.getcutarea(),cp.gettreenum(),cp.getcutstore(),cp.getvolume(),cp.getStarttime(),cp.getEndtime(),cp.getCertifier(),cp.getUpdatedate(),cp.getUpdatevolume(),cp.getUpdatenum(),cp.getProjectid(),cp.getCutnumfile()});
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
    public int updateCutnum(cutnum cp) {
    	int res=0;
    	try {
    		String sql="update cutnum set cutnum=?,certificatenum=?,number=?,company=?,cutsite=?,sizhi=?,gpsinfo=?,treeorigin=?,foresttype=?,treetype=?,ownership=?,forestid=?,cuttype=?,cutmethod=?,cutqiang=?,cutarea=?,treenum=?,cutstore=?,volume=?,starttime=?,endtime=?,certifier=?,updatedate=?,updatevolume=?,updatenum=? where cutnum=?";
    		res=dbc.doUpdate(sql, new Object[] {cp.getCutnum(),cp.getCertificatenum(),cp.getNumber(),cp.getCompany(),cp.getcutsite(),cp.getSizhi(),cp.getGpsinfo(),cp.getTreeorigin(),cp.getforesttype(),cp.getTreetype(),cp.getOwnership(),cp.getForestid(),
					cp.getcuttype(),cp.getcutmethod(),cp.getcutqiang(),cp.getcutarea(),cp.gettreenum(),cp.getcutstore(),cp.getvolume(),cp.getStarttime(),cp.getEndtime(),cp.getCertifier(),cp.getUpdatedate(),cp.getUpdatevolume(),cp.getUpdatenum()});
    	}catch(Exception e) {
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
				cn.setCutnumid(rs.getDouble(1));
				cn.setCutnum(rs.getString(2));
				cn.setNumber(rs.getDouble(3));
				cn.setCompany(rs.getString(4));
				cn.setcutsite(rs.getString(5));
				cn.setSizhi(rs.getString(6));
				cn.setTreeorigin(rs.getString(7));
				cn.setforesttype(rs.getString(8));
				cn.setTreetype(rs.getString(9));
				cn.setOwnership(rs.getString(10));
				cn.setForestid(rs.getString(11));
				cn.setcuttype(rs.getString(12));
				cn.setcutmethod(rs.getString(13));
				cn.setcutqiang(rs.getString(14));
				cn.setcutarea(rs.getDouble(15));
				cn.settreenum(rs.getDouble(16));
				cn.setcutstore(rs.getDouble(17));
				cn.setvolume(rs.getDouble(18));
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
    			cn.setCutnum(rs.getString(1));
    			cn.setCertificatenum(rs.getString(2));
				cn.setNumber(rs.getDouble(3));
				cn.setCompany(rs.getString(4));
				cn.setcutsite(rs.getString(5));
				cn.setSizhi(rs.getString(6));
				cn.setGpsinfo(rs.getString(7));
				cn.setTreeorigin(rs.getString(8));
				cn.setforesttype(rs.getString(9));
				cn.setTreetype(rs.getString(10));
				cn.setOwnership(rs.getString(11));
				cn.setForestid(rs.getString(12));
				cn.setcuttype(rs.getString(13));
				cn.setcutmethod(rs.getString(14));
				cn.setcutqiang(rs.getString(15));
				cn.setcutarea(rs.getDouble(16));
				cn.settreenum(rs.getDouble(17));
				cn.setcutstore(rs.getDouble(18));
				cn.setvolume(rs.getDouble(19));
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
    }
    
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
    			cn.setCutnumid(rs.getDouble(1));
				cn.setCutnum(rs.getString(2));
				cn.setNumber(rs.getDouble(3));
				cn.setCompany(rs.getString(4));
				cn.setcutsite(rs.getString(5));
				cn.setSizhi(rs.getString(6));
				cn.setTreeorigin(rs.getString(7));
				cn.setforesttype(rs.getString(8));
				cn.setTreetype(rs.getString(9));
				cn.setOwnership(rs.getString(10));
				cn.setForestid(rs.getString(11));
				cn.setcuttype(rs.getString(12));
				cn.setcutmethod(rs.getString(13));
				cn.setcutqiang(rs.getString(14));
				cn.setcutarea(rs.getDouble(15));
				cn.settreenum(rs.getDouble(16));
				cn.setcutstore(rs.getDouble(17));
				cn.setvolume(rs.getDouble(18));
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
    			//cn.setCutnumid(rs.getDouble(1));
    			cn.setStarttime(rs.getDate(1));
				cn.setEndtime(rs.getDate(2));
				cn.setCutnum(rs.getString(3));
				cn.setCertificatenum(rs.getString(4));
				cn.setNumber(rs.getDouble(5));
				cn.setCompany(rs.getString(6));
				cn.setcutsite(rs.getString(7));
				cn.setSizhi(rs.getString(8));
				cn.setGpsinfo(rs.getString(9));
				cn.setTreeorigin(rs.getString(10));
				cn.setforesttype(rs.getString(11));
				cn.setTreetype(rs.getString(12));
				cn.setOwnership(rs.getString(13));
				cn.setForestid(rs.getString(14));
				cn.setcuttype(rs.getString(15));
				cn.setcutmethod(rs.getString(16));
				cn.setcutqiang(rs.getString(17));
				cn.setcutarea(rs.getDouble(18));
				cn.settreenum(rs.getDouble(19));
				cn.setcutstore(rs.getDouble(20));
				cn.setvolume(rs.getDouble(21));
				cn.setCertifier(rs.getString(22));
				cn.setUpdatedate(rs.getDate(23));
				cn.setUpdatevolume(rs.getDouble(24));
				cn.setUpdatenum(rs.getDouble(25));
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
    			cn.setNumber(rs.getDouble(2));
    			cn.setStarttime(rs.getDate(3));
    			cn.setEndtime(rs.getDate(4));
				cn.setCompany(rs.getString(5));
				cn.setcutsite(rs.getString(6));
				cn.setcutstore(rs.getDouble(7));
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
    	String sql="update cutnum set proj_package_id=? WHERE cutnum like '%"+each+"%'";
    	int res = 0;
    	try {
    		res=dbc.doUpdate(sql, new Object[] {cn.getProjectid()});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	dbc.close();
        }
    	return res;
    }
    
    @Override
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
    }
    
    
    @Override
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
    }
    
    @Override
    public projectpackage findprojectpackage(String sql) {
    	projectpackage  pt= new projectpackage();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		if(rs.next()) {
    			pt.setprojectPackageid(rs.getDouble(1));
    			pt.setprojectPackagename(rs.getString(2));
    			pt.setmanagerPhone(rs.getString(3));
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
    			pt.setprojectPackageid(rs.getDouble(1));
    			pt.setprojectPackagename(rs.getString(2));
    			pt.setmanagerPhone(rs.getString(3));
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
			res=dbc.doUpdate(sql, new Object[] {cp.getapplyid(),cp.getDesignum(),cp.getCutaddress(),cp.getCutaddress(),cp.getCutvillage(),cp.getQuartel(),cp.getLargeblock(),cp.getSmallblock(),cp.getSmallblackarea(),cp.getOrigin(),
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
    		String sql="update cutnum_application set designum=?,cut_reson=?,cut_adress=?,cut_village=?,quartel=?,larg_block=?,small_block=?,small_block_area=?,origin=?,forest_type=?,type_consist=?,mange_type=?,forest_age=?,cut_area=?,cut_type=?,cut_way=?,"
    				+ "cut_strength=?,tree_type=?,cut_volume=?,cut_num=?,total=?,cut_intermediate=?,total2=?,size_wood=?,small_timber=?,short_timber=?,firewood=?,cutpath=?,applay_date=? WHERE apply_id="+apid+" ";
    		res=dbc.doUpdate(sql, new Object[] {cp.getDesignum(),cp.getCutaddress(),cp.getCutaddress(),cp.getCutvillage(),cp.getQuartel(),cp.getLargeblock(),cp.getSmallblock(),cp.getSmallblackarea(),cp.getOrigin(),
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
    			ca.setSmallblackarea(rs.getDouble(8));
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
    			cf.setCutnumid(rs.getDouble(1));
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
    			cn.setCutnumid(rs.getDouble(1));
				cn.setCutnum(rs.getString(2));
				cn.setCertificatenum(rs.getString(3));
				cn.setNumber(rs.getDouble(4));
				cn.setCompany(rs.getString(5));
				cn.setcutsite(rs.getString(6));
				cn.setSizhi(rs.getString(7));
				cn.setGpsinfo(rs.getString(8));
				cn.setTreeorigin(rs.getString(9));
				cn.setforesttype(rs.getString(10));
				cn.setTreetype(rs.getString(11));
				cn.setOwnership(rs.getString(12));
				cn.setForestid(rs.getString(13));
				cn.setcuttype(rs.getString(14));
				cn.setcutmethod(rs.getString(15));
				cn.setcutqiang(rs.getString(16));
				cn.setcutarea(rs.getDouble(17));
				cn.settreenum(rs.getDouble(18));
				cn.setcutstore(rs.getDouble(19));
				cn.setvolume(rs.getDouble(20));
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
    			cn.setProjectPackageid(rs.getDouble(2));
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
    			cn.setCutnum(rs.getString(1));
    			cn.setStarttime(rs.getDate(2));
    			cn.setEndtime(rs.getDate(3));
				cn.setcutsite(rs.getString(4));
				cn.setStatus(rs.getInt(5));
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


}
