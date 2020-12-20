import java.io.IOException;
import java.util.List;

public class Results {

    public static void main(String[] args) throws IOException {
        List<Integer> data = ReadBinaryFile.readBinaryFile(args[0]);
        Calculations calc = new Calculations();
        double dispersion = calc.dispersion(data);
        List<Double> autocorrelation = calc.autocorrelationDiscreteSignal(data);
        System.out.println(calc.printAllValues(data));
        calc.autoCorrelationInterval(autocorrelation);
    }
}

