package CollectionInteraction;
import Exceptions.WrongTicketData;
import TicketStuff.*;
import java.io.*;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Класс, описывающий объект, позволяющий получать коллекцию из файла и сохранять ее в файл.
 * @author Supletsov Dmitriy "supletsovd@gmail.com"
 */
public class FileInteraction implements CollectionInteraction{
    public final String sourceName;
    public FileInteraction(String sourceName){
        this.sourceName = sourceName;
    }
    /**
     * Метод получающий коллекцию PriorityQueue<Ticket> из файла источника
     * @throws IOException
     * @throws WrongTicketData
     */
    public PriorityQueue<Ticket> read() throws IOException, WrongTicketData{
        PriorityQueue<Ticket> queue = new PriorityQueue<Ticket>();
        FileInputStream fis = new FileInputStream(this.sourceName);
        Scanner scanner = new Scanner(fis);

        while (true){
            if (new File(this.sourceName).length()<3){
                break;
            }
            try{
                Ticket ticket = new Ticket();
                String idLine= scanner.nextLine();
                if (!idLine.split(";")[0].equals("ID")){
                    throw new IndexOutOfBoundsException();
                }
                ticket.setId(Long.parseLong(idLine.split(";")[1]));
                String nameLine = scanner.nextLine();
                if (!nameLine.split(";")[0].equals("Name")){
                    throw new IndexOutOfBoundsException();
                }
                ticket.setName(nameLine.split(";")[1]);
                String xLine = scanner.nextLine();
                if (!xLine.split(";")[0].equals("X")){

                    throw new IndexOutOfBoundsException();
                }
                int x = Integer.parseInt(xLine.split(";")[1]);
                String yLine = scanner.nextLine();
                if (!yLine.split(";")[0].equals("Y")){
                    throw new IndexOutOfBoundsException();
                }
                Double y = Double.valueOf(yLine.split(";")[1]);
                ticket.setCoordinates(new Coordinates(x, y));
                String creationLine= scanner.nextLine();
                if (!creationLine.split(";")[0].equals("CreationDate")){
                    throw new IndexOutOfBoundsException();
                }
                try{
                    ticket.setCreationDate(java.time.ZonedDateTime.parse(creationLine.split(";")[1]));
                }catch (Exception e){
                    throw new IndexOutOfBoundsException();
                }
                String priceLine = scanner.nextLine();
                if (!priceLine.split(";")[0].equals("Price")){
                    throw new IndexOutOfBoundsException();
                }

                ticket.setPrice(Float.valueOf(priceLine.split(";")[1]));
                String typeLine = scanner.nextLine();
                if (!typeLine.split(";")[0].equals("Type")){
                    throw new IndexOutOfBoundsException();
                }
                ticket.setType(Enum.valueOf(TicketType.class, typeLine.split(";")[1]));
                String idEventLine = scanner.nextLine();
                if (!idEventLine.split(";")[0].equals("Event ID")){
                    throw new IndexOutOfBoundsException();
                }
                long idEvent = Long.parseLong(idEventLine.split(";")[1]);
                String nameEventLine = scanner.nextLine();
                if (!nameEventLine.split(";")[0].equals("Event Name")){
                    throw new IndexOutOfBoundsException();
                }
                String name = nameEventLine.split(";")[1];

                String ticketCountLine = scanner.nextLine();
                if (!ticketCountLine.split(";")[0].equals("Event TicketsCount")){
                    throw new IndexOutOfBoundsException();
                }
                long ticketCount;
                try{
                    ticketCount = Long.parseLong(ticketCountLine.split(";")[1]);
                }catch (Exception e){
                    throw new IndexOutOfBoundsException();
                }
                String descriptionLine=scanner.nextLine();
                String description="";
                if (!descriptionLine.split(";")[0].equals("Event Description")){
                    throw new IndexOutOfBoundsException();
                }
                try{
                    description = descriptionLine.split(";")[1];
                }catch (Exception e){
                    assert true;
                }
                Event tempEvent = new Event(name, ticketCount, description);
                tempEvent.setId(idEvent);
                ticket.setEvent(tempEvent);
                queue.add(ticket);
            }catch (NoSuchElementException e){
                break;
            }catch(IndexOutOfBoundsException e){
                System.out.println("В коллекции " + this.sourceName + " содержаться нечитаемые данные");
                System.exit(1);
            }
        }
        return queue;
    }
    /**
     * Метод записыващий коллекцию PriorityQueue<Ticket> в файл
     * @throws IOException
     * @throws WrongTicketData
     */
    public void write(PriorityQueue<Ticket> queue) {
        PriorityQueue<Ticket> queueClone = new PriorityQueue<Ticket> ();
        queueClone.addAll(queue);

        try {
            FileOutputStream fos = new FileOutputStream(this.sourceName);
            int x = queue.size();
            for(int i=0; i < x; i++){
                Ticket ticket = queueClone.poll();
                assert ticket != null;
                fos.write(("ID;" + ticket.getId() +"\n").getBytes());
                fos.write(("Name;"+ticket.getName()+"\n").getBytes());
                fos.write(("X;"+ticket.getCoordinates().getX()+"\n").getBytes());
                fos.write(("Y;"+ticket.getCoordinates().getY()+"\n").getBytes());
                fos.write(("CreationDate;"+ ticket.getCreationDate() + "\n").getBytes());
                fos.write(("Price;" + ticket.getPrice() + "\n").getBytes());
                fos.write(("Type;" + ticket.getType().name() + "\n").getBytes());
                fos.write(("Event ID;"+ticket.getEvent().getId()+ "\n").getBytes());
                fos.write(("Event Name;"+ticket.getEvent().getName()+ "\n").getBytes());
                fos.write(("Event TicketsCount;"+ticket.getEvent().getTicketsCount()+ "\n").getBytes());
                fos.write(("Event Description;"+ticket.getEvent().getDescription()+ "\n").getBytes());
            }
        }catch (IOException e){
            System.out.println("Невозможно записать очередь в файл");
        }
    }

    /**
     * Метод фозвращающий путь до файла-источника
     */
    public String getSource() {
        return this.sourceName;
    }
}
