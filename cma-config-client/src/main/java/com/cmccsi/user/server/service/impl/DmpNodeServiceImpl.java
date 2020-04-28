package com.cmccsi.user.server.service.impl;

import com.cmccsi.user.server.annotation.CurDataSource;
import com.cmccsi.user.server.config.DataSourceNames;
import com.cmccsi.user.server.entity.DmpNode;
import com.cmccsi.user.server.dao.DmpNodeDao;
import com.cmccsi.user.server.service.DmpNodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (DmpNode)表服务实现类
 *
 * @author makejava
 * @since 2020-04-10 15:28:05
 */
@Service("dmpNodeService")
public class DmpNodeServiceImpl implements DmpNodeService {
    @Resource
    private DmpNodeDao dmpNodeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param nodeId 主键
     * @return 实例对象
     */
    @Override
    @CurDataSource(name= DataSourceNames.FIRST)
    public DmpNode queryById(String nodeId) {
        return this.dmpNodeDao.queryById(nodeId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    @CurDataSource(name= DataSourceNames.FIRST)
    public List<DmpNode> queryAllByLimit(int offset, int limit) {
        return this.dmpNodeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param dmpNode 实例对象
     * @return 实例对象
     */
    @Override
    @CurDataSource(name= DataSourceNames.FIRST)
    public DmpNode insert(DmpNode dmpNode) {
        this.dmpNodeDao.insert(dmpNode);
        return dmpNode;
    }

    /**
     * 修改数据
     *
     * @param dmpNode 实例对象
     * @return 实例对象
     */
    @Override
    @CurDataSource(name= DataSourceNames.FIRST)
    public DmpNode update(DmpNode dmpNode) {
        this.dmpNodeDao.update(dmpNode);
        return this.queryById(dmpNode.getNodeId());
    }

    /**
     * 通过主键删除数据
     *
     * @param nodeId 主键
     * @return 是否成功
     */
    @Override
    @CurDataSource(name= DataSourceNames.FIRST)
    public boolean deleteById(String nodeId) {
        return this.dmpNodeDao.deleteById(nodeId) > 0;
    }
}