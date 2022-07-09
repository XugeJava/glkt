package com.xuge.ggkt.vod.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuge.ggkt.result.R;
import com.xuge.ggkt.vod.service.ChapterService;
import com.xuge.ggkt.vod.service.CourseService;
import com.xuge.model.vod.Course;
import com.xuge.vo.vod.CourseQueryVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * author: yjx
 * Date :2022/7/814:15
 **/
@RestController
@RequestMapping("/api/vod/course")
public class CourseApiController {
  @Autowired
  private CourseService courseService;
  @Autowired
  private ChapterService chapterService;
  //根据课程分类查询课程列表（分页）
  @ApiOperation("根据课程分类查询课程列表")
  @GetMapping("{subjectParentId}/{page}/{limit}")
  public R findPageCourse(@ApiParam(value = "课程一级分类ID", required = true) @PathVariable Long subjectParentId,
                          @ApiParam(name = "page", value = "当前页码", required = true) @PathVariable Long page,
                          @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable Long limit) {
    //封装条件
    CourseQueryVo courseQueryVo = new CourseQueryVo();
    courseQueryVo.setSubjectParentId(subjectParentId);
    //创建page对象
    Page<Course> pageParam = new Page<>(page,limit);
    Map<String,Object> map = courseService.findPage(pageParam,courseQueryVo);
    return R.ok(map);
  }

  //根据ID查询课程
  @ApiOperation("根据ID查询课程")
  @GetMapping("getInfo/{courseId}")
  public R getInfo(
          @ApiParam(value = "课程ID", required = true)
          @PathVariable Long courseId){
    Map<String, Object> map = courseService.getInfoById(courseId);
    return R.ok(map);
  }
  @ApiOperation("根据关键字查询课程")
  @GetMapping("inner/findByKeyword/{keyword}")
  public List<Course> findByKeyword(
          @ApiParam(value = "关键字", required = true)
          @PathVariable String keyword){
    QueryWrapper<Course> queryWrapper = new QueryWrapper();
    queryWrapper.like("title", keyword);
    List<Course> list = courseService.list(queryWrapper);
    return list;
  }
}