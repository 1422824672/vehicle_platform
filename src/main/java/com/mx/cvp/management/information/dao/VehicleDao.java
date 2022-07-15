package com.mx.cvp.management.information.dao;

import com.mx.cvp.management.information.dto.VehicleDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 */
@Mapper
public interface VehicleDao {
    /**
     * @author 宋泽麟
     * 单个新增车辆
     */
    Integer addVehicle(VehicleDto vehicleDto);

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
     */
    void addVehicles(Integer vehicleTeamId, String vehicleName, String plateNumber, String vehiclePlateColor, String simNumber, String terminalNumber, String terminalIdentification);

    /**
     * 修改车辆信息
     * @author 宋泽麟
     */
    void updateVehicle(Integer vehicleId,
                       String plateNumber,
                       String vehicleName,
                       Integer vehicleTeamId,
                       String vehiclePlateColor,
                       String terminalIdentification,
                       Integer driverId,
                       String remarks,
                       String explain,
                       String vehicleColor,
                       String vehicleType,
                       String vehicleBusiness);

    void updateVehicleBaseProperty(Integer vehicleId,
                                   Integer maxTonne,
                                   Integer maxPerson,
                                   Integer initialMileage,
                                   Integer initialTime,
                                   Integer mileageUpdate,
                                   String installArea,
                                   String vin,
                                   String installTime,
                                   String vehicleColor);

    void updateVehicleOtherProperty(Integer vehicleId,
                                    String vehicleFrame,
                                    String vehicleDomicile,
                                    String businessName,
                                    String businessPhone);

    /**
     * 批量修改车辆信息
     * @author 宋泽麟
     */
    void updateVehicles(Integer vehicleId, Integer vehicleTeamId, String vehiclePlateColor, Timestamp linkTime, Timestamp installTime, String explain);

    /**
     * 获取车辆列表
     * @author 宋泽麟
     */
    List<VehicleDto> getVehicleList(Integer page, Integer rows, Integer vehicleTeamId, Integer condition);

    /**
     * 车辆列表总数
     * @author 宋泽麟
     */
    Integer getVehicleListTotal(Integer vehicleTeamId, Integer condition);

    /**
     * 车辆查询
     * @author 宋泽麟
     */
    List<VehicleDto> searchVehicle(String plateNumber, String vehicleName, String terminalIdentification, String terminalNumber, String simNumber, String driverName, String createUser, Timestamp minLinkTime, Timestamp maxLinkTime, Timestamp minInstallTime, Timestamp maxInstallTime, Integer page, Integer rows, Integer condition);

    /**
     * 获取查询车辆的总数
     * @author 宋泽麟
     */
    Integer getSearchVehicleTotal(String plateNumber, String vehicleName, String terminalIdentification, String terminalNumber, String simNumber, String driverName, String createUser, String minLinkTime, String maxLinkTime, String minInstallTime, String maxInstallTime, Integer condition);

    /**
     * 更新车辆状态
     * @author 宋泽麟
     */
    void updateVehicleCondition(Integer id, Integer condition);
}
