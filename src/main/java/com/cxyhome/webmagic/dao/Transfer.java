package com.cxyhome.webmagic.dao;

import com.cxyhome.webmagic.domain.Info;
import com.cxyhome.webmagic.domain.ProcessState;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Transfer {

    public static void main(String[] args) throws IOException, ParseException {
//        trademarkToTradeInfo();
//            insertToTrademark();
            trademarkToProcessState();
    }

    public static  void insertToTrademark() throws IOException {
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



    public static void trademarkToTradeInfo() throws IOException, ParseException {
        SqlSession session = getSqlSession();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        try {
            TrademarkMapper mapper = session.getMapper(TrademarkMapper.class);
            for (int i = 1; i <= 90440 ; i++) {
                Trademark trademark = mapper.getTrademarkById(i);
                //申请日期
                Info info = new Info();
                info.setId(null);
                info.setMid(null);
                info.setImgAddr(trademark.getThumbnail());
                info.setMark(trademark.getTitle());
                info.setSerialNumber(trademark.getNumber());
                info.setClassificationInfo(trademark.getRelated());
                info.setRegistrationNumber(trademark.getNumber());

                if (trademark.getApplyDate()!=null &&  trademark.getApplyDate().length()>0){
                    info.setFilingDate(sdf.parse(trademark.getApplyDate()));
                }else {
                    info.setFilingDate(null);
                }
                if (trademark.getI18NType()!=null && trademark.getI18NType().length()>0){
                    info.setInternationalClasses(Integer.parseInt(trademark.getI18NType()));
                }else {
                    info.setInternationalClasses(null);
                }
                info.setApplicantName(trademark.getApplicantZh());
                info.setApplicantNameEn(trademark.getApplicantAddrEn());
                info.setApplicantAddress(trademark.getApplicantAddrZh());
                info.setApplicantAddressEn(trademark.getApplicantAddrEn());

                if (trademark.getReview1StCode()!=null && trademark.getReview1StCode().length()>0){
                    info.setFirstPublicationNumber(Integer.parseInt(trademark.getReview1StCode()));
                }else {
                    info.setFirstPublicationNumber(null);
                }

                if (trademark.getReview1StDate()!=null &&  trademark.getReview1StDate().length()>0){
                    info.setPublishedOppositionDate(sdf.parse(trademark.getReview1StDate()));
                }else{
                    info.setPublishedOppositionDate(null);
                }
                info.setIsMultipleOwners(trademark.getBrandShared());
                info.setMultipleOwners(trademark.getSharedList());
                info.setRegisterType(trademark.getBrandType());

                if (trademark.getBrandExpired()==null && trademark.getBrandExpired().length()>0){
                info.setPossessionTermStart(sdf.parse(trademark.getPossessionTermStart()));
                info.setPossessionTermEnd(sdf.parse(trademark.getPossessionTermEnd()));
                }else {
                    info.setPossessionTermStart(null);
                    info.setPossessionTermEnd(null);
                }
                info.setMarkType(trademark.getBrandForm());
                if (trademark.getI18NRegisterDate()!=null && trademark.getI18NRegisterDate().length()>0){
                 info.setInternationalRegistrationDate(sdf.parse(trademark.getI18NRegisterDate()));
                }else {
                    info.setInternationalRegistrationDate(null);
                }
                if (trademark.getTargetDate() != null && trademark.getTargetDate().length()>0){
                  info.setLateSpecifiedDate(sdf.parse(trademark.getTargetDate()));
                }else {
                    info.setLateSpecifiedDate(null);
                }
                info.setPriorityDate(trademark.getPriorityDate());
                info.setCorrespondent(trademark.getAgentName());
                info.setBrandStatus(trademark.getBrandStatus());
                int result = mapper.insertInfo(info);
                session.commit();
            }
        } finally {
            session.close();
        }
    }

    public static SqlSession getSqlSession() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory.openSession();
    }


    public static void trademarkToProcessState() throws IOException, ParseException {
        SqlSession session = getSqlSession();
        TrademarkMapper mapper = session.getMapper(TrademarkMapper.class);
        ProcessState processState = new ProcessState();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        for (int i = 1; i <= 90440 ; i++) {
            Trademark trademark = mapper.getTrademarkById(i);
            processState.setId(null);
            processState.setMark(trademark.getTitle());
            if (trademark.getI18NType()!=null && trademark.getI18NType().length()>0){
                processState.setInternationalClasses(Long.parseLong(trademark.getI18NType()));
            }else {
                processState.setInternationalClasses(null);
            }
            processState.setSerialNumber(trademark.getNumber());
            processState.setBrandProcessState(trademark.getBrandProcedure());
            mapper.insertProcessState(processState);
            if (i%100 == 0){
                session.commit();
            }
        }




    }

    /**
     * 获取 trademark的url集合
     * @return
     * @throws IOException
     */
    public static List<String> getPicUrl() throws IOException {
        SqlSession session = getSqlSession();
        TrademarkMapper mapper = session.getMapper(TrademarkMapper.class);
        List<String> list = new ArrayList<String>();
        for (int i = 1; i <= 10000 ; i++) {
            list.add(mapper.getTrademarkById(i).getThumbnail());
        }
        return list;
    }


    /**
     * @return
     * @throws IOException
     */
    public static List<String> quanDaShiToDB() throws IOException {
        SqlSession session = getSqlSession();
        TrademarkMapper mapper = session.getMapper(TrademarkMapper.class);
        List<String> list = new ArrayList<String>();
        return null;
    }



}
