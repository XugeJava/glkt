package com.xuge.ggkt.vod.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuge.ggkt.result.R;
import com.xuge.ggkt.vod.service.TeacherService;
import com.xuge.model.vod.Teacher;
import com.xuge.vo.vod.TeacherQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author xuge
 * @since 2022-06-30
 */
@RestController
//@CrossOrigin
@Api(tags={"讲师管理"})
@RequestMapping("/admin/vod/teacher")
public class TeacherController {
  @Autowired
  private TeacherService teacherService;

  @ApiOperation("查询所有讲师")
  @GetMapping("findAll")
  public R findAllTeacher(){
    return R.ok(teacherService.list()).message("查询数据成功");
  }

  @ApiOperation("逻辑删除讲师")
  @DeleteMapping("remove/{id}")
  public  R  removeTeacher(@PathVariable Long id){
    boolean isSuccess = teacherService.removeById(id);
    if(isSuccess){
      return R.ok(null);
    }
    return R.fail(null);
  }
  @ApiOperation("条件分页查询讲师")
  @PostMapping("findQueryPage/{current}/{limit}")
  public R  findQueryPage(@PathVariable  Integer current, @PathVariable Integer limit, @RequestBody(required=false) TeacherQueryVo teacherQueryVo){
     //判断查询条件是否为空
    Page<Teacher> page = new Page<>(current, limit);
    if(teacherQueryVo==null){
      //执行分页查询
      teacherService.page(page);
      //返回
      return R.ok(page);
    }
    String name=teacherQueryVo.getName();
    Integer level=teacherQueryVo.getLevel();
    String joinDateBegin = teacherQueryVo.getJoinDateBegin();
    String joinDateEnd = teacherQueryVo.getJoinDateEnd();

    //条件封装
    QueryWrapper<Teacher> qw = new QueryWrapper<>();
    qw.like(name!=null,"name",name)
            .eq(level!=null,"level",level);
    if(!StringUtils.isEmpty(joinDateBegin)) {
      qw.ge("join_date",joinDateBegin);
    }
    if(!StringUtils.isEmpty(joinDateEnd)) {
      qw.le("join_date",joinDateEnd);
    }

    //按照创建时间进行逆序，修改时间逆序排序
    qw.orderByDesc("create_time")
            .orderByDesc("update_time");

    teacherService.page(page,qw);
    return R.ok(page).message("条件查询成功");
  }

  @ApiOperation("添加讲师")
  @PostMapping("save")
  public  R  save(@RequestBody  Teacher teacher){
    teacherService.save(teacher);
    return R.ok(null);
  }


  @ApiOperation("根据ID查询讲师")
  @GetMapping("{id}")
  public R  getTeacherById(@PathVariable Long id){
    return R.ok(teacherService.getById(id));
  }

  @ApiOperation("修改讲师")
  @PutMapping("update")
  public  R updateById(@RequestBody Teacher teacher){
    boolean flag = teacherService.updateById(teacher);
    if(flag){
      return R.ok(null);
    }
    return R.fail(null);


  }

  @ApiOperation("根据id列表删除")
  @DeleteMapping("batchRemove")
  public R  batchRemove(@RequestBody List<Long>idList){
    teacherService.removeByIds(idList);
    return R.ok(null);
  }






}

