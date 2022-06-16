package jspstudy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jspstudy.dbconn.Dbconn;
import jspstudy.domain.ReplyVo;

public class ReplyDao {
	private Connection conn;                      
	private PreparedStatement pstmt; 
	
	public ReplyDao() {
		Dbconn db = new Dbconn();
		this.conn = db.getConnection();
		
	}
	
	public int insertReply(String r_name, int bidx, String r_content) {
		int value = 0;
		String sql = "INSERT INTO B_REPLY(r_origin,r_depth,r_lev,r_name,bidx,r_content)"
		+"select max(ridx)+1,0,0,?,?,? from b_reply";
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, r_name);
			pstmt.setInt(2, bidx);
			pstmt.setString(3, r_content);
			
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
	
	
	public ArrayList<ReplyVo> ReplySelect(int bidx) {
		ArrayList<ReplyVo> rlist = new ArrayList<ReplyVo>();
		
		   PreparedStatement pstmt = null;
			ResultSet rs= null;
			
			
			String sql = "select * from b_reply where R_DELYN='N'and bidx =? order by r_origin desc, r_depth asc";
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, bidx);
				
				rs=pstmt.executeQuery();
				
				 while(rs.next()){
						
					 ReplyVo rv = new  ReplyVo();  
	                            
					 rv.setBidx(rs.getInt("bidx"));
					 rv.setR_content(rs.getString("r_content"));
					 rv.setR_depth(rs.getInt("r_depth"));
					 rv.setR_lev(rs.getInt("r_lev"));
					 rv.setR_name(rs.getString("r_name"));
					 rv.setRidx(rs.getInt("ridx"));
					 rv.setR_origin(rs.getInt("r_origin"));
					 rv.setR_wday(rs.getString("r_wday"));
					 rv.setR_like_it(rs.getInt("r_like_it"));
					
					rlist.add(rv);
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
			return rlist;
		 }
			
			
	//좋아요 업데이트	
		public int update_Good(int ridx){		
			int value = 0;
			
			 try{	
				 
				 String sql = "update b_reply set r_like_it = r_like_it+1 where ridx=?";
				 System.out.println("ridx :"+ridx);
				 
		         pstmt = conn.prepareStatement(sql);  // '?'바인드를 사용해서 sql문을 효과 적으로 사용할수있음						pstmt.setInt(1,bno);
		           
		         pstmt.setInt(1,ridx);
		           
		         value = pstmt.executeUpdate();
		        
		         System.out.println(value);
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
		
	// 댓글에 댓글달기 (대댓글)
		public int Re_reply(ReplyVo rv) {
			int value = 0;
			
			String sql1= "UPDATE b_reply SET r_depth = r_depth+1 WHERE r_origin=? and r_depth>?";
			
			String sql2= "INSERT INTO b_reply(r_origin,r_depth,r_lev,bidx ,r_name,r_content)"
					+"VALUES(?,?,?,?,?,?)";
			
			try {
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(sql1);
				pstmt.setInt(1, rv.getR_origin());
				pstmt.setInt(2, rv.getR_depth());
				pstmt.executeUpdate();
				
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, rv.getR_origin());
				pstmt.setInt(2, rv.getR_depth()+1);
				pstmt.setInt(3, rv.getR_lev()+1);
				pstmt.setInt(4, rv.getBidx());
				pstmt.setString(5, rv.getR_name());
				pstmt.setString(6, rv.getR_content());
				
				value= pstmt.executeUpdate();
				System.out.println("value :"+value);
				
				conn.commit();
				
			} catch (SQLException e) {
				try {
					conn.rollback();    // 위에 try에서 오류(예외)발생시 롤백 처리
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
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
	
	
	
	
	
	
	
	
	
			
			
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
