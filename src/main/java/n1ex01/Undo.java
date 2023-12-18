package n1ex01;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.IntStream;

public enum Undo {
    INSTANCE;
    ArrayList<String> commands;

    Undo(){
        commands = new ArrayList<>();
    }

    public Undo getInstance(){
        return INSTANCE;
    }

    public void addCommand(Scanner entry){
        System.out.println("Introduce the command you want to add: ");
        String command = entry.nextLine();
        commands.add(command);
        System.out.println("Command " + command + " successfully added.\n");
    }

    public void deleteCommand(Scanner entry){
        if (commands.isEmpty()) {
            System.out.println("Can't delete any command. Command history is empty.\n");
            return;
        }

        listAllCommands();
        System.out.println("Introduce the command you want to delete: ");
        String command = entry.nextLine();

        if (findCommand(command) != -1){
            commands.remove(command);
            System.out.println("Command " + command + " successfully deleted.\n");
        } else {
            System.out.println(command + " is not in command history\n");
        }
    }

    public int findCommand(String command){
        if (!commands.isEmpty()){
            for (int i = 0; i < commands.size(); i++){
                if (commands.get(i).equalsIgnoreCase(command)){
                    return i;
                }
            }
        }
        return -1;
    }

    public void listAllCommands(){
        if (!commands.isEmpty()){
            System.out.println("Commands list: ");
            IntStream.range(0, commands.size()).forEach(i -> System.out.println("Command " + (i + 1) + ": " + commands.get(i)));
        }
    }

    public void listLastCommands(Scanner entry) throws InputMismatchException {
        if (commands.isEmpty()) {
            System.out.println("Can't list commands. Command history is empty.\n");
            return;
        }

        int quantCommands = 0;
        do {
            System.out.println("How many last commands do you want to see?");

            quantCommands = entry.nextInt();
            if (quantCommands > commands.size()){
                System.out.println("You cannot retrieve " + quantCommands + " commands. There are " + commands.size() + " commands.\n");
            } else if (quantCommands < 0){
                System.out.println("Please introduce a valid value.\n");
            }

        } while (quantCommands == -1 | quantCommands > commands.size());

        int startInd = commands.size() - quantCommands;

        IntStream.range(startInd, commands.size()).forEach(i -> System.out.println("Command " + (i + 1) + ": " + commands.get(i)));
    }
}
