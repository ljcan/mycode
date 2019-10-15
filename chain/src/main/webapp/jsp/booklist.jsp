<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'rightFrame.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="./js/jquery.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
	$("#sub").click(function(){
	var indexpages=$("#txt").val();
	alert(indexpages);
	if(indexpages!=""){
	indexpages=parseInt(indexpages);
	$.get("book.spring?indexpages="+indexpages,function(data,status){alert(data+" "+status);});
	
	}else{
	alert('请输入跳转的页数');
	}
	});
	});
	</script>
	<script type="text/javascript" src="./js/jquery.min.js"></script>
	<script type="text/javascript">
	 $(document).ready(function(){
	    $("#btn").click(function(){
	    alert('点击');
	      $.post("insertCar.spring",{'uname':'刘军强','bname':$(#bname).val(),'bprice':$(#bprice),'bauthor':$(#bauthor)});
	 	 alert('成功加入购物车');
	 });
	 });
	</script>

  </head>
  
  <body>
    <table border="1" align="center">
    <tr>
    <th width="30%" align="center">书名</th>
    <th width="30%" align="center">定价</th>
    <th width="30%" align="center">作者</th>
    <th width="30%" align="center">求购</th>
    </tr>
    <c:forEach items="${currentlist}" var="book">
    <tr>
    <td height="30%" align="center">${book.bname}<input type="hidden" id="bname" value="${book.bname}"></td>
    <td height="30%" align="center">${book.bprice}<input type="hidden" id="bprice" value="${book.bprice}"></td>
    <td height="30%" align="center">${book.bauthor}<input type="hidden" id="bauthor" value="${book.bauthor}"></td>
    <td height="30%" align="center"><button id="btn" type="button">加入购物车</button></td>
     </tr> 
    </c:forEach>
    </table>
    <%!
      int pages=0;		//默认从第一页开始
      int currentpages=1;		//设置当前页默认为1
     %>
     <%
     pages=Integer.parseInt((String)request.getParameter("pages"));
      %>
    <%
    if(pages>1&&currentpages>1&&currentpages!=pages){
     %>
     <a href="book.spring?indexpages=<%=currentpages=1%>" align="center">首页</a>
     <a href="book.spring?indexpages=<%=currentpages=currentpages-1%>" align="center">上一页</a>
     <a href="book.spring?indexpages=<%=currentpages=currentpages+1%>" align="center">下一页</a>
     <%
     }
      %>
      <%
      if(pages>1&&currentpages==pages){
       %>
     <a href="book.spring?indexpages=<%=1%>" align="center">首页</a>
     <a href="book.spring?indexpages=<%=currentpages=currentpages-1%>" align="center">上一页</a>
     <%
     }
      %>
     <%
     if(pages>1&&currentpages==1){
      %>
     <a href="book.spring?indexpages=<%=currentpages=currentpages+1%>" align="center">下一页</a>
     <%
     }
     //具体跳转到某一页
      %>
      
      <input type="text" name="pagecounts" id="txt" size="2"/>
      <button id="sub" type="button">查询</button>
      
   
  </body>
</html>
