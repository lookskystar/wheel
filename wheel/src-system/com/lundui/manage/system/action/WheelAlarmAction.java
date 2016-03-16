package com.lundui.manage.system.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.lundui.manage.common.service.BasicInfoService;
import com.lundui.manage.model.AxleType;
import com.lundui.manage.model.Depot;
import com.lundui.manage.model.WheelStock;
import com.lundui.manage.model.WheelType;
import com.lundui.manage.system.service.WheelAlarmService;
import com.lundui.manage.util.PageModel;

/**
 * 轮对库存报警
 * @author Administrator
 *
 */
public class WheelAlarmAction {
	
	private WheelAlarmService service;
	
	@Resource(name="basicInfoService")
	private BasicInfoService basicInfoService;
	
	private PageModel<WheelStock> pageModel;
	
	private List<AxleType> axleTypes;
	
	private List<WheelType> wheelTypes;
	
	private List<Depot> depots;
	
	private List<WheelStock> alarms;
	
	private WheelStock stock;
	
	//查询
	private String axleNum;
	
	private String wheelNum;
	
	private String depotCode;
	
	private Integer minStock;
	
	private Integer maxBadStock;
	
	private String result;
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String  getAlermList(){
		this.axleTypes=service.getAxleTypes();
		this.wheelTypes=service.getWheelTypes();
		String curDepotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");
		this.depots=basicInfoService.getYYDepotList(curDepotCode);
		this.pageModel=service.getAlarmInfo(axleNum,wheelNum,depotCode);
		return "list";
	}
	
	public String toAdd(){
		this.axleTypes=service.getAxleTypes();
		this.wheelTypes=service.getWheelTypes();
		String curDepotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");
		this.depots=basicInfoService.getYYDepotList(curDepotCode);
		return "toAdd";
	}
	
	public String edit(){
		this.axleTypes=service.getAxleTypes();
		this.wheelTypes=service.getWheelTypes();
		String curDepotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");
		this.depots=basicInfoService.getYYDepotList(curDepotCode);
		stock=this.service.getStock(stock.getId());
		return "toAdd";
	}
	
	/**
	 * 更新设置
	 * @return
	 */
	public String update(){
		WheelStock stock=this.service.updateAlarmInfo(axleNum, wheelNum, depotCode, minStock, maxBadStock);
		if(stock!=null){
			result="1";
		}else{
			result="0";
		}
		return "updateSuccess";
	}
	
	public String showAlarm(){
		String depotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");
		alarms=this.service.getAlarm(depotCode);
		return "showAlarm";
	}
	
	public String showJSONAlarm(){
		String depotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");
		result=this.service.getAlarmJSON(depotCode);
		return "showJSON";
	}
	
	public String getAlarmAsJson(){
		return "";
	}

	public WheelAlarmService getService() {
		return service;
	}

	public void setService(WheelAlarmService service) {
		this.service = service;
	}

	public PageModel<WheelStock> getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel<WheelStock> pageModel) {
		this.pageModel = pageModel;
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

	public List<Depot> getDepots() {
		return depots;
	}

	public void setDepots(List<Depot> depots) {
		this.depots = depots;
	}

	public String getAxleNum() {
		return axleNum;
	}

	public void setAxleNum(String axleNum) {
		this.axleNum = axleNum;
	}

	public String getWheelNum() {
		return wheelNum;
	}

	public void setWheelNum(String wheelNum) {
		this.wheelNum = wheelNum;
	}

	public String getDepotCode() {
		return depotCode;
	}

	public void setDepotCode(String depotCode) {
		this.depotCode = depotCode;
	}

	public Integer getMinStock() {
		return minStock;
	}

	public void setMinStock(Integer minStock) {
		this.minStock = minStock;
	}

	public Integer getMaxBadStock() {
		return maxBadStock;
	}

	public void setMaxBadStock(Integer maxBadStock) {
		this.maxBadStock = maxBadStock;
	}

	public WheelStock getStock() {
		return stock;
	}

	public void setStock(WheelStock stock) {
		this.stock = stock;
	}

	public List<WheelStock> getAlarms() {
		return alarms;
	}

	public void setAlarms(List<WheelStock> alarms) {
		this.alarms = alarms;
	}
	
	

}
