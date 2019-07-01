package cn.sujunhua.web.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.sujunhua.common.utils.ContractANDTrack;
import cn.sujunhua.common.utils.ContractEcharts;
import cn.sujunhua.common.utils.ContractMoneyEchart;
import cn.sujunhua.pojo.Contract;
import cn.sujunhua.pojo.State;
import cn.sujunhua.pojo.Status;
import cn.sujunhua.pojo.User;
import cn.sujunhua.service.ContractService;
import cn.sujunhua.service.StateService;
import cn.sujunhua.service.StatusService;
import cn.sujunhua.service.TrackService;

@Controller
@Scope("request")
public class ContractController {
	@Autowired
	private ContractService contractService;
	@Autowired
	private TrackService trackService;
	@Autowired
	private StateService stateService;
	@Autowired
	private StatusService statusService;
	
	//去添加合同界面
	@RequestMapping("/contract/toadd.do")
	public String toaddContractforPro(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("COOKIE_USER");
		if (user.getUser_type()==1) {
			return "noJurisdiction";
		}
		return "addcontract";
	}
	
	//判断用户跟踪权限
	@RequestMapping("/contract/tudgementauthority.do")
	@ResponseBody
	public String tudgementauthority(HttpServletRequest request,String contract_id) throws ParseException {
		Contract contract = contractService.findConractByid(contract_id);
		if (contract.getContract_trackstatus() == 1) {
			return "yes";
		}
		else {
			HttpSession session = request.getSession();
			User COOKIE_USER = (User) session.getAttribute("COOKIE_USER");
			if (COOKIE_USER.getUser_id() == contract.getContract_user()) {
				return "yes";
			}
			else return "no";
		}
	}
	
	@RequestMapping(value = "/contract/add.do", method = RequestMethod.POST)
	@ResponseBody
	public String addcontract(@RequestParam("contract_name") String contract_name,
			@RequestParam("contract_parta") String contract_parta,
			@RequestParam("contract_partb") String contract_partb,
			@RequestParam("contract_partbrepresentative") String contract_partbrepresentative,
			@RequestParam("contract_partbaddress") String contract_partbaddress,
			@RequestParam("contract_partbtelephone") String contract_partbtelephone,
			@RequestParam("contract_partbsigningtime") String contract_partbsigningtime,
			@RequestParam("contract_startime") String contract_startime,
			@RequestParam("contract_endtime") String contract_endtime,
			@RequestParam("contract_advancetime") String contract_advancetime,
			@RequestParam("contract_money") BigDecimal contract_money,
			@RequestParam("contract_remarks") String contract_remarks,
			@RequestParam("contract_trackstatus") String contract_trackstatus,
			@RequestParam("contract_file") MultipartFile contract_file, 
			HttpServletRequest request) throws IOException {
		User user = (User) request.getSession().getAttribute("COOKIE_USER");
		if (user.getUser_type()==1) {
			return "noJurisdiction";
		}
		try {
			return contractService.addContract(contract_name,contract_parta, 
					contract_partb,contract_partbrepresentative,contract_partbaddress,
					contract_partbtelephone,contract_partbsigningtime,
					contract_startime,contract_endtime,contract_advancetime,
					contract_money,contract_remarks,Integer.valueOf(contract_trackstatus),contract_file,request);
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}
	
	//跳转到合同编辑界面
	@RequestMapping("/contract/list.do")
	public String selectContract(HttpServletRequest request,
			@RequestParam(value="partbinformation",required=false)String partbinformation,
			@RequestParam(value="contract_state",required=false)Integer contract_state,
			@RequestParam(value="contract_status",required=false)Integer contract_status,
			@RequestParam(value="startsigningtime",required=false)String startsigningtime,
			@RequestParam(value="endsigningtime",required=false)String endsigningtime
			) throws ParseException {
		contractService.updatecontract_accumulatedarrears();
		
		User user = (User) request.getSession().getAttribute("COOKIE_USER");
		request.setAttribute("user_type", user.getUser_type());
		
		List<State> states = stateService.selectAllState();
		request.setAttribute("states", states);
		
		List<Status> statuses = statusService.selectAllStatus();
		request.setAttribute("statuses", statuses);
		
		if ((!"".equals(startsigningtime)&&startsigningtime != null) && 
				(!"".equals(endsigningtime)&&endsigningtime != null)) {
			if (startsigningtime.compareTo(endsigningtime)>0) {
				request.setAttribute("datesize", "error");
				List<Contract> contracts = contractService.selectContract(partbinformation,contract_state,
						contract_status,null,null);
				//数据回显
				request.setAttribute("partbinformation", partbinformation);
				request.setAttribute("contract_state", contract_state);
				request.setAttribute("contract_status", contract_status);
				request.setAttribute("contracts", contracts);
				return "contractlist";
			}
			else {
				List<Contract> contracts = contractService.selectContract(partbinformation,contract_state,
						contract_status,startsigningtime,endsigningtime);
				request.setAttribute("contracts", contracts);
				//数据回显
				request.setAttribute("partbinformation", partbinformation);
				request.setAttribute("contract_state", contract_state);
				request.setAttribute("contract_status", contract_status);
				request.setAttribute("startsigningtime", startsigningtime);
				request.setAttribute("endsigningtime", endsigningtime);
				return "contractlist";
			}
		}
		List<Contract> contracts = contractService.selectContract(partbinformation,contract_state,
				contract_status,startsigningtime,endsigningtime);
		request.setAttribute("contracts", contracts);
		//数据回显
		request.setAttribute("partbinformation", partbinformation);
		request.setAttribute("contract_state", contract_state);
		request.setAttribute("contract_status", contract_status);
		request.setAttribute("startsigningtime", startsigningtime);
		request.setAttribute("endsigningtime", endsigningtime);
		return "contractlist";
	}
	//查出合同信息
	@RequestMapping("/contract/selectlist.do")
	@ResponseBody
	public List<Contract> selectContractselect() throws ParseException {
		List<Contract> contracts = contractService.selectContract(null,null,null,null,null);
		return contracts;
	}
	
	@RequestMapping(value="/contract/update.do",method=RequestMethod.POST)
	@ResponseBody
	public String updateContract(@RequestParam("contract_id") String contract_id,
			@RequestParam("contract_name") String contract_name,
			@RequestParam("contract_parta") String contract_parta,
			@RequestParam("contract_partb") String contract_partb,
			@RequestParam("contract_partbrepresentative") String contract_partbrepresentative,
			@RequestParam("contract_partbaddress") String contract_partbaddress,
			@RequestParam("contract_partbtelephone") String contract_partbtelephone,
			@RequestParam("contract_partbsigningtime") String contract_partbsigningtime,
			@RequestParam("contract_startime") String contract_startime,
			@RequestParam("contract_endtime") String contract_endtime,
			@RequestParam("contract_advancetime") String contract_advancetime,
			@RequestParam("contract_money") BigDecimal contract_money,
			@RequestParam("contract_remarks") String contract_remarks,
			@RequestParam("contract_file") MultipartFile contract_file, 
			HttpServletRequest request) throws IOException {
		User user = (User) request.getSession().getAttribute("COOKIE_USER");
		if (user.getUser_type()==1) {
			return "noJurisdiction";
		}
		try {
			return contractService.updateContractByid(contract_id, contract_name,
					contract_parta, contract_partb,contract_partbrepresentative,contract_partbaddress,
					contract_partbtelephone,contract_partbsigningtime, contract_startime, 
					contract_endtime, contract_advancetime, contract_money,
					contract_remarks, contract_file, request);
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}
	@RequestMapping("/contract/delete.do")
	@ResponseBody
	public String deleteContractByid(HttpServletRequest request,String contract_id) {
		User user = (User) request.getSession().getAttribute("COOKIE_USER");
		if (user.getUser_type()==1) {
			return "noJurisdiction";
		}
		try {
			return contractService.deleteContractByid(contract_id,request);
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}

	@RequestMapping("/contract/poiContract.do")
	public void poiContract(HttpServletRequest request,HttpServletResponse response) {
		User COOKIE_USER = (User) request.getSession().getAttribute("COOKIE_USER");
		if (COOKIE_USER.getUser_type()<=1) {
			return ;
		}
		try {
			contractService.poiContract(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/contract/detail.do")
	public String findConractByid(HttpServletRequest request,String contract_id) throws ParseException {
		contractService.updatecontract_accumulatedarrears();
		Contract contract = contractService.findConractByid(contract_id);
		request.setAttribute("c", contract);
		return "contract_detail";
	}
	
	@RequestMapping("/contract/track.do")
	@ResponseBody
	public ContractANDTrack contractTrack(String contract_id) throws ParseException {
		contractService.updatecontract_accumulatedarrears();
		ContractANDTrack contractANDTrack = new ContractANDTrack();
		contractANDTrack.setContract(contractService.findConractByid(contract_id));
		contractANDTrack.setTracks(trackService.selectTrackByContractid(contract_id));
		return contractANDTrack;
	}
    
	@RequestMapping("/contract/toupdate.do")
	public String toupdateContract(HttpServletRequest request,String contract_id) throws ParseException {
		User user = (User) request.getSession().getAttribute("COOKIE_USER");
		if (user.getUser_type()==1) {
			return "noJurisdiction";
		}
		Contract contract = contractService.findConractByid(contract_id);
		request.setAttribute("c", contract);
		return "contractupdate";
	}
	
	@RequestMapping("/contract/findone.do")
	@ResponseBody
	public Contract findone(String contract_id) throws ParseException {
		return contractService.findConractByid(contract_id);
	}
	
	//收款
	@RequestMapping("/contract/receivables.do")
	@ResponseBody
	public String receivables(HttpServletRequest request,String contract_id) {
		try {
			User user = (User) request.getSession().getAttribute("COOKIE_USER");
			if (user.getUser_type()==1) {
				return "noJurisdiction";
			}
			return contractService.conractreceivables(contract_id);
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}
	
	//合同中止
	@RequestMapping("/contract/suspension.do")
	@ResponseBody
	public String contractsuspension(HttpServletRequest request,String contract_id) {
		try {
			User user = (User) request.getSession().getAttribute("COOKIE_USER");
			if (user.getUser_type()==1) {
				return "noJurisdiction";
			}
			return contractService.contractsuspension(contract_id);
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}
	//合同恢复
	@RequestMapping("/contract/recovery.do")
	@ResponseBody
	public String contractrecovery(HttpServletRequest request,String contract_id) {
		try {
			User user = (User) request.getSession().getAttribute("COOKIE_USER");
			if (user.getUser_type()==1) {
				return "noJurisdiction";
			}
			return contractService.contractrecovery(contract_id);
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}
	//合同完成
	@RequestMapping("/contract/complete.do")
	@ResponseBody
	public String contractcomplete(HttpServletRequest request,String contract_id) {
		try {
			User user = (User) request.getSession().getAttribute("COOKIE_USER");
			if (user.getUser_type()==1) {
				return "noJurisdiction";
			}
			return contractService.contractcomplete(contract_id);
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}
	//合同归档
	@RequestMapping("/contract/place.do")
	@ResponseBody
	public String contractplace(HttpServletRequest request,String contract_id) {
		try {
			User user = (User) request.getSession().getAttribute("COOKIE_USER");
			if (user.getUser_type()==1) {
				return "noJurisdiction";
			}
			return contractService.contractplace(contract_id);
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}
	//查出合同收款状态
	@RequestMapping("/contract/echartallcontract.do")
	@ResponseBody
	public List<ContractEcharts> echartallcontract(){
		return contractService.echartallcontract();
	}
	
	//柱状图
	@RequestMapping("/contract/echartsbyday.do")
	@ResponseBody
	public List<ContractMoneyEchart> echartsbyday(){
		return contractService.echartsbyday();
	}
	
	//本年柱状图
	@RequestMapping("/contract/echarthisyear.do")
	@ResponseBody
	public List<ContractEcharts> echarthisyear(){
		return contractService.echarthisyear();
	}
	
	//上一年柱状图
	@RequestMapping("/contract/echartlastyear.do")
	@ResponseBody
	public List<ContractEcharts> echartlastyear(){
		return contractService.echartlastyear();
	}
}
