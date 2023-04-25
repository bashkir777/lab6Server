package ServerCommands;


import CommandsManagement.CommandsEnum;
import TicketStuff.Ticket;
import java.util.PriorityQueue;
/**
 * Класс, описывающий команду выводящую в стандартный поток вывода элемент, значения поля name, которого содержит введенное значение.
 * @author Supletsov Dmitriy "supletsovd@gmail.com"
 */
public class FilterContainsName implements Command {
    public static final CommandsEnum type = CommandsEnum.FILTER_CONTAINS_NAME;
    private PriorityQueue<Ticket> queue;
    private String name;

    public FilterContainsName(PriorityQueue<Ticket> queue, String name) {
        this.queue = new PriorityQueue<>(queue);
        this.name = name;
    }

    /**
     * выводит в стандартный поток вывода элемент, значения поля name, которого содержит введенное значение
     */
    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        queue.stream().filter(ticket -> ticket.getName().contains(name)).forEach(ticket -> sb.append(ticket).append("\n"));
        try{
            return sb.substring(0, sb.length()-1);
        }catch (Exception e){
            return "объектов, подходящих под фильтр, не существует";
        }

    }
}