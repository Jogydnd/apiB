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
    private PreparedStatement pstmt;
	
	
	public MemberDao() {
		Dbconn db = new Dbconn();
		this.conn = db.getConnection();
				
	}
		
    public int insertMember(String memberEmail, String memberPwd, String memberName, String memberGender, String memberAddr, String memberPhone, String ip){     // insert �Լ� �����
    	int value =0;   // value �� �ʱ�ȭ
    	 System.out.println(memberEmail);
		    System.out.println(memberPwd);
		    System.out.println(memberName);
		    System.out.println(memberPhone);
		    System.out.println(memberGender);
		    System.out.println(memberAddr);
		    System.out.println(ip);
    	String sql = "insert into b_member(MEMBEREMAIL,MEMBERPWD,"       //sql���� insert ������ �����´�.
        		+"MEMBERNAME,MEMBERGENDER,MEMBERADDR,"
        		+"MEMBERPHONE,MEMBERIP) "
        		+"values(?,?,?,?,?,?,?)";
    	
            try{    // ���ܹ߻��� ���ִ� �ڵ� �κ�
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
	 }finally {   // �׻� ������ �Ǵ� �κ��Դϴ�. // try ���� ���������� ������ close �ڵ尡 �۵��ϵ��� ��. 
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
	
	String sql="select * from b_member where delyn='N' and memberEmail=? and memberpwd=?";  // b_member���� delyn�� N�ΰ��� ���̵�� ��й�ȣ�� �������� �ڵ�
			
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
			}finally {  // try ���� ���������� ������ close �ڵ尡 �۵��ϵ��� ��. 
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
 

//--------------------------비밀번호 찾기 메소드-----------------------------
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

//-----------------------------------회원 삭제 메소드 구현준비-------------------------------
//public void delMember(String memberEmail) {
//	
//	  try {
//		pstmt=	conn.prepareStatement(sql);
//	} catch (SQLException e) {
//	
//		e.printStackTrace();
//	}
//}


 //-----------------------------------마이페이지로 넘어가기위한 본인확인 및 정보 넘겨주는 메소드 -----------------------
public MemberVo MyPage(String memberEmail) {
	MemberVo mv = null;
	ResultSet rs = null;
	
	String sql="select * from b_member where delyn='N' and memberEmail=?";  // b_member���� delyn�� N�ΰ��� ���̵�� ��й�ȣ�� �������� �ڵ�
			
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
			}finally {  // try ���� ���������� ������ close �ڵ尡 �۵��ϵ��� ��. 
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


   ////////////------------- 이메일 중복확인 메소드 -----------------------------------------
public String EmailJoin(String memberemail) {
	ResultSet rs = null;
	String member_email = null;
	String sql="select memberemail from b_member where memberEmail=?";  // b_member���� delyn�� N�ΰ��� ���̵�� ��й�ȣ�� �������� �ڵ�
			
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

//// 멤버 등급 가져오기====================================================
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




			



