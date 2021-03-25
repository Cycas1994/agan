package com.cycas.dao;

import com.cycas.pojo.Account;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao {

    @Update({
            "update account set ",
            "used = used + #{money,jdbcType=INTEGER},",
            "residue = residue - #{money,jdbcType=INTEGER}",
            "where user_id = #{userId,jdbcType=BIGINT}"
    })
    void decrease(@Param("userId") Long userId, @Param("money") Integer money);

    @Insert({
            "insert into account (",
            "id, user_id,",
            "total, used,",
            "residue)",
            "values( ",
            "#{id,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER},",
            "#{total,jdbcType=DECIMAL}, #{used,jdbcType=DECIMAL},",
            "#{residue,jdbcType=DECIMAL})",
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Account account);

}
