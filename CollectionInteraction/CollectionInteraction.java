package CollectionInteraction;
import Exceptions.WrongTicketData;
import TicketStuff.Ticket;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.PriorityQueue;
/**
 * Интерфейс, который должен исполнять каждый класс, способный читать или сохранять коллекцию из/в какого-либо/какой-либо источник(а).
 * @author Supletsov Dmitriy "supletsovd@gmail.com"
 */
public interface CollectionInteraction {
    /**
     * Метод получающий коллекцию PriorityQueue<Ticket> из какого-либо источника
     * @throws IOException
     * @throws WrongTicketData
     */
    public PriorityQueue<Ticket> read() throws  IOException, WrongTicketData;
    /**
     * Метод записыващий коллекцию PriorityQueue<Ticket> в какой-либо источник
     * @throws IOException
     * @throws WrongTicketData
     */
    public void write(PriorityQueue<Ticket> queue) throws IOException;
    public String getSource();
}
