package jspstudy.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

<<<<<<< HEAD
@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();// ���� �ּ� ���� �޼���
		String pj = request.getContextPath();
		String command = uri.substring(pj.length());   // ������Ʈ �̸��� �� ������ ������ ����
		
		String[] subpath = command.split("/");
		String location = subpath[1];            // member ���ڿ��� ����ȴ�.
		
		if(location.equals("member")) {    //����� �ִ� ��ü���� ��  ��������
			MemberController mc = new MemberController();
			mc.doGet(request, response);
			
		}else if(location.equals("board")) {  //����� �ƴҰ�� ������ ��ü�� ������ ��������
			BoardController bc = new BoardController();
			bc.doGet(request, response);
		}else if(location.equals("home")) {
			HomeController hc = new HomeController();
			hc.doGet(request, response);
		}else if(location.equals("notice")) {
			NoticeController nc = new NoticeController();
			nc.doGet(request,response);
		}else if(location.equals("reply")) {
			ReplyController rc = new ReplyController();
			rc.doGet(request,response);
		}
			
	}

=======

@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String uri = request.getRequestURI();
		String pj = request.getContextPath();
		String command = uri.substring(pj.length());  //������Ʈ�̸��� �� ������ ������ ����	
		// ex)   /member/memberList.do
		
		String[] subpath = command.split("/");
		String location = subpath[1];        // member ���ڿ��� ����
		
		if (location.equals("member")) {
			MemberController mc = new MemberController();
			mc.doGet(request, response);
			
		}else if (location.equals("board")) {
			BoardController bc = new BoardController();
			bc.doGet(request, response);			
		}
		
		
		
		
	}

	
>>>>>>> branch 'master' of https://github.com/tjdwnd105/apiB.git
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
