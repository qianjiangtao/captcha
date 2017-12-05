package com.lvmama.captcha.web.controller;

import com.lvmama.captcha.model.BusinessType;
import com.lvmama.captcha.model.RiskscoreSecurelevel;
import com.lvmama.captcha.service.BusinessTypeService;
import com.lvmama.captcha.service.RiskscoreSecurelevelService;
import com.lvmama.captcha.vo.RiskscoreSecurelevelVo;
import com.lvmama.comm.pet.vo.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

/**
 * Created by Administrator on 2016/11/23.
 */
@Controller
@RequestMapping(value = "/riskscoreSecurelevelController")
public class RiskscoreSecurelevelController extends BaseController{

    private static final Log logger = LogFactory.getLog(RiskscoreSecurelevelController.class);

    @Resource
    private BusinessTypeService businessTypeService;

    @Resource
    private RiskscoreSecurelevelService riskscoreSecurelevelService;

    @RequestMapping(value ="/riskscoreSecurelevelIndex.do")
    public ModelAndView riskscoreSecurelevelIndex(Long id){
        ModelAndView mav = new ModelAndView("WEB-INF/pages/riskscoreSecurelevel");
        try {
            if(id == null) {
                List<BusinessType> businessTypesList = businessTypeService.selectByRiskControl();
                List<Map> list = new ArrayList<Map>();
                for (BusinessType businessType : businessTypesList) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("code", businessType.getCode());
                    map.put("codeDesc", businessType.getCodeDesc());
                    map.put("canSetLevel", businessType.getCanSetLevel());
                    list.add(map);
                }
                mav.addObject("businessTypeList", list);
            }else {
                RiskscoreSecurelevelVo riskscoreSecurelevelVo = riskscoreSecurelevelService.selectRiskscoreSecurelevelById(id);
                mav.addObject("riskscoreSecurelevelVo",riskscoreSecurelevelVo);
            }
        }catch (Exception e){
            logger.error("打开保存页面或新建页面失败,riskscoreSecurelevelIndex()抛出异常:",e);
        }
        return mav;
    }

    @RequestMapping(value ="/save.do")
    public void save(Long id , String businessCode,String secureLevel,Integer fromScore, Integer toScore,HttpServletResponse response){
        boolean result = true;
        try{
            RiskscoreSecurelevel riskscoreSecurelevel = new RiskscoreSecurelevel();
            riskscoreSecurelevel.setId(id);
            riskscoreSecurelevel.setBusinessCode(businessCode);
            riskscoreSecurelevel.setSecureLevel(secureLevel);
            riskscoreSecurelevel.setFromScore(fromScore);
            riskscoreSecurelevel.setToScore(toScore);
            riskscoreSecurelevel.setUpdateTime(new Date());
            Map<String,String> map = new HashMap<String,String>();
            map.put("businessCode",businessCode);
            map.put("secureLevel",secureLevel);
            if(!riskscoreSecurelevelService.decideById(id) && riskscoreSecurelevelService.selectCountByCondition(map)<1) {
                riskscoreSecurelevel.setCreateTime(new Date());
                riskscoreSecurelevelService.insert(riskscoreSecurelevel);
            }else {
                Long temp = riskscoreSecurelevelService.selectIdByCondition(businessCode,secureLevel);
                if(temp != null){
                    riskscoreSecurelevel.setId(temp);
                }
                riskscoreSecurelevelService.updateByPrimaryKeySelective(riskscoreSecurelevel);
            }
        }catch (Exception e){
            logger.error("风控分设置保存失败,save()抛出异常:", e);
            result = false;
        }
        returnJsonObject(response,result);
    }

    /**
     * 根据条件分页查询
     * @param businessCode
     * @param secureLevel
     * @param page
     * @return
     */
    @RequestMapping(value ="/index.do")
    public ModelAndView index(String businessCode,String secureLevel,Long page,HttpServletRequest request){
        ModelAndView mav=new ModelAndView("WEB-INF/pages/riskscoreSecurelevelIndex");
        Map<String,Object> parameter=new HashMap<String,Object>();
        //封装查询条件
        if(StringUtils.isNotBlank(businessCode)){
            parameter.put("businessCode",businessCode);
        }
        if(StringUtils.isNotBlank(secureLevel)){
            parameter.put("secureLevel",secureLevel);
        }
        try {
            List<BusinessType> businessTypesList = businessTypeService.selectByRiskControl();
            List<Map> list = new ArrayList<Map>();
            for (BusinessType businessType : businessTypesList) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("code", businessType.getCode());
                map.put("codeDesc", businessType.getCodeDesc());
                map.put("canSetLevel", businessType.getCanSetLevel());
                list.add(map);
            }
            mav.addObject("list",list);
            //查询符合条件订单的总数量
            Long totalCount = riskscoreSecurelevelService.selectCountByCondition(parameter);
            //封装分页信息
            page = (page == null) ? 1 : page;
            Page pageContent = new Page(totalCount, PAGE_SIZE, page);
            parameter.put("startRow", pageContent.getStartRows() - 1);
            parameter.put("pageSize", PAGE_SIZE);
            //分页查询
            List<RiskscoreSecurelevelVo> riskscoreSecurelevelList = riskscoreSecurelevelService.selectRiskscoreSecurelevelByCondition(parameter);
            pageContent.setItems(riskscoreSecurelevelList);
            //请求url
            pageContent.buildUrl(request);
            mav.addObject("pageContent", pageContent);
            //获取查询参数进行回显
            String codeDesc = businessTypeService.selectCodeDescByCode(businessCode);
            echoQueryData(mav,"businessCode",businessCode,codeDesc,"secureLevel",secureLevel);
        } catch (Exception e) {
            logger.error("条件查询失败,index()抛出异常:",e);
        }
        return mav;
    }

    @RequestMapping(value = "/delete.do")
    public void deleteByPrimaryKey(Long id ,HttpServletResponse response){
        try{
            riskscoreSecurelevelService.deleteByPrimaryKey(id);
            returnJsonObject(response, true, "删除风控分成功！");
        }catch (Exception e){
            returnJsonObject(response,false,"删除风控分失败！");
            logger.error("删除风控分失败,delete()抛出异常:",e);
        }
    }

}
