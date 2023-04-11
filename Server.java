

import CollectionInteraction.FileInteraction;
import Exceptions.WrongTicketData;
import ServerModules.AnswerModule;
import ServerModules.ReadModule;
import ServerModules.CommandProcessing;
import TicketStuff.Ticket;

import java.io.File;
import java.io.IOException;
import java.net.*;
import java.util.PriorityQueue;

public class Server {
    public static void main(String[] args) throws IOException, WrongTicketData {
        String pathToFile = null;

        try{
            pathToFile = args[0];
        }catch (IndexOutOfBoundsException e){
            System.out.println("При запуске серверу необходимо передать путь до файла с коллекцией");
            System.exit(0);
        }
        try{
            if (!pathToFile.split("\\.")[1].equals("csv")){
                System.out.println("Сервер работает только с файлами .csv");
                System.exit(0);
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println("Сервер работает только с файлами .csv");
            System.exit(0);
        }

        File file = new File(pathToFile);
        if (!file.exists()){
            System.out.println("Файла с таким названием не существует");
            System.exit(0);
        }
        FileInteraction fileInteraction = new FileInteraction(pathToFile);
        try{
            fileInteraction.read();
        }catch (Exception e){
            System.out.println("В коллекции содержаться нечитаемые данные");
            System.exit(0);
        }

        byte[] arr = new byte[1024];
        DatagramSocket ds = null;
        try{
            ds = new DatagramSocket(6879);
        }catch (SocketException e){
            System.out.println("Порт " + 6879 +" занят");
            System.exit(0);
        }
        while (true){
            PriorityQueue<Ticket> queue = fileInteraction.read();
            DatagramPacket dp = ReadModule.read(arr, ds);
            byte[] buf = dp.getData();
            String commandResult;
            try{
                commandResult = CommandProcessing.startProcessing(queue, buf);
            }catch (java.lang.NoClassDefFoundError|StackOverflowError e){
                commandResult = "в скрипте присутствует бесконечная рекурсия";
            }
            AnswerModule.answer(commandResult, dp, ds);
            fileInteraction.write(queue);
        }
    }
}
