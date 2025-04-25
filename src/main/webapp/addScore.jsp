<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title>成績登録</title>
	<style>
        .input-wrapper {max-width: 300px; display: flex; justify-content: space-between; margin-bottom: 15px;}
        .input-text, #select-class, .input-btn-wrapper {width: 220px;}
        .input-label {width: 50px;}
    </style>
</head>
<body>
<div style="max-width: 700px; padding: 20px 30px 0;">
	<p style="border-bottom: 1px solid #000;">個人スコア新規登録</p>
	<div style="margin-top: 20px; padding-left: 60px;">
		<form action="addScore" method="post" onSubmit="return checkBeforeSubmit()">
		    <div>
		    	<div class="input-wrapper">
		    		<span class="input-label">名前</span>
		    		<input type="text" name="stuName" class="input-text input-text-name" value=""  maxlength="10" required/>
		    	</div>
		    	
		    	<div class="input-wrapper">
		    		<span class="input-label">クラス</span>
			    	<select name="className" id="select-class" required>
			    		<option value="" selected disabled></option>
			            <option value="Java">Java班</option>
			            <option value="C#">C#班</option>
			            <option value="PHP">PHP班</option>
			            <option value="C">C班</option>
			        </select>
			    </div>
		    	<div class="input-wrapper">
		    		<span class="input-label">成績</span>
		    		<input type="number" name="score" class="input-text" value="" min="0" max="100" style="text-align: right;" required/>
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

<script>
	function checkBeforeSubmit() {
		const disallowed = /[\p{Emoji_Presentation}\p{Extended_Pictographic}\p{S}\p{P}]/gu;
	  	if (disallowed.test( document.getElementsByClassName("input-text-name")[0].value)) {
	      	alert("名前に特殊文字が含まれています。");
	      	return false;
	  	} else {
		 	 return confirm('本当に登録していいですか？');
	  	}
	}
</script>
</body>
</html>