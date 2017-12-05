<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>业务编码列表</title>
</head>
<body align="center">
    <#include "pages/base/head_meta.ftl"/>
    <div style="margin-top:82px;text-align: center;">


        <div class="iframe_content">
            <div class="p_box">
                <form method="post" action='/captcha-back/businessTypeController/index.do' id="queryForm">
                    <table class="s_table">
                        <tbody>
                        <tr >
                            <td class="s_label" width="300px">业务类型：</td>
                            <td class="w18" width="300px">
                                <select id="code" class="w18" onChange="updateCanSetLevel(this.value)" name="code">
                                    <option value="" <#if !code?if_exists>selected</#if>>全部</option>
                                <#if list?exists>
                                    <#list  list as businessType>
                                        <option id="${businessType.code}" name="${businessType.canSetLevel}" value="${businessType.code}" <#if code='${businessType.code}'>selected</#if>>${businessType.codeDesc}</option>
                                    </#list>
                                </#if>
                                </select>
                            </td>
                            <td class="s_label" width="300px">安全级别：</td>
                            <td class="w18" width="300px">
                                <select class="w18" name="canSetLevel" id="canSetLevelQuery">
                                    <#if !code?if_exists>
                                        <option value="" >全部</option>
                                        <option value="primary" <#if primary?if_exists>selected</#if>>初级</option>
                                        <option value="middle" <#if middle?if_exists>selected</#if>>中级</option>
                                        <option value="senior"<#if senior?if_exists>selected</#if>>高级</option>
                                    <#else >
                                        <#if primary?if_exists>
                                            <option value="<#if canSetLevel?if_exists>${canSetLevel}</#if>" >初级</option>
                                        </#if>
                                        <#if middle?if_exists>
                                            <option value="<#if canSetLevel?if_exists>${canSetLevel}</#if>" >中级</option>
                                        </#if>
                                        <#if senior?if_exists>
                                            <option value="<#if canSetLevel?if_exists>${canSetLevel}</#if>">高级</option>
                                        </#if>
                                    </#if>
                                </select>
                            </td>
                            <td width="600px">
                                <button class="btn btn_cc1" type="submit">查询</button>
                                <button class="btn btn_cc1" type="button" id="createNewGift" onclick="businessTypeIndex('no')">新建</button>
                            </td>
                        </tr>
                        </tbody>
                    </table>

                </form>
            </div>


            <div class="p_box">
                <table  class="p_table table_center">
                    <tr>
                        <th align="middle" width="600px">业务类型</th>
                        <th align="middle" width="600px">安全级别</th>
                        <th>操作</th>
                    </tr>
                    <!-- 表格的要填充的内容开始-->
                <#if pageContent?exists>
                    <#list pageContent.items as businessType>
                        <tr>
                            <td align="middle" height="50" id="${businessType.code}">${businessType.codeDesc}</td>
                            <#if businessType.canSetLevel?if_exists>
                                <#if businessType.canSetLevel='primary'>
                                    <td id="canSetLevel">初级</td>
                                <#elseif businessType.canSetLevel='middle'>
                                    <td id="canSetLevel">中级</td>
                                <#elseif businessType.canSetLevel='senior'>
                                    <td id="canSetLevel">高级</td>
                                <#elseif businessType.canSetLevel='primary,middle'>
                                    <td id="canSetLevel">初级&nbsp;中级</td>
                                <#elseif businessType.canSetLevel='primary,senior'>
                                    <td id="canSetLevel">初级&nbsp;高级</td>
                                <#elseif businessType.canSetLevel='middle,senior'>
                                    <td id="canSetLevel">中级&nbsp;高级</td>
                                <#elseif businessType.canSetLevel='primary,middle,senior' >
                                    <td id="canSetLevel">初级&nbsp;中级&nbsp;高级</td>
                                <#else>
                                    <td></td>
                                </#if>
                            <#else >
                                <td></td>
                            </#if>
                            <td>
                                <button  type="button" onclick="businessTypeIndex('${businessType.id}')">&nbsp;&nbsp;修改&nbsp;&nbsp;</button>&nbsp;&nbsp;
                                <input type="button" class="button" value=" &nbsp;&nbsp;删除&nbsp;&nbsp; " id="delete" onclick="deleteBusinessType('${businessType.id}','${businessType.code}');"/><br/>
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
                var a = document.getElementById(code);
                var canSetLevel = a.getAttribute("name"); //获取值
                var canSetLevels = new Array();
                canSetLevels = canSetLevel.split(",");
                for (i = 0; i < canSetLevels.length; i++) {
                    if (canSetLevels[i] == "primary") {
                        document.getElementById('canSetLevelQuery').options.add(new Option('初级', canSetLevel));
                    } else if (canSetLevels[i] == "middle") {
                        document.getElementById('canSetLevelQuery').options.add(new Option('中级', canSetLevel));
                    } else if (canSetLevels[i] == "senior") {
                        document.getElementById('canSetLevelQuery').options.add(new Option('高级', canSetLevel));
                    }
                }
            }
        }

        function businessTypeIndex(id){
            var url="/captcha-back/businessTypeController/businessTypeIndex.do?";
            //新建业务编码页面
            if (id == "no") {
                addDialog = new xDialog(url, {}, {
                    title: "新建业务编码",
                    width: 980,
                    height: 650
                });
            } else {
                //修改业务编码页面
                addDialog = new xDialog(url, {"id":id}, {
                    title: "修改业务编码",
                    width: 980,
                    height: 650
                });
            }
        }

        //删除
        function deleteBusinessType(id,code){
            msg="你确定删除该业务编码?";
            var url = "/captcha-back/businessTypeController/delete.do";
            $.confirm(msg,function(){
                $.ajax({
                    url: url,
                    data: {"id":id,"code":code},
                    dataType:"json",
                    type:"POST",
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