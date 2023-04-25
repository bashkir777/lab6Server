package ServerModules;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class ReadModule {

    public static DatagramPacket read(byte[] arr, DatagramSocket ds) throws IOException {
        DatagramPacket dp = new DatagramPacket(arr, arr.length);
        ds.receive(dp);
        return dp;
    }

}
