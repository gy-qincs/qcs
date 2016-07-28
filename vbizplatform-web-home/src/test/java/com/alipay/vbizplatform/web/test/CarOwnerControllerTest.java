/**
  * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.alipay.vbizplatform.web.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
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
import com.alipay.vbizplatform.web.controller.CarOwnerController;

/**
 * 
 * @author yuanfeng
 * @version $Id: CarOwnerControllerTest.java, v 0.1 2016年5月1日 下午5:34:50 yuanfeng Exp $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:META-INF/application-springmvc.xml",
                                    "classpath:META-INF/applicationContext-sofaclient.xml",
                                    "classpath:META-INF/applicationContext-memcached.xml" })
@TransactionConfiguration(defaultRollback = true)
//@Transactional
public class CarOwnerControllerTest {

    @Autowired
    CarOwnerController    carOwnerController;

    @Autowired
    WebApplicationContext wac;

    MockMvc               mockMvc;

    //用户信息
    UserModel             userModel;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        InitConfigServlet.setSYSTEMID_PFILE(
            "D:/git/vbizservice/vbizplatform/vbizplatform-web-home/src/main/resources/config/common-config.properties");
    }

    @Test
    public void testModifyResidentcity() throws Exception {
        //准备参数
        String cityName = "北京";
        String cityCode = "110100";
        MockHttpServletRequestBuilder requestBuilders = MockMvcRequestBuilders
            .post("/owner/modifyResidentcity");
        requestBuilders.sessionAttr(Constant.ALIPAY_USER_SESSION_KEY, this.getUserModel());
        requestBuilders.param("cityName", cityName);
        requestBuilders.param("cityCode", cityCode);
        //发送请求
        ResultActions resultActions = this.mockMvc.perform(requestBuilders);
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====设置常驻城市客户端获得反馈数据:" + result);
    }

    @Test
    public void testQueryLimited() throws Exception {
        //准备参数
        String cityCode = "3700100";
        MockHttpServletRequestBuilder requestBuilders = MockMvcRequestBuilders
            .post("/owner/queryLimited");
        requestBuilders.sessionAttr(Constant.ALIPAY_USER_SESSION_KEY, this.getUserModel());
        requestBuilders.param("cityCode", cityCode);
        //发送请求
        ResultActions resultActions = this.mockMvc.perform(requestBuilders);
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====查询城市限行信息ajax方法客户端获得反馈数据:" + result);
    }

    /**
     * Getter method for property <tt>userModel</tt>.
     * 
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

}
