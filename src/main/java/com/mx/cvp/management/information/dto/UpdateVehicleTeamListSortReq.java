package com.mx.cvp.management.information.dto;

import lombok.Data;

import java.util.List;

/**
 * 【车队管理】更新车队列表排序入参
 *
 * @Author 哑雀
 * @Date 2021/9/24 14:30
 * @Version 1.0
 */
@Data
public class UpdateVehicleTeamListSortReq {

    /**
     * 车队ID有序集合
     */
    private List<Integer> ids;
}
