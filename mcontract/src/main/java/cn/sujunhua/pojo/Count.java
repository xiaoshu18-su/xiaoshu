package cn.sujunhua.pojo;

import java.io.Serializable;

public class Count implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer count_id;
	// 这个是user表的id，主键
	private Integer count_uid;
	private String count_date;
	private User user;
	public Count() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Count(Integer count_id, Integer count_uid, String count_date, User user) {
		super();
		this.count_id = count_id;
		this.count_uid = count_uid;
		this.count_date = count_date;
		this.user = user;
	}

	public Integer getCount_id() {
		return count_id;
	}

	public Integer getCount_uid() {
		return count_uid;
	}

	public String getCount_date() {
		return count_date;
	}

	public User getUser() {
		return user;
	}

	public void setCount_id(Integer count_id) {
		this.count_id = count_id;
	}

	public void setCount_uid(Integer count_uid) {
		this.count_uid = count_uid;
	}

	public void setCount_date(String count_date) {
		this.count_date = count_date;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Count [count_id=" + count_id + ", count_uid=" + count_uid + ", count_date=" + count_date + ", user="
				+ user + "]";
	}

}
