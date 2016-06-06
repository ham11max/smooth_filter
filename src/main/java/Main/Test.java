package Main;

import Service.AdaptiveSmoothingFilter;
import Service.NoiseGenerator;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by HAMMAX on 17.05.2016.
 */
public class Test {
    public static void main(String[] args)throws Exception {
        String pathToInput = "Output\\inputFile.txt";
        String pathToOutput = "Output\\output.txt";
        String pathToImages = "Output\\images";

        NoiseGenerator noiseGenerator = new NoiseGenerator();
        ArrayList <Double> trend =  AdaptiveSmoothingFilter.getTrendFromFile(pathToInput);

        PlotResults.plotDataAndSaveToImage(pathToImages+File.separator+"TrendData.jpg","Trend" , trend, 1);

        ArrayList<Double> noiseTrend = noiseGenerator.addNoiseToTrend(noiseGenerator.getNoiseByMaxAmplitude(5, 0.11), AdaptiveSmoothingFilter.getTrendFromFile(pathToInput));
        AdaptiveSmoothingFilter adaptiveSmoothingFilter = new AdaptiveSmoothingFilter();

        PlotResults.plotDataAndSaveToImage(pathToImages+File.separator+"NoisesData.jpg", "Trend+Noise" , noiseTrend, 1);

        ArrayList<Double> res =adaptiveSmoothingFilter.filterData(noiseTrend,pathToImages ,pathToOutput);
        PlotResults.plotDataAndSaveToImage(pathToImages+ File.separator+"FinalResult.jpg","Final RESULT", res, 1);

        System.out.println(AdaptiveSmoothingFilter.compareResults(trend , res));
    }
}
