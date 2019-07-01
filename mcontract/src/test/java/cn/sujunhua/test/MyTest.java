package cn.sujunhua.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.sujunhua.common.utils.ContractEcharts;
import cn.sujunhua.common.utils.ContractMoneyEchart;
import cn.sujunhua.common.utils.CountNumer;
import cn.sujunhua.dao.ContractDao;
import cn.sujunhua.dao.CountDao;
import cn.sujunhua.dao.ModifyDao;
import cn.sujunhua.dao.SecretDao;
import cn.sujunhua.dao.StateDao;
import cn.sujunhua.dao.StatusDao;
import cn.sujunhua.dao.TemplateDao;
import cn.sujunhua.dao.UserDao;
import cn.sujunhua.dao.UsertypeDao;
import cn.sujunhua.pojo.Contract;
import cn.sujunhua.pojo.Count;
import cn.sujunhua.pojo.Modify;
import cn.sujunhua.pojo.Secret;
import cn.sujunhua.pojo.State;
import cn.sujunhua.pojo.Status;
import cn.sujunhua.pojo.Template;
import cn.sujunhua.pojo.User;
import cn.sujunhua.pojo.Usertype;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext.xml"})
public class MyTest {
	
	@Autowired
	private CountDao countDao;

	@Autowired
	private ContractDao contractDao;
	
	@Autowired
	private UsertypeDao usertypeDao;
	
	@Autowired
	private SecretDao secretDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ModifyDao modifyDao;
	
	@Autowired
	private StateDao stateDao;
	
	@Autowired
	private StatusDao statusDao;
	
	@Autowired
	private TemplateDao templateDao;
	
	@Test
	public void testName() throws Exception {
		System.out.println("edwqdw");
	}
	
	@Test
	public void testName1() throws Exception {
		List<Count> list = countDao.selectAllCount();
		for (Count count : list) {
			System.out.println(count);
		}
	}
	
	@Test
	public void testName2() throws Exception {
		List<Count> list = countDao.selectCountByDay(7);
		List<CountNumer> countNumers=new ArrayList<>();
		String date="";
		CountNumer countNumer=null;
		for (Count count : list) {
			System.out.println(count);
			if (!date.equals(count.getCount_date().substring(0, 10))) {
				countNumer=new CountNumer();
				countNumer.setDate(count.getCount_date().substring(5,10));
				countNumer.setNumber(1);
				countNumers.add(countNumer);
			}
			if (date.equals(count.getCount_date().substring(0, 10))) {
				countNumer.setNumber(countNumer.getNumber()+1);
			}
			date=count.getCount_date().substring(0, 10);
		}
		for (CountNumer countN : countNumers) {
			System.out.println(countN);
		}

	}
	
	/**
	 * 用于生成合同编号    ，用日期加上时分秒
	 * @throws Exception
	 */
	@Test
	public void testName5() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date date = new Date();
		String string = format.format(date);
		System.out.println(string);
		/*String str=string.replaceAll("-", "");
		str=str.replaceAll(" ", "");
		str=str.replaceAll(":", "");
		System.out.println(str);*/
	}
	
	@Test
	public void testName6() throws Exception {
		List<Usertype> list = usertypeDao.selectUsertype(null);
		for (Usertype usertype : list) {
			System.out.println(usertype);
		}
	}
	
	@Test
	public void testName7() throws Exception {
		List<Secret> list = secretDao.selectSecret();
		for (Secret secret : list) {
			System.out.println(secret);
		}
	}
	
	@Test
	public void testName8() throws Exception {
		User user = userDao.findUserPwd("15627526650", 5, "苏俊桦");
		System.out.println(user.getUser_pwd());
	}
	
	@Test
	public void testName9() throws Exception {
		State state = stateDao.selectStateByName("执行中");
		System.out.println(state);
	}
	
	@Test
	public void testName10() throws Exception {
		Modify modify = modifyDao.selectModifyByName("未修改");
		System.out.println(modify);
	}
	
	@Test
	public void testName11() throws Exception {
		List<Contract> list = contractDao.selectContract(null,null,null,null,null);
		for (Contract contract : list) {
			System.out.println(contract);
		}
	}
	
	@Test
	public void testName12() throws Exception {
		List<Count> list = countDao.selectAllCount();
		for (Count count : list) {
			System.out.println(count);
		}
	}
	
    @Test
	public void testName13() throws Exception {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		String string = format.format(date);
		System.out.println(string.substring(0, 10));
		System.out.println(string.substring(5, 10));
	}
    
    @Test
	public void testName14() throws Exception {
		Status status = statusDao.selectStatusByName("");
		System.out.println(status);
	}
    
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
     * 比较两个日期的大小
     * 如果date1小于date2，则返回1
     * 否则返回0
     * @param date1
     * @param date2
     * @return
     */
    public int ghorltBytwoDay(Date date1,Date date2) {
    	if (date1.before(date2)) {
			return 1;
		}
    	else return 0;
    }
    
    @SuppressWarnings("deprecation")
	@Test
	public void testName16() throws Exception {
    	String dateStr = "2019-04-23";
    	Date date = new Date(dateStr);
    	System.out.println(date);
	}
    
    @Test
	public void testName17() throws Exception {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date advancetime = null;
		String dateStr = "2019-04-20";
		//转换成date
		try {
				advancetime = format.parse(dateStr);
				System.out.println(advancetime);
				System.out.println(format.format(advancetime));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    @Test
	public void testName15() throws Exception {
    	String dateStr2 = "2019-04-26";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try 
        {
            Date date2 = format.parse(dateStr2);
            Date date3 = new Date();
            String dateStr3 = format.format(date3);
            Date date = format.parse(dateStr3);
            System.out.println("两个日期的差距：" + differentDaysByMillisecond(date,date2));
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
    
    @Test
	public void testName18() throws Exception {
    	//创建保存需要修改的合同的id
    	List<String> contractids = new ArrayList<String>();
    	//查出所有合同
    	List<Contract> contracts = contractDao.selectContract(null,null,null,null,null);
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
    	for (Contract contract : contracts) {
			System.out.println(contract);
			advancetime = contract.getContract_advancetime();
			//调用计算两个日期相差的天数的方法
			temp = differentDaysByMillisecond(today, format.parse(advancetime));
			if (temp <= 3) {
				contractids.add(contract.getContract_id());
			}
		}
    	if (contractids.size()>0) {
    		for (String contractid : contractids) {
    			System.out.println(contractid);
    		}
    		List<Contract> contractInIDs = contractDao.selectContractInID(contractids);
        	for (Contract contract : contractInIDs) {
    			System.out.println(contract);
    		}
		}
	}
    
    @Test
	public void testName19() throws Exception {
    	//创建保存需要修改的合同的id
    	List<String> contractids = new ArrayList<String>();
    	//查出所有合同
    	List<Contract> contracts = contractDao.selectContract(null,null,null,null,null);
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
    	for (Contract contract : contracts) {
			System.out.println(contract);
			advancetime = contract.getContract_advancetime();
			//调用计算两个日期相差的天数的方法
			temp = differentDaysByMillisecond(today, format.parse(advancetime));
			if (temp <= 3) {
				contractids.add(contract.getContract_id());
			}
		}
    	if (contractids.size()>0) {
    		for (String contractid : contractids) {
    			System.out.println(contractid);
    		}
    		Status status = statusDao.selectStatusByName("待收款");
    		Integer i = contractDao.updateContractsInID(contractids, status.getStatus_id());
    		System.out.println(i);
		}
	}
    @Test
	public void testName20() throws Exception {
		Contract contract = contractDao.findConractByid("20190421211559378");
		System.out.println(contract);
		Contract contract2 = contractDao.findContractFileByid("20190421211559378");
		System.out.println(contract2.getContract_file());
	}
    
    @Test
	public void testName21() throws Exception {
    	//创建保存用户登录记录的id
		List<Integer> countids = new ArrayList<Integer>();
		//查出近来7天或30天的记录
		List<Count> counts = countDao.selectCountByDay(50);
		if (counts.size()>0) {
			for (Count count : counts) {
				countids.add(count.getCount_id());
			}
		}
		if (countids.size()>0) {
			List<Count> selectCountNotInIds = countDao.selectCountNotInId(countids);
			for (Count count : selectCountNotInIds) {
				System.out.println(count);
			}
		}
	}
    
    @Test
	public void testName22() throws Exception {
		List<User> list = userDao.selectUser();
		List<User> users = new ArrayList<User>();
		if (list.size()>0) {
			for (User user : list) {
				if (!"超级管理员".equals(user.getUsertype().getUsertype_name())) {
					users.add(user);
				}
			}
		}
		if (users.size()>0) {
			for (User user : users) {
				System.out.println(user.getUser_name());
			}
		}
	}
    
    //test饼状图
    @Test
	public void testName23() throws Exception {
		List<Contract> contracts = contractDao.selectContractorderbystatus();
		List<ContractEcharts> echarts = new ArrayList<ContractEcharts>();
		Integer statusid = 0;
		ContractEcharts contractEcharts = null;
		if (contracts.size()>0) {
			for (Contract contract : contracts) {
				if (contract.getContract_status() != statusid) {
					Status status = statusDao.selectStatusByID(contract.getContract_status());
					contractEcharts = new ContractEcharts();
					if ("".equals(status.getStatus_name())) {
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
		if (echarts.size()>0) {
			for (ContractEcharts contractEchart : echarts) {
				System.out.println(contractEchart);
			}
		}
	}
    @Test
	public void testName24() throws Exception {
		List<Contract> contracts = contractDao.selectContractlimit();
		List<ContractEcharts> echarts = new ArrayList<ContractEcharts>();
		if (contracts.size()>0) {
			for (Contract contract : contracts) {
				ContractEcharts contractEcharts = new ContractEcharts();
				contractEcharts.setBrokenlinename(contract.getContract_name());
				contractEcharts.setBrokenlinemoney(String.valueOf(contract.getContract_money()));
				echarts.add(contractEcharts);
			}
		}
		if (echarts.size()>0) {
			for (ContractEcharts contractEcharts : echarts) {
				System.out.println(contractEcharts);
			}
		}
	}
    @Test
	public void testName25() throws Exception {
		List<Contract> contracts = contractDao.selecthisyear();
		List<ContractEcharts> echarts = new ArrayList<ContractEcharts>();
		ContractEcharts contractEcharts = null;
		String columnarmonth="";
		if (contracts.size()>0) {
			for (Contract contract : contracts) {
				if (!columnarmonth.equals(contract.getContract_startime().substring(0, 7))) {
					contractEcharts = new ContractEcharts();
					contractEcharts.setColumnarmonth(contract.getContract_startime().substring(0, 7));
					contractEcharts.setColumnarcount(1);
					echarts.add(contractEcharts);
				}
				else {
					contractEcharts.setColumnarcount(contractEcharts.getColumnarcount()+1);
				}
				columnarmonth = contract.getContract_startime().substring(0, 7);
			}
		}
		if (echarts.size()>0) {
			for (ContractEcharts contractEcharts2 : echarts) {
				System.out.println(contractEcharts2);
			}
		}
	}
    @Test
	public void testName26() throws Exception {
    	List<Contract> contracts = contractDao.selectlastyear(1);
		List<ContractEcharts> echarts = new ArrayList<ContractEcharts>();
		ContractEcharts contractEcharts = null;
		String columnarmonth="";
		if (contracts.size()>0) {
			for (Contract contract : contracts) {
				if (!columnarmonth.equals(contract.getContract_startime().substring(0, 7))) {
					contractEcharts = new ContractEcharts();
					contractEcharts.setColumnarmonth(contract.getContract_startime().substring(0, 7));
					contractEcharts.setColumnarcount(1);
					echarts.add(contractEcharts);
				}
				else {
					contractEcharts.setColumnarcount(contractEcharts.getColumnarcount()+1);
				}
				columnarmonth = contract.getContract_startime().substring(0, 7);
			}
		}
		if (echarts.size()>0) {
			for (ContractEcharts contractEcharts2 : echarts) {
				System.out.println(contractEcharts2);
			}
		}
	}
    @Test
	public void testName27() throws Exception {
		Usertype usertype = usertypeDao.selectUserByName("普通用户");
		System.out.println(usertype);
		Usertype usertype1 = usertypeDao.selectUserByName("管理员");
		System.out.println(usertype1);
	}
    @Test
	public void testName28() throws Exception {
		Template template = new Template();
		template.setTemplate_file("wefwefwef w");
		template.setTemplate_filename("wefwefwewefw wef ");
		templateDao.add(template);
	}
    @Test
	public void testName29() throws Exception {
		List<Template> list = templateDao.list();
		for (Template template : list) {
			System.out.println(template);
		}
	}
    @Test
	public void testName30() throws Exception {
		Template findByid = templateDao.findByid(1);
		System.out.println(findByid);
	}
    @Test
	public void testName31() throws Exception {
		templateDao.delete(2);
	}
    @Test
	public void testName32() throws Exception {
    	List<Contract> contracts = contractDao.selectContractlimit();
		List<ContractEcharts> echarts = new ArrayList<ContractEcharts>();
		if (contracts.size()>0) {
			for(int i=contracts.size()-1;i>=0;i--) {
				ContractEcharts contractEcharts = new ContractEcharts();
				contractEcharts.setBrokenlinename(contracts.get(i).getContract_name());
				contractEcharts.setBrokenlinemoney(String.valueOf(contracts.get(i).getContract_money()));
				echarts.add(contractEcharts);
			}
		}
		if (echarts.size()>0) {
			for (ContractEcharts contractEcharts : echarts) {
				System.out.println(contractEcharts);
			}
		}
	}
    @Test
	public void testName33() throws Exception {
    	BigDecimal bigDecimal1 = new BigDecimal("150.0120");
		BigDecimal bigDecimal2 = new BigDecimal(60.0000);
		BigDecimal divide = bigDecimal2.divide(bigDecimal1,2);
		System.out.println(divide);
	}
    @Test
	public void testName34() throws Exception {
    	BigDecimal bigDecimal1 = new BigDecimal(150.0120);
		BigDecimal bigDecimal2 = new BigDecimal(55.0000);
		BigDecimal decimal = bigDecimal2.divide(bigDecimal1,2,BigDecimal.ROUND_HALF_UP);
		System.out.println(decimal);
		System.out.println(bigDecimal2);
		BigDecimal multiply = decimal.multiply(new BigDecimal(100));
		System.out.println(multiply.intValue());
	}
    @Test
	public void testName35() throws Exception {
    	BigDecimal bigDecimal1 = new BigDecimal("150.1230");
		BigDecimal bigDecimal2 = new BigDecimal(0);
		BigDecimal add = bigDecimal2.add(bigDecimal1);
		System.out.println(add);
	}
    @Test
	public void testName36() throws Exception {
    	BigDecimal bigDecimal1 = new BigDecimal("150.1230");
		BigDecimal bigDecimal2 = new BigDecimal("150.1230");
		BigDecimal subtract = bigDecimal2.subtract(bigDecimal1);
		System.out.println(subtract);
	}
    @Test
	public void testName38() throws Exception {
		List<Contract> contracts = contractDao.selectContractlimit();
		List<ContractMoneyEchart> contractMoneyEcharts = new ArrayList<ContractMoneyEchart>();
		if (contracts.size()>0) {
			for (Contract contract : contracts) {
				ContractMoneyEchart contractMoneyEchart = new ContractMoneyEchart();
				contractMoneyEchart.setContractname(contract.getContract_name());
				contractMoneyEchart.setMoneycount(String.valueOf(contract.getContract_money()));
				contractMoneyEchart.setReceiptscount(String.valueOf(contract.getContract_accumulatedreceipts()));
				contractMoneyEchart.setArrearscount(String.valueOf(contract.getContract_accumulatedarrears()));
				contractMoneyEcharts.add(contractMoneyEchart);
			}
		}
		if (contractMoneyEcharts.size()>0) {
			for (ContractMoneyEchart contractMoneyEchart : contractMoneyEcharts) {
				System.out.println(contractMoneyEchart);
			}
		}
	}
}
