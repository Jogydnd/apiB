package jspstudy.domain;

public class Criteria {
	
<<<<<<< HEAD
	private int page;              
	private int perPageNum;     
	
	public Criteria() {
		this.page = 1;
		this.perPageNum = 15;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		
		if(page<=1) {
			this.page = 1;
			return;
		}
		
		this.page = page;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}
=======
		private int page;           //������ ��ȣ
		private int perPageNum;     //ȭ�鿡 ����Ʈ ��°���
	
	
		public Criteria() {
			this.page = 1;
			this.perPageNum = 15;
		}


		public int getPage() {
			return page;
		}


		public void setPage(int page) {
			
			if (page<=1) {
				this.page = 1;
				return;
			}
			
			this.page = page;
		}


		public int getPerPageNum() {
			return perPageNum;
		}


		public void setPerPageNum(int perPageNum) {
			this.perPageNum = perPageNum;
		}		
	
>>>>>>> branch 'master' of https://github.com/tjdwnd105/apiB.git

}
