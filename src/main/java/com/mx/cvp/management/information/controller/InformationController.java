package com.mx.cvp.management.information.controller;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mchange.io.FileUtils;
import com.mx.cvp.common.controller.BaseController;
import com.mx.cvp.management.information.dto.UpdateVehicleTeamListSortReq;
import com.mx.cvp.management.information.dto.VehicleDto;
import com.mx.cvp.management.information.dto.VehicleTeamDto;
import com.mx.cvp.management.information.service.VehicleService;
import com.mx.cvp.management.information.service.VehicleTeamService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 管理中心-信息管理
 *
 * @author cikai <cikai@mxnavi.com>
 * @date 2021/9/8
 */
@RestController
@RequestMapping("/management/information/")
public class InformationController extends BaseController {

    private Gson gson = new Gson();
//
//    /* 车队管理 */
//
//    /**
//     * 【车队管理】添加车队
//     *
//     * @param vehicleTeam 车队信息
//     * @return 返回值
//     */
//    @RequestMapping("/addVehicleTeam")
//    public @ResponseBody
//    Object addVehicleTeam(VehicleTeamDto vehicleTeam) {
//        // TODO: 添加车队代码实现
//        Map<String, Object> resultMap = new HashMap<>();
//        resultMap.put("id", null);
//        return resultSuccessMap(resultMap);
//    }
//
//    /**
//     * 【车队管理】更新车队
//     *
//     * @param vehicleTeam 车队信息
//     * @return 返回值
//     */
//    @RequestMapping("/updateVehicleTeam")
//    public @ResponseBody
//    Object updateVehicleTeam(VehicleTeamDto vehicleTeam) {
//        // TODO: 更新车队代码实现
//        return resultSuccess();
//    }
//
//    /**
//     * 【车队管理】删除车队
//     *
//     * @param id 车队ID
//     * @return 返回值
//     */
//    @RequestMapping("/deleteVehicleTeam")
//    public @ResponseBody
//    Object deleteVehicleTeam(@RequestParam("id") Integer id) {
//        // TODO: 删除车队代码实现
//        return resultSuccess();
//    }
//
//    /**
//     * 【车队管理】获取车队列表
//     *
//     * @return 车队列表
//     */
//    @RequestMapping("/getVehicleTeamList")
//    public @ResponseBody
//    Object getVehicleTeamList() {
//        // TODO: 获取车队列表代码实现
//        return resultSuccessRows(null);
//    }
//
//    /**
//     * 【车队管理】获取车队详情
//     *
//     * @param id 车队ID
//     * @return 车队信息
//     */
//    @RequestMapping("/getVehicleTeamDetail")
//    public @ResponseBody
//    Object getVehicleTeamDetail(@RequestParam("id") Integer id) {
//        // TODO: 获取车队详情代码实现
//        return resultSuccessData(null);
//    }
//
//    /**
//     * 【车队管理】关键字查询车队
//     *
//     * @param name 关键字
//     * @return 车队列表
//     */
//    @RequestMapping("/searchVehicleTeam")
//    public @ResponseBody
//    Object searchVehicleTeam(@RequestParam("name") Integer name) {
//        // TODO: 关键字查询车队代码实现
//        return resultSuccessRows(null);
//    }
//
//    /**
//     * 【车队管理】更新车队列表排序
//     *
//     * @param ids 车队ID有序集合
//     * @return 返回值
//     */
//    @RequestMapping("/updateVehicleTeamListSort")
//    public @ResponseBody
//    Object updateVehicleTeamListSort(@RequestParam("ids") List<Integer> ids) {
//        // TODO: 更新车队列表排序代码实现
//        return resultSuccess();
//    }
//
//    /* 车辆管理 */


    //    private final Logger log = LogManager.getLogger(InformationController.class);
    @Resource
    VehicleTeamService vehicleTeamService;

    @Resource
    VehicleService vehicleService;



    /**
     * 【车队管理】更新车队
     * @author 刘睿
     * @return
     */
    @RequestMapping("/updateVehicleTeam")
    public Object updateVehicleTeam(@RequestBody VehicleTeamDto vehicleTeamDto) {
//        id = 1;
//        name = "修改后的答案";
//        remark = "修改后的备注";
//        if (id==null||name==null)

        if (ObjectUtils.isEmpty(vehicleTeamDto) || ObjectUtils.isEmpty(vehicleTeamDto.getName()) || ObjectUtils.isEmpty(vehicleTeamDto.getId()) || vehicleTeamDto.getId() == vehicleTeamDto.getParentTeamId() )
            return resultErrorParamsNull();
//        VehicleTeamDto vehicleTeamDto = new VehicleTeamDto();
//        vehicleTeamDto.setId(id);
//        vehicleTeamDto.setName(name);
//        vehicleTeamDto.setParentTeamId(parentTeamId);
//        vehicleTeamDto.setType(type);
//        vehicleTeamDto.setVehicleCapacity(vehicleCapacity);
//        vehicleTeamDto.setContactName(contactName);
//        vehicleTeamDto.setContactPhone(contactPhone);
//        vehicleTeamDto.setRemark(remark);
        Date da = new Date();
        System.out.println(da);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("北京时间：yyyy 年 MM 月 dd 日 E HH 时 mm 分 ss 秒");
        System.out.println(simpleDateFormat.format(da));
//        articleInfo.setUpdataTime(ma1.format(da));
        Boolean key = vehicleTeamService.updateVehicleTeam(vehicleTeamDto);
        if(key==false)
        {
            return resultErrorParamsNull();
        }
        return resultSuccess();
    }

    /**
     * 【车队管理】添加车队
     * @author 刘睿
     * @return
     */
    @RequestMapping("/addVehicleTeam")
    public Object addVehicleTeam(@RequestBody VehicleTeamDto vehicleTeamDto) {
//        name = "十八组车队";
//        remark = "我是另一条备注";
//        if (name==null)
        if (ObjectUtils.isEmpty(vehicleTeamDto) || ObjectUtils.isEmpty(vehicleTeamDto.getName()) || vehicleTeamDto.getId() == vehicleTeamDto.getParentTeamId() )
            resultErrorParamsNull();
//        VehicleTeamDto vehicleTeamDto = new VehicleTeamDto();
//        vehicleTeamDto.setName(name);
//        vehicleTeamDto.setParentTeamId(parentTeamId);
//        vehicleTeamDto.setType(type);
//        vehicleTeamDto.setVehicleCapacity(vehicleCapacity);
//        vehicleTeamDto.setContactName(contactName);
//        vehicleTeamDto.setContactPhone(contactPhone);
//        vehicleTeamDto.setRemark(remark);
        Date da = new Date();
        System.out.println(da);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("北京时间：yyyy 年 MM 月 dd 日 E HH 时 mm 分 ss 秒");
        System.out.println(simpleDateFormat.format(da));
        vehicleTeamService.addVehicleTeam(vehicleTeamDto);
        return resultSuccess();
    }

    /**
     * 【车队管理】删除车队
     * @author 刘睿
     * @return
     */
    @RequestMapping("/deleteVehicleTeam")
    public Object deleteVehicleTeam(@RequestBody VehicleTeamDto vehicleTeamDto) {
        if (ObjectUtils.isEmpty(vehicleTeamDto) || ObjectUtils.isEmpty(vehicleTeamDto.getId()))
            return resultErrorParamsNull();
        Boolean key = vehicleTeamService.deleteVehicleTeam(vehicleTeamDto.getId());
        if(key == false)
        {
            return resultErrorParamsNull();
        }
        return resultSuccess();
    }

    /**
     * 【车队管理】关键字查询车队
     * @author 李达
     * @param vehicleTeamDto
     * @return
     */
    @RequestMapping("/searchVehicleTeam")
    public Object searchVehicleTeam(@RequestBody VehicleTeamDto vehicleTeamDto) {
        if (vehicleTeamDto == null || vehicleTeamDto.getName() == null) {
            return resultErrorParamsNull();
        }
        List<VehicleTeamDto> list = vehicleTeamService.searchVehicleTeam(vehicleTeamDto.getName());
        return resultSuccessRows(list);
    }

    /**
     * 【车队管理】获取车队详情
     * @author 刘睿
     * @param
     * @return
     */
    @RequestMapping("/getVehicleTeamDetail")
    public Object getVehicleTeamDetail(@RequestBody VehicleTeamDto vehicleTeamDto) {
//        id = 1;
//        if(id == 0)
        if (ObjectUtils.isEmpty(vehicleTeamDto) || ObjectUtils.isEmpty(vehicleTeamDto.getId())) {
            return resultErrorParamsNull();
        }
        VehicleTeamDto vehicleTeamEntity = vehicleTeamService.getVehicleTeamDetail(vehicleTeamDto.getId());
        if(ObjectUtils.isEmpty(vehicleTeamEntity))
        {
            return resultErrorParamsNull();
        }
        return resultSuccessData(vehicleTeamEntity);
    }
    /**
     * 【车队管理】获取车队列表
     * @author 李达
     * @param
     * @return
     */
    @RequestMapping("/getVehicleTeamList")
    public Object getVehicleTeamList() {
        List<VehicleTeamDto> vehicleTeamEntities = vehicleTeamService.getVehicleTeamList();
//        Map<Integer, VehicleTeamDto> vehicleTeamMap = new HashMap<>();
////        找到所有根节点
//        List<VehicleTeamDto> rootList = new ArrayList<>();
//        for (VehicleTeamDto vehicleTeamEntity : vehicleTeamEntities) {
//            if (vehicleTeamEntity.getParentTeamId() == 0)
//                rootList.add(vehicleTeamEntity);
//        }
//        for (VehicleTeamDto vehicleTeamEntity : rootList) {
//            vehicleTeamEntity.setChildVehicleTeam(setChildList(vehicleTeamEntity.getId(), vehicleTeamEntities));
//        }

        return resultSuccessRows(vehicleTeamEntities);

//        return null;
    }


//    //    负责递归
//    private List<VehicleTeamDto> setChildList(Integer id, List<VehicleTeamDto> vehicleTeamEntities) {
//        List<VehicleTeamDto> childList = new ArrayList<>();
//        for (VehicleTeamDto vehicleTeamEntity : vehicleTeamEntities) {
//            if (id.equals(vehicleTeamEntity.getParentTeamId()))
//                childList.add(vehicleTeamEntity);
//        }
//        if (childList.size() == 0)
//            return null;
//        for (VehicleTeamDto vehicleTeamEntity : childList) {
//            vehicleTeamEntity.setChildVehicleTeam(setChildList(vehicleTeamEntity.getId(), vehicleTeamEntities));
//        }
//        return childList;
//    }

    /**
     * 【车队管理】车队排序
     * @author 李达
     * @param
     * @return
     */
    @RequestMapping("/updateVehicleTeamListSort")
    public Object updateVehicleTeamListSort(@RequestBody UpdateVehicleTeamListSortReq req) {
        if (ObjectUtils.isEmpty(req) || ObjectUtils.isEmpty(req.getIds())) {
            return resultErrorParamsNull();
        }
        Boolean key = vehicleTeamService.updateVehicleTeamListSort(req.getIds());
        if (key == false)
        {
            return resultErrorParamsNull();
        }
        return resultSuccess();
    }

    /**
     * 【车队管理】车辆导出
     * @author 李达
     * @param
     * @return
     */
    @RequestMapping("/deriveVehicle")
    public Object deriveVehicle(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
//        String path = request.getServletContext().getContextPath();
//        System.out.println(path);
//        path = "C:\\Users\\Administrator\\Desktop\\新建文件夹";
//        String fileName = "生成的excel表格";
//        InputStream is = new FileInputStream(path+File.separator+fileName);
//        byte[] bytes = new byte[is.available()];
//        is.read(bytes);
//        MultiValueMap<String, String> headers = new HttpHeaders();
//        headers.add("Content-Disposition","attachment:filename=胖胖的emoji.dox");
//        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes,headers, HttpStatus.OK);
//        is.close();
//        return responseEntity;


//        String path = "C:\\Users\\Administrator\\Desktop\\新建文件夹";
//        File file = new File(path+File.separator+"emoji.doc");
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentDispositionFormData("attachment", "filename");
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        ResponseEntity<byte> responseEntity = new ResponseEntity<byte>(FileUtils)
//        return resultSuccess();
        String file = "C:\\Users\\Administrator\\Desktop\\新建文件夹\\emoji.doc";
        FileInputStream inputStream = new FileInputStream(file);
        byte[] data = new byte[inputStream.available()];
        inputStream.read(data);
        String diskfilename = "emoji.doc";
        response.setContentType("application/msword");
//        response.setHeader("Content-Disposition", "attachment; filename=\"" + diskfilename + "\"");
        response.setHeader("Content-Disposition", "attachment; filename=emoji.doc");
        System.out.println("data.length " + data.length);
        response.setContentLength(data.length);
        response.setHeader("Content-Range", "" + Integer.valueOf(data.length - 1));
        response.setHeader("Accept-Ranges", "bytes");
//        response.setHeader("Etag", "W/\"9767057-1323779115364\"");
        OutputStream os = response.getOutputStream();

        os.write(data);
        //先声明的流后关掉！
        os.flush();
        os.close();
        inputStream.close();
        return resultSuccess();
    }

//    @RequestMapping("/download")
//    public ResponseEntity download()
//    {
//        HttpHeaders headers = new HttpHeaders();
//        String fileName = new String("测试.xls".getBytes("UTF-8"), "iso-8859-1");//为了解决中文名称乱码问题
//        headers.setContentDispositionFormData("attachment", fileName);
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        ResponseEntity<byte[]> filebyte = new ResponseEntity<byte[]>(out.toByteArray(),headers, HttpStatus.CREATED);
//        try {
//            out.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return filebyte;
//    }

    /**
     * 批量新增车辆
     * @author 宋泽麟
     */
    @RequestMapping("/addVehicles")
    public Object addAndUpdateVehicles(Integer vehicleTeamId, MultipartFile file) throws Exception {
        if(file != null){
            String filename = file.getOriginalFilename();
            InputStream in = file.getInputStream();
            if(vehicleService.addVehicles(vehicleTeamId, filename, in)){
                return resultSuccess();
            }
            else{
                return resultError();
            }
        }
        return resultSuccess();
    }

    /**
     * 【车辆管理】单个新增和修改车辆信息
     * @author 宋泽麟
     */
    @RequestMapping("/addAndUpdateVehicle")
    public Object addAndUpdateVehicle(@RequestBody VehicleDto vehicleDto){
        if (ObjectUtils.isEmpty(vehicleDto)){
            resultErrorParamsNull();
        }
        if(vehicleDto.getUpdateOrUpdates() != null) {
            List<VehicleDto> vehicleDtoList = vehicleDto.getData();
            vehicleDtoList.forEach(data -> {
                        data.getVehicleId();
                    }
            );
            gson.toJson(vehicleDtoList);
            if (vehicleDto.getUpdateOrUpdates() == 0) {
                if (vehicleService.updateVehicle(vehicleDto.getVehicleId(),
                        vehicleDto.getVehicleTeamId(),
                        vehicleDto.getPlateNumber(),
                        vehicleDto.getVehicleName(),
                        vehicleDto.getVehiclePlateColor(),
                        vehicleDto.getTerminalIdentification(),
                        vehicleDto.getDriverId(),
                        vehicleDto.getRemarks(),
                        vehicleDto.getExplain(),
                        vehicleDto.getVehicleColor(),
                        vehicleDto.getMaxTonne(),
                        vehicleDto.getMaxPerson(),
                        vehicleDto.getInitialMileage(),
                        vehicleDto.getInitialTime(),
                        vehicleDto.getMileageUpdate(),
                        vehicleDto.getInstallArea(),
                        vehicleDto.getVin(),
                        vehicleDto.getInstallTime(),
                        vehicleDto.getVehicleType(),
                        vehicleDto.getVehicleBusiness(),
                        vehicleDto.getVehicleFrame(),
                        vehicleDto.getVehicleDomicile(),
                        vehicleDto.getBusinessName(),
                        vehicleDto.getBusinessPhone())) {
                    return resultSuccess();
                } else {
                    return resultError();
                }
            }
            if (vehicleDto.getUpdateOrUpdates() == 1) {
                for (VehicleDto v : vehicleDtoList) {
                    vehicleService.updateVehicles(Integer.parseInt(gson.toJson(v.getVehicleId())),
                            Integer.parseInt(gson.toJson(vehicleDto.getVehicleTeamId())),
                            vehicleDto.getVehiclePlateColor(),
                            vehicleDto.getLinkTime(),
                            vehicleDto.getInstallTime(),
                            vehicleDto.getExplain());
                }
                return resultSuccess();
            }
            return resultErrorParamsNull();
        }
        /*新增*/
        VehicleDto vDto = new VehicleDto();
        vDto.setPlateNumber(vehicleDto.getPlateNumber());
        vDto.setVehicleName(vehicleDto.getVehicleName());
        vDto.setVehicleTeamId(vehicleDto.getVehicleTeamId());
        vDto.setVehiclePlateColor(vehicleDto.getVehiclePlateColor());
        vDto.setTerminalIdentification(vehicleDto.getTerminalIdentification());
        vDto.setDriverId(vehicleDto.getDriverId());
        vDto.setRemarks(vehicleDto.getRemarks());
        vDto.setExplain(vehicleDto.getExplain());
        vDto.setVehicleColor(vehicleDto.getVehicleColor());
        vDto.setMaxTonne(vehicleDto.getMaxTonne());
        vDto.setMaxPerson(vehicleDto.getMaxPerson());
        vDto.setInitialMileage(vehicleDto.getInitialMileage());
        vDto.setInitialTime(vehicleDto.getInitialTime());
        vDto.setMileageUpdate(vehicleDto.getMileageUpdate());
        vDto.setInstallArea(vehicleDto.getInstallArea());
        vDto.setVin(vehicleDto.getVin());
        vDto.setInstallTime(vehicleDto.getInstallTime());
        vDto.setVehicleType(vehicleDto.getVehicleType());
        vDto.setVehicleBusiness(vehicleDto.getVehicleBusiness());
        vDto.setVehicleFrame(vehicleDto.getVehicleFrame());
        vDto.setVehicleDomicile(vehicleDto.getVehicleDomicile());
        vDto.setBusinessName(vehicleDto.getBusinessName());
        vDto.setBusinessPhone(vehicleDto.getBusinessPhone());
        if(vehicleService.addVehicle(vDto)){
            vehicleService.addVehicleBaseProperty(vDto);
            vehicleService.addVehicleOtherProperty(vDto);
            return resultSuccess();
        }
        return resultError();
    }

    /**
     * 获取车辆列表
     * condition 0-正常 1-停用 2-回收
     * @author 宋泽麟
     */
    @RequestMapping("/getVehicleList")
    public Object getVehicleList(@RequestBody VehicleDto vehicleDto){
        if (ObjectUtils.isEmpty(vehicleDto)){
            resultErrorParamsNull();
        }
        int vehicleTeamId = Integer.parseInt(gson.toJson(vehicleDto.getVehicleTeamId()));
        int condition = Integer.parseInt(gson.toJson(vehicleDto.getCondition()));
        int getVehicleListTotal = vehicleService.getVehicleListTotal(vehicleTeamId, condition);
        int first = (Integer.parseInt(gson.toJson(vehicleDto.getPage())) - 1) * Integer.parseInt(gson.toJson(vehicleDto.getRows()));
        List<VehicleDto> vehicleList = vehicleService.getVehicleList(first, Integer.parseInt(gson.toJson(vehicleDto.getRows())), vehicleTeamId, condition);
        return resultSuccessRows(getVehicleListTotal, vehicleList);
    }

    /**
     * 获取车辆详情
     */
    @RequestMapping("/getVehicleDetail")
    public Object getVehicleDetail(@RequestBody VehicleDto vehicleDto){

        return resultSuccess();
    }

    /**
     * 更新车辆状态
     * @author 宋泽麟
     * @param vehicleDto 车辆属性
     * @return 返回状态码
     */
    @RequestMapping("/updateVehicleCondition")
    public Object updateVehicleCondition(@RequestBody VehicleDto vehicleDto){
        if(ObjectUtils.isEmpty(vehicleDto)){
            return  resultErrorParamsNull();
        }
        List<VehicleDto> vehicleDtoList = vehicleDto.getData();
        vehicleDtoList.forEach(data->{
                    data.getId();
                }
        );
        gson.toJson(vehicleDtoList);
        int vehicleCondition = Integer.parseInt(gson.toJson(vehicleDto.getCondition()));
        for (VehicleDto v : vehicleDtoList) {
            vehicleService.updateVehicleCondition(v.getId(), vehicleCondition);
        }
        return resultSuccess();
    }

    /**
     * 查询车辆信息
     * @author 宋泽麟
     * @param vehicleDto 车辆属性
     * @return 返回查询总数和查询的列表
     */
    @RequestMapping("/searchVehicle")
    public Object searchVehicle(@RequestBody VehicleDto vehicleDto){
        if (ObjectUtils.isEmpty(vehicleDto)){
            resultErrorParamsNull();
        }
        List<VehicleDto> vehicleDtoList = vehicleDto.getData();
        gson.toJson(vehicleDtoList);
        VehicleDto v = vehicleDtoList.get(0);
        int searchVehicleTotal = vehicleService.getSearchVehicleTotal(v.getPlateNumber(),
                v.getVehicleName(),
                v.getTerminalIdentification(),
                v.getTerminalNumber(),
                v.getSimNumber(),
                v.getDriverName(),
                v.getCreateUser(),
                v.getMinLinkTime(),
                v.getMaxLinkTime(),
                v.getMinInstallTime(),
                v.getMaxInstallTime(),
                Integer.parseInt(gson.toJson(vehicleDto.getCondition())));
        int first = (Integer.parseInt(gson.toJson(vehicleDto.getPage())) - 1) * Integer.parseInt(gson.toJson(vehicleDto.getRows()));
        List<VehicleDto> searchVehicleList = vehicleService.searchVehicle(v.getPlateNumber(),
                v.getVehicleName(),
                v.getTerminalIdentification(),
                v.getTerminalNumber(),
                v.getSimNumber(),
                v.getDriverName(),
                v.getCreateUser(),
                v.getMinLinkTime(),
                v.getMaxLinkTime(),
                v.getMinInstallTime(),
                v.getMaxInstallTime(),
                first,
                Integer.parseInt(gson.toJson(vehicleDto.getRows())),
                Integer.parseInt(gson.toJson(vehicleDto.getCondition())));
        return resultSuccessRows(searchVehicleTotal, searchVehicleList);
    }
}

