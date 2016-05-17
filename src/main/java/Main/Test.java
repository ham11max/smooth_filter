package Main;

import Service.AdaptiveSmoothingFilter;
import Service.NoiseGenerator;

import java.util.ArrayList;

/**
 * Created by HAMMAX on 17.05.2016.
 */
public class Test {
    public static void main(String[] args)throws Exception {
        String path = "C:\\Users\\HAMMAX\\Desktop\\Диплом\\Output\\inputFile.txt";
        String pathToImages = "C:\\Users\\HAMMAX\\Desktop\\Диплом\\Output\\images";
        NoiseGenerator noiseGenerator = new NoiseGenerator();
        ArrayList<Double> noiseTrend = noiseGenerator.addNoiseToTrend(noiseGenerator.getNoiseByMaxAmplitude(5, 0.05), AdaptiveSmoothingFilter.getTrendFromFile(path));

    }
}
