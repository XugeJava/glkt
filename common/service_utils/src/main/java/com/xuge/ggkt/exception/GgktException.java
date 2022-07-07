package com.xuge.ggkt.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author: yjx
 * Date :2022/6/3016:34
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GgktException extends RuntimeException {
  private Integer code;
  private String msg;
}