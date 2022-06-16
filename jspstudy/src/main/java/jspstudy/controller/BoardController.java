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
<<<<<<< HEAD
import jspstudy.domain.PageMaker;
import jspstudy.domain.ReplyVo;
import jspstudy.domain.SearchCriteria;
import jspstudy.service.BoardDao;
import jspstudy.service.ReplyDao;


@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	response.getWriter().append("Served at: ").append(request.getContextPath());  ï¿½Ñ±Û±ï¿½ï¿½ï¿½
		
		//ï¿½ï¿½ï¿½ï¿½ï¿½Î·ï¿½ ï¿½ï¿½ requestï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ Ã³ï¿½ï¿½
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String uri = request.getRequestURI();   // ï¿½ï¿½ï¿½ï¿½ ï¿½Ö¼ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½Ş¼ï¿½ï¿½ï¿½
		String pj = request.getContextPath();
		String command = uri.substring(pj.length());
		
		
		int sizeLimit = 1024*1024*15;
		String uploadPath="D:\\dev\\workspace\\jspstudy\\src\\main\\webapp\\";
		String saveFolder="img";
		String saveFullPath = uploadPath + saveFolder;
		System.out.println("savaFullPath :"+saveFullPath);
		//==============ê¸€ì“°ê¸°(ê²Œì‹œë¬¼ ì‘ì„±)==============================================================
		if(command.equals("/board/boardwrite.do")) {
			System.out.println("ê¸€ì“°ê¸° í˜ì´ì§€ë¡œ ë“¤ì–´ì™”ìŠµë‹ˆë‹¤.");
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardwrite.jsp");
			rd.forward(request, response);
		}else if(command.equals("/board/boardwriteAction.do")) {
			System.out.println("ê¸€ì“°ê¸° ì²˜ë¦¬ í˜ì´ì§€ë¡œ ë“¤ì–´ì™”ìŠµë‹ˆë‹¤.");
			
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
						System.out.println("ê²Œì‹œíŒ ë¦¬ìŠ¤íŠ¸ í˜ì´ì§€ì— ë“¤ì–´ì™”ìŠµë‹ˆë‹¤.");
						
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
	         //ï¿½Ìµï¿½
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardList.jsp");
			rd.forward(request, response);
		}
		
		else if(command.equals("/board/boardList1.do")) {
			System.out.println("ê²Œì‹œíŒ ë¦¬ìŠ¤íŠ¸ í˜ì´ì§€ì— ë“¤ì–´ì™”ìŠµë‹ˆë‹¤.");
			
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
 //ï¿½Ìµï¿½
RequestDispatcher rd = request.getRequestDispatcher("/board/boardList1.jsp");
rd.forward(request, response);
}
		
		else if(command.equals("/board/boardList2.do")) {
			System.out.println("ê²Œì‹œíŒ ë¦¬ìŠ¤íŠ¸ í˜ì´ì§€ì— ë“¤ì–´ì™”ìŠµë‹ˆë‹¤.");
			
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
 //ï¿½Ìµï¿½
RequestDispatcher rd = request.getRequestDispatcher("/board/boardList2.jsp");
rd.forward(request, response);
}
		
		
		// ê²Œì‹œíŒ ë¦¬ìŠ¤íŠ¸ ì¡°íšŒìˆ˜ ìˆœìœ¼ë¡œ ë‚˜ì—´
		 else if(command.equals("/board/HitArray.do")) {
			    System.out.println("ì¡°íšŒìˆ˜ ìˆœìœ¼ë¡œ ë‚˜ì—´");
			    
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
		
		// ê²Œì‹œíŒ ë¦¬ìŠ¤íŠ¸ ì¢‹ì•„ìš”ìˆœìœ¼ë¡œ ë‚˜ì—´
			 else if(command.equals("/board/LikeArray.do")) {
				    System.out.println("ì¡°íšŒìˆ˜ ìˆœìœ¼ë¡œ ë‚˜ì—´");
				    
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
	  //ï¿½Ìµï¿½
		RequestDispatcher rd = request.getRequestDispatcher("/board/boardListLike.jsp");
		rd.forward(request, response);
					 
					 	 }
		
		
			// ê²Œì‹œíŒ ë¦¬ìŠ¤íŠ¸ í™”ë‚˜ìš”ìˆœìœ¼ë¡œ ë‚˜ì—´
			 else if(command.equals("/board/AngryArray.do")) {
				    System.out.println("ì¡°íšŒìˆ˜ ìˆœìœ¼ë¡œ ë‚˜ì—´");
				    
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
	  //ï¿½Ìµï¿½
		RequestDispatcher rd = request.getRequestDispatcher("/board/boardListAngry.jsp");
		rd.forward(request, response);
					 
					 	 }
		
		
			
	//===============ê²Œì‹œë¬¼ ë³´rl=============================================================
			else if (command.equals("/board/boardContent.do")) {
			System.out.println("ê²Œì‹œë¬¼ ë³´ê¸° í˜ì´ì§€ë¡œ ë“¤ì–´ì™”ìŠµë‹ˆë‹¤.");
			
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
			System.out.println("ê´€ë¦¬ì ì „ìš© ê²Œì‹œë¬¼ ì—´ëŒ í˜ì´ì§€");
			
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
			System.out.println("ì¼ë°˜ ê²Œì‹œë¬¼ ì—´ëŒ í˜ì´ì§€ë¡œ ë“¤ì–´ì™”ìŠµë‹ˆë‹¤.");
			
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

		
		//------------------ê²Œì‹œë¬¼ ìˆ˜ì •í•˜ê¸° --------------------------------------------------------------------------------
		else if(command.equals("/board/boardModify.do")) {
			System.out.println("ìˆ˜ì •í•˜ê¸° í˜ì´ì§€ë¡œ ë„˜ì–´ê°€ë‹ˆë‹¤.");
			
			String bidx = request.getParameter("bidx");             
			int bidx_ = Integer.parseInt(bidx);                       
		
			
			BoardDao bd = new BoardDao();                         
			BoardVo bv = bd.boardSelectOne(bidx_);
			request.setAttribute("bv", bv);     
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardModify.jsp");        
			rd.forward(request, response);  
		}
		else if(command.equals("/board/boardModifyAction.do")) {                    
			System.out.println("ìˆ˜ì •í•˜ê¸° í˜ì´ì§€ë¡œ ë“¤ì–´ì™”ìŠµë‹ˆë‹¤.");
			
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

			}else {     // ìˆ˜ì •í•˜ê¸° ì‹¤íŒ¨í•˜ì˜€ì„ ë•Œ
				response.sendRedirect(request.getContextPath()+"/board/boardModify.do?bidx=");  
			}
					
			}
		
		// ê²Œì‹œë¬¼ ì‚­ì œí•˜ê¸°========================================================================
		else if(command.equals("/board/boardDelete.do"))   {
				System.out.println("ì‚­ì œí•˜ê¸° í˜ì´ì§€ë¡œ ë„˜ì–´ê°‘ë‹ˆë‹¤.");
				
				String bidx = request.getParameter("bidx");   
				int bidx_ = Integer.parseInt(bidx);          
			
				
				BoardDao bd = new BoardDao();
				BoardVo bv = bd.boardSelectOne(bidx_);
				request.setAttribute("bv", bv);   
				
				RequestDispatcher rd = request.getRequestDispatcher("/board/boardDelete.jsp");
				rd.forward(request, response);
			}
		else if(command.equals("/board/boardDeleteAction.do")) {
				System.out.println("ê²Œì‹œë¬¼ ì‚­ì œí•˜ê¸° í˜ì´ì§€ë¡œ ë“¤ì–´ì™”ìŠµë‹ˆë‹¤.");
				
				String bidx = request.getParameter("bidx");    
				int bidx_ = Integer.parseInt(bidx);            
				
				BoardDao bd = new BoardDao();
				int value = bd.delete(bidx_);
			
				if(value==1) {				
					response.sendRedirect(request.getContextPath()+"/home/index.do");   
				}else {  // ê²Œì‹œë¬¼ ì‚­ì œì— ì‹¤íŒ¨í•˜ì˜€ì„ë•Œ
					response.sendRedirect(request.getContextPath()+"/board/boardContent.do?bidx=");  
				}
				
	
			} 
		// íŒŒì¼ ë‹¤ìš´ë¡œë“œ ì•„ì§ ì ìš©ì‹œí‚¬ ìƒê° ì—†ìŒ.
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
			    System.out.println("ì¡°íšŒìˆ˜ ìˆœìœ¼ë¡œ ë‚˜ì—´");
			    
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
     //ï¿½Ìµï¿½
	RequestDispatcher rd = request.getRequestDispatcher("/board/boardList.jsp");
	rd.forward(request, response);
				 
				 
			 }
	

  
		
		
		
		

		
		
		
		
		
		
		
		
}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
=======
import jspstudy.domain.Criteria;
import jspstudy.domain.PageMaker;
import jspstudy.domain.SearchCriteria;
import jspstudy.service.BoardDao;


@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//°¡»ó°æ·Î·Î ¿Â request°¡ ÀÖÀ¸¸é  Ã³¸®
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String uri = request.getRequestURI();
		String pj = request.getContextPath();
		String command = uri.substring(pj.length());
		
		int sizeLimit = 1024*1024*15;
		String uploadPath="D:\\openApi(B)\\dev\\jspstudy\\src\\main\\webapp\\";
		String saveFolder="images";
		String saveFullPath = uploadPath+saveFolder;
		
		
		if (command.equals("/board/boardWrite.do")) {
			System.out.println("±Û¾²±â È­¸é¿¡ µé¾î¿ÔÀ½");
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardWrite.jsp");
			rd.forward(request, response);
		}else if (command.equals("/board/boardWriteAction.do")) {
			System.out.println("±Û¾²±â Ã³¸® È­¸éÀ¸·Î µé¾î¿ÔÀ½");
			
			
			MultipartRequest multipartRequest = null;
			multipartRequest = new MultipartRequest(request,saveFullPath,sizeLimit,"UTF-8",new DefaultFileRenamePolicy());
						
			String subject = multipartRequest.getParameter("subject");
			String content = multipartRequest.getParameter("content");
			String writer = multipartRequest.getParameter("writer");
			//System.out.println(subject+";"+content+";"+writer);
			
			//¿­°ÅÀÚÀÎ ÀúÀåµÉ ÆÄÀÏÀ» ´ã´Â °´Ã¼»ı¼º
			Enumeration files =  multipartRequest.getFileNames();
			//´ã±ä °´Ã¼ÀÇ ÆÄÀÏ ÀÌ¸§À» ¾ò´Â´Ù
			String file = (String)files.nextElement();
			//³Ñ¾î¿À´Â °´Ã¼Áß¿¡ ÇØ´çµÇ´Â ÆÄÀÏÀÌ¸§À¸·Î µÇ¾îÀÖ´Â ÆÄÀÏÀÌ¸§À» ÃßÃâÇÑ´Ù(ÀúÀåµÇ´Â ÆÄÀÏÀÌ¸§)
			String fileName = multipartRequest.getFilesystemName(file);
			//¿øº»ÀÇ ÆÄÀÏÀÌ¸§
			String originFileName= multipartRequest.getOriginalFileName(file);
			
			
			String ip = InetAddress.getLocalHost().getHostAddress();
			
			//int midx = 2;
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
			
			BoardDao bd = new BoardDao();
			int value = bd.insertBoard(subject, content, writer, ip, midx,fileName);
			
			if (value==1) {
				response.sendRedirect(request.getContextPath()+"/index.jsp");				
			}else {
				response.sendRedirect(request.getContextPath()+"/board/boardWrite.do");
			}			
			
		}else if (command.equals("/board/boardList.do")) {
			System.out.println("°Ô½ÃÆÇ ¸®½ºÆ® È­¸é µé¾î¿ÔÀ½");
			
			String page = request.getParameter("page");
			if (page == null) page = "1";
			int pagex = Integer.parseInt(page);
			
			String keyword = request.getParameter("keyword");
			if (keyword == null) keyword = "";
				
			String searchType = request.getParameter("searchType");
			if (searchType == null) searchType="subejct";
					
			SearchCriteria scri = new SearchCriteria();
			scri.setPage(pagex);
			scri.setSearchType(searchType);
			scri.setKeyword(keyword);
						
			//Ã³¸®
			BoardDao bd = new BoardDao();
			int cnt = bd.boardTotal(scri);			
			
			PageMaker pm = new PageMaker();
			pm.setScri(scri);
			pm.setTotalCount(cnt);
			
			ArrayList<BoardVo> alist  = bd.boardSelectAll(scri);
			
			request.setAttribute("alist", alist);
			request.setAttribute("pm", pm);
			
			//ÀÌµ¿
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardList.jsp");
			rd.forward(request, response);
			
		}else if (command.equals("/board/boardContent.do")) {
			//1.³Ñ¾î¿Â °ªÀ» ¹Ş´Â´Ù
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);
			
			//2.Ã³¸®ÇÑ´Ù
			BoardDao bd =new BoardDao();
			BoardVo bv = bd.boardSelectOne(bidx_);
			request.setAttribute("bv", bv);		 //³»ºÎ¿¡ °°Àº À§Ä¡¿¡¼­ ÀÚ¿ø °øÀ¯ÇÑ´Ù
			
			//3.ÀÌµ¿ÇÑ´Ù
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardContent.jsp");
			rd.forward(request, response);
		}else if (command.equals("/board/boardModify.do")) {
			
			System.out.println("¼öÁ¤ µé¾î¿ÔÀ½");
			//1.ÆÄ¶ó¹ÌÅÍ°¡ ³Ñ¾î¿È
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);
			
			//2. Ã³¸®ÇÔ
			BoardDao bd = new BoardDao();
			BoardVo bv = bd.boardSelectOne(bidx_);
			
			request.setAttribute("bv", bv);		//³»ºÎÀûÀ¸·Î ÀÚ¿ø°øÀ¯	
			
			//3.ÀÌµ¿ÇÔ			
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardModify.jsp");			
			rd.forward(request, response);	
		}else if (command.equals("/board/boardModifyAction.do")) {
			
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);
			
			String ip = InetAddress.getLocalHost().getHostAddress();
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
			BoardDao bd = new BoardDao();
			int value = bd.updateBoard(subject, content, writer, ip, midx,bidx_);
			System.out.println(value);
			if (value ==1) {
				response.sendRedirect(request.getContextPath()+"/board/boardContent.do?bidx="+bidx);				
			}else {
				response.sendRedirect(request.getContextPath()+"/board/boardModify.do?bidx="+bidx);				
			}	
			
			
		}else if (command.equals("/board/boardDelete.do")) {
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);
			
			request.setAttribute("bidx", bidx);
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardDelete.jsp");					
			rd.forward(request, response);
			
		}else if (command.equals("/board/boardDeleteAction.do")) {
			
		
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);
						
			BoardDao bd = new BoardDao();
			int value = bd.deleteBoard(bidx_);
	
			if (value ==1) {
				response.sendRedirect(request.getContextPath()+"/board/boardList.do");				
			}else {
				response.sendRedirect(request.getContextPath()+"/board/boardContent.do?bidx="+bidx);				
			}	
		}else if (command.equals("/board/boardReply.do")) {
			
			String bidx = request.getParameter("bidx");
			String originbidx = request.getParameter("originbidx");
			String depth = request.getParameter("depth");
			String level_ = request.getParameter("level_");
			
			BoardVo bv = new BoardVo();
			bv.setBidx(Integer.parseInt(bidx));
			bv.setOriginbidx(Integer.parseInt(originbidx));
			bv.setDepth(Integer.parseInt(depth));
			bv.setLevel_(Integer.parseInt(level_));
			
			request.setAttribute("bv", bv);			
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardReply.jsp");					
			rd.forward(request, response);
						
		} else if (command.equals("/board/boardReplyAction.do")) {
			
			String bidx = request.getParameter("bidx");
			String originbidx = request.getParameter("originbidx");
			String depth = request.getParameter("depth");
			String level_ = request.getParameter("level_");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			String ip = InetAddress.getLocalHost().getHostAddress();
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
			BoardVo bv = new BoardVo();
			bv.setBidx(Integer.parseInt(bidx));
			bv.setOriginbidx(Integer.parseInt(originbidx));
			bv.setDepth(Integer.parseInt(depth));
			bv.setLevel_(Integer.parseInt(level_));
			bv.setSubject(subject);
			bv.setContent(content);
			bv.setWriter(writer);
			bv.setIp(ip);
			bv.setMidx(midx);
			
			BoardDao bd = new BoardDao();
			int value = bd.replyBoard(bv);
			
			if (value ==1) {
				response.sendRedirect(request.getContextPath()+"/board/boardList.do");				
			}else {
				response.sendRedirect(request.getContextPath()+"/board/boardContent.do?bidx="+bidx);				
			}	
			
		} else if (command.equals("/board/fileDownload.do")) {
			
			String filename= request.getParameter("filename");
			//ÆÄÀÏÀÇ ÀüÃ¼°æ·Î
			String filePath = saveFullPath+File.separator+filename;
			Path source = Paths.get(filePath);
			
			String mimeType = Files.probeContentType(source);
			
			if(mimeType == null) { 
				mimeType = "application/octet-stream"; 
			} 			
			
			//ÆÄÀÏÇü½ÄÀ» Çì´õÁ¤º¸¿¡ ´ã´Â´Ù
			response.setContentType(mimeType);
			
			String encodingFileName = new String(filename.getBytes("UTF-8"));
			//Ã·ºÎÇØ¼­ ´Ù¿î·ÎµåµÇ´Â ÆÄÀÏÀ» Çì´õÁ¤º¸¿¡ ´ã´Â´Ù
			response.setHeader("Content-Disposition", "attachment;fileName="+encodingFileName);
			
			//ÇØ´çÀ§Ä¡¿¡ ÀÖ´Â ÆÄÀÏÀ» ÀĞ¾îµå¸°´Ù.
			FileInputStream fileInputStream = new FileInputStream(filePath);
			//ÆÄÀÏ¾²±âÀ§ÇÑ ½ºÆ®¸²
			ServletOutputStream servletOutStream = response.getOutputStream();
			
			byte[] b = new byte[4096];
			
			int read = 0;
			
			while((read =fileInputStream.read(b, 0, b.length))!=-1) {
				//ÆÄÀÏ¾²±â
				servletOutStream.write(b, 0, read);
				
			}
			//Ãâ·Â
			servletOutStream.flush();
			servletOutStream.close();
			fileInputStream.close();
			
			
		}
		
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
>>>>>>> branch 'master' of https://github.com/tjdwnd105/apiB.git
