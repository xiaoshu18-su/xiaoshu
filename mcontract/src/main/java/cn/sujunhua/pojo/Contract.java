package cn.sujunhua.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Contract implements Serializable {
	private static final long serialVersionUID = 1L;
	private String contract_id;
	private String contract_name;
	private String contract_parta;
	private String contract_partb;
	//乙方代表人
	private String contract_partbrepresentative;
	//乙方地址
	private String contract_partbaddress;
	//乙方电话
	private String contract_partbtelephone;
	//乙方签订时间
	private String contract_partbsigningtime;
	private String contract_startime;
	private String contract_endtime;
	private String contract_advancetime;
	private String contract_collectiontime;
	private BigDecimal contract_money;
	private BigDecimal contract_accumulatedreceipts;
	private BigDecimal contract_accumulatedarrears;
	private String contract_file;
	private String contract_filename;
	private Integer contract_state;
	private Integer contract_status;
    private Integer contract_modify;
    private String contract_modify_time;
    private String contract_remarks;
    private Integer contract_user;
    private Integer contract_trackstatus;
    //收款进度
    private Integer progress;
    
	private User user;
	private State state;
	private Modify modify;
	private Status status;
    private List<Track> tracks;
	public String getContract_id() {
		return contract_id;
	}
	public String getContract_name() {
		return contract_name;
	}
	public String getContract_parta() {
		return contract_parta;
	}
	public String getContract_partb() {
		return contract_partb;
	}
	public String getContract_partbrepresentative() {
		return contract_partbrepresentative;
	}
	public String getContract_partbaddress() {
		return contract_partbaddress;
	}
	public String getContract_partbtelephone() {
		return contract_partbtelephone;
	}
	public String getContract_partbsigningtime() {
		return contract_partbsigningtime;
	}
	public String getContract_startime() {
		return contract_startime;
	}
	public String getContract_endtime() {
		return contract_endtime;
	}
	public String getContract_advancetime() {
		return contract_advancetime;
	}
	public String getContract_collectiontime() {
		return contract_collectiontime;
	}
	public BigDecimal getContract_money() {
		return contract_money;
	}
	public BigDecimal getContract_accumulatedreceipts() {
		return contract_accumulatedreceipts;
	}
	public BigDecimal getContract_accumulatedarrears() {
		return contract_accumulatedarrears;
	}
	public String getContract_file() {
		return contract_file;
	}
	public String getContract_filename() {
		return contract_filename;
	}
	public Integer getContract_state() {
		return contract_state;
	}
	public Integer getContract_status() {
		return contract_status;
	}
	public Integer getContract_modify() {
		return contract_modify;
	}
	public String getContract_modify_time() {
		return contract_modify_time;
	}
	public String getContract_remarks() {
		return contract_remarks;
	}
	public Integer getContract_user() {
		return contract_user;
	}
	public Integer getContract_trackstatus() {
		return contract_trackstatus;
	}
	public Integer getProgress() {
		return progress;
	}
	public User getUser() {
		return user;
	}
	public State getState() {
		return state;
	}
	public Modify getModify() {
		return modify;
	}
	public Status getStatus() {
		return status;
	}
	public List<Track> getTracks() {
		return tracks;
	}
	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}
	public void setContract_name(String contract_name) {
		this.contract_name = contract_name;
	}
	public void setContract_parta(String contract_parta) {
		this.contract_parta = contract_parta;
	}
	public void setContract_partb(String contract_partb) {
		this.contract_partb = contract_partb;
	}
	public void setContract_partbrepresentative(String contract_partbrepresentative) {
		this.contract_partbrepresentative = contract_partbrepresentative;
	}
	public void setContract_partbaddress(String contract_partbaddress) {
		this.contract_partbaddress = contract_partbaddress;
	}
	public void setContract_partbtelephone(String contract_partbtelephone) {
		this.contract_partbtelephone = contract_partbtelephone;
	}
	public void setContract_partbsigningtime(String contract_partbsigningtime) {
		this.contract_partbsigningtime = contract_partbsigningtime;
	}
	public void setContract_startime(String contract_startime) {
		this.contract_startime = contract_startime;
	}
	public void setContract_endtime(String contract_endtime) {
		this.contract_endtime = contract_endtime;
	}
	public void setContract_advancetime(String contract_advancetime) {
		this.contract_advancetime = contract_advancetime;
	}
	public void setContract_collectiontime(String contract_collectiontime) {
		this.contract_collectiontime = contract_collectiontime;
	}
	public void setContract_money(BigDecimal contract_money) {
		this.contract_money = contract_money;
	}
	public void setContract_accumulatedreceipts(BigDecimal contract_accumulatedreceipts) {
		this.contract_accumulatedreceipts = contract_accumulatedreceipts;
	}
	public void setContract_accumulatedarrears(BigDecimal contract_accumulatedarrears) {
		this.contract_accumulatedarrears = contract_accumulatedarrears;
	}
	public void setContract_file(String contract_file) {
		this.contract_file = contract_file;
	}
	public void setContract_filename(String contract_filename) {
		this.contract_filename = contract_filename;
	}
	public void setContract_state(Integer contract_state) {
		this.contract_state = contract_state;
	}
	public void setContract_status(Integer contract_status) {
		this.contract_status = contract_status;
	}
	public void setContract_modify(Integer contract_modify) {
		this.contract_modify = contract_modify;
	}
	public void setContract_modify_time(String contract_modify_time) {
		this.contract_modify_time = contract_modify_time;
	}
	public void setContract_remarks(String contract_remarks) {
		this.contract_remarks = contract_remarks;
	}
	public void setContract_user(Integer contract_user) {
		this.contract_user = contract_user;
	}
	public void setContract_trackstatus(Integer contract_trackstatus) {
		this.contract_trackstatus = contract_trackstatus;
	}
	public void setProgress(Integer progress) {
		this.progress = progress;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setState(State state) {
		this.state = state;
	}
	public void setModify(Modify modify) {
		this.modify = modify;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}
	@Override
	public String toString() {
		return "Contract [contract_id=" + contract_id + ", contract_name=" + contract_name + ", contract_parta="
				+ contract_parta + ", contract_partb=" + contract_partb + ", contract_partbrepresentative="
				+ contract_partbrepresentative + ", contract_partbaddress=" + contract_partbaddress
				+ ", contract_partbtelephone=" + contract_partbtelephone + ", contract_partbsigningtime="
				+ contract_partbsigningtime + ", contract_startime=" + contract_startime + ", contract_endtime="
				+ contract_endtime + ", contract_advancetime=" + contract_advancetime + ", contract_collectiontime="
				+ contract_collectiontime + ", contract_money=" + contract_money + ", contract_accumulatedreceipts="
				+ contract_accumulatedreceipts + ", contract_accumulatedarrears=" + contract_accumulatedarrears
				+ ", contract_file=" + contract_file + ", contract_filename=" + contract_filename + ", contract_state="
				+ contract_state + ", contract_status=" + contract_status + ", contract_modify=" + contract_modify
				+ ", contract_modify_time=" + contract_modify_time + ", contract_remarks=" + contract_remarks
				+ ", contract_user=" + contract_user + ", contract_trackstatus=" + contract_trackstatus + ", progress="
				+ progress + ", user=" + user + ", state=" + state + ", modify=" + modify + ", status=" + status
				+ ", tracks=" + tracks + "]";
	}
}
