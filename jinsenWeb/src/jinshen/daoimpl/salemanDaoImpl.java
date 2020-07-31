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
import jinshen.bean.cutnumStatus;
import jinshen.bean.saleCalloutOrder;
import jinshen.bean.salecontract;
import jinshen.bean.salemansql;
import jinshen.bean.salesman;
import jinshen.bean.singleworkid;
import jinshen.bean.tree;
import jinshen.bean.worktree;
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
		String sql="insert into customer values(?,?,?,?,?,?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getCutstomerid(),cp.getKname(),cp.getContractnum(),cp.getCompany(),cp.getAddress(),cp.getIDCard(),cp.getTelephone(),cp.getShipaddress()});
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
				c.setKname(rs.getString(1));
				c.setContractnum(rs.getString(2));
				c.setCompany(rs.getString(3));
				c.setAddress(rs.getString(4));
				c.setIDCard(rs.getString(5));
				c.setTelephone(rs.getString(6));
				c.setShipaddress(rs.getString(7));
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
		String sql="insert into Laowu values(?,?,?,?,?,?,?,?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getCutnum(),cp.getProjectPackageid(),cp.getForperson(),cp.getManageUnit(),cp.getTreetype(),cp.getUnitprice(),cp.getprice(),cp.getPerson(),cp.getttvolume(),cp.gettprice()});
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

	@Override
	public int addContract(salecontract cp) {
		String sql="insert into sale_contract values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getContractid(),cp.getContractnum(),cp.getProvider(),cp.getDemander(),cp.getDeliveryplace(),cp.getTreenumber(),cp.getCost(),cp.getMeasurestard(),
					cp.getSettlemethod(),cp.getStarttime(),cp.getEndtime(),cp.getContractfile(),cp.getMargin()});
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
		String sql="insert into sale_callout_orderid values(?,?,?,?,?,?,?,?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getSaleCallid(),cp.getSaleCalloutOrder(),cp.getContractnum(),cp.getYard(),cp.getDemander(),cp.getPaymentnum(),cp.getPaymentamount(),
					cp.getTotalnum(),cp.getSigner(),cp.getSalestatus()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public int addCalloutOrderTree(saleCalloutOrder cp) {
		String sql="insert into sale_callout_orderid_tree values(?,?,?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getSaleCallid(),cp.getTreetype(),cp.getTlong(),cp.getTradius(),cp.getTnum()});
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
	    			co.setSaleCalloutOrder(rs.getString(2));
					co.setContractnum(rs.getString(3));
					co.setYard(rs.getString(4));
	    			co.setDemander(rs.getString(5));
	    			co.setPaymentnum(rs.getString(6));
	    			co.setPaymentamount(rs.getString(7));
	    			co.setTotalnum(rs.getInt(8));
	    			co.setSigner(rs.getString(9));
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
				ad.setTreenumber(rs.getInt(5));
				ad.setStarttime(rs.getDate(6));
				ad.setEndtime(rs.getDate(7));
				ad.setMargin(rs.getString(8));
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
    			cf.setDemander(rs.getString(4));
    			cf.setPaymentnum(rs.getString(5));
    			cf.setPaymentamount(rs.getString(6));
    			cf.setTotalnum(rs.getInt(7));
    			cf.setSigner(rs.getString(8));	
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
    			cf.setTnum(rs.getInt(4));
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
			String sql="update sale_callout_orderid SET sale_callout_order_id=?,contractnum=?,yard=?,demander=?,Paymentnum=?,Paymentamount=?,totalnum=?,Signer=?,sale_status=? WHERE sale_order_id='"+id+"'";
		    res=dbc.doUpdate(sql, new Object[] {cp.getSaleCalloutOrder(),cp.getContractnum(),cp.getYard(),cp.getDemander(),cp.getPaymentnum(),cp.getPaymentamount(),cp.getTotalnum(),cp.getSigner(),cp.getSalestatus()});
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
			String sql="update sale_callout_orderid_tree set treetype=?,tlong=?,tradius=?,tnum=? WHERE sale_order_id=?";
		    res=dbc.doUpdate(sql, new Object[] {cp.getTreetype(),cp.getTlong(),cp.getTradius(),cp.getTnum(),cp.getSaleCallid()});
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
    			cf.setDeliveryplace(rs.getString(4));
    			cf.setTreenumber(rs.getDouble(5));
    			cf.setCost(rs.getString(6));
    			cf.setMeasurestard(rs.getString(7));
    			cf.setSettlemethod(rs.getString(8));
    			cf.setStarttime(rs.getDate(9));
    			cf.setEndtime(rs.getDate(10));
    			cf.setContractfile(rs.getString(11));
    			cf.setMargin(rs.getString(12));	
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
			    addr.setProjectPackageid(rs.getDouble(1));
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
	

}
