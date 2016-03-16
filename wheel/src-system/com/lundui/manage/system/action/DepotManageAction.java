package com.lundui.manage.system.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.lundui.manage.model.Depot;
import com.lundui.manage.system.service.DepotManageService;

/**
 * 车间管理
 * @author Administrator
 *
 */
public class DepotManageAction {
	
	private DepotManageService service;
	
	private Map<Depot, List<Depot>> depots;
	
	private Depot depot;
	
	private String result;
	
	/**
	 * 查询所有
	 * @return
	 */
	public String listAll(){
		depots=service.getDepotList();
		return "list";
	}
	
	/**
	 * 添加
	 * @return
	 */
	public String toAdd(){
		depot=service.getDepot(depot);
		return "toAdd";
	}
	
	public String toEditDepot(){
		depot=service.getDepot(depot);
		return "toEditDepot";
	}
	
	/**
	 * 添加
	 * @return
	 */
	public String addDepot(){
		depot=service.addDepot(depot);
		if(depot==null){
			this.result="0";
		}else{
			result="1";
		}
		return "addSuccess";
	}
	
	public String toEdit(){
		depot=service.getDepot(depot);
		return "toEdit";
	}
	
	/**
	 * 更新
	 * @return
	 */
	public String updateDepot(){
		depot=service.updateDepot(depot);
		if(depot==null){
			result="0";
		}else{
			result="1";
		}
		return "updateSuccess";
	}
	
	/**
	 * 删除车间
	 * @return
	 */
	public String deleteDepot(){
		service.deleteDepot(depot);
		result="1";
		return "deleteSuccess";
	}
	
	
	/**
	 * 删除段(厂)
	 * @return
	 */
	public String deleteSubDepot(){
		service.deleteSubDepot(depot);
		result="1";
		return "deleteSuccess";
	}
	
	/**
	 * 查找该段(厂)下是否存在车间
	 */
	public String countDepot() {
		long count=service.countDepot(depot.getId());
		HttpServletResponse response=ServletActionContext.getResponse();
		String date="false";
		if(count==0){
			service.deleteSubDepot(depot);
			date="success";
		}
		response.setContentType("text/plain");
		try {
			response.getWriter().write(date);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * 查找该车间下是否存在用户
	 */
	public String countUser() {
		long count=service.countUser(depot.getId());
		HttpServletResponse response=ServletActionContext.getResponse();
		String date="false";
		if(count==0){
			service.deleteDepot(depot);
			date="success";
		}
		response.setContentType("text/plain");
		try {
			response.getWriter().write(date);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	

	public DepotManageService getService() {
		return service;
	}

	public void setService(DepotManageService service) {
		this.service = service;
	}

	public Map<Depot, List<Depot>> getDepots() {
		return depots;
	}

	public void setDepots(Map<Depot, List<Depot>> depots) {
		this.depots = depots;
	}

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	

}
