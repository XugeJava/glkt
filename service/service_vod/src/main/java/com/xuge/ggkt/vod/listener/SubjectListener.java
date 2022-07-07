package com.xuge.ggkt.vod.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.xuge.ggkt.vod.mapper.SubjectMapper;
import com.xuge.model.vod.Subject;
import com.xuge.vo.vod.SubjectEeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * author: yjx
 * Date :2022/7/114:18
 **/
@Component
public class SubjectListener  extends AnalysisEventListener<SubjectEeVo> {
  @Autowired
  private SubjectMapper subjectMapper;
  @Override
  public void invoke(SubjectEeVo subjectEeVo, AnalysisContext analysisContext) {
    Subject subject = new Subject();
    BeanUtils.copyProperties(subjectEeVo, subject);
    subjectMapper.insert(subject);
  }

  @Override
  public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    super.invokeHeadMap(headMap, context);
  }

  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {

  }
}
