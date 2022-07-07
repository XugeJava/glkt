package com.xuge.ggkt.vod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuge.model.vod.Video;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author xuge
 * @since 2022-07-01
 */
public interface VideoService extends IService<Video> {

  void removeVideoByCourseId(Long id);

  void removeVideoById(Long id);
}
