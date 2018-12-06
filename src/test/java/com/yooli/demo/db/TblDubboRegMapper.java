package com.yooli.demo.db;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by shaoxi.zhu
 * Date: 2018/11/15
 * Time: 5:15 PM
 */
public class TblDubboRegMapper implements RowMapper {
    @Nullable
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }
}
