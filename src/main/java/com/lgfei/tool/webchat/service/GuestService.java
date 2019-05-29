package com.lgfei.tool.webchat.service;

import com.lgfei.tool.webchat.vo.GuestVO;

public interface GuestService extends BaseService<GuestVO>
{
    void onLine(String guestId);
    
    void offLine(String guestId);
}
