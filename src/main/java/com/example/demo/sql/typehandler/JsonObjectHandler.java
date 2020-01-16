package com.example.demo.sql.typehandler;

import com.example.demo.util.ObjectMapperUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.util.Assert;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Desc Mysql JSON Type Column Type Handler
 * @Author Mushen
 * @Create 2019-11-21
 */
public class JsonObjectHandler<T> extends BaseTypeHandler<T> {
    private Class<T> clazz;

    public JsonObjectHandler(Class<T> clazz) {
        Assert.notNull(clazz, "Type argument cannot be null");
        this.clazz = clazz;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, ObjectMapperUtil.writeValueAsString(parameter));
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return ObjectMapperUtil.readValue(rs.getString(columnName), clazz);
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return ObjectMapperUtil.readValue(rs.getString(columnIndex), clazz);
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return ObjectMapperUtil.readValue(cs.getString(columnIndex), clazz);
    }
}
