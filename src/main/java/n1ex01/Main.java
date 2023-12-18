package n1ex01;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner entry = new Scanner(System.in);

    public static void main(String[] args) {
        Undo undo = Undo.INSTANCE;
        boolean exit = false;
        do {
            switch (menu()) {
                case 1 -> undo.addCommand(entry);
                case 2 -> undo.deleteCommand(entry);
                case 3 -> undo.listLastCommands(entry);
                case 0 -> {
                    exit = true;
                    System.out.println("Bye!");
                }
                default -> System.err.println("Please introduce a valid value.\n");
            }
        } while (!exit);
    }
    private static int menu() {
       return readInt("Command option menu:" +
               "\n1. Add command" +
               "\n2. Delete command" +
               "\n3. List last commands" +
               "\n0. Exit");
    }

    private static int readInt(String message){
        while (true){
            System.out.println(message);
            int i;
            try {
                i = entry.nextInt();
                entry.nextLine();
                return i;
            }
            catch(InputMismatchException ex) {
                System.out.println("Wrong format input. Please introduce a valid value.\n");
                entry.nextLine();
            }
        }
    }
}
