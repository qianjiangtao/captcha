/**
 * Created by qianjiangtao on 2016/11/22.
 */



//保存验证码规则
function saveCaptchaRule(id) {
    if (!formCheck(id)) {
        return;
    }
    msg = "你确定要保存该验证码规则？";
    $.confirm(msg, function () {
        var formData = formatData();
        $.ajax({
            url: "/captcha-back/captchaRuleController/saveCaptchaRule.do",
            type: "POST",
            data: formData,
            dataType: "json",
            success: function (json) {
                alert(json.message);
                if (!json.success) {
                    return;
                }
                //新建则跳到第一页
                if (typeof(id) == "undefined") {
                    window.location.href = "/captcha-back/captchaRuleController/captchaRuleList.do?page=1";
                } else {
                    //更新则进入当前页
                    window.location.reload();
                }
            }
        });
    })
}
/* ----------------业务类型,安全级别联动------------------- */
function showCanSelectLevel(code) {
    $("#secureLevel").empty();
    var a = document.getElementById(code);
    if (a != null) {
        var canSetLevel = a.getAttribute("name"); //获取值
        var canSetLevels = new Array();
        canSetLevels = canSetLevel.split(",");
        for (i = 0; i < canSetLevels.length; i++) {
            if (canSetLevels[i] == "primary") {
                document.getElementById('secureLevel').options.add(new Option('初级', canSetLevels[i]));
            } else if (canSetLevels[i] == "middle") {
                document.getElementById('secureLevel').options.add(new Option('中级', canSetLevels[i]));
            } else if (canSetLevels[i] == "senior") {
                document.getElementById('secureLevel').options.add(new Option('高级', canSetLevels[i]));
            }
        }
    }
}
/*  --------选择验证码类型为运算型的隐藏一些输入框------------ */
function disableSomeInput() {
    $("input[name='chineseCharLength']").val(0);
    $("input[name='chineseCharLength']").attr("disabled", true);
    $("input[name='englishCharLength']").val(0);
    $("input[name='englishCharLength']").attr("disabled", true);
    $("input[name='numbersCharLength']").val(2);
    $("input[name='numbersCharLength']").attr("readonly", true);

}
function showSomeInput() {
    $("input[name='chineseCharLength']").val('');
    $("input[name='chineseCharLength']").attr("disabled", false);
    $("input[name='englishCharLength']").val('');
    $("input[name='englishCharLength']").attr("disabled", false);
    $("input[name='numbersCharLength']").val('');
    $("input[name='numbersCharLength']").attr("readonly", false);
}
/*-----------------------预览-----------------------------*/
function previewCaptcha() {

    if (!formCheck()) {
        return;
    }
    var formData = formatData();

    var bizCode = formData.businessCode;
    var secureLevel = formData.secureLevel;

    //front url 前缀
    var prefixURL = $("#prefixURL").val();
    $.ajax({
        url: "/captcha-back/captchaRuleController/cacheCaptchaData.do",
        type: "POST",
        data: formData,
        dataType: "json",
        success: function (json) {
            if (json.success) {
                var elt = document.getElementById("captchaImg");
                elt.src = prefixURL + "/previewCaptcha?bizCode=" + bizCode + "&secureLevel=" + secureLevel + "&timestamp=" + (new Date).getTime();
            } else {
                alert(json.message);
            }
            //隐藏业务code和安全级别
            $("#businessCode").attr("disabled", true);
            $("#secureLevel").attr("disabled", true);
        }
    });
}
/* -----------------隐藏字体-------------------- */
function hiddenEngFont(selectObj) {
    var chineseCharLength = $("input[name='chineseCharLength']").val();
    //当选择的验证码为中文字体时 不展示英文字体
    var options = selectObj.options;
    if (chineseCharLength = '' || chineseCharLength == 0) {
        hiddenEngFontComm(selectObj, false);
    } else {
        hiddenEngFontComm(selectObj, true);
    }
}
function hiddenEngFontComm(selectObj, boolean) {
    var options = selectObj.options;
    for (var i = 0; i < options.length; i++) {
        if (options[i].getAttribute("name") == "englishFont") {
            if (boolean == true) {
                options[i].setAttribute("hidden", boolean);
            } else {
                options[i].removeAttribute("hidden");
            }
        }
    }
}
function hiddenFont() {
    $('#fontName option:eq(0)').attr('selected', 'selected');
}
/* ---------------------表单校验--------------------------- */
function formCheck(id) {

    //新建进行业务类型和安全级别校验
    if (typeof(id) == "undefined") {
        //业务类型
        var businessCode = $("#businessCode").val();
        if (businessCode == '') {
            alert("清选择业务类型!!!");
            return false;
        }
        //安全级别
        var secureLevel = $("#secureLevel").val();
        if (secureLevel == '') {
            alert("清选择安全级别!!!");
            return false;
        }
    }
    //验证码宽度
    var imgWidth = $("#imgWidth").val();
    if (imgWidth == '') {
        alert("验证码的宽度不能为空!!!");
        return false;
    }
    var reg = /^[1-9]*[1-9][0-9]*$/;
    var reg2 = /^[0-9]*[0-9][0-9]*$/;
    if (!reg.test(imgWidth)) {
        alert("验证码的宽度必须为正整数!!!");
        return false;
    }

    //验证码高度
    var imgHeight = $("#imgHeight").val();
    if (imgHeight == '') {
        alert("验证码的高度不能为空!!!");
        return false;
    }
    if (!reg.test(imgHeight)) {
        alert("验证码的高度必须为正整数!!!");
        return false;
    }

    //验证码字符个数
    var chineseCharLength = $("input[name='chineseCharLength']").val();
    var englishCharLength = $("input[name='englishCharLength']").val();
    var numbersCharLength = $("input[name='numbersCharLength']").val();
    if (chineseCharLength == '' && englishCharLength == '' && numbersCharLength == '') {
        alert("验证码字符位数不能全为空!!!");
        return false;
    }
    if (chineseCharLength != '' && !reg2.test(chineseCharLength)) {
        alert("验证码字符位数只能为正整数!!!");
        return false;
    }
    if (englishCharLength != '' && !reg2.test(englishCharLength)) {
        alert("验证码字符位数只能为正整数!!!");
        return false;
    }
    if (numbersCharLength != '' && !reg2.test(numbersCharLength)) {
        alert("验证码字符位数只能为正整数!!!");
        return false;
    }
    var num = Number(chineseCharLength) + Number(englishCharLength) + Number(numbersCharLength);
    //非运算型的需要进行位数校验
    var checkedValue = $("input[type='radio']:checked").val();
    if (checkedValue != 1) {
        if (parseInt(num) < 4 || parseInt(num) > 8) {
            alert("验证码长度4~8位!!!");
            return false;
        }
    }

    //字体大小
    var fontSizeArr = $("input[name='fontSize']");
    for (var i = 0; i < fontSizeArr.length; i++) {
        if (fontSizeArr[i].value == '') {
            alert("字体大小不能为空!!!");
            return false;
            break;
        }
        if (!reg.test(fontSizeArr[i].value)) {
            alert("字体大小必须为正整数!!!");
            return false;
            break;
        }
    }
    //背景颜色
    var bgColorArr = $("input[name='bgColor']");
    for (var i = 0; i < bgColorArr.length; i++) {
        if (bgColorArr[i].value == '') {
            alert("背景颜色颜色不能为空!!!");
            return false;
            break;
        }
    }
    //字符变色
    var charColorArr = $("input[name='charColor']");
    for (var i = 0; i < charColorArr.length; i++) {
        if (charColorArr[i].value == '') {
            alert("字符颜色不能为空!!!");
            return false;
            break;
        }
    }
    //干扰线颜色
    var noiseColorArr = $("input[name='noiseColor']");
    for (var i = 0; i < noiseColorArr.length; i++) {
        if (noiseColorArr[i].value == '') {
            alert("干扰线颜色不能为空!!!");
            return false;
            break;
        }
    }
    //干扰线宽度
    var noiseWidthArr = $("input[name='noiseWidth']");
    for (var i = 0; i < noiseWidthArr.length; i++) {
        if (noiseWidthArr[i].value == '') {
            alert("干扰线宽度不能为空!!!");
            return false;
            break;
        }
        if (!reg.test(noiseWidthArr[i].value)) {
            alert("干扰线宽度必须为正整数!!!");
            return false;
            break;
        }
    }

    var overlapPercent = $("#overlapPercent").val();
    if (overlapPercent != '' && !reg2.test(overlapPercent)) {
        alert("粘连比必须为正整数!!!");
        return false;
    }

    //过期时间校验
    var expireTime = $("input[name='expireTime']").val();
    if (expireTime == '') {
        alert("过期时间不能为空!!!");
        return false;
    }
    if (!reg.test(expireTime)) {
        alert("过期时间必须为正整数!!!");
        return false;
    }
    if (parseInt(expireTime) < 10) {
        alert("过期时间不能太短,不能少于10秒!!!");
        return false;
    }
    return true;
}


/* ---------------------表单操作--------------------------- */
//添加字体
function addFont() {
    var num = 1;
    var fontDivHtml = $("#fontDiv").html();//minicolors
    var fontHtml = "<div id='fontStyle" + (++num) + "'>" +
        fontDivHtml +
        "<input type='button' value='删除' class='btn btn-small w3' onclick='removeFontStyle(" + num + ");'/>" +
        "</div>"
    $("#addFontDiv").append(fontHtml);
}

//删除字体
function removeFontStyle(num) {
    $("#fontStyle" + num).remove();
}

//添加背景颜色
function addBgColor() {
    var num = 1;
    var bgColorHtml = "<div id='bgColorStyle" + (++num) + "'>" +
        "颜色&nbsp;<input type='text' class='form-control demo' value='#ffffff' name='bgPreColor'>" +
        " <input type='text' class='form-control demo' value='#ffffff' name='bgBackColor'>" +
        "&nbsp;<input type='button' value='删除' class='btn btn-small w3' onclick='removeBgColorStyle(" + num + ");'/>" +
        "</div>"
    $("#addBgColortDiv").append(bgColorHtml);
    readyPlugin();
}

//删除背景颜色
function removeBgColorStyle(num) {
    $("#bgColorStyle" + num).remove();
}

//添加字符颜色
function addFontColor() {
    var num = 1;
    var fontColorHtml = "<div id='FontColorStyle" + (++num) + "'>" +
        "颜色&nbsp;<input type='text' class='form-control demo' value='#ff0066' name='charColor'>" +
        "&nbsp;<input type='button' value='删除' class='btn btn-small w3' onclick='removeFontColorStyle(" + num + ");'/>" +
        "</div>"
    $("#addFontColortDiv").append(fontColorHtml);
    readyPlugin();
}

//删除字符颜色
function removeFontColorStyle(num) {
    $("#FontColorStyle" + num).remove();
}

//添加干扰线
var num = 1;
function addnoise() {
    var noiseHtml = "<div id='noiseStyle" + (++num) + "'>" +
        "颜色&nbsp;<input type='text' class='form-control demo' value='#ff0066' name='noiseColor'>" +
        "&nbsp;宽度<input type='text' style='width: 20px' name='noiseWidth'/>" +
        "<input type='button' value='删除' class='btn btn-small w3' onclick='removeNoiseStyle(" + num + ");'/>" +
        "</div>";
    if (num == 1) {
        noiseHtml += "<input type='button' value='删除' class='btn btn-small w3' onclick='removeNoiseStyle(" + num + ");'/>";
    }
    $("#addNoisetDiv").append(noiseHtml);
    readyPlugin();
}

//删除干扰线
function removeNoiseStyle(num) {
    $("#noiseStyle" + num).remove();
    if (num == 2) {
        $("#delete").attr("disabled", false);
    }
}
/*  ----------------------取色器插件-------------------- */
$(document).ready(function () {
    readyPlugin();
});
function readyPlugin() {
    $('.demo').each(function () {

        $(this).minicolors({

            control: $(this).attr('data-control') || 'hue',

            defaultValue: $(this).attr('data-defaultValue') || '',

            inline: $(this).attr('data-inline') === 'true',

            letterCase: $(this).attr('data-letterCase') || 'lowercase',

            opacity: $(this).attr('data-opacity'),

            position: $(this).attr('data-position') || 'bottom left',

            change: function (hex, opacity) {

                if (!hex) return;

                if (opacity) hex += ', ' + opacity;

            },
        });
    });
}

//格式化数据
function formatData() {

    $("select[name='businessCode']").attr("disabled", false);
    $("select[name='secureLevel']").attr("disabled", false);
    $("input[name='chineseCharLength']").attr("disabled", false);
    $("input[name='englishCharLength']").attr("disabled", false);
    $("input[name='numbersCharLength']").attr("disabled", false);

    var formData = fromToJson("captchaForm");

    var fontnames = formData.fontName.split(",");
    var fontStyle = formData.fontStyle.split(",");
    var fontSize = formData.fontSize.split(",");

    var charColors = formData.charColor.split(",");
    var bgColors = formData.bgColor.split(",");
    var charLengthArr = new Array();
    charLengthArr.push(formData.chineseCharLength == "" ? 0 : parseInt(formData.chineseCharLength));
    charLengthArr.push(formData.englishCharLength == "" ? 0 : parseInt(formData.englishCharLength));
    charLengthArr.push(formData.numbersCharLength == "" ? 0 : parseInt(formData.numbersCharLength));

    var bgColorArr = new Array();
    $.each(bgColors, function (index, value) {
        bgColorArr.push(getIntColor(value));
    });

    //字体
    var fontArr = new Array();
    $.each(fontnames, function (index, value) {
        var fontJson = {};
        fontJson.name = value;
        fontJson.style = parseInt(fontStyle[index]);
        fontJson.size = parseInt(fontSize[index]);
        fontArr.push(fontJson);
    });
    //干扰线
    var noiseArr = new Array();
    if(typeof (formData.noiseWidth)!="undefined"){
        var noiseColors = formData.noiseColor.split(",");
        var noiseWidths = formData.noiseWidth.split(",");
        $.each(noiseColors, function (index, value) {
            var noiseJson = {};
            noiseJson.noiseColor = getIntColor(value);
            noiseJson.noiseWidth = parseInt(noiseWidths[index]);
            noiseArr.push(noiseJson);
        });
    }
    formData.noise = JSON.stringify(noiseArr);
    //字体颜色
    var charColorArr = new Array();
    $.each(charColors, function (index, value) {
        charColorArr.push(getIntColor(value));
    });
    var overlapPercent = formData.overlapPercent;
    if (overlapPercent == '') {
        overlapPercent = 0;
    }
    formData.overlapPercent = overlapPercent;
    //背景类型
    var bgTypeJson = {};
    bgTypeJson.shadow = (formData.shadow == undefined) ? 0 : parseInt(formData.shadow);
    bgTypeJson.stretch = (formData.stretch == undefined) ? 0 : parseInt(formData.stretch);
    bgTypeJson.fishEye = (formData.fishEye == undefined) ? 0 : parseInt(formData.fishEye);
    bgTypeJson.ripple = (formData.ripple == undefined) ? 0 : parseInt(formData.ripple);

    formData.charColor = JSON.stringify(charColorArr);
    formData.bgColor = JSON.stringify(bgColorArr);
    formData.font = JSON.stringify(fontArr);
    formData.charLength = JSON.stringify(charLengthArr);
    formData.bgType = JSON.stringify(bgTypeJson);
    //删除多余的数据
    delete formData.chineseCharLength;
    delete formData.englishCharLength;
    delete formData.numbersCharLength;
    delete formData.fontStyle;
    delete formData.fontSize;
    delete formData.noiseColor;
    delete formData.noiseWidth;
    console.log(formData);
    return formData;
}

//十六进制的颜色转换为十进制
function getIntColor(str) {
    var hex = str.substring(1, str.length);
    return parseInt(hex, 16);
}

function fromToJson(form) {
    var result = {};
    var fieldArray = $('#' + form).serializeArray();
    for (var i = 0; i < fieldArray.length; i++) {
        var field = fieldArray[i];
        if (field.name in result) {
            result[field.name] += ',' + field.value;
        } else {
            result[field.name] = field.value;
        }
    }
    return result;
}