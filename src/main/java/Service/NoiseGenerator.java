package Service;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.Random;

/**
 * Created by HAMMAX on 17.05.2016.
 */
public class NoiseGenerator {
    private final static int LENGTH = 1000;


    public double[] getNoiseByMaxAmplitude(double maxValue ,  double percentages ){
        double [] noise = new double [LENGTH];
        for(int i = 0; i<LENGTH;i++) {
            noise[i] = generateRandomValue(-maxValue*percentages , maxValue*percentages);
        }
        return noise;
    }

    private static double generateRandomValue(double rangeMax , double rangeMin){
        Random rand = new Random();
        double randomValue = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
        return  randomValue;
    }

    public double[] addNoiseToTrend(double [] noise  , double [] trend ){
        for(int i = 0;i<LENGTH;i++) {
            trend[i] = noise[i]+trend[i];
        }
        return  trend;
    }
}
