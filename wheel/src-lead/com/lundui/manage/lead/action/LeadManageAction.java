package com.lundui.manage.lead.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.lundui.manage.common.service.BasicInfoService;
import com.lundui.manage.lead.service.LeadManageService;
import com.lundui.manage.model.Depot;
import com.lundui.manage.model.WheelStock;
import com.lundui.manage.stock.service.TempRepairManageService;
import com.lundui.manage.stock.service.WheelStockManageService;
import com.lundui.manage.util.JSONUtil;
import com.lundui.manage.util.PageModel;

public class LeadManageAction {
	
	private LeadManageService service;
	
	@Resource(name="tempRepairManageService")
	private TempRepairManageService tempRepairService;
	
	@Resource(name="basicInfoService")
	private BasicInfoService basicInfoService;
	
	@Resource(name="wheelStockManageService")
	private WheelStockManageService stockService;
	
	private Date dateFrom;
	
	private Date dateTo;
	
	private String depot;
	
	private Date currentDate;
	
	private  List<Object>  incomeReport;
	
	private Map<String,Map<String,Integer>> outlayReport;
	
	@SuppressWarnings("rawtypes")
	private PageModel pageModel;
	
	private Integer show=0;
	
	private String result;
	
	//是否显示检修单位信息
	private Boolean showJX=false;
	
	private Boolean showCurrent=true;
	
	
	public Boolean getShowJX() {
		return showJX;
	}

	public void setShowJX(Boolean showJX) {
		this.showJX = showJX;
	}

	public Boolean getShowCurrent() {
		return showCurrent;
	}

	public void setShowCurrent(Boolean showCurrent) {
		this.showCurrent = showCurrent;
	}
	
	/**
	 * 统计
	 */
	public int[] getDepotCount(Depot d){
		int countAll=0,countGood=0, countBad=0;
		if(d.getType()==4||d.getType()==3){//车间
			List<WheelStock> stocks=stockService.queryStock(d.getDepotCode());
			WheelStock stock=null;
			for(int i=0;i<stocks.size();i++){
				stock=stocks.get(i);
				countAll+=stock.getInventory();
				countGood+=stock.getGoodNum();
				countBad+=stock.getNotGoodNum();
			}
		}else{
			List<Depot> depots=basicInfoService.getSubDepotList(d.getDepotCode());
			int[] cc=null;
			for(int i=0;i<depots.size();i++){
				cc=getDepotCount(depots.get(i));
				countAll+=cc[0];
				countGood+=cc[1];
				countBad+=cc[2];
			}
		}
		return new int[]{countAll,countGood,countBad};
	}
	
	public String getSubDepot(){
		String depotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");
		if(depot==null||depot.equals("")){
			depot=depotCode;
		}
		List<Depot> depots=basicInfoService.getSubDepotList(depot);
		JSONArray ja=new JSONArray();
		JSONObject jo=null;
		int[] counts=null;
		Depot d=this.basicInfoService.getDepot(depot);
		if(d.getType()==3||d.getType()==4){//检修单位和运用单位的下级菜单就是库存列表
			List<WheelStock> stocks=stockService.queryStock(depot);
			for(int i=0;i<stocks.size();i++){
				ja.add(JSONUtil.putToJSON(stocks.get(i), i));
			}
		}else{
			if(showCurrent){
				jo=JSONUtil.putDepotToJSON(d, false);
				counts=this.getDepotCount(d);
				jo.put("inventory", counts[0]);
				jo.put("goodNum", counts[1]);
				jo.put("notGoodNum", counts[2]);
				ja.add(jo);
			}
			for(int i=0;i<depots.size();i++){
				d=depots.get(i);
				if(!showJX){
					if(d.getType()==4) continue;
				}
				if(depot.equals(d.getDepotCode())){
					jo=JSONUtil.putDepotToJSON(d, false);
				}else{
					jo=JSONUtil.putDepotToJSON(d, true);
				}
				counts=this.getDepotCount(d);
				jo.put("inventory", counts[0]);
				jo.put("goodNum", counts[1]);
				jo.put("notGoodNum", counts[2]);
				ja.add(jo);
			}
		}
		this.result="{rows:"+ja.toString()+",total:"+ja.size()+"}";
		return "jsonList";
	}
	
	/**
	 * 获取库存的JSON格式数据
	 * @return
	 */
	public String getReportJSON(){
		String depotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");
		List<Depot> depots= basicInfoService.getDepotList(depotCode);
		List<WheelStock> list=service.findSubStocksByDepot(depotCode);
		
		JSONArray ja=new JSONArray();
		JSONObject r=new JSONObject();
		Depot depot=null;
		WheelStock stock=null;
		
		for(int i=0;i<depots.size();i++){
			depot=depots.get(i);
			if(depot.getDepotCode().equals(depotCode)){
				ja.add(JSONUtil.putDepotToJSON(depot,false));
			}else{
				ja.add(JSONUtil.putDepotToJSON(depot,true));
			}
		}
		for(int i=0;i<list.size();i++){
			stock=list.get(i);
			ja.add(JSONUtil.putToJSON(stock,i));
		}
		r.put("total", depots.size()+list.size());
		r.put("rows", ja);
		//System.out.println(ja.toString());
		result=r.toString();
		return "repotjson";
	}
	
	/**
	 * 查看报表
	 * @return
	 */
	public String getReport(){
		return "treeList";
		/*
		String depotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");
		if(depot==null||depot.equals("")){
			depot=depotCode;
		}
		Depot dep=basicInfoService.getDepot(depot);
		if(dep.getType()==1){//集团
			ServletActionContext.getRequest().getSession().setAttribute("depots", basicInfoService.getSubDepotList(dep.getDepotCode()));
			return "depotList";
		}else if(dep.getType()==2){//车辆厂、车辆段
			List<Depot> depots= basicInfoService.getSubDepotList(dep.getDepotCode());
			if(depots.size()!=0&&show==0){//有下级
				List<Object[]> report=new ArrayList<Object[]>();
				Depot subDepot=null;
				report.add(new Object[]{dep.getDepotName(),dep.getDepotCode(),service.findStockByDepot(dep.getDepotCode())});
				ServletActionContext.getRequest().getSession().setAttribute("depots", basicInfoService.getSubDepotList(dep.getDepotCode()));
				for(int i=0;i<depots.size();i++){
					subDepot=depots.get(i);
					report.add(new Object[]{subDepot.getDepotName(),subDepot.getDepotCode(),service.findStockByDepot(subDepot.getDepotCode())});
				}
				ServletActionContext.getRequest().getSession().setAttribute("reportInfo",report);
				return "stockreport";
			}else{//没有下级
				pageModel=service.findStockPageByDepot(depot);
				return "stockinfo";
			}
		}else{
			pageModel=service.findStockPageByDepot(depot);
			return "stockinfo";
		}*/
		/*
		Calendar current=Calendar.getInstance();
		if(dateFrom==null){//如果没有输入日期，查询本月信息
			Calendar start=Calendar.getInstance();
			start.set(current.get(Calendar.YEAR), current.get(Calendar.MONTH), 1, 0, 0,0);
			Calendar end=Calendar.getInstance();
			end.set(current.get(Calendar.YEAR), current.get(Calendar.MONTH), 31, 24, 59,59);
			dateTo=end.getTime();
			dateFrom=start.getTime();
		}
		currentDate=current.getTime();
		incomeReport=service.findIncomeRecordByMonth(dateFrom, dateTo,dep);
		//outlayReport=service.findOutlayReportByMonth(dateFrom, dateTo,null);
		return "report";*/
	}
	
	/**
	 * 临修故障统计
	 * @return
	 */
	public String getTempRepair(){
		String depotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");
		if(depot==null||depot.equals("")){
			depot=depotCode;
		}
		ServletActionContext.getRequest().getSession().setAttribute("depots", basicInfoService.getDepotList(depotCode));
		this.pageModel=this.tempRepairService.findTemporaryRepair(depot, dateFrom, dateTo);
		return "tempRepairReport";
	}

	public LeadManageService getService() {
		return service;
	}

	public void setService(LeadManageService service) {
		this.service = service;
	}

	public List<Object> getIncomeReport() {
		return incomeReport;
	}

	public void setIncomeReport(List<Object> incomeReport) {
		this.incomeReport = incomeReport;
	}

	public Map<String, Map<String, Integer>> getOutlayReport() {
		return outlayReport;
	}

	public void setOutlayReport(Map<String, Map<String, Integer>> outlayReport) {
		this.outlayReport = outlayReport;
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

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public TempRepairManageService getTempRepairService() {
		return tempRepairService;
	}

	public void setTempRepairService(TempRepairManageService tempRepairService) {
		this.tempRepairService = tempRepairService;
	}

	@SuppressWarnings("rawtypes")
	public PageModel getPageModel() {
		return pageModel;
	}

	@SuppressWarnings("rawtypes")
	public void setPageModel( PageModel pageModel) {
		this.pageModel = pageModel;
	}

	public String getDepot() {
		return depot;
	}

	public void setDepot(String depot) {
		this.depot = depot;
	}

	public BasicInfoService getBasicInfoService() {
		return basicInfoService;
	}

	public void setBasicInfoService(BasicInfoService basicInfoService) {
		this.basicInfoService = basicInfoService;
	}

	public Integer getShow() {
		return show;
	}

	public void setShow(Integer show) {
		this.show = show;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	

}
