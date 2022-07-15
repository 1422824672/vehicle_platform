package com.mx.cvp.management.information.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * 车队
 *
 * @author cikai <cikai@mxnavi.com>
 * @date 2021/9/8
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleTeamDto {
    /**
     * 车队ID
     */
    private Integer id;

    /**
     * 车队名称
     */
    private String name;

    /**
     * 父类车队ID
     */
    private Integer parentTeamId;

    /**
     * 车队类型
     */
    private Integer type;

    /**
     * 序号
     */
    private Integer sort;

    /**
     * 最大车辆数量
     */
    private Integer vehicleCapacity;

    /**
     * 车队联系人姓名
     */
    private String contactName;

    /**
     * 车队联系方式
     */
    private String contactPhone;

    /**
     * 上次车队信息变更时间
     */
    private String remark;

    /**
     * 车队创建时间
     */
    private String updateTime;

    /**
     * 所含车队列表
     */
    private List<VehicleTeamDto> subTeams;

    /**
     * 正常车辆数量
     */
    private Integer normalVehicle;

    /**
     * 已停用车辆数量
     */
    private Integer disabledVehicle;

    /**
     * 回收车辆数量
     */
    private Integer recycledVehicle;
}
