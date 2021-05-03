<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container" style="margin-top:25px;">
	<div id="memberSearchDiv" class="text-center">
	
		<form id="searchForm" name="searchForm" method="post" class="navbar-form navbar-left" role="search" onsubmit="event.preventDefault();">
			<input type="hidden" name="currentPage" value="1"/>
			<input type="hidden" name="countPerPage" value="100"/>
			<input type="hidden" name="resultType" value="json"/> 
			<input type="hidden" id="confmKey" name="confmKey" value="devU01TX0FVVEgyMDIxMDQwOTE4MTczNjExMTAzMjA="/> 
		
			<div class="form-group">
				<input type="text" id="keyword" name="keyword" class="form-control" placeholder="도로명+건물번호, 건물명, 지번을 입력하세요." onkeypress="javascript:enterSearch();" />
			</div>
			<button type="button" class="btn btn-default" onclick="javascript:fn_search();">검색</button>
		</form>
		
	</div>
	
	<div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>주소</th>
					<th>우편번호</th>
				</tr>
			</thead>
			<tbody id="addressTableTbody">
				
			</tbody>
		</table>	
	</div>
</div>

<script>
	<%-- 주소명으로 검색 --%>
	function fn_search(){

		$.ajax({
			 url :"http://www.juso.go.kr/addrlink/addrLinkApiJsonp.do"
			,type:"post"
			,data:$("#searchForm").serialize()
			,dataType:"jsonp"
			,crossDomain:true
			,success:function(jsonStr){
				var errCode = jsonStr.results.common.errorCode;
				var errDesc = jsonStr.results.common.errorMessage;
				if(errCode != "0"){
					alert(errCode+"="+errDesc);
				}else{
					if(jsonStr != null){
						fn_makeListJson(jsonStr);
					}
				}
			}
		    ,error: function(xhr,status, error){
		    	alert("에러발생");
		    }
		});
		
	}

	<%-- 결과 테이블 생성 --%>
	function fn_makeListJson(jsonStr){
		var htmlStr = "";
		$(jsonStr.results.juso).each(function(){
			htmlStr += "<tr onclick=\"javascript:chooseAddress('"+this.roadAddr+"', '"+this.jibunAddr+"', '"+this.zipNo+"');\">";
			htmlStr += "<td>";
			htmlStr += "<dl>"+this.roadAddr+"</dl>";
			htmlStr += "<dl>"+this.jibunAddr+"</dl>";
			htmlStr += "</td>";
			htmlStr += "<td>"+this.zipNo+"</td>";
			htmlStr += "</tr>";
		});
		$("#addressTableTbody").html(htmlStr);
		
	}

	<%-- Enter 키 이벤트 --%>
	function enterSearch() {
		var evt_code = (window.netscape) ? ev.which : event.keyCode;
		if (evt_code == 13) {    
			event.keyCode = 0;  
			fn_search(); //jsonp사용시 enter검색 
		} 
	}

	<%-- 주소 선택 --%>
	function chooseAddress(roadAddr, jibunAddr, zipNo){
		var aParam = [];
		aParam["roadAddr"] = roadAddr;
		aParam["jibunAddr"] = roadAddr;
		aParam["zipNo"] = roadAddr;

		opener.callback_openAddressPopup(aParam);
		window.close();
	}
</script>