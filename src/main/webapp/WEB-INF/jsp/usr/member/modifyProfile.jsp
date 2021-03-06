<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="pageTitle" value="Profile"/>

<%@ include file="../common/head.jspf" %>

<c:set var="member" value="${rq.loginedMember}"/>
	<section class="container mx-auto mt-5 px-3">
		<form action="/usr/member/doModifyProfile" onsubmit="if(profilePhoto__chk(this) == false){return false;}" method="post" enctype="multipart/form-data" class="profileForm">
			<div class="avatar flex flex-col items-center left-1/2 transform -translate-x-1/2 mb-5">
				<div>
		  			<div class="rounded-full w-40 h-40 overflow-hidden">
		    			<img class="profileImg" src="${member.profileImgUrl}">
		  			</div>
		  			<label class="relative left-36 -top-4 cursor-pointer">
		  				<i class="fas fa-camera"></i>
		  				<input type="file" class="hidden profileImgFile" accept="image/*" name="file__member__0__common__profile"/>
		  				<input type="hidden" class="relId" value="${member.id}" name="relId"/>
		  			</label>
				</div>
		  			<span class="text-xl">${member.name}</span>
			</div>
			<div class="w-2/4 mx-auto flex justify-between">
				<div></div>
				<button>확인</button>
			</div>
			<div class="w-2/4 mx-auto bg-gray-200 text-gray-600 flex flex-col text-center py-5">
	  			<label class="mb-2">이메일 : <input type="email" value="${member.email}" name="email" required/></label>
	  			<label class="mb-2">닉네임 : <input type="text" value="${member.nickname}" name="nickname" required/></label>
	  			<label>전화번호 : <input type="tel" value="${member.cellphoneNo}" name="cellphoneNo" pattern="[0-9]{3}[0-9]{4}[0-9]{4}" required/></label>
			</div> 
		</form>
	</section>
	
<script>
	$(".profileImgFile").change(function (event) {         
	    // Get form         
	    var form = $('.profileForm')[0];  	    
	    // Create an FormData object          
	    var data = new FormData(form);  	   
	    
	    $.ajax({             
	    	type: "POST",          
	        enctype: 'multipart/form-data',  
	        url: "/usr/member/changeProfileImg",        
	        data: data,          
	        processData: false,    
	        contentType: false,      
	        cache: false,
	        success: function (data) {
		        $(".profileImg").attr("src", data + "?time=" + new Date().getTime());
	        },          
	        error: function (e) {  
	        	console.log("ERROR : ", e);     
	        }
		});  
	});


	function profilePhoto__chk(form){
		var file = form.file__member__0__common__profile;
		if(file.value){
			if(file.files[0].type.startsWith("image") == false){
				alert("잘못된 형식의 프로필 이미지 입니다.")
				return false;
			}
		}
	}
</script>
<%@ include file="../common/foot.jspf" %>