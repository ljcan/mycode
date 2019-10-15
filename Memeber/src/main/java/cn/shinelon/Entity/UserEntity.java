package cn.shinelon.Entity;


public class UserEntity {
	private Integer id;
	private String username;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public UserEntity(Integer id, String username) {
		super();
		this.id = id;
		this.username = username;
	}
	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", username=" + username + "]";
	}
	
}
