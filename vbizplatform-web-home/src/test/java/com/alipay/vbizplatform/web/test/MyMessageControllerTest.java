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
import com.alipay.vbizplatform.web.controller.MyMessageController;

/**
 * 
 * @author yuanfeng
 * @version $Id: MyMessageControllerTest.java, v 0.1 2016年5月1日 下午11:43:53 yuanfeng Exp $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:META-INF/application-springmvc.xml",
                                    "classpath:META-INF/applicationContext-sofaclient.xml",
                                    "classpath:META-INF/applicationContext-memcached.xml" })
public class MyMessageControllerTest {

    @Autowired
    MyMessageController   messageController;

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
    public void testGetMessageList() throws Exception {
        //准备参数
        MockHttpServletRequestBuilder requestBuilders = MockMvcRequestBuilders
            .post("/msg/getMessageList");
        requestBuilders.sessionAttr(Constant.ALIPAY_USER_SESSION_KEY, this.getUserModel());
        //发送请求
        ResultActions resultActions = this.mockMvc.perform(requestBuilders);
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out
            .println("=====获取消息列表客户端获得反馈数据:" + result + ",页面跳转：" + mvcResult.getModelAndView());
    }

    @Test
    public void testMsgIsread() throws Exception {
        //准备参数
        String messageId = "2";
        String broadcastScope = "USER";
        MockHttpServletRequestBuilder requestBuilders = MockMvcRequestBuilders
            .post("/msg/msgIsread");
        requestBuilders.param("messageId", messageId);
        requestBuilders.param("broadcastScope", broadcastScope);
        requestBuilders.sessionAttr(Constant.ALIPAY_USER_SESSION_KEY, this.getUserModel());
        //发送请求
        ResultActions resultActions = this.mockMvc.perform(requestBuilders);
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out
            .println("=====修改消息阅读状态客户端获得反馈数据:" + result + ",页面跳转：" + mvcResult.getModelAndView());
    }

    @Test
    public void testMsgDel() throws Exception {
        //准备参数
        String messageId = "2";
        String broadcastScope = "USER";
        MockHttpServletRequestBuilder requestBuilders = MockMvcRequestBuilders.post("/msg/msgDel");
        requestBuilders.param("messageId", messageId);
        requestBuilders.param("broadcastScope", broadcastScope);
        requestBuilders.sessionAttr(Constant.ALIPAY_USER_SESSION_KEY, this.getUserModel());
        //发送请求
        ResultActions resultActions = this.mockMvc.perform(requestBuilders);
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====删除消息客户端获得反馈数据:" + result + ",页面跳转：" + mvcResult.getModelAndView());
    }

    @Test
    public void testMessageSend() throws Exception {
        //准备参数 
        MockHttpServletRequestBuilder requestBuilders = MockMvcRequestBuilders
            .post("/msg/messageSend");
        requestBuilders.sessionAttr(Constant.ALIPAY_USER_SESSION_KEY, this.getUserModel());
        //发送请求
        ResultActions resultActions = this.mockMvc.perform(requestBuilders);
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====发送消息客户端获得反馈数据:" + result + ",页面跳转：" + mvcResult.getModelAndView());
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
