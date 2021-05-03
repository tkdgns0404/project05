<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="css/signin.css" rel="stylesheet">

<div class="container">
	<form id="findPwForm" name="findPwForm" class="form-signin">
		<h2 class="form-findId-heading">PASSWORD찾기</h2>
		
		<label for="id" class="sr-only">ID</label>
		<input type="text" id="id" name="id" class="form-control" placeholder="가입할 때 입력한 Id를 입력해주세요" required autofocus>
		
		<br>
		<button class="btn btn-lg btn-primary btn-block" type="button" onclick="javascript:fn_confirmId();">비밀번호 찾기</button>
		<button class="btn btn-lg btn-info btn-block" type="button" onclick="javascript:fn_main();">메인화면</button>
	</form>
</div><!-- /container -->
    
<script>	
	var submitYN = false;
	
	
	<%-- 로그인 --%>
	function fn_confirmId(){

		if(document.getElementById("id").value.length < 1){
			alert("아이디를 입력하세요.");
			document.getElementById("id").focus()
			return;
		}
		if(!submitYN){
			submitYN = true;
			
			$.ajax({
				 url :"/findPw.do"
				,type:"post"
				,data:$("#findPwForm").serialize()
				,success:function(data){
					if(data != "N"){
						alert(document.getElementById("id").value + "회원님의 비밀번호는 "+ data +"입니다.");
						location.href="/login.do"
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
	
	function fn_main(){
		location.href = "/index.do";
	}
	
</script>