package ServerCommands;

import CommandsManagement.CommandsEnum;


/**
 * Класс, описывающий команду выводящую в стандартный поток вывода справку по всем доступным командам.
 * @author Supletsov Dmitriy "supletsovd@gmail.com"
 */
public class Help implements Command {
    public static final CommandsEnum type = CommandsEnum.HELP;
    @Override
    /**
     * Выводит в стандартный поток вывода справку по всем доступным командам.
     */
    public String execute() {
        StringBuilder sb = new StringBuilder();
        sb.append("help : вывести справку по доступным командам").append("\n");
        sb.append("info : вывести в стандартный поток вывода информацию о коллекции").append("\n");
        sb.append("show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении").append("\n");
        sb.append("add {element} : добавить новый элемент в коллекцию").append("\n");
        sb.append("update id {element} : обновить значение элемента коллекции, id которого равен заданному").append("\n");
        sb.append("remove_by_id id : удалить элемент из коллекции по его id").append("\n");
        sb.append("clear : очистить коллекцию").append("\n");
        sb.append("save : сохранить коллекцию в файл").append("\n");
        sb.append("execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.").append("\n");
        sb.append("exit(ctrl+d) : завершить программу (без сохранения в файл)").append("\n");
        sb.append("remove_first : удалить первый элемент из коллекции").append("\n");
        sb.append("remove_head : вывести первый элемент коллекции и удалить его").append("\n");
        sb.append("add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции").append("\n");
        sb.append("filter_by_event event : вывести элементы, значение поля event которых равно заданному").append("\n");
        sb.append("filter_contains_name name : вывести элементы, значение поля name которых содержит заданную подстроку").append("\n");
        sb.append("print_field_ascending_price : вывести значения поля price всех элементов в порядке возрастания").append("\n");
        return sb.toString();
    }
}
