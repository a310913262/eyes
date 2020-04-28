package com.cmccsi.user.server.dao;

import com.cmccsi.user.server.entity.DmpNode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * (DmpNode)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-10 15:28:04
 */
@Component
public interface DmpNodeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param nodeId 主键
     * @return 实例对象
     */
    DmpNode queryById(String nodeId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DmpNode> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param dmpNode 实例对象
     * @return 对象列表
     */
    List<DmpNode> queryAll(DmpNode dmpNode);

    /**
     * 新增数据
     *
     * @param dmpNode 实例对象
     * @return 影响行数
     */
    int insert(DmpNode dmpNode);

    /**
     * 修改数据
     *
     * @param dmpNode 实例对象
     * @return 影响行数
     */
    int update(DmpNode dmpNode);

    /**
     * 通过主键删除数据
     *
     * @param nodeId 主键
     * @return 影响行数
     */
    int deleteById(String nodeId);

}