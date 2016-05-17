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
        ArrayList <Double> trend =  AdaptiveSmoothingFilter.getTrendFromFile(path);
        ArrayList<Double> noiseTrend = noiseGenerator.addNoiseToTrend(noiseGenerator.getNoiseByMaxAmplitude(5, 0.05), AdaptiveSmoothingFilter.getTrendFromFile(path));
        AdaptiveSmoothingFilter adaptiveSmoothingFilter = new AdaptiveSmoothingFilter();
        System.out.println(trend);
        System.out.println(trend.size());
        System.out.println(noiseTrend);
        adaptiveSmoothingFilter.filterData(noiseTrend,trend,pathToImages);
    }
}
