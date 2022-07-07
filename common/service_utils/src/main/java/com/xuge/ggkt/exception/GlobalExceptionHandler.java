package com.xuge.ggkt.exception;

import com.xuge.ggkt.result.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * author: yjx
 * Date :2022/6/3016:29
 **/
@ControllerAdvice
public class GlobalExceptionHandler {
  //全局异常处理
  @ExceptionHandler(Exception.class)
  @ResponseBody
  public R error(Exception e){
   e.printStackTrace();
   return R.fail(null).message("执行全局异常处理");
  }

  @ExceptionHandler(NullPointerException.class)
  @ResponseBody
  public R error(NullPointerException e){
    e.printStackTrace();
    return R.fail(null).message("执行特定异常处理");
  }


  @ExceptionHandler(GgktException.class)
  @ResponseBody
  public R error(GgktException e){
    e.printStackTrace();
    return R.fail(null).message(e.getMsg()).code(e.getCode());
  }
}
