package com.cycas.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageDao {

    @Update({
            "update storage_tbl set ",
            "used = used + #{count,jdbcType=INTEGER},",
            "residue = residue - #{count,jdbcType=INTEGER}",
            "where product_id = #{productId,jdbcType=BIGINT}"
    })
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);

}
