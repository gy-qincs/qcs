/**
  * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.alipay.vbizplatform.web.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alipay.vbizplatform.common.util.Constant;
import com.alipay.vbizplatform.common.util.InitConfigServlet;
import com.alipay.vbizplatform.core.model.UserModel;
import com.alipay.vbizplatform.web.controller.MyCarManageController;

/**
 * 
 * @author yuanfeng
 * @version $Id: MyCarManageControllerTest.java, v 0.1 2016年5月2日 上午1:18:59 yuanfeng Exp $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:META-INF/application-springmvc.xml",
                                    "classpath:META-INF/applicationContext-sofaclient.xml",
                                    "classpath:META-INF/applicationContext-memcached.xml" })
public class MyCarManageControllerTest {

    @Autowired
    MyCarManageController myCarManageController;

    @Autowired
    WebApplicationContext wac;

    MockMvc               mockMvc;

    //用户信息
    UserModel             userModel;

    Map<String, Object>   vehicleMap;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        InitConfigServlet.setSYSTEMID_PFILE(
            "D:/git/vbizservice/vbizplatform/vbizplatform-web-home/src/main/resources/config/common-config.properties");
    }

    @Test
    public void testPortal() throws Exception {
        //准备参数
        MockHttpServletRequestBuilder requestBuilders = MockMvcRequestBuilders.post("/car/portal");
        requestBuilders.sessionAttr(Constant.ALIPAY_USER_SESSION_KEY, this.getUserModel());
        //发送请求
        ResultActions resultActions = this.mockMvc.perform(requestBuilders);
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out
            .println("=====车主服务入口客户端获得反馈数据:" + result + ",页面跳转：" + mvcResult.getModelAndView());
    }

    @Test
    public void testQueryBrand() throws Exception {
        //准备参数
        MockHttpServletRequestBuilder requestBuilders = MockMvcRequestBuilders
            .post("/car/queryBrand");
        requestBuilders.sessionAttr(Constant.ALIPAY_USER_SESSION_KEY, this.getUserModel());
        //发送请求
        ResultActions resultActions = this.mockMvc.perform(requestBuilders);
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out
            .println("=====选择车辆品牌客户端获得反馈数据:" + result + ",页面跳转：" + mvcResult.getModelAndView());
    }

    @Test
    public void testQueryCarSeries() throws Exception {
        //准备参数
        String brandName = "奥迪";
        MockHttpServletRequestBuilder requestBuilders = MockMvcRequestBuilders
            .post("/car/queryCarSeries");
        requestBuilders.sessionAttr(Constant.ALIPAY_USER_SESSION_KEY, this.getUserModel());
        requestBuilders.param("brandName", brandName);
        //发送请求
        ResultActions resultActions = this.mockMvc.perform(requestBuilders);
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out
            .println("=====查询汽车品牌相关车系客户端获得反馈数据:" + result + ",页面跳转：" + mvcResult.getModelAndView());
    }
 
    @Test
    public void testQueryCarYear() throws Exception {
        //准备参数
        String carSeriesName = "A1";
        MockHttpServletRequestBuilder requestBuilders = MockMvcRequestBuilders
            .post("/car/queryCarYear");
        requestBuilders.sessionAttr(Constant.ALIPAY_USER_SESSION_KEY, this.getUserModel());
        Map<String, Object> carMap = this.getVehicleMap();
        requestBuilders.sessionAttr("newVehicleModel", carMap);
        requestBuilders.param("carSeriesName", carSeriesName);

        //发送请求
        ResultActions resultActions = this.mockMvc.perform(requestBuilders);
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out
            .println("=====查询车辆年款信息客户端获得反馈数据:" + result + ",页面跳转：" + mvcResult.getModelAndView());
    }

    @Test
    public void testQueryCarModels() throws Exception {
        //准备参数
        String carYearName = "2012款";
        MockHttpServletRequestBuilder requestBuilders = MockMvcRequestBuilders
            .post("/car/queryCarModels");
        requestBuilders.sessionAttr(Constant.ALIPAY_USER_SESSION_KEY, this.getUserModel());
        Map<String, Object> map = this.getVehicleMap();
        map.put("viSeriesName", "A1");
        requestBuilders.sessionAttr("newVehicleModel", map);
        requestBuilders.param("carYearName", carYearName);
        //发送请求
        ResultActions resultActions = this.mockMvc.perform(requestBuilders);
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println(
            "=====查询车系相关的车辆型号客户端获得反馈数据:" + result + ",页面跳转：" + mvcResult.getModelAndView());
    }

    /**
     * Getter method for property <tt>userModel</tt>.
     * @return property value of userModel
     */
    public UserModel getUserModel() {
        if (userModel == null) {
            userModel = new UserModel();
        }
        userModel.setUid("2015052100077000000000120773");
        userModel.setRealName("冰城风儿");
        userModel.setPhoneNumber("13810331329");
        userModel.setCertNo("2301012345678905678");
        return userModel;
    }

    /**
     * Getter method for property <tt>vehicleMap</tt>.
     * 
     * @return property value of vehicleMap
     */
    public Map<String, Object> getVehicleMap() {
        if (vehicleMap == null) {
            vehicleMap = new HashMap<String, Object>();
        }
        vehicleMap.put("viBrandName", "奥迪");
        vehicleMap.put("bgUrl", "http://pic8.nipic.com/20100808/4953913_162517044879_2.jpg");
        vehicleMap.put("viLogoUrl", "http://www.zt.zj.com/a/audishaoxin/logo.jpg");
        return vehicleMap;
    }

}
