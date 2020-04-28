package com.cmcc.xpa.service.microservice.notes.mappers;

import com.cmcc.xpa.service.microservice.notes.entity.XaSysteamProclamation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XaSysteamProclamationMapper {
    int delete(Long pSysId);

    int insert(XaSysteamProclamation xaSysteamProclamation);

    int insertDynamic(XaSysteamProclamation xaSysteamProclamation);

    int updateDynamic(XaSysteamProclamation xaSysteamProclamation);

    int update(XaSysteamProclamation xaSysteamProclamation);

    XaSysteamProclamation selectByPSysId(Long pSysId);

    List<XaSysteamProclamation> findPageWithResult(@Param("star_time") String star_time, @Param("end_time") String end_time, @Param("p_sys_content") String p_sys_content, @Param("offset") Integer offset, @Param("limit") Integer limit);

    Integer findPageWithCount(@Param("star_time") String star_time, @Param("end_time") String end_time, @Param("p_sys_content") String p_sys_content);

    List<XaSysteamProclamation> queryfirstPage();

    List<XaSysteamProclamation> selectModuleSystem(String p_sys_module);
}