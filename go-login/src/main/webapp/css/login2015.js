/**
 * sdk - login2015.js
 * 
 */
var LoginConstant = {
    HTTP_SCHEME: "http:",
    HTTPS_SCHEME: "https:"
};

var Util = {
    Cookie: {
        set: function (name, value, expire) {
            var exp = new Date();
            exp.setTime(exp.getTime() + expire * 24 * 60 * 60 * 1000);
            document.cookie = name + "=" + encodeURIComponent(value, "UTF-8") + ";expires=" + exp.toGMTString() + ";domain=passport.jd.com;path=/";
        },
        get: function (key) {
            var cookies = document.cookie ? document.cookie.split('; ') : [];
            for (var i = 0, l = cookies.length; i < l; i++) {
                var parts = cookies[i].split('=');
                var name = parts.shift();
                var cookie = parts.join('=');
                if (key && key === name) {
                    return cookie;
                }
            }
        },
        setALCookie: function () {
            if ($("input[name='chkRememberMe']").prop("checked")) {
                var Days = 3 * 30;
                this.set("alpin", $("#loginname").val(), Days);
            } else {
                var v = this.get("alpin");
                if (v) {
                    this.set("alpin", "", -100);
                }
            }
        }
    },
    Header: {
        getProtocol: function(){
            var parentScheme = '';
            try{
                parentScheme = parent.location.protocol;
            }catch(e){
                parentScheme = LoginConstant.HTTP_SCHEME;
            }
            if(!parentScheme){
                parentScheme = LoginConstant.HTTP_SCHEME;
            }
            return parentScheme;
        }
    }
};

(function(){

    //装配登陆表单，便于对表单进行统一序列化
    function assemblyForm(){
        //获取安全控件，并对loginpwd隐藏域进行填充
        if($("#chkOpenCtrl").prop("checked")==true){
            var uuid = $("#uuid").val();
            $.ajax({
                url: "../uc/srand?r="+Math.random() + "&uuid=" + uuid,
                type: "GET",
                dataType:"text",
                async: false,
                success: function(result){
                    if(result){
                        var obj = eval(result);
                        if (obj.failure) {
                            return false;
                        }
                        pgeditor.pwdSetSk(obj.info);
                    }
                }
            });
            $("#loginpwd").val(pgeditor.pwdResult());
            try{
                $("#machineNet").val(pgeditor.machineNetwork());
                $("#machineCpu").val(pgeditor.machineCPU());
                $("#machineDisk").val(pgeditor.machineDisk());
            }catch(e){}
        } else {
            $("#loginpwd").val($("#nloginpwd").val());
        }
    }

    //显示验证码
    function showAuthCode(){
        if($("#o-authcode").css("display")!="none"){
            return;
        }
        var loginUrl = "../uc/showAuthCode";
        var loginName=$("#loginname").val();
        $.ajax({
            type: "POST",
            url: loginUrl + "?r=" + Math.random()+"&version=2015",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: {
                loginName:loginName
            },
            dataType:"text",
            success: function (result) {
                if (result) {
                    var obj = eval(result);
                    if (obj.verifycode) {
                        $("#o-authcode").show();
                        $("#JD_Verification1").click();
                    } else {
                        $("#o-authcode").hide();
                    }
                }
            }
        });
    }

    var rmData={};
	var info = '<div class="msg-warn"><b></b>公共场所不建议自动登录，以防账号丢失</div>';
    function rememberMeCheck(){
    	var loginName=$("#loginname").val();
    	if(!loginName){
    		return;
    	}
    	if(rmData[loginName]){
    		$('#autoLogin').prop("checked",true);
			$('.msg-wrap .msg-error').hide();
			$('.msg-wrap .msg-warn').replaceWith(info);
    		return;
    	}
		$.ajax({
            type: "POST",
            url: "../uc/rememberMeCheck?r=" + Math.random()+"&version=2015",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: {
                loginName:loginName
            },
            dataType:"text",
            success: function (result) {
            	var data = eval(result);
                if (data.checked) {
                	$('#autoLogin').prop("checked",true);
        			$('.msg-wrap .msg-error').hide();
        			$('.msg-wrap .msg-warn').replaceWith(info);
                }else{
                	$('#autoLogin').prop("checked",false);
                	var msgWrap=$('.login-form .msg-wrap');
                	msgWrap.html("<div class=\"msg-warn hide\"><b></b></div><div class=\"msg-error hide\"><b></b></div>");
                }
            }
        });
    }
    //登陆操作
    function loginSubmit(callback) {
        $('#loginsubmit').text('正在登录...');
        if(window.location.href.indexOf("/popupLogin2013")!=-1){
            frameLoginSubmit(callback);
            return;
        }

        var loginUrl = "../uc/loginService";
        var uuid = $("#uuid").val();
        $.ajax({
            url: loginUrl + "?uuid=" + uuid + "&" + location.search.substring(1) + "&r=" + Math.random()+"&version=2015",
            type: "POST",
            dataType: "text",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: $("#formlogin").serialize(),
            error: function () {
                showMesInfo("网络超时，请稍后再试","error");
            },
            success: function (result) {
                if (result) {
                    var obj = eval(result);
                    if (obj.success) {
                        Util.Cookie.setALCookie();
                        var isIE = !-[1,];
                        if (isIE) {
                            var link = document.createElement("a");
                            link.href = obj.success;
                            link.style.display = 'none';
                            document.body.appendChild(link);
                            link.click();
                        } else {
                            window.location = obj.success;
                        }
                        return;
                    }
                    if (obj.transfer) {
                        window.location = obj.transfer + window.location.search;
                        return;
                    }
                    if (obj.venture) {
                        window.location = "http://safe.jd.com/dangerousVerify/index.action?username=" + obj.venture + "&ReturnUrl=" + encodeURI(obj.ventureRet) + "&t=" + new Date().getTime();
                        return;
                    }
                    if (obj.resetpwd) {
                        window.location = "http://safe.jd.com/resetPwd/reset.action?username=" + obj.resetpwd;
                        return;
                    }

                    if (obj.verifycode || obj.authcode1 || obj.authcode2 || obj.emptyAuthcode) {
                        $("#o-authcode").show();
                    }
                    $("#JD_Verification1").click();
                    if (obj.authcode2) {
                        callback(obj.authcode2,"error",["#authcode"]);
                    }
                    if (obj.username) {
                        callback(obj.username,"error",["#loginname"]);
                    }
                    if (obj.pwd) {
                        callback(obj.pwd,"error",["#nloginpwd"]);
                        clearPwd();
                    }
                    if (obj.emptyAuthcode) {
                        callback(obj.emptyAuthcode,"error",["#authcode"]);
                    }
                }
                $("#loginsubmit").html("登&nbsp;&nbsp;&nbsp;&nbsp;录");
            }
        });
    }

    function frameLoginSubmit(callback){
        var uuid = $("#uuid").val();
        var protocol = Util.Header.getProtocol();
        $.ajax({
            type: "POST",
            dataType: "text",
            url: "../uc/loginService?nr=1&uuid=" + uuid + "&" + location.search.substring(1) + "&r=" + Math.random() + "&version=2015",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: $("#formlogin").serialize(),
            error: function () {
                showMesInfo("网络超时，请稍后再试","error");
            },
            success: function (result) {
                if (result) {
                    var obj = eval(result);
                    if (obj.success || obj.transfer) {
                        Util.Cookie.setALCookie();
                        var relayUrl = protocol + '//passport.jd.com/relay/loginRelay.htm';
                        try{
                        	docRef = document.referrer;
                        	var regExp = /[\w-]+\.(jd\.hk|jd360\.hk|yiyaojd\.com)\//;
                        	var match = docRef.match(regExp);
                        	if(match != null && match.length>=2){
                        		relayUrl = protocol + "//sso." +match[1]+ "/relay/loginRelay.htm";
                        	}                        	
                        }catch(e){
                        }        
                        
                        if (obj.notnr) {
                            window.location.href=relayUrl;
                            return;
                        }

                        try {
                            $.ajax({
                                type: "GET",
                                url: obj.success,
                                dataType: "jsonp",
                                timeout: 1000,
                                success: function (result) {
                                	window.location.href=relayUrl;
                                    return;
                                }
                            });
                        } catch (e) {
                        	window.location.href=relayUrl;
                            return;
                        }
                    }
                    if (obj.venture) {
                    	var parentRef="";
                    	try{
                    		parentRef = document.referrer;
                    	}catch(e){                    		
                    	}
                        window.parent.location = protocol + "//safe.jd.com/dangerousVerify/index.action?username=" + obj.venture + "&ReturnUrl=" + encodeURI(parentRef) + "&t=" + new Date().getTime();
                        return;
                    }
                    if (obj.resetpwd) {
                        window.parent.location = protocol + "//safe.jd.com/resetPwd/reset.action?username=" + obj.resetpwd;
                        return;
                    }

                    if (obj.verifycode || obj.authcode1 || obj.authcode2 || obj.emptyAuthcode) {
                        $("#o-authcode").show();
                    }
                    $("#JD_Verification1").click();
                    if (obj.authcode2) {
                        callback(obj.authcode2,"error",["#authcode"]);
                    }
                    if (obj.username) {
                        callback(obj.username,"error",["#loginname"]);
                    }
                    if (obj.pwd) {
                        callback(obj.pwd,"error",["#nloginpwd"]);
                        clearPwd();
                    }
                    if (obj.emptyAuthcode) {
                        callback(obj.emptyAuthcode,"error",["#authcode"]);
                    }
                }
                $("#loginsubmit").html("登&nbsp;&nbsp;&nbsp;&nbsp;录");
            }
        });
    }

    function showMesInfo(msg, type) {
        $('.form>.msg-wrap').empty();
        if (type == 'warn') {
            var info = '<div class="msg-warn"><b></b>' + msg + '</div>';
            $('.form>.msg-wrap').append(info);
        }
        if (type == 'error') {
            var info = '<div class="msg-error"><b></b>' + msg + '</div>';
            $('.form>.msg-wrap').append(info);
        }
    }

    function clearPwd(){
        if(typeof pgeditor != 'undefined'){
            if($("#chkOpenCtrl").prop("checked")){
                pgeditor.pwdclear();
            }
        }
        $("#nloginpwd").val("");
        $("#loginpwd").val("");
        $('#nloginpwd').siblings('.clear-btn').hide();
    }
    $('#loginname').blur(function(){
    	rememberMeCheck();
    });
    window.loginSubmit=loginSubmit;
    window.assemblyForm=assemblyForm;
    window.showAuthCode=showAuthCode;
})();



