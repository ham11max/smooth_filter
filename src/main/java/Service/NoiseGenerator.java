package Service;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by HAMMAX on 17.05.2016.
 */
public class NoiseGenerator {
    private final static int LENGTH = 100;


    public ArrayList<Double> getNoiseByMaxAmplitude(double maxValue , double percentages ){
        ArrayList<Double> noise = new ArrayList<Double>(LENGTH);
        for(int i = 0; i<LENGTH;i++) {
            noise.add(generateRandomValue(-maxValue*percentages , maxValue*percentages));
        }
        return noise;
    }

    private static double generateRandomValue(double rangeMax , double rangeMin){
        Random rand = new Random();
        double randomValue = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
        return  randomValue;
    }

    public static ArrayList<Double> addNoiseToTrend(ArrayList<Double> noise  , ArrayList<Double> trend ){
            for(int i =0;i<LENGTH;i++){
                trend.set(i ,trend.get(i)+noise.get(i));
            }
        return  trend;
    }
}
