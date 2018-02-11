<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>新巴巴-欢迎登录</title>
<link rel="stylesheet" type="text/css" href="/css/base.css" media="all">
<link type="text/css" rel="stylesheet" href="/css/a.css" source="widget">
<script type="text/javascript" src="/css/jquery-1.js"></script>
<script type="text/javascript">
$(function(){
	$("#loginsubmit").click(function(){
		$("#loginForm").submit();
	});
});
</script>
</head>
<body>
	<!-- SDK 登录 -->
	<div class="w">
		<div id="logo">
			<a href="javascript:;"> <img src="/images/XBB2.png" alt="新巴巴"
				height="60" width="170"></a><b></b>
		</div>
	</div>
	<div id="content">
		<div class="login-wrap">
			<div class="w">
				<div class="login-form">
					<div class="login-box">
						<div class="mt">
							<h1>新巴巴会员</h1>
							<div class="extra-r">
								<div class="regist-link">
									<a href="javascript:;" target="_blank"
										clstag="pageclick|keycount|20150112ABD|1"><b></b>立即注册</a>
								</div>
							</div>
						</div>
						<div class="msg-wrap">
							<div class="msg-warn" <c:if test="${!empty error }">style="display: none;"</c:if>>
								<b></b>公共场所不建议自动登录，以防账号丢失
							</div>
							<div <c:if test="${empty error }">style="display: none;"</c:if> class="msg-error">
								<b></b>${error }
							</div>
						</div>
						<div class="mc">
							<div class="form">
								<form id="loginForm" action="/login.aspx" method="post">
									<input type="hidden" name="returnUrl" value="${param.returnUrl }"/>
									<div class="item item-fore1">
										<label for="loginname" class="login-label name-label"></label>
										<input class="itxt" name="username"
											 placeholder="邮箱/用户名/已验证手机"
											type="text"> <span class="clear-btn"></span>
									</div>
									<div id="entry" class="item item-fore2">
										<label class="login-label pwd-label" for="nloginpwd"></label>
										<input name="password" class="itxt itxt-error"
											 placeholder="密码"
											type="password">
									</div>
									<div class="item item-fore3">
										<div class="safe">
											<span> <input checked="checked" id="autoLogin"
												name="chkRememberMe" class="jdcheckbox" tabindex="3"
												clstag="pageclick|keycount|20150112ABD|6" type="checkbox">
													<label for="">自动登录</label></span> <span class="forget-pw-safe">
												<a href="javascript:;" class=""
												clstag="pageclick|keycount|20150112ABD|8">忘记密码?</a>
											</span>
										</div>
									</div>
									<div class="item item-fore5">
										<div class="login-btn">
											<a href="javascript:;" class="btn-img btn-entry"
												id="loginsubmit"
												clstag="pageclick|keycount|20150112ABD|2">登&nbsp;&nbsp;&nbsp;&nbsp;录</a>
										</div>
									</div>
								</form>
							</div>
							<div class="coagent">
								<h5>使用合作网站账号登录新巴巴：</h5>
								<ul>
									<li><a href="javascript:void(0)"
										clstag="pageclick|keycount|20150112ABD|3">新巴巴钱包</a> <span
										class="line">|</span></li>
									<li><a href="javascript:void(0)"
										clstag="pageclick|keycount|20150112ABD|4">QQ</a> <span
										class="line">|</span></li>
									<li><a href="javascript:void(0)"
										clstag="pageclick|keycount|20150112ABD_201505135|2">微信</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="qrcode-login">
						<div class="mc">
							<div class="qrcode-desc">
								<h2>
									用新巴巴APP <span class="ml5">扫码安全登录</span>
								</h2>
							</div>
							<div class="qrcode-error">
								<b></b>
								<h6>登录失败</h6>
								请刷新二维码后重新扫描
							</div>
							<div class="qrcode-main">
								<div class="qrcode-img">
									<img src="/css/blank.gif" alt=""><div
											class="qrcode-error02 hide" id="J-qrcoderror">
											<a href="#none"> <span class="error-icon"></span>
												<div class="txt">
													网络开小差咯<span class="ml10">刷新二维码</span>
												</div>
											</a>
										</div>
								</div>
								<div class="qrcode-panel">
									<ul>
										<li class="fore1"><a href="#none">刷新二维码</a></li>
										<li><a href="http://help.jd.com/user/issue/353-1096.html">使用帮助</a></li>
									</ul>
									<div class="qrcode-tips">
										<span>扫描不上，版本过低？</span>
										<div class="triangle-border tb-border"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<a href="#nogo" class="qrcode-target btn-2qrcode"
						clstag="pageclick|keycount|20150112ABD_201505135|1">扫码登录</a>
				</div>
			</div>
			<div class="login-banner" style="background-color: #f2f0c0">
				<div class="w">
					<div id="banner-bg" clstag="pageclick|keycount|20150112ABD|46"
						class="i-inner"
						style="background: url(//img14.360buyimg.com/da/jfs/t2314/140/2063657963/118316/2fa0f374/5693044cNcc6a1504.jpg) 0px 0px no-repeat; background-color: #f2f0c0"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="w">
		<div class="ar">
			<a href="javascript:;" class="q-link"><b></b>登录页面，调查问卷</a>
		</div>
	</div>
	<div class="w">
		<div id="footer-2013">
			<div class="links">
				<a rel="nofollow" href="javascript:;"> 关于我们 </a> | <a rel="nofollow"
					href="javascript:;"> 联系我们 </a> | <a rel="nofollow"
					href="javascript:;"> 人才招聘 </a> | <a rel="nofollow"
					href="javascript:;"> 商家入驻 </a> | <a rel="nofollow"
					href="javascript:;"> 广告服务 </a> | <a rel="nofollow"
					href="javascript:;"> 手机新巴巴 </a> | <a href="javascript:;"> 友情链接
				</a> | <a href="javascript:;"> 销售联盟 </a> | <a href="javascript:;">
					新巴巴社区 </a> | <a href="javascript:;"> 新巴巴公益 </a> | <a
					clstag="pageclick|keycount|20150112ABD|9">English Site</a>
			</div>
			<div class="copyright">
				Copyright&#169;2016-2020&nbsp;&nbsp;新巴巴&nbsp;版权所有</div>
		</div>
	</div>

	<!-- SDK 登录 -->
</body>
</html>