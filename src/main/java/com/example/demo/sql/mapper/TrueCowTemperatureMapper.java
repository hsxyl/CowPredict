package com.example.demo.sql.mapper;

import com.example.demo.sql.entity.TrueCowTemperature;
import com.example.demo.sql.entity.TrueCowTemperatureExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

public interface TrueCowTemperatureMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table true_cow_temperature
     *
     * @mbggenerated Sat Jan 11 21:54:14 CST 2020
     */
    int countByExample(TrueCowTemperatureExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table true_cow_temperature
     *
     * @mbggenerated Sat Jan 11 21:54:14 CST 2020
     */
    int deleteByExample(TrueCowTemperatureExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table true_cow_temperature
     *
     * @mbggenerated Sat Jan 11 21:54:14 CST 2020
     */
    @Delete({
        "delete from true_cow_temperature",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table true_cow_temperature
     *
     * @mbggenerated Sat Jan 11 21:54:14 CST 2020
     */
    @Insert({
        "insert into true_cow_temperature (time, ",
        "value, cow_id)",
        "values (#{time,jdbcType=TIMESTAMP,typeHandler=org.apache.ibatis.type.LocalDateTimeTypeHandler}, ",
        "#{value,jdbcType=REAL}, #{cowId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(TrueCowTemperature record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table true_cow_temperature
     *
     * @mbggenerated Sat Jan 11 21:54:14 CST 2020
     */
    int insertSelective(TrueCowTemperature record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table true_cow_temperature
     *
     * @mbggenerated Sat Jan 11 21:54:14 CST 2020
     */
    List<TrueCowTemperature> selectByExampleWithRowbounds(TrueCowTemperatureExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table true_cow_temperature
     *
     * @mbggenerated Sat Jan 11 21:54:14 CST 2020
     */
    List<TrueCowTemperature> selectByExample(TrueCowTemperatureExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table true_cow_temperature
     *
     * @mbggenerated Sat Jan 11 21:54:14 CST 2020
     */
    @Select({
        "select",
        "id, time, value, cow_id",
        "from true_cow_temperature",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    TrueCowTemperature selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table true_cow_temperature
     *
     * @mbggenerated Sat Jan 11 21:54:14 CST 2020
     */
    int updateByExampleSelective(@Param("record") TrueCowTemperature record, @Param("example") TrueCowTemperatureExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table true_cow_temperature
     *
     * @mbggenerated Sat Jan 11 21:54:14 CST 2020
     */
    int updateByExample(@Param("record") TrueCowTemperature record, @Param("example") TrueCowTemperatureExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table true_cow_temperature
     *
     * @mbggenerated Sat Jan 11 21:54:14 CST 2020
     */
    int updateByPrimaryKeySelective(TrueCowTemperature record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table true_cow_temperature
     *
     * @mbggenerated Sat Jan 11 21:54:14 CST 2020
     */
    @Update({
        "update true_cow_temperature",
        "set time = #{time,jdbcType=TIMESTAMP,typeHandler=org.apache.ibatis.type.LocalDateTimeTypeHandler},",
          "value = #{value,jdbcType=REAL},",
          "cow_id = #{cowId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TrueCowTemperature record);
}