<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>业务编码维护</title>
</head>
<body align="center">
    <br><br>
    <div style="margin-top:82px;text-align: center;">
    <form id="queryForm">
        <div >
            <div >
                业务编码:<span style="color:red;">[*]</span>&nbsp;&nbsp;&nbsp;
                <#if businessType?if_exists>
                    <input id="code" name="businessType.code" type="text" value="${businessType.code}" readonly="true"/>
                <#else>
                    <input id="code" name="businessType.code" type="text"/>
                </#if>
            </div>
            <br>
            <div >
                业务描述:<span style="color:red;">[*]</span>&nbsp;&nbsp;&nbsp;
            <#if businessType?if_exists>
                <input id="codeDesc" name="businessType.codeDesc"  type="text"  value="${businessType.codeDesc}" >
            <#else>
                <input id="codeDesc" name="businessType.codeDesc"  type="text"   >
            </#if>
            </div>
            <br>
            <div >
                安全级别:<span style="color:red;">[*]</span>&nbsp;&nbsp;&nbsp;
            <#if businessType?if_exists>
                <#if businessType.canSetLevel?if_exists && businessType.canSetLevel=="primary">
                    <input id="canSetLevel1" name="businessType.canSetLevel" type="checkbox" value="primary" checked="checked" onclick="change()">初级&nbsp;&nbsp;
                    <input id="canSetLevel2" name="businessType.canSetLevel" type="checkbox" value="middle" onclick="change()">中级&nbsp;&nbsp;
                    <input id="canSetLevel3" name="businessType.canSetLevel" type="checkbox" value="senior" onclick="change()">高级
                <#elseif businessType.canSetLevel?if_exists && businessType.canSetLevel=="middle">
                    <input id="canSetLevel1" name="businessType.canSetLevel" type="checkbox" value="primary" onclick="change()">初级&nbsp;&nbsp;
                    <input id="canSetLevel2" name="businessType.canSetLevel" type="checkbox" value="middle" checked="checked" onclick="change()">中级&nbsp;&nbsp;
                    <input id="canSetLevel3" name="businessType.canSetLevel" type="checkbox" value="senior" onclick="change()">高级
                <#elseif businessType.canSetLevel?if_exists && businessType.canSetLevel=="senior">
                    <input id="canSetLevel1" name="businessType.canSetLevel" type="checkbox" value="primary" onclick="change()">初级&nbsp;&nbsp;
                    <input id="canSetLevel2" name="businessType.canSetLevel" type="checkbox" value="middle" onclick="change()">中级&nbsp;&nbsp;
                    <input id="canSetLevel3" name="businessType.canSetLevel" type="checkbox" value="senior" checked="checked" onclick="change()">高级
                <#elseif businessType.canSetLevel?if_exists && businessType.canSetLevel=="primary,middle">
                    <input id="canSetLevel1" name="businessType.canSetLevel" type="checkbox" value="primary" checked="checked" onclick="change()">初级&nbsp;&nbsp;
                    <input id="canSetLevel2" name="businessType.canSetLevel" type="checkbox" value="middle" checked="checked" onclick="change()">中级&nbsp;&nbsp;
                    <input id="canSetLevel3" name="businessType.canSetLevel" type="checkbox" value="senior" onclick="change()">高级
                <#elseif businessType.canSetLevel?if_exists && businessType.canSetLevel=="primary,senior">
                    <input id="canSetLevel1" name="businessType.canSetLevel" type="checkbox" value="primary" checked="checked" onclick="change()">初级&nbsp;&nbsp;
                    <input id="canSetLevel2" name="businessType.canSetLevel" type="checkbox" value="middle" onclick="change()">中级&nbsp;&nbsp;
                    <input id="canSetLevel3" name="businessType.canSetLevel" type="checkbox" value="senior" checked="checked" onclick="change()">高级
                <#elseif businessType.canSetLevel?if_exists && businessType.canSetLevel=="middle,senior">
                    <input id="canSetLevel1" name="businessType.canSetLevel" type="checkbox" value="primary" onclick="change()">初级&nbsp;&nbsp;
                    <input id="canSetLevel2" name="businessType.canSetLevel" type="checkbox" value="middle" checked="checked" onclick="change()">中级&nbsp;&nbsp;
                    <input id="canSetLevel3" name="businessType.canSetLevel" type="checkbox" value="senior" checked="checked" onclick="change()">高级
                <#elseif businessType.canSetLevel?if_exists && businessType.canSetLevel=="primary,middle,senior">
                    <input id="canSetLevel1" name="businessType.canSetLevel" type="checkbox" value="primary" checked="checked" onclick="change()">初级&nbsp;&nbsp;
                    <input id="canSetLevel2" name="businessType.canSetLevel" type="checkbox" value="middle" checked="checked" onclick="change()">中级&nbsp;&nbsp;
                    <input id="canSetLevel3" name="businessType.canSetLevel" type="checkbox" value="senior" checked="checked" onclick="change()">高级
                </#if>
            <#else>
                <input id="canSetLevel1" name="businessType.canSetLevel" type="checkbox" value="primary" onclick="change()">初级&nbsp;&nbsp;
                <input id="canSetLevel2" name="businessType.canSetLevel" type="checkbox" value="middle" onclick="change()">中级&nbsp;&nbsp;
                <input id="canSetLevel3" name="businessType.canSetLevel" type="checkbox" value="senior" onclick="change()">高级
            </#if>
            </div>
            <br>
        <#--<div >
            是否用风控:<span style="color:red;">[*]</span>&nbsp;&nbsp;
            <input id="riskControl1" name="businessType.riskControl" type="radio" value="0" <#if businessType?if_exists && businessType.riskControl=0>checked="checked"</#if>>否&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input id="riskControl2" name="businessType.riskControl" type="radio" value="1" <#if businessType?if_exists && businessType.riskControl=1>checked="checked"</#if> >是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </div>-->
            <br>
        <#--<div id="applicationEnvetId" <#if businessType?if_exists && businessType.riskControl=0>style="display:none"</#if> >
            风控策略:<span style="color:red;">[*]</span>&nbsp;&nbsp;&nbsp;
            <select id="applicationEnvetId" class="w18" name="businessType.applicationEnvetId">

            </select>
            <br>
        </div>-->
            <div id ="br" <#if businessType?if_exists && businessType.riskControl=0>style="display:none"</#if>><br></div>
            <div>
                默认级别:<span style="color:red;">[*]</span>&nbsp;&nbsp;&nbsp;
            <select id="defaultLevel" class="w18" name="businessType.defaultLevel">
                <#if businessType?if_exists>
                    <#if businessType.canSetLevel?if_exists && businessType.canSetLevel=="primary">
                        <option value="primary" <#if businessType?if_exists && businessType.defaultLevel="primary">selected</#if> >初级</option>
                    <#elseif businessType.canSetLevel?if_exists && businessType.canSetLevel=="middle">
                        <option value="middle" <#if businessType?if_exists && businessType.defaultLevel="middle">selected</#if>>中级</option>
                    <#elseif businessType.canSetLevel?if_exists && businessType.canSetLevel=="senior">
                        <option value="senior" <#if businessType?if_exists && businessType.defaultLevel="senior">selected</#if>>高级</option>
                    <#elseif businessType.canSetLevel?if_exists && businessType.canSetLevel=="primary,middle">
                        <option value="primary" <#if businessType?if_exists && businessType.defaultLevel="primary">selected</#if> >初级</option>
                        <option value="middle" <#if businessType?if_exists && businessType.defaultLevel="middle">selected</#if>>中级</option>
                    <#elseif businessType.canSetLevel?if_exists && businessType.canSetLevel=="primary,senior">
                        <option value="primary" <#if businessType?if_exists && businessType.defaultLevel="primary">selected</#if> >初级</option>
                        <option value="senior" <#if businessType?if_exists && businessType.defaultLevel="senior">selected</#if>>高级</option>()">高级
                    <#elseif businessType.canSetLevel?if_exists && businessType.canSetLevel=="middle,senior">
                        <option value="middle" <#if businessType?if_exists && businessType.defaultLevel="middle">selected</#if>>中级</option>
                        <option value="senior" <#if businessType?if_exists && businessType.defaultLevel="senior">selected</#if>>高级</option>
                    <#elseif businessType.canSetLevel?if_exists && businessType.canSetLevel=="primary,middle,senior">
                        <option value="primary" <#if businessType?if_exists && businessType.defaultLevel="primary">selected</#if> >初级</option>
                        <option value="middle" <#if businessType?if_exists && businessType.defaultLevel="middle">selected</#if>>中级</option>
                        <option value="senior" <#if businessType?if_exists && businessType.defaultLevel="senior">selected</#if>>高级</option>
                    </#if>
                <#else>
                    <option value="primary" <#if businessType?if_exists && businessType.defaultLevel="primary">selected</#if> >初级</option>
                    <option value="middle" <#if businessType?if_exists && businessType.defaultLevel="middle">selected</#if>>中级</option>
                    <option value="senior" <#if businessType?if_exists && businessType.defaultLevel="senior">selected</#if>>高级</option>
                </#if>
            </select>
            </div>
            <br><br>
            <div>
                <#if businessType?if_exists>
                    <a href="javascript:void(0);" class="btn btn_cc1" id="export_button" onclick="save(${businessType.id})">保存</a>
                <#else>
                    <a href="javascript:void(0);" class="btn btn_cc1" id="export_button" onclick="save(-1)">保存</a>
                </#if>
            </div>
        </div>
    </form>
    </div>

    <script>
        $(document).ready(function(){
            $("#riskControl2").click(function(){
                /*var applicationEnvetId = document.getElementById("applicationEnvetId");
                applicationEnvetId.setAttribute("style","display:block");*/
                var br = document.getElementById("br");
                br.setAttribute("style","display:block")
            });
            $("#riskControl1").click(function(){
                /* var applicationEnvetId = document.getElementById("applicationEnvetId");
                 applicationEnvetId.setAttribute("style","display:none");*/
                var br = document.getElementById("br");
                br.setAttribute("style","display:none")
            });

        });

        function save(id){
            var code = $("#code").val();
            var codeDesc = $("#codeDesc").val();
            var canSetLevel="";
            $("input:checkbox[name='businessType.canSetLevel']:checked").each(function(){
                canSetLevel += $(this).val() + ",";
            });
            /*var riskControl=$("input[type='radio']:checked").val();*/
            var defaultLevel = $("#defaultLevel").val();
            if (code == "") {
                alert("业务编码不能为空！");
                return false;
            }
            if(code.replace(/[\w]+/g,"")!=""){
                alert("业务编码输入非法！应该填写数字,字母或下划线");
                return false;
            }
            if (codeDesc == "") {
                alert("业务描述不能为空！");
                return false;
            }
            if(typeof(canSetLevel)=="undefined" || canSetLevel == ""){
                alert("请选择安全级别！");
                return false;
            }
            /*if(typeof(riskControl)=="undefined"){
                alert("请选择是否用风控！");
                return false;
            }*/
            /*if(typeof(riskControl)=="1"){
                var applicationEnvetId = $("#applicationEnvetId").val();
                if(applicationEnvetId==""){
                    alert("请选择风控策略！");
                }
            }*/
            if (defaultLevel == "") {
                alert("请选择默认级别！");
                return false;
            }
            $.confirm("是否保存!", function () {
                var url="/captcha-back/businessTypeController/save.do";
                $.ajax({
                    url:url,
                    data: {
                        "id": id,
                        "code": code,
                        "codeDesc": codeDesc,
                        "canSetLevel": canSetLevel, /*"riskControl":riskControl,*/
                        "defaultLevel": defaultLevel/*,
                        "applicationEnvetId": applicationEnvetId*/
                    },
                    dataType:"json",
                    type:"POST",
                    success:function(result) {
                        if(result.success==false){
                            if(result.message=="repeat"){
                                alert("业务编码重复！");
                            }else if(result.message=="exception"){
                                alert("保存失败！");
                            }
                            return false;
                        }else{
                            window.location.reload();
                        }
                    }
                });
            });
        }

        function change(){
            var canSetLevels = "";
            $("input:checkbox[name='businessType.canSetLevel']:checked").each(function(){
                canSetLevels += $(this).val() + ",";
            });
            var canSetLevel = canSetLevels.split(",");
            $("#defaultLevel").empty();
            for(var i=0;i<canSetLevel.length;i++){
                if (canSetLevel[i] == "primary") {
                    document.getElementById('defaultLevel').options.add(new Option('初级', "primary"));
                } else if (canSetLevel[i] == "middle") {
                    document.getElementById('defaultLevel').options.add(new Option('中级', "middle"));
                } else if (canSetLevel[i] == "senior") {
                    document.getElementById('defaultLevel').options.add(new Option('高级', "senior"));
                }
            }
        }

    </script>
</body>