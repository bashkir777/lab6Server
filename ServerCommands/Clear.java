package ServerCommands;

import CollectionInteraction.CollectionInteraction;

import CommandsManagement.CommandsEnum;
import TicketStuff.Ticket;
import java.util.PriorityQueue;

/**
 * Класс, описывающий команду, которая удаляет все элементы из коллекции
 * @author Supletsov Dmitriy "supletsovd@gmail.com"
 */
public class Clear implements Command {
    public static final CommandsEnum type = CommandsEnum.CLEAR;
    private PriorityQueue<Ticket> queue;
    public Clear(PriorityQueue<Ticket> queue){
        this.queue = queue;
    }

    /**
     * Удаляет все элементы из коллекции коллекцию
     */
    @Override
    public String execute(){
        queue.clear();
        return "Все элементы удалены из коллекции";
    }
}
