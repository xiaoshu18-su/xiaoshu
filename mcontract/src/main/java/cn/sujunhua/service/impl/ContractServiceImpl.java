package cn.sujunhua.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.sujunhua.common.utils.ContractEcharts;
import cn.sujunhua.common.utils.ContractMoneyEchart;
import cn.sujunhua.dao.ContractDao;
import cn.sujunhua.dao.ModifyDao;
import cn.sujunhua.dao.StateDao;
import cn.sujunhua.dao.StatusDao;
import cn.sujunhua.pojo.Contract;
import cn.sujunhua.pojo.Modify;
import cn.sujunhua.pojo.State;
import cn.sujunhua.pojo.Status;
import cn.sujunhua.pojo.User;
import cn.sujunhua.service.ContractService;

@Service
@Scope("prototype")
public class ContractServiceImpl implements ContractService {
	// tomcat管理的文件夹    路径读取设为/images
	private final String TOMCATFILE = "/images/";
	//修改状态
	//private final Integer UPDATE_STATE=0;
	@Autowired
	private ContractDao contractDao;

	@Autowired
	private StateDao stateDao;
	
	@Autowired
	private ModifyDao modifyDao;
	
	@Autowired
	private StatusDao statusDao;
	
     /**
      * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    public int differentDaysByMillisecond(Date date1,Date date2)
    {
        return (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
    }
    
    /**
       * 去编辑合同界面前需要执行的方法
     * 该方法把合同表的数据进行更新，
     * 如果是已经收过款的就直接改成收款跟踪中
     * 按系统当前日期进行更新
     * 如果系统当前时间已经超过了合同的预收款时间，则需要把
     * 该合同的收款状态更改为待收款状态。
     * 如果系统当前时间距离预收款时间只有3天了
     * 则需要把该合同的收款状态更改为待收款状态
     * @return
     * @throws ParseException 
     */
    public void updateContractByDay() throws ParseException {
    	//创建保存需要修改的合同的id
    	//contractids1表示要修改为待收款的ids
    	//contractids2表示要修改为 的ids
    	//contractids2表示要修改为收款跟踪中的ids
    	List<String> contractids1 = new ArrayList<String>();
    	List<String> contractids2 = new ArrayList<String>();
    	List<String> contractids3 = new ArrayList<String>();
    	List<String> contractids4 = new ArrayList<String>();
    	List<Integer> statusids=new ArrayList<Integer>();
    	Status status4 = statusDao.selectStatusByName("已完全收款");
    	Status status3 = statusDao.selectStatusByName("待收款");
    	Status status2 = statusDao.selectStatusByName("未开始收款");
    	Status status1 = statusDao.selectStatusByName("收款跟踪中");
    	statusids.add(status1.getStatus_id());
    	statusids.add(status2.getStatus_id());
    	statusids.add(status3.getStatus_id());
    	statusids.add(status4.getStatus_id());
    	State state = stateDao.selectStateByName("执行中");
    	List<Contract> contracts = contractDao.selectContractByStatus(statusids,state.getState_id());
    	//创建格式化日期对象
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	//创建系统当前时间日期对象
    	Date date = new Date();
    	String dateStr = format.format(date);
    	Date today = format.parse(dateStr);
    	//创建标志
    	int temp;
    	//记录合同的预收款时间
    	String advancetime = "";
    	if (contracts.size()>0) {
    		for (Contract contract : contracts) {
    				if (contract.getContract_accumulatedreceipts() != null) {
    					if (contract.getContract_money().compareTo(contract.getContract_accumulatedreceipts())==0) {
    						contractids4.add(contract.getContract_id());
    					}
    					else {
        				contractids3.add(contract.getContract_id());
    					}
    				}
        			else {
        				advancetime = contract.getContract_advancetime();
            			//调用计算两个日期相差的天数的方法
            			temp = differentDaysByMillisecond(today, format.parse(advancetime));
            			if (temp <= 3) {
            				contractids1.add(contract.getContract_id());
            			}
            			if (temp > 3) {
            				contractids2.add(contract.getContract_id());
            			}
        			}
    			}
		}
    	if (contractids1.size()>0) {
    		Status status = statusDao.selectStatusByName("待收款");
    		contractDao.updateContractsInID(contractids1, status.getStatus_id());
		}
    	if (contractids2.size()>0) {
			Status status = statusDao.selectStatusByName("未开始收款");
			contractDao.updateContractsInID(contractids2, status.getStatus_id());
		}
    	if (contractids3.size()>0) {
    		Status status = statusDao.selectStatusByName("收款跟踪中");
    		contractDao.updateContractsInID(contractids3, status.getStatus_id());
		}
    	if (contractids4.size()>0) {
    		Status status = statusDao.selectStatusByName("已完全收款");
    		contractDao.updateContractsInID(contractids4, status.getStatus_id());
		}
    }

	@Override
	public List<Contract> selectContract(String partbinformation,Integer contract_state,
			Integer contract_status,String startsigningtime,
			String endsigningtime) throws ParseException {
		//调用方法
		updateContractByDay();
		List<Contract> contracts = contractDao.selectContract(partbinformation,contract_state,
				contract_status,startsigningtime,endsigningtime);
		if (contracts.size()>0) {
		    for (Contract contract : contracts) {
				if (contract.getContract_accumulatedreceipts()==null) {
					contract.setContract_accumulatedreceipts(new BigDecimal(0));
				}
				//合同金额
				BigDecimal contract_money = contract.getContract_money();
				//合同累计收款
				BigDecimal contract_accumulatedreceipts = contract.getContract_accumulatedreceipts();
				BigDecimal divide = contract_accumulatedreceipts.divide(contract_money,2,BigDecimal.ROUND_HALF_UP);
				BigDecimal multiply = divide.multiply(new BigDecimal(100));
				contract.setProgress(multiply.intValue());
			}
		}
		return contracts;
	}

	@Override
	public String deleteContractByid(String contract_id,HttpServletRequest request) {
		Contract contract = contractDao.findConractByid(contract_id);
		if (!"已终止".equals(contract.getState().getState_name())) {
			return "noJurisdiction";
		}
		String filename=contract.getContract_file();
			if (filename != null && !"".equals(filename)) {
				File file = new File(request.getServletContext().getRealPath(TOMCATFILE), filename);
				file.delete();
			}
		Integer row = contractDao.deleteContractByid(contract_id);
		if (row>0) {
			return "yes";
		}
		else return "no";
	}

	//添加合同
	@Override
	public String addContract(String contract_name,String contract_parta,String contract_partb,
			   String contract_partbrepresentative,String contract_partbaddress,
			   String contract_partbtelephone,String contract_partbsigningtime,
			   String contract_startime,String contract_endtime,String contract_advancetime,
			   BigDecimal contract_money,String contract_remarks,Integer contract_trackstatus,
			   MultipartFile contract_file,HttpServletRequest request) {
		// 文件的原始名称
		String originalFilename = "";
		// 新的文件名
		String newfileName = "";
		// 判断所上传文件是否存在
		if (!contract_file.isEmpty() && contract_file.getSize() > 0) {
			// 获取上传文件的原始名称
			originalFilename = contract_file.getOriginalFilename();
			// 获取后缀名
			String extension = FilenameUtils.getExtension(originalFilename);
			// UUID创建随机String 用于作为新的文件名
			String string = UUID.randomUUID().toString();

			// 新的文件名
			newfileName = string.replaceAll("-", "") + "." + extension;
			// 设置上传文件的保存地址目录
			String dirPath = request.getServletContext().getRealPath(TOMCATFILE);
			File filePath = new File(dirPath);
			// 如果目录不存在就创建目录
			if (!filePath.exists())
				filePath.mkdirs();
			try {
				contract_file.transferTo(new File(dirPath + newfileName));
			} catch (Exception e) {
				e.printStackTrace();
				return "no";
			}
		}
		//设置执行状态，添加时设置为执行中
		State state = stateDao.selectStateByName("执行中");
		//设置收款状态，根据预收款时间或者结束时间来决定
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Status status = null;
		Date advancetime = null;
		//转换成date
		try {
			if (contract_advancetime != null && !"".equals(contract_advancetime)) {
				advancetime = format.parse(contract_advancetime);
			}
			else {
				advancetime = format.parse(contract_endtime);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
		//计算相差天数，第一个传入当前时间，第二个传入预收款时间或者结束时间
		int missday = differentDaysByMillisecond(new Date(), advancetime);
		if (missday < 3) {
			//离收款日期只有3天之距，则设置为待收款状态
			//或者已经超过预收款日期
		    status = statusDao.selectStatusByName("待收款");
		}
		else {
			//设置为空字符串
			status = statusDao.selectStatusByName("未开始收款");
		}
		//添加合同时设置为未修改状态
		Modify modify = modifyDao.selectModifyByName("未修改");
		//session中获得当前用户
		User user = (User) request.getSession().getAttribute("COOKIE_USER");
		//设置编号
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String contract_id = format1.format(new Date());
		Contract contract = new Contract();
		contract.setContract_id(contract_id);
		contract.setContract_name(contract_name);
		contract.setContract_parta(contract_parta);
		contract.setContract_partb(contract_partb);
		contract.setContract_partbrepresentative(contract_partbrepresentative);
		contract.setContract_partbaddress(contract_partbaddress);
		contract.setContract_partbtelephone(contract_partbtelephone);
		contract.setContract_partbsigningtime(contract_partbsigningtime);
		contract.setContract_startime(contract_startime);
		contract.setContract_endtime(contract_endtime);
		contract.setContract_advancetime(format.format(advancetime));
		contract.setContract_money(contract_money);
		contract.setContract_accumulatedarrears(contract_money);
		contract.setContract_file(TOMCATFILE+newfileName);
		contract.setContract_filename(originalFilename);
		contract.setContract_state(state.getState_id());
		contract.setContract_status(status.getStatus_id());
		contract.setContract_modify(modify.getModify_id());
		contract.setContract_remarks(contract_remarks);
		contract.setContract_trackstatus(contract_trackstatus);
		contract.setContract_user(user.getUser_id());
		Integer row = contractDao.addContract(contract);
		if (row > 0) {
			return "yes";
		} else
			return "no";
		
	}
	//修改合同
	@Override
	public String updateContractByid(String contract_id, String contract_name, String contract_parta,
			String contract_partb, 
			String contract_partbrepresentative,String contract_partbaddress,
			   String contract_partbtelephone,String contract_partbsigningtime,
			String contract_startime, String contract_endtime, String contract_advancetime,
			BigDecimal contract_money, String contract_remarks, MultipartFile contract_file,
			HttpServletRequest request) {
		// 文件的原始名称
		String originalFilename = null;
		// 新的文件名
		String newfileName = null;
		Contract contract2 = contractDao.findConractByid(contract_id);
		// 判断所上传文件是否存在
		if (!contract_file.isEmpty() && contract_file.getSize() > 0) {
			// 获取上传文件的原始名称
			originalFilename = contract_file.getOriginalFilename();
			// 获取后缀名
			String extension = FilenameUtils.getExtension(originalFilename);
			// UUID创建随机String 用于作为新的文件名
			String string = UUID.randomUUID().toString();

			// 新的文件名
			newfileName = string.replaceAll("-", "") + "." + extension;
			// 设置上传文件的保存地址目录
			String dirPath = request.getServletContext().getRealPath(TOMCATFILE);
			File filePath = new File(dirPath);
			
			// 如果目录不存在就创建目录
			if (!filePath.exists())
				filePath.mkdirs();
			try {
				contract_file.transferTo(new File(dirPath + newfileName));
				//如果上传了新的合同文件，则需要删除原来的文件
				File file = new File(dirPath + contract2.getContract_file());
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
				return "no";
			}
		}
		//设置为已修改状态
		Modify modify = modifyDao.selectModifyByName("已修改");
		//设置修改的时间
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date datetime = new Date();
		String contract_modify_time = format.format(datetime);
		Contract contract = new Contract();
		contract.setContract_id(contract_id);
		contract.setContract_name(contract_name);
		contract.setContract_parta(contract_parta);
		contract.setContract_partb(contract_partb);
		contract.setContract_partbrepresentative(contract_partbrepresentative);
		contract.setContract_partbaddress(contract_partbaddress);
		contract.setContract_partbtelephone(contract_partbtelephone);
		contract.setContract_partbsigningtime(contract_partbsigningtime);
		contract.setContract_startime(contract_startime);
		contract.setContract_endtime(contract_endtime);
		contract.setContract_advancetime(contract_advancetime);
		contract.setContract_money(contract_money);
		if (contract2.getContract_accumulatedreceipts()!=null) {
			contract.setContract_accumulatedarrears(contract_money.subtract(contract2.getContract_accumulatedreceipts()));
		}
		else {
			contract.setContract_accumulatedarrears(contract_money);
		}
		contract.setContract_file(TOMCATFILE+newfileName);
		contract.setContract_filename(originalFilename);
		contract.setContract_modify(modify.getModify_id());
		contract.setContract_modify_time(contract_modify_time);
		contract.setContract_remarks(contract_remarks);
		Integer row = contractDao.updateContractByid(contract);
		if (row > 0) {
			return "yes";
		} else
			return "no";
	}

	@SuppressWarnings("resource")
	@Override
	public void poiContract(HttpServletResponse response) {
		HSSFWorkbook book = new HSSFWorkbook();
		HSSFSheet sheet = book.createSheet();
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("合同编号");   //设置表头
		sheet.setColumnWidth(0, "xxxx".getBytes().length*256*3*2);   //设置列宽
		row.createCell(1).setCellValue("合同名称");
		sheet.setColumnWidth(1, "xxxx".getBytes().length*256*4*2);
		row.createCell(2).setCellValue("甲方");
		sheet.setColumnWidth(2, "xxxx".getBytes().length*256*2*2);
		row.createCell(3).setCellValue("乙方");
		sheet.setColumnWidth(3, "xxxx".getBytes().length*256*2*2);
		row.createCell(4).setCellValue("乙方代表人");
		sheet.setColumnWidth(4, "xxxx".getBytes().length*256*2*2);
		row.createCell(5).setCellValue("乙方地址");
		sheet.setColumnWidth(5, "xxxx".getBytes().length*256*2*2);
		row.createCell(6).setCellValue("乙方电话");
		sheet.setColumnWidth(6, "xxxx".getBytes().length*256*2*2);
		row.createCell(7).setCellValue("乙方签订时间");
		sheet.setColumnWidth(7, "xxxx".getBytes().length*256*2*2);
		row.createCell(8).setCellValue("开始时间");
		sheet.setColumnWidth(8, "xxxx".getBytes().length*256*2*2);
		row.createCell(9).setCellValue("结束时间");
		sheet.setColumnWidth(9, "xxxx".getBytes().length*256*2*2);
		row.createCell(10).setCellValue("预收款时间");
		sheet.setColumnWidth(10, "xxxx".getBytes().length*256*2*2);
		row.createCell(11).setCellValue("金额");
		sheet.setColumnWidth(11, "xxxx".getBytes().length*256*3*2);
		row.createCell(12).setCellValue("累计收款");
		sheet.setColumnWidth(12, "xxxx".getBytes().length*256*3*2);
		row.createCell(13).setCellValue("欠款");
		sheet.setColumnWidth(13, "xxxx".getBytes().length*256*3*2);
		row.createCell(14).setCellValue("合同文件名称");
		sheet.setColumnWidth(14, "xxxx".getBytes().length*256*4*2);
		row.createCell(15).setCellValue("合同状态");
		sheet.setColumnWidth(15, "xxxx".getBytes().length*256*2);
		row.createCell(16).setCellValue("合同收款状态");
		sheet.setColumnWidth(16, "xxxx".getBytes().length*256*2);
		row.createCell(17).setCellValue("合同修改状态");
		sheet.setColumnWidth(17, "xxxx".getBytes().length*256*2);
		row.createCell(18).setCellValue("最新修改时间");
		sheet.setColumnWidth(18, "xxxx".getBytes().length*256*4*2);
		row.createCell(19).setCellValue("备注");
		sheet.setColumnWidth(19, "xxxx".getBytes().length*256*4*2);
		row.createCell(20).setCellValue("合同上传人");
		sheet.setColumnWidth(20, "xxxx".getBytes().length*256*2);
		row.createCell(21).setCellValue("合同跟踪权限");
		sheet.setColumnWidth(21, "xxxx".getBytes().length*256*4);
		int i=1;
		List<Contract> contracts = contractDao.selectContract(null,null,null,null,null);
		for (Contract contract : contracts) {
			HSSFRow rowi = sheet.createRow(i);
			rowi.createCell(0).setCellValue(contract.getContract_id());
			rowi.createCell(1).setCellValue(contract.getContract_name());
			rowi.createCell(2).setCellValue(contract.getContract_parta());
			rowi.createCell(3).setCellValue(contract.getContract_partb());
			rowi.createCell(4).setCellValue(contract.getContract_partbrepresentative());
			rowi.createCell(5).setCellValue(contract.getContract_partbaddress());
			rowi.createCell(6).setCellValue(contract.getContract_partbtelephone());
			rowi.createCell(7).setCellValue(contract.getContract_partbsigningtime());
			rowi.createCell(8).setCellValue(contract.getContract_startime());
			rowi.createCell(9).setCellValue(contract.getContract_endtime());
			rowi.createCell(10).setCellValue(contract.getContract_advancetime());
			rowi.createCell(11).setCellValue(String.valueOf(contract.getContract_money()));
			rowi.createCell(12).setCellValue(String.valueOf(contract.getContract_accumulatedreceipts()));
			rowi.createCell(13).setCellValue(String.valueOf(contract.getContract_accumulatedarrears()));
			rowi.createCell(14).setCellValue(contract.getContract_filename());
			rowi.createCell(15).setCellValue(contract.getState().getState_name());
			rowi.createCell(16).setCellValue(contract.getStatus().getStatus_name());
			rowi.createCell(17).setCellValue(contract.getModify().getModify_name());
			rowi.createCell(18).setCellValue(contract.getContract_modify_time());
			rowi.createCell(19).setCellValue(contract.getContract_remarks());
			rowi.createCell(20).setCellValue(contract.getUser().getUser_name());
			if (contract.getContract_trackstatus()==1) {
				rowi.createCell(21).setCellValue("所有人");
			}else {
				rowi.createCell(21).setCellValue(contract.getUser().getUser_name());
			}
			i++;
		}
		//设置响应头,响应的内容是为附件形式
		try {
			response.addHeader("Content-Disposition",
					"attachment;filename=" + new String("合同信息.xlsx".getBytes(), "ISO-8859-1"));
			book.write(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Contract findConractByid(String contract_id) throws ParseException {
		//调用方法
		updateContractByDay();
		Contract contract = contractDao.findConractByid(contract_id);
		if (contract.getContract_accumulatedreceipts()==null) {
			contract.setContract_accumulatedreceipts(new BigDecimal(0));
		}
		//合同金额
		BigDecimal contract_money = contract.getContract_money();
		//合同累计收款
		BigDecimal contract_accumulatedreceipts = contract.getContract_accumulatedreceipts();
		BigDecimal divide = contract_accumulatedreceipts.divide(contract_money,2,BigDecimal.ROUND_HALF_UP);
		BigDecimal multiply = divide.multiply(new BigDecimal(100));
		contract.setProgress(multiply.intValue());
		return contract;
	}
    
	//合同收款,收款时确定收款时间,当前系统时间作为收款时间
	@Override
	public String conractreceivables(String contract_id) {
		Status status = statusDao.selectStatusByName("已收款");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Contract contract = new Contract();
		contract.setContract_id(contract_id);
		contract.setContract_status(status.getStatus_id());
		contract.setContract_collectiontime(format.format(new Date()));
		Integer row = contractDao.updateContractByid(contract);
		if (row>0) {
			return "yes";
		}
		else return "no";
	}
    //合同中止
	@Override
	public String contractsuspension(String contract_id) {
		State state = stateDao.selectStateByName("已终止");
		Contract contract = new Contract();
		contract.setContract_id(contract_id);
		contract.setContract_state(state.getState_id());
		Integer row = contractDao.updateContractByid(contract);
		if (row>0) {
			return "yes";
		}
		else return "no";
	}

	@Override
	public String contractrecovery(String contract_id) {
		State state = stateDao.selectStateByName("执行中");
		Contract contract = new Contract();
		contract.setContract_id(contract_id);
		contract.setContract_state(state.getState_id());
		Integer row = contractDao.updateContractByid(contract);
		if (row>0) {
			return "yes";
		}
		else return "no";
	}

	@Override
	public String contractcomplete(String contract_id) {
		State state = stateDao.selectStateByName("已完成");
		Contract contract = new Contract();
		contract.setContract_id(contract_id);
		contract.setContract_state(state.getState_id());
		Integer row = contractDao.updateContractByid(contract);
		if (row>0) {
			return "yes";
		}
		else return "no";
	}

	@Override
	public String contractplace(String contract_id) {
		State state = stateDao.selectStateByName("已归档");
		Contract contract = new Contract();
		contract.setContract_id(contract_id);
		contract.setContract_state(state.getState_id());
		Integer row = contractDao.updateContractByid(contract);
		if (row>0) {
			return "yes";
		}
		else return "no";
	}

	@Override
	public List<ContractEcharts> echarthisyear() {
		List<Contract> contracts = contractDao.selecthisyear();
		List<ContractEcharts> echarts = new ArrayList<ContractEcharts>();
		ContractEcharts contractEcharts = null;
		String columnarmonth="";
		if (contracts.size()>0) {
			for (Contract contract : contracts) {
				if (!columnarmonth.equals(contract.getContract_partbsigningtime().substring(0, 7))) {
					contractEcharts = new ContractEcharts();
					contractEcharts.setColumnarmonth(contract.getContract_partbsigningtime().substring(0, 7));
					contractEcharts.setColumnarcount(1);
					echarts.add(contractEcharts);
				}
				else {
					contractEcharts.setColumnarcount(contractEcharts.getColumnarcount()+1);
				}
				columnarmonth = contract.getContract_partbsigningtime().substring(0, 7);
			}
		}
		return echarts;
	}

	@Override
	public List<ContractEcharts> echartlastyear() {
		List<Contract> contracts = contractDao.selectlastyear(1);
		List<ContractEcharts> echarts = new ArrayList<ContractEcharts>();
		ContractEcharts contractEcharts = null;
		String columnarmonth="";
		if (contracts.size()>0) {
			for (Contract contract : contracts) {
				if (!columnarmonth.equals(contract.getContract_partbsigningtime().substring(0, 7))) {
					contractEcharts = new ContractEcharts();
					contractEcharts.setColumnarmonth(contract.getContract_partbsigningtime().substring(0, 7));
					contractEcharts.setColumnarcount(1);
					echarts.add(contractEcharts);
				}
				else {
					contractEcharts.setColumnarcount(contractEcharts.getColumnarcount()+1);
				}
				columnarmonth = contract.getContract_partbsigningtime().substring(0, 7);
			}
		}
		return echarts;
	}

	@Override
	public List<ContractEcharts> echartallcontract() {
		List<Contract> contracts = contractDao.selectContractorderbystatus();
		List<ContractEcharts> echarts = new ArrayList<ContractEcharts>();
		Integer statusid = 0;
		ContractEcharts contractEcharts = null;
		if (contracts.size()>0) {
			for (Contract contract : contracts) {
				if (contract.getContract_status() != statusid) {
					Status status = statusDao.selectStatusByID(contract.getContract_status());
					contractEcharts = new ContractEcharts();
					if ("未开始收款".equals(status.getStatus_name())) {
						contractEcharts.setCakelikename("未到收款时间");
					}
					else {
						contractEcharts.setCakelikename(status.getStatus_name());
					}
					contractEcharts.setCakelikecount(1);
					echarts.add(contractEcharts);
				}
				else {
					contractEcharts.setCakelikecount(contractEcharts.getCakelikecount()+1);
				}
				statusid = contract.getContract_status();
			}
		}
		return echarts;
	}

	@Override
	public List<ContractMoneyEchart> echartsbyday() {
		List<Contract> contracts = contractDao.selectContractlimit();
		List<ContractMoneyEchart> contractMoneyEcharts = new ArrayList<ContractMoneyEchart>();
		if (contracts.size()>0) {
			for(int i=contracts.size()-1;i>=0;i--) {
				ContractMoneyEchart contractMoneyEchart = new ContractMoneyEchart();
				contractMoneyEchart.setContractname(contracts.get(i).getContract_name());
				contractMoneyEchart.setMoneycount(String.valueOf(contracts.get(i).getContract_money()));
				contractMoneyEchart.setReceiptscount(String.valueOf(contracts.get(i).getContract_accumulatedreceipts()));
				contractMoneyEchart.setArrearscount(String.valueOf(contracts.get(i).getContract_accumulatedarrears()));
				contractMoneyEcharts.add(contractMoneyEchart);
			}
		}
		return contractMoneyEcharts;
	}

	@Override
	public Integer updateContractByid(Contract contract) {
		//设置为已修改状态
		Modify modify = modifyDao.selectModifyByName("已修改");
		//设置修改的时间
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date datetime = new Date();
		String contract_modify_time = format.format(datetime);
		contract.setContract_modify(modify.getModify_id());
		contract.setContract_modify_time(contract_modify_time);
		return contractDao.updateContractByid(contract);
	}

	@Override
	public Integer updatecontract_accumulatedarrears() {
		return contractDao.updatecontract_accumulatedarrears();
	}

}
