package com.bjitic.p2p.mapper.user;

import com.bjitic.p2p.model.user.User;

public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table u_user
     *
     * @mbggenerated Tue Aug 27 16:14:54 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table u_user
     *
     * @mbggenerated Tue Aug 27 16:14:54 CST 2019
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table u_user
     *
     * @mbggenerated Tue Aug 27 16:14:54 CST 2019
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table u_user
     *
     * @mbggenerated Tue Aug 27 16:14:54 CST 2019
     */
    User selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table u_user
     *
     * @mbggenerated Tue Aug 27 16:14:54 CST 2019
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table u_user
     *
     * @mbggenerated Tue Aug 27 16:14:54 CST 2019
     */
    int updateByPrimaryKey(User record);

    /**
     * 获取平台注册总人数
     * @return
     */
    Long selectAllUserCount();

}