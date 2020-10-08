package jinshen.action;
/*画超级管理员页面折线图，柱形图。木材报表导出*/
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import jinshen.bean.cutnum;
import jinshen.bean.producetree;
import jinshen.bean.projectPackTable;
import jinshen.bean.salemansql;
import jinshen.bean.treeChart;
import jinshen.bean.treeoutChart;
import jinshen.bean.treeoutPrint;
import jinshen.bean.treetypeChart;
import jinshen.dao.chartDao;
import jinshen.daoimpl.chartDaoImpl;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class chartServlet
 */
@WebServlet("/chartServlet")
public class chartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public chartServlet() {
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
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession();
        ObjectMapper mapper = new ObjectMapper();
        chartDao cd= new chartDaoImpl();
        //木材销售价格折线图（时间）
        if(action.equals("treechart")) {
        	List<treeChart>  tc =new ArrayList<treeChart>();
        	tc=cd.treechart();
        	ObjectMapper map = new ObjectMapper();
        	String json=map.writeValueAsString(tc);
        	//System.out.println(tc);
        	response.getWriter().write(json);
			//map.writeValue(response.getWriter(), tc);	
        }
        //木材库存
        else if(action.equals("treetype")) {
        	List<treetypeChart> ty = new ArrayList<treetypeChart>();
        	ty=cd.treetypechart();
        	ObjectMapper map = new ObjectMapper();
        	String json=map.writeValueAsString(ty);
        	response.getWriter().write(json);
        }
        //木材销售
        else if(action.equals("treeout")) {
        	List<treeoutChart> ty = new ArrayList<treeoutChart>();
        	ty=cd.treeoutChart();
        	ObjectMapper map = new ObjectMapper();
        	String json=map.writeValueAsString(ty);
        	response.getWriter().write(json);
        }
        //木材生产销售报表
        else if(action.equals("producetree13")) {
        	String year = request.getParameter("year");
        	String month = request.getParameter("month");
        	//System.out.println("...." +month + "...");
        	List<producetree> dp = cd.findProduce();
        	//System.out.println("...."+dp + "...");
			List<producetree> dplist = new ArrayList<producetree>();
			if(dp != null) {
				if (month.equals("1-12"))
				{
					for(int i = 0;i<dp.size();i ++)
					{
					producetree d = cd.printProduceY(Integer.valueOf(year));
					d.setYear(dp.get(i).getYear());
					d.setMonth(dp.get(i).getMonth());
					d.setDayt(dp.get(i).getDayt());
					d.setCutNum(dp.get(i).getCutNum());
					d.setCutSite(dp.get(i).getCutSite());
					d.setCheckSite(dp.get(i).getCheckSite());
					d.setCarNumber(dp.get(i).getCarNumber());
					d.setYard(dp.get(i).getYard());
					d.setTreetype(dp.get(i).getTreetype());
					d.setTlong(dp.get(i).getTlong());
					d.setTradius(dp.get(i).getTradius());
					d.setNum(dp.get(i).getNum());
					d.setTvolume(dp.get(i).getTvolume());
					d.setSurveyor(dp.get(i).getSurveyor());
					d.setForester(dp.get(i).getForester());
					dplist.add(d);
					}
				}
				else if(month.equals("1-3")) {
					int month1=1;
					int month2=2;
					int month3=3;
					for(int i = 0;i<dp.size();i ++) {
						producetree d = cd.printProduceM(Integer.valueOf(year),month1,month2,month3);
						if((Integer.valueOf(year) == dp.get(i).getYear() && (month1== dp.get(i).getMonth() || month2== dp.get(i).getMonth() || month3== dp.get(i).getMonth())))
						{
							d.setYear(dp.get(i).getYear());
							d.setMonth(dp.get(i).getMonth());
							d.setDayt(dp.get(i).getDayt());
							d.setCutNum(dp.get(i).getCutNum());
							d.setCutSite(dp.get(i).getCutSite());
							d.setCheckSite(dp.get(i).getCheckSite());
							d.setCarNumber(dp.get(i).getCarNumber());
							d.setYard(dp.get(i).getYard());
							d.setTreetype(dp.get(i).getTreetype());
							d.setTlong(dp.get(i).getTlong());
							d.setTradius(dp.get(i).getTradius());
							d.setNum(dp.get(i).getNum());
							d.setTvolume(dp.get(i).getTvolume());
							d.setSurveyor(dp.get(i).getSurveyor());
							d.setForester(dp.get(i).getForester());
							dplist.add(d);
						}
					}
				}
				else if(month.equals("4-6")) {
					int month1=4;
					int month2=5;
					int month3=6;
					for(int i = 0;i<dp.size();i ++) {
						producetree d = cd.printProduceM(Integer.valueOf(year),month1,month2,month3);
						if((Integer.valueOf(year) == dp.get(i).getYear() && (month1== dp.get(i).getMonth() || month2== dp.get(i).getMonth() || month3== dp.get(i).getMonth())))
						{
							d.setYear(dp.get(i).getYear());
							d.setMonth(dp.get(i).getMonth());
							d.setDayt(dp.get(i).getDayt());
							d.setCutNum(dp.get(i).getCutNum());
							d.setCutSite(dp.get(i).getCutSite());
							d.setCheckSite(dp.get(i).getCheckSite());
							d.setCarNumber(dp.get(i).getCarNumber());
							d.setYard(dp.get(i).getYard());
							d.setTreetype(dp.get(i).getTreetype());
							d.setTlong(dp.get(i).getTlong());
							d.setTradius(dp.get(i).getTradius());
							d.setNum(dp.get(i).getNum());
							d.setTvolume(dp.get(i).getTvolume());
							d.setSurveyor(dp.get(i).getSurveyor());
							d.setForester(dp.get(i).getForester());
							dplist.add(d);
						}
					}
				}
				else if(month.equals("7-9")) {
					int month1=7;
					int month2=8;
					int month3=9;
					for(int i = 0;i<dp.size();i ++) {
						producetree d = cd.printProduceM(Integer.valueOf(year),month1,month2,month3);
						if((Integer.valueOf(year) == dp.get(i).getYear() && (month1== dp.get(i).getMonth() || month2== dp.get(i).getMonth() || month3== dp.get(i).getMonth())))
						{
							d.setYear(dp.get(i).getYear());
							d.setMonth(dp.get(i).getMonth());
							d.setDayt(dp.get(i).getDayt());
							d.setCutNum(dp.get(i).getCutNum());
							d.setCutSite(dp.get(i).getCutSite());
							d.setCheckSite(dp.get(i).getCheckSite());
							d.setCarNumber(dp.get(i).getCarNumber());
							d.setYard(dp.get(i).getYard());
							d.setTreetype(dp.get(i).getTreetype());
							d.setTlong(dp.get(i).getTlong());
							d.setTradius(dp.get(i).getTradius());
							d.setNum(dp.get(i).getNum());
							d.setTvolume(dp.get(i).getTvolume());
							d.setSurveyor(dp.get(i).getSurveyor());
							d.setForester(dp.get(i).getForester());
							dplist.add(d);
						}
					}
				}
				else if(month.equals("10-12")) {
					int month1=10;
					int month2=11;
					int month3=12;
					for(int i = 0;i<dp.size();i ++) {
						producetree d = cd.printProduceM(Integer.valueOf(year),month1,month2,month3);
						if((Integer.valueOf(year) == dp.get(i).getYear() && (month1== dp.get(i).getMonth() || month2== dp.get(i).getMonth() || month3== dp.get(i).getMonth())))
						{
							d.setYear(dp.get(i).getYear());
							d.setMonth(dp.get(i).getMonth());
							d.setDayt(dp.get(i).getDayt());
							d.setCutNum(dp.get(i).getCutNum());
							d.setCutSite(dp.get(i).getCutSite());
							d.setCheckSite(dp.get(i).getCheckSite());
							d.setCarNumber(dp.get(i).getCarNumber());
							d.setYard(dp.get(i).getYard());
							d.setTreetype(dp.get(i).getTreetype());
							d.setTlong(dp.get(i).getTlong());
							d.setTradius(dp.get(i).getTradius());
							d.setNum(dp.get(i).getNum());
							d.setTvolume(dp.get(i).getTvolume());
							d.setSurveyor(dp.get(i).getSurveyor());
							d.setForester(dp.get(i).getForester());
							dplist.add(d);
						}
					}
				}
				else
					{
					for(int i = 0;i<dp.size();i ++) 
					   {
						 producetree d = cd.printProduce(Integer.valueOf(year),Integer.valueOf(month));
                     if ((Integer.valueOf(year) == dp.get(i).getYear() && Integer.valueOf(month) == dp.get(i).getMonth()))
						 {
							d.setYear(dp.get(i).getYear());
							d.setMonth(dp.get(i).getMonth());
							d.setDayt(dp.get(i).getDayt());
							d.setCutNum(dp.get(i).getCutNum());
							d.setCutSite(dp.get(i).getCutSite());
							d.setCheckSite(dp.get(i).getCheckSite());
							d.setCarNumber(dp.get(i).getCarNumber());
							d.setYard(dp.get(i).getYard());
							d.setTreetype(dp.get(i).getTreetype());
							d.setTlong(dp.get(i).getTlong());
							d.setTradius(dp.get(i).getTradius());
							d.setNum(dp.get(i).getNum());
							d.setTvolume(dp.get(i).getTvolume());
							//
							d.setSurveyor(dp.get(i).getSurveyor());
							d.setForester(dp.get(i).getForester());
							dplist.add(d);
							//System.out.println("...." +dplist+ "...");
						}
					}
					}
			}
			//System.out.println("...." +dplist + "...");
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), dplist);
        }
        //木材销售报表
//        else if(action.equals("treeoutTable1")) {
//        	String year = request.getParameter("year");
//        	String month = request.getParameter("month");
//        	//System.out.println("...." +month + "...");
//        	List<treeoutPrint> dp = cd.findTreeout();
//        	//System.out.println("...."+dp + "...");
//			List<treeoutPrint> dplist = new ArrayList<treeoutPrint>();
//			if(dp != null) {
//				if (month.equals("1-12"))
//				{
//					for(int i = 0;i<dp.size();i ++)
//					{
//				    treeoutPrint d = cd.printTreeoutY(Integer.valueOf(year));
//					d.setWorkid(dp.get(i).getWorkid());
//					d.setYear(dp.get(i).getYear());
//					d.setMonth(dp.get(i).getMonth());
//					d.setCarNumber(dp.get(i).getCarNumber());
//					d.setYard(dp.get(i).getYard());
//					d.setSection(dp.get(i).getSection());
//					d.setTreetype(dp.get(i).getTreetype());
//					d.setTlong(dp.get(i).getTlong());
//					d.setTradius(dp.get(i).getTradius());
//					d.setNum(dp.get(i).getNum());
//					d.setTvolume(dp.get(i).getTvolume());
//					d.setUnitprice(dp.get(i).getUnitprice());
//					d.setPrice(dp.get(i).getPrice());
//					d.setSurveyor(dp.get(i).getSurveyor());
//					d.setSaleman(dp.get(i).getSaleman());
//					dplist.add(d);
//					}
//				}
//				else if(month.equals("1-3")) {
//					int month1=1;
//					int month2=2;
//					int month3=3;
//					for(int i = 0;i<dp.size();i ++) {
//						treeoutPrint d = cd.printTreeoutM(Integer.valueOf(year),month1,month2,month3);
//						if((Integer.valueOf(year) == dp.get(i).getYear() && (month1== dp.get(i).getMonth() || month2== dp.get(i).getMonth() || month3== dp.get(i).getMonth())))
//						{
//							d.setWorkid(dp.get(i).getWorkid());
//	     					   d.setYear(dp.get(i).getYear());
//	     					   d.setMonth(dp.get(i).getMonth());
//	     					   d.setCarNumber(dp.get(i).getCarNumber());
//	     					   d.setYard(dp.get(i).getYard());
//	     					   d.setSection(dp.get(i).getSection());
//	     					   d.setTreetype(dp.get(i).getTreetype());
//	     					   d.setTlong(dp.get(i).getTlong());
//	     					   d.setTradius(dp.get(i).getTradius());
//	     					   d.setNum(dp.get(i).getNum());
//	     					   d.setTvolume(dp.get(i).getTvolume());
//	     					   d.setUnitprice(dp.get(i).getUnitprice());
//	     					   d.setPrice(dp.get(i).getPrice());
//	     					   d.setSurveyor(dp.get(i).getSurveyor());
//	     					   d.setSaleman(dp.get(i).getSaleman());
//								dplist.add(d);
//						}
//					}
//				}
//				else if(month.equals("4-6")) {
//					int month1=4;
//					int month2=5;
//					int month3=6;
//					for(int i = 0;i<dp.size();i ++) {
//						treeoutPrint d = cd.printTreeoutM(Integer.valueOf(year),month1,month2,month3);
//						if((Integer.valueOf(year) == dp.get(i).getYear() && (month1== dp.get(i).getMonth() || month2== dp.get(i).getMonth() || month3== dp.get(i).getMonth())))
//						{
//							d.setWorkid(dp.get(i).getWorkid());
//	     					   d.setYear(dp.get(i).getYear());
//	     					   d.setMonth(dp.get(i).getMonth());
//	     					   d.setCarNumber(dp.get(i).getCarNumber());
//	     					   d.setYard(dp.get(i).getYard());
//	     					   d.setSection(dp.get(i).getSection());
//	     					   d.setTreetype(dp.get(i).getTreetype());
//	     					   d.setTlong(dp.get(i).getTlong());
//	     					   d.setTradius(dp.get(i).getTradius());
//	     					   d.setNum(dp.get(i).getNum());
//	     					   d.setTvolume(dp.get(i).getTvolume());
//	     					   d.setUnitprice(dp.get(i).getUnitprice());
//	     					   d.setPrice(dp.get(i).getPrice());
//	     					   d.setSurveyor(dp.get(i).getSurveyor());
//	     					   d.setSaleman(dp.get(i).getSaleman());
//								dplist.add(d);
//						}
//					}
//				}
//				else if(month.equals("7-9")) {
//					int month1=7;
//					int month2=8;
//					int month3=9;
//					for(int i = 0;i<dp.size();i ++) {
//						treeoutPrint d = cd.printTreeoutM(Integer.valueOf(year),month1,month2,month3);
//						if((Integer.valueOf(year) == dp.get(i).getYear() && (month1== dp.get(i).getMonth() || month2== dp.get(i).getMonth() || month3== dp.get(i).getMonth())))
//						{
//							d.setWorkid(dp.get(i).getWorkid());
//	     					   d.setYear(dp.get(i).getYear());
//	     					   d.setMonth(dp.get(i).getMonth());
//	     					   d.setCarNumber(dp.get(i).getCarNumber());
//	     					   d.setYard(dp.get(i).getYard());
//	     					   d.setSection(dp.get(i).getSection());
//	     					   d.setTreetype(dp.get(i).getTreetype());
//	     					   d.setTlong(dp.get(i).getTlong());
//	     					   d.setTradius(dp.get(i).getTradius());
//	     					   d.setNum(dp.get(i).getNum());
//	     					   d.setTvolume(dp.get(i).getTvolume());
//	     					   d.setUnitprice(dp.get(i).getUnitprice());
//	     					   d.setPrice(dp.get(i).getPrice());
//	     					   d.setSurveyor(dp.get(i).getSurveyor());
//	     					   d.setSaleman(dp.get(i).getSaleman());
//								dplist.add(d);
//						}
//					}
//				}
//				else if(month.equals("10-12")) {
//					int month1=10;
//					int month2=11;
//					int month3=12;
//					for(int i = 0;i<dp.size();i ++) {
//						treeoutPrint d = cd.printTreeoutM(Integer.valueOf(year),month1,month2,month3);
//						if((Integer.valueOf(year) == dp.get(i).getYear() && (month1== dp.get(i).getMonth() || month2== dp.get(i).getMonth() || month3== dp.get(i).getMonth())))
//						{
//							d.setWorkid(dp.get(i).getWorkid());
//	     					   d.setYear(dp.get(i).getYear());
//	     					   d.setMonth(dp.get(i).getMonth());
//	     					   d.setCarNumber(dp.get(i).getCarNumber());
//	     					   d.setYard(dp.get(i).getYard());
//	     					  d.setSection(dp.get(i).getSection());
//	     					   d.setTreetype(dp.get(i).getTreetype());
//	     					   d.setTlong(dp.get(i).getTlong());
//	     					   d.setTradius(dp.get(i).getTradius());
//	     					   d.setNum(dp.get(i).getNum());
//	     					   d.setTvolume(dp.get(i).getTvolume());
//	     					   d.setUnitprice(dp.get(i).getUnitprice());
//	     					   d.setPrice(dp.get(i).getPrice());
//	     					   d.setSurveyor(dp.get(i).getSurveyor());
//	     					   d.setSaleman(dp.get(i).getSaleman());
//								dplist.add(d);
//						}
//					}
//				}
//				else
//					{
//					for(int i = 0;i<dp.size();i ++) 
//					   {
//						treeoutPrint d = cd.printTreeout(Integer.valueOf(year),Integer.valueOf(month));
//						//System.out.println("...." +d+ "...");
//                       if ((Integer.valueOf(year) == dp.get(i).getYear() && Integer.valueOf(month) == dp.get(i).getMonth()))
//						 {
//                    	   d.setWorkid(dp.get(i).getWorkid());
//     					   d.setYear(dp.get(i).getYear());
//     					   d.setMonth(dp.get(i).getMonth());
//     					   d.setCarNumber(dp.get(i).getCarNumber());
//     					   d.setYard(dp.get(i).getYard());
//     					   d.setSection(dp.get(i).getSection());
//     					   d.setTreetype(dp.get(i).getTreetype());
//     					   d.setTlong(dp.get(i).getTlong());
//     					   d.setTradius(dp.get(i).getTradius());
//     					   d.setNum(dp.get(i).getNum());
//     					   d.setTvolume(dp.get(i).getTvolume());
//     					   d.setUnitprice(dp.get(i).getUnitprice());
//     					   d.setPrice(dp.get(i).getPrice());
//     					   d.setSurveyor(dp.get(i).getSurveyor());
//     					   d.setSaleman(dp.get(i).getSaleman());
//							dplist.add(d);
//						}
//					}
//					}
//			}
//			//System.out.println("...." +dplist + "...");
//			ObjectMapper map = new ObjectMapper();
//			map.writeValue(response.getWriter(), dplist);
//        }
        //销售管理主页面销售报表的显示
        else if(action.equals("saleman")) {
        	List<salemansql> dp = cd.findsaleman();
        	List<salemansql> dplist = new ArrayList<salemansql>();
        	if(dp != null) {
        		for(int i = 0;i<dp.size();i ++) {
        			salemansql s=cd.printsaleman();
        			s.setWorkid(dp.get(i).getWorkid());
        			s.setTreetype(dp.get(i).getTreetype());
        			s.setPrice(dp.get(i).getPrice());
        			dplist.add(s);
        		}
        	}
        	ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), dplist);
        }
        //木材生产详细台账
        /*else if(action.equals("producetree1")) {
        	String timeStart = request.getParameter("timeStart");
        	String timeEnd = request.getParameter("timeEnd");
        	String treetype = request.getParameter("treetype");
        	String tradius = request.getParameter("tradius");
        	String yard = request.getParameter("yard");
        	System.out.println("...." +treetype + "...");
        	String sql="";
        	if(!timeStart.isEmpty() && !timeEnd.isEmpty() && yard.isEmpty() && treetype.isEmpty() && tradius.isEmpty()) 
        	{
        	sql="select i.yarddate,w.cutNum,i.yard,t.treetype,t.tlong,t.tradius,\r\n" + 
        			"sum(DISTINCT t.num) as tnum,sum(DISTINCT t.tvolume)as ttvolume,i.surveyor,\r\n" + 
        			"w.forester from workpage as w JOIN tree as t on w.workid=t.workid join inyard as i on w.workid=i.workid WHERE i.yarddate>='"+timeStart+"' AND i.yarddate<='"+timeEnd+"'  GROUP BY i.yarddate,w.cutNum,i.yard,t.treetype,t.tlong,t.tradius,i.surveyor,w.forester ORDER BY i.yarddate DESC";
        	List<producetree> dp = cd.findProduceDet(sql);
        	mapper.writeValue(response.getWriter(), dp);
        	}
        	else if(!timeStart.isEmpty() && !timeEnd.isEmpty() && !yard.isEmpty() && treetype.isEmpty() && tradius.isEmpty())
        	{
        		sql="select i.yarddate,w.cutNum,i.yard,t.treetype,t.tlong,t.tradius,\r\n" + 
        				"sum(DISTINCT t.num) as tnum,sum(DISTINCT t.tvolume)as ttvolume,i.surveyor,\r\n" + 
        				"w.forester from workpage as w JOIN tree as t on w.workid=t.workid join inyard as i on w.workid=i.workid WHERE i.yarddate>='"+timeStart+"' AND i.yarddate<='"+timeEnd+"' and i.yard='"+yard+"' GROUP BY i.yarddate,w.cutNum,i.yard,t.treetype,t.tlong,t.tradius,i.surveyor,w.forester ORDER BY i.yarddate DESC";
        		List<producetree> dp = cd.findProduceDet(sql);
            	mapper.writeValue(response.getWriter(), dp);
        	}
        	else if(!timeStart.isEmpty() && !timeEnd.isEmpty() && !yard.isEmpty() && !treetype.isEmpty() && tradius.isEmpty())
        	{
        		sql="select i.yarddate,w.cutNum,i.yard,t.treetype,t.tlong,t.tradius,\r\n" + 
        				"sum(DISTINCT t.num) as tnum,sum(DISTINCT t.tvolume)as ttvolume,i.surveyor,\r\n" + 
        				"w.forester from workpage as w JOIN tree as t on w.workid=t.workid join inyard as i on w.workid=i.workid WHERE i.yarddate>='"+timeStart+"' AND i.yarddate<='"+timeEnd+"' and i.yard='"+yard+"' and t.treetype='"+treetype+"' GROUP BY i.yarddate,w.cutNum,i.yard,t.treetype,t.tlong,t.tradius,i.surveyor,w.forester ORDER BY i.yarddate DESC";
        		List<producetree> dp = cd.findProduceDet(sql);
            	mapper.writeValue(response.getWriter(), dp);
        	}
        	else if(!timeStart.isEmpty() && !timeEnd.isEmpty() && !yard.isEmpty() && !treetype.isEmpty() && !tradius.isEmpty())
        	{
        		sql="select i.yarddate,w.cutNum,i.yard,t.treetype,t.tlong,t.tradius,\r\n" + 
        				"sum(DISTINCT t.num) as tnum,sum(DISTINCT t.tvolume)as ttvolume,i.surveyor,\r\n" + 
        				"w.forester from workpage as w JOIN tree as t on w.workid=t.workid join inyard as i on w.workid=i.workid WHERE i.yarddate>='"+timeStart+"' AND i.yarddate<='"+timeEnd+"' and i.yard='"+yard+"' and t.treetype='"+treetype+"' and t.tradius='"+tradius+"' GROUP BY w.cutdate,w.cutNum,i.yard,t.treetype,t.tlong,t.tradius,i.surveyor,w.forester ORDER BY i.yarddate DESC";
        		List<producetree> dp = cd.findProduceDet(sql);
            	mapper.writeValue(response.getWriter(), dp);
        	}
        	
        }*/
        
        else if(action.equals("producetree1")) {
        	String timeStart = request.getParameter("timeStart");
        	String timeEnd = request.getParameter("timeEnd");
        	String treetype = request.getParameter("treetype");
        	String tradius = request.getParameter("tradius");
        	String yard = request.getParameter("yard");
        	String cutnum = request.getParameter("cutnum");
        	String tlong = request.getParameter("tlong");
        	String projectPackageName = request.getParameter("projectPackageName");
        	String contractionSide = request.getParameter("contractionSide");
        	//System.out.println("...." +yard + "...");
        	String sql="";
        	sql="select i.yarddate,w.cutNum,i.yard,t.treetype,t.tlong,t.tradius,\r\n" + 
        			"sum(DISTINCT t.num) as tnum,sum(DISTINCT t.tvolume)as ttvolume,i.surveyor,\r\n" + 
        			"w.forester,c.proj_package_Name,p.contractionSide from workpage as w JOIN tree as t on w.workid=t.workid join inyard as i on w.workid=i.workid\r\n" + 
        			"JOIN cutnum c on w.cutNum=c.cutnum JOIN proj_package p on c.proj_package_Name=p.proj_package_Name where 1=1";
        	if(!timeStart.isEmpty() && !timeEnd.isEmpty()) {
        		sql=sql+" and i.yarddate>='"+timeStart+"' AND i.yarddate<='"+timeEnd+"'";
        	}
        	if(!treetype.isEmpty()) {
        		sql=sql+" and t.treetype='"+treetype+"'";
        	}
        	if(!tradius.isEmpty())
        	{
        		sql=sql+" and t.tradius='"+tradius+"'";
        	}
        	if(!tlong.isEmpty())
        	{
        		sql=sql+" and t.tlong='"+tlong+"'";
        	}
        	if(!yard.isEmpty())
        	{
        		sql=sql+" and i.yard='"+yard+"'";
        	}
        	if(!cutnum.isEmpty())
        	{
        		sql=sql+" and w.cutNum='"+cutnum+"'";
        	}
        	if(!contractionSide.isEmpty())
        	{
        		sql=sql+" and p.contractionSide='"+contractionSide+"'";
        	}
        	if(!projectPackageName.isEmpty())
        	{
        		sql=sql+" and c.proj_package_Name='"+projectPackageName+"'";
        	}
        	///System.out.print(sql);
        	sql=sql+" GROUP BY i.yarddate,w.cutNum,i.yard,t.treetype,t.tlong,t.tradius,i.surveyor,w.forester,c.proj_package_Name,p.contractionSide ORDER BY i.yarddate DESC,w.cutNum";
        	List<producetree> dp = cd.findProduceDet(sql);
        	mapper.writeValue(response.getWriter(), dp);	
        }
        
        //木材产出库存详细
        /*else if("totalproduce".equals(action)) {
        	String timeStart = request.getParameter("timeStart");
        	String timeEnd = request.getParameter("timeEnd");
        	String treetype = request.getParameter("treetype");
        	String tradius = request.getParameter("tradius");
        	String yard = request.getParameter("yard");
        	System.out.println("...." +treetype + "...");
        	String sql="";
        	if(!timeStart.isEmpty() && !timeEnd.isEmpty() && yard.isEmpty() && treetype.isEmpty() && tradius.isEmpty()) 
        	{
        	sql="select i.yard,t.treetype,sum(t.num) as tnum,round(sum(t.tvolume),4) as ttvolume\r\n" + 
        			" from workpage as w JOIN tree as t on w.workid=t.workid join inyard as i on w.workid=i.workid WHERE i.yarddate>='"+timeStart+"' AND i.yarddate<='"+timeEnd+"' GROUP BY i.yard,t.treetype ORDER BY i.yard,t.treetype";
        	List<producetree> dp = cd.findProduceTol(sql);
        	mapper.writeValue(response.getWriter(), dp);
        	}
        	else if(!timeStart.isEmpty() && !timeEnd.isEmpty() && !yard.isEmpty() && treetype.isEmpty() && tradius.isEmpty())
        	{
        		sql="select i.yard,t.treetype,sum(t.num) as tnum,round(sum(t.tvolume),4) as ttvolume\r\n" + 
        				" from workpage as w JOIN tree as t on w.workid=t.workid join inyard as i on w.workid=i.workid WHERE i.yarddate>='"+timeStart+"' AND i.yarddate<='"+timeEnd+"' and i.yard='"+yard+"' GROUP BY i.yard,t.treetype ORDER BY i.yard,t.treetype";
        		List<producetree> dp = cd.findProduceTol(sql);
            	mapper.writeValue(response.getWriter(), dp);
        	}
        	else if(!timeStart.isEmpty() && !timeEnd.isEmpty() && !yard.isEmpty() && !treetype.isEmpty() && tradius.isEmpty())
        	{
        		sql="select i.yard,t.treetype,sum(t.num) as tnum,round(sum(t.tvolume),4) as ttvolume\r\n" + 
        				" from workpage as w JOIN tree as t on w.workid=t.workid join inyard as i on w.workid=i.workid WHERE i.yarddate>='"+timeStart+"' AND i.yarddate<='"+timeEnd+"' and i.yard='"+yard+"' and t.treetype='"+treetype+"' GROUP BY i.yard,t.treetype ORDER BY i.yard,t.treetype";
        		List<producetree> dp = cd.findProduceTol(sql);
            	mapper.writeValue(response.getWriter(), dp);
        	}
        	else if(!timeStart.isEmpty() && !timeEnd.isEmpty() && !yard.isEmpty() && !treetype.isEmpty() && !tradius.isEmpty())
        	{
        		sql="select i.yard,t.treetype,sum(t.num) as tnum,round(sum(t.tvolume),4) as ttvolume\r\n" + 
        				" from workpage as w JOIN tree as t on w.workid=t.workid join inyard as i on w.workid=i.workid WHERE i.yarddate>='"+timeStart+"' AND i.yarddate<='"+timeEnd+"' and i.yard='"+yard+"' and t.treetype='"+treetype+"' and t.tradius='"+tradius+"' GROUP BY i.yard,t.treetype ORDER BY i.yard,t.treetype";
        		List<producetree> dp = cd.findProduceTol(sql);
            	mapper.writeValue(response.getWriter(), dp);
        	}
        }*/
      //木材产出库存总
        else if("totalproduce".equals(action)) {
        	String timeStart = request.getParameter("timeStart");
        	String timeEnd = request.getParameter("timeEnd");
        	String treetype = request.getParameter("treetype");
        	String tradius = request.getParameter("tradius");
        	String yard = request.getParameter("yard");
        	String cutnum = request.getParameter("cutnum");
        	String tlong = request.getParameter("tlong");
        	String projectPackageName = request.getParameter("projectPackageName");
        	String contractionSide = request.getParameter("contractionSide");
        	//System.out.println("...." +treetype + "...");
        	String sql="";
        	sql="select i.yard,t.treetype,sum(t.num) as tnum,round(sum(t.tvolume),4) as ttvolume\r\n" + 
        			"from workpage as w JOIN tree as t on w.workid=t.workid join inyard as i on w.workid=i.workid \r\n" + 
        			"JOIN cutnum c on w.cutNum=c.cutnum JOIN proj_package p on c.proj_package_Name=p.proj_package_Name where 1=1";
        	if(!timeStart.isEmpty() && !timeEnd.isEmpty()) {
        		sql=sql+" and i.yarddate>='"+timeStart+"' AND i.yarddate<='"+timeEnd+"'";
        	}
        	if(!treetype.isEmpty()) {
        		sql=sql+" and t.treetype='"+treetype+"'";
        	}
        	if(!tradius.isEmpty())
        	{
        		sql=sql+" and t.tradius='"+tradius+"'";
        	}
        	if(!tlong.isEmpty())
        	{
        		sql=sql+" and t.tlong='"+tlong+"'";
        	}
        	if(!yard.isEmpty())
        	{
        		sql=sql+" and i.yard='"+yard+"'";
        	}
        	if(!cutnum.isEmpty())
        	{
        		sql=sql+" and w.cutNum='"+cutnum+"'";
        	}
        	if(!contractionSide.isEmpty())
        	{
        		sql=sql+" and p.contractionSide='"+contractionSide+"'";
        	}
        	if(!projectPackageName.isEmpty())
        	{
        		sql=sql+" and c.proj_package_Name='"+projectPackageName+"'";
        	}
        	//System.out.print(sql);
        	sql=sql+" GROUP BY i.yard,t.treetype ORDER BY i.yard,t.treetype";
        	List<producetree> dp = cd.findProduceTol(sql);
        	mapper.writeValue(response.getWriter(), dp);
        }
        
        //木材销售台账
        /*else if(action.equals("treeoutTable")) {
        	String timeStart = request.getParameter("timeStart");
        	String timeEnd = request.getParameter("timeEnd");
        	String treetype = request.getParameter("treetype");
        	String tradius = request.getParameter("tradius");
        	String yard = request.getParameter("yard");
        	//System.out.println("...." +timeEnd + "...");
        	String sql="";
        	if(!timeStart.isEmpty() && !timeEnd.isEmpty() && yard.isEmpty() && treetype.isEmpty() && tradius.isEmpty()) 
        	{
            //sql="select w.workid,year(w.yarddate) as yeart,month(w.yarddate) as montht,w.carNumber,w.yard,w.section,t.treetype,t.tlong,t.tradius,t.num,t.tvolume,t.unitprice,t.totalnum,w.surveyor, s.salesman from outyard as w JOIN treeout as t join saleman as s on w.workid=t.workid=s.workid";
        	sql="select w.yarddate,w.contractnum,w.sale_callout_orderid,w.yard,t.treetype,t.tlong,t.tradius,sum(DISTINCT t.num) as tnum,sum(DISTINCT t.tvolume) as ttvolume from outyard as w JOIN treeout as t  on w.workid=t.workid \r\n" + 
        			"WHERE w.yarddate>='"+timeStart+"' and w.yarddate<='"+timeEnd+"'\r\n" + 
        			"GROUP BY w.yarddate,w.contractnum,w.sale_callout_orderid,w.yard,t.treetype,t.tlong,t.tradius ORDER BY w.yarddate DESC,w.contractnum";
        	List<treeoutPrint> dp = cd.findTreeout(sql);
        	mapper.writeValue(response.getWriter(), dp);
        	}
        	else if(!timeStart.isEmpty() && !timeEnd.isEmpty() && !yard.isEmpty() && treetype.isEmpty() && tradius.isEmpty())
        	{
        		sql="select w.yarddate,w.contractnum,w.sale_callout_orderid,w.yard,t.treetype,t.tlong,t.tradius,sum(DISTINCT t.num) as tnum,sum(DISTINCT t.tvolume) as ttvolume from outyard as w JOIN treeout as t  on w.workid=t.workid \r\n" + 
        				"WHERE w.yarddate>='"+timeStart+"' and w.yarddate<='"+timeEnd+"' and w.yard='"+yard+"'\r\n" + 
        				"GROUP BY w.yarddate,w.contractnum,w.sale_callout_orderid,w.yard,t.treetype,t.tlong,t.tradius ORDER BY w.yarddate DESC,w.contractnum";
        		List<treeoutPrint> dp = cd.findTreeout(sql);
            	mapper.writeValue(response.getWriter(), dp);
        	}
        	else if(!timeStart.isEmpty() && !timeEnd.isEmpty() && !yard.isEmpty() && !treetype.isEmpty() && tradius.isEmpty())
        	{
        		sql="select w.yarddate,w.contractnum,w.sale_callout_orderid,w.yard,t.treetype,t.tlong,t.tradius,sum(DISTINCT t.num) as tnum,sum(DISTINCT t.tvolume) as ttvolume from outyard as w JOIN treeout as t  on w.workid=t.workid \r\n" + 
        				"WHERE w.yarddate>='"+timeStart+"' and w.yarddate<='"+timeEnd+"' and w.yard='"+yard+"' and t.treetype='"+treetype+"'\r\n" + 
        				"GROUP BY w.yarddate,w.contractnum,w.sale_callout_orderid,w.yard,t.treetype,t.tlong,t.tradius ORDER BY w.yarddate DESC,w.contractnum";
        		List<treeoutPrint> dp = cd.findTreeout(sql);
            	mapper.writeValue(response.getWriter(), dp);
        	}
        	else if(!timeStart.isEmpty() && !timeEnd.isEmpty() && !yard.isEmpty() && !treetype.isEmpty() && !tradius.isEmpty())
        	{
        		sql="select w.yarddate,w.contractnum,w.sale_callout_orderid,w.yard,t.treetype,t.tlong,t.tradius,sum(DISTINCT t.num) as tnum,sum(DISTINCT t.tvolume) as ttvolume from outyard as w JOIN treeout as t  on w.workid=t.workid \r\n" + 
        				"WHERE w.yarddate>='"+timeStart+"' and w.yarddate<='"+timeEnd+"' and w.yard='"+yard+"' and t.treetype='"+treetype+"' and t.tradius='"+tradius+"'\r\n" + 
        				"GROUP BY w.yarddate,w.contractnum,w.sale_callout_orderid,w.yard,t.treetype,t.tlong,t.tradius ORDER BY w.yarddate DESC,w.contractnum";
        		List<treeoutPrint> dp = cd.findTreeout(sql);
            	mapper.writeValue(response.getWriter(), dp);
        	}
        }*/
        else if(action.equals("treeoutTable")) {
        	String timeStart = request.getParameter("timeStart");
        	String timeEnd = request.getParameter("timeEnd");
        	String treetype = request.getParameter("treetype");
        	String tradius = request.getParameter("tradius");
        	String yard = request.getParameter("yard");
        	String cutnum = request.getParameter("cutnum");
        	String tlong = request.getParameter("tlong");
        	String contractnum = request.getParameter("contractnum");
        	String demander = request.getParameter("demander");
        	//System.out.println("...." +contractnum + "...");
        	String sql="";
        	sql="select w.yarddate,w.contractnum,w.sale_callout_orderid,w.yard,t.treetype,t.tlong,t.tradius,sum(DISTINCT t.num) as tnum,sum(DISTINCT t.tvolume) as ttvolume,s.demander,w.cutnum \r\n" + 
        			"from outyard as w JOIN treeout as t  on w.workid=t.workid JOIN sale_contract s on w.contractnum=s.contractnum WHERE 1=1";
        	if(!timeStart.isEmpty() && !timeEnd.isEmpty()) {
        		sql=sql+" and w.yarddate>='"+timeStart+"' AND w.yarddate<='"+timeEnd+"'";
        	}
        	if(!treetype.isEmpty()) {
        		sql=sql+" and t.treetype='"+treetype+"'";
        	}
        	if(!tradius.isEmpty())
        	{
        		sql=sql+" and t.tradius='"+tradius+"'";
        	}
        	if(!tlong.isEmpty())
        	{
        		sql=sql+" and t.tlong='"+tlong+"'";
        	}
        	if(!yard.isEmpty())
        	{
        		sql=sql+" and w.yard='"+yard+"'";
        	}
        	if(!cutnum.isEmpty())
        	{
        		sql=sql+" and w.cutnum'"+cutnum+"'";
        	}
        	if(!contractnum.isEmpty())
        	{
        		sql=sql+" and w.contractnum='"+contractnum+"'";
        	}
        	if(!demander.isEmpty())
        	{
        		sql=sql+" and s.demander='"+demander+"'";
        	}
        	//System.out.println("...." +sql + "...");
        	sql=sql+" GROUP BY w.yarddate,w.contractnum,w.sale_callout_orderid,w.yard,t.treetype,t.tlong,t.tradius,s.demander,w.cutnum  ORDER BY w.yarddate DESC,w.contractnum";
        		List<treeoutPrint> dp = cd.findTreeout(sql);
            	mapper.writeValue(response.getWriter(), dp);
        }
      //木材销售总的材积台账
        /*else if(action.equals("treeoutDetTable")) {
        	String timeStart = request.getParameter("timeStart");
        	String timeEnd = request.getParameter("timeEnd");
        	String treetype = request.getParameter("treetype");
        	String tradius = request.getParameter("tradius");
        	String yard = request.getParameter("yard");
        	//System.out.println("...." +month + "...");
        	String sql="";
        	if(!timeStart.isEmpty() && !timeEnd.isEmpty() && yard.isEmpty() && treetype.isEmpty() && tradius.isEmpty()) 
        	{
            //sql="select w.workid,year(w.yarddate) as yeart,month(w.yarddate) as montht,w.carNumber,w.yard,w.section,t.treetype,t.tlong,t.tradius,t.num,t.tvolume,t.unitprice,t.totalnum,w.surveyor, s.salesman from outyard as w JOIN treeout as t join saleman as s on w.workid=t.workid=s.workid";
        	sql="select w.yard,t.treetype,t.tlong,t.tradius,sum(t.num) as tnum,round(sum(t.tvolume),4) as ttvolume from outyard as w JOIN treeout as t  on w.workid=t.workid \r\n" + 
        			"WHERE w.yarddate>='"+timeStart+"' and w.yarddate<='"+timeEnd+"'\r\n" + 
        			"GROUP BY w.yard,t.treetype,t.tlong,t.tradius ORDER BY w.yard,t.treetype";
        	List<treeoutPrint> dp = cd.findTreeoutDet(sql);
        	mapper.writeValue(response.getWriter(), dp);
        	}
        	else if(!timeStart.isEmpty() && !timeEnd.isEmpty() && !yard.isEmpty() && treetype.isEmpty() && tradius.isEmpty())
        	{
        		sql="select w.yard,t.treetype,t.tlong,t.tradius,sum(t.num) as tnum,round(sum(t.tvolume),4) as ttvolume from outyard as w JOIN treeout as t  on w.workid=t.workid \r\n" + 
        				"WHERE w.yarddate>='"+timeStart+"' and w.yarddate<='"+timeEnd+"' and w.yard='"+yard+"'\r\n" + 
        				"GROUP BY w.yard,t.treetype,t.tlong,t.tradius ORDER BY w.yard,t.treetype";
        		List<treeoutPrint> dp = cd.findTreeoutDet(sql);
            	mapper.writeValue(response.getWriter(), dp);
        	}
        	else if(!timeStart.isEmpty() && !timeEnd.isEmpty() && !yard.isEmpty() && !treetype.isEmpty() && tradius.isEmpty())
        	{
        		sql="select w.yard,t.treetype,t.tlong,t.tradius,sum(t.num) as tnum,round(sum(t.tvolume),4) as ttvolume from outyard as w JOIN treeout as t  on w.workid=t.workid \r\n" + 
        				"WHERE w.yarddate>='"+timeStart+"' and w.yarddate<='"+timeEnd+"' and w.yard='"+yard+"' and t.treetype='"+treetype+"'\r\n" + 
        				"GROUP BY w.yard,t.treetype,t.tlong,t.tradius ORDER BY w.yard,t.treetype";
        		List<treeoutPrint> dp = cd.findTreeoutDet(sql);
            	mapper.writeValue(response.getWriter(), dp);
        	}
        	else if(!timeStart.isEmpty() && !timeEnd.isEmpty() && !yard.isEmpty() && !treetype.isEmpty() && !tradius.isEmpty())
        	{
        		sql="select w.yard,t.treetype,t.tlong,t.tradius,sum(t.num) as tnum,round(sum(t.tvolume),4) as ttvolume from outyard as w JOIN treeout as t  on w.workid=t.workid \r\n" + 
        				"WHERE w.yarddate>='"+timeStart+"' and w.yarddate<='"+timeEnd+"' and w.yard='"+yard+"' and t.treetype='"+treetype+"' and t.tradius='"+tradius+"'\r\n" + 
        				"GROUP BY w.yard,t.treetype,t.tlong,t.tradius ORDER BY w.yard,t.treetype";
        		List<treeoutPrint> dp = cd.findTreeoutDet(sql);
            	mapper.writeValue(response.getWriter(), dp);
        	}
        }*/
      //木材销售总的材积台账
        else if(action.equals("treeoutDetTable")) {
        	String timeStart = request.getParameter("timeStart");
        	String timeEnd = request.getParameter("timeEnd");
        	String treetype = request.getParameter("treetype");
        	String tradius = request.getParameter("tradius");
        	String yard = request.getParameter("yard");
        	String cutnum = request.getParameter("cutnum");
        	String tlong = request.getParameter("tlong");
        	String contractnum = request.getParameter("contractnum");
        	String demander = request.getParameter("demander");
        	//System.out.println("...." +month + "...");
        	String sql="";
        	sql="select w.yard,t.treetype,t.tlong,t.tradius,sum(t.num) as tnum,round(sum(t.tvolume),4) as ttvolume from outyard as w JOIN treeout as t  on w.workid=t.workid\r\n" + 
        			"JOIN sale_contract s on w.contractnum=s.contractnum WHERE 1=1";
        	if(!timeStart.isEmpty() && !timeEnd.isEmpty()) {
        		sql=sql+" and w.yarddate>='"+timeStart+"' AND w.yarddate<='"+timeEnd+"'";
        	}
        	if(!treetype.isEmpty()) {
        		sql=sql+" and t.treetype='"+treetype+"'";
        	}
        	if(!tradius.isEmpty())
        	{
        		sql=sql+" and t.tradius='"+tradius+"'";
        	}
        	if(!tlong.isEmpty())
        	{
        		sql=sql+" and t.tlong='"+tlong+"'";
        	}
        	if(!yard.isEmpty())
        	{
        		sql=sql+" and w.yard='"+yard+"'";
        	}
        	if(!cutnum.isEmpty())
        	{
        		sql=sql+" and w.cutnum'"+cutnum+"'";
        	}
        	if(!contractnum.isEmpty())
        	{
        		sql=sql+" and w.contractnum='"+contractnum+"'";
        	}
        	if(!demander.isEmpty())
        	{
        		sql=sql+" and s.demander='"+demander+"'";
        	}
        	sql=sql+"GROUP BY w.yard,t.treetype,t.tlong,t.tradius ORDER BY w.yard,t.treetype";
        		List<treeoutPrint> dp = cd.findTreeoutDet(sql);
            	mapper.writeValue(response.getWriter(), dp);
        }
        //货场库存柱状图表
    	else if("yardStock".equals(action))
    	{
//    		List<treeoutChart> ty = new ArrayList<treeoutChart>();
//        	ty=cd.treeoutChart();
        	ObjectMapper map = new ObjectMapper();
        	treetypeChart tp = new treetypeChart();
        	JSONArray list = new JSONArray();
        	String sql="SELECT ROUND(sum(i.tolstere),4) as tvolume,i.yard from tree t join inyard i on t.workid=i.workid GROUP BY i.yard";
        	List<treetypeChart> yardin=cd.findyardStock(sql);
//        	String sql1="SELECT ROUND(sum(i.tolstere),4) as tvolume,i.yard from treeout t join outyard i on t.workid=i.workid GROUP BY i.yard";
//        	List<treetypeChart> yardout=cd.findyardStock(sql1);
//        	String sql2="SELECT ROUND(sum(tvolume),4),cancellingStocksSite FROM cancellingstockstable GROUP BY cancellingStocksSite";
//        	List<treetypeChart> cancelStock=cd.findyardStock(sql2);
        	String inyard="";
        	String intvo="";
        	String outyard="";
        	String outtvo="";
        	String outtvo1="";
        	String cancelyard="";
        	String canceltvo="";
        	String canceltvo1="";
        	String stockyard="";
        	double stocktvo1=0;
        	String stocktvo="";
        	DecimalFormat df = new DecimalFormat("#.00");
        	if(yardin.size()>0) {
        		for(int i=0;i<yardin.size();i++) {
        			inyard=yardin.get(i).getYard();
        			intvo=yardin.get(i).getTvolume();
        			//出仓
        			String sql1="SELECT ROUND(sum(i.tolstere),4) as tvolume,i.yard from treeout t join outyard i on t.workid=i.workid where i.yard='"+inyard+"' GROUP BY i.yard";
                	treetypeChart yardout=cd.findyardStock2(sql1);
                	outtvo1=yardout.getTvolume();
                	if(String.valueOf(outtvo1).isEmpty() || String.valueOf(outtvo1)=="null")
                	{
                		outtvo="0";
                	}
                	else
                	{
                		outtvo=outtvo1;
                	}
                	//报损
                	String sql2="SELECT ROUND(sum(tvolume),4),cancellingStocksSite FROM cancellingstockstable where cancellingStocksSite='"+inyard+"' GROUP BY cancellingStocksSite";
                	treetypeChart cancelStock=cd.findyardStock2(sql2);
                	canceltvo1=cancelStock.getTvolume();
                	if(String.valueOf(canceltvo1).isEmpty() || String.valueOf(canceltvo1)=="null")
                	{
                		canceltvo="0";
                	}
                	else
                	{
                		canceltvo=canceltvo1;
                	}
                	stocktvo1=Double.parseDouble(intvo)-Double.parseDouble(outtvo)-Double.parseDouble(canceltvo);
                	stocktvo=df.format(stocktvo1);//库存量
                	tp.setYard(inyard);
                	tp.setTvolume(stocktvo);
                	list.put(tp);
        		}
        		//list.put(tp);
        		out.print(list);
//        		System.out.print(list);
//        		String json=map.writeValueAsString(list);
//        		System.out.print(json);
//            	response.getWriter().write(json);
        	}
        	
    	}
        //画管理部门主页柱状图
    	else if("barManage".equals(action)) {
    		List<cutnum> ty = new ArrayList<cutnum>();
        	ty=cd.cutnumChart();
        	ObjectMapper map = new ObjectMapper();
        	String json=map.writeValueAsString(ty);
        	//System.out.print(json);
        	response.getWriter().write(json);
    	}
	}

}
