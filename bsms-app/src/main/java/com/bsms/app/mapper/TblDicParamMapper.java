package com.bsms.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bsms.app.model.TblDicParam;
import com.bsms.app.model.TblDicParamExample;

public interface TblDicParamMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dic_param
     *
     * @mbg.generated Fri Nov 16 12:25:58 CST 2018
     */
    long countByExample(TblDicParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dic_param
     *
     * @mbg.generated Fri Nov 16 12:25:58 CST 2018
     */
    int deleteByExample(TblDicParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dic_param
     *
     * @mbg.generated Fri Nov 16 12:25:58 CST 2018
     */
    int deleteByPrimaryKey(Long pkDicParam);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dic_param
     *
     * @mbg.generated Fri Nov 16 12:25:58 CST 2018
     */
    int insert(TblDicParam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dic_param
     *
     * @mbg.generated Fri Nov 16 12:25:58 CST 2018
     */
    int insertSelective(TblDicParam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dic_param
     *
     * @mbg.generated Fri Nov 16 12:25:58 CST 2018
     */
    List<TblDicParam> selectByExample(TblDicParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dic_param
     *
     * @mbg.generated Fri Nov 16 12:25:58 CST 2018
     */
    TblDicParam selectByPrimaryKey(Long pkDicParam);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dic_param
     *
     * @mbg.generated Fri Nov 16 12:25:58 CST 2018
     */
    int updateByExampleSelective(@Param("record") TblDicParam record, @Param("example") TblDicParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dic_param
     *
     * @mbg.generated Fri Nov 16 12:25:58 CST 2018
     */
    int updateByExample(@Param("record") TblDicParam record, @Param("example") TblDicParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dic_param
     *
     * @mbg.generated Fri Nov 16 12:25:58 CST 2018
     */
    int updateByPrimaryKeySelective(TblDicParam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dic_param
     *
     * @mbg.generated Fri Nov 16 12:25:58 CST 2018
     */
    int updateByPrimaryKey(TblDicParam record);
}