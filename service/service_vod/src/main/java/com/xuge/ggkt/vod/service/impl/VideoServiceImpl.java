package com.xuge.ggkt.vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xuge.ggkt.vod.mapper.VideoMapper;
import com.xuge.ggkt.vod.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuge.ggkt.vod.service.VodService;
import com.xuge.model.vod.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author xuge
 * @since 2022-07-01
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {
  @Autowired
  private VodService vodService;

  @Override
  public void removeVideoByCourseId(Long id) {
    //根据课程id查询所有课程小结节
    QueryWrapper<Video> qw = new QueryWrapper<>();
    qw.eq("course_id", id);
    List<Video> videos = baseMapper.selectList(qw);

    //遍历集合得到每个小结，取出每个小结进行，获取其视频id
    for (Video video : videos) {
      String videoSourceId = video.getVideoSourceId();
      if (videoSourceId != null) {
        vodService.removeVideo(videoSourceId);
      }
    }

    //删除小结

    baseMapper.delete(qw);
  }

  //删除小结的时候，删除视频
  @Override
  public void removeVideoById(Long id) {
    //删除视屏如果视频id存在
    Video video = baseMapper.selectById(id);
    //获取视频id
    String videoSourceId = video.getVideoSourceId();
    if (videoSourceId != null) {
      //调用方法删除
      vodService.removeVideo(videoSourceId);
    }
    //删除小节
    baseMapper.deleteById(id);
  }
}
