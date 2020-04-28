package com.cmccsi.user.server.service.impl;

import com.cmccsi.user.server.annotation.CurDataSource;
import com.cmccsi.user.server.config.DataSourceNames;
import com.cmccsi.user.server.entity.Hr;
import com.cmccsi.user.server.dao.HrDao;
import com.cmccsi.user.server.service.HrService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Hr)表服务实现类
 *
 * @author makejava
 * @since 2020-04-27 19:59:05
 */
@Service("hrService")
public class HrServiceImpl implements HrService {
    @Resource
    private HrDao hrDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    @CurDataSource(name= DataSourceNames.SECOND)
    public Hr queryById(Integer id) {
        return this.hrDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    @CurDataSource(name= DataSourceNames.SECOND)
    public List<Hr> queryAllByLimit(int offset, int limit) {
        return this.hrDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param hr 实例对象
     * @return 实例对象
     */
    @Override
    public Hr insert(Hr hr) {
        this.hrDao.insert(hr);
        return hr;
    }

    /**
     * 修改数据
     *
     * @param hr 实例对象
     * @return 实例对象
     */
    @Override
    @CurDataSource(name= DataSourceNames.SECOND)
    public Hr update(Hr hr) {
        this.hrDao.update(hr);
        return this.queryById(hr.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    @CurDataSource(name= DataSourceNames.SECOND)
    public boolean deleteById(Integer id) {
        return this.hrDao.deleteById(id) > 0;
    }
}