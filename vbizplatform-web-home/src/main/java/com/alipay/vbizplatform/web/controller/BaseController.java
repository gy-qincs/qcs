package com.alipay.vbizplatform.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.vbizplatform.common.util.Constant;
import com.alipay.vbizplatform.common.util.ReadConfig;
import com.alipay.vbizplatform.core.model.UserModel;

@SuppressWarnings({ "rawtypes" })
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger("vbizplatform"); // 信息日志

    public BaseController() {
    }

    /**
     * 获取session中用户信息
     * @param request
     * @return
     */
    public UserModel getUserInfo(HttpServletRequest request) {
        Object object = request.getSession().getAttribute(Constant.ALIPAY_USER_SESSION_KEY);
        UserModel userModel = null;
        if (object != null) {
            userModel = (UserModel) object;
        }
        return userModel;
    }

    /**
     * 处理页面传入参数
     * @param request
     * @return Map<String, String>:页面传参的集合
     */
    public Map<String, String> getParametersFromPage(HttpServletRequest request) {
        Map<String, String> param = new HashMap<String, String>();
        String varName = "default";
        String varValue = null;
        StringBuilder log = new StringBuilder("参数打印:");
        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()) { // 循环获取参数
            Object obj = e.nextElement();
            varName = obj.toString();
            varValue = request.getParameter(varName);
            param.put(varName, varValue);
            log.append(varName).append("=").append(varValue);
        }
        if (logger.isDebugEnabled()) {
            logger.debug(log.toString());
        }
        return param;
    }

    /**
     * 跳转到支付宝app的错误页面
     * 
     * @param errtype:错误类型
     *            LOADING-ERR(加载出错) BUSY-ERR(系统正忙，客官请稍候) EMPTY-ERR（空白页）
     *            NETWORK-ERR（网络无法连接） NOTFOUND-ERR（404错误）
     * 
     * @param title:错误页面的错误描述
     * @param button:错误页面的按钮名称
     * @param url:点击按钮后跳转的地址
     */
    public void redirectErrorPage(String errtype, String title, String button, String url,
                                  HttpServletResponse response) {
        StringBuffer redirectUrl = new StringBuffer(
            "https://os.alipayobjects.com/others/rpcLimit.html?");
        redirectUrl.append("errtype=").append(errtype);
        redirectUrl.append("&title=");
        try {
            redirectUrl.append(URLEncoder.encode(title, "UTF-8"));
            redirectUrl.append("&button=");
            if (StringUtils.isEmpty(button)) {
                button = "返回首页";
            }
            redirectUrl.append(URLEncoder.encode(button, "UTF-8"));
            redirectUrl.append("&url=");
            if (StringUtils.isEmpty(url)) {
                url = ReadConfig.getInstance().getConfigItem("project.publish.url");
            }
            redirectUrl.append(url);
            redirectUrl.append("&webview_options=so%3DNO");
            response.sendRedirect(redirectUrl.toString());
        } catch (UnsupportedEncodingException e) {
            if (logger.isErrorEnabled()) {
                logger.error("跳转到错误页面转码异常", e);
            }
        } catch (IOException e) {
            if (logger.isErrorEnabled()) {
                logger.error("跳转到错误页面异常", e);
            }
        }
    }

    /**
     * 返回支付宝app的错误页面URL
     * 
     * @param errtype:错误类型
     *            LOADING-ERR(加载出错) BUSY-ERR(系统正忙，客官请稍候) EMPTY-ERR（空白页）
     *            NETWORK-ERR（网络无法连接） NOTFOUND-ERR（404错误）
     * 
     * @param title:错误页面的错误描述
     * @param button:错误页面的按钮名称
     * @param url:点击按钮后跳转的地址
     */
    public String errorPageUrl(String errtype, String title, String button, String url) {
        StringBuffer redirectUrl = new StringBuffer(
            "https://os.alipayobjects.com/others/rpcLimit.html?");
        redirectUrl.append("errtype=").append(errtype);
        redirectUrl.append("&title=");
        try {
            redirectUrl.append(URLEncoder.encode(title, "UTF-8"));
            redirectUrl.append("&button=");
            if (StringUtils.isEmpty(button)) {
                button = "返回首页";
            }
            redirectUrl.append(URLEncoder.encode(button, "UTF-8"));
            redirectUrl.append("&url=");
            if (StringUtils.isEmpty(url)) {
                url = ReadConfig.getInstance().getConfigItem("project.publish.url");
            }
            redirectUrl.append(url);
            redirectUrl.append("&webview_options=so%3DNO");
        } catch (UnsupportedEncodingException e) {
            if (logger.isErrorEnabled()) {
                logger.error("跳转到错误页面转码异常", e);
            }
            return redirectUrl.toString();
        }
        return redirectUrl.toString();
    }

    /**
     * 根据vm模版动态生成html
     * 
     * @return 生成后html代码
     */
    protected String invokeVelocityDynamicCreateHtml(String url) {
        //初始化并取得Velocity引擎
        VelocityEngine ve = new VelocityEngine();
        Properties pros = new Properties();
        //取得velocity的上下文context
        VelocityContext context = new VelocityContext();
        StringWriter write = new StringWriter();
        String wi = "";
        //取得velocity的模版
        Template t = null;
        try {
            InputStream is = BaseController.class.getClassLoader()
                .getResourceAsStream("config/velocity.properties");
            InputStreamReader in = new InputStreamReader(is);
            pros.load(in);
            ve.init(pros);
            t = ve.getTemplate(url);
            t.merge(context, write);
            wi = write.toString();
            write.flush();
            write.close();
            in.close();
            is.close();
        } catch (Throwable e) {
            if (logger.isErrorEnabled()) {
                logger.error("根据velocity生成html异常", e);
            }
            return "";
        }
        if (logger.isInfoEnabled()) {
            logger.info("调用vm生成成功,html={}", write.toString());
        }
        return wi;
    }

    /**
     * 城市区域编码格式化市编码
     * @param cityCode
     * @return
     */
    public String districtToCity(String cityCode) {
        if (StringUtils.isNotEmpty(cityCode)) {
            boolean tf = false;
            for (String city : Constant.MUNICIPALS) {
                if (cityCode.indexOf(city) == 0) {
                    tf = true;
                    break;
                }
            }
            if (tf) {
                cityCode = cityCode.substring(0, 3) + "100";
            } else {
                cityCode = cityCode.substring(0, 4) + "00";
            }
        }
        return cityCode;
    }

}
