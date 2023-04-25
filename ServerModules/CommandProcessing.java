package ServerModules;

import CollectionInteraction.FileInteraction;
import CommandsManagement.Commands;
import CommandsManagement.FindCommand;
import Exceptions.WrongTicketData;
import ServerCommands.Command;
import TicketStuff.Ticket;

import java.io.IOException;
import java.util.PriorityQueue;

public class CommandProcessing {
    public static String startProcessing(PriorityQueue<Ticket> queue, byte[] buf, FileInteraction fileInteraction) throws WrongTicketData, IOException {
        Commands cmd = Commands.deSerializeCommand(buf);
        Command command = FindCommand.makeServerCommand(cmd, queue, fileInteraction);
        return command.execute();
    }

}
