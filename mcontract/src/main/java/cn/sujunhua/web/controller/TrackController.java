package cn.sujunhua.web.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sujunhua.pojo.Contract;
import cn.sujunhua.pojo.Track;
import cn.sujunhua.pojo.User;
import cn.sujunhua.service.ContractService;
import cn.sujunhua.service.TrackService;

@Controller
@Scope("request")
public class TrackController {
    @Autowired
    private TrackService trackService;
    @Autowired
    private ContractService contractService;
    
    @RequestMapping("/track/list.do")
    @ResponseBody
    public List<Track> list(String track_contractid) {
		return trackService.selectTrackByContractid(track_contractid);
	}
    
    @RequestMapping("/track/add.do")
    @ResponseBody
    public String add(@RequestParam("track_contractid")String track_contractid,
    		@RequestParam("track_collectiondate")String track_collectiondate,
    		@RequestParam("track_amountreceived")BigDecimal track_amountreceived,
    		@RequestParam("tract_remarks")String tract_remarks,
    		HttpServletRequest request) throws ParseException {
    	HttpSession session = request.getSession();
    	User user = (User) session.getAttribute("COOKIE_USER");
    	//创建收款跟踪对象
    	Track track = new Track();
    	track.setTrack_contractid(track_contractid);
    	track.setTrack_userid(user.getUser_id());
    	track.setTrack_collectiondate(track_collectiondate);
    	track.setTrack_amountreceived(track_amountreceived);
    	track.setTract_remarks(tract_remarks);
    	//执行添加
    	Integer row1 = trackService.addTract(track);
    	//根据合同id查出收款跟踪
    	//List<Track> tracks = trackService.selectTrackByContractid(track_contractid);
/*    	BigDecimal bigDecimal = new BigDecimal(0);
    	if (tracks.size()>0) {
			for (Track track2 : tracks) {
				bigDecimal=bigDecimal.add(track2.getTrack_amountreceived());
			}
		}
    	else {
    		bigDecimal=null;
    	}*/
    	//根据id查出合同
    	Contract contract2 = contractService.findConractByid(track_contractid);
    	BigDecimal contract_money = contract2.getContract_money();
    	BigDecimal contract_accumulatedreceipts = contract2.getContract_accumulatedreceipts();
    	//创建合同对象
    	Contract contract = new Contract();
    	contract.setContract_id(track_contractid);
/*     	if (contract_money!=(contract_accumulatedreceipts.add(track_amountreceived))) {
     		if (contract_accumulatedreceipts==null) {
         		contract.setContract_accumulatedreceipts(track_amountreceived);
            	contract.setContract_accumulatedarrears(contract_money.subtract(track_amountreceived));
    		}
         	if (contract_accumulatedreceipts!=null) {
         		contract.setContract_accumulatedreceipts(contract_accumulatedreceipts.add(track_amountreceived));
    			contract.setContract_accumulatedarrears(contract_money.subtract(contract_accumulatedreceipts.add(track_amountreceived)));
    		}
		}
     	else {
     		contract.setContract_accumulatedreceipts(contract_money);
     		contract.setContract_accumulatedarrears(new BigDecimal(0));
     	}*/
    	if (track_amountreceived.compareTo(contract_money)==0) {
    		contract.setContract_accumulatedreceipts(contract_money);
    		contract.setContract_accumulatedarrears(new BigDecimal(0));
		}
    	else {
    		if (contract_accumulatedreceipts!=null) {
        		if ((contract_accumulatedreceipts.add(track_amountreceived)).compareTo(contract_money)==0) {
    				contract.setContract_accumulatedreceipts(contract_money);
    				contract.setContract_accumulatedarrears(new BigDecimal(0));
    			}
        		else {
        			BigDecimal add = contract_accumulatedreceipts.add(track_amountreceived);
        			contract.setContract_accumulatedreceipts(add);
        			contract.setContract_accumulatedarrears(contract_money.subtract(add));
        		}
    		}
        	else {
        		contract.setContract_accumulatedreceipts(track_amountreceived);
        		contract.setContract_accumulatedarrears(contract_money.subtract(track_amountreceived));
        	}
    	}
    	//修改合同
        Integer row2 = contractService.updateContractByid(contract);
    	if (row1 > 0 && row2 > 0) {
			return "yes";
		}
    	else return "no";
    }
/*    
    @RequestMapping("/track/toadd.do")
    @ResponseBody
    public Contract toadd(String track_contractid) {
    	return contractService.findConractByid(track_contractid);
    }
    */
    @RequestMapping("/track/check.do")
    @ResponseBody
    public boolean checkmoney(String track_contractid,BigDecimal track_amountreceived) throws ParseException {
    	contractService.updatecontract_accumulatedarrears();
    	Contract contract = contractService.findConractByid(track_contractid);
    	BigDecimal contract_accumulatedarrears = contract.getContract_accumulatedarrears();
    	if (contract_accumulatedarrears.compareTo(track_amountreceived)==1 ||
    			contract_accumulatedarrears.compareTo(track_amountreceived)==0) {
			return true;
		}
    	else return false;
    }
}
