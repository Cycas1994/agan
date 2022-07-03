package com.cycas.transaction.dao;

import com.cycas.transaction.pojo.Student;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDAO {
    @Insert({
            "insert into student (",
            "s_id, s_name,",
            "s_birth, s_sex)",
            "values( ",
            "#{sId,jdbcType=VARCHAR}, #{sName,jdbcType=VARCHAR},",
            "#{sBirth,jdbcType=VARCHAR}, #{sSex,jdbcType=VARCHAR})",
    })
    int insert(Student student);

    @Select({
            "select ",
            "s_id, s_name, s_birth, s_sex",
            "from student ",
            "where id = #{id,jdbcType=INTEGER} "
    })
    @Results({
            @Result(column = "s_id", property = "sId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "s_name", property = "sName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "s_birth", property = "sBirth", jdbcType = JdbcType.VARCHAR),
            @Result(column = "s_sex", property = "sSex", jdbcType = JdbcType.VARCHAR),
    })
    Student selectByPrimaryKey(Integer id);

    @Update({
            "update student set ",
            "s_id = #{sId,jdbcType=VARCHAR},",
            "s_name = #{sName,jdbcType=VARCHAR},",
            "s_birth = #{sBirth,jdbcType=VARCHAR},",
            "s_sex = #{sSex,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Student student);
}
