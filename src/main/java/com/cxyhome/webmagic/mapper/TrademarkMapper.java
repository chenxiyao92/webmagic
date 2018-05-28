package com.cxyhome.webmagic.mapper;

import com.cxyhome.webmagic.domain.Trademark;
import org.apache.ibatis.annotations.Param;

public interface TrademarkMapper {

    Trademark getTrademarkById(@Param("id") int id);

    int insertTmClassificationInfo(@Param("trademark")Trademark trademark);

}
