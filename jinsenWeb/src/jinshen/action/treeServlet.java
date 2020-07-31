package jinshen.action;
/*录入进场木材信息和出场木材信息*/
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import jinshen.bean.codejson;
import jinshen.bean.compareVolume;
import jinshen.bean.inyard;
import jinshen.bean.inyardStatus;
import jinshen.bean.outyard;
import jinshen.bean.surveyorFee;
import jinshen.bean.tree;
import jinshen.bean.volume;
import jinshen.bean.workpageStatus;
import jinshen.bean.yardInventory;
import jinshen.dao.treeDao;
import jinshen.dao.volumeDao;
import jinshen.dao.workpageDao;
import jinshen.daoimpl.treeDaoImpl;
import jinshen.daoimpl.volumeDaoImpl;
import jinshen.daoimpl.workpageDaoImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class treeServlet
 */
@WebServlet("/treeServlet")
public class treeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public treeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession();
        String sql="";
        treeDao trd=new treeDaoImpl();
        volumeDao vl=new volumeDaoImpl();
        workpageDao wpd=new workpageDaoImpl();
        ObjectMapper mapper = new ObjectMapper();
        //选择材积
        if("volume".equals(action))
        {
        	double td=Double.parseDouble(request.getParameter("td"));
        	double tr=Double.parseDouble(request.getParameter("tr"));
        	sql="select * from volume where tlong ="+td+" and tradius="+tr+"";
			volume vll=vl.findVolumeSingle(sql);
			if(vll!=null)
			{
				float volume=0.00f;
				volume=(float)vll.getMvolume();
				out.print(volume);
			   //out.print(vll.getMvolume());
			}
			else
			{
				out.print(" ");
			}
        }
        //将jsp页面输入的木材信息把保存到数据库tree表中
        else if("treeAdd".equals(action)) {
        	String rebate = request.getParameter("newtree");
        	int id=Integer.parseInt(request.getParameter("id"));
        	JSONObject jb = JSONObject.fromObject(rebate);
        	double workid=Double.parseDouble(request.getParameter("workid"));
        	sql="delete from tree where workid="+workid+"";
        	int ii=trd.delById(sql);
        	for(int i=0;i<id;i++)
        	{
        		JSONArray s=jb.getJSONArray(String.valueOf(i));
        		tree t=new tree();
        		t.setWorkid(workid);
        		t.setTreetype(s.getString(0));
            	t.setTlong(Double.parseDouble(s.getString(1)));
            	t.setTradius(Double.parseDouble(s.getString(2)));
            	t.setNum(Double.parseDouble(s.getString(3)));
            	sql="select * from volume where tlong ="+Double.parseDouble(s.getString(1))+" and tradius="+Double.parseDouble(s.getString(2));
            	volume vll=vl.findVolumeSingle(sql);
            	float tvolume = 0.00f; 
    			tvolume=(float)vll.getMvolume();
            	t.setTvolume(tvolume*t.getNum());
            	t.setUnitprice(1);//默认单价为1
            	t.setTotalnum(1);
            	int flag=trd.addTree(t);//添加木材信息到数据库中
            	out.print(flag);
        	}
        }
        //材积输出
        else if("volumeout".equals(action)) {
        	double td=Double.parseDouble(request.getParameter("td"));
        	double tr=Double.parseDouble(request.getParameter("tr"));
        	sql="select * from volume where tlong ="+td+" and tradius="+tr+"";
			volume vll=vl.findVolumeSingle(sql);
			if(vll!=null)
			{
				float volume=0.00f;
				volume=(float)vll.getMvolume();
				out.print(volume);
			   //out.print(vll.getMvolume());
			}
			else
			{
				out.print(" ");
			}
        }
        //录入销售输出木材，并保存都数据库木材输出表treeout中
        else if("treeAddout".equals(action)) {
        	String rebate = request.getParameter("newtree");
        	int id=Integer.parseInt(request.getParameter("id"));
        	JSONObject jb = JSONObject.fromObject(rebate);
        	double workid=Double.parseDouble(request.getParameter("workid"));
        	sql="delete from treeout where workid="+workid+"";
        	int ii=trd.delById(sql);
        	for(int i=0;i<id;i++)
        	{
        		JSONArray s=jb.getJSONArray(String.valueOf(i));
        		tree t=new tree();
        		t.setWorkid(workid);
        		t.setTreetype(s.getString(0));
            	t.setTlong(Double.parseDouble(s.getString(1)));
            	t.setTradius(Double.parseDouble(s.getString(2)));
            	t.setNum(Double.parseDouble(s.getString(3)));
            	sql="select * from volume where tlong ="+Double.parseDouble(s.getString(1))+" and tradius="+Double.parseDouble(s.getString(2));
            	volume vll=vl.findVolumeSingle(sql);
            	float tvolume = 0.00f; 
    			tvolume=(float)vll.getMvolume();
            	t.setTvolume(tvolume*t.getNum());
            	//材积单价还没有
            	t.setUnitprice(1);
            	t.setTotalnum(1);
            	int flag=trd.addTreeout(t);
            	out.print(flag);
        }
        
	}
        //把输入木材信息页面的信息存到数据库表inyard中
        else if("inyard".equals(action)) {
        	double workid=Double.parseDouble(request.getParameter("workid"));
        	String cutNum = request.getParameter("cutnum");
        	String cutSite=request.getParameter("cutsite");
        	String yarddate=request.getParameter("yarddate");
        	String carNumber=request.getParameter("carnumber");
        	String yard=request.getParameter("yard");
        	//double batchNum=Double.parseDouble(request.getParameter("batchnum"));
        	String section=request.getParameter("section");
        	String surveyor=request.getParameter("surveyor");
        	double toltree=Double.parseDouble(request.getParameter("toltree"));
        	double tolstere=Double.parseDouble(request.getParameter("tolstere"));
        	//System.out.println(tolstere);
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        	Date d = null;
			try {
				d = format.parse(yarddate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date yardtime = new java.sql.Date(d.getTime());
			inyard cp = new inyard();
			cp.setWorkid(workid);
			cp.setCutNum(cutNum);
			cp.setCutSite(cutSite);
			cp.setYarddate(yardtime);
			cp.setCarNumber(carNumber);
			cp.setYard(yard);
			cp.setBatchNum(1);
			cp.setSurveyor(surveyor);
			cp.setTolTree(toltree);
			cp.setTolStere(tolstere);
			cp.setSection(section);
			sql = "select count(*) from inyard where workid="+cp.getWorkid()+"";
			int flag=0;
			double f=trd.findcount(sql);
			if(f==1) {
				flag=trd.updateInyard(cp,workid);
			}
			else {
				flag=trd.addInyard(cp);
			}
			if(flag==1) {
				sql="select * from inyard where workid="+workid+"";
				inyard inyard=trd.findInSingle(sql);
				sql="select * from tree where workid="+workid+"";
				List<tree> tree=trd.findTree(sql);
				request.setAttribute("inyard", inyard);
				request.setAttribute("tree", tree);
				request.getRequestDispatcher("treeinUpdate.jsp").forward(request, response);
			}
			else {
			request.getRequestDispatcher("treein.jsp").forward(request, response);	
			}
        }
        //更新木材inyard木材信息
        else if("updateTreein".equals(action)) {
        	double workid=Double.parseDouble(request.getParameter("workid"));
        	String cutNum = request.getParameter("cutnum");
        	String cutSite=request.getParameter("cutsite");
        	String yarddate=request.getParameter("yarddate");
        	String carNumber=request.getParameter("carnumber");
        	String yard=request.getParameter("yard");
        	//double batchNum=Double.parseDouble(request.getParameter("batchnum"));
        	String section=request.getParameter("section");
        	String surveyor=request.getParameter("surveyor");
        	double toltree=Double.parseDouble(request.getParameter("toltree"));
        	double tolstere=Double.parseDouble(request.getParameter("tolstere"));
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        	Date d = null;
			try {
				d = format.parse(yarddate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date yardtime = new java.sql.Date(d.getTime());
			inyard cp = new inyard();
			cp.setWorkid(workid);
			cp.setCutNum(cutNum);
			cp.setCutSite(cutSite);
			cp.setYarddate(yardtime);
			cp.setCarNumber(carNumber);
			cp.setYard(yard);
			cp.setBatchNum(1);//批次号默认为1，以后再进行改正
			cp.setSurveyor(surveyor);
			cp.setTolTree(toltree);
			cp.setTolStere(tolstere);
			cp.setSection(section);
			sql = "select count(*) from inyard where workid="+cp.getWorkid()+"";
			int flag=0;
			double f=trd.findcount(sql);
			if(f==1) {
				flag = trd.updateInyard(cp,workid);
				sql="select count(*) from codestatus where workid="+workid+" and status='back'";
				double ff=trd.findcount(sql);
				if(ff>0) {
					sql="update codestatus set status='again' where workid="+workid+"";
					flag = trd.updateWork(sql);
				}
			}
			else {
				flag=trd.addInyard(cp);
			}
			if(flag==1) {
				sql = "select * from inyard where workid="+workid+"";
				inyard inyard = trd.findInSingle(sql);
				sql="select * from tree where workid="+workid+"";
				List<tree> tree=trd.findTree(sql);
				request.setAttribute("inyard", inyard);
				request.setAttribute("tree", tree);
				request.getRequestDispatcher("treeinUpdate.jsp").forward(request, response);
			}
			else {
				sql = "select * from inyard where workid="+workid+"";
				inyard inyard = trd.findInSingle(sql);
				sql="select * from tree where workid="+workid+"";
        		List<tree> tree=trd.findTree(sql);
				request.setAttribute("inyard", inyard);
				request.setAttribute("tree", tree);
				request.getRequestDispatcher("treeinUpdate.jsp").forward(request, response);
			}
        }
        //把销售输出木材页面信息保存到数据库outyard页面
        else if("outyard".equals(action)) {
        	double workid=Double.parseDouble(request.getParameter("workid"));
        	String yarddate=request.getParameter("yarddate");
        	String carNumber=request.getParameter("carnumber");
        	String yard=request.getParameter("yard");
        	String batchNum=request.getParameter("batchnum");        			
        	String surveyor=request.getParameter("surveyor");
        	double toltree=Double.parseDouble(request.getParameter("toltree"));
        	double tolstere=Double.parseDouble(request.getParameter("tolstere"));
        	String contractnum=request.getParameter("sale_contract_id");//销售合同
        	String sale_callout_orderid=request.getParameter("sale_callout_orderid");
        	String section=request.getParameter("section");
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        	Date d = null;
			try {
				d = format.parse(yarddate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date yardtime = new java.sql.Date(d.getTime());
			outyard cp = new outyard();
			cp.setWorkid(workid);;
			cp.setYarddate(yardtime);
			cp.setCarNumber(carNumber);
			cp.setYard(yard);
			cp.setBatchNum(batchNum);
			cp.setSurveyor(surveyor);
			cp.setTolstere(tolstere);
			cp.setToltree(toltree);
			cp.setContractnum(contractnum);
			cp.setSaleCalloutorderid(sale_callout_orderid);
			cp.setSection(section);
			sql = "select count(*) from outyard where workid="+cp.getWorkid()+"";
			int flag=0;
			double f=trd.findcount(sql);
			if(f==1) {
				flag=trd.updateOutyard(cp);
			}
			else {
				flag=trd.addOutyard(cp);
			}
			if(flag==1) {
				sql="select * from outyard where workid="+workid+"";
				outyard outyard=trd.findOutSingle(sql);
				sql="select * from treeout where workid="+workid+"";
				List<tree> tree=trd.findTree(sql);
				request.setAttribute("outyard", outyard);
				request.setAttribute("tree", tree);
				request.getRequestDispatcher("treeoutUpdate.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("treeout.jsp").forward(request, response);
			}
        }
        //更新销售页面的信息到数据库中outyard表中
        else if("updateTreeout".equals(action)) {
        	double workid=Double.parseDouble(request.getParameter("workid"));
        	String yarddate=request.getParameter("yarddate");
        	String carNumber=request.getParameter("carnumber");
        	String yard=request.getParameter("yard");
        	String batchNum=request.getParameter("batchnum");        			
        	String surveyor=request.getParameter("surveyor");
        	double toltree=Double.parseDouble(request.getParameter("toltree"));
        	double tolstere=Double.parseDouble(request.getParameter("tolstere"));
        	String contractnum=request.getParameter("sale_contract_id");//销售合同
        	String sale_callout_orderid=request.getParameter("sale_callout_orderid");
        	String section=request.getParameter("section");
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        	Date d = null;
			try {
				d = format.parse(yarddate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date yardtime = new java.sql.Date(d.getTime());
			outyard cp = new outyard();
			cp.setWorkid(workid);
			cp.setYarddate(yardtime);
			cp.setCarNumber(carNumber);
			cp.setYard(yard);
			cp.setBatchNum(batchNum);
			cp.setSurveyor(surveyor);
			cp.setTolstere(tolstere);
			cp.setToltree(toltree);
			cp.setContractnum(contractnum);
			cp.setSaleCalloutorderid(sale_callout_orderid);
			cp.setSection(section);
			sql = "select count(*) from outyard where workid="+cp.getWorkid()+"";
			int flag=0;
			double f=trd.findcount(sql);
			if(f==1) {
				flag = trd.updateOutyard(cp);
				sql="select count(*) from codestatus where workid="+workid+" and status='back'";
				double ff=trd.findcount(sql);
				if(ff>0) {
					sql="update codestatus set status='again' where workid="+workid+"";
					flag = trd.updateWork(sql);
				}
			}
			else {
				flag=trd.addOutyard(cp);
			}
			if(flag==1) {
				sql = "select * from outyard where workid="+workid+"";
				outyard outyard = trd.findOutSingle(sql);
				sql="select * from treeout where workid="+workid+"";
				List<tree> tree=trd.findTree(sql);
				request.setAttribute("outyard", outyard);
				request.setAttribute("tree", tree);
				request.getRequestDispatcher("treeoutUpdate.jsp").forward(request, response);
			}
			else {
				sql = "select * from outyard where workid="+workid+"";
				outyard outyard = trd.findOutSingle(sql);
				sql="select * from treeout where workid="+workid+"";
				List<tree> tree=trd.findTree(sql);
				request.setAttribute("outyard", outyard);
				request.setAttribute("tree", tree);
				request.getRequestDispatcher("treeoutUpdate.jsp").forward(request, response);
			}
        }        
        /*else if("inyard1".equals(action)) {
        	double workid=Double.parseDouble(request.getParameter("workid"));
        	double cutNum = Double.parseDouble(request.getParameter("cutnum"));
        	String cutSite=request.getParameter("cutsite");
        	String yarddate=request.getParameter("yarddate");
        	String carNumber=request.getParameter("carnumber");
        	String yard=request.getParameter("yard");
        	double batchNum=Double.parseDouble(request.getParameter("batchnum"));        			
        	String surveyor=request.getParameter("surveyor");
        	double toltree=Double.parseDouble(request.getParameter("toltree"));
        	double tolstere=Double.parseDouble(request.getParameter("tolstere"));
        	//System.out.println(tolstere);
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        	Date d = null;
			try {
				d = format.parse(yarddate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date yardtime = new java.sql.Date(d.getTime());
			sql="select sum(tolstere) as tolstere1,b.cutstore from inyard a join cutnum b on a.cutNum=b.cutnum where a.cutNum="+cutNum+"";
        	compareVolume compareVolume=trd.findVolume(sql);
        	if(compareVolume!=null)
        	{
        		float volumec=0.00f;
				float volumet=0.00f;
				volumec=(float)compareVolume.getcutstore();
				volumet=(float)compareVolume.getTolstere();
				if (volumet<(volumec*0.8))
				{
					inyard cp = new inyard();
					cp.setWorkid(workid);
					cp.setCutNum(cutNum);
					cp.setCutSite(cutSite);
					cp.setYarddate(yardtime);
					cp.setCarNumber(carNumber);
					cp.setYard(yard);
					cp.setBatchNum(batchNum);
					cp.setSurveyor(surveyor);
					cp.setTolTree(toltree);
					cp.setTolStere(tolstere);
					sql = "select count(*) from inyard where workid="+cp.getWorkid()+"";
					int flag=0;
					double f=trd.findcount(sql);
					if(f==1)
					{
						flag=trd.updateInyard(cp);
					}
					else
					{
						flag=trd.addInyard(cp);
						///System.out.println(volumec*0.8-volumet);
						//out.print(volumec*0.8-volumet);
					}
					if(flag==1) {
						sql="select * from inyard where workid="+workid+"";
						inyard inyard=trd.findInSingle(sql);
						sql="select * from tree where workid="+workid+"";
						List<tree> tree=trd.findTree(sql);
						request.setAttribute("inyard", inyard);
						request.setAttribute("tree", tree);
						out.print(volumec*0.8-volumet);
						request.getRequestDispatcher("treeinUpdate.jsp").forward(request, response);
						
					}
					else {
					request.getRequestDispatcher("treein.jsp").forward(request, response);	
					}
				}
				else
				{
					out.print("输入材积已经超过本砍伐证的蓄留材积的80%");
				}
        	}
        	else
			{
				out.print(" ");
			}
			
        }*/
        //将木材信息添加到数据库表tree中,并判断是否大于砍伐证输入的材积
        else if("treeAdd2".equals(action)) {
        	String rebate = request.getParameter("newtree");
        	int id=Integer.parseInt(request.getParameter("id"));
        	JSONObject jb = JSONObject.fromObject(rebate);
        	double workid=Double.parseDouble(request.getParameter("workid"));
        	String cutNum = request.getParameter("cutnum");
        	String cutSite=request.getParameter("cutsite");
        	String yarddate=request.getParameter("yarddate");
        	String carNumber=request.getParameter("carnumber");
        	String yard=request.getParameter("yard");
        	//double batchNum=Double.parseDouble(request.getParameter("batchnum"));
        	String section=request.getParameter("section");
        	String surveyor=request.getParameter("surveyor");
        	double toltree=Double.parseDouble(request.getParameter("toltree"));
        	double tolstere=Double.parseDouble(request.getParameter("tolstere"));
        	//System.out.println(tolstere);
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        	Date d = null;
			try {
				d = format.parse(yarddate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date yardtime = new java.sql.Date(d.getTime());
				//保存材积
				int flagt=0;
				for(int i=0;i<id;i++)
		        {
		        	JSONArray s=jb.getJSONArray(String.valueOf(i));
		        	tree t=new tree();
		        	t.setWorkid(workid);
		        	t.setTreetype(s.getString(0));
		            t.setTlong(Double.parseDouble(s.getString(1)));
		            t.setTradius(Double.parseDouble(s.getString(2)));
		            t.setNum(Double.parseDouble(s.getString(3)));
		            sql="select * from volume where tlong ="+Double.parseDouble(s.getString(1))+" and tradius="+Double.parseDouble(s.getString(2));
		            volume vll=vl.findVolumeSingle(sql);
		            float tvolume = 0.00f; 
		    		tvolume=(float)vll.getMvolume();
		            t.setTvolume(tvolume*t.getNum());
		            t.setUnitprice(1);//默认单价为1
		            t.setTotalnum(1);
		            flagt=trd.addTree(t);//添加木材信息到数据库中
		        }
				//保存进场信息
			inyard cp = new inyard();
			cp.setWorkid(workid);
			cp.setCutNum(cutNum);
			cp.setCutSite(cutSite);
			cp.setYarddate(yardtime);
			cp.setCarNumber(carNumber);
			cp.setYard(yard);
			cp.setBatchNum(1);
			cp.setSurveyor(surveyor);
			cp.setTolTree(toltree);
			cp.setTolStere(tolstere);
			cp.setSection(section);
			sql = "select count(*) from inyard where workid="+cp.getWorkid()+"";
			double f=trd.findcount(sql);
			int flag=trd.addInyard(cp);
			System.out.println(flag);
			if(flag>0)
			{
				workpageStatus ws=new workpageStatus();
				ws.setWorkidstatus(4);//检尺员填写木材信息工单状态为4
				int wpp=wpd.updateWorkpagestatus1(workid,ws);
				if(wpp>0) {
					out.print("保存成功");
				}
				else {
					out.write("保存失败");
				}
				//System.out.println(workid);
			}
			else {
				out.print("保存失败");	
			}	
        }
        //更新进场表
        else if("inyardupdate2".equals(action)) {
        	String rebate = request.getParameter("newtree");
        	int id=Integer.parseInt(request.getParameter("id"));
        	JSONObject jb = JSONObject.fromObject(rebate);
        	double workid=Double.parseDouble(request.getParameter("workid"));
        	String cutNum = request.getParameter("cutnum");
        	String cutSite=request.getParameter("cutsite");
        	String yarddate=request.getParameter("yarddate");
        	String carNumber=request.getParameter("carnumber");
        	String yard=request.getParameter("yard");
        	//double batchNum=Double.parseDouble(request.getParameter("batchnum"));
        	String section=request.getParameter("section");
        	String surveyor=request.getParameter("surveyor");
        	double toltree=Double.parseDouble(request.getParameter("toltree"));
        	double tolstere=Double.parseDouble(request.getParameter("tolstere"));
        	//System.out.println(tolstere);
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        	Date d = null;
			try {
				d = format.parse(yarddate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date yardtime = new java.sql.Date(d.getTime());			
				//保存材积
				int flagt=0;
				for(int i=0;i<id;i++)
		        {
		        	JSONArray s=jb.getJSONArray(String.valueOf(i));
		        	tree t=new tree();
		        	t.setWorkid(workid);
		        	t.setTreetype(s.getString(0));
		            t.setTlong(Double.parseDouble(s.getString(1)));
		            t.setTradius(Double.parseDouble(s.getString(2)));
		            t.setNum(Double.parseDouble(s.getString(3)));
		            sql="select * from volume where tlong ="+Double.parseDouble(s.getString(1))+" and tradius="+Double.parseDouble(s.getString(2));
		            volume vll=vl.findVolumeSingle(sql);
		            float tvolume = 0.00f; 
		    		tvolume=(float)vll.getMvolume();
		            t.setTvolume(tvolume*t.getNum());
		            t.setUnitprice(1);//默认单价为1
		            t.setTotalnum(1);
		            sql = "select count(*) from tree where workid="+t.getWorkid()+"";
					double ff=trd.findcount(sql);
					if(ff>=1) {
						flagt=trd.updateTree(t);
					}
					else
						{
						flagt=trd.addTree(t);//添加木材信息到数据库中
						}
		        }
				//保存进场信息
			inyard cp = new inyard();
			cp.setWorkid(workid);
			cp.setCutNum(cutNum);
			cp.setCutSite(cutSite);
			cp.setYarddate(yardtime);
			cp.setCarNumber(carNumber);
			cp.setYard(yard);
			cp.setBatchNum(1);
			cp.setSurveyor(surveyor);
			cp.setTolTree(toltree);
			cp.setTolStere(tolstere);
			cp.setSection(section);
			sql = "select count(*) from inyard where workid="+cp.getWorkid()+"";
			double f=trd.findcount(sql);
			int flag=0;
			 if (f==1)
			    {
			    	flag = trd.updateInyard(cp,workid);
			    }
			    else {
					flag=trd.addInyard(cp);
				}
			//System.out.println(flag);
			if(flag>0)
			{
				workpageStatus ws=new workpageStatus();
				ws.setWorkidstatus(7);//检尺员填写木材信息工单状态为4
				int wpp=wpd.updateWorkpagestatus1(workid,ws);
				if(wpp>0) {
					out.print("工单状态保存成功");
				}
				else {
					out.write("工单状态保存失败");
				}
				//System.out.println(workid);
			}
			else {
				out.print("进场工单更新失败");	
			}
				}
        //暂时没用
        else if("treeAdd3".equals(action)) {
        	String rebate = request.getParameter("newtree");
        	int id=Integer.parseInt(request.getParameter("id"));
        	JSONObject jb = JSONObject.fromObject(rebate);
        	double workid=Double.parseDouble(request.getParameter("workid"));
        	String cutNum = request.getParameter("cutnum");
        	String cutSite=request.getParameter("cutsite");
        	String yarddate=request.getParameter("yarddate");
        	String carNumber=request.getParameter("carnumber");
        	String yard=request.getParameter("yard");
        	//double batchNum=Double.parseDouble(request.getParameter("batchnum"));
        	String section=request.getParameter("section");
        	String surveyor=request.getParameter("surveyor");
        	double toltree=Double.parseDouble(request.getParameter("toltree"));
        	double tolstere=Double.parseDouble(request.getParameter("tolstere"));
        	//System.out.println(tolstere);
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        	Date d = null;
			try {
				d = format.parse(yarddate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date yardtime = new java.sql.Date(d.getTime());
			//sql="SELECT SUM(inyard.tolstere) AS tolstere1 FROM inyard WHERE cutNum like '%"+cutNum+"%'";//从inyard数据库中选取材积
			sql="SELECT SUM(inyard.tolstere) AS tolstere1 FROM inyard WHERE cutNum='"+cutNum+"'";//从inyard数据库中选取材积
			compareVolume compareVolume=trd.findVolume(sql);//inyard输入的材积库存材积
			double volumet=(float)compareVolume.getTolstere();//相同采伐证累加的材积
			double tolstereal=volumet+tolstere;
			sql="SELECT statusVolume from cutnum_status JOIN cutnum ON cutnum.cutnumid=cutnum_status.cutnumid WHERE cutnum like '%"+cutNum+"%'";
			compareVolume compareVolumecut=trd.findVolume(sql);//
			double volumetcut=(float)compareVolumecut.getTolstere();//从采伐证才选择的材积
			if(tolstereal<(0.95*volumetcut))
			{    
				//保存材积
				int flagt=0;
				for(int i=0;i<id;i++)
		        {
		        	JSONArray s=jb.getJSONArray(String.valueOf(i));
		        	tree t=new tree();
		        	t.setWorkid(workid);
		        	t.setTreetype(s.getString(0));
		            t.setTlong(Double.parseDouble(s.getString(1)));
		            t.setTradius(Double.parseDouble(s.getString(2)));
		            t.setNum(Double.parseDouble(s.getString(3)));
		            sql="select * from volume where tlong ="+Double.parseDouble(s.getString(1))+" and tradius="+Double.parseDouble(s.getString(2));
		            volume vll=vl.findVolumeSingle(sql);
		            float tvolume = 0.00f; 
		    		tvolume=(float)vll.getMvolume();
		            t.setTvolume(tvolume*t.getNum());
		            t.setUnitprice(1);//默认单价为1
		            t.setTotalnum(1);
		            flagt=trd.addTree(t);//添加木材信息到数据库中
		        }
				//保存进场信息
			inyard cp = new inyard();
			cp.setWorkid(workid);
			cp.setCutNum(cutNum);
			cp.setCutSite(cutSite);
			cp.setYarddate(yardtime);
			cp.setCarNumber(carNumber);
			cp.setYard(yard);
			cp.setBatchNum(1);
			cp.setSurveyor(surveyor);
			cp.setTolTree(toltree);
			cp.setTolStere(tolstere);
			cp.setSection(section);
			sql = "select count(*) from inyard where workid="+cp.getWorkid()+"";
			double f=trd.findcount(sql);
			int flag=trd.addInyard(cp);
			System.out.println(flag);
			if(flag>0)
			{
				workpageStatus ws=new workpageStatus();
				ws.setWorkidstatus(4);//检尺员填写木材信息工单状态为4
				int wpp=wpd.updateWorkpagestatus1(workid,ws);
				if(wpp>0) {
					out.print("保存成功");
				}
				else {
					out.write("保存失败");
				}
				//System.out.println(workid);
			}
			else {
				out.print("保存s失败");	
			}
			}
			else if((tolstereal>(0.95*volumetcut)) && (tolstereal<volumetcut))
			{
				//保存材积
				int flagt=0;
				for(int i=0;i<id;i++)
		        {
		        	JSONArray s=jb.getJSONArray(String.valueOf(i));
		        	tree t=new tree();
		        	t.setWorkid(workid);
		        	t.setTreetype(s.getString(0));
		            t.setTlong(Double.parseDouble(s.getString(1)));
		            t.setTradius(Double.parseDouble(s.getString(2)));
		            t.setNum(Double.parseDouble(s.getString(3)));
		            sql="select * from volume where tlong ="+Double.parseDouble(s.getString(1))+" and tradius="+Double.parseDouble(s.getString(2));
		            volume vll=vl.findVolumeSingle(sql);
		            float tvolume = 0.00f; 
		    		tvolume=(float)vll.getMvolume();
		            t.setTvolume(tvolume*t.getNum());
		            t.setUnitprice(1);//默认单价为1
		            t.setTotalnum(1);
		            flagt=trd.addTree(t);//添加木材信息到数据库中
		        }
				//保存进场信息
			     inyard cp = new inyard();
			     cp.setWorkid(workid);
			     cp.setCutNum(cutNum);
			     cp.setCutSite(cutSite);
			     cp.setYarddate(yardtime);
			     cp.setCarNumber(carNumber);
			     cp.setYard(yard);
			     cp.setBatchNum(1);
			     cp.setSurveyor(surveyor);
			     cp.setTolTree(toltree);
			     cp.setTolStere(tolstere);
			     cp.setSection(section);
			     sql = "select count(*) from inyard where workid="+cp.getWorkid()+"";
			     int flag=0;
			    double f=trd.findcount(sql);
			    flag=trd.addInyard(cp);
			    out.write("输入材积已经超过本砍伐证的蓄留材积95%");
			   if(flag>0)
			   {
				   workpageStatus ws=new workpageStatus();
				   ws.setWorkidstatus(4);//检尺员填写木材信息工单状态为4
				   int wpp=wpd.updateWorkpagestatus1(workid,ws);
					System.out.println(wpp);
					if(wpp>0) {
						out.write("保存成功");
					}
			    }
			    else {
			    	out.print("保存s失败");		
			    }
			   }
			  else {
					out.write("输入材积已经超过本砍伐证的蓄留材积");
				}
				}
        //更新inyard//暂时没用
        else if("inyardupdate3".equals(action)) {
        	String rebate = request.getParameter("newtree");
        	int id=Integer.parseInt(request.getParameter("id"));
        	JSONObject jb = JSONObject.fromObject(rebate);
        	double workid=Double.parseDouble(request.getParameter("workid"));
        	String cutNum = request.getParameter("cutnum");
        	String cutSite=request.getParameter("cutsite");
        	String yarddate=request.getParameter("yarddate");
        	String carNumber=request.getParameter("carnumber");
        	String yard=request.getParameter("yard");
        	//double batchNum=Double.parseDouble(request.getParameter("batchnum"));
        	String section=request.getParameter("section");
        	String surveyor=request.getParameter("surveyor");
        	double toltree=Double.parseDouble(request.getParameter("toltree"));
        	double tolstere=Double.parseDouble(request.getParameter("tolstere"));
        	//System.out.println(tolstere);
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        	Date d = null;
			try {
				d = format.parse(yarddate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date yardtime = new java.sql.Date(d.getTime());
			sql="SELECT SUM(inyard.tolstere) AS tolstere1 FROM inyard WHERE cutNum like '%"+cutNum+"%'";//从inyard数据库中选取材积
			compareVolume compareVolume=trd.findVolume(sql);//inyard输入的材积库存材积
			double volumet=(float)compareVolume.getTolstere();//相同采伐证累加的材积
			double tolstereal=volumet+tolstere;
			sql="SELECT statusVolume from cutnum_status JOIN cutnum ON cutnum.cutnumid=cutnum_status.cutnumid WHERE cutnum like '%"+cutNum+"%'";
			compareVolume compareVolumecut=trd.findVolume(sql);//
			double volumetcut=(float)compareVolumecut.getTolstere();//从采伐证才选择的材积
			if(tolstereal<(0.95*volumetcut))
			{    
				//保存材积
				int flagt=0;
				for(int i=0;i<id;i++)
		        {
		        	JSONArray s=jb.getJSONArray(String.valueOf(i));
		        	tree t=new tree();
		        	t.setWorkid(workid);
		        	t.setTreetype(s.getString(0));
		            t.setTlong(Double.parseDouble(s.getString(1)));
		            t.setTradius(Double.parseDouble(s.getString(2)));
		            t.setNum(Double.parseDouble(s.getString(3)));
		            sql="select * from volume where tlong ="+Double.parseDouble(s.getString(1))+" and tradius="+Double.parseDouble(s.getString(2));
		            volume vll=vl.findVolumeSingle(sql);
		            float tvolume = 0.00f; 
		    		tvolume=(float)vll.getMvolume();
		            t.setTvolume(tvolume*t.getNum());
		            t.setUnitprice(1);//默认单价为1
		            t.setTotalnum(1);
		            sql = "select count(*) from tree where workid="+t.getWorkid()+"";
					double ff=trd.findcount(sql);
					if(ff==1) {
						flagt=trd.updateTree(t);
					}
					else
						{
						flagt=trd.addTree(t);//添加木材信息到数据库中
						}
		        }
				//保存进场信息
			inyard cp = new inyard();
			cp.setWorkid(workid);
			cp.setCutNum(cutNum);
			cp.setCutSite(cutSite);
			cp.setYarddate(yardtime);
			cp.setCarNumber(carNumber);
			cp.setYard(yard);
			cp.setBatchNum(1);
			cp.setSurveyor(surveyor);
			cp.setTolTree(toltree);
			cp.setTolStere(tolstere);
			cp.setSection(section);
			sql = "select count(*) from inyard where workid="+cp.getWorkid()+"";
			double f=trd.findcount(sql);
			int flag=0;
			 if (f==1)
			    {
			    	flag = trd.updateInyard(cp,workid);
			    }
			    else {
					flag=trd.addInyard(cp);
				}
			//System.out.println(flag);
			if(flag>0)
			{
				workpageStatus ws=new workpageStatus();
				ws.setWorkidstatus(7);//检尺员填写木材信息工单状态为4
				int wpp=wpd.updateWorkpagestatus1(workid,ws);
				if(wpp>0) {
					out.print("工单状态保存成功");
				}
				else {
					out.write("工单状态保存失败");
				}
				//System.out.println(workid);
			}
			else {
				out.print("进场工单更新失败");	
			}
			}
			else if((tolstereal>(0.95*volumetcut)) && (tolstereal<volumetcut))
			{
				//保存材积
				int flagt=0;
				for(int i=0;i<id;i++)
		        {
		        	JSONArray s=jb.getJSONArray(String.valueOf(i));
		        	tree t=new tree();
		        	t.setWorkid(workid);
		        	t.setTreetype(s.getString(0));
		            t.setTlong(Double.parseDouble(s.getString(1)));
		            t.setTradius(Double.parseDouble(s.getString(2)));
		            t.setNum(Double.parseDouble(s.getString(3)));
		            sql="select * from volume where tlong ="+Double.parseDouble(s.getString(1))+" and tradius="+Double.parseDouble(s.getString(2));
		            volume vll=vl.findVolumeSingle(sql);
		            float tvolume = 0.00f; 
		    		tvolume=(float)vll.getMvolume();
		            t.setTvolume(tvolume*t.getNum());
		            t.setUnitprice(1);//默认单价为1
		            t.setTotalnum(1);
		            //flagt=trd.addTree(t);//添加木材信息到数据库中
		            sql = "select count(*) from tree where workid="+t.getWorkid()+"";
					double ff=trd.findcount(sql);
					if(ff==1) {
						flagt=trd.updateTree(t);
					}
					else
						{
						flagt=trd.addTree(t);//添加木材信息到数据库中
						}
		        }
				//保存进场信息
			     inyard cp = new inyard();
			     cp.setWorkid(workid);
			     cp.setCutNum(cutNum);
			     cp.setCutSite(cutSite);
			     cp.setYarddate(yardtime);
			     cp.setCarNumber(carNumber);
			     cp.setYard(yard);
			     cp.setBatchNum(1);
			     cp.setSurveyor(surveyor);
			     cp.setTolTree(toltree);
			     cp.setTolStere(tolstere);
			     cp.setSection(section);
			     sql = "select count(*) from inyard where workid="+cp.getWorkid()+"";
			     int flag=0;
			    double f=trd.findcount(sql);
			    if (f==1)
			    {
			    	flag = trd.updateInyard(cp,workid);
			    }
			    else {
					flag=trd.addInyard(cp);
				}
			    out.write("输入材积已经超过本砍伐证的蓄留材积95%");
			   if(flag>0)
			   {
				   workpageStatus ws=new workpageStatus();
					ws.setWorkidstatus(7);//补交材料状态为7
					int wpp=wpd.updateWorkpagestatus1(workid,ws);
					System.out.println(wpp);
					if(wpp>0) {
						out.write("保存成功");
					}
			    }
			    else {
			    	out.print("保存S失败");		
			    }
			   }
			  else {
					out.write("输入材积已经超过本砍伐证的蓄留材积");
				}
				}
        else if("inyardinfo".equals(action))
        {
        	String str=request.getParameter("workid");
    		str=str.replace("'", "");
    		double workid=Double.parseDouble(str);
        	sql="select workid,cutNum,yarddate,cutSite,carNumber,yard,surveyor,toltree,tolstere,section from inyard where workid="+workid+"";
			inyard inyard=trd.findInSingle(sql);
			sql="select workid,treetype,tlong,tradius,num,tvolume from tree where workid="+workid+"";
			List<tree> tree=trd.findTree(sql);
			request.setAttribute("inyard", inyard);
			request.setAttribute("tree", tree);
			request.getRequestDispatcher("treeinUpdateinfo.jsp").forward(request, response);
        }
        //审核通过的工单
        else if("inyardtreePass".equals(action))
        {
        	String str=request.getParameter("workid");
    		str=str.replace("'", "");
    		double workid=Double.parseDouble(str);
        	sql="select workid,cutNum,yarddate,cutSite,carNumber,yard,surveyor,toltree,tolstere,section from inyard where workid="+workid+"";
			inyard inyard=trd.findInSingle(sql);
			sql="select workid,treetype,tlong,tradius,num,tvolume from tree where workid="+workid+"";
			List<tree> tree=trd.findTree(sql);
			request.setAttribute("inyard", inyard);
			request.setAttribute("tree", tree);
			request.getRequestDispatcher("treeinUpdatePass.jsp").forward(request, response);
        }
        
        //信息中心审核进场工单
        else if("writepage".equals(action))
        {
        	String mytype = request.getParameter("type");
        	if(mytype.equals("all"))
        	{
        		sql="select workid,cutNum,yarddate,cutSite,carNumber,yard,surveyor,section from inyard";
        		List<inyard> worki=trd.findinyards(sql);
        		//System.out.print(worki.get(8));
            	mapper.writeValue(response.getWriter(), worki);
        	}
        	//未通过
        	else if(mytype.equals("notpass"))
        	{
        		sql="select i.workid,i.cutNum,i.yarddate,i.cutSite,i.carNumber,i.yard,i.surveyor,i.section from inyard as i join workpage_status as w on i.workid=w.workid where w.workid_status=6";
        		List<inyard> work=trd.findinyards(sql);
                //System.out.print(work.get(8));
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//通过
        	else if(mytype.equals("pass"))
        	{
        		sql="select i.workid,i.cutNum,i.yarddate,i.cutSite,i.carNumber,i.yard,i.surveyor,i.section from inyard as i join workpage_status as w on i.workid=w.workid where w.workid_status=5";
        		List<inyard> worki=trd.findinyards(sql);
            	mapper.writeValue(response.getWriter(), worki);
        	}
        	//未审核
        	else if(mytype.equals("notshenhe"))
        	{
        		sql="select i.workid,i.cutNum,i.yarddate,i.cutSite,i.carNumber,i.yard,i.surveyor,i.section from inyard as i join workpage_status as w on i.workid=w.workid where w.workid_status=4";
        		List<inyard> work=trd.findinyards(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//审核后补充材料
        	else if(mytype.equals("buchong"))
        	{
        		sql="select i.workid,i.cutNum,i.yarddate,i.cutSite,i.carNumber,i.yard,i.surveyor,i.section from inyard as i join workpage_status as w on i.workid=w.workid where w.workid_status=7";
        		List<inyard> work=trd.findinyards(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//删除
        	else if(mytype.equals("alldelete"))
        	{
        		String mygroup = request.getParameter("workid");
            	JSONArray json=JSONArray.fromObject(mygroup); 
            	double each=0;
            	for(int i=0;i<json.length();i++)
            	{
            		each=Double.parseDouble(json.getString(i));
            		 sql="delete from inyard where workid="+each+"";
            		 String sql1="delete from tree where workid="+each+"";
            		 int j=wpd.updateWork(sql);
            		 int jj=wpd.updateWork(sql1);
            		 if(j==0 && jj==0)
            		 {
            			 out.print("删除失败！");
            			 break;
            		 }
            	}
        	}
        }
        //信息中心对进场工单进行审核
        else if("inyardyesorno".equals(action)) {
        	String mytype = request.getParameter("type");
        	double workid=Double.parseDouble(request.getParameter("workid"));
        	String rejectreason = request.getParameter("rejectreason");
        	System.out.println("...."+rejectreason + "...");
        	if(mytype.equals("yes")) {
        		workpageStatus ws=new workpageStatus();
        		//ws.setWorkid(workid);
        		ws.setWorkidstatus(5);
        		ws.setRejectreason(rejectreason);
        		int flag=wpd.updateWorkpagestatus(workid,ws);
        		if(flag>0){
        			out.print(flag);
        			}
        	}
        	else if(mytype.equals("not")) {
        		workpageStatus ws=new workpageStatus();
        		//ws.setWorkid(workid);
        		ws.setWorkidstatus(6);
        		ws.setRejectreason(rejectreason);
        		int flag=wpd.updateWorkpagestatusN(workid,ws);
        		if(flag>0){
        			out.print(flag);
        			}
        	}
        }
        //信息中心对修改的采伐证
        else if("inyardyesornoxiu".equals(action)) {
        	String mytype = request.getParameter("type");
        	double workid=Double.parseDouble(request.getParameter("workid"));
        	//String rejectreason = request.getParameter("rejectreason");
        	//System.out.println("...."+rejectreason + "...");
        	if(mytype.equals("yes")) {
        		workpageStatus ws=new workpageStatus();
        		//ws.setWorkid(workid);
        		ws.setWorkidstatus(5);
        		//ws.setRejectreason(rejectreason);
        		int flag=wpd.updateWorkpagestatus1(workid,ws);
        		if(flag>0){
        			out.print(flag);
        			}
        	}
        	else if(mytype.equals("not")) {
        		workpageStatus ws=new workpageStatus();
        		//ws.setWorkid(workid);
        		ws.setWorkidstatus(6);
        		//ws.setRejectreason(rejectreason);
        		int flag=wpd.updateWorkpagestatus1(workid,ws);
        		if(flag>0){
        			out.print(flag);
        			}
        	}
        }
        //检尺员查看信息中心反馈审核信息
        else if("inyardsee".equals(action))
        {
        	String str=request.getParameter("workid");
    		str=str.replace("'", "");
    		double workid=Double.parseDouble(str);
        	sql="select workid,cutNum,yarddate,cutSite,carNumber,yard,surveyor,toltree,tolstere,section from inyard where workid="+workid+"";
			inyard inyard=trd.findInSingle(sql);
			sql="select workid,treetype,tlong,tradius,num,tvolume from tree where workid="+workid+"";
			List<tree> tree=trd.findTree(sql);
			sql="SELECT reject_reason FROM workpage_status join workpage on workpage_status.workid=workpage.workid WHERE workpage_status.workid="+workid+"";
    		workpageStatus workpageStatus=wpd.findWapStatus(sql);
			request.setAttribute("inyard", inyard);
			request.setAttribute("tree", tree);
			request.setAttribute("workpageStatus", workpageStatus);
			request.getRequestDispatcher("treeinUpdateInyard.jsp").forward(request, response);
			//request.getRequestDispatcher("treeinUpdateInyinfo.jsp").forward(request, response);
        }
        //信息中心查看检尺员修改的进场信息
        else if("inyardinfosee".equals(action))
        {
        	String str=request.getParameter("workid");
    		str=str.replace("'", "");
    		double workid=Double.parseDouble(str);
        	sql="select workid,cutNum,yarddate,cutSite,carNumber,yard,surveyor,toltree,tolstere,section from inyard where workid="+workid+"";
			inyard inyard=trd.findInSingle(sql);
			sql="select workid,treetype,tlong,tradius,num,tvolume from tree where workid="+workid+"";
			List<tree> tree=trd.findTree(sql);
			sql="SELECT reject_reason FROM workpage_status join workpage on workpage_status.workid=workpage.workid WHERE workpage_status.workid="+workid+"";
    		workpageStatus workpageStatus=wpd.findWapStatus(sql);
			request.setAttribute("inyard", inyard);
			request.setAttribute("tree", tree);
			request.setAttribute("workpageStatus", workpageStatus);
			//request.getRequestDispatcher("treeinUpdateInyard.jsp").forward(request, response);
			request.getRequestDispatcher("treeinUpdateInyinfo.jsp").forward(request, response);
        }
        //库存盘点
        else if("findhcpd".equals(action)) {
        	String yeart=request.getParameter("year");
            sql="SELECT YEAR(outyard.yarddate) as yeart,outyard.yard from outyard join inyard on outyard.yard=inyard.yard WHERE YEAR(outyard.yarddate)="+yeart+" GROUP BY outyard.yard";
            List<yardInventory> cw=trd.findhyard(sql);
        	//System.out.println("...."+cw.size() + "...");
        	mapper.writeValue(response.getWriter(), cw);
        }
        else if("findyardList".equals(action)) {
        	String yardname=request.getParameter("yardname");
        	String yeart=request.getParameter("yeart");
        	yardname=yardname.replace("'", "");
        	yeart=yeart.replace("'", "");
    		sql="SELECT yard,year(yarddate) as yeart,month(yarddate) as montht,sum(tolstere) as tolstere from inyard where yard='"+yardname+"' and  year(yarddate)='"+yeart+"' GROUP BY month(yarddate)";
    		List<yardInventory> inyardT=trd.findyardList(sql);
    		sql="SELECT yard,year(yarddate) as yeart,month(yarddate) as montht,sum(tolstere) as tolstere from outyard where yard='"+yardname+"' and  year(yarddate)='"+yeart+"' GROUP BY month(yarddate)";
    		List<yardInventory> outyardT=trd.findyardList(sql);
    		sql="SELECT yard,year(yarddate) as yeart from inyard where yard='"+yardname+"' and  year(yarddate)='"+yeart+"' GROUP BY month(yarddate)";
    		yardInventory yardyear=trd.findhcpd(sql);
    		//System.out.print(outyardT);
    		request.setAttribute("inyardT", inyardT);
			request.setAttribute("outyardT", outyardT);
			request.setAttribute("yardyear", yardyear);
			request.getRequestDispatcher("yardInventory.jsp").forward(request, response);
        }
        
        //通过销售工单号查询销售工单信息
        else if(action.equals("searchoutyard")){
     	   double workid=Double.parseDouble(request.getParameter("workid"));
             	if (workid==0)
             	{
             		return;
             	}
             sql="SELECT contractnum,workid,sale_callout_orderid,yard,section,batchNum,yarddate,carNumber,surveyor from outyard WHERE workid ="+workid+"";
             	List<outyard> cw=trd.findOutyardC(sql);
             	mapper.writeValue(response.getWriter(), cw);
             }
            //结束
     	//跳转检尺费页面
         else if(action.equals("findoutyardCostTable")){
         	String str=request.getParameter("workid");
         	str=str.replace("'", "");
         	double workid=Double.parseDouble(str);
         	sql="select workid,contractnum,sale_callout_orderid,yard,section,batchNum,yarddate,carNumber,surveyor from outyard where workid="+workid+"";
     		outyard outyard=trd.findOutSingle1(sql);
     		
     		//sql="select workid,treetype,tlong,tradius,num,tvolume from tree where workid="+workid+"";
     		sql="select workid,treetype,tlong,tradius,num,tvolume from treeout where workid="+workid+"";
     		//List<tree> tree=trd.findTree(sql);
     		List<surveyorFee> surveyorFee=trd.findSurveyorFee(sql);
         	request.setAttribute("workid", outyard);
     		//request.setAttribute("tree", tree);
         	request.setAttribute("surveyorFee", surveyorFee);
         	request.getRequestDispatcher("outyardCostTable.jsp").forward(request, response);	
             }
      //保存检尺费信息
         else if(action.equals("saveSurveyorFee")) {
         	String rebate = request.getParameter("newtree");
         	int id=Integer.parseInt(request.getParameter("id"));
         	System.out.println("...." + id + "...");
         	JSONObject jb = JSONObject.fromObject(rebate);            	

         	double workid=Double.parseDouble(request.getParameter("workid"));
         	double surveyorFee=Double.parseDouble(request.getParameter("surveyorFee"));
         	for(int i=0;i<id;i++) {
         		JSONArray s=jb.getJSONArray(String.valueOf(i));
         		surveyorFee cp = new surveyorFee();
           
             	cp.setUnitprice(Double.parseDouble(s.getString(0)));
             	cp.setPrice(Double.parseDouble(s.getString(1)));
             	cp.setWorkid(workid);
             	cp.setSurveyorFee(surveyorFee);

             	int flag=trd.saveSurveyorFee(cp);
             	
             	out.print(flag);
         	}
         }
        //显示在salesmanThree。jsp页面检尺费表格
         else if(action.equals("surveyorFeeList")) {
        	 sql="SELECT s.workid,s.unitprice,o.tolstere,s.surveyorFee FROM surveyor_fee as s JOIN outyard as o WHERE s.workid=o.workid GROUP BY s.workid";
        	 List<surveyorFee> cw=trd.findSurveyorFeeList(sql);
          	mapper.writeValue(response.getWriter(), cw);
         }

	}

}
