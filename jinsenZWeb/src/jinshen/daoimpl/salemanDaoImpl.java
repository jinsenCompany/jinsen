package jinshen.daoimpl;
//销售部门录入木材价格
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jinshen.bean.Laowu;
import jinshen.bean.contractProgress;
import jinshen.bean.customer;
import jinshen.bean.cutnum;
import jinshen.bean.cutnumStatus;
import jinshen.bean.projectpackage;
import jinshen.bean.saleCalloutOrder;
import jinshen.bean.salecontract;
import jinshen.bean.salemansql;
import jinshen.bean.salesman;
import jinshen.bean.singleworkid;
import jinshen.bean.tree;
import jinshen.bean.treeoutSalary;
import jinshen.bean.worktree;
import jinshen.bean.yardManage;
import jinshen.dao.salemanDao;
import jinshen.db.DBcon;

public class salemanDaoImpl implements salemanDao{
	private DBcon dbc=new DBcon();
	Connection connection = null;
    PreparedStatement pStatement = null;
	@Override
	public int findMaxid(String sql) {
		int num=0;
		try {
			ResultSet rs=dbc.doQuery(sql,new Object[]{});
			if(rs.next()) {
				num=rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	@Override
	public List<salesman> findSaleList(String sql){
		List<salesman> salesman=new ArrayList<salesman>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				salesman addr = new salesman();
				addr.setProvider(rs.getString(1));
				addr.setDemander(rs.getString(2));
				addr.setContractnum(rs.getString(3));
				addr.setSaleCalloutOrder(rs.getString(4));
				addr.setYard(rs.getString(5));
				addr.setYarddate(rs.getDate(6));
				addr.setCarNumber(rs.getString(7));
				addr.setSurveyor(rs.getString(8));
				salesman.add(addr);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbc.close();
		}
		return salesman;
	}
	
	@Override
	public List<worktree> findworktree(String sql)
	{
		List<worktree> work=new ArrayList<worktree>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()){
				worktree w=new worktree();
				w.setType(rs.getString(1));
				w.setTlong(rs.getString(2));
				w.setTradius(rs.getString(3));
				w.setTvolume(rs.getDouble(4));
				w.setUnitprice(rs.getDouble(5));
				w.setPrice(rs.getDouble(6));
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
	public List<worktree> findworktreeSalary(String sql)
	{
		List<worktree> work=new ArrayList<worktree>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()){
				worktree w=new worktree();
				w.setType(rs.getString(1));
				w.setTlong(rs.getString(2));
				w.setTradius(rs.getString(3));
				w.setNum(rs.getString(4));
				w.setTvolume(rs.getDouble(5));
				w.setUnitprice(rs.getDouble(6));
				w.setPrice(rs.getDouble(7));
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
	public List<worktree> findworktreeSum(String sql)
	{
		List<worktree> work=new ArrayList<worktree>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()){
				worktree w=new worktree();
				w.setType(rs.getString(1));
				w.setTvolume(rs.getDouble(2));
				w.setUnitprice(rs.getDouble(3));
				w.setPrice(rs.getDouble(4));
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
	public salesman findsaleSingle(String sql) {
		salesman addr = new salesman();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			if(rs.next()) {
				//addr.setWorkid(rs.getDouble(1));
				addr.setProvider(rs.getString(1));
				addr.setDemander(rs.getString(2));
				addr.setContractnum(rs.getString(3));
				addr.setSaleCalloutOrder(rs.getString(4));
				addr.setYard(rs.getString(5));
				addr.setYarddate(rs.getDate(6));
				addr.setCarNumber(rs.getString(7));
				addr.setSurveyor(rs.getString(8));
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbc.close();
		}
		return addr;
	}
	
	@Override
	public int update(String sql)
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
	
	//插入数据到数据，保存数据
	@Override
	public int addWork(salemansql cp) {
		String sql="insert into saleman values(?,?,?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getWorkid(),cp.getTreetype(),cp.getUnitprice(),cp.getPrice(),cp.getSaleman()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}
	
	//保存生业业主信息到数据库中
	@Override
	public int addCustomer(customer cp) {
		String sql="insert into customer values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getCutstomerid(),cp.getCreatdate(),cp.getCustomernum(),cp.getKname(),cp.getCompany(),cp.getAddress(),cp.getIDCard(),cp.getTelephone(),
					cp.getCustomertype(),cp.getContractnum(),cp.getTreenumber(),cp.getTotaltree(),cp.getShipaddress(),cp.getBreakcontract()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}
	
	//显示生产业主信息
	@Override
	public List<customer> findCustomer(String sql){
		List<customer> cust=new ArrayList<customer>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()){
				customer c=new customer();
				c.setCutstomerid(rs.getInt(1));
				c.setCreatdate(rs.getTimestamp(2));
				c.setCustomernum(rs.getString(3));
				c.setKname(rs.getString(4));
				c.setCompany(rs.getString(5));
				c.setAddress(rs.getString(6));
				c.setIDCard(rs.getString(7));
				c.setTelephone(rs.getString(8));
				c.setCustomertype(rs.getString(9));
				c.setContractnum(rs.getString(10));
				c.setTreenumber(rs.getString(11));
				c.setTotaltree(rs.getString(12));
				c.setShipaddress(rs.getString(13));
				c.setBreakcontract(rs.getString(14));
				cust.add(c);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbc.close();
			}
			return cust;
	}
	//将工单结算信息插入到laowu表里
	@Override
	public int addProduce(Laowu cp) {
		String sql="insert into Laowu values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getCutnum(),cp.getProjectPackageName(),cp.getForperson(),cp.getManageUnit(),cp.getTreetype(),cp.getTlong(),cp.getTradius(),cp.getTvolume(),cp.getUnitprice(),cp.getprice(),cp.getPerson(),cp.getttvolume(),cp.getTprice(),cp.getCreatTime(),
					cp.getTimeStart(),cp.getTimeEnd(),cp.getTworkid(),cp.getYard(),cp.getSection()});
					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						dbc.close();
					}
					return res;
	}
	
	//在工资结算单页面根据采伐证号显示工单
	@Override
	public List<singleworkid> findworkid(String sql)
	{
		List<singleworkid> sw=new ArrayList<singleworkid>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()){
				singleworkid addr=new singleworkid();
			    addr.setWorkid(rs.getDouble(1));
			    sw.add(addr);
			}
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}finally {
				dbc.close();
			}
			return sw;
	}
	//在木材销售货款结算页面显示货场信息
	@Override
	public List<singleworkid> findworkidYard(String sql)
	{
		List<singleworkid> sw=new ArrayList<singleworkid>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()){
				singleworkid addr=new singleworkid();
			    addr.setWorkid(rs.getDouble(1));
			    addr.setYard(rs.getString(2));
			    sw.add(addr);
			}
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}finally {
				dbc.close();
			}
			return sw;
	}

	@Override
	public int addContract(salecontract cp) {
		String sql="insert into sale_contract values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getContractid(),cp.getContractnum(),cp.getProvider(),cp.getDemander(),cp.getMarknumber(),cp.getContractsigner(),cp.getDeliveryplace(),cp.getSection(),cp.getTreenumber(),cp.getCost(),cp.getCostB(),cp.getMeasurestard(),
					cp.getSettlemethod(),cp.getStarttime(),cp.getEndtime(),cp.getContractfile(),cp.getMargin(),cp.getCreatTime(),cp.getContractstatus(),cp.getCutnum()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public int addContractTree(salecontract cp) {
		String sql="insert into sale_contract_tree values(?,?,?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getContractid(),cp.getTreetype(),cp.getTlong(),cp.getTradius(),cp.getUnitprice()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}
	
	@Override
	public salecontract findsalecontractid(String sql) {
		salecontract  cf= new salecontract();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		if(rs.next()) {
    			cf.setContractid(rs.getInt(1));
    		}
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return cf;
	}

	@Override
	public int addCalloutOrder(saleCalloutOrder cp) {
		String sql="insert into sale_callout_orderid values(?,?,?,?,?,?,?,?,?,?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getSaleCallid(),cp.getSaleCalloutOrder(),cp.getContractnum(),cp.getYard(),cp.getSection(),cp.getDemander(),cp.getPaymentamount(),
					cp.getTotalnum(),cp.getSigner(),cp.getCallidtime(),cp.getCreattime(),cp.getSalestatus()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public int addCalloutOrderTree(saleCalloutOrder cp) {
		String sql="insert into sale_callout_orderid_tree values(?,?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getSaleCallid(),cp.getTreetype(),cp.getTlong(),cp.getTradius()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}
	
	@Override
	public int addCalloutOrderTree1(saleCalloutOrder cp) {
		String sql="replace into sale_callout_orderid_tree values(?,?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getSaleCallid(),cp.getTreetype(),cp.getTlong(),cp.getTradius()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public saleCalloutOrder findsaleorderid(String sql) {
		saleCalloutOrder  cf= new saleCalloutOrder();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		if(rs.next()) {
    			cf.setSaleCallid(rs.getInt(1));
    		}
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return cf;
	}

	@Override
	public List<saleCalloutOrder> findsaleCallid(String sql) {
			List<saleCalloutOrder> sa=new ArrayList<saleCalloutOrder>();
			try {
				ResultSet rs=dbc.doQuery(sql, new Object[] {});
				while(rs.next()){
					saleCalloutOrder co = new saleCalloutOrder();
					co.setSaleCallid(rs.getInt(1));
					co.setCallidtime(rs.getTimestamp(2));
					co.setContractnum(rs.getString(3));
	    			co.setSaleCalloutOrder(rs.getString(4));
					co.setYard(rs.getString(5));
					co.setSection(rs.getString(6));
	    			co.setDemander(rs.getString(7));
	    			co.setPaymentamount(rs.getString(8));
	    			co.setTotalnum(rs.getString(9));
	    			co.setSigner(rs.getString(10));
					sa.add(co);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbc.close();
			}
			return sa;
		}

	@Override
	public List<saleCalloutOrder> findsaleCallpass(String sql) {
			List<saleCalloutOrder> sa=new ArrayList<saleCalloutOrder>();
			try {
				ResultSet rs=dbc.doQuery(sql, new Object[] {});
				while(rs.next()){
					saleCalloutOrder co = new saleCalloutOrder();
					co.setSaleCalloutOrder(rs.getString(1));
					co.setContractnum(rs.getString(2));
					co.setDemander(rs.getString(3));
					co.setYard(rs.getString(4));
					co.setTotalnum(rs.getString(5));
					co.setCallidtime(rs.getTimestamp(6));
					sa.add(co);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbc.close();
			}
			return sa;
		}
	
	@Override
	public List<salecontract> findsalecontractP(String sql) {
		List<salecontract> sm=new ArrayList<salecontract>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				salecontract ad= new salecontract();
				//addr.setWorkid(rs.getDouble(1));
				ad.setContractid(rs.getInt(1));
				ad.setContractnum(rs.getString(2));
				ad.setProvider(rs.getString(3));
				ad.setDemander(rs.getString(4));
				ad.setContractsigner(rs.getString(5));
				ad.setTreenumber(rs.getString(6));
				ad.setStarttime(rs.getDate(7));
				ad.setEndtime(rs.getDate(8));
				ad.setMargin(rs.getString(9));
				ad.setCreatTime(rs.getString(10));
				ad.setContractstatus(rs.getInt(11));
				sm.add(ad);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbc.close();
		}
		return sm;
	}

	@Override
	public saleCalloutOrder findsaleorder(String sql) {
		saleCalloutOrder  cf= new saleCalloutOrder();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		if(rs.next()) {
    			cf.setSaleCalloutOrder(rs.getString(1));
    			cf.setContractnum(rs.getString(2));
    			cf.setYard(rs.getString(3));
    			cf.setSection(rs.getString(4));
    			cf.setDemander(rs.getString(5));
    			cf.setPaymentamount(rs.getString(6));
    			cf.setTotalnum(rs.getString(7));
    			cf.setSigner(rs.getString(8));
    			cf.setCallidtime(rs.getTimestamp(9));//new Timestamp(new Date().getTime())
    			cf.setCreattime(rs.getTimestamp(10));
    		}
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return cf;
	}

	@Override
	public List<saleCalloutOrder> findsaletree(String sql) {
		List<saleCalloutOrder> addrList=new ArrayList<saleCalloutOrder>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				//saleOrdertree  cf= new saleOrdertree();
				saleCalloutOrder  cf= new saleCalloutOrder();
				cf.setTreetype(rs.getString(1));
    			cf.setTlong(rs.getString(2));
    			cf.setTradius(rs.getString(3));
    			//cf.setTnum(rs.getInt(4));
				addrList.add(cf);
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
	public int updateSaleCallorder(saleCalloutOrder cp, String sa) {
		int res=0;
		try {
			String sql="UPDATE sale_callout_orderid set sale_status=? WHERE sale_callout_order_id='"+sa+"'";
		    res=dbc.doUpdate(sql, new Object[] {cp.getSalestatus()});
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
	public int updateSaleCallOrderid(saleCalloutOrder cp,int id) {
		int res=0;
		try {
			String sql="update sale_callout_orderid SET sale_callout_order_id=?,contractnum=?,yard=?,section=?,demander=?,Paymentamount=?,totalnum=?,Signer=?,callidtime=?,sale_status=? WHERE sale_order_id='"+id+"'";
		    res=dbc.doUpdate(sql, new Object[] {cp.getSaleCalloutOrder(),cp.getContractnum(),cp.getYard(),cp.getSection(),cp.getDemander(),cp.getPaymentamount(),
					cp.getTotalnum(),cp.getSigner(),cp.getCallidtime(),cp.getSalestatus()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public int updateSaleCallOrderTree(saleCalloutOrder cp) {
		int res=0;
		try {
			String sql="update sale_callout_orderid_tree set treetype=?,tlong=?,tradius=? WHERE sale_order_id=?";
		    res=dbc.doUpdate(sql, new Object[] {cp.getTreetype(),cp.getTlong(),cp.getTradius(),cp.getSaleCallid()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public salecontract findcontract(String sql) {
		salecontract  cf= new salecontract();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		if(rs.next()) {
    			cf.setContractnum(rs.getString(1));
    			cf.setProvider(rs.getString(2));
    			cf.setDemander(rs.getString(3));
    			cf.setContractsigner(rs.getString(4));
    			cf.setDeliveryplace(rs.getString(5));
    			cf.setSection(rs.getString(6));
    			cf.setTreenumber(rs.getString(7));
    			cf.setCost(rs.getString(8));
    			cf.setCostB(rs.getString(9));
    			cf.setMeasurestard(rs.getString(10));
    			cf.setSettlemethod(rs.getString(11));
    			cf.setStarttime(rs.getDate(12));
    			cf.setEndtime(rs.getDate(13));
    			cf.setContractfile(rs.getString(14));
    			cf.setMargin(rs.getString(15));	
    			cf.setMarknumber(rs.getString(16));
    			cf.setCutnum(rs.getString(17));
    		}
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return cf;
	}

	@Override
	public List<salecontract> fincontractTree(String sql) {
		List<salecontract> addrList=new ArrayList<salecontract>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				//saleOrdertree  cf= new saleOrdertree();
				salecontract  cf= new salecontract();
				cf.setTreetype(rs.getString(1));
    			cf.setTlong(rs.getString(2));
    			cf.setTradius(rs.getString(3));
    			cf.setUnitprice(rs.getDouble(4));
				addrList.add(cf);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return addrList;
	}

	@Override
	public contractProgress findconProgress(String sql) {
		contractProgress  cf= new contractProgress();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		if(rs.next()) {
    			cf.setTotalStere(rs.getDouble(1));
    			cf.setTreenumber(rs.getDouble(2));
    		}
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return cf;
	}

	@Override
	public List<singleworkid> findProjectp(String sql) {
		List<singleworkid> sw=new ArrayList<singleworkid>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()){
				singleworkid addr=new singleworkid();
			    addr.setProjectPackageName(rs.getString(1));
			    addr.setForperson(rs.getString(2));
			    addr.setManageUnit(rs.getString(3));
			    sw.add(addr);
			}
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}finally {
				dbc.close();
			}
			return sw;
	}

	@Override
	public List<yardManage> findyardname(String sql) {
		List<yardManage> yd=new ArrayList<yardManage>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				yardManage addr = new yardManage();
				addr.setYardname(rs.getString(1));
				yd.add(addr);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbc.close();
		}
		return yd;
	}

	@Override
	public List<yardManage> findyardSec(String sql) {
		List<yardManage> yd=new ArrayList<yardManage>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				yardManage addr = new yardManage();
				addr.setSection(rs.getString(1));
				yd.add(addr);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbc.close();
		}
		return yd;
	}

	@Override
	public int addCalloutSurveyor(saleCalloutOrder cp) {
		String sql="insert into sale_callout_orderid_surveyor values(?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getSaleCallid(),cp.getSurveyor()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public salecontract findcontractPrivode(String sql) {
		salecontract addr = new salecontract();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			if(rs.next()) {
				//addr.setWorkid(rs.getDouble(1));
				addr.setProvider(rs.getString(1));
				addr.setMarknumber(rs.getString(2));
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbc.close();
		}
		return addr;
	}

	@Override
	public customer findcutnumAddres(String sql) {
		customer addr = new customer();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			if(rs.next()) {
				addr.setCompany(rs.getString(1));
				addr.setShipaddress(rs.getString(2));
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbc.close();
		}
		return addr;
	}

	@Override
	public List<cutnum> findcutnumPackid(String sql) {
		List<cutnum> ct=new ArrayList<cutnum>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				cutnum a = new cutnum();
				a.setCompany(rs.getString(1));
				a.setProjectPackageName(rs.getString(2));
				ct.add(a);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbc.close();
		}
		return ct;
	}

	@Override
	public List<projectpackage> findContractionSide(String sql) {
		List<projectpackage> p=new ArrayList<projectpackage>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				projectpackage a = new projectpackage();
				a.setContractionSide(rs.getString(1));
				p.add(a);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbc.close();
		}
		return p;
	}

	@Override
	public List<cutnum> findcutnumPackidLin(String sql) {
		List<cutnum> ct=new ArrayList<cutnum>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				cutnum a = new cutnum();
				a.setCompany(rs.getString(1));
				a.setProjectPackageName(rs.getString(2));
				a.setCutaddress(rs.getString(3));
				a.setCutvillage(rs.getString(4));
				a.setQuartel(rs.getString(5));
				a.setLargeblock(rs.getString(6));
				a.setSmallblock(rs.getString(7));
				ct.add(a);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbc.close();
		}
		return ct;
	}

	@Override
	public int addProduceLaowu(Laowu cp) {
		String sql="insert into Laowu_price values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getCutnum(),cp.getProjectPackageName(),cp.getForperson(),cp.getManageUnit(),cp.getFreightNum(),cp.getUnitfreight(),cp.getFreight(),
					cp.getRemarks(),cp.getToprice(),cp.getPremiumNum(),cp.getUnitpremium(),cp.getPremium(),cp.getTaxationNum(),cp.getTaxation(),cp.getTotalAll(),
					cp.getPerson(),cp.getCreatTime(),cp.getTreetype(),cp.getTvolume(),cp.getUnitprice(),cp.getprice(),cp.getTimeStart(),cp.getTimeEnd(),cp.getYard(),cp.getSection()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}
	
	@Override
	public int addProduceTreesalary(Laowu cp) {
		String sql="insert into produce_tree_salary values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getCutnum(),cp.getConsignee(),cp.getCompany(),cp.getYard(),cp.getContractnum(),cp.getSaleCalloutOrder(),cp.getTnum(),cp.getttvolume(),cp.getTprice(),
					cp.getTworkid(),cp.getPerson(),cp.getTreetype(),cp.getTlong(),cp.getTradius(),cp.getNum(),cp.getTvolume(),cp.getUnitprice(),cp.getprice(),cp.getCreatTime(),
					cp.getTimeStart(),cp.getTimeEnd(),cp.getSection()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public List<Laowu> printTreeSalary(String sql) {
		List<Laowu> l=new ArrayList<Laowu>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				Laowu a = new Laowu();
				a.setCreatTime(rs.getTimestamp(1));
				a.setProjectPackageName(rs.getString(2));
				a.setCutnum(rs.getString(3));
				a.setForperson(rs.getString(4));
				a.setCompany(rs.getString(5));
				a.setYard(rs.getString(6));
				a.setTnum(rs.getString(7));
				a.setTprice(rs.getDouble(8));
				a.setTvolume(rs.getString(9));
				a.setTimeStart(rs.getDate(10));
				a.setTimeEnd(rs.getDate(11));
				a.setSection(rs.getString(12));
//				a.setTlong(rs.getString(7));
//				a.setTradius(rs.getString(8));
//				a.setNum(rs.getString(9));
//				a.setTvolume(rs.getString(10));
//				a.setUnitprice(rs.getDouble(11));
//				a.setprice(rs.getDouble(12));
				
				l.add(a);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbc.close();
		}
		return l;
	}

	@Override
	public List<cutnum> findById(String sql) {
		List<cutnum> l=new ArrayList<cutnum>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				cutnum a = new cutnum();
				a.setCutnumid(rs.getInt(1));
				a.setCompany(rs.getString(2));
				a.setCertificatenum(rs.getString(3));
				a.setForestid(rs.getString(4));
				a.setCutnum(rs.getString(5));
				a.setCutaddress(rs.getString(6));
				a.setCutvillage(rs.getString(7));
				a.setQuartel(rs.getString(8));
				a.setLargeblock(rs.getString(9));
				a.setSmallblock(rs.getString(10));
				a.setCuttype(rs.getString(11));
				a.setCutmethod(rs.getString(12));
				a.setCutqiang(rs.getString(13));
				a.setCutarea(rs.getString(14));
				a.setStarttime(rs.getDate(15));
				a.setEndtime(rs.getDate(16));
				l.add(a);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbc.close();
		}
		return l;
	}

	@Override
	public List<salecontract> fincontractnum(String sql) {
		List<salecontract> sm=new ArrayList<salecontract>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				salecontract ad= new salecontract();
				ad.setTreetype(rs.getString(1));
				ad.setTlong(rs.getString(2));
				ad.setTradius(rs.getString(3));
				ad.setDemander(rs.getString(4));
				sm.add(ad);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbc.close();
		}
		return sm;
	}

	@Override
	public List<treeoutSalary> findtreesalaOrder(String sql) {
		List<treeoutSalary> sm=new ArrayList<treeoutSalary>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				treeoutSalary ad= new treeoutSalary();
				ad.setSaleCalloutOrder(rs.getString(1));
				ad.setContractnum(rs.getString(2));
				ad.setDemander(rs.getString(3));
				ad.setProvider(rs.getString(4));
				ad.setYard(rs.getString(5));
				ad.setSection(rs.getString(6));
				ad.setCutnum(rs.getString(7));
				sm.add(ad);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbc.close();
		}
		return sm;
	}
	@Override
	public int addProduceTreeoutsalary(Laowu cp) {
		String sql="insert into produce_treeout_salary values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getSaleCalloutOrder(),cp.getContractnum(),cp.getCutnum(),cp.getConsignee(),cp.getCompany(),cp.getYard(),cp.getTnum(),cp.getttvolume(),cp.getTprice(),
					cp.getTworkid(),cp.getPerson(),cp.getTreetype(),cp.getTlong(),cp.getTradius(),cp.getNum(),cp.getTvolume(),cp.getUnitprice(),cp.getprice(),cp.getCreatTime(),});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public List<Laowu> findtreeoutPridce(String sql) {
		List<Laowu> l=new ArrayList<Laowu>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				Laowu a = new Laowu();
				a.setContractnum(rs.getString(1));
				a.setSaleCalloutOrder(rs.getString(2));
				a.setConsignee(rs.getString(3));
				a.setCompany(rs.getString(4));
				a.setYard(rs.getString(5));
				a.setTnum(rs.getString(6));
				a.setTvolume(rs.getString(7));
				a.setTprice(rs.getDouble(8));
				a.setCreatTime(rs.getTimestamp(9));
				l.add(a);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbc.close();
		}
		return l;
	}

	@Override
	public List<Laowu> findtreeoutPridceDet(String sql) {
		List<Laowu> l=new ArrayList<Laowu>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				Laowu a = new Laowu();
				a.setSaleCalloutOrder(rs.getString(1));
				a.setContractnum(rs.getString(2));
				a.setCutnum(rs.getString(3));
				a.setConsignee(rs.getString(4));
				a.setCompany(rs.getString(5));
				a.setYard(rs.getString(6));
				a.setTnum(rs.getString(7));
				a.setttvolume(rs.getDouble(8));
				a.setTprice(rs.getDouble(9));
				a.setTworkid(rs.getString(10));
				a.setPerson(rs.getString(11));
				a.setTreetype(rs.getString(12));
				a.setTlong(rs.getString(13));
				a.setTradius(rs.getString(14));
				a.setNum(rs.getString(15));
				a.setTvolume(rs.getString(16));
				a.setUnitprice(rs.getDouble(17));
				a.setprice(rs.getDouble(18));
				a.setCreatTime(rs.getTimestamp(19));
				l.add(a);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbc.close();
		}
		return l;
	}

	@Override
	public List<Laowu> findProducePDet(String sql) {
		List<Laowu> l=new ArrayList<Laowu>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				Laowu a = new Laowu();
				a.setProjectPackageName(rs.getString(1));
				a.setCutnum(rs.getString(2));
				a.setConsignee(rs.getString(3));
				a.setCompany(rs.getString(4));
				a.setYard(rs.getString(5));
				a.setContractnum(rs.getString(6));
				a.setSaleCalloutOrder(rs.getString(7));
				a.setTnum(rs.getString(8));
				a.setttvolume(rs.getDouble(9));
				a.setTprice(rs.getDouble(10));
				a.setTworkid(rs.getString(11));
				a.setPerson(rs.getString(12));
				a.setTreetype(rs.getString(13));
				a.setTlong(rs.getString(14));
				a.setTradius(rs.getString(15));
				a.setNum(rs.getString(16));
				a.setTvolume(rs.getString(17));
				a.setUnitprice(rs.getDouble(18));
				a.setprice(rs.getDouble(19));
				a.setCreatTime(rs.getTimestamp(20));
				l.add(a);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbc.close();
		}
		return l;
	}

	@Override
	public List<Laowu> printProductpriceT(String sql) {
		List<Laowu> l=new ArrayList<Laowu>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				Laowu a = new Laowu();
				a.setCutnum(rs.getString(1));
				a.setProjectPackageName(rs.getString(2));
				a.setForperson(rs.getString(3));
				a.setManageUnit(rs.getString(4));
				a.setTotalAll(rs.getString(5));
				a.setTimeStart(rs.getDate(6));
				a.setTimeEnd(rs.getDate(7));
				a.setCreatTime(rs.getTimestamp(8));
				a.setYard(rs.getString(9));
				a.setSection(rs.getString(10));
				l.add(a);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbc.close();
		}
		return l;
	}

	@Override
	public List<Laowu> seeProductpriceT(String sql) {
		List<Laowu> l=new ArrayList<Laowu>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				Laowu cp = new Laowu();
				cp.setCutnum(rs.getString(1));
				cp.setProjectPackageName(rs.getString(2));
				cp.setForperson(rs.getString(3));
				cp.setManageUnit(rs.getString(4));
				cp.setFreightNum(rs.getString(5));
				cp.setUnitfreight(rs.getString(6));
				cp.setFreight(rs.getString(7));
				cp.setRemarks(rs.getString(8));
				cp.setToprice(rs.getString(9));
				cp.setPremiumNum(rs.getString(10));
				cp.setUnitpremium(rs.getString(11));
				cp.setPremium(rs.getString(12));
				cp.setTaxationNum(rs.getString(13));
				cp.setTaxation(rs.getString(14));
				cp.setTotalAll(rs.getString(15));
				cp.setPerson(rs.getString(16));
				cp.setCreatTime(rs.getTimestamp(17));
				cp.setTreetype(rs.getString(18));
				cp.setTvolume(rs.getString(19));
				cp.setUnitprice(rs.getDouble(20));
				cp.setprice(rs.getDouble(21));
				cp.setTimeStart(rs.getDate(22));
				cp.setTimeEnd(rs.getDate(22));
				cp.setYard(rs.getString(23));
				cp.setSection(rs.getString(24));
				l.add(cp);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbc.close();
		}
		return l;
	}

	@Override
	public List<Laowu> printProduct2priceT(String sql) {
		List<Laowu> l=new ArrayList<Laowu>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				Laowu a = new Laowu();
				a.setCutnum(rs.getString(1));
				a.setProjectPackageName(rs.getString(2));
				a.setForperson(rs.getString(3));
				a.setManageUnit(rs.getString(4));
				a.setttvolume(rs.getDouble(5));
				a.setTprice(rs.getDouble(6));
				a.setTimeStart(rs.getDate(7));
				a.setTimeEnd(rs.getDate(8));
				a.setCreatTime(rs.getTimestamp(9));
				a.setYard(rs.getString(10));
				a.setSection(rs.getString(11));
				l.add(a);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbc.close();
		}
		return l;
	}

	@Override
	public int addProduceRad(Laowu cp) {
		String sql="insert into Laowu_class values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getCutnum(),cp.getProjectPackageName(),cp.getForperson(),cp.getManageUnit(),cp.getTreetype(),cp.getTlong(),cp.getTradius(),cp.getTvolume(),cp.getUnitprice(),cp.getprice(),cp.getPerson(),cp.getttvolume(),cp.getTprice(),cp.getCreatTime(),
					cp.getTimeStart(),cp.getTimeEnd(),cp.getTworkid(),cp.getYard(),cp.getSection()});
					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						dbc.close();
					}
					return res;
	}

	@Override
	public List<Laowu> seeProduct23priceT(String sql) {
		List<Laowu> l=new ArrayList<Laowu>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				Laowu cp = new Laowu();
				cp.setCutnum(rs.getString(1));
				cp.setProjectPackageName(rs.getString(2));
				cp.setForperson(rs.getString(3));
				cp.setManageUnit(rs.getString(4));
				cp.setTreetype(rs.getString(5));
				cp.setTlong(rs.getString(6));
				cp.setTradius(rs.getString(7));
				cp.setTvolume(rs.getString(8));
				cp.setUnitprice(rs.getDouble(9));
				cp.setprice(rs.getDouble(10));
				cp.setPerson(rs.getString(11));
				cp.setttvolume(rs.getDouble(12));
				cp.setTprice(rs.getDouble(13));
				cp.setCreatTime(rs.getTimestamp(14));
				cp.setTimeStart(rs.getDate(15));
				cp.setTimeEnd(rs.getDate(16));
				cp.setTworkid(rs.getString(17));
				cp.setYard(rs.getString(18));
				cp.setSection(rs.getString(19));
				l.add(cp);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbc.close();
		}
		return l;
	}

	@Override
	public salecontract findUseTreenum(String sql) {
		salecontract  cf= new salecontract();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		if(rs.next()) {
    			cf.setUseTreenum(rs.getString(1));
    		}
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return cf;
	}

	@Override
	public int updateContractStatus(salecontract ac, int status) {
		int res=0;
		try {
			String sql="UPDATE sale_contract SET contract_status=? WHERE sale_contract_id='"+status+"' ";
		    res=dbc.doUpdate(sql, new Object[] {ac.getContractstatus()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public List<salecontract> findMeasurestard(String sql) {
		List<salecontract> sa=new ArrayList<salecontract>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()){
				salecontract co = new salecontract();
				co.setMeasurestard(rs.getString(1));
				sa.add(co);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return sa;
	}

	@Override
	public List<saleCalloutOrder> findsaleCallout(String sql) {
		List<saleCalloutOrder> sa=new ArrayList<saleCalloutOrder>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()){
				saleCalloutOrder co = new saleCalloutOrder();
				co.setSaleCallid(rs.getInt(1));
				co.setCallidtime(rs.getTimestamp(2));
				co.setContractnum(rs.getString(3));
    			co.setSaleCalloutOrder(rs.getString(4));
				co.setYard(rs.getString(5));
				co.setSection(rs.getString(6));
    			co.setDemander(rs.getString(7));
    			co.setPaymentamount(rs.getString(8));
    			co.setTotalnum(rs.getString(9));
    			co.setSigner(rs.getString(10));
    			co.setSalestatus(rs.getInt(11));
				sa.add(co);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return sa;
	}

	@Override
	public saleCalloutOrder findsaleSurveyor(String sql) {
		saleCalloutOrder  cf= new saleCalloutOrder();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		if(rs.next()) {
    			cf.setSurveyor(rs.getString(1));
    		}
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return cf;
	}

	@Override
	public saleCalloutOrder findsaleorderCutnum(String sql) {
		// TODO Auto-generated method stub
		saleCalloutOrder  cf= new saleCalloutOrder();
    	try {
    		ResultSet rs=dbc.doQuery(sql, new Object[] {});
    		if(rs.next()) {
    			cf.setSaleCalloutOrder(rs.getString(1));
    			cf.setContractnum(rs.getString(2));
    			cf.setYard(rs.getString(3));
    			cf.setSection(rs.getString(4));
    			cf.setDemander(rs.getString(5));
    			cf.setPaymentamount(rs.getString(6));
    			cf.setTotalnum(rs.getString(7));
    			cf.setSigner(rs.getString(8));
    			cf.setCallidtime(rs.getTimestamp(9));//new Timestamp(new Date().getTime())
    			cf.setCreattime(rs.getTimestamp(10));
    			cf.setCutnum(rs.getString(11));
    		}
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return cf;
	}

	@Override
	public customer findcutnumDet(String sql) {
		customer c=new customer();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			if(rs.next()){
				c.setCutstomerid(rs.getInt(1));
				c.setCreatdate(rs.getTimestamp(2));
				c.setCustomernum(rs.getString(3));
				c.setKname(rs.getString(4));
				c.setCompany(rs.getString(5));
				c.setAddress(rs.getString(6));
				c.setIDCard(rs.getString(7));
				c.setTelephone(rs.getString(8));
				c.setCustomertype(rs.getString(9));
				c.setContractnum(rs.getString(10));
				c.setTreenumber(rs.getString(11));
				c.setTotaltree(rs.getString(12));
				c.setShipaddress(rs.getString(13));
				c.setBreakcontract(rs.getString(14));
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbc.close();
			}
			return c;
	}

	@Override
	public customer findcutnumTreenum(String sql) {
		customer c=new customer();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			if(rs.next()){
				c.setContractnum(rs.getString(1));
				c.setTreenumber(rs.getString(2));
				c.setTotaltree(rs.getString(3));
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbc.close();
			}
			return c;
	}
	

}
