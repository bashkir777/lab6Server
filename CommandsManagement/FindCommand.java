package CommandsManagement;

import CollectionInteraction.FileInteraction;
import Exceptions.NoSuchCommand;
import Exceptions.WrongScript;
import Exceptions.WrongTicketData;
import ServerCommands.*;
import TicketStuff.Coordinates;
import TicketStuff.Event;
import TicketStuff.Ticket;
import TicketStuff.TicketType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FindCommand {

    public static Command makeServerCommand(Commands cmd, PriorityQueue<Ticket> queue, FileInteraction fileInteraction){
        return switch (cmd.getType()) {
            case ADD -> new Add((Ticket) cmd.getAttr()[0], queue);
            case ADD_IF_MAX -> new AddIfMax((Ticket) cmd.getAttr()[0], queue);
            case CLEAR -> new Clear(queue);
            case FILTER_BY_EVENT -> new FilterByEvent(queue, (Event) cmd.getAttr()[0]);
            case FILTER_CONTAINS_NAME -> new FilterContainsName(queue, (String) cmd.getAttr()[0]);
            case HELP -> new Help();
            case INFO -> new Info(queue);
            case PRINT_FIELD_ASCENDING_PRICE -> new PrintFieldAscendingPrice(queue);
            case REMOVE_BY_ID -> new RemoveById(queue, Long.parseLong((String) cmd.getAttr()[0]));
            case REMOVE_FIRST -> new RemoveFirst(queue);
            case REMOVE_HEAD -> new RemoveHead(queue);
            case EXECUTE_SCRIPT -> new ExecuteScript(queue, (String) cmd.getAttr()[0], fileInteraction);
            case UPDATE_ID -> new UpdateId(queue, Long.parseLong((String) cmd.getAttr()[0]), (Ticket)cmd.getAttr()[1]);
            case SHOW -> new Show(fileInteraction);
            case EXIT -> new Exit(fileInteraction, queue);
        };
    }

    public static ArrayList<Commands> findCommands(String line) throws WrongScript, WrongTicketData{
        Scanner scanner = new Scanner(line);
        ArrayList<Commands> arrCommands = new ArrayList<>();
        while (scanner.hasNext()){
            String[] commandArr = scanner.nextLine().split(" ");
            System.out.println(commandArr[0]);
            Commands command = null;
            for(CommandsEnum cmd: CommandsEnum.values()){
                if (cmd.getName().equals(commandArr[0])){
                    switch (cmd){
                        case UPDATE_ID:
                            Object[] args = new Object[]{commandArr[1], FindCommand.readTicket(scanner)};
                            command = new Commands(cmd, args);
                            break;
                        case ADD, ADD_IF_MAX:
                            args = new Object[]{FindCommand.readTicket(scanner)};
                            command = new Commands(cmd, args);
                            break;
                        case FILTER_BY_EVENT:
                            args = new Object[]{FindCommand.readEvent(scanner)};
                            command = new Commands(cmd, args);
                            break;
                        case FILTER_CONTAINS_NAME, REMOVE_BY_ID, EXECUTE_SCRIPT:
                            try{
                                args = new Object[]{commandArr[1]};
                                command = new Commands(cmd, args);
                                break;
                            }catch (IndexOutOfBoundsException e){
                                throw new WrongScript();
                            }
                        default:
                            command = new Commands(cmd);
                            break;
                    }
                }
            }
            if (command == null){
                throw new WrongScript();
            }
            arrCommands.add(command);
        }
        return arrCommands;
    }
    public static Ticket readTicket(Scanner scanner) throws WrongTicketData {
        Ticket ticket = new Ticket();
        try {
            ticket.setName(scanner.nextLine());
            String cordX = scanner.nextLine();
            int x;
            double y;
            x = Integer.parseInt(cordX);
            String cordY = scanner.nextLine();
            y = Double.parseDouble(cordY);
            ticket.setCoordinates(new Coordinates(x, y));
            float price = Float.parseFloat(scanner.nextLine());
            ticket.setPrice(price);
            int choice;
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    ticket.setType(TicketType.VIP);
                    break;
                case 2:
                    ticket.setType(TicketType.USUAL);
                    break;
                case 3:
                    ticket.setType(TicketType.BUDGETARY);
                    break;
                case 4:
                    ticket.setType(TicketType.CHEAP);
                    break;

            }
            String[] string = new String[3];
            string[0] = scanner.nextLine();
            if (!(string[0].length() >= 1)) {
                throw new WrongTicketData("Неверное название мероприятия");
            }
            int x2 = Integer.parseInt(scanner.nextLine());
            if (x2 < 1) {
                throw new WrongTicketData("Количество билетов должно быть неотрицательно");
            }
            string[1] = Integer.toString(x2);

            string[2] = scanner.nextLine();
            if (string[2].equals("-")) {
                string[2] = " ";
            }
            ticket.setEvent(new Event(string[0], Integer.parseInt(string[1]), string[2]));
            return ticket;
        } catch (Exception e) {
            throw new WrongTicketData("неправильная информация в скрипте");
        }
    }
    public static Event readEvent(Scanner scanner) throws WrongTicketData {
        Event event = new Event();
        try{
            String name = scanner.nextLine();
            if (!(name.length() >= 1)) {
                throw new WrongTicketData("Неверное название мероприятия");
            }
            event.setName(name);

            int x = Integer.parseInt(scanner.nextLine());
            if (x < 1) {
                throw new WrongTicketData("Количество билетов должно быть неотрицательно");
            }
            event.setTicketsCount(x);

            String description = scanner.nextLine();
            if (description.equals("-")) {
                description = " ";
            }
            event.setDescription(description);
            return event;
        }catch (Exception e){
            throw new WrongTicketData("неправильная информация в скрипте");
        }
    }
}
