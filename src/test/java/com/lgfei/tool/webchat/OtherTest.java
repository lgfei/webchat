package com.lgfei.tool.webchat;

import java.io.File;

import org.springframework.util.StringUtils;

public class OtherTest
{
    public static void main(String[] args)
    {
        File propFile = new File("classpath:log4j.properties");
        System.out.println(propFile.exists());
        
        String str1 = null;
        String str2 = null;
        System.out.println(StringUtils.pathEquals(str1, str2));
    }
}
