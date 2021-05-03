<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Custom styles for this template -->
<link href="css/signin.css" rel="stylesheet">

<div class="container">
	<form id="loginForm" name="loginForm" class="form-signin">
		<h2 class="form-signin-heading">로그인</h2>
		
		<label for="memberId" class="sr-only">ID</label>
		<input type="text" id="id" name="id" class="form-control" placeholder="아이디를 입력하세요." required autofocus>
		
		<label for="memberPw" class="sr-only">Password</label>
		<input type="password" id="password" name="password" class="form-control" placeholder="비밀번호를 입력하세요." required>
		
		<button class="btn btn-lg btn-primary btn-block" type="button" onclick="javascript:fn_signIn();">로그인</button>
		<button class="btn btn-lg btn-info btn-block" type="button" onclick="javascript:fn_signUp();">회원가입</button>
		<button class="btn btn-lg btn-warning btn-block" type="button" onclick="javascript:fn_findId();">아이디/비밀번호 찾기</button>
	</form>
</div><!-- /container -->

<script>
	var submitYN = false;

	<%-- 로그인 --%>
	function fn_signIn(){

		if(document.getElementById("id").value.length < 1){
			alert("아이디를 입력하세요.");
			document.getElementById("id").focus()
			return;
		}
		
		if(document.getElementById("password").value.length < 1){
			alert("비밀번호를 입력하세요.");
			document.getElementById("password").focus()
			return;
		}
		
		if(!submitYN){
			submitYN = true;
			
			$.ajax({
				 url :"/loginAction.do"
				,type:"post"
				,data:$("#loginForm").serialize()
				,success:function(data){
					if(data != "N"){
						alert(data + "회원님, 환영합니다");
						location.href="/index.do"
					}else{
						alert("회원가입을 해주세요");
					}
				}
			    ,error: function(xhr, status, error){
			    	alert("에러발생");
			    }
			    ,complete : function() {
			    	submitYN = false;
			    }
			    
			});
			
		}
		
	}

	<%-- 멤버등록화면으로 이동 --%>
	function fn_signUp(){
		location.href = "/fwdMemberReg.do";
	}
	
	function fn_findId(){
		location.href = "/findMember.do";
	}

</script>