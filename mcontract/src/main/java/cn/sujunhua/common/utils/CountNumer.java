package cn.sujunhua.common.utils;

public class CountNumer {
	private Integer number;
	private String date;

	public CountNumer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CountNumer(Integer number, String date) {
		super();
		this.number = number;
		this.date = date;
	}

	public Integer getNumber() {
		return number;
	}

	public String getDate() {
		return date;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "CountNumer [number=" + number + ", date=" + date + "]";
	}

}
