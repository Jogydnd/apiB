package jspstudy.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jspstudy.domain.BoardVo;
import jspstudy.domain.PageMaker;
import jspstudy.domain.ReplyVo;
import jspstudy.domain.SearchCriteria;
import jspstudy.service.BoardDao;
import jspstudy.service.ReplyDao;


@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	response.getWriter().append("Served at: ").append(request.getContextPath());  �ѱ۱���
		
		//�����η� �� request�� ������ ó��
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String uri = request.getRequestURI();   // ���� �ּ� ���� �޼���
		String pj = request.getContextPath();
		String command = uri.substring(pj.length());
		
		
		int sizeLimit = 1024*1024*15;
		String uploadPath="D:\\dev\\workspace\\jspstudy\\src\\main\\webapp\\";
		String saveFolder="img";
		String saveFullPath = uploadPath + saveFolder;
		System.out.println("savaFullPath :"+saveFullPath);
		//==============글쓰기(게시물 작성)==============================================================
		if(command.equals("/board/boardwrite.do")) {
			System.out.println("글쓰기 페이지로 들어왔습니다.");
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardwrite.jsp");
			rd.forward(request, response);
		}else if(command.equals("/board/boardwriteAction.do")) {
			System.out.println("글쓰기 처리 페이지로 들어왔습니다.");
			
			MultipartRequest multipartRequest = null;
			multipartRequest = new MultipartRequest(request, saveFullPath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());
			
			String subject =multipartRequest.getParameter("subject");
			String content = multipartRequest.getParameter("content");
			String writer =multipartRequest.getParameter("writer");
			String[] transportation = multipartRequest.getParameterValues("transportation");
			
			System.out.println(subject+";"+content+";"+writer);
			
			String move = "";
		    for(int i=0; i<transportation.length; i++){
		    	move = move +","+ transportation[i];  
		    System.out.println(transportation[i]+"<br>");
		    };
		    move = move.substring(1);
			
		
			Enumeration files = multipartRequest.getFileNames();
			System.out.println("files :"+ files);
			
			String file = (String) files.nextElement();
			System.out.println("file :"+file);

			String fileName = multipartRequest.getFilesystemName(file);
			System.out.println("fileName :"+fileName);
	
			String originFileName = multipartRequest.getOriginalFileName(file);
			System.out.println("originFileName :"+originFileName);
			
			String ip = InetAddress.getLocalHost().getHostAddress();
			
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
			BoardDao bd = new BoardDao();   
			int value = bd.insertBoard(subject, content, writer, ip, midx, fileName, move);
			
			if(value==1) {    
				response.sendRedirect(request.getContextPath()+"/home/index.do");    

			}else {    
				response.sendRedirect(request.getContextPath()+"/board/boardwrite.do");  
				
			}
			}
		
		else if(command.equals("/board/boardList.do")) {
						System.out.println("게시판 리스트 페이지에 들어왔습니다.");
						
						String page = request.getParameter("page");
						if ( page == null) page = "1" ;
						int pagex = Integer.parseInt(page);
						
						String keyword = request.getParameter("keyword");
						if (keyword == null) keyword = "";
						
						String searchType = request.getParameter("searchType");
						if(searchType == null ) searchType = "subject";
						
						
						SearchCriteria scri = new SearchCriteria();
					     scri.setPage(pagex); 
					     scri.setSearchType(searchType);
					     scri.setKeyword(keyword);
						

			BoardDao bd = new BoardDao();			
			int cnt = bd.boardTotal(scri);
			
		     PageMaker pm = new PageMaker();
		     pm.setScri(scri);  
		     pm.setTotalCount(cnt);  
		
	          ArrayList<BoardVo> blist = bd.boardSelectAll(scri);
	          
	          
	          request.setAttribute("blist", blist);
	          request.setAttribute("pm", pm);
	         //�̵�
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardList.jsp");
			rd.forward(request, response);
		}
		
		else if(command.equals("/board/boardList1.do")) {
			System.out.println("게시판 리스트 페이지에 들어왔습니다.");
			
			String page = request.getParameter("page");
			if ( page == null) page = "1" ;
			int pagex = Integer.parseInt(page);
			
			String keyword = request.getParameter("keyword");
			if (keyword == null) keyword = "";
			
			String searchType = request.getParameter("searchType");
			if(searchType == null ) searchType = "subject";
			
			
			SearchCriteria scri = new SearchCriteria();
		     scri.setPage(pagex); 
		     scri.setSearchType(searchType);
		     scri.setKeyword(keyword);
			

BoardDao bd = new BoardDao();			
int cnt = bd.boardTotal(scri);

 PageMaker pm = new PageMaker();
 pm.setScri(scri);  
 pm.setTotalCount(cnt);  

  ArrayList<BoardVo> blist = bd.boardSelectAll(scri);
  
  
  request.setAttribute("blist", blist);
  request.setAttribute("pm", pm);
 //�̵�
RequestDispatcher rd = request.getRequestDispatcher("/board/boardList1.jsp");
rd.forward(request, response);
}
		
		else if(command.equals("/board/boardList2.do")) {
			System.out.println("게시판 리스트 페이지에 들어왔습니다.");
			
			String page = request.getParameter("page");
			if ( page == null) page = "1" ;
			int pagex = Integer.parseInt(page);
			
			String keyword = request.getParameter("keyword");
			if (keyword == null) keyword = "";
			
			String searchType = request.getParameter("searchType");
			if(searchType == null ) searchType = "subject";
			
			
			SearchCriteria scri = new SearchCriteria();
		     scri.setPage(pagex); 
		     scri.setSearchType(searchType);
		     scri.setKeyword(keyword);
			

BoardDao bd = new BoardDao();			
int cnt = bd.boardTotal(scri);

 PageMaker pm = new PageMaker();
 pm.setScri(scri);  
 pm.setTotalCount(cnt);  

  ArrayList<BoardVo> blist = bd.boardSelectAll(scri);
  
  
  request.setAttribute("blist", blist);
  request.setAttribute("pm", pm);
 //�̵�
RequestDispatcher rd = request.getRequestDispatcher("/board/boardList2.jsp");
rd.forward(request, response);
}
		
		
		// 게시판 리스트 조회수 순으로 나열
		 else if(command.equals("/board/HitArray.do")) {
			    System.out.println("조회수 순으로 나열");
			    
			    String page = request.getParameter("page");
				if ( page == null) page = "1" ;
				int pagex = Integer.parseInt(page);
				
				String keyword = request.getParameter("keyword");
				if (keyword == null) keyword = "";
				
				String searchType = request.getParameter("searchType");
				if(searchType == null ) searchType = "subject";
				
				
				SearchCriteria scri = new SearchCriteria();
			     scri.setPage(pagex); 
			     scri.setSearchType(searchType);
			     scri.setKeyword(keyword);
				

	BoardDao bd = new BoardDao();			
	int cnt = bd.boardTotal(scri);
	
  PageMaker pm = new PageMaker();
  pm.setScri(scri);  
  pm.setTotalCount(cnt);  

   ArrayList<BoardVo> blist = bd.boardSelectHits(scri);
   
   
   request.setAttribute("blist", blist);
   request.setAttribute("pm", pm);
 
	RequestDispatcher rd = request.getRequestDispatcher("/board/boardListHits.jsp");
	rd.forward(request, response);
				 
				 	 }
		
		// 게시판 리스트 좋아요순으로 나열
			 else if(command.equals("/board/LikeArray.do")) {
				    System.out.println("조회수 순으로 나열");
				    
				    String page = request.getParameter("page");
					if ( page == null) page = "1" ;
					int pagex = Integer.parseInt(page);
					
					String keyword = request.getParameter("keyword");
					if (keyword == null) keyword = "";
					
					String searchType = request.getParameter("searchType");
					if(searchType == null ) searchType = "subject";
					
					
					SearchCriteria scri = new SearchCriteria();
				     scri.setPage(pagex); 
				     scri.setSearchType(searchType);
				     scri.setKeyword(keyword);
					

		BoardDao bd = new BoardDao();			
		int cnt = bd.boardTotal(scri);
		
	  PageMaker pm = new PageMaker();
	  pm.setScri(scri);  
	  pm.setTotalCount(cnt);  

	   ArrayList<BoardVo> blist = bd.boardSelectLike(scri);
	   
	   
	   request.setAttribute("blist", blist);
	   request.setAttribute("pm", pm);
	  //�̵�
		RequestDispatcher rd = request.getRequestDispatcher("/board/boardListLike.jsp");
		rd.forward(request, response);
					 
					 	 }
		
		
			// 게시판 리스트 화나요순으로 나열
			 else if(command.equals("/board/AngryArray.do")) {
				    System.out.println("조회수 순으로 나열");
				    
				    String page = request.getParameter("page");
					if ( page == null) page = "1" ;
					int pagex = Integer.parseInt(page);
					
					String keyword = request.getParameter("keyword");
					if (keyword == null) keyword = "";
					
					String searchType = request.getParameter("searchType");
					if(searchType == null ) searchType = "subject";
					
					
					SearchCriteria scri = new SearchCriteria();
				     scri.setPage(pagex); 
				     scri.setSearchType(searchType);
				     scri.setKeyword(keyword);
					

		BoardDao bd = new BoardDao();			
		int cnt = bd.boardTotal(scri);
		
	  PageMaker pm = new PageMaker();
	  pm.setScri(scri);  
	  pm.setTotalCount(cnt);  

	   ArrayList<BoardVo> blist = bd.boardSelectAngry(scri);
	   
	   
	   request.setAttribute("blist", blist);
	   request.setAttribute("pm", pm);
	  //�̵�
		RequestDispatcher rd = request.getRequestDispatcher("/board/boardListAngry.jsp");
		rd.forward(request, response);
					 
					 	 }
		
		
			
	//===============게시물 보rl=============================================================
			else if (command.equals("/board/boardContent.do")) {
			System.out.println("게시물 보기 페이지로 들어왔습니다.");
			
			String bidx = request.getParameter("bidx");                        
			int bidx_ = Integer.parseInt(bidx);                             
			
			BoardDao bd = new BoardDao();                        
			BoardVo bv = bd.boardSelectOne(bidx_);
			   
			ReplyDao r_d = new ReplyDao();
			ArrayList<ReplyVo> rlist = r_d.ReplySelect(bidx_);
	          
			request.setAttribute("bv", bv); 
	        request.setAttribute("rlist", rlist);
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardContent.jsp");
			rd.forward(request, response);
			
		}else if (command.equals("/board/boardContent2.do")) {
			System.out.println("관리자 전용 게시물 열람 페이지");
			
			String bidx = request.getParameter("bidx");                       
			int bidx_ = Integer.parseInt(bidx);                              
			
			BoardDao bd = new BoardDao();
			BoardVo bv = bd.boardSelectOne2(bidx_);
							
            ReplyDao r_d = new ReplyDao();
			
			ArrayList<ReplyVo> rlist = r_d.ReplySelect(bidx_);
			
			request.setAttribute("bv", bv); 
	        request.setAttribute("rlist", rlist);
		
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardContent2.jsp");
			rd.forward(request, response);
			
		}else if (command.equals("/board/boardContent3.do")) {
			System.out.println("일반 게시물 열람 페이지로 들어왔습니다.");
			
			String bidx = request.getParameter("bidx");                       
			int bidx_ = Integer.parseInt(bidx);                               
			
			BoardDao bd = new BoardDao();
			BoardVo bv = bd.boardSelectOne2(bidx_);
						
            ReplyDao r_d = new ReplyDao();
			
			ArrayList<ReplyVo> rlist = r_d.ReplySelect(bidx_);
			
			request.setAttribute("bv", bv); 
	        request.setAttribute("rlist", rlist);    
	        
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardContent3.jsp");
			rd.forward(request, response);
		}

		
		//------------------게시물 수정하기 --------------------------------------------------------------------------------
		else if(command.equals("/board/boardModify.do")) {
			System.out.println("수정하기 페이지로 넘어가니다.");
			
			String bidx = request.getParameter("bidx");             
			int bidx_ = Integer.parseInt(bidx);                       
		
			
			BoardDao bd = new BoardDao();                         
			BoardVo bv = bd.boardSelectOne(bidx_);
			request.setAttribute("bv", bv);     
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardModify.jsp");        
			rd.forward(request, response);  
		}
		else if(command.equals("/board/boardModifyAction.do")) {                    
			System.out.println("수정하기 페이지로 들어왔습니다.");
			
			String subject = request.getParameter("subject");
			System.out.println("subject"+subject);
			String content = request.getParameter("content");
			System.out.println("content"+content);
			String writer = request.getParameter("writer");
			System.out.println("writer"+writer);
			String bidx = request.getParameter("bidx");
			System.out.println("bidx"+bidx);
			int bidx_ = Integer.parseInt(bidx);
			
			System.out.println(subject+";"+content+";"+writer);
			
			String ip = InetAddress.getLocalHost().getHostAddress();
			
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
			BoardDao bd = new BoardDao();   
			int value = bd.update(subject, content, writer, ip, midx, bidx_);
			System.out.println(value);
			
			if(value==1) {    
				response.sendRedirect(request.getContextPath()+"/board/boardList.do");    

			}else {     // 수정하기 실패하였을 때
				response.sendRedirect(request.getContextPath()+"/board/boardModify.do?bidx=");  
			}
					
			}
		
		// 게시물 삭제하기========================================================================
		else if(command.equals("/board/boardDelete.do"))   {
				System.out.println("삭제하기 페이지로 넘어갑니다.");
				
				String bidx = request.getParameter("bidx");   
				int bidx_ = Integer.parseInt(bidx);          
			
				
				BoardDao bd = new BoardDao();
				BoardVo bv = bd.boardSelectOne(bidx_);
				request.setAttribute("bv", bv);   
				
				RequestDispatcher rd = request.getRequestDispatcher("/board/boardDelete.jsp");
				rd.forward(request, response);
			}
		else if(command.equals("/board/boardDeleteAction.do")) {
				System.out.println("게시물 삭제하기 페이지로 들어왔습니다.");
				
				String bidx = request.getParameter("bidx");    
				int bidx_ = Integer.parseInt(bidx);            
				
				BoardDao bd = new BoardDao();
				int value = bd.delete(bidx_);
			
				if(value==1) {				
					response.sendRedirect(request.getContextPath()+"/home/index.do");   
				}else {  // 게시물 삭제에 실패하였을때
					response.sendRedirect(request.getContextPath()+"/board/boardContent.do?bidx=");  
				}
				
	
			} 
		// 파일 다운로드 아직 적용시킬 생각 없음.
//			else if(command.equals("/board/fileDownload.do")) {
//				
//				String filename = request.getParameter("filename");
//				
//			  
//				String filePath = saveFullPath + File.separator + filename;
//				
//				Path source = Paths.get(filePath);
//				
//				String mimeType = Files.probeContentType(source);
//				
//				
//				response.setContentType(mimeType);
//				
//				String encodingFileName = new String(filename.getBytes("utf-8")); 
//			
//				response.setHeader("Content-Disposition", "attachment;fileName="+encodingFileName);
//				
//
//				FileInputStream fileInputStream = new FileInputStream(filePath);
//	
//				ServletOutputStream servletOutStream = response.getOutputStream();
//				
//				byte[] b = new byte[4096];
//				
//				int read = 0;
//		
//				while((read = fileInputStream.read(b, 0, b.length))!=-1) {
//
//					servletOutStream.write(b,0,read);
//					
//				}
//
//				servletOutStream.flush();
//				servletOutStream.close();
//				
//				fileInputStream.close();
//			}
		
			 else if(command.equals("/board/like.do")) {
				 String bidx = request.getParameter("bidx");    
					int bidx_ = Integer.parseInt(bidx); 
				
					BoardDao bd = new BoardDao();
					int value = bd.update_Like(bidx_);
					System.out.println("value :"+value);
				
					request.setAttribute("value" , value);

					 RequestDispatcher rd= request.getRequestDispatcher("/board/likeCheck.jsp");
					  rd.forward(request, response);
				
			 }	
		
			 else if(command.equals("/board/angry.do")) {
				 String bidx = request.getParameter("bidx");    
					int bidx_ = Integer.parseInt(bidx); 
				
					BoardDao bd = new BoardDao();
					int value = bd.update_Angry(bidx_);
					System.out.println("value :"+value);
				
					request.setAttribute("value" , value);

					 RequestDispatcher rd= request.getRequestDispatcher("/board/AngryCheck.jsp");
					  rd.forward(request, response);
				
			 }	
		
		
			 else if(command.equals("/board/HitArray.do")) {
			    System.out.println("조회수 순으로 나열");
			    
			    String page = request.getParameter("page");
				if ( page == null) page = "1" ;
				int pagex = Integer.parseInt(page);
				
				String keyword = request.getParameter("keyword");
				if (keyword == null) keyword = "";
				
				String searchType = request.getParameter("searchType");
				if(searchType == null ) searchType = "subject";
				
				
				SearchCriteria scri = new SearchCriteria();
			     scri.setPage(pagex); 
			     scri.setSearchType(searchType);
			     scri.setKeyword(keyword);
				

	BoardDao bd = new BoardDao();			
	int cnt = bd.boardTotal(scri);
	
     PageMaker pm = new PageMaker();
     pm.setScri(scri);  
     pm.setTotalCount(cnt);  

      ArrayList<BoardVo> blist = bd.boardSelectAll(scri);
      
      
      request.setAttribute("blist", blist);
      request.setAttribute("pm", pm);
     //�̵�
	RequestDispatcher rd = request.getRequestDispatcher("/board/boardList.jsp");
	rd.forward(request, response);
				 
				 
			 }
	

  
		
		
		
		

		
		
		
		
		
		
		
		
}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}