package cn.sujunhua.common.utils;

import java.util.List;

import cn.sujunhua.pojo.Contract;
import cn.sujunhua.pojo.Track;

public class ContractANDTrack {
	private Contract contract;
	private List<Track> tracks;

	public Contract getContract() {
		return contract;
	}

	public List<Track> getTracks() {
		return tracks;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}

}
