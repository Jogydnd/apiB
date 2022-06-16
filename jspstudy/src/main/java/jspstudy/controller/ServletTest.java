package jspstudy.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletTest")
<<<<<<< HEAD
public class ServletTest extends HttpServlet { //HttpServlet 를 상속받고 있기때문에 웹페이지로 이동 가능
	private static final long serialVersionUID = 1L;
       
  
//    public ServletTest() {
//        super();
// 
//    } 디폴트 생성자이기 때문에 생략해도 무관함.

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  // 내용을 노출시켜 전송  인터넷은 두개로 구성request, response
				//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("utf-8");       // 자료가 넘어올때 utf-8로 지정해 한글이 깨지지 않게설정
		response.setContentType("text/html;charset=utf-8"); // 자료를 받을때 utf-8로 지정해 한글이 깨지지 않게설정
		
		PrintWriter out = response.getWriter();
		out.println("<html>"
				+ "<head>"
				+ "<title>서블릿</title>"
				+ "</head>"
				+ "<body>"
				+ "<h1>안녕하세요</h1>"
				+ "<h2>반갑습니다.</h2>"
				+ "</body>"
				+ "</html>");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  // 내용을 감춰서 전송
=======
public class ServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println("<html>"
				+ "<head>"
				+ "<title>서블릿</title>"
				+ "</head>"
				+ "<body>"
				+ "<h1>안녕하세요</h1>"
				+ "<h2>반갑습니다.</h2>"
				+ "</body>"
				+ "</html>");	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
>>>>>>> branch 'master' of https://github.com/tjdwnd105/apiB.git
		doGet(request, response);
	}

}
