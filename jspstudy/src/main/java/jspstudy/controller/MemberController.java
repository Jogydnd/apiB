package jspstudy.controller;

import java.io.IOException;
import java.io.PrintWriter;
<<<<<<< HEAD
import java.net.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jspstudy.domain.MemberVo;
import jspstudy.service.MemberDao;

@WebServlet("/MemberController")  
public class MemberController<requestDispatcher> extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String uri = request.getRequestURI();// ���� �ּ� ���� �޼���
		String pj = request.getContextPath();
		String command = uri.substring(pj.length());
		
		System.out.println("command:"+command);
////////----------------------------- 회원가입페이지 -----------------------------------------------------------		
		if(command.equals("/member/memberJoinAction.do")) {
			
			   String memberEmail = request.getParameter("memberEmail");
			    String memberPwd = request.getParameter("memberPwd");
			    String memberName = request.getParameter("memberName");
			    String memberPhone = request.getParameter("memberPhone");
			    String memberGender = request.getParameter("memberGender");
			    String memberAddr = request.getParameter("memberAddr");
			  			    // ���ڿ� �ڸ��� 0������ �����ϱ� ������ ','�� ����� ���� 1������ ����
			    
			    String ip=InetAddress.getLocalHost().getHostAddress();  //String Ÿ���� ip�� ������� ip�ּҰ��� ����.
			    
			    MemberDao md = new MemberDao();
			    System.out.println(md);
			   int value = md.insertMember(memberEmail,memberPwd,memberName,memberGender,memberAddr,memberPhone,ip);
			
			   PrintWriter out = response.getWriter(); 
			   if (value == 1){   // �Է��� �Ǿ�����
				   response.sendRedirect(request.getContextPath()+"/home/index.do");
				   
					  //out.println("<script>alert('ȸ�����Լ���');location.href='"+request.getContextPath()+"/'</script>");  // ���� �ּ� ���
				  }else{    // �Է��� �ȵǾ�����
					  response.sendRedirect(request.getContextPath()+"/member/memberJoin.do");
					//  out.println("<script>alert('ȸ�����Խ���');location.href='"+request.getContextPath()+"/'</script>");
				  }
		      }
		else if (command.equals("/member/memberJoin.do")){
		    	  		    	  
			RequestDispatcher rd= request.getRequestDispatcher("/member/memberJoin.jsp");
			rd.forward(request, response);
			
			}
		
////////----------------------------- 회원 리스트 (관리자만 열람 가능) -----------------------------------------------------------
		else if (command.equals("/member/memberList.do")) {
		       
				MemberDao md = new MemberDao();
		          ArrayList<MemberVo> alist = md.memberSelectAll();
		          
		          request.setAttribute("alist", alist);
		         
		          
		          RequestDispatcher rd = request.getRequestDispatcher("/member/memberList.jsp");
		          rd.forward(request, response);   
		          
 ////////----------------------------- 로그인 화면!!!!! -----------------------------------------------------------
			}else if (command.equals("/member/memberLogin.do")){
				System.out.println("ȸ�� �α��� â���� ���Խ��ϴ�.");
				
				RequestDispatcher rd= request.getRequestDispatcher("/member/memberLogin.jsp");
				rd.forward(request, response);
				
			}else if (command.equals("/member/memberLoginAction.do")){
				//1. �Ѿ�� ��
				     String memberEmail = request.getParameter("memberEmail");
				     String memberPwd = request.getParameter("memberPwd");
				     
				//2. ó��
				    MemberDao md = new MemberDao();
				    MemberVo mv = md.memberLogin(memberEmail, memberPwd);
				    System.out.println("mv");
				    
				   HttpSession session = request.getSession(); 
	
				    
				//3. �̵�
				   if(mv != null) {  // �α��ο� �����Ͽ��� ��
					   session.setAttribute("midx", mv.getMidx());
					   session.setAttribute("memberRank", mv.getMemberrank());
					   session.setAttribute("memberEmail", mv.getMamberemail());
					   session.setAttribute("memberName", mv.getMembername());

					   
					   if ( session.getAttribute("saveUrl") != null) {
						   response.sendRedirect((String)session.getAttribute("saveUrl"));
					   }else {response.sendRedirect(request.getContextPath()+"/home/index.do");
					   }
				   }else {  // �α����� ���� �ʾ��� ��
					   response.sendRedirect(request.getContextPath()+"/member/memberLogin.do");
				   }
		
				// �α׾ƿ� ������
			}else if (command.equals("/member/memberLogout.do")){
		
				HttpSession session = request.getSession();
				session.invalidate();
				response.sendRedirect(request.getContextPath()+"/home/index.do");
		}
		
//-------   비밀번호 찾기 !!!!!!!!     ------ ----------------------------------------------------------------------------------------------
			else if (command.equals("/member/findpwd.do")){
		    	  
				  RequestDispatcher rd= request.getRequestDispatcher("/member/findpwd.jsp");
				  rd.forward(request, response);
				}
			else if (command.equals("/member/findpwdAction.do")){
				//1. �Ѿ�� ��
				
				 String membername = request.getParameter("membername");
				 String memberemail = request.getParameter("memberemail");
				 System.out.println(membername+memberemail);
				 
				 
				 MemberDao md = new MemberDao();
				 String memberpwd = md.findPwd( membername, memberemail);
//				 request.setAttribute("memberpwd", memberpwd);
				 HttpSession session = request.getSession();
				 session.setAttribute("memberpwd", memberpwd);
		
				 if(memberpwd==null) {
					
					 response.sendRedirect(request.getContextPath()+"/member/findpwd.do");}
				 else {
						response.sendRedirect(request.getContextPath()+"/member/findpwdAfter.do");   
					}	
				 }
		
			else if (command.equals("/member/findpwdAfter.do")){	
				 
				 RequestDispatcher rd= request.getRequestDispatcher("/member/findpwdAfter.jsp");
				  rd.forward(request, response);
			}
		////////----------------------------- 마이페이지 -----------------------------------------------------------
		
			else if(command.equals("/member/MyPage.do")) {
				
				RequestDispatcher rd= request.getRequestDispatcher("/member/MyPage.jsp");
				rd.forward(request, response);
			}
		
			else if (command.equals("/member/MyPageAction.do")){
				
				 String memberEmail = request.getParameter("memberEmail");
				 
				 MemberDao md = new MemberDao();
			MemberVo mv = md.MyPage(memberEmail);
				    System.out.println("mv");
				    
				    HttpSession session = request.getSession(); 
					
				  if(mv != null) {  // �α��ο� �����Ͽ��� ��
				 session.setAttribute("midx", mv.getMidx());
				 session.setAttribute("memberEmail", mv.getMamberemail());
				 session.setAttribute("memberName", mv.getMembername());
				 session.setAttribute("writeday", mv.getWriteday());
				 session.setAttribute("memberPhone", mv.getMemberphone());
				 session.setAttribute("memberAddr",mv.getMemberaddr());
				 session.setAttribute("memberGender", mv.getMembergender());
						   
			      response.sendRedirect(request.getContextPath()+"/member/MyPageAfter.do");
				 }
				else {  // �α����� ���� �ʾ��� ��
				  response.sendRedirect(request.getContextPath()+"/member/MyPage.do");
				}
		        }
		
	          if(command.equals("/member/MyPageAfter.do")){
				 RequestDispatcher rd= request.getRequestDispatcher("/member/MyPageAfter.jsp");
				  rd.forward(request, response);
			}
		
		
		//------------------아이디(이메일)중복체크------------------------
		
             else if(command.equals("/member/Join.do")) {
				
				
				String memberemail = (String)request.getParameter("Email");
				
							
				MemberDao md = new MemberDao();
				String member_email = md.EmailJoin(memberemail);
				System.out.println("member_email :"+member_email);
				System.out.println("memberemail :"+memberemail);
		        String flag = "N";
		       
		         if(memberemail.equals(member_email)||memberemail==""||memberemail.length()<6){//기존의 이메일 아이디와 중복될 경우
					flag= "Y";   }

				request.setAttribute("flag" , flag);

				 RequestDispatcher rd= request.getRequestDispatcher("/member/checkId.jsp");
				  rd.forward(request, response);
				
			
          }


		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
		
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
	
=======
import java.net.InetAddress;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspstudy.domain.MemberVo;
import jspstudy.service.MemberDao;


@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
           
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String uri = request.getRequestURI();
		String pj = request.getContextPath();
		String command = uri.substring(pj.length());
		
		System.out.println("command:"+command);
		
		if(command.equals("/member/memberJoinAction.do")) {
			
			String memberId = request.getParameter("memberId");
			String memberPwd = request.getParameter("memberPwd");
			String memberName = request.getParameter("memberName");
			String memberEmail = request.getParameter("memberEmail");
			String memberPhone = request.getParameter("memberPhone");
			String memberJumin = request.getParameter("memberJumin");
			String memberGender = request.getParameter("memberGender");
			String memberAddr = request.getParameter("memberAddr");
			String[] memberHobby = request.getParameterValues("memberHobby");	
			
			String hobby = "";	
			for(int i=0; i<memberHobby.length;i++){	
				hobby = hobby +","+ memberHobby[i];
				//out.println(memberHobby[i]+"<br>");
			}
			hobby = hobby.substring(1);	
			
			String ip = InetAddress.getLocalHost().getHostAddress();	
			
			MemberDao md = new MemberDao();
			int value = md.insertMember(memberId,memberPwd,memberName,memberGender,memberAddr,memberJumin,memberPhone,hobby,memberEmail,ip);
			
		//	PrintWriter out = response.getWriter();
			//�Է��� �Ǿ�����
			if (value ==1){
				response.sendRedirect(request.getContextPath()+"/member/memberList.do");
				
			//	out.println("<script>alert('ȸ�����Լ���'); location.href='"+request.getContextPath()+"/'</script>");		
			}else{
				response.sendRedirect(request.getContextPath()+"/member/memberJoin.do");
			//	out.println("<script>alert('ȸ�����Խ���'); location.href='./memberJoin.html'</script>");
			}
			
		}else if (command.equals("/member/memberJoin.do")) {			
			
			RequestDispatcher rd = request.getRequestDispatcher("/member/memberJoin.jsp");
			rd.forward(request, response);
			
		}else if (command.equals("/member/memberList.do")) {
			
			MemberDao md = new MemberDao();	
			ArrayList<MemberVo> alist = md.memberSelectAll();
			
			request.setAttribute("alist", alist);
			
			RequestDispatcher rd = request.getRequestDispatcher("/member/memberList.jsp");
			rd.forward(request, response);			
		}else if (command.equals("/member/memberLogin.do")) {
			
			RequestDispatcher rd = request.getRequestDispatcher("/member/memberLogin.jsp");
			rd.forward(request, response);	
			
			
		}else if (command.equals("/member/memberLoginAction.do")) {
		
				//1. �Ѿ�°� 
				String memberId = request.getParameter("memberId");
				String memberPwd = request.getParameter("memberPwd");
				//2.ó��			
				MemberDao md = new MemberDao();
				MemberVo mv = md.memberLogin(memberId, memberPwd);
				System.out.println("mv"+mv);
				
				HttpSession session = request.getSession();
								
				//3.�̵�
				if (mv != null) {
					session.setAttribute("midx", mv.getMidx());
					session.setAttribute("memberId", mv.getMemberid());
					session.setAttribute("memberName", mv.getMembername());					
				
					if (session.getAttribute("saveUrl") != null) {
						response.sendRedirect((String)session.getAttribute("saveUrl"));	
					}else {
						response.sendRedirect(request.getContextPath()+"/member/memberList.do");
					}
				
				}else {
				response.sendRedirect(request.getContextPath()+"/member/memberLogin.do");
				}		
		}else if (command.equals("/member/memberLogout.do")) {
			
			HttpSession session = request.getSession();		
			session.invalidate();
			response.sendRedirect(request.getContextPath()+"/");
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
>>>>>>> branch 'master' of https://github.com/tjdwnd105/apiB.git
