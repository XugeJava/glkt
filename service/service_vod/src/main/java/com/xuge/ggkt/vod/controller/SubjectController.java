package com.xuge.ggkt.vod.controller;


import com.xuge.ggkt.result.R;
import com.xuge.ggkt.vod.service.SubjectService;
import com.xuge.model.vod.Subject;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author xuge
 * @since 2022-07-01
 */
@RestController
@RequestMapping("/admin/vod/subject")
//@CrossOrigin
@ApiOperation("课程管理")
public class SubjectController {
  @Autowired
  private SubjectService subjectService;
 //课程分类列表
  @ApiOperation("课程分类列表")
  //懒加载，每次执行一次数据
  @GetMapping("getChildSubject/{id}")
  public R getChildSubject(@PathVariable Long id){
    List<Subject> list=subjectService.selectSubjctList(id);
    return R.ok(list);
  }

  @ApiOperation("课程分类导出")
  @GetMapping("export")
  public void exportData(HttpServletResponse response){
    subjectService.exportData(response);
  }


  @ApiOperation("课程分类导入")
  @PostMapping("import")
  public R importData(MultipartFile file){
    Boolean falg=subjectService.importData(file);
    if(falg){
      return R.ok(null);
    }
    return R.fail(null);

  }


}

