package ServerCommands;

import Exceptions.WrongCommandSyntax;
import TicketStuff.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Класс, описывающий команду, которая обновляет элемент коллекции по полю id.
 * @author Supletsov Dmitriy "supletsovd@gmail.com"
 */
public class UpdateId implements Command{
    public static final CommandsManagement.CommandsEnum type = CommandsManagement.CommandsEnum.UPDATE_ID;
    private PriorityQueue<Ticket> queue;
    private Ticket ticket;
    private long id;
    public UpdateId(PriorityQueue<Ticket> queue, long id, Ticket ticket){
        this.id = id;
        this.queue = queue;
        this.ticket = ticket;
    }

    /**
     * Обновляет элемент коллекции по полю id.
     */
    @Override
    public String execute(){
        List<Ticket> tempList = new ArrayList<Ticket>();
        boolean flag = false;
        for (Ticket t: this.queue){
            if (t.getId() == this.id){
                ticket.setId(t.getId());
                tempList.add(ticket);
                flag = true;
                continue;
            }
            tempList.add(t);
        }
        if (flag){
            this.queue.clear();
            this.queue.addAll(tempList);
            return "Элемент успешно изменен";
        }
        return "Элемента с таким id не существует";
    }
}
