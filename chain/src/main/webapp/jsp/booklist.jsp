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
	alert('��������ת��ҳ��');
	}
	});
	});
	</script>
	<script type="text/javascript" src="./js/jquery.min.js"></script>
	<script type="text/javascript">
	 $(document).ready(function(){
	    $("#btn").click(function(){
	    alert('���');
	      $.post("insertCar.spring",{'uname':'����ǿ','bname':$(#bname).val(),'bprice':$(#bprice),'bauthor':$(#bauthor)});
	 	 alert('�ɹ����빺�ﳵ');
	 });
	 });
	</script>

  </head>
  
  <body>
    <table border="1" align="center">
    <tr>
    <th width="30%" align="center">����</th>
    <th width="30%" align="center">����</th>
    <th width="30%" align="center">����</th>
    <th width="30%" align="center">��</th>
    </tr>
    <c:forEach items="${currentlist}" var="book">
    <tr>
    <td height="30%" align="center">${book.bname}<input type="hidden" id="bname" value="${book.bname}"></td>
    <td height="30%" align="center">${book.bprice}<input type="hidden" id="bprice" value="${book.bprice}"></td>
    <td height="30%" align="center">${book.bauthor}<input type="hidden" id="bauthor" value="${book.bauthor}"></td>
    <td height="30%" align="center"><button id="btn" type="button">���빺�ﳵ</button></td>
     </tr> 
    </c:forEach>
    </table>
    <%!
      int pages=0;		//Ĭ�ϴӵ�һҳ��ʼ
      int currentpages=1;		//���õ�ǰҳĬ��Ϊ1
     %>
     <%
     pages=Integer.parseInt((String)request.getParameter("pages"));
      %>
    <%
    if(pages>1&&currentpages>1&&currentpages!=pages){
     %>
     <a href="book.spring?indexpages=<%=currentpages=1%>" align="center">��ҳ</a>
     <a href="book.spring?indexpages=<%=currentpages=currentpages-1%>" align="center">��һҳ</a>
     <a href="book.spring?indexpages=<%=currentpages=currentpages+1%>" align="center">��һҳ</a>
     <%
     }
      %>
      <%
      if(pages>1&&currentpages==pages){
       %>
     <a href="book.spring?indexpages=<%=1%>" align="center">��ҳ</a>
     <a href="book.spring?indexpages=<%=currentpages=currentpages-1%>" align="center">��һҳ</a>
     <%
     }
      %>
     <%
     if(pages>1&&currentpages==1){
      %>
     <a href="book.spring?indexpages=<%=currentpages=currentpages+1%>" align="center">��һҳ</a>
     <%
     }
     //������ת��ĳһҳ
      %>
      
      <input type="text" name="pagecounts" id="txt" size="2"/>
      <button id="sub" type="button">��ѯ</button>
      
   
  </body>
</html>
