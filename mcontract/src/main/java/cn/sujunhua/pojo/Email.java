package cn.sujunhua.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Email implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer email_id;
	private String email_send;
	private String email_receive;
	private String email_theme;
	private String email_content;
	private String email_namefile;
	private String email_file;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date email_datetime;

	public Email() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Email(Integer email_id, String email_send, String email_receive, String email_theme, String email_content,
			String email_namefile, String email_file, Date email_datetime) {
		super();
		this.email_id = email_id;
		this.email_send = email_send;
		this.email_receive = email_receive;
		this.email_theme = email_theme;
		this.email_content = email_content;
		this.email_namefile = email_namefile;
		this.email_file = email_file;
		this.email_datetime = email_datetime;
	}

	public Integer getEmail_id() {
		return email_id;
	}

	public void setEmail_id(Integer email_id) {
		this.email_id = email_id;
	}

	public String getEmail_send() {
		return email_send;
	}

	public void setEmail_send(String email_send) {
		this.email_send = email_send;
	}

	public String getEmail_receive() {
		return email_receive;
	}

	public void setEmail_receive(String email_receive) {
		this.email_receive = email_receive;
	}

	public String getEmail_theme() {
		return email_theme;
	}

	public void setEmail_theme(String email_theme) {
		this.email_theme = email_theme;
	}

	public String getEmail_content() {
		return email_content;
	}

	public void setEmail_content(String email_content) {
		this.email_content = email_content;
	}

	public String getEmail_file() {
		return email_file;
	}

	public void setEmail_file(String email_file) {
		this.email_file = email_file;
	}

	public Date getEmail_datetime() {
		return email_datetime;
	}

	public void setEmail_datetime(Date email_datetime) {
		this.email_datetime = email_datetime;
	}

	public String getEmail_namefile() {
		return email_namefile;
	}

	public void setEmail_namefile(String email_namefile) {
		this.email_namefile = email_namefile;
	}

	@Override
	public String toString() {
		return "Email [email_id=" + email_id + ", email_send=" + email_send + ", email_receive=" + email_receive
				+ ", email_theme=" + email_theme + ", email_content=" + email_content + ", email_namefile="
				+ email_namefile + ", email_file=" + email_file + ", email_datetime=" + email_datetime + "]";
	}

}
