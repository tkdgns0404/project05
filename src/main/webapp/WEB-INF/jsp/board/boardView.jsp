<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	.input-group{
		margin-top: 25px;
	}
</style>
<div class="container">
	<div>
		<form id="insertForm" name="insertForm" method="post" enctype="multipart/form-data">
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">제목</span>
				<input type="text" id="title" name="title" class="form-control" placeholder="제목를 작성하세요." aria-describedby="basic-addon1">
			</div>
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">내용</span>
				<textarea  style="height: 300px" id="content" name="content" class="form-control" placeholder="내용을 작성하세요." aria-describedby="basic-addon1"></textarea>
			</div>
			<div class="input-group text-center">
				<img class="rg_i Q4LuWd" id="userImage" src="./defaultpic.png" data-atf="true" width="150" height="150" onerror="this.src='images/defaultpic.png'">
				<label class="btn btn-default">
				    파일 선택 <input type="file" id="userImageAttachFile" name="userImageAttachFile" hidden onchange="setThumbnail(event);" />
				</label>
			</div>
		</form>
	</div>

	<div style="margin-top:15px;">
		<div>
			<button type="button" class="btn btn-lg btn-success pull-right" onclick="javascript:fn_submit();">저장</button>
			<button type="button" class="btn btn-lg btn-warning pull-right" onclick="javascript:history.go(-1);">뒤로가기</button>
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
		f.action = "/insertBoard.do";
		f.submit();
	}


	<%-- 유효성 검사 --%>
	function fn_validation(){

		if(document.getElementById("title").value.length < 1) {
			alert("제목를 작성하세요.");
			document.getElementById("title").focus();
			return true;
		}else if(document.getElementById("content").value.length < 1) {
			alert("내용을 작성하세요.");
			document.getElementById("content").focus();
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
	
</script>
