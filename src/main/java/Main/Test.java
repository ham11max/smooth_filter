package Main;

import Service.AdaptiveSmoothingFilter;
import Service.NoiseGenerator;

/**
 * Created by HAMMAX on 17.05.2016.
 */
public class Test {
    public static void main(String[] args) {
        String path = "C:\\FilesFor filter\\inputFile.txt";
        AdaptiveSmoothingFilter.getTrendFromFile(path);
        NoiseGenerator noiseGenerator = new NoiseGenerator();
        noiseGenerator.getNoiseByMaxAmplitude(5 ,0.2);
        System.out.println( noiseGenerator.addNoiseToTrend(noiseGenerator.getNoiseByMaxAmplitude(5 ,0.2) ,AdaptiveSmoothingFilter.getTrendFromFile(path) ));


    }
}
