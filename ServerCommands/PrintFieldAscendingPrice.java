package ServerCommands;


import CommandsManagement.CommandsEnum;
import TicketStuff.Ticket;
import java.util.PriorityQueue;
import java.util.stream.Stream;

/**
 * Класс, описывающий команду выводящую в стандартный поток вывода в порядке возрастания значения полей price всех элементов.
 * @author Supletsov Dmitriy "supletsovd@gmail.com"
 */
public class PrintFieldAscendingPrice implements Command {
    public static final CommandsEnum type = CommandsEnum.PRINT_FIELD_ASCENDING_PRICE;
    private PriorityQueue<Ticket> queue;
    public PrintFieldAscendingPrice(PriorityQueue<Ticket> queue){
        this.queue = new PriorityQueue<Ticket>(queue);
    }

    /**
     * Выводит в стандартный поток вывода в порядке возрастания значения полей price всех элементов.
     */
    @Override
    public String execute(){
        StringBuilder sb = new StringBuilder();
        queue.stream().sorted().forEach(ticket -> sb.append(ticket).append("\n"));
        return sb.toString();
    }
}
