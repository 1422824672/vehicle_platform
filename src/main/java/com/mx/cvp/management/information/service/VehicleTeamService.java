package com.mx.cvp.management.information.service;

import com.mx.cvp.management.information.dto.VehicleTeamDto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @author 李达
 * @date {DATE}
 */
public interface VehicleTeamService {
    /**
     * @author
     * 更新车队信息
     * @return
     */
    Boolean updateVehicleTeam(VehicleTeamDto vehicleTeamDto);




    /**
     * @author
     * 新增车队
     */
    void addVehicleTeam(VehicleTeamDto vehicleTeamDto);



    /**
     * @author
     * 删除车队
     * @return
     */
    Boolean deleteVehicleTeam(Integer isDelete);



    /**
     * @author
     * 车队模糊搜索
     */
    List<VehicleTeamDto> searchVehicleTeam(String name);


    /**
     * @author
     * 车队详细信息
     */
    VehicleTeamDto getVehicleTeamDetail(Integer id);

    /**
     * @author
     * 获取车队列表
     */
    List<VehicleTeamDto> getVehicleTeamList();

    /**
     * @author
     * 更新车队列表
     * @return
     */
    Boolean updateVehicleTeamListSort(List<Integer> ids);

    /**
     * @author
     * 车辆导出
     * @return
     */
    File deriveVehicle() throws IOException;
}
