package com.lundui.manage.system.service;

import java.util.List;

import com.lundui.manage.model.AxleType;
import com.lundui.manage.model.Depot;
import com.lundui.manage.model.WheelStock;
import com.lundui.manage.model.WheelType;
import com.lundui.manage.util.PageModel;

public interface WheelAlarmService {
	
	public PageModel<WheelStock> getAlarmInfo(String axleNum,String wheelType,String depotCode);
	
	public WheelStock updateAlarmInfo(String axleNum,String wheelType,String depotCode,Integer min,Integer maxbad);
	
	public List<AxleType> getAxleTypes();
	
	public List<WheelType> getWheelTypes();
	
	public List<Depot> getDepots();
	
	public List<WheelStock> getAlarm(String depotCode);
	
	public WheelStock getStock(Long id);
	
	public String getAlarmJSON(String depotCode);

}
