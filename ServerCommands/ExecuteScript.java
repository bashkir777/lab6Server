package ServerCommands;


import CommandsManagement.Commands;
import CommandsManagement.CommandsEnum;
import CommandsManagement.FindCommand;
import Exceptions.WrongScript;
import Exceptions.WrongTicketData;
import TicketStuff.Ticket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Класс, описывающий команду применяемую для исполнения сккрипта, находящегося в стороннем файле
 * @author Supletsov Dmitriy "supletsovd@gmail.com"
 */

public class ExecuteScript implements Command{
    public static final CommandsEnum type = CommandsEnum.EXECUTE_SCRIPT;
    private PriorityQueue<Ticket> queue;
    private PriorityQueue<Ticket> queueCopy;
    private String pathToScript;
    public ExecuteScript(PriorityQueue<Ticket> queue, String pathToScript){
        this.queue = queue;
        this.pathToScript = pathToScript;
        this.queueCopy = new PriorityQueue<>(queue);
    }
    /**
     * Исполняет скрипт
     */
    @Override
    public String execute(){
        ArrayList<Commands> listToExecute;
        StringBuilder sb = new StringBuilder();
        try {
            listToExecute = FindCommand.findCommands(this.pathToScript);
        } catch (WrongScript | WrongTicketData e) {
            return "скрипт невозможно выполнить";
        } catch (IOException e) {
            return "файл со скриптом не найден";
        }
        for (Commands cmd : listToExecute) {
            sb.append(FindCommand.makeServerCommand(cmd, queueCopy).execute()).append("\n");
        }
        queue.clear();
        queue.addAll(queueCopy);
        return sb.append("скрипт выполен").append("\n").toString();

    }
}
