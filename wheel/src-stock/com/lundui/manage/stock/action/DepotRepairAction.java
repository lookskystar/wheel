package com.lundui.manage.stock.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.lundui.manage.common.service.BasicInfoService;
import com.lundui.manage.model.Depot;
import com.lundui.manage.model.DepotRepair;
import com.lundui.manage.model.User;
import com.lundui.manage.stock.service.DepotRepairManageService;
import com.lundui.manage.util.PageModel;

/**
 * 段修
 * @author Administrator
 *
 */
public class DepotRepairAction {
	
	@Resource(name="depotRepairManageService")
	private DepotRepairManageService depotRepairService ;
	
	@Resource(name="basicInfoService")
	private BasicInfoService basicInfoService;
	
	private PageModel<DepotRepair> pageModel;
	
	private DepotRepair depotRepair;
	
	private String depot;
	
	private String jcNum;
	
	private Date dateFrom;
	
	private Date dateTo;
	
	private String result;
	
	/**
	 * 获取段修列表
	 * @return
	 */
	public String getDepotRepairList(){
		String depotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");
		if(depot==null||depot.equals("")){
			depot=depotCode;
		}
		this.pageModel = this.depotRepairService.findDepotRepairs(jcNum, dateFrom, dateTo, depot);
		return "list";
	}
	
	public String addDepotRepair(){
		User user=(User)ServletActionContext.getRequest().getSession().getAttribute("currentUser");
		String depotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");	
		Depot currentDepot=this.basicInfoService.getDepot(depotCode);
		depotRepair.setDepotCode(depotCode);
		depotRepair.setDepotName(currentDepot.getDepotName());
		depotRepair.setHandlerUserNum(user.getJobNum());
		depotRepair.setHandlerUserName(user.getUsername());
		depotRepair=this.depotRepairService.addDepotRepair(user,currentDepot,depotRepair);
		if(depotRepair==null){
			result="0";
		}else{
			result="1";
		}
		return "addSuccess";
	}
	
	public String toAdd(){
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ServletActionContext.getRequest().setAttribute("now", sdf.format(now));
		return "toAdd";
	}

	public PageModel<DepotRepair> getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel<DepotRepair> pageModel) {
		this.pageModel = pageModel;
	}

	public DepotRepair getDepotRepair() {
		return depotRepair;
	}

	public void setDepotRepair(DepotRepair depotRepair) {
		this.depotRepair = depotRepair;
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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

}
