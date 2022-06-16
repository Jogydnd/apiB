package jspstudy.controller;

import java.io.IOException;
import java.net.InetAddress;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspstudy.domain.NoticeVo;
import jspstudy.domain.PageMaker;
import jspstudy.domain.SearchCriteria;
import jspstudy.service.NoticeDao;

@WebServlet("/NoticeController")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		request.setCharacterEncoding("utf-8");      
		response.setContentType("text/html;charset=utf-8"); 
		
		String uri = request.getRequestURI();
		String pj = request.getContextPath();
		String command = uri.substring(pj.length());
		
		System.out.println("command: " + command);
		
	   // ==============공지사항 작성하기==================================================================
		if(command.equals("/notice/noticeWrite.do")) {
		System.out.println("공지사항 작성 페이지로 들어왔습니다.");
				
		RequestDispatcher rd = request.getRequestDispatcher("/notice/noticeWrite.jsp");
		rd.forward(request, response);	
		 }
		else if(command.equals("/notice/noticeWriteAction.do")) {
		    	
	
	    String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		HttpSession session = request.getSession();
		int midx = (int)session.getAttribute("midx");
		
		NoticeDao nd = new NoticeDao();   
		int value = nd.insertNotice(subject, content, midx);
		
		if(value==1) {
			response.sendRedirect(request.getContextPath()+"/home/index.do");
			System.out.println("공지사항 쓰기 성공했습니다!!!");
		}else {
			response.sendRedirect(request.getContextPath()+"/notice/notice.do");
			System.out.println("공지사항 쓰기에 실패했습니다.");
		}	
		    }
		//======공지사항 리스트 페이지 구현======================================================
		else if(command.equals("/notice/noticeList.do")) {
			System.out.println("공지사항 리스트 페이지에 들어왔습니다.");
			
			//페이징 구축------------
			String page = request.getParameter("page");
			if ( page == null) page = "1" ;
			int pagex = Integer.parseInt(page);
			
			String keyword = request.getParameter("keyword");
			if (keyword == null) keyword = "";
			
			//검색하기 기능============
			String searchType = request.getParameter("searchType");
			if(searchType == null ) searchType = "subject";
			
			SearchCriteria scri = new SearchCriteria();
		     scri.setPage(pagex);  
		     scri.setSearchType(searchType);
		     scri.setKeyword(keyword);
		     
		     NoticeDao nd = new NoticeDao();
		     int cnt = nd.noticeTotal(scri);
		     
		     PageMaker pm = new PageMaker();
		     pm.setScri(scri);  
		     pm.setTotalCount(cnt);
		     
		     ArrayList<NoticeVo> nlist = nd.noticeSelectAll(scri);
		     
		     request.setAttribute("nlist", nlist);
		     request.setAttribute("pm", pm);
		     
		     RequestDispatcher rd = request.getRequestDispatcher("/notice/noticeList.jsp");
			rd.forward(request, response);	
		}
		//운영진이 아닐경우 2번으로 이동
		else if(command.equals("/notice/noticeList2.do")) {
			System.out.println("공지사항 리스트 페이지에 들어왔습니다.");
			
			//페이징 구축------------
			String page = request.getParameter("page");
			if ( page == null) page = "1" ;
			int pagex = Integer.parseInt(page);
			
			String keyword = request.getParameter("keyword");
			if (keyword == null) keyword = "";
			
			//검색하기 기능============
			String searchType = request.getParameter("searchType");
			if(searchType == null ) searchType = "subject";
			
			SearchCriteria scri = new SearchCriteria();
		     scri.setPage(pagex);  
		     scri.setSearchType(searchType);
		     scri.setKeyword(keyword);
		     
		     NoticeDao nd = new NoticeDao();
		     int cnt = nd.noticeTotal(scri);
		     
		     PageMaker pm = new PageMaker();
		     pm.setScri(scri);  
		     pm.setTotalCount(cnt);
		     
		     ArrayList<NoticeVo> nlist = nd.noticeSelectAll(scri);
		     
		     request.setAttribute("nlist", nlist);
		     request.setAttribute("pm", pm);
		     
		     RequestDispatcher rd = request.getRequestDispatcher("/notice/noticeList2.jsp");
			rd.forward(request, response);	
		}
		
		else if(command.equals("/notice/noticeList3.do")) {
			System.out.println("공지사항 리스트 페이지에 들어왔습니다.");
			
			//페이징 구축------------
			String page = request.getParameter("page");
			if ( page == null) page = "1" ;
			int pagex = Integer.parseInt(page);
			
			String keyword = request.getParameter("keyword");
			if (keyword == null) keyword = "";
			
			//검색하기 기능============
			String searchType = request.getParameter("searchType");
			if(searchType == null ) searchType = "subject";
			
			SearchCriteria scri = new SearchCriteria();
		     scri.setPage(pagex);  
		     scri.setSearchType(searchType);
		     scri.setKeyword(keyword);
		     
		     NoticeDao nd = new NoticeDao();
		     int cnt = nd.noticeTotal(scri);
		     
		     PageMaker pm = new PageMaker();
		     pm.setScri(scri);  
		     pm.setTotalCount(cnt);
		     
		     ArrayList<NoticeVo> nlist = nd.noticeSelectAll(scri);
		     
		     request.setAttribute("nlist", nlist);
		     request.setAttribute("pm", pm);
		     
		     RequestDispatcher rd = request.getRequestDispatcher("/notice/noticeList3.jsp");
			rd.forward(request, response);	
		}
		//=======공지사항 열람=================================
		else if (command.equals("/notice/noticeContent.do")) {
		
			String nidx = request.getParameter("nidx");                        
			int nidx_ = Integer.parseInt(nidx);
			
			NoticeDao nd = new NoticeDao();
			NoticeVo nv = nd.noticeSelectOne(nidx_);
			System.out.println("nv :"+nv);
			request.setAttribute("nv", nv);
			
			RequestDispatcher rd = request.getRequestDispatcher("/notice/noticeContent.jsp");
			rd.forward(request, response);	
		}
		// 운영진이 아닐경우 2번으로 이동!!
		else if (command.equals("/notice/noticeContent2.do")) {
			
			String nidx = request.getParameter("nidx");                        
			int nidx_ = Integer.parseInt(nidx);
			
			NoticeDao nd = new NoticeDao();
			NoticeVo nv = nd.noticeSelectOne(nidx_);
			System.out.println("nv :"+nv);
			request.setAttribute("nv", nv);
			
			RequestDispatcher rd = request.getRequestDispatcher("/notice/noticeContent2.jsp");
			rd.forward(request, response);	
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  // ������ ���缭 ����
		doGet(request, response);
	}

}