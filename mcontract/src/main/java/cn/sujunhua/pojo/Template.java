package cn.sujunhua.pojo;

import java.io.Serializable;

public class Template implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer template_id;
	private String template_file;
	private String template_filename;

	public Integer getTemplate_id() {
		return template_id;
	}

	public String getTemplate_file() {
		return template_file;
	}

	public String getTemplate_filename() {
		return template_filename;
	}

	public void setTemplate_id(Integer template_id) {
		this.template_id = template_id;
	}

	public void setTemplate_file(String template_file) {
		this.template_file = template_file;
	}

	public void setTemplate_filename(String template_filename) {
		this.template_filename = template_filename;
	}

	@Override
	public String toString() {
		return "Template [template_id=" + template_id + ", template_file=" + template_file + ", template_filename="
				+ template_filename + "]";
	}
}
