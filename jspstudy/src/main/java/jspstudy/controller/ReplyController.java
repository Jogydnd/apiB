package jspstudy.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspstudy.domain.ReplyVo;
import jspstudy.service.BoardDao;
import jspstudy.service.ReplyDao;

@WebServlet("/ReplyController")
public class ReplyController extends HttpServlet { //HttpServlet �� ��ӹް� �ֱ⶧���� ���������� �̵� ����
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");     
		response.setContentType("text/html;charset=utf-8"); 
		
		PrintWriter out = response.getWriter();
		
		String uri = request.getRequestURI();
		String pj = request.getContextPath();
		String command = uri.substring(pj.length());
		
		System.out.println("command: " + command);
		
		
		if(command.equals("/reply/Insert.do")) {
			String bidx = request.getParameter("bidx");    
				int bidx_ = Integer.parseInt(bidx); 
				System.out.println("bidx_: " + bidx_);
				
			String r_content = request.getParameter("replytext");
			System.out.println("r_content: " + r_content);
			
			String r_name = request.getParameter("memberEmail");
			System.out.println("r_name: " + r_name);
			
			ReplyDao rd = new ReplyDao();
			int value = rd.insertReply(r_name, bidx_, r_content);
			System.out.println("value :"+ value);
			
			request.setAttribute("value" , value);
			
			
			 RequestDispatcher Qd= request.getRequestDispatcher("/reply/InsertCheck.jsp");
			  Qd.forward(request, response);
			
		
			
			
			    }
		else if(command.equals("/reply/Good.do")) {
			 String ridx = request.getParameter("ridx"); 
			 System.out.println("ridx:"+ridx);
				int ridx_ = Integer.parseInt(ridx); 
				System.out.println("ridx_:"+ridx_);
			
				ReplyDao red = new ReplyDao();
				int value = red.update_Good(ridx_);
				System.out.println("value :"+value);
			
				request.setAttribute("value" , value);

				 RequestDispatcher rd= request.getRequestDispatcher("/reply/GoodCheck.jsp");
				  rd.forward(request, response);
			
		 }
		else if(command.equals("/reply/RRInsert.do")) {
			String ridx = request.getParameter("ridx");
			System.out.println("ridx :"+ridx);
			
			String r_origin = request.getParameter("R_origin");
			System.out.println("r_origin :"+r_origin);
			
			String r_depth = request.getParameter("R_depth");
			System.out.println("r_depth :"+r_depth);
			
			String r_lev = request.getParameter("R_lev");
			System.out.println("r_lev :"+r_lev);
			
			String r_name = request.getParameter("memberEmail");
			System.out.println("r_name :"+r_name);
			
			String r_content = request.getParameter("Re_retext");
			System.out.println("r_content :"+r_content);
			
			String bidx = request.getParameter("bidx");
			System.out.println("bidx :"+bidx);
			
			
			// rv에 형변환 후 값 담기
			ReplyVo rv = new ReplyVo();
			
			rv.setRidx(Integer.parseInt(ridx));
			System.out.println("ridx :"+ridx);
			
			rv.setR_origin(Integer.parseInt(r_origin));
			System.out.println("r_origin :"+r_origin);
			
			rv.setR_depth(Integer.parseInt(r_depth));
			System.out.println("r_depth :"+r_depth);
			
			rv.setR_lev(Integer.parseInt(r_lev));
			System.out.println("r_lev :"+r_lev);
			
			rv.setR_name(r_name);
			System.out.println("r_name :"+r_name);
			
			rv.setR_content(r_content);
			System.out.println("r_content :"+r_content);
			
			rv.setBidx(Integer.parseInt(bidx));
			System.out.println("bidx :"+bidx);
			
			ReplyDao red = new ReplyDao();
			int value = red.Re_reply(rv);
			
			System.out.println("value :"+value);
			
			request.setAttribute("value" , value);
			
			RequestDispatcher Qd= request.getRequestDispatcher("/reply/RRInsertCheck.jsp");
			 Qd.forward(request, response);
			
		}
		
		
		
		
		
		
		
		
		}	
		

		
		
		

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  // ������ ���缭 ����
		doGet(request, response);
	}
}
