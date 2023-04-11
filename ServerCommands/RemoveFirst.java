package ServerCommands;

import CommandsManagement.CommandsEnum;
import TicketStuff.Ticket;
import java.util.PriorityQueue;
/**
 * Класс, описывающий команду удаляющую первый элемент коллекции.
 * @author Supletsov Dmitriy "supletsovd@gmail.com"
 */
public class RemoveFirst implements Command {
    public static final CommandsEnum type = CommandsEnum.REMOVE_FIRST;
    private PriorityQueue<Ticket> queue;
    public RemoveFirst(PriorityQueue<Ticket> queue){
        this.queue = queue;
    }

    /**
     * Удаляет первый элемент коллекции
     */
    @Override
    public String execute(){
        queue.poll();
        return "Объект удален";
    }
}
