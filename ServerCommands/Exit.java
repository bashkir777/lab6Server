package ServerCommands;

import CollectionInteraction.FileInteraction;
import TicketStuff.Ticket;

import java.util.PriorityQueue;

public class Exit implements Command{
    private FileInteraction fileInteraction;
    private PriorityQueue<Ticket> queue;
    public Exit(FileInteraction fileInteraction, PriorityQueue<Ticket> queue){
        this.fileInteraction = fileInteraction;
        this.queue = queue;
    }

    @Override
    public String execute() {
        fileInteraction.write(queue);
        System.out.println("Изменения сохранены");
        return "";
    }
}
