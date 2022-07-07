package com.xuge.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.xuge.excel.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author: yjx
 * Date :2022/7/113:52
 **/
public class ExcelListener extends AnalysisEventListener<User> {
  //创建list集合封装最终的数据
  List<User> list = new ArrayList<User>();
  //一行一行去读取excle内容
  //读取excel中的内容
  @Override
  public void invoke(User user, AnalysisContext analysisContext) {
    System.out.println("***"+user);
    list.add(user);
  }

  @Override
  public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    System.out.println("表头信息："+headMap);
  }
  //所有方法执行过后
  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {

  }



}
