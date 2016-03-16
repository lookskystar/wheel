package com.lundui.manage.system.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts2.ServletActionContext;

import com.lundui.manage.common.service.BasicInfoService;
import com.lundui.manage.model.Depot;
import com.lundui.manage.model.Income;
import com.lundui.manage.model.Outlay;
import com.lundui.manage.model.User;
import com.lundui.manage.model.WheelRec;
import com.lundui.manage.model.WheelRecord;
import com.lundui.manage.model.WheelType;
import com.lundui.manage.stock.service.IncomeManageService;
import com.lundui.manage.stock.service.OutlayManageService;
import com.lundui.manage.stock.service.WheelRecordManageService;
import com.lundui.manage.util.PageModel;

/**
 * 后台轮对履历管理
 * @author Administrator
 *
 */
public class WheelEditAction {
	
	@Resource(name="wheelRecordManageService")
	private WheelRecordManageService wheelRecordService;
	
	@Resource(name="incomeManageService")
	private IncomeManageService incomeManageService;
	
	@Resource(name="outlayManageService")
	private OutlayManageService outlayManageService;
	
	@Resource(name="basicInfoService")
	private BasicInfoService basicInfoService;
	
	@Resource(name="incomeManageService")
	private IncomeManageService service;
	
	private HttpServletRequest request = ServletActionContext.getRequest();
	
	public static final SimpleDateFormat YMD_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	private WheelRecord record;
	
	private PageModel<WheelRecord> pageModel;
	
	private PageModel<WheelRec> pm;
	
	private List<Long> ids=new ArrayList<Long>();
	
	private List<WheelType> wheelTypes;
	
	private List<Depot> depots;
	
	private String result;
	
	private String queryName;
	
	private String value;
	
	private Date dateFrom;
	
	private Date dateTo;
	
	private Date beginTime;	
	
	private Date endTime;	
	
	private Short status;
	
	private String valueParam;
	
	private String axleType;
	
	private String axleNum;
	
	private String oldaxleNum;
	
	private String userName;
	
	private String wheelType;
	
	private String depot;
	
	private Short whereabouts;
	
	private String factory;
	
	private String view;
	
	private Boolean sub=true;
	
	private Double diameterFrom;

	private Double diameterTo;

	private File excel;
	
	private String jcNum;

	private String position;
	
	
	private void initInfo(){
		ServletActionContext.getRequest().getSession().setAttribute("axleTypes", basicInfoService.getAxleTypes());
		ServletActionContext.getRequest().getSession().setAttribute("wheelTypes", basicInfoService.getWheelTypes());
	}
	
	
	/**
	 * 后台查看轮对履历列表
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String recordList() throws UnsupportedEncodingException{
		initInfo();
		if(valueParam!=null && !"".equals(valueParam.trim())){
			factory = URLDecoder.decode(valueParam, "utf-8");
		}
		String depotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");
		if(depot==null||depot.equals("")){
			depot=depotCode;
		}
		this.depots=basicInfoService.getDepotList(depotCode);
		ServletActionContext.getRequest().getSession().setAttribute("depots", basicInfoService.getDepotList(depotCode));
		this.pageModel=wheelRecordService.queryWheelRecodList(axleNum, axleType, wheelType, depot, factory, dateFrom, dateTo, status, whereabouts,true);
		if(factory!=null&&!factory.equals("")){
			valueParam = URLEncoder.encode(factory, "utf-8");
		}
		return "wheellist";
	}
	
	
	/**
	 * 进入编辑轮对信息
	 */
	public String editWheelRecordInput() throws Exception {
		WheelRecord wheel=wheelRecordService.getRecord(Long.valueOf(request.getParameter("id")));
		request.setAttribute("axleTypes", basicInfoService.getAxleTypes());
		request.setAttribute("wheelTypes", basicInfoService.getWheelTypes());
		request.setAttribute("zxTypes", basicInfoService.getZXTypes());
		request.setAttribute("wheel", wheel);
		return "editwheel";
	}
	

	/**
	 * 编辑轮对
	 * @throws Exception
	 */
	public String editWheelRecord() throws Exception{
		WheelRecord wheel=wheelRecordService.getRecord(Long.valueOf(request.getParameter("id")));
		String axleNum =wheel.getAxleNum();//原轴号
		wheel.setAxleNum(record.getAxleNum());
//		wheel.setAxleType(record.getAxleType());                    轴型及轮型都是能被修改
//		wheel.setWheelTypeName(record.getWheelTypeName());
		wheel.setAxleBoxType(record.getAxleBoxType());
		wheel.setHasAxleBoxRelay(record.getHasAxleBoxRelay());
		wheel.setHasAntiSkid(record.getHasAntiSkid());
		wheel.setAntiSkidSize(record.getAntiSkidSize());
		wheel.setAntiSkidLoc(record.getAntiSkidLoc());
		wheel.setFactory(record.getFactory());
		wheel.setCreateDate(record.getCreateDate());
		wheel.setBoxCreateDate(record.getBoxCreateDate());
		wheel.setWheelCreateDate(record.getWheelCreateDate());
		wheel.setAxleCreateDate(record.getAxleCreateDate());
		wheel.setLeftRimThickness(record.getLeftRimThickness());
		wheel.setRightRimThickness(record.getRightRimThickness());
		wheel.setLeftBrakeDiscWear(record.getLeftBrakeDiscWear());
		wheel.setRightBrakeDiscWear(record.getRightBrakeDiscWear());
		wheel.setLeftDiameter(record.getLeftDiameter());
		wheel.setRightDiamter(record.getRightDiamter());
		wheel.setLeftFlangeThickness(record.getLeftFlangeThickness()); // 轮缘厚(左端)
		wheel.setRightFlangeThickness(record.getRightFlangeThickness());
		wheel.setLeftCircularWear(record.getLeftCircularWear());
		wheel.setRightCircularWear(record.getRightCircularWear());
		wheel.setInsideistance(record.getInsideistance());
//		wheel.setStatus(record.getStatus());//状态
		wheel.setComment(record.getComment());
		User user = (User) request.getSession().getAttribute("currentUser");
		if(!axleNum.equals(record.getAxleNum())){//轴号变化生成记录
			  
			  WheelRec wheelrec=new WheelRec();
			  wheelrec.setAxleNum(record.getAxleNum());
			  wheelrec.setOldaxleNum(axleNum);
			  wheelrec.setUserName(user.getUsername());
			  wheelrec.setUserTime(java.sql.Date.valueOf(YMD_FORMAT.format(new Date())));
			  wheelrec.setDepotCode(wheel.getDepotCode());
			
			  wheelRecordService.saveWheelRec(wheelrec);
		}
		wheelRecordService.updateWheelRecord(wheel);

		request.setAttribute("message", "轮对信息编辑成功");
		return recordList();
	}
	
	/**
	 * 查看履历信息
	 * @return
	 */
	public String info(){
		record=wheelRecordService.getRecord(record.getId());
		Income income = incomeManageService.queryLastestIncomeByWheel(record.getId());
		Outlay out = outlayManageService.findLastestOutlayByWheel(record.getId());
		ServletActionContext.getRequest().setAttribute("income", income);
		ServletActionContext.getRequest().setAttribute("out", out);
		return "info";
	}
	
	/**
	 * 后台查看轮对履历   修改  列表
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String recList() throws UnsupportedEncodingException{
		initInfo();
		if(valueParam!=null && !"".equals(valueParam.trim())){
			userName = URLDecoder.decode(valueParam, "utf-8");
		}
		String depotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");
		if(depot==null||depot.equals("")){
			depot=depotCode;
		}
		this.depots=basicInfoService.getDepotList(depotCode);
		ServletActionContext.getRequest().getSession().setAttribute("depots", basicInfoService.getDepotList(depotCode));
		this.pm=wheelRecordService.queryWheelRecList(axleNum, oldaxleNum, userName, depot, beginTime, endTime, true);
		if(userName!=null&&!userName.equals("")){
			valueParam = URLEncoder.encode(userName, "utf-8");
		}
		return "reclist";
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
	
	
	

	public WheelRecord getRecord() {
		return record;
	}

	public void setRecord(WheelRecord record) {
		this.record = record;
	}

	public PageModel<WheelRecord> getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel<WheelRecord> pageModel) {
		this.pageModel = pageModel;
	}

	
	public PageModel<WheelRec> getPm() {
		return pm;
	}


	public void setPm(PageModel<WheelRec> pm) {
		this.pm = pm;
	}


	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<WheelType> getWheelTypes() {
		return wheelTypes;
	}

	public void setWheelTypes(List<WheelType> wheelTypes) {
		this.wheelTypes = wheelTypes;
	}

	public String getValueParam() {
		return valueParam;
	}

	public void setValueParam(String valueParam) {
		this.valueParam = valueParam;
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

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public Boolean getSub() {
		return sub;
	}

	public void setSub(Boolean sub) {
		this.sub = sub;
	}

	public Double getDiameterFrom() {
		return diameterFrom;
	}

	public void setDiameterFrom(Double diameterFrom) {
		this.diameterFrom = diameterFrom;
	}

	public Double getDiameterTo() {
		return diameterTo;
	}

	public void setDiameterTo(Double diameterTo) {
		this.diameterTo = diameterTo;
	}

	public List<Depot> getDepots() {
		return depots;
	}

	public void setDepots(List<Depot> depots) {
		this.depots = depots;
	}

	public File getExcel() {
		return excel;
	}

	public void setExcel(File excel) {
		this.excel = excel;
	}
	

	public String getJcNum() {
		return jcNum;
	}

	public void setJcNum(String jcNum) {
		this.jcNum = jcNum;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}


	public Date getBeginTime() {
		return beginTime;
	}


	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}


	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public String getOldaxleNum() {
		return oldaxleNum;
	}


	public void setOldaxleNum(String oldaxleNum) {
		this.oldaxleNum = oldaxleNum;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
