package cn.sujunhua.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer user_id;
	private String user_name;
	private String user_sex;
	private String user_address;
	private String user_email;
	private String user_pwd;
	private String user_id_card;
	private String user_phone;
	private Integer user_type;
	private Integer user_part_id;
	private String user_qq_no;
	private String user_education;
	private String user_political;
	private String user_nation;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date user_birthday;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date user_entry;
	private Integer user_status;
	private String user_image;
    private Integer user_secret_id;
    private String user_secret_answer;

	// 部门实体 员工对部门 多对一
	private Department department;
	// 用户类型实体  员工对类型  多对一
	private Usertype usertype;
	// 密保实体类型  员工对密保  多对一
	private Secret secret;
	
	public Integer getUser_id() {
		return user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public String getUser_sex() {
		return user_sex;
	}
	public String getUser_address() {
		return user_address;
	}
	public String getUser_email() {
		return user_email;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public String getUser_id_card() {
		return user_id_card;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public Integer getUser_type() {
		return user_type;
	}
	public Integer getUser_part_id() {
		return user_part_id;
	}
	public String getUser_qq_no() {
		return user_qq_no;
	}
	public String getUser_education() {
		return user_education;
	}
	public String getUser_political() {
		return user_political;
	}
	public String getUser_nation() {
		return user_nation;
	}
	public Date getUser_birthday() {
		return user_birthday;
	}
	public Date getUser_entry() {
		return user_entry;
	}
	public Integer getUser_status() {
		return user_status;
	}
	public String getUser_image() {
		return user_image;
	}
	public Integer getUser_secret_id() {
		return user_secret_id;
	}
	public String getUser_secret_answer() {
		return user_secret_answer;
	}
	public Department getDepartment() {
		return department;
	}
	public Usertype getUsertype() {
		return usertype;
	}
	public Secret getSecret() {
		return secret;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public void setUser_id_card(String user_id_card) {
		this.user_id_card = user_id_card;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}
	public void setUser_part_id(Integer user_part_id) {
		this.user_part_id = user_part_id;
	}
	public void setUser_qq_no(String user_qq_no) {
		this.user_qq_no = user_qq_no;
	}
	public void setUser_education(String user_education) {
		this.user_education = user_education;
	}
	public void setUser_political(String user_political) {
		this.user_political = user_political;
	}
	public void setUser_nation(String user_nation) {
		this.user_nation = user_nation;
	}
	public void setUser_birthday(Date user_birthday) {
		this.user_birthday = user_birthday;
	}
	public void setUser_entry(Date user_entry) {
		this.user_entry = user_entry;
	}
	public void setUser_status(Integer user_status) {
		this.user_status = user_status;
	}
	public void setUser_image(String user_image) {
		this.user_image = user_image;
	}
	public void setUser_secret_id(Integer user_secret_id) {
		this.user_secret_id = user_secret_id;
	}
	public void setUser_secret_answer(String user_secret_answer) {
		this.user_secret_answer = user_secret_answer;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public void setUsertype(Usertype usertype) {
		this.usertype = usertype;
	}
	public void setSecret(Secret secret) {
		this.secret = secret;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name + ", user_sex=" + user_sex + ", user_address="
				+ user_address + ", user_email=" + user_email + ", user_pwd=" + user_pwd + ", user_id_card="
				+ user_id_card + ", user_phone=" + user_phone + ", user_type=" + user_type + ", user_part_id="
				+ user_part_id + ", user_qq_no=" + user_qq_no + ", user_education=" + user_education
				+ ", user_political=" + user_political + ", user_nation=" + user_nation + ", user_birthday="
				+ user_birthday + ", user_entry=" + user_entry + ", user_status=" + user_status + ", user_image="
				+ user_image + ", user_secret_id=" + user_secret_id + ", user_secret_answer=" + user_secret_answer
				+ ", department=" + department + ", usertype=" + usertype + ", secret=" + secret + "]";
	}
	
}
