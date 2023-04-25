

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
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Server {
    public static void main(String[] args) throws WrongTicketData, IOException {
        String pathToFile = null;

        try {
            pathToFile = args[0];
        } catch (IndexOutOfBoundsException e) {
            System.out.println("При запуске серверу необходимо передать путь до файла с коллекцией");
            System.exit(0);
        }
        try {
            if (!pathToFile.split("\\.")[1].equals("csv")) {
                System.out.println("Сервер работает только с файлами .csv");
                System.exit(0);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Сервер работает только с файлами .csv");
            System.exit(0);
        }

        File file = new File(pathToFile);
        if (!file.exists()) {
            System.out.println("Файла с таким названием не существует");
            System.exit(0);
        }
        FileInteraction fileInteraction = new FileInteraction(pathToFile);
        try {
            fileInteraction.read();
        } catch (Exception e) {
            System.out.println("В коллекции содержаться нечитаемые данные");
            System.exit(0);
        }
        int port = 0;
        try{
            port = Integer.parseInt(args[1]);
        }catch (Exception e){
            System.out.println("Вторым аргументом требуется подать номер порта");
        }
        byte[] arr = new byte[20000];
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket(port);
        } catch (SocketException e) {
            System.out.println("Невозможно открыть соединение через порт " + port);
            System.exit(0);
        }
        PriorityQueue<Ticket> queue = fileInteraction.read();
        ds.setSoTimeout(300);

        while (true) {
            DatagramPacket dp;
            try {
                dp = ReadModule.read(arr, ds);
            } catch (IOException e) {
                if (System.in.available()!=0){
                    if(new Scanner(System.in).nextLine().equals("save")){
                        fileInteraction.write(queue);
                        System.out.println("Изменения сохранены");
                    }else{
                        System.out.println("такой команды не существует");
                    }
                }
                /*TimerTask task = new TimerTask(){
                    @Override
                    public void run() {
                        Scanner scanner = new Scanner(System.in);
                        if (scanner.nextLine().equals("save")) {
                            fileInteraction.write(queue);
                            System.out.println("Изменения сохранены");
                        }else{
                            System.out.println("Такой команды не существует");
                        }
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task, 300);*/
                continue;
            }
            byte[] buf = dp.getData();
            String commandResult;
            try {
                commandResult = CommandProcessing.startProcessing(queue, buf, fileInteraction);
            } catch (java.lang.NoClassDefFoundError | StackOverflowError e) {
                commandResult = "в скрипте присутствует бесконечная рекурсия";
            }
            AnswerModule.answer(commandResult, dp, ds);
        }
    }
}
