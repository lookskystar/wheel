package com.lundui.manage.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.lundui.manage.stock.service.IncomeManageService;
import com.lundui.manage.stock.service.OutlayManageService;
import com.lundui.manage.stock.service.WheelRecordManageService;
import com.lundui.manage.stock.service.WheelStockManageService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 导出轮对信息
 * @author long
 *
 */
public class WheelDownloadAction extends ActionSupport {

	private static final long serialVersionUID = -5557251246732438610L;

	@Resource(name="wheelRecordManageService")
	private WheelRecordManageService wheelRecordService;
	
	@Resource(name="incomeManageService")
	private IncomeManageService incomeManageService;
	
	@Resource(name="outlayManageService")
	private OutlayManageService outlayManageService;
	
	private String fileName;
	
	private String inputPath;
	
	private String ids;
	
	private String axleNum;
	
	private String axleType;
	
	private String wheelType;
	
	private String depotCode;
	
	private String factory;
	
	private Date createDateFrom;
	
	private Date createDateTO;
	
	private Short status;
	
	private Short whereabouts;
	
	private Boolean sub=true;
	
	public InputStream getDownloadFile(){
		if(ids!=null){
			String[] idstr=ids.split(",");
			Long[] idl=new Long[idstr.length];
			for(int i=0;i<idstr.length;i++){
				idl[i]=Long.parseLong(idstr[i]);	
			}
			return wheelRecordService.getExcelFilebyIds(idl);
		}else{
			return wheelRecordService.getExcelFile(axleNum, axleType, wheelType, depotCode, factory, createDateFrom, createDateTO, status, whereabouts, sub);
		}
		
	}
	
	 @Override  
    public String execute() throws Exception  
    {  
        return SUCCESS;  
    }  
	
	public String getFileName() {
		Calendar cal=Calendar.getInstance();
		int year=cal.get(Calendar.YEAR);
		int month=cal.get(Calendar.MONTH)+1;
		int day=cal.get(Calendar.DATE);
		return "lundui"+String.format("%d-%d-%d.xls", year,month,day);
		//return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public WheelRecordManageService getWheelRecordService() {
		return wheelRecordService;
	}

	public void setWheelRecordService(WheelRecordManageService wheelRecordService) {
		this.wheelRecordService = wheelRecordService;
	}

	public IncomeManageService getIncomeManageService() {
		return incomeManageService;
	}

	public void setIncomeManageService(IncomeManageService incomeManageService) {
		this.incomeManageService = incomeManageService;
	}

	public OutlayManageService getOutlayManageService() {
		return outlayManageService;
	}

	public void setOutlayManageService(OutlayManageService outlayManageService) {
		this.outlayManageService = outlayManageService;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getAxleNum() {
		return axleNum;
	}

	public void setAxleNum(String axleNum) {
		this.axleNum = axleNum;
	}

	public String getAxleType() {
		return axleType;
	}

	public void setAxleType(String axleType) {
		this.axleType = axleType;
	}

	public String getWheelType() {
		return wheelType;
	}

	public void setWheelType(String wheelType) {
		this.wheelType = wheelType;
	}

	public String getDepotCode() {
		return depotCode;
	}

	public void setDepotCode(String depotCode) {
		this.depotCode = depotCode;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public Date getCreateDateFrom() {
		return createDateFrom;
	}

	public void setCreateDateFrom(Date createDateFrom) {
		this.createDateFrom = createDateFrom;
	}

	public Date getCreateDateTO() {
		return createDateTO;
	}

	public void setCreateDateTO(Date createDateTO) {
		this.createDateTO = createDateTO;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Short getWhereabouts() {
		return whereabouts;
	}

	public void setWhereabouts(Short whereabouts) {
		this.whereabouts = whereabouts;
	}

	public Boolean getSub() {
		return sub;
	}

	public void setSub(Boolean sub) {
		this.sub = sub;
	}

	public String getInputPath() {
		return inputPath;
	}
}
