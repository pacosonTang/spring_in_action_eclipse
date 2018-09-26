package com.atguigu.crud.dao;

import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.bean.EmployeeExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface EmployeeMapper {
    long countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(String rcrdId);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(String rcrdId);

    int updateByExampleSelective(@Param("record") Employee record
    		, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record
    		, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
    
    List<Employee> empListQry(@Param("deptRcrdId") String deptRcrdId);
    
    List<Map<String, Object>> empMapListQry(@Param("deptRcrdId") String deptRcrdId);
}