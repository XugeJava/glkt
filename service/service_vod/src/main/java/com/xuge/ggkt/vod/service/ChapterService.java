package com.xuge.ggkt.vod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuge.model.vod.Chapter;
import com.xuge.vo.vod.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author xuge
 * @since 2022-07-01
 */
public interface ChapterService extends IService<Chapter> {

  List<ChapterVo> getNestedTreeList(Long courseId);

  void removeChapterByCourseId(Long id);
}
