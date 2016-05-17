package Service;

import Main.PlotResults;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by HAMMAX on 17.05.2016.
 */
public class AdaptiveSmoothingFilter {
    private BufferedWriter bufferedWriter = null;
    private ArrayList<Double> finalResult;
    private HashMap<Integer , Double> mistake;

    public ArrayList filterData (ArrayList<Double> noisesData , ArrayList<Double> trendData ,String imagesPath ,String pathToOutputFile)throws  Exception{
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
            getSmoothElementWithInterval(interval ,noisesData ,imagesPath , trendData);
        }
        closeWriter();
        System.out.println(mistake);
        return finalResult;
    }

    private void getSmoothElementWithInterval( int interval , ArrayList<Double> noiseData ,
                                               String imagesPath ,ArrayList<Double> trendData) throws Exception{
        ArrayList <Double>  intervalDataSmooth = noiseData;
        for(int i = interval;i < noiseData.size() -interval;i++) {
            double newValue = getArrangeInInterval(interval,i,noiseData);
            double sigma = Math.pow(newValue - trendData.get(i) ,2);
            if(mistake.get(i)!=null){
                if(mistake.get(i)>sigma){
                    finalResult.set(i ,newValue);
                    mistake.put(i , sigma);
                    writeToOutput( i , sigma , newValue);
                }
            }else if(mistake.get(i)==null){
                finalResult.set(i , newValue);
                mistake.put(i , sigma);
                writeToOutput( i , sigma , newValue);
            }
            intervalDataSmooth.set(i ,newValue );
        }
        if(interval<10) {
            String header = "Noise Data with interval " + interval;
            String fullPath = PlotResults.getNameOFImageFile(interval, imagesPath);
            PlotResults.plotDataAndSaveToImage(fullPath, header, intervalDataSmooth, interval);
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
}
