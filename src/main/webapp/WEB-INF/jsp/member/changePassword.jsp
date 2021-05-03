<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="css/signin.css" rel="stylesheet">

<div class="container">
	<form id="changePwForm" name="changePwForm" class="form-pw">
		<h2 class="form-pw-heading">비밀번호 변경</h2>
		
		<label for="password" class="sr-only">ID</label>
		<input type="password" id="password" name="password" class="form-control" placeholder="변경할 비밀번호를 입력하세요." required autofocus>
		
		<label for="memberPw" class="sr-only">Password</label>
		<input type="password" id="password" name="password" class="form-control" placeholder="비밀번호를 다시 한번 입력하세요." required>
		
		<button class="btn btn-lg btn-primary btn-block" type="button" onclick="javascript:fn_changePw();">로그인</button>
	</form>
</div><!-- /container -->
