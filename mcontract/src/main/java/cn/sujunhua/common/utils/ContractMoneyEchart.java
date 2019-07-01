package cn.sujunhua.common.utils;

public class ContractMoneyEchart {
	private String contractname;
	private String moneycount;
	private String receiptscount;
	private String arrearscount;
	public String getContractname() {
		return contractname;
	}
	public String getMoneycount() {
		return moneycount;
	}
	public String getReceiptscount() {
		return receiptscount;
	}
	public String getArrearscount() {
		return arrearscount;
	}
	public void setContractname(String contractname) {
		this.contractname = contractname;
	}
	public void setMoneycount(String moneycount) {
		this.moneycount = moneycount;
	}
	public void setReceiptscount(String receiptscount) {
		this.receiptscount = receiptscount;
	}
	public void setArrearscount(String arrearscount) {
		this.arrearscount = arrearscount;
	}
	@Override
	public String toString() {
		return "ContractMoneyEchart [contractname=" + contractname + ", moneycount=" + moneycount + ", receiptscount="
				+ receiptscount + ", arrearscount=" + arrearscount + "]";
	}
}
