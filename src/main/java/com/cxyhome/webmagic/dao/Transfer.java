package com.cxyhome.webmagic.dao;

import com.cxyhome.webmagic.domain.TmInfo;
import com.cxyhome.webmagic.domain.Trademark;
import com.cxyhome.webmagic.mapper.TrademarkMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class Transfer {

    public static void main(String[] args) throws IOException, ParseException {
        trademarkToTradeInfo();
//            insertToTrademark();
    }

    private static  void insertToTrademark() throws IOException {
        //设置自动提交
        SqlSession session = getSqlSession();
        Trademark trademark = new Trademark();
        TrademarkMapper mapper = session.getMapper(TrademarkMapper.class);
        for (int i = 0; i < 20000000; i++) {
            System.out.println("当前插入第"+i+"条");
            trademark.setId((int)(Math.random() * 1000000));
            trademark.setNumber(UUID.randomUUID().toString());
            mapper.insertToTrademark(trademark);
            if (i % 100 == 0 ){
                session.clearCache();
                session.commit();
            }
        }
    }



    private static void trademarkToTradeInfo() throws IOException, ParseException {
        SqlSession session = getSqlSession();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        try {
            TrademarkMapper mapper = session.getMapper(TrademarkMapper.class);
            for (int i = 1; i <= 90440 ; i++) {
                Trademark trademark = mapper.getTrademarkById(i);
                //申请日期
                TmInfo tmInfo = new TmInfo();
                tmInfo.setId(null);
                tmInfo.setMid(null);
                tmInfo.setImgAddr(trademark.getThumbnail());
                tmInfo.setMark(trademark.getTitle());
                tmInfo.setSerialNumber(trademark.getNumber());
                tmInfo.setClassificationInfo(trademark.getRelated());
                tmInfo.setRegistrationNumber(trademark.getNumber());

                if (trademark.getApplyDate()!=null &&  trademark.getApplyDate().length()>0){
                    tmInfo.setFilingDate(sdf.parse(trademark.getApplyDate()));
                }else {
                    tmInfo.setFilingDate(null);
                }
                if (trademark.getI18NType()!=null && trademark.getI18NType().length()>0){
                    tmInfo.setInternationalClasses(Integer.parseInt(trademark.getI18NType()));
                }else {
                    tmInfo.setInternationalClasses(null);
                }
                tmInfo.setApplicantName(trademark.getApplicantZh());
                tmInfo.setApplicantNameEn(trademark.getApplicantAddrEn());
                tmInfo.setApplicantAddress(trademark.getApplicantAddrZh());
                tmInfo.setApplicantAddressEn(trademark.getApplicantAddrEn());

                if (trademark.getReview1StCode()!=null && trademark.getReview1StCode().length()>0){
                    tmInfo.setFirstPublicationNumber(Integer.parseInt(trademark.getReview1StCode()));
                }else {
                    tmInfo.setFirstPublicationNumber(null);
                }

                if (trademark.getReview1StDate()!=null &&  trademark.getReview1StDate().length()>0){
                    tmInfo.setPublishedOppositionDate(sdf.parse(trademark.getReview1StDate()));
                }else{
                    tmInfo.setPublishedOppositionDate(null);
                }
                tmInfo.setIsMultipleOwners(trademark.getBrandShared());
                tmInfo.setMultipleOwners(trademark.getSharedList());
                tmInfo.setRegisterType(trademark.getBrandType());

                if (trademark.getBrandExpired()==null && trademark.getBrandExpired().length()>0){
                tmInfo.setPossessionTermStart(sdf.parse(trademark.getPossessionTermStart()));
                tmInfo.setPossessionTermEnd(sdf.parse(trademark.getPossessionTermEnd()));
                }else {
                    tmInfo.setPossessionTermStart(null);
                    tmInfo.setPossessionTermEnd(null);
                }


                tmInfo.setMarkType(trademark.getBrandForm());
                if (trademark.getI18NRegisterDate()!=null && trademark.getI18NRegisterDate().length()>0){
                 tmInfo.setInternationalRegistrationDate(sdf.parse(trademark.getI18NRegisterDate()));
                }else {
                    tmInfo.setInternationalRegistrationDate(null);
                }
                if (trademark.getTargetDate() != null && trademark.getTargetDate().length()>0){
                  tmInfo.setLateSpecifiedDate(sdf.parse(trademark.getTargetDate()));
                }else {
                    tmInfo.setLateSpecifiedDate(null);
                }
                tmInfo.setPriorityDate(trademark.getPriorityDate());
                tmInfo.setCorrespondent(trademark.getAgentName());
                tmInfo.setBrandStatus(trademark.getBrandStatus());
                int result = mapper.insertInfo(tmInfo);
                session.commit();
            }
        } finally {
            session.close();
        }
    }

    private static SqlSession getSqlSession() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory.openSession();
    }
}
