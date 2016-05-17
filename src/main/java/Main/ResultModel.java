package Main;

/**
 * Created by HAMMAX on 17.05.2016.
 */
public class ResultModel {
    double value;
    int interval;
    double error;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }
}
