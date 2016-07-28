package com.alipay.vbizplatform.web.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.common.lang.ObjectUtil;
import com.alipay.vbizplatform.common.util.Constant;
import com.alipay.vbizplatform.core.model.UserModel;
import com.alipay.vbizplatform.vbizplatform.common.service.integration.UserQueryIntegration;
import com.alipay.vbizplatform.vbizplatform.common.service.integration.UserauthIntegration;

/**
 * 会话过滤器
 * 
 * 
 * @author synck
 * @version $Id: SessionPowerFilter.java, v 0.1 2016年5月5日 下午7:41:20 synck Exp $
 */
public class SessionPowerFilter implements Filter {
    private List<String>         noLoginAccessUrlList;                                     //不需要登录就能访问的资源
    private String               noSessionRedirectUrl;                                     //没有会话跳转地址
    private String               indexRedirectUrl;                                         //认证完成跳转的主页
    private String               authPathUrl;                                              //强制认证URL

    private String               appId;                                                    //车主服务AppId
    //private String aspMd5Key;
    private static final Logger  logger = (Logger) LoggerFactory.getLogger("vbizplatform");

    @Resource(name = "userauthIntegration")
    private UserauthIntegration  userauthIntegration;

    @Resource(name = "userQueryIntegration")
    private UserQueryIntegration userQueryIntegration;

    public SessionPowerFilter() {
    }

    public void destroy() {
    }

    /**
     * 实现会话过滤
     * 1，用户会话是否存在
     * 2，如果是权限认证访问，则刷新用户信息
     * 3，会话不存在将用户重定向至userauth
     * 4,请求中带encrypt和random则进行access_token的解析
     * 5，通过access_token换取用户信息
     * 6，生成会话信息进行保存
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        String currentAccessUrl = request.getRequestURI();
        //获取用户信息
        UserModel loginUser = (UserModel) request.getSession().getAttribute("loginUser");
        //用户id
        String uid = loginUser == null ? "" : loginUser.getUid();
        //用户ip
        String requestIp = request.getRemoteAddr();
        if (logger.isInfoEnabled()) {
            logger.info("SessionPowerFilter sessionId={},currentAccessUrl={}",
                new Object[] { sessionId, currentAccessUrl });
        }
        if (logger.isDebugEnabled()) {
            logger.debug("SessionPowerFilter sessionId={},requestIp={},uid={},requestUrl={}",
                new Object[] { sessionId, requestIp, uid, currentAccessUrl });
        }
        /**
         * 不需要认证登录即可访问资源过滤
         */
        if (this.isNoLoginAccessUrlList(currentAccessUrl)) {
            filterChain.doFilter(request, response);
            return;
        }
        String sourceUrl = request.getParameter("sourceUrl");
        if (logger.isInfoEnabled()) {
            logger.info("来源url={}", sourceUrl);
        }
        //访问路径是否为认证授权路径如果匹配则强制用户进行认证跳转
        if (authPathUrl.equals(currentAccessUrl)) {
            if (null == loginUser) {
                if (logger.isInfoEnabled())
                    logger.info("用户请求授权页面链接，session 用户信息为空，跳转到阿里授权链接");
                String userauthUrl = this.userauthIntegration.createRedirectUrl(sourceUrl);//授权跳转链接
                response.sendRedirect(userauthUrl);
                return;
            } else {
                if (logger.isInfoEnabled())
                    logger.info("用户={}，请求授权页面链接，session 存在当前用户信息，根据uid调用获取用户信息接口", uid);

                //根据uid获取用户
                //用户根据access_token登录授权
                UserModel userModel = null;
                Map<String, Object> authResult = new HashMap<String, Object>();
                try {
                    authResult = this.userauthIntegration.createUserModel("", uid, "");
                } catch (Exception e) {
                    //e.printStackTrace();
                    logger.error("用户根据access_token登录授权", e);
                }

                String errorCode = ObjectUtil.toString(authResult.get("errorCode"), "");
                if (!(Constant.SOFA_INDUSTRYDATA_CODE_SUCCESS.equals(errorCode))) {
                    //用户session失效，置空session用户信息，防止死循环
                    session.removeAttribute("loginUser");
                    logger.error(
                        "SessionPowerFilter sessionId={},uid={},获取用户信息失败，错误码{},currentAccessUrl={},用户根据uid登录授权失败",
                        new Object[] { sessionId, uid, errorCode, currentAccessUrl });
                    //获取subCode
                    String subCode = ObjectUtil.toString(authResult.get("subCode"), "");
                    if(Constant.ALIPAY_USERAUTH_TOKEN_SUBCODE_UNAVALABLE.equals(subCode)){
                        if (logger.isInfoEnabled())
                            logger.info("用户请求授权页面链接，用户access_token无效，跳转到阿里授权链接");
                        String userauthUrl = this.userauthIntegration.createRedirectUrl(sourceUrl);//授权跳转链接
                        response.sendRedirect(userauthUrl);
                        return;
                    } else {
                        response.sendRedirect(this.noSessionRedirectUrl);
                        return;
                    }
                }
                //用户根据access_token登录授权失败
                userModel = (UserModel) authResult.get("userModel");
                //授权成功生产会话数据
                session.setAttribute("loginUser", userModel);

                response.sendRedirect(this.indexRedirectUrl);
                return;
            }
        }
        String random = request.getParameter("random");
        String encrypt = request.getParameter("encrypt");

        /**
         * 用户登录过滤
         */
        //重定向连接
        //        String userauthUrl = URLEncoder.encode(this.userauthIntegration.createRedirectUrl());//授权跳转链接

        if (loginUser == null || (random != null && encrypt != null)) {
            String access_token = null;
            //用户没有登录并且无access_token将用户重定向到授权验证模块进行验证

            if (StringUtils.isEmpty(random) || StringUtils.isEmpty(encrypt)) {
                if (logger.isDebugEnabled()) {
                    logger.debug(
                        "SessionPowerFilter用户登录授权数据为空sessionId={},requestIp={},uid={},requestUrl={},random={},encrypt={}",
                        new Object[] { sessionId, requestIp, uid, currentAccessUrl, random,
                                       encrypt });
                }
                if (logger.isInfoEnabled()) {
                    logger.info(
                        "SessionPowerFilter sessionId={},uid={},UserModel为空,currentAccessUrl={},用户将被重定向到授权模块",
                        new Object[] { sessionId, uid, currentAccessUrl });
                }
                //重定向连接
                response.sendRedirect(this.authPathUrl);
                return;
            }
            //认证请求及解析出access_token
            access_token = userauthIntegration.validateRequestSign(random, encrypt);
            logger.info("认证流程access_token={}", access_token);
            //认证请求及解析access_token失败
            if (access_token == null) {
                if (logger.isDebugEnabled()) {
                    logger.debug(
                        "SessionPowerFilter用户登录授权签名失败空sessionId={},requestIp={},uid={},requestUrl={},random={},encrypt={}",
                        new Object[] { sessionId, requestIp, uid, currentAccessUrl, random,
                                       encrypt });
                }
                logger.info("认证流程access_token={}失败,跳转到noSessionRedirectUrl={}", access_token,
                    noSessionRedirectUrl);
                response.sendRedirect(this.noSessionRedirectUrl);
                return;
            }
            //用户根据access_token登录授权
            UserModel userModel = null;
            Map<String, Object> authResult = new HashMap<String, Object>();
            try {
                authResult = this.userauthIntegration.createUserModel("", "", access_token);
            } catch (Exception e) {
                logger.error("用户根据access_token登录授权", e);
                response.sendRedirect(this.noSessionRedirectUrl);
                return;
            }

            String errorCode = ObjectUtil.toString(authResult.get("errorCode"), "");
            if (!(Constant.SOFA_INDUSTRYDATA_CODE_SUCCESS.equals(errorCode))) {
                logger.error(
                    "SessionPowerFilter sessionId={},uid={},获取用户信息失败，错误码{},currentAccessUrl={},用户根据access_token登录授权失败",
                    new Object[] { sessionId, uid, errorCode, currentAccessUrl });
                response.sendRedirect(this.noSessionRedirectUrl);
                return;
            }
            //用户根据access_token登录授权失败
            userModel = (UserModel) authResult.get("userModel");
            if (userModel == null) {
                logger.info(
                    "SessionPowerFilter sessionId={},uid={},userModel为空,currentAccessUrl={},用户根据access_token登录授权失败",
                    new Object[] { sessionId, uid, currentAccessUrl });
                response.sendRedirect(this.noSessionRedirectUrl);
                return;
            }
            logger.info(
                "SessionPowerFilter sessionId={},uid={},currentAccessUrl={},用户根据access_token登录授权成功,用户certNo={}",
                new Object[] { sessionId, userModel.getUid(), currentAccessUrl });
            //授权成功生产会话数据
            session.setAttribute("loginUser", userModel);
            /**
            //保存用户信息
            try {
                boolean isExist = userQueryIntegration.userIsExist(userModel.getUid());
                if (!isExist)
                    userQueryIntegration.createVehicleOwnerInfo(userModel);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                //              e.printStackTrace();
                logger.error("用户新增操作异常", e);
            }
            **/
            if (com.alipay.vbizplatform.common.util.StringUtils.isNotEmpty(sourceUrl)) {
                //登录及授权成功，将用户重定向到来源页
                response.sendRedirect(sourceUrl);
            } else {
                //登录及授权成功，将用户重定向到主页
                response.sendRedirect(this.indexRedirectUrl);
            }
            return;
        } else {
            request.setAttribute("appId", this.appId);
            request.setAttribute("GLOBLE_SESSIONID", sessionId);
            filterChain.doFilter(request, response);
            return;
        }
    }

    public void init(FilterConfig arg0) throws ServletException {
    }

    public List<String> getNoLoginAccessUrlList() {
        return noLoginAccessUrlList;
    }

    public void setNoLoginAccessUrlList(List<String> noLoginAccessUrlList) {
        this.noLoginAccessUrlList = noLoginAccessUrlList;
    }

    private boolean isNoLoginAccessUrlList(String currentAccessUrl) {
        if (this.noLoginAccessUrlList == null || currentAccessUrl == null)
            return false;
        for (int i = 0; i < this.noLoginAccessUrlList.size(); i++) {
            if (currentAccessUrl.equals(this.noLoginAccessUrlList.get(i).toString())) {
                return true;
            }
        }
        return false;
    }

    public String getNoSessionRedirectUrl() {
        return noSessionRedirectUrl;
    }

    public void setNoSessionRedirectUrl(String noSessionRedirectUrl) {
        this.noSessionRedirectUrl = noSessionRedirectUrl;
    }

    public String getIndexRedirectUrl() {
        return indexRedirectUrl;
    }

    public void setIndexRedirectUrl(String indexRedirectUrl) {
        this.indexRedirectUrl = indexRedirectUrl;
    }

    public UserauthIntegration getUserauthIntegration() {
        return userauthIntegration;
    }

    public void setUserauthIntegration(UserauthIntegration userauthIntegration) {
        this.userauthIntegration = userauthIntegration;
    }

    public UserQueryIntegration getUserQueryIntegration() {
        return userQueryIntegration;
    }

    public void setUserQueryIntegration(UserQueryIntegration userQueryIntegration) {
        this.userQueryIntegration = userQueryIntegration;
    }

    public String getAuthPathUrl() {
        return authPathUrl;
    }

    public void setAuthPathUrl(String authPathUrl) {
        this.authPathUrl = authPathUrl;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

}
