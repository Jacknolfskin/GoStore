<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>订单结算页 -新巴巴商城</title>
<!--结算页面样式-->
<link rel="stylesheet" type="text/css" href="/css/base.css" media="all" />
<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
<script type="text/javascript" src="/js/base.js"></script>
<link type="text/css" rel="stylesheet" href="/css/a_002.css"
	source="widget">
	<link type="text/css" rel="stylesheet" href="/css/a.css">
</head>
<body id="mainframe">
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
			<dl class="normal doing">
				<dt class="s-num">2</dt>
				<dd class="s-text">
					2.填写核对订单信息<s></s><b></b>
				</dd>
			</dl>
			<dl class="normal last">
				<dt class="s-num">3</dt>
				<dd class="s-text">
					3.成功提交订单<s></s><b></b>
				</dd>
			</dl>
		</div>
	</div>
	<!-- main -->
<form action="/buyer/submitOrder" method="post">
	<div id="container">
		<div id="content" class="w">
			<div class="m">
				<div class="checkout-tit">
					<span class="tit-txt">填写并核对订单信息</span>
				</div>
				<div class="mc">
					<div class="checkout-steps">
						<div class="step-tit">
							<h3>收货人信息</h3>
							<div class="extra-r">
								<a href="javascript:;" class="ftx-05">新增收货地址</a> <input
									id="del_consignee_type" value="0" type="hidden">
							</div>
						</div>
						<div class="step-cont">
							<div id="consignee-addr" class="consignee-content">
								<div class="consignee-scrollbar">
									<div class="ui-scrollbar-main">
										<div class="consignee-scroll">
											<div style="height: 42px;" class="consignee-cont"
												id="consignee1">
												<ul id="consignee-list">
													<li
														class="ui-switchable-panel ui-switchable-panel-selected"
														style="display: list-item;" id="consignee_index_137629184"
														selected="selected">
														<div class="consignee-item item-selected"
															id="consignee_index_div_137629184">
															<span limit="8" title="范冰冰">范冰冰 北京</span><b></b>
														</div>
														<div class="addr-detail">
															<span title="范冰冰 " class="addr-name" limit="6">范冰冰
															</span> <span class="addr-info" limit="45">北京 海淀区 西三旗
																XXXXXXXXX</span> <span class="addr-tel">158***888888</span>
														</div>
														<div class="op-btns" consigneeid="137629184">
															<a href="#none" class="ftx-05 setdefault-consignee"
																fid="137629184">设为默认地址</a> <a href="#none"
																class="ftx-05 edit-consignee" fid="137629184">编辑</a> <a
																href="#none" class="ftx-05 del-consignee"
																fid="137629184">删除</a>
														</div>
													</li>
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="hr"></div>
						<div id="shipAndSkuInfo">
							<div id="payShipAndSkuInfo">
								<div class="step-tit">
									<h3>支付方式</h3>
								</div>
								<div class="step-cont">
									<div class="payment-list" >
										<div class="list-cont">
											<ul id="payment-list">
												<input type="hidden" name="paymentWay" value="1">
													<li style="cursor: pointer;" onclick="paymentWay(this,1);">
														<div class="payment-item item-selected online-payment"
															for="pay-method-1" payname="货到付款" payid="1">
															<b></b> 货到付款
														</div>
												</li>
												<li style="cursor: pointer;" onclick="paymentWay(this,2);">
														<div class="payment-item  online-payment "
															for="pay-method-2" payname="在线支付" payid="2">
															<b></b> 在线支付
														</div>
												</li>
												<li style="cursor: pointer;" onclick="paymentWay(this,3); ">
														<div class="payment-item  online-payment "
															for="pay-method-3" payname="公司转账" payid="3">
															<b></b> 公司转账
														</div>
												</li>
												<li style="cursor: pointer;" onclick="paymentWay(this,4); ">
														<div class="payment-item  online-payment"
															for="pay-method-4" payname="邮局汇款" payid="4">
															<b></b> 邮局汇款
														</div>
												</li>
											</ul>
										</div>
										<script type="text/javascript">
										//选择支付方式
										function paymentWay(target,p){
											$("#payment-list div").removeClass("item-selected");
											$(target).children("div").addClass("item-selected");
											$("input[name='paymentWay']").val(p);
										}
										</script>
									</div>
								</div>
								<div class="hr"></div>
								<div class="step-tit">
									<h3>送货清单</h3>
									<div class="extra-r">
										<a href="/shopping/toCart" class="return-edit ftx-05">返回修改购物车</a>
									</div>
								</div>
								<div class="step-cont" id="skuPayAndShipment-cont">
									<!--添加商品清单  zhuqingjie -->
									<div class="shopping-lists" id="shopping-lists">
										<!--定义大商品清单LIST-->
										<div class="shopping-list ABTest">
											<div class="goods-list">
												<!--购物车单品商品-->
												<!--一般套装商品-->
												<!--满返套装商品-->
												<!--满赠套装商品-->
												<!--配送方式-->
												<div class="goods-tit">
													<h4 class="vendor_name_h" id="0">商家：新巴巴自营</h4>
												</div>

												<!--单品开始-->
												<div class="goods-items"></div>
												<!--单品结束-->
												<!--一般套装开始-->
												<!--一般套装结束-->
												<!--满返套装开始-->
												<!--满返套装结束-->
												<!--满赠套装开始-->
												<div class="goods-items">
													<div class="goods-suit goods-last">
														<div class="goods-suit-tit">
															<span class="sales-icon">满赠</span> <strong>
																已购满2.00元 ，再加4.00元，可返回购物车领取赠品 </strong>
														</div>

														<div class="goods-item goods-item-extra"
															goods-id="1305606">

															<div class="p-img">
																<a target="_blank"
																	href="http://item.jd.com/1305606.html"><img
																	src="/order_files/565d0624N54b49906_002.jpg" alt=""></a>
															</div>
															<div class="goods-msg">
																<div class="goods-msg-gel">
																	<div class="p-name">
																		<a href="http://item.jd.com/1305606.html"
																			target="_blank"> 帮宝适 Pampers 超薄干爽 婴儿拉拉裤
																			大号L84片【9-14kg】 </a>
																	</div>
																	<div class="p-price">
																		<!--增加预售金额显示 begin   预售分阶段支付类型（1：一阶梯全款支付；2：一阶梯定金支付(全款或定金可选)；3：三阶梯(仅定金)；4：三阶梯(全款或定金可选)） -->
																		<strong class="jd-price">￥135.00</strong>
																		<!--增加预售金额显示 end-->
																		<span class="p-num"> x1 </span> <span class="p-state"
																			skuid="1305606">有货</span>

																	</div>
																</div>
															</div>
															<div>
																<i class="p-icon p-icon-w"></i><span class="ftx-04">7天无理由退货</span>
															</div>

															<div class="clr"></div>
														</div>
													</div>
												</div>
												<!--满赠套装结束-->
											</div>
											<!--goods-list 结束-->
											<div class="dis-modes">
												<!--购物车单品商品-->
												<!--一般套装商品-->
												<!--满返套装商品-->
												<!--满赠套装商品-->
												<!--配送方式-->
												<!--以下为新巴巴配送方式-->
												<!--配送方式-->
												<!--配送方式-->
												<div class="mode-item mode-tab">
													<div class="mode-item-tit">
														<h4>配送方式</h4>
														<div class="extral-r">
															<a id="jd-goods-item" class="cor-goods" href="#none"><i></i>对应商品</a>
														</div>
													</div>
													<div class="mode-tab-nav">
														<ul>
															<li class="mode-tab-item curr"><span
																id="jdShip-span-tip" class="m-txt">新巴巴快递
																	<li class="mode-tab-item" id="pick_shipment_item"><span
																		class="m-txt">上门自提<span class="ftx01">(荐)</span></li>
														</ul>
													</div>
													<div class="mode-tab-con  ui-switchable-panel-selected"
														id="jd_shipment">
														<ul class="mode-list">
															<li>
																<div class="fore1" id="payment_name_div">
																	<span class="ftx-03">付款方式：</span> <input type="radio"
																		value="1" name="paymentCash" checked="checked" />现金 <input
																		type="radio" value="2" name="paymentCash" />POS机
																</div>
															</li>
															<li>
																<div class="fore1" id="jd_shipment_calendar_date">
																	<span class="ftx-03">配送时间：</span>
																	预计&nbsp;1月12日&nbsp;09:00-15:00&nbsp;送达
																</div>
																<div class="fore2">
																	<a href="javascript:;" class="ftx-05">修改</a>
																</div>
															</li>
														</ul>
													</div>

													<!--非大件对应商品清单开始-->
													<div class="hide" id="jdItem_surpportSku">
														<div class="tooltip-goods">
															<div class="tooltip-tit">
																以下商品为<strong>非大件商品</strong>
															</div>
															<div class="goods-items">
																<div class="goods-item">
																	<div class="p-img">
																		<a href="#none"><img
																			src="/order_files/565d0624N54b49906_002.jpg" alt=""></a>
																	</div>
																	<div class="p-name">
																		<a href="#none">帮宝适 Pampers 超薄干爽 婴儿拉拉裤
																			大号L84片【9-14kg】</a>
																	</div>
																</div>
															</div>
														</div>
													</div>
													<!--非大件对应商品清单结束-->
												</div>
											</div>
											<!--dis-modes 结束-->
											<div class="clr"></div>
										</div>
										<!--shopping-list 结束-->
										<div class="shopping-list ABTest">
											<div class="goods-list">
												<!--购物车单品商品-->
												<!--一般套装商品-->
												<!--满返套装商品-->
												<!--满赠套装商品-->
												<!--配送方式-->
												<div class="goods-tit">
													<h4 class="vendor_name_h" id="137701">商家：怡盈童装专营店</h4>
												</div>
												<!--单品开始-->
												<div class="goods-items">
													<div class="goods-item goods-item-extra"
														goods-id="1579699464">

														<div class="p-img">
															<a target="_blank" href="javascript:;"><img
																src="/order_files/55668cb2N2cb0a8fa_002.jpg" alt=""></a>
														</div>
														<div class="goods-msg">
															<div class="goods-msg-gel">
																<div class="p-name">
																	<a href="javascript:;" target="_blank">
																		婴儿连体衣夏装宝宝短袖哈衣夏季薄初生儿衣服婴幼儿爬服夏天 黄色 73cm </a>
																</div>
																<div class="p-price">
																	<!--增加预售金额显示 begin   预售分阶段支付类型（1：一阶梯全款支付；2：一阶梯定金支付(全款或定金可选)；3：三阶梯(仅定金)；4：三阶梯(全款或定金可选)） -->
																	<strong class="jd-price">￥72.00</strong>
																	<!--增加预售金额显示 end-->
																	<span class="p-num"> x1 </span> <span class="p-state"
																		skuid="1579699464">有货</span>

																</div>
															</div>
														</div>
														<div>
															<i class="p-icon p-icon-w"></i><span class="ftx-04">7天无理由退货</span>
														</div>

														<div class="clr"></div>
													</div>
												</div>
												<!--单品结束-->
												<!--一般套装开始-->
												<!--一般套装结束-->
												<!--满返套装开始-->
												<!--满返套装结束-->
												<!--满赠套装开始-->
												<!--满赠套装结束-->
											</div>
											<!--goods-list 结束-->
											<div class="dis-modes">
												<!--购物车单品商品-->
												<!--一般套装商品-->
												<!--满返套装商品-->
												<!--满赠套装商品-->
												<!--配送方式-->
												<!--以下为新巴巴配送方式-->
												<!--配送方式-->
												<!--配送方式-->
												<!--以下为新巴巴大家电配送-->

												<!--以下为新巴巴第三方配送-->
												<!--以下为第三方配送-->
												<!--如果是SOP快递或者是新巴巴中小件商品，但是不支持配送，则采用快递运输-->
												<div class="mode-item mode-tab">
													<h4>配送方式</h4>
													<div class="mode-tab-nav">
														<ul>
															<li class="mode-tab-item curr tips-hover"><span
																class="m-txt">快递运输<i class="qmark-icon qmark-tip"
																	data-tips="由商家选择合作快递为您配送"></i>
															</span> <b></b></li>
														</ul>
													</div>
													<div class="mode-tab-con hide">
														<div class="mode-promise">
															<span id="promise-ico"><a
																href="javascript:void(0)" class="pop_FreightInsurance"
																title=""> </a></span>
															<div class="promise-txt yfx_div_remark" id="137701">
																为您购买了运费险，最高赔付20.00元/单</div>
														</div>
													</div>
												</div>
											</div>
											<!--dis-modes 结束-->
											<div class="clr"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!--shopping-list 结束-->
						<!--添加备注信息-->
						<div style="display: block;" class="order-remarks hide"
							id="orderRemarkItem">
							<div class="remark-tit">添加订单备注</div>
							<div id="remarkId" style="margin-bottom: 7px">
								<div class="form remark-cont">
									<input name="note" maxlength="45" size="15" class="itxt itxt01"
										placeholder="限45个字（定制类商品，请将购买需求在备注中做详细说明）"
										onblur="if(this.value==''||this.value=='限45个字（定制类商品，请将购买需求在备注中做详细说明）'){this.value='限45个字（定制类商品，请将购买需求在备注中做详细说明）';this.style.color='#cccccc'}"
										onfocus="if(this.value=='限45个字（定制类商品，请将购买需求在备注中做详细说明）') {this.value='';};this.style.color='#000000';"
										type="text"><span class="ftx-03 ml10">&nbsp;&nbsp;提示：请勿填写有关支付、收货、发票方面的信息</span>
								</div>
							</div>
						</div>
						<div class="hr"></div>
						<div class="step-tit" id="invoice-step">
							<h3>发票信息</h3>
						</div>
						<div class="step-content">
							<div id="part-inv" class="invoice-cont">
								<span class="mr10"> 普通发票（纸质） &nbsp; </span><span class="mr10">
									个人 &nbsp; </span><span class="mr10"> 明细 &nbsp; </span> <a href="#none"
									class="ftx-05 invoice-edit" onclick="edit_Invoice()">修改</a>
							</div>
						</div>
						<div class="clr"></div>
						<div class="hr"></div>
						<div class="order-coupon">
							<!-- 优惠券开始 -->
							<div class="item" id="orderCouponItem">
								<div class="toggle-title">
									<a class="toggler" href="#none" onclick="query_coupons()"><b></b>使用优惠券（京券/东券/免运费券）</a>
								</div>
								<div class="toggle-wrap hide" id="orderCouponId">
									<div class="cbox" data-widget="tabs" id="coupons"></div>
								</div>
							</div>
							<!-- 优惠券结束 -->
							<!-- 新巴巴卡 -->
							<div class="item" id="orderGiftCardItem">
								<div class="toggle-title">
									<a class="toggler" href="#none" onclick="query_giftCards(2)"><b></b>使用新巴巴卡</a>
								</div>
								<div class="toggle-wrap hide" id="giftCardId">
									<div class="cbox" id="gift"></div>
								</div>
							</div>
							<!-- 新巴巴卡结束 -->
							<!-- 新巴巴E卡 -->
							<div class="item" id="orderEGiftCardItem">
								<div class="toggle-title">
									<a class="toggler" href="#none" onclick="query_giftCards(3)"><b></b>使用新巴巴E卡/图书卡（不能与新巴巴卡同时使用）</a>
								</div>
								<div class="toggle-wrap hide" id="eCardId">
									<div id="ecard"></div>
								</div>
							</div>
							<!-- 新巴巴E卡结束 -->
							<!-- 京豆支付开始 -->
							<div class="item" id="orderBeanItem">
								<div class="toggle-title">
									<a class="toggler" href="#none" onclick="showOrHideJdBean()"><b></b>使用京豆</a>
								</div>
								<div class="toggle-wrap" id="jdBeans-new" style="display: none;"></div>
							</div>
							<!-- 京豆支付结束 -->
							<!-- 余额支付 -->
							<div class="item" id="balance-div">
								<div class="toggle-title">
									<a class="toggler" href="#none"><b></b>使用余额</a>
								</div>
								<div class="toggle-wrap hide" id="jdBalance">
									<div class="cbox">
										<div class="inner">
											<div class="form">
												<input disabled="disabled" id="selectOrderBalance"
													class="jdcheckbox" value=""
													onclick="useOrCancelBalance(this)" type="checkbox">
													<label id="canUsedBalanceId" for="selectOrderBalance"
													value="0.00">使用余额（账户当前余额：0.00元）</label>
											</div>
											<div class="ftx01 safeLpkPart hide" id="safeBalancePart">
												为保障您的账户资金安全，余额暂不可用，请先 <a target="_blank"
													href="http://safe.jd.com/user/paymentpassword/safetyCenter.action">[开启支付密码]</a>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- 余额支付结束 -->
							<!-- 支付密码 -->
							<div class="pay-password" id="paypasswordPanel"
								style="display: none">
								<span class="label"> 支付密码： </span>

								<div class="fl">
									<input class="textbox1" id="txt_paypassword" type="password">
										<span class="forgot-password"> <a target="_blank"
											href="http://safe.jd.com/user/paymentpassword/getBackPassword.action">忘记支付密码？
										</a>
									</span>
								</div>
							</div>
							<!-- 支付密码 -->
						</div>
					</div>
				</div>
			</div>
			<div class="order-summary">
				<!--  预售 计算支付展现方式 begin -->
				<div class="statistic fr">
					<div class="list">
						<span><em class="ftx-01">4</em> 件商品，总商品金额：</span> <em
							class="price" id="warePriceId" v="343.8">￥343.80</em>
					</div>
					<div class="list">
						<span>返现：</span> <em class="price" id="cachBackId" v="0">
							-￥0.00</em>
					</div>
					<div class="list">
						<span><i class="freight-icon"></i>运费：</span> <em class="price"
							id="freightPriceId"><font color="#FF6600"> ￥18.00</font></em>
					</div>
					<div class="list" id="showCouponPrice" style="display: none;">
						<span>商品优惠：</span><em style="display: none;" class="price"
							id="couponPriceId">-￥0.00</em>
					</div>
					<div class="list" id="showFreeFreight" style="display: none;">
						<span>运费优惠：</span><em class="price" id="freeFreightPriceId">
							-￥0.00</em>
					</div>
					<div class="list" id="showGiftCardPrice" style="display: none;">
						<span>新巴巴卡/E卡/图书卡：</span><em class="price" id="giftCardPriceId">-￥0.00</em>
					</div>
					<div class="list" id="showUsedJdBean" style="display: none;">
						<span>新巴巴豆：</span><em class="price" id="usedJdBeanId">-￥0.00</em>
					</div>
					<div class="list" id="showUsedOrderBalance" style="display: none;">
						<span>余额：</span><em class="price" id="usedBalanceId">-￥0.00</em>
					</div>
					<div class="list" id="showPeriodFee" style="display: none;">
						<span>分期手续费(由分期银行收取)：</span><em class="price" id="periodFee">￥0.00</em>
					</div>
					<div class="list">
						<span>应付总额：</span> <em class="price" id="sumPayPriceId">￥361.80</em>
					</div>
				</div>
				<div class="clr"></div>
			</div>
			<div class="hr"></div>
			<!-- 配送地址确认 -->
			<div class="trade-foot">
				<div class="consignee-foot">
					<p>寄送至： 北京 海淀区 西三旗 XXXXXXXXXXXXXXXXX</p>
					<p>收货人：范冰冰 158****8888</p>
				</div>
				<div id="checkout-floatbar" class="group">
					<div class="ui-ceilinglamp checkout-buttons">
						<div class="sticky-wrap">
							<div class="inner">
								<input type="submit" class="checkout-submit" value="提交订单" /> <span
									class="total">应付总额：<strong id="payPrice">￥361.80</strong>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 我要反馈 -->
			<div id="backpanel">
				<div style="right: 139.5px;" id="backpanel-inner" class="hide">
					<div class="bp-item bp-item-survey">
						<a href="javascript:;" class="survey">我要反馈</a>
					</div>
					<div class="bp-item bp-item-backtop" data-top="0">
						<a href="#none" class="backtop" target="_self">返回顶部</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>
	<!-- /main -->
	<jsp:include page="commons/footer.jsp" />
</body>
</html>