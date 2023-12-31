<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     
<!DOCTYPE html>
<html lang="ko">
<head>
<title>MOVIEPLANET-회원가입</title>

    <link href="http://localhost/prj3_mvc3/css/" rel="stylesheet" type="text/css" />
    <link href="http://localhost/prj3_mvc3/css/common-sv-202006161246.css" rel="stylesheet" type="text/css" />
    <link href="http://localhost/prj3_mvc3/css/join-sv-202203141106.css" rel="stylesheet" type="text/css" />

<style type="text/css">
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400&display=swap');
h2 { font-family: 'Noto Sans KR', sans-serif; }


input[type="date" i] {

	display: block;
    width: 100%;
    height: 60px;
    border: 0;
    outline: 0;
    box-sizing: border-box;
    color: #999;
    font-family: 'Malgun Gothic';
    background: transparent;

}


.mbrRegist .row {
    position: relative;
    display: block;
    margin-bottom: 20px;
    font-size: 0;
}


.error-message {
      display: none;
      color: red;
      height:10px;
      font-size:10px;
      padding:0px;
    }
    
</style>

<!-- jQuery CDN 시작 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<!-- jQuery CDN 끝 -->

<!-- 다음 우편번호 API시작 -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function findZip() {
        new daum.Postcode({
            oncomplete: function(data) {
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('zipcode').value = data.zonecode;
                document.getElementById("addr").value = roadAddr;
                document.getElementById("detail_addr").focus(); 
                
            }
        }).open();
    }
</script>
<!-- 다음 우편번호 API끝 -->



<script type="text/javascript">

	//휴대폰번호 형식 유효성 검증 
	function telValidation(input) {
		// 입력에서 숫자가 아닌 모든 문자 제거
		var sanitized = input.value.replace(/[^0-9]/g, '');

		// 입력이 6자보다 더 이상인지 점검
		if (sanitized.length > 6) {
			// 적절한 위치에 대시 삽입
			sanitized = sanitized.slice(0, 3) + '-' + sanitized.slice(3, 7)
					+ '-' + sanitized.slice(7, 11);
		}

		// 형식 지정된 날짜로 입력 값 업데이트
		input.value = sanitized;
	}//telValidation

	
	//내용 보기 
	function toggleDiv() {
		var div1 = document.getElementById("DevPolicyService");

		//보이기/숨기기
		if (div1.style.display === "none") {
			div1.style.display = "block";
		} else {
			div1.style.display = "none";
		}// end else
	}

	function toggleDiv2() {
		var div2 = document.getElementById("DevPolicyPrivacy");

		if (div2.style.display === "none") {
			div2.style.display = "block";
		} else {
			div2.style.display = "none";
		}// end else
	}

	
	//정규식 패턴 정의 (특정 형식을 가진 문자열 검증)
	const regexPatterns = {
		id : /^[a-zA-Z0-9_-]{3,16}$/,
		nickname : /^[a-zA-Z0-9_\uac00-\ud7af]{1,10}$/,
		email : /^\S+@\S+\.\S+$/,
		password : /^[a-zA-Z0-9]{4,16}$/
	};
	
	
	// 정규식 패턴 일치 함수 - 입력값, 정규식, 에러메시지
	function validateInput(input, regex, errorMessage) {
		var value = input.val().trim();
		var validationMsg = input.next(".error-message"); // 다음 형제 요소 중 class가 error-message인 요소를 선택

		//유효 여부를 true/false로 반환 (isXxValid)
		if (!regex.test(value)) { 
			validationMsg.text(errorMessage).show(); //일치하지 않으면 메시지를 보여주고 false 반환
			return false;
		} else {
			validationMsg.hide(); //일치하면 메시지를 숨기고 true 반환
			return true;
		}
	}//validateInput

	
	//비번-비번확인 검사 함수
	function checkPassword() {
		var password = $("#pass").val().trim();
		var confirmPass = $("#passCheck").val().trim();
		var passCheckValidation = $("#error_passCheck"); //메시지 div 요소 선택

		if (password !== confirmPass) {
			passCheckValidation.text("입력한 비밀번호와 불일치합니다.").show();
			return false; // 일치하지 않으면 false 반환
		}else {
		passCheckValidation.hide();
		return true; // 일치하면 true 반환
		}
	}//checkPassword

	
	//ready 시작
	$(function() {

		// 유효성 확인 결과 저장용 (isValidXxx)
		let isValidId = false;
		let isValidNickname = false;
		let isValidEmail = false;
		let isValidPassword = false;
		let isValidPasswordCheck = false;

		//중복 확인 결과 저장용 (isXxxAvailable)
		let isIdAvailable = false;
		let isNicknameAvailable = false;
		let isEmailAvailable = false;

		
		//주소검색 버튼
		$("#kakao").click(function() {
			findZip();
		});// click

		//약관 모두 선택하기
		$("#lb_chk_all").click(function() {
			var isChecked = $(this).prop('checked');
			//name 속성이 "terms"인 요소의 속성 설정
			$('input[name="terms"]').prop('checked', isChecked);
		});//click

		
		
		// 각 필드에 대한 유효성 검사 시작
		//아이디 (패턴 일치, 중복 여부)
		$("#user_id").blur( //포커스가 빠져나갈 때
				function() {
					var userId = $(this).val();

					if (userId != "") { //값이 입력되어 있을 때
						//유효성 검사
						isValidId = validateInput($(this), regexPatterns.id,
								"아이디는 3~16자의 영문, 숫자, 특수문자(_,-)만 사용 가능합니다.");

						//유효하지 않을 경우 종료
						if (!isValidId) {
							return;
						}//end if

						//db 중복값 확인
						$.ajax({

							url : "idDup.do",
							data : {
								user_id : userId
							},
							dataType : "json",
							success : function(response) {
								
								//중복 여부
								isIdAvailable = response.available;

								if (!isIdAvailable) {
									alert("이미 사용 중인 아이디입니다. 다시 입력해주세요.");
									$("#user_id").val("");
									$("#user_id").focus();
									return;
								}//end if
							},
							error : function(xhr) {
								alert("서버 오류가 발생했습니다.");
							}//end error

						});//ajax

					}//end if

				});//blur

		//이메일(패턴 일치, 중복 여부)
		$("#email").blur(
				function() {

					var email = $(this).val();

					if (email != "") { //값이 입력되었을 때
						isValidEmail = validateInput($(this),
								regexPatterns.email, "유효한 이메일을 입력해주세요.");

						if (!isValidEmail) {
							return;
						}//end if

						//db 중복확인	
						$.ajax({

							url : "emailDup.do",
							data : {
								email : email
							},
							method : "post",
							dataType : "json",
							success : function(response) {

								isEmailAvailable = response.available;

								if (!isEmailAvailable) {
									alert("이미 사용 중인 이메일입니다. 다른 이메일을 사용해주세요.");
									$("#email").val("");
									$("#email").focus();
									return;
								}//end else

							},
							error : function(xhr) {
								alert("서버 오류가 발생했습니다.");
							}//end error

						});//ajax

					}//end if 

				});//blur

				
		//닉네임 (패턴 일치, 중복 여부)
		$("#nick_name").blur(
				function() {

					var nickname = $(this).val();

					if (nickname !== "") {
						isValidNickname = validateInput($(this),
								regexPatterns.nickname,
								"닉네임은 1~10자의 한글, 영문, 숫자, 밑줄(_)만 사용 가능합니다.");

						if (!isValidNickname) {
							return;
						}//end if

						//db 중복 확인
						$.ajax({

							url : "nickDup.do",
							data : {
								nick_name : nickname
							},
							method : "post",
							dataType : "json",
							success : function(response) {

								isNicknameAvailable = response.available;

								if (!isNicknameAvailable) {
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

					}//end if

				});//blur

				
		//비번 유효성 (패턴 일치)
		$("#pass").on("keyup", function() { //keyup 이벤트에 핸들러 등록
					//비번의 유효성 검사
					isValidPassword = validateInput($(this),regexPatterns.password,"비밀번호는 4~10자의 영문과 숫자만 가능합니다.");
		
					//비번 확인과 일치 검사 (비번과 비번확인의 값 선택,비교하여 메시지 설정)
					checkPassword(); 
		});

				
		//비번확인 유효성
		$("#passCheck").on("keyup", function() {
			isValidPasswordCheck = checkPassword();
		});

		
		
		//회원가입 버튼 클릭
		$("#joinBtn").click(
				function() {

					//필수 입력 확인
					if ($("#user_id").val() == "") {
						alert("아이디는 필수입력입니다.");
						$("#user_id").focus();
						return;
					}//end if

					if ($("#nick_name").val() == "") {
						alert("닉네임은 필수입력입니다.");
						$("#nick_name").focus();
						return;
					}//end if

					if ($("#name").val() == "") {
						alert("이름은 필수입력입니다.");
						$("#name").focus();
						return;
					}//end if

					if ($("#pass").val() == "") {
						alert("비밀번호는 필수입력입니다.");
						$("#pass").focus();
						return;
					}//end if

					if ($("#passCheck").val() == "") {
						alert("비밀번호 확인은 필수입력입니다.");
						$("#passCheck").focus();
						return;
					}//end if

					if ($("#email").val() == "") {
						alert("이메일은 필수입력입니다.");
						$("#email").focus();
						return;
					}//end if

					if ($("#tel").val() == "") {
						alert("전화번호는 필수입력입니다.");
						$("#tel").focus();
						return;
					}//end if

					if ($("#birth_date").val() == "") {
						alert("생년월일은 필수입력입니다.");
						$("#birth_date").focus();
						return;
					}//end if

					if ($("#zipcode").val() == "") {
						alert("우편번호는 필수입력입니다.");
						return;
					}//end if

					if ($("#addr").val() == "") {
						alert("주소는 필수입력입니다.");
						return;
					}//end if 

					
					//체크 여부 확인
					if ($('#lb_chk_age').is(':checked') == false
							|| $('#lb_chk_service').is(':checked') == false
							|| $('#lb_chk_privacy').is(':checked') == false) {
						alert("필수동의가 체크되지 않았습니다.");
						return;
					}//end if

					
					//유효하지 않은 값으로 가입하는 것을 막음
					if (!isValidId || !isValidNickname || !isValidEmail
							|| !isValidPassword || !isValidPasswordCheck) {
						alert("필수 항목을 확인해주세요");
						return; // 조건을 만족하지 않을 때 함수 종료
					}

					$("#joinFrm").submit();
				});// click
	});// ready
</script>
</head>

<body class="jkJoin">
	<div id="wrap">

		<div id="header">
			<div class="joinTop">
				<h2 style="font-family: 'Noto Sans KR', sans-serif;">
					MOVIEPLANET
				</h2>
			</div>

			<h3 class="skip">글로벌 메뉴</h3>
			<ul class="gnb f_clear">
				<li><a href="mainPage.do">홈</a></li>
			</ul>

			<h3 class="skip">회원 형태별 가입</h3>

		</div>



		<input type="hidden" id="hdnEduId" value="" />
		<div id="container" class="mbrRegist">
			<h3 class="skip">개인회원 가입 정보</h3>
			<fieldset>
				<legend>개인회원 가입</legend>
				<form action="join_process.do" name="joinFrm" id="joinFrm" method="post">
					<!-- 약관 동의 -->
					<input data-val="true"
						data-val-required="&lt;p class=&quot;failure&quot;>필수 정보입니다.&lt;/p>"
						id="M_Id" name="M_Id" type="hidden" value="" /><input id="OEM_No"
						name="OEM_No" type="hidden" value="1" /><input id="DI_Code"
						name="DI_Code" type="hidden" value="" /><input id="getCertifynum"
						name="getCertifynum" type="hidden" value="" /><input
						id="CertifyReCall" name="CertifyReCall" type="hidden" value="" /><input
						id="ReSubmit" name="ReSubmit" type="hidden" value="" /><input
						id="CertifyCode" name="CertifyCode" type="hidden" value="" /><input
						id="Aptitute_Stat" name="Aptitute_Stat" type="hidden" value="" /><input
						id="CheckParamString" name="CheckParamString" type="hidden"
						value="8d3bc0d8e98202294a311db74c5eb05d" /><input id="Re_Url"
						name="Re_Url" type="hidden" value="" />
						
					<!-- 소셜 로그인 -->
					<div class="row_group mbr_info">
						<h4>회원가입하고 다양한 혜택을 누리세요!</h4>
						<p class="subTx">
							<strong>*</strong> 필수 입력 정보입니다.
						</p>
						<div class="row mbr_name">
							<div class="col_1">
								<i class="icon required" aria-hidden="hidde">*</i>
							</div>
							<div class="col_2">
								<input type="text" id="name" name="name" placeholder="이름 (실명)" maxlength="12">
								<div class="notice_msg" id="notice_msg_name"></div>
							</div>
						</div>
						<div class="row mbr_id">
							<div class="col_1">
								<i class="icon required" aria-hidden="hidde">*</i>
							</div>
							<div class="col_2"><!--id가 user_id인 요소의 다음 형제 요소 중 class가 error-message인 요소-->
								<input type="text" id="user_id" name="user_id" placeholder="아이디 (3~16자의 영문, 숫자, 특수문자(_,-)만 사용가능)" maxlength="12">
								<div class="error-message" id="error_id"></div>
							</div>
						</div>
						<div class="row mbr_id">
							<div class="col_1">
								<i class="icon required" aria-hidden="hidde">*</i>
							</div>
							<div class="col_2">
								<input type="text" id="nick_name" name="nick_name" placeholder="닉네임 (1~10자의 한글, 영문, 숫자, 밑줄(_))만 사용가능" maxlength="12">
								<div class="error-message" id="error_nickname"></div>
							</div>
						</div>
						

						<div class="row mbr_passwdChk">
							<div class="col_1">
								<i class="icon required" aria-hidden="hidde">*</i>
							</div>
							<div class="col_2">
								<input type="password" id="pass" name="pass" placeholder="비밀번호 (4~16자의 영문, 숫자)만 사용가능" class="mbr_name devReadOnly dev-name" maxlength="12">
								<div class="error-message" id="error_pass"></div>
							</div>
						</div>
						
						<div class="row mbr_passwdChk">
							<div class="col_1">
								<i class="icon required" aria-hidden="hidde">*</i>
							</div>
							<div class="col_2">
								<input type="password" id="passCheck" name="passCheck" placeholder="비밀번호 확인" class="mbr_name devReadOnly dev-name" maxlength="12">
								<div class="error-message" id="error_passCheck"></div>
							</div>
						</div>
						


						<div class="row mbr_email">
							<div class="col_1">
								<i class="icon required" aria-hidden="hidde">*</i>
							</div>
							<div class="col_2">
								<input type="text" id="email" name="email" placeholder="이메일 (example@example.com)" class="mbr_email_id dev-mail" size="8" maxlength="30">
								<div class="error-message" id="error_email"></div>
								<input type="hidden" id="Email_ID" name="Email_ID" /> 
								<input type="hidden" id="Email_Addr" name="Email_Addr" />
							</div>
						</div>
						<div class="row mbr_phone">
							<div class="col_1">
								<i class="icon required" aria-hidden="hidde">*</i>
							</div>
							<div class="col_2">
								<input type="text" id="tel" name="tel" size="4" placeholder="휴대폰번호" maxlength="13" 
								oninput="telValidation(this)">
								<div class="notice_msg" id="notice_msg_phone"></div>
								<input type="hidden" id="M_Phone1" name="M_Phone1" /> 
								<input type="hidden" id="M_Phone2" name="M_Phone2" /> 
								<input type="hidden" id="M_Phone3" name="M_Phone3" />
							</div>
						</div>
						<label for="birth_date" style="font-size: 14px; color: #999; margin-bottom:5px">생일</label>
						<div class="row mbr_phone">
							<div class="col_1">
								<i class="icon required" aria-hidden="hidde">*</i>
							</div>
							<div class="col_2">
								<input type=date id="birth_date" name="birth_date" size="4" placeholder="생일">
							</div>
						</div>

						
							<div class="row mbr_phone">
							<div class="col_1">
								<i class="icon required" aria-hidden="hidde">*</i>
							</div>
							<div class="col_2">
								<input type="text" readonly="readonly" id="zipcode" name="zipcode" size="4" placeholder="우편번호" maxlength="13">
								<button type="button" class="button buttonSendCertification" id="kakao">
									<span>주소 검색</span>
								</button>
							</div>
						</div>
					

						<div class="row mbr_addr">
							<div class="col_1">
								<i class="icon required" aria-hidden="hidde">*</i>
							</div>
							<div class="col_addr">
								<input type="text" id="addr" readonly="readonly" name="addr" placeholder="주소"/>
							</div>

							<div class="col_1">
								<label for="M_Addr2"></label>
							</div>
							<div class="col_addr2">
								<input type="text" id="detail_addr" name="detail_addr" placeholder="상세주소"/>
							</div>
						</div>
					</div>
					
					
					
					
					<h4 class="skip">약관 동의</h4>
					<div class="row_group line_all policy">
						<div class="row policy_check_all">
							<input type="checkbox" id="lb_chk_all" name="allTerms" value="Y" class="mbrCheckOff">
							<label for="lb_chk_all" class="chk_all">
								<span class="txt">필수동의 항목에 모두 동의합니다.</span>
							</label>
						</div>
						<div class="row policy_check_service required">
							<input type="checkbox" id="lb_chk_age" name="terms"
								class="mbrCheckOn" value="Y"> <label for="lb_chk_age"
								class="chk_age"><strong>[필수]</strong> 만 15세 이상입니다 </label>
						</div>
						<div class="row policy_check_service required">
							<input type="checkbox" id="lb_chk_service" name="terms" class="mbrCheckOn" value="Y"> 
								<label for="lb_chk_service" class="chk_service"><strong>[필수]</strong> 이용약관 동의 
									<a href="#DevPolicyService" class="mbrBtnPolicy" onclick="toggleDiv()">내용보기</a>
								</label>
								<div id="DevPolicyService" class="policyTplBox">
									<div class="pvsSec pvsCntTp">
									<ol>
										<li>
											<dl>
												<dt>
													<a name="gg01" id="gg01"></a><strong>제 1 조 (목적) </strong>
												</dt>
												<dd>본 약관은 잡코리아(이하 "회사")가 운영하는 "서비스"를 이용함에 있어 "회사"와 회원간의
													이용 조건 및 제반 절차, 권리, 의무 및 책임사항, 기타 필요한 사항을 규정함을 목적으로 한다.</dd>
											</dl>
										</li>
										<li>
											<dl>
												<dt>
													<a name="gg02" id="gg02"></a><strong>제 2 조 (용어의
														정의) </strong>
												</dt>
												<dd>
													<p>이 약관에서 사용하는 용어의 정의는 아래와 같다.</p>
													<ol>
														<li>① "사이트"라 함은 회사가 서비스를 "회원"에게 제공하기 위하여 컴퓨터 등
															정보통신설비를 이용하여 설정한 가상의 영업장 또는 회사가 운영하는 웹사이트, 모바일웹, 앱 등의
															서비스를 제공하는 모든 매체를 통칭하며, 통합된 하나의 회원 계정(아이디 및 비밀번호)을 이용하여
															서비스를 제공받을 수 있는 아래의 사이트를 말한다.
															<ul>
																<li>- www.jobkorea.co.kr</li>
																<li>- www.albamon.com</li>
																<li>- www.campusmon.com</li>
																<li>- m.jobkorea.co.kr</li>
																<li>- m.albamon.com</li>
																<li>- learning.jobkorea.co.kr</li>
															</ul>
														</li>
														<li>② "서비스"라 함은 회사가 운영하는 사이트를 통해 개인이 구직, 교육 등의 목적으로
															등록하는 자료를 DB화하여 각각의 목적에 맞게 분류 가공, 집계하여 정보를 제공하는 서비스와 사이트에서
															제공하는 모든 부대 서비스를 말한다.</li>
														<li>③ "회원"이라 함은 "회사"가 제공하는 서비스를 이용하거나 이용하려는 자로, 페이스북
															등 외부 서비스 연동을 통해 "회사"와 이용계약을 체결한자 또는 체결하려는 자를 포함하며, 아이디와
															비밀번호의 설정 등 회원가입 절차를 거쳐 회원가입확인 이메일 등을 통해 회사로부터 회원으로 인정받은
															"개인회원"을 말한다.</li>
														<li>④ "아이디"라 함은 회원가입 시 회원의 식별과 회원의 서비스 이용을 위하여 "회원"이
															선정하고 "회사"가 부여하는 문자와 숫자의 조합을 말한다.</li>
														<li>⑤ "비밀번호"라 함은 위 제4항에 따라 회원이 회원가입시 아이디를 설정하면서 아이디를
															부여받은 자와 동일인임을 확인하고 "회원"의 권익을 보호하기 위하여 "회원"이 선정한 문자와 숫자의
															조합을 말한다.</li>
														<li>⑥ "비회원"이라 함은 회원가입 절차를 거치지 않고 "회사"가 제공하는 서비스를
															이용하거나 하려는 자를 말한다.</li>
													</ol>
												</dd>
											</dl>
										</li>
										<li>
											<dl>
												<dt>
													<a name="gg03" id="gg03"></a><strong>제 3 조 (약관의
														명시와 개정) </strong>
												</dt>
												<dd>
													<ol>
														<li>① "회사"는 이 약관의 내용과 상호, 영업소 소재지, 대표자의 성명, 사업자등록번호,
															연락처 등을 "회원"이 알 수 있도록 초기 화면에 게시하거나 기타의 방법으로 "회원"에게 공지해야
															한다.</li>
														<li>② "회사"는 약관의 규제에 관한 법률, 전기통신기본법, 전기통신사업법, 정보통신망
															이용촉진 및 정보보호 등에 관한 법률 등 관련법을 위반하지 않는 범위에서 이 약관을 개정할 수 있다.</li>
														<li>③ "회사"가 약관을 개정할 경우에는 적용일자 및 개정사유를 명시하여 현행약관과 함께 그
															개정약관의 적용일자 7일 전부터 적용일자 전일까지 공지한다. 단 "회원"의 권리, 의무에 중대한 영향을
															주는 변경의 경우에는 적용일자 30일 전부터 공지하도록 한다.</li>
														<li>④ "회원"은 변경된 약관에 대해 거부할 권리가 있다. "회원"은 변경된 약관이 공지된
															지 15일 이내에 거부의사를 표명할 수 있다. "회원"이 거부하는 경우 본 서비스 제공자인 "회사"는
															15일의 기간을 정하여 "회원"에게 사전 통지 후 당해 "회원"과의 계약을 해지할 수 있다. 만약,
															"회원"이 거부의사를 표시하지 않거나, 전항에 따라 시행일 이후에 "서비스"를 이용하는 경우에는 동의한
															것으로 간주한다.</li>
													</ol>
												</dd>
											</dl>
										</li>
										<li>
											<dl>
												<dt>
													<a name="gg04" id="gg04"></a><strong>제 4 조 (약관의
														해석) </strong>
												</dt>
												<dd>
													<ol>
														<li>① 이 약관에서 규정하지 않은 사항에 관해서는 약관의 규제에 관한 법률, 전기통신기본법,
															전기통신사업법, 정보통신망 이용촉진 및 정보보호 등에 관한 법률 등의 관계법령에 따른다.</li>
														<li>② 각 사이트 및 서비스 이용약관이 있는 경우에는 서비스 이용약관이 우선한다.</li>
														<li>③ "회원"이 "회사"와 개별 계약을 체결하여 서비스를 이용하는 경우에는 개별 계약이
															우선한다.</li>
													</ol>
												</dd>
											</dl>
										</li>
										<li>
											<dl>
												<dt>
													<a name="gg05" id="gg05"></a><strong>제 5 조 (이용계약의
														성립) </strong>
												</dt>
												<dd>
													<ol>
														<li>① "회사"의 서비스 이용계약(이하 "이용계약")은 서비스를 이용하고자 하는 자가 본
															약관과 개인정보처리방침을 읽고 "동의" 또는 "확인" 버튼을 누른 경우 본 약관에 동의한 것으로
															간주한다.</li>
														<li>② 제1항 신청에 있어 "회사"는 "회원"의 종류에 따라 전문기관을 통한 실명확인 및
															본인인증을 요청할 수 있으며, "회원"은 본인인증에 필요한 이름, 생년월일, 연락처 등을 제공하여야
															한다.</li>
														<li>③ "회원"이 이용신청(회원가입 신청) 작성 후에 "회사"가 웹상의 안내 및 전자메일로
															"회원"에게 통지함으로써 이용계약이 성립된다.</li>
														<li>④ 페이스북 등 외부 서비스와의 연동을 통해 이용계약을 신청할 경우, 본 약관과
															개인정보처리방침, 서비스 제공을 위해 "회사"가 "회원"의 외부 서비스 계정 정보 접근 및 활용에
															"동의" 또는 "확인" 버튼을 누르면 "회사"가 웹상의 안내 및 전자메일로 "회원"에게 통지함으로써
															이용계약이 성립된다.</li>
													</ol>
												</dd>
											</dl>
										</li>
										<li>
											<dl>
												<dt>
													<a name="gg06" id="gg06"></a><strong>제 6 조 (이용신청의
														승낙과 제한) </strong>
												</dt>
												<dd>
													<ol>
														<li>① "회사"는 전조의 규정에 의한 이용신청 고객에 대하여 업무수행상 또는 기술상 지장이
															없는 경우에는 원칙적으로 접수순서에 따라 서비스 이용을 승낙한다.</li>
														<li>② "회사"는 아래사항에 해당하는 경우에 대해서는 서비스 이용신청을 승낙하지 아니한다.
															<ol>
																<li>1. 실명이 아니거나 타인의 명의를 이용하여 신청한 경우</li>
																<li>2. 이용계약 신청서의 내용을 허위로 기재하거나 "회사"가 제시하는 내용을 기재하지
																	않은 경우</li>
																<li>3. 만 15세 미만의 아동이 신청한 경우. 다만, 만 13세 이상 만 15세 미만의
																	아동으로서 노동부장관 발급의 취직인허증이 있는 경우에는 그러하지 아니한다.</li>
																<li>4. 이용자의 귀책사유로 인하여 승인이 불가능하거나 기타 규정한 제반 사항을 위반하여
																	신청하는 경우</li>
															</ol>
														</li>
														<li>③ "회사"는 아래사항에 해당하는 경우에는 그 신청에 대하여 승낙제한 사유가 해소될
															때까지 승낙을 유보할 수 있다.
															<ol>
																<li>1. "회사"가 설비의 여유가 없는 경우</li>
																<li>2. "회사"의 기술상 지장이 있는 경우</li>
																<li>3. 기타 "회사"의 귀책사유로 이용승낙이 곤란한 경우</li>
															</ol>
														</li>
													</ol>
												</dd>
											</dl>
										</li>
										<li>
											<dl>
												<dt>
													<a name="gg07" id="gg07"></a><strong>제 7 조 (서비스의
														내용) </strong>
												</dt>
												<dd>
													<ol>
														<li>① "회사"는 제2조 2항의 서비스를 제공할 수 있으며 그 내용은 다음 각 호와 같다.
															<ol>
																<li>1. 이력서 등록 및 인재정보 게재 서비스</li>
																<li>2. 온라인 입사지원 서비스</li>
																<li>3. 헤드헌팅/아웃소싱 서비스</li>
																<li>4. 구인/구직과 관련된 제반 서비스</li>
																<li>5. 기업리뷰 등록과 조회 서비스</li>
																<li>6. 기업정보 조회와 관련된 제반 서비스</li>
																<li>7. 교육과 관련된 제반 서비스</li>
																<li>8. 이용자간의 교류와 소통에 관련한 서비스</li>
																<li>9. 자료거래에 관련한 서비스</li>
																<li>10. 기타"회사"가 추가 개발, 편집, 재구성하거나 제휴계약 등을 통해 “회원”에게
																	제공하는 일체의 서비스</li>
															</ol>
														</li>
														<li>② "회사"는 필요한 경우 서비스의 내용을 추가 또는 변경할 수 있다. 단, 이 경우
															"회사"는 추가 또는 변경내용을 "회원"에게 공지해야 한다.</li>
													</ol>
												</dd>
											</dl>
										</li>
										<li>
											<dl>
												<dt>
													<a name="gg08" id="gg08"></a><strong>제 8 조 (회원,
														이력서 및 게시물 등의 정보) </strong>
												</dt>
												<dd>
													<ol>
														<li>① "회원"의 이력서는 개인이 회원가입 또는 이력서 작성 및 수정시 희망한 형태로 등록
															및 제공된다.</li>
														<li>② "회사"는 "회원"이 이력서의 인재정보 등록/미등록 지정, 이력서상의 연락처 제공
															여부를 자유롭게 선택할 수 있도록 하여야 한다.</li>
														<li>③ "회사"는 "회원"이 이력서의 인재정보 등록 및 제공을 희망했을 경우, 기업회원,
															서치펌회원의 이력서 열람 및 각종 포지션 제안에 동의한 것으로 간주하며 "회사"는 기업회원,
															서치펌회원에게 유료로 이력서 열람 서비스를 제공할 수 있다. 다만, 연락처 각 항목이 비공개로 지정된
															경우 해당 항목별 연락처를 노출할 수 없다.</li>
														<li>④ "회사"는 안정적인 서비스를 제공하기 위해 테스트 및 모니터링 용도로 "사이트"
															운영자가 이력서 및 게시물 등의 정보를 열람하도록 할 수 있다.</li>
														<li>⑤ "회사"는 "회원"의 선택에 의하여 등록 및 제공되는 이력서 및 게시물 등의 정보를
															기준으로 구직활동에 보다 유익한 서비스를 제공하기 위하여 이를 개발, 편집, 재구성한 통계 자료로 활용
															할 수 있다.</li>
													</ol>
												</dd>
											</dl>
										</li>
										<li>
											<dl>
												<dt>
													<a name="gg9" id="gg9"></a><strong>제 9 조 (제휴를 통한
														서비스) </strong>
												</dt>
												<dd>
													<ol>
														<li>① "회사"는 제휴 관계를 체결한 여타 인터넷 웹 사이트 및 채용박람회 또는 신문, 잡지
															등의 오프라인 매체를 통해 사이트에 등록한 "회원"의 이력서 정보가 열람될 수 있도록 서비스를 제공할
															수 있다. 단, 제휴 서비스를 통해 노출되는 이력서의 연락처 정보는 “회원”이 이력서 등록 시 선택한
															연락처 공개 여부에 따라 제공된다.</li>
														<li>② "회사"는 제휴를 통해 타 사이트 및 매체에 등록될 수 있음을 고지해야 하며, 제휴
															사이트 목록을 사이트내에서 상시 열람할 수 있도록 해야 한다. 단, 등록의 형태가 "회사"가 직접
															구축하지 않고, 제휴사가 xml 또는 api 형태로 "회사"로부터 제공 받아 구축한 매체 목록은 본
															약관외 별도의 제휴리스트에서 열람할 수 있도록 한다.</li>
														<li>③ "회사"는 "회원"이 공개를 요청한 이력서에 한해 제휴를 맺은 타 사이트에 이력서
															정보를 제공한다. (본 약관 시행일 현재에는 제휴를 통한 타 사이트 및 매체는 없다.)</li>
														<li>④ 본 조 제 3항 제휴를 통한 사이트의 변동사항 발생 시 공지사항을 통해 고지 후 진행
															한다.</li>
													</ol>
												</dd>
											</dl>
										</li>
										<li>
											<dl>
												<dt>
													<a name="gg10" id="gg10"></a><strong>제 10 조 (서비스의
														요금) </strong>
												</dt>
												<dd>
													<ol>
														<li>① "회원" 가입과 이력서 등록은 무료이다. 다만 기업회원 또는 사이트에 방문한
															기업체에게 자신의 이력서 정보를 보다 효과적으로 노출시키기 위한 유료서비스 및 인성, 적성 검사 등
															회원 가입 목적 외 기타 서비스를 이용하기 위한 별도의 서비스는 유료로 제공될 수 있다.</li>
														<li>② "회사"는 유료서비스를 제공할 경우 사이트에 요금에 대해서 공지를 하여야 한다.</li>
														<li>③ "회사"는 유료서비스 이용금액을 서비스의 종류 및 기간에 따라 "회사"가 예고 없이
															변경할 수 있다. 다만, 변경 이전에 적용 또는 계약한 금액은 소급하여 적용하지 아니한다.</li>
													</ol>
												</dd>
											</dl>

										</li>
										<li>
											<dl>
												<dt>
													<a name="gg11" id="gg11"></a><strong>제 11 조 (서비스
														요금의 환불)</strong>
												</dt>
												<dd>
													<ol>
														<li>① "회사"는 다음 각 호에 해당하는 경우 이용요금을 환불한다. 단, 각 당사자의
															귀책사유에 따라 환불 조건이 달라질 수 있다.
															<ol>
																<li>1. 유료서비스 이용이 개시되지 않은 경우</li>
																<li>2. 네트워크 또는 시스템 장애로 서비스 이용이 불가능한 경우</li>
																<li>3. 유료서비스 신청 후 “회원”의 사정에 의해 서비스가 취소될 경우</li>
															</ol>
														</li>
														<li>② "회사"가 본 약관 제19조에 따라 가입해지/서비스중지/자료삭제를 취한 경우,
															“회사”는 “회원”에게 이용요금을 환불하지 않으며, 별도로 “회원”에게 손해배상을 청구할 수 있다.</li>
														<li>③ 이용료를 환불받고자 하는 회원은 고객센터로 환불을 요청해야 한다.</li>
														<li>④ "회사"는 환불 요건에 부합하는 것으로 판단될 경우, 각 서비스 환불 안내에 따라
															유료이용 계약 당시 상품의 정가 기준으로 서비스 제공된 기간에 해당하는 요금을 차감한 잔액을 환불한다.</li>
													</ol>
												</dd>
											</dl>
										</li>
										<li>
											<dl>
												<dt>
													<a name="gg12" id="gg12"></a><strong>제 12 조 (서비스
														이용시간) </strong>
												</dt>
												<dd>
													<ol>
														<li>① "회사"는 특별한 사유가 없는 한 연중무휴, 1일 24시간 서비스를 제공한다. 다만,
															"회사"는 서비스의 종류나 성질에 따라 제공하는 서비스 중 일부에 대해서는 별도로 이용시간을 정할 수
															있으며, 이 경우 "회사"는 그 이용시간을 사전에 "회원"에게 공지 또는 통지하여야 한다.</li>
														<li>② "회사"는 자료의 가공과 갱신을 위한 시스템 작업시간, 장애해결을 위한 보수작업
															시간, 정기 PM작업, 시스템 교체작업, 회선 장애 등이 발생한 경우 일시적으로 서비스를 중단할 수
															있으며 계획된 작업의 경우 공지란에 서비스 중단 시간과 작업 내용을 알려야 한다. 다만, "회사"가
															사전에 통지할 수 없는 부득이한 사유가 있는 경우 사후에 통지할 수 있다.</li>
													</ol>
												</dd>
											</dl>
										</li>
										<li>
											<dl>
												<dt>
													<a name="gg13" id="gg13"></a><strong>제 13 조 (서비스
														제공의 중지) </strong>
												</dt>
												<dd>
													<ol>
														<li>① "회사"는 다음 각 호에 해당하는 경우 서비스의 제공을 중지할 수 있다.
															<ol>
																<li>1. 설비의 보수 등 "회사"의 필요에 의해 사전에 "회원"들에게 통지한 경우</li>
																<li>2. 기간통신사업자가 전기통신서비스 제공을 중지하는 경우</li>
																<li>3. 기타 불가항력적인 사유에 의해 서비스 제공이 객관적으로 불가능한 경우</li>
															</ol>
														</li>
														<li>② 전항의 경우, "회사"는 기간의 정함이 있는 유료서비스 이용자들에게는 그 이용기간을
															연장하거나 환불 등의 방법으로 손실을 보상하여야 한다.</li>
													</ol>
												</dd>
											</dl>
										</li>
										<li>
											<dl>
												<dt>
													<a name="gg14" id="gg14"></a><strong>제 14 조 (정보의
														제공 및 광고의 게재) </strong>
												</dt>
												<dd>
													<ol>
														<li>① "회사"는 "회원"에게 서비스 이용에 필요가 있다고 인정되거나 서비스 개선 및
															회원대상의 서비스 소개 등의 목적으로 하는 각종 정보에 대해서 전자우편이나 서신우편을 이용한 방법으로
															제공할 수 있다.</li>
														<li>② "회사"는 제공하는 서비스와 관련되는 정보 또는 광고를 서비스 화면, 홈페이지,
															전자우편 등에 게재할 수 있으며, 광고가 게재된 전자우편을 수신한 "회원"은 수신거절을 "회사"에게 할
															수 있다.</li>
														<li>③ "회사"는 서비스상에 게재되어 있거나 본 서비스를 통한 광고주의 판촉활동에 "회원"이
															참여하거나 교신 또는 거래를 함으로써 발생하는 모든 손실과 손해에 대해 책임을 지지 않는다.</li>
														<li>④ 본 서비스의 "회원"은 서비스 이용 시 노출되는 광고게재에 대해 동의 하는 것으로
															간주한다.</li>
													</ol>
												</dd>
											</dl>
										</li>
										<li>
											<dl>
												<dt>
													<a name="gg15" id="gg15"></a><strong>제 15 조 (자료내용의
														책임과 "회사"의 정보 수정 권한) </strong>
												</dt>
												<dd>
													<ol>
														<li>① 자료내용은 "회원"이 등록한 개인정보 및 이력서와 사이트에 게시한 게시물을 말한다.</li>
														<li>② "회원"은 자료 내용 및 게시물을 사실에 근거하여 성실하게 작성해야 하며, 만일
															자료의 내용이 사실이 아니거나 부정확하게 작성되어 발생하는 모든 책임은 "회원"에게 있다. "회사"는
															"회원"이 작성한 게시물 등의 정확성 및 진실성을 보증하지 아니하며, 게시물에 대한 일체의 책임을
															부담하지 아니한다.</li>
														<li>③ 모든 자료내용의 관리와 작성은 "회원" 본인이 하는 것이 원칙이나 사정상 위탁 또는
															대행관리를 하더라도 자료내용의 책임은 "회원"에게 있으며 "회원"은 주기적으로 자신의 자료를 확인하여
															항상 정확하게 관리가 되도록 노력해야 한다.</li>
														<li>④ "회사"는 "회원"이 등록한 자료 내용에 오자, 탈자 또는 사회적 통념에 어긋나는
															문구와 내용이 있을 경우 이를 언제든지 수정할 수 있다.</li>
														<li>⑤ "회원"이 등록한 자료으로 인해 타인(또는 타법인)으로부터 허위사실 및 명예훼손
															등으로 삭제 요청이 접수된 경우 "회사"는 "회원"에게 사전 통지 없이 본 자료를 삭제할 수 있으며
															삭제 후 메일 등의 방법으로 통지할 수 있다.</li>
													</ol>
												</dd>
											</dl>
										</li>
										<li>
											<dl>
												<dt>
													<a name="gg16" id="gg16"></a><strong>제 16 조 (자료
														내용의 활용 및 취급) </strong>
												</dt>
												<dd>
													<ol>
														<li>① "회원"이 선택하거나 입력한 정보는 취업 및 관련 동향의 통계 자료로 구성, 활용될
															수 있으며 그 자료는 매체를 통한 언론 배포 또는 제휴사에게 제공될 수 있다. 단, 개인을 식별할 수
															있는 형태가 아니어야 한다.</li>
														<li>② 제8조 제3항에 따라 "사이트"에서 정당한 절차를 거쳐 기업회원, 서치펌회원이 열람한
															"회원"의 이력서 정보는 해당 기업의 인사자료이며 이에 대한 관리 권한은 해당 기업의 정책에 의한다.</li>
														<li>③ "회사"는 "사이트"의 온라인 입사지원 시스템을 통해 지원한 "회원"의 이력서 열람
															여부 및 기업회원이 제공한 채용정보에 입사지원한 구직자들의 각종 통계데이터를 "회원"에게 제공할 수
															있다.</li>
													</ol>
												</dd>
											</dl>
										</li>
										<li>
											<dl>
												<dt>
													<a name="gg17" id="gg17"></a><strong>제 17 조 ("회사"의
														의무) </strong>
												</dt>
												<dd>
													<ol>
														<li>① "회사"는 본 약관에서 정한 바에 따라 계속적, 안정적으로 서비스를 제공할 수 있도록
															최선의 노력을 다해야 한다.</li>
														<li>② "회사"는 서비스와 관련한 "회원"의 불만사항이 접수되는 경우 이를 즉시 처리하여야
															하며, 즉시 처리가 곤란한 경우에는 그 사유와 처리일정을 서비스 화면 또는 기타 방법을 통해 동
															"회원"에게 통지하여야 한다.</li>
														<li>③ "회사"는 유료 결제와 관련한 결제 사항 정보를 1년 이상 보존한다. 다만 회원
															자격이 없는 회원은 예외로 한다.</li>
														<li>④ 천재지변 등 예측하지 못한 일이 발생하거나 시스템의 장애가 발생하여 서비스가 중단될
															경우 이에 대한 손해에 대해서는 "회사"가 책임을 지지 않는다. 다만 자료의 복구나 정상적인 서비스
															지원이 되도록 최선을 다할 의무를 진다.</li>
														<li>⑤ "회원"의 자료를 본 서비스 이외의 목적으로 제3자에게 제공하거나 열람시킬 경우
															반드시 "회원"의 동의를 얻어야 한다.</li>
													</ol>
												</dd>
											</dl>
										</li>
										<li>
											<dl>
												<dt>
													<a name="gg18" id="gg18"></a><strong>제 18 조 ("회원"의
														의무) </strong>
												</dt>
												<dd>
													<ol>
														<li>① "회원"은 관계법령과 본 약관의 규정 및 기타 "회사"가 통지하는 사항을 준수하여야
															하며, 기타 "회사"의 업무에 방해되는 행위를 해서는 안 된다.</li>
														<li>② "회원"이 신청한 유료서비스는 등록 또는 신청과 동시에 "회사"와 채권, 채무 관계가
															발생하며, "회원"은 이에 대한 요금을 지정한 기일 내에 납부하여야 한다.</li>
														<li>③ "회원"이 결제 수단으로 신용카드를 사용할 경우 비밀번호 등 정보 유실 방지는
															"회원" 스스로 관리해야 한다. 단, "사이트"의 결함에 따른 정보유실의 발생에 대한 책임은 "회원"의
															의무에 해당하지 아니한다.</li>
														<li>④ "회원"은 서비스를 이용하여 얻은 정보를 "회사"의 사전동의 없이 복사, 복제,
															번역, 출판, 방송 기타의 방법으로 사용하거나 이를 타인에게 제공할 수 없다.</li>
														<li>⑤ "회원"은 본 서비스를 건전한 취업 및 경력관리 이외의 목적으로 사용해서는 안되며
															이용 중 다음 각 호의 행위를 해서는 안 된다.
															<ol>
																<li>1. 다른 회원의 아이디를 부정 사용하는 행위</li>
																<li>2. 범죄행위를 목적으로 하거나 기타 범죄행위와 관련된 행위</li>
																<li>3. 타인의 명예를 훼손하거나 모욕하는 행위</li>
																<li>4. 타인의 지적재산권 등의 권리를 침해하는 행위</li>
																<li>5. 해킹행위 또는 바이러스의 유포 행위</li>
																<li>6. 타인의 의사에 반하여 광고성 정보 등 일정한 내용을 계속적으로 전송하는 행위</li>
																<li>7. 서비스의 안정적인 운영에 지장을 주거나 줄 우려가 있다고 판단되는 행위</li>
																<li>8. 사이트의 정보 및 서비스를 이용한 영리 행위</li>
																<li>9. 그밖에 선량한 풍속, 기타 사회질서를 해하거나 관계법령에 위반하는 행위</li>
															</ol>
														</li>
													</ol>
												</dd>
											</dl>
										</li>
										<li>
											<dl>
												<dt>
													<a name="gg19" id="gg19"></a><strong>제 19 조 ("회원"의
														가입해지/서비스중지/자료삭제) </strong>
												</dt>
												<dd>
													<ol>
														<li>① "회원"은 언제든지 회원탈퇴 또는 이력서 등록을 해지하기 위해 고객센터 또는 회원탈퇴
															메뉴 등을 통하여 이용계약 해지 신청을 할 수 있으며, "회사"는 관련법 등이 정하는 바에 따라 이를
															처리한다.</li>
														<li>② 다음의 사항에 해당하는 경우 "회사"는 사전 동의없이 가입해지나 서비스 중지, 이력서
															삭제 조치를 취할 수 있다.
															<ol>
																<li>1. 회원의 의무를 성실하게 이행하지 않았을 때</li>
																<li>2. 규정한 유료서비스 이용 요금을 납부하지 않았을 때</li>
																<li>3. 본 서비스 목적에 맞지 않는 분야에 정보를 활용하여 사회적 물의가 발생한 때</li>
																<li>4. 회원이 등록한 정보의 내용이 사실과 다르거나 조작되었을 때</li>
																<li>5. 본 서비스와 관련하여 회사 또는 제3자의 명예를 훼손하였을 때</li>
																<li>6. 기타 위 각호에 준하는 사유가 발생하였을 때</li>
															</ol>
														</li>
														<li>③ "회원"이 유료서비스를 이용하는 중 "회사"의 책임에 의해 정상적인 서비스가 제공되지
															않을 경우 "회원"은 본 서비스의 해지를 요청할 수 있으며 "회사"는 기간의 정함이 있는 유료서비스의
															경우에는 해지일까지 이용일수를 1일 기준금액으로 계산하여 이용금액을 공제 후 환급하고, 이용건수의
															정함이 있는 유료서비스의 경우에는 기 사용분을 1건 기준금액으로 계산하여 이용금액을 공제후 환급한다.
														</li>
														<li>④ "회사"는 회원 가입이 해지된 경우에는 개인정보처리방침 중, 04.개인정보의 보유 및
															이용기간 규정에 따른다.</li>
														<li>⑤ 개인정보보호를 위하여 "회원"이 선택한 개인정보 보유기간(1년, 3년, 회원탈퇴시)
															동안 "사이트"를 이용하지 않은 경우, "아이디"를 "휴면계정"으로 분리하여 해당 계정의 이용을 중지할
															수 있다. 이 경우 "회사"는 "휴면계정 처리 예정일"로부터 30일 이전에 해당사실을 전자메일, 서면,
															SMS 중 하나의 방법으로 사전통지하며 "회원"이 직접 본인확인을 거쳐, 다시 "사이트" 이용
															의사표시를 한 경우에는 "사이트" 이용이 가능하다. "휴면계정"으로 분리 보관된 개인정보는 5년간 보관
															후 지체없이 파기한다.</li>
													</ol>
												</dd>
											</dl>
										</li>
										<li>
											<dl>
												<dt>
													<a name="gg20" id="gg20"></a><strong>제 20 조 (손해배상)
													</strong>
												</dt>
												<dd>
													<ol>
														<li>① "회사"가 이 약관의 규정을 위반한 행위로 "회원"에게 손해를 입히거나 기타
															"회사"가 제공하는 모든 서비스와 관련하여 "회사"의 책임 있는 사유로 인해 이용자에게 손해가 발생한
															경우 "회사"는 그 손해를 배상하여야 한다.</li>
														<li>② "회사"는 책임 있는 사유로 제공한 정보가 사실과 달라 "회원"이 손해를 입었을
															경우에 "회사"는 그 손해를 배상하여야 한다.</li>
														<li>③ "회원"이 이 약관의 규정을 위반한 행위로 "회사" 및 제3자에게 손해를 입히거나
															"회원"의 책임 있는 사유에 의해 "회사" 및 제3자에게 손해를 입힌 경우에는 "회원"은 그 손해를
															배상하여야 한다.</li>
														<li>④ 타 회원(개인회원, 기업회원, 서치펌회원 모두 포함)의 귀책사유로 "회원"의 손해가
															발생한 경우 "회사"는 이에 대한 배상 책임이 없다.</li>
													</ol>
												</dd>
											</dl>
										</li>
										<li>
											<dl>
												<dt>
													<a name="gg21" id="gg21"></a><strong>제 21 조 (양도
														금지) </strong>
												</dt>
												<dd>
													<p>“회원”의 서비스 받을 권리는 제3자에게 양도, 대여, 증여 등으로 사용할 수 없다.</p>
												</dd>
											</dl>
										</li>
										<li>
											<dl>
												<dt>
													<a name="gg22" id="gg22"></a><strong>제 22 조 (이용요금
														오류의 조정) </strong>
												</dt>
												<dd>
													<p>"회사"는 이용요금과 관련하여 오류가 있는 경우에 "회원"의 요청, 또는 "회사"의 사전
														통지에 의하여 다음에 해당하는 조치를 취한다.</p>
													<ol>
														<li>1. 과다납입한 요금에 대하여는 그 금액을 반환한다. 다만, "회원"이 동의할 경우 다음
															달에 청구할 요금에서 해당 금액만큼을 감하여 청구한다.</li>
														<li>2. 요금을 반환받아야 할 "회원"이 요금체납이 있는 경우에는 반환해야 할 요금에서 이를
															우선 공제하고 반환한다.</li>
														<li>3. "회사"는 과소청구액에 대해서는 익월에 합산청구한다.</li>
													</ol>
												</dd>
											</dl>
										</li>
										<li>
											<dl>
												<dt>
													<a name="gg23" id="gg23"></a><strong>제 23 조 ("회원"의
														개인정보보호) </strong>
												</dt>
												<dd>"회사"는 "회원"의 개인정보보호를 위하여 노력해야 한다. "회원"의 개인정보보호에 관해서는
													정보통신망이용촉진 및 정보보호 등에 관한 법률, 개인정보보호법에 따르고, "사이트"에 "개인정보처리방침"을
													고지한다.</dd>
											</dl>
										</li>
										<li>
											<dl>
												<dt>
													<a name="gg24" id="gg24"></a><strong>제 24 조 (신용정보의
														제공 활용 동의) </strong>
												</dt>
												<dd>
													<ol>
														<li>① "회사"가 회원가입과 관련하여 취득한 "회원"의 개인신용정보를 타인에게 제공하거나
															활용하고자 할 때에는 신용정보의 이용 및 보호에 관한 법률 제23조의 규정에 따라 사전에 그 사유 및
															해당기관 또는 업체명 등을 밝히고 해당 "회원"의 동의를 얻어야 한다.</li>
														<li>② 본 서비스와 관련하여 "회사"가 "회원"으로부터 신용정보의 이용 및 보호에 관한
															법률에 따라 타인에게 제공 활용에 동의를 얻은 경우 "회원"은 "회사"가 신용정보 사업자 또는 신용정보
															집중기관에 정보를 제공하여 "회원"의 신용을 판단하기 위한 자료로 활용하거나, 공공기관에서 정책자료로
															활용되도록 정보를 제공하는 데 동의한 것으로 간주한다.</li>
													</ol>
												</dd>
											</dl>
										</li>
										<li>
											<dl class="nobg">
												<dt>
													<a name="gg25" id="gg25"></a><strong>제 25 조 (분쟁의
														해결) </strong>
												</dt>
												<dd>
													<ol>
														<li>① "회사"와 "회원"은 서비스와 관련하여 발생한 분쟁을 원만하게 해결하기 위하여 필요한
															모든 노력을 하여야 한다.</li>
														<li>② 전항의 노력에도 불구하고, 동 분쟁에 관한 소송은 "회사"의 주소지 관할법원으로
															한다.</li>
													</ol>
												</dd>
											</dl>
										</li>
										<li class="etc">
											<dl class="nobg">
												<dt>
													<strong>부칙</strong>
												</dt>
												<dd>
													<ul>
														<li>- 이 약관은 2022년 4월 27일부터 시행한다.</li>
													</ul>
												</dd>
											</dl>
										</li>
									</ol>
								</div>
							</div>
						</div>
						<div class="row policy_check_privacy required">
							<input type="checkbox" id="lb_chk_privacy" name="terms" class="mbrCheckOn" value="1"> 
							<label for="lb_chk_privacy" class="chk_privacy"><strong>[필수]</strong> 개인정보 수집 및 이용 동의 
								<a href="#DevPolicyPrivacy" class="mbrBtnPolicy" onclick="toggleDiv2()">내용보기</a>
							</label>
							<div id="DevPolicyPrivacy" class="policyTplBox">
								<div class="pvsSec pvsTpList">
									<!-- 7.0 변경 -->
									<p>
										잡코리아 및 알바몬 서비스 이용을 위해 아래와 같이 개인정보를 수집 및 이용합니다. <br> 동의를
										거부할 권리가 있으며, 동의 거부 시 잡코리아 및 알바몬 회원서비스 이용이 불가합니다.
									</p>

									<table class="policy-table">
										<colgroup>
											<col width="33%">
											<col width="33%">
											<col width="*">
										</colgroup>
										<thead>
											<tr>
												<th>목적</th>
												<th>항목</th>
												<th>보유 및 이용기간</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>본인여부 확인, 각종 맞춤형 서비스 제공, 서비스 개선 및 신규 서비스 개발을 위한 통계
													활용, 계약이행 및 약관변경 등의 고지를 위한 연락, 본인의사확인 및 민원 등의 고객불만처리</td>
												<td>[필수] 이름, 아이디, 비밀번호, 휴대폰번호, 이메일<br /> [선택]
													(알바몬:생년월일, 성별, 주소, 닉네임)
												</td>
												<td rowspan="2"><font
													style="text-decoration: underline; font-size: 14px;">회원
														탈퇴 시 즉시 파기</font></td>
											</tr>
											<tr>
												<td>공모전 정보 등록자 확인 및 고객불만 처리, 각종 맞춤형 서비스 제공</td>
												<td>[필수] 담당자명, 이메일<br /> [선택] 전화번호
												</td>
											</tr>
											<tr>
												<td>입사지원 및 이력서 공개 등 취업활동 서비스 제공, 각종 맞춤형 서비스 제공</td>
												<td>[필수] 이름, 생년월일, 성별, 휴대폰번호, 학력사항 또는 경력사항 <br /> [선택]
													이메일, 전화번호, 주소, 홈페이지, 사진, 인턴·대외활동, 교육이수, 자격증, 수상, 해외경험, 어학,
													포트폴리오, 취업우대·병역(보훈대상, 취업보호대상, 고용지원금대상, 장애, 병역), 자기소개서
												</td>
												<td><font
													style="text-decoration: underline; font-size: 14px;">이력서
														삭제 또는 회원 탈퇴 시 즉시 파기</font></td>
											</tr>
										</tbody>
									</table>
									<!-- //7.0 변경 -->
								</div>
							</div>
						</div>
					</div>

					<!-- 회원 가입 버튼 -->
					<div class="row_group line_none regist_complete" style="position: relative;">
						<div class="row">
							<input type="button" value="가입하기" id="joinBtn" class="mbrBtnRegist" style="border: none;">
						</div>
					</div>
				</form>
			</fieldset>


		</div>

		<div id="footer">
			<p id="copyright">
			© MOVIEPLANET LLC. All rights reserved.
			</p>
			<map name="copyright">
				<area shape="rect" coords="10,0,80,19"
					href="https://www.jobkorea.co.kr" target="_blank" alt="잡코리아">
			</map>
		</div>
	</div>

	<div
		style="position: absolute; left: -5000px; overflow: hidden; display: none;">
	</div>
	<div
		style="position: absolute; left: -5000px; overflow: hidden; display: none;">
	</div>
</body>
</html>