package jspstudy.domain;

public class ReplyVo {
	
	private int ridx;
	private int r_origin;
	private int r_depth;
	private int r_lev;
	private int bidx;
	private String r_name;
	private String r_content;
	private String r_wday;
	private String r_delyn;
	private String r_mdday;
	private int r_like_it;
	
	
	public int getRidx() {
		return ridx;
	}
	public void setRidx(int ridx) {
		this.ridx = ridx;
	}
	public int getR_origin() {
		return r_origin;
	}
	public void setR_origin(int r_origin) {
		this.r_origin = r_origin;
	}
	public int getR_depth() {
		return r_depth;
	}
	public void setR_depth(int r_depth) {
		this.r_depth = r_depth;
	}
	public int getR_lev() {
		return r_lev;
	}
	public void setR_lev(int r_lev) {
		this.r_lev = r_lev;
	}
	public int getBidx() {
		return bidx;
	}
	public void setBidx(int bidx) {
		this.bidx = bidx;
	}
	public String getR_name() {
		return r_name;
	}
	public void setR_name(String r_name) {
		this.r_name = r_name;
	}
	public String getR_content() {
		return r_content;
	}
	public void setR_content(String r_content) {
		this.r_content = r_content;
	}
	public String getR_wday() {
		return r_wday;
	}
	public void setR_wday(String r_wday) {
		this.r_wday = r_wday;
	}
	public String getR_delyn() {
		return r_delyn;
	}
	public void setR_delyn(String r_delyn) {
		this.r_delyn = r_delyn;
	}
	public String getR_mdday() {
		return r_mdday;
	}
	public void setR_mdday(String r_mdday) {
		this.r_mdday = r_mdday;
	}
	
    public int getR_like_it() {
		return r_like_it;
	}
	public void setR_like_it(int r_like_it) {
		this.r_like_it = r_like_it;
	}
	@Override
    public String toString() {
        return "ReplyVo [ridx=" + ridx + ", bidx=" + bidx + ", r_content=" + r_content + ", r_name=" + r_name + ", r_delyn=" + r_delyn + ", r_mdday=" + r_mdday + ", r_wday=" + r_wday + ", r_lev="
                + r_lev + "]";
    }
	

}
