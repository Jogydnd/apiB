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
public class ServletTest extends HttpServlet { //HttpServlet �� ��ӹް� �ֱ⶧���� ���������� �̵� ����
	private static final long serialVersionUID = 1L;
       
  
//    public ServletTest() {
//        super();
// 
//    } ����Ʈ �������̱� ������ �����ص� ������.

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  // ������ ������� ����  ���ͳ��� �ΰ��� ����request, response
				//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("utf-8");       // �ڷᰡ �Ѿ�ö� utf-8�� ������ �ѱ��� ������ �ʰԼ���
		response.setContentType("text/html;charset=utf-8"); // �ڷḦ ������ utf-8�� ������ �ѱ��� ������ �ʰԼ���
		
		PrintWriter out = response.getWriter();
		out.println("<html>"
				+ "<head>"
				+ "<title>����</title>"
				+ "</head>"
				+ "<body>"
				+ "<h1>�ȳ��ϼ���</h1>"
				+ "<h2>�ݰ����ϴ�.</h2>"
				+ "</body>"
				+ "</html>");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  // ������ ���缭 ����
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
				+ "<title>����</title>"
				+ "</head>"
				+ "<body>"
				+ "<h1>�ȳ��ϼ���</h1>"
				+ "<h2>�ݰ����ϴ�.</h2>"
				+ "</body>"
				+ "</html>");	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
>>>>>>> branch 'master' of https://github.com/tjdwnd105/apiB.git
		doGet(request, response);
	}

}
