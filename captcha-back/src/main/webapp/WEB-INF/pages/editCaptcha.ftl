<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>验证码设置</title>
</head>
<body>
<div style="overflow-y: scroll; height:560px; width:880px;">
    <form action="" method="post" id="captchaForm">
        <input name="id" value="<#if captchaRuleVO?if_exists>${captchaRuleVO.id}</#if>" type="hidden"/>
        <table class="p_table form-inline" align="center">
            <tr>
                <td>
                    业务类型选择<span style="color:red;">[*]</span>
                </td>
                <td>
                    <#if captchaRuleVO?if_exists>
                        <select name="businessCode" disabled id="businessCode" style="width: 125px" >
                            <option value="${captchaRuleVO.businessCode}" selected>${captchaRuleVO.businessCode}</option>
                        </select>
                    <#else>
                        <select name="businessCode" onchange="showCanSelectLevel(this.value)" id="businessCode" style="width: 125px">
                            <option value="" selected>请选择业务类型</option>
                            <#if businessTypes?if_exists>
                                <#list businessTypes as businessType>
                                    <option id="${businessType.code}" name="${businessType.canSetLevel}" value="${businessType.code}" <#if captchaRuleVO?if_exists && captchaRuleVO.businessCode="${businessType.code}">selected</#if>>${businessType.code}</option>
                                </#list>
                             </#if>
                        </select>
                    </#if>
                </td>
            </tr>
            <tr>
                <td>
                    安全级别选择<span style="color:red;">[*]</span>
                </td>
                <td>
                    <select name="secureLevel" <#if captchaRuleVO?if_exists>disabled="disabled"</#if> id="secureLevel" style="width: 125px">
                        <#if captchaRuleVO?if_exists>
                            <option value="${captchaRuleVO.secureLevel}" selected>
                            <#if captchaRuleVO.secureLevel=='primary'>
                                初级
                                <#elseif captchaRuleVO.secureLevel=='middle'>
                                中级
                                <#else >
                                高级
                            </#if>
                            </option>
                        </#if>
                    </select>
                </td>
            </tr>
            <tr>
                <td>验证码宽度<span style="color:red;">[*]</span></td>
                <td>
                    <input type="text" name="imgWidth" id="imgWidth" value="<#if captchaRuleVO?if_exists>${captchaRuleVO.imgWidth}</#if>" style="width: 110px"/>
                </td>
            </tr>
            <tr>
                <td>验证码高度<span style="color:red;">[*]</span></td>
                <td>
                    <input type="text" name="imgHeight" id="imgHeight" value="<#if captchaRuleVO?if_exists>${captchaRuleVO.imgHeight}</#if>" style="width: 110px"/>
                </td>
            </tr>
            <tr>
                <td>验证码类型<span style="color:red;">[*]</span></td>
                <td>
                    <input type="radio" name="charType" value="0" <#if !captchaRuleVO?if_exists || captchaRuleVO.charType==0>checked</#if> onclick="showSomeInput()"/>字符型
                    <input type="radio" name="charType" value="1" <#if captchaRuleVO?if_exists && captchaRuleVO.charType==1>checked</#if> onclick="disableSomeInput()"/>运算型
                    &nbsp;&nbsp;&nbsp;&nbsp;<font color="#778899">*兼容旧版本验证码,运算型验证码样式固定*</font>
                </td>
            </tr>
            <tr>
                <td>字符个数<span style="color:red;">[*]</span></td>
                <td>
                    中文<input type="text" style="width: 20px" name="chineseCharLength" value="<#if captchaRuleVO?if_exists>${captchaRuleVO.charLengths[0]}</#if>" <#if captchaRuleVO?if_exists && captchaRuleVO.charType==1>disabled="true"</#if>' onchange="hiddenFont()"/>
                    英文<input type="text" style="width: 20px" name="englishCharLength" value="<#if captchaRuleVO?if_exists>${captchaRuleVO.charLengths[1]}</#if>" <#if captchaRuleVO?if_exists && captchaRuleVO.charType==1>disabled="true"</#if>'/>
                    数字<input type="text" style="width: 20px" name="numbersCharLength" value="<#if captchaRuleVO?if_exists>${captchaRuleVO.charLengths[2]}</#if>"/>
                </td>
            </tr>
            <tr>
                <td>字体<span style="color:red;">[*]</span></td>
                <td>
                    <#if !captchaRuleVO?if_exists>
                    <span id="fontDiv">
                        字体
                        <select style="width: 120px" name="fontName" onclick="hiddenEngFont(this)" id="fontName">
                            <option value="宋体">宋体</option>
                            <option value="黑体">黑体</option>
                            <option value="隶书">隶书</option>
                            <option value="楷体">楷体</option>
                            <option value="幼圆">幼圆</option>
                            <option value="华文彩云">华文彩云</option>
                            <option value="华文行楷">华文行楷</option>
                            <option value="方正舒体">方正舒体</option>
                            <option value="幼圆">幼圆</option>
                            <option value="微软雅黑">微软雅黑</option>
                            <option value="Arial" name="englishFont">Arial</option>
                            <option value="Courier New" name="englishFont">Courier New</option>
                            <option value="Impact" name="englishFont">Impact</option>
                            <option value="times new roman" name="englishFont">times new roman</option>
                            <option value="Times" name="englishFont">Times</option>
                            <option value="Zapfino" name="englishFont">Zapfino</option>
                            <option value="Comic Sans" name="englishFont">Comic Sans</option>
                            <option value="Castellar" name="englishFont">Castellar</option>
                            <option value="Helvetica" name="englishFont">Helvetica</option>
                            <option value="Trebuchet" name="englishFont">Trebuchet</option>
                            <option value="Monotype Corsiva" name="englishFont">Monotype Corsiva</option>
                            <option value="Algerian" name="englishFont">Algerian</option>
                            <option value="Vladimir Script" name="englishFont">Vladimir Script</option>
                        </select>
                        类型
                        <select id="fontStyle" style="width: 100px" name="fontStyle">
                            <option value="0">普通</option>
                            <option value="1">粗体</option>
                            <option value="2">斜体</option>
                        </select>
                        大小
                        <input type="text" id="fontSize" style="width: 30px;height: 15px" name="fontSize" value=""/>
                    </span>
                    <input class="btn btn-small w3" type="button" value="+添加" onclick="addFont()"/>
                    <#else>
                        <#list captchaRuleVO.fonts as font >
                            <div id='fontStyle${font_index}'>
                                <span id="fontDiv">
                                    字体
                                    <select id="fontName" style="width: 120px" name="fontName" onclick="hiddenEngFont(this)">
                                        <option value="宋体" <#if font.name=='宋体'>selected</#if>>宋体</option>
                                        <option value="黑体" <#if font.name=='黑体'>selected</#if>>黑体</option>
                                        <option value="隶书" <#if font.name=='隶书'>selected</#if>>隶书</option>
                                        <option value="楷体" <#if font.name=='楷体'>selected</#if>>楷体</option>
                                        <option value="幼圆" <#if font.name=='幼圆'>selected</#if>>幼圆</option>
                                        <option value="华文彩云" <#if font.name=='华文彩云'>selected</#if>>华文彩云</option>
                                        <option value="华文行楷" <#if font.name=='华文行楷'>selected</#if>>华文行楷</option>
                                        <option value="方正舒体" <#if font.name=='方正舒体'>selected</#if>>方正舒体</option>
                                        <option value="幼圆" <#if font.name=='幼圆'>selected</#if>>幼圆</option>
                                        <option value="微软雅黑" <#if font.name=='微软雅黑'>selected</#if>>微软雅黑</option>
                                        <option value="Arial" name="englishFont" <#if font.name=='Arial'>selected</#if>>Arial</option>
                                        <option value="Courier New" name="englishFont" <#if font.name=='Courier New'>selected</#if>>Courier New</option>
                                        <option value="Impact" name="englishFont" <#if font.name=='Impact'>selected</#if>>Impact</option>
                                        <option value="times new roman" name="englishFont" <#if font.name=='times new roman'>selected</#if>>times new roman</option>
                                        <option value="Times" name="englishFont" <#if font.name=='Times'>selected</#if>>Times</option>
                                        <option value="Zapfino" name="englishFont" <#if font.name=='Zapfino'>selected</#if>>Zapfino</option>
                                        <option value="Comic Sans" name="englishFont" <#if font.name=='Comic Sans'>selected</#if>>Comic Sans</option>
                                        <option value="Castellar" name="englishFont" <#if font.name=='Castellar'>selected</#if>>Castellar</option>
                                        <option value="Helvetica" name="englishFont" <#if font.name=='Helvetica'>selected</#if>>Helvetica</option>
                                        <option value="Trebuchet" name="englishFont" <#if font.name=='Trebuchet'>selected</#if>>Trebuchet</option>
                                        <option value="Monotype Corsiva" name="englishFont" <#if font.name=='Monotype Corsiva'>selected</#if>>Monotype Corsiva</option>
                                        <option value="Algerian" name="englishFont" <#if font.name=='Algerian'>selected</#if>>Algerian</option>
                                        <option value="Vladimir Script" name="englishFont" <#if font.name=='Vladimir Script'>selected</#if>>Vladimir Script</option>
                                    </select>
                                    类型
                                    <select id="fontStyle" style="width: 100px" name="fontStyle">
                                        <option value="0" <#if font.style==0>selected</#if>>普通</option>
                                        <option value="1" <#if font.style==1>selected</#if>>粗体</option>
                                        <option value="2" <#if font.style==2>selected</#if>>斜体</option>
                                    </select>
                                    大小
                                    <input type="text" id="fontSize" style="width: 30px;height: 15px" name="fontSize" value="${font.size}"/>
                                </span>
                                <#if font_index==0>
                                    <input class="btn btn-small w3" type="button" value="+添加" onclick="addFont()"/>
                                    <#else >
                                    <input type='button' value='删除' class='btn btn-small w3' onclick='removeFontStyle(${font_index})'/>
                                </#if>
                            </div>
                        </#list>
                    </#if>
                    <div id="addFontDiv">
                    </div>
                </td>
            </tr>
            <tr>
                <td>背景渐变色<span style="color:red;">[*]</span></td>
                <td>
                    颜色&nbsp;<input type="text" class="form-control demo" value="<#if captchaRuleVO?if_exists>${captchaRuleVO.bgColors[0]}<#else >#ffffff</#if>" name="bgColor">
                    <input type="text" class="form-control demo" value="<#if captchaRuleVO?if_exists>${captchaRuleVO.bgColors[1]}<#else >#ffffff</#if>" name="bgColor">
                </td>
            </tr>
            <tr>
                <td>字符颜色<span style="color:red;">[*]</span></td>
                <td>
                    <#if !captchaRuleVO?if_exists>
                        颜色&nbsp;<input type="text" class="form-control demo" value="#ff0066" name="charColor">
                        <input class="btn btn-small w3" type="button" value="+添加" onclick="addFontColor()"/>
                        <di id="addFontColortDiv">
                        </di>
                    <#else>
                    <#list captchaRuleVO.charColors as charColor>
                        <div id='FontColorStyle${charColor_index}'>
                        颜色&nbsp;<input type="text" class="form-control demo" value="${charColor}" name="charColor">
                        <#if charColor_index==0>
                        <input class="btn btn-small w3" type="button" value="+添加" onclick="addFontColor()"/>
                        <#else >
                            <input type='button' value='删除' class='btn btn-small w3' onclick='removeFontColorStyle(${charColor_index})'/>
                        </#if>
                        </div>
                    </#list>
                    <div id="addFontColortDiv">
                    </div>
                    </#if>

                </td>
            </tr>
            <tr>
                <td>干扰线</td>
                <td>
                    <#if !captchaRuleVO?if_exists>
                    <div style="width:50%;float:left;">
                        <div id="addNoisetDiv">

                        </div>
                    </div>
                    <div style="width:50%;float:left;">
                        <input class="btn btn-small w3" type="button" value="+添加" onclick="addnoise()"/>
                    </div>
                        <#else >
                        <div style="width:50%;float:left;">
                            <#list captchaRuleVO.noises as noise>
                                    <div id='noiseStyle${noise_index}'>
                                    颜色&nbsp;<input type='text' class='form-control demo' value='${noise.noiseStrColor}' name='noiseColor'>
                                    &nbsp;宽度<input type='text' style='width: 20px' name='noiseWidth' value="${noise.noiseWidth}"/>
                                    <input type='button' value='删除' class='btn btn-small w3' onclick='removeNoiseStyle(${noise_index});'/>
                                    </div>
                            </#list>
                            <div id="addNoisetDiv">
                            </div>
                        </div>
                        <div style="width:50%;float:left;">
                            <input class="btn btn-small w3" type="button" value="+添加" onclick="addnoise()"/>
                        </div>
                    </#if>
                </td>
            </tr>
            <tr>
                <td>粘连百分比</td>
                <td>
                    <input type="text" style="width: 20px" name="overlapPercent" value="<#if captchaRuleVO?if_exists>${captchaRuleVO.overlapPercent}</#if>" id="overlapPercent"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;<font color="#778899">*粘连百分比不选默认为0,无粘连*</font>
                </td>
            </tr>
            <tr>
                <td>背景类型</td>
                <td>
                    <label>阴影<input type="checkbox" value="1" name="shadow" <#if captchaRuleVO?if_exists && captchaRuleVO.shadow==1>checked</#if>/></label>
                    <label>伸展<input type="checkbox" value="1" name="stretch" <#if captchaRuleVO?if_exists && captchaRuleVO.stretch==1>checked</#if>/></label>
                    <label>网格<input type="checkbox" value="1" name="fishEye" <#if captchaRuleVO?if_exists && captchaRuleVO.fishEye==1>checked</#if>/></label>
                    <label>波浪<input type="checkbox" value="1" name="ripple" <#if captchaRuleVO?if_exists && captchaRuleVO.ripple==1>checked</#if>/></label>
                </td>
            </tr>
            <tr>
                <td>字符是否旋转</td>
                <td>
                    <input type="radio" name="rotate" value="1" <#if !captchaRuleVO?if_exists || captchaRuleVO.rotate!=0>checked</#if>/>是
                    <input type="radio" name="rotate" value="0" <#if captchaRuleVO?if_exists && captchaRuleVO.rotate==0>checked</#if>/>否
                </td>
            </tr>
            <tr>
                <td>过期时间<span style="color:red;">[*]</span></td>
                <td>
                    <input type="text" name="expireTime" value="<#if captchaRuleVO?if_exists>${captchaRuleVO.expireTime}<#else >60</#if>" style="width: 125px"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;<font color="#778899">*过期时间以秒为单位*</font>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
    <div>
        <br>
        <div align="center">
            <img id="captchaImg" alt="点击预览" src="" onclick="previewCaptcha()">
        </div>
        <br>
        <div align="center">
            <input class="btn btn-small w3" value="保存" type="button" onclick="saveCaptchaRule(<#if captchaRuleVO?if_exists>${captchaRuleVO.id}</#if>)">
        </div>
    </div>
</div>
<script src="/captcha-back/js/captcha/addCaptcha.js"></script>
</body>
</html>