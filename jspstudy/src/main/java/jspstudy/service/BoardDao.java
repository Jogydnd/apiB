package jspstudy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jspstudy.dbconn.Dbconn;
import jspstudy.domain.BoardVo;
import jspstudy.domain.SearchCriteria;


public class BoardDao {
	
	private Connection conn;                 
	private PreparedStatement pstmt;  
	
	public BoardDao() {
		Dbconn db = new Dbconn();
		this.conn = db.getConnection();
		
	}
	
	public int insertBoard(String subject, String content, String writer, String ip, int midx, String fileName, String move ) {   
		int value = 0;
		String sql = "INSERT INTO b_board(subject,content,writer,ip,midx,fileName,transportation)"
				+ "VALUES(?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setString(3, writer);
			pstmt.setString(4, ip);
			pstmt.setInt(5, midx);
			pstmt.setString(6, fileName);
			pstmt.setString(7, move);
			
			value = pstmt.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}	
		return value;
	}
	
	// 게시판 리스트
	public ArrayList<BoardVo> boardSelectAll(SearchCriteria scri) {  
		ArrayList<BoardVo> blist = new ArrayList<BoardVo>();
	   PreparedStatement pstmt = null;
		ResultSet rs= null;
		String str="";
        if (scri.getSearchType().equals("subject")) {  
			str = "and subject like ?";  
		}else {
			str = "and writer like ?";
		}
		
		String sql = "select * from b_board where delyn='N' "+str+"order by bidx desc limit ?,?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+scri.getKeyword()+"%");
			pstmt.setInt(2, (scri.getPage() -1)*15);
			pstmt.setInt(3, 15);
			
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()){
				 
				 BoardVo bv = new  BoardVo();  
                         
				 bv.setbidx(rs.getInt("bidx"));  
				 bv.setcontent(rs.getString("content"));  
				 bv.setdelyn(rs.getString("delyn"));   
				 bv.setmidx(rs.getInt("midx"));     
				 bv.setsubject(rs.getString("subject"));   
				 bv.setwriter(rs.getString("writer"));  
				 bv.setwriteday(rs.getString("Writeday"));    
				 bv.setfilename(rs.getString("filename"));
				 bv.setLike_it(rs.getInt("like_it"));
				 bv.setHits(rs.getInt("hits"));
				 bv.setAngry_it(rs.getInt("angry_it"));
			
				 blist.add(bv);
			 }	 
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally {
			 try {
				 rs.close();
				 pstmt.close();
				 conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 }
		
		 System.out.println("blist :" +blist);
		 return blist;
			 }
	
	// 게시판 리스트 조회수순으로 나열
	public ArrayList<BoardVo> boardSelectHits(SearchCriteria scri) {  
		ArrayList<BoardVo> blist = new ArrayList<BoardVo>();
		   PreparedStatement pstmt = null;
			ResultSet rs= null;
			String str="";
	        if (scri.getSearchType().equals("subject")) {  
				str = "and subject like ?";  
			}else {
				str = "and writer like ?";
			}
			
			String sql = "select * from b_board where delyn='N' "+str+"order by hits desc limit ?,?";
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+scri.getKeyword()+"%");
				pstmt.setInt(2, (scri.getPage() -1)*15);
				pstmt.setInt(3, 15);
				
				 rs = pstmt.executeQuery();
				 
				 while(rs.next()){
					 
					 BoardVo bv = new  BoardVo();  
	                         
					 bv.setbidx(rs.getInt("bidx"));  
					 bv.setcontent(rs.getString("content"));  
					 bv.setdelyn(rs.getString("delyn"));   
					 bv.setmidx(rs.getInt("midx"));     
					 bv.setsubject(rs.getString("subject"));   
					 bv.setwriter(rs.getString("writer"));  
					 bv.setwriteday(rs.getString("Writeday"));    
					 bv.setfilename(rs.getString("filename"));
					 bv.setLike_it(rs.getInt("like_it"));
					 bv.setHits(rs.getInt("hits"));
					 bv.setAngry_it(rs.getInt("angry_it"));
				
					 blist.add(bv);
				 }	 
			 }catch(Exception e){
				 e.printStackTrace();
			 }finally {
				 try {
					 rs.close();
					 pstmt.close();
					 conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			 }
			
			 System.out.println("blist :" +blist);
			 return blist;
				 }
	
	
	// 게시판 리스트 좋아요순으로 나열
		public ArrayList<BoardVo> boardSelectLike(SearchCriteria scri) {  
			ArrayList<BoardVo> blist = new ArrayList<BoardVo>();
			   PreparedStatement pstmt = null;
				ResultSet rs= null;
				String str="";
		        if (scri.getSearchType().equals("subject")) {  
					str = "and subject like ?";  
				}else {
					str = "and writer like ?";
				}
				
				String sql = "select * from b_board where delyn='N' "+str+"order by like_it desc limit ?,?";
				
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "%"+scri.getKeyword()+"%");
					pstmt.setInt(2, (scri.getPage() -1)*15);
					pstmt.setInt(3, 15);
					
					 rs = pstmt.executeQuery();
					 
					 while(rs.next()){
						 
						 BoardVo bv = new  BoardVo();  
		                         
						 bv.setbidx(rs.getInt("bidx"));  
						 bv.setcontent(rs.getString("content"));  
						 bv.setdelyn(rs.getString("delyn"));   
						 bv.setmidx(rs.getInt("midx"));     
						 bv.setsubject(rs.getString("subject"));   
						 bv.setwriter(rs.getString("writer"));  
						 bv.setwriteday(rs.getString("Writeday"));    
						 bv.setfilename(rs.getString("filename"));
						 bv.setLike_it(rs.getInt("like_it"));
						 bv.setHits(rs.getInt("hits"));
						 bv.setAngry_it(rs.getInt("angry_it"));
					
						 blist.add(bv);
					 }	 
				 }catch(Exception e){
					 e.printStackTrace();
				 }finally {
					 try {
						 rs.close();
						 pstmt.close();
						 conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				 }
				
				 System.out.println("blist :" +blist);
				 return blist;
					 }
		
		// 게시판 리스트 화나요순으로 나열
				public ArrayList<BoardVo> boardSelectAngry(SearchCriteria scri) {  
					ArrayList<BoardVo> blist = new ArrayList<BoardVo>();
					   PreparedStatement pstmt = null;
						ResultSet rs= null;
						String str="";
				        if (scri.getSearchType().equals("subject")) {  
							str = "and subject like ?";  
						}else {
							str = "and writer like ?";
						}
						
						String sql = "select * from b_board where delyn='N' "+str+"order by angry_it desc limit ?,?";
						
						try {
							pstmt = conn.prepareStatement(sql);
							pstmt.setString(1, "%"+scri.getKeyword()+"%");
							pstmt.setInt(2, (scri.getPage() -1)*15);
							pstmt.setInt(3, 15);
							
							 rs = pstmt.executeQuery();
							 
							 while(rs.next()){
								 
								 BoardVo bv = new  BoardVo();  
				                         
								 bv.setbidx(rs.getInt("bidx"));  
								 bv.setcontent(rs.getString("content"));  
								 bv.setdelyn(rs.getString("delyn"));   
								 bv.setmidx(rs.getInt("midx"));     
								 bv.setsubject(rs.getString("subject"));   
								 bv.setwriter(rs.getString("writer"));  
								 bv.setwriteday(rs.getString("Writeday"));    
								 bv.setfilename(rs.getString("filename"));
								 bv.setLike_it(rs.getInt("like_it"));
								 bv.setHits(rs.getInt("hits"));
								 bv.setAngry_it(rs.getInt("angry_it"));
							
								 blist.add(bv);
							 }	 
						 }catch(Exception e){
							 e.printStackTrace();
						 }finally {
							 try {
								 rs.close();
								 pstmt.close();
								 conn.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						 }
						
						 System.out.println("blist :" +blist);
						 return blist;
							 }
	
	
	//========게시물 보기===================================================
	public BoardVo boardSelectOne(int bidx) {
		BoardVo bv = null;
		ResultSet rs = null;
		String sql1 = "select * from b_board where bidx =?"; 
		String sql2 = "update b_board set hits = hits+1 where bidx=?";
		
		try {
			pstmt = conn.prepareStatement(sql1);  
			pstmt.setInt(1, bidx);
			
			rs = pstmt.executeQuery();
			
			conn.setAutoCommit(false);  
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, bidx);
			pstmt.executeUpdate();
			
			if(rs.next())   {   
				bv = new BoardVo();     
				
				bv.setbidx(rs.getInt("bidx"));   
                bv.setsubject(rs.getString("subject"));
				bv.setcontent(rs.getString("content"));
				bv.setwriter(rs.getString("writer"));
				bv.setwriteday(rs.getString("writeday"));
				bv.setfilename(rs.getString("filename"));
				bv.setmidx(rs.getInt("midx"));
				bv.setTransportation(rs.getString("transportation"));
				bv.setLike_it(rs.getInt("like_it"));
				bv.setHits(rs.getInt("hits"));
				bv.setAngry_it(rs.getInt("angry_it"));
				
			}	 
		} catch (SQLException e) {
			e.printStackTrace(); 
		}finally {		
			try {
				rs.close();      
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bv;
	}
	
	public BoardVo boardSelectOne2(int bidx) {
		BoardVo bv = null;
		ResultSet rs = null;
		String sql = "select * from b_board where bidx =?"; 

		try {
			pstmt = conn.prepareStatement(sql);   
			pstmt.setInt(1, bidx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())   {   
				bv = new BoardVo();    
				
				bv.setbidx(rs.getInt("bidx")); 
                bv.setsubject(rs.getString("subject"));
				bv.setcontent(rs.getString("content"));
				bv.setwriter(rs.getString("writer"));
				bv.setwriteday(rs.getString("writeday"));
				bv.setfilename(rs.getString("filename"));
				bv.setmidx(rs.getInt("midx"));
				bv.setTransportation(rs.getString("transportation"));
				bv.setLike_it(rs.getInt("like_it"));
				bv.setHits(rs.getInt("hits"));
				bv.setAngry_it(rs.getInt("angry_it"));
				
			}	 
		} catch (SQLException e) {
			e.printStackTrace(); 
		}finally {		
			try {
				rs.close();     
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bv;
	}
		

 //============글수정하기=====================================================================
	public int update(String subject, String content, String writer, String ip,int midx, int bidx) {
		System.out.println(subject+";"+content+";"+writer);
		System.out.println(bidx);
int value = 0;
	String sql = "UPDATE b_board set subject=?, content=?, writer=?, ip=?, midx=?, writeday=now() where bidx=?";

	try {
		 pstmt = conn.prepareStatement(sql);
	
		pstmt.setString(1, subject);    
		pstmt.setString(2, content);    
		pstmt.setString(3, writer);     
		pstmt.setString(4, ip);         
		pstmt.setInt(5, midx);           
		pstmt.setInt(6, bidx);             
	
		
		value = pstmt.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		try {
			pstmt.close();   
			conn.close();      
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	System.out.println(value);
	return value;
	}
	
	// 게시물 삭제하기
	public int delete(int bidx)  {
		int value = 0;
		String sql = "UPDATE b_board set delyn = 'Y', writeday=now() where bidx=?";   
				
		try {   
			pstmt = conn.prepareStatement(sql);
			
 			pstmt.setInt(1, bidx);     
			
			value = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}try {
			pstmt.close();      
			conn.close();         
		} catch (SQLException e) {
			e.printStackTrace();
		}
	System.out.println(value);
	return value;
	}
	
	// 게시물 페이징 처리
	public int boardTotal(SearchCriteria scri){
		int cnt = 0;
		ResultSet rs = null;
		String str = "";
		
		if (scri.getSearchType().equals("subject")) {  
			str = "and subject like ?";  
		}else {
			str = "and writer like ?";
		}
				String sql = "select count(*) as cnt from b_board where delyn='N' "+str+" ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+scri.getKeyword()+"%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt("cnt");
				}
			System.out.println(cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	
	
	
	
	//좋아요 업데이트	
	public int update_Like(int bidx){		
		int value = 0;
		 String sql = "update b_board set like_it=like_it+1 where bidx=?";
	           
		 try{	
	         pstmt = conn.prepareStatement(sql);  // '?'바인드를 사용해서 sql문을 효과 적으로 사용할수있음						pstmt.setInt(1,bno);
	           
	         pstmt.setInt(1,bidx);
	           
	         value = pstmt.executeUpdate();
	            
	           }catch(SQLException e){	
	        	System.out.println("SQL 에러 : " + e.getMessage());
	            e.printStackTrace();		
	           }finally {			
	        	   try {
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	           }
		 System.out.println(value);
			return value;
	      }	
	
	
	//화나요 업데이트	
	public int update_Angry(int bidx){		
		int value = 0;
		 String sql = "update b_board set angry_it=angry_it+1 where bidx=?";
	           
		 try{	
	         pstmt = conn.prepareStatement(sql);  // '?'바인드를 사용해서 sql문을 효과 적으로 사용할수있음						pstmt.setInt(1,bno);
	           
	         pstmt.setInt(1,bidx);
	           
	         value = pstmt.executeUpdate();
	            
	           }catch(SQLException e){	
	        	System.out.println("SQL 에러 : " + e.getMessage());
	            e.printStackTrace();		
	           }finally {			
	        	   try {
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	           }
		 System.out.println(value);
			return value;
	      }	
	
	

	
	
	
	
	
	}
