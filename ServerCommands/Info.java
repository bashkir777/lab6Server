package ServerCommands;

import CommandsManagement.CommandsEnum;
import TicketStuff.Ticket;


import java.util.PriorityQueue;

/**
 * Класс, описывающий команду выводящую в стандартный поток вывода информацию о коллекции.
 * @author Supletsov Dmitriy "supletsovd@gmail.com"
 */
public class Info implements Command {
    public static final CommandsEnum type = CommandsEnum.INFO;

    private PriorityQueue<Ticket> queue;
    public Info(PriorityQueue<Ticket> queue){
        this.queue = queue;
    }

    /**
     * Выводит в стандартный поток вывода информацию о коллекции.
     */
    @Override
    public String execute(){
        StringBuilder sb = new StringBuilder();
        sb.append("Тип коллекции: PriorityQueue<Ticket>").append("\n");
        sb.append("В коллекции содержится ").append(queue.size()).append(" элемент(ов)");
        return sb.toString();
    }
}
