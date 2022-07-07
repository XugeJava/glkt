package com.xuge.ggkt.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * author: yjx
 * Date :2022/7/110:19
 **/
public interface FileService {
  String upload(MultipartFile file);
}
