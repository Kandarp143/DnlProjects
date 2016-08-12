/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 02948
 */
public class IpAddress {

    public static void main(String[] args) throws Exception {
        IpAddress ip = new IpAddress();
        ip.isProdEnv();
        //        // logger.log(Level.INFO, "Connected !");
//        InetAddress IP = InetAddress.getLocalHost();
//        System.out.println("IP of my system is := " + IP.getHostAddress());
    }

    public boolean isProdEnv() {
        Boolean ans = false;
        String host = "";
        try {
            //System.out.println("Your Host addr: " + InetAddress.getLocalHost().getHostAddress());
            InetAddress.getLocalHost().getHostAddress();// often returns "127.0.0.1"
            Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
            for (; n.hasMoreElements();) {
                NetworkInterface e = n.nextElement();
                Enumeration<InetAddress> a = e.getInetAddresses();
                for (; a.hasMoreElements();) {
                    InetAddress addr = a.nextElement();
//                System.out.println("  " + addr.getHostAddress());
                    host = addr.getHostAddress();
                }
            }
//            System.out.println("Host" + host);
            if (host.startsWith("200.200.")) {
                ans = true;
            }

        } catch (UnknownHostException ex) {
            Logger.getLogger(IpAddress.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(IpAddress.class.getName()).log(Level.SEVERE, null, ex);
        }
//        System.out.println("isProductionEnvironent :" + ans);
        return ans;
    }

}
