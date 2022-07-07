package com.xuge.ggkt.vod.controller.common;

import com.xuge.ggkt.result.R;
import com.xuge.ggkt.vod.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * author: yjx
 * Date :2022/7/110:17
 **/
@RequestMapping("/admin/vod/file")
@Api(tags = "文件上传接口")
@RestController
//@CrossOrigin
public class FileUploadController {
  @Autowired
  private FileService fileService;
  @ApiOperation("文件上传")
  @PostMapping("upload")
  public R upload(MultipartFile file){
    String s=fileService.upload(file);
    return R.ok(s).message("文件上传成功");
  }

}
