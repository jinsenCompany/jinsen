package jinshen.action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
/*录入进仓木材信息和出场木材信息*/
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.fasterxml.jackson.databind.ObjectMapper;

import jinshen.bean.codejson;
import jinshen.bean.compareVolume;
import jinshen.bean.customer;
import jinshen.bean.cutnum;
import jinshen.bean.inyard;
import jinshen.bean.inyardStatus;
import jinshen.bean.managesdatecard;
import jinshen.bean.outyard;
import jinshen.bean.saleCalloutOrder;
import jinshen.bean.salecontract;
import jinshen.bean.surveyorFee;
import jinshen.bean.tree;
import jinshen.bean.treefile;
import jinshen.bean.volume;
import jinshen.bean.workpage;
import jinshen.bean.workpageStatus;
import jinshen.bean.yardInventory;
import jinshen.dao.cutnumDao;
import jinshen.dao.salemanDao;
import jinshen.dao.treeDao;
import jinshen.dao.volumeDao;
import jinshen.dao.workpageDao;
import jinshen.daoimpl.cutnumDaoImpl;
import jinshen.daoimpl.salemanDaoImpl;
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
        salemanDao sd=new salemanDaoImpl();
        cutnumDao cnd=new cutnumDaoImpl();
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
        	double workid=Double.parseDouble(request.getParameter("workid"));
        	String yarddate=request.getParameter("yarddate");
        	String carNumber=request.getParameter("carnumber");
        	String yard=request.getParameter("yard");
        	String cutnum=request.getParameter("cutnum");        			
        	String surveyor=request.getParameter("surveyor");
        	double toltree=Double.parseDouble(request.getParameter("toltree"));
        	double tolstere=Double.parseDouble(request.getParameter("tolstere"));
        	String contractnum=request.getParameter("contractnum");//销售合同
        	String saleCalloutOrder=request.getParameter("saleCalloutOrder");
        	String section=request.getParameter("section");
        	String rebate = request.getParameter("newtree");
        	int id=Integer.parseInt(request.getParameter("id"));
        	JSONObject jb = JSONObject.fromObject(rebate);
        	//System.out.print(jb);
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	Date yardtime=null;
        	try {
        		yardtime = new java.sql.Timestamp(format.parse(yarddate).getTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	outyard cp = new outyard();
        	cp.setWorkid(workid);
			cp.setYarddate(yardtime);
			cp.setCarNumber(carNumber);
			cp.setYard(yard);
			cp.setCutnum(cutnum);
			cp.setSurveyor(surveyor);
			cp.setTolstere(tolstere);
			cp.setToltree(toltree);
			cp.setContractnum(contractnum);
			cp.setSaleCalloutorderid(saleCalloutOrder);
			cp.setSection(section);
			int flag=trd.addOutyard(cp);
//			sql="SELECT workid from outyard WHERE contractnum='"+contractnum+"' and sale_callout_orderid='"+saleCalloutOrder+"' and yarddate='"+yarddate+"' and carNumber='"+carNumber+"'";
//			outyard w=trd.findworkid(sql);
//			double workid=w.getWorkid();
			int flagt=0;
			if(flag>0) {
				int i=0;
				/*if(jb.has("0"))
				{
					i=0;
				}
				else if(jb.has("1"))
				{
					i=1;
				}
				else if(jb.has("2")) {
					i=2;
				}
				else if(jb.has("3")) {
					i=3;
				}*/
				JSONArray s=null;
        	for(int j=i;j<id;j++)
        	{ 
        		if(jb.has(String.valueOf(j))) {
		        	//System.out.println(String.valueOf(j));
		        	s=jb.getJSONArray(String.valueOf(j));
		        }
		        else if(!jb.has(String.valueOf(j)) && jb.has(String.valueOf(j+1))) {
		        	continue;
		        }
		        else if(!jb.has(String.valueOf(j))) {
		        	//s=jb.getJSONArray(String.valueOf(j+2));
		        	continue;
		        }
        		//JSONArray s=jb.getJSONArray(String.valueOf(j));
        		tree t=new tree();
        		t.setWorkid(workid);
        		t.setTreetype(s.getString(0));
            	t.setTlong(Double.parseDouble(s.getString(1)));
            	t.setTradius(Double.parseDouble(s.getString(2)));
            	t.setNum(Double.parseDouble(s.getString(3)));
            	t.setTvolume(Double.parseDouble(s.getString(4)));
            	//材积单价还没有
            	t.setUnitprice(1);
            	t.setTotalnum(1);
            	String treetype=s.getString(0);
            	double tradius=Double.parseDouble(s.getString(2));
            	//double tlong=Double.parseDouble(s.getString(1));
            	if(treetype.equals("杉木") && tradius>0 && tradius<13)
	            {
	            	t.setTreeid(1);//衫小径
	            }
	            else if (treetype.equals("杉木") && tradius>13)
	            {
	            	t.setTreeid(2);//衫原木
	            }
	            else if (treetype.equals("松木") && tradius>0 && tradius<13)
	            {
	            	t.setTreeid(3);//松小径
	            }
	            else if (treetype.equals("松木") && tradius>13)
	            {
	            	t.setTreeid(4);//松原木
	            }
	            else if (treetype.equals("杂木") && tradius>0 && tradius<13)
	            {
	            	t.setTreeid(5);//杂小径
	            }
	            else if (treetype.equals("杂木") && tradius>13)
	            {
	            	t.setTreeid(6);//杂原木
	            }
	            else {
	            	t.setTreeid(7);//其他
	            }
            	flagt=trd.addTreeout(t);
            	//out.print(flagt);
        		}
        	if(flagt>0) {
        		outyard cpS = new outyard();
        		cpS.setWorkid(workid);
        		cpS.setOutStatus(0);
        		int flagto=trd.addTreeoutStatus(cpS);
        		out.print(flagto);
        	}
        	else 
        		{out.print(flagt);}
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
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	Date yardtime=null;
        	try {
        		yardtime = new java.sql.Timestamp(format.parse(yarddate).getTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
        	System.out.println("jb:"+jb);
        	System.out.println("id:"+id);
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	Date yardtime=null;
        	try {
        		yardtime = new java.sql.Timestamp(format.parse(yarddate).getTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	String treetype="";
        	double tradius=0;
		   //保存材积
			int flagt=0;
			int i=0;
			/*if(jb.has("0"))
			{
				i=0;
			}
			else if(jb.has("1"))
			{
				i=1;
			}
			else if(jb.has("2")) {
				i=2;
			}
			else if(jb.has("3")) {
				i=3;
			}
			else if(jb.has("4")) {
				i=4;
			}
			else if(jb.has("5")) {
				i=5;
			}*/
			JSONArray s=null;
			
    	   for(int j=i;j<id;j++)
		        {  
    		        /*if(jb.has(String.valueOf(j))) {
    		        	System.out.println("j:"+String.valueOf(j));
    		        	s=jb.getJSONArray(String.valueOf(j));
    		        }
    		        else if(jb.has(String.valueOf(j+1))) {
    		        	s=jb.getJSONArray(String.valueOf(j+1));
    		        }
    		        else {
    		        	s=jb.getJSONArray(String.valueOf(j+2));
    		        	System.out.println("j=2:"+String.valueOf(j+2));
    		        }*/
    		       if(jb.has(String.valueOf(j))) {
		        	//System.out.println("j:"+String.valueOf(j));
		        	s=jb.getJSONArray(String.valueOf(j));
		           }
		           else if(!jb.has(String.valueOf(j)) && jb.has(String.valueOf(j+1))) {
		        	//s=jb.getJSONArray(String.valueOf(j+1));
		        	   continue;
		        	//System.out.println("j=2:"+String.valueOf(j+2));
		           }
		           else if(!jb.has(String.valueOf(j))){
		        	//s=jb.getJSONArray(String.valueOf(j+2));
		        	//System.out.println("j=2:"+String.valueOf(j+2));
		        	   continue;
		           }
		        	//s=jb.getJSONArray(String.valueOf(j));
		        	//System.out.println(String.valueOf(j));
		        	tree t=new tree();
		        	t.setWorkid(workid);
		        	t.setTreetype(s.getString(0));
		        	treetype=s.getString(0);
		        	//System.out.println(s.getString(0));
		        	
		            t.setTlong(Double.parseDouble(s.getString(1)));
		            t.setTradius(Double.parseDouble(s.getString(2)));
		            t.setNum(Double.parseDouble(s.getString(3)));
		            t.setTvolume(Double.parseDouble(s.getString(4)));
		            
		            t.setUnitprice(1);//默认单价为1
		            t.setTotalnum(1);
		            tradius=Double.parseDouble(s.getString(2));
		            double tlong=Double.parseDouble(s.getString(1));
		            if(treetype.equals("杉木") && tlong>=1.5 && tradius>0 && tradius<13)
		            {
		            	t.setTreeid(1);//杉小径
		            }
		            else if (treetype.equals("杉木") && tlong>=1.5 && tradius>13)
		            {
		            	t.setTreeid(2);//杉原木
		            }
		            else if (treetype.equals("杉木") && tlong<1.5)
		            {
		            	t.setTreeid(8);//杉短材
		            }
		            else if (treetype.equals("松木") && tradius>0 && tradius<13)
		            {
		            	t.setTreeid(3);//松小径
		            }
		            else if (treetype.equals("松木") && tradius>13)
		            {
		            	t.setTreeid(4);//松原木
		            }
		            else if (treetype.equals("杂木") && tradius>0 && tradius<13)
		            {
		            	t.setTreeid(5);//杂小径
		            }
		            else if (treetype.equals("杂木") && tradius>13)
		            {
		            	t.setTreeid(6);//杂原木
		            }
		            else {
		            	t.setTreeid(7);//其他
		            }
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
			//System.out.println(flag);
			if(flag>0)
			{
				workpageStatus ws=new workpageStatus();
				ws.setWorkidstatus(8);//检尺员填写木材信息工单状态为7,8审核通过
				int wpp=wpd.updateWorkpagestatus1(workid,ws);
				/*if(wpp>0) {
					out.print("保存成功");
				}
				else {
					out.write("保存失败");
				}*/
				//System.out.println(workid);
				out.print(wpp);
			}
			else {
				out.print(flag);	
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
				String treetype="";
	        	double tradius=0;
	        	sql = "select count(*) from tree where workid='"+workid+"'";
				double ff=trd.findcount(sql);
				if(ff>=1) {
					sql="DELETE from tree WHERE workid='"+workid+"'";
					int j=wpd.delWorkPage(sql);//删除工单数据
					if(j>0)
					{
				for(int i=0;i<id;i++)
		        {
		        	JSONArray s=jb.getJSONArray(String.valueOf(i));
		        	System.out.println(s);
		        	tree t=new tree();
		        	t.setWorkid(workid);
		        	t.setTreetype(s.getString(0));
		            t.setTlong(Double.parseDouble(s.getString(1)));
		            t.setTradius(Double.parseDouble(s.getString(2)));
		            t.setNum(Double.parseDouble(s.getString(3)));
		            t.setTvolume(Double.parseDouble(s.getString(4)));
		            
		            t.setUnitprice(1);
		            t.setTotalnum(1);
		            tradius=Double.parseDouble(s.getString(2));
		            double tlong=Double.parseDouble(s.getString(1));
		            if(treetype.equals("杉木") && tlong>=1.5 && tradius>0 && tradius<13)
		            {
		            	t.setTreeid(1);//杉小径
		            }
		            else if (treetype.equals("杉木") && tlong>=1.5 && tradius>13)
		            {
		            	t.setTreeid(2);//杉原木
		            }
		            else if (treetype.equals("杉木") && tlong<1.5)
		            {
		            	t.setTreeid(8);//杉短材
		            }
		            else if (treetype.equals("松木") && tradius>0 && tradius<13)
		            {
		            	t.setTreeid(3);//松小径
		            }
		            else if (treetype.equals("松木") && tradius>13)
		            {
		            	t.setTreeid(4);//松原木
		            }
		            else if (treetype.equals("杂木") && tradius>0 && tradius<13)
		            {
		            	t.setTreeid(5);//杂小径
		            }
		            else if (treetype.equals("杂木") && tradius>13)
		            {
		            	t.setTreeid(6);//杂原木
		            }
		            else {
		            	t.setTreeid(7);//其他
		            }
		            flagt=trd.addTree(t);//更新检尺数据
		        }
		     }
				}
					else
					{
						for(int i=0;i<id;i++)
				        {
				        	JSONArray s=jb.getJSONArray(String.valueOf(i));
				        	System.out.println(s);
				        	tree t=new tree();
				        	t.setWorkid(workid);
				        	t.setTreetype(s.getString(0));
				            t.setTlong(Double.parseDouble(s.getString(1)));
				            t.setTradius(Double.parseDouble(s.getString(2)));
				            t.setNum(Double.parseDouble(s.getString(3)));
				            t.setTvolume(Double.parseDouble(s.getString(4)));
				            
				            t.setUnitprice(1);
				            t.setTotalnum(1);
				            tradius=Double.parseDouble(s.getString(2));
				            double tlong=Double.parseDouble(s.getString(1));
				            if(treetype.equals("杉木") && tlong>=1.5 && tradius>0 && tradius<13)
				            {
				            	t.setTreeid(1);//杉小径
				            }
				            else if (treetype.equals("杉木") && tlong>=1.5 && tradius>13)
				            {
				            	t.setTreeid(2);//杉原木
				            }
				            else if (treetype.equals("杉木") && tlong<1.5)
				            {
				            	t.setTreeid(8);//杉短材
				            }
				            else if (treetype.equals("松木") && tradius>0 && tradius<13)
				            {
				            	t.setTreeid(3);//松小径
				            }
				            else if (treetype.equals("松木") && tradius>13)
				            {
				            	t.setTreeid(4);//松原木
				            }
				            else if (treetype.equals("杂木") && tradius>0 && tradius<13)
				            {
				            	t.setTreeid(5);//杂小径
				            }
				            else if (treetype.equals("杂木") && tradius>13)
				            {
				            	t.setTreeid(6);//杂原木
				            }
				            else {
				            	t.setTreeid(7);//其他
				            }
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
				ws.setWorkidstatus(10);//检尺员填写木材信息工单状态为10
				int wpp=wpd.updateWorkpagestatus1(workid,ws);
				/*if(wpp>0) {
					out.print("工单状态保存成功");
				}
				else {
					out.write("工单状态保存失败");
				}*/
				//System.out.println(workid);
				out.print(wpp);
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
			sql="select * from workpage where workid="+workid+"";
       		workpage workpage=wpd.findCodeSingle(sql);
       		sql="SELECT treein_file from treein_file WHERE workid='"+str+"'";
        	treefile treef=wpd.findtreefile(sql);
        	String tfile=String.valueOf(treef.getTreefile());
        	System.out.print(tfile);
        	if(!tfile.equals("null"))
        	{
        		tfile = tfile.substring(tfile.lastIndexOf("/") + 1);
        	}
        	else
        	{
        		tfile="";
        	}
        	request.setAttribute("treef", treef);
    		request.setAttribute("tfile", tfile);
       		request.setAttribute("workpage", workpage);
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
			sql="select * from workpage where workid="+workid+"";
       		workpage workpage=wpd.findCodeSingle(sql);
       		sql="SELECT treein_file from treein_file WHERE workid='"+str+"'";
        	treefile treef=wpd.findtreefile(sql);
        	String tfile=String.valueOf(treef.getTreefile());
        	System.out.print(tfile);
        	if(!tfile.equals("null"))
        	{
        		tfile = tfile.substring(tfile.lastIndexOf("/") + 1);
        	}
        	else
        	{
        		tfile="";
        	}
        	request.setAttribute("treef", treef);
    		request.setAttribute("tfile", tfile);
       		request.setAttribute("workpage", workpage);
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
        		sql="select i.workid,i.cutNum,i.yarddate,i.cutSite,i.carNumber,i.yard,i.surveyor,i.section from inyard as i join workpage_status as w on i.workid=w.workid where w.workid_status=9";
        		List<inyard> work=trd.findinyards(sql);
                //System.out.print(work.get(8));
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//通过
        	else if(mytype.equals("pass"))
        	{
        		sql="select i.workid,i.cutNum,i.yarddate,i.cutSite,i.carNumber,i.yard,i.surveyor,i.section from inyard as i join workpage_status as w on i.workid=w.workid where w.workid_status=8";
        		List<inyard> worki=trd.findinyards(sql);
            	mapper.writeValue(response.getWriter(), worki);
        	}
        	//未审核
        	else if(mytype.equals("notshenhe"))
        	{
        		sql="select i.workid,i.cutNum,i.yarddate,i.cutSite,i.carNumber,i.yard,i.surveyor,i.section from inyard as i join workpage_status as w on i.workid=w.workid where w.workid_status=7";
        		List<inyard> work=trd.findinyards(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//审核后补充材料
        	else if(mytype.equals("buchong"))
        	{
        		sql="select i.workid,i.cutNum,i.yarddate,i.cutSite,i.carNumber,i.yard,i.surveyor,i.section from inyard as i join workpage_status as w on i.workid=w.workid where w.workid_status=10";
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
        		ws.setWorkidstatus(8);//信息中心审核木材通过
        		ws.setRejectreason(rejectreason);
        		int flag=wpd.updateWorkpagestatus(workid,ws);
        		if(flag>0){
        			out.print(flag);
        			}
        	}
        	else if(mytype.equals("not")) {
        		workpageStatus ws=new workpageStatus();
        		//ws.setWorkid(workid);
        		ws.setWorkidstatus(9);//信息中心审核木材不通古
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
        		ws.setWorkidstatus(8);
        		//ws.setRejectreason(rejectreason);
        		int flag=wpd.updateWorkpagestatus1(workid,ws);
        		if(flag>0){
        			out.print(flag);
        			}
        	}
        	else if(mytype.equals("not")) {
        		workpageStatus ws=new workpageStatus();
        		//ws.setWorkid(workid);
        		ws.setWorkidstatus(9);
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
    		sql="select * from workpage where workid="+workid+"";
       		workpage workpage=wpd.findCodeSingle(sql);
       		sql="SELECT treein_file from treein_file WHERE workid='"+str+"'";
        	treefile treef=wpd.findtreefile(sql);
        	String tfile=String.valueOf(treef.getTreefile());
        	//System.out.print(tfile);
        	if(!tfile.equals("null"))
        	{
        		tfile = tfile.substring(tfile.lastIndexOf("/") + 1);
        	}
        	else
        	{
        		tfile="";
        	}
        	request.setAttribute("treef", treef);
    		request.setAttribute("tfile", tfile);
       		request.setAttribute("workpage", workpage);
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
    		sql="select * from workpage where workid="+workid+"";
       		workpage workpage=wpd.findCodeSingle(sql);
       		
       		sql="SELECT treein_file from treein_file WHERE workid='"+str+"'";
        	treefile treef=wpd.findtreefile(sql);
        	String tfile=String.valueOf(treef.getTreefile());
        	System.out.print(tfile);
        	if(!tfile.equals("null"))
        	{
        		tfile = tfile.substring(tfile.lastIndexOf("/") + 1);
        	}
        	else
        	{
        		tfile="";
        	}
        	request.setAttribute("treef", treef);
    		request.setAttribute("tfile", tfile);
       		request.setAttribute("workpage", workpage);
			request.setAttribute("inyard", inyard);
			request.setAttribute("tree", tree);
			request.setAttribute("workpageStatus", workpageStatus);
			//request.getRequestDispatcher("treeinUpdateInyard.jsp").forward(request, response);
			request.getRequestDispatcher("treeinUpdateInyinfo.jsp").forward(request, response);
        }
        //库存盘点
        else if("findhcpd".equals(action)) {
        	String yeart=request.getParameter("year");
            sql="SELECT YEAR(outyard.yarddate) as yeart,outyard.yard from outyard join inyard on outyard.yard=inyard.yard WHERE YEAR(outyard.yarddate)="+yeart+" GROUP BY outyard.yard,yeart";
            List<yardInventory> cw=trd.findhyard(sql);
        	//System.out.println("...."+cw.size() + "...");
        	mapper.writeValue(response.getWriter(), cw);
        }
        else if("findyardList".equals(action)) {
        	String yardname=request.getParameter("yardname");
        	String yeart=request.getParameter("yeart");
        	yardname=yardname.replace("'", "");
        	yeart=yeart.replace("'", "");
    		sql="SELECT yard,year(yarddate) as yeart,month(yarddate) as montht,sum(tolstere) as tolstere from inyard where yard='"+yardname+"' and  year(yarddate)='"+yeart+"' GROUP BY yard,yeart,montht";
    		List<yardInventory> inyardT=trd.findyardList(sql);
    		sql="SELECT yard,year(yarddate) as yeart,month(yarddate) as montht,sum(tolstere) as tolstere from outyard where yard='"+yardname+"' and  year(yarddate)='"+yeart+"' GROUP BY yard,yeart,montht";
    		List<yardInventory> outyardT=trd.findyardList(sql);
    		sql="SELECT yard,year(yarddate) as yeart from inyard where yard='"+yardname+"' and  year(yarddate)='"+yeart+"' GROUP BY yard,yeart";
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
             sql="SELECT contractnum,workid,sale_callout_orderid,yard,section,cutnum,yarddate,carNumber,surveyor from outyard WHERE workid ="+workid+"";
             	List<outyard> cw=trd.findOutyardC(sql);
             	mapper.writeValue(response.getWriter(), cw);
             }
            //结束
     	//跳转检尺费页面
         else if(action.equals("findoutyardCostTable")){
         	String str=request.getParameter("workid");
         	str=str.replace("'", "");
         	double workid=Double.parseDouble(str);
         	sql="select workid,contractnum,sale_callout_orderid,yard,section,cutnum,yarddate,carNumber,surveyor from outyard where workid="+workid+"";
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
        	 sql="SELECT s.workid,s.unitprice,o.tolstere,s.surveyorFee FROM surveyor_fee as s JOIN outyard as o WHERE s.workid=o.workid GROUP BY s.workid,s.unitprice,o.tolstere,s.surveyorFee";
        	 List<surveyorFee> cw=trd.findSurveyorFeeList(sql);
          	mapper.writeValue(response.getWriter(), cw);
         }
        //录入出仓坚持数据保存后跳转至详情页面
         else if("seetreeout".equals(action)) {
        	 String str=request.getParameter("saleCalloutOrder");
        	 double workid=Double.parseDouble(request.getParameter("workid"));
         	str=str.replace("'", "");
         	sql="select sale_callout_order_id,contractnum,yard,section,demander,Paymentamount,totalnum,Signer,callidtime,creattime from sale_callout_orderid WHERE sale_callout_order_id='"+str+"'";
         	saleCalloutOrder saleCalloutOrder=sd.findsaleorder(sql);
         	String contractnum=saleCalloutOrder.getContractnum();
         	sql="SELECT provider,marknumber from sale_contract WHERE contractnum='"+contractnum+"'";
         	salecontract contractPrivoder=sd.findcontractPrivode(sql);
         	sql="SELECT company,shipaddress from customer WHERE contractnum='"+contractnum+"'";
         	customer compShip=sd.findcutnumAddres(sql);
         	sql="select * from outyard where sale_callout_orderid='"+str+"' and workid='"+workid+"'";
			outyard outyard=trd.findOutSingle(sql);
			//double workid=outyard.getWorkid();
			String cutnum=String.valueOf(outyard.getCutnum());
			sql="SELECT * from cutnum WHERE cutnum='"+cutnum+"'";
        	cutnum cutn = cnd.findCodeSingle(sql);
			sql="select * from treeout where workid='"+workid+"'";
			List<tree> tree=trd.findTree(sql);
			request.setAttribute("workid", workid);
			request.setAttribute("outyard", outyard);
			request.setAttribute("tree", tree);
         	request.setAttribute("saleCalloutOrder", saleCalloutOrder);
         	request.setAttribute("contractPrivoder", contractPrivoder);
         	request.setAttribute("compShip", compShip);
         	request.getRequestDispatcher("treeout3.jsp").forward(request, response);
			BigDecimal bigDecimal = new BigDecimal(workid);  //不显示科学计数法
	        String result = bigDecimal.toString();
			System.out.print("workid:"+result);
         	Date d1 = null;
			try {
				d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(String.valueOf(outyard.getYarddate()));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}//定义起始日期
        	SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy");
        	SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
        	SimpleDateFormat sdf2= new SimpleDateFormat("dd");
        	SimpleDateFormat sdf3= new SimpleDateFormat("HH");
        	String str0 = sdf0.format(d1);//年
        	String str1 = sdf1.format(d1);//月
        	String str2 = sdf2.format(d1);//日
        	String str3 = sdf3.format(d1);//时
         	
         	//FileInputStream file = new FileInputStream(new File("f:\\treeoutYe.xls"));
        	FileInputStream file = new FileInputStream(new File("C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 9.0\\webapps\\jinsenZ\\WEB-INF\\PrintOutTree\\saleCallout.xls"));
			HSSFWorkbook workbook =new HSSFWorkbook(file);
      //   	HSSFSheet sheet = workbook.createSheet("Sample sheet445");
 	        HSSFSheet sheet = workbook.getSheet("Sheet1");       	
 	        int a=sheet.getFirstRowNum()+80;//从80列开始
 	        int b=sheet.getLastRowNum();
        	try {
        				System.out.print("已经创建好文件，进入到sheet1");
        				System.out.print(b);
                        Row row = sheet.getRow(80);//获取第80行
//        				// 这个判断很必要  确保下面cell操作顺利执行
        		          if(row == null){
        		              row = sheet.createRow(80);
        		          }
        				 for (int j=a;j<b;j++)
        				 {
        					 if(sheet.getRow(j)!=null)
        					 {
        					 sheet.removeRow(sheet.getRow(j));
        					 //sheet.setSelected(true);
        					 sheet.setActive(true);
        					 }
        				 }
        				    //Row row = sheet.createRow(80);
        	                Cell cell = row.createCell(16);//第16列
        	                cell.setCellValue((String)saleCalloutOrder.getSaleCalloutOrder());//调令
        	                
							Cell cell1 = row.createCell(17);
							cell1.setCellValue((String)saleCalloutOrder.getContractnum());
							
						
							Cell cell2 = row.createCell(18);
							cell2.setCellValue((String)saleCalloutOrder.getDemander());
							
							  Cell cell3 = row.createCell(19);
							  cell3.setCellValue((String)contractPrivoder.getProvider());
							
							  
							  Cell cell4 = row.createCell(20);
							  cell4.setCellValue((String)outyard.getYard());
							 
							 Cell cell5 = row.createCell(21);
							  cell5.setCellValue((String)compShip.getShipaddress());//地址
							
							  Cell cell6 = row.createCell(22);
							  cell6.setCellValue(String.valueOf(outyard.getYarddate()));//时间
							  //System.out.print(treeout.getCallidtime());
							  
							  Cell cell7 = row.createCell(23);
							  cell7.setCellValue(outyard.getCarNumber());
//							  
							  Cell cell8 = row.createCell(24);
							  cell8.setCellValue(outyard.getToltree());
						
							  Cell cell9 = row.createCell(25);
							  cell9.setCellValue(outyard.getTolstere());
//							  
							  Cell cell10 = row.createCell(26);
							  cell10.setCellValue(result);
							  
							  Cell cell11 = row.createCell(27);
							  cell11.setCellValue(str0);
							  
							  Cell cell12 = row.createCell(28);
							  cell12.setCellValue(str1);
						
							  Cell cell13 = row.createCell(29);
							  cell13.setCellValue(str2);
							  
							  Cell cell14 = row.createCell(30);
							  cell14.setCellValue(str3);
							  
							  Cell cell15 = row.createCell(31);
							  cell15.setCellValue(outyard.getSurveyor());
//							  
							  Cell cell16 = row.createCell(32);
							  cell16.setCellValue(String.valueOf(cutn.getProjectPackageName()));
							  
							  Cell cell17 = row.createCell(33);
							  cell17.setCellValue(String.valueOf(cutn.getCutaddress()));
							  
							  Cell cell18 = row.createCell(34);
							  cell18.setCellValue(String.valueOf(cutn.getCutvillage()));
							  
							  Cell cell19 = row.createCell(35);
							  cell19.setCellValue(String.valueOf(cutn.getQuartel()));
							  
							  Cell cell20 = row.createCell(36);
							  cell20.setCellValue(String.valueOf(cutn.getLargeblock()));//大班
							  
							  Cell cell21 = row.createCell(37);
							  cell21.setCellValue(String.valueOf(cutn.getSmallblock()));//小班
							  
							  Cell cell22 = row.createCell(38);
							  cell22.setCellValue(String.valueOf(cutnum));//采伐证
							  
								 for (int i=0;i<tree.size();i++) {  
									Row rowx = sheet.createRow(i+82);//从82行开始
				        	            Cell cella = rowx.createCell(16);//第16列
				        	            cella.setCellValue((String) tree.get(i).getTreetype());
				        	            Cell cellb = rowx.createCell(17);
				        	            cellb.setCellValue((double)tree.get(i).getTlong());
				        	            Cell cellc = rowx.createCell(18);
				        	            cellc.setCellValue((double) tree.get(i).getTradius());
				        	            Cell   celld = rowx.createCell(19);
				        	            celld.setCellValue((double) tree.get(i).getNum());
				        	            Cell celle = rowx.createCell(20);
				        	            celle.setCellValue((double) tree.get(i).getTvolume());
								 }
								 sheet.setForceFormulaRecalculation(true);
        	     file.close();
        		
        	    //FileOutputStream out1=new FileOutputStream(new File("f:\\treeoutYe.xls"));
        	    FileOutputStream out1=new FileOutputStream(new File("C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 9.0\\webapps\\jinsenZ\\WEB-INF\\PrintOutTree\\treeoutYe.xls"));
        	    workbook.write(out1);
				/*
				 * sheet = workbook.getSheetAt(0); Iterator<Row> rowIte = sheet.iterator();
				 * while(rowIte.hasNext()){ rowIte.next(); rowIte.remove(); }
				 * System.out.println("已删除完毕");
				 */
        	    file.close();
        	    out1.close();
        	    workbook.close();
        	    System.out.println("Excel written successfully..");
        	   
        	} catch (FileNotFoundException e) {
        	    e.printStackTrace();
        	} catch (IOException e) {
        	    e.printStackTrace();
        	}
         }
        //选择已录入检尺数据的输出工单
         else if("treeoutCodepage".equals(action)) {
        	 sql="SELECT o.yarddate,o.workid,s.provider,s.demander,o.contractnum,o.sale_callout_orderid,o.yard,o.toltree,o.tolstere FROM outyard as o join sale_contract as s on o.contractnum=s.contractnum ORDER BY o.yarddate desc";
        	 List<outyard> cw=trd.findtreeoutCode(sql);
           	mapper.writeValue(response.getWriter(), cw);
         }
      //打印销售码单
         else if("seetreeout1".equals(action)) {
        	 String str=request.getParameter("saleCalloutorderid");
        	 String yarddate=request.getParameter("yarddate");
        	 System.out.print(yarddate);
         	str=str.replace("'", "");
         	yarddate=yarddate.replace("'", "");
         	sql="select sale_callout_order_id,contractnum,yard,section,demander,Paymentamount,totalnum,Signer,callidtime,creattime from sale_callout_orderid WHERE sale_callout_order_id='"+str+"'";
         	saleCalloutOrder saleCalloutOrder=sd.findsaleorder(sql);
         	String contractnum=saleCalloutOrder.getContractnum();
         	sql="SELECT provider,marknumber from sale_contract WHERE contractnum='"+contractnum+"'";
         	salecontract contractPrivoder=sd.findcontractPrivode(sql);
         	sql="SELECT company,shipaddress from customer WHERE contractnum='"+contractnum+"'";
         	customer compShip=sd.findcutnumAddres(sql);
         	sql="select * from outyard where sale_callout_orderid='"+str+"' and yarddate='"+yarddate+"'";
			outyard outyard=trd.findOutSingle(sql);
			String workid=String.valueOf(outyard.getWorkid());
			BigDecimal bigDecimal = new BigDecimal(workid);  //不显示科学计数法
	        String result = bigDecimal.toString();
			System.out.print("workid:"+result);
			String cutnum=String.valueOf(outyard.getCutnum());
			sql="SELECT * from cutnum WHERE cutnum='"+cutnum+"'";
        	cutnum cutn = cnd.findCodeSingle(sql);
			
			sql="select * from treeout where workid="+workid+"";
			List<tree> tree=trd.findTree(sql);
			request.setAttribute("workid", workid);
			request.setAttribute("outyard", outyard);
			request.setAttribute("tree", tree);
         	request.setAttribute("saleCalloutOrder", saleCalloutOrder);
         	request.setAttribute("contractPrivoder", contractPrivoder);
         	request.setAttribute("compShip", compShip);
         	request.getRequestDispatcher("treeoutPrint.jsp").forward(request, response);
         	
         	Date d1 = null;
			try {
				d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(String.valueOf(outyard.getYarddate()));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}//定义起始日期
        	SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy");
        	SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
        	SimpleDateFormat sdf2= new SimpleDateFormat("dd");
        	SimpleDateFormat sdf3= new SimpleDateFormat("HH");
        	String str0 = sdf0.format(d1);//年
        	String str1 = sdf1.format(d1);//月
        	String str2 = sdf2.format(d1);//日
        	String str3 = sdf3.format(d1);//时
         	
         	FileInputStream file = new FileInputStream(new File("f:\\treeoutYe.xls"));
        	//FileInputStream file = new FileInputStream(new File("C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 9.0\\webapps\\jinsenZWeb\\WEB-INF\\PrintOutTree\\treeoutYe.xls"));
			HSSFWorkbook workbook =new HSSFWorkbook(file);
      //   	HSSFSheet sheet = workbook.createSheet("Sample sheet445");
 	        HSSFSheet sheet = workbook.getSheet("Sheet1");       	
 	        int a=sheet.getFirstRowNum()+80;//从80列开始
 	        int b=sheet.getLastRowNum();
        	try {
        				System.out.print("已经创建好文件，进入到sheet1");
        				System.out.print(b);
                        Row row = sheet.getRow(80);//获取第80行
//        				// 这个判断很必要  确保下面cell操作顺利执行
        		          if(row == null){
        		              row = sheet.createRow(80);
        		          }
        				 for (int j=a;j<b;j++)
        				 {
        					 if(sheet.getRow(j)!=null)
        					 {
        					 sheet.removeRow(sheet.getRow(j));
        					 //sheet.setSelected(true);
        					 sheet.setActive(true);
        					 }
        				 }
        				    //Row row = sheet.createRow(80);
        	                Cell cell = row.createCell(16);//第16列
        	                cell.setCellValue((String)saleCalloutOrder.getSaleCalloutOrder());//调令
        	                
							Cell cell1 = row.createCell(17);
							cell1.setCellValue((String)saleCalloutOrder.getContractnum());
							
						
							Cell cell2 = row.createCell(18);
							cell2.setCellValue((String)saleCalloutOrder.getDemander());
							
							  Cell cell3 = row.createCell(19);
							  cell3.setCellValue((String)contractPrivoder.getProvider());
							
							  
							  Cell cell4 = row.createCell(20);
							  cell4.setCellValue((String)outyard.getYard());
							 
							 Cell cell5 = row.createCell(21);
							  cell5.setCellValue((String)compShip.getShipaddress());//地址
							
							  Cell cell6 = row.createCell(22);
							  cell6.setCellValue(String.valueOf(outyard.getYarddate()));//时间
							  //System.out.print(treeout.getCallidtime());
							  
							  Cell cell7 = row.createCell(23);
							  cell7.setCellValue(outyard.getCarNumber());
//							  
							  Cell cell8 = row.createCell(24);
							  cell8.setCellValue(outyard.getToltree());
						
							  Cell cell9 = row.createCell(25);
							  cell9.setCellValue(outyard.getTolstere());
//							  
							  Cell cell10 = row.createCell(26);
							  cell10.setCellValue(result);
							 // System.out.print(workid);
							  
							  Cell cell11 = row.createCell(27);
							  cell11.setCellValue(str0);
							  
							  Cell cell12 = row.createCell(28);
							  cell12.setCellValue(str1);
						
							  Cell cell13 = row.createCell(29);
							  cell13.setCellValue(str2);
							  
							  Cell cell14 = row.createCell(30);
							  cell14.setCellValue(str3);
							  
							  Cell cell15 = row.createCell(31);
							  cell15.setCellValue(outyard.getSurveyor());
//							  
							  Cell cell16 = row.createCell(32);
							  cell16.setCellValue(String.valueOf(cutn.getProjectPackageName()));
							  
							  Cell cell17 = row.createCell(33);
							  cell17.setCellValue(String.valueOf(cutn.getCutaddress()));
							  
							  Cell cell18 = row.createCell(34);
							  cell18.setCellValue(String.valueOf(cutn.getCutvillage()));
							  
							  Cell cell19 = row.createCell(35);
							  cell19.setCellValue(String.valueOf(cutn.getQuartel()));
							  
							  Cell cell20 = row.createCell(36);
							  cell20.setCellValue(String.valueOf(cutn.getLargeblock()));//大班
							  
							  Cell cell21 = row.createCell(37);
							  cell21.setCellValue(String.valueOf(cutn.getSmallblock()));//小班
							  
							  Cell cell22 = row.createCell(38);
							  cell22.setCellValue(String.valueOf(cutnum));//采伐证
							  
								 for (int i=0;i<tree.size();i++) {  
									Row rowx = sheet.createRow(i+82);//从82行开始
				        	            Cell cella = rowx.createCell(16);//第16列
				        	            cella.setCellValue((String) tree.get(i).getTreetype());
				        	            Cell cellb = rowx.createCell(17);
				        	            cellb.setCellValue((double)tree.get(i).getTlong());
				        	            Cell cellc = rowx.createCell(18);
				        	            cellc.setCellValue((double) tree.get(i).getTradius());
				        	            Cell   celld = rowx.createCell(19);
				        	            celld.setCellValue((double) tree.get(i).getNum());
				        	            Cell celle = rowx.createCell(20);
				        	            celle.setCellValue((double) tree.get(i).getTvolume());
								 }
								 sheet.setForceFormulaRecalculation(true);
        	     file.close();
        		
        	    FileOutputStream out1=new FileOutputStream(new File("f:\\treeoutYe.xls"));
        	     //FileOutputStream out1=new FileOutputStream(new File("C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 9.0\\webapps\\jinsenZWeb\\WEB-INF\\PrintOutTree\\treeoutYe.xls"));
        	    workbook.write(out1);
				/*
				 * sheet = workbook.getSheetAt(0); Iterator<Row> rowIte = sheet.iterator();
				 * while(rowIte.hasNext()){ rowIte.next(); rowIte.remove(); }
				 * System.out.println("已删除完毕");
				 */
        	    file.close();
        	    out1.close();
        	    workbook.close();
        	    System.out.println("Excel written successfully..");
        	   
        	} catch (FileNotFoundException e) {
        	    e.printStackTrace();
        	} catch (IOException e) {
        	    e.printStackTrace();
        	}
         }
      //货场费用结算
         else if("yardmoneyS".equals(action)) {
         	String yeart=request.getParameter("year");
         	String staffname=request.getParameter("staff_name");
             //sql="SELECT year(luRuDate) as yeart,yard from goodyard_cost WHERE year(luRuDate)='"+yeart+"' and luruperson='"+staffname+"' GROUP BY year(luRuDate),yard";
         	sql="SELECT year(luRuDate) as yeart,yard from goodyard_cost WHERE year(luRuDate)='"+yeart+"' GROUP BY year(luRuDate),yard";
             List<yardInventory> cw=trd.findhyard(sql);
         	//System.out.println("...."+cw.size() + "...");
         	mapper.writeValue(response.getWriter(), cw);
         }
        //删除野账数据
         else if("alldelete1".equals(action))
         {
         	String mygroup = request.getParameter("workid");
         	//workpage ac=new workpage();
         	sql="delete from workpage where workid='"+mygroup+"'";
         	int i=wpd.delWorkPage(sql);
         	sql="delete from inyard where workid='"+mygroup+"'";
         	int ii=wpd.delWorkPage(sql);
         	sql="delete from tree where workid='"+mygroup+"'";
         	int iii=wpd.delWorkPage(sql);
         	int iiii=0;
         	if(i>0 && ii>0 && iii>0)
         	{
         		iiii=1;
         	}
         	//System.out.println("...." +i + "...");
         	ObjectMapper mapper1=new ObjectMapper();
     		mapper1.writeValue(response.getWriter(),iiii);
     		int flag=0;
     		if(i<0)
      		{
     		sql="update codestatus set status='delete' where workid="+mygroup+"";
         	flag=wpd.updateWork(sql);
      		}
      		else {
     		 sql="insert into codestatus values("+mygroup+",'delete')";
     		 flag=wpd.addCodeStatus(sql);
      		}
         }
        
        //
         else if(action.equals("addTreeYezhang")) {
     		treefile cp = new treefile();//采伐证信息
         	// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
     		String savePath = this.getServletContext().getRealPath("/WEB-INF/treeinfile");//施工方资料
     		//得到文件访问的相对路径
     		String readPath = "../WEB-INF/treeinfile/";
     		File file = new File(savePath);
     		// 判断上传文件的保存目录是否存在
     		if (!file.exists() || !file.isDirectory()) {
     			System.out.println(savePath + "目录不存在，需要创建");
     			// 创建目录
     			file.mkdir();
     		}
     		// 消息提示
     		String message = "";
     		try {
     			// 使用Apache文件上传组件处理文件上传步骤：
     			// 1、创建一个DiskFileItemFactory工厂
     			DiskFileItemFactory factory = new DiskFileItemFactory();
     			// 2、创建一个文件上传解析器
     			ServletFileUpload upload = new ServletFileUpload(factory);
     			// 解决上传文件名的中文乱码
     			upload.setHeaderEncoding("UTF-8");
     			// 3、判断提交上来的数据是否是上传表单的数据
     			if (!ServletFileUpload.isMultipartContent(request)) {
     				// 按照传统方式获取数据
     				return;
     			}
     			// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
     			List<FileItem> list = upload.parseRequest(request);
     			System.out.println(list.size());
     			for (FileItem item : list) {
     				// 如果fileitem中封装的是普通输入项的数据
     				if (item.isFormField()) {
     					String name = item.getFieldName();//普通输入项username
     					if("workid".equals(name)) {
     						cp.setWorkid(item.getString());
     					}
     					// 解决普通输入项的数据的中文乱码问题
     					String value = item.getString("UTF-8");
     					// value = new String(value.getBytes("iso8859-1"),"UTF-8");
     					System.out.println(name + "=" + value);
     				} else {// 如果fileitem中封装的是上传文件
     						// 得到上传的文件名称，
     					String filename = item.getName();
     					System.out.println(filename);
     					if (filename == null || filename.trim().equals("")) {
     						continue;
     					}
     					// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如： c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
     					// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
     					filename = filename.substring(filename.lastIndexOf("\\") + 1);
     					//request.setAttribute("filename", filename);
     					// 获取item中的上传文件的输入流
     					InputStream in = item.getInputStream();
     					// 创建一个文件输出流
     					FileOutputStream outt = new FileOutputStream(savePath + "\\" + filename);
     					//输出文件保存路径
     					System.out.println("savePath:" + savePath + "\\" +filename);
     					//显示文件读取的相对路径
     					readPath = readPath+ filename;
     					cp.setTreefile(readPath);
     					System.out.println("readPath :"+ readPath);
     					// 创建一个缓冲区
     					byte buffer[] = new byte[1024];
     					// 判断输入流中的数据是否已经读完的标识
     					int len = 0;
     					// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
     					while ((len = in.read(buffer)) > 0) {
     						// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
     						outt.write(buffer, 0, len);
     					}
     					// 关闭输入流
     					in.close();
     					// 关闭输出流
     					outt.close();
     					// 删除处理文件上传时生成的临时文件
     					item.delete();
     					message = "文件上传成功！";
     				}
     			}
     		} catch (Exception e) {
     			message = "文件上传失败！";
     			e.printStackTrace();
     		}
     		int flag=trd.addTreefile(cp);
     		//out.print(flag);
         	if(flag>0) {
         		//out.print("添加成功");
         		out.print("上传附件成功,3秒后返回录入界面<script>setTimeout(\"window.location.href ='passworkpage.jsp';\", 3000);</script>");
         	}
         	else {
         		out.print("添加失败");
         	}
     	}
      //审核进场工单(全部信息) 
         else if(action.equals("printall")) {
         	String timeStart = request.getParameter("timeStart");
         	String timeEnd = request.getParameter("timeEnd");
         	String yard = request.getParameter("yard");
         	String section = request.getParameter("section");
         	//System.out.println("...." +timeStart + "...");
         	//System.out.println("...." +yard + "...");       	
     		sql="select i.workid,i.cutNum,i.yarddate,i.cutSite,i.carNumber,i.yard,i.surveyor,i.section from inyard as i where 1=1";
         	if(!timeStart.isEmpty() && !timeEnd.isEmpty()) {
         		sql=sql+" and i.yarddate>='"+timeStart+"' AND i.yarddate<='"+timeEnd+"'";
         	}
         	if(!yard.isEmpty()) {
         		sql=sql+" and i.yard='"+yard+"'";
         	}
         	if(!section.isEmpty())
         	{
         		sql=sql+" and i.section='"+section+"'";
         	}
         	
         	///System.out.print(sql);
     		List<inyard> work=trd.findinyards(sql);
         	mapper.writeValue(response.getWriter(), work);
         }        
         //审核进场工单(未审核) 
         else if(action.equals("printNotShenHe")) {
         	String timeStart = request.getParameter("timeStart");
         	String timeEnd = request.getParameter("timeEnd");
         	String yard = request.getParameter("yard");
         	String section = request.getParameter("section");
         	//System.out.println("...." +timeStart + "...");
         	//System.out.println("...." +yard + "...");       	
     		sql="select i.workid,i.cutNum,i.yarddate,i.cutSite,i.carNumber,i.yard,i.surveyor,i.section from inyard as i join workpage_status as w on i.workid=w.workid where w.workid_status=7";
         	if(!timeStart.isEmpty() && !timeEnd.isEmpty()) {
         		sql=sql+" and i.yarddate>='"+timeStart+"' AND i.yarddate<='"+timeEnd+"'";
         	}
         	if(!yard.isEmpty()) {
         		sql=sql+" and i.yard='"+yard+"'";
         	}
         	if(!section.isEmpty())
         	{
         		sql=sql+" and i.section='"+section+"'";
         	}
         	
         	///System.out.print(sql);
     		List<inyard> work=trd.findinyards(sql);
         	mapper.writeValue(response.getWriter(), work);
         }        
         //审核进场工单(已通过) 
         else if(action.equals("printPass")) {
         	String timeStart = request.getParameter("timeStart");
         	String timeEnd = request.getParameter("timeEnd");
         	String yard = request.getParameter("yard");
         	String section = request.getParameter("section");
         	//System.out.println("...." +timeStart + "...");
         	//System.out.println("...." +yard + "...");       	
     		sql="select i.workid,i.cutNum,i.yarddate,i.cutSite,i.carNumber,i.yard,i.surveyor,i.section from inyard as i join workpage_status as w on i.workid=w.workid where w.workid_status=8";
         	if(!timeStart.isEmpty() && !timeEnd.isEmpty()) {
         		sql=sql+" and i.yarddate>='"+timeStart+"' AND i.yarddate<='"+timeEnd+"'";
         	}
         	if(!yard.isEmpty()) {
         		sql=sql+" and i.yard='"+yard+"'";
         	}
         	if(!section.isEmpty())
         	{
         		sql=sql+" and i.section='"+section+"'";
         	}
         	
         	///System.out.print(sql);
     		List<inyard> work=trd.findinyards(sql);
         	mapper.writeValue(response.getWriter(), work);
         }        
         //审核进场工单(未通过) 
         else if(action.equals("printNotPass")) {
         	String timeStart = request.getParameter("timeStart");
         	String timeEnd = request.getParameter("timeEnd");
         	String yard = request.getParameter("yard");
         	String section = request.getParameter("section");
         	//System.out.println("...." +timeStart + "...");
         	//System.out.println("...." +yard + "...");       	
     		sql="select i.workid,i.cutNum,i.yarddate,i.cutSite,i.carNumber,i.yard,i.surveyor,i.section from inyard as i join workpage_status as w on i.workid=w.workid where w.workid_status=9";
         	if(!timeStart.isEmpty() && !timeEnd.isEmpty()) {
         		sql=sql+" and i.yarddate>='"+timeStart+"' AND i.yarddate<='"+timeEnd+"'";
         	}
         	if(!yard.isEmpty()) {
         		sql=sql+" and i.yard='"+yard+"'";
         	}
         	if(!section.isEmpty())
         	{
         		sql=sql+" and i.section='"+section+"'";
         	}
         	
         	///System.out.print(sql);
     		List<inyard> work=trd.findinyards(sql);
         	mapper.writeValue(response.getWriter(), work);
         }


	}

}
