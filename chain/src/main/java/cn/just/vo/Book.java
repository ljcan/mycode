package cn.just.vo;


/**
 * 涔︾被
 * @author Shinelon
 *
 */
public class Book {
	private int id;
	private String bname;		//涔﹀悕
	private double bprice;		//涔︾殑浠锋牸
	private String bauthor;		//涔︾殑浣滆��
	private int bbuy;			//鏄惁璐拱涔︼紝1琛ㄧず璐拱锛�0琛ㄧず涓嶈喘涔�
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	
	public String getBauthor() {
		return bauthor;
	}
	public void setBauthor(String bauthor) {
		this.bauthor = bauthor;
	}
	public int getBbuy() {
		return bbuy;
	}
	public void setBbuy(int bbuy) {
		this.bbuy = bbuy;
	}
	public double getBprice() {
		return bprice;
	}
	public void setBprice(double bprice) {
		this.bprice = bprice;
	}
	public Book(int id, String bname, double bprice, String bauthor, int bbuy) {
		super();
		this.id = id;
		this.bname = bname;
		this.bprice = bprice;
		this.bauthor = bauthor;
		this.bbuy = bbuy;
	}
	public Book() {
		// TODO Auto-generated constructor stub
	}	
}
