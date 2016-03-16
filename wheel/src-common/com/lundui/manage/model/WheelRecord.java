package com.lundui.manage.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 轮对履历表
 * @author Administrator
 *
 */
public class WheelRecord implements Serializable{
	
	private static final long serialVersionUID = 3018263959020292139L;
	
	private Long id;
	/**
	 * 轴号
	 */
	private String axleNum;
	
	/**
	 * 轴型
	 */
	private String axleType;
	
	/**
	 * 轮型
	 */
	private String wheelTypeName;
	
	/**
	 * 轴箱型号
	 */
	private  String axleBoxType;
	
	/**
	 * 是否有轴箱接地装置：1有，0没有
	 */
	private Short hasAxleBoxRelay=0;
	
	/**
	 * 是否有防滑器：1有，0没有
	 */
	private Short hasAntiSkid=0;
	
	/**
	 * 防滑器的齿轮，分为80尺和90尺
	 */
	private String antiSkidSize;
	
	/**
	 * 防滑器齿轮位置：left,right
	 */
	private String antiSkidLoc;
	
	/**
	 * 车轴制造厂
	 */
	private String factory;

	/**
	 * 轮对组装日期
	 */
	private Date createDate;
	
	/**
	 * 车轮制造时间、
	 */
	private Date wheelCreateDate;
	
	/**
	 * 车轴制造时间
	 */
	private Date boxCreateDate;
	/**
	 * 轮轴组装日期
	 */
	private Date axleCreateDate;
	
	/**
	 * 轮辋厚(左端)
	 */
	private Double leftRimThickness;
	
	/**
	 * 轮辋厚(右端)
	 */
	private Double rightRimThickness;
	/**
	 * 踏面圆周磨耗（左端）
	 */
	private Double leftCircularWear;
	/**
	 *  踏面圆周磨耗（右端）
	 */
	private Double rightCircularWear;
	/**
	 * 轮径(左端)
	 */
	private Double leftDiameter;
	
	/**
	 * 轮径(右端)
	 */
	private Double rightDiamter;
	
	/**
	 * 轮缘厚(左端)
	 */
	private Double leftFlangeThickness;
	
	/**
	 * 轮缘厚(右端)
	 */
	private Double rightFlangeThickness;
	/**
	 * 制动盘磨耗（左端）
	 */
	private Double leftBrakeDiscWear;
	/**
	 * 制动盘磨耗（右端）
	 */
	private Double rightBrakeDiscWear;
	/**
	 * 轮对内侧距离
	 */
	private Double insideistance;
	
	/**
	 * 轮对状态 0表示良好、1表示不良、2表示报废
	 */
	private Short status;
	
	/**
	 * 去向 :0表示在库存中、1表示已经装车、2表示送修,修竣、3表示其他。
	 */
	private Short whereabouts;
	
	/**
	 * 所属车辆段
	 */
	private String depotCode;
	
	/**
	 * 所在段的名称
	 */
	private String depotName;
	/**
	 * 上车号
	 */
	private String jcNum;
	/**
	 * 上车位
	 */
	private String position;
	/**
	 * 备注
	 */
	private String comment;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getWheelTypeName() {
		return wheelTypeName;
	}
	public void setWheelTypeName(String wheelTypeName) {
		this.wheelTypeName = wheelTypeName;
	}
	public String getAxleBoxType() {
		return axleBoxType;
	}
	public void setAxleBoxType(String axleBoxType) {
		this.axleBoxType = axleBoxType;
	}
	public Short getHasAxleBoxRelay() {
		return hasAxleBoxRelay;
	}
	public void setHasAxleBoxRelay(Short hasAxleBoxRelay) {
		this.hasAxleBoxRelay = hasAxleBoxRelay;
	}
	public Short getHasAntiSkid() {
		return hasAntiSkid;
	}
	public void setHasAntiSkid(Short hasAntiSkid) {
		this.hasAntiSkid = hasAntiSkid;
	}
	public String getAntiSkidSize() {
		return antiSkidSize;
	}
	public void setAntiSkidSize(String antiSkidSize) {
		this.antiSkidSize = antiSkidSize;
	}
	public String getAntiSkidLoc() {
		return antiSkidLoc;
	}
	public void setAntiSkidLoc(String antiSkidLoc) {
		this.antiSkidLoc = antiSkidLoc;
	}
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Double getLeftRimThickness() {
		return leftRimThickness;
	}
	public void setLeftRimThickness(Double leftRimThickness) {
		this.leftRimThickness = leftRimThickness;
	}
	public Double getRightRimThickness() {
		return rightRimThickness;
	}
	public void setRightRimThickness(Double rightRimThickness) {
		this.rightRimThickness = rightRimThickness;
	}
	public Double getLeftCircularWear() {
		return leftCircularWear;
	}
	public void setLeftCircularWear(Double leftCircularWear) {
		this.leftCircularWear = leftCircularWear;
	}
	public Double getRightCircularWear() {
		return rightCircularWear;
	}
	public void setRightCircularWear(Double rightCircularWear) {
		this.rightCircularWear = rightCircularWear;
	}
	public Double getLeftDiameter() {
		return leftDiameter;
	}
	public void setLeftDiameter(Double leftDiameter) {
		this.leftDiameter = leftDiameter;
	}
	public Double getRightDiamter() {
		return rightDiamter;
	}
	public void setRightDiamter(Double rightDiamter) {
		this.rightDiamter = rightDiamter;
	}
	public Double getLeftFlangeThickness() {
		return leftFlangeThickness;
	}
	public void setLeftFlangeThickness(Double leftFlangeThickness) {
		this.leftFlangeThickness = leftFlangeThickness;
	}
	public Double getRightFlangeThickness() {
		return rightFlangeThickness;
	}
	public void setRightFlangeThickness(Double rightFlangeThickness) {
		this.rightFlangeThickness = rightFlangeThickness;
	}
	public Double getLeftBrakeDiscWear() {
		return leftBrakeDiscWear;
	}
	public void setLeftBrakeDiscWear(Double leftBrakeDiscWear) {
		this.leftBrakeDiscWear = leftBrakeDiscWear;
	}
	public Double getRightBrakeDiscWear() {
		return rightBrakeDiscWear;
	}
	public void setRightBrakeDiscWear(Double rightBrakeDiscWear) {
		this.rightBrakeDiscWear = rightBrakeDiscWear;
	}
	public Double getInsideistance() {
		return insideistance;
	}
	public void setInsideistance(Double insideistance) {
		this.insideistance = insideistance;
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
	public String getDepotCode() {
		return depotCode;
	}
	public void setDepotCode(String depotCode) {
		this.depotCode = depotCode;
	}
	public String getDepotName() {
		return depotName;
	}
	public void setDepotName(String depotName) {
		this.depotName = depotName;
	}
	public String getJcNum() {
		return jcNum;
	}
	public void setJcNum(String jcNum) {
		this.jcNum = jcNum;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Date getWheelCreateDate() {
		return wheelCreateDate;
	}
	public void setWheelCreateDate(Date wheelCreateDate) {
		this.wheelCreateDate = wheelCreateDate;
	}
	public Date getBoxCreateDate() {
		return boxCreateDate;
	}
	public void setBoxCreateDate(Date boxCreateDate) {
		this.boxCreateDate = boxCreateDate;
	}
	public Date getAxleCreateDate() {
		return axleCreateDate;
	}
	public void setAxleCreateDate(Date axleCreateDate) {
		this.axleCreateDate = axleCreateDate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	

}
