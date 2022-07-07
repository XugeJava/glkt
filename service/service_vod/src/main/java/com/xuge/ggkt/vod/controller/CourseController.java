package com.xuge.ggkt.vod.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuge.ggkt.result.R;
import com.xuge.ggkt.vod.service.CourseService;
import com.xuge.model.vod.Course;
import com.xuge.vo.vod.CourseFormVo;
import com.xuge.vo.vod.CoursePublishVo;
import com.xuge.vo.vod.CourseQueryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author xuge
 * @since 2022-07-01
 */
@RestController
//@CrossOrigin
@RequestMapping("/admin/vod/course")
public class CourseController {
  @Autowired
  private CourseService courseService;
  @ApiOperation("点播课程列表")
  @PostMapping("/pageCourse/{current}/{limit}")
  public R pageCourse(@PathVariable Integer current, @PathVariable Integer limit, @RequestBody CourseQueryVo  queryVo){
    Page<Course> page = new Page<>(current, limit);
    Map<String,Object>map= courseService.pageCourse(page,queryVo);
     return R.ok(map);
  }


  @ApiOperation("添加课程基本信息")
  @PostMapping("save")
  public R save(@RequestBody CourseFormVo courseFormVo){
     Long id=courseService.saveCourseForm(courseFormVo);
     return R.ok(id);
  }

  @ApiOperation("根据id获取课程信息")
  @GetMapping("get/{id}")
  public  R  getCourse(@PathVariable Long  id){
    CourseFormVo courseFormVo=courseService.getCourseInfo(id);
    return R.ok(courseFormVo);
  }

  @ApiOperation("修改课程基本信息")
  @PostMapping("update")
  public R update(@RequestBody CourseFormVo courseFormVo){
    Long id=courseService.updateCourseInfo(courseFormVo);
    return R.ok(id);
  }

  @ApiOperation("id查询发布课程信息")
  @GetMapping("getCoursePublishVo/{id}")
  public R getCoursePublishVo(@PathVariable  Long  id){
    CoursePublishVo publishVo=courseService.getCoursePublishVo(id);
    return R.ok(publishVo);
  }

  @ApiOperation("课程最终发布")
  @PutMapping("publishCourse/{id}")
  public R publishCourse(@PathVariable Long id){
    courseService.publishCourse(id);
    return R.ok(null);
  }

  @ApiOperation(value = "删除课程")
  @DeleteMapping("remove/{id}")
  public R remove(@PathVariable Long id) {
    courseService.removeCourseById(id);
    return R.ok(null);
  }


}

