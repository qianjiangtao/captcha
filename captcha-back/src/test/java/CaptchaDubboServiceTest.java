import com.lvmama.captcha.dto.*;
import com.lvmama.captcha.service.BusinessTypeService;
import com.lvmama.captcha.service.CaptchaRuleDubboService;
import com.lvmama.captcha.vo.FontVO;
import com.lvmama.captcha.vo.NoiseVO;
import com.lvmama.captcha.vo.RiskscoreSecurelevelVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * Created by qianjiangtao on 2016/12/2.
 * 验证码dubbo接口测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring.xml"})
public class CaptchaDubboServiceTest{
    @Resource
    private CaptchaRuleDubboService captchaRuleDubboService;
    /**
     * 根据业务编码,安全级别查询验证码规则
     *
     * @return
     */
    @Test
    public void queryCaptchaRuleByCodeAndLevel(){
        CaptchaRuleDTO captchaRuleDTO= captchaRuleDubboService.queryCaptchaRuleByCodeAndLevel("login_web","primary");
        List<FontDTO> fontDTOs = captchaRuleDTO.getFonts();
        List<NoiseDTO> noiseVOs =captchaRuleDTO.getNoises();
        List<Integer> charColors = captchaRuleDTO.getCharColors();
        System.out.println("charColors:"+charColors);
        for(FontDTO font:fontDTOs){
            System.out.println("name:"+font.getName()+";size:"+font.getSize()+";style:"+font.getStyle());
        }
        for (NoiseDTO noise:noiseVOs){
            System.out.println("inColor:"+noise.getNoiseColor()+";width:"+noise.getNoiseWidth());
        }

    }

    /**
     * 根据业务编码查询所有业务信息
     *
     * @return
     */
    @Test
    public void queryAllBusinessCode(){
        List<String> captchaCodes = captchaRuleDubboService.queryAllBusinessCode();
        System.out.println(captchaCodes);
    }

    /**
     * 根据业务编码查询业务数据
     *
     * @return 默认安全等级, 是否接入风控, 应用标识, 事件标识
     */
    @Test
    public void queryBusinessTypeByCode(){
        BusinessTypeDTO businessTypeVO = captchaRuleDubboService.queryBusinessTypeByCode("登录注册");
        String code = businessTypeVO.getCode();
        String application = businessTypeVO.getApplicationId();
        String defaultLevel = businessTypeVO.getDefaultLevel();
        String riskControl = businessTypeVO.getRiskControl();
        System.out.println("++++++++++++code:"+code+";application:"+application+";defaultLevel:"+defaultLevel+";riskcontrol:"+riskControl);
    }
    @Test
    public void queryRiskscoreSecurelevelByCode(){
        List<RiskscoreSecurelevelDTO> riskscoreSecurelevelDTOs = captchaRuleDubboService.queryRiskscoreSecurelevelByCode("login_web");
        for (RiskscoreSecurelevelDTO riskscoreSecurelevelDTO:riskscoreSecurelevelDTOs){
            System.out.println(riskscoreSecurelevelDTO.getBusinessCode());
            System.out.println(riskscoreSecurelevelDTO.getFromScore());
            System.out.println(riskscoreSecurelevelDTO.getToScore());
            System.out.println(riskscoreSecurelevelDTO.getSecureLevel());
        }
    }
    @Test
    public void queryCodeAndLevel(){
        Map map = captchaRuleDubboService.queryCodeAndLevels();
        System.out.println(map);
    }
    @Test
    public void queryCodeAndDefaultLevel(){
        Map map = captchaRuleDubboService.queryCodeAndDefaultLevel();
        System.out.println(map);
    }
}
