package Service;

import Main.PlotResults;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;

/**
 * Created by HAMMAX on 17.05.2016.
 */
public class AdaptiveSmoothingFilter {
    private static final int LENGTH = 100;
    private BufferedWriter bufferedWriter = null;

    public void filterData (ArrayList<Double> noisesData , ArrayList<Double> trendData ,String imagesPath)throws  Exception{
        int maxInterval;
        if(noisesData.size()%2 ==0){
            maxInterval = noisesData.size()/2-1;
        }else{
            maxInterval = noisesData.size()/2+1;
        }
        for(int interval = 1; interval<=maxInterval;interval++){
            getSmoothElementWithInterval(interval ,noisesData ,imagesPath);
        }
    }


    private void getSmoothElementWithInterval( int interval , ArrayList<Double> noiseData , String imagesPath) throws Exception{
        ArrayList<Double>  intervalDataSmooth = noiseData;
        for(int i =interval;i < noiseData.size() -interval;i++) {
            intervalDataSmooth.set(i , getArrangeInInterval(interval,i,noiseData));
        }
        String fullPath = PlotResults.getNameOFImageFile(interval,imagesPath);
        PlotResults.plotDataAndSaveToImage(fullPath,intervalDataSmooth,interval);

    }
    private double getArrangeInInterval(int interval, int current,ArrayList<Double> noiseData){
        double result = 0.0;
        for(int  i = current-interval; i<=current+interval;i++){
            result = result+noiseData.get(i)/(2*interval+1);
        }
        return result;
    }



    public static ArrayList<Double> getTrendFromFile(String pathToFile){
        ArrayList<Double> trend = new ArrayList<Double>();
        File file = new File(pathToFile);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null) {
                trend.add(Double.parseDouble(text));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }

        return trend;
    }


    private void createFile(String pathToFile){
        try{
            File file = new File(pathToFile);
            FileWriter fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private  void writeToOutput( String pathToOutput, int interval , double accuracy) throws IOException{
        bufferedWriter.write("interval: "+interval +"  | accuracy: "+accuracy);
        bufferedWriter.newLine();
    }
    private void closeWriter() throws IOException{
        bufferedWriter.close();
    }


}
