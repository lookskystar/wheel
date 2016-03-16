package com.lundui.manage.util;

import net.sf.json.JSONObject;

import com.lundui.manage.model.Depot;
import com.lundui.manage.model.WheelStock;

public class JSONUtil {
	
	public static JSONObject  putToJSON(WheelStock stock,int index){
		JSONObject jo=new JSONObject();
		jo.put("id", Integer.parseInt("1"+stock.getDepotCode())*100+stock.getId());
		jo.put("_id_", stock.getId());
		jo.put("axleType", stock.getAxleType());
		jo.put("wheelType", stock.getWheelType());
		jo.put("depotCode", stock.getDepotCode());
		jo.put("depotName", stock.getDepotName());
		jo.put("minStock", stock.getMinStock());
		jo.put("inventory", stock.getInventory());
		jo.put("maxBadStock", stock.getMaxBadStock());
		jo.put("notGoodNum", stock.getNotGoodNum());
		jo.put("goodNum", stock.getGoodNum());
		jo.put("_parentId",Integer.parseInt(stock.getDepotCode()));
		jo.put("reason", "库存不足");
		return jo;
	}
	
	public static JSONObject putDepotToJSON(Depot depot,boolean hasParent){
		JSONObject jo=new JSONObject();
		jo.put("depotCode", depot.getDepotCode());
		jo.put("depotName", depot.getDepotName());
		jo.put("id", Integer.parseInt(depot.getDepotCode()));
		if(hasParent){
			jo.put("_parentId",Integer.parseInt(depot.getParent().getDepotCode()));
		}
		return jo;
	}

}
