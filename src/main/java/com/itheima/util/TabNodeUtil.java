package com.itheima.util;

import com.itheima.domain.Tab;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TabNodeUtil {
    public  Tab getTreeChildren(Tab parent,List<Tab> tabList){
        //子集导航栏集合
        List<Tab> childrenList=new ArrayList<>();
        //遍历所有导航栏数据，获取当前parent栏的直接下级导航栏数据
        for (Tab tab:tabList){
            if(parent.getId().equals(tab.getPid())){
                //递归调用
                childrenList.add(this.getTreeChildren(tab,tabList));
            }
        }

        //循环结束，设置parent的子级结构
        parent.setChildren(childrenList);
        return parent;
    }

    public  List<Tab> buildTree(List<Tab> tabList){
        //获取所有最顶层导航栏
        List<Tab> topTabList=new ArrayList<>();
        for (Tab tab:tabList){
            if ("-1".equals(tab.getPid())){
                topTabList.add(tab);
            }
        }

        //获取顶层导航栏的下级导航栏
        List<Tab> result =new ArrayList<>();
        for (Tab tab:topTabList){
            Tab treeChildren=this.getTreeChildren(tab,tabList);
            result.add(treeChildren);
        }
        return result;
    }

}
