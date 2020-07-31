package jinshen.action;
//获得云平台数据信息
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

import jinshen.bean.gpsInfo;
import jinshen.dao.gpsDao;
import jinshen.daoimpl.gpsDaoImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
/**
 * Servlet implementation class gpsServlet
 */
@WebServlet("/gpsServlet")
public class gpsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gpsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.setContentType("textml;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		String sql="";
		gpsDao gd=new gpsDaoImpl();
		//循环数据
		/*if (action.equals("gpsv")) {
		String str=gethttp("http://api.heclouds.com/devices/606617689/datastreams");
			//System.out.println(str);
			JSONObject json=JSONObject.fromObject(str);
			//System.out.println(json);
            JSONArray gps_1 = (JSONArray) json.get("data");
            String errno = String.valueOf(json.get("errno").toString());
            System.out.println("\nerrno: " +errno);
            String error = String.valueOf(json.get("error").toString());
            System.out.print("\nerror: " +error);
            System.out.println(gps_1);
            gpsInfo gI=new gpsInfo();
			gI.setErron(Double.parseDouble(errno));
			gI.setError(Double.parseDouble(error));
			for(int i=0;i<gps_1.length();i++) 
			{				
				JSONObject data1 = (JSONObject)gps_1.get(1);
			    String create_time =(String) data1.get("create_time");
				System.out.println(create_time);
				String update_at = (String) data1.get("update_at");
				System.out.println(update_at);
				String id = (String) data1.get("id");
				System.out.println(id);
				String uuid = (String) data1.get("uuid");
				System.out.println(uuid);
				String current_value = String.valueOf(data1.get("current_value"));//gps数据
				System.out.println(current_value);
				//out.print(str_data5);
				out.print(current_value);
				}
			//out.print(gps_1);
			//out.print(str_data5);
		}*/

		if (action.equals("gpsv")) {
			String str=gethttp("http://api.heclouds.com/devices/606617689/datastreams");
			//System.out.println(str);
			JSONObject json=JSONObject.fromObject(str);
			//System.out.println(json);
            JSONArray gps_1 = (JSONArray) json.get("data");
            String errno = String.valueOf(json.get("errno").toString());
            System.out.println("\nerrno: " +errno);
            String error = String.valueOf(json.get("error").toString());
            System.out.print("\nerror: " +error);
            System.out.println(gps_1);
            //获取data数据			
			JSONObject data1 = (JSONObject)gps_1.get(1);
			//String create_time =(String) data1.get("create_time");
			String create_time = String.valueOf(data1.get("create_time"));
			System.out.println(create_time);
			String update_at = (String) data1.get("update_at");
			System.out.println(update_at);
			String id = (String) data1.get("id");
			System.out.println(id);
			String uuid = (String) data1.get("uuid");
			System.out.println(uuid);
			String current_value = String.valueOf(data1.get("current_value"));//gps数据
			System.out.println(current_value);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d = null;
			Date dd = null;
				try {
					d = format.parse(create_time);
					dd = format.parse(update_at);
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			java.sql.Date creattime = new java.sql.Date(d.getTime());
			java.sql.Date updatetime = new java.sql.Date(dd.getTime());
			gpsInfo gI=new gpsInfo();
			gI.setErron(Double.parseDouble(errno));
			gI.setError(error);
			gI.setCreateTime(creattime);
			gI.setUpdateAt(updatetime);
			gI.setId(id);
			gI.setUuid(uuid);
			gI.setcurrentValue(current_value);
			sql = "select count(*) from gps_infomation where creattime="+creattime+"";
			double f=gd.findcount(sql);
			int flag=0;
			if(f==0) {
				flag=gd.addgpsInfo(gI);
				out.print(flag);
			}
			else {
				out.print("已经存在gps信息");
			}
			
		}
	}
	//获取gps信息
	public static String gethttp(String path) {
		BufferedReader in = null;
        StringBuffer result = null;
        try {
        	URL url=new URL(path);
        	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        	connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
        	connection.setRequestProperty("Charset", "utf-8");
        	connection.setRequestProperty("api-key", "2RDqOgUiQO4A0GnBOVgS2l1W1gE=");  
        	connection.connect();
        	 result = new StringBuffer();
        	 in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        	 String line;
        	 while ((line = in.readLine()) != null) {
                 result.append(line);
             }
        	 return result.toString();
        }catch (Exception e) {
        	e.printStackTrace();
        	}finally {
        		try {
        			if (in != null) {
        				in.close();
        				}
        			}
        		catch (Exception e2) {
        			e2.printStackTrace();
        			}
        		}
        return null;

	}
	

}
