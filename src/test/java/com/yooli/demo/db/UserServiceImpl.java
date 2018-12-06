package com.yooli.demo.db;

import com.yooli.demo.domain.TblDubboRegDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by shaoxi.zhu
 * Date: 2018/11/15
 * Time: 5:13 PM
 */
public class UserServiceImpl implements IUserService {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(com.mchange.v2.c3p0.ComboPooledDataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public TblDubboRegDto addTblDubboRegDto() {
        jdbcTemplate.update("");
        return null;//todo

    }

}
