package com.mhsenpc.v2raybot.xui.dto;

public class TcpSettings {
    private boolean acceptProxyProtocol;
    private Header header;

    public boolean isAcceptProxyProtocol() {
        return acceptProxyProtocol;
    }

    public Header getHeader() {
        return header;
    }
}