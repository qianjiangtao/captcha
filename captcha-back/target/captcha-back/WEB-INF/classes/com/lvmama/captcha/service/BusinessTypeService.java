package com.lvmama.captcha.service;

import com.lvmama.captcha.model.BusinessType;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/21.
 */
public interface BusinessTypeService {

    int insert(BusinessType businessType);

    /*查询所有使用风控分的业务*/
    List<BusinessType> selectByRiskControl();

    /*查询code是否重复*/
    Boolean selectByCode(String code);

    /* 根据code查询等级*/
    String selectCodeDescByCode(String code);

    Long selectCountByCondition(Map map);

    /*根据条件查询所有符合条件的业务编码*/
    List<BusinessType> selectBusinessTypeByCondition(Map map);

    /*根据所有业务编码的code，codeDesc，canSetLevel*/
    List<BusinessType> selectCondition();

    int deleteByPrimaryKey(Long id);

    BusinessType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessType businessType);

    String queryDefaultLevelByCode(String businessTypeCode);
}
