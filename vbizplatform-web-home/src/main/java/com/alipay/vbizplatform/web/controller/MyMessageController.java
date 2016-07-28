package com.alipay.vbizplatform.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.oe.industrydata.common.service.facade.model.rpc.Param;
import com.alipay.vbizplatform.common.util.Constant;
import com.alipay.vbizplatform.common.util.ReadConfig;
import com.alipay.vbizplatform.common.util.StringUtils;
import com.alipay.vbizplatform.core.model.UserModel;
import com.alipay.vbizplatform.vehicle.service.IOwnerMessage;

/**
 * @desc 车主信息入口
 * @author yuanfeng
 * @datetime 2016-4-14 下午4:11:24
 */
@RequestMapping("/msg")
@Controller
public class MyMessageController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger("vbizplatform");// 信息日志

    @Resource(name = "ownerMessage")
    private IOwnerMessage       ownerMessage;

    /**
     * 进入消息列表页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/toMessageList")
    public String toMessageList(HttpServletRequest request) {
        return "page/msg/carsMsgsMain";
    }

    /**
     * 获取消息列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getMessageList")
    public @ResponseBody List<Map<String, Object>> getMessageList(HttpServletRequest request,
                                                                  HttpServletResponse response) {
        Map<String, String> pageParam = super.getParametersFromPage(request);
        List<Map<String, Object>> messageList = null;
        int page = 1; //第一页
        try {
            UserModel userModel = super.getUserInfo(request);
            if (StringUtils.isNotEmpty(pageParam.get("page"))) {
                page = Integer.parseInt(pageParam.get("page"));
            }
            // 1、查询消息列表
            messageList = ownerMessage.messageBatchquery(userModel.getUid(), null, page, 10);
        } catch (Exception e) {
            logger.error("处理用户请求进入消息列表页面异常", e);
            return null;
        }
        return messageList;
    }

    /**
     * 修改消息阅读状态
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/msgIsread")
    public @ResponseBody String msgIsread(HttpServletRequest request,
                                          HttpServletResponse response) {
        Map<String, String> pageParam = super.getParametersFromPage(request);
        String ret = null;
        try {
            UserModel userModel = super.getUserInfo(request);
            //1、修改该消息阅读状态
            if (ownerMessage.messageEditIsread(userModel.getUid(), pageParam.get("messageId"),
                pageParam.get("broadcastScope"))) {
                ret = "ok";
            }
        } catch (Exception e) {
            logger.error("处理消息已读状态修改异常", e);
            return null;
        }
        return ret;
    }

    /**
     * 删除消息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/msgDel")
    public @ResponseBody String msgDel(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> pageParam = super.getParametersFromPage(request);
        String ret = null;
        try {
            UserModel userModel = super.getUserInfo(request);
            //1、删除消息
            if (ownerMessage.messageDelete(userModel.getUid(), pageParam.get("messageId"),
                pageParam.get("broadcastScope"))) {
                ret = "ok";
            }
        } catch (Exception e) {
            logger.error("处理消息已读状态修改异常", e);
            return null;
        }
        return ret;
    }

    /**
     * 发送消息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/messageSend")
    public @ResponseBody String messageSend(HttpServletRequest request,
                                            HttpServletResponse response) {
        try {
            UserModel userModel = super.getUserInfo(request);
            for (int i = 0; i < 10; i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("method", "alipay.open.ecology.message.send");
                map.put("charset", "UTF-8");
                map.put("version", "1.0");
                map.put("appId", ReadConfig.getInstance().getConfigItem(Constant.ALIPAY_VBIZPLATFORM_APPID));
                map.put("userId", userModel.getUid());
                map.put("messageType", "MESSAGE_OFFICIAL");
                map.put("senderType", "SENDER_PLATFORM");
                map.put("senderId", "678567564536475869");
                map.put("title", i + "车生活" + i);
                map.put("content", "参与车生活体验活动");
                map.put("icon", "fghjjkkjjh");
                map.put("urlLink", "lkjghjkl;jhgjkljkhjg");
                map.put("broadcastScope", "USER");
                //1、发送消息
                ownerMessage.messageSend(map);
            }
        } catch (Exception e) {
            logger.error("处理消息已读状态修改异常", e);
            return null;
        }
        return "ok";
    }

}
