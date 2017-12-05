package com.lvmama.captcha.dao;

import com.lvmama.captcha.dto.BusinessTypeDTO;
import org.springframework.stereotype.Repository;

import com.lvmama.captcha.dao.base.MysqlDaoSupport;
import com.lvmama.captcha.model.BusinessType;

import java.util.List;
import java.util.Map;


@Repository
public class BusinessTypeDao extends MysqlDaoSupport {
    public BusinessTypeDao() {
        super("com.lvmama.captcha.dao.BusinessType");
    }

    public int deleteByPrimaryKey(Long id) {
        return super.delete("deleteByPrimaryKey", id);
    }

    public int insert(BusinessType record) {
        return super.insert("insert", record);
    }

    public BusinessType selectByPrimaryKey(Long id) {
        return super.get("selectByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(BusinessType record) {
        return super.update("updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(BusinessType record) {
        return super.update("updateByPrimaryKey", record);
    }

    public List<BusinessType> selectByRiskControl() {
        return super.queryForList("selectByRiskControl");
    }

    public Boolean selectByCode(String code) {
        return super.get("selectByCode", code) == null ? true : false;
    }

    public String selectCodeDescByCode(String code) {
        return super.get("selectCodeDescByCode", code);
    }

    public Long selectCountByCondition(Map map) {
        return super.get("selectCountByCondition", map);
    }

    public List<BusinessType> selectBusinessTypeByCondition(Map map) {
        return super.queryForList("selectBusinessTypeByCondition", map);
    }

    public List<BusinessType> selectCondition() {
        return super.queryForList("selectCondition");
    }

    public List<String> queryAllBusinessCode() {
        return super.queryForList("queryAllBusinessCode");
    }

    public String queryDefaultLevelByCode(String businessTypeCode){
        return super.get("queryDefaultLevelByCode",businessTypeCode);
    }

    public BusinessTypeDTO queryBusinessTypeByCode(String businessTypeCode) {
        return super.get("queryBusinessTypeByCode", businessTypeCode);
    }

    public List<BusinessTypeDTO> queryCodeAndDefaultLevel(){
        return super.queryForList("queryCodeAndLevel");
    }
}