package vu.lt.mybatis.dao;

import java.util.List;

import org.mybatis.cdi.Mapper;
import vu.lt.mybatis.model.Post;

@Mapper
public interface PostMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.POST
     *
     * @mbg.generated Sun May 01 15:16:32 EEST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.POST
     *
     * @mbg.generated Sun May 01 15:16:32 EEST 2022
     */
    int insert(Post record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.POST
     *
     * @mbg.generated Sun May 01 15:16:32 EEST 2022
     */
    Post selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.POST
     *
     * @mbg.generated Sun May 01 15:16:32 EEST 2022
     */
    List<Post> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.POST
     *
     * @mbg.generated Sun May 01 15:16:32 EEST 2022
     */
    int updateByPrimaryKey(Post record);
}