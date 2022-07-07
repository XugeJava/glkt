package com.xuge.ggkt.result;

import lombok.Data;

/**
 * author: yjx
 * Date :2022/6/3015:02
 **/
@Data
public class R<T> {
  //状态码
  private Integer code;
  //返回信息
  private String message;
  //返回数据
  private T data;

//  //成功
//  public static <T> R<T> ok() {
//    R<T> r = new R<>();
//    r.setCode(200);
//    r.setMessage("成功");
//
//    return r;
//
//
//  }

  //成功携带数据
  public static <T> R<T> ok(T data) {
    R<T> r = new R<>();
    r.setMessage("成功");
    r.setCode(20000);
    if (data != null) {
      r.setData(data);
    }

    return r;
  }

//  //失败
//  public static <T> R<T> fail() {
//    R<T> r = new R<>();
//    r.setCode(201);
//    r.setMessage("失败");
//    return r;
//  }

  //失败
  public static <T> R<T> fail(T data) {
    R<T> r = new R<>();
    r.setCode(20001);
    r.setMessage("失败");
    if (data != null) {
      r.setData(data);
    }
    return r;
  }


  public R<T> message(String msg){
    this.setMessage(msg);
    return this;
  }

  public R<T> code(Integer code){
    this.setCode(code);
    return this;
  }


}
