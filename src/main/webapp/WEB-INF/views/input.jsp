<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 引入SpringMvc的表单标签 -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 引入JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
	<!--  -->
	<form action="testConversionServiceConverer" method="post">
		<!-- lastName-email-gender-department.id
			例如:fc-fc@qq.com-0-105
		 -->
		Employee:<input type="text" name="employee"/>
		<input type="submit" value="Sub"/>
	</form>
	<br /><br />
	<!-- 1. 为什么使用SpringMvc的form标签呢?
			可以更快速的开发出表单页面,而且更方便的进行表单值的回显
		 2. 注意:
		 	SpringMvc要求,想要显示该页面的话,必须在域对象中存在于表单中path对应的属性
		 	如果没有指定该属性,则默认从Request域对象中读取command的表单bean
		 	如果该属性值也不存在,则会发生错误
		 	同时需要通过modelAttribute属性指定绑定的模型属性
	 -->
	 <form:form action="${pageContext.request.contextPath }/emp" method="post" modelAttribute="employee">
	 
	 	<!-- path:属性对应HTML表单标签的name属性值 -->
	 	<c:if test="${empty employee.id }">
	 		LastName:<form:input path="lastName" />&nbsp;<form:errors path="lastName"></form:errors>
	 		<br />
	 	</c:if>
	 	<c:if test="${!empty employee.id }">
	 		<form:hidden path="id" />
	 		<input type="hidden" name="_method" value="PUT"/>
	 	</c:if>
	 	Email:<form:input path="email" />&nbsp;<form:errors path="email"></form:errors>
	 	<br />
	 	<%
	 		Map<String,String> genders =new HashMap();
	 		genders.put("1","Male");
	 		genders.put("0","Female");
	 		request.setAttribute("genders", genders);
	 	%>
	 	<!-- items:可以放一个集合结合,代码会自动识别,将集合的key作为实际的值,
	 			将value作为显示的值
	 		 itemLabel:显示的值
	 		 itemValue:实际的值
	 	 -->
	 	Gender:<form:radiobuttons path="gender" items="${genders }"/>
	 	<br />
	 	Department:<form:select path="department.id" items="${departments }"
	 		itemLabel="departmentName" itemValue="id"></form:select>
		<br />	 	
		<!-- 
			1.	数据类型转换
			2.	数据类型格式化
			3.	数据校验
				1.如何校验?注解?
					①使用JSR303验证标准
					②加入hibernate Validator验证框架
					③在SpringMvc配置文件中添加<mvc:annotation-driven/>
					④需要在bean的属性上添加对应的注解
					⑤ 在目标方法bean类型的前面添加@Valid注解
				2.验证出错转向到哪一个页面
					① 需要校验的Bean对象和其绑定结果对象或错误对象是成对出现的,他们之间不允许声明其他形参
				3.错误消息?如何显示,如何把错误消息进行国际化
					
		 -->
	 	Birth:<form:input path="birth" />&nbsp;<form:errors path="birth"></form:errors>
	 	<br />
	 	Salary:<form:input path="salary" />
	 	<br />
	 	
	 	<input  type="submit" value="Submit"/>
	 </form:form>
</body>
</html> 




















