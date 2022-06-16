package jspstudy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jspstudy.dbconn.Dbconn;
import jspstudy.domain.MemberVo;

public class MemberDao {
	
	private Connection conn;
<<<<<<< HEAD
    private PreparedStatement pstmt;
	
	
	public MemberDao() {
		Dbconn db = new Dbconn();
		this.conn = db.getConnection();
				
	}
		
    public int insertMember(String memberEmail, String memberPwd, String memberName, String memberGender, String memberAddr, String memberPhone, String ip){     // insert ï¿½Ô¼ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½
    	int value =0;   // value ï¿½ï¿½ ï¿½Ê±ï¿½È­
    	 System.out.println(memberEmail);
		    System.out.println(memberPwd);
		    System.out.println(memberName);
		    System.out.println(memberPhone);
		    System.out.println(memberGender);
		    System.out.println(memberAddr);
		    System.out.println(ip);
    	String sql = "insert into b_member(MEMBEREMAIL,MEMBERPWD,"       //sqlï¿½ï¿½ï¿½ï¿½ insert ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½Â´ï¿½.
        		+"MEMBERNAME,MEMBERGENDER,MEMBERADDR,"
        		+"MEMBERPHONE,MEMBERIP) "
        		+"values(?,?,?,?,?,?,?)";
    	
            try{    // ï¿½ï¿½ï¿½Ü¹ß»ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½Ö´ï¿½ ï¿½Úµï¿½ ï¿½Îºï¿½
            pstmt=	conn.prepareStatement(sql);

            pstmt.setString(1,memberEmail);
            pstmt.setString(2,memberPwd);
            pstmt.setString(3,memberName);
            pstmt.setString(4,memberGender);
            pstmt.setString(5,memberAddr);
            pstmt.setString(6,memberPhone);
            pstmt.setString(7,ip);
            
            value = pstmt.executeUpdate();
            
 //   Statement stmt = conn.createStatement();
   //   value =  stmt.executeUpdate(sql); 
            }catch(Exception e){    
            	e.printStackTrace();   
            }finally {     
            	try {   
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }
    	
    	return value;
    }
  
 public ArrayList<MemberVo> memberSelectAll() {
	 ArrayList<MemberVo> alist = new ArrayList<MemberVo>();
	 PreparedStatement pstmt = null;
	 ResultSet rs = null;
	 

	 String sql="select * from b_member where delyn='N' order by midx desc";
	 
	 
	 
	 try{ 	
	 pstmt = conn.prepareStatement(sql);
	 rs = pstmt.executeQuery();
	 
	 while(rs.next()){
		
		 MemberVo mv = new MemberVo();
		 
		 mv.setMidx(rs.getInt("midx"));
		 mv.setMembername(rs.getString("memberName"));
		 mv.setMemberphone(rs.getString("memberPhone"));
		 mv.setWriteday(rs.getString("writeday"));
		
		 alist.add(mv);
	 }
	 
	 }catch(Exception e){
		 e.printStackTrace();
	 }finally {   // ï¿½×»ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ç´ï¿½ ï¿½Îºï¿½ï¿½Ô´Ï´ï¿½. // try ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ close ï¿½Úµå°¡ ï¿½Ûµï¿½ï¿½Ïµï¿½ï¿½ï¿½ ï¿½ï¿½. 
		 try {
			 pstmt.close();
			 conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 }
	 
	 return alist;
 }
 
 public MemberVo memberLogin(String memberEmail, String memberPwd) {
	MemberVo mv = null;
	ResultSet rs = null;
	
	String sql="select * from b_member where delyn='N' and memberEmail=? and memberpwd=?";  // b_memberï¿½ï¿½ï¿½ï¿½ delynï¿½ï¿½ Nï¿½Î°ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½Ìµï¿½ï¿½ ï¿½ï¿½Ğ¹ï¿½È£ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Úµï¿½
			
			try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberEmail);
			pstmt.setString(2, memberPwd);
		
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mv = new MemberVo();
				mv.setMidx(rs.getInt("midx"));
				mv.setMemberrank(rs.getString("memberrank"));
				mv.setMamberemail(rs.getString("memberEmail"));
				mv.setMembername(rs.getString("memberName"));
				
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {  // try ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ close ï¿½Úµå°¡ ï¿½Ûµï¿½ï¿½Ïµï¿½ï¿½ï¿½ ï¿½ï¿½. 
				try {
					rs.close();
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	 	 return mv;
 }
 

//--------------------------ë¹„ë°€ë²ˆí˜¸ ì°¾ê¸° ë©”ì†Œë“œ-----------------------------
public String findPwd(String membername, String memberemail) {
	String memberpwd=null;
	ResultSet rs = null;
	
			try {
		String sql="select memberpwd from b_member where membername=? and memberemail=?";
						
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, membername);
				pstmt.setString(2, memberemail);
				
				rs= pstmt.executeQuery();
				if(rs.next())
					memberpwd=rs.getString("memberpwd");
			
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
			return memberpwd;
}

//-----------------------------------íšŒì› ì‚­ì œ ë©”ì†Œë“œ êµ¬í˜„ì¤€ë¹„-------------------------------
//public void delMember(String memberEmail) {
//	
//	  try {
//		pstmt=	conn.prepareStatement(sql);
//	} catch (SQLException e) {
//	
//		e.printStackTrace();
//	}
//}


 //-----------------------------------ë§ˆì´í˜ì´ì§€ë¡œ ë„˜ì–´ê°€ê¸°ìœ„í•œ ë³¸ì¸í™•ì¸ ë° ì •ë³´ ë„˜ê²¨ì£¼ëŠ” ë©”ì†Œë“œ -----------------------
public MemberVo MyPage(String memberEmail) {
	MemberVo mv = null;
	ResultSet rs = null;
	
	String sql="select * from b_member where delyn='N' and memberEmail=?";  // b_memberï¿½ï¿½ï¿½ï¿½ delynï¿½ï¿½ Nï¿½Î°ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½Ìµï¿½ï¿½ ï¿½ï¿½Ğ¹ï¿½È£ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Úµï¿½
			
			try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberEmail);
		
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mv = new MemberVo();
				mv.setMidx(rs.getInt("midx"));
				mv.setMamberemail(rs.getString("memberEmail"));
				mv.setMembername(rs.getString("memberName"));
				mv.setMemberphone(rs.getString("memberPhone"));
				mv.setWriteday(rs.getString("writeday"));
				mv.setMemberaddr(rs.getString("memberAddr"));
				mv.setMembergender(rs.getString("memberGender"));
				
			
				
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {  // try ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ close ï¿½Úµå°¡ ï¿½Ûµï¿½ï¿½Ïµï¿½ï¿½ï¿½ ï¿½ï¿½. 
				try {
					rs.close();
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	 	 return mv;
 }


   ////////////------------- ì´ë©”ì¼ ì¤‘ë³µí™•ì¸ ë©”ì†Œë“œ -----------------------------------------
public String EmailJoin(String memberemail) {
	ResultSet rs = null;
	String member_email = null;
	String sql="select memberemail from b_member where memberEmail=?";  // b_memberï¿½ï¿½ï¿½ï¿½ delynï¿½ï¿½ Nï¿½Î°ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½Ìµï¿½ï¿½ ï¿½ï¿½Ğ¹ï¿½È£ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Úµï¿½
			
			try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberemail);	rs = pstmt.executeQuery();
			
			rs= pstmt.executeQuery();
			if(rs.next())
				member_email =rs.getString("memberemail");
		
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
		return member_email;
}

//// ë©¤ë²„ ë“±ê¸‰ ê°€ì ¸ì˜¤ê¸°====================================================
//public MemberVo rankGet(int midx) {
//	MemberVo mv = null;
//	ResultSet rs = null;
//	
//	String sql="select * from b_member where delyn='N' and midx=?";
//	
//	try {
//		pstmt = conn.prepareStatement(sql);
//		
//		pstmt.setInt(1, midx);
//			
//		rs = pstmt.executeQuery();
//		
//		if(rs.next()) {
//			mv = new MemberVo();
//			mv.setMemberrank(rs.getString("memberRank"));
//		}
//	} catch (SQLException e) {
//		e.printStackTrace();
//	}finally {  
//		try {
//			rs.close();
//			pstmt.close();
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	 return mv;
//}


























}




			



=======
	private PreparedStatement pstmt;
	
	public MemberDao() {
		Dbconn db = new Dbconn();
		this.conn = db.getConnection();		
	}	
	
	public int insertMember(String memberId,String memberPwd,String memberName,String memberGender,String memberAddr,String memberJumin,String memberPhone,String hobby, String memberEmail,String ip){
		int value=0;	
		
		String sql = "insert into b_member(MEMBERID,MEMBERPWD,MEMBERNAME,MEMBERGENDER,MEMBERADDR,MEMBERJUMIN,MEMBERPHONE,MEMBERHOBBY,MEMBEREMAIL,MEMBERIP) " 
				+"values(?,?,?,?,?,?,?,?,?,?)";
		// ±¸¹®À» ½ÇÇàÇÏ°í ¸®ÅÏ°ªÀ¸·Î ½ÇÇàµÇ¾úÀ¸¸é 1 ¾Æ´Ï¸é 0À» value º¯¼ö¿¡ ´ã´Â´Ù
		
		//¿¬°á°´Ã¼¸¦ ÅëÇØ¼­ Statement ±¸¹®°´Ã¼¸¦ »ı¼ºÇØ¼­ stmt¿¡ ´ã´Â´Ù
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPwd);
			pstmt.setString(3, memberName);
			pstmt.setString(4, memberGender);
			pstmt.setString(5, memberAddr);
			pstmt.setString(6, memberJumin);
			pstmt.setString(7, memberPhone);
			pstmt.setString(8, hobby);
			pstmt.setString(9, memberEmail);
			pstmt.setString(10,ip);
			value = pstmt.executeUpdate();			
	
		}catch(Exception e){
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


	public ArrayList<MemberVo> memberSelectAll(){
		//MemberVo ¿©·¯ °´Ã¼¸¦ ´ã´Â ArrayList Å¬·¡½º¸¦ °´Ã¼»ı¼ºÇÑ´Ù 
		ArrayList<MemberVo> alist = new ArrayList<MemberVo>();
		
		ResultSet rs = null;
		//Äõ¸®±¸¹®À» ¹®ÀÚ¿­·Î ¸¸µé¾î³õ´Â´Ù
		String sql="select * from b_member where delyn='N' order by midx desc";
			
		try{
			//¿¬°á°´Ã¼¿¡ ÀÖ´Â prepareStatement ¸Ş¼Òµå¸¦ ½ÇÇàÇØ¼­ sql¹®ÀÚ¿­À» ´ã¾Æ ±¸¹®°´Ã¼¸¦ ¸¸µç´Ù
			pstmt =  conn.prepareStatement(sql);
			rs =pstmt.executeQuery();
		
			while(rs.next()){
				//¹İº¹ÇÒ¶§¸¶´Ù °´Ã¼»ı¼ºÇÑ´Ù
				MemberVo mv  =new MemberVo();
				// rs¿¡ ´ã¾Æ³õÀº ÄÃ·³°ªµéÀ» mv¿¡ ¿Å°Ü´ã´Â´Ù
				mv.setMidx(rs.getInt("midx"));
				mv.setMembername(rs.getString("memberName"));
				mv.setMemberphone(rs.getString("memberphone"));
				mv.setWriteday(rs.getString("writeday"));
				//alist¿¡ °ªÀ» ´ãÀº °´Ã¼¸¦ Ãß°¡ÇÑ´Ù
				alist.add(mv);
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
		System.out.println("alist:"+alist);
		return alist;
	}
	
	
	public MemberVo memberLogin(String memberId, String memberPwd) {
		MemberVo mv = null;
		ResultSet rs = null;
		String sql="select * from b_member where delyn='N' and memberid=? and memberpwd=? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPwd);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				mv = new MemberVo();
				mv.setMidx(rs.getInt("midx"));
				mv.setMemberid(rs.getString("memberid"));
				mv.setMembername(rs.getString("memberName"));				
			}			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}			
		}		
		return mv;
	}
	
	

}
>>>>>>> branch 'master' of https://github.com/tjdwnd105/apiB.git
