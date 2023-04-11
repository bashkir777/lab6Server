package ServerCommands;


import CommandsManagement.CommandsEnum;

import TicketStuff.Ticket;
import java.util.PriorityQueue;
/**
 * Класс, описывающий команду, которая выводит в стандартный поток вывода все элементы коллекции.
 * @author Supletsov Dmitriy "supletsovd@gmail.com"
 */
public class Show implements Command {
    public static final CommandsEnum type = CommandsEnum.SHOW;
    private PriorityQueue<Ticket> queue;
    public Show(PriorityQueue<Ticket> queue){
        this.queue = queue;
    }

    /**
     * Выводит в стандартный поток вывода все элементы коллекции
     */
    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        queue.stream().forEach(ticket -> sb.append(ticket).append("\n"));
        try{
            return sb.substring(0, sb.length()-1);
        }catch(StringIndexOutOfBoundsException e){
            return "коллекция пустая";
        }
    }
}
