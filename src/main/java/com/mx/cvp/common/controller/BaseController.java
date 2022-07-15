package com.mx.cvp.common.controller;

import com.mx.cvp.common.constant.RespCode;
import com.mx.cvp.common.exception.DataNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller 共通基类
 *
 * @author cikai <cikai@mxnavi.com>
 * @date 2021/9/13
 */
public class BaseController {

    private Logger logger = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler
    @ResponseBody
    public Object exception(HttpServletRequest request, Exception exception) {
        logger.error(exception.toString());
        /* 数据不存在异常 */
        if (exception instanceof DataNotFoundException) {
            return resultError(RespCode.DATA_NOT_FOUND);
        }
        return resultError();
    }

    /**
     * 应答错误
     *
     * @return
     */
    public Map<String, Object> resultError() {
        Map<String, Object> resultMap = new HashMap<>(1);
        resultMap.put("respCode", RespCode.SERVER_ERROR.value());
        return resultMap;
    }

    /**
     * 应答错误（自定义）
     *
     * @return
     */
    public Map<String, Object> resultError(RespCode respCode) {
        Map<String, Object> resultMap = new HashMap<>(1);
        resultMap.put("respCode", respCode.value());
        return resultMap;
    }

    /**
     * 应答成功
     *
     * @return
     */
    public Map<String, Object> resultSuccess() {
        Map<String, Object> resultMap = new HashMap<>(1);
        resultMap.put("respCode", RespCode.SUCCESS.value());
        return resultMap;
    }

    /**
     * 应答成功，返回自定义结构
     *
     * @return
     */
    public Map<String, Object> resultSuccessMap(Map<String, Object> resultMap) {
        resultMap.put("respCode", RespCode.SUCCESS.value());
        return resultMap;
    }

    /**
     * 应答成功，返回数据 data
     *
     * @param data 查询结果
     * @return
     */
    public Map<String, Object> resultSuccessData(Object data) {
        Map<String, Object> resultMap = new HashMap<>(2);
        resultMap.put("respCode", RespCode.SUCCESS.value());
        resultMap.put("data", data);
        return resultMap;
    }

    /**
     * 应答成功，返回数据 rows
     *
     * @param rows 查询结果
     * @return
     */
    public Map<String, Object> resultSuccessRows(Object rows) {
        Map<String, Object> resultMap = new HashMap<>(2);
        resultMap.put("respCode", RespCode.SUCCESS.value());
        resultMap.put("rows", rows);
        return resultMap;
    }

    /**
     * 应答成功，返回数据 rows
     *
     * @param rows 查询结果
     * @return
     */
    public Map<String, Object> resultSuccessRows(Object total, Object rows) {
        Map<String, Object> resultMap = new HashMap<>(3);
        resultMap.put("respCode", RespCode.SUCCESS.value());
        resultMap.put("total", total);
        resultMap.put("rows", rows);
        return resultMap;
    }

    /**
     * 应答错误，参数缺失
     *
     * @return
     */
    public Map<String, Object> resultErrorParamsNull() {
        Map<String, Object> resultMap = new HashMap<>(1);
        resultMap.put("respCode", RespCode.PARAM_ERR_NULL_PARAM.value());
        return resultMap;
    }
}
