import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Calculations {
    private static double averageValueOfTheSamples;

    public static int dynamic(List<Integer> values) {
        Collections.sort(values);
        return values.get(values.size() - 1) - values.get(0);
    }

    private int energy(List<Integer> values) {
        return values.stream().reduce(0, (a, b) -> a + b * b);
    }

    private double signalAvgValue(List<Integer> values) {
        double result = 0;
        for (int i : values) {
            result += i;
        }
        double ms = result;
        Calculations.averageValueOfTheSamples = ms;
        return ms;
    }

    double dispersion(List<Integer> values) {
        double signalAvgVal = signalAvgValue(values);
        double result = 0;

        for (int i : values) {
            result += Math.pow((i - signalAvgVal), 2);
        }
        return result / values.size();
    }

    List<Double> autocorrelationDiscreteSignal(List<Integer> numbers) {
        List<Double> autocorrelation = new ArrayList<>();
        double tmp;
        for (int t = 0; t <= 5; t++) {
            tmp = 0;
            for (int i = 0; i < numbers.size() - t - 1; i++) {
                tmp += (numbers.get(i + t) - averageValueOfTheSamples) * (numbers.get(i) - averageValueOfTheSamples);
            }
            autocorrelation.add(tmp / numbers.size() - t);
        }
        return autocorrelation;
    }

    private double power(List<Integer> values) {
        double power = (double) values.stream().reduce(0, (a, b) -> a + b * b) / values.size();
        return power;
    }

    private double correlationInterval(List<Integer> numbers, List<Double> autocorrelation) {
        double tkor;
        double rxxj = 0;
        double rxx0 = 0;
        for (Double aDouble : autocorrelation) {
            rxxj += aDouble;
        }
        for (int i = 0, t = 0; i < numbers.size() - t - 1; i++) {
            rxx0 += (numbers.get(i + t) - averageValueOfTheSamples) * (numbers.get(i) - averageValueOfTheSamples);
        }
        tkor = rxxj / rxx0;
        return tkor;
    }

    public String printAllValues(List<Integer> numbers) {
        List<Double> autocorrelation = autocorrelationDiscreteSignal(numbers);
        String result = "\nSignal dynamic range: " + dynamic(numbers) +
                "\nSignal energy: " + energy(numbers) +
                "\nAverage signal strength: " + power(numbers) +
                "\nAverage value of signal samples: " + signalAvgValue(numbers) +
                "\nDispersion of signal sample values: " + dispersion(numbers) +
                "\nCorrelation interval :" + correlationInterval(numbers, autocorrelation) + "\n";
        return result;
    }

    public void autoCorrelationInterval(List<Double> correll) {
        for (int i = 0; i < correll.size(); i++) {
            System.out.println(i + "=>" + correll.get(i));
        }
    }
}
