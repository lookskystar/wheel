package com.lundui.manage.stock.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.lundui.manage.common.service.BasicInfoService;
import com.lundui.manage.model.Depot;
import com.lundui.manage.model.Income;
import com.lundui.manage.model.Outlay;
import com.lundui.manage.model.User;
import com.lundui.manage.model.WheelRecord;
import com.lundui.manage.model.WheelType;
import com.lundui.manage.stock.service.IncomeManageService;
import com.lundui.manage.stock.service.OutlayManageService;
import com.lundui.manage.stock.service.WheelRecordManageService;
import com.lundui.manage.util.PageModel;

/**
 * 轮对履历管理
 * @author Administrator
 *
 */
public class WheelRecordManangeAction {
	
	@Resource(name="wheelRecordManageService")
	private WheelRecordManageService wheelRecordService;
	
	@Resource(name="incomeManageService")
	private IncomeManageService incomeManageService;
	
	@Resource(name="outlayManageService")
	private OutlayManageService outlayManageService;
	
	@Resource(name="basicInfoService")
	private BasicInfoService basicInfoService;
	
	private HttpServletRequest request = ServletActionContext.getRequest();
	
	private WheelRecord record;
	
	private PageModel<WheelRecord> pageModel;
	
	private List<Long> ids=new ArrayList<Long>();
	
	private List<WheelType> wheelTypes;
	
	private List<Depot> depots;
	
	private String result;
	
	private String queryName;
	
	private String value;
	
	private Date dateFrom;
	
	private Date dateTo;
	
	private Short status;
	
	private String valueParam;
	
	private String axleType;
	
	private String axleNum;
	
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
	
	public String toAddRecord(){
		this.wheelTypes=basicInfoService.getWheelTypes();
		return "toAdd";
	}
	
	/**
	 * 添加一个轮对
	 * @return
	 */
	public String addRecord(){
		User user=(User)ServletActionContext.getRequest().getSession().getAttribute("user");
		Depot depot=user.getDepot();
		record=wheelRecordService.addWheelRecord(record);
		if(record!=null){
			result="true";
		}else{
			result="false";
		}
		return "addSuccess";
	}
	
	public void uploadFile(){
		PrintWriter pw = null;
		try {
			pw=ServletActionContext.getResponse().getWriter();
			int result= this.wheelRecordService.saveFromExcel(excel);
			if(result>0){
				pw.write("1");
			}else{
				pw.write("0");
			}
		} catch (IOException e) {
			pw.write("0");
			e.printStackTrace();
		}finally{
			if(pw != null){
				pw.flush();
				pw.close();
			}
		}
	}
	
	private void initInfo(){
		ServletActionContext.getRequest().getSession().setAttribute("axleTypes", basicInfoService.getAxleTypes());
		ServletActionContext.getRequest().getSession().setAttribute("wheelTypes", basicInfoService.getWheelTypes());
	}
	
	/**
	 * 轮对履历列表
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String jxRecordList() throws UnsupportedEncodingException{
		initInfo();
		if(valueParam!=null && !"".equals(valueParam.trim())){
			factory = URLDecoder.decode(valueParam, "utf-8");
		}
		String depotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");
		if(depot==null||depot.equals("")){
			depot=depotCode;
		}
		depots=this.basicInfoService.getAllDepots();
		ServletActionContext.getRequest().getSession().setAttribute("depots", basicInfoService.getDepotList(depotCode));
		this.pageModel=wheelRecordService.queryWheelRecodList(axleNum, axleType, wheelType, depot, factory, dateFrom, dateTo, status, whereabouts,true);
		if(factory!=null&&!factory.equals("")){
			valueParam = URLEncoder.encode(factory, "utf-8");
		}
		return "jxlist";
	}
	
	/**
	 * 领导查看轮对履历列表
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String simpleRecordList() throws UnsupportedEncodingException{
		initInfo();
		if(valueParam!=null && !"".equals(valueParam.trim())){
			factory = URLDecoder.decode(valueParam, "utf-8");
		}
		String depotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");
		if(depot==null||depot.equals("")){
			depot=depotCode;
		}
		ServletActionContext.getRequest().getSession().setAttribute("depots", basicInfoService.getDepotList(depotCode));
		this.pageModel=wheelRecordService.queryWheelRecodList(axleNum, axleType, wheelType, depot, factory, dateFrom, dateTo, status, whereabouts,true);
		if(factory!=null&&!factory.equals("")){
			valueParam = URLEncoder.encode(factory, "utf-8");
		}
		if(view!=null&&view.equals("zc")){
			return "zclist";
		}else if(view!=null&&view.equals("sx")){
			return "sxlist";
		}
		return "simplelist";
	}
	
	/**
	 * 轮对卡片选择
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String cardList()throws UnsupportedEncodingException{
		initInfo();
		if(valueParam!=null && !"".equals(valueParam.trim())){
			factory = URLDecoder.decode(valueParam, "utf-8");
		}
		String depotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");
		if(depot==null||depot.equals("")){
			depot=depotCode;
		}
		ServletActionContext.getRequest().getSession().setAttribute("depots", basicInfoService.getDepotList(depotCode));
		this.pageModel=wheelRecordService.queryWheelRecodList(axleNum, axleType, wheelType, depot, factory, dateFrom, dateTo, status, whereabouts,sub,diameterFrom,diameterTo);
		if(factory!=null&&!factory.equals("")){
			valueParam = URLEncoder.encode(factory, "utf-8");
		}
		return "cardList";
	}
	
	public String cardList2()throws UnsupportedEncodingException{
		cardList();
		return "cardList2";
	}
	
	public String srCardList(){
		String depotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");
		List<WheelRecord> list=wheelRecordService.findWheelRecordsByDepot(depotCode);
		this.pageModel=new PageModel<WheelRecord>();
		this.pageModel.setCount(list.size());
		this.pageModel.setDatas(list);
		return "cardList4";
	}
	
	public String cardList3(){
		record=wheelRecordService.queryWheelRecordBySC(jcNum, position);
		return "cardList3";
	}

	
	
	/**
	 * 查询可以供选择的轮对（除当前轮对和报废的轮对）
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String electiveRecordList() throws UnsupportedEncodingException{
		String recordId = ServletActionContext.getRequest().getParameter("recordId");
		ServletActionContext.getRequest().getSession().setAttribute("axleTypes", basicInfoService.getAxleTypes());
		ServletActionContext.getRequest().getSession().setAttribute("wheelTypes", basicInfoService.getWheelTypes());
		if(valueParam!=null && !"".equals(valueParam.trim())){
			value = URLDecoder.decode(valueParam, "utf-8");
		}
		if(depot==null||depot.equals("")){
			depot=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");
		}
		this.pageModel=wheelRecordService.queryElectiveWheelRecodList(recordId,axleNum, axleType, wheelType, depot, factory);
		if(factory!=null&&!factory.equals("")){
			valueParam = URLEncoder.encode(factory, "utf-8");
		}
		return "list";
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
	 * 编辑履历信息
	 * @return
	 */
	public String edit(){
		this.wheelTypes=basicInfoService.getWheelTypes();
		record=wheelRecordService.getRecord(record.getId());
		return "edit";
	}
	
	/**
	 * 修改轮对履历
	 * @return
	 */
	public String updateRecord(){
		User user=(User)ServletActionContext.getRequest().getSession().getAttribute("user");
		Depot depot=user.getDepot();
		record=wheelRecordService.updateWheelRecord(record);
		if(record==null){
			this.result="0";
		}else{
			result="1";
		}
		return "updateSuccess";
	}
	
	/**
	 * 根据车号查询车上的轮对
	 * @return
	 */
	public  String getRecordsByJC(){
		List<WheelRecord> records=this.wheelRecordService.findWheelRecords(jcNum);
		JSONArray ja=new JSONArray();
		JSONObject jo=null;
		WheelRecord r=null;
		if(records!=null){
			for(int i=0;i<records.size();i++){
				r=records.get(i);
				jo=new JSONObject();
				jo.put("id", r.getId());
				jo.put("axleNum", r.getAxleNum());
				jo.put("position", r.getPosition());
				ja.add(jo);
			}
		}
		this.result=ja.toString();
		return "jclist";
	}
	
	/**
	 * 删除轮对履历记录
	 * @return
	 */
	public String deleteRecords(){
		return "deleteSuccess";
	}

	public WheelRecordManageService getWheelRecordService() {
		return wheelRecordService;
	}

	public void setWheelRecordService(WheelRecordManageService wheelRecordService) {
		this.wheelRecordService = wheelRecordService;
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
}
