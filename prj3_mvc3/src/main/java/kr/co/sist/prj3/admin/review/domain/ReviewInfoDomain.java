package kr.co.sist.prj3.admin.review.domain;

import java.sql.Clob;

public class ReviewInfoDomain {

	private String title; //제목
	private String nick_name; // 닉네임
	private String profile; // 프로필
	private String input_date;//작성일
	private String profile_msg; // 자기소개
	private int hits;// 조회수
	private int like_total; // 총 좋아요
	private int sum_total; // 총 댓글수
	private Clob content; // 내용
	
}//class
