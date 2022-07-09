package com.xuge.ggkt.vod.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuge.model.vod.Course;
import com.xuge.vo.vod.CoursePublishVo;
import com.xuge.vo.vod.CourseVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author xuge
 * @since 2022-07-01
 */
public interface CourseMapper extends BaseMapper<Course> {

  CoursePublishVo selectCoursePublishVoById(Long id);

  CourseVo selectCourseVoById(Long id);
}
