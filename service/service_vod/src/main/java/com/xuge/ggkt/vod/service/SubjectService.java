package com.xuge.ggkt.vod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuge.model.vod.Subject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author xuge
 * @since 2022-07-01
 */
public interface SubjectService extends IService<Subject> {

  List<Subject> selectSubjctList(Long id);

  void exportData(HttpServletResponse response);

  Boolean importData(MultipartFile file);
}
