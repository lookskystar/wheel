package com.lundui.manage.stock.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.lundui.manage.common.service.BasicInfoService;
import com.lundui.manage.model.AxleType;
import com.lundui.manage.model.Depot;
import com.lundui.manage.model.WheelStock;
import com.lundui.manage.model.WheelType;
import com.lundui.manage.stock.service.WheelStockManageService;
import com.lundui.manage.util.JSONUtil;
import com.lundui.manage.util.PageModel;

/**
 * 轮对库存管理Action
 * @author Administrator
 *
 */
public class WheelStockManageAction {
	
	private WheelStock stock;
	
	private PageModel<WheelStock> pageModel;
	
	@Resource(name="wheelStockManageService")
	private WheelStockManageService service;
	
	@Resource(name="basicInfoService")
	private BasicInfoService basicInfoService;
	
	private List<AxleType> axleTypes;
	
	private List<WheelType> wheelTypes;
	
	//轮型
	private String wheelType;
	
	//轴型
	private String axleType;
	
	private String valueParam;
	
	private String depot;
	
	private String result;
	
	/**
	 * 轮对库存列表
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String stockList() throws UnsupportedEncodingException{
		axleTypes=basicInfoService.getAxleTypes();
		wheelTypes=basicInfoService.getWheelTypes();
		String depotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");
		if(depot==null||depot.equals("")){
			depot=depotCode;
		}
		ServletActionContext.getRequest().getSession().setAttribute("depots", basicInfoService.getDepotList(depotCode));
		pageModel=service.queryStocks(axleType, wheelType, depot);
		return "list";
	}
	
	public String simpleStockList() throws UnsupportedEncodingException{
		stockList();
		return "simpleList";
	}

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
	 * 获取下级Depot列表
	 * @return
	 */
	public String getSubDepot(){
		String depotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");
		if(depot==null||depot.equals("")){
			depot=depotCode;
		}
		List<Depot> depots=basicInfoService.getSubDepotList(depot);
		JSONArray ja=new JSONArray();
		Depot d=this.basicInfoService.getDepot(depot);
		if(d.getType()==3||d.getType()==4){
			List<WheelStock> stocks=service.queryStock(depot);
			for(int i=0;i<stocks.size();i++){
				ja.add(JSONUtil.putToJSON(stocks.get(i), i));
			}
		}else{
			if(showCurrent)ja.add(JSONUtil.putDepotToJSON(d, false));
			for(int i=0;i<depots.size();i++){
				d=depots.get(i);
				if(!showJX){
					if(d.getType()==4) continue;
				}
				if(depot.equals(d.getDepotCode())){
					ja.add(JSONUtil.putDepotToJSON(d, false));
				}else{
					ja.add(JSONUtil.putDepotToJSON(d, true));
				}
			}
		}
		this.result="{rows:"+ja.toString()+",total:"+ja.size()+"}";
		return "jsonList";
	}
	
	public String edit(){
		return "edit";
	}
	
	public String updateStock(){
		return "updateSuccess";
	}

	public WheelStock getStock() {
		return stock;
	}

	public void setStock(WheelStock stock) {
		this.stock = stock;
	}

	public PageModel<WheelStock> getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel<WheelStock> pageModel) {
		this.pageModel = pageModel;
	}

	public WheelStockManageService getService() {
		return service;
	}

	public void setService(WheelStockManageService service) {
		this.service = service;
	}

	public String getValueParam() {
		return valueParam;
	}

	public void setValueParam(String valueParam) {
		this.valueParam = valueParam;
	}

	public String getWheelType() {
		return wheelType;
	}

	public void setWheelType(String wheelType) {
		this.wheelType = wheelType;
	}

	public String getAxleType() {
		return axleType;
	}

	public void setAxleType(String axleType) {
		this.axleType = axleType;
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

	public BasicInfoService getBasicInfoService() {
		return basicInfoService;
	}

	public void setBasicInfoService(BasicInfoService basicInfoService) {
		this.basicInfoService = basicInfoService;
	}

	public String getDepot() {
		return depot;
	}

	public void setDepot(String depot) {
		this.depot = depot;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
