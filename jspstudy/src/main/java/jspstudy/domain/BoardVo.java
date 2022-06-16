package jspstudy.domain;

<<<<<<< HEAD
public class BoardVo {   //board sql�� �ִ� �͵� �����ͼ� ����ȯ ��Ű��
	
    private int bidx;
	private String subject;
	private String content;
	private String writer;
	private String writeday;
	private String ip;
	private String delyn;
	private int midx;
	private String filename;
	private String transportation;
	private int like_it;
	private int hits;
	private int angry_it;
	 
	 public String getTransportation() {
		return transportation;
	}
	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}
	public String getFilename() {
		return filename;
	}
	public void setfilename(String filename) {
		this.filename = filename;
	}
	public int getbidx() {
		 return bidx;
	 }
	 public void setbidx(int bidx) {
		 this.bidx = bidx;
	 }
	 
	 public String getsubject() {
		 return subject;
	 }
	 public void setsubject(String subject) {
		 this.subject = subject;
	 }
	 
	 public String getcontent() {
		 return content;
	 }
	 public void setcontent(String content) {
		 this.content = content;
	 }
	 
	 public String getwriter() {
		 return writer;
	 }
	 public void setwriter(String writer) {
		 this.writer = writer;
	 }
	 
	 public String getwriteday() {
		 return writeday;
	 }
	 public void setwriteday(String writeday) {
		 this.writeday = writeday;
	 }
	 
	public String getip() {
			return ip;
	}
	public void setip(String ip) {
			this.ip = ip;
	}
	
	public String getdelyn() {
		return delyn;
	}
	public void setdelyn(String delyn) {
		this.delyn = delyn;
	}
	
	public int getmidx() {
		return midx;
	}
	public void setmidx(int midx) {
		this.midx = midx;
	}
	
	public int getLike_it() {
		return like_it;
	}
	public void setLike_it(int like_it) {
		this.like_it = like_it;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getAngry_it() {
		return angry_it;
	}
	public void setAngry_it(int angry_it) {
		this.angry_it = angry_it;
	}
	

	
	
	
}
=======
public class BoardVo {	
	
	private int bidx;
	private int originbidx;
	private int depth;
	private int level_;
	private String subject;
	private String content;
	private String writer;
	private String writeday;
	private String delyn;
	private String ip;
	private int midx;
	private String filename;
	
	
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getMidx() {
		return midx;
	}
	public void setMidx(int midx) {
		this.midx = midx;
	}
	public int getBidx() {
		return bidx;
	}
	public void setBidx(int bidx) {
		this.bidx = bidx;
	}
	public int getOriginbidx() {
		return originbidx;
	}
	public void setOriginbidx(int originbidx) {
		this.originbidx = originbidx;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getLevel_() {
		return level_;
	}
	public void setLevel_(int level_) {
		this.level_ = level_;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWriteday() {
		return writeday;
	}
	public void setWriteday(String writeday) {
		this.writeday = writeday;
	}
	public String getDelyn() {
		return delyn;
	}
	public void setDelyn(String delyn) {
		this.delyn = delyn;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
	

}
>>>>>>> branch 'master' of https://github.com/tjdwnd105/apiB.git
