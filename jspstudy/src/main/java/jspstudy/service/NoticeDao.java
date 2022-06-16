package jspstudy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jspstudy.dbconn.Dbconn;
import jspstudy.domain.NoticeVo;
import jspstudy.domain.SearchCriteria;

public class NoticeDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	
	public NoticeDao() {
		Dbconn db = new Dbconn();
		this.conn = db.getConnection();
	}
	public int insertNotice(String subject, String content, int midx) {   
		int value = 0;
		String sql = "INSERT INTO b_Notice(subject,content,midx)"
				+ "VALUES(?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setInt(3, midx);
			
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
	
	public int noticeTotal(SearchCriteria scri) {
		int cnt = 0;
		ResultSet rs = null;
		String str = "";
		
	if (scri.getSearchType().equals("subject")) {  
		str = "and subject like ?";  
	}else {
		str = "and content like ?";
	}
			String sql = "select count(*) as cnt from b_notice where delyn='N' "+str+" ";
	
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
	public ArrayList<NoticeVo> noticeSelectAll(SearchCriteria scri) {
		ArrayList<NoticeVo> nlist = new ArrayList<NoticeVo>();
		
		  PreparedStatement pstmt = null;
			ResultSet rs= null;
			String str="";
			
			if (scri.getSearchType().equals("subject")) {  
				str = "and subject like ?";  
			}else {
				str = "and content like ?";
			}
			
			String sql = "select * from b_notice where delyn='N'"+str
					+"order by nidx desc limit ?,?";
		
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+scri.getKeyword()+"%");
				pstmt.setInt(2, (scri.getPage() -1)*15);
				pstmt.setInt(3, 15);
				
				 rs = pstmt.executeQuery();
				 while(rs.next()){
					 
					 NoticeVo nv = new  NoticeVo(); 
	                         
					 nv.setNidx(rs.getInt("nidx"));  
					 nv.setSubject(rs.getString("subject")); 
					 nv.setContent(rs.getString("content"));    
					 nv.setMidx(rs.getInt("midx"));     
					 nv.setWriteday(rs.getString("Writeday")); 
					 nv.setDelyn(rs.getString("delyn"));
				
					 nlist.add(nv);
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
			 
			 return nlist;
		 }
	//=========================공지사항 보기==============================================
	public NoticeVo noticeSelectOne(int nidx) {
		NoticeVo nv = null;
		ResultSet rs = null;
		String sql = "select * from b_notice where nidx =?";
		
		try {
			pstmt = conn.prepareStatement(sql);  
			pstmt.setInt(1, nidx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())   {   
			    nv = new NoticeVo();
				
				nv.setNidx(rs.getInt("nidx"));
			    nv.setSubject(rs.getString("subject"));
				nv.setContent(rs.getString("content"));
				nv.setWriteday(rs.getString("writeday"));
				nv.setMidx(rs.getInt("midx"));
				
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
		return nv;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
