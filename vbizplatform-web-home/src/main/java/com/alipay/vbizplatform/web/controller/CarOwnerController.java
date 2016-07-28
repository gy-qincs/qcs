package com.alipay.vbizplatform.web.controller;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.vbizplatform.common.util.Constant;
import com.alipay.vbizplatform.common.util.CookieTool;
import com.alipay.vbizplatform.common.util.ReadConfig;
import com.alipay.vbizplatform.common.util.SpyMemcachedClient;
import com.alipay.vbizplatform.core.model.UserModel;
import com.alipay.vbizplatform.vehicle.service.IOwnerMessage;

/**
 * CarOwnerController.java
 * 
 * @desc 个人中心
 * @author yuanfeng
 * @datetime 2016-4-5 下午8:30:24
 */
@RequestMapping("/owner")
@Controller
@SuppressWarnings("unchecked")
public class CarOwnerController extends BaseController {

    private static final Logger logger             = LoggerFactory.getLogger("vbizplatform");// 信息日志

    //成功结果的常量
    private static final String BUS_RESULT_SUCCESS = "0";
    //失败结果的常量
    private static final String BUS_RESULT_FAILS   = "1";

    @Resource(name = "ownerMessage")
    private IOwnerMessage       ownerMessage;

    @Resource(name = "spyMemcachedClient")
    private SpyMemcachedClient  spyMemcachedClient;

    /**
     * 设置常驻城市
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/modifyResidentcity")
    public @ResponseBody Map<String, String> modifyResidentcity(HttpServletRequest request,
                                                                HttpServletResponse response) {
        Map<String, String> retMap = new HashMap<String, String>();
        retMap.put("result", BUS_RESULT_FAILS);
        UserModel userModel = null;
        Map<String, String> pageParam = super.getParametersFromPage(request);
        try {
            //1、获取用户信息
            Object object = request.getSession().getAttribute(Constant.ALIPAY_USER_SESSION_KEY);
            if (object != null) {
                userModel = (UserModel) object;
            }
            String cityCode = pageParam.get("cityCode");
            cityCode = super.districtToCity(cityCode);
            //2、保存在cookie中
            CookieTool.addCookie(response, "aliPay_residentCityName",
                URLEncoder.encode(pageParam.get("cityName"), "UTF-8"), 2592000);
            CookieTool.addCookie(response, "aliPay_residentCityCode",
                URLEncoder.encode(cityCode, "UTF-8"), 2592000);
            //3、调用设置常驻城市方法
            boolean tf = ownerMessage.modifyResidentcity(userModel.getUid(), cityCode,
                pageParam.get("cityName"));
            if (tf) {//设置成功
                //首展用户定位session修改 start
                Object userLocaObject = request.getSession()
                    .getAttribute(Constant.ALIPAY_USER_LOCATION_SESSION_KEY);
                Map<String, String> userLoca = null;
                if (null != userLocaObject) {
                    userLoca = (Map<String, String>) userLocaObject;
                } else {
                    userLoca = new HashMap<String, String>();
                }
                userLoca.put("residentCityCode", cityCode);
                userLoca.put("residentCityName", pageParam.get("cityName"));
                request.getSession().setAttribute(Constant.ALIPAY_USER_LOCATION_SESSION_KEY,
                    userLoca);
                //end
                retMap.put("result", BUS_RESULT_SUCCESS);
                //4、获取当前城市限行信息
                String limitedContent = ownerMessage.queryLimitedLineContent(cityCode, new Date());
                retMap.put("content", limitedContent);
                return retMap;
            } else {
                return null;
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled())
                logger.error("处理用户请求设置常驻城市异常", e);
        }
        return null;
    }

    /**
     * 保存用户坐标信息
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/modifyLocation")
    public @ResponseBody Map<String, String> modifyLocation(HttpServletRequest request,
                                                            HttpServletResponse response) {
        Map<String, String> retMap = new HashMap<String, String>();
        retMap.put("result", BUS_RESULT_SUCCESS);
        //        UserModel userModel = null;
        Map<String, String> pageParam = super.getParametersFromPage(request);
        try {
            UserModel userModel = super.getUserInfo(request);
            Object userLocaObject = request.getSession()
                .getAttribute(Constant.ALIPAY_USER_LOCATION_SESSION_KEY);

            Map<String, String> locationMap = null;
            if (null != userLocaObject) {
                locationMap = (Map<String, String>) userLocaObject;
            } else {
                locationMap = new HashMap<String, String>();
            }
            //获取用户坐标
            locationMap.put("latitude", pageParam.get("latitude"));
            locationMap.put("longitude", pageParam.get("longitude"));
            locationMap.put("cityName", pageParam.get("city"));
            locationMap.put("cityCode", pageParam.get("citycode"));
            //1、保存坐标信息在会话中
            request.getSession().setAttribute(Constant.ALIPAY_USER_LOCATION_SESSION_KEY,
                locationMap);
            //2、保存定位信息在ocs中
            spyMemcachedClient.set(userModel.getUid() + "_LastTimeGPS",
                Constant.MEMCACHED_SAVETIME_24 * 3, pageParam.get("citycode"));
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("处理用户请求设置常驻城市异常", e);
            }
            retMap.put("result", BUS_RESULT_FAILS);
        }
        return retMap;
    }

    /**
     * 查询城市限行信息ajax方法
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryLimited")
    public @ResponseBody Object queryLimited(HttpServletRequest request,
                                             HttpServletResponse response) {
        Map<String, String> pageParam = super.getParametersFromPage(request);
        Map<String, String> result = new HashMap<String, String>();
        try {
            String cityCode = pageParam.get("cityCode");
            cityCode = super.districtToCity(cityCode);
            //1、查询所在城市限行信息
            //ownerMessage.queryLimitedLineContent(cityCode, new Date());

            result = this.modifyResidentcity(request, response);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("查询城市限行信息异常", e);
            }
        }
        return result;
    }

    /**
     * 进入车主个人中心
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        UserModel userModel = null;
        try {
            // 1、验证用户是否授权使用车主服务
            Object object = request.getSession().getAttribute(Constant.ALIPAY_USER_SESSION_KEY);
            if (object != null) {
                userModel = (UserModel) object;
                request.setAttribute("loginUser", userModel.getUid());
            } else {
                super.redirectErrorPage("BUSY-ERR", "用户未获得授权", null, null, response);
                return null;
            }
            request.setAttribute("myOrders",
                ReadConfig.getInstance().getConfigItem("alipay.vbizplatform.myorder"));

            request.setAttribute("pid",
                ReadConfig.getInstance().getConfigItem("alipay.vbizplatform.pid"));
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("处理用户请求进入车主个人中心异常", e);
            }
            super.redirectErrorPage("BUSY-ERR", "系统正忙、客官请稍候", null, null, response);
            return null;
        }
        return "page/personal";
    }

    /**
     * 进入订单列表页面
     * 
     * @param request
     * @param response
     * @return
     */
    //@RequestMapping(value = "/order")
    public String order(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 1、查询订单列表信息

        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("处理用户请求进入订单列表页面异常", e);
            }
            super.redirectErrorPage("BUSY-ERR", "页面加载错误", null, null, response);
            return null;
        }
        return null;
    }

    /**
     * 进入订单详情页面
     * 
     * @param request
     * @param response
     * @return
     */
    //@RequestMapping(value = "/orderInfo")
    public String orderInfo(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 1、查询订单详情信息

        } catch (Exception e) {
            logger.error("处理用户请求进入订单详情页面异常", e);
            super.redirectErrorPage("BUSY-ERR", "页面加载错误", null, null, response);
            return null;
        }
        return null;
    }

}
