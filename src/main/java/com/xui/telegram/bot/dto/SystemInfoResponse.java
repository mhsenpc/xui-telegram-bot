package com.xui.telegram.bot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SystemInfoResponse {

    private boolean success;
    private String msg;
    private SystemInfo obj;

    // Getters and Setters

    public boolean isSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }

    public SystemInfo getObj() {
        return obj;
    }

    static class SystemInfo {
        private double cpu;
        @JsonProperty("cpuCores")
        private int cpuCores;
        @JsonProperty("cpuSpeedMhz")
        private double cpuSpeedMhz;
        private MemoryInfo mem;
        private MemoryInfo swap;
        private DiskInfo disk;
        private XRayInfo xray;
        private long uptime;
        private double[] loads;
        @JsonProperty("tcpCount")
        private int tcpCount;
        @JsonProperty("udpCount")
        private int udpCount;
        private NetIO netIO;
        private NetTraffic netTraffic;
        private PublicIP publicIP;
        private AppStats appStats;

        public double getCpu() {
            return cpu;
        }

        public int getCpuCores() {
            return cpuCores;
        }

        public double getCpuSpeedMhz() {
            return cpuSpeedMhz;
        }

        public MemoryInfo getMem() {
            return mem;
        }

        public MemoryInfo getSwap() {
            return swap;
        }

        public DiskInfo getDisk() {
            return disk;
        }

        public XRayInfo getXray() {
            return xray;
        }

        public long getUptime() {
            return uptime;
        }

        public double[] getLoads() {
            return loads;
        }

        public int getTcpCount() {
            return tcpCount;
        }

        public int getUdpCount() {
            return udpCount;
        }

        public NetIO getNetIO() {
            return netIO;
        }

        public NetTraffic getNetTraffic() {
            return netTraffic;
        }

        public PublicIP getPublicIP() {
            return publicIP;
        }

        public AppStats getAppStats() {
            return appStats;
        }
    }

    static class MemoryInfo {
        private long current;
        private long total;

        public long getCurrent() {
            return current;
        }

        public long getTotal() {
            return total;
        }
    }

    static class DiskInfo {
        private long current;
        private long total;

        public long getCurrent() {
            return current;
        }

        public long getTotal() {
            return total;
        }
    }

    static class XRayInfo {
        private String state;
        private String errorMsg;
        private String version;

        public String getState() {
            return state;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public String getVersion() {
            return version;
        }
    }

    static class NetIO {
        private long up;
        private long down;

        public long getUp() {
            return up;
        }

        public long getDown() {
            return down;
        }
    }

    static class NetTraffic {
        private long sent;
        private long recv;

        public long getSent() {
            return sent;
        }

        public long getRecv() {
            return recv;
        }
    }

    static class PublicIP {
        private String ipv4;
        private String ipv6;

        public String getIpv4() {
            return ipv4;
        }

        public String getIpv6() {
            return ipv6;
        }
    }

    static class AppStats {
        private int threads;
        private long mem;
        private long uptime;

        public int getThreads() {
            return threads;
        }

        public long getMem() {
            return mem;
        }

        public long getUptime() {
            return uptime;
        }
    }
}
