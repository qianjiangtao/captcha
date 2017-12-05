package com.lvmama.captcha.service.impl;

import com.lvmama.captcha.dao.BusinessTypeDao;
import com.lvmama.captcha.model.BusinessType;
import com.lvmama.captcha.service.BusinessTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/21.
 */
@Service("businessTypeService")
public class BusinessTypeServiceImpl implements BusinessTypeService {

    @Autowired
    private BusinessTypeDao businessTypeDao;

    @Override
    public int insert(BusinessType businessType) {
        return businessTypeDao.insert(businessType);
    }

    /*查询所有使用风控分的业务*/
    @Override
    public List<BusinessType> selectByRiskControl() {
        return businessTypeDao.selectByRiskControl();
    }

    public Boolean selectByCode(String code) {
        return businessTypeDao.selectByCode(code);
    }

    @Override
    public String selectCodeDescByCode(String code) {
        return businessTypeDao.selectCodeDescByCode(code);
    }

    @Override
    public Long selectCountByCondition(Map map) {
        return businessTypeDao.selectCountByCondition(map);
    }

    @Override
    public List<BusinessType> selectBusinessTypeByCondition(Map map) {
        return businessTypeDao.selectBusinessTypeByCondition(map);
    }

    @Override
    public List<BusinessType> selectCondition() {
        return businessTypeDao.selectCondition();
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return businessTypeDao.deleteByPrimaryKey(id);
    }

    @Override
    public BusinessType selectByPrimaryKey(Long id) {
        return businessTypeDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(BusinessType businessType) {
        return businessTypeDao.updateByPrimaryKeySelective(businessType);
    }

    @Override
    public String queryDefaultLevelByCode(String businessTypeCode) {
        return businessTypeDao.queryDefaultLevelByCode(businessTypeCode);
    }
}
