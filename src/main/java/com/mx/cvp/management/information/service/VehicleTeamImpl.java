package com.mx.cvp.management.information.service;

import com.mx.cvp.management.information.dao.VehicleTeamDao;
import com.mx.cvp.management.information.dto.VehicleTeamDto;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李达
 * @date {DATE}
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class VehicleTeamImpl implements VehicleTeamService{
    @Resource
    VehicleTeamDao vehicleTeamDao;

    @Override
    public void addVehicleTeam(VehicleTeamDto vehicleTeamDto) {
        vehicleTeamDto.setSort(vehicleTeamDao.checkSort(vehicleTeamDto.getParentTeamId()));
        vehicleTeamDao.addVehicleTeam(vehicleTeamDto);
    }

    @Override
    public Boolean deleteVehicleTeam(Integer id) {
        List<VehicleTeamDto> vehicleTeamEntities = vehicleTeamDao.getVehicleTeamList();
        System.out.println(vehicleTeamEntities);
//        查询是否存在 是否超过最大值
        Boolean key = false;
        for(VehicleTeamDto vehicleTeamDto: vehicleTeamEntities)
        {
            if(vehicleTeamDto.getId()==id)
                key = true;
        }



//        找到所有根节点
        List<VehicleTeamDto> rootList = new ArrayList<>();
        for (VehicleTeamDto vehicleTeamEntity : vehicleTeamEntities) {
            if (vehicleTeamEntity.getParentTeamId() == 0)
                rootList.add(vehicleTeamEntity);
        }
        for (VehicleTeamDto vehicleTeamEntity : rootList) {
            vehicleTeamEntity.setSubTeams(setChildList(vehicleTeamEntity.getId(), vehicleTeamEntities));
        }
//        System.out.println(rootList);
        List<Integer> list = new ArrayList<>();
        list.add(id);
        for(VehicleTeamDto vehicleTeamDto:rootList)
        {
            addChildId(vehicleTeamDto.getSubTeams(), id, list);
        }
        System.out.println(list);
        vehicleTeamDao.deleteVehicleTeam(list);
        System.out.println(key);
        return key;
    }

    //    查询子类ID
    private void addChildId(List<VehicleTeamDto> rootList,Integer id, List<Integer> list)
    {
        if(ObjectUtils.isEmpty(rootList))
            return;
        for(VehicleTeamDto vehicleTeamDto:rootList){
            if(vehicleTeamDto.getParentTeamId()==id) {
                list.add(vehicleTeamDto.getId());
                addChildId(vehicleTeamDto.getSubTeams(), vehicleTeamDto.getId(), list);
            }
            else
                addChildId(vehicleTeamDto.getSubTeams(), id, list);
        }
    }


    @Override
    public List<VehicleTeamDto> searchVehicleTeam(String name) {
        List<VehicleTeamDto> vehicleTeamDtos = vehicleTeamDao.searchVehicleTeam(name);
        return vehicleTeamDtos;
    }

    @Override
    public Boolean updateVehicleTeam(VehicleTeamDto vehicleTeamDto) {
        List<VehicleTeamDto> vehicleTeamDtoList = vehicleTeamDao.getVehicleTeamList();
        Boolean key = false;
        for(VehicleTeamDto vehicleTeam:vehicleTeamDtoList)
        {
            if(vehicleTeamDto.getId()==vehicleTeam.getId())
            {
                key = true;
            }
        }
        vehicleTeamDao.updateVehicleTeam(vehicleTeamDto);
        return key;
    }

    @Override
    public VehicleTeamDto getVehicleTeamDetail(Integer id) {
        return vehicleTeamDao.getVehicleTeamDetail(id);
    }

    @Override
    public List<VehicleTeamDto> getVehicleTeamList() {
        List<VehicleTeamDto> vehicleTeamEntities = vehicleTeamDao.getVehicleTeamList();
        System.out.println(vehicleTeamEntities);
        Map<Integer, VehicleTeamDto> vehicleTeamMap = new HashMap<>();
//        找到所有根节点
        List<VehicleTeamDto> rootList = new ArrayList<>();
        for (VehicleTeamDto vehicleTeamEntity : vehicleTeamEntities) {
            if (vehicleTeamEntity.getParentTeamId() == 0)
                rootList.add(vehicleTeamEntity);
        }
        for (VehicleTeamDto vehicleTeamEntity : rootList) {
            vehicleTeamEntity.setSubTeams(setChildList(vehicleTeamEntity.getId(), vehicleTeamEntities));
        }
        return rootList;
    }

    @Override
    public Boolean updateVehicleTeamListSort(List<Integer> ids) {
//        检查是否为同一父类下的车队
        List<VehicleTeamDto> vehicleTeamDtoList = vehicleTeamDao.getVehicleTeamList();
        List<Integer> integerList = new ArrayList<>();
        int key = -1;
        for(Integer id:ids) {
            for (VehicleTeamDto vehicleTeamDto : vehicleTeamDtoList) {
                if (vehicleTeamDto.getId() == id)
                {
                    if(key==-1)
                    {
                        key=vehicleTeamDto.getParentTeamId();
                    }else if(key!=vehicleTeamDto.getParentTeamId())
                    {
                        return false;
                    }
                }
            }
        }
        vehicleTeamDao.updateVehicleTeamListSort(ids);
        return true;
    }

    //    负责递归
    private List<VehicleTeamDto> setChildList(Integer id, List<VehicleTeamDto> vehicleTeamEntities) {
        List<VehicleTeamDto> childList = new ArrayList<>();
        for (VehicleTeamDto vehicleTeamEntity : vehicleTeamEntities) {
            if (id.equals(vehicleTeamEntity.getParentTeamId()))
                childList.add(vehicleTeamEntity);
        }
        if (childList.size() == 0)
            return null;
        for (VehicleTeamDto vehicleTeamEntity : childList) {
            vehicleTeamEntity.setSubTeams(setChildList(vehicleTeamEntity.getId(), vehicleTeamEntities));
        }
        return childList;
    }

    @Override
    public File deriveVehicle() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("我是一张表");
        Row row = sheet.createRow(1);
        Cell cell = row.createCell(1);
        cell.setCellValue("我是单元格内的数据");
        String PATH = "F:\\Code\\ideaJavaEE\\vehicle_platform_develop\\";
        FileOutputStream fileOutputStream = new FileOutputStream(PATH+"生成的excel表格.xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        System.out.println("文件已经生成");
        return null;
    }
}
