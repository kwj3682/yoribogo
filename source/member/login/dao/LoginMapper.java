package kr.co.yoribogo.member.login.dao;

import kr.co.yoribogo.member.vo.MemberVO;

public interface LoginMapper {
	
	MemberVO selectLogin(MemberVO member);
	
}
