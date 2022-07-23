package com.cycas.dao;

import com.cycas.pojo.Order;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao {

    @Insert({
            "insert into order_tbl (",
            "user_id,",
            "product_id, count,",
            "money, status)",
            "values( ",
            "#{userId,jdbcType=BIGINT},",
            "#{productId,jdbcType=BIGINT}, #{count,jdbcType=INTEGER},",
            "#{money,jdbcType=DECIMAL}, 0)",
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Order order);


    @Update({
            "update order_tbl set ",
            "status = 1",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int updateStatus(Integer id);

    @Select({
            "select ",
            "id, user_id, product_id, count, money,",
            "status",
            "from order_tbl ",
            "where id = #{id,jdbcType=BIGINT} "
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.BIGINT),
            @Result(column = "product_id", property = "productId", jdbcType = JdbcType.BIGINT),
            @Result(column = "count", property = "count", jdbcType = JdbcType.INTEGER),
            @Result(column = "money", property = "money", jdbcType = JdbcType.DECIMAL),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
    })
    Order selectByPrimaryKey(Long id);

}
