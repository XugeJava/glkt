package com.xuge.ggkt.vod.controller.common;

import com.xuge.ggkt.result.R;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * author: yjx
 * Date :2022/6/3016:59
 **/
@RestController
//@CrossOrigin
@RequestMapping("/admin/vod/user")
public class UserLoginController {
  @PostMapping("login")
  public R login(){
    //{"code":20000,"data":{"token":"admin-token"}}
    Map<String,Object>map=new HashMap<>();
    map.put("token","admin-token");
    return R.ok(map).code(20000);
  }
  @GetMapping("info")
  public R info(){
    //{"code":20000,"data":{"roles":["admin"],"introduction":"I am a super administrator","avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif","name":"Super Admin"}}
    Map<String, Object> map = new HashMap<>();
    map.put("roles","[admin]");
    map.put("name","admin");
    map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    return R.ok(map);
  }
  /**
   * 退出
   * @return
   */
  @PostMapping("logout")
  public R logout(){
    return R.ok(null);
  }
}
