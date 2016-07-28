/**
  * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.alipay.vbizplatform.web.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.common.lang.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.alipay.common.log.util.LogUtil;
import com.alipay.common.log.util.MethodCallResultEnum;
import com.alipay.fuellingPlatform.common.service.facade.model.CityMapModel;
import com.alipay.vbizplatform.common.util.Constant;
import com.alipay.vbizplatform.common.util.CookieTool;
import com.alipay.vbizplatform.common.util.DateUtil;
import com.alipay.vbizplatform.common.util.KeyedDigestMD5;
import com.alipay.vbizplatform.common.util.RandomUtil;
import com.alipay.vbizplatform.common.util.SpyMemcachedClient;
import com.alipay.vbizplatform.common.util.StringUtils;
import com.alipay.vbizplatform.core.model.UserModel;
import com.alipay.vbizplatform.vehicle.service.IMyCarManageService;
import com.alipay.vbizplatform.vehicle.service.IOwnerMessage;

/**
 * 用于其他tp应用完善车辆信息
 * @author yuanfeng
 * @version $Id: CarInfoInterfaceController.java, v 0.1 2016年6月17日 上午10:44:37 yuanfeng Exp $
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@RequestMapping("/tpitf")
@Controller
public class CarInfoInterfaceController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger("vbizplatform"); // 信息日志

    @Autowired
    private LogUtil             logUtil;

    @Resource(name = "spyMemcachedClient")
    private SpyMemcachedClient  spyMemcachedClient;

    @Resource(name = "myCarManageService")
    private IMyCarManageService myCarManageService;

    @Resource(name = "ownerMessage")
    private IOwnerMessage       ownerMessage;

    /**
     * tp车辆完善接口页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/carInfo")
    public String carInfo(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> pageParam = super.getParametersFromPage(request);
        long startTime = System.currentTimeMillis();
        UserModel userModel = super.getUserInfo(request);
        try {
            /**** test_begin ****/
//            if (userModel == null) {
//                userModel = new UserModel();
//                //                userModel.setUid("2015052100077120" + RandomUtil.getFixLenthString(3));
//                userModel.setUid("20150521000770120774");
//                userModel.setPhoneNumber("13810331329");
//                userModel.setRealName("chezhutest");
//                userModel.setCertNo("2301012345678905678");
//                request.getSession().setAttribute(Constant.ALIPAY_USER_SESSION_KEY, userModel);
//            }
            /******test_end************/
            String vehicleMustParamKey = userModel.getUid()
                                         + Constant.TP_VC_MODEL_MUSTPARAM_MEM_KEY;
            String vehicleMapKey = userModel.getUid() + Constant.TP_VEHICLE_MODEL_MEM_KEY;
            //1、校验必填参数
            String mustParam = pageParam.get("mustParam"); //必填参数
            List<String> mustParamList = null;
            if (StringUtils.isNotEmpty(mustParam)) { //必填参数不为空
                if (mustParam.indexOf(",") != -1) {
                    String[] mustParams = mustParam.split(",");
                    mustParamList = Arrays.asList(mustParams);
                } else {
                    mustParamList = new ArrayList<String>();
                    mustParamList.add(mustParam);
                }
                mustParamList = this.mustParamToWebId(mustParamList);
                //必填参数保存在ocs中,用于后续页面跳转使用
                spyMemcachedClient.set(vehicleMustParamKey, Constant.MEMCACHED_SAVETIME_24,
                    mustParamList);
            } else {
                mustParamList = spyMemcachedClient.get(vehicleMustParamKey);
            }
            //必填参数传入页面
            request.setAttribute("mustParamList", JSONObject.toJSON(mustParamList));
            //2、判断车辆id
            String viId = pageParam.get("viId");
            if (StringUtils.isNotEmpty(viId)) { //车辆id不为空
                //根据tp用户id和车辆id查询车辆信息
                Map<String, Object> vehicleMap = myCarManageService
                    .getVehicleInfo(userModel.getUid(), viId);
                if (vehicleMap == null) {
                    if (logger.isErrorEnabled()) {
                        logger.error("TP提供的车辆id与会话中的用户信息不匹配，uid={},viId={}", userModel.getUid(),
                            viId);
                    }
                } else {
                    //车辆信息保存在ocs中,用于后续页面跳转使用
                    spyMemcachedClient.set(vehicleMapKey, Constant.MEMCACHED_SAVETIME_24,
                        vehicleMap);
                    request.setAttribute("vehicleModel", vehicleMap);
                    String viNumber = String.valueOf(vehicleMap.get("viNumber"));// 车牌
                    if (StringUtils.isNotEmpty(viNumber) && viNumber.length() > 1) {
                        request.setAttribute("cityAB", viNumber.substring(0, 1)); // 车牌地区简写
                        request.setAttribute("carNumber", viNumber.substring(1, viNumber.length())); // 车牌数字
                    }
                    if (vehicleMap.get("viStartTime") != null) { // 上路时间,转换成yyyy-mm格式
                        request.setAttribute("viStartTime", DateUtil.parserDateToString(
                            (Date) vehicleMap.get("viStartTime"), DateUtil.DATEFORMAT5));
                    }
                    logUtil.log(new Throwable(), "CARINFO", userModel.getUid(),
                        MethodCallResultEnum.SUCCESS, null, "处理TP请求进入车辆信息完善页面成功", startTime);
                    //车辆信息修改验证
                    if (mustParamList != null) {
                        //车辆信息拼接串
                        StringBuilder vehicleStr = new StringBuilder(
                            ObjectUtil.toString(vehicleMap.get("viNumber")));
                        for (String str : mustParamList) {
                            if (str.lastIndexOf("Li") != -1) {
                                vehicleStr.append(
                                    ObjectUtil.toString(vehicleMap.get(str.replace("Li", ""))));
                            }
                        }
                        String vehicleStrMd5 = KeyedDigestMD5.getKeyedDigest(vehicleStr.toString(),
                            "");
                        //车辆信息md5保存在ocs中,用于后续页面验证是否修改
                        spyMemcachedClient.set(vehicleMapKey + "MD5",
                            Constant.MEMCACHED_SAVETIME_24, vehicleStrMd5);
                        request.setAttribute("vehicleStrMd5", vehicleStrMd5);
                    }
                    return "page/tpcar/tpCarsDetail";
                }
            } else {
                //车辆信息保存在ocs中,用于后续页面跳转使用
                spyMemcachedClient.set(vehicleMapKey, Constant.MEMCACHED_SAVETIME_24,
                    new HashMap<String, Object>());
            }
            //3、获取tp用户常驻城市车牌的简写
            Cookie cityCodeCookie = CookieTool.getCookieByName(request, "aliPay_residentCityCode");
            String cityCode = "330100";//杭州
            if (cityCodeCookie != null) {
                cityCode = cityCodeCookie.getValue();
            } else {
                Object object = request.getSession()
                    .getAttribute(Constant.ALIPAY_USER_LOCATION_SESSION_KEY);
                if (object != null
                    && StringUtils.isNotEmpty(((Map<String, String>) object).get("cityCode"))) {
                    cityCode = ((Map<String, String>) object).get("cityCode");
                }
            }
            CityMapModel cityMapModel = ownerMessage
                .queryCityMapInfo(URLDecoder.decode(cityCode, "UTF-8"));
            if (null != cityMapModel) {
                request.setAttribute("cityAB", cityMapModel.getCarNoPrefix()); // 车牌地区简写
            }
            // 4、查询车主个人车辆信息
            List<Map<String, Object>> vehicleList = myCarManageService
                .queryVehicleListByUid(userModel.getUid());
            request.setAttribute("carSize", vehicleList == null ? 0 : vehicleList.size());
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("处理tp用户从isv平台请求进入车辆信息增加页面异常", e);
            }
            logUtil.log(new Throwable(), "CARINFO", userModel.getUid(),
                MethodCallResultEnum.EXCEPTION, null, "处理tp用户请求进入车辆信息完善页面异常", startTime);
            return "page/error/tp_error_mang";
        }
        logUtil.log(new Throwable(), "CARINFO", userModel.getUid(), MethodCallResultEnum.SUCCESS,
            null, "处理TP请求进入车辆信息新增页面成功", startTime);
        return "page/tpcar/tpCompletion";
    }

    /**
     * 进入tp选择车辆品牌
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryBrand")
    public String queryBrand(HttpServletRequest request, HttpServletResponse response) {
        logger.info("uid={},class={},method=queryBrand,desc=查询车辆品牌",
            super.getUserInfo(request).getUid(), this.getClass().getName());
        Map<String, String> pageParam = super.getParametersFromPage(request);
        long startTime = System.currentTimeMillis();
        UserModel userModel = super.getUserInfo(request);
        try {
            // 1、获取所有车辆品牌信息
            Map<String, List<Map<String, Object>>> brandsMap = myCarManageService.getBrands();
            //1、session中取出暂存车辆信息
            //车辆信息
            Map<String, Object> vo = null;
            String vehicleMapKey = userModel.getUid() + Constant.TP_VEHICLE_MODEL_MEM_KEY;
            Object obj = spyMemcachedClient.get(vehicleMapKey);
            if (obj == null) {
                vo = new HashMap<String, Object>();
            } else {
                vo = (HashMap<String, Object>) obj;
            }
            /*****页面传入参数存入车辆信息vo******/
            if (StringUtils.isNotEmpty(pageParam.get("viNumber"))) {
                vo.put("viNumber",
                    URLDecoder.decode(pageParam.get("viNumber"), "UTF-8").toUpperCase());
            }
            if (StringUtils.isNotEmpty(pageParam.get("viStartTime"))) {// 上路时间
                vo.put("viStartTime", DateUtil.parserDateFromString(pageParam.get("viStartTime"),
                    DateUtil.DATEFORMAT5));
            }
            if (StringUtils.isNotEmpty(pageParam.get("vlCityId"))) {// 车辆归属地
                vo.put("vlCityId", pageParam.get("vlCityId"));
            }
            if (StringUtils.isNotEmpty(pageParam.get("vlCityName"))) {// 车辆归属地名称
                vo.put("vlCityName", URLDecoder.decode(pageParam.get("vlCityName"), "UTF-8"));
            }
            if (StringUtils.isNotEmpty(pageParam.get("viMileage"))) {// 行使里程
                vo.put("viMileage", pageParam.get("viMileage"));
            }
            if (StringUtils.isNotEmpty(pageParam.get("viVin"))) {// 车架号
                vo.put("viVin", pageParam.get("viVin").toUpperCase());
            }
            if (StringUtils.isNotEmpty(pageParam.get("engineNo"))) {// 发动机号
                vo.put("engineNo", pageParam.get("engineNo").toUpperCase());
            }
            spyMemcachedClient.set(vehicleMapKey, Constant.MEMCACHED_SAVETIME_24, vo);
            //2、品牌信息传给页面
            request.setAttribute("brandsMap", brandsMap);
            request.setAttribute("brandSize", brandsMap.size());
            request.setAttribute("upst", pageParam.get("upst"));
            request.setAttribute("viId", pageParam.get("viId"));
            request.setAttribute("newCarFlag", pageParam.get("newCarFlag"));
            request.setAttribute("fromPage", pageParam.get("fromPage"));
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("处理tp用户请求进入添加爱车页面异常", e);
            }
            logUtil.log(new Throwable(), "BRAND", userModel.getUid(),
                MethodCallResultEnum.EXCEPTION, null, "处理tp用户请求进入添加爱车页面异常", startTime);
            return "page/error/tp_error_mang";
        }
        if (logger.isInfoEnabled()) {
            logger.info("tp查询品牌信息成功,uid={},toPage->page/tpcar/tpSelectBrand.jsp",
                super.getUserInfo(request).getUid());
        }
        logUtil.log(new Throwable(), "BRAND", userModel.getUid(), MethodCallResultEnum.SUCCESS,
            null, "tp用户请求进入选择品牌页面成功", startTime);
        return "page/tpcar/tpSelectBrand";
    }

    /**
     * tp查询汽车品牌相关车系
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/queryCarSeries")
    public String queryCarSeries(HttpServletRequest request, HttpServletResponse response) {
        logger.info("uid={},class={},method=queryCarSeries,desc=查询车辆车系",
            super.getUserInfo(request).getUid(), this.getClass().getName());
        Map<String, String> pageParam = super.getParametersFromPage(request);
        long startTime = System.currentTimeMillis();
        String brandName = null;
        // 品牌图片
        String picUrl = null;
        //背景图片
        String bgUrl = null;
        UserModel userModel = super.getUserInfo(request);
        //车系信息
        Map<String, List<Map<String, Object>>> vehicleSeciesMap = null;
        try {
            if (StringUtils.isNotEmpty(pageParam.get("brandName"))) {
                brandName = URLDecoder.decode(pageParam.get("brandName"), "UTF-8");// 车辆品牌
            }
            if (StringUtils.isNotEmpty(pageParam.get("picUrl"))) {
                picUrl = URLDecoder.decode(pageParam.get("picUrl"), "UTF-8");// 品牌id
            }
            if (StringUtils.isNotEmpty(pageParam.get("bgUrl"))) {
                bgUrl = URLDecoder.decode(pageParam.get("bgUrl"), "UTF-8");// 品牌背景图片
            }
            // 1、根据车辆品牌查询车系
            vehicleSeciesMap = myCarManageService.getVehicleSecies(brandName);
            request.setAttribute("viId", pageParam.get("viId"));
            request.setAttribute("carSeriesMap", vehicleSeciesMap);
            request.setAttribute("brandName", brandName);
            request.setAttribute("picUrl", picUrl);
            request.setAttribute("bgUrl", bgUrl);
            request.setAttribute("upst", pageParam.get("upst"));

            request.setAttribute("newCarFlag", pageParam.get("newCarFlag"));
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("处理tp用户请求查询车系异常", e);
            }
            logUtil.log(new Throwable(), "CARSERIES", userModel.getUid(),
                MethodCallResultEnum.EXCEPTION, null, "处理tp用户请求查询车系异常", startTime);
            return "page/error/tp_error_mang";
        }
        if (logger.isInfoEnabled()) {
            logger.info("tp用户请求查询车系信息成功,uid={},toPage->page/tpcar/tpSelectCarSeries.jsp",
                super.getUserInfo(request).getUid());
        }
        logUtil.log(new Throwable(), "CARSERIES", userModel.getUid(), MethodCallResultEnum.SUCCESS,
            null, "处理tp用户请求查询车系成功", startTime);
        return "page/tpcar/tpSelectCarSeries";
    }

    /**
     * tp进入车辆信息完善页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/newCarInfo")
    public String newCarInfo(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> pageParam = super.getParametersFromPage(request);
        // 车辆型号名称        
        Map<String, Object> vo = null;
        //厂商名称
        String manufacturer = null;
        // 车系名称
        String carSeriesName = null;
        long startTime = System.currentTimeMillis();
        UserModel userModel = super.getUserInfo(request);
        try {
            String vehicleMapKey = userModel.getUid() + Constant.TP_VEHICLE_MODEL_MEM_KEY;
            Object obj = spyMemcachedClient.get(vehicleMapKey);
            if (obj == null) {
                vo = new HashMap<String, Object>();
                this.spyMemcachedClient.set(vehicleMapKey, Constant.MEMCACHED_SAVETIME_24, vo);
            } else {
                vo = (HashMap<String, Object>) obj;
            }
            /*****页面传入参数存入车辆信息vo******/
            if (StringUtils.isNotEmpty(pageParam.get("carSeriesName"))) {
                // 车系名称
                carSeriesName = URLDecoder.decode(pageParam.get("carSeriesName"), "UTF-8");
                if (!(ObjectUtil.toString(vo.get("viSeriesName")).equals(carSeriesName))) {
                    vo.put("viSeriesName", carSeriesName);
                    //品牌图片
                    vo.put("viBrandName", URLDecoder.decode(pageParam.get("brandName"), "UTF-8"));
                    //品牌名称
                    vo.put("viBrandLogo", URLDecoder.decode(pageParam.get("picUrl"), "UTF-8"));
                    //车系图片
                    vo.put("viLogoUrl", URLDecoder.decode(pageParam.get("modPicUrl"), "UTF-8"));
                    //品牌背景
                    vo.put("bgUrl", URLDecoder.decode(pageParam.get("bgUrl"), "UTF-8"));
                    //厂商名称
                    manufacturer = URLDecoder.decode(pageParam.get("manufacturer"), "UTF-8");
                    vo.put("manufacturer", manufacturer);
                    //修改车系
                    vo.put("sweptVolume", "");
                    vo.put("viStyleName", "");
                    vo.put("viModelName", "");
                    vo.put("viModelId", "");
                    vo.put("engineType", "");
                }
            }
            //判断是否存在发动机型号
            List<String> carEngineList = request.getAttribute("carEngineList") == null
                ? new ArrayList<String>() : (List<String>) request.getAttribute("carEngineList");
            String carEngineFlag = pageParam.get("carEngineFlag");
            if (!carEngineList.isEmpty() || "false".equals(carEngineFlag)) {
                String engineType = "";
                if (StringUtils.isNotEmpty(pageParam.get("engineType"))) {
                    // 车系名称
                    engineType = URLDecoder.decode(pageParam.get("engineType"), "UTF-8");
                } else {
                    engineType = carEngineList.get(0);
                }
                if (null != engineType
                    && !(ObjectUtil.toString(vo.get("engineType")).equals(engineType))) {
                    String sweptVolume = URLDecoder
                        .decode(ObjectUtil.toString(pageParam.get("sweptVolume")), "UTF-8");
                    String productionYear = URLDecoder
                        .decode(ObjectUtil.toString(pageParam.get("carYearName")), "UTF-8");
                    String carModelName = URLDecoder
                        .decode(ObjectUtil.toString(pageParam.get("carModelName")), "UTF-8");
                    //排量
                    vo.put("sweptVolume", sweptVolume);
                    //生产年份
                    vo.put("productionYear", productionYear);
                    //车型
                    vo.put("viModelName", carModelName);
                    //发动机型号
                    vo.put("engineType", engineType);
                    Map<String, Object> map = getVihcleInfo(
                        ObjectUtil.toString(vo.get("manufacturer")),
                        ObjectUtil.toString(vo.get("viSeriesName")), sweptVolume, productionYear,
                        carModelName, engineType);
                    //年份
                    vo.put("viStyleName", ObjectUtil.toString(map.get("style")));
                }
            } else {
                if (StringUtils.isNotEmpty(pageParam.get("carModelName"))) {
                    // 车系名称
                    String carModelName = URLDecoder.decode(pageParam.get("carModelName"), "UTF-8");
                    if (null != carModelName
                        && !(ObjectUtil.toString(vo.get("viModelName")).equals(carModelName))) {
                        String sweptVolume = URLDecoder
                            .decode(ObjectUtil.toString(pageParam.get("sweptVolume")), "UTF-8");
                        String productionYear = URLDecoder
                            .decode(ObjectUtil.toString(pageParam.get("carYearName")), "UTF-8");
                        //排量
                        vo.put("sweptVolume", sweptVolume);
                        //年份
                        //生产年份
                        vo.put("productionYear", productionYear);
                        //车型
                        vo.put("viModelName", carModelName);
                        Map<String, Object> map = getVihcleInfo(
                            ObjectUtil.toString(vo.get("manufacturer")),
                            ObjectUtil.toString(vo.get("viSeriesName")), sweptVolume,
                            productionYear, carModelName, "填充数据类型");
                        //年份
                        vo.put("viStyleName", ObjectUtil.toString(map.get("style")));
                    }
                }
            }
            // 从cookie中取出常驻城市
            if (StringUtils.isEmpty(ObjectUtils.toString(vo.get("vlCityName")))) {
                Map<String, String> cityInfo = getCityInfo(request);
                if (!cityInfo.isEmpty()) {
                    vo.put("vlCityId", cityInfo.get("resident_city_code"));
                    vo.put("vlCityName", cityInfo.get("resident_city_name"));
                }
            }
            this.spyMemcachedClient.set(vehicleMapKey, Constant.MEMCACHED_SAVETIME_24, vo);

            request.setAttribute("vehicleModel", vo);
            String viNumber = String.valueOf(vo.get("viNumber")); // 车牌
            if (StringUtils.isNotEmpty(viNumber) && viNumber.length() >= 1) {
                request.setAttribute("cityAB", viNumber.substring(0, 1)); // 车牌地区简写
                request.setAttribute("carNumber", viNumber.substring(1, viNumber.length())); // 车牌数字
            } else {
                if (!StringUtils.isEmpty(ObjectUtil.toString(vo.get("vlCityId")))
                    && (StringUtils.isEmpty(viNumber))) {
                    CityMapModel cityCode = ownerMessage
                        .queryCityMapInfo(vo.get("vlCityId").toString());
                    if (null != cityCode)
                        request.setAttribute("cityAB", cityCode.getCarNoPrefix()); // 车牌地区简写
                }
            }
            if (vo.get("viStartTime") != null) { // 上路时间
                request.setAttribute("viStartTime", DateUtil
                    .parserDateToString((Date) vo.get("viStartTime"), DateUtil.DATEFORMAT5));
            }
            //从ocs中取出必填参数
            String vehicleMustParamKey = userModel.getUid()
                                         + Constant.TP_VC_MODEL_MUSTPARAM_MEM_KEY;
            List<String> mustParamList = spyMemcachedClient.get(vehicleMustParamKey);
            //必填参数传入页面
            request.setAttribute("mustParamList", JSONObject.toJSON(mustParamList));
            // 查询车主个人车辆信息
            List<Map<String, Object>> vehicleList = myCarManageService
                .queryVehicleListByUid(userModel.getUid());
            request.setAttribute("carSize", vehicleList == null ? 0 : vehicleList.size());
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("处理tp用户请求进入车辆信息完善页面异常", e);
            }
            logUtil.log(new Throwable(), "CARINFO", userModel.getUid(),
                MethodCallResultEnum.EXCEPTION, null, "处理tp用户请求进入车辆信息完善页面异常", startTime);
            return "page/error/tp_error_mang";
        }
        logUtil.log(new Throwable(), "CARINFO", userModel.getUid(), MethodCallResultEnum.SUCCESS,
            null, "处理tp用户请求进入车辆信息完善页面成功", startTime);
        return "page/tpcar/tpCompletion";
    }

    /**
     * tp进入修改车辆信息页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/toCarInfo")
    public String toCarInfo(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> pageParam = super.getParametersFromPage(request);
        long startTime = System.currentTimeMillis();
        try {
            // 1、session中取出车主信息
            UserModel userModel = super.getUserInfo(request);
            String carSeriesName = null;
            String manufacturer = null;
            Map<String, Object> updateVo = null;
            String vehicleMapKey = userModel.getUid() + Constant.TP_VEHICLE_MODEL_MEM_KEY;
            Object obj = spyMemcachedClient.get(vehicleMapKey);
            if (obj != null) {
                updateVo = (HashMap<String, Object>) obj;
            } else {
                if (logger.isErrorEnabled()) {
                    logger.error("缓存中未获取到车辆信息");
                }
                logUtil.log(new Throwable(), "CARINFO", userModel.getUid(),
                    MethodCallResultEnum.EXCEPTION, null, "处理tp用户请求进入车辆信息修改页面异常", startTime);
                return "page/error/tp_error_mang";
            }
            if (StringUtils.isNotEmpty(pageParam.get("carSeriesName"))) {
                // 车系名称
                carSeriesName = URLDecoder.decode(pageParam.get("carSeriesName"), "UTF-8");
                if (!(ObjectUtil.toString(updateVo.get("viSeriesName")).equals(carSeriesName))) {
                    updateVo.put("viSeriesName", carSeriesName);
                    //品牌图片
                    updateVo.put("viBrandName",
                        URLDecoder.decode(pageParam.get("brandName"), "UTF-8"));
                    //品牌名称
                    updateVo.put("viBrandLogo",
                        URLDecoder.decode(pageParam.get("picUrl"), "UTF-8"));
                    //车系图片
                    updateVo.put("viLogoUrl",
                        URLDecoder.decode(pageParam.get("modPicUrl"), "UTF-8"));
                    //品牌背景
                    updateVo.put("bgUrl", URLDecoder.decode(pageParam.get("bgUrl"), "UTF-8"));
                    //厂商名称
                    manufacturer = URLDecoder.decode(pageParam.get("manufacturer"), "UTF-8");
                    updateVo.put("manufacturer", manufacturer);
                    //修改车系
                    updateVo.put("sweptVolume", "");
                    updateVo.put("viStyleName", "");
                    updateVo.put("viModelName", "");
                    updateVo.put("viModelId", "");
                    updateVo.put("engineType", "");
                    updateVo.put("productionYear", "");
                }
            }
            //判断是否存在发动机型号
            List<String> carEngineList = request.getAttribute("carEngineList") == null
                ? new ArrayList<String>() : (List<String>) request.getAttribute("carEngineList");
            String carEngineFlag = pageParam.get("carEngineFlag");
            if (!carEngineList.isEmpty() || "false".equals(carEngineFlag)) {
                String engineType = null;
                if (StringUtils.isNotEmpty(pageParam.get("engineType"))) {
                    // 车系名称
                    engineType = URLDecoder.decode(pageParam.get("engineType"), "UTF-8");
                } else {
                    engineType = carEngineList.get(0);
                }
                if (StringUtils.isNotEmpty(engineType)
                    && !(ObjectUtil.toString(updateVo.get("engineType")).equals(engineType))) {
                    String sweptVolume = ObjectUtil.toString(pageParam.get("sweptVolume"));
                    String productionYear = URLDecoder
                        .decode(ObjectUtil.toString(pageParam.get("carYearName")), "UTF-8");
                    String carModelName = URLDecoder
                        .decode(ObjectUtil.toString(pageParam.get("carModelName")), "UTF-8");
                    //排量
                    updateVo.put("sweptVolume", sweptVolume);
                    //生产年份
                    updateVo.put("productionYear", productionYear);
                    //车型
                    updateVo.put("viModelName", carModelName);
                    //发动机型号
                    updateVo.put("engineType", engineType);

                    Map<String, Object> map = getVihcleInfo(
                        ObjectUtil.toString(updateVo.get("manufacturer")),
                        ObjectUtil.toString(updateVo.get("viSeriesName")), sweptVolume,
                        productionYear, carModelName, engineType);
                    //年份
                    updateVo.put("viStyleName", ObjectUtil.toString(map.get("style")));
                }
            } else {
                if (StringUtils.isNotEmpty(pageParam.get("carModelName"))) {
                    // 车系名称
                    String carModelName = URLDecoder.decode(pageParam.get("carModelName"), "UTF-8");
                    if (!(ObjectUtil.toString(updateVo.get("viModelName")).equals(carModelName))) {
                        String sweptVolume = ObjectUtil.toString(pageParam.get("sweptVolume"));
                        String productionYear = URLDecoder
                            .decode(ObjectUtil.toString(pageParam.get("carYearName")), "UTF-8");
                        //排量
                        updateVo.put("sweptVolume", sweptVolume);
                        //生产年份
                        updateVo.put("productionYear", productionYear);
                        //车型
                        updateVo.put("viModelName", carModelName);
                        Map<String, Object> map = getVihcleInfo(
                            ObjectUtil.toString(updateVo.get("manufacturer")),
                            ObjectUtil.toString(updateVo.get("viSeriesName")), sweptVolume,
                            productionYear, carModelName, "填充数据类型");
                        //年份
                        updateVo.put("viStyleName", ObjectUtil.toString(map.get("style")));
                    }
                }
            }
            this.spyMemcachedClient.set(vehicleMapKey, Constant.MEMCACHED_SAVETIME_24, updateVo);
            request.setAttribute("vehicleModel", updateVo);
            String viNumber = String.valueOf(updateVo.get("viNumber"));// 车牌
            if (StringUtils.isNotEmpty(viNumber) && viNumber.length() > 1) {
                request.setAttribute("cityAB", viNumber.substring(0, 1)); // 车牌地区简写
                request.setAttribute("carNumber", viNumber.substring(1, viNumber.length())); // 车牌数字
            }
            if (updateVo.get("viStartTime") != null) { // 上路时间,转换成yyyy-mm格式
                request.setAttribute("viStartTime", DateUtil
                    .parserDateToString((Date) updateVo.get("viStartTime"), DateUtil.DATEFORMAT5));
            }
            String vehicleMustParamKey = userModel.getUid()
                                         + Constant.TP_VC_MODEL_MUSTPARAM_MEM_KEY;
            List<String> mustParamList = spyMemcachedClient.get(vehicleMustParamKey);
            //必填参数传入页面
            request.setAttribute("mustParamList", JSONObject.toJSON(mustParamList));
            //ocs中取出车辆信息md5
            String vehicleStrMd5 = spyMemcachedClient.get(vehicleMapKey + "MD5");
            request.setAttribute("vehicleStrMd5", vehicleStrMd5);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("处理tp用户请求进入车辆修改页面异常", e);
            }
            return "page/error/tp_error_mang";
        }
        return "page/tpcar/tpCarsDetail";
    }

    /**
     * tp查询车辆排量信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/queryCarCC")
    public String queryCarCC(HttpServletRequest request, HttpServletResponse response) {
        logger.info("uid={},class={},method=queryCarCC,desc=查询车辆排量",
            super.getUserInfo(request).getUid(), this.getClass().getName());
        Map<String, String> pageParam = super.getParametersFromPage(request);
        long startTime = System.currentTimeMillis();
        //车辆信息
        Map<String, Object> vo = null;
        //年款
        Map<String, Map> vehicleTypeListMap = null;
        UserModel userModel = super.getUserInfo(request);
        try {
            String vehicleMapKey = userModel.getUid() + Constant.TP_VEHICLE_MODEL_MEM_KEY;
            Object obj = spyMemcachedClient.get(vehicleMapKey);
            if (obj != null) {
                vo = (HashMap<String, Object>) obj;
                /*****页面传入参数存入车辆信息vo******/
                if (StringUtils.isNotEmpty(pageParam.get("viNumber"))) {
                    vo.put("viNumber",
                        URLDecoder.decode(pageParam.get("viNumber"), "UTF-8").toUpperCase());
                }
                if (StringUtils.isNotEmpty(pageParam.get("viStartTime"))) {// 上路时间
                    vo.put("viStartTime", DateUtil
                        .parserDateFromString(pageParam.get("viStartTime"), DateUtil.DATEFORMAT5));
                }
                if (StringUtils.isNotEmpty(pageParam.get("vlCityId"))) {// 车辆归属地
                    vo.put("vlCityId", pageParam.get("vlCityId"));
                }
                if (StringUtils.isNotEmpty(pageParam.get("vlCityName"))) {// 车辆归属地名称
                    vo.put("vlCityName", URLDecoder.decode(pageParam.get("vlCityName"), "UTF-8"));
                }
                if (StringUtils.isNotEmpty(pageParam.get("viMileage"))) {// 行使里程
                    vo.put("viMileage", pageParam.get("viMileage"));
                }
                if (StringUtils.isNotEmpty(pageParam.get("viVin"))) {// 车架号
                    vo.put("viVin", pageParam.get("viVin").toUpperCase());
                }
                if (StringUtils.isNotEmpty(pageParam.get("engineNo"))) {// 发动机号
                    vo.put("engineNo", pageParam.get("engineNo").toUpperCase());
                }
                spyMemcachedClient.set(vehicleMapKey, Constant.MEMCACHED_SAVETIME_24, vo);
                // 页面回传参数
                request.setAttribute("brandName", vo.get("viBrandName"));
                request.setAttribute("carSeriesName", vo.get("viSeriesName"));
                if (StringUtils.isNotEmpty(ObjectUtil.toString(vo.get("viLogoUrl")))) {
                    request.setAttribute("modPicUrl", vo.get("viLogoUrl"));
                } else {
                    request.setAttribute("modPicUrl", vo.get("viBrandLogo"));
                }
                request.setAttribute("viId", vo.get("viId"));
                request.setAttribute("upst", pageParam.get("upst"));
            } else { // 跳转到错误页面
                if (logger.isErrorEnabled()) {
                    logger.error("session中未获取到车辆临时信息,toPage->/page/error/tp_error_mang.jsp");
                }
                logUtil.log(new Throwable(), "CARCC", userModel.getUid(),
                    MethodCallResultEnum.EXCEPTION, null, "tp用户请求获取车辆排量异常，session中未获取到车辆临时信息",
                    startTime);
                return "page/error/tp_error_mang";
            }
            // 1、根据品牌和车系查询车型相关信息
            vehicleTypeListMap = myCarManageService.getVehiclesNow(
                String.valueOf(vo.get("manufacturer")), String.valueOf(vo.get("viSeriesName")));
            //年款map转set
            List<String> carCCList = null;
            if (vehicleTypeListMap != null && !vehicleTypeListMap.isEmpty()) {
                carCCList = new ArrayList<String>();
                for (Map.Entry<String, Map> entry : vehicleTypeListMap.entrySet()) {
                    carCCList.add(entry.getKey());
                }
                request.setAttribute("carCCList", carCCList);
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("处理tp用户请求查询车辆排量信息异常", e);
            }
            logUtil.log(new Throwable(), "CARCC", userModel.getUid(),
                MethodCallResultEnum.EXCEPTION, null, "处理tp用户请求查询车辆排量信息异常", startTime);
            super.redirectErrorPage("BUSY-ERR", "系统正忙、客官请稍候", null, null, response);
            return "page/error/tp_error_mang";
        }
        logUtil.log(new Throwable(), "CARCC", userModel.getUid(), MethodCallResultEnum.SUCCESS,
            null, "处理tp用户请求查询车辆排量信息成功", startTime);
        return "page/tpcar/tpSelectCarCc";
    }

    /**
     * tp查询车辆年款信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/queryCarYear")
    public String queryCarYear(HttpServletRequest request, HttpServletResponse response) {
        logger.info("uid={},class={},method=queryCarYear,desc=查询车辆年款",
            super.getUserInfo(request).getUid(), this.getClass().getName());
        Map<String, String> pageParam = super.getParametersFromPage(request);
        //车辆信息
        Map<String, Object> vo = null;
        //年款
        Map<String, Map> vehicleTypeListMap = null;
        long startTime = System.currentTimeMillis();
        UserModel userModel = super.getUserInfo(request);
        try {
            //1、ocs中取出暂存车辆信息
            String vehicleMapKey = userModel.getUid() + Constant.TP_VEHICLE_MODEL_MEM_KEY;
            Object obj = spyMemcachedClient.get(vehicleMapKey);
            if (obj != null) {
                vo = (HashMap<String, Object>) obj;
                /*****页面传入参数存入车辆信息vo******/
                // 页面回传参数
                request.setAttribute("brandName", vo.get("viBrandName"));
                request.setAttribute("carSeriesName", vo.get("viSeriesName"));
                if (StringUtils.isNotEmpty(ObjectUtil.toString(vo.get("viLogoUrl")))) {
                    request.setAttribute("modPicUrl", vo.get("viLogoUrl"));
                } else {
                    request.setAttribute("modPicUrl", vo.get("viBrandLogo"));
                }
                request.setAttribute("sweptVolume", pageParam.get("sweptVolume"));
                request.setAttribute("viId", vo.get("viId"));
                request.setAttribute("upst", pageParam.get("upst"));
            } else { // 跳转到车辆列表界面
                if (logger.isErrorEnabled()) {
                    logger.error("session中未获取到车辆临时信息tp,toPage->/page/error/tp_error_mang.jsp");
                }
                logUtil.log(new Throwable(), "CARYEAR", userModel.getUid(),
                    MethodCallResultEnum.EXCEPTION, null, "session中未获取到车辆临时信息,tp用户请求车辆年份异常",
                    startTime);
                return "page/error/tp_error_mang";
            }
            //缓存区
            // 1、根据品牌和车系查询车型相关信息
            vehicleTypeListMap = myCarManageService.getVehiclesNow(
                String.valueOf(vo.get("manufacturer")), String.valueOf(vo.get("viSeriesName")));
            //年款map转set
            List<String> carYearList = null;
            if (vehicleTypeListMap != null && !vehicleTypeListMap.isEmpty()) {
                Map<String, Object> YearNode = vehicleTypeListMap
                    .get(URLDecoder.decode(pageParam.get("sweptVolume"), "UTF-8"));
                carYearList = new ArrayList<String>();
                if (null != YearNode) {
                    for (Map.Entry<String, Object> entry : YearNode.entrySet()) {
                        carYearList.add(entry.getKey());
                    }
                }
                request.setAttribute("carYearList", carYearList);
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("处理tp用户请求查询车辆年份信息异常", e);
            }
            logUtil.log(new Throwable(), "CARYEAR", userModel.getUid(),
                MethodCallResultEnum.EXCEPTION, null, "处理tp用户请求查询车辆年份信息异常", startTime);
            super.redirectErrorPage("BUSY-ERR", "系统正忙、客官请稍候", null, null, response);
            return "page/error/tp_error_mang";
        }
        logUtil.log(new Throwable(), "CARYEAR", userModel.getUid(), MethodCallResultEnum.SUCCESS,
            null, "处理tp用户请求查询车辆年份信息成功", startTime);
        return "page/tpcar/tpSelectCarvProductionDate";
    }

    /**
     * tp查询车系相关的车辆型号
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/queryCarModels")
    public String queryCarModels(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> pageParam = super.getParametersFromPage(request);
        // 车辆年款名称
        Map<String, Object> vo = null;
        long startTime = System.currentTimeMillis();
        //车型
        Map<String, Map> vehicleTypeListMap = null;
        UserModel userModel = super.getUserInfo(request);
        try {
            //1、ocs中取出暂存车辆信息
            String vehicleMapKey = userModel.getUid() + Constant.TP_VEHICLE_MODEL_MEM_KEY;
            Object obj = spyMemcachedClient.get(vehicleMapKey);
            if (obj != null) {
                vo = (HashMap<String, Object>) obj;
                request.setAttribute("brandName", vo.get("viBrandName"));
                request.setAttribute("seriesName", vo.get("viSeriesName"));
                request.setAttribute("sweptVolume", pageParam.get("sweptVolume"));
                request.setAttribute("carYearName",
                    URLDecoder.decode(pageParam.get("carYearName"), "UTF-8"));
                request.setAttribute("viId", vo.get("viId"));
                request.setAttribute("upst", pageParam.get("upst"));
            } else { // 跳转到错误页面
                logUtil.log(new Throwable(), "CARMODELS", userModel.getUid(),
                    MethodCallResultEnum.EXCEPTION, null, "tp用户请求获取车辆型号异常", startTime);
                return "page/error/tp_error_mang";
            }
            // 1、根据品牌和车系查询车型相关信息
            vehicleTypeListMap = myCarManageService.getVehiclesNow(
                String.valueOf(vo.get("manufacturer")), String.valueOf(vo.get("viSeriesName")));
            List<String> carModelsList = new ArrayList<String>();
            if (null != vehicleTypeListMap) {
                Map<String, Object> vehicleYearMap = vehicleTypeListMap
                    .get(URLDecoder.decode(pageParam.get("sweptVolume"), "UTF-8"));
                carModelsList = new ArrayList<String>();
                if (null != vehicleYearMap) {
                    Map<String, Object> vehicleTypeMap = (Map<String, Object>) vehicleYearMap.get(
                        String.valueOf(URLDecoder.decode(pageParam.get("carYearName"), "UTF-8")));
                    if (null != vehicleTypeMap) {
                        for (Map.Entry<String, Object> entry : vehicleTypeMap.entrySet()) {
                            carModelsList.add(entry.getKey());
                        }
                    }
                }
            }
            request.setAttribute("carModelList", carModelsList);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("处理tp用户请求查询车辆型号信息异常", e);
            }
            logUtil.log(new Throwable(), "CARMODELS", userModel.getUid(),
                MethodCallResultEnum.EXCEPTION, null, "tp用户请求获取车辆型号异常", startTime);
            return "page/error/tp_error_mang";
        }
        logUtil.log(new Throwable(), "CARMODELS", userModel.getUid(), MethodCallResultEnum.SUCCESS,
            null, "tp用户请求获取车辆型号成功", startTime);
        return "page/tpcar/tpSelectCarType";
    }

    /**
     * 查询车系相关的车辆型号
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/queryCarEngine")
    public String queryCarEngine(HttpServletRequest request, HttpServletResponse response) {
        long startTime = System.currentTimeMillis();
        Map<String, String> pageParam = super.getParametersFromPage(request);
        // 车辆年款名称
        Map<String, Object> vo = null;
        List<String> carEngineList = new ArrayList<String>();
        //车型
        Map<String, Map> vehicleTypeListMap = null;
        UserModel userModel = super.getUserInfo(request);
        try {
            //1、ocs中取出暂存车辆信息
            String vehicleMapKey = userModel.getUid() + Constant.TP_VEHICLE_MODEL_MEM_KEY;
            Object obj = spyMemcachedClient.get(vehicleMapKey);
            if (obj != null) {
                vo = (HashMap<String, Object>) obj;
                vehicleTypeListMap = myCarManageService.getVehiclesNow(
                    String.valueOf(vo.get("manufacturer")), String.valueOf(vo.get("viSeriesName")));
                Map<String, Object> vehicleYearMap = vehicleTypeListMap
                    .get(URLDecoder.decode(pageParam.get("sweptVolume"), "UTF-8"));
                if (null != vehicleYearMap) {
                    Map<String, Object> vehicleTypeMap = (Map<String, Object>) vehicleYearMap
                        .get(URLDecoder.decode(pageParam.get("carYearName"), "UTF-8"));
                    if (null != vehicleTypeMap) {
                        //是否存在发动机型号
                        Map<String, Object> vehicleEngineMap = (Map<String, Object>) vehicleTypeMap
                            .get(URLDecoder.decode(pageParam.get("carModelName"), "UTF-8"));
                        if (null != vehicleEngineMap && !vehicleEngineMap.isEmpty()) {
                            for (Map.Entry<String, Object> entry : vehicleEngineMap.entrySet()) {
                                if (!"填充数据类型".equals(entry.getKey())
                                    && !("null".equals(entry.getKey()))) {
                                    carEngineList.add(entry.getKey());
                                }
                            }
                        }
                    }
                }
                if (carEngineList.isEmpty() || carEngineList.size() == 1) {
                    request.setAttribute("carEngineList", carEngineList);
                    if (pageParam.get("upst").equals("1")) {
                        return this.toCarInfo(request, response);
                    } else {
                        return this.newCarInfo(request, response);
                    }
                }
                request.setAttribute("brandName", vo.get("viBrandName"));
                request.setAttribute("seriesName", vo.get("viSeriesName"));
                request.setAttribute("viId", vo.get("viId"));
                request.setAttribute("upst", pageParam.get("upst"));
                request.setAttribute("carEngineFlag", carEngineList.isEmpty());
                request.setAttribute("sweptVolume", pageParam.get("sweptVolume"));
                request.setAttribute("carYearName",
                    URLDecoder.decode(pageParam.get("carYearName"), "UTF-8"));
                request.setAttribute("carModelName",
                    URLDecoder.decode(pageParam.get("carModelName"), "UTF-8"));
            } else { // 跳转到错误页面
                logUtil.log(new Throwable(), "CARMODELS", userModel.getUid(),
                    MethodCallResultEnum.EXCEPTION, null, "tp用户请求获取车辆型号异常", startTime);
                return "page/error/tp_error_mang";
            }
            request.setAttribute("carEngineList", carEngineList);
            request.setAttribute("collapseFlag", pageParam.get("collapseFlag"));
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("处理tp用户请求查询车辆型号信息异常", e);
            }
            return "page/error/tp_error_mang";
        }
        return "page/tpcar/tpSelectCarEngine";
    }

    /**
     * tp新增车辆信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/addNewCarInfoAjax")
    public @ResponseBody Map<String, String> addNewCarInfoAjax(HttpServletRequest request,
                                                               HttpServletResponse response) {
        Map<String, String> pageParam = super.getParametersFromPage(request);
        Map<String, Object> vo = null;
        long startTime = System.currentTimeMillis();
        Map<String, String> map = new HashMap<String, String>();
        map.put("success", "false");
        UserModel userModel = super.getUserInfo(request);
        try {
            //1、ocs中取出暂存车辆信息
            String vehicleMapKey = userModel.getUid() + Constant.TP_VEHICLE_MODEL_MEM_KEY;
            Object obj = spyMemcachedClient.get(vehicleMapKey);
            if (obj != null) {
                vo = (HashMap<String, Object>) obj;
                vo.put("uid", userModel.getUid());
                if (pageParam.get("viNumber") != null) {
                    vo.put("viNumber",
                        URLDecoder.decode(pageParam.get("viNumber"), "UTF-8").toUpperCase());
                }
                if (StringUtils.isNotEmpty(pageParam.get("viStartTime"))) {// 上路时间
                    vo.put("viStartTime", DateUtil
                        .parserDateFromString(pageParam.get("viStartTime"), DateUtil.DATEFORMAT5));
                }
                if (StringUtils.isNotEmpty(pageParam.get("vlCityId"))) {// 车辆归属地
                    vo.put("vlCityId", pageParam.get("vlCityId"));
                }
                if (StringUtils.isNotEmpty(pageParam.get("vlCityName"))) {// 车辆归属地名称
                    vo.put("vlCityName", URLDecoder.decode(pageParam.get("vlCityName"), "UTF-8"));
                }
                if (StringUtils.isNotEmpty(pageParam.get("viMileage"))) {// 行使里程
                    vo.put("viMileage", pageParam.get("viMileage"));
                }
                if (StringUtils.isNotEmpty(pageParam.get("viVin"))) {// 车架号
                    vo.put("viVin", pageParam.get("viVin").toUpperCase());
                }
                if (StringUtils.isNotEmpty(pageParam.get("engineNo"))) {// 发动机号
                    vo.put("engineNo", pageParam.get("engineNo").toUpperCase());
                }
                if (StringUtils.isNotEmpty(pageParam.get("sweptVolume"))) { //排量
                    vo.put("sweptVolume", URLDecoder.decode(pageParam.get("sweptVolume"), "UTF-8"));
                }
                spyMemcachedClient.set(vehicleMapKey, Constant.MEMCACHED_SAVETIME_24, vo);
                // 1、sofa新增车辆信息
                Map<String, String> resMap = myCarManageService.addVehicle(vo);
                if (resMap == null) {// 添加车辆信息不成功
                    if (logger.isErrorEnabled()) {
                        logger.error("处理tp用户提交车辆信息增加异常，sofa接口返回：null");
                    }
                    map.put("errorMsg", "新增车辆信息失败");
                    return map;
                } else if (!Constant.SOFA_RETURN_CODE_SUCCESS.equals(resMap.get("returnCode"))) {
                    if (logger.isErrorEnabled()) {
                        logger.error("处理tp用户提交车辆信息增加异常，sofa接口返回码：{} |错误描述：{}",
                            resMap.get("returnCode"), resMap.get("returnDesc"));
                    }
                    logUtil.log(new Throwable(), "NEWCARINFO", userModel.getUid(),
                        MethodCallResultEnum.EXCEPTION, null, "处理tp用户提交车辆信息增加异常", startTime);
                    map.put("errorMsg", resMap.get("returnDesc"));
                    return map;
                } else {
                    logUtil.log(new Throwable(), "NEWCARINFO", userModel.getUid(),
                        MethodCallResultEnum.SUCCESS, null, "处理tp用户提交车辆信息增加成功", startTime);
                    map.put("success", "true");
                    map.put("viId", resMap.get("viId"));
                    return map;
                }
            } else { // 跳转到车辆列表界面
                logUtil.log(new Throwable(), "NEWCARINFO", userModel.getUid(),
                    MethodCallResultEnum.EXCEPTION, null, "处理tp提交车辆信息增加异常", startTime);
                map.put("errorMsg", "系统忙，请关闭页面稍候再试");
                return map;
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("处理tp用户提交车辆信息增加异常", e);
            }
            logUtil.log(new Throwable(), "NEWCARINFO", userModel.getUid(),
                MethodCallResultEnum.EXCEPTION, null, "处理tp用户提交车辆信息增加异常", startTime);
            map.put("errorMsg", "系统忙，请关闭页面稍候再试");
            return map;
        }
    }

    /**
     * tp修改车辆信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateCarInfoAjax")
    public @ResponseBody Map<String, String> updateCarInfoAjax(HttpServletRequest request,
                                                               HttpServletResponse response) {
        Map<String, String> pageParam = super.getParametersFromPage(request);
        Map<String, Object> vo = null;
        Map<String, String> map = new HashMap<String, String>();
        map.put("success", "false");
        UserModel userModel = super.getUserInfo(request);
        try {
            //1、ocs中取出暂存车辆信息
            String vehicleMapKey = userModel.getUid() + Constant.TP_VEHICLE_MODEL_MEM_KEY;
            Object obj = spyMemcachedClient.get(vehicleMapKey);
            if (obj != null) {
                vo = (HashMap<String, Object>) obj;
                vo.put("uid", userModel.getUid());
                if (pageParam.get("viNumber") != null) {
                    vo.put("viNumber",
                        URLDecoder.decode(pageParam.get("viNumber"), "UTF-8").toUpperCase());
                }
                if (StringUtils.isNotEmpty(pageParam.get("viStartTime"))) {// 上路时间
                    vo.put("viStartTime", DateUtil
                        .parserDateFromString(pageParam.get("viStartTime"), DateUtil.DATEFORMAT5));
                }
                if (StringUtils.isNotEmpty(pageParam.get("vlCityId"))) {// 车辆归属地
                    vo.put("vlCityId", pageParam.get("vlCityId"));
                }
                if (StringUtils.isNotEmpty(pageParam.get("vlCityName"))) {// 车辆归属地名称
                    vo.put("vlCityName", URLDecoder.decode(pageParam.get("vlCityName"), "UTF-8"));
                }
                if (StringUtils.isNotEmpty(pageParam.get("viMileage"))) {// 行使里程
                    vo.put("viMileage", pageParam.get("viMileage"));
                }
                if (StringUtils.isNotEmpty(pageParam.get("viVin"))) {// 车架号
                    vo.put("viVin", pageParam.get("viVin").toUpperCase());
                }
                if (StringUtils.isNotEmpty(pageParam.get("engineNo"))) {// 发动机号
                    vo.put("engineNo", pageParam.get("engineNo").toUpperCase());
                }
                if (StringUtils.isNotEmpty(pageParam.get("sweptVolume"))) { //排量
                    vo.put("sweptVolume", URLDecoder.decode(pageParam.get("sweptVolume"), "UTF-8"));
                }

                this.spyMemcachedClient.set(vehicleMapKey, Constant.MEMCACHED_SAVETIME_24, vo);
                // 1、sofa修改车辆信息
                Map<String, String> resMap = myCarManageService.modifyVehicle(vo);
                if (resMap == null) {// 修改不成功
                    if (logger.isErrorEnabled()) {
                        logger.error("处理tp用户提交车辆信息修改异常，sofa接口返回：null");
                    }
                    map.put("errorMsg", "修改车辆信息失败");
                    return map;
                } else if (!Constant.SOFA_RETURN_CODE_SUCCESS.equals(resMap.get("returnCode"))) {
                    if (logger.isErrorEnabled()) {
                        logger.error("处理tp用户提交车辆信息修改异常，sofa接口返回码：{} |错误描述：{}",
                            resMap.get("returnCode"), resMap.get("returnDesc"));
                    }
                    map.put("errorMsg", resMap.get("returnDesc"));
                    return map;
                } else {
                    map.put("success", "true");
                    map.put("viId", ObjectUtil.toString(vo.get("viId")));
                    return map;
                }
            } else { // 跳转到车辆列表页面
                logger.error("处理tp用户提交车辆信息修改异常,session中无更新车辆信息");
                map.put("errorMsg", "系统忙，请关闭页面稍候再试");
                return map;
            }
        } catch (Exception e) {
            logger.error("处理tp用户提交车辆信息增加异常", e);
            map.put("errorMsg", "系统忙，请关闭页面稍候再试");
            return map;
        }
    }

    /**
     * 进入个人车辆列表页面
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/myCarList")
    public String myCarList(HttpServletRequest request, HttpServletResponse response) {
        long startTime = System.currentTimeMillis();
        Map<String, String> pageParam = super.getParametersFromPage(request);
        String uId = null;
        try {
            // 1、session中取出车主信息
            UserModel userModel = (UserModel) request.getSession()
                .getAttribute(Constant.ALIPAY_USER_SESSION_KEY);
            uId = userModel.getUid();
            // 2、查询车主个人车辆信息
            List<Map<String, Object>> vehicleList = myCarManageService.queryVehicleListByUid(uId);
            if (vehicleList == null || vehicleList.size() == 0) { //跳转到新增车辆页
                return this.carInfo(request, response);
            }
            if (StringUtils.isNotEmpty(pageParam.get("vid")) && vehicleList.size() == 1) {//跳转到新增车辆页
                return this.carInfo(request, response);
            }
            request.setAttribute("viId", pageParam.get("vid"));
            request.setAttribute("myCarList", vehicleList);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("处理tp用户请求进入换车列表页面异常", e);
            }
            logUtil.log(new Throwable(), "PORTAL", uId, MethodCallResultEnum.EXCEPTION, null,
                "处理tp用户请求进入换车列表页面异常", startTime);
            super.redirectErrorPage("BUSY-ERR", "网络错误，请稍后再试", null, null, response);
            return null;
        }
        logUtil.log(new Throwable(), "PORTAL", uId, MethodCallResultEnum.SUCCESS, null,
            "处理tp用户请求进入换车列表页面异常", startTime);
        return "page/tpcar/tpCarsList";
    }

    /**
     * 必填参数转换为页面id
     * @param mustParamList
     * @return
     */
    private List<String> mustParamToWebId(List<String> mustParamList) {
        List<String> webIdList = new ArrayList<String>();
        boolean baoyangEnable = false;
        boolean weizhangEnable = false;
        for (String param : mustParamList) {
            webIdList.add(param + "Li");
            if (param.equals("viSeriesName") || param.equals("viModelName")
                || param.equals("viMileage") || param.equals("viStartTime")) {
                baoyangEnable = true;
            }
            if (param.equals("vlCityName") || param.equals("viVin") || param.equals("engineNo")) {
                weizhangEnable = true;
            }
        }
        if (baoyangEnable) {
            webIdList.add("baoyang");
            webIdList.add("baoyangDiv");
        }
        if (weizhangEnable) {
            webIdList.add("weizhang");
            webIdList.add("weizhangDiv");
        }
        return webIdList;
    }

    /**
     * 车型库树获取尾节点数据
     * @param manufacturer 厂商
     * @param viSeriesName 车系
     * @param sweptVolume 排量
     * @param prodYear 年份
     * @param viModelName 车型
     * @param engineType 发动机类型
     * @return 车型库信息
     */
    private Map<String, Object> getVihcleInfo(String manufacturer, String viSeriesName,
                                              String sweptVolume, String prodYear,
                                              String viModelName, String engineType) {
        Map<String, Object> vehicleInfoMap = new HashMap<String, Object>();
        try {
            Map<String, Map> vehicleTypeListMap = myCarManageService.getVehiclesNow(manufacturer,
                viSeriesName);
            Map<String, Object> vehicleYearMap = vehicleTypeListMap.get(sweptVolume);
            if (null != vehicleYearMap) {
                Map<String, Object> vehicleTypeMap = (Map<String, Object>) vehicleYearMap
                    .get(prodYear);
                if (null != vehicleTypeMap) {
                    //是否存在发动机型号
                    Map<String, Object> vehicleEngineMap = (Map<String, Object>) vehicleTypeMap
                        .get(viModelName);
                    if (null != vehicleEngineMap && !vehicleEngineMap.isEmpty()) {
                        vehicleInfoMap = (Map<String, Object>) vehicleEngineMap.get(engineType);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("获取车型信息异常:", e);
        }
        return vehicleInfoMap;
    }

    private Map<String, String> getCityInfo(HttpServletRequest request) throws Exception {
        Map<String, String> cityMap = null;
        try {
            UserModel userModel = super.getUserInfo(request);
            cityMap = ownerMessage.queryResidentcity(userModel.getUid());
        } catch (Exception e) {
            logger.error("调用查询常驻城市sofa接口异常", e);
        }
        if (cityMap == null) {
            cityMap = new HashMap<String, String>();
            // 从cookie中取出常驻城市
            Cookie cityName = CookieTool.getCookieByName(request, "aliPay_residentCityName");
            Cookie cityCode = CookieTool.getCookieByName(request, "aliPay_residentCityCode");
            if (cityName != null && null != cityCode) {
                cityMap.put("vlCityId", URLDecoder.decode(cityCode.getValue(), "UTF-8"));
                cityMap.put("vlCityName", URLDecoder.decode(cityName.getValue(), "UTF-8"));
            }
        } else {
            cityMap.put("vlCityId", cityMap.get("resident_city_code"));
            cityMap.put("vlCityName", cityMap.get("resident_city_name"));
        }
        return cityMap;
    }
}
