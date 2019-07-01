package cn.sujunhua.common.utils;

/**
 * 这个类是统计合同信息的 其中包含了三种图形的信息，柱状图，折线图以及饼状图。
 *  其中，柱状图是根据合同的开始时间来统计的 折线图是根据合同的金额来统计
 * 饼状图是根据收款状态来统计
 * 
 * @author xiaoshu
 *
 */
public class ContractEcharts {
	// 柱状图的月份，这个是根据月份统计的
	private String columnarmonth;

	// 柱状图当前月份拥有的合同数量
	private Integer columnarcount;

	// 当前合同的合同名称
	private String brokenlinename;

	// 当前合同的合同金额,读取出来后从decimal转成String
	private String brokenlinemoney;

	// 饼状图的收款状态的名称
	private String cakelikename;

	// 饼状图收款状态的数量
	private Integer cakelikecount;

	public String getColumnarmonth() {
		return columnarmonth;
	}

	public Integer getColumnarcount() {
		return columnarcount;
	}

	public String getBrokenlinename() {
		return brokenlinename;
	}

	public String getBrokenlinemoney() {
		return brokenlinemoney;
	}

	public String getCakelikename() {
		return cakelikename;
	}

	public Integer getCakelikecount() {
		return cakelikecount;
	}

	public void setColumnarmonth(String columnarmonth) {
		this.columnarmonth = columnarmonth;
	}

	public void setColumnarcount(Integer columnarcount) {
		this.columnarcount = columnarcount;
	}

	public void setBrokenlinename(String brokenlinename) {
		this.brokenlinename = brokenlinename;
	}

	public void setBrokenlinemoney(String brokenlinemoney) {
		this.brokenlinemoney = brokenlinemoney;
	}

	public void setCakelikename(String cakelikename) {
		this.cakelikename = cakelikename;
	}

	public void setCakelikecount(Integer cakelikecount) {
		this.cakelikecount = cakelikecount;
	}
	@Override
	public String toString() {
		return "ContractEcharts [columnarmonth=" + columnarmonth + ", columnarcount=" + columnarcount
				+ ", brokenlinename=" + brokenlinename + ", brokenlinemoney=" + brokenlinemoney + ", cakelikename="
				+ cakelikename + ", cakelikecount=" + cakelikecount + "]";
	}
}
