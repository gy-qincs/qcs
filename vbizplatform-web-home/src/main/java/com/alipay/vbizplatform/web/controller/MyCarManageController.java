package com.alipay.vbizplatform.web.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
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
import com.alipay.vbizplatform.common.util.CommonCodeEnum;
import com.alipay.vbizplatform.common.util.Constant;
import com.alipay.vbizplatform.common.util.CookieTool;
import com.alipay.vbizplatform.common.util.DateUtil;
import com.alipay.vbizplatform.common.util.RandomUtil;
import com.alipay.vbizplatform.common.util.SpyMemcachedClient;
import com.alipay.vbizplatform.common.util.StringUtils;
import com.alipay.vbizplatform.core.model.UserModel;
import com.alipay.vbizplatform.vehicle.service.IMyCarManageService;
import com.alipay.vbizplatform.vehicle.service.IOwnerMessage;
import com.alipay.vbizplatform.vehicle.service.VehicleDrivingLicenseDistinguishBizService;
import com.alipay.vehicleownercore.common.service.facade.model.ResponseData;

/**
 * MyCarManageController.java
 * 
 * @desc 车主服务入口与车辆维护
 * @author yuanfeng
 * @datetime 2016-4-5 下午8:31:34
 */
@RequestMapping("/car")
@Controller
@SuppressWarnings({ "unchecked" })
public class MyCarManageController extends BaseController {

    private static final Logger                        logger                   = LoggerFactory
                                                                                    .getLogger("vbizplatform"); // 信息日志

    private static final String                        VEHICLE_MODEL_KEY_PREFIX = "vehicleM";

    @Resource(name = "myCarManageService")
    private IMyCarManageService                        myCarManageService;

    @Resource(name = "spyMemcachedClient")
    private SpyMemcachedClient                         spyMemcachedClient;

    @Resource(name = "ownerMessage")
    private IOwnerMessage                              ownerMessage;

    @Resource(name = "vehicleDrivingLicenseDistinguishBizService")
    private VehicleDrivingLicenseDistinguishBizService vehicleDrivingLicenseDistinguishBizService;

    @Autowired
    private LogUtil                                    logUtil;

    /**
     * 车主服务入口
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/portal")
    public String portal(HttpServletRequest request, HttpServletResponse response) {
        UserModel userModel = super.getUserInfo(request);
        /**
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            StringBuilder loading = new StringBuilder();
            loading.append("<script type=\"text/javascript\" src=\"../js/portal.js\"></script>");
            loading.append("<script type=\"text/javascript\">");
            loading.append("document.write(_LoadingHtml);");
            loading.append("</script>");
            out.println(loading.toString());
            out.flush();
        } catch (Exception e) {
            logger.error("优先显示loading异常", e);
        }
        **/
        // 动态生成banner
        long startTime = System.currentTimeMillis();
        String banner = super.invokeVelocityDynamicCreateHtml("banner/sample/topbars.utf8.vm");
        request.setAttribute("banner", banner);
        // 1、验证用户是否授权使用车主服务
        try {
            logger.info("userModel={}", userModel != null ? userModel.getUid() : "");
            /**** test_begin ****/
            if (userModel == null) {
                userModel = new UserModel();
                userModel.setUid("2015052100077120770" + RandomUtil.getFixLenthString(3));
                //                userModel.setUid("2015052100077000000000120774");
                userModel.setPhoneNumber("13810331329");
                userModel.setRealName("冰城风儿");
                userModel.setCertNo("2301012345678905678");
                request.getSession().setAttribute(Constant.ALIPAY_USER_SESSION_KEY, userModel);
            }
            request.setAttribute("userMap", userModel);
            logger.info("userModel={}", JSONObject.toJSON(userModel));
            /***** test_end *******/
            // 2、后端获取车主的车辆信息
            List<Map<String, Object>> vehicleList = myCarManageService
                .queryVehicleListByUid(userModel.getUid());
            Map<String, Object> myCar = null;
            String viNumber = null;
            // 3、获取车主默认车辆
            if (vehicleList != null && vehicleList.size() > 1) {
                // 车辆信息保存在缓存24小时
                spyMemcachedClient.set(userModel.getUid() + "_myCarList",
                    Constant.MEMCACHED_SAVETIME_24, vehicleList);
                for (Map<String, Object> car : vehicleList) {
                    if (car.get("defaultStatus") instanceof Integer
                        && Integer.parseInt(car.get("defaultStatus").toString()) == 1) {
                        myCar = car;
                        viNumber = String.valueOf(myCar.get("viNumber"));
                    }
                }
                if (myCar == null) { //
                    myCar = vehicleList.get(0);
                    viNumber = String.valueOf(myCar.get("viNumber"));
                }
            } else if (vehicleList != null && vehicleList.size() == 1) {
                myCar = vehicleList.get(0);
                viNumber = String.valueOf(myCar.get("viNumber"));
            }
            // 4、车辆信息返回页面
            request.setAttribute("myCar", myCar);
            // 5、查询常驻城市
            Map<String, String> cityMap = null;
            try {
                cityMap = ownerMessage.queryResidentcity(userModel.getUid());
            } catch (Exception e) {
                if (logger.isErrorEnabled())
                    logger.error("调用查询常驻城市sofa接口异常", e);
            }
            if (cityMap == null) {
                // 从cookie中取出常驻城市
                Cookie cityName = CookieTool.getCookieByName(request, "aliPay_residentCityName");
                Cookie cityCode = CookieTool.getCookieByName(request, "aliPay_residentCityCode");
                if (cityName != null) {
                    cityMap = new HashMap<String, String>();
                    cityMap.put("resident_city_name",
                        URLDecoder.decode(cityName.getValue(), "UTF-8"));
                    cityMap.put("resident_city_code",
                        URLDecoder.decode(cityCode.getValue(), "UTF-8"));
                }
            }

            request.setAttribute("cityMap", cityMap);
            // 6、查询应用列表
            String cityCode = "330100"; //城市编码
            if (cityMap != null && !cityMap.isEmpty()) {
                cityCode = cityMap.get("resident_city_code");

                //首展用户定位session修改 start
                Object userLocaObject = request.getSession().getAttribute(
                    Constant.ALIPAY_USER_LOCATION_SESSION_KEY);
                Map<String, String> userLoca = null;
                if (null != userLocaObject) {
                    userLoca = (Map<String, String>) userLocaObject;
                } else {
                    userLoca = new HashMap<String, String>();
                }
                userLoca.put("residentCityCode", cityCode);
                userLoca.put("residentCityName", cityMap.get("resident_city_name"));
                request.getSession().setAttribute(Constant.ALIPAY_USER_LOCATION_SESSION_KEY,
                    userLoca);
                //end
            }
            Map<String, String> userMap = new HashMap<String, String>();
            userMap.put("userId", userModel.getUid());
            List<Map<String, Object>> categoryAppList = ownerMessage.queryCategoryApp(userMap,
                cityCode, viNumber);
            request.setAttribute("categoryAppList", categoryAppList);
            // 7、查询是否有未读消息
            int unReadMessage = 0;
            try {
                unReadMessage = ownerMessage.messageGetUnreadcount(userModel.getUid());
            } catch (Exception e) {
                logger.error("调用查询未读消息数量sofa接口异常", e);
            }
            if (unReadMessage > 0) {
                // 查询未读消息
                try {
                    List<Map<String, Object>> messageList = ownerMessage.messageBatchquery(
                        userModel.getUid(), false, 1, 1);
                    if (messageList != null && !messageList.isEmpty()) {
                        request.setAttribute("message", messageList.get(0));
                    }
                } catch (Exception e) {
                    logger.error("调用查询最新未读消息sofa接口异常", e);
                }
            } else if (cityMap != null && cityMap.get("resident_city_code") != null) {
                try {
                    String limitedContent = ownerMessage.queryLimitedLineContent(
                        cityMap.get("resident_city_code"), new Date());
                    request.setAttribute("limitedContent", limitedContent);
                } catch (Exception e) {
                    logger.error("调用查询限行信息sofa接口异常", e);
                }
            }
            //8、获取用户上次定位的城市
            String userLastTimeGPS = spyMemcachedClient.get(userModel.getUid() + "_LastTimeGPS");
            request.setAttribute("lastTimeGPS", userLastTimeGPS);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("处理用户请求进入车主服务异常", e);
            }
            super.redirectErrorPage("BUSY-ERR", "系统正忙、客官请稍候", null, null, response);
            logUtil.log(new Throwable(), "PORTAL", userModel.getUid(),
                MethodCallResultEnum.EXCEPTION, null, "处理用户请求进入车主服务异常", startTime);
            return null;
        }
        logUtil.log(new Throwable(), "PORTAL", userModel.getUid(), MethodCallResultEnum.SUCCESS,
            null, "用户请求进入车主服务成功", startTime);
        return "/page/portal";
    }

    /**
     * 进入添加爱车页面(选择车辆品牌)
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryBrand")
    public String queryBrand(HttpServletRequest request, HttpServletResponse response) {
        logger.info("uid={},class={},method=queryBrand,desc=查询车辆品牌", super.getUserInfo(request)
            .getUid(), this.getClass().getName());
        Map<String, String> pageParam = super.getParametersFromPage(request);

        long startTime = System.currentTimeMillis();
        String uId = "";
        try {

            // 1、获取所有车辆品牌信息
            Map<String, List<Map<String, Object>>> brandsMap = myCarManageService.getBrands();
            uId = super.getUserInfo(request).getUid();
            //1、session中取出暂存车辆信息
            //车辆信息
            Map<String, Object> vo = null;
            //            Object obj = request.getSession().getAttribute("newVehicleModel");

            String vehicleModelKey = uId + VEHICLE_MODEL_KEY_PREFIX;
            Object obj = spyMemcachedClient.get(vehicleModelKey);

            if (obj != null) {
                vo = (HashMap<String, Object>) obj;
                /*****页面传入参数存入车辆信息vo******/
                if (StringUtils.isNotEmpty(pageParam.get("viNumber"))) {
                    vo.put("viNumber", URLDecoder.decode(pageParam.get("viNumber"), "UTF-8")
                        .toUpperCase());
                }
                if (StringUtils.isNotEmpty(pageParam.get("viStartTime"))) {// 上路时间
                    vo.put("viStartTime", DateUtil.parserDateFromString(
                        pageParam.get("viStartTime"), DateUtil.DATEFORMAT5));
                }
                if (StringUtils.isNotEmpty(pageParam.get("vlCityId"))) {// 车辆归属地
                    vo.put("vlCityId", pageParam.get("vlCityId"));
                }
                if (StringUtils.isNotEmpty(pageParam.get("vlCityName"))) {// 车辆归属地名称
                    vo.put("vlCityName", URLDecoder.decode(pageParam.get("vlCityName"), "UTF-8"));
                }
                if (null != (pageParam.get("viMileage"))) {// 行使里程
                    vo.put("viMileage", pageParam.get("viMileage"));
                }
                if (null != (pageParam.get("viVin"))) {// 车架号
                    vo.put("viVin", pageParam.get("viVin").toUpperCase());
                }
                if (null != (pageParam.get("engineNo"))) {// 车架号
                    vo.put("engineNo", pageParam.get("engineNo").toUpperCase());
                }
                //                request.getSession().setAttribute("newVehicleModel", vo);
                this.spyMemcachedClient.set(vehicleModelKey, Constant.MEMCACHED_SAVETIME_24, vo);
            }

            //2、品牌信息传给页面
            request.setAttribute("brandsMap", brandsMap);
            request.setAttribute("brandSize", brandsMap.size());
            request.setAttribute("upst", pageParam.get("upst"));
            request.setAttribute("viId", ObjectUtil.toString(pageParam.get("viId")));

            request.setAttribute("newCarFlag", pageParam.get("newCarFlag"));
            request.setAttribute("fromPage", pageParam.get("fromPage"));
            //3、页面请求时传入的返回连接放入session中
            request.getSession().setAttribute("backUrl",
                Constant.BACKURLMAP.get(pageParam.get("backUrl")));
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("处理用户请求进入添加爱车页面异常", e);
            }
            logUtil.log(new Throwable(), "BRAND", uId, MethodCallResultEnum.EXCEPTION, null,
                "处理用户请求进入添加爱车页面异常", startTime);
            super.redirectErrorPage("BUSY-ERR", "系统正忙、客官请稍候", null, null, response);
            return null;
        }
        if (logger.isInfoEnabled()) {
            logger.info("查询品牌信息成功,uid={},toPage->page/cars/selectBrand.jsp",
                super.getUserInfo(request).getUid());
        }
        logUtil.log(new Throwable(), "BRAND", uId, MethodCallResultEnum.SUCCESS, null,
            "用户请求进入添加爱车页面成功", startTime);
        return "page/cars/selectBrand";
    }

    /**
     * 查询汽车品牌相关车系
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/queryCarSeries")
    public String queryCarSeries(HttpServletRequest request, HttpServletResponse response) {

        logger.info("uid={},class={},method=queryCarSeries,desc=查询车辆车系", super.getUserInfo(request)
            .getUid(), this.getClass().getName());
        Map<String, String> pageParam = super.getParametersFromPage(request);
        long startTime = System.currentTimeMillis();

        String brandName = null;
        // 品牌图片
        String picUrl = null;

        //背景图片
        String bgUrl = null;

        String uId = null;
        //车系信息
        Map<String, List<Map<String, Object>>> vehicleSeciesMap = null;
        try {
            uId = super.getUserInfo(request).getUid();

            if (StringUtils.isNotEmpty(pageParam.get("brandName"))) {
                // 车辆品牌
                brandName = URLDecoder.decode(pageParam.get("brandName"), "UTF-8");
            }
            if (StringUtils.isNotEmpty(pageParam.get("picUrl"))) {
                // 品牌id
                picUrl = URLDecoder.decode(pageParam.get("picUrl"), "UTF-8");
            }
            if (StringUtils.isNotEmpty(pageParam.get("bgUrl"))) {
                // 品牌背景图片
                bgUrl = URLDecoder.decode(pageParam.get("bgUrl"), "UTF-8");
            }

            // 车辆信息暂存session
            //request.getSession().setAttribute("newVehicleModel", vo);
            // 1、根据车辆品牌查询车系
            vehicleSeciesMap = myCarManageService.getVehicleSecies(brandName);

            request.setAttribute("viId", ObjectUtil.toString(pageParam.get("viId")));
            request.setAttribute("carSeriesMap", vehicleSeciesMap);
            request.setAttribute("brandName", brandName);
            request.setAttribute("picUrl", picUrl);
            request.setAttribute("bgUrl", bgUrl);
            request.setAttribute("upst", pageParam.get("upst"));

            request.setAttribute("newCarFlag", pageParam.get("newCarFlag"));
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("处理用户请求查询车系异常", e);
            }
            logUtil.log(new Throwable(), "CARSERIES", uId, MethodCallResultEnum.EXCEPTION, null,
                "处理用户请求查询车系异常", startTime);
            super.redirectErrorPage("BUSY-ERR", "系统正忙、客官请稍候", null, null, response);
            return null;
        }
        if (logger.isInfoEnabled()) {
            logger.info("用户请求查询车系信息成功,uid={},toPage->page/cars/selectCarSeries.jsp", super
                .getUserInfo(request).getUid());
        }
        logUtil.log(new Throwable(), "CARSERIES", uId, MethodCallResultEnum.SUCCESS, null,
            "处理用户请求查询车系成功", startTime);
        return "page/cars/selectCarSeries";

    }

    /**
     * 查询车辆排量信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/queryCarCC")
    public String queryCarCC(HttpServletRequest request, HttpServletResponse response) {
        logger.info("uid={},class={},method=queryCarCC,desc=查询车辆排量", super.getUserInfo(request)
            .getUid(), this.getClass().getName());
        Map<String, String> pageParam = super.getParametersFromPage(request);

        long startTime = System.currentTimeMillis();

        String uId = null;
        //车辆信息
        Map<String, Object> vo = null;
        //年款
        Map<String, Map> vehicleTypeListMap = null;
        try {
            //1、session中取出暂存车辆信息
            //            Object obj = request.getSession().getAttribute("newVehicleModel");
            uId = super.getUserInfo(request).getUid();
            String vehicleModelKey = uId + VEHICLE_MODEL_KEY_PREFIX;
            Object obj = spyMemcachedClient.get(vehicleModelKey);
            if (obj != null) {
                vo = (HashMap<String, Object>) obj;
                /*****页面传入参数存入车辆信息vo******/
                if (StringUtils.isNotEmpty(pageParam.get("viNumber"))) {
                    vo.put("viNumber", URLDecoder.decode(pageParam.get("viNumber"), "UTF-8")
                        .toUpperCase());
                }
                if (StringUtils.isNotEmpty(pageParam.get("viStartTime"))) {// 上路时间
                    vo.put("viStartTime", DateUtil.parserDateFromString(
                        pageParam.get("viStartTime"), DateUtil.DATEFORMAT5));
                }
                if (StringUtils.isNotEmpty(pageParam.get("vlCityId"))) {// 车辆归属地
                    vo.put("vlCityId", pageParam.get("vlCityId"));
                }
                if (StringUtils.isNotEmpty(pageParam.get("vlCityName"))) {// 车辆归属地名称
                    vo.put("vlCityName", URLDecoder.decode(pageParam.get("vlCityName"), "UTF-8"));
                }
                // 行使里程
                vo.put("viMileage", pageParam.get("viMileage"));
                // 车架号
                vo.put("viVin", pageParam.get("viVin").toUpperCase());

                // 发动机号
                vo.put("engineNo", pageParam.get("engineNo").toUpperCase());

                this.spyMemcachedClient.set(vehicleModelKey, Constant.MEMCACHED_SAVETIME_24, vo);
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

                request.setAttribute("url_res", ObjectUtil.toString(pageParam.get("url_res")));
            } else { // 跳转到车辆列表界面
                if (logger.isErrorEnabled()) {
                    logger.error("session中未获取到车辆临时信息,toPage->/page/cars/carsList.jsp");
                }
                logUtil.log(new Throwable(), "CARCC", uId, MethodCallResultEnum.EXCEPTION, null,
                    "用户请求获取车辆排量异常，session中未获取到车辆临时信息", startTime);
                return "redirect:/car/myCarList";
            }
            //缓存区
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
                request.setAttribute("collapseFlag", pageParam.get("collapseFlag"));
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("处理用户请求查询车辆排量信息异常", e);
            }
            logUtil.log(new Throwable(), "CARCC", uId, MethodCallResultEnum.EXCEPTION, null,
                "处理用户请求查询车辆排量信息异常", startTime);
            super.redirectErrorPage("BUSY-ERR", "系统正忙、客官请稍候", null, null, response);
            return null;
        }
        logUtil.log(new Throwable(), "CARCC", uId, MethodCallResultEnum.SUCCESS, null,
            "处理用户请求查询车辆排量信息成功", startTime);
        return "page/cars/selectCarCc";
    }

    /**
     * 查询车辆年款信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/queryCarYear")
    public String queryCarYear(HttpServletRequest request, HttpServletResponse response) {
        logger.info("uid={},class={},method=queryCarYear,desc=查询车辆年款", super.getUserInfo(request)
            .getUid(), this.getClass().getName());
        Map<String, String> pageParam = super.getParametersFromPage(request);
        //车辆信息
        Map<String, Object> vo = null;
        //年款
        Map<String, Map> vehicleTypeListMap = null;

        long startTime = System.currentTimeMillis();

        String uId = null;
        try {
            //1、session中取出暂存车辆信息
            //            Object obj = request.getSession().getAttribute("newVehicleModel");
            uId = super.getUserInfo(request).getUid();
            String vehicleModelKey = uId + VEHICLE_MODEL_KEY_PREFIX;
            Object obj = spyMemcachedClient.get(vehicleModelKey);
            if (obj != null) {
                vo = (HashMap<String, Object>) obj;
                /*****页面传入参数存入车辆信息vo******/
                if (StringUtils.isNotEmpty(pageParam.get("viStartTime"))) {// 上路时间
                    vo.put("viStartTime", DateUtil.parserDateFromString(
                        pageParam.get("viStartTime"), DateUtil.DATEFORMAT5));
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
                //                request.getSession().setAttribute("newVehicleModel", vo);
                this.spyMemcachedClient.set(vehicleModelKey, Constant.MEMCACHED_SAVETIME_24, vo);
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
                    logger.error("session中未获取到车辆临时信息,toPage->/page/cars/carsList.jsp");
                }
                logUtil.log(new Throwable(), "CARYEAR", uId, MethodCallResultEnum.EXCEPTION, null,
                    "session中未获取到车辆临时信息,用户请求车辆年份异常", startTime);
                return "redirect:/car/myCarList";
            }
            //缓存区
            // 1、根据品牌和车系查询车型相关信息
            vehicleTypeListMap = myCarManageService.getVehiclesNow(
                String.valueOf(vo.get("manufacturer")), String.valueOf(vo.get("viSeriesName")));
            //年款map转set
            List<String> carYearList = null;
            if (vehicleTypeListMap != null && !vehicleTypeListMap.isEmpty()) {
                Map<String, Object> YearNode = vehicleTypeListMap.get(URLDecoder.decode(
                    pageParam.get("sweptVolume"), "UTF-8"));
                carYearList = new ArrayList<String>();
                if (null != YearNode) {
                    for (Map.Entry<String, Object> entry : YearNode.entrySet()) {
                        carYearList.add(entry.getKey());
                    }
                }
                request.setAttribute("carYearList", carYearList);
                request.setAttribute("collapseFlag", pageParam.get("collapseFlag"));
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("处理用户请求查询车辆年份信息异常", e);
            }
            logUtil.log(new Throwable(), "CARYEAR", uId, MethodCallResultEnum.EXCEPTION, null,
                "处理用户请求查询车辆年份信息异常", startTime);
            super.redirectErrorPage("BUSY-ERR", "系统正忙、客官请稍候", null, null, response);
            return null;
        }
        logUtil.log(new Throwable(), "CARYEAR", uId, MethodCallResultEnum.SUCCESS, null,
            "处理用户请求查询车辆年份信息成功", startTime);
        return "page/cars/selectCarvProductionDate";
    }

    /**
     * 查询车系相关的车辆型号
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/queryCarModels")
    public String queryCarModels(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> pageParam = super.getParametersFromPage(request);
        // 车辆年款名称
        String carYearName = null;
        Map<String, Object> vo = null;

        String uId = null;

        long startTime = System.currentTimeMillis();
        //车型
        Map<String, Map> vehicleTypeListMap = null;
        try {
            //            Object obj = request.getSession().getAttribute("newVehicleModel");
            uId = super.getUserInfo(request).getUid();
            String vehicleModelKey = uId + VEHICLE_MODEL_KEY_PREFIX;
            Object obj = spyMemcachedClient.get(vehicleModelKey);
            if (obj != null) {
                vo = (HashMap<String, Object>) obj;
                if (StringUtils.isNotEmpty(pageParam.get("viNumber"))) {
                    vo.put("viNumber", URLDecoder.decode(pageParam.get("viNumber"), "UTF-8")
                        .toUpperCase());
                }
                if (StringUtils.isNotEmpty(pageParam.get("viStartTime"))) {// 上路时间
                    vo.put("viStartTime", DateUtil.parserDateFromString(
                        pageParam.get("viStartTime"), DateUtil.DATEFORMAT5));
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
                if (StringUtils.isNotEmpty(pageParam.get("engineType"))) { // 发动机号
                    vo.put("engineType", pageParam.get("engineType").toUpperCase());
                }
                //                request.getSession().setAttribute("newVehicleModel", vo);
                this.spyMemcachedClient.set(vehicleModelKey, Constant.MEMCACHED_SAVETIME_24, vo);

                request.setAttribute("brandName", vo.get("viBrandName"));
                request.setAttribute("seriesName", vo.get("viSeriesName"));
                request.setAttribute("sweptVolume", pageParam.get("sweptVolume"));
                request.setAttribute("carYearName",
                    URLDecoder.decode(pageParam.get("carYearName"), "UTF-8"));

                request.setAttribute("viId", vo.get("viId"));
                request.setAttribute("upst", pageParam.get("upst"));

            } else { // 跳转到车辆列表界面
                logUtil.log(new Throwable(), "CARMODELS", uId, MethodCallResultEnum.EXCEPTION,
                    null, "用户请求获取车辆型号异常", startTime);
                return "redirect:/car/myCarList";
            }
            // 1、根据品牌和车系查询车型相关信息
            vehicleTypeListMap = myCarManageService.getVehiclesNow(
                String.valueOf(vo.get("manufacturer")), String.valueOf(vo.get("viSeriesName")));

            List<String> carModelsList = new ArrayList<String>();
            if (null != vehicleTypeListMap) {
                Map<String, Object> vehicleYearMap = vehicleTypeListMap.get(URLDecoder.decode(
                    pageParam.get("sweptVolume"), "UTF-8"));

                carModelsList = new ArrayList<String>();
                if (null != vehicleYearMap) {
                    Map<String, Object> vehicleTypeMap = (Map<String, Object>) vehicleYearMap
                        .get(String.valueOf(URLDecoder.decode(pageParam.get("carYearName"), "UTF-8")));
                    if (null != vehicleTypeMap) {
                        for (Map.Entry<String, Object> entry : vehicleTypeMap.entrySet()) {
                            carModelsList.add(entry.getKey());
                        }
                    }
                }
            }
            request.setAttribute("carModelList", carModelsList);

            request.setAttribute("collapseFlag", pageParam.get("collapseFlag"));
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("处理用户请求查询车辆型号信息异常", e);
            }
            logUtil.log(new Throwable(), "CARMODELS", uId, MethodCallResultEnum.EXCEPTION, null,
                "用户请求获取车辆型号异常", startTime);

            super.redirectErrorPage("BUSY-ERR", "系统正忙、客官请稍候", null, null, response);
            return null;
        }
        logUtil.log(new Throwable(), "CARMODELS", uId, MethodCallResultEnum.SUCCESS, null,
            "用户请求获取车辆型号成功", startTime);
        return "page/cars/selectCarType";
    }

    /**
     * 查询车系相关的车辆型号
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/queryCarEngine")
    public String queryCarEngine(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> pageParam = super.getParametersFromPage(request);
        // 车辆年款名称
        Map<String, Object> vo = null;

        List<String> carEngineList = new ArrayList<String>();
        //车型
        Map<String, Map> vehicleTypeListMap = null;
        try {
            //            Object obj = request.getSession().getAttribute("newVehicleModel");
            String uId = super.getUserInfo(request).getUid();
            String vehicleModelKey = uId + VEHICLE_MODEL_KEY_PREFIX;
            Object obj = spyMemcachedClient.get(vehicleModelKey);
            if (obj != null) {
                vo = (HashMap<String, Object>) obj;
                vehicleTypeListMap = myCarManageService.getVehiclesNow(
                    String.valueOf(vo.get("manufacturer")), String.valueOf(vo.get("viSeriesName")));
                Map<String, Object> vehicleYearMap = vehicleTypeListMap.get(URLDecoder.decode(
                    pageParam.get("sweptVolume"), "UTF-8"));

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
                        return toCarInfo(request, response);
                    } else {
                        return newCarInfo(request, response);
                    }
                }

                if (StringUtils.isNotEmpty(pageParam.get("viNumber"))) {
                    vo.put("viNumber", URLDecoder.decode(pageParam.get("viNumber"), "UTF-8")
                        .toUpperCase());
                }
                if (StringUtils.isNotEmpty(pageParam.get("viStartTime"))) {// 上路时间
                    vo.put("viStartTime", DateUtil.parserDateFromString(
                        pageParam.get("viStartTime"), DateUtil.DATEFORMAT5));
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
                //                if (StringUtils.isNotEmpty(pageParam.get("engineType"))) { // 发动机号
                //                    vo.put("engineType", pageParam.get("engineType").toUpperCase());
                //                }
                //                if (StringUtils.isNotEmpty(pageParam.get("sweptVolume"))) { //排量
                //                    vo.put("sweptVolume", pageParam.get("sweptVolume"));
                //                }

                //                request.getSession().setAttribute("newVehicleModel", vo);
                this.spyMemcachedClient.set(vehicleModelKey, Constant.MEMCACHED_SAVETIME_24, vo);

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
            } else { // 跳转到车辆列表界面
                return "redirect:/car/myCarList";
            }
            // 1、根据品牌和车系查询车型相关信息

            request.setAttribute("carEngineList", carEngineList);

            request.setAttribute("collapseFlag", pageParam.get("collapseFlag"));
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("处理用户请求查询车辆型号信息异常", e);
            }
            super.redirectErrorPage("BUSY-ERR", "系统正忙、客官请稍候", null, null, response);
            return null;
        }
        return "page/cars/selectCarEngine";
    }

    /**
     * 进入车辆信息完善页面
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

        String uId = null;

        long startTime = System.currentTimeMillis();
        try {
            //            Object obj = request.getSession().getAttribute("newVehicleModel");
            uId = super.getUserInfo(request).getUid();
            String vehicleModelKey = uId + VEHICLE_MODEL_KEY_PREFIX;
            Object obj = spyMemcachedClient.get(vehicleModelKey);
            if (obj == null
                || (pageParam.get("newCarFlag") != null && pageParam.get("newCarFlag").equals("1"))) {
                vo = new HashMap<String, Object>();
                //                request.getSession().setAttribute("newVehicleModel", vo);
                this.spyMemcachedClient.set(vehicleModelKey, Constant.MEMCACHED_SAVETIME_24, vo);
            } else {
                vo = (HashMap<String, Object>) obj;
            }

            /*****页面传入参数存入车辆信息vo******/
            if (StringUtils.isNotEmpty(pageParam.get("carSeriesName"))) {
                // 车系名称
                carSeriesName = URLDecoder.decode(pageParam.get("carSeriesName"), "UTF-8");
                if (null != carSeriesName
                    && !(ObjectUtil.toString(vo.get("viSeriesName")).equals(carSeriesName))) {
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
            List<String> carEngineList = request.getAttribute("carEngineList") == null ? new ArrayList<String>()
                : (List<String>) request.getAttribute("carEngineList");
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
                    String sweptVolume = URLDecoder.decode(
                        ObjectUtil.toString(pageParam.get("sweptVolume")), "UTF-8");
                    String productionYear = URLDecoder.decode(
                        ObjectUtil.toString(pageParam.get("carYearName")), "UTF-8");
                    String carModelName = URLDecoder.decode(
                        ObjectUtil.toString(pageParam.get("carModelName")), "UTF-8");
                    //排量
                    vo.put("sweptVolume", sweptVolume);
                    //年份
                    //                        vo.put("viStyleName", );
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
                        String sweptVolume = URLDecoder.decode(
                            ObjectUtil.toString(pageParam.get("sweptVolume")), "UTF-8");
                        String productionYear = URLDecoder.decode(
                            ObjectUtil.toString(pageParam.get("carYearName")), "UTF-8");
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

            //            request.getSession().setAttribute("newVehicleModel", vo);
            this.spyMemcachedClient.set(vehicleModelKey, Constant.MEMCACHED_SAVETIME_24, vo);

            request.setAttribute("vehicleModel", vo);
            String viNumber = String.valueOf(vo.get("viNumber")); // 车牌
            if (StringUtils.isNotEmpty(viNumber) && viNumber.length() >= 1) {
                request.setAttribute("cityAB", viNumber.substring(0, 1)); // 车牌地区简写
                request.setAttribute("carNumber", viNumber.substring(1, viNumber.length())); // 车牌数字
            } else {
                if (!StringUtils.isEmpty(ObjectUtil.toString(vo.get("vlCityId")))
                    && (StringUtils.isEmpty(viNumber))) {
                    CityMapModel cityCode = ownerMessage.queryCityMapInfo(vo.get("vlCityId")
                        .toString());
                    if (null != cityCode)
                        request.setAttribute("cityAB", cityCode.getCarNoPrefix()); // 车牌地区简写
                }
            }
            if (vo.get("viStartTime") != null) { // 上路时间
                request
                    .setAttribute("viStartTime", DateUtil.parserDateToString(
                        (Date) vo.get("viStartTime"), DateUtil.DATEFORMAT5));
            }
            String backUrl = "/car/myCarList";
            Object backObj = request.getSession().getAttribute("backUrl");
            if (backObj != null) {
                backUrl = backObj.toString();
            }
            request.setAttribute("backUrl", backUrl);
            //在ocs缓存中获取车辆列表信息
            List<Map<String, Object>> vehicleList = spyMemcachedClient.get(super.getUserInfo(
                request).getUid()
                                                                           + "_myCarList");
            request.setAttribute("carList", JSONObject.toJSON(vehicleList));
            //修改collapse展开标示 
            request.setAttribute("collapseFlag", pageParam.get("collapseFlag"));

        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("处理用户请求进入车辆信息完善页面异常", e);
            }
            logUtil.log(new Throwable(), "CARINFO", uId, MethodCallResultEnum.EXCEPTION, null,
                "处理用户请求进入车辆信息完善页面异常", startTime);
            super.redirectErrorPage("BUSY-ERR", "系统正忙、客官请稍候", null, null, response);
            return null;
        }
        logUtil.log(new Throwable(), "CARINFO", uId, MethodCallResultEnum.SUCCESS, null,
            "处理用户请求进入车辆信息完善页面成功", startTime);
        return "page/cars/completion";
    }

    /**
     * 新增车辆信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/addNewCarInfo")
    public String addNewCarInfo(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> pageParam = super.getParametersFromPage(request);
        Map<String, Object> vo = null;

        long startTime = System.currentTimeMillis();

        String uId = null;
        try {
            //            Object obj = request.getSession().getAttribute("newVehicleModel");
            uId = super.getUserInfo(request).getUid();
            String vehicleModelKey = uId + VEHICLE_MODEL_KEY_PREFIX;
            Object obj = spyMemcachedClient.get(vehicleModelKey);
            if (obj != null) {
                vo = (HashMap<String, Object>) obj;
                UserModel userModel = (UserModel) request.getSession().getAttribute(
                    Constant.ALIPAY_USER_SESSION_KEY);
                vo.put("uid", userModel.getUid());
                if (pageParam.get("viNumber") != null) {
                    vo.put("viNumber", URLDecoder.decode(pageParam.get("viNumber"), "UTF-8")
                        .toUpperCase());
                }
                if (StringUtils.isNotEmpty(pageParam.get("viStartTime"))) {// 上路时间
                    vo.put("viStartTime", DateUtil.parserDateFromString(
                        pageParam.get("viStartTime"), DateUtil.DATEFORMAT5));
                }
                if (StringUtils.isNotEmpty(pageParam.get("vlCityId"))) {// 车辆归属地
                    vo.put("vlCityId", pageParam.get("vlCityId"));
                }
                if (StringUtils.isNotEmpty(pageParam.get("vlCityName"))) {// 车辆归属地名称
                    vo.put("vlCityName", URLDecoder.decode(pageParam.get("vlCityName"), "UTF-8"));
                }
                // 行使里程
                vo.put("viMileage", pageParam.get("viMileage"));

                // 车架号
                vo.put("viVin", pageParam.get("viVin").toUpperCase());

                // 发动机号
                vo.put("engineNo", pageParam.get("engineNo").toUpperCase());

                //                request.getSession().setAttribute("newVehicleModel", vo);
                this.spyMemcachedClient.set(vehicleModelKey, Constant.MEMCACHED_SAVETIME_24, vo);
                // 1、sofa新增车辆信息
                Map<String, String> resMap = myCarManageService.addVehicle(vo);
                if (resMap == null) {// 添加车辆信息不成功
                    if (logger.isErrorEnabled()) {
                        logger.error("处理用户提交车辆信息增加异常，sofa接口返回：null");
                    }
                    super.redirectErrorPage("BUSY-ERR", "网络错误，请稍后再试", null, null, response);
                    return null;
                } else if (!Constant.SOFA_RETURN_CODE_SUCCESS.equals(resMap.get("returnCode"))) {
                    String errorMsg = "新增车辆信息失败";
                    if ("11006".equals(resMap.get("returnCode"))) {
                        errorMsg = "已添加过该辆车";
                    }
                    if (logger.isErrorEnabled()) {
                        logger.error("处理用户提交车辆信息增加异常，sofa接口返回码：{} |错误描述：{}",
                            resMap.get("returnCode"), resMap.get("returnDesc"));
                    }
                    logUtil.log(new Throwable(), "NEWCARINFO", uId, MethodCallResultEnum.EXCEPTION,
                        null, "处理用户提交车辆信息增加异常", startTime);
                    super.redirectErrorPage("BUSY-ERR", errorMsg, null, null, response);
                    return null;
                } else {
                    String backUrl = "/car/myCarList";
                    Object backObj = request.getSession().getAttribute("backUrl");
                    if (backObj != null) {
                        backUrl = backObj.toString();
                    }
                    request.setAttribute("backUrl", backUrl);
                    logUtil.log(new Throwable(), "NEWCARINFO", uId, MethodCallResultEnum.SUCCESS,
                        null, "处理用户提交车辆信息增加成功", startTime);
                    return new StringBuilder("redirect:").append(backUrl).toString();
                }
            } else { // 跳转到车辆列表界面
                logUtil.log(new Throwable(), "NEWCARINFO", uId, MethodCallResultEnum.EXCEPTION,
                    null, "处理用户提交车辆信息增加异常", startTime);
                super.redirectErrorPage("BUSY-ERR", "用户登录超时,请重新登录", null, null, response);
                return null;
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("处理用户提交车辆信息增加异常", e);
            }
            logUtil.log(new Throwable(), "NEWCARINFO", uId, MethodCallResultEnum.EXCEPTION, null,
                "处理用户提交车辆信息增加异常", startTime);
            super.redirectErrorPage("BUSY-ERR", "网络错误，请稍后再试", null, null, response);
            return null;
        }
    }

    /**
     * 新增车辆信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/addNewCarInfoAjax")
    public @ResponseBody
    Object addNewCarInfoAjax(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> pageParam = super.getParametersFromPage(request);
        Map<String, Object> vo = null;

        long startTime = System.currentTimeMillis();

        String uId = null;

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", "false");
        try {
            //            Object obj = request.getSession().getAttribute("newVehicleModel");
            uId = super.getUserInfo(request).getUid();
            String vehicleModelKey = uId + VEHICLE_MODEL_KEY_PREFIX;
            Object obj = spyMemcachedClient.get(vehicleModelKey);
            if (obj != null) {
                vo = (HashMap<String, Object>) obj;
                UserModel userModel = (UserModel) request.getSession().getAttribute(
                    Constant.ALIPAY_USER_SESSION_KEY);
                vo.put("uid", userModel.getUid());
                if (pageParam.get("viNumber") != null) {
                    vo.put("viNumber", URLDecoder.decode(pageParam.get("viNumber"), "UTF-8")
                        .toUpperCase());
                }
                if (StringUtils.isNotEmpty(pageParam.get("viStartTime"))) {// 上路时间
                    vo.put("viStartTime", DateUtil.parserDateFromString(
                        pageParam.get("viStartTime"), DateUtil.DATEFORMAT5));
                }
                if (StringUtils.isNotEmpty(pageParam.get("vlCityId"))) {// 车辆归属地
                    vo.put("vlCityId", pageParam.get("vlCityId"));
                }
                if (StringUtils.isNotEmpty(pageParam.get("vlCityName"))) {// 车辆归属地名称
                    vo.put("vlCityName", URLDecoder.decode(pageParam.get("vlCityName"), "UTF-8"));
                }
                // 行使里程
                vo.put("viMileage", pageParam.get("viMileage"));

                // 车架号
                vo.put("viVin", pageParam.get("viVin").toUpperCase());

                // 发动机号
                vo.put("engineNo", pageParam.get("engineNo").toUpperCase());

                if (StringUtils.isNotEmpty(pageParam.get("sweptVolume"))) { //排量
                    vo.put("sweptVolume", URLDecoder.decode(pageParam.get("sweptVolume"), "UTF-8"));
                }
                //                request.getSession().setAttribute("newVehicleModel", vo);
                this.spyMemcachedClient.set(vehicleModelKey, Constant.MEMCACHED_SAVETIME_24, vo);
                // 1、sofa新增车辆信息
                Map<String, String> resMap = myCarManageService.addVehicle(vo);
                if (resMap == null) {// 添加车辆信息不成功
                    if (logger.isErrorEnabled()) {
                        logger.error("处理用户提交车辆信息增加异常，sofa接口返回：null");
                    }
                    map.put("errorUrl", super.errorPageUrl("BUSY-ERR", "网络错误，请稍后再试", null, null));
                    return map;
                } else if (!Constant.SOFA_RETURN_CODE_SUCCESS.equals(resMap.get("returnCode"))) {
                    String errorMsg = "新增车辆信息失败";
                    if ("11006".equals(resMap.get("returnCode"))) {
                        errorMsg = "已添加过该辆车";
                    }
                    if (logger.isErrorEnabled()) {
                        logger.error("处理用户提交车辆信息增加异常，sofa接口返回码：{} |错误描述：{}",
                            resMap.get("returnCode"), resMap.get("returnDesc"));
                    }
                    logUtil.log(new Throwable(), "NEWCARINFO", uId, MethodCallResultEnum.EXCEPTION,
                        null, "处理用户提交车辆信息增加异常", startTime);
                    map.put("errorUrl", super.errorPageUrl("BUSY-ERR", errorMsg, null, null));
                    return map;
                } else {
                    String backUrl = "/car/myCarList";
                    Object backObj = request.getSession().getAttribute("backUrl");
                    if (backObj != null) {
                        backUrl = backObj.toString();
                    }
                    request.setAttribute("backUrl", backUrl);
                    logUtil.log(new Throwable(), "NEWCARINFO", uId, MethodCallResultEnum.SUCCESS,
                        null, "处理用户提交车辆信息增加成功", startTime);
                    map.put("success", "true");
                    return map;
                }
            } else { // 跳转到车辆列表界面
                logUtil.log(new Throwable(), "NEWCARINFO", uId, MethodCallResultEnum.EXCEPTION,
                    null, "处理用户提交车辆信息增加异常", startTime);
                map.put("errorUrl", super.errorPageUrl("BUSY-ERR", "用户登录超时,请重新登录", null, null));
                return map;
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("处理用户提交车辆信息增加异常", e);
            }
            logUtil.log(new Throwable(), "NEWCARINFO", uId, MethodCallResultEnum.EXCEPTION, null,
                "处理用户提交车辆信息增加异常", startTime);
            map.put("errorUrl", super.errorPageUrl("BUSY-ERR", "网络错误，请稍后再试", null, null));
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

        String uId = null;
        try {
            // 1、session中取出车主信息
            UserModel userModel = (UserModel) request.getSession().getAttribute(
                Constant.ALIPAY_USER_SESSION_KEY);
            uId = userModel.getUid();
            // 2、查询车主个人车辆信息
            List<Map<String, Object>> vehicleList = myCarManageService.queryVehicleListByUid(uId);
            if (vehicleList == null || vehicleList.size() == 0) {
                return "page/cars/carsListNull";
            }
            // 车辆信息保存在缓存24小时
            spyMemcachedClient.set(uId + "_myCarList", Constant.MEMCACHED_SAVETIME_24, vehicleList);
            request.setAttribute("myCarList", vehicleList);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("处理用户请求进入个人车辆列表页面异常", e);
            }
            logUtil.log(new Throwable(), "PORTAL", uId, MethodCallResultEnum.EXCEPTION, null,
                "处理用户请求进入个人车辆列表页面异常", startTime);
            super.redirectErrorPage("BUSY-ERR", "网络错误，请稍后再试", null, null, response);
            return null;
        }
        logUtil.log(new Throwable(), "PORTAL", uId, MethodCallResultEnum.SUCCESS, null,
            "处理用户请求进入个人车辆列表页面成功", startTime);
        return "page/cars/carsList";
    }

    /**
     * 删除车主的一个车辆信息
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/delMyCar")
    public @ResponseBody
    String delMyCar(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> pageParam = super.getParametersFromPage(request);
        Map<String, String> resMap = null;
        try {
            // 用户id
            UserModel userModel = (UserModel) request.getSession().getAttribute(
                Constant.ALIPAY_USER_SESSION_KEY);
            if (StringUtils.isNotEmpty(pageParam.get("viId")) //判断车辆id不为空
                && pageParam.get("viId").matches("^\\d+$")) {//判断车辆id纯数字
                //删除车辆信息
                resMap = myCarManageService.deleteVehicleInfo(userModel.getUid(),
                    pageParam.get("viId"));
                if (resMap != null
                    && Constant.SOFA_RETURN_CODE_SUCCESS.equals(resMap.get("returnCode"))) {
                    try {
                        //处理缓存中的车辆列表
                        List<Map<String, Object>> vehicleList = spyMemcachedClient.get(userModel
                            .getUid() + "_myCarList");
                        if (!vehicleList.isEmpty()) {
                            for (Map<String, Object> vMap : vehicleList) {
                                if (vMap.get("viId").equals(pageParam.get("viId"))) {
                                    vehicleList.remove(vMap); //删除缓存中这辆车
                                    break;
                                }
                            }
                            //更新缓存中的车辆数据
                            spyMemcachedClient.set(userModel.getUid() + "_myCarList",
                                Constant.MEMCACHED_SAVETIME_24, vehicleList);
                        }
                    } catch (Exception e) {
                        if (logger.isErrorEnabled()) {
                            logger.error("处理ocs缓存中车辆列表信息异常", e);
                        }
                    }
                    return "ok";
                }
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("处理用户请求删除一个车辆异常", e);
            }
        }
        return null;
    }

    /**
     * 设置车主的默认车辆
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/setDefaultCar")
    public @ResponseBody
    String setDefaultCar(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> pageParam = super.getParametersFromPage(request);
        Map<String, String> resMap = null;
        try {
            // 用户id
            UserModel userModel = (UserModel) request.getSession().getAttribute(
                Constant.ALIPAY_USER_SESSION_KEY);
            if (StringUtils.isNotEmpty(pageParam.get("viId")) //判断车辆id不为空
                && pageParam.get("viId").matches("^\\d+$")) {//判断车辆id纯数字
                //设置默认车辆
                resMap = myCarManageService.updateDefaultVehicleSetting(userModel.getUid(),
                    pageParam.get("viId"));
                if (resMap != null
                    && Constant.SOFA_RETURN_CODE_SUCCESS.equals(resMap.get("returnCode"))) {
                    return "ok";
                }
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled())
                logger.error("处理用户请求设置默认车辆异常", e);
        }
        return null;
    }

    /**
     * 进入修改车辆信息页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/toCarInfo")
    public String toCarInfo(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> pageParam = super.getParametersFromPage(request);
        try {
            // 1、session中取出车主信息
            UserModel userModel = super.getUserInfo(request);
            String viId = pageParam.get("viId");
            String carSeriesName = null;
            String manufacturer = null;
            Map<String, Object> updateVo = null;

            String uId = super.getUserInfo(request).getUid();
            String vehicleModelKey = uId + VEHICLE_MODEL_KEY_PREFIX;

            boolean tf = false; //预设session中没有找到车辆信息,解决车辆修改不保存的问题

            if (StringUtils.isEmpty(pageParam.get("backUrl"))) {
                //                Object obj = request.getSession().getAttribute("newVehicleModel");

                Object obj = spyMemcachedClient.get(vehicleModelKey);
                if (obj != null) {
                    updateVo = (HashMap<String, Object>) obj;
                    if (viId.equals(String.valueOf(updateVo.get("viId")))) {
                        tf = true; //找到相同车辆信息
                    } else {
                        updateVo = null;
                    }
                }
            }
            if (!tf) { //没有在session中找到车辆信息
                //从memcached中查找车辆信息
                List<Map<String, Object>> myCarList = spyMemcachedClient.get(userModel.getUid()
                                                                             + "_myCarList");
                if (myCarList != null && myCarList.size() > 0) {
                    for (Map<String, Object> vo : myCarList) {
                        if (viId.equals(String.valueOf(vo.get("viId")))) {
                            updateVo = vo;
                        }
                    }
                }
                if (updateVo == null) {
                    //根据用户id和车辆id查询车辆信息
                    updateVo = myCarManageService.getVehicleInfo(userModel.getUid(), viId);
                }
            }
            if (updateVo == null) {
                super.redirectErrorPage("BUSY-ERR", "网络错误，请稍后再试", null, null, response);
                return null;
            }
            if (StringUtils.isNotEmpty(pageParam.get("carSeriesName"))) {
                // 车系名称
                carSeriesName = URLDecoder.decode(pageParam.get("carSeriesName"), "UTF-8");
                if (null != carSeriesName
                    && !(ObjectUtil.toString(updateVo.get("viSeriesName")).equals(carSeriesName))) {
                    updateVo.put("viSeriesName", carSeriesName);
                    //品牌图片
                    updateVo.put("viBrandName",
                        URLDecoder.decode(pageParam.get("brandName"), "UTF-8"));
                    //品牌名称
                    updateVo
                        .put("viBrandLogo", URLDecoder.decode(pageParam.get("picUrl"), "UTF-8"));
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
            List<String> carEngineList = request.getAttribute("carEngineList") == null ? new ArrayList<String>()
                : (List<String>) request.getAttribute("carEngineList");
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
                    && !(ObjectUtil.toString(updateVo.get("engineType")).equals(engineType))) {
                    String sweptVolume = ObjectUtil.toString(pageParam.get("sweptVolume"));
                    String productionYear = URLDecoder.decode(
                        ObjectUtil.toString(pageParam.get("carYearName")), "UTF-8");
                    String carModelName = URLDecoder.decode(
                        ObjectUtil.toString(pageParam.get("carModelName")), "UTF-8");
                    //排量
                    updateVo.put("sweptVolume", sweptVolume);
                    //年份
                    //                        vo.put("viStyleName", );
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
                    if (null != carModelName
                        && !(ObjectUtil.toString(updateVo.get("viModelName")).equals(carModelName))) {
                        String sweptVolume = ObjectUtil.toString(pageParam.get("sweptVolume"));
                        String productionYear = URLDecoder.decode(
                            ObjectUtil.toString(pageParam.get("carYearName")), "UTF-8");
                        //排量
                        updateVo.put("sweptVolume", sweptVolume);
                        //年份
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
            // 用于车辆信息修改页面返回url
            if (StringUtils.isNotEmpty(pageParam.get("backUrl"))) {
                request.getSession().setAttribute("backUrl",
                    Constant.BACKURLMAP.get(pageParam.get("backUrl")));
            }
            //            request.getSession().setAttribute("newVehicleModel", updateVo);
            this.spyMemcachedClient.set(vehicleModelKey, Constant.MEMCACHED_SAVETIME_24, updateVo);

            request.setAttribute("vehicleModel", updateVo);
            String viNumber = String.valueOf(updateVo.get("viNumber"));// 车牌
            if (StringUtils.isNotEmpty(viNumber) && viNumber.length() > 1) {
                request.setAttribute("cityAB", viNumber.substring(0, 1)); // 车牌地区简写
                request.setAttribute("carNumber", viNumber.substring(1, viNumber.length())); // 车牌数字
            }
            if (updateVo.get("viStartTime") != null) { // 上路时间,转换成yyyy-mm格式
                request.setAttribute("viStartTime", DateUtil.parserDateToString(
                    (Date) updateVo.get("viStartTime"), DateUtil.DATEFORMAT5));
            }
            String backUrl = "/car/myCarList";
            Object backObj = request.getSession().getAttribute("backUrl");
            if (backObj != null) {
                backUrl = backObj.toString();
            }
            request.setAttribute("backUrl", backUrl);
            //在ocs缓存中获取车辆列表信息
            List<Map<String, Object>> vehicleList = spyMemcachedClient.get(super.getUserInfo(
                request).getUid()
                                                                           + "_myCarList");
            request.setAttribute("carList", JSONObject.toJSON(vehicleList));
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("处理用户请求进入个人车辆列表页面异常", e);
            }
            super.redirectErrorPage("BUSY-ERR", "网络错误，请稍后再试", null, null, response);
            return null;
        }
        return "page/cars/carsDetail";
    }

    /**
     * 修改车辆信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateCarInfo")
    public String updateCarInfo(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> pageParam = super.getParametersFromPage(request);
        Map<String, Object> vo = null;
        try {
            //            Object obj = request.getSession().getAttribute("newVehicleModel");
            String uId = super.getUserInfo(request).getUid();
            String vehicleModelKey = uId + VEHICLE_MODEL_KEY_PREFIX;
            Object obj = spyMemcachedClient.get(vehicleModelKey);
            if (obj != null) {
                vo = (HashMap<String, Object>) obj;
                UserModel userModel = super.getUserInfo(request);
                vo.put("uid", userModel.getUid());
                if (pageParam.get("viNumber") != null) {
                    vo.put("viNumber", URLDecoder.decode(pageParam.get("viNumber"), "UTF-8")
                        .toUpperCase());
                }
                if (StringUtils.isNotEmpty(pageParam.get("viStartTime"))) {// 上路时间
                    vo.put("viStartTime", DateUtil.parserDateFromString(
                        pageParam.get("viStartTime"), DateUtil.DATEFORMAT5));
                }
                if (StringUtils.isNotEmpty(pageParam.get("vlCityId"))) {// 车辆归属地
                    vo.put("vlCityId", pageParam.get("vlCityId"));
                }
                if (StringUtils.isNotEmpty(pageParam.get("vlCityName"))) {// 车辆归属地名称
                    vo.put("vlCityName", URLDecoder.decode(pageParam.get("vlCityName"), "UTF-8"));
                }
                // 行使里程
                vo.put("viMileage", pageParam.get("viMileage"));

                // 车架号
                vo.put("viVin", pageParam.get("viVin").toUpperCase());

                // 发动机号
                vo.put("engineNo", pageParam.get("engineNo").toUpperCase());

                if (StringUtils.isNotEmpty(pageParam.get("sweptVolume"))) { //排量
                    vo.put("sweptVolume", URLDecoder.decode(pageParam.get("sweptVolume"), "UTF-8"));
                }
                //                request.getSession().setAttribute("newVehicleModel", vo);
                this.spyMemcachedClient.set(vehicleModelKey, Constant.MEMCACHED_SAVETIME_24, vo);
                // 1、sofa新增车辆信息
                Map<String, String> resMap = myCarManageService.modifyVehicle(vo);
                if (resMap == null) {// 修改不成功
                    if (logger.isErrorEnabled()) {
                        logger.error("处理用户提交车辆信息修改异常，sofa接口返回：null");
                    }
                    super.redirectErrorPage("BUSY-ERR", "网络错误，请稍后再试", null, null, response);
                    return null;
                } else if (!Constant.SOFA_RETURN_CODE_SUCCESS.equals(resMap.get("returnCode"))) {
                    String errorMsg = "修改车辆信息失败";
                    if (Constant.SOFA_RETURN_CODE_REPEAT.equals(resMap.get("returnCode"))) {
                        errorMsg = "已添加过该辆车";
                    }
                    if (logger.isErrorEnabled()) {
                        logger.error("处理用户提交车辆信息修改异常，sofa接口返回码：{} |错误描述：{}",
                            resMap.get("returnCode"), resMap.get("returnDesc"));
                    }
                    super.redirectErrorPage("BUSY-ERR", errorMsg, null, null, response);
                    return null;
                } else {
                    //处理缓存中的车辆列表
                    List<Map<String, Object>> vehicleList = spyMemcachedClient.get(userModel
                        .getUid() + "_myCarList");
                    if (null != vehicleList && !vehicleList.isEmpty()) {
                        for (int i = 0; i < vehicleList.size(); i++) {
                            Map<String, Object> vMap = vehicleList.get(i);
                            if (vMap.get("viId").equals(vo.get("viId"))) {
                                vehicleList.set(i, vo); //修改缓存中这辆车
                                break;
                            }
                        }
                        //更新缓存中的车辆数据
                        spyMemcachedClient.set(userModel.getUid() + "_myCarList",
                            Constant.MEMCACHED_SAVETIME_24, vehicleList);
                    }
                    //////////////////
                    String backUrl = "/car/myCarList";
                    Object backObj = request.getSession().getAttribute("backUrl");
                    if (backObj != null) {
                        backUrl = backObj.toString();
                    }
                    request.setAttribute("backUrl", backUrl);
                    return new StringBuilder("redirect:").append(backUrl).toString();
                }
            } else { // 跳转到车辆列表页面
                logger.error("处理用户提交车辆信息修改异常,session中无更新车辆信息");
                super.redirectErrorPage("BUSY-ERR", "用户登录超时,请重新登录", null, null, response);
                return null;
            }
        } catch (Exception e) {
            logger.error("处理用户提交车辆信息增加异常", e);
            super.redirectErrorPage("BUSY-ERR", "网络错误，请稍后再试", null, null, response);
            return null;
        }
    }

    /**
     * 修改车辆信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateCarInfoAjax")
    public @ResponseBody
    Object updateCarInfoAjax(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> pageParam = super.getParametersFromPage(request);
        Map<String, Object> vo = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", "false");
        try {
            //            Object obj = request.getSession().getAttribute("newVehicleModel");
            String uId = super.getUserInfo(request).getUid();
            String vehicleModelKey = uId + VEHICLE_MODEL_KEY_PREFIX;
            Object obj = spyMemcachedClient.get(vehicleModelKey);
            if (obj != null) {
                vo = (HashMap<String, Object>) obj;
                UserModel userModel = super.getUserInfo(request);
                vo.put("uid", userModel.getUid());
                if (pageParam.get("viNumber") != null) {
                    vo.put("viNumber", URLDecoder.decode(pageParam.get("viNumber"), "UTF-8")
                        .toUpperCase());
                }
                if (StringUtils.isNotEmpty(pageParam.get("viStartTime"))) {// 上路时间
                    vo.put("viStartTime", DateUtil.parserDateFromString(
                        pageParam.get("viStartTime"), DateUtil.DATEFORMAT5));
                }
                if (StringUtils.isNotEmpty(pageParam.get("vlCityId"))) {// 车辆归属地
                    vo.put("vlCityId", pageParam.get("vlCityId"));
                }
                if (StringUtils.isNotEmpty(pageParam.get("vlCityName"))) {// 车辆归属地名称
                    vo.put("vlCityName", URLDecoder.decode(pageParam.get("vlCityName"), "UTF-8"));
                }
                // 行使里程
                vo.put("viMileage", pageParam.get("viMileage"));

                // 车架号
                vo.put("viVin", pageParam.get("viVin").toUpperCase());

                // 发动机号
                vo.put("engineNo", pageParam.get("engineNo").toUpperCase());

                if (StringUtils.isNotEmpty(pageParam.get("sweptVolume"))) { //排量
                    vo.put("sweptVolume", URLDecoder.decode(pageParam.get("sweptVolume"), "UTF-8"));
                }
                //                request.getSession().setAttribute("newVehicleModel", vo);
                this.spyMemcachedClient.set(vehicleModelKey, Constant.MEMCACHED_SAVETIME_24, vo);
                // 1、sofa新增车辆信息
                Map<String, String> resMap = myCarManageService.modifyVehicle(vo);
                if (resMap == null) {// 修改不成功
                    if (logger.isErrorEnabled()) {
                        logger.error("处理用户提交车辆信息修改异常，sofa接口返回：null");
                    }
                    map.put("errorUrl", super.errorPageUrl("BUSY-ERR", "网络错误，请稍后再试", null, null));
                    return map;
                } else if (!Constant.SOFA_RETURN_CODE_SUCCESS.equals(resMap.get("returnCode"))) {
                    String errorMsg = "修改车辆信息失败";
                    if (Constant.SOFA_RETURN_CODE_REPEAT.equals(resMap.get("returnCode"))) {
                        errorMsg = "已添加过该辆车";
                    }
                    if (logger.isErrorEnabled()) {
                        logger.error("处理用户提交车辆信息修改异常，sofa接口返回码：{} |错误描述：{}",
                            resMap.get("returnCode"), resMap.get("returnDesc"));
                    }
                    map.put("errorUrl", super.errorPageUrl("BUSY-ERR", errorMsg, null, null));
                    return map;
                } else {
                    //处理缓存中的车辆列表
                    List<Map<String, Object>> vehicleList = spyMemcachedClient.get(userModel
                        .getUid() + "_myCarList");
                    if (null != vehicleList && !vehicleList.isEmpty()) {
                        for (int i = 0; i < vehicleList.size(); i++) {
                            Map<String, Object> vMap = vehicleList.get(i);
                            if (vMap.get("viId").equals(vo.get("viId"))) {
                                vehicleList.set(i, vo); //修改缓存中这辆车
                                break;
                            }
                        }
                        //更新缓存中的车辆数据
                        spyMemcachedClient.set(userModel.getUid() + "_myCarList",
                            Constant.MEMCACHED_SAVETIME_24, vehicleList);
                    }
                    //////////////////
                    String backUrl = "/car/myCarList";
                    Object backObj = request.getSession().getAttribute("backUrl");
                    if (backObj != null) {
                        backUrl = backObj.toString();
                    }
                    request.setAttribute("backUrl", backUrl);
                    map.put("success", "true");
                    return map;
                }
            } else { // 跳转到车辆列表页面
                logger.error("处理用户提交车辆信息修改异常,session中无更新车辆信息");
                map.put("errorUrl", super.errorPageUrl("BUSY-ERR", "用户登录超时,请重新登录", null, null));
                return map;
            }
        } catch (Exception e) {
            logger.error("处理用户提交车辆信息增加异常", e);
            map.put("errorUrl", super.errorPageUrl("BUSY-ERR", "用户登录超时,请重新登录", null, null));
            return map;
        }
    }

    @RequestMapping("/distinguish")
    public @ResponseBody
    Map distinguish(HttpServletRequest request, HttpServletResponse response) {
        String base64Content = request.getParameter("base64Content");
        UserModel userModel = super.getUserInfo(request); //从session中获取用户信息 
        ResponseData<Map<String, String>> responseData = vehicleDrivingLicenseDistinguishBizService
            .distinguish(userModel.getUid(), base64Content);

        Map responseMap = new HashMap();

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");

        if (!CommonCodeEnum.SUCCESS_ENUM.getErrorCode().equals(responseData.getReturnCode())) {
            responseMap.put("code", responseData.getReturnCode());
            responseMap.put("errorMsg", "识别失败,请手动填写车辆信息");
            logger.info("识别失败getUid={},responseMap={}", userModel.getUid(),
                JSONObject.toJSON(responseMap).toString());
            return responseMap;
        }
        responseMap.put("code", CommonCodeEnum.SUCCESS_ENUM.getErrorCode());
        responseMap.put("errorMsg", "识别成功");
        responseMap.put("res", JSONObject.toJSON(responseData.getObj()).toString());
        logger.info("识别成功getUid={},responseMap={}", userModel.getUid(),
            JSONObject.toJSON(responseMap).toString());
        return responseMap;
    }

    /**
     * ajax获取用户默认车辆信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getDefaultCarInfo")
    public @ResponseBody
    Object getDefaultCarInfo(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        // 2、后端获取车主的车辆信息
        // 1、验证用户是否授权使用车主服务
        UserModel userModel = super.getUserInfo(request);
        try {
            logger.info("userModel={}", userModel != null ? userModel.getUid() : "");

            List<Map<String, Object>> vehicleList = myCarManageService
                .queryVehicleListByUid(userModel.getUid());

            Map<String, Object> myCar = null;

            String limitedContent = null;

            List<Map<String, Object>> messageList = null;
            // 3、获取车主默认车辆
            if (vehicleList != null && vehicleList.size() > 1) {
                for (Map<String, Object> car : vehicleList) {
                    if (car.get("defaultStatus") instanceof Integer
                        && Integer.parseInt(car.get("defaultStatus").toString()) == 1) {
                        myCar = car;
                    }
                }
                if (myCar == null) { //
                    myCar = vehicleList.get(0);
                }
            } else if (vehicleList != null && vehicleList.size() == 1) {
                myCar = vehicleList.get(0);
            }

            // 5、查询常驻城市
            Map<String, String> cityMap = null;
            try {
                cityMap = ownerMessage.queryResidentcity(userModel.getUid());
            } catch (Exception e) {
                if (logger.isErrorEnabled())
                    logger.error("调用查询常驻城市sofa接口异常", e);
            }
            if (cityMap == null) {
                // 从cookie中取出常驻城市
                Cookie cityName = CookieTool.getCookieByName(request, "aliPay_residentCityName");
                Cookie cityCode = CookieTool.getCookieByName(request, "aliPay_residentCityCode");
                if (cityName != null) {
                    cityMap = new HashMap<String, String>();
                    cityMap.put("resident_city_name",
                        URLDecoder.decode(cityName.getValue(), "UTF-8"));
                    cityMap.put("resident_city_code",
                        URLDecoder.decode(cityCode.getValue(), "UTF-8"));
                }
            }
            // 7、查询是否有未读消息
            int unReadMessage = 0;
            try {
                unReadMessage = ownerMessage.messageGetUnreadcount(userModel.getUid());
            } catch (Exception e) {
                logger.error("调用查询未读消息数量sofa接口异常", e);
            }
            if (unReadMessage > 0) {
                // 查询未读消息
                try {
                    messageList = ownerMessage.messageBatchquery(userModel.getUid(), false, 1, 1);

                } catch (Exception e) {
                    logger.error("调用查询最新未读消息sofa接口异常", e);
                }
            } else if (cityMap != null && cityMap.get("resident_city_code") != null) {
                try {
                    limitedContent = ownerMessage.queryLimitedLineContent(
                        cityMap.get("resident_city_code"), new Date());

                } catch (Exception e) {
                    logger.error("调用查询限行信息sofa接口异常", e);
                }
            }

            result.put("success", true);
            result.put("defaultCar", myCar);
            if (messageList != null && !messageList.isEmpty()) {
                //                request.setAttribute("message", messageList.get(0));
                result.put("messageList", messageList.get(0));
            } else {
                result.put("messageList", null);
            }

            result.put("limitedContent", ObjectUtil.toString(limitedContent));
        } catch (Exception e) {
            if (logger.isErrorEnabled())
                logger.error("首页ajax获取默认车辆异常", e);
        }
        return result;
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

    /**
     * 查询类目信息ajax方法
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryCategoryAppList")
    public @ResponseBody
    Object queryCategoryAppList(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> pageParam = super.getParametersFromPage(request);
        List<Map<String, Object>> result = null;
        try {
            Map<String, String> userMap = new HashMap<String, String>();
            UserModel userModel = super.getUserInfo(request);
            userMap.put("userId", userModel.getUid());
            String viNumber = null;
            if (StringUtils.isNotEmpty(pageParam.get("viNumber"))) {
                viNumber = URLDecoder.decode(pageParam.get("viNumber"), "UTF-8");
            }
            result = ownerMessage.queryCategoryApp(userMap,
                super.districtToCity(pageParam.get("cityCode")), viNumber);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("查询城市限行信息异常", e);
            }
        }
        return result;
    }

}
