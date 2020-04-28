package com.cmcc.xpa.service.microservice.notes.mapper;

import com.cmcc.xpa.service.microservice.notes.entity.XaProclamation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface XaProclamationMapper {
    int delete(Long pId);

    int insert(XaProclamation xaProclamation);

    int insertDynamic(XaProclamation xaProclamation);

    int updateDynamic(XaProclamation xaProclamation);

    int update(XaProclamation xaProclamation);

    XaProclamation selectByPId(Long pId);

    List<XaProclamation> findPageWithResult(@Param("pType") String pType, @Param("p_is_sucess") String p_is_sucess, @Param("star_time") String starTime, @Param("end_time") String endTime, @Param("offset") Integer offset, @Param("limit") Integer limit);

    long findPageWithCount(@Param("pType") String pType, @Param("p_is_sucess") String p_is_sucess, @Param("star_time") String starTime, @Param("end_time") String endTime);

    Long ProclamationBrowseVolume(Long p_id);


    int updateProclamationBrowseVolume(@Param("p_id") Long p_id, @Param("p_pageview") Long p_pageview);

    int updateProclamationStatus(@Param("p_id") Long p_id, @Param("p_is_sucess") String p_is_sucess);

    List<XaProclamation> selectNoticeHiding();

    List<Map<String, String>> queryModule(String userId);
}