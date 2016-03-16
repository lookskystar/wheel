package com.lundui.manage.stock.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.lundui.manage.common.service.BasicInfoService;
import com.lundui.manage.model.AxleType;
import com.lundui.manage.model.Depot;
import com.lundui.manage.model.Income;
import com.lundui.manage.model.User;
import com.lundui.manage.model.WheelRecord;
import com.lundui.manage.model.WheelType;
import com.lundui.manage.model.XC;
import com.lundui.manage.stock.service.IncomeManageService;
import com.lundui.manage.system.service.UserManageService;
import com.lundui.manage.util.PageModel;

/**
 * 轮对收入管理
 * @author Administrator
 *
 */
public class IncomeManageAction {
	
	private Income income;
	
	private WheelRecord record;
	
	@Resource(name="incomeManageService")
	private IncomeManageService service;
	
	@Resource(name="userManageService")
	private UserManageService userService;
	
	@Resource(name="basicInfoService")
	private BasicInfoService basicInfoService;
	
	private PageModel<Income> pageModel;
	
	private String result;
	
	private Long[] ids;
	
	private List<XC> xcList;
	
	/**
	 * 用户列表
	 */
	private List<User> users;
	
	/**
	 * 段列表
	 */
	private List<Depot> depots;
	
	private List<AxleType> axleTypes;
	
	private List<WheelType> wheelTypes;
	
	public static final SimpleDateFormat YMD_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	//查询
	
	private String queryName;
	
	private String value;
	
	private String valueParam;
	
	private Date dateFrom;
	
	private Date dateTo;
	
	private Short status;
	
	private String axleType;
	
	private String axleNum;
	
	private String wheelType;
	
	private String depot;
	
	private Short whereabouts;
	
	public String toAddIncome(){
		String timeTemp =YMD_FORMAT.format(new Date());
		String depotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");
		this.xcList=basicInfoService.getXCs();
		this.users=userService.getUserList(depotCode);
		this.axleTypes=basicInfoService.getAxleTypes();
		this.wheelTypes=basicInfoService.getWheelTypes();
		ServletActionContext.getRequest().getSession().setAttribute("zxTypes", basicInfoService.getZXTypes());
		ServletActionContext.getRequest().getSession().setAttribute("timeTemp", timeTemp);
		this.depots=basicInfoService.getDepotList(depotCode);
		return "toAdd";
	}
	
	public String toAddIncomeNew(){
		toAddIncome();
		return "toAddnew";
	}
	
	public String toAddIncomeRepair(){
		toAddIncome();
		return "toAddrepair";
	}
	
	/**
	 * 检修单位进入添加轮对收入页面
	 * @return
	 */
	public String toAddJxIncome(){
		String depotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");
		this.xcList=basicInfoService.getXCs();
		this.users=userService.getUserList(depotCode);
		this.depots=userService.getDepotList();
		return "toAdd2";
	}
	
	public String addNewIncome(){
		String depotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");	
		Depot currentDepot=this.basicInfoService.getDepot(depotCode);
		income.setDepotCode(depotCode);
		income.setDepotName(currentDepot.getDepotName());
		income.setReason((short)1);//收入原因：新购
		income=this.service.addNewIncome(income,record);
		if(income!=null){
			result="1";
		}else{
			result="0";
		}
		return "addSuccess";
	}
	
	/**
	 * 添加收入
	 * @return
	 */
	public String addIncome(){
		String depotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");	
		Depot currentDepot=this.basicInfoService.getDepot(depotCode);
		income.setDepotCode(depotCode);
		income.setDepotName(currentDepot.getDepotName());
		income=this.service.addIncome(income,record,false);
		if(income!=null){
			result="1";
		}else{
			result="0";
		}
		return "addSuccess";
	}
	
	/**
	 * 收入列表
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String incomeList() throws UnsupportedEncodingException{
		this.axleTypes=basicInfoService.getAxleTypes();
		this.wheelTypes=basicInfoService.getWheelTypes();
		if(valueParam!=null && !"".equals(valueParam.trim())){
			value = URLDecoder.decode(valueParam, "utf-8");
		}
		String depotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");
		if(depot==null||depot.equals("")){
			depot=depotCode;
		}
		this.depots=basicInfoService.getDepotList(depotCode);
		this.pageModel=service.queryIncom(axleNum, axleType, wheelType, dateFrom, dateTo, status, value, depot,true);
		//this.pageModel=service.queryIncome(queryName, value, dateFrom, dateTo, status);
		if(value!=null)valueParam = URLEncoder.encode(value, "utf-8");
		return "list";
	}
	
	/**
	 * 检修单位的轮对支出列表
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String jxincomeList() throws UnsupportedEncodingException{
		this.axleTypes=basicInfoService.getAxleTypes();
		this.wheelTypes=basicInfoService.getWheelTypes();
		if(queryName==null||queryName.equals("")){
			this.pageModel=service.queryIncome();
		}else{
			if(valueParam!=null && !"".equals(valueParam.trim())){
				value = URLDecoder.decode(valueParam, "utf-8");
			}
			this.pageModel=service.queryIncome(queryName, value, dateFrom, dateTo, status);
			valueParam = URLEncoder.encode(value, "utf-8");
		}
		return "jxlist";
	}
	
	/**
	 * 查询一个收入
	 * @return
	 */
	public String findIncome(){
		income=service.getIncome(income.getId());
		return "edit";
	}
	
	/**
	 * 更新一个收入
	 * @return
	 */
	public String updateIncome(){
		income=service.updateIncome(income);
		return "updateSuccess";
	}
	
	/**
	 * 查看该轴号是否存在
	 */
	public String ajaxExistAxleNum(){
		String axleNum=ServletActionContext.getRequest().getParameter("axleNum");
		HttpServletResponse response=ServletActionContext.getResponse();
		List<WheelRecord> wheelRecords=service.getWheelRecordByAxleNum(axleNum);
		String result="success";
		if(wheelRecords!=null&&wheelRecords.size()>0){
			WheelRecord wheelRecord=wheelRecords.get(0);
			String depotName=wheelRecord.getDepotName();
			result=depotName;
		}
		response.setContentType("text/plain");
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public IncomeManageService getService() {
		return service;
	}

	public void setService(IncomeManageService service) {
		this.service = service;
	}

	public Income getIncome() {
		return income;
	}

	public void setIncome(Income income) {
		this.income = income;
	}

	public PageModel<Income> getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel<Income> pageModel) {
		this.pageModel = pageModel;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public WheelRecord getRecord() {
		return record;
	}

	public void setRecord(WheelRecord record) {
		this.record = record;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public List<XC> getXcList() {
		return xcList;
	}

	public void setXcList(List<XC> xcList) {
		this.xcList = xcList;
	}

	public UserManageService getUserService() {
		return userService;
	}

	public void setUserService(UserManageService userService) {
		this.userService = userService;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Depot> getDepots() {
		return depots;
	}

	public void setDepots(List<Depot> depots) {
		this.depots = depots;
	}

	public String getValueParam() {
		return valueParam;
	}

	public void setValueParam(String valueParam) {
		this.valueParam = valueParam;
	}

	public BasicInfoService getBasicInfoService() {
		return basicInfoService;
	}

	public void setBasicInfoService(BasicInfoService basicInfoService) {
		this.basicInfoService = basicInfoService;
	}

	public List<AxleType> getAxleTypes() {
		return axleTypes;
	}

	public void setAxleTypes(List<AxleType> axleTypes) {
		this.axleTypes = axleTypes;
	}

	public List<WheelType> getWheelTypes() {
		return wheelTypes;
	}

	public void setWheelTypes(List<WheelType> wheelTypes) {
		this.wheelTypes = wheelTypes;
	}

	public String getAxleType() {
		return axleType;
	}

	public void setAxleType(String axleType) {
		this.axleType = axleType;
	}

	public String getAxleNum() {
		return axleNum;
	}

	public void setAxleNum(String axleNum) {
		this.axleNum = axleNum;
	}

	public String getWheelType() {
		return wheelType;
	}

	public void setWheelType(String wheelType) {
		this.wheelType = wheelType;
	}

	public String getDepot() {
		return depot;
	}

	public void setDepot(String depot) {
		this.depot = depot;
	}

	public Short getWhereabouts() {
		return whereabouts;
	}

	public void setWhereabouts(Short whereabouts) {
		this.whereabouts = whereabouts;
	}
	
	
}
