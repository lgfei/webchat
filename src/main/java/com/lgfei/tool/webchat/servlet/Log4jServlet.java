package com.lgfei.tool.webchat.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

/**
 * 加载log4j
 * <功能详细描述>
 * 
 * @author  Lgfei
 * @version  [版本号, 2018年5月3日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@WebServlet("/Log4jServlet")
public class Log4jServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Default constructor. 
     */
    public Log4jServlet()
    {
        super();
    }
    
    /**
     * 初始化
     */
    @Override
    public void init(ServletConfig config)
        throws ServletException
    {
        String log4jLocation = config.getInitParameter("log4j-properties-location");
        
        ServletContext sc = config.getServletContext();
        
        if (log4jLocation == null)
        {
            BasicConfigurator.configure();
        }
        else
        {
            String webAppPath = sc.getRealPath("/");
            String log4jProp = webAppPath + log4jLocation;
            File propFile = new File(log4jProp);
            if (propFile.exists())
            {
                PropertyConfigurator.configure(log4jProp);
            }
            else
            {
                BasicConfigurator.configure();
            }
        }
        super.init(config);
    }
    
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }
    
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
    
}
