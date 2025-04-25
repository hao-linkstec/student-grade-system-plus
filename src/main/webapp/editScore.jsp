<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.example.dao.ScoreDao, com.example.dto.ScoreDto" %>
<%
    int scoreId = Integer.parseInt(request.getParameter("scoreId"));
    ScoreDao dao = new ScoreDao();
    ScoreDto dto = dao.getScoreById(scoreId);
%>
<html>
<head>
	<title>成績修正</title>
	<style>
        .input-wrapper {max-width: 300px; display: flex; justify-content: space-between; margin-bottom: 15px;}
        .input-text, .input-btn-wrapper {width: 220px;}
        .input-text:read-only {background-color: #D3D3D3;}
        .input-label {width: 50px;}
    </style>
</head>
<body>
<div style="max-width: 700px; padding: 20px 30px 0;">
	<p style="border-bottom: 1px solid #000;"><%= dto.getStuName() %>個人スコア編集</p>
	<div style="margin-top: 20px; padding-left: 60px;">
		<form action="updateScore" method="post" onSubmit="return confirm('変更します。よろしいですか？')">
		    <input type="hidden" name="scoreId" value="<%= dto.getScoreId() %>"/>
		    <div>
		    	<div class="input-wrapper">
		    		<span class="input-label">氏名</span>
		    		<input type="text" class="input-text" value="<%= dto.getStuName() %>" readonly/>
		    	</div>
		    	<div class="input-wrapper">
		    		<span class="input-label">成績</span>
		    		<input type="number" name="score" class="input-text" value="<%= dto.getScore() %>" min="0" max="100" style="text-align: right;" required/>
		    	</div>
		    	<div class="input-wrapper">
		    		<span class="input-label"></span>
		    		<div class="input-btn-wrapper">
		    			<input type="submit" value="保存" style="margin-right: 25px; padding: 0 15px;"/>
		    			<button type="button" style="padding: 0 15px;" onclick="location.href='listScore'">キャンセル</button>
		    		</div>
		    	</div>
		    </div>
		</form>
	</div>
</div>
</body>
</html>