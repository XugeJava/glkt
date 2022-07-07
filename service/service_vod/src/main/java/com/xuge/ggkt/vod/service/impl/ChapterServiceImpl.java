package com.xuge.ggkt.vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xuge.ggkt.vod.mapper.ChapterMapper;
import com.xuge.ggkt.vod.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuge.ggkt.vod.service.VideoService;
import com.xuge.model.vod.Chapter;
import com.xuge.model.vod.Video;
import com.xuge.vo.vod.ChapterVo;
import com.xuge.vo.vod.VideoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author xuge
 * @since 2022-07-01
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {
 @Autowired
 private VideoService videoService;
  @Override
  public List<ChapterVo> getNestedTreeList(Long courseId) {
    //定义最终返回数据集合
    List<ChapterVo> finalList=new ArrayList<>();

    //根据课程id获取所有的章节信息
    QueryWrapper<Chapter>  qw1=new QueryWrapper<>();
    qw1.eq("course_id",courseId);
    List<Chapter> chapterList = baseMapper.selectList(qw1);
    //根据课程Id获取所有的小节信息
    QueryWrapper<Video> qw2 = new QueryWrapper<>();
    qw2.eq("course_id",courseId);
    List<Video> videoList = videoService.list(qw2);

    //封装章节
    for (int i = 0; i < chapterList.size(); i++) {
      //得到每个章节
      Chapter chapter = chapterList.get(i);
      //chapter==>chapterVo
      ChapterVo chapterVo = new ChapterVo();
      BeanUtils.copyProperties(chapter, chapterVo);
      //封装小节
      List<VideoVo>  videos=new ArrayList<>();
      //遍历小节,判断小节的chapter_id与章节id是否相同
      for (Video video : videoList) {
        if(video.getChapterId().equals(chapter.getId())){
          VideoVo videoVo = new VideoVo();
          BeanUtils.copyProperties(video, videoVo);
          videos.add(videoVo);
        }

      }
      //设置章节子小节属性
      chapterVo.setChildren(videos);
      //得到的章节放入到finalList
      finalList.add(chapterVo);

    }

   //返回总的章节数据
    return finalList;
  }
  //根据课程id删除章节
  @Override
  public void removeChapterByCourseId(Long id) {
    QueryWrapper<Chapter> qw = new QueryWrapper<>();
    qw.eq("course_id",id);
    baseMapper.delete(qw);


  }
}
