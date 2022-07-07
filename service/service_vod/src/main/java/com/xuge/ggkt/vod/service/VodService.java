package com.xuge.ggkt.vod.service;

/**
 * author: yjx
 * Date :2022/7/710:28
 **/
public interface VodService {
  String uploadVideo();

  void removeVideo(String videoId);
}
