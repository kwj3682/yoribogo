/*
create table tb_member(
    id varchar2(20) primary key,
    pass varchar2(20) not null,
    name varchar2(30) not null    
);

insert into tb_member(id, pass, name) values('admin', '1111', '관리자');
insert into tb_member(id, pass, name) values('kim', '1111', '김연아');
 */
package kr.co.yoribogo.member.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.yoribogo.common.db.MyAppSqlConfig;
import kr.co.yoribogo.member.login.dao.LoginMapper;
import kr.co.yoribogo.member.vo.MemberVO;

@WebServlet("/login/login.do")
public class LoginController extends HttpServlet {
	private LoginMapper mapper;
	public LoginController() {
		mapper = MyAppSqlConfig.getSqlSession().getMapper(LoginMapper.class);
	}
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberVO member = new MemberVO(); 
		member.setId(request.getParameter("id"));
		member.setPass(request.getParameter("pass"));
		
		// DB에서 해당 정보를 가져온다
		MemberVO user = mapper.selectLogin(member);
		// 로그인 실패 : 사용자 입력이 정확하지 않은 경우
		if(user == null) {
			// 다시 로그인 하도록 로그인폼으로 보낸다
			response.sendRedirect("loginform.do");
			return;
		}
		
		// 로그인 성공시 : 세션에 사용자 정보를 등록시킨다!!!!
		HttpSession session = request.getSession();
		// DB에서 가져온 정보를 session 공유영역에 올린다.
		// 즉, 공유영역에 이름객체가 등록된 경우, 로그인한 상태임을 알 수 있다.
		session.setAttribute("user", user);
		
		// 로그인 성공시 메인페이지로 이동(url변경)
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	
	}
	
}
