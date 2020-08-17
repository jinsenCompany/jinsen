package jinshen.action;
import java.io.File;
import java.io.FileOutputStream;
/*销售部门输入木材销售价格页面*/
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;

import jinshen.dao.salemanDao;
import jinshen.dao.treeDao;
import jinshen.dao.workpageDao;
import jinshen.daoimpl.salemanDaoImpl;
import jinshen.daoimpl.treeDaoImpl;
import jinshen.daoimpl.workpageDaoImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import jinshen.bean.*;

/**
 * Servlet implementation class salaryServlet
 */
@WebServlet("/salaryServlet")
public class salaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public salaryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}*/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String sql="";
        treeDao trd=new treeDaoImpl();
        salemanDao sd=new salemanDaoImpl();
        treeDao td=new treeDaoImpl();
        workpageDao wpd=new workpageDaoImpl();
        ObjectMapper mapper = new ObjectMapper();
        //通过当销售工单号和数据库中一样，查询数据库中的树材信息，然后显示再treePrice.jsp页面
        if(action.equals("mysave"))
        {
        	double workid = Double.parseDouble(request.getParameter("workid"));
        	//System.out.println("...." + workid + "...");
        	Map map=new HashMap();
        	List<salesman> list=new ArrayList<salesman>();
        	sql="select count(*) from saleman where workid="+workid+"";
        	int flag=sd.findMaxid(sql);
        	//JSONArray json=JSONArray.fromObject(workid);
        	//System.out.println("...." + flag + "...");
        	if(flag>0) {
        		sql="select s.provider,s.demander,o.contractnum,o.sale_callout_orderid,o.yard,o.yarddate,o.carNumber,o.surveyor from outyard o join sale_contract s on o.contractnum=s.contractnum where workid="+workid+"";
        		list =sd.findSaleList(sql);
        		System.out.println("...." + list + "...");
        		sql="select treetype,sum(tvolume) as total,unitprice,(sum(tvolume)*unitprice) as totalprice  from treeout a join outyard b on a.workid=b.workid where b.workid="+workid+" group by treetype";
        		List<worktree> worktree=sd.findworktree(sql);
        		map.put("salesman", list);
        		map.put("tree", worktree);
        		mapper.writeValue(response.getWriter(), map);
        		//System.out.println("...." + map.put("salesman", list) + "...");
        	}
        	else {
        		//double each=0;
            	//int count=0;
            	//String str="(";
            	//str+=",";
            	sql = "select s.provider,s.demander,o.contractnum,o.sale_callout_orderid,o.yard,o.yarddate,o.carNumber,o.surveyor from outyard o join sale_contract s on o.contractnum=s.contractnum where workid="+workid+"";
            	salesman s=sd.findsaleSingle(sql);//擦护照sql的数据通过findsaleSingle读取显示，再赋值给s
            	list.add(s);            	
            	//str+=each;
            	//str+=")";
            	sql="select treetype,sum(tvolume) as total,unitprice,(sum(tvolume)*unitprice) as totalprice  from treeout a join outyard b on a.workid=b.workid where b.workid="+workid+" group by treetype";
            	List<worktree> worktree=sd.findworktree(sql);
            	map.put("salesman", list);
        		map.put("tree", worktree);
        		mapper.writeValue(response.getWriter(), map);
        		//System.out.println("...." + map.put("salesman", list) + "...");
        	}
        }
       /* else if(action.equals("savesaleman")) {
        	double workid = Double.parseDouble(request.getParameter("workid"));
        	String treetype = request.getParameter("treetype");
        	double unitprice =Double.parseDouble(request.getParameter("unitprice"));
        	double price = Double.parseDouble(request.getParameter("price"));
        	String salesman= request.getParameter("salesman");
        	salemansql cp = new salemansql();
        	cp.setWorkid(workid);
        	cp.setTreetype(treetype);
        	cp.setUnitprice(unitprice);
        	cp.setPrice(price);
        	cp.setSaleman(salesman);
        	int flag=sd.addWork(cp);
        		out.print("flag"); 
        }*/
        //保存销售treePrice.jsp页面数据到数据库saleman中
        else if(action.equals("savesaleman")) {
        	String rebate = request.getParameter("newtree");
        	System.out.println("...." + rebate + "...");
        	int id=Integer.parseInt(request.getParameter("id"));
        	JSONObject jb = JSONObject.fromObject(rebate);            	
        	double workid=Double.parseDouble(request.getParameter("workid"));
        	String salesman=request.getParameter("salesman");
        	List<salesman> list=new ArrayList<salesman>();
        	sql="select count(*) from saleman where workid="+workid+"";
        	int flag1=sd.findMaxid(sql);
        	/*if(flag1>0)
        	{
        		for(int i=0;i<id;i++) {
            		JSONArray s=jb.getJSONArray(String.valueOf(i));
                	String treetype=s.getString(0);
                	double unitprice=Double.parseDouble(s.getString(1));
                	double price=Double.parseDouble(s.getString(2));
                	sql="update saleman set treetype="+treetype+",unitprice="+unitprice+",price="+price+" where workid="+workid+"";
                	
            	}
        		
        	}*/
        		for(int i=0;i<id;i++) {
            		JSONArray s=jb.getJSONArray(String.valueOf(i));
            		salemansql cp = new salemansql();
                	cp.setWorkid(workid);
                	cp.setTreetype(s.getString(0));
                	cp.setUnitprice(Double.parseDouble(s.getString(1)));
                	cp.setPrice(Double.parseDouble(s.getString(2)));
                	cp.setSaleman(salesman);
                	int flag=sd.addWork(cp);
                	out.print(flag);
            	}
        }
       //更新再treeprice.jsp页面对木材信息的修改，并把他们保存到数据库表treeout中
        else if("treeAdd".equals(action))
        {

        	String rebate = request.getParameter("newtree");
        	int id=Integer.parseInt(request.getParameter("id"));
        	JSONObject jb = JSONObject.fromObject(rebate);            	
        	double workid=Double.parseDouble(request.getParameter("workid"));
            for(int i=0;i<id;i++)
            {
            	JSONArray s=jb.getJSONArray(String.valueOf(i));
            	String type=s.getString(0);
            	double volume=Double.parseDouble(s.getString(1));
            	double unitprice=Double.parseDouble(s.getString(2));
            	double price=Double.parseDouble(s.getString(3));
            	sql="update treeout set unitprice="+unitprice+",totalnum=unitprice*tvolume where  treeout.workid in "
            			+ "(select workid from outyard) and treetype='"+type+"'";
            	int flag=td.updateTree(sql);
            }
            out.print("更新完成！");
        }
        //生产业主信息
        else if("addcustomer".equals(action)) {
        	//double workid=Double.parseDouble(request.getParameter("workid"));
        	String kname=request.getParameter("kname");
        	String contractnum=request.getParameter("contractnum");
        	String address=request.getParameter("address");
        	String company=request.getParameter("company");
        	String iDCard=request.getParameter("IDCard");
        	String telephone=request.getParameter("telephone");
        	//System.out.println("...." + treetype + "...");
        	String shipaddress=request.getParameter("shipaddress");
        	String creatdate=request.getParameter("creatdate");
        	String customertype=request.getParameter("customertype");
        	String customernum=request.getParameter("customernum");
        	String treenumber=request.getParameter("treenumber");
        	String totaltree=request.getParameter("totaltree");
        	String breakcontract=request.getParameter("breakcontract");
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	Date creattime=null;
        	try {
				creattime = new java.sql.Timestamp(format.parse(creatdate).getTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	customer cp=new customer();
        	cp.setKname(kname);
        	cp.setContractnum(contractnum);
        	cp.setCompany(company);
        	cp.setAddress(address);
        	cp.setIDCard(iDCard);
        	cp.setTelephone(telephone);
        	cp.setShipaddress(shipaddress);
        	cp.setCreatdate(creattime);
        	cp.setCustomertype(customertype);
        	cp.setCustomernum(customernum);
        	cp.setTreenumber(treenumber);
        	cp.setTotaltree(totaltree);
        	cp.setBreakcontract(breakcontract);
        	int flag=sd.addCustomer(cp);
        	if(flag>0) {
        	    out.print("添加信息成功");
        	    //request.getRequestDispatcher("productowner.jsp").forward(request, response);
        	    }
        	else {
        		out.print("添加信息失败");
        	}
        }
        //发现生产业主信息到并发送到productoy.jsp页面中
        else if("findcustomer".equals(action)) {
        	sql="SELECT * from customer";
        	List<customer> ct=sd.findCustomer(sql);
        	mapper.writeValue(response.getWriter(),ct);
        }
        //根据采伐证号显示材积
        else if(action.equals("account")) {
    		double cutnum=Double.parseDouble(request.getParameter("cutnum"));
    		Map map=new HashMap();
    		List<Laowu> list=new ArrayList<Laowu>();
    		sql="select count(*) from inyard where cutnum="+cutnum+"";
    		int flag=sd.findMaxid(sql);
    		if(flag>0) {
    			sql="select treetype,sum(tvolume) as total,unitprice,(sum(tvolume)*unitprice) as totalprice  from tree a join inyard b on a.workid=b.workid where b.cutNum="+cutnum+" group by treetype";
        		List<worktree> worktree=sd.findworktree(sql);
        		map.put("tree", worktree);
        		mapper.writeValue(response.getWriter(), map);
    		}
    		else {
    			sql="select treetype,sum(tvolume) as total,unitprice,(sum(tvolume)*unitprice) as totalprice  from tree a join inyard b on a.workid=b.workid where b.cutNum="+cutnum+" group by treetype";
        		List<worktree> worktree=sd.findworktree(sql);
        		map.put("tree", worktree);
        		mapper.writeValue(response.getWriter(), map);
    		}
    	}
        //保存材积到laowu表
        else if(action.equals("saveLaowu")) {
        	String rebate = request.getParameter("newtree");
        	int id=Integer.parseInt(request.getParameter("id"));
        	System.out.println("...." + id + "...");
        	JSONObject jb = JSONObject.fromObject(rebate);            	
        	String cutnum=request.getParameter("cutnum");
        	double projectPackageid=Double.parseDouble(request.getParameter("projectPackageid"));
        	String person=request.getParameter("person");
        	String forperson=request.getParameter("forperson");
        	String manageUnit=request.getParameter("manageUnit");
        	double ttvolume=Double.parseDouble(request.getParameter("ttvolume"));
        	double tprice=Double.parseDouble(request.getParameter("tprice"));
        	for(int i=0;i<id;i++) {
        		JSONArray s=jb.getJSONArray(String.valueOf(i));
        		Laowu cp = new Laowu();
            	cp.setCutnum(cutnum);
            	cp.setProjectPackageid(projectPackageid);
            	cp.setForperson(forperson);
            	cp.setManageUnit(manageUnit);
            	cp.setTreetype(s.getString(0));
            	cp.setUnitprice(Double.parseDouble(s.getString(1)));
            	cp.setprice(Double.parseDouble(s.getString(2)));
            	cp.setPerson(person);
            	cp.setttvolume(ttvolume);
            	cp.settprice(tprice);
            	int flag=sd.addProduce(cp);
            	out.print(flag);
        	}
        }
      //根据采伐证号显示材积
        else if(action.equals("savew")) {
        	String cutnum=request.getParameter("cutnum");
        	Map map=new HashMap();
    		List<singleworkid> list=new ArrayList<singleworkid>();
    		sql="select count(*) from inyard where cutnum='"+cutnum+"'";
    		int flag=sd.findMaxid(sql);
    		String mygroup = request.getParameter("mygroup");
        	JSONArray json=JSONArray.fromObject(mygroup); 
        	if(flag>0 && json.length()==0)
        	{
        		sql="select c.proj_package_id,m.ownername,c.company from cutnum c JOIN managesdatecard m on c.cutnum=m.cutnum WHERE c.cutnum='"+cutnum+"'";
        		List<singleworkid> project=sd.findProjectp(sql);//显示工程包信息
        		sql="select a.workid from tree a join inyard b on a.workid=b.workid where b.cutNum='"+cutnum+"' group by workid";
        		list=sd.findworkid(sql);
        		sql="select treetype,sum(tvolume) as total,unitprice,(sum(tvolume)*unitprice) as totalprice  from tree a join inyard b on a.workid=b.workid where b.cutNum='"+cutnum+"' group by treetype";
        		List<worktree> worktree=sd.findworktree(sql);
        		map.put("project", project);
        		map.put("work", list);
        		map.put("tree", worktree);
        		mapper.writeValue(response.getWriter(), map);
        	}
        	else {
        		double each=0;
        		String str="(";
        		str+=")";
        		sql="select c.proj_package_id,m.ownername,c.company from cutnum c JOIN managesdatecard m on c.cutnum=m.cutnum WHERE c.cutnum='"+cutnum+"'";
        		List<singleworkid> project=sd.findProjectp(sql);//显示工程包信息
        		sql="select a.workid from tree a join inyard b on a.workid=b.workid where b.cutNum="+cutnum+" group by treetype";
        		list=sd.findworkid(sql);
        		sql="select treetype,sum(tvolume) as total,unitprice,(sum(tvolume)*unitprice) as totalprice  from tree a join inyard b on a.workid=b.workid where b.cutNum="+cutnum+" group by treetype";
        		List<worktree> worktree=sd.findworktree(sql);
        		map.put("project", project);
        		map.put("work", list);
        		map.put("tree", worktree);
        		mapper.writeValue(response.getWriter(), map);
        	}
        }
        //保存销售合同
        else if("addcontract".equals(action)) {
        	String contractnum=request.getParameter("contractnum");
        	String provider=request.getParameter("provider");
        	String demander=request.getParameter("demander");
        	String rebate = request.getParameter("newtree");
        	int id=Integer.parseInt(request.getParameter("id"));
        	JSONObject jb = JSONObject.fromObject(rebate);
        	//String treetype=request.getParameter("treetype");
        	//String tlong=request.getParameter("tlong");
        	//String tradius=request.getParameter("tradius");
        	//double unitprice=Double.parseDouble(request.getParameter("unitprice"));
        	//System.out.println("...." + contractnum + "...");
        	String deliveryplace=request.getParameter("deliveryplace");
        	String treenumber=request.getParameter("treenumber");
        	String cost=request.getParameter("cost");
        	String measurestard=request.getParameter("measurestard");
        	String settlemethod=request.getParameter("settlemethod");
        	String starttime=request.getParameter("starttime");
        	String endtime=request.getParameter("endtime");
        	String margin=request.getParameter("margin");
        	String contractfile=request.getParameter("contractfile");
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
        	Date d = null;
			Date dd = null;
			try {
				d = format.parse(starttime);
				dd = format.parse(endtime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date begin = new java.sql.Date(d.getTime()); 
			java.sql.Date end = new java.sql.Date(dd.getTime()); 
        	salecontract cp=new salecontract();
        	cp.setContractnum(contractnum);
        	cp.setProvider(provider);
        	cp.setDemander(demander);
        	cp.setDeliveryplace(deliveryplace);
        	cp.setTreenumber(treenumber);
        	cp.setCost(cost);
        	cp.setMeasurestard(measurestard);
        	cp.setSettlemethod(settlemethod);
        	cp.setStarttime(begin);
        	cp.setEndtime(end);
        	cp.setContractfile(contractfile);
        	cp.setMargin(margin);
        	int flag=sd.addContract(cp);
        	if(flag>0) {
            sql="select sale_contract_id from sale_contract WHERE contractnum like '%"+contractnum+"%' and treenumber="+treenumber+"";
            salecontract idd=sd.findsalecontractid(sql);
            int contractid=idd.getContractid();
            int flags=0;
            for(int i=0;i<id;i++)
        	{
        		JSONArray s=jb.getJSONArray(String.valueOf(i));
        		salecontract sc=new salecontract();
        		sc.setContractid(contractid);
        		sc.setTreetype(s.getString(0));
            	sc.setTlong(s.getString(1));
            	sc.setTradius(s.getString(2));
            	sc.setUnitprice(Double.parseDouble(s.getString(3)));
            	flags=sd.addContractTree(sc);
        	}
        	//int flags=sd.addContractTree(sc);
        	if(flags>0) {
        	    out.print("添加信息成功");
        	    //request.getRequestDispatcher("productowner.jsp").forward(request, response);
        	    }
        	else {
        		out.print("添加信息失败");
        	}
        	}
        	
        }
      //保存销售合同
        else if("addcontract1".equals(action)) {
        	salecontract cp=new salecontract();//采伐证信息
        	salecontract sc=new salecontract();//采伐证木材信息
        	String contractnum="";
        	String treenumber="";
        	String treetype="";
        	String tlong="";
        	String tradius="";
        	Double unitprice=null;
        	ArrayList<String> Tret=new ArrayList<>();
        	ArrayList<String> tl=new ArrayList<>();
        	ArrayList<String> trad=new ArrayList<>();
        	ArrayList<Double> unitp=new ArrayList<>();
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
        	Date d = null;
			Date dd = null;
        	// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
    		String savePath = this.getServletContext().getRealPath("/WEB-INF/saleContractfile");
    		//得到文件访问的相对路径
    		String readPath = "../WEB-INF/saleContractfile/";
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
    					if("contractnum".equals(name)) {
    						cp.setContractnum(item.getString("UTF-8"));
    						contractnum=item.getString("UTF-8");
    					}
    					else if("provider".equals(name)) {
    						cp.setProvider(item.getString("UTF-8"));
    					}
    					else if("demander".equals(name)) {
    						cp.setDemander(item.getString("UTF-8"));
    					}
    					else if("marknumber".equals(name)) {
    						cp.setMarknumber(item.getString("UTF-8"));
    					}
    					else if("deliveryplace".equals(name)) {
    						cp.setDeliveryplace(item.getString("UTF-8"));
    					}
    					else if("treenumber".equals(name)) {
    						cp.setTreenumber(item.getString("UTF-8"));
    						treenumber=item.getString("UTF-8");
    					}
    					else if("cost".equals(name)) {
    						cp.setCost(item.getString("UTF-8"));
    					}
    					else if("measurestard".equals(name)) {
    						cp.setMeasurestard(item.getString("UTF-8"));
    					}
    					else if("settlemethod".equals(name)){
    						cp.setSettlemethod(item.getString("UTF-8"));
    					}
    					else if("margin".equals(name)) {
    						cp.setMargin(item.getString("UTF-8"));
    					}
    					else if("starttime".equals(name)) {
    						String sttime=item.getString("UTF-8");
    						try {
    							d = format.parse(sttime);
    						} catch (ParseException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						} 
    						java.sql.Date begin = new java.sql.Date(d.getTime()); 
    						cp.setStarttime(begin);
    					}
    					else if("endtime".equals(name)) {
    						String endtime=item.getString("UTF-8");
    						try {
    							dd = format.parse(endtime);
    						} catch (ParseException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						} 
    						java.sql.Date end = new java.sql.Date(dd.getTime()); 
    						cp.setEndtime(end);
    					}
    					else if("treetype".equals(name)) {
    						treetype=item.getString("UTF-8");
    						Tret.add(treetype);
    					}
    					else if("tlong".equals(name)) {
    						tlong=item.getString("UTF-8");
    						tl.add(tlong);
    					}
    					else if("tradius".equals(name)) {
    						tradius=item.getString("UTF-8");
    						trad.add(tradius);
    					}
    					else if("unitprice".equals(name)) {
    						unitprice=Double.parseDouble(item.getString("UTF-8"));
    						unitp.add(unitprice);
    					}
    					// 解决普通输入项的数据的中文乱码问题
    					String value = item.getString("UTF-8");
    					// value = new String(value.getBytes("iso8859-1"),"UTF-8");
    					//System.out.println(name + "=" + value);
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
    					cp.setContractfile(readPath);
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
    		
    		int flag=sd.addContract(cp);
        	if(flag>0) {
            sql="select sale_contract_id from sale_contract WHERE contractnum='"+contractnum+"' and treenumber='"+treenumber+"'";
            salecontract idd=sd.findsalecontractid(sql);
            int contractid=idd.getContractid();
            int flags=0;
            for (int i=0;i<Tret.size();i++) {
            	 sc.setContractid(contractid);
                sc.setTreetype(Tret.get(i));
                sc.setTlong(tl.get(i));
                sc.setTradius(trad.get(i));
                sc.setUnitprice(unitp.get(i));
                flags=sd.addContractTree(sc);
			}
            out.print(flags);
        	}
        }
        //合同进度信息查看
        else if("contractProgress".equals(action)) {
        	sql="SELECT sale_contract_id,contractnum,provider,demander,treenumber,starttime,endtime,margin FROM sale_contract";
        	List<salecontract> ac=sd.findsalecontractP(sql);
        	mapper.writeValue(response.getWriter(),ac);
        }
        //在页面显示合同详细信息
        else if("contractList".equals(action)) {
        	String str=request.getParameter("contractid");
        	str=str.replace("'", "");
        	double number=Double.parseDouble(str);
        	sql="SELECT contractnum,provider,demander,deliveryplace,treenumber,cost,measurestard,settlemethod,starttime,endtime,contractfile,margin,marknumber from sale_contract WHERE sale_contract_id="+number+"";
        	salecontract salecontract=sd.findcontract(sql);
        	String contractfile=salecontract.getContractfile();
        	contractfile = contractfile.substring(contractfile.lastIndexOf("/") + 1);//去掉储存路径斜杠
        	sql="SELECT treetype,tlong,tradius,unitprice from sale_contract_tree WHERE sale_contract_id="+number+"";
        	List<salecontract> salecontractTree=sd.fincontractTree(sql);
        	sql="SELECT sum(o.tolstere) as tolstere,s.treenumber from outyard as o join sale_contract as s on o.contractnum=s.contractnum WHERE s.sale_contract_id="+number+"";
        	contractProgress contractProgress=sd.findconProgress(sql);
        	double tolstere=contractProgress.getTotalStere();
        	double treenumber=contractProgress.getTreenumber();
        	double treebaifen=tolstere/treenumber;
        	DecimalFormat df1 = new DecimalFormat("##.00%");
        	String treebaifenb = df1.format(treebaifen);
        	request.setAttribute("contractfile", contractfile);
        	request.setAttribute("salecontract", salecontract);
        	request.setAttribute("salecontractTree", salecontractTree);
        	request.setAttribute("contractProgress", contractProgress);
        
        	request.getRequestDispatcher("salaryContractUpdate.jsp").forward(request, response);
        }
        //合同进度
        else if("contractListProg".equals(action)) {
        	Map map=new HashMap();
        	String str=request.getParameter("contractid");
        	//str=str.replace("'", "");
        	double number=Double.parseDouble(str);
        	sql="SELECT sum(o.tolstere) as tolstere,s.treenumber from outyard as o join sale_contract as s on o.contractnum=s.contractnum WHERE s.sale_contract_id="+number+"";
        	contractProgress contractProgress=sd.findconProgress(sql);
    		//mapper.writeValue(response.getWriter(),contractProgress);
    		//out.print(contractProgress);
        	double tolstere=contractProgress.getTotalStere();
        	double treenumber=contractProgress.getTreenumber();
        	double treebaifen=tolstere/treenumber;
        	DecimalFormat df1 = new DecimalFormat("##.00%");
        	String treebaifenb = df1.format(treebaifen);
    		//map.put("contractProgress", treebaifenb);
    		//System.out.print(treebaifenb);
    		//mapper.writeValue(response.getWriter(), map);
        	request.setAttribute("contractProgress", contractProgress);
        	request.getRequestDispatcher("salaryContractUpdate.jsp").forward(request, response);
        }
        //暂时没用
        else if("treeAdd".equals(action)) {
        	String rebate = request.getParameter("newtree");
        	int id=Integer.parseInt(request.getParameter("id"));
        	JSONObject jb = JSONObject.fromObject(rebate);
        	String contractnum = request.getParameter("contractnum");
        	double treenumber=Double.parseDouble(request.getParameter("treenumber"));
        	sql="select sale_contract_id from sale_contract WHERE contractnum like '%"+contractnum+"%' and treenumber="+treenumber+"";
            salecontract cid=sd.findsalecontractid(sql);//获取合同单号
            int contractid=cid.getContractid();
        	for(int i=0;i<id;i++)
        	{
        		JSONArray s=jb.getJSONArray(String.valueOf(i));
        		salecontract sc=new salecontract();
        		sc.setContractid(contractid);
        		sc.setTreetype(s.getString(0));
            	sc.setTlong(s.getString(1));
            	sc.setTradius(s.getString(2));
            	sc.setUnitprice(Double.parseDouble(s.getString(3)));
            	int flags=sd.addContractTree(sc);
            	out.print(flags);
        	}
        }
        //销售调令
        else if("salecalloutAdd".equals(action)) {
            String rebate = request.getParameter("newtree");
    	    int id=Integer.parseInt(request.getParameter("id"));
    	    JSONObject jb = JSONObject.fromObject(rebate);
    	    String saleCalloutOrder = request.getParameter("saleCalloutOrder");
    	    String contractnum = request.getParameter("contractnum");
    	    String yardname = request.getParameter("yardname");
    	    String demander=request.getParameter("demander");
    	    String section = request.getParameter("section");//去掉票据
    	    String paymentamount = request.getParameter("Paymentamount");//货款金额
    	    String signer = request.getParameter("Signer");
    	    int totalnum = Integer.parseInt(request.getParameter("totalnum"));
    	    String callidtime = request.getParameter("callidtime");//调运时间
    	    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    	    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	    Date date=null;
    	    String date1="";
    	    try {
				date = sdf1.parse(callidtime);
				date1=sdf2.format(date);
				System.out.print(date1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	    
    	    
    	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        	Date calliddate=null;
        	try {
        		calliddate = new java.sql.Timestamp(format.parse(date1).getTime());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
        	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createtime = format1.format(new Date());
			//System.out.println(createtime);
			Timestamp Time = Timestamp.valueOf(createtime);
    	    saleCalloutOrder sc=new saleCalloutOrder();
    	    sc.setSaleCalloutOrder(saleCalloutOrder);
    	    sc.setContractnum(contractnum);
    	    sc.setYard(yardname);
    	    sc.setDemander(demander);
    	    sc.setSection(section);
    	    sc.setPaymentamount(paymentamount);
    	    sc.setTotalnum(totalnum);
    	    sc.setSigner(signer);
    	    sc.setCallidtime(calliddate);
    	    sc.setCreattime(Time);
    	    sc.setSalestatus(0);//销售调令状态，0：表示添加未审核，1：审核通过，2审核未通过，3：审核未通过补交材料
    	    int flag=sd.addCalloutOrder(sc);
    	    if (flag>0) {
    	    	sql="SELECT  sale_order_id from sale_callout_orderid WHERE sale_callout_order_id='"+saleCalloutOrder+"'";
    	    	saleCalloutOrder fsd=sd.findsaleorderid(sql);
                int saleCallid=fsd.getSaleCallid();
    	       for(int i=0;i<id;i++)
        	  {
        		JSONArray s=jb.getJSONArray(String.valueOf(i));
        		 saleCalloutOrder sco=new saleCalloutOrder();
        		 sco.setSaleCallid(saleCallid);
        		 sco.setTreetype(s.getString(0));
            	 sco.setTlong(s.getString(1));
            	 sco.setTradius(s.getString(2));
            	int flags=sd.addCalloutOrderTree(sco);
            	out.print(flags);
        	}
    	    }
        }
        else if("seesalecalloutorder".equals(action)) {
        	//String saleCalloutOrder=request.getParameter("saleCalloutOrder");
        	//System.out.print(saleCalloutOrder);
        	//按时间降序
        	//sql="select sale_order_id,callidtime,contractnum,sale_callout_order_id,yard,section,demander,Paymentamount,totalnum,Signer from sale_callout_orderid ORDER BY callidtime desc";
        	sql="select sale_order_id,callidtime,contractnum,sale_callout_order_id,yard,section,demander,Paymentamount,totalnum,Signer from sale_callout_orderid";
        	List<saleCalloutOrder> cw=sd.findsaleCallid(sql);
        	mapper.writeValue(response.getWriter(), cw);
        }
        //第一次审核调令
        else if("salecallupdate".equals(action)) {
        	String str=request.getParameter("saleCallid");
        	str=str.replace("'", "");
        	double number=Double.parseDouble(str);
        	//System.out.println("...."+number + "...");
        	sql="select sale_callout_order_id,contractnum,yard,section,demander,Paymentamount,totalnum,Signer,callidtime,creattime from sale_callout_orderid WHERE sale_order_id="+number+"";
        	saleCalloutOrder saleCalloutOrder=sd.findsaleorder(sql);
        	Date sss=saleCalloutOrder.getCallidtime();
        	String yardname=saleCalloutOrder.getYard();
        	sql="select yardmanager from yardmanage WHERE yardname='"+yardname+"'";
        	yardManage ya=wpd.findyardmanager(sql);//管理人员姓名
        	String yardmanager=ya.getYardmanager();
        	sql="SELECT treetype,tlong,tradius from sale_callout_orderid_tree WHERE sale_order_id="+number+"";
        	List<saleCalloutOrder> saleOrdertree=sd.findsaletree(sql);
        	System.out.print(sss);
        	sql="SELECT * from surveyor_info";
    		List<surveyor> surv=wpd.findSurveyor(sql);
    		request.setAttribute("surveyor", surv);
        	request.setAttribute("saleCalloutOrder", saleCalloutOrder);
        	request.setAttribute("saleOrdertree", saleOrdertree);
        	request.setAttribute("yardmanager", yardmanager);
        	request.getRequestDispatcher("saleCalloutOrderUpdate.jsp").forward(request, response);
        }
      //已完成调令
        else if("salecallupdateM".equals(action)) {
        	String str=request.getParameter("saleCallid");
        	str=str.replace("'", "");
        	double number=Double.parseDouble(str);
        	sql="select sale_callout_order_id,contractnum,yard,section,demander,Paymentamount,totalnum,Signer,callidtime,creattime from sale_callout_orderid WHERE sale_order_id="+number+"";
        	saleCalloutOrder saleCalloutOrder=sd.findsaleorder(sql);
        	Date sss=saleCalloutOrder.getCallidtime();
        	String yardname=saleCalloutOrder.getYard();
        	sql="select yardmanager from yardmanage WHERE yardname='"+yardname+"'";
        	yardManage ya=wpd.findyardmanager(sql);//管理人员姓名
        	String yardmanager=ya.getYardmanager();
        	sql="SELECT treetype,tlong,tradius from sale_callout_orderid_tree WHERE sale_order_id="+number+"";
        	List<saleCalloutOrder> saleOrdertree=sd.findsaletree(sql);
        	System.out.print(sss);
        	sql="SELECT * from sale_callout_orderid_surveyor";
    		List<surveyor> surv=wpd.findSurveyor(sql);
    		request.setAttribute("surveyor", surv);
        	request.setAttribute("saleCalloutOrder", saleCalloutOrder);
        	request.setAttribute("saleOrdertree", saleOrdertree);
        	request.setAttribute("yardmanager", yardmanager);
        	request.getRequestDispatcher("saleCalloutOrderUpdateFinish.jsp").forward(request, response);
        }
      //修改调令
        else if("salecallupdateF".equals(action)) {
        	String str=request.getParameter("saleCallid");
        	str=str.replace("'", "");
        	double number=Double.parseDouble(str);
        	//System.out.println("...."+number + "...");
        	sql="select sale_callout_order_id,contractnum,yard,section,demander,Paymentamount,totalnum,Signer,callidtime from sale_callout_orderid WHERE sale_order_id="+number+"";
        	saleCalloutOrder saleCalloutOrder=sd.findsaleorder(sql);
        	sql="SELECT treetype,tlong,tradius from sale_callout_orderid_tree WHERE sale_order_id="+number+"";
        	List<saleCalloutOrder> saleOrdertree=sd.findsaletree(sql);
        	//System.out.print(saleOrdertree);
        	request.setAttribute("saleCalloutOrder", saleCalloutOrder);
        	request.setAttribute("saleOrdertree", saleOrdertree);
        	request.getRequestDispatcher("saleCalloutOrderUpdateModify.jsp").forward(request, response);
        }
        //审核销售调令//销售调令状态，0：表示添加未审核，1：审核通过，2审核未通过，3：调令完成
        else if("writesale".equals(action))
        {
        	String mytype = request.getParameter("type");
        	if(mytype.equals("all"))
        	{
        		sql="select sale_order_id,callidtime,contractnum,sale_callout_order_id,yard,section,demander,Paymentamount,totalnum,Signer from sale_callout_orderid ORDER BY callidtime desc";
            	List<saleCalloutOrder> work=sd.findsaleCallid(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//未通过
        	else if(mytype.equals("notpass"))
        	{
        		sql="select sale_order_id,callidtime,contractnum,sale_callout_order_id,yard,section,demander,Paymentamount,totalnum,Signer from sale_callout_orderid WHERE sale_status=2";
        		List<saleCalloutOrder> work=sd.findsaleCallid(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//通过
        	else if(mytype.equals("pass"))
        	{
        		sql="select sale_order_id,callidtime,contractnum,sale_callout_order_id,yard,section,demander,Paymentamount,totalnum,Signer from sale_callout_orderid WHERE sale_status=1";
        		List<saleCalloutOrder> work=sd.findsaleCallid(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//未审核
        	else if(mytype.equals("notshenhe"))
        	{
        		sql="select sale_order_id,callidtime,contractnum,sale_callout_order_id,yard,section,demander,Paymentamount,totalnum,Signer from sale_callout_orderid WHERE sale_status=0";
        		List<saleCalloutOrder> work=sd.findsaleCallid(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//审核后补充材料
        	else if(mytype.equals("buchong"))
        	{
        		sql="select sale_order_id,callidtime,contractnum,sale_callout_order_id,yard,section,demander,Paymentamount,totalnum,Signer from sale_callout_orderid WHERE sale_status=3";
        		List<saleCalloutOrder> work=sd.findsaleCallid(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//货场管理员审核通过的调令
        	else if(mytype.equals("shenhepass"))
        	{
        		sql="select sale_callout_order_id,contractnum,demander,yard,totalnum,callidtime from sale_callout_orderid WHERE sale_status=1";
        		List<saleCalloutOrder> work=sd.findsaleCallpass(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        }
        	//删除内容从数据库中
        	else if("alldelete".equals(action))
            {
            	String mygroup = request.getParameter("saleCallid");
            	//workpage ac=new workpage();
            	sql="delete from sale_callout_orderid join sale_callout_orderid_tree where workid="+mygroup+"";
            	int i=sd.delWorkPage(sql);
            	//System.out.println("...." +i + "...");
            	ObjectMapper mapper1=new ObjectMapper();
        		mapper1.writeValue(response.getWriter(),i);
        		out.print(i);	
            }
        //销售部门审核销售调令
        	else if("worksaleyesorno".equals(action)) {
            	String mytype = request.getParameter("type");
            	String rebate = request.getParameter("newtree");
        	    int id=Integer.parseInt(request.getParameter("id"));
        	    JSONObject jb = JSONObject.fromObject(rebate);
            	String saleCalloutOrder=request.getParameter("saleCalloutOrder");
            	sql="SELECT  sale_order_id from sale_callout_orderid WHERE sale_callout_order_id='"+saleCalloutOrder+"'";
    	    	saleCalloutOrder fsd=sd.findsaleorderid(sql);
                int saleCallid=fsd.getSaleCallid();
            	if(mytype.equals("yes")) {
            		saleCalloutOrder sc=new saleCalloutOrder();
            		sc.setSalestatus(1);
            		int flag=sd.updateSaleCallorder(sc,saleCalloutOrder);
            		if(flag>0){
            			 for(int i=0;i<id;i++)
                   	  {
                   		JSONArray s=jb.getJSONArray(String.valueOf(i));
                   		saleCalloutOrder sco=new saleCalloutOrder();
                   		sco.setSaleCallid(saleCallid);
                   		sco.setSurveyor(s.getString(0));
                   		int flags=sd.addCalloutSurveyor(sco);
                   		out.print(flags);
                     }
            	}
            		else
            			out.print(flag);
            	}
            	else if(mytype.equals("not")) {
            		saleCalloutOrder sc=new saleCalloutOrder();
            		sc.setSalestatus(2);//审核未通过
            		int flag=sd.updateSaleCallorder(sc,saleCalloutOrder);
            		if(flag>0){
            			out.print(flag);
            			}
            	}
            }
        //更新销售调令
        	else if("updatesaleCallout".equals(action)) {
        		 String rebate = request.getParameter("newtree");
         	    int id=Integer.parseInt(request.getParameter("id"));
         	    JSONObject jb = JSONObject.fromObject(rebate);
         	    String saleCalloutOrder = request.getParameter("saleCalloutOrder");
         	    String contractnum = request.getParameter("contractnum");
         	    String yard = request.getParameter("yardname");
         	   //System.out.println("...." +yard + "...");
         	    String demander=request.getParameter("demander");
         	    String section = request.getParameter("section");//去掉票据
         	    String paymentamount = request.getParameter("Paymentamount");//货款金额
         	    String signer = request.getParameter("Signer");
         	    int totalnum = Integer.parseInt(request.getParameter("totalnum"));
         	    String callidtime = request.getParameter("callidtime");//货款金额
         	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
             	Date d = null;
     			try {
     				d = format.parse(callidtime);
     			} catch (ParseException e) {
     				// TODO Auto-generated catch block
     				e.printStackTrace();
     			} 
     			java.sql.Date calliddate = new java.sql.Date(d.getTime()); 
        	    saleCalloutOrder sc=new saleCalloutOrder();
        	    sc.setSaleCalloutOrder(saleCalloutOrder);
        	    sc.setContractnum(contractnum);
        	    sc.setYard(yard);
        	    sc.setDemander(demander);
        	    sc.setSection(section);
        	    sc.setPaymentamount(paymentamount);
        	    sc.setTotalnum(totalnum);
        	    sc.setSigner(signer);
        	    sc.setCallidtime(calliddate);
        	    sc.setSalestatus(3);//销售调令状态，0：表示添加未审核，1：审核通过，2审核未通过，3：审核未通过补交材料
        	    sql="SELECT  sale_order_id FROM sale_callout_orderid where sale_callout_order_id='"+saleCalloutOrder+"'";
        	    saleCalloutOrder fsd=sd.findsaleorderid(sql);
                int saleCallid=fsd.getSaleCallid();
                //System.out.print(saleCallid);
        	    sql="SELECT COUNT(*) FROM sale_callout_orderid WHERE sale_order_id="+saleCallid+"";
        	    double f=sd.findcount(sql);
        	    if(f==1) {
        	    	int flag=sd.updateSaleCallOrderid(sc, saleCallid);
        	    	if(flag>0) {
        	    		for(int i=0;i<id;i++)
                  	  {
                  		JSONArray s=jb.getJSONArray(String.valueOf(i));
                  		 saleCalloutOrder sco=new saleCalloutOrder();
                  		 sco.setSaleCallid(saleCallid);
                  		 sco.setTreetype(s.getString(0));
                      	 sco.setTlong(s.getString(1));
                      	 sco.setTradius(s.getString(2));
                      	 int flagt=sd.addCalloutOrderTree1(sco);
                      	out.print(flagt);
        	    	}
        	    }
        	}
        	    else {
        	    	out.print("不能改变销售调令");
        	    }
        	}
        //货场信息
        	else if(action.equals("loadyardinfo")) {
        		sql="SELECT yardname from yardmanage";
        		List<yardManage> yardname=sd.findyardname(sql);
        		mapper.writeValue(response.getWriter(),yardname);
        	}
      //第一次审核调令
            else if("salecallupdate1".equals(action)) {
            	String str=request.getParameter("saleCallid");
            	str=str.replace("'", "");
            	double number=Double.parseDouble(str);
            	//System.out.println("...."+number + "...");
            	sql="select sale_callout_order_id,contractnum,yard,section,demander,Paymentamount,totalnum,Signer,callidtime,creattime from sale_callout_orderid WHERE sale_order_id="+number+"";
            	saleCalloutOrder saleCalloutOrder=sd.findsaleorder(sql);
            	String yardname=saleCalloutOrder.getYard();
            	sql="select yardmanager from yardmanage WHERE yardname='"+yardname+"'";
            	yardManage ya=wpd.findyardmanager(sql);//管理人员姓名
            	String yardmanager=ya.getYardmanager();
            	String[] sourceStrArray = yardmanager.split("、");
                for (int i = 0; i < sourceStrArray.length; i++) {
                    //System.out.println(sourceStrArray[i]);
                }
                System.out.println(sourceStrArray);
            	sql="SELECT treetype,tlong,tradius from sale_callout_orderid_tree WHERE sale_order_id="+number+"";
            	List<saleCalloutOrder> saleOrdertree=sd.findsaletree(sql);
            	//System.out.print(yardmanager);
            	sql="SELECT * from surveyor_info";
        		List<surveyor> surv=wpd.findSurveyor(sql);
        		request.setAttribute("surveyor", surv);
            	request.setAttribute("saleCalloutOrder", saleCalloutOrder);
            	request.setAttribute("saleOrdertree", saleOrdertree);
            	request.setAttribute("yardmanager", yardmanager);
            	request.setAttribute("sourceStrArray", sourceStrArray);
            	request.getRequestDispatcher("saleCalloutOrderUpdate.jsp").forward(request, response);
            }
        //货场分区
        	else if(action.equals("loadyardsection")) {
        		String yardname = request.getParameter("yardname");
        		sql="SELECT section from yard_section WHERE yardname='"+yardname+"'";
        		List<yardManage> section=sd.findyardSec(sql);
        		mapper.writeValue(response.getWriter(),section);
        	}
        //录入检尺数据
        	else if("luruJianchi".equals(action)) {
        		String str=request.getParameter("saleCalloutOrder");
            	str=str.replace("'", "");
            	sql="select sale_callout_order_id,contractnum,yard,section,demander,Paymentamount,totalnum,Signer,callidtime,creattime from sale_callout_orderid WHERE sale_callout_order_id='"+str+"'";
            	saleCalloutOrder saleCalloutOrder=sd.findsaleorder(sql);
            	String contractnum=saleCalloutOrder.getContractnum();
            	sql="SELECT provider,marknumber from sale_contract WHERE contractnum='"+contractnum+"'";
            	salecontract contractPrivoder=sd.findcontractPrivode(sql);
            	sql="SELECT company,shipaddress from customer WHERE contractnum='"+contractnum+"'";
            	customer compShip=sd.findcutnumAddres(sql);
            	request.setAttribute("saleCalloutOrder", saleCalloutOrder);
            	request.setAttribute("contractPrivoder", contractPrivoder);
            	request.setAttribute("compShip", compShip);
            	request.getRequestDispatcher("treeout.jsp").forward(request, response);
        	}
        //调令完成
        	else if("finishcorder".equals(action)) {
        		String str=request.getParameter("saleCalloutOrder");
            	str=str.replace("'", "");
                saleCalloutOrder sc=new saleCalloutOrder();
        		sc.setSalestatus(3);//调令完成
        		int flag=sd.updateSaleCallorder(sc,str);
        		if(flag>0){
        			out.print("调令完成");
        			}
        	}

	}

}
