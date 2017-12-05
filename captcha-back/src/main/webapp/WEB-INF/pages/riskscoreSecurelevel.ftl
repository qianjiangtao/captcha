<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>风控分设置</title>
</head>
<body align="center">
<br><br><br><br>
<div style="margin-top:82px;text-align: center;">
    <form id="queryForm">
        <div >
            <div class="s_label" >
                业务类型选择:&nbsp;
            <#if riskscoreSecurelevelVo?if_exists>
                <input id="id" style="display:none" value="${riskscoreSecurelevelVo.id}"/>
                <select id="businessCode" class="w18"  name="businessCode">
                    <option   value="${riskscoreSecurelevelVo.businessCode}">${riskscoreSecurelevelVo.businessCodeDesc}</option>
                </select>
            <#else>
                <select id="businessCode" class="w18" onChange="updatesecureLevel(this.value)" name="businessCode">
                    <option value="" selected >请选择业务类型</option>
                    <#if businessTypeList?exists>
                        <#list businessTypeList as businessType>
                            <option id="${businessType.code}" name="${businessType.canSetLevel}" value="${businessType.code}">${businessType.codeDesc}</option>
                        </#list>
                    </#if>
                </select>
            </#if>
            </div>
            <br><br>
            <div class="s_label" >
                安全级别选择:&nbsp;
            <#if riskscoreSecurelevelVo?if_exists>
                <select id="secureLevel">
                    <#if riskscoreSecurelevelVo.secureLevel?if_exists && riskscoreSecurelevelVo.secureLevel=="primary">
                        <option id="secureLevel1" name="secureLevel"  value="primary">初级</option>
                    <#elseif riskscoreSecurelevelVo.secureLevel?if_exists && riskscoreSecurelevelVo.secureLevel=="middle">
                        <option id="secureLevel2" name="secureLevel" value="middle">中级</option>
                    <#elseif riskscoreSecurelevelVo.secureLevel?if_exists && riskscoreSecurelevelVo.secureLevel=="senior">
                        <option id="secureLevel3" name="secureLevel" value="senior">高级</option>
                    </#if>
                </select>
            <#else>
                <select class="w18" name="secureLevel" id="secureLevel">
                </select>
            </#if>
            </div>
            <br><br>
            <div >
            <#if riskscoreSecurelevelVo?if_exists>
                风控分:<span style="color:red;">[*]</span>&nbsp;&nbsp;&nbsp;
                <input id="fromScore" name="fromScore"  type="text" style="width: 55px;"  value="${riskscoreSecurelevelVo.fromScore}">&nbsp;到&nbsp;<input id="toScore" name="toScore"  type="text" style="width: 55px;" value="${riskscoreSecurelevelVo.toScore}">
            <#else>
                风控分:<span style="color:red;">[*]</span>&nbsp;&nbsp;&nbsp;
                <input id="fromScore" name="fromScore"  type="text" style="width: 55px;"  >&nbsp;到&nbsp;<input id="toScore" name="toScore"  type="text" style="width: 55px;" >
            </#if>
            </div>
            <br><br>
            <div>
                <a href="javascript:void(0);" class="btn btn_cc1" id="export_button">保存</a>
            </div>
        </div>
    </form>
</div>

<script>
    $(document).ready(function(){
        $("#export_button").click(function() {
            if (!$("#queryForm").validate().form()) {
                return false;
            }
            if (checkForm()) {
                $.confirm("是否保存!", function () {
                    var url="/captcha-back/riskscoreSecurelevelController/save.do";
                    $.ajax({
                        url:url,
                        data: {"id":$("#id").val(),"businessCode":$("#businessCode").val(),"secureLevel":$("#secureLevel").val(),"fromScore":$("#fromScore").val(),"toScore":$("#toScore").val()},
                        dataType:"json",
                        type:"POST",
                        success:function(result) {
                            if(result==false){
                                alert("保存失败！");
                                return false;
                            }else{
                                window.location.reload();
                            }
                        }
                    });
                });
            }
        });
    });

    function checkForm(){
        if($("#businessCode").val() == "") {
            alert("业务类型不能为空！");
            $("#businessCode").focus();
            return;
        }
        var reg =  /^(?:0|[1-9][0-9]?|100)$/;
        if(!reg.test($("#fromScore").val())) {
            alert("风控分请输入100以内的正整数！");
            $("#fromScore").focus();
            return
        }
        if(!reg.test($("#toScore").val())) {
            alert("风控分请输入100以内的正整数！");
            $("#stocks").focus();
            return
        }
        var i = parseInt($("#fromScore").val());
        var j = parseInt($("#toScore").val());
        if(i>j){
            alert("风控分最低分不能大于最高分！");
            $("#fromScore").focus();
            $("#stocks").focus();
            return
        }

        return true;
    }

    function updatesecureLevel(code){
        $("#secureLevel").empty();
        var a = document.getElementById(code);
        var secureLevel = a.getAttribute("name"); //获取值
        var secureLevels= new Array();
        secureLevels = secureLevel.split(",");
        for(i=0;i<secureLevels.length;i++){
            if(secureLevels[i] == "primary"){
                document.getElementById('secureLevel').options.add(new Option('初级','primary'));
            }else if(secureLevels[i] == "middle"){
                document.getElementById('secureLevel').options.add(new Option('中级',"middle"));
            }else if(secureLevels[i] == "senior"){
                document.getElementById('secureLevel').options.add(new Option('高级',"senior"));
            }
        }
    }
</script>
</body>