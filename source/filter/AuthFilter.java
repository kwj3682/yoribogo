/** 로그인 세션 인증   */
package kr.co.yoribogo.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.yoribogo.member.vo.MemberVO;

public class AuthFilter implements Filter {
   private List<String> list = new ArrayList<>();
   @Override
   public void init(FilterConfig filterConfig) throws ServletException {
      String pages = filterConfig.getInitParameter("pages");
      String[] arr = pages.split(";");
      // 출력시 앞 뒤 공백 포함하여 출력되므로 제거해주기 trim()
      for(String page : arr) {
//         System.out.println("-" + page.trim() + "-");
         list.add(page.trim());
      }
   }
   
   @Override
   public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
      /*
       *  사용자가 호출한 페이지 주소가 로그인이 필요한 페이지인지 확인하고
       *  만약, 로그인이 필요한 페이지라면 아래의 로그인 체크로직을 확인한다.
      */
      HttpServletRequest request = (HttpServletRequest)req;
      HttpServletResponse response = (HttpServletResponse)res;
      
      // 프로젝트 경로  포함되어 있음.
      // 가져온 uri주소는 프로젝트명까지 포함되어 있으므로 web.xml에 설정한 주소와 비교하려면 프로젝트 경로를 제거해야한다.******
      String uri = request.getRequestURI();
      System.out.println("uri:"+uri);
      
      // uri에서 프로젝트 경로 제거하기
      String contextPath = request.getContextPath();
      System.out.println("contextPath : " + contextPath);      // /10_servletjsp_board
      uri = uri.substring(contextPath.length());               // /login/loginform.do
      System.out.println("uri:"+uri);
      
      // web.xml 주소와 비교하기
      int index = list.indexOf(uri);   // -1 : web.xml에서 못찾은 경우. 로그인 했을때 접근이 가능한 페이지..
      
      //로그인 체크 : 로그인 폼으로 이동
      if(index == -1) {
            HttpSession session = request.getSession();
            MemberVO user = (MemberVO)session.getAttribute("user");
               
            // 로그인 하지 않은 상태 -> 로그인 폼으로 이동
            if(user== null) {
               response.sendRedirect(request.getContextPath()+"/login/loginform.do");
               return;
            }
      }
         
      //로그인이 되어있으면 원래 호출하려던거 부름
        chain.doFilter(req, res);
    }
}