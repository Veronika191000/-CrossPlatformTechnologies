import java.util.ArrayList;

public class Washer extends Devices {
    private int currMode = 1;
    private ArrayList<String> modes;

    public Washer(Logger logger, String name) {
        super(logger, name);
        initModes();
    }

    private void initModes() {
        modes = new ArrayList<String>();
        modes.add("Automatic");
        modes.add("Local");
        modes.add("Around perimeter");
        modes.add("Max");
    }

    public String getCurrentMode() {
        return modes.get(currMode - 1);
    }

    public String getModes() {
        String result = "";
        for (String mode : this.modes) {
            result += mode + "\n";
        }
        return result;
    }

    @Override
    public String getState() {
        String result = super.getState();
        result += "\nMode: " + getCurrentMode();
        return result;
    }

    public boolean setCurrMode(int currMode) {
        if (currMode <= modes.size() && super.isTurnedOn()) {
            this.currMode = currMode;
            this.logger.Log(super.getName() + " mode changed to " + currMode + "(" + getCurrentMode() + ")");
            return true;
        }
        return false;
    }

    private boolean isThereThisMode(String name) {
        for (String channel : this.modes) {
            if (channel == name) return true;
        }
        return false;
    }
}
