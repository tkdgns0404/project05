<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="container" style="margin-top:25px;">

	<div id="memberSearchDiv" class="text-center" style="display:none;float:right;">
		<form id="searchForm" name="searchForm" method="post" class="navbar-form navbar-left" role="search">
			<input type="hidden" id="page" name="page" value="" />
			<input type="hidden" id="size" name="size" value="10" />
			<div class="form-group">
				<input type="text" id="memberName" name="memberName" class="form-control" placeholder="Search">
			</div>
			<button type="submit" class="btn btn-default" onclick="javascript:fn_searchSubmit();">Search</button>
		</form>
	</div>
	
	<div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th><input type="checkbox" onclick="javascript:fn_checkboxAllCheck(this);"/></th>
					<th>회원번호</th>
					<th>이름</th>
					<th>이메일</th>
					<th>전화번호</th>
					<th>생년월일</th>
					<th>계정권한</th>
					<th>회원사진</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty resultList}">
					<tr>
						<td colspan="8" class="text-center">조회된 회원이 없습니다.</td>
					</tr>
				</c:if>
				<c:if test="${not empty resultList}">
					<c:forEach items="${resultList}" var="result" varStatus="status">
						<tr>
							<td><input type="checkbox" name="chooseRecord"/></td>
							<td>${status.count}
								<input type="hidden" id="idx_${status.index}" name="idx" value="${result.idx}" />
							</td>
							<td>${result.name}</td>
							<td>${result.email}</td>
							<td>${result.phoneNum}</td>
							<td>${result.birth}</td>
							<td>${result.role}</td>
							<td>
								<c:set var="fullURL" value="${pageContext.request.requestURL}"></c:set>
								<c:set var="pathURI" value="${pageContext.request.requestURI}"></c:set>
								<c:set var="baseURL" value="${fn:replace(fullURL, pathURI, '')}"></c:set>
								<img src="${baseURL}/${result.fileName}" width="150" height="150" onerror="this.src='images/defaultpic.png'"/>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>	
	</div>
	
	<c:if test="${not empty resultList}">
	<div class="text-center">
		<c:set var="startPage" value="${pagingVO.startPage}" />
		<c:set var="endPage" value="${pagingVO.endPage}" />
		<c:set var="totalPage" value="${pagingVO.totalPage}" />
		<nav>
			<ul class="pagination">
			<c:if test="${result.number >= startPage}">
					<li>
						<a href="javascript:fn_goPage('${result.number}');" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
				   	</li>
				</c:if>
			   	<c:forEach var="i" begin="${startPage}" end="${endPage}">
			   		<c:if test="${i-1 eq result.number}">
						<li class="active"><a href="javascript:fn_goPage('${i}');">${i}</a></li>
			   		</c:if>
			   		<c:if test="${i-1 ne result.number}">
						<li><a href="javascript:fn_goPage('${i}');">${i}</a></li>
			   		</c:if>
				</c:forEach>
				<c:if test="${result.number != totalPage-1}">
					<li>
						<a href="javascript:fn_goPage('${result.number + 2}');" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
					</li>
				</c:if>
			</ul>
		</nav>
	</div>
	</c:if>
	
	<div>
		<button type="button" class="btn btn-lg btn-danger pull-right" onclick="javascript:fn_delete();">삭제</button>
		<button type="button" class="btn btn-lg btn-warning pull-right" onclick="javascript:fn_update();">수정</button>
		<button type="button" class="btn btn-lg btn-info pull-right" onclick="javascript:fn_search();">조회</button>
		<button type="button" class="btn btn-lg btn-primary pull-right" onclick="javascript:fn_reg();">등록</button>
	</div>

</div>

<form id="detailForm" name="detailForm" method="post">
	<input type="hidden" id="idxs" name="idxs" value="" />
</form>
<script>

	<%-- 페이징_페이지 이동 --%>
	function fn_goPage(page){
		var f = document.searchForm;
		f.page.value = page-1;
		f.action = "/selectMemberList.do?size=5";
		f.submit();
	}

	<%-- 체크박스 모든선택 --%>
	function fn_checkboxAllCheck(obj){
		var chooseRecords = document.getElementsByName("chooseRecord");
		for(var i=0; i<chooseRecords.length; i++){
			chooseRecords[i].checked = obj.checked;
		}
	}

	<%-- 등록으로 이동 --%>
	function fn_reg(){
		location.href = "/fwdMemberReg.do";
	}

	<%-- 검색창 --%>
	function fn_search(){
		var memberSearchDiv = document.getElementById("memberSearchDiv");
		
		if(memberSearchDiv.style.display == "none"){
			memberSearchDiv.style.display = "block";
		}else{
			memberSearchDiv.style.display = "none";
		}
		
	}

	<%-- 검색 --%>
	function fn_searchSubmit(){
		var f = document.searchForm;
		f.action = "/selectMemberList.do";
		f.submit();
	}

	<%-- 삭제 --%>
	function fn_delete(){
		var memberIdxs = []; <%--배열--%>
		var chooseRecordCount = 0; <%--체크된 갯수 초기값 0 --%>
		var chooseRecords = document.getElementsByName("chooseRecord");  <%--체크박스--%>
		
		for(var i=0; i<chooseRecords.length; i++){ <%--체크박스 수 만큼 반복--%> <%--예) 10개 -> 10번 반복 --%> 
			if(chooseRecords[i].checked){ <%--그중에 체크 된게 있으면--%> <%--예) 0, 3, 4번째 체크박스가 체크 됐다치면 --%> 
				chooseRecordCount++; <%--체크된 만큼 체크 수 증가--%> <%-- 예) 3증가 --%>
				memberIdxs.push(document.getElementsByName("idx")[i].value); <%--${result.memberIdx}를 배열에 추가로 삽입--%>
																					<%--예) 0, 3, 4번째 회원번호를 배열로 받음--%>
			}
		}

		if(chooseRecordCount < 1){  <%-- 예) 3이니 알림문은 뜨지 않음 --%>
			alert("데이터를 선택하세요.");
			return;
		}
		
		var f = document.detailForm;
		f.idxs.value = memberIdxs; <%--배열을 리턴--%>
		f.action = "/deleteMemberByAdmin.do";	<%--MemberController의 delete 메소드 실행--%>
		f.submit();
	}

	<%-- 수정페이지 이동 --%>
	function fn_update(){
		var chooseRecordCount = 0;
		var chooseRecords = document.getElementsByName("chooseRecord");
		
		for(var i=0; i<chooseRecords.length; i++){
			if(chooseRecords[i].checked){
				chooseRecordCount++;
			}
		}

		if(chooseRecordCount < 1){
			alert("데이터를 선택하세요.");
			return;
			
		}

		var memberIdx = 0;
		var memberIdxs = document.getElementsByName("idx");
		if(chooseRecordCount > 1){
			alert("2개 이상 선택이기 때문에 첫번째 데이터값으로 이동합니다.");
			for(var i=0; i<chooseRecords.length; i++){
				chooseRecords[i].checked = false;
			}
			chooseRecords[0].checked = true;
			memberIdx = 0;
		}else{
			for(var i=0; i<chooseRecords.length; i++){
				if(chooseRecords[i].checked){
					memberIdx = i;
					break;
				}
			}
		}

		location.href = "/fwdMemberUpt.do?idx="+memberIdxs[memberIdx].value;
	}
</script>