package jinshen.dao;
//采伐证输入用到的动作Dao
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

public interface cutnumDao {
	public int addCutnum(cutnum cp);
	public int updateCutnum(cutnum cp);
	public cutnumStatus findCutnumStatus(String sql);//根据采伐证输入得到的信息得到cutnumid
	public int addCutnumStatus(cutnumStatus cp);//在cutnum_status数据表中插入数据
	public int updateCutnumStatus(cutnumStatus cp,double cutnumid);//更新采伐证状态，在工单录入界面，当采伐证超过蓄留材积采伐证状态变为1
	
	public double findcount(String sql);
	public cutnum findCodeSingle(String sql);
	public List<cutnumWatch> findCuteSingle(String sql);//用于采伐证审核
	
	public List<cutnum> findCutnum();
	public cutnum printCutnum();
    public List<cutnum> findCutnumC(String sql);
    public List<cutnum> findCutnumF(String sql);
    
	public List<cutnum> findCutnumproject(String sql);//用于在创建工程包页面选择采伐证号
	
	//public int addprojectid(double each,cutnum cn);//添加工程包到数据库采伐证表中cutnum是double
	//public int addprojectid(cutnum cn);
	public int addprojectid(String each,cutnum cn);//添加工程包到数据库采伐证表中cutnum是字符型
	
	public int addprojectpackage(projectpackage cp);//将信息添加到数据库表工程包中
	public int updateprojectpackage(projectpackage cp);//更新信息添加到数据库表工程包中
	
	public projectpackage findprojectpackage(String sql);//发现工程包信息
	public List<projectpackage> seeprojectpackag(String sql);//在jsp页面显示工程包信息
	public List<cuntnumproj> findCutnumproject1(String sql);//工程包管理台账
	
	public int addCutnumApply(cutnumApply cp);//采伐证申请书数据存入数据库中
	public int updateCutnumApply(double apid,cutnumApply cp);//更新采伐证数据
	public cutnumApply findCutnumApply(String sql);//发现工程包信息
	
	public int  addcutumapplystatus(cutnumfeedback cn);//插入申请id和状态到采伐证申请反馈表
	public int  updateCutnumApplypath(double apid,cutnumfeedback cn);//更新f反馈表的材料
	public int  updatecutumapplystatus(double apid,cutnumfeedback cn);//更新反馈可办理
	public int updatecutumapplystatusN(double apid,cutnumfeedback cn);//更新不可办理状态（木材申请反馈单）
	public cutnumfeedback findApplyid(String sql);//选择申请号
	public List<cutnumApply> seecutnumApply(String sql);//在管理部门申请页面显示采伐证申请页面页面显示工程包信息
	
	public cutnumfeedback findCutfeedback(String sql);//查看采伐证反馈申请表信息（无法通过原因）
	public cutnumfeedback findCutfeedbackP(String sql);//查看采伐证反馈申请表信息（无法通过原因，保存材料路径）
	
	public List<cutnumCheck> findCutnumCheck(String sql);//采伐证中期检查
	public int addcutnumCheck(cutnumCheck cp);//保存中期检查数据
	
	public cutnumProgress findProgress(String sql);//查看采伐证进程进度
}
