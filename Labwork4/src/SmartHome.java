import java.util.ArrayList;

public class SmartHome implements Logger {
    private int WasherId = 0;
    private int garlandId = 0;
    private int lampId = 0;
    private int microwaveId = 0;
    private int TVId = 0;
    private Commands consoleHelper;
    private ArrayList<Devices> components;

    {
        consoleHelper = new Commands();
        components = new ArrayList<>();
    }

    private boolean addDeviceToHome(String component) {
        Devices newComponent;
        switch (component) {
            case "Washer":
                newComponent = new Washer(this, "Washer" + ++WasherId);
                break;
            case "Garland":
                newComponent = new Garland(this, "Garland" + ++garlandId);
                break;
            case "Lamp":
                newComponent = new Lamp(this, "Lamp" + ++lampId);
                break;
            case "Microwave":
                newComponent = new Microwave(this, "Microwave" + ++microwaveId);
                break;
            case "TV":
                newComponent = new TV(this, "TV" + ++TVId);
            default:
                return false;
        }
        components.add(newComponent);
        consoleHelper.print(newComponent.getName() + " has been added");
        return true;
    }

    private boolean removeComponent(String name) {
        Devices compToRemove = null;
        for (Devices component : components) {
            if (component.getName().equals(name)) {
                compToRemove = component;
            }
        }
        if (compToRemove != null) {
            components.remove(compToRemove);
            consoleHelper.print(name + " has been removed");
            return true;
        }
        return false;
    }

    private Devices findComponentByName(String name) {
        for (Devices cmp : components) {
            if (cmp.getName().equals(name)) {
                return cmp;
            }
        }
        return null;
    }

    public void performCommand(String cmd) {
        if (!consoleHelper.isCommandValid(cmd)) {
            consoleHelper.printInfoAboutSuccess(false);
            return;
        }
        String[] cmdParts = cmd.split(" ");
        String action = cmdParts[0];
        boolean succeed = true;
        switch (action) {
            case "HELP":
                consoleHelper.print("----- AVAILABLE DEVICES ----- " +
                        "\nWasher" +
                        "\nGarland" +
                        "\nLamp" +
                        "\nMicrowave" +
                        "\nTV"+
                        "\n-----AVAILABLE COMMANDS -----\n" +
                        "* ADD deviceName (example: ADD Lamp) \n" +
                        "* DELETE name_device (example: DELETE Lamp1) \n" +
                        "* ON name_device (example ON Lamp1)\n" +
                        "* OFF name_device (example OFF Lamp1)\n" +
                        "* INFO\n" +
                        "* MODE washingMashine_name mode_number \n" +
                        " -- Mode -- \n1. Intensive\n2. Manual\n3. Cotton and linen\n4. Daily wash\n" +
                        "* CHANEL name_TV chanel_number \n" +
                        "-- Chanel -- \n1. TET\n2. QTV\n3. NEW\n4. ICTV\n5. INTER\n"
                );
                break;
            case "ADD":
                String compType = cmdParts[1];
                succeed = addDeviceToHome(compType);
                break;
            case "DELETE":
                String compName = cmdParts[1];
                succeed = removeComponent(compName);
                break;
            case "ON":
                compName = cmdParts[1];
                Devices component = findComponentByName(compName);
                if (component != null) {
                    succeed = component.turnOnOff(true);
                } else {
                    succeed = false;
                }
                break;
            case "OFF":
                compName = cmdParts[1];
                component = findComponentByName(compName);
                if (component != null) {
                    succeed = component.turnOnOff(false);
                } else {
                    succeed = false;
                }
                break;

            case "MODE":
                compName = cmdParts[1];
                component = findComponentByName(compName);
                if (component != null && component.isTurnedOn()) {
                    int modeNumber = Integer.valueOf(cmdParts[2]);

                    if (component.getClass().equals(Washer.class)) {
                        succeed = ((Washer) component).setCurrMode(modeNumber);
                    }
                } else {
                    succeed = false;
                }
                break;
            case "CHANEL":
                compName = cmdParts[1];
                component = findComponentByName(compName);
                if (component != null && component.isTurnedOn()) {
                    int modeNumber = Integer.valueOf(cmdParts[2]);
                    if (component.getClass().equals(TV.class)) {
                        succeed = ((TV) component).setCurrMode(modeNumber);
                    }
                } else {
                    succeed = false;
                }
                break;
            case "INFO":
                consoleHelper.printDetailedInformation(components);
        }
        consoleHelper.printInfoAboutSuccess(succeed);
    }

    @Override
    public void Log(String message) {
        consoleHelper.print(message);
    }
}
