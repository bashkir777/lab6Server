package ServerCommands;

import CommandsManagement.CommandsEnum;
import TicketStuff.Ticket;

import java.util.PriorityQueue;

/**
 * Класс, описывающий команду, которая добавляет элемент в коллекцию, если он больше максимального элемента в коллекции
 * @author Supletsov Dmitriy "supletsovd@gmail.com"
 */
public class AddIfMax implements Command {
    public static final CommandsEnum type = CommandsEnum.ADD_IF_MAX;
    private Ticket ticket;
    private PriorityQueue<Ticket> queue;
    private PriorityQueue<Ticket> queueCopy;
    public AddIfMax(Ticket ticket, PriorityQueue<Ticket> queue){
        this.ticket = ticket;
        this.queue = queue;
        this.queueCopy = new PriorityQueue<Ticket> (queue);
    }

    /**
     * Добавляет элемент в коллекцию, если он больше максимального элемента в коллекции
     */
    @Override
    public String execute() {
        float max = -1;
        for (Ticket ticket1 : queueCopy) {
            if (ticket1.getPrice() > max){
                max = ticket1.getPrice();
            }
        }
        if(ticket.getPrice() > max){
            ticket.setId(Ticket.idCounter);
            Ticket.idCounter++;
            queue.add(ticket);
            return  "Объект добавлен";
        }else{
            return "Объект не был добавлен";
        }
    }
}
