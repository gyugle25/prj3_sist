<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" info="여러 노드의 파싱"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<head>
<!-- META -->
<meta charset="utf-8">
<meta name="generator" content="Rhymix">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="" />
<meta name="csrf-token" content="UkcBJRYhQI7ZosRT" />
<!-- TITLE -->
<title>타이틀</title>
<!-- CSS -->
<link rel="stylesheet" href="http://localhost/prj3_mvc3/css/NewFile.css" />
<link rel="stylesheet" href="http://localhost/prj3_mvc3/css/NewFile1.css" />
<link rel="stylesheet" href="http://localhost/prj3_mvc3/css/NewFile2.css" />
<link rel="stylesheet" href="http://localhost/prj3_mvc3/css/NewFile3.css" />
<link rel="stylesheet" href="http://localhost/prj3_mvc3/css/NewFile4.css" />
<link rel="stylesheet" href="http://localhost/prj3_mvc3/css/NewFile5.css" />
<link rel="stylesheet" href="http://localhost/prj3_mvc3/css/NewFile6.css" />
<link rel="stylesheet" href="http://localhost/prj3_mvc3/css/NewFile7.css" />
<link rel="stylesheet" href="http://localhost/prj3_mvc3/css/NewFile8.css" />
<link rel="stylesheet" href="http://localhost/prj3_mvc3/css/NewFile9.css" />
<link rel="stylesheet"
	href="http://localhost/prj3_mvc3/css/NewFile10.css" />
<link rel="stylesheet"
	href="http://localhost/prj3_mvc3/css/NewFile11.css" />
<link rel="stylesheet"
	href="http://localhost/prj3_mvc3/css/NewFile12.css" />
<link rel="stylesheet"
	href="http://localhost/prj3_mvc3/css/NewFile13.css" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />

<!-- JS -->
<script src="/common/js/jquery-2.2.4.min.js?20190112221345"></script>
<script src="/files/cache/assets/minified/rhymix.min.js?20221117005757"></script>
<script src="/modules/board/tpl/js/board.js?20201227091305"></script>
<script
	src="/modules/board/skins/ink_board_rawdell/js/board.js?20211226033228"></script>
<script
	src="/modules/board/skins/ink_board_rawdell/js/board_pc.js?20211014021546"></script>
<script
	src="/modules/board/skins/ink_board_rawdell/js/jquery.selectbox.min.js?20201227114829"></script>
<script
	src="/modules/editor/skins/ckeditor/js/xe_textarea.js?20190112221345"></script>
<script src="/modules/editor/tpl/js/editor_common.js?20190112221345"></script>
<script src="/layouts/ink_layout_rawdell/js/layout.js?20201227114846"></script>
<script
	src="/layouts/ink_layout_rawdell/js/jquery.scrollbar.min.js?20201227114846"></script>
<script
	src="/layouts/ink_layout_rawdell/js/jquery.selectbox.min.js?20201227114846"></script>
<script src="/layouts/ink_layout_rawdell/js/layout_pc.js?20201227114846"></script>
<script
	src="/layouts/ink_layout_rawdell/js/jquery.cookie.js?20201227114846"></script>
<script
	src="/layouts/ink_layout_rawdell/js/swiper.min.js?20201227114846"></script>




<!-- jQuery CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<!-- jQuery CDN -->



<style>
#popup_menu_area {
	outline: none !important;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.15) !important;
}
</style>
<style>
body, table, input, button, .ink_wrap .xe_content, .text_en {
	font-family: Pretendard, -apple-system, BlinkMacSystemFont, system-ui,
		'Apple SD Gothic Neo', 'Noto Sans KR', 'Malgun Gothic', sans-serif;
	font-weight: 400;
}

#popup_menu_area {
	padding: 12px 15px 5px;
}

#popup_menu_area li {
	margin-bottom: 7px;
}

#popup_menu_area a {
	padding: 0 !important;
	color: #999;
	transition-property: color;
	transition-duration: 0.2s;
	transition-timing-function: ease-in;
}

#popup_menu_area a:hover {
	color: #333;
	background-color: transparent !important;
}

.ink_container, .ink_banner_top, .ink_footer {
	width: 1200px;
}

.ink_footer_wrap {
	min-width: 1200px;
}

.nav_wrap.fixed {
	width: 1200px;
	margin-left: -600px;
}

.ink_content {
	width: 930px;
}

.ink_sidebar.right_sidebar {
	width: 220px;
}

.ink_sidebar.left_sidebar {
	width: 250px;
}

.ink_sidebar.right_sidebar::after {
	right: 245px
}

.ink_sidebar.left_sidebar::after {
	left: 275px;
}

.containerB .ink_content {
	width: 905px;
}

.containerB.sidebar_typeN .ink_content {
	width: 100%;
}

.containerB .ink_sidebar.right_sidebar {
	width: 195px;
}

.containerB .ink_sidebar.left_sidebar {
	width: 225px;
}

.normal_mode .ink_gnb .menu_1depth {
	line-height: 70px;
}

.nav_wrap.view_mode, .normal_mode .gnb_space .menu_1depth {
	height: 70px;
}

.ink_header {
	margin-top: 25px;
}

.nav_wrap.color_nav, .view_mode.color_nav .ink_gnb, .normal_mode.color_nav .menu_1depth,
	.nav_wrap .mymenu_title, .nav_wrap.color_nav.fixed {
	background-color: #c14c3d !important;
}

.nav_wrap.color_nav .menu_1depth, .color_nav.view_mode a, .nav_wrap.color_nav .nav_buttons button
	{
	color: rgba(255, 255, 255, 0.7);
}

.nav_wrap.color_nav .menu_1depth:hover, .color_nav.view_mode a:hover,
	.nav_wrap.color_nav .nav_buttons button:hover, .nav_wrap.color_nav .active>a.menu_1depth
	{
	color: #FFF;
}

.view_mode.color_nav .ink_gnb>ul>li {
	border-color: rgba(255, 255, 255, 0.3);
}

.view_mode.color_nav .ink_gnb .menu_1depth {
	border-color: rgba(255, 255, 255, 0.5);
}

.ink_header .logo, .ink_header .header_side {
	height: 72px;
}

.header_typeB3 {
	padding-top: -18px;
}

body {
	background-color: #e6e6e6;
}

.ink_sidebar.left_sidebar::after, .ink_sidebar.right_sidebar::after {
	background-color: rgba(0, 0, 0, 0.05);
}
</style>
<style>
.atc_body .xe_content {
	padding: 30px 40px 35px 40px;
}

.ldd>ul>li, .ldn td {
	padding-top: 14px;
	padding-bottom: 14px;
}

.list_wrap .title_link, .ldf header h1 {
	font-size: 13px;
	font-size: 15px;
}

.ldn .list_author {
	font-size: 13px;
	font-size: 13px;
}

.hd{
height:100px;
}

.hd_span{
 display: inline-block; /* 인라인 요소를 블록 요소로 변경 */
  height: 100%;
}


.inner.scrollbar-macosx {
        max-height: 400px; /* 최대 높이 설정 */
        overflow: auto; /* 스크롤바 표시 설정 */
    }

.atc_who .inner {
    height: 110px;
}


.xe_content a, .atc_ex td a {
    border-bottom: none; /* 또는 border-bottom: 0; */
    color: inherit; /* 또는 color: initial; */
}

</style>
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700&display=swap" />


<script>


//댓글 제출	
function submitComment(button) {
    
    var form = $(button).closest('form');
    var textarea = form.find('.cmt_textarea');
    var content = textarea.val();
    	
    if(content===""){
    	alert("댓글을 입력해주세요");
    	return;
    }//end if
    
    // 폼 제출
    form.submit();
}//submitComment



//대댓글 제출
function submitReply(button) {
	  
	  // 대댓글 폼에서 입력된 값을 가져옴
	    var form = $(button).closest('form');
	    var textarea = form.find('.cmt_textarea');
	    var content = textarea.val();
	  
	  
	  if(content==""){
	    	alert("댓글을 입력해주세요");
	    	return;
	    }//end if
	  

	  // 폼을 서버로 제출
	  form.submit();
	}//submitReply



//부모 댓글 삭제
function confirmAndDel(commentId) {
	  if (confirm("댓글을 삭제하시겠습니까?")) {
	    // 해당 댓글 unit을 식별하여 대댓글 unit을 확인합니다.
	    const commentUnit = $("#comment_"+commentId);
	    const replyUnit = commentUnit.next(".cmt_unit.reply").first();

	    var com_num = commentId;
	    var has_replies = false;

	    if (replyUnit.length > 0) {
	      // 대댓글 unit이 존재하는 경우
	      has_replies = true;
	    }

	    
	    $.ajax({
	      url: "delete_comment.do",
	      data: {
	        com_num: com_num,
	        has_replies: has_replies
	      },
	      dataType: "json",
	      success: function(response) {
	        if (!response.resultFlag) {
	          alert("댓글 삭제에 실패했습니다.");
	          return;
	        } else {
	        
	          //화면 업데이트
	          if(has_replies){ //대댓이 있을 때
	        	  const contentElement = commentUnit.find(".rhymix_content");
	        	  contentElement.html('<span style="color: #777;">삭제된 댓글입니다.</span>');
	        	  
	          }else{
	        	  commentUnit.remove(); // 코멘트 유닛 삭제
	          }//end else
	        
	        }//end else
	        
	      },
	      error: function(xhr) {
	        console.log(xhr.status);
	        alert("문제 발생");
	      }
	    });
	  }
	}//confirmAndDel
	
	

//대댓글 삭제(업뎃)	
function deleteReply(event, replyNum){
		

	  if (confirm('댓글을 삭제하시겠습니까?')) {
		        
		  
	    $.ajax({
	      url: 'delete_reply.do',
	      data: { reply_num: replyNum },
	      dataType: 'json',
	      success: function(response) {
	        // 응답을 받았을 때의 동작 수행
	       
	        if (!response.resultFlag) {
	          alert("댓글 삭제에 실패했습니다.");
	          return;
	        } else {
	        	 //화면 업데이트
	        	 const replyUnit = $("#reply_" + replyNum);
		        const contentElement = replyUnit.find(".rhymix_content");
		        contentElement.html('<span style="color: #777;">삭제된 댓글입니다.</span>');	  
	        	
	        }//end else
	        	
	      },
	      error: function(xhr, status, error) {
	        // 에러 처리
	      }//end error
	    });
	    
	    
	  }//end if	
		
		
}//deleteReply	
	
	
/* function openEditor(button) {
		    var writeUnit = $(button).closest('.cmt_unit').find('.cmt_write_unit');
		    var editor = writeUnit.find('.cmt_textarea');
		    writeUnit.show();
		    writeUnit.height('auto');
		    editor.focus();
		  }//openEditor	 */

		  
		  
//추천인 리스트 업데이트
function updateRecommendationList() {
	
	var rv_num = ${param.rv_num};
	
	 $.ajax({
	        url: "/prj3_mvc3/updateRecommendations.do", // 업데이트하는 API 엔드포인트
	        method: "get",
	        data: {rv_num: rv_num},
	        dataType: "json",
	        success: function(updatedRecommendations) {
	            var recommendationList = $("#voted_who ul.scroll_x"); // 추천인 리스트의 ul 엘리먼트 선택
	            recommendationList.empty(); // 기존 리스트 비우기

	            if (updatedRecommendations.length === 0) {
	                recommendationList.append("추천인이 존재하지 않습니다.");
	            } else {
	            	var rowDiv = $("<div>").addClass("row"); // 줄을 나타내는 div 엘리먼트 생성
	            	recommendationList.append(rowDiv); // 최초의 div 엘리먼트를 ul에 추가


	            	updatedRecommendations.forEach(function(recommendation, index) {
	            	    var listItem = $("<li>");

	            	    var profileImgSpan = $("<span>") // 이미지를 감싸는 span 엘리먼트 생성
	            	        .addClass("inkpf color round") // span에 클래스 추가
	            	        .append($("<img>")
	            	            .attr("src", "http://localhost/prj3_mvc3/upload/" + recommendation.profile)
	            	            .addClass("inkpf_img")
	            	            .on("error", function() {
	            	                $(this).attr("src", "http://localhost/prj3_mvc3/images/no.png");
	            	            })
	            	        );

	            	    var nicknameSpan = $("<span>")
	            	        .addClass("vote_nickname")
	            	        .text(recommendation.nick_name);

	            	    listItem.append(profileImgSpan, "<br>", nicknameSpan);
	            	    rowDiv.append(listItem);

	            	    if ((index + 1) % 5 === 0) { // 5명마다 줄바꿈
	            	        rowDiv = $("<div>").addClass("row"); // 새로운 줄의 div 엘리먼트 생성
	            	        recommendationList.append(rowDiv); // 새로운 div 엘리먼트를 ul에 추가
	            	    }
	            	});//forEach
	            	
	            }//success
	        },
	        error: function(xhr) {
	            alert("문제");
	        }
	    });//ajax
	
}//updateRecommendationList







$(function() {
	
	
	
	
	var currentLikes = $("#cntLike").text();
	
	// 유저의 id 받기
	var user_id = $("#hiddenId").val();
	var rv_num = ${param.rv_num};

	// 좋아요 버튼이 눌리면
	$("#likeBtn").click(function() {

		// 사용자가 로그인 상태인지 확인
		if (user_id !== "") {

			// 현재 버튼의 클래스를 확인
			var liked = $(this).hasClass('submitted');
			// 눌린 상태였다면 true, 안눌린 상태였다면 false 

			$.ajax({
				// db의 좋아요 테이블에 insert 혹은 delete해주기
				url: "/prj3_mvc3/likeClicked.do",
				method: "get",
				data: {
					user_id: user_id,
					rv_num: rv_num,
					liked: liked
				},
				dataType: "json",
				success: function(jsonObj) {

					// db에서 할일

					if (jsonObj.resultFlag) { // db 작업에 성공하면

						if (liked) { // 눌린 상태였다면 해제하기

							$("#likeBtn").removeClass('submitted');
							currentLikes--;
							$("#cntLike").text(currentLikes);
						} else { // 안 눌린 상태였다면 누르기

							$("#likeBtn").addClass('submitted');
							currentLikes++;
							$("#cntLike").text(currentLikes);
						}//end else

							
							
						//추천인 리스트 받아서 화면 업데이트	
						updateRecommendationList();
							
					}//end if	
					
				},//end success
				error: function(xhr) {
					alert("문제");
				}//end error

			});//ajax

		} else {
			alert("로그인 후 이용 가능합니다.");
		}//end else

});//click




	
	//댓글 버튼 눌렀을 때 대댓글창 열기	
		 $('.bt_recomment').click(function() {
			// 유저의 id 받기
			var user_id = $("#hiddenId").val();
			 
			 if(user_id ==""){
				alert("로그인 후 이용 가능합니다.");
				 return;
			 }//end if
		
			 
			    $('.cmt_write_unit:not(.cmt_write)').hide();
			 
			    // 클릭된 버튼의 상위 요소인 cmt_unit을 찾습니다.
			    var cmtUnit = $(this).closest('.cmt_unit');

			    // cmt_write_unit 요소를 보이도록 설정합니다.
			    cmtUnit.find('.cmt_write_unit').show();

			    // form 요소의 height를 auto로 변경합니다.
			    cmtUnit.find('form.cmt_form').css('height', 'auto');

			    // cmt_textarea에 포커스를 설정합니다.
			    cmtUnit.find('.cmt_textarea').focus();
			  });//click
		
			  
			  
	//취소 버튼 클릭 시 댓글 입력창 숨기기
		 $('.bt_close').click(function() {
			    $(this).closest('.cmt_write_unit').hide();
			});		  



	
});//ready



</script>

</head>
<!-- BODY START -->
<body>
	<!-- PC -->
	<input type="hidden" id="hiddenId" value="${ lrDomain.user_id }"/>
	<div class="ink_wrap lightmode">
		<div id="container" class="ink_container header_typeB3 containerN">

			<div id="ink_wrapper" class="ink_wrapper clearfix">

				<div id="content" class="ink_content" role="main">

					<section class="ink_board guest_mode">
						<div class="bd_header" >
						
						<div style="height:100px; margin-top:20px;">
						<div style="width:930px; height:80px; border-radius: 15px; background-color: white;">
						<div style="float:left"><img src="http://localhost/prj3_mvc3/images/movie_small.png" style="height:50px; margin-top:20px;"/></div>
						<div style="font-size:20px; margin-top:27px; float:left"><font font style="color:#E74C3C; font-weight: bold;">MOVIEPLANET</font></div>
						<a href="login_success.do" style="float:right; margin-right:30px; margin-top:50px; font-weight: bold;" >HOME</a>
						</div>
						</div>
							<h2 class="bd_title" >
								<i class="far fa-list-alt big-icon"></i> <a href="login_success.do">메인으로</a>
								<i class="fas fa-angle-right"></i> 
								<a class="category_link" href="main_info.do?m_num=${ param.m_num }"><c:out value="${param.m_title} "/></a>
							</h2>
						</div>
						<article class="ink_atc round20 has_list">
							<header class="atc_header">
								<h1>
									<a href=""
										class="atc_title"><c:out value="${reviewInfo.title}"/></a>
								</h1>
								<div class="atc_info clearfix">
									<span class="atc_nickname"><span
										class="inkpf color round small"><img class="inkpf_img"
											src="http://localhost/prj3_mvc3/upload/${reviewInfo.profile}" onerror="this.src='http://localhost/prj3_mvc3/images/no.png'"/>
											<%-- ${profile} --%></span> <a href=""
										class="member_66498994">${reviewInfo.nick_name}</a>
									</span> <span class="text_en atc_date font_grey1"><c:out value="${reviewInfo.input_date}"/></span>
									<div class="atc_info_right text_en font_grey1">
										<span class="count_read"><i class="fas fa-eye"
											title="조회 수"><c:out value="${reviewInfo.hits}"/></i></span> <span class="count_vote pt_col"><i
											class="fas fa-heart" title="좋아요 수"><c:out value="${reviewInfo.like_total}"/></i></span> <span
											class="count_cmt pt_col2"><i
											class="fas fa-comment-dots" tilte="댓글"><c:out value="${ commSize }"/></i></span>
									
									<!-- 수정 삭제 버튼 choose -->
									<c:choose>
										<c:when test="${lrDomain.user_id eq reviewInfo.user_id}">
												<form action="review_write_modify.do" name="modifyFrm" method="post" style="display: inline-block;">
													<input type="hidden" name="rv_num" value="${ param.rv_num }"/>
													<input type="hidden" name="m_num" value="${ param.m_num }"/>
													<input type="hidden" name="m_title" value="${ param.m_title }"/>
													<span style="margin-left: 15px;"><button class="ib ib2 ib_color" style="background-color: #75A99C" type="submit">수정</button></span>
												</form>
												<a href="review_write_delete.do?rv_num=${ param.rv_num }&m_num=${ param.m_num }">
													<span><button class="ib ib2 ib_color" type="submit">삭제</button></span>
												</a>
										</c:when>
		
										<c:when test="${ empty lrDomain }">
											
										</c:when>
									</c:choose>
									</div>
								</div>
							</header>
							
							<div class="atc_body">

								<!--BeforeDocument(90958928,66498994)-->
								<div
									class="document_90958928_66498994 rhymix_content xe_content">
									<p>${reviewInfo.content}</p>
								</div>
								<!--AfterDocument(90958928,66498994)-->
								<div class="atc_buttons clearfix">
									<div class="atc_vote">


										<c:choose>
											<c:when test="${likeStatus}">
												<button id="likeBtn" class="bt_vote vote_area submitted"
													type="button">
													<!--onclick="insertWarn('로그인 해주세요.')"  -->
													좋아요<span id="cntLike" class="voted_count text_en"><c:out
															value="${likeCnt}" /></span>
												</button>
											</c:when>
											<c:otherwise>
												<button id="likeBtn" class="bt_vote vote_area" type="button">
													<!--onclick="insertWarn('로그인 해주세요.')"  -->
													좋아요<span id="cntLike" class="voted_count text_en"><c:out
															value="${likeCnt}" /></span>
												</button>
											</c:otherwise>
										</c:choose>

									</div>
								</div>
								<div class="atc_box bg_grey1">
									<div class="atc_sign">
										<h3>
											<span class="inkpf color round"><img class="inkpf_img"
												src="http://localhost/prj3_mvc3/upload/${reviewInfo.profile}"
												onerror="this.src='http://localhost/prj3_mvc3/images/no.png'"
												alt="NeoSun" /></span> <span class="nickname"><c:out
													value="${ reviewInfo.nick_name }" /></span>
										</h3>
										<div class="sign_body">
											<div
												style="max-height: 100px; overflow: auto; overflow-x: hidden; height: expression(this.scrollHeight &amp; amp;">
												<p>
													<c:out value="${reviewInfo.profile_msg}" />
												</p>
											</div>
										</div>
									</div>
								</div>

								<!-- 추천인 해야함 -->
								<div id="voted_who">
									<div class="atc_who bg_grey1 scroll_wrap show">
										<h3>
											추천인
											<!--좋아요 누른 사람들 list로 가져와서 뿌려야함  -->
										</h3>
										<div class="inner scrollbar-macosx">
											<ul class="scroll_x">
												 <c:if test="${ empty likeUser }">
       												 추천인이 존재하지 않습니다.
   												 </c:if>
												<c:if test="${ not empty likeUser }">
														<div class="row">
														<c:forEach var="user" items="${likeUser}" varStatus="loop">
															<li><span class="inkpf color round"> 
															<img
																	src="http://localhost/prj3_mvc3/upload/${user.profile}"
																	class="inkpf_img"
																	onerror="this.src='http://localhost/prj3_mvc3/images/no.png'" />
															</span> <br /> <span class="vote_nickname"> <c:out
																		value="${user.nick_name}" />
															</span></li>
															<c:if test="${(loop.index + 1) % 5 == 0 || loop.last}">
                    											</div>
                  											  	<c:if test="${!loop.last}">
                      									  			<div class="row">
                  										  	  	</c:if>
               											 	</c:if>
													</c:forEach>
												</c:if>
										</ul>
									</div>

								</div>
							</div>
							<!-- 추천인 해야함 -->
				</div>

				<div id="comment" class="cmt cmt_bubble">
								<div class="cmt_title">
									<h3>
										댓글 <span class="pt_col text_en cmt_count"><c:out value="${ commSize }"/></span>
									</h3>
								</div>
								<div class="cmt_notice">
									<i class="fas fa-microphone"></i>추천+댓글을 달면 포인트가 더 올라갑니다 <br>
									정치,종교 관련 언급 절대 금지입니다 <br> 상대방의 의견에 반박, 비아냥, 조롱 금지입니다 <br>
									영화는 개인의 취향이니, 상대방의 취향을 존중하세요 <br> <a
										href="https://extmovie.com/notice/30806202"
										style="color: #228b22;">자세한 익무 규칙은 여길 클릭하세요</a>
								</div>
								<!-- //cmt_notice -->
								<div class="cmt_wrap has_top">
									<div class="cmt_list">
									<!-- 댓글 시작  -->
						<!--부모댓글 시작  -->			
							<c:if test="${!empty comments }">
								<c:forEach items="${comments }" var="comment">
									<article class="cmt_unit" id="comment_${comment.com_num}">
											<div class="inkpf_wrap">
												<span class="inkpf round"> 
												<img class="inkpf_img"
													src="http://localhost/prj3_mvc3/upload/${comment.profile }" 
													onerror="this.src='http://localhost/prj3_mvc3/images/no.png'" /></span>
											</div>
											<div class="cmt_header">
												<a href="#popup_menu_area" class="nickname member_71600550">${comment.nick_name }</a>
											<c:if test="${comment.user_id eq reviewInfo.user_id }">
											<span class="writer pt_bg2">작성자</span>
											</c:if>
											</div>
											<div class="cmt_body">
												<div
													class="comment_90954240_71600550 rhymix_content xe_content">
													<c:choose>
													<c:when test="${!empty comment.content}">
													${comment.content }
												<div class="cmt_buttons">
													<a class="bt bt2 bt_recomment" href="javascript:void(0)">댓글</a>
													<c:if test="${comment.user_id eq lrDomain.user_id }">		
													<a class="bt bt2" href="javascript:void(0);" onclick="confirmAndDel('${comment.com_num}')">삭제</a>
													</c:if>
												</div>
													</c:when>
													
													<c:otherwise>
														<span style="color: #777;">삭제된 댓글입니다.</span>	
													</c:otherwise>
													
													</c:choose>
												</div>
												<div class="cmt_date_wrap text_en font_grey1">
													<div class="cmt_date">${comment.input_date }</div>
												</div>
											</div>
											<!-- //cmt_write_re 자식 댓글 입력창 시작 -->
											<div class="cmt_write_unit cmt_write_re" id="cmt_write_re" style="display: none;">
												<form action="add_reply.do" method="post" class="cmt_form" style="height: 0">
													<input type="hidden" id="com_num" name="com_num" value="${comment.com_num}">
													<input type="hidden" id="rv_num" name="rv_num" value="${param.rv_num}">
													<input type="hidden" id="user_id" name="user_id" value="${lrDomain.user_id }">
													 <span class="inkpf round"></span>
													<div class="cmt_write_input text_ver">
														<textarea class="cmt_textarea" id="editor_${comment.com_num}" name="content"
															placeholder="댓글 내용을 입력해주세요."></textarea>
													</div>
													<div class="cmt_write_option">
														<span class="write_option"></span>
														<div class="bt_area bt_right">
															<button class="ib ib2 ib_mono bt_close" type="button"
																>취소</button>
															<button class="ib ib2 ib_color" type="button" onclick="submitReply(this)">댓글 등록</button>
														</div>
													</div>
												</form>
											</div>
											<!-- //자식 댓글 입력창 끝	 -->
											
											<!--댓글 바디 끝 -->
										</article>
										<!-- 댓글 아티클 끝  -->
										
										<c:if test="${!empty comment.replies }">
										<c:forEach var="reply" items="${comment.replies}">
										<!--대댓글 아티클 시작  -->
										<article class="cmt_unit reply" id="reply_${reply.rp_num }">
											<div class="inkpf_wrap">
												<span class="inkpf round"> <img class="inkpf_img"
													src="http://localhost/prj3_mvc3/upload/${reply.profile}"
													onerror="this.src='http://localhost/prj3_mvc3/images/no.png'"
													alt="프로필 이미지" /></span>
											</div>
											<div class="cmt_header">
												<a href="" class="nickname member_80215049">${reply.nick_name }</a>
												<c:if test="${reply.user_id eq reviewInfo.user_id }">
												<span class="writer pt_bg2">작성자</span>
												</c:if>	
												
											</div>
											<div class="cmt_body">
												<div class="parent">
													<i class="fas fa-comment-dots"></i>${comment.nick_name }
												</div>
												<!--BeforeComment(90954854,80215049)-->
												
												
												<div class="comment_90954854_80215049 rhymix_content xe_content">
													<c:choose>
													<c:when test="${!empty reply.content}">
													${reply.content }
													
													 <div class="cmt_buttons">
													<a class="bt bt2 bt_recomment" href="javascript:void(0)">댓글</a>
													<c:if test="${reply.user_id eq lrDomain.user_id }">		
													<a class="bt bt2" href="javascript:void(0);" onclick="deleteReply(event, ${reply.rp_num})">삭제</a>
													</c:if>
													</div> 
													</c:when>
													<c:otherwise>
														<span style="color: #777;">삭제된 댓글입니다.</span>
													</c:otherwise>
													</c:choose>
													
												</div>
												<!--AfterComment(90954854,80215049)-->
												
												
												<div class="cmt_date_wrap text_en font_grey1">
													<div class="cmt_date">${reply.input_date }</div>
												</div>
											</div>
											
											<div class="cmt_write_unit cmt_write_re" id="cmt_write_re" style="display: none;">
												<form action="add_reply.do" method="post" class="cmt_form" style="height: 0"
													>
													<input type="hidden" id="com_num" name="com_num" value="${comment.com_num}">
													<input type="hidden" id="rv_num" name="rv_num" value="${param.rv_num}">
													<input type="hidden" id="user_id" name="user_id" value="${lrDomain.user_id }">
													 <span class="inkpf round"></span>
													<div class="cmt_write_input text_ver">
														<textarea class="cmt_textarea" id="editor_${reply.rp_num}" name="content"
															placeholder="댓글 내용을 입력해주세요."></textarea>
													</div>
													<div class="cmt_write_option">
														<span class="write_option"></span>
														<div class="bt_area bt_right">
															<button class="ib ib2 ib_mono bt_close" type="button"
																>취소</button>
															<button class="ib ib2 ib_color" type="button" onclick="submitReply(this)">댓글 등록</button>
														</div>
													</div>
												</form>
											</div>

										</article>
										</c:forEach>
										</c:if>
										<!--대댓글 끝  -->
										</c:forEach>
										</c:if>

									</div><!-- //cmt_list 여기 안쪽에서 댓글 유닛이 반복되면서 위치해야함-->
								</div><!-- //has top-->

							<!-- 여러가지 댓글 창 상태 확인 -->
							<!-- when 1.아이디 접속 + 내가 쓴 게시글-->
							<!-- when 2.아이디 접속 + 다른 사람 게시글 -->
							<!-- when 3.비로그인 -->
						<!-- 로그인한 사람과 리뷰쓴 사람이 같을 때 -->
							<c:choose>
								<c:when test="${!empty lrDomain.user_id}">
										<!-- //cmt_write -->
										<!-- //부모 댓글 입력창 시작 -->
										<div class="cmt_write cmt_write_unit">
											<span class="inkpf round"><img class="inkpf_img"
												src="http://localhost/prj3_mvc3/upload/${lrDomain.profile}"
												onerror="this.src='http://localhost/prj3_mvc3/images/no.png'" /></span>
											<form action="add_comment.do" method="post" class="cmt_form">
											<input type="hidden" id="user_id" name="user_id" value="${lrDomain.user_id}">
											<input type="hidden" id="rv_num" name="rv_num" value="${param.rv_num}">
											
												<div class="cmt_write_input text_ver">
													<textarea class="cmt_textarea" id="editor" name="content"
														cols="50" rows="4" placeholder="댓글 내용을 입력해주세요."></textarea>
												</div>
												<div class="cmt_write_option">
													<div class="bt_area bt_right">
														<button id="commentBtn" class="ib ib2 ib_color" type="button" onclick="submitComment(this)">댓글 등록</button>
													</div>
												</div>
											</form>
										</div>
										<!-- //부모 댓글 입력창 끝-->
								</c:when>
							
								<c:when test="${ empty lrDomain.user_id }">
									<div class="cmt_write cmt_write_unit no_grant">
										<div class="cmt_not_permitted">
											<i class="fas fa-comment-dots font_grey1"></i> 권한이 없습니다.
											<a class="ink_link2" href="login.do?returnTo=${param.rv_num }" onclick="">로그인</a>
										</div>
									</div>
								</c:when>
							</c:choose>


							</div><!-- //cmt_버블 -->
							
							
						</article><!-- 전체 article ink_atc round20 has_list-->
						
					</section><!--ink_board guest_mode  -->
				</div>
			</div>
		</div>
	</div>

</body>
</html>