package com.xuge.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * author: yjx
 * Date :2022/7/113:44
 **/
@Data
public class User {
    //设置表头名称
    //设置列对应的属性
  @ExcelProperty(value="用户编号",index=0)
  private Integer id;
  @ExcelProperty(value="用户名称",index=1)
  private String name;
}
