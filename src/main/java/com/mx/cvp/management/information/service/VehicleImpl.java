package com.mx.cvp.management.information.service;

import com.mx.cvp.management.information.dao.VehicleDao;
import com.mx.cvp.management.information.dto.VehicleDto;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dell
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class VehicleImpl implements VehicleService{

    @Resource
    VehicleDao vehicleDao;

    /**
     * 新增车辆
     * @author 宋泽麟
     */
    @Override
    public boolean addVehicle(VehicleDto vehicleDto) {
        vehicleDao.addVehicle(vehicleDto);
        return true;
    }

    /**
     * 新增车辆基本属性表
     * @author 宋泽麟
     * @param vehicleDto
     */
    @Override
    public void addVehicleBaseProperty(VehicleDto vehicleDto) {
        vehicleDao.addVehicleBaseProperty(vehicleDto);
    }

    @Override
    public void addVehicleOtherProperty(VehicleDto vehicleDto) {
        vehicleDao.addVehicleOtherProperty(vehicleDto);
    }

    /**
     * 转换车牌颜色
     * @author 宋泽麟
     */
    public String changeVehiclePlateColor(String vehiclePlateColor){
        String[] color = new String[]{"黑色", "白色", "灰色", "银色", "红色", "蓝色", "黄色", "绿色", "粉色", "紫色", "黄绿", "蓝绿", "农黄", "农绿", "其它"};
        for (int i = 0; i < color.length; i++){
            if (vehiclePlateColor.equals(color[i])){
                i = i + 1;
                return i+"";
            }
        }
        return "其它";
    }

    /**
     * excel表数据存list传车辆dao循环方法
     * @author 宋泽麟
     */
    public void addVehicles(Integer vehicleTeamId,List list){
        String vehiclePlateColor = "";
        for (int i = 0 ; i < list.size(); i++) {
            vehiclePlateColor = changeVehiclePlateColor(list.get(i+2).toString());
            vehicleDao.addVehicles(vehicleTeamId,
                    list.get(i).toString(),
                    list.get(i+1).toString(),
                    vehiclePlateColor,
                    list.get(i+3).toString(),
                    list.get(i+4).toString(),
                    list.get(i+5).toString());
            i = i + 5;
        }
    }

    /**
     * 批量新增车辆
     * 方法体
     * @author 宋泽麟
     */
    @Override
    public boolean addVehicles(Integer vehicleTeamId, String fileName, InputStream excelFile) throws Exception {
        boolean flag = true;
        List list = new ArrayList();
        //创建Excel工作薄
        Workbook workbook = this.getWorkbook(excelFile,fileName);
        Sheet sheet = workbook.getSheet("Sheet0");
        Row row;//行
        Cell cell;//单元格
        for (int j = 3;j <= 255;j++) {
            row = sheet.getRow(j);//从第j行开始
            for (int i = 1; i <= 6; i++) {//从第二个单元格到最后一个
                if(row.getCell(1) == null){
                    addVehicles(vehicleTeamId, list);
                    return flag;
                }
                cell = row.getCell(i);
                list.add(cell);//这里调用单个新增车辆的接口
            }
        }
        addVehicles(vehicleTeamId, list);
        return flag;
    }



    /**
     * 修改车辆信息
     * @author 宋泽麟
     * @param vehicleId
     * @return
     */
    @Override
    public boolean updateVehicle(Integer vehicleId, Integer vehicleTeamId, String plateNumber, String vehicleName, String vehiclePlateColor, String terminalIdentification, Integer driverId, String remarks, String explain, String vehicleColor, Integer maxTonne, Integer maxPerson, Integer initialMileage, Integer initialTime, Integer mileageUpdate, String installArea, String vin, String installTime, String vehicleType, String vehicleBusiness, String vehicleFrame, String vehicleDomicile, String businessName, String businessPhone) {
        vehicleDao.updateVehicle(vehicleId, plateNumber, vehicleName, vehicleTeamId, vehiclePlateColor, terminalIdentification, driverId, remarks, explain, vehicleColor, vehicleType, vehicleBusiness);
        vehicleDao.updateVehicleBaseProperty(vehicleId, maxTonne, maxPerson, initialMileage, initialTime, mileageUpdate, installArea, vin, installTime, vehicleColor);
        vehicleDao.updateVehicleOtherProperty(vehicleId, vehicleFrame, vehicleDomicile, businessName, businessPhone);
        return true;
    }

    /**
     * 批量修改车辆信息
     */
    @Override
    public void updateVehicles(Integer vehicleId, Integer vehicleTeamId, String vehiclePlateColor, String linkTime, String installTime, String explain) {
        Timestamp linkTimes = null;
        Timestamp installTimes = null;
        if (linkTime != null){
            linkTimes = Timestamp.valueOf(linkTime);
        }
        if (installTime != null){
            installTimes = Timestamp.valueOf(installTime);
        }
        vehicleDao.updateVehicles(vehicleId, vehicleTeamId,vehiclePlateColor,linkTimes,installTimes,explain);
    }

    /**
     * 获取车辆列表
     * @return
     */
    @Override
    public List<VehicleDto> getVehicleList(Integer page, Integer rows, Integer vehicleTeamId, Integer condition) {
        return vehicleDao.getVehicleList(page, rows, vehicleTeamId, condition);
    }

    /**
     * 车辆搜索
     * @return
     */
    @Override
    public List<VehicleDto> searchVehicle(String plateNumber, String vehicleName, String terminalIdentification, String terminalNumber, String simNumber, String driverName, String createUser, String minLinkTime, String maxLinkTime, String minInstallTime, String maxInstallTime, Integer page, Integer rows, Integer condition) {
        Timestamp minLinkTimes = null;
        Timestamp maxLinkTimes = null;
        Timestamp minInstallTimes = null;
        Timestamp maxInstallTimes = null;
        if (minLinkTime != null){
            minLinkTimes = Timestamp.valueOf(minLinkTime);
        }
        if (maxLinkTime != null){
            maxLinkTimes = Timestamp.valueOf(maxLinkTime);
        }
        if (minInstallTime != null){
            minInstallTimes = Timestamp.valueOf(minInstallTime);
        }
        if (maxInstallTime != null){
            maxInstallTimes = Timestamp.valueOf(maxInstallTime);
        }
        return vehicleDao.searchVehicle(plateNumber, vehicleName, terminalIdentification, terminalNumber, simNumber, driverName, createUser, minLinkTimes, maxLinkTimes, minInstallTimes, maxInstallTimes, page, rows, condition);
    }

    /**
     * 车辆列表总数
     * @param vehicleTeamId
     * @param condition
     * @return
     */
    @Override
    public Integer getVehicleListTotal(Integer vehicleTeamId, Integer condition) {
        return vehicleDao.getVehicleListTotal(vehicleTeamId, condition);
    }

    /**
     * 更新车辆状态
     * @return
     */
    @Override
    public void updateVehicleCondition(Integer id, Integer condition) {
        vehicleDao.updateVehicleCondition(id, condition);
    }

    /**
     * 获取
     * @param plateNumber
     * @param vehicleName
     * @param terminalIdentification
     * @param terminalNumber
     * @param simNumber
     * @param driverName
     * @param createUser
     * @param minLinkTime
     * @param maxLinkTime
     * @param minInstallTime
     * @param maxInstallTime
     * @param condition
     * @return
     */
    @Override
    public Integer getSearchVehicleTotal(String plateNumber, String vehicleName, String terminalIdentification, String terminalNumber, String simNumber, String driverName, String createUser, String minLinkTime, String maxLinkTime, String minInstallTime, String maxInstallTime, Integer condition) {
        return vehicleDao.getSearchVehicleTotal(plateNumber, vehicleName, terminalIdentification, terminalNumber, simNumber, driverName, createUser, minLinkTime, maxLinkTime, minInstallTime, maxInstallTime, condition);
    }

    /**
     * 判断文件格式.xls/.xlsx
     * @author 宋泽麟
     * @param in
     * @param fileName
     * @return
     * @throws Exception
     */
    public Workbook getWorkbook(InputStream in, String fileName) throws Exception {
        Workbook workbook = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if(".xls".equals(fileType)){
            workbook = new HSSFWorkbook(in);
        }else if(".xlsx".equals(fileType)){
            workbook = new XSSFWorkbook(in);
        }else {
            throw new Exception("请上传.xls/.xlsx格式文件！");
        }
        return workbook;
    }
}
