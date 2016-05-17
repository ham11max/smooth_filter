package Main;

import Service.AdaptiveSmoothingFilter;
import Service.NoiseGenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by HAMMAX on 17.05.2016.
 */
public class Test {
    public static void main(String[] args)throws Exception {

        String pathToInput = "C:\\Users\\HAMMAX\\Desktop\\Диплом\\Output\\inputFile.txt";
        String pathToOutput = "C:\\Users\\HAMMAX\\Desktop\\Диплом\\Output\\output.txt";
        String pathToImages = "C:\\Users\\HAMMAX\\Desktop\\Диплом\\Output\\images";
        String outputPath = "C:\\Users\\HAMMAX\\Desktop\\Диплом\\Output";
        NoiseGenerator noiseGenerator = new NoiseGenerator();
        ArrayList <Double> trend =  AdaptiveSmoothingFilter.getTrendFromFile(pathToInput);
        ArrayList<Double> noiseTrend = noiseGenerator.addNoiseToTrend(noiseGenerator.getNoiseByMaxAmplitude(6, 0.05), AdaptiveSmoothingFilter.getTrendFromFile(pathToInput));
        AdaptiveSmoothingFilter adaptiveSmoothingFilter = new AdaptiveSmoothingFilter();
        PlotResults.plotDataAndSaveToImage(pathToImages+"noisesData.jpg", noiseTrend, 1);

        ArrayList<Double> res =adaptiveSmoothingFilter.filterData(noiseTrend,trend,pathToImages ,pathToOutput);
        PlotResults.plotDataAndSaveToImage(outputPath+ File.separator+"FinalResult.jpg", res, 1);
    }
}
