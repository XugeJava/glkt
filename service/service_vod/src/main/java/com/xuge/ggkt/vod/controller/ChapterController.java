package com.xuge.ggkt.vod.controller;


import com.xuge.ggkt.result.R;
import com.xuge.ggkt.vod.service.ChapterService;
import com.xuge.model.vod.Chapter;
import com.xuge.vo.vod.ChapterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@RequestMapping("/admin/vod/chapter")
@Api(tags = "章节管理")
public class ChapterController {
  @Autowired
  private ChapterService chapterService;

  @ApiOperation("课程大纲列表")
  @GetMapping("getNestedTreeList/{courseId}")
  public R getTreeList(@PathVariable Long courseId){
    List<ChapterVo> chapterVoList = chapterService.getNestedTreeList(courseId);
    return R.ok(chapterVoList);
  }



  @ApiOperation("添加章节")
  @PostMapping("save")
  public R  save(@RequestBody Chapter chapter){
    chapterService.save(chapter);
    return R.ok(null);
  }




  @ApiOperation("根据id查询章节")
  @GetMapping("get/{id}")
  public R getChapter(@PathVariable Long id){
    Chapter chapter = chapterService.getById(id);
    return R.ok(chapter);
  }



  @ApiOperation("修改章节")
  @PostMapping("update")
  public R update(@RequestBody Chapter chapter){
    chapterService.updateById(chapter);
    return R.ok(null);
  }

  @ApiOperation("删除章节")
  @DeleteMapping("delete/{id}")
  public  R deleteChapter(@PathVariable Long id){
    chapterService.removeById(id);
    return R.ok(null);
  }

}

