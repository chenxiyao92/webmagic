package com.cxyhome.webmagic.mapper;

import com.cxyhome.webmagic.domain.*;
import org.apache.ibatis.annotations.Param;

public interface TrademarkMapper {

    Trademark getTrademarkById(@Param("id") int id);

    int insertInfo(@Param("info")Info info);

    int insertToTrademark(@Param("trademark")Trademark trademark);

    int insertProcessState(@Param("processState")ProcessState processState);

    int insertInternationalClasses(@Param("internationalClasses")InternationalClasses internationalClasses);

    int insertClassificationInfo(@Param("internationalClasses")ClassificationInfo classificationInfo);
}
