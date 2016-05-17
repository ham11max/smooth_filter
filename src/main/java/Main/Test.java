package Main;

import Service.NoiseGenerator;

/**
 * Created by HAMMAX on 17.05.2016.
 */
public class Test {
    public static void main(String[] args) {
        NoiseGenerator noiseGenerator = new NoiseGenerator();
        double [] mass = noiseGenerator.getNoiseByMaxAmplitude(30 , 0.2);
        for(int i = 0; i< 1000;i++){

            System.out.println(mass[i]);
        }
    }
}
