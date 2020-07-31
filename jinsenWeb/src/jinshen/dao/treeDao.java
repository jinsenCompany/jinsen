package jinshen.dao;

import java.util.List;

import jinshen.bean.compareVolume;
import jinshen.bean.inyard;
import jinshen.bean.inyardStatus;
import jinshen.bean.outyard;
import jinshen.bean.surveyorFee;
import jinshen.bean.tree;
import jinshen.bean.treebuy;
import jinshen.bean.workpage;
import jinshen.bean.yardInventory;

public interface treeDao {

	public List<tree> findTree(String sql);
	public int addTree(tree c);//
	public tree findTreeSingle(String sql);
	public int delById(String sql);
	public int updateTree(tree c);
	public int findMaxid();
	public int updateTree(String sql);
	//public List<cat> findByNameList(String sql);
	public int addTreeout(tree c);
	public int updateTreeout(tree c);
	public int updateTreeout(String sql);
	public int addInyard(inyard cp);
	public int updateInyard(inyard cp,double workid);
	public int addOutyard(outyard cp);
	public int updateOutyard(outyard cp);
	public double findcount(String sql);
	public inyard findInSingle(String sql);
	public outyard findOutSingle(String sql);
	public int updateWork(String sql);
	
	public compareVolume findVolume(String sql);//砍伐证的材积与输入材积和进行比较
	
	public int addinyardStatus(inyardStatus c);//给进场工单添加状态
	
	public List<inyard> findinyards(String sql);//搜寻采伐证信息
	
	public yardInventory findhcpd(String sql);//库存盘点搜索年货场
	public List<yardInventory> findhyard(String sql);//库存盘点列表
	public List<yardInventory> findyardList(String sql);//库存进场盘点列表
	
	public List<outyard> findOutyardC(String sql);
	public outyard searchoutyard();//通过工单号查询检尺费
	public treebuy findoutyardCostTable(String sql);//发现检尺费信息
	public outyard findOutSingle1(String sql);//检尺费
	public List<surveyorFee> findSurveyorFee(String sql);
	public int saveSurveyorFee(surveyorFee cp);//保存检尺费信息
	public List<surveyorFee> findSurveyorFeeList(String sql);
}
