package com.xuge.ggkt.vod.controller.common;

import com.xuge.ggkt.result.R;
import com.xuge.ggkt.vod.service.VodService;
import com.xuge.ggkt.vod.utils.ConstantPropertiesUtil;
import com.xuge.ggkt.vod.utils.Signature;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.xuge.ggkt.vod.utils.*;
import java.util.Random;

/**
 * author: yjx
 * Date :2022/7/710:26
 **/
@Api(tags={"视频点播管理"})
@RestController
//@CrossOrigin
@RequestMapping("/admin/vod")
public class VodController {
  @Autowired
  private VodService vodService;
  //上传视频接口
  @PostMapping("upload")
  @ApiOperation("上传视频接口")
  public R upload(){
    String fileId=vodService.uploadVideo();
    return R.ok(fileId);
  }
  @ApiOperation("删除视频接口")
  @DeleteMapping("delete/{videoId}")
  public R delete(@PathVariable String videoId){
    vodService.removeVideo(videoId);
    return R.ok(null);
  }

  @ApiOperation("返回客户端视频签名")
  @GetMapping("sign")
  public R  getSign(){

    Signature sign = new Signature();
    // 设置 App 的云 API 密钥
    sign.setSecretId(ConstantPropertiesUtil.ACCESS_KEY_ID);
    sign.setSecretKey(ConstantPropertiesUtil.ACCESS_KEY_SECRET);
    sign.setCurrentTime(System.currentTimeMillis() / 1000);
    sign.setRandom(new Random().nextInt(java.lang.Integer.MAX_VALUE));
    sign.setSignValidDuration(3600 * 24 * 2); // 签名有效期：2天
    try {
      String signature = sign.getUploadSignature();
      System.out.println("signature : " + signature);
      return R.ok(signature);
    } catch (Exception e) {
      System.out.print("获取签名失败");
      e.printStackTrace();
      return R.fail(null);
    }
  }
}
