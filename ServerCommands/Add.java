package ServerCommands;

import CommandsManagement.CommandsEnum;
import TicketStuff.*;

import java.util.PriorityQueue;

/**
 * Класс, описывающий команду применяемую для добавления элемента в коллекцию
 * @author Supletsov Dmitriy "supletsovd@gmail.com"
 */
public class Add implements Command {
    public static final CommandsEnum type = CommandsEnum.ADD;
    private Ticket ticket;
    private PriorityQueue<Ticket> queue;
    public Add(Ticket ticket, PriorityQueue<Ticket> queue){
        this.ticket = ticket;
        this.queue = queue;
    }

    /**
     * Добавляет элемент в коллекцию
     */
    @Override
    public String execute() {
        ticket.setId(Ticket.idCounter);
        Ticket.idCounter++;
        queue.add(ticket);
        return "Объект добавлен";
    }
}
