package com.xuge.ggkt.vod.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuge.model.vod.VideoVisitor;
import com.xuge.vo.vod.VideoVisitorCountVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 视频来访者记录表 Mapper 接口
 * </p>
 *
 * @author xuge
 * @since 2022-07-07
 */
public interface VideoVisitorMapper extends BaseMapper<VideoVisitor> {

  List<VideoVisitorCountVo> findCount(@Param("courseId")Long courseId, @Param("startDate")String startDate, @Param("endDate")String endDate);
}
