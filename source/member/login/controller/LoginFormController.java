package kr.co.yoribogo.member.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login/loginform.do")
public class LoginFormController extends HttpServlet {
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//  loginform.jsp를 호출하는 클래스
		request.getRequestDispatcher("loginform.jsp").forward(request, response);
		
		
	
	}
}
