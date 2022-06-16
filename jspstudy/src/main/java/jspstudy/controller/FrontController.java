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
		
		String uri = request.getRequestURI();// ï¿½ï¿½ï¿½ï¿½ ï¿½Ö¼ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½Þ¼ï¿½ï¿½ï¿½
		String pj = request.getContextPath();
		String command = uri.substring(pj.length());   // ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Æ® ï¿½Ì¸ï¿½ï¿½ï¿½ ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
		
		String[] subpath = command.split("/");
		String location = subpath[1];            // member ï¿½ï¿½ï¿½Ú¿ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½È´ï¿½.
		
		if(location.equals("member")) {    //ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ö´ï¿½ ï¿½ï¿½Ã¼ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½  ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
			MemberController mc = new MemberController();
			mc.doGet(request, response);
			
		}else if(location.equals("board")) {  //ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Æ´Ò°ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½Ã¼ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
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
		String command = uri.substring(pj.length());  //ÇÁ·ÎÁ§Æ®ÀÌ¸§À» »« ³ª¸ÓÁö °¡»ó°æ·Î ÃßÃâ	
		// ex)   /member/memberList.do
		
		String[] subpath = command.split("/");
		String location = subpath[1];        // member ¹®ÀÚ¿­ÀÌ ÃßÃâ
		
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
