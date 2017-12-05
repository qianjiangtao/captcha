package com.lvmama.captcha.dao;

import com.lvmama.captcha.dto.RiskscoreSecurelevelDTO;
import com.lvmama.captcha.vo.RiskscoreSecurelevelVo;
import org.springframework.stereotype.Repository;

import com.lvmama.captcha.dao.base.MysqlDaoSupport;
import com.lvmama.captcha.model.RiskscoreSecurelevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RiskscoreSecurelevelDao extends MysqlDaoSupport {
	public RiskscoreSecurelevelDao() {
		super("com.lvmama.captcha.dao.RiskscoreSecurelevel");
	}

	public int deleteByPrimaryKey(Long id) {
		return super.delete("deleteByPrimaryKey",id);
	}

	public int 	insert(RiskscoreSecurelevel record) {
		return super.insert("insert", record);
	}

	public RiskscoreSecurelevel selectByPrimaryKey(Long id) {
		return super.get("selectByPrimaryKey", id);
	}

	public int updateByPrimaryKeySelective(RiskscoreSecurelevel record) {
		return super.update("updateByPrimaryKeySelective", record);
	}

	public int updateByPrimaryKey(RiskscoreSecurelevel record) {
		return super.update("updateByPrimaryKey",record);
	}

	public Boolean decideById(Long id){
		return  super.get("decideById",id) == null?false:true;
	}

	public int updateByBusinessCodeSelective(RiskscoreSecurelevel record){
		return super.update("updateByBusinessCodeSelective",record);
	}

	public Long selectCountByCondition(Map map){
		return super.get("selectCountByCondition", map);
	}

	public List<RiskscoreSecurelevelVo> selectRiskscoreSecurelevelByCondition(Map map){
		return  super.queryForList("selectRiskscoreSecurelevelByCondition", map);
	}

	public RiskscoreSecurelevelVo selectRiskscoreSecurelevelById(Long id){
		return super.get("selectRiskscoreSecurelevelById", id);
	}
	public List<RiskscoreSecurelevelDTO> queryRiskscoreSecurelevelCode(String businessCode){
		return super.queryForList("queryRiskscoreSecurelevelCode", businessCode);
	}

	public Long selectIdByCondition(String businessCode,String secureLevel){
		Map<String,String> map = new HashMap<>();
		map.put("businessCode",businessCode);
		map.put("secureLevel",secureLevel);
		return super.get("selectIdByCondition",map);
	}
}