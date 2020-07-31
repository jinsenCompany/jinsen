package jinshen.dao;

import java.sql.Date;
import java.util.List;
import jinshen.bean.*;

public interface workpageDao {
	public int addWorkPage(workpage cp);//添加工单信息
	public int updateWorkPage(workpage cp);//更新工单信息
	public List<workpage> findWorkPage(String sql);//发现数据库工单信息内容
	public int delWorkPage(String sql);//删除工单信息
	public workpage findCodeSingle(String sql);//发现工单信息
	public int updateWork(String sql);//更新工单状况（考虑要不要用）
	public double findcount(String sql);//计算与输入工单相同的工单个数
	public int addCodeStatus(String sql);//添加工单状态（目前考虑要不要用）
	public List<codejson> findcodeJson(String sql);//发现工单个别信息
	public boolean delete_work(double workid);
	
	public int addWorkpageStatus(workpageStatus cp);//插入工单状况（最新）
	
	public int insertcompareTree(compareTree t);//插入照片
	public List<compareTree> findcomparet(String sql);//发现用于bootstrap显示
	public compareTree findcompares(String sql);//发现图片信息用于页面显示
	
	public int updateWorkpagestatus(double workid,workpageStatus ws);//信息中心第一次审核更新工单状态yes
	public int updateWorkpagestatusN(double workid,workpageStatus ws);//信息中心第一次审核更新工单状态not
	public workpageStatus findWapStatus(String sql);//发现砍伐工单状态表被退回原因信息
	public int updateWorkpagestatus1(double workid,workpageStatus ws);//伐区监管员根据信息中心反馈消息更新工单状态
	public int updateWorkPageFa(workpage cp,double workid);//伐区管理员更新工单信息
	
	public int addyardmanage(yardManage cp);//将货场信息保存到数据库中
    public List<yardManage> findyardmanage(String sql);//搜索数据库中的货场信息
    
    public int addmanagesdatecard(managesdatecard cp);//将木材生产施工方管理资料卡保存到数据库中
    public List<managesdatecard> findmanagesdatecard(String sql);//搜索数据库中的资料卡信息
    
    public List<workpage> findWorkpageC(String sql);//通过工单号查询工单信息
    public workpage printworkpage();
    
	public List<tree> findTree(String sql);
	public workpage findCodeSingle1(String sql);//木材收购单发现工单信息
}
