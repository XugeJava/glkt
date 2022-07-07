package com.xuge.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * author: yjx
 * Date :2022/7/113:46
 **/
public class TestWrite {
  public static void main(String[] args) {
    //设置文件名称和路
    String fileName="D:\\temp\\1.xlsx";
    //调用方法
    // 如果这里想使用03 则 传入excelType参数即可
    EasyExcel.write(fileName, User.class).sheet("写入方法").doWrite(data());
  }

  //循环设置要添加的数据，最终封装到list集合中
  private static List<User> data() {
    List<User> list = new ArrayList<User>();
    for (int i = 0; i < 10; i++) {
      User data = new User();
      data.setId(i);
      data.setName("张三"+i);
      list.add(data);
    }
    return list;
  }
}
