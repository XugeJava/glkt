package com.xuge.ggkt.vod.controller;


import com.xuge.ggkt.result.R;
import com.xuge.ggkt.vod.service.VideoVisitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 视频来访者记录表 前端控制器
 * </p>
 *
 * @author xuge
 * @since 2022-07-07
 */
@RestController
//@CrossOrigin
@Api(tags={"课程统计管理"})
@RequestMapping("/admin/vod/videoVisitor")
public class VideoVisitorController {
  @Autowired
  private VideoVisitorService videoVisitorService;

  @ApiOperation("显示统计数据")
  @GetMapping("findCount/{courseId}/{startDate}/{endDate}")
  public R showChart(@PathVariable Long courseId,@PathVariable String startDate,@PathVariable String endDate ){

    Map<String, Object> map = videoVisitorService.findCount(courseId, startDate, endDate);
    return R.ok(map);
  }
}

