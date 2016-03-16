package com.lundui.manage.stock.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.lundui.manage.common.service.BasicInfoService;
import com.lundui.manage.model.Depot;
import com.lundui.manage.model.FactoryRepair;
import com.lundui.manage.model.User;
import com.lundui.manage.stock.service.FactoryRepairManageService;
import com.lundui.manage.util.PageModel;

public class FactoryRepairAction {
	
	@Resource(name="factoryRepairManageService")
	private FactoryRepairManageService factoryRepairService;
	
	@Resource(name="basicInfoService")
	private BasicInfoService basicInfoService;
	
	private PageModel<FactoryRepair> pageModel;
	
	private FactoryRepair factoryRepair;
	
	private String depot;
	
	private String jcNum;
	
	private Date dateFrom;
	
	private Date dateTo;
	
	private String result;
	
	/**
	 * 列表
	 * @return
	 */
	public String list(){
		String depotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");
		if(depot==null||depot.equals("")){
			depot=depotCode;
		}
		this.pageModel = this.factoryRepairService.findFactoryRepair(jcNum, dateFrom, dateTo, depot);
		return "list";
	}
	
	public String toAdd(){
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ServletActionContext.getRequest().setAttribute("now", sdf.format(now));
		return "toAdd";
	}
	
	/**
	 * 添加
	 * @return
	 */
	public String addFactoryRepair(){
		User user=(User)ServletActionContext.getRequest().getSession().getAttribute("currentUser");
		String depotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");	
		Depot currentDepot=this.basicInfoService.getDepot(depotCode);
		factoryRepair.setDepotCode(depotCode);
		factoryRepair.setDepotName(currentDepot.getDepotName());
		factoryRepair.setHandlerUserNum(user.getJobNum());
		factoryRepair.setHandlerUserName(user.getUsername());
		factoryRepair=this.factoryRepairService.addFactoryRepair(user,currentDepot,factoryRepair);
		if(factoryRepair==null){
			result="0";
		}else{
			result="1";
		}
		return "addSuccess";
	}

	public PageModel<FactoryRepair> getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel<FactoryRepair> pageModel) {
		this.pageModel = pageModel;
	}

	public FactoryRepair getFactoryRepair() {
		return factoryRepair;
	}

	public void setFactoryRepair(FactoryRepair factoryRepair) {
		this.factoryRepair = factoryRepair;
	}

	public String getDepot() {
		return depot;
	}

	public void setDepot(String depot) {
		this.depot = depot;
	}

	public String getJcNum() {
		return jcNum;
	}

	public void setJcNum(String jcNum) {
		this.jcNum = jcNum;
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	

}
