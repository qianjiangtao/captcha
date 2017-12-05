package com.lvmama.captcha.web.controller;

import com.lvmama.captcha.constant.CaptchaConstant;
import com.lvmama.captcha.model.BusinessType;
import com.lvmama.captcha.service.BusinessTypeService;
import com.lvmama.comm.pet.vo.Page;

import com.lvmama.comm.utils.MemcachedUtil;
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
 * Created by Administrator on 2016/11/21.
 */
@Controller
@RequestMapping(value = "/businessTypeController")
public class BusinessTypeController extends BaseController{

    private static final Log logger = LogFactory.getLog(BusinessTypeController.class);

    @Resource
    private BusinessTypeService businessTypeService;

    @RequestMapping(value ="/businessTypeIndex.do")
    public ModelAndView businessTypeIndex(Long id){
        ModelAndView mav = new ModelAndView("WEB-INF/pages/businessType");
        try {
            if (id != null) {
                BusinessType businessType = businessTypeService.selectByPrimaryKey(id);
                mav.addObject("businessType", businessType);
            }
        }catch (Exception e){
            logger.error("打开保存页面或新建页面失败,businessTypeIndex()抛出异常:",e);
        }
        return mav;
    }

    @RequestMapping(value ="/save.do")
    public void save(Long id, String code, String codeDesc, String canSetLevel/*,String riskControl*/, String defaultLevel/*,String applicationEnvetId*/, HttpServletResponse response) {
        boolean result = false;
        String message=null;
        try{
            BusinessType businessType = new BusinessType();
            businessType.setCode(code);
            businessType.setCodeDesc(codeDesc);
            businessType.setCanSetLevel(canSetLevel.substring(0, canSetLevel.length() - 1));
            /*businessType.setRiskControl(riskControl);*/
            businessType.setDefaultLevel(defaultLevel);
            /*if (applicationEnvetId != null) {
                String[] str = applicationEnvetId.split(",");
                businessType.setApplicationId(str[0]);
                businessType.setEventId(str[1]);
            }*/
            businessType.setUpdateTime(new Date());
            if(businessTypeService.selectByPrimaryKey(id)==null) {
                if (businessTypeService.selectByCode(code)) {
                    businessType.setCreateTime(new Date());
                    businessTypeService.insert(businessType);
                    result = true;
                }else {
                    message = "repeat";
                }
            }else{
                businessType.setId(id);
                businessTypeService.updateByPrimaryKeySelective(businessType);
                MemcachedUtil.getInstance().remove(CaptchaConstant.BUSINESS_TYPE_PREFIX + code);
                result = true;
            }
            //插入或更新成功删除code和等级接口缓存
            if (result){
                MemcachedUtil.getInstance().remove(CaptchaConstant.ALL_CODE_LEVELS);
                MemcachedUtil.getInstance().remove(CaptchaConstant.ALL_CODE_DEFAULT_LEVEL);
            }
        }catch (Exception e){
            logger.error("业务编码维护保存失败,save()抛出异常:",e);
            message = "exception";
        }
        returnJsonObject(response,result,message);
    }

    @RequestMapping(value = "/delete.do")
    public void deleteByPrimaryKey(Long id,String code ,HttpServletResponse response){
        try{
            int result = businessTypeService.deleteByPrimaryKey(id);
            if (result==1){
                returnJsonObject(response, true, "删除业务编码成功！");
                MemcachedUtil.getInstance().remove(CaptchaConstant.ALL_CODE_LEVELS);
                MemcachedUtil.getInstance().remove(CaptchaConstant.ALL_CODE_DEFAULT_LEVEL);
                MemcachedUtil.getInstance().remove(CaptchaConstant.BUSINESS_TYPE_PREFIX+code);
            }else{
                returnJsonObject(response,false,"删除业务编码失败！");
            }
        }catch (Exception e){
            returnJsonObject(response,false,"删除业务编码失败！");
            logger.error("删除业务编码失败,delete()抛出异常:",e);
        }
    }

    /**
     * 根据条件分页查询
     * @param code
     * @param canSetLevel
     * @param page
     * @return
     */
    @RequestMapping(value ="/index.do")
    public ModelAndView index(String code,String canSetLevel,Long page,HttpServletRequest request){
        ModelAndView mav=new ModelAndView("WEB-INF/pages/businessTypeIndex");
        Map<String,Object> parameter=new HashMap<String,Object>();
        //封装查询条件
        if(StringUtils.isNotBlank(code)){
            parameter.put("code",code);
        }
        if(StringUtils.isNotBlank(canSetLevel)){
            parameter.put("canSetLevel",canSetLevel);
        }
        try {
            mav.addObject("list",businessTypeService.selectCondition());
            //查询符合条件订单的总数量
            Long totalCount = businessTypeService.selectCountByCondition(parameter);
            //封装分页信息
            page = (page == null) ? 1 : page;
            Page pageContent = new Page(totalCount, PAGE_SIZE, page);
            parameter.put("startRow", pageContent.getStartRows() - 1);
            parameter.put("pageSize", PAGE_SIZE);
            //分页查询
            List<BusinessType> businessTypeList = businessTypeService.selectBusinessTypeByCondition(parameter);
            pageContent.setItems(businessTypeList);
            //请求url
            pageContent.buildUrl(request);
            mav.addObject("pageContent", pageContent);
            //获取查询参数进行回显
            String codeDesc = businessTypeService.selectCodeDescByCode(code);
            echoQueryData(mav,"code",code,codeDesc,"canSetLevel",canSetLevel);
        } catch (Exception e) {
            logger.error("条件查询失败,index()抛出异常:",e);
        }
        return mav;
    }

}
