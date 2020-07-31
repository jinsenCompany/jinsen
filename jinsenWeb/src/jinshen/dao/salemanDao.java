package jinshen.dao;
//销售部门，录入木材价格
import java.util.List;

import jinshen.bean.*;

public interface salemanDao {
	public int findMaxid(String sql);
	public List<salesman> findSaleList(String sql);///木材销售
	public List<worktree> findworktree(String sql);//发现木材类型，材积，单价等信息是对worktree对象的实现
	public salesman findsaleSingle(String sql);//读取outyard数据库中yard,yarddate,carNumber等数据
	public int update(String sql);//更新表
	public int addWork(salemansql cp);//将数据插入到saleman
	
	public List<customer> findCustomer(String sql);//显示发现生产业主
	public int addCustomer(customer cp);//添加生产业主到数据库
    
	public int addProduce(Laowu cp);//将劳务插入到laowu数据库中
	public List<singleworkid> findworkid(String sql);//显示工单信息
	public List<singleworkid> findProjectp(String sql);//在劳务结算页面显示工程包信息
	
	public int addContract(salecontract cp);//将合同信息保存到合同表里
	public int addContractTree(salecontract cp);//将合同信息保存到合同表里
	public salecontract findsalecontractid(String sql);//通过查询合同编号
	public List<salecontract> findsalecontractP(String sql);//通过搜索销售合同信息显示在销售合同进程页面
	public salecontract findcontract(String sql);//在页面显示合同详细信息
	public List<salecontract> fincontractTree(String sql);//合同中树的信息
	public contractProgress findconProgress(String sql);//合同进度
	
	public int addCalloutOrder(saleCalloutOrder cp);//将调令信息保存到数据库中
	public int addCalloutOrderTree(saleCalloutOrder cp);//将合同信息保存到合同表里
	public saleCalloutOrder findsaleorderid(String sql);//通过查询调令查找调令编号
	public saleCalloutOrder findsaleorder(String sql);//
	public List<saleCalloutOrder> findsaletree(String sql);//查找销售凋零中树的信息
	public int delWorkPage(String sql);//删除选中的内容
	public int updateSaleCallorder(saleCalloutOrder cp,String sa);//更新销售调令状态
	public double findcount(String sql);//计算与输入相同的调令个数
	public int updateSaleCallOrderid(saleCalloutOrder cp,int id);//更新销售调令
	public int updateSaleCallOrderTree(saleCalloutOrder cp);//更新销售调令木材信息
	
	
	public List<saleCalloutOrder> findsaleCallid(String sql);//显示搜索的调令
}
