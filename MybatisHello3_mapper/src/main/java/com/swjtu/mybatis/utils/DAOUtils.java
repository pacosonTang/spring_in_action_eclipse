package com.swjtu.mybatis.utils;

import com.swjtu.mybatis.exp.FrameException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Map;

public class DAOUtils {
    public static void insert(String sqlId, Object param, String db) {
        SqlSessionFactory sqlSessionFactory = MyBatisUtils.getSqlSessionFactory(db);
        if (sqlSessionFactory == null) {
            throw new FrameException("获取sql连接失败");
        }
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert(sqlId, param);
        } finally {
            if (sqlSession != null) {
                sqlSession.commit();
                sqlSession.close();
            }
        }
    }

    public static List<Map<String, Object>> selectList(String sqlId, Object param, String db) {
        SqlSessionFactory sqlSessionFactory = MyBatisUtils.getSqlSessionFactory(db);
        if (sqlSessionFactory == null) {
            throw new FrameException("获取sql连接失败");
        }
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            return sqlSession.selectList(sqlId, param);
        } finally {
            if (sqlSession != null) {
                sqlSession.commit();
                sqlSession.close();
            }
        }
    }

}
