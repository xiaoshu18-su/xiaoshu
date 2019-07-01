package cn.sujunhua.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.sujunhua.pojo.Contract;

public interface ContractDao {
   
   public List<Contract> selectContractByUser(Contract contract);
   
   public Integer addContract(Contract contract);
   
   public List<Contract> selectContract(@Param("partbinformation")String partbinformation,
		   @Param("contract_state")Integer contract_state,
		   @Param("contract_status")Integer contract_status,
		   @Param("startsigningtime")String startsigningtime,
		   @Param("endsigningtime")String endsigningtime);
   
   public List<Contract> selectContractByStatus(@Param("contract_status")List<Integer> contract_status,
		   @Param("contract_state")Integer contract_state);
   
   public Integer deleteContractByid(String contract_id);
   
   //修改合同的累计欠款
   public Integer updatecontract_accumulatedarrears();
   
   //修改合同方法
   public Integer updateContractByid(Contract contract);
   
   //根据id查出合同
   public Contract findConractByid(String contract_id);
   
   //根据id查出合同的文件在磁盘存下的名称
   public Contract findContractFileByid(String contract_id);
   
   //查最近添加的15份合同
   public List<Contract> selectContractlimit();
   
   //test method2  查看近7天和近30天的方法
   public List<Contract> selectchartbyday(@Param("day")Integer day);
   
   //test method3    查看本月或者是上一个月的方法
   public List<Contract> selectmonth(@Param("count")Integer count);
   
   //查本年的合同
   public List<Contract> selecthisyear();
   
   //查上一年或更前的
   public List<Contract> selectlastyear(@Param("count")Integer count);
   
   //查询所有合同，根据收款状态排序
   public List<Contract> selectContractorderbystatus();
   
   public List<Contract> selectContractInID(@Param("contractids")List<String> contractids);
   
   public Integer updateContractsInID(@Param("contractids")List<String> contractids,
		   @Param("contract_status")Integer contract_status);
}
