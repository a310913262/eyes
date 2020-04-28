package com.cmccsi.user.server.service;

import com.cmccsi.user.server.entity.DmpNode;
import java.util.List;

/**
 * (DmpNode)表服务接口
 *
 * @author makejava
 * @since 2020-04-10 15:28:04
 */
public interface DmpNodeService {

    /**
     * 通过ID查询单条数据
     *
     * @param nodeId 主键
     * @return 实例对象
     */
    DmpNode queryById(String nodeId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DmpNode> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param dmpNode 实例对象
     * @return 实例对象
     */
    DmpNode insert(DmpNode dmpNode);

    /**
     * 修改数据
     *
     * @param dmpNode 实例对象
     * @return 实例对象
     */
    DmpNode update(DmpNode dmpNode);

    /**
     * 通过主键删除数据
     *
     * @param nodeId 主键
     * @return 是否成功
     */
    boolean deleteById(String nodeId);

}