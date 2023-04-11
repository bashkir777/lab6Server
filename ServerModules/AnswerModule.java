package ServerModules;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class AnswerModule {
    public static void answer(String string, DatagramPacket dp, DatagramSocket ds){
        byte[] answer = string.getBytes();
        DatagramPacket dpAnswer = new DatagramPacket(answer, answer.length, dp.getAddress(), dp.getPort());
        try{
            ds.send(dpAnswer);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
