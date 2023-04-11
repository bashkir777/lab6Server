package ServerCommands;



import CommandsManagement.CommandsEnum;
import TicketStuff.Ticket;
import java.util.PriorityQueue;
/**
 * Класс, описывающий команду, которая удаляет первый элемент коллекции и выводит его в стандартный поток вывода.
 * @author Supletsov Dmitriy "supletsovd@gmail.com"
 */
public class RemoveHead implements Command {
    public static final CommandsEnum type = CommandsEnum.REMOVE_HEAD;
    private PriorityQueue<Ticket> queue;
    public RemoveHead(PriorityQueue<Ticket> queue){
        this.queue = queue;
    }

    /**
     * Удаляет первый элемент коллекции и выводит его в стандартный поток вывода.
     */
    @Override
    public String execute(){
        StringBuilder sb = new StringBuilder();
        sb.append("Удаляемый объект: ").append(queue.poll()).append("\n").append("Объект удален");
        return sb.toString();
    }
}