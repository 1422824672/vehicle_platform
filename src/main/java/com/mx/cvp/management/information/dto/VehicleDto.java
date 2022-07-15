package com.mx.cvp.management.information.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * 车辆
 *
 * @author cikai <cikai@mxnavi.com>
 * @date 2021/9/8
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleDto {

    /**
     * 车辆ID
     */
    private Integer id;

    /**
     * 车牌号码
     */
    private String plateNumber;

    /**
     * 车辆名称
     */
    private String vehicleName;

    /**
     * 车牌颜色
     */
    private String vehiclePlateColor;

    /**
     * 终端标识
     */
    private String terminalIdentification;

    /**
     * 驾驶员ID
     */
    private Integer driverId;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 说明
     */
    private String explain;

    /**
     * 车辆颜色
     */
    private String vehicleColor;

    /**
     * 核载吨数
     */
    private Integer maxTonne;

    /**
     * 核载人数
     */
    private Integer maxPerson;

    /**
     * 初始里程
     */
    private Integer initialMileage;

    /**
     * 初始时长
     */
    private Integer initialTime;

    /**
     * 里程修正
     */
    private Integer mileageUpdate;

    /**
     * 安装地区
     */
    private String installArea;

    /**
     * VIN
     */
    private String vin;

    /**
     * 安装时间
     */
    private String installTime;

    /**
     * 车辆类型
     */
    private String vehicleType;

    /**
     * 车辆行业
     */
    private String vehicleBusiness;

    /**
     * 车架号
     */
    private String vehicleFrame;

    /**
     * 车籍地
     */
    private String vehicleDomicile;

    /**
     * 业户名称
     */
    private String businessName;

    /**
     * 业户电话
     */
    private String businessPhone;

    /**
     * 车辆ID
     */
    private Integer vehicleId;

    /**
     * 车队ID
     */
    private Integer vehicleTeamId;

    /**
     * sim卡号
     */
    private String simNumber;

    /**
     * 终端号
     */
    private String terminalNumber;

    /**
     * 创建者
     */
    private String createUser;

    /**
     * 入网时间
     */
    private String linkTime;

    /**
     * 车辆状态
     * 0-正常
     * 1-停用
     * 2-回收
     */
    private Integer condition;

    /**
     * 最小入网时间
     */
    private String minLinkTime;

    /**
     * 最大入网时间
     */
    private String maxLinkTime;

    /**
     * 最小安装时间
     */
    private String minInstallTime;

    /**
     * 最大安装时间
     */
    private String maxInstallTime;

    /**
     * 页号
     */
    private Integer page;

    /**
     * 行数
     */
    private Integer rows;

    /**
     * 车主姓名
     */
    private String driverName;

    /**
     * 单个修改或批量修改参数
     * 0-单个，1-批量
     */
    private Integer updateOrUpdates;

    /**
     * 参数列表
     */
    private List<VehicleDto> data;
}
