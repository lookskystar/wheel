package com.lundui.manage.stock.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import sun.org.mozilla.javascript.internal.Undefined;

import com.lundui.manage.common.dao.AxleTypeDao;
import com.lundui.manage.common.dao.WheelTypeDao;
import com.lundui.manage.common.dao.ZXTypeDao;
import com.lundui.manage.model.AxleType;
import com.lundui.manage.model.Depot;
import com.lundui.manage.model.Income;
import com.lundui.manage.model.WheelRec;
import com.lundui.manage.model.WheelRecord;
import com.lundui.manage.model.WheelType;
import com.lundui.manage.model.ZXType;
import com.lundui.manage.stock.dao.OutlayDao;
import com.lundui.manage.stock.dao.WheelRecDao;
import com.lundui.manage.stock.dao.WheelRecordDao;
import com.lundui.manage.stock.service.WheelRecordManageService;
import com.lundui.manage.stock.service.WheelStockManageService;
import com.lundui.manage.system.dao.DepotDao;
import com.lundui.manage.util.ExcelReader;
import com.lundui.manage.util.PageModel;

public class WheelRecordManageServiceImpl implements WheelRecordManageService{
	
	@Resource(name="wheelRecordDao")
	private WheelRecordDao wheelRecordDao;
	
	@Resource(name="wheelRecDao")
	private WheelRecDao wheelRecDao;
	
	@Resource(name="outlayDao")
	private OutlayDao outlayDao;
	
	@Resource(name="depotDao")
	private DepotDao depotDao;
	
	@Resource(name="axleTypeDao")
	private AxleTypeDao axleTypeDao;
	
	@Resource(name="wheelTypeDao")
	private WheelTypeDao wheelTypeDao;
	
	@Resource(name="zxTypeDao")
	private ZXTypeDao zxTypeDao;
	
	@Resource(name="wheelStockManageService")
	private WheelStockManageService stockService;
	
	@Override
	public WheelRecord addWheelRecord(WheelRecord record) {
		return wheelRecordDao.saveEntity(record);
	}

	@Override
	public void deleteWheelRecord(WheelRecord record) {
		wheelRecordDao.deleteEntity(record);
	}

	@Override
	public WheelRecord updateWheelRecord(WheelRecord record) {
		return wheelRecordDao.updateEntity(record);
	}

	public WheelRecordDao getWheelRecordDao() {
		return wheelRecordDao;
	}

	public void setWheelRecordDao(WheelRecordDao wheelRecordDao) {
		this.wheelRecordDao = wheelRecordDao;
	}


	@Override
	public PageModel<WheelRecord> queryWheelRecordList(String depotCode) {
		Criterion[] cirts=new Criterion[]{Restrictions.like("depotCode", depotCode+"%")};
		return wheelRecordDao.findPageModel(cirts);
	}

	@Override
	public WheelRecord getRecord(Long id) {
		return wheelRecordDao.getEntityById(id);
	}

	@Override
	public PageModel<WheelRecord> queryWheelRecordList(String queryName,
			String value, Date dataFrom, Date dateTo, Short status,String depotCode) {
		Criterion crit=null;
		if(queryName.equals("status")){
			crit=Restrictions.eq(queryName, status);
		}else if(queryName.equals("lastBuildTime")){
			crit=Restrictions.between(queryName, dataFrom, dateTo);
		}else{
			crit=Restrictions.like(queryName, "%"+value+"%");
		}
		Criterion[] cirts=new Criterion[2];
		cirts[0]=crit;
		cirts[1]=Restrictions.eq("depotCode", depotCode);
		return wheelRecordDao.findPageModel(cirts);
	}

	@Override
	public PageModel<WheelRecord> queryWheelRecodList(String axleNum,
			String axleType, String wheelType, String depotCode,
			String factory, Date createDateFrom, Date createDateTO,
			Short status, Short whereabouts,boolean sub) {
		List<Criterion> crits=new ArrayList<Criterion>();
		if(axleNum!=null&&!axleNum.equals("")){
			crits.add(Restrictions.like("axleNum", "%"+axleNum+"%"));
//			String axleNums = axleNum.replaceAll("\\,|\\，", ",");
//			crits.add(Restrictions.in("axleNum", axleNums.split(",")));
		}
		if(axleType!=null&&!axleType.equals("")){
			crits.add(Restrictions.eq("axleType", axleType));
		}
		if(wheelType!=null&&!wheelType.equals("")){
			crits.add(Restrictions.eq("wheelTypeName", wheelType));
		}
		if(depotCode!=null&&!depotCode.equals("")){
			if(sub){//包含下级
				crits.add(Restrictions.like("depotCode", depotCode+"%"));
			}else{
				crits.add(Restrictions.eq("depotCode", depotCode));
			}
		}
		if(factory!=null&&!factory.equals("")){
			crits.add(Restrictions.like("factory", "%"+factory+"%"));
		}
		if(createDateFrom!=null){
			crits.add(Restrictions.ge("axleCreateDate", createDateFrom));
		}
		if(createDateTO!=null){
			crits.add(Restrictions.le("axleCreateDate", createDateTO));
		}
		if(status!=null){
			crits.add(Restrictions.eq("status", status));
		}
		if(whereabouts!=null){
			crits.add(Restrictions.eq("whereabouts", whereabouts));
		}
		return wheelRecordDao.findPageModel(crits,Order.desc("axleType"));
	}

	@Override
	public PageModel<WheelRec> queryWheelRecList(String axleNum,
			String oldaxleNum, String userName, String depotCode,Date beginTime, Date endTime,boolean sub) {
		List<Criterion> crits=new ArrayList<Criterion>();
		if(axleNum!=null&&!axleNum.equals("")){
			String axleNums = axleNum.replaceAll("\\,|\\，", ",");
			crits.add(Restrictions.in("axleNum", axleNums.split(",")));
		}
		if(oldaxleNum!=null&&!oldaxleNum.equals("")){
			String oldaxleNums = oldaxleNum.replaceAll("\\,|\\，", ",");
			crits.add(Restrictions.in("oldaxleNum", oldaxleNums.split(",")));
		}
		if(userName!=null&&!userName.equals("")){
			crits.add(Restrictions.like("userName", "%"+userName+"%"));
		}
		if(depotCode!=null&&!depotCode.equals("")){
			if(sub){//包含下级
				crits.add(Restrictions.like("depotCode", depotCode+"%"));
			}else{
				crits.add(Restrictions.eq("depotCode", depotCode));
			}
		}
		if(beginTime!=null){
			crits.add(Restrictions.ge("userTime", beginTime));
		}
		if(endTime!=null){
			crits.add(Restrictions.le("userTime", endTime));
		}
		return wheelRecDao.findPageModel(crits,Order.desc("userTime"));
	}
	
	
	@Override
	public PageModel<WheelRecord> queryElectiveWheelRecodList(String recordId,
			String axleNum, String axleType, String wheelType, String depotCode,
			String factory) {
		List<Criterion> crits=new ArrayList<Criterion>();
		if(axleNum!=null&&!axleNum.equals("")){
			crits.add(Restrictions.eq("axleNum", axleNum));
		}
		if(axleType!=null&&!axleType.equals("")){
			crits.add(Restrictions.eq("axleType", axleType));
		}
		if(wheelType!=null&&!wheelType.equals("")){
			crits.add(Restrictions.eq("wheelTypeName", wheelType));
		}
		if(depotCode!=null&&!depotCode.equals("")){
			crits.add(Restrictions.like("depotCode", depotCode+"%"));
		}
		if(factory!=null&&!factory.equals("")){
			crits.add(Restrictions.like("factory", "%"+factory+"%"));
		}
		crits.add(Restrictions.eq("status", (short)0)); //状态为良好
		crits.add(Restrictions.ne("id", recordId)); //不包含当前轮对
		return wheelRecordDao.findPageModel(crits);
	}
	

	public Date getDate(String value){
		SimpleDateFormat fomater=null;
    	if(value!=null&&!value.equals("")){
    		if(value.indexOf("-")!=-1){
    			fomater=new SimpleDateFormat("yyyy-MM-dd");
    		}else if(value.indexOf("#")!=-1){
    			fomater=new SimpleDateFormat("yyyy#MM#dd");
    		}else if(value.indexOf("年")!=-1){
    			fomater=new SimpleDateFormat("yyyy年MM月dd日");
    		}
    		if(fomater!=null){
    			try {
					return fomater.parse(value);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    	return null;
	}
	
	public int saveFromExcel(File file){
		InputStream is=null;
		InputStream is2=null;
		int result = 0;
		try {
			is = new FileInputStream(file);
			is2 = new FileInputStream(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ExcelReader excelReader = new ExcelReader();
        String[] title = excelReader.readExcelTitle(is);
        Map<Integer, Map<Integer,String>> content = excelReader.readExcelContent(is2);
        WheelRecord record=null;
        String value=null;
        Map<Integer,String> map=null;
        boolean flag;
        for(Map.Entry<Integer,  Map<Integer,String>> entry:content.entrySet()){//循环每条记录
        	map=entry.getValue();
        	record=new WheelRecord();
        	String s=null;
        	flag = true;
        	for(int i=0;i<title.length;i++){  //循环每个字段
        		s=title[i].trim();
        		value=map.get(i);
        		if(s.equals("轴号")){
        			if(value==null||value.equals("")) {
        				flag = false;
        				break;
        			}
        			if(value.contains(".")){
        				value = value.substring(0, value.lastIndexOf("."));
        			}
        			record.setAxleNum(value);
        		}else if(s.equals("轴型")){
        			if(value==null||value.equals("")){
        				flag = false;
        				break;
        			}
        			AxleType type=this.axleTypeDao.findAxleTypeByName(value);
        			if(type==null){
        				type=new AxleType();
        				type.setAxleNum(value);
        				this.axleTypeDao.saveEntity(type);
        			}
        			record.setAxleType(value);
        		}else if(s.equals("轮型")){
        			if(value==null||value.equals("")){ 
        				flag = false;
        				break;
        			}
        			WheelType type=this.wheelTypeDao.findWheelTypeByName(value);
        			if(type==null){
        				type=new WheelType();
        				type.setWheelNum(value);
        				this.wheelTypeDao.saveEntity(type);
        			}
        			record.setWheelTypeName(value);
        		}else if(s.equals("轴箱型号")){
        			ZXType type=this.zxTypeDao.findZXTypeByName(value);
        			if(type==null){
        				type=new ZXType();
        				type.setTypeName(value);
        				this.zxTypeDao.saveEntity(type);
        			}
        			record.setAxleBoxType(value);
        		}else if(s.equals("是否有轴箱接地装置")){
        			if(value.equals("是")){
        				record.setHasAxleBoxRelay((short)1);
        			}else{
        				record.setHasAxleBoxRelay((short)0);
        			}
        		}else if(s.equals("是否有防滑器")){
        			if(value.equals("是")){
        				record.setHasAntiSkid((short)1);
        			}else{
        				record.setHasAntiSkid((short)0);
        			}
        		}else if(s.equals("防滑器的齿轮尺寸")){
        			record.setAntiSkidSize(value);
        		}else if(s.equals("防滑器齿轮位置 ")){
        			record.setAntiSkidLoc(value);
        		}else if(s.equals("车轴制造厂")){
        			record.setFactory(value);
        		}else if(s.equals("轮对组装日期")){
        			record.setCreateDate(getDate(value));
        		}else if(s.equals("轮辋厚(左端)")){
        			if(value!=null&&!value.equals("")){
        				record.setLeftRimThickness(Double.parseDouble(value));
        			}
        		}else if(s.equals("轮辋厚(右端)")){
        			if(value!=null&&!value.equals("")){
        				record.setRightRimThickness(Double.parseDouble(value));
        			}
        		}else if(s.equals("踏面圆周磨耗(左端)")){
        			if(value!=null&&!value.equals("")){
        				record.setLeftCircularWear(Double.parseDouble(value));
        			}
        		}else if(s.equals("踏面圆周磨耗(右端)")){
        			if(value!=null&&!value.equals("")){
        				record.setRightCircularWear(Double.parseDouble(value));
        			}
        		}else if(s.equals("轮径(左端)")){
        			if(value!=null&&!value.equals("")){
        				record.setLeftDiameter(Double.parseDouble(value));
        			}
        		}else if(s.equals("轮径(右端)")){
            		if(value!=null&&!value.equals("")){
            			record.setRightDiamter(Double.parseDouble(value));
            		}
        		}else if(s.equals("轮缘厚(左端)")){
            		if(value!=null&&!value.equals("")){
            			record.setLeftFlangeThickness(Double.parseDouble(value));
            		}
        		}else if(s.equals("制动盘磨耗(左端)")){
        			if(value!=null&&!value.equals("")){
        				record.setLeftBrakeDiscWear(Double.parseDouble(value));
        			}
        		}else if(s.equals("制动盘磨耗(右端)")){
        			if(value!=null&&!value.equals("")){
        				record.setRightBrakeDiscWear(Double.parseDouble(value));
        			}
        		}else if(s.equals("轮缘厚(右端)")){
        			if(value!=null&&!value.equals("")){
        				record.setRightFlangeThickness(Double.parseDouble(value));
        			}
        		}else if(s.equals("轮对内侧距离")){
	            	if(value!=null&&!value.equals("")){
	            		record.setInsideistance(Double.parseDouble(value));
	            	}
        		}else if(s.equals("车轮制造时间")){
        			record.setWheelCreateDate(getDate(value));
        		}else if(s.equals("车轴制造时间")){
        			record.setBoxCreateDate(getDate(value));
        		}else if(s.equals("状态")){
        			if(value.equals("良好")||value.equals("0")){
        				record.setStatus((short)0);
        			}else if(value.equals("不良")||value.equals("1")){
        				record.setStatus((short)1);
        			}else if(value.equals("报废")||value.equals("2")){
        				record.setStatus((short)2);
        			}
        		}else if(s.equals("收入单位")){
        			record.setDepotName(value);
        			Depot depot=depotDao.getDepotByName(value);
        			if(depot!=null){
        				record.setDepotCode(depot.getDepotCode());
        			}
        		}else if(s.equals("位置")){
        			if(value.equals("库存中")){
        				record.setWhereabouts((short)0);
        			}else if(value.equals("已上车")){
        				record.setWhereabouts((short)1);
        			}else{//其他
        				record.setWhereabouts((short)3);
        			}
        		}else if(s.equals("上车号")){
        			record.setJcNum(value);
        		}else if(s.equals("上车位")){
        			if(value!=null && !"".equals(value)){
        				record.setPosition(value);
        			}
        		}else if(s.equals("轮轴组装日期")){
        			record.setAxleCreateDate(getDate(value));
        		}
        	//this.wheelRecordDao.saveEntity(record);
        	}
        	//循环完一条记录的所有字段，开始保存记录
        	try{
	        	if(flag){  //判断记录是否完整，若完整则保存记录
		        	if(record.getStatus()==null){//状态默认良好
		        		record.setStatus((short)0);
		        	}
		        	if(record.getWhereabouts()==0){//库存中
		        		this.stockService.addStockRecord(record.getAxleType(), record.getWheelTypeName(), record.getDepotCode(), record.getDepotName(), record.getStatus()==0?true:false, (short)-1);
		        	}else if(record.getWhereabouts()==1){//已经上车
		        		
		        	}
		        	this.wheelRecordDao.saveEntity(record);
		        	result++;
	        	}
        	}catch(Exception e){
        		e.printStackTrace();
        		continue;
        	}
        }
        return result;
	}

	@Override
	public PageModel<WheelRecord> queryWheelRecodList(String axleNum,
			String axleType, String wheelType, String depotCode,
			String factory, Date createDateFrom, Date createDateTO,
			Short status, Short whereabouts, boolean sub, Double diameterFrom,
			Double diameterTo) {
		List<Criterion> crits=new ArrayList<Criterion>();
		if(axleNum!=null&&!axleNum.equals("")){
//			crits.add(Restrictions.eq("axleNum", axleNum));
			String axleNums = axleNum.replaceAll("\\,|\\，", ",");
			crits.add(Restrictions.in("axleNum", axleNums.split(",")));
		}
		if(axleType!=null&&!axleType.equals("")){
			crits.add(Restrictions.eq("axleType", axleType));
		}
		if(wheelType!=null&&!wheelType.equals("")){
			crits.add(Restrictions.eq("wheelTypeName", wheelType));
		}
		if(depotCode!=null&&!depotCode.equals("")){
			if(sub){//包含下级
				crits.add(Restrictions.like("depotCode", depotCode+"%"));
			}else{
				crits.add(Restrictions.eq("depotCode", depotCode));
			}
		}
		if(factory!=null&&!factory.equals("")){
			crits.add(Restrictions.like("factory", "%"+factory+"%"));
		}
		if(createDateFrom!=null){
			crits.add(Restrictions.ge("createDate", createDateFrom));
		}
		if(createDateTO!=null){
			crits.add(Restrictions.le("createDate", createDateTO));
		}
		if(status!=null){
			crits.add(Restrictions.eq("status", status));
		}
		if(whereabouts!=null){
			crits.add(Restrictions.eq("whereabouts", whereabouts));
		}
		if(diameterFrom!=null){
			crits.add(Restrictions.ge("leftDiameter", diameterFrom));
		}
		if(diameterTo!=null){
			crits.add(Restrictions.le("leftDiameter", diameterTo));
		}
		return wheelRecordDao.findPageModel(crits);
	}

	public InputStream getExcelFile(List<WheelRecord> stocks) {
        HSSFWorkbook wb = new HSSFWorkbook();  //new 一个HSSFWorkbook实例   
        //创建一个sheet脚本  
        HSSFSheet sheet = wb.createSheet("sheet1");  
       //创建一行 ，第一行是标题如 姓名 性别 年龄  
        HSSFRow row = sheet.createRow(0);  
        //往第一行上插入单元格   
        HSSFCell cell =  row.createCell(0, HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue("轴号");  
        cell=row.createCell(1, HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue("轴型");
        cell=row.createCell(2, HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue("轮型");  
        cell=row.createCell(3, HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue("轴箱型号");  
        cell=row.createCell(4, HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue("是否有轴箱接地装置");  
        cell=row.createCell(5, HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue("是否有防滑器");  
        cell=row.createCell(6, HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue("防滑器的齿轮尺寸"); 
        cell=row.createCell(7, HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue("防滑器齿轮位置"); 
        cell=row.createCell(8, HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue("车轴制造厂");  
        cell=row.createCell(9, HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue("轮对组装日期");  
        cell=row.createCell(10, HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue("轮辋厚(左端)");  
        cell=row.createCell(11, HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue("轮辋厚(右端)");  
        cell=row.createCell(12, HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue("踏面圆周磨耗(左端)");
        cell=row.createCell(13, HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue("踏面圆周磨耗(右端)");
        cell=row.createCell(14, HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue("轮径(左端)");  
        cell=row.createCell(15, HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue("轮径(右端)");  
        cell=row.createCell(16, HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue("轮缘厚(左端)"); 
        cell=row.createCell(17, HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue("轮缘厚(右端)");
        cell=row.createCell(18, HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue("制动盘磨耗(左端)");  
        cell=row.createCell(19, HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue("制动盘磨耗(右端)");  
        cell=row.createCell(20, HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue("轮对内侧距离");  
        cell=row.createCell(21, HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue("所在单位");  
      //获取要生成表的数据  
//        List<WheelRecord> list = this.f.findAll();  
       //以行的形式出入表格中  
        WheelRecord stock=null;
        SimpleDateFormat fomater=new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < stocks.size(); ++i)  
        {  
        	row = sheet.createRow(i+1);  
        	stock=stocks.get(i);
        	cell=row.createCell(0, HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(stock.getAxleNum());
            cell=row.createCell(1, HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(stock.getAxleType());
            cell=row.createCell(2, HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(stock.getWheelTypeName());
            cell=row.createCell(3, HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(stock.getAxleBoxType());
            cell=row.createCell(4, HSSFCell.CELL_TYPE_STRING);
            if(stock.getHasAxleBoxRelay()==1){
            	cell.setCellValue("是");
            }else{
            	cell.setCellValue("否");
            }
            cell=row.createCell(5, HSSFCell.CELL_TYPE_STRING);
            if(stock.getHasAntiSkid()==1){
            	cell.setCellValue("是");
            	cell=row.createCell(6, HSSFCell.CELL_TYPE_STRING);
            	cell.setCellValue(stock.getAntiSkidSize());
            	cell=row.createCell(7, HSSFCell.CELL_TYPE_STRING);
            	cell.setCellValue(stock.getAntiSkidLoc());
            }else{
            	cell.setCellValue("否");
            	cell=row.createCell(6, HSSFCell.CELL_TYPE_STRING);
            	cell=row.createCell(7, HSSFCell.CELL_TYPE_STRING);
            }
            cell=row.createCell(8, HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(stock.getFactory());
            cell=row.createCell(9, HSSFCell.CELL_TYPE_STRING);
            if(stock.getCreateDate()!=null&&!"".equals(stock.getCreateDate())){
            	cell.setCellValue(fomater.format(stock.getCreateDate()));
            }else{
            	cell.setCellValue("");
            }
            cell=row.createCell(10, HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue(stock.getRightRimThickness());
            cell=row.createCell(11, HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue(stock.getLeftRimThickness());
            cell=row.createCell(12, HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue(stock.getLeftCircularWear()==null?0:stock.getLeftCircularWear());
            cell=row.createCell(13, HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue(stock.getRightCircularWear()==null?0:stock.getRightCircularWear());
            cell=row.createCell(14, HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue(stock.getLeftDiameter());
            cell=row.createCell(15, HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue(stock.getRightDiamter());
            cell=row.createCell(16, HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue(stock.getLeftFlangeThickness());
            cell=row.createCell(17, HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue(stock.getRightFlangeThickness());
            cell=row.createCell(18, HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue(stock.getLeftBrakeDiscWear()==null?0:stock.getLeftBrakeDiscWear());
            cell=row.createCell(19, HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue(stock.getRightBrakeDiscWear()==null?0:stock.getRightBrakeDiscWear());
            cell=row.createCell(20, HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue(stock.getInsideistance());
            cell=row.createCell(21, HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(stock.getDepotName());
        }  
        //问题：上序代码在内存中创建了一个execle表格 ，如何下载到本地  
        //解决方案一：在本地new一个临时file ，把内存中的数据写道本地file中 ，再用inputstream供用户下载  
        //解决方案二：在内存中直接下载到本地（性能好）  
        //以下对这两种方式都做了实现  
          
          
        ByteArrayOutputStream os = new ByteArrayOutputStream();  
          
        try  
        {  
            wb.write(os);  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
          
        byte[] content = os.toByteArray();  
          
        InputStream is = new ByteArrayInputStream(content);  
          
        return is;  
	}

	@Override
	public InputStream getExcelFile(String axleNum, String axleType,
			String wheelType, String depotCode, String factory,
			Date createDateFrom, Date createDateTO, Short status,
			Short whereabouts, boolean sub) {
		List<Criterion> crits=new ArrayList<Criterion>();
		if(axleNum!=null&&!axleNum.equals("")){
//			crits.add(Restrictions.eq("axleNum", axleNum));
			String axleNums = axleNum.replaceAll("\\,|\\，", ",");
			crits.add(Restrictions.in("axleNum", axleNums.split(",")));
		}
		if(axleType!=null&&!axleType.equals("")){
			crits.add(Restrictions.eq("axleType", axleType));
		}
		if(wheelType!=null&&!wheelType.equals("")){
			crits.add(Restrictions.eq("wheelTypeName", wheelType));
		}
		if(depotCode!=null&&!depotCode.equals("")){
			if(sub){//包含下级
				crits.add(Restrictions.like("depotCode", depotCode+"%"));
			}else{
				crits.add(Restrictions.eq("depotCode", depotCode));
			}
		}
		if(factory!=null&&!factory.equals("")){
			crits.add(Restrictions.like("factory", "%"+factory+"%"));
		}
		if(createDateFrom!=null){
			crits.add(Restrictions.ge("createDate", createDateFrom));
		}
		if(createDateTO!=null){
			crits.add(Restrictions.le("createDate", createDateTO));
		}
		if(status!=null){
			crits.add(Restrictions.eq("status", status));
		}
		if(whereabouts!=null){
			crits.add(Restrictions.eq("whereabouts", whereabouts));
		}
		List<WheelRecord> stocks=this.wheelRecordDao.findAll(crits);
		return this.getExcelFile(stocks);
	}

	@Override
	public InputStream getExcelFilebyIds(Long[] ids) {
		List<Criterion> crits=new ArrayList<Criterion>();
		crits.add(Restrictions.in("id",ids));
		List<WheelRecord> stocks=this.wheelRecordDao.findAll(crits);
		return this.getExcelFile(stocks);
	}

	@Override
	public WheelRecord queryWheelRecordBySC(String jcNum, String position) {
		return this.wheelRecordDao.getWheelRecordBySC(jcNum, position);
	}

	@Override
	public List<WheelRecord> findWheelRecords(String jcNum) {
		List<Criterion> crits=new ArrayList<Criterion>();
		crits.add(Restrictions.eq("jcNum",jcNum));
		crits.add(Restrictions.eq("whereabouts",(short)1));//只查询已经上车的记录
		return this.wheelRecordDao.findAll(crits);
	}

	@Override
	public List<WheelRecord> findWheelRecordsByDepot(String depotCode) {
//		Depot depot =depotDao.getDepot(depotCode);
//		if(depot.getType()==3){//运用单位
//			return outlayDao.findWheelRecordsByDepot(depotCode,2);
//		}else{//检修单位
//			return outlayDao.findWheelRecordsByDepot(depotCode,2);
//		}
		return outlayDao.findWheelRecordsByDepot(depotCode,2);
		
	}

	@Override
	public WheelRec saveWheelRec(WheelRec wheelrec) {
		
		return wheelRecDao.saveWheelRec(wheelrec);
	}

	
	
	

}
