package com.jerry.demo.usercenter.data.utils;

import com.jerry.demo.usercenter.api.enums.AuthType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthTypeHandler extends BaseTypeHandler<AuthType> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, AuthType type, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, type.code);
    }

    @Override
    public AuthType getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return AuthType.valueOf(resultSet.getInt(s));
    }

    @Override
    public AuthType getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return AuthType.valueOf(resultSet.getInt(i));
    }

    @Override
    public AuthType getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return AuthType.valueOf(callableStatement.getInt(i));
    }
}
