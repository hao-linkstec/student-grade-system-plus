<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, com.example.dto.ScoreDto" %>
<%
    List<ScoreDto> list = (List<ScoreDto>) request.getAttribute("scoreList");
	String searchedClass = (String) request.getAttribute("searchedClass");
	String searchedName = (String) request.getAttribute("searchedName");
%>
<html>
<head>
    <title>成績一覧</title>
    <style>
        th, td {text-align: center; padding: 10px 25px;}
        td:nth-child(1) {text-align: center;}
        td:nth-child(4) {text-align: right; padding-right: 5px;}
        td:nth-child(5) {text-align: center;}
        td:nth-child(6) {padding: 7px 40px;}
        .red {color: red;}
    </style>
</head>
<body>
<div style="max-width: 700px; padding: 0 30px;">
	<div style="text-align: right; margin-top: 15px;">
		<button type="button" onclick="location.href='addScore.jsp'" style="width: 70px;">新規登録</button>
	</div>
	<div style="display: flex; align-items: bottom; padding-bottom: 3px; border-bottom: 1px solid #000;">
		<p>学生成績一覧</span>
	
		<form action="searchScore" method="get" style="margin: auto 0 auto auto;">
			<label>所属クラス</label>
			<select name="className" id="select-class" style="width: 120px; height: 22px; margin-right: 30px;">
				<option value="" selected disabled></option>
	            <option value="All">全クラス</option>
	            <option value="Java">Java</option>
	            <option value="C#">C#</option>
	            <option value="PHP">PHP</option>
	            <option value="C">C</option>
	        </select>
	        
		    <label>名前</label>
		    <input name="stuName" value="<%= searchedName %>" maxlength="10" style="margin-right: 50px"/>
		    
		    <input type="submit" value="検索" style="width: 70px;"/>
		</form>
	</div>
	<div style="margin-top: 30px; padding-left: 60px;">
		<table border="1" style="border-collapse: collapse">
		    <tr>
		        <th>番号</th>
		        <th>クラス</th>
		        <th>名前</th>
		        <th>成績</th>
		        <th>備考</th>
		        <th>操作</th>
		    </tr>
		    <%
		        int no = 1;
		        for (ScoreDto dto : list) {
		            int score = dto.getScore();
		            String grade = score >= 80 ? "優" : score >= 60 ? "可" : "不可";
		            String style = score < 60 ? "red" : "";
		    %>
		    <tr>
		        <td><%= no++ %></td>
		        <td><%= dto.getClassName() %></td>
		        <td><%= dto.getStuName() %></td>
		        <td class="<%= style %>"><%= score %></td>
        		<td class="<%= style %>"><%= grade %></td>
		        <td>
		            <a href="editScore.jsp?scoreId=<%= dto.getScoreId() %>">修正</a>
		            <a href="deleteScore?scoreId=<%= dto.getScoreId() %>" onclick="return confirm('本当に削除していいですか？')">削除</a>
		        </td>
		    </tr>
		    <% } %>
		</table>
		<p>合計：<%= list.size() %>人</p>
	</div>
</div>
<script>
	var searchedClass = "<%= searchedClass %>";
	document.getElementById("select-class").querySelector("option[value='" + searchedClass + "']").selected = true;
</script>
</body>
</html>