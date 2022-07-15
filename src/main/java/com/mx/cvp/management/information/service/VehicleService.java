package com.mx.cvp.management.information.service;

import com.mx.cvp.management.information.dto.VehicleDto;

import java.io.InputStream;
import java.util.List;

public interface VehicleService {

    /**
     * 新增车辆主表
     * @author 宋泽麟
     * @return 真假值
     */
    boolean addVehicle(VehicleDto vehicleDto);

    /**
     * 新增车辆基本属性表
     * @author 宋泽麟
     * @param vehicleDto
     */
    void addVehicleBaseProperty(VehicleDto vehicleDto);

    /**
     * 新增车辆其它属性表
     * @author 宋泽麟
     * @param vehicleDto
     */
    void addVehicleOtherProperty(VehicleDto vehicleDto);

    /**
     * 批量新增车辆
     * @author 宋泽麟
     * @param vehicleTeamId 车队/企业ID
     * @param filename 文件名
     * @param in 输入流
     * @return 真假值
     * @throws Exception 异常
     */
    boolean addVehicles(Integer vehicleTeamId, String filename, InputStream in) throws Exception;

    /**
     * 修改车辆信息
     */
    boolean updateVehicle(Integer vehicleId,
                          Integer vehicleTeamId,
                          String plateNumber,
                          String vehicleName,
                          String vehiclePlateColor,
                          String terminalIdentification,
                          Integer driverId,
                          String remarks,
                          String explain,
                          String vehicleColor,
                          Integer maxTonne,
                          Integer maxPerson,
                          Integer initialMileage,
                          Integer initialTime,
                          Integer mileageUpdate,
                          String installArea,
                          String vin,
                          String installTime,
                          String vehicleType,
                          String vehicleBusiness,
                          String vehicleFrame,
                          String vehicleDomicile,
                          String businessName,
                          String businessPhone);

    /**
     * 批量修改车辆信息
     */
    void updateVehicles(Integer vehicleId, Integer vehicleTeamId,String vehiclePlateColor,String linkTime,String installTime,String explain);

    /**
     * 获取车辆列表
     * @author 宋泽麟
     * @param page 页号
     * @param rows 行数
     * @param vehicleTeamId 车队/企业ID
     * @param condition 车辆状态
     * @return 车辆列表
     */
    List<VehicleDto> getVehicleList(Integer page, Integer rows, Integer vehicleTeamId, Integer condition);

    /**
     * 车辆查询
     * @author 宋泽麟
     * @param plateNumber 车牌号
     * @param vehicleName 车辆名称
     * @param terminalIdentification 终端标识
     * @param terminalNumber 终端号
     * @param simNumber sim号
     * @param driverName 驾驶员姓名
     * @param createUser 创建者
     * @param minLinkTime 最小连接时间
     * @param maxLinkTime 最大连接时间
     * @param minInstallTime 最小安装时间
     * @param maxInstallTime 最大安装时间
     * @param page 页号
     * @param rows 行数
     * @param condition 车辆状态
     * @return 车辆列表
     */
    List<VehicleDto> searchVehicle(String plateNumber,
                                   String vehicleName,
                                   String terminalIdentification,
                                   String terminalNumber,
                                   String simNumber,
                                   String driverName,
                                   String createUser,
                                   String minLinkTime,
                                   String maxLinkTime,
                                   String minInstallTime,
                                   String maxInstallTime,
                                   Integer page,
                                   Integer rows,
                                   Integer condition);

    /**
     * 车辆列表总数
     * @author 宋泽麟
     * @param vehicleTeamId 车队/企业ID
     * @param condition 车辆状态
     * @return 列表总数
     */
    Integer getVehicleListTotal(Integer vehicleTeamId, Integer condition);

    /**
     * 更新车辆状态
     * @author 宋泽麟
     * @param id 车辆id
     * @param condition 车辆状态
     */
    void updateVehicleCondition(Integer id, Integer condition);

    /**
     * 获取查询车辆的总数
     * @author 宋泽麟
     * @param plateNumber 车牌号
     * @param vehicleName 车辆名称
     * @param terminalIdentification 终端标识
     * @param terminalNumber 终端号
     * @param simNumber sim号
     * @param driverName 驾驶员姓名
     * @param createUser 创建者
     * @param minLinkTime 最小连接时间
     * @param maxLinkTime 最大连接时间
     * @param minInstallTime 最小安装时间
     * @param maxInstallTime 最大安装时间
     * @param condition 车辆状态
     * @return 查询总数
     */
    Integer getSearchVehicleTotal(String plateNumber,
                                  String vehicleName,
                                  String terminalIdentification,
                                  String terminalNumber,
                                  String simNumber,
                                  String driverName,
                                  String createUser,
                                  String minLinkTime,
                                  String maxLinkTime,
                                  String minInstallTime,
                                  String maxInstallTime,
                                  Integer condition);
}


