<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>验证码规则列表</title>
    <#include "/pages/base/foot.ftl"/><!--引用的公共js -->
    <#include "/pages/base/head_meta.ftl"/><!--引用的公共css -->
    <!-- MiniColors -->
    <script src="/captcha-back/js/captcha/jquery.minicolors.js"></script>
    <link rel="stylesheet" href="/captcha-back/css/captcha/jquery.minicolors.css">
</head>
<body>
<br><br>
<div align="center">
    <div class="p_box">
        <!--默认级别隐藏域-->
        <#list businessTypes as biz>
            <input id="${biz.code}" type="hidden" value="${biz.defaultLevel}"/>
        </#list>
        <!--预览前缀-->
        <input id="prefixURL" type="hidden" value="${prefixURL}"/>
        <form method="post" action='/captcha-back/captchaRuleController/captchaRuleList.do' id="queryForm">
            <table class="p_table table_center">
                <tbody>
                    <tr>
                        <td>
                            业务描述
                            <select name="businessCode">
                                <option value="">不限</option>
                                <#if businessTypes?if_exists>
                                    <#list businessTypes as businessType>
                                        <option value="${businessType.code}"<#if businessCode==businessType.code>selected</#if> >${businessType.codeDesc}</option>
                                    </#list>
                                </#if>
                            </select>
                        </td>
                        <td>
                            安全级别
                            <select name="secureLevel">
                                <option value="">不限</option>
                                <option value="primary" <#if secureLevel='primary'>selected</#if>>初级</option>
                                <option value="middle" <#if secureLevel='middle'>selected</#if>>中级</option>
                                <option value="senior" <#if secureLevel='senior'>selected</#if>>高级</option>
                            </select>
                        </td>
                        <td><input class="btn btn-small w3" type="submit" value="查询"/></td>
                        <td><input class="btn btn-small w3" type="button" value="新建" onclick="editCaptchaRule()"/></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </div>

    <!--显示内容表格开始 -->
    <div class="p_box">
        <table class="p_table table_center">
            <!-- 表格的表头开始-->
            <tr>
                <th>业务类型</th>
                <th>安全级别</th>
                <th>操作</th>
            </tr>
            <!-- 表格的表头结束-->
            <#if pageContent?if_exists>
                <#list pageContent.items as captchaRule>
                    <tr>
                        <td>${captchaRule.businessCode}</td>
                        <td>
                            <#if captchaRule.secureLevel=='primary'>
                                初级
                            <#elseif captchaRule.secureLevel=='middle'>
                                中级
                            <#else >
                                高级
                            </#if>
                        </td>
                        <td>
                            <input class="btn btn-small w3" type="button" value="修改" onclick='editCaptchaRule(${captchaRule.id})'/>
                            <input class="btn btn-small w3" type="button" value="删除" onclick="deleteCaptchaRule('${captchaRule.id}','${captchaRule.businessCode}','${captchaRule.secureLevel}')"/>
                        </td>
                    </tr>
                </#list>
            </#if>
        </table>
        <!--分页开始 -->
        <div align="center">
            <#if pageContent.items?exists>
                <div class="paging">
                ${pageContent.getPagination()}
                </div>
            </#if>
        </div>
        <!--分页结束 -->
    </div>
    <script src="/captcha-back/js/captcha/addCaptcha.js"></script>
    <script type="text/javascript">

        function editCaptchaRule(captchaRuleId) {
            var url = "/captcha-back/captchaRuleController/editCaptchaRule.do";
            if(typeof(captchaRuleId)=="undefined"){
                //新建验证码规则
                addDialog = new xDialog(url,{},{
                    title:"新建验证码规则",
                    width:900,
                    height: 650
                });
            }else {
                //编辑验证码规则
                addDialog = new xDialog(url,{"captchaRuleId":captchaRuleId},{
                    title:"修改验证码规则",
                    width:900,
                    height: 650
                });
            }
        }
        //删除验证码规则
        function deleteCaptchaRule(captchaRuleId,businessCode,secureLevel) {
            var defaultLevel = $("#"+businessCode).val();
            msg="你确定要删除该验证码规则???";
            if (defaultLevel == secureLevel){
                alert("默认级别的验证码规则不可删除,可修改默认级别再删除");
                return;
            }
            $.confirm(msg,function () {
                $.ajax({
                    url:"/captcha-back/captchaRuleController/deleteCaptchaRule.do",
                    type:"post",
                    data:{"captchaRuleId":captchaRuleId,"businessCode":businessCode,"secureLevel":secureLevel},
                    dataType:"json",
                    success:function (json) {
                        alert(json.message);
                        if (json.success){
                            window.location.reload();
                        }
                    }
                });
            })
        }
    </script>
</body>