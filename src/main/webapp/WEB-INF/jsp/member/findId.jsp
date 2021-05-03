<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="css/signin.css" rel="stylesheet">

<div class="container">
	<form id="findIdForm" name="findIdForm" class="form-signin">
		<h2 class="form-findId-heading">ID찾기</h2>
		
		<label for="name" class="sr-only">name</label>
		<input type="text" id="name" name="name" class="form-control" placeholder="가입할 때 입력한 이름을 입력해주세요." required autofocus>
		
		<label for="phoneNum" class="sr-only">phone number</label>
		<input type="text" id="phoneNum" name="phoneNum" class="form-control" placeholder="가입할 때 입력한 휴대폰번호를 입력해주세요." required autofocus>
		
		<br>
		<button class="btn btn-lg btn-primary btn-block" type="button" onclick="javascript:fn_confirmPhone();">아이디 찾기</button>
		<button class="btn btn-lg btn-info btn-block" type="button" onclick="javascript:fn_main();">메인화면</button>
		<button class="btn btn-lg btn-warning btn-block" type="button" onclick="javascript:fn_findPw();">비밀번호 찾기</button>
	</form>
</div><!-- /container -->
    
<script>	
	var submitYN = false;
	
	
	<%-- 로그인 --%>
	function fn_confirmPhone(){

		if(document.getElementById("phoneNum").value.length < 1){
			alert("휴대폰 번호를 입력하세요.");
			document.getElementById("phoneNum").focus()
			return;
		}
		if(!submitYN){
			submitYN = true;
			
			$.ajax({
				 url :"/findId.do"
				,type:"post"
				,data:$("#findIdForm").serialize()
				,success:function(data){
					if(data != "N"){''
						alert(document.getElementById("name").value+"님의 아이디는 " +data+"입니다.");
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
	
	function fn_findPw(){
		location.href = "/findMemberPw.do";
	}

	
</script>
	