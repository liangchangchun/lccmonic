 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.model.vo.auth.login;

import java.io.Serializable;
import java.util.Date;

 /**
 * <b>Description：</b> 存储的用户基本信息  <br/>
 * <b>ClassName：</b> UserInfo <br/>
 * <b>@author：</b> hui.yan <br/>
 * <b>@date：</b> 2016年8月1日 下午8:07:45 <br/>
 * <b>@version: </b>  <br/>
 */
public class MerchantUserInfo extends UserInfo   implements Serializable{
	
	private static final long serialVersionUID = -8229382355519062100L;

	private String empId;

    private String empFs;

    private String empName;

    private String empPhone;

    private String empPost;

    private Integer empGrade;

    private Integer empDrawgrade;

    private String empPic;

    private String empUser;

    private String empPower;

    private String empDesc;

    private Date updateTime;

    private String updateUser;

    private String dataState;

    private String empIsSm;

    private String empEmail;

    private String empType;

    private Integer empAutoRespond;

    private String cityCode;
    
	private String fsName;
    private String fsAbbrname;
    private String fsAddress; 
    private String fsPic;
    private String fsTel;
    private String areaValue;
    
	private String fsIssign;
    private String fsArea;
    private int respondCount;
    private float avgPro;//平均专业
    private float avgService;//平均服务
    private float avgTime;//平均守时
    private int userAppointNum;//预约该销售人员次数
    private String sysUserName;

     private String registId;//设备标识

     public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId == null ? null : empId.trim();
    }

    public String getEmpFs() {
        return empFs;
    }

    public void setEmpFs(String empFs) {
        this.empFs = empFs == null ? null : empFs.trim();
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone == null ? null : empPhone.trim();
    }

    public String getEmpPost() {
        return empPost;
    }

    public void setEmpPost(String empPost) {
        this.empPost = empPost == null ? null : empPost.trim();
    }

    public Integer getEmpGrade() {
        return empGrade;
    }

    public void setEmpGrade(Integer empGrade) {
        this.empGrade = empGrade;
    }

    public Integer getEmpDrawgrade() {
        return empDrawgrade;
    }

    public void setEmpDrawgrade(Integer empDrawgrade) {
        this.empDrawgrade = empDrawgrade;
    }

    public String getEmpPic() {
        return empPic;
    }

    public void setEmpPic(String empPic) {
        this.empPic = empPic == null ? null : empPic.trim();
    }

    public String getEmpUser() {
        return empUser;
    }

    public void setEmpUser(String empUser) {
        this.empUser = empUser == null ? null : empUser.trim();
    }

    public String getEmpPower() {
        return empPower;
    }

    public void setEmpPower(String empPower) {
        this.empPower = empPower == null ? null : empPower.trim();
    }

    public String getEmpDesc() {
        return empDesc;
    }

    public void setEmpDesc(String empDesc) {
        this.empDesc = empDesc == null ? null : empDesc.trim();
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

    public String getDataState() {
        return dataState;
    }

    public void setDataState(String dataState) {
        this.dataState = dataState == null ? null : dataState.trim();
    }

    public String getEmpIsSm() {
        return empIsSm;
    }

    public void setEmpIsSm(String empIsSm) {
        this.empIsSm = empIsSm == null ? null : empIsSm.trim();
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail == null ? null : empEmail.trim();
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType == null ? null : empType.trim();
    }

    public Integer getEmpAutoRespond() {
        return empAutoRespond;
    }

    public void setEmpAutoRespond(Integer empAutoRespond) {
        this.empAutoRespond = empAutoRespond;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }
    
    public int getUserAppointNum() {
		return userAppointNum;
	}
	public void setUserAppointNum(int userAppointNum) {
		this.userAppointNum = userAppointNum;
	}
	public float getAvgPro() {
		return avgPro;
	}
	public void setAvgPro(float avgPro) {
		this.avgPro = avgPro;
	}
	public float getAvgService() {
		return avgService;
	}
	public void setAvgService(float avgService) {
		this.avgService = avgService;
	}
	public float getAvgTime() {
		return avgTime;
	}
	public void setAvgTime(float avgTime) {
		this.avgTime = avgTime;
	}
	public int getRespondCount() {
		return respondCount;
	}
	public void setRespondCount(int respondCount) {
		this.respondCount = respondCount;
	}
	public String getFsArea() {
		return fsArea;
	}
	public void setFsArea(String fsArea) {
		this.fsArea = fsArea;
	}
    public String getFsIssign() {
		return fsIssign;
	}
	public void setFsIssign(String fsIssign) {
		this.fsIssign = fsIssign;
	}
    public String getAreaValue() {
		return areaValue;
	}
	public void setAreaValue(String areaValue) {
		this.areaValue = areaValue;
	}
	public String getFsPic() {
		return fsPic;
	}
	public void setFsPic(String fsPic) {
		this.fsPic = fsPic;
	}
	public String getFsTel() {
		return fsTel;
	}
	public void setFsTel(String fsTel) {
		this.fsTel = fsTel;
	}
	public String getFsAddress() {
		return fsAddress;
	}
	public void setFsAddress(String fsAddress) {
		this.fsAddress = fsAddress;
	}
	public String getFsAbbrname() {
		return fsAbbrname;
	}
	public void setFsAbbrname(String fsAbbrname) {
		this.fsAbbrname = fsAbbrname;
	}
	public String getFsName() {
		return fsName;
	}

	public void setFsName(String fsName) {
		this.fsName = fsName;
	}
	public String getSysUserName() {
		return sysUserName;
	}
	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}


     public String getRegistId() {
         return registId;
     }

     public void setRegistId(String registId) {
         this.registId = registId;
     }
 }
