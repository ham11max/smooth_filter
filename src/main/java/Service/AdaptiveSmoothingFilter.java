package Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by HAMMAX on 17.05.2016.
 */
public class AdaptiveSmoothingFilter {
    private static final int LENGTH = 1000;
    private BufferedWriter bufferedWriter = null;


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

    public ArrayList<Double> filterData (ArrayList<Double> noisesData){
        ArrayList<Double> filterData = new ArrayList<Double>(LENGTH);
        filterData.set()

    }

    private  void writeToOutput( String pathToOutput, int interval , double accuracy) throws IOException{
        try {
            File file = new File(pathToOutput);
            FileWriter fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("interval: "+interval +"  | accuracy: "+accuracy);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            bufferedWriter.close();
        }

    }

    private double getSmoothElementWith()




}
