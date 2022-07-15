package com.mx.cvp.management.information.dao;

import com.mx.cvp.management.information.dto.VehicleTeamDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author 李达
 * @date {DATE}
 */
@Mapper
public interface VehicleTeamDao {
    /**
     * @author
     * 更新车队信息
     */
    void updateVehicleTeam(VehicleTeamDto vehicleTeamDto);



    /**
     * @author
     * 新增车队 设置车队编号
     */
    void addVehicleTeam(VehicleTeamDto vehicleTeamDto);


    /**
     * @author
     * 删除车队
     */
    void deleteVehicleTeam(List<Integer> ids);



    /**
     * @author
     * 车队模糊查询
     */
    List<VehicleTeamDto> searchVehicleTeam(String name);


    /**
     * @author
     * 获取车队详情
     */
    VehicleTeamDto getVehicleTeamDetail(Integer id);


    /**
     * @author
     * 获取车队列表
     */
    List<VehicleTeamDto> getVehicleTeamList();



    /**
     * @author
     * 获取车队所属车辆
     */
//    Integer searchVehicleNumber(Integer id, Integer type);


    /**
     * @author
     * 车辆列表排序
     */
    void updateVehicleTeamListSort(List<Integer> ids);

    /**
     * @author
     * 查询排序
     */
    Integer checkSort(Integer parentTeamId);
}
