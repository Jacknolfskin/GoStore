<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
   <meta http-equiv="pragma" content="no-cache" />
   <meta http-equiv="cache-control" content="no-cache" />
   <meta http-equiv="expires" content="0" /> 
   <meta name="format-detection" content="telephone=no" />  
   <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" /> 
   <meta name="format-detection" content="telephone=no" />
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
   <link type="text/css" rel="stylesheet" href="/css/base.css" />
   <link rel="stylesheet" type="text/css" href="/css/purchase.base.2012.css" />
   <link rel="stylesheet" type="text/css" href="/css/purchase.sop.css" />
   <link type="text/css" rel="stylesheet" href="/css/a_002.css"
	source="widget">
	<link type="text/css" rel="stylesheet" href="/css/a.css">
   <title>订单成功页面 - 新巴巴商城</title>
   <script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
   <script type="text/javascript" src="/js/base-2011.js" charset="utf-8"></script>
   <script type="text/javascript" src="/js/jquery.cookie.js" charset="utf-8"></script>
</head> 
<body id="mainframe">
<!--shortcut start-->
<jsp:include page="commons/shortcut.jsp" />
<!--shortcut end-->
	<div class="w w1 header clearfix">
		<div id="logo">
			<a href="/"><img src="/images/XBB2.png" alt="新巴巴商城"></a> <a
				href="javascript:;" class="link2"><b></b>"结算页"</a>
		</div>
		<div class="stepflex" id="#sflex03">
			<dl class="first done">
				<dt class="s-num">1</dt>
				<dd class="s-text">
					1.我的购物车<s></s><b></b>
				</dd>
			</dl>
			<dl class="normal done">
				<dt class="s-num">2</dt>
				<dd class="s-text">
					2.填写核对订单信息<s></s><b></b>
				</dd>
			</dl>
			<dl class="normal doing">
				<dt class="s-num">3</dt>
				<dd class="s-text">
					3.成功提交订单<s></s><b></b>
				</dd>
			</dl>
		</div>
	</div>
<div class="w main">
	<div class="m m3 msop">
        <div class="mt" id="success_tittle"><s class="icon-succ02"></s><h3 class="ftx-02">感谢您，订单提交成功！</h3>
		</div>
		<div class="mc" id="success_detail">	
		    <ul class="list-order">
			    <li class="li-st">
					<div class="fore1">订单号：<a href="javascript:void(0)">66666888888</a></div>
					<!-- 货到付款 -->
					<div class="fore2">货到付款：<strong class="ftx-01">361.0元</strong></div>
					<div class="fore3">
					   	新巴巴快递 &nbsp; 送货时间: 预计 2016-01-13 14:49:39 送达&nbsp;
					</div>
				</li>
			</ul>
		<!-- 在线支付按钮  -->
				<div id="bookDiv"></div>
 					<p class="i-tips01">
				            	您的订单已经在处理中，发货后订单内容会显示承运人联系方式，如有必要您可以联系对方
             		</p>
		 </div>
	</div>
</div>
	<!-- footer start -->
	<jsp:include page="commons/footer.jsp" />
	<!-- footer end -->
</body> 
</html>