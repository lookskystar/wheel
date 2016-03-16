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
import com.lundui.manage.model.AxleType;
import com.lundui.manage.model.Depot;
import com.lundui.manage.model.Outlay;
import com.lundui.manage.model.User;
import com.lundui.manage.model.WheelRecord;
import com.lundui.manage.model.WheelType;
import com.lundui.manage.model.XC;
import com.lundui.manage.stock.service.OutlayManageService;
import com.lundui.manage.stock.service.WheelRecordManageService;
import com.lundui.manage.system.service.UserManageService;
import com.lundui.manage.util.PageModel;

/**
 * 轮对支出管理
 * @author Administrator
 *
 */
public class OutlayManageAction {
	
	@Resource(name="outlayManageService")
	private OutlayManageService service;
	
	@Resource(name="userManageService")
	private UserManageService userService;
	
	@Resource(name="basicInfoService")
	private BasicInfoService basicInfoService;
	
	@Resource(name="wheelRecordManageService")
	private WheelRecordManageService wheelRecordService;
	
	private Outlay outlay;
	
	private WheelRecord record;
	
	private PageModel<Outlay> pageModel;
	
	private List<User> users;
	
	private List<Long> ids=new ArrayList<Long>();
	
	private String result;
	
	private String queryName;
	
	private String value;
	
	private Date dateFrom;
	
	private Date dateTo;
	
	private Short status;
	private String axleType;
	
	private String axleNum;
	
	private String wheelType;
	
	private String depot;
	
	private String valueParam;
	
	private List<XC> xcList;
	/**
	 * 段列表
	 */
	private List<Depot> depots;
	
	private List<AxleType> axleTypes;
	
	private List<WheelType> wheelTypes;
	
	public static final SimpleDateFormat YMD_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 查询轮对支出
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String outlayList() throws UnsupportedEncodingException{
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
		this.pageModel=service.queryOutlayList(axleNum, axleType, wheelType, dateFrom, dateTo, status, value, depot,true);
		//this.pageModel=service.queryIncome(queryName, value, dateFrom, dateTo, status);
		if(value!=null)valueParam = URLEncoder.encode(value, "utf-8");
		return "list";
	}
	
	/**
	 * 添加轮对支出
	 * @return
	 */
	public String addOutlay(){
		String depotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");	
		Depot currentDepot=this.basicInfoService.getDepot(depotCode);
		outlay.setDepotCode(currentDepot.getDepotCode());
		outlay.setDepotName(currentDepot.getDepotName());
		if(outlay.getReason()==2||outlay.getReason()==5){//送修和修竣
			Depot targeDepot=this.basicInfoService.getDepot(outlay.getTargetUnit());
			outlay.setRemark("接收单位:"+targeDepot.getDepotName());
		}
		
		if(outlay.getReason()==1){
			WheelRecord r=wheelRecordService.queryWheelRecordBySC(record.getJcNum(), record.getPosition());
			if(r!=null){
				result="{result:1,msg:'该上车位已经有一个轮对记录'}";
				return "addSuccess";
			}
			outlay.setRemark(String.format("上车号:%s,上车位:%s",record.getJcNum(),record.getPosition()));
		}
		outlay =service.addOutlay(outlay,record);
		if(outlay==null){
			result="1";
		}else{
			result="0";
		}
		return "addSuccess";
	}
	
	public String toAddOutlay(){
		String timeTemp =YMD_FORMAT.format(new Date());
		String depotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");
		this.xcList=basicInfoService.getXCs();
		this.users=userService.getUserList(depotCode);
		this.axleTypes=basicInfoService.getAxleTypes();
		this.wheelTypes=basicInfoService.getWheelTypes();
		ServletActionContext.getRequest().getSession().setAttribute("zxTypes", basicInfoService.getZXTypes());
		ServletActionContext.getRequest().getSession().setAttribute("timeTemp", timeTemp);
		ServletActionContext.getRequest().getSession().setAttribute("depot", basicInfoService.getCJDepots());
		return "toAdd";
	}
	
	/**
	 * 删除轮对支出
	 * @return
	 */
	public String deleteOutlays(){
		return "deleteSuccess";
	}
	

	public OutlayManageService getService() {
		return service;
	}

	public void setService(OutlayManageService service) {
		this.service = service;
	}

	public Outlay getOutlay() {
		return outlay;
	}

	public void setOutlay(Outlay outlay) {
		this.outlay = outlay;
	}

	public WheelRecord getRecord() {
		return record;
	}

	public void setRecord(WheelRecord record) {
		this.record = record;
	}

	public PageModel<Outlay> getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel<Outlay> pageModel) {
		this.pageModel = pageModel;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
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

	public List<XC> getXcList() {
		return xcList;
	}

	public void setXcList(List<XC> xcList) {
		this.xcList = xcList;
	}

	public List<Depot> getDepots() {
		return depots;
	}

	public void setDepots(List<Depot> depots) {
		this.depots = depots;
	}

}
