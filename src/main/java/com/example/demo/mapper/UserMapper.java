package com.example.demo.mapper;

import com.example.demo.model.PageContent;
import com.example.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/10/30
 */
@Mapper
@Component
public interface UserMapper {

    User selectById(Long id);

//    @Select("<script>\n" +
//            "select * from user\n" +
//            "<where>\n" +
//            "    <if test = \"id != null\">\n" +
//            "        and id = #{id}\n" +
//            "    </if>\n" +
//            "    <if test = \"userName != null\">\n" +
//            "        and username = #{userName}\n" +
//            "    </if>\n" +
//            "    <if test = \"passWord != null\">\n" +
//            "        and password = #{passWord}\n" +
//            "    </if>\n" +
//            "</where>\n" +
//            "</script>")
    List<User> selectByUserAndPage(@Param("user") User user,@Param("page") PageContent page);

    User selectOne(User user);

//    @Insert("insert into user(username,password,privilege,register_time,email)" +
//            "values(#{userName},#{passWord},#{privilege},#{registerTime},#{email})")
//    @Options(useGeneratedKeys = true)
    int insert(User user);

//    @Update("<script>\n" +
//            "    update user\n" +
//            "    <set>\n" +
//            "        <if test=\"userName!=null\">\n" +
//            "            username = #{userName},\n" +
//            "        </if>\n" +
//            "        <if test=\"passWord!=null\">\n" +
//            "            password = #{passWord},\n" +
//            "        </if>\n" +
//            "    </set>\n" +
//            "    where id = #{id}\n" +
//            "</script>")
    long update(User user);

//    @Delete("delete from user where username = #{userName}")
    long delete(User user);
}
