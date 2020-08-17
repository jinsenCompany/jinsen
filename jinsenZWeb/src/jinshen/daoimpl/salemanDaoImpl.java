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
		String sql="insert into sale_contract values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getContractid(),cp.getContractnum(),cp.getProvider(),cp.getDemander(),cp.getMarknumber(),cp.getDeliveryplace(),cp.getTreenumber(),cp.getCost(),cp.getMeasurestard(),
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
					co.setCallidtime(rs.getDate(2));
					co.setContractnum(rs.getString(3));
	    			co.setSaleCalloutOrder(rs.getString(4));
					co.setYard(rs.getString(5));
					co.setSection(rs.getString(6));
	    			co.setDemander(rs.getString(7));
	    			co.setPaymentamount(rs.getString(8));
	    			co.setTotalnum(rs.getInt(9));
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
					co.setTotalnum(rs.getInt(5));
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
				ad.setTreenumber(rs.getString(5));
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
    			cf.setSection(rs.getString(4));
    			cf.setDemander(rs.getString(5));
    			cf.setPaymentamount(rs.getString(6));
    			cf.setTotalnum(rs.getInt(7));
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
    			cf.setDeliveryplace(rs.getString(4));
    			cf.setTreenumber(rs.getString(5));
    			cf.setCost(rs.getString(6));
    			cf.setMeasurestard(rs.getString(7));
    			cf.setSettlemethod(rs.getString(8));
    			cf.setStarttime(rs.getDate(9));
    			cf.setEndtime(rs.getDate(10));
    			cf.setContractfile(rs.getString(11));
    			cf.setMargin(rs.getString(12));	
    			cf.setMarknumber(rs.getString(13));
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
	

}
