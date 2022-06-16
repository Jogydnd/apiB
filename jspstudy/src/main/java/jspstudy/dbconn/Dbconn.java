package jspstudy.dbconn;

<<<<<<< HEAD
import java.sql.*;

public class Dbconn {
	
	private String url="jdbc:mysql://127.0.0.1:3306/mysql?serverTimezone=UTC&characterEncoding=UTF-8";
	private String user="root";
	private String password="1234";
    
    public Connection getConnection() {
    	Connection conn = null;
    	
    try {
    	//ë“±ë¡í•œ ë“œë¼ì´ë²„ì¤‘ì— ì‚¬ìš©ê°€ëŠ¥í•œ í´ëž˜ìŠ¤ ì°¾ì•„ì„œ ìƒì„±
		Class.forName("com.mysql.cj.jdbc.Driver");
		  //ì—°ê²° ì •ë³´ë¥¼ í†µí•´ì„œ ì—°ê²°ê°ì²´ë¥¼ ì°¸ì¡°ë³€ìˆ˜ connì— ë‹´ëŠ”ë‹¤.
		conn = DriverManager.getConnection(url, user, password);
	} catch (Exception e) {
		e.printStackTrace();
	}
  
   return conn;
    }
	
}
=======
import java.sql.Connection;
import java.sql.DriverManager;

public class Dbconn {	
	
	private String url="jdbc:mysql://127.0.0.1:3306/mysql?serverTimezone=UTC&characterEncoding=UTF-8";
	private String user="root";
	private String password="1234";		

	public Connection getConnection() {
		Connection conn = null;			

	try {
		//µî·ÏÇÑ µå¶óÀÌ¹öÁß¿¡ »ç¿ë°¡´ÉÇÑ Å¬·¡½º Ã£¾Æ¼­ »ý¼º
		Class.forName("com.mysql.cj.jdbc.Driver");
		//¿¬°áÁ¤º¸¸¦ ÅëÇØ¼­ ¿¬°á°´Ã¼¸¦ ÂüÁ¶º¯¼ö conn¿¡ ´ã´Â´Ù
		conn = DriverManager.getConnection(url, user, password);
	} catch (Exception e) {		
		e.printStackTrace();
	}	

	return conn;
	}
}
>>>>>>> branch 'master' of https://github.com/tjdwnd105/apiB.git
