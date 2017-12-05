<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>风控分列表</title>
</head>
<body align="center">
    <#include "pages/base/head_meta.ftl"/>
    <div style="margin-top:82px;text-align: center;">
        <div class="iframe_content">
            <div class="p_box">
                <form method="post" action='/captcha-back/riskscoreSecurelevelController/index.do' id="queryFormIndex">
                    <table class="s_table">
                        <tbody>
                        <tr >
                            <td class="s_label" width="300px">业务类型：</td>
                            <td class="w18" width="300px">
                                <select id="code" class="w18" onChange="updateCanSetLevel(this.value)" name="businessCode">
                                    <option value="" <#if !businessCode?if_exists>selected</#if>>全部</option>
                                <#if list?exists>
                                    <#list  list as businessType>
                                        <option id="${businessType.code},Index" name="${businessType.canSetLevel}" value="${businessType.code}" <#if businessCode='${businessType.code}'>selected</#if>>${businessType.codeDesc}</option>
                                    </#list>
                                </#if>
                                </select>
                            </td>
                            <td class="s_label" width="300px">安全级别：</td>
                            <td class="w18" width="300px">
                                <select class="w18" name="secureLevel" id="canSetLevelQuery">
                                    <option value="" >全部</option>
                                    <option value="primary" <#if primary?if_exists>selected</#if>>初级</option>
                                    <option value="middle" <#if middle?if_exists>selected</#if>>中级</option>
                                    <option value="senior"<#if senior?if_exists>selected</#if>>高级</option>
                                </select>
                            </td>
                            <td width="600px">
                                <button class="btn btn_cc1" type="submit">查询</button>
                                <button class="btn btn_cc1" type="button" id="createNewGift" onclick="index('no')">新建</button>
                            </td>
                        </tr>
                        </tbody>
                    </table>

                </form>
            </div>
            <div class="p_box">
                <table  class="p_table table_center">
                    <tr>
                        <th align="middle" width="450px">业务类型</th>
                        <th align="middle" width="450px">安全级别</th>
                        <th align="middle" width="450px">风控分</th>
                        <th>操作</th>
                    </tr>
                    <!-- 表格的要填充的内容开始-->
                <#if pageContent?exists>
                    <#list pageContent.items as riskscoreSecurelevel>
                        <tr>
                            <td align="middle" height="50" id="${riskscoreSecurelevel.businessCode}">${riskscoreSecurelevel.businessCode}</td>
                            <#if riskscoreSecurelevel.secureLevel?if_exists>
                                <#if riskscoreSecurelevel.secureLevel='primary'>
                                    <td id="canSetLevel">初级</td>
                                <#elseif riskscoreSecurelevel.secureLevel='middle'>
                                    <td id="canSetLevel">中级</td>
                                <#elseif riskscoreSecurelevel.secureLevel='senior'>
                                    <td id="canSetLevel">高级</td>
                                <#elseif riskscoreSecurelevel.secureLevel='primary,middle'>
                                    <td id="canSetLevel">初级&nbsp;中级</td>
                                <#elseif riskscoreSecurelevel.secureLevel='primary,senior'>
                                    <td id="canSetLevel">初级&nbsp;高级</td>
                                <#elseif riskscoreSecurelevel.secureLevel='middle,senior'>
                                    <td id="canSetLevel">中级&nbsp;高级</td>
                                <#elseif riskscoreSecurelevel.secureLevel='primary,middle,senior' >
                                    <td id="canSetLevel">初级&nbsp;中级&nbsp;高级</td>
                                <#else>
                                    <td></td>
                                </#if>
                            <#else >
                                <td></td>
                            </#if>
                            <td align="middle">
                                ${riskscoreSecurelevel.fromScore}&nbsp;-&nbsp;${riskscoreSecurelevel.toScore}
                            </td>
                            <td>
                                <button  type="button" onclick="index('${riskscoreSecurelevel.id}')">&nbsp;&nbsp;修改&nbsp;&nbsp;</button>&nbsp;&nbsp;
                                <input type="button" class="button" value=" &nbsp;&nbsp;删除&nbsp;&nbsp; " id="delete" onclick="deleteriskscoreSecurelevel(${riskscoreSecurelevel.id})"/><br/>
                            </td>
                        </tr>
                    </#list>
                </#if>
                    <!-- 表格的要填充的内容结束-->
                </table>
                <!--分页开始 -->
            <#if pageContent.items?exists>
                <div class="paging">
                ${pageContent.getPagination()}
                </div>
            </#if>
                <!--分页结束 -->
            </div>
        </div>
    <#include "pages/base/foot.ftl"/>

    <script>
        function updateCanSetLevel(code){
            $("#canSetLevelQuery").empty();
            if(code == ""){
                document.getElementById('canSetLevelQuery').options.add(new Option('全部', ""));
                document.getElementById('canSetLevelQuery').options.add(new Option('初级', "primary"));
                document.getElementById('canSetLevelQuery').options.add(new Option('中级', "middle"));
                document.getElementById('canSetLevelQuery').options.add(new Option('高级', "senior"));

            }else {
                var a = document.getElementById(code + ",Index");
                var canSetLevel = a.getAttribute("name"); //获取值
                var canSetLevels = new Array();
                canSetLevels = canSetLevel.split(",");
                document.getElementById('canSetLevelQuery').options.add(new Option('全部', ""));
                for (i = 0; i < canSetLevels.length; i++) {
                    if (canSetLevels[i] == "primary") {
                        document.getElementById('canSetLevelQuery').options.add(new Option('初级', "primary"));
                    } else if (canSetLevels[i] == "middle") {
                        document.getElementById('canSetLevelQuery').options.add(new Option('中级', "middle"));
                    } else if (canSetLevels[i] == "senior") {
                        document.getElementById('canSetLevelQuery').options.add(new Option('高级', "senior"));
                    }
                }
            }
        }

        function index(id){
            var url="/captcha-back/riskscoreSecurelevelController/riskscoreSecurelevelIndex.do?";
            //新建风控分页面
            if (id == "no") {
                addDialog = new xDialog(url, {}, {
                    title: "新建风控分",
                    width: 980,
                    height: 650
                });
            } else {
                //修改风控分页面
                addDialog = new xDialog(url, {"id":id}, {
                    title: "修改风控分",
                    width: 980,
                    height: 650
                });
            }
        }

        //删除
        function deleteriskscoreSecurelevel(id){
            msg="你确定删除该业务编码?";
            var url = "/captcha-back/riskscoreSecurelevelController/delete.do";
            $.confirm(msg,function(){
                $.ajax({
                    url: url,
                    data: {"id":id},
                    dataType:"json",
                    success: function(result) {
                        if (result.success) {
                            alert(result.message);
                            window.location.reload();
                        } else {
                            alert(result.message);
                        }
                    }
                });
            });
        }
    </script>
</body>