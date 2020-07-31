package jinshen.action;
/*画超级管理员页面折线图，柱形图。木材报表导出*/
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import jinshen.bean.producetree;
import jinshen.bean.salemansql;
import jinshen.bean.treeChart;
import jinshen.bean.treeoutChart;
import jinshen.bean.treeoutPrint;
import jinshen.bean.treetypeChart;
import jinshen.dao.chartDao;
import jinshen.daoimpl.chartDaoImpl;

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
        else if(action.equals("producetree")) {
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
        else if(action.equals("treeoutTable")) {
        	String year = request.getParameter("year");
        	String month = request.getParameter("month");
        	//System.out.println("...." +month + "...");
        	List<treeoutPrint> dp = cd.findTreeout();
        	//System.out.println("...."+dp + "...");
			List<treeoutPrint> dplist = new ArrayList<treeoutPrint>();
			if(dp != null) {
				if (month.equals("1-12"))
				{
					for(int i = 0;i<dp.size();i ++)
					{
				    treeoutPrint d = cd.printTreeoutY(Integer.valueOf(year));
					d.setWorkid(dp.get(i).getWorkid());
					d.setYear(dp.get(i).getYear());
					d.setMonth(dp.get(i).getMonth());
					d.setCarNumber(dp.get(i).getCarNumber());
					d.setYard(dp.get(i).getYard());
					d.setCheckstd(dp.get(i).getCheckstd());
					d.setTreetype(dp.get(i).getTreetype());
					d.setTlong(dp.get(i).getTlong());
					d.setTradius(dp.get(i).getTradius());
					d.setNum(dp.get(i).getNum());
					d.setTvolume(dp.get(i).getTvolume());
					d.setUnitprice(dp.get(i).getUnitprice());
					d.setPrice(dp.get(i).getPrice());
					d.setSurveyor(dp.get(i).getSurveyor());
					d.setSaleman(dp.get(i).getSaleman());
					dplist.add(d);
					}
				}
				else if(month.equals("1-3")) {
					int month1=1;
					int month2=2;
					int month3=3;
					for(int i = 0;i<dp.size();i ++) {
						treeoutPrint d = cd.printTreeoutM(Integer.valueOf(year),month1,month2,month3);
						if((Integer.valueOf(year) == dp.get(i).getYear() && (month1== dp.get(i).getMonth() || month2== dp.get(i).getMonth() || month3== dp.get(i).getMonth())))
						{
							d.setWorkid(dp.get(i).getWorkid());
	     					   d.setYear(dp.get(i).getYear());
	     					   d.setMonth(dp.get(i).getMonth());
	     					   d.setCarNumber(dp.get(i).getCarNumber());
	     					   d.setYard(dp.get(i).getYard());
	     					   d.setCheckstd(dp.get(i).getCheckstd());
	     					   d.setTreetype(dp.get(i).getTreetype());
	     					   d.setTlong(dp.get(i).getTlong());
	     					   d.setTradius(dp.get(i).getTradius());
	     					   d.setNum(dp.get(i).getNum());
	     					   d.setTvolume(dp.get(i).getTvolume());
	     					   d.setUnitprice(dp.get(i).getUnitprice());
	     					   d.setPrice(dp.get(i).getPrice());
	     					   d.setSurveyor(dp.get(i).getSurveyor());
	     					   d.setSaleman(dp.get(i).getSaleman());
								dplist.add(d);
						}
					}
				}
				else if(month.equals("4-6")) {
					int month1=4;
					int month2=5;
					int month3=6;
					for(int i = 0;i<dp.size();i ++) {
						treeoutPrint d = cd.printTreeoutM(Integer.valueOf(year),month1,month2,month3);
						if((Integer.valueOf(year) == dp.get(i).getYear() && (month1== dp.get(i).getMonth() || month2== dp.get(i).getMonth() || month3== dp.get(i).getMonth())))
						{
							d.setWorkid(dp.get(i).getWorkid());
	     					   d.setYear(dp.get(i).getYear());
	     					   d.setMonth(dp.get(i).getMonth());
	     					   d.setCarNumber(dp.get(i).getCarNumber());
	     					   d.setYard(dp.get(i).getYard());
	     					   d.setCheckstd(dp.get(i).getCheckstd());
	     					   d.setTreetype(dp.get(i).getTreetype());
	     					   d.setTlong(dp.get(i).getTlong());
	     					   d.setTradius(dp.get(i).getTradius());
	     					   d.setNum(dp.get(i).getNum());
	     					   d.setTvolume(dp.get(i).getTvolume());
	     					   d.setUnitprice(dp.get(i).getUnitprice());
	     					   d.setPrice(dp.get(i).getPrice());
	     					   d.setSurveyor(dp.get(i).getSurveyor());
	     					   d.setSaleman(dp.get(i).getSaleman());
								dplist.add(d);
						}
					}
				}
				else if(month.equals("7-9")) {
					int month1=7;
					int month2=8;
					int month3=9;
					for(int i = 0;i<dp.size();i ++) {
						treeoutPrint d = cd.printTreeoutM(Integer.valueOf(year),month1,month2,month3);
						if((Integer.valueOf(year) == dp.get(i).getYear() && (month1== dp.get(i).getMonth() || month2== dp.get(i).getMonth() || month3== dp.get(i).getMonth())))
						{
							d.setWorkid(dp.get(i).getWorkid());
	     					   d.setYear(dp.get(i).getYear());
	     					   d.setMonth(dp.get(i).getMonth());
	     					   d.setCarNumber(dp.get(i).getCarNumber());
	     					   d.setYard(dp.get(i).getYard());
	     					   d.setCheckstd(dp.get(i).getCheckstd());
	     					   d.setTreetype(dp.get(i).getTreetype());
	     					   d.setTlong(dp.get(i).getTlong());
	     					   d.setTradius(dp.get(i).getTradius());
	     					   d.setNum(dp.get(i).getNum());
	     					   d.setTvolume(dp.get(i).getTvolume());
	     					   d.setUnitprice(dp.get(i).getUnitprice());
	     					   d.setPrice(dp.get(i).getPrice());
	     					   d.setSurveyor(dp.get(i).getSurveyor());
	     					   d.setSaleman(dp.get(i).getSaleman());
								dplist.add(d);
						}
					}
				}
				else if(month.equals("10-12")) {
					int month1=10;
					int month2=11;
					int month3=12;
					for(int i = 0;i<dp.size();i ++) {
						treeoutPrint d = cd.printTreeoutM(Integer.valueOf(year),month1,month2,month3);
						if((Integer.valueOf(year) == dp.get(i).getYear() && (month1== dp.get(i).getMonth() || month2== dp.get(i).getMonth() || month3== dp.get(i).getMonth())))
						{
							d.setWorkid(dp.get(i).getWorkid());
	     					   d.setYear(dp.get(i).getYear());
	     					   d.setMonth(dp.get(i).getMonth());
	     					   d.setCarNumber(dp.get(i).getCarNumber());
	     					   d.setYard(dp.get(i).getYard());
	     					   d.setCheckstd(dp.get(i).getCheckstd());
	     					   d.setTreetype(dp.get(i).getTreetype());
	     					   d.setTlong(dp.get(i).getTlong());
	     					   d.setTradius(dp.get(i).getTradius());
	     					   d.setNum(dp.get(i).getNum());
	     					   d.setTvolume(dp.get(i).getTvolume());
	     					   d.setUnitprice(dp.get(i).getUnitprice());
	     					   d.setPrice(dp.get(i).getPrice());
	     					   d.setSurveyor(dp.get(i).getSurveyor());
	     					   d.setSaleman(dp.get(i).getSaleman());
								dplist.add(d);
						}
					}
				}
				else
					{
					for(int i = 0;i<dp.size();i ++) 
					   {
						treeoutPrint d = cd.printTreeout(Integer.valueOf(year),Integer.valueOf(month));
						//System.out.println("...." +d+ "...");
                       if ((Integer.valueOf(year) == dp.get(i).getYear() && Integer.valueOf(month) == dp.get(i).getMonth()))
						 {
                    	   d.setWorkid(dp.get(i).getWorkid());
     					   d.setYear(dp.get(i).getYear());
     					   d.setMonth(dp.get(i).getMonth());
     					   d.setCarNumber(dp.get(i).getCarNumber());
     					   d.setYard(dp.get(i).getYard());
     					   d.setCheckstd(dp.get(i).getCheckstd());
     					   d.setTreetype(dp.get(i).getTreetype());
     					   d.setTlong(dp.get(i).getTlong());
     					   d.setTradius(dp.get(i).getTradius());
     					   d.setNum(dp.get(i).getNum());
     					   d.setTvolume(dp.get(i).getTvolume());
     					   d.setUnitprice(dp.get(i).getUnitprice());
     					   d.setPrice(dp.get(i).getPrice());
     					   d.setSurveyor(dp.get(i).getSurveyor());
     					   d.setSaleman(dp.get(i).getSaleman());
							dplist.add(d);
						}
					}
					}
			}
			//System.out.println("...." +dplist + "...");
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), dplist);
        }
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
        
	}

}
