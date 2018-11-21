package com.bsms.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bsms.app.model.TblMarketingActivity;
import com.bsms.app.model.TblMarketingActivityExample;

public interface TblMarketingActivityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_marketing_activity
     *
     * @mbg.generated Thu Nov 15 20:59:20 CST 2018
     */
    long countByExample(TblMarketingActivityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_marketing_activity
     *
     * @mbg.generated Thu Nov 15 20:59:20 CST 2018
     */
    int deleteByExample(TblMarketingActivityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_marketing_activity
     *
     * @mbg.generated Thu Nov 15 20:59:20 CST 2018
     */
    int insert(TblMarketingActivity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_marketing_activity
     *
     * @mbg.generated Thu Nov 15 20:59:20 CST 2018
     */
    int insertSelective(TblMarketingActivity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_marketing_activity
     *
     * @mbg.generated Thu Nov 15 20:59:20 CST 2018
     */
    List<TblMarketingActivity> selectByExample(TblMarketingActivityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_marketing_activity
     *
     * @mbg.generated Thu Nov 15 20:59:20 CST 2018
     */
    int updateByExampleSelective(@Param("record") TblMarketingActivity record, @Param("example") TblMarketingActivityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_marketing_activity
     *
     * @mbg.generated Thu Nov 15 20:59:20 CST 2018
     */
    int updateByExample(@Param("record") TblMarketingActivity record, @Param("example") TblMarketingActivityExample example);
}