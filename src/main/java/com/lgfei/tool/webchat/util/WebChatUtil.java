package com.lgfei.tool.webchat.util;

import com.lgfei.tool.webchat.exception.SystemException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * webchat相关的工具类
 * <功能详细描述>
 * 
 * @author  Lgfei
 * @version  [版本号, 2018年7月8日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public final class WebChatUtil
{
    private static Logger log = Logger.getLogger(WebChatUtil.class);
    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？ 
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。 
     *  
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 192.168.1.100 
     * 用户真实IP为： 192.168.1.110 
     * 
     * @param request
     * @return 用户真实ip
     * @see [类、类#方法、类#成员]
     */
    public static String getIp(HttpServletRequest request)
    {
        if (null == request)
        {
            return "unknown";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {

        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
            if (ip.equals("0:0:0:0:0:0:0:1")) {
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    //e.printStackTrace();
                    log.error("获取客户端ip异常:" + e.getMessage());
                    throw new RuntimeException("获取客户端ip异常");
                }
                ip = inet.getHostAddress();
            }
        }
        log.info("获取到的客户端ip=" + ip);
        return ip;
    }
}
