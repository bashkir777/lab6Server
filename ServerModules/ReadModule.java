package ServerModules;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class ReadModule {

    public static DatagramPacket read(byte[] arr, DatagramSocket ds){
        DatagramPacket dp = new DatagramPacket(arr, arr.length);
        try{
            ds.receive(dp);
        }catch (IOException e){
            e.printStackTrace();
        }
        return dp;
    }

}
