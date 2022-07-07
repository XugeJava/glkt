package com.xuge.ggkt.vod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuge.model.vod.VideoVisitor;

import java.util.Map;

/**
 * <p>
 * 视频来访者记录表 服务类
 * </p>
 *
 * @author xuge
 * @since 2022-07-07
 */
public interface VideoVisitorService extends IService<VideoVisitor> {

  Map<String, Object> findCount(Long courseId, String startDate, String endDate);
}
