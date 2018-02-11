<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>商品搜索 - 新巴巴运动商城</title>
<link rel="stylesheet" type="text/css" href="/css/base.css" media="all" />
<link type="text/css" rel="stylesheet" href="/css/search.css">
<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
<script type="text/javascript">
var price = '${price}' ;
var brandId = '${brandId}' ;
//过滤品牌 id = 品牌ID
function fqBrand(id){
	if('' != price){
		window.location.href = "/search?keyword=${keyword}" + "&brandId=" + id + "&price=" + price;
	}else{
		window.location.href = "/search?keyword=${keyword}" + "&brandId=" + id;
	}
}
//过滤条件  价格 id == 价格区间
function fqPrice(id){
	if('' != brandId){
		window.location.href = "/search?keyword=${keyword}" + "&brandId=" + brandId + "&price=" + id;
	}else{
		window.location.href = "/search?keyword=${keyword}" + "&price=" + id;
	}
}
</script>

</head>
<body>
<!-- header start -->
<jsp:include page="commons/header.jsp" />
<!-- header end -->
<div id="J_searchWrap" class="w">
<div id="J_crumbsBar" class="crumbs-bar">
	<div id="J_giftBox" class="cb-gift">
		<div class="cb-gift-trigger">
			<i class="ico-gift"></i>
			<span class="txt">
				<a href="javascript:;" >新巴巴帮您选礼物</a>
			</span>
		</div>
		<div class="cb-gift-main">
			<div class="cb-gift-filter"><div class="title">送给谁：</div><ul class="cb-gift-filter-list clearfix"><li><a href="javascript:;" title="爸爸"><i class="check"></i>爸爸</a></li><li><a href="javascript:;" title="妈妈"><i class="check"></i>妈妈</a></li><li><a href="javascript:;" title="丈夫"><i class="check"></i>丈夫</a></li><li><a href="javascript:;" title="妻子"><i class="check"></i>妻子</a></li><li><a href="javascript:;" title="男朋友"><i class="check"></i>男朋友</a></li><li><a href="javascript:;" title="女朋友"><i class="check"></i>女朋友</a></li><li><a href="javascript:;" title="老人"><i class="check"></i>老人</a></li><li><a href="javascript:;" title="小孩"><i class="check"></i>小孩</a></li><li><a href="javascript:;" title="领导"><i class="check"></i>领导</a></li></ul></div>
			<div class="cb-gift-filter"><div class="title">啥时送：</div><ul class="cb-gift-filter-list clearfix"><li><a href="javascript:;" title="春节"><i class="check"></i>春节</a></li><li><a href="javascript:;" title="中秋"><i class="check"></i>中秋</a></li><li><a href="javascript:;" title="新年"><i class="check"></i>新年</a></li><li><a href="javascript:;" title="情人节"><i class="check"></i>情人节</a></li><li><a href="javascript:;" title="生日"><i class="check"></i>生日</a></li></ul></div>
			<div class="gift-btnbox"><a href="javascript:;" class="btn btn-primary btn-XL">搜索</a></div>
		</div>
	</div>
	<div class="crumbs-nav">
		<div class="crumbs-nav-main clearfix">
			<div class="crumbs-nav-item">
				<div class="crumbs-first">
				<a href="javascript:;">全部结果</a>
				</div>
			</div>
			<i class="crumbs-arrow">&gt;</i>
			<div class="crumbs-nav-item">
				<strong class="search-key">"瑜伽服"</strong>
			</div>
			<c:if test="${fn:length(map) != 0 }">
			<div class="sl-b-selected J_brandSelected">
				<span class="crumbs-arrow">已选条件：</span>
					<c:forEach items="${map }" var="m">
						<a title="依琦莲（yiqilian）"  href="javascript:;" class="crumb-select-item">
							<b>${m.key }：</b><em>${m.value }</em><i></i>
						</a>
					</c:forEach>
			</div>
			</c:if>
		</div>
	</div>
</div>
<div id="J_boxSearch" style="display:none;"></div>
<div id="J_container" class="container">
<div id="J_selector" class="selector">
<c:if test="${empty brandId }">
<div class="J_selectorLine s-brand">
	<div class="sl-wrap">
		<div class="sl-key"><strong>品牌：</strong></div>
		<div class="sl-value">
			<div class="sl-v-list">
				<ul class="J_valueList v-fixed">
				<c:forEach items="${brands }" var="brand">
					<li id="brand-38118" data-initial="j" style="display:block;">
						<a href="javascript:;" onclick="fqBrand('${brand.id }')" title="${brand.name }"><i></i>${brand.name }</a>
					</li>
				</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</div>
</c:if>
<c:if test="${empty price }">
<div id="J_selectorPrice" class="J_selectorLine s-line">
	<div class="sl-wrap">
		<div class="sl-key"><span>价格：</span></div>
		<div class="sl-value">
			<div class="sl-v-list">
				<ul class="J_valueList">
					<li>
						<a href="javascript:;" onclick="fqPrice('0-99')"><i></i>0-99</a>
					</li>
					<li>
						<a href="javascript:;" onclick="fqPrice('100-299')"><i></i>100-299</a>
					</li>
					<li>
						<a href="javascript:;" onclick="fqPrice('300-599')"><i></i>300-599</a>
					</li>
					<li>
						<a href="javascript:;" onclick="fqPrice('600-999')"><i></i>600-999</a>
					</li>
					<li>
						<a href="javascript:;" onclick="fqPrice('1000-1599')"><i></i>1000-1599</a>
					</li>
					<li>
						<a href="javascript:;" onclick="fqPrice('1600')"><i></i>1600以上</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
</c:if>
<div class="J_selectorLine s-line">
	<div class="sl-wrap">
		<div class="sl-key">
			<span>材质：</span>
		</div>
		<div class="sl-value">
			<div class="sl-v-list">
				<ul class="J_valueList">
					<li>
						<a href="javascript:;"><i></i>涤纶</a>
					</li>
					<li>
						<a href="javascript:;"><i></i>莫代尔</a>
					</li>
					<li>
						<a href="javascript:;"><i></i>PVC</a>
					</li>
					<li>
						<a href="javascript:;"><i></i>TPE</a>
					</li>
					<li>
						<a href="javascript:;"><i></i>锦纶</a>
					</li>
					<li>
						<a href="javascript:;"><i></i>橡胶</a>
					</li>
					<li>
						<a href="javascript:;"><i></i>竹纤维</a>
					</li>
					<li>
						<a href="javascript:;"><i></i>环保人棉</a>
					</li>
					<li>
						<a href="javascript:;"><i></i>棉麻</a>
					</li>
					<li>
						<a href="javascript:;"><i></i>蚕丝</a>
					</li>
					<li>
						<a href="javascript:;"><i></i>牛奶丝</a>
					</li>
					<li>
						<a href="javascript:;"><i></i>莱卡棉</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="J_selectorLine s-line">
		<div class="sl-wrap">
			<div class="sl-key">
				<span>类别：</span>
			</div>
			<div class="sl-value">
				<div class="sl-v-list">
					<ul class="J_valueList">
						<li>
							<a href="javascript:;" onclick=""><i></i>瑜伽垫</a>
						</li>
						<li>
							<a href="javascript:;" onclick=""><i></i>瑜伽服</a>
						</li>
						<li>
							<a href="javascript:;" onclick=""><i></i>舞蹈鞋服</a>
						</li>
						<li>
							<a href="javascript:;" onclick=""><i></i>其它</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	<div id="J_selectorSenior" class="J_selectorLine s-line s-senior">
	<div class="sl-wrap">
		<div class="sl-key">
			<span>高级选项：</span>
		</div>
		<div class="sl-value">
			<div class="sl-v-tab">
				<div class="sl-tab-trigger clearfix">
					<a class="trig-item" href="javascript:;"><span class="text">适用人群</span><i class="arrow"></i></a>
					<a class="trig-item" href="javascript:;"><span class="text">其他分类</span><i class="arrow"></i></a>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
<div id="J_main" class="g-main2 left2right">
	<div class="m-list">
		<div class="ml-wrap">
			<div id="J_filter" class="filter">
				<div class="f-line top">
					<div class="f-sort"><a href="javascript:;" class="curr">综合排序<i></i></a><a href="javascript:;" class="">销量<i></i></a><a href="javascript:;" class="">价格<i></i></a><a href="javascript:;" class="" >评论数<i></i></a><a href="javascript:;" class="">新品<i></i></a></div>
					<div class="f-search">
						<input type="text" value="在结果中搜索" class="input-txt">
						<a data-type="all" data-field="exp_key" class="btn btn-default" data-url="search?keyword=%E7%91%9C%E4%BC%BD%E6%9C%8D&amp;enc=utf-8&amp;qrst=1&amp;rt=1&amp;stop=1&amp;vt=2&amp;exp_key=" href="javascript:;">确定</a>
					</div>
					<div id="J_topPage" class="f-pager"><span class="fp-text"><b>1</b><em>/</em><i>100</i></span><a class="fp-prev disabled" href="javascript:;">&lt;</a><a class="fp-next" onclick="SEARCH.page(3)" href="javascript:;" title="使用方向键右键也可翻到下一页哦！">&gt;</a></div>
					<div class="f-result-sum">共<span id="J_resCount" class="num">5.6万</span>件商品</div>
					<span class="clr"></span>
				</div>
				<div class="f-line">
					<div class="f-store">
						<div class="fs-cell delivery-location">配送至</div>
						<div id="store-selector" class="">
							<div class="text">北京朝阳区三环以内<b></b></div>
							<div class="content"><div data-widget="tabs" class="m JD-stock"><div class="mt">    <ul class="tab">        <li data-index="0" data-widget="tab-item" class=""><a href="javascript:;" class="hover" title="北京"><em>北京</em><i></i></a></li>        <li data-index="1" data-widget="tab-item" style="" class=""><a href="javascript:;" class="" title="朝阳区"><em>朝阳区</em><i></i></a></li>        <li data-index="2" data-widget="tab-item" style="" class="curr"><a href="javascript:;" class="" title="三环以内"><em>三环以内</em><i></i></a></li>        <li data-index="3" data-widget="tab-item" style="display:none;"><a href="javascript:;" class=""><em>请选择</em><i></i></a></li>    </ul>    <div class="stock-line"></div></div><div class="mc" data-area="0" data-widget="tab-content" style="display: none;">    <ul class="area-list">       <li><a href="javascript:;" data-value="1">北京</a></li><li><a href="javascript:;" data-value="2">上海</a></li><li><a href="javascript:;" data-value="3">天津</a></li><li><a href="javascript:;" data-value="4">重庆</a></li><li><a href="javascript:;" data-value="5">河北</a></li><li><a href="javascript:;" data-value="6">山西</a></li><li><a href="javascript:;" data-value="7">河南</a></li><li><a href="javascript:;" data-value="8">辽宁</a></li><li><a href="javascript:;" data-value="9">吉林</a></li><li><a href="javascript:;" data-value="10">黑龙江</a></li><li><a href="javascript:;" data-value="11">内蒙古</a></li><li><a href="javascript:;" data-value="12">江苏</a></li><li><a href="javascript:;" data-value="13">山东</a></li><li><a href="javascript:;" data-value="14">安徽</a></li><li><a href="javascript:;" data-value="15">浙江</a></li><li><a href="javascript:;" data-value="16">福建</a></li><li><a href="javascript:;" data-value="17">湖北</a></li><li><a href="javascript:;" data-value="18">湖南</a></li><li><a href="javascript:;" data-value="19">广东</a></li><li><a href="javascript:;" data-value="20">广西</a></li><li><a href="javascript:;" data-value="21">江西</a></li><li><a href="javascript:;" data-value="22">四川</a></li><li><a href="javascript:;" data-value="23">海南</a></li><li><a href="javascript:;" data-value="24">贵州</a></li><li><a href="javascript:;" data-value="25">云南</a></li><li><a href="javascript:;" data-value="26">西藏</a></li><li><a href="javascript:;" data-value="27">陕西</a></li><li><a href="javascript:;" data-value="28">甘肃</a></li><li><a href="javascript:;" data-value="29">青海</a></li><li><a href="javascript:;" data-value="30">宁夏</a></li><li><a href="javascript:;" data-value="31">新疆</a></li><li><a href="javascript:;" data-value="32">台湾</a></li><li><a href="javascript:;" data-value="42">香港</a></li><li><a href="javascript:;" data-value="43">澳门</a></li><li><a href="javascript:;" data-value="84">钓鱼岛</a></li>    </ul></div><div class="mc" data-area="1" data-widget="tab-content" style="display: none;"><ul class="area-list"><li><a href="javascript:;" data-value="72">朝阳区</a></li><li><a href="javascript:;" data-value="2800">海淀区</a></li><li><a href="javascript:;" data-value="2801">西城区</a></li><li><a href="javascript:;" data-value="2802">东城区</a></li><li><a href="javascript:;" data-value="2803">崇文区</a></li><li><a href="javascript:;" data-value="2804">宣武区</a></li><li><a href="javascript:;" data-value="2805">丰台区</a></li><li><a href="javascript:;" data-value="2806">石景山区</a></li><li><a href="javascript:;" data-value="2807">门头沟</a></li><li><a href="javascript:;" data-value="2808">房山区</a></li><li><a href="javascript:;" data-value="2809">通州区</a></li><li><a href="javascript:;" data-value="2810">大兴区</a></li><li><a href="javascript:;" data-value="2812">顺义区</a></li><li><a href="javascript:;" data-value="2814">怀柔区</a></li><li><a href="javascript:;" data-value="2816">密云区</a></li><li><a href="javascript:;" data-value="2901">昌平区</a></li><li><a href="javascript:;" data-value="2953">平谷区</a></li><li><a href="javascript:;" data-value="3065">延庆县</a></li></ul></div><div class="mc" data-area="2" data-widget="tab-content"><ul class="area-list"><li><a href="javascript:;" data-value="2799">三环以内</a></li><li><a href="javascript:;" data-value="4137">管庄</a></li><li><a href="javascript:;" data-value="4139">北苑</a></li><li><a href="javascript:;" data-value="4211">定福庄</a></li><li class="long-area"><a href="javascript:;" data-value="2819">三环到四环之间</a></li><li class="long-area"><a href="javascript:;" data-value="2839">四环到五环之间</a></li><li class="long-area"><a href="javascript:;" data-value="2840">五环到六环之间</a></li></ul></div><div class="mc" data-area="3" data-widget="tab-content"></div></div><span class="clr"></span></div>
							<div class="close"></div>
						</div>
					</div>
					<div id="J_feature" class="f-feature">
						<ul>
							<li><a data-field="wtype" data-val="1" class="" href="javascript:;" onclick="searchlog(1,0,0,43)"><i></i>京东配送</a></li>
							<li><a data-field="cod" data-val="1" class="" href="javascript:;" onclick="searchlog(1,0,0,34)"><i></i>货到付款</a></li>
							<li><a data-field="stock" data-val="1" class="" href="javascript:;" onclick="searchlog(1,0,0,41)"><i></i>仅显示有货</a></li>
							<li><a data-field="prom_type" data-val="0" class="" href="javascript:;" onclick="searchlog(1,0,0,38)"><i></i>促销商品</a></li>
						</ul>
					</div>
					<div id="J_viewType" class="f-type" data-ref="1">
						<a class="ft-item shop" href="javascript:;" data-value="3" onclick="searchlog(1,0,0,28)"><i class="i-shop"></i>店铺</a>
						<a class="ft-item grid selected" href="javascript:;" data-value="2" onclick="searchlog(1,0,0,45)"><i class="i-grid"></i>商品</a>
					</div>
					<span class="clr"></span>
				</div>
			</div>
<div id="J_goodsList" class="goods-list-v1 gl-type-1 J-goods-list">
	<ul class="gl-warp clearfix" data-tpl="1">
		<c:forEach items="${pagination.list }" var="product">
		<li data-sku="1711416562" class="gl-item">
			<div class="gl-i-wrap">
				<div class="p-img">
					<a href="javascript:;" onclick="window.open('/product/detail?id=${product.id}')" style="position: relative;">
						<img width="220" height="220" class="err-product"  src="${product.imgUrl}">
						<div id="gwd_float_curve_trigger" class="gwd_float_curve_trigger gwd_float_curve_up" style="left: 70px; top: 180px;">
							<div class="gwd_float_curve_wrapper">
							<span class="gwd_float_curve_trigger_icon" style="background-image:url(chrome-extension://dobbgecnokkloebjbcnjpgcopegjabpa/images/background_new.png)"></span>
							<span class="gwd_float_curve_trigger_hint">上涨</span><div class="gwd_float_curve_overlay">
							</div>
							</div>
						</div>
					</a>
						<div data-ico="1201" data-venid="14951"></div>
					</a>
				</div>
				<div class="p-price">
					<strong class="J_1711416562">
						<em>￥</em><i>${product.price }</i>
					</strong>						
					<div class="p-icons" id="J_pro_1711416562">
						<i class="icons" title="购买本商品送赠品">赠品</i>
						<i class="goods-icons-s1" title="该商品支持货到付款">货到付款</i>
					</div>
				</div>
				<div class="p-name p-name-type-2">
					<a  title="满129立减10,199减20优惠券,支持货到付款" href="javascript:;" onclick="window.open('http://localhost:8084/html/product/${product.id}.html')">
						<em>${product.name }</em>
					</a>
				</div>
				<div class="p-commit">
					<strong>已有<a id="J_comment_1711416562" target="_blank" href="javascript:;" onclick="">133</a>人评价</strong>
				</div>
				<div class="p-operate">
					<a class="p-o-btn contrast J_contrast" data-sku="1711416562" href="javascript:;" ><i></i>对比</a>
					<a class="p-o-btn focus J_focus" data-sku="1711416562" href="javascript:;" ><i></i>关注</a>
					<a class="p-o-btn addcart" data-stock-val="1" data-disable-notice="0" data-presale="0" href="javascript:;" data-parallel="0"><i></i>加入购物车</a>
				</div>
			</div>
		</li>
		</c:forEach>
	</ul>
	<span class="clr"></span>
</div>
<div class="page pb15">
	<div class="r inb_a page_b" style="float: right;">
		<c:forEach items="${pagination.pageView }" var="page">
			${page }
		</c:forEach>
	</div>
</div>		
</div>
</div>
	<div class="m-aside">
		<div class="aside-bar">
			<div id="J_promGoodsWrap_6" class="ab-goods" style="display: none;">
				<div class="mt"><h3>推广商品</h3></div>
				<div class="mc"></div>
			</div>
			<div id="J_recommendGoods" class="ab-goods" style="display: block;">
				<div class="mt"><h3>精品推荐</h3></div>
				<div class="mc">
					<ul class="clearfix">
						<li>  
							<div class="p-img">      
								<a  href="javascript:;" title="依琦莲新款瑜伽服套装 宽松大码女士瑜珈舞蹈服 健身服跳操服 8505长袖黑+红花裤子 L" style="position: relative;">
						     		<img width="160" height="160" data-img="1" src="/images/ad3.jpg" class="err-product">      
						     		<div id="gwd_float_curve_trigger" class="gwd_float_curve_trigger gwd_float_curve_up" style="left: 40px; top: 49px;">
						     		<div class="gwd_float_curve_wrapper">
						     		<span class="gwd_float_curve_trigger_icon" style="background-image:url(chrome-extension://dobbgecnokkloebjbcnjpgcopegjabpa/images/background_new.png)"></span>
						     		<span class="gwd_float_curve_trigger_hint">上涨</span>
						     		<div class="gwd_float_curve_overlay"></div>
						     		</div>
						     		</div>
						     	</a>  
						     </div>  
						     <div class="p-price">      
						     	<span>特价：</span>
						     	<strong><em>¥</em><i class="J-rec-p-1030027896">148.00</i></strong>  
						     </div>  
						     <div class="p-name">      
						     <a target="_blank" href="javascript:;" title="依琦莲新款瑜伽服套装 宽松大码女士瑜珈舞蹈服 健身服跳操服 8505长袖黑+红花裤子 L">          
						     	<em>依琦莲新款瑜伽服套装 宽松大码女士瑜珈舞蹈服 健身服跳操服 8505长袖黑+红花裤子 L</em>      
						     </a>  
						     </div>
						 </li>
						<li>  
							<div class="p-img">      
								<a  href="javascript:;" title="依琦莲新款瑜伽服套装 宽松大码女士瑜珈舞蹈服 健身服跳操服 8505长袖黑+红花裤子 L" style="position: relative;">
						     		<img width="160" height="160" data-img="1" src="/images/ad2.jpg" class="err-product">      
						     		<div id="gwd_float_curve_trigger" class="gwd_float_curve_trigger gwd_float_curve_up" style="left: 40px; top: 49px;">
						     		<div class="gwd_float_curve_wrapper">
						     		<span class="gwd_float_curve_trigger_icon" style="background-image:url(chrome-extension://dobbgecnokkloebjbcnjpgcopegjabpa/images/background_new.png)"></span>
						     		<span class="gwd_float_curve_trigger_hint">上涨</span>
						     		<div class="gwd_float_curve_overlay"></div>
						     		</div>
						     		</div>
						     	</a>  
						     </div>  
						     <div class="p-price">      
						     	<span>特价：</span>
						     	<strong><em>¥</em><i class="J-rec-p-1030027896">148.00</i></strong>  
						     </div>  
						     <div class="p-name">      
						     <a target="_blank" href="javascript:;" title="依琦莲新款瑜伽服套装 宽松大码女士瑜珈舞蹈服 健身服跳操服 8505长袖黑+红花裤子 L">          
						     	<em>依琦莲新款瑜伽服套装 宽松大码女士瑜珈舞蹈服 健身服跳操服 8505长袖黑+红花裤子 L</em>      
						     </a>  
						     </div>
						 </li>
						<li>  
							<div class="p-img">      
								<a  href="javascript:;" title="依琦莲新款瑜伽服套装 宽松大码女士瑜珈舞蹈服 健身服跳操服 8505长袖黑+红花裤子 L" style="position: relative;">
						     		<img width="160" height="160" data-img="1" src="/images/55237eb5Ndf2c9fcf(1).jpg" class="err-product">      
						     		<div id="gwd_float_curve_trigger" class="gwd_float_curve_trigger gwd_float_curve_up" style="left: 40px; top: 49px;">
						     		<div class="gwd_float_curve_wrapper">
						     		<span class="gwd_float_curve_trigger_icon" style="background-image:url(chrome-extension://dobbgecnokkloebjbcnjpgcopegjabpa/images/background_new.png)"></span>
						     		<span class="gwd_float_curve_trigger_hint">上涨</span>
						     		<div class="gwd_float_curve_overlay"></div>
						     		</div>
						     		</div>
						     	</a>  
						     </div>  
						     <div class="p-price">      
						     	<span>特价：</span>
						     	<strong><em>¥</em><i class="J-rec-p-1030027896">148.00</i></strong>  
						     </div>  
						     <div class="p-name">      
						     <a target="_blank" href="javascript:;" title="依琦莲新款瑜伽服套装 宽松大码女士瑜珈舞蹈服 健身服跳操服 8505长袖黑+红花裤子 L">          
						     	<em>依琦莲新款瑜伽服套装 宽松大码女士瑜珈舞蹈服 健身服跳操服 8505长袖黑+红花裤子 L</em>      
						     </a>  
						     </div>
						 </li>
						 						<li>  
							<div class="p-img">      
								<a  href="javascript:;" title="依琦莲新款瑜伽服套装 宽松大码女士瑜珈舞蹈服 健身服跳操服 8505长袖黑+红花裤子 L" style="position: relative;">
						     		<img width="160" height="160" data-img="1" src="/images/ad4.jpg" class="err-product">      
						     		<div id="gwd_float_curve_trigger" class="gwd_float_curve_trigger gwd_float_curve_up" style="left: 40px; top: 49px;">
						     		<div class="gwd_float_curve_wrapper">
						     		<span class="gwd_float_curve_trigger_icon" style="background-image:url(chrome-extension://dobbgecnokkloebjbcnjpgcopegjabpa/images/background_new.png)"></span>
						     		<span class="gwd_float_curve_trigger_hint">上涨</span>
						     		<div class="gwd_float_curve_overlay"></div>
						     		</div>
						     		</div>
						     	</a>  
						     </div>  
						     <div class="p-price">      
						     	<span>特价：</span>
						     	<strong><em>¥</em><i class="J-rec-p-1030027896">148.00</i></strong>  
						     </div>  
						     <div class="p-name">      
						     <a target="_blank" href="javascript:;" title="依琦莲新款瑜伽服套装 宽松大码女士瑜珈舞蹈服 健身服跳操服 8505长袖黑+红花裤子 L">          
						     	<em>依琦莲新款瑜伽服套装 宽松大码女士瑜珈舞蹈服 健身服跳操服 8505长袖黑+红花裤子 L</em>      
						     </a>  
						     </div>
						 </li>
						 						<li>  
							<div class="p-img">      
								<a  href="javascript:;" title="依琦莲新款瑜伽服套装 宽松大码女士瑜珈舞蹈服 健身服跳操服 8505长袖黑+红花裤子 L" style="position: relative;">
						     		<img width="160" height="160" data-img="1" src="/images/ad2.jpg" class="err-product">      
						     		<div id="gwd_float_curve_trigger" class="gwd_float_curve_trigger gwd_float_curve_up" style="left: 40px; top: 49px;">
						     		<div class="gwd_float_curve_wrapper">
						     		<span class="gwd_float_curve_trigger_icon" style="background-image:url(chrome-extension://dobbgecnokkloebjbcnjpgcopegjabpa/images/background_new.png)"></span>
						     		<span class="gwd_float_curve_trigger_hint">上涨</span>
						     		<div class="gwd_float_curve_overlay"></div>
						     		</div>
						     		</div>
						     	</a>  
						     </div>  
						     <div class="p-price">      
						     	<span>特价：</span>
						     	<strong><em>¥</em><i class="J-rec-p-1030027896">148.00</i></strong>  
						     </div>  
						     <div class="p-name">      
						     <a target="_blank" href="javascript:;" title="依琦莲新款瑜伽服套装 宽松大码女士瑜珈舞蹈服 健身服跳操服 8505长袖黑+红花裤子 L">          
						     	<em>依琦莲新款瑜伽服套装 宽松大码女士瑜珈舞蹈服 健身服跳操服 8505长袖黑+红花裤子 L</em>      
						     </a>  
						     </div>
						 </li>
						 						<li>  
							<div class="p-img">      
								<a  href="javascript:;" title="依琦莲新款瑜伽服套装 宽松大码女士瑜珈舞蹈服 健身服跳操服 8505长袖黑+红花裤子 L" style="position: relative;">
						     		<img width="160" height="160" data-img="1" src="/images/ad1.jpg" class="err-product">      
						     		<div id="gwd_float_curve_trigger" class="gwd_float_curve_trigger gwd_float_curve_up" style="left: 40px; top: 49px;">
						     		<div class="gwd_float_curve_wrapper">
						     		<span class="gwd_float_curve_trigger_icon" style="background-image:url(chrome-extension://dobbgecnokkloebjbcnjpgcopegjabpa/images/background_new.png)"></span>
						     		<span class="gwd_float_curve_trigger_hint">上涨</span>
						     		<div class="gwd_float_curve_overlay"></div>
						     		</div>
						     		</div>
						     	</a>  
						     </div>  
						     <div class="p-price">      
						     	<span>特价：</span>
						     	<strong><em>¥</em><i class="J-rec-p-1030027896">148.00</i></strong>  
						     </div>  
						     <div class="p-name">      
						     <a target="_blank" href="javascript:;" title="依琦莲新款瑜伽服套装 宽松大码女士瑜珈舞蹈服 健身服跳操服 8505长袖黑+红花裤子 L">          
						     	<em>依琦莲新款瑜伽服套装 宽松大码女士瑜珈舞蹈服 健身服跳操服 8505长袖黑+红花裤子 L</em>      
						     </a>  
						     </div>
						 </li>
						 
					</ul>
				</div>
		</div>
</div>
<!-- footer start -->
<jsp:include page="commons/footer.jsp" />
<!-- footer end -->
</body>
</html>