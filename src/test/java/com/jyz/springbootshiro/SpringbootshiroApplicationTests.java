package com.jyz.springbootshiro;

import com.jyz.springbootshiro.mvc.dao.UserDao;
import com.jyz.springbootshiro.mvc.entity.UserEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootshiroApplicationTests {

    @Test
    public void contextLoads() throws  Exception{
        System.out.println("asdf");
    }

}
