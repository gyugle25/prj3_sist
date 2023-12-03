<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../checkLogin.jsp" %> 


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta http-equiv="Expires" content="-1" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Cache-Control" content="No-Cache" />
    <meta http-equiv="imagetoolbar" content="no" />
    <meta name="viewport" content="width=1024" />
    <meta name="keywords" content="CGV, 시지브이, 영화관, 극장, 영화, 티켓, 박스오피스, 극장, Movie, Theater, Cinema, Cgv, cgv, 예매, 상영작" />
    <meta name="description" content="CGV는 선진화된 관람문화와 최고의 서비스로 고객에게 잊을 수 없는 감동을 선사합니다. CGV홈페이지를 통해 영화 예매뿐만 아니라 그 이상의 서비스와 감동을 전달하고, 다양한 즐거움과 특별한 경험을 제공하고자 합니다." />
    <meta property="og:site_name" content="영화 그 이상의 감동. CGV"/>
    <meta id="ctl00_ctl00_og_title" property="og:title" content="프로필관리 &lt; My CGV | 영화 그 이상의 감동. CGV"></meta>
    
    
    <meta id="ctl00_ctl00_og_image" property="og:image" content="https://img.cgv.co.kr/WebApp/images/common/logo_new_kakao_prevw.png"></meta>
    <link rel="alternate" href="http://m.cgv.co.kr" />
    <link rel="shortcut icon" href="https://img.cgv.co.kr/theater_img/favicon.ico" type="image/x-icon" />
    <title id="ctl00_ctl00_headerTitle">프로필관리 &lt;</title>
    <link rel="shortcut icon" type="image/x-icon" href="https://img.cgv.co.kr/R2014/images/favicon.ico" />
    <link rel="stylesheet" media="all" type="text/css" href="https://img.cgv.co.kr/R2014/css/webfont.css" />
	<link rel="stylesheet" media="all" type="text/css" href="https://img.cgv.co.kr/R2014/css/reset.css" />
    <link rel="stylesheet" media="all" type="text/css" href="https://img.cgv.co.kr/R2014/css/layout.css" />
    <link rel="stylesheet" media="all" type="text/css" href="https://img.cgv.co.kr/R2014/css/module.css?20211209" />
    <link rel="stylesheet" media="all" type="text/css" href="https://img.cgv.co.kr/R2014/css/content.css" />
    <link rel="stylesheet" media="all" type="text/css" href="https://img.cgv.co.kr/R2014/css/common.css" />
    
    <link rel="stylesheet" media="all" type="text/css" href="https://img.cgv.co.kr/R2014/css/eggupdate.css" />
    <link rel="stylesheet" media="print" type="text/css" href="https://img.cgv.co.kr/R2014/css/print.css" />    
    <link rel="stylesheet" type="text/css" href="https://img.cgv.co.kr/R2014/js/jquery.ui/smoothness/jquery-ui-1.10.4.custom.min.css" />
    
    <script type="text/javascript" src="/common/js/extraTheaters.js"></script>
    <script type="text/javascript" src="https://img.cgv.co.kr/R2014/js/app.config.js"></script>
    <script type="text/javascript" src="https://img.cgv.co.kr/R2014/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="https://img.cgv.co.kr/R2014/js/jquery.plugin/jquery.tmpl.min.js"></script>
    <script type="text/javascript" src="https://img.cgv.co.kr/R2014/js/jquery.plugin/jquery.validate.js"></script>
    <script type="text/javascript" src="https://img.cgv.co.kr/R2014/js/jquery.plugin/jquery.paging.min.js"></script>
    <script type="text/javascript" src="https://img.cgv.co.kr/R2014/js/jquery.ui/jquery-ui-1.10.4.custom.min.js"></script>
    <script type="text/javascript" src="https://img.cgv.co.kr/R2014/js/jquery.utils.js"></script>
    <script type="text/javascript" src="https://img.cgv.co.kr/R2014/js/app.utils.js"></script>
	<script type="text/javascript" src="https://img.cgv.co.kr/R2014/js/jquery.utils.pageing.js"></script>
    <script type="text/javascript" src="https://img.cgv.co.kr/R2014/js/app.init.js"></script>

    <!--[if lte IE 9]><script type="text/javascript" src="https://img.cgv.co.kr/R2014/js/jquery.plugin/jquery.placeholder.js"></script><![endif]-->
    <script type="text/javascript" src="https://img.cgv.co.kr/R2014/js/jquery.plugin/jquery.dotdotdot.min.js"></script>
    <script type="text/javascript" src="https://img.cgv.co.kr/R2014/js/silverlight_link.js"></script>
	<script src="https://img.cgv.co.kr/R2014/js/slick/slick.js" type="text/javascript" charset="utf-8"></script>

    <link rel="stylesheet" media="all" type="text/css" href="https://img.cgv.co.kr/R2014/css/phototicket/phototicket.css" />
    <link rel="stylesheet" media="all" type="text/css" href="https://img.cgv.co.kr/R2014/css/slick.css" />
	<link rel="stylesheet" media="all" type="text/css" href="https://img.cgv.co.kr/R2014/css/slick-theme-custom.css" />

    <script type="text/javascript" src="https://img.cgv.co.kr/R2014/js/icheck/login.timer.js"></script>
    <script src="https://img.cgv.co.kr/R2014/js/icheck/icheck.min.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="https://img.cgv.co.kr/R2014/js/icheck/iCheck.css" />

    <!-- 2020.05.07 감정지수/프리에그 프로젝트 추가 -->
    <link rel="stylesheet" media="all" type="text/css" href="https://img.cgv.co.kr/R2014/css/preegg.css" />

    <!-- 홈페이지 CSS 일원화로 인한 반영 20220721 -->
    <link rel="stylesheet" type="text/css" href="https://img.cgv.co.kr/resource_pc/css/cgv.min.css" />
    <script type="text/javascript" src="https://img.cgv.co.kr/resource_pc/js/cgvUi.js"></script>

    <!-- 각페이지 Header Start--> 
    
    <link rel="stylesheet" media="all" type="text/css" href="https://img.cgv.co.kr/R2014/css/phototicket/phototicket.css" />
    <!--통합결제-->
    <link rel="stylesheet" type="text/css" href="https://img.cgv.co.kr/R2014/css/mypagenew.css" />

    <!--/각페이지 Header End--> 
   


<script>

//errorMessage 값을 확인하여 alert 띄우는 함수
function showAlert() {
  var errorMessage = "${errorMessage}";
  if (errorMessage) {
    alert(errorMessage);
  }//end if
}//showAlert


// 페이지 로딩 시 showAlert 함수 호출
window.onload = function() {
  showAlert();
};


$(function(){
	
	//중복 확인된 닉네임
	var checkedNick = "";
	
	
		//닉네임 중복버튼
		
	$("#dupBtn").click(function() {

			var nickname = ($("#nick_name").val()).trim();

			if (nickname != "") { //닉네임이 입력된 상태라면

				//기존의 닉네임과 일치할 때 (변경x)

				if (($("#old_nick_name").val()).trim() == nickname) {
					//중복 확인된 닉네임에 기존 닉네임 저장
					checkedNick = nickname;
					return;
				}//end if	

				
				//정규식 패턴
				var regex = /^[a-zA-Z0-9_\uac00-\ud7af]{1,10}$/;
				if (!regex.test(nickname)) {
					alert("닉네임은 1~10자의 한글, 영문, 숫자, 밑줄(_)만 사용 가능합니다.");
					$("#nick_name").val("");
					$("#nick_name").focus() ; 
					return;
				}//end if 

				
				//중복 검사
				$.ajax({

					url : "nick_dup.do",
					data : {
						nick_name : nickname
					},
					method : "post",
					dataType : "json",
					success : function(response) {

						if (response.available) {
							alert("사용 가능합니다.");

							//중복확인된 닉네임에 저장
							checkedNick = $("#nick_name").val();

						} else {
							alert("이미 사용 중인 닉네임입니다. 다시 입력해주세요.");
							$("#nick_name").val("");
							$("#nick_name").focus();
							return;
						}//end else

					},
					error : function(xhr) {
						alert("서버 오류가 발생했습니다.");
					}//end error

				});//ajax

			} else {
				alert("닉네임을 입력해주세요.");
				return;
			}//end else
		});//click

		//프로필 등록 버튼
		$("#setProfileBtn").click(function() {

			var profile_msg = ($("#profile_msg").val()).trim();
			var nickname = ($("#nick_name").val()).trim();
			var oldnick = ($("#old_nick_name").val()).trim();

			if (nickname == "") {
				alert("닉네임은 필수 입력입니다.");
				return;
			}//end if

			if (profile_msg == "") {
				alert("자기소개는 필수 입력입니다.");
				return;
			}//end if

			//닉네임 변경된 경우
			if (oldnick != nickname) {
				//중복 확인했는지 먼저 확인
				if (checkedNick != nickname) { //중복확인된 닉네임과 최종 입력값 비교
					alert("닉네임 중복 확인을 먼저 수행해주세요");
					return;
				}//end if
			}//end if

			$("#frm").submit();

		});//click

		//영화 검색	
		$("#searchBtn").click(function() {

			$("#movieSearchFrm").submit();

		});//click	

	});//ready
</script>


    
</head>

<body class="">

<div class="skipnaiv">
	<a href="#contents" id="skipHeader">메인 컨텐츠 바로가기</a>
</div> 
   
<div id="cgvwrap">
    
	<div class="header">			
            <!-- 서비스 메뉴 --> 
            
<div class="header_content">
    <div class="contents">
        <h1 onclick=""><a href="main_loged_frm.do"><img src="http://localhost/prj3_mvc3/images/movie.png" alt="CGV" /></a><span>MOVIEPLANET</span></h1>
        <ul class="memberInfo_wrap">
            
            
            <li><a href="mypage.do"><img src="https://img.cgv.co.kr/R2014/images/common/ico/loginMember.png" alt="MY PAGE" /><span>마이페이지</span></a></li>
            <!-- <li><a href="/support/default.aspx"><img src="https://img.cgv.co.kr/R2014/images/common/ico/loginCustomer.png" alt="고객센터" /><span>고객센터</span></a></li> -->
        </ul>
    </div>
</div>


<div class="nav">
    <div class="contents">
        <h1><a href="/" tabindex="-1"><img src="https://img.cgv.co.kr/R2014/images/common/logo/logoWhite.png" alt="CGV" /></a></h1>
        <ul class="nav_menu">
            <li>
                <h2><a href="search_movie.do">영화</a></h2>
            </li>
            <li>
                <h2><a href="movie_reserve.do">예매</a></h2>
            </li>
            <li>
                <h2><a href="/culture-event/popcorn-store/"></a></h2>
                
            </li>
            <li>
                <h2 onclick="gaEventLog('PC_GNB','주메뉴_이벤트','')"><a href="/culture-event/event/defaultNew.aspx"></a></h2>
            </li>
            <li>
                <h2 onclick="gaEventLog('PC_GNB','주메뉴_해택','')"><a href="/discount/discountlist.aspx"></a></h2>
            </li>
        </ul>
        <div class="totalSearch_wrap">
            <form id="movieSearchFrm" name="movieSearch" action="search_movie.do">
            <label for="totalSearch">
                <input type="text" id="title" name="title" placeholder="영화 검색" value=""/>
            </label>
            <button type="button" class="btn_totalSearch" id="searchBtn">검색</button>
            </form>
            <iframe src="//ad.cgv.co.kr/NetInsight/html/CGV/CGV_201401/main@Search_txt" width="0" height="0" title="" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" allowfullscreen="allowfullscreen" mozallowfullscreen="mozallowfullscreen" msallowfullscreen="msallowfullscreen" oallowfullscreen="oallowfullscreen" webkitallowfullscreen="webkitallowfullscreen"></iframe>
        </div>
    </div>
</div>
            <!-- 서브 메뉴 -->			
	</div>
	<!-- E Header -->

	<!-- Contaniner -->
	<div id="contaniner"  class=""><!-- 벽돌 배경이미지 사용 시 class="bg-bricks" 적용 / 배경이미지가 없을 경우 class 삭제  -->
        

		<!-- Contents Area -->
		 <div id="contents" class="">
        
            
            <!-- Contents Start -->
			
   

<div class="cols-content" id="menu">
    <div class="col-aside">
		<div class="skipnaiv">
			<a href="#mycgv_contents" id="skipMycgvMenu">MYCGV 서브메뉴 건너띄기</a>
		</div>
	    <h2>MY CGV 서브메뉴</h2>
	    <div class="snb">
	        <ul>
	            <li >
                    <a href="mypage.do" >MY PAGE HOME <i></i></a>
                </li>
	           
                        
	        </ul>
	        <ul>
                 <li>
                    <a href="" title="현재 선택">프로필 관리<i></i></a>
                </li>
	            
                        
	        </ul>
	       
	    </div>
    </div>
	<div class="col-detail" id="mycgv_contents">
	    
    <input type="hidden" id="isIPIN" name="isIPIN" value="jFZKM5GCrrspxSCDBJ/aZPJzBHY84L6fg3gkITx1hLIKdGBTiHNfbVLgRDBBtHzxd4gu39B69dAGYP5rjFUhWQ==" />
<div class="tit-mycgv">
    <h3>나의 정보</h3>
</div>
<div class="tit-mycgv">
    <h4>나의 프로필</h4>
</div>


<form name="frm" method="post" action="modify_profile.do" id="frm" enctype="multipart/form-data" novalidate="novalidate">
<div>
</div>

<div>

    <input type="hidden" id="user_id" name="user_id" value="${profile.user_id}" /> 
</div>
<div class="tbl-form">
    <table summary="나의프로필정보 이름,아이디, 닉네임,프로필이미지 표기">
        <caption>나의 프로필 정보</caption>
        <colgroup>
        <col width="19%" />
        <col width="*" />
        </colgroup>
        <tbody>
            <tr>
                <th scope="row">이름</th>
                <td><strong><c:out value="${profile.name}"/></strong></td>
            </tr>
            <tr>
                <th scope="row">아이디</th>
                <td><span><c:out value="${profile.user_id}"/></span></td>
            </tr>
            <tr>
                <th scope="row"><label for="nick_namee">닉네임</label></th>
                <td>
                    <p>한글, 영문, 숫자 혼용 가능 (한글 기준 10자 이내)</p>
                    <input type="hidden" id="old_nick_name" name="old_nick_name" value="${profile.nick_name}" /> 
                    <input type="text" id="nick_name" name="nick_name" 
                    value="<c:out value="${profile.nick_name}"/>" maxlength="10" class="s-medium" /> 
                    <button id="dupBtn" type="button" class="round gray"><span>중복확인</span></button>
                </td>
            </tr>
            
            <tr>
                <th scope="row">프로필이미지</th>
                <td>
                    <p class="profile-info">각 서비스(이벤트, 매거진, 영화리뷰 등)의 리뷰 및 덧글작성시 등록하신 대표이미지가 노출됩니다.<br />프로필 이미지 종류를 선택해 주세요.</p>
                    <%-- <input type="hidden" id="profile" name="profile" value="<c:out value="${profile}"/>" />
                    <input type="hidden" id="user_small_image" name="user_small_image" value="" /> --%>
                    <div class="sect-profile-img">
                        <div class="box-image">
                            <span class="thumb-image" >
                            <img id="profile_set" name="profile_set" src="<c:out value='http://localhost/prj3_mvc3/upload/${profile.profile}?timestamp=${System.currentTimeMillis()}'/>" alt="${profile.name }님 프로필 사진" onerror="this.src='http://localhost/prj3_mvc3/images/no.png'" />
                               	<input type="hidden" name="profile" id="profile" value="${profile.profile}"/>
                                <span class="profile-mask"></span>
                            </span>
                        </div>
                        <div class="box-contents">
                            <p>jpg, gif, BMP, png 파일만 등록 가능합니다. (최대 3M)</p>
                            <input type="file" id="profile_upload_file" name="profile_upload_file" title="내용"  />
                        </div>
                    </div>

					

                </td>
            </tr>
            
             <tr>
                <th scope="row"><label>자기소개</label></th>
                <td>
                    <p>자기소개를 입력해주세요 (한글 기준 100자 이내)</p>
                    <textarea id="profile_msg" name="profile_msg" maxlength="100" class=""><c:out value="${profile.profile_msg }"/></textarea>
                </td>
            </tr>
            
            
            
        </tbody>
    </table>
</div>
<div class="set-btn aright"><button type="button" id="setProfileBtn" class="round inred on" ><span>등록하기</span></button></div>
</form>







<script type="text/javascript" src="https://img.cgv.co.kr/R2014//js/system/crypto.js"></script>

</div>

            
            <!--/ Contents End -->
		 </div>
		<!-- /Contents Area -->
	</div>
	
	

</body>
</html>