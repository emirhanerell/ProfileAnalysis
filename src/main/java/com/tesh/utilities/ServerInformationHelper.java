package com.tesh.utilities;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.io.File;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ServerInformationHelper {

    public static String getServername() {
        var servername = "";
        try {
            servername = InetAddress.getLocalHost().getHostName();
            System.out.println("Bilgisayar Adı: " + servername);
        } catch (UnknownHostException e) {
            System.out.println("Bilgisayar adı alınamadı: " + e.getMessage());
        }
        return servername;
    }

    public static double getCpuUsage() {
        var cpuUsage = 0.0;
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        cpuUsage = osBean.getSystemCpuLoad() * 100;
        return cpuUsage;
    }

    public static String getIpAddress() {
        var ipAddress = "";
        try {
            InetAddress ip = InetAddress.getLocalHost();
            ipAddress = ip.getHostAddress();
            System.out.println("Bilgisayar Adı: " + ip.getHostName());
            System.out.println("Yerel IP Adresi: " + ip.getHostAddress());
        } catch (UnknownHostException e) {
            System.out.println("IP adresi alınamadı: " + e.getMessage());
        }
        return ipAddress;
    }

    public static double getMemoryUsage() {
        // MXBean nesnesini al
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        // Toplam RAM ve kullanılabilir RAM'i al
        long totalMemory = osBean.getTotalPhysicalMemorySize();
        long freeMemory = osBean.getFreePhysicalMemorySize();

        // Kullanılan RAM hesaplama
        long usedMemory = totalMemory - freeMemory;
        double ramUsagePercent = ((double) usedMemory / totalMemory) * 100;

        return ramUsagePercent;
    }

    public static double getDiskUsage() {
        File disk = new File("C:/"); // Windows için C: sürücüsünü kontrol et
        // File disk = new File("/"); // Linux/macOS için ana dizini kontrol et

        long totalSpace = disk.getTotalSpace(); // Toplam alan (byte)
        long freeSpace = disk.getFreeSpace();   // Boş alan (byte)
        long usedSpace = totalSpace - freeSpace; // Kullanılan alan (byte)

        double usagePercent = ((double) usedSpace / totalSpace) * 100;
        return usagePercent;
    }

    public static double getNetworkUsage() throws InterruptedException {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        List<NetworkIF> networkIFs = hardware.getNetworkIFs();
        List<Double> networkUsage = new ArrayList<Double>();

        for (NetworkIF net : networkIFs) {

            net.updateAttributes();
            long bytesSent1 = net.getBytesSent();
            long bytesRecv1 = net.getBytesRecv();

            Thread.sleep(1000);

            net.updateAttributes();
            long bytesSent2 = net.getBytesSent();
            long bytesRecv2 = net.getBytesRecv();

            long sentPerSec = bytesSent2 - bytesSent1;
            long recvPerSec = bytesRecv2 - bytesRecv1;

            System.out.println("Arayüz Adı: " + net.getName());
            System.out.println("  Yükleme (Upload): " + net.getBytesSent());
            System.out.println("  İndirme (Download): " + net.getBytesRecv());
            System.out.println("--------------------------------");

            networkUsage.add(recvPerSec / 1024.0);
        }

        return Collections.max(networkUsage);
    }
}
