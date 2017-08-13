package com.jerry.demo.usercenter.data.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StringListTypeHandler extends BaseTypeHandler<List<String>> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<String> strings, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, JSONUtils.toJsonString(strings));
    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, String s) throws SQLException {

        return JSONUtils.fromJsonString(resultSet.getString(s)
                , new TypeReference<List<String>>() {
                });
    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return JSONUtils.fromJsonString(resultSet.getString(i)
                , new TypeReference<List<String>>() {
                });
    }

    @Override
    public List<String> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return JSONUtils.fromJsonString(callableStatement.getString(i)
                , new TypeReference<List<String>>() {
                });
    }
}
