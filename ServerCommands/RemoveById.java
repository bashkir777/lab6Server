package ServerCommands;


import CommandsManagement.CommandsEnum;
import TicketStuff.Ticket;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
/**
 * Класс, описывающий команду удаляющую элементы из коллекции по полю id.
 * @author Supletsov Dmitriy "supletsovd@gmail.com"
 */
public class RemoveById implements Command {
    public static final CommandsEnum type = CommandsEnum.REMOVE_BY_ID;
    private PriorityQueue<Ticket> queue;
    private PriorityQueue<Ticket> queueCopy;
    private long id;
    public RemoveById(PriorityQueue<Ticket> queue, long id){
        this.queue = queue;
        this.queueCopy = new PriorityQueue<>(queue);
        this.id = id;
    }

    /**
     * Удаляет из коллекции элемент с указанным id
     */
    @Override
    public String execute(){
        List<Ticket> tempList = new ArrayList<>();
        boolean DeleteSuccess = false;
        for (Ticket ticket: queueCopy){
            if (ticket.getId() == this.id){
                DeleteSuccess = true;
                continue;
            }
            tempList.add(ticket);
        }

        this.queue.clear();
        this.queue.addAll(tempList);
        if (DeleteSuccess){
            return "Удаление прошло успешно";
        }else {
            return "Элемент с таким id не найден";
        }
    }
}
