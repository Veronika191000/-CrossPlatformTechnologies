import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SmartHome smartHome = new SmartHome();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, Input HELP to get more information about available command");
        for (; ; ) {
            String cmd = scanner.nextLine();
            smartHome.performCommand(cmd);
        }
    }
}
