package com.xuge.ggkt.wechat.service.extend;

import java.util.Map;

/**
 * author: yjx
 * Date :2022/7/814:20
 **/
public interface MessageService {
  //接收消息
  String receiveMessage(Map<String, String> param);

  void pushPayMessage(long l);
}
