package ServerCommands;


import CollectionInteraction.FileInteraction;
import CommandsManagement.CommandsEnum;

import Exceptions.WrongTicketData;
import TicketStuff.Ticket;

import java.io.IOException;
import java.util.PriorityQueue;
/**
 * Класс, описывающий команду, которая выводит в стандартный поток вывода все элементы коллекции.
 * @author Supletsov Dmitriy "supletsovd@gmail.com"
 */
public class Show implements Command {
    public static final CommandsEnum type = CommandsEnum.SHOW;

    private FileInteraction fileInteraction;
    public Show(FileInteraction fileInteraction){
        this.fileInteraction = fileInteraction;
    }

    /**
     * Выводит в стандартный поток вывода все элементы коллекции
     */
    @Override
    public String execute() throws WrongTicketData, IOException {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Ticket> queue = fileInteraction.read();
        queue.stream().forEach(ticket -> sb.append(ticket).append("\n"));
        try{
            return sb.substring(0, sb.length()-1);
        }catch(StringIndexOutOfBoundsException e){
            return "коллекция пустая";
        }
    }
}
