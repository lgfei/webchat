package com.lgfei.tool.webchat.constant;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component("roomConfig")
public class RoomConfig
{
    private String host;
    
    private String ports;
    
    private List<Integer> portList;
    
    public String getHost()
    {
        return host;
    }
    
    @Value("#{prop.host}")
    public void setHost(String host)
    {
        this.host = host;
    }
    
    public String getPorts()
    {
        return ports;
    }
    
    @Value("#{prop.ports}")
    public void setPorts(String ports)
    {
        this.ports = ports;
    }
    
    public List<Integer> getPortList()
    {
        if (StringUtils.isEmpty(ports))
        {
            return new ArrayList<Integer>();
        }
        portList = new ArrayList<>();
        String[] portArr = ports.split(",");
        for (String portStr : portArr)
        {
            portList.add(Integer.parseInt(portStr));
        }
        
        return portList;
    }
    
}
