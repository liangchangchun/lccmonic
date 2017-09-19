package com.help.facade.model.entity;

import java.io.Serializable;
import java.util.Date;


public class CarModel implements Serializable{
	
	private static final long serialVersionUID = 1023375667535500912L;

	private String modelId;

    private String modelType;

    private Short modelNum;

    private String modelBrand;

    private String modelName;

    private String modelCategory;

    private Integer modelSeating;

    private String modelOrigin;

    private Integer modelDoor;

    private String modelRoof;

    private String modelPolicy;

    private String modelAge;

    private Float modelPrice;

    private Float modelMarket;

    private String modelDrive;

    private String modelEngine;

    private String modelOil;

    private String modelDisp;

    private String modelGear;

    private String modelAir;

    private String modelGearbox;

    private String modelIshot;

    private String modelOutColor;

    private String modelColorValue;

    private Integer askCount;

    private Integer dealCount;

    private Date updateTime;

    private String updateUser;

    private Short modelIsstop;

    private Short modelIsshprice;

    private String dataState;

    private String modelTypename;

    private String modelPower;

    private String modelLevel;

    private String modelTaxPolicy;

    public String getModelId() {
        return modelId;
    }
	 public CarModel(String modelId, String modelType, String modelBrand, Short modelNum, String modelName, String modelCategory, 
    		Integer modelSeating, String modelOrigin, Integer modelDoor, String modelRoof, String modelPolicy, String modelAge, Float modelPrice, 
    		Float modelMarket, String modelDrive, String modelEngine, 
    		String modelOil, String modelDisp, String modelGear, String modelAir,
			String modelGearbox, String modelIshot, String modelOutColor,String modelColorValue, Integer askCount, Integer dealCount, Date updateTime,
			String updateUser, Short modelIsstop, Short modelIsshprice, String dataState, String modelTypename, String modelPower, String modelLevel) {
		super();
		this.modelId = modelId;
		this.modelType = modelType;
		this.modelBrand = modelBrand;
		this.modelNum = modelNum;
		this.modelName = modelName;
		this.modelCategory = modelCategory;
		this.modelSeating = modelSeating;
		this.modelOrigin = modelOrigin;
		this.modelDoor = modelDoor;
		this.modelRoof = modelRoof;
		this.modelPolicy = modelPolicy;
		this.modelAge = modelAge;
		this.modelPrice = modelPrice;
		this.modelMarket = modelMarket;
		this.modelDrive = modelDrive;
		this.modelEngine = modelEngine;
		this.modelOil = modelOil;
		this.modelDisp = modelDisp;
		this.modelGear = modelGear;
		this.modelAir = modelAir;
		this.modelGearbox = modelGearbox;
		this.modelIshot = modelIshot;
		this.modelOutColor = modelOutColor;
		this.modelColorValue=modelColorValue;
		this.askCount = askCount;
		this.dealCount = dealCount;
		this.updateTime = updateTime;
		this.updateUser = updateUser;
		this.modelIsstop = modelIsstop;
		this.modelIsshprice = modelIsshprice;
		this.dataState = dataState;
		this.modelTypename = modelTypename;
		this.modelPower = modelPower;
		this.modelLevel = modelLevel;
	}

    public CarModel() {
	}

    public void setModelId(String modelId) {
        this.modelId = modelId == null ? null : modelId.trim();
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType == null ? null : modelType.trim();
    }

    public Short getModelNum() {
        return modelNum;
    }

    public void setModelNum(Short modelNum) {
        this.modelNum = modelNum;
    }

    public String getModelBrand() {
        return modelBrand;
    }

    public void setModelBrand(String modelBrand) {
        this.modelBrand = modelBrand == null ? null : modelBrand.trim();
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName == null ? null : modelName.trim();
    }

    public String getModelCategory() {
        return modelCategory;
    }

    public void setModelCategory(String modelCategory) {
        this.modelCategory = modelCategory == null ? null : modelCategory.trim();
    }

    public Integer getModelSeating() {
        return modelSeating;
    }

    public void setModelSeating(Integer modelSeating) {
        this.modelSeating = modelSeating;
    }

    public String getModelOrigin() {
        return modelOrigin;
    }

    public void setModelOrigin(String modelOrigin) {
        this.modelOrigin = modelOrigin == null ? null : modelOrigin.trim();
    }

    public Integer getModelDoor() {
        return modelDoor;
    }

    public void setModelDoor(Integer modelDoor) {
        this.modelDoor = modelDoor;
    }

    public String getModelRoof() {
        return modelRoof;
    }

    public void setModelRoof(String modelRoof) {
        this.modelRoof = modelRoof == null ? null : modelRoof.trim();
    }

    public String getModelPolicy() {
        return modelPolicy;
    }

    public void setModelPolicy(String modelPolicy) {
        this.modelPolicy = modelPolicy == null ? null : modelPolicy.trim();
    }

    public String getModelAge() {
        return modelAge;
    }

    public void setModelAge(String modelAge) {
        this.modelAge = modelAge == null ? null : modelAge.trim();
    }

    public Float getModelPrice() {
        return modelPrice;
    }

    public void setModelPrice(Float modelPrice) {
        this.modelPrice = modelPrice;
    }

    public Float getModelMarket() {
        return modelMarket;
    }

    public void setModelMarket(Float modelMarket) {
        this.modelMarket = modelMarket;
    }

    public String getModelDrive() {
        return modelDrive;
    }

    public void setModelDrive(String modelDrive) {
        this.modelDrive = modelDrive == null ? null : modelDrive.trim();
    }

    public String getModelEngine() {
        return modelEngine;
    }

    public void setModelEngine(String modelEngine) {
        this.modelEngine = modelEngine == null ? null : modelEngine.trim();
    }

    public String getModelOil() {
        return modelOil;
    }

    public void setModelOil(String modelOil) {
        this.modelOil = modelOil == null ? null : modelOil.trim();
    }

    public String getModelDisp() {
        return modelDisp;
    }

    public void setModelDisp(String modelDisp) {
        this.modelDisp = modelDisp == null ? null : modelDisp.trim();
    }

    public String getModelGear() {
        return modelGear;
    }

    public void setModelGear(String modelGear) {
        this.modelGear = modelGear == null ? null : modelGear.trim();
    }

    public String getModelAir() {
        return modelAir;
    }

    public void setModelAir(String modelAir) {
        this.modelAir = modelAir == null ? null : modelAir.trim();
    }

    public String getModelGearbox() {
        return modelGearbox;
    }

    public void setModelGearbox(String modelGearbox) {
        this.modelGearbox = modelGearbox == null ? null : modelGearbox.trim();
    }

    public String getModelIshot() {
        return modelIshot;
    }

    public void setModelIshot(String modelIshot) {
        this.modelIshot = modelIshot == null ? null : modelIshot.trim();
    }

    public String getModelOutColor() {
        return modelOutColor;
    }

    public void setModelOutColor(String modelOutColor) {
        this.modelOutColor = modelOutColor == null ? null : modelOutColor.trim();
    }

    public String getModelColorValue() {
        return modelColorValue;
    }

    public void setModelColorValue(String modelColorValue) {
        this.modelColorValue = modelColorValue == null ? null : modelColorValue.trim();
    }

    public Integer getAskCount() {
        return askCount;
    }

    public void setAskCount(Integer askCount) {
        this.askCount = askCount;
    }

    public Integer getDealCount() {
        return dealCount;
    }

    public void setDealCount(Integer dealCount) {
        this.dealCount = dealCount;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Short getModelIsstop() {
        return modelIsstop;
    }

    public void setModelIsstop(Short modelIsstop) {
        this.modelIsstop = modelIsstop;
    }

    public Short getModelIsshprice() {
        return modelIsshprice;
    }

    public void setModelIsshprice(Short modelIsshprice) {
        this.modelIsshprice = modelIsshprice;
    }

    public String getDataState() {
        return dataState;
    }

    public void setDataState(String dataState) {
        this.dataState = dataState == null ? null : dataState.trim();
    }

    public String getModelTypename() {
        return modelTypename;
    }

    public void setModelTypename(String modelTypename) {
        this.modelTypename = modelTypename == null ? null : modelTypename.trim();
    }

    public String getModelPower() {
        return modelPower;
    }

    public void setModelPower(String modelPower) {
        this.modelPower = modelPower == null ? null : modelPower.trim();
    }

    public String getModelLevel() {
        return modelLevel;
    }

    public void setModelLevel(String modelLevel) {
        this.modelLevel = modelLevel == null ? null : modelLevel.trim();
    }

    public String getModelTaxPolicy() {
        return modelTaxPolicy;
    }

    public void setModelTaxPolicy(String modelTaxPolicy) {
        this.modelTaxPolicy = modelTaxPolicy == null ? null : modelTaxPolicy.trim();
    }
}