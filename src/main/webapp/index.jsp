<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#testJson").click(function(){
			var url = this.href;
			var args = {};
			$.post(url, args, function(data){
				for(var i = 0; i < data.length; i++){
					var id = data[i].id;
					var lastName = data[i].lastName;
					alert(id + ": " + lastName);
				}
			});
			return false;
		});
	})
</script>
</head>
<body>
	<a href="emps">List All Employees</a>
	
	<br />
	<a href="testJson">Test JSON</a>
	<br /><br />
	<form action="testHttpMessageConverter" method="post" enctype="multipart/form-data">
		File:<input type="file" name="file"/>
		Desc:<input type="text" name="desc"/>
		<input type="submit" value="Submit"/>
	</form>
	<br />
	<a href="testResponseEntity">Test ResponseEntity</a>
	<br />
	<a href="i18n">I18N2  PAGE</a>
</body>
</html>