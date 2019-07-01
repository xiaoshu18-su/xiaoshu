package cn.sujunhua.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class Track implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer track_id;
	private Integer track_userid;
	private Integer track_user;
	private String track_contractid;
	private String track_partb;
	// 金额
	private BigDecimal track_money;
	private String track_collectiondate;
	// 实收金额
	private BigDecimal track_amountreceived;
	// 累计收款
	private BigDecimal tract_accumulatedreceipts;
	// 累计欠款
	private BigDecimal tract_accumulatedarrears;
	private String tract_remarks;

	private User user;

	public Integer getTrack_id() {
		return track_id;
	}

	public Integer getTrack_userid() {
		return track_userid;
	}

	public Integer getTrack_user() {
		return track_user;
	}

	public String getTrack_contractid() {
		return track_contractid;
	}

	public String getTrack_partb() {
		return track_partb;
	}

	public BigDecimal getTrack_money() {
		return track_money;
	}

	public String getTrack_collectiondate() {
		return track_collectiondate;
	}

	public BigDecimal getTrack_amountreceived() {
		return track_amountreceived;
	}

	public BigDecimal getTract_accumulatedreceipts() {
		return tract_accumulatedreceipts;
	}

	public BigDecimal getTract_accumulatedarrears() {
		return tract_accumulatedarrears;
	}

	public String getTract_remarks() {
		return tract_remarks;
	}

	public User getUser() {
		return user;
	}

	public void setTrack_id(Integer track_id) {
		this.track_id = track_id;
	}

	public void setTrack_userid(Integer track_userid) {
		this.track_userid = track_userid;
	}

	public void setTrack_user(Integer track_user) {
		this.track_user = track_user;
	}

	public void setTrack_contractid(String track_contractid) {
		this.track_contractid = track_contractid;
	}

	public void setTrack_partb(String track_partb) {
		this.track_partb = track_partb;
	}

	public void setTrack_money(BigDecimal track_money) {
		this.track_money = track_money;
	}

	public void setTrack_collectiondate(String track_collectiondate) {
		this.track_collectiondate = track_collectiondate;
	}

	public void setTrack_amountreceived(BigDecimal track_amountreceived) {
		this.track_amountreceived = track_amountreceived;
	}

	public void setTract_accumulatedreceipts(BigDecimal tract_accumulatedreceipts) {
		this.tract_accumulatedreceipts = tract_accumulatedreceipts;
	}

	public void setTract_accumulatedarrears(BigDecimal tract_accumulatedarrears) {
		this.tract_accumulatedarrears = tract_accumulatedarrears;
	}

	public void setTract_remarks(String tract_remarks) {
		this.tract_remarks = tract_remarks;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Track [track_id=" + track_id + ", track_userid=" + track_userid + ", track_user=" + track_user
				+ ", track_contractid=" + track_contractid + ", track_partb=" + track_partb + ", track_money="
				+ track_money + ", track_collectiondate=" + track_collectiondate + ", track_amountreceived="
				+ track_amountreceived + ", tract_accumulatedreceipts=" + tract_accumulatedreceipts
				+ ", tract_accumulatedarrears=" + tract_accumulatedarrears + ", tract_remarks=" + tract_remarks
				+ ", user=" + user + "]";
	}
}
