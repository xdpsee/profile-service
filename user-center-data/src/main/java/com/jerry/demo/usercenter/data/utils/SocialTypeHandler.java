package com.jerry.demo.usercenter.data.utils;

import com.jerry.demo.usercenter.api.enums.SocialType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SocialTypeHandler extends BaseTypeHandler<SocialType> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, SocialType type, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, type.code);
    }

    @Override
    public SocialType getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return SocialType.valueOf(resultSet.getInt(s));
    }

    @Override
    public SocialType getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return SocialType.valueOf(resultSet.getInt(i));
    }

    @Override
    public SocialType getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return SocialType.valueOf(callableStatement.getInt(i));
    }
}
