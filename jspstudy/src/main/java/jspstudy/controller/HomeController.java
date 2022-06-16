package jspstudy.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Home")
public class HomeController extends HttpServlet { 
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  // ������ ������� ����  ���ͳ��� �ΰ��� ����request, response
		
		request.setCharacterEncoding("utf-8");     
		response.setContentType("text/html;charset=utf-8"); 
		
		PrintWriter out = response.getWriter();
		
		String uri = request.getRequestURI();
		String pj = request.getContextPath();
		String command = uri.substring(pj.length());
		
		System.out.println("command :"+command);
		if(command.equals("/home/index.do")) {
			System.out.println("로그인 X 화면에 입장");
			
			 RequestDispatcher rd= request.getRequestDispatcher("/home/index.jsp");
			  rd.forward(request, response);
		}	
		
		else if(command.equals("/home/index2.do")) {
			System.out.println("로그인 O 관리자 화면에 입장");
			 RequestDispatcher rd= request.getRequestDispatcher("/home/index2.jsp");
			  rd.forward(request, response);
		}
		
		else if(command.equals("/home/index3.do")) {
			System.out.println("로그인 O 일반회원 화면에 입장");
			 RequestDispatcher rd= request.getRequestDispatcher("/home/index3.jsp");
			  rd.forward(request, response);
		}
		
		
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  // ������ ���缭 ����
		doGet(request, response);
	}

}
