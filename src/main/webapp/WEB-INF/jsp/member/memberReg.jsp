<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	.input-group{
		margin-top: 25px;
	}
</style>
<div class="container">
	<div>
		<form id="insertForm" name="insertForm" method="post" enctype="multipart/form-data">
			<div class="input-group text-center">
				<img class="rg_i Q4LuWd" id="userImage" src="./defaultpic.png" data-atf="true" width="150" height="150" onerror="this.src='images/defaultpic.png'">
				<label class="btn btn-default">
				    사진 선택 <input type="file" id="userImageAttachFile" name="userImageAttachFile" hidden onchange="setThumbnail(event);" />
				</label>
			</div>
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">계정 권한</span>
				<select id="role" name="role" style="padding: .4em">
				<option>사용자</option>
				<option>관리자</option>
				</select>
			</div>
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">아이디</span>
				<input type="text" id="id" name="id" class="form-control" placeholder="아이디를 입력하세요." aria-describedby="basic-addon1">
			</div>
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">비밀번호</span>
				<input type="password" id="password" name="password" class="form-control" placeholder="비밀번호를 입력해 주세요." aria-describedby="basic-addon1">
			</div>
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">비밀번호 확인</span>
				<input type="password" id="password2" name="password2" class="form-control" placeholder="비밀번호를 한번 더 입력하세요." aria-describedby="basic-addon1">
			</div>
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">이름</span>
				<input type="text" id="name" name="name" class="form-control" placeholder="한글로 공백없이 입력해주세요." aria-describedby="basic-addon1">
			</div>
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">이메일</span>
				<input type="text" id="email" name="email" class="form-control" placeholder="이메일을 입력하세요." aria-describedby="basic-addon1">
			</div>
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">휴대전화</span>
				<input type="text" id="phoneNum" name="phoneNum" class="form-control" placeholder="휴대전화를 입력하세요." aria-describedby="basic-addon1">
			</div>
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">생년월일</span>
				<input type="text" id="birth" name="birth" class="form-control" placeholder="생년월일을 입력하세요. 월/일/년" aria-describedby="basic-addon1">
			</div>
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">주소</span>
				<input type="text" id="address" name="address" class="form-control" placeholder="주소를 선택하세요." aria-describedby="basic-addon1" readonly="readonly">
				<span class="input-group-btn">
					<button class="btn btn-default" type="button" onclick="javascript:fn_openAddressPopup();">Search</button>
				</span>
			</div>
			<div class="input-group" style="margin-top:0px;">
				<span class="input-group-addon" id="basic-addon1">나머지 주소</span>
				<input type="text" id="address2" name="address2" class="form-control" placeholder="나머지 주소를 입력하세요." aria-describedby="basic-addon1">
			</div>
		</form>
	</div>

	<div style="margin-top:15px;">
		<div>
			<button type="button" class="btn btn-lg btn-warning pull-right" onclick="javascript:history.go(-1);">뒤로가기</button>
			<button type="button" class="btn btn-lg btn-success pull-right" onclick="javascript:fn_submit();">저장</button>
		</div>
	</div>
		
</div>

<script>
	<%-- 등록 --%>
	function fn_submit(){
		if(fn_validation()){
			return;
		}
		
		var f = document.insertForm;
		f.action = "/insertMember.do";
		f.submit();
	}

	<%-- 주소검색 팝업 호출 --%>
	function fn_openAddressPopup(){
		var url = "/fwdSearchAddressPopup.do";
		var name = "AddressPopup";
		var option = "width=650, height=500, top=100, left=200, location=no"
		window.open(url, name, option);
	}
	
	<%-- 주소검색 팝업 호출 CallBack --%>
	function callback_openAddressPopup(aParam){
		document.getElementById("address").value = aParam["roadAddr"];
	}

	<%-- 유효성 검사 --%>
	function fn_validation(){

		var emailReg = /@/gi;
		var phoneNumberReg = /^[0-9]+$/;
		
		
		if(document.getElementById("id").value.length < 1) {
			alert("아이디를 입력하세요.");
			document.getElementById("id").focus();
			return true;
		}else if(document.getElementById("password").value.length < 1 || document.getElementById("password2").value.length < 1){
			alert("비밀번호를 입력하세요.");
			return true;
		}else if(document.getElementById("password").value != document.getElementById("password2").value){
			alert("비밀번호가 일치하지않습니다.");
			document.getElementById("password2").focus();
			return true;
		}else if(document.getElementById("name").value.length < 1) {
			alert("이름을 입력하세요.");
			document.getElementById("name").focus();
			return true;
		}else if(!emailReg.test(document.getElementById("email").value)){
			alert("이메일 주소는 '@'가 필수로 입력되어야 합니다");
			document.getElementById("email").focus();
			return true;
		}else if(!phoneNumberReg.test(document.getElementById("phoneNum").value)){
			alert("휴대전화는 숫자만 입력 가능합니다.");
			document.getElementById("phoneNum").focus();
			return true;
		}else if(document.getElementById("birth").value.length < 1) {
			alert("생년월일을 입력하세요.");
			document.getElementById("birth").focus();
			return true;
		}else if(document.getElementById("address").value.length < 1) {
			alert("주소를 검색하세요.");
			document.getElementById("address").focus();
			return true;
		}else if(document.getElementById("address2").value.length < 1) {
			alert("나머지 주소를 입력하세요.");
			document.getElementById("address2").focus();
			return true;
		}
		
		return false;
	}

	<%-- 이미지 업로드시 이미지 미리보기 --%>
	function setThumbnail(event) { 
		var reader = new FileReader();

		reader.onload = function(event) { 
			document.getElementById("userImage").setAttribute("src", event.target.result);
		};

		reader.readAsDataURL(event.target.files[0]); 
	}
	
	$( function() {
	    $( "#birth" ).datepicker();
	} );
</script>
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
