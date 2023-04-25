package ServerCommands;

import Exceptions.ScriptNotFound;
import Exceptions.WrongTicketData;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Интерфейс, который должна исполнять каждая команда
 * @author Supletsov Dmitriy "supletsovd@gmail.com"
 */
public interface Command {
    /**
     * Метод запускающий выполнение команды
     */
    public String execute() throws WrongTicketData, IOException;
}
