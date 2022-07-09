package com.xuge.ggkt.vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuge.ggkt.vod.mapper.CourseMapper;
import com.xuge.ggkt.vod.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuge.ggkt.vod.service.*;
import com.xuge.model.vod.Course;
import com.xuge.model.vod.CourseDescription;
import com.xuge.model.vod.Subject;
import com.xuge.model.vod.Teacher;
import com.xuge.vo.vod.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author xuge
 * @since 2022-07-01
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
  @Autowired
  private TeacherService teacherService;
  @Autowired
  private SubjectService subjectService;
  @Autowired
  private CourseDescriptionService courseDescriptionService;
  @Autowired
  private VideoService videoService;
  @Autowired
  private ChapterService chapterService;

  //课程条件查询带分页
  @Override
  public Map<String, Object> pageCourse(Page<Course> page, CourseQueryVo queryVo) {
    //1.获取条件值
    Long subjectId = queryVo.getSubjectId();
    Long subjectParentId = queryVo.getSubjectParentId();
    Long teacherId = queryVo.getTeacherId();
    String title = queryVo.getTitle();

    //2.封装条件
    QueryWrapper<Course> qw = new QueryWrapper<>();
    //先按照创建时间逆序排序
    qw.orderByDesc("create_time");
    if (!StringUtils.isEmpty(title)) {
      qw.like("title", title);
    }
    if (!StringUtils.isEmpty(subjectId)) {
      qw.eq("subject_id", subjectId);
    }
    if (!StringUtils.isEmpty(subjectParentId)) {
      qw.eq("subject_parent_id", subjectParentId);
    }
    if (!StringUtils.isEmpty(teacherId)) {
      qw.eq("teacher_id", teacherId);
    }
    Page<Course> pages = baseMapper.selectPage(page, qw);
    long totalCount = pages.getTotal();//总记录数
    long totalPage = pages.getPages();//总页数
    long currentPage = pages.getCurrent();//当前页
    long size = pages.getSize();//每页记录数
    //每页数据集合
    List<Course> records = pages.getRecords();
    records.stream().forEach((item) -> {
      this.getNameById(item);
    });

    //封装返回数据
    Map<String, Object> map = new HashMap<>();
    map.put("totalCount", totalCount);
    map.put("totalPage", totalPage);
    map.put("records", records);
    //3.返回map

    return map;
  }

  //添加课程基本信息以及描述信息
  @Transactional
  @Override
  public Long saveCourseForm(CourseFormVo courseFormVo) {
    //添加课程基本信息
    Course course = new Course();
    BeanUtils.copyProperties(courseFormVo, course);
    baseMapper.insert(course);
    //添加课程描述信息
    CourseDescription courseDescription = new CourseDescription();
    //设置课程id
    courseDescription.setCourseId(course.getId());
    courseDescription.setDescription(courseFormVo.getDescription());
    courseDescriptionService.save(courseDescription);
    return course.getId();
  }

  //根据id查询课程信息
  @Override
  @Transactional
  public CourseFormVo getCourseInfo(Long id) {
    //课程基本信息
    Course course = baseMapper.selectById(id);
    if(course == null) {
      return null;
    }
    //课程描述信息
    CourseDescription courseDescription = courseDescriptionService.getById(id);
    //封装
    CourseFormVo courseFormVo = new CourseFormVo();
    BeanUtils.copyProperties(course,courseFormVo);
    //封装描述
    if(courseDescription != null) {
      courseFormVo.setDescription(courseDescription.getDescription());
    }
    return courseFormVo;
  }

  //修改课程信息
  @Override
  @Transactional
  public Long updateCourseInfo(CourseFormVo courseFormVo) {
    //课程基本信息
    Course course = new Course();
    //课程描述信息
    CourseDescription courseDescription = new CourseDescription();
    BeanUtils.copyProperties(courseFormVo, course);
    courseDescription.setDescription(courseFormVo.getDescription());
    courseDescription.setId(course.getId());
    //执行修改课程信息
    baseMapper.updateById(course);
    //执行修改描述信息
    courseDescriptionService.updateById(courseDescription);

    return courseFormVo.getId();

  }
  //通过课程id获取最终发布的信息
  @Override
  public CoursePublishVo getCoursePublishVo(Long id) {
    return baseMapper.selectCoursePublishVoById(id);
  }
  //课程最终发布
  @Override
  public void publishCourse(Long id) {
    Course course = baseMapper.selectById(id);
    course.setStatus(1);
    course.setPublishTime(new Date());
    baseMapper.updateById(course);



  }

  @Override
  @Transactional
  public void removeCourseById(Long id) {
    //根据课程id删除小节
    videoService.removeVideoByCourseId(id);
    //根据课程id删除章节
    chapterService.removeChapterByCourseId(id);
    //根据课程id删除描述
    courseDescriptionService.removeById(id);
    //根据课程id删除课程
    baseMapper.deleteById(id);

  }

  //课程列表
  @Override
  public Map<String,Object> findPage(Page<Course> pageParam, CourseQueryVo courseQueryVo) {
    //获取条件值
    String title = courseQueryVo.getTitle();//名称
    Long subjectId = courseQueryVo.getSubjectId();//二级分类
    Long subjectParentId = courseQueryVo.getSubjectParentId();//一级分类
    Long teacherId = courseQueryVo.getTeacherId();//讲师
    //封装条件
    QueryWrapper<Course> wrapper = new QueryWrapper<>();
    if(!StringUtils.isEmpty(title)) {
      wrapper.like("title",title);
    }
    if(!StringUtils.isEmpty(subjectId)) {
      wrapper.eq("subject_id",subjectId);
    }
    if(!StringUtils.isEmpty(subjectParentId)) {
      wrapper.eq("subject_parent_id",subjectParentId);
    }
    if(!StringUtils.isEmpty(teacherId)) {
      wrapper.eq("teacher_id",teacherId);
    }
    //调用方法查询
    Page<Course> pages = baseMapper.selectPage(pageParam, wrapper);

    long totalCount = pages.getTotal();//总记录数
    long totalPage = pages.getPages();//总页数
    long currentPage = pages.getCurrent();//当前页
    long size = pages.getSize();//每页记录数
    //每页数据集合
    List<Course> records = pages.getRecords();
    records.stream().forEach(item -> {
      this.getTeacherOrSubjectName(item);
    });

    Map<String,Object> map = new HashMap<>();
    map.put("totalCount",totalCount);
    map.put("totalPage",totalPage);
    map.put("records",records);

    return map;
  }

  //获取讲师和分类名称
  private Course getTeacherOrSubjectName(Course course) {
    Teacher teacher = teacherService.getById(course.getTeacherId());
    if(teacher != null) {
      course.getParam().put("teacherName",teacher.getName());
    }

    Subject subjectOne = subjectService.getById(course.getSubjectParentId());
    if(subjectOne != null) {
      course.getParam().put("subjectParentTitle",subjectOne.getTitle());
    }
    Subject subjectTwo = subjectService.getById(course.getSubjectId());
    if(subjectTwo != null) {
      course.getParam().put("subjectTitle",subjectTwo.getTitle());
    }
    return course;
  }

  //根据id查询课程
  @Override
  public Map<String, Object> getInfoById(Long id) {
    //更新流量量
    Course course = baseMapper.selectById(id);
    course.setViewCount(course.getViewCount() + 1);
    baseMapper.updateById(course);

    Map<String, Object> map = new HashMap<>();
    CourseVo courseVo = baseMapper.selectCourseVoById(id);
    List<ChapterVo> chapterVoList = chapterService.getNestedTreeList(id);
    CourseDescription courseDescription = courseDescriptionService.getById(id);
    Teacher teacher = teacherService.getById(course.getTeacherId());

    //TODO后续完善
    Boolean isBuy = false;

    map.put("courseVo", courseVo);
    map.put("chapterVoList", chapterVoList);
    map.put("description", null != courseDescription ?
            courseDescription.getDescription() : "");
    map.put("teacher", teacher);
    map.put("isBuy", isBuy);//是否购买
    return map;
  }

  //根据这些id获取对饮名称
  private void getNameById(Course course) {
    //根据讲师id获取讲师名称
    Teacher teacher = teacherService.getById(course.getTeacherId());
    if (teacher != null) {
      course.getParam().put("teacherName", teacher.getName());
    }

    //根据课程一级分类获取一级分类名称
    Subject subject1 = subjectService.getById(course.getSubjectParentId());
    if (subject1 != null) {
      course.getParam().put("subjectParentName", subject1.getTitle());
    }

    //根据课程二级分类获取二级分类名称
    Subject subject2 = subjectService.getById(course.getSubjectId());
    if (subject2 != null) {
      course.getParam().put("subjectName", subject2.getTitle());
    }

  }
}
