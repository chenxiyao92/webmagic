package com.cxyhome.webmagic.mybaties;

import com.cxyhome.webmagic.domain.Trademark;
import com.cxyhome.webmagic.domain.User;
import com.cxyhome.webmagic.mapper.TrademarkMapper;
import com.cxyhome.webmagic.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Transfer {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();
        try {
            TrademarkMapper mapper = session.getMapper(TrademarkMapper.class);
            for (int i = 1; i <= 90440 ; i++) {
                 Trademark trademark = mapper.getTrademarkById(i);
                int result = mapper.insertTmClassificationInfo(trademark);
                session.commit();

            }




        } finally {
            session.close();
        }



    }
}
