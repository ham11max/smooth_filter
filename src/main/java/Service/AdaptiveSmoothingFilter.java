package Service;

import Main.PlotResults;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by HAMMAX on 17.05.2016.
 */
public class AdaptiveSmoothingFilter {
    private BufferedWriter bufferedWriter = null;
    private ArrayList<Double> finalResult;
    private HashMap<Integer , Double> mistake;

    public ArrayList filterData (ArrayList<Double> noisesData,String imagesPath ,String pathToOutputFile)throws  Exception{
        int maxInterval;
        mistake = new HashMap<Integer, Double>();
        finalResult = noisesData;
        createFile(pathToOutputFile);
        if(noisesData.size()%2 ==0){
            maxInterval = noisesData.size()/2-1;
        }else{
            maxInterval = noisesData.size()/2+1;
        }
        for(int interval = 1; interval<=maxInterval; interval++){
            writeToOutput("------interval = "+interval+"------");
            getSmoothElementWithInterval(interval ,noisesData ,imagesPath);
        }
        closeWriter();
        return finalResult;
    }

    private void getSmoothElementWithInterval( int interval , ArrayList<Double> noiseData ,
                                               String imagesPath ) throws Exception{
        for(int i = interval;i < noiseData.size() -interval;i++) {
            double newValue = getArrangeInInterval(interval,i,finalResult);
            if (interval ==1){
                finalResult.set(i , newValue);
                writeToOutput( i , 0.0 , newValue);

            }else if(interval>1){
                double sigma = Math.pow(newValue - finalResult.get(i), 2);
                if (mistake.get(i) != null) {
                    if (mistake.get(i) > sigma) {
                        finalResult.set(i, newValue);
                        mistake.put(i, sigma);
                        writeToOutput(i, sigma, newValue);
                    }
                } else if (mistake.get(i) == null) {
                    finalResult.set(i, newValue);
                    mistake.put(i, sigma);
                    writeToOutput(i, sigma, newValue);
                }
            }
        }
        if(interval<10) {
            String header = "Noise Data with interval " + interval;
            String fullPath = PlotResults.getNameOFImageFile(interval, imagesPath);
            PlotResults.plotDataAndSaveToImage(fullPath, header, finalResult, interval);
        }

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
                trend.add(Double.parseDouble(text.replace("," , ".")));
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

    private  void writeToOutput( int interval , double accuracy , double value) throws IOException{
        bufferedWriter.write("value: "+ value+"  |   index : "+interval +"  | accuracy: "+accuracy);
        bufferedWriter.newLine();
    }

    private void closeWriter() throws IOException{
        bufferedWriter.close();
    }

    private  void writeToOutput( String value) throws IOException{
        bufferedWriter.write(value);
        bufferedWriter.newLine();
    }

    public static double compareResults(ArrayList<Double> trend, ArrayList<Double> result){
        double determinationValue  = 0.0;
        for(int i = 1; i<trend.size()-1;i++){
            determinationValue = determinationValue + Math.pow(trend.get(i) - result.get(i),2)/(trend.size()-2);
        }
        determinationValue = Math.sqrt(determinationValue);
        return determinationValue;
    }
}

