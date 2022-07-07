package com.xuge.ggkt.vod.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xuge.ggkt.exception.GgktException;
import com.xuge.ggkt.vod.listener.SubjectListener;
import com.xuge.ggkt.vod.mapper.SubjectMapper;
import com.xuge.ggkt.vod.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuge.model.vod.Subject;
import com.xuge.vo.vod.SubjectEeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author xuge
 * @since 2022-07-01
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {
  @Autowired
  private SubjectListener subjectListener;
  @Override
  public List<Subject> selectSubjctList(Long id) {
    QueryWrapper<Subject> qw = new QueryWrapper<>();
    qw.eq("parent_id",id);
    List<Subject> list = this.list(qw);
    //todo  数据进一步封装
    //1.遍历集合，判断是否有下层数据
    for (Subject subject : list) {
      Long id1 = subject.getId();
      //查询
      if(this.isChildren(id1)){
        //设置是否有下层数据
        subject.setHasChildren(true);
      }

    }
    return list;





  }
  //课程导出功能实现
  @Override
  public void exportData(HttpServletResponse response) {
    try {
      response.setContentType("application/vnd.ms-excel");
      response.setCharacterEncoding("utf-8");
      // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
      String fileName = URLEncoder.encode("课程分类", "UTF-8");
      response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");
      List<Subject> dictList = baseMapper.selectList(null);
      List<SubjectEeVo> dictVoList = new ArrayList<>(dictList.size());
      //将subject==>subjectVo
      for(Subject dict : dictList) {
        SubjectEeVo dictVo = new SubjectEeVo();
        BeanUtils.copyProperties(dict,dictVo);
        dictVoList.add(dictVo);
      }
      //写
      EasyExcel.write(response.getOutputStream(), SubjectEeVo.class).sheet("课程分类").doWrite(dictVoList);
    } catch (IOException e) {
      e.printStackTrace();
      throw new GgktException(20001,"导出课程失败");
    }
  }
 //课程分类导入
  @Override
  public Boolean importData(MultipartFile file) {
    try {
      EasyExcel.read(file.getInputStream(),SubjectEeVo.class,subjectListener).sheet().doRead();
      return true;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  private boolean isChildren(Long id1) {
    QueryWrapper<Subject> qw = new QueryWrapper<>();
    qw.eq("parent_id",id1);
    return baseMapper.selectCount(qw)>0;
  }
}
