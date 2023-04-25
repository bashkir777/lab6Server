package ServerCommands;


import CollectionInteraction.FileInteraction;
import CommandsManagement.Commands;
import CommandsManagement.CommandsEnum;
import CommandsManagement.FindCommand;
import Exceptions.WrongScript;
import Exceptions.WrongTicketData;
import TicketStuff.Ticket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Класс, описывающий команду применяемую для исполнения сккрипта, находящегося в стороннем файле
 * @author Supletsov Dmitriy "supletsovd@gmail.com"
 */

public class ExecuteScript implements Command{
    public static final CommandsEnum type = CommandsEnum.EXECUTE_SCRIPT;
    private PriorityQueue<Ticket> queue;
    private PriorityQueue<Ticket> queueCopy;
    private String str;
    private FileInteraction fileInteraction;
    public ExecuteScript(PriorityQueue<Ticket> queue, String str, FileInteraction fileInteraction){
        this.queue = queue;
        this.queueCopy = new PriorityQueue<>(queue);
        this.str = str;
        this.fileInteraction = fileInteraction;
    }
    /**
     * Исполняет скрипт
     */
    @Override
    public String execute() throws WrongTicketData, IOException {
        ArrayList<Commands> listToExecute;
        StringBuilder sb = new StringBuilder();
        try {
            listToExecute = FindCommand.findCommands(str);
        } catch (WrongScript | WrongTicketData e) {
            return "скрипт невозможно выполнить";
        }
        for (Commands cmd : listToExecute) {
            sb.append(FindCommand.makeServerCommand(cmd, queueCopy, fileInteraction).execute()).append("\n");
        }
        queue.clear();
        queue.addAll(queueCopy);
        return sb.append("скрипт выполен").append("\n").toString();

    }
}
