package com.xuge.ggkt.vod.controller;


import com.xuge.ggkt.result.R;
import com.xuge.ggkt.vod.service.VideoService;
import com.xuge.model.vod.Video;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author xuge
 * @since 2022-07-01
 */
@RestController
//@CrossOrigin
@RequestMapping("/admin/vod/video")
public class VideoController {
  @Autowired
  private VideoService videoService;

  @ApiOperation("添加小节")
  @PostMapping("save")
  public R save(@RequestBody Video video) {
    videoService.save(video);
    return R.ok(null);
  }

  @ApiOperation("获取小结信息")
  @GetMapping("get/{id}")
  public R getVideo(@PathVariable Long id) {
    Video video = videoService.getById(id);
    return R.ok(video);
  }

  @ApiOperation("修改小结")
  @PostMapping("update")
  public R updateVideo(@RequestBody Video video) {
    videoService.updateById(video);
    return R.ok(null);
  }

  @ApiOperation("删除小结")
  @DeleteMapping("delete/{id}")
  public R delete(@PathVariable Long id) {
    videoService.removeVideoById(id);
    return R.ok(null);
  }


}

