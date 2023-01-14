<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<%@ include file="../../pages/common/base_css.jsp"%>
<%--	引入js文件--%>
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
<%--	給修改商品数量绑定onchange事件--%>
	<script>
		$(function () {
			$(".updateCount").change(function () {
				// 获取商品姓名
				var itemName=$(this).parent().parent().find("td:first").text();
				var itemId=$(this).attr('itemId');
				var count=this.value;
				if(confirm("是否要将"+itemName+"的数量修改为"+count+"?")){
					location.href="http://localhost:8080/BookStore/cartServlet?action=updateItemById&id="+itemId+"&count="+count;
				}else{
					// 点击取消.defaultvalue表示默认以前的value值
					this.value=this.defaultValue;
				}
			});
		});

	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../user/static/img/logo.gif" >
			<span class="wel_word">购物车</span>
	    	<%@ include file="../../pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${empty sessionScope.cart or empty sessionScope.cart.items}">
			<tr>
				<td>快去买东西吧</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			</c:if>

			<c:if test="${not empty sessionScope.cart and not empty sessionScope.cart.items}">
					<c:forEach items="${sessionScope.cart.items}" var="entry">
						<tr>
						<td>${entry.value.name}</td>
<%--					此处应该把商品的数量设置为可以修改的表单或者是输入框--%>
						<td>
							<input type="text" class="updateCount" style="width: 80px" itemid="${entry.value.id}" value="${entry.value.count}">
						</td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
						</tr>
					</c:forEach>
			</c:if>
		</table>
		<c:if test="${not empty sessionScope.cart and not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalItemsPrice}</span>元</span>
				<span class="cart_span"><a href="cartServlet?action=clearCart">清空购物车</a></span>
				<span class="cart_span"><a href="pages/cart/checkout.jsp">去结账</a></span>
			</div>
		</c:if>
	</div>
	
	<%@ include file="../../pages/common/footer.jsp"%>
</body>
</html>