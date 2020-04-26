package com.zpb.cloud.interceptor;


import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.Properties;

/**
 * @dec :
 * @Date: 2020/4/10
 * @Auther: pengbo.zhao
 * @version: 1.0
 *
 *  @Intercepts 说明是一个拦截器
 *  @Signature 拦截器的签名
 *  type 拦截的类型 四大对象之一( Executor,ResultSetHandler,ParameterHandler,StatementHandler)
 *  method 拦截的方法
 *  args 参数,高版本需要加个Integer.class参数,不然会报错
 *
 */
//@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class MyBatisInterceptor implements Interceptor {

    //每页显示的条目数
    private int pageSize;

    //当前页数
    private int currPage;

    //数据库类型
    private String dbType;
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //获取StatementHandler，默认是RoutingStatementHandler
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();

        //获取statementHandler包装类
        MetaObject MetaObjectHandler = SystemMetaObject.forObject(statementHandler);

        //分离代理对象链
        while (MetaObjectHandler.hasGetter("h")) {
            Object obj = MetaObjectHandler.getValue("h");
            MetaObjectHandler = SystemMetaObject.forObject(obj);
        }

        return null;
    }

    @Override
    public Object plugin(Object target) {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
