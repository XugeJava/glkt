package com.xuge.ggkt.vod.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xuge.model.vod.Course;
import com.xuge.vo.vod.CourseFormVo;
import com.xuge.vo.vod.CoursePublishVo;
import com.xuge.vo.vod.CourseQueryVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author xuge
 * @since 2022-07-01
 */
public interface CourseService extends IService<Course> {

  Map<String, Object> pageCourse(Page<Course> page, CourseQueryVo queryVo);

  Long saveCourseForm(CourseFormVo courseFormVo);

  CourseFormVo getCourseInfo(Long id);

  Long updateCourseInfo(CourseFormVo courseFormVo);

  CoursePublishVo getCoursePublishVo(Long id);

  void publishCourse(Long id);

  void removeCourseById(Long id);
}
