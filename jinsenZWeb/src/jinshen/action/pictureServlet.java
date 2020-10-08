package jinshen.action;
//货场提交装车照片
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import jinshen.bean.codejson;
import jinshen.bean.compareTree;
import jinshen.bean.inyard;
import jinshen.bean.tree;
import jinshen.bean.workpage;
import jinshen.bean.workpageStatus;
import jinshen.dao.treeDao;
import jinshen.dao.workpageDao;
import jinshen.daoimpl.treeDaoImpl;
import jinshen.daoimpl.workpageDaoImpl;
import net.sf.json.JSONArray;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class pictureServlet
 */
@WebServlet("/pictureServlet")
public class pictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private workpageDao d;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pictureServlet() {
    	d = new workpageDaoImpl();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		workpageDao wpd=new workpageDaoImpl();
		treeDao trd=new treeDaoImpl();
		String sql="";
		ObjectMapper mapper = new ObjectMapper();
		if(action.equals("treecompare")) {
			double workid=Double.parseDouble(request.getParameter("workid"));
			String pic = request.getParameter("pic");
			String da = request.getParameter("da");
			
			
			compareTree t = new compareTree();
			//System.out.println(workid);
			t.setWorkid(workid);
			t.setFile(da);
			t.setPic(pic);
			
			int i = d.insertcompareTree(t);
			if(i>0) {
				   workpageStatus ws=new workpageStatus();
				   ws.setWorkidstatus(4);//货场管理员装车对比拍照状态为48
				   int wpp=wpd.updateWorkpagestatus1(workid,ws);
					System.out.println(wpp);
					if(wpp>0) {
						out.print("保存状态成功");
					}
			    else {
			    	out.print("保存状态失败");		
			    }
				//out.print("保存图片成功");
			}
			//response.sendRedirect("compareTree.jsp");
		}
		else if("treecompare1".equals(action)) {
			//double workid=Double.parseDouble(request.getParameter("workid"));
			//String da = request.getParameter("da");
			// 获得路径使用
			ServletConfig config = this.getServletConfig();
			// 创建对象
			SmartUpload mySmartUpload = new SmartUpload();
			// 初始化
			mySmartUpload.initialize(config, request, response);
			double workid=0;
			String da="";
			String flag = "未上传";
			String readImgPath="";
			try {
				// 初始化上传图片
				mySmartUpload.upload();
				// 这里是获取jsp页面中的input（file）的属性，切记input（file）中要写name属性，否则会报1205错误
				com.jspsmart.upload.File f1 = mySmartUpload.getFiles().getFile(0);
				workid=Double.parseDouble(mySmartUpload.getRequest().getParameter("workid"));
				//com.jspsmart.upload.File f2 = mySmartUpload.getFiles().getFile(1);
				//da=mySmartUpload.getRequest().getParameter("da");
				// 切记这下面获得图片名字的方法是getFileName（），如果写成getFiledName（），将获得的是jsp页面中input输入框的名字
				String imgName = f1.getFileName();			// System.out.println("这是获得图片名字："+imgName);
				// 读取图片名字中。的位置，是为了得到图片的格式
				int d = imgName.lastIndexOf(".");
				// 截取字符串，获得图片的格式（加。）
				String imgType = imgName.substring(d, imgName.length());
				// System.out.println("这是获得图片类型："+imgType);
				// 根据时间产生一个字符串，作为图片的名字
				String NewImgName = String.valueOf(System.currentTimeMillis());
				// 得到图片将要存储的位置
				String Path = "D:\\jee-2019-122\\workOne\\jinsen\\WebContent\\images";
				// 如果上面的文件夹不存在，则创建文件夹
				File file = new File(Path);
				// 判断问价夹是否存在
				if (!file.exists()) {
					file.mkdirs();
				}
				// 设置图片存储的真正路径，保存在tomcat下webapps中相应工程下的images文件夹中
				String imgPath = Path + File.separator + NewImgName + imgType;
				flag = "已上传";
				// 以新的路径和名字保存图片
				f1.saveAs(imgPath);
				// 存放浏览器访问图片的路径
				readImgPath = "../images/" + NewImgName + imgType;
				System.out.println("imgPath:" + imgPath);
				//request.setAttribute("readImgPath", readImgPath);
				//request.setAttribute("flag", flag);
				 System.out.println("readImgPath:"+readImgPath);
			} catch (SmartUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			compareTree t = new compareTree();
			//System.out.println(workid);
			t.setWorkid(workid);
			t.setFile(da);
			t.setPic(readImgPath);
			
			int i = d.insertcompareTree(t);
			if(i>0) {
				   workpageStatus ws=new workpageStatus();
				   ws.setWorkidstatus(5);//货场管理员装车对比拍照状态为4//审核通过为5
				   int wpp=wpd.updateWorkpagestatus1(workid,ws);
					System.out.println(wpp);
					if(wpp>0) {
						out.print("保存状态成功");
					}
			    else {
			    	out.print("保存状态失败");		
			    }
				out.print("保存图片成功");
			}
		}
		//上传装车照片
		else if("treecompare2".equals(action)) {
			String da="";
			compareTree t = new compareTree();
			String savePath = this.getServletContext().getRealPath("/WEB-INF/comparePic");
			//得到文件访问的相对路径
			String readPath = "../WEB-INF/comparePic/";
			double workid=0;
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
							t.setWorkid(Double.parseDouble(item.getString("UTF-8")));//工单号
							workid=Double.parseDouble(item.getString("UTF-8"));
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
						request.setAttribute("filename", filename);
						// 获取item中的上传文件的输入流
						InputStream in = item.getInputStream();
						// 创建一个文件输出流
						FileOutputStream outt = new FileOutputStream(savePath + "\\" + filename);
						//输出文件保存路径
						System.out.println("savePath:" + savePath + "\\" +filename);
						//显示文件读取的相对路径
						readPath = readPath+ filename;
						t.setPic(readPath);//图片相对路径
						t.setFile(da);
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
			int flag=d.insertcompareTree(t);
			if(flag>0) {
				   workpageStatus ws=new workpageStatus();
				   ws.setWorkidstatus(8);//货场管理员装车对比拍照状态为4
				   int wpp=wpd.updateWorkpagestatus1(workid,ws);
					//System.out.println(wpp);
					if(wpp>0) {
						out.print("保存状态成功");
					}
			    else {
			    	out.print("保存状态失败");		
			    }
				//out.print("保存图片成功");
			}
			//request.setAttribute("message", message);
			//request.setAttribute("readPath", readPath);
			//request.getRequestDispatcher("message.jsp").forward(request, response);
			//compareTree t = new compareTree();
			//System.out.println(workid);
			/*t.setWorkid(workid);
			t.setFile(da);
			t.setPic(readImgPath);
			
			int i = d.insertcompareTree(t);
			if(i>0) {
				   workpageStatus ws=new workpageStatus();
				   ws.setWorkidstatus(8);//货场管理员装车对比拍照状态为8
				   int wpp=wpd.updateWorkpagestatus1(workid,ws);
					System.out.println(wpp);
					if(wpp>0) {
						out.print("保存状态成功");
					}
			    else {
			    	out.print("保存状态失败");		
			    }
				//out.print("保存图片成功");
			}*/
		}
		else if("writepage".equals(action))
        {
        	String mytype = request.getParameter("type");
        	if(mytype.equals("all"))
        	{
        		sql="select c.workid,c.cutNum,c.cutdate,c.cutSite,c.forester from workpage as c JOIN workpage_status join comparetree on c.workid=workpage_status.workid and workpage_status.workid=comparetree.workid";
        		List<codejson> work=wpd.findcodeJson(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//未通过
        	else if(mytype.equals("notpass"))
        	{
        		sql="select c.workid,c.cutNum,c.cutdate,c.cutSite,c.forester from workpage as c JOIN workpage_status join comparetree on c.workid=workpage_status.workid and workpage_status.workid=comparetree.workid WHERE workpage_status.workid_status=6";
        		List<codejson> work=wpd.findcodeJson(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//通过
        	else if(mytype.equals("pass"))
        	{
        		sql="select c.workid,c.cutNum,c.cutdate,c.cutSite,c.forester from workpage as c JOIN workpage_status join comparetree on c.workid=workpage_status.workid and workpage_status.workid=comparetree.workid WHERE workpage_status.workid_status=5 or workpage_status.workid_status>6";
        		List<codejson> work=wpd.findcodeJson(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//未审核
        	else if(mytype.equals("notshenhe"))
        	{
        		sql="select c.workid,c.cutNum,c.cutdate,c.cutSite,c.forester from workpage as c JOIN workpage_status join comparetree on c.workid=workpage_status.workid and workpage_status.workid=comparetree.workid WHERE workpage_status.workid_status=4";
        		List<codejson> work=wpd.findcodeJson(sql);
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
            		 sql="delete from comparetree where workid="+each+"";
            		 int j=wpd.updateWork(sql);
            		 if(j==0)
            		 {
            			 out.print("删除失败！");
            			 break;
            		 }
            	}
        	}
        }
        //暂时没用
    	else if("searchstatus".equals(action))
        {
        	sql="select workid,cutNum,cutSite,surveyor from workpage where surveyor like '0'";
        	List<codejson> work=wpd.findcodeJson(sql);
        	mapper.writeValue(response.getWriter(), work);
        }
    	else if(action.equals("deletework"))
		 {
    		double workid=Double.parseDouble(request.getParameter("workid"));
				workpageDaoImpl ac=new workpageDaoImpl();
       		   boolean b=ac.delete_work(workid);
	    		mapper.writeValue(response.getWriter(),b);
		 }
		 //信息中心审核采伐工单
    	else if(action.equals("singlecomparetree1"))
    	{
    		String str=request.getParameter("workid");
    		str=str.replace("'", "");
    		double workid=Double.parseDouble(str);
    		sql="select pic from comparetree where workid="+workid+"";
    		compareTree picpath=wpd.findcompares1(sql);
    		sql="select * from workpage where workid="+workid+"";
       		workpage workpage=wpd.findCodeSingle(sql);
        	//sql="select workid,cutNum,yarddate,cutSite,carNumber,yard,surveyor,toltree,tolstere,section from inyard where workid="+workid+"";
			//inyard inyard=trd.findInSingle(sql);
			//sql="select workid,treetype,tlong,tradius,num,tvolume from tree where workid="+workid+"";
			//List<tree> tree=trd.findTree(sql);
    		request.setAttribute("workpage", workpage);
			//request.setAttribute("inyard", inyard);
			//request.setAttribute("tree", tree);
    		request.setAttribute("picpath", picpath);
		    request.getRequestDispatcher("compareTreeUpdate.jsp").forward(request, response);
    	}
		//删除木材装车照片
    	else if("alldelete1".equals(action))
        {
        	String mygroup = request.getParameter("workid");
        	//workpage ac=new workpage();
        	sql="delete from comparetree where workid='"+mygroup+"'";
        	int i=wpd.delWorkPage(sql);
        	ObjectMapper mapper1=new ObjectMapper();
    		mapper1.writeValue(response.getWriter(),i);
        }
	}

}
