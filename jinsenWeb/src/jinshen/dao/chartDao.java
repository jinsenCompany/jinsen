package jinshen.dao;

import java.util.List;

import jinshen.bean.producetree;
import jinshen.bean.salemansql;
import jinshen.bean.treeChart;
import jinshen.bean.treetypeChart;
import jinshen.bean.treeoutChart;
import jinshen.bean.treeoutPrint;

public interface chartDao {
     public List<treeChart> treechart();//木材销售
     public List<treetypeChart> treetypechart();//木材库存
     public List<treeoutChart> treeoutChart();//销售木材画图
     
     public List<producetree> findProduce();//木材生产表格
 	 public producetree printProduce(int year,int month);
 	 public producetree printProduceY(int year);
 	 public producetree printProduceM(int year,int month,int month1,int month2);//生产按季度搜索订单信息
 	 
 	 public List<treeoutPrint> findTreeout();//木材销售打印表格
 	 public treeoutPrint printTreeout(int year,int month);//年月搜索显示订单信息
 	 public treeoutPrint printTreeoutY(int year);//年搜索
 	 public treeoutPrint printTreeoutM(int year,int month,int month1,int month2);//销售按季度搜索订单信息
 	 
 	 public List<salemansql> findsaleman();//木材销售订单展示
 	 public salemansql printsaleman();
}
