package com.cxyhome.webmagic.mapper;

import com.cxyhome.webmagic.domain.*;
import org.apache.ibatis.annotations.Param;

public interface TrademarkMapper {

    Trademark getTrademarkById(@Param("id") int id);

    int insertInfo(@Param("tmInfo")TmInfo tmInfo);

    int insertToTrademark(@Param("trademark")Trademark trademark);

    int insertProcessState(@Param("processState")TmProcessState processState);

    int insertInternationalClasses(@Param("internationalClasses")TmInternationalClasses internationalClasses);

    int insertClassificationInfo(@Param("internationalClasses")TmClassificationInfo classificationInfo);
}
