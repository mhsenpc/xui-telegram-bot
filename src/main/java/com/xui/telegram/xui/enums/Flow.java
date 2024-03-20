package com.xui.telegram.xui.enums;

public enum Flow {
    NONE(""),
    XTLS_RPRX_VISION("xtls-rprx-vision"),
    XTLS_RPRX_VISION_UDP443("xtls-rprx-vision-udp443");

    private final String value;

    Flow(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
