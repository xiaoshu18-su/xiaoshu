package cn.sujunhua.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import cn.sujunhua.common.utils.ContractEcharts;
import cn.sujunhua.common.utils.ContractMoneyEchart;
import cn.sujunhua.pojo.Contract;

public interface ContractService {
   public String addContract(String contract_name,String contract_parta,String contract_partb,
		   String contract_partbrepresentative,String contract_partbaddress,
		   String contract_partbtelephone,String contract_partbsigningtime,
		   String contract_startime,String contract_endtime,String contract_advancetime,
		   BigDecimal contract_money,String contract_remarks,Integer contract_trackstatus,MultipartFile contract_file,
		   HttpServletRequest request);
   
   public String updateContractByid(String contract_id,String contract_name,String contract_parta,
		   String contract_partb,
		   String contract_partbrepresentative,String contract_partbaddress,
		   String contract_partbtelephone,String contract_partbsigningtime,
		   String contract_startime,String contract_endtime,
		   String contract_advancetime,BigDecimal contract_money,String contract_remarks,
		   MultipartFile contract_file,HttpServletRequest request);
   
   public Integer updateContractByid(Contract contract);
   
   //修改合同的累计欠款
   public Integer updatecontract_accumulatedarrears();
   
   //查询合同
   public List<Contract> selectContract(String partbinformation,Integer contract_state,
			Integer contract_status,String startsigningtime,
			String endsigningtime)throws ParseException;
   
   //合同收款
   public String conractreceivables(String contract_id);
   
   //合同中止
   public String contractsuspension(String contract_id);
   
   //合同恢复
   public String contractrecovery(String contract_id);
   
   //完成合同
   public String contractcomplete(String contract_id);
   
   //合同归档
   public String contractplace(String contract_id);
   
   //根据id查询合同详细信息
   public Contract findConractByid(String contract_id) throws ParseException ;
   
   public String deleteContractByid(String contract_id,HttpServletRequest request);
   
   //折线图,查看7天的合同金额和名称
   public List<ContractMoneyEchart> echartsbyday();
   
   //查本年
   public List<ContractEcharts> echarthisyear();
   
   //查上一年或更上
   public List<ContractEcharts> echartlastyear();
   
   //查询全部按收款状态排序
   public List<ContractEcharts> echartallcontract();
   
   //poi导出所有合同信息
   public void poiContract(HttpServletResponse response);
}
