package ServerCommands;

import CommandsManagement.CommandsEnum;

import TicketStuff.Event;
import TicketStuff.Ticket;

import java.util.PriorityQueue;

/**
 * Класс, описывающий команду выводящую в стандартный поток вывода элемент, значения поля event, которого совпадает с введенным
 * @author Supletsov Dmitriy "supletsovd@gmail.com"
 */
public class FilterByEvent implements Command {
    public static final CommandsEnum type = CommandsEnum.FILTER_BY_EVENT;
    private PriorityQueue<Ticket> queue;
    private Event event;
    public FilterByEvent(PriorityQueue<Ticket> queue, Event event){
        this.queue = new PriorityQueue<>(queue);
        this.event = event;
    }

    /**
     * выводит в стандартный поток вывода элемент, значения поля event, которого совпадает с введенным.
     */
    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        queue.stream().filter(ticket -> ticket.getEvent().getName().equals(event.getName())&&ticket.getEvent().getTicketsCount()==event.getTicketsCount()).forEach(ticket -> sb.append(ticket).append("\n"));
        if(sb.length() == 0){
            return "В коллекции не содержаться билеты, удовлетворяющие фильтру";
        }
        return sb.toString();
    }
}
