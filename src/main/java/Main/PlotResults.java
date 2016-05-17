package Main;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by HAMMAX on 17.05.2016.
 */
public class PlotResults {

    public static void plotDataAndSaveToImage(String imagePath , ArrayList<Double> data , int interval) throws IOException{
        DefaultCategoryDataset categoryDataSet = new DefaultCategoryDataset();
        for (Integer i = 0; i<data.size(); i++){
            categoryDataSet.addValue( data.get(i) , "interval" , i);
        }
        JFreeChart lineChartObject = ChartFactory.createLineChart("Noise Data with interval "+interval,"Time", "Data", categoryDataSet, PlotOrientation.VERTICAL, true,true,false);
        int width = 640;
        int height = 480;
        File lineChart = new File( imagePath );
        ChartUtilities.saveChartAsJPEG(lineChart ,lineChartObject, width ,height);
    }
    public static String getNameOFImageFile(int interval , String imagePath){
        return imagePath+File.separator+"Filter with interval "+ interval+".jpg";
    }
}
