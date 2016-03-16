package com.lundui.manage.stock.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.lundui.manage.common.service.BasicInfoService;
import com.lundui.manage.model.Depot;
import com.lundui.manage.model.TemporaryRepair;
import com.lundui.manage.model.User;
import com.lundui.manage.model.WheelRecord;
import com.lundui.manage.stock.service.TempRepairManageService;
import com.lundui.manage.system.service.UserManageService;
import com.lundui.manage.util.PageModel;

/**
 * 临修管理
 * @author Administrator
 *
 */
public class TempRepairManageAction {
	
	@Resource(name="tempRepairManageService")
	private TempRepairManageService service;
	
	private TemporaryRepair tempRepair;
	
	@Resource(name="userManageService")
	private UserManageService userService;
	
	@Resource(name="basicInfoService")
	private BasicInfoService basicInfoService;
	
	private List<User> userList;
	
	private String result;
	
	private PageModel<TemporaryRepair> pageModel;
	
	private List<Long> ids=new ArrayList<Long>();
	
	private List<Depot> depots;
	
	/**
	 * 收入轮对
	 */
	private WheelRecord record;
	
	/**
	 * 支出轮对
	 */
	private WheelRecord outRecord;
	
	/**
	 * 轴号
	 */
	private String axleNum;
	
	/**
	 * 轴型
	 */
	private String axleType;
	
	/**
	 * 处理方式
	 */
	private Short treatment;
	
	/**
	 * 机车号
	 */
	private String jcNum;
	
	/**
	 * 单位代码
	 */
	private String depot;
	
	/**
	 * 轮型
	 */
	private  String wheelType;
	
	/**
	 * 临修时间
	 */
	private Date dateFrom;
	
	private Date dateTo;
	
	public static final SimpleDateFormat YMD_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	private void initInfo(){
		ServletActionContext.getRequest().getSession().setAttribute("axleTypes", basicInfoService.getAxleTypes());
		ServletActionContext.getRequest().getSession().setAttribute("wheelTypes", basicInfoService.getWheelTypes());
	}
	
	/**
	 * 轮对临修记录类表
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String tmpRepairList() throws UnsupportedEncodingException{
		initInfo();
		String depotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");
		if(depot==null||depot.equals("")){
			depot=depotCode;
		}
		this.depots=basicInfoService.getDepotList(depotCode);
		pageModel=service.findTemporaryRepair(axleNum, axleType, wheelType, dateFrom, dateTo, treatment, jcNum, depot, true);
		return "list";
	}
	
	public String toAddTempRepair(){
		String depotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");
		ServletActionContext.getRequest().getSession().setAttribute("timeTemp", YMD_FORMAT.format(new Date()));
		this.depots=basicInfoService.getDepotList(depotCode);
		userList=userService.getUserList(depotCode);
		return "toAdd";
	}
	
	/**
	 * 添加轮对临修记录
	 * @return
	 */
	public String addTempRepair(){
		try{
		String depotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");	
		Depot currentDepot=this.basicInfoService.getDepot(depotCode);
		tempRepair.setDepotCode(depotCode);//设置当前段；
		tempRepair.setDepotName(currentDepot.getDepotName());
		tempRepair=service.addTemporaryRepair(tempRepair,record,outRecord);
		if(tempRepair!=null){
			result="1";
		}else{
			result="0";
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "addSuccess";
	}
	
	/**
	 *更新轮对记录列表
	 * @return
	 */
	public String updateTempRepair(){
		service.updateTemporaryRepair(tempRepair);
		return "updateSuccess";
	}
	
	/**
	 * 批量删除轮对临修记录
	 * @return
	 */
	public String deleteTempRepairs(){
		service.delteTemporaryRepair(ids);
		result="1";
		return "deleteSuccess";
	}

	public TempRepairManageService getService() {
		return service;
	}

	public void setService(TempRepairManageService service) {
		this.service = service;
	}

	public TemporaryRepair getTempRepair() {
		return tempRepair;
	}

	public void setTempRepair(TemporaryRepair tempRepair) {
		this.tempRepair = tempRepair;
	}

	public UserManageService getUserService() {
		return userService;
	}

	public void setUserService(UserManageService userService) {
		this.userService = userService;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public PageModel<TemporaryRepair> getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel<TemporaryRepair> pageModel) {
		this.pageModel = pageModel;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public WheelRecord getOutRecord() {
		return outRecord;
	}

	public void setOutRecord(WheelRecord outRecord) {
		this.outRecord = outRecord;
	}

	public WheelRecord getRecord() {
		return record;
	}

	public void setRecord(WheelRecord record) {
		this.record = record;
	}

	public BasicInfoService getBasicInfoService() {
		return basicInfoService;
	}

	public void setBasicInfoService(BasicInfoService basicInfoService) {
		this.basicInfoService = basicInfoService;
	}

	public String getAxleNum() {
		return axleNum;
	}

	public void setAxleNum(String axleNum) {
		this.axleNum = axleNum;
	}

	public String getAxleType() {
		return axleType;
	}

	public void setAxleType(String axleType) {
		this.axleType = axleType;
	}

	public Short getTreatment() {
		return treatment;
	}

	public void setTreatment(Short treatment) {
		this.treatment = treatment;
	}

	public String getJcNum() {
		return jcNum;
	}

	public void setJcNum(String jcNum) {
		this.jcNum = jcNum;
	}

	public String getDepot() {
		return depot;
	}

	public void setDepot(String depot) {
		this.depot = depot;
	}

	public String getWheelType() {
		return wheelType;
	}

	public void setWheelType(String wheelType) {
		this.wheelType = wheelType;
	}

	public List<Depot> getDepots() {
		return depots;
	}

	public void setDepots(List<Depot> depots) {
		this.depots = depots;
	}

}
