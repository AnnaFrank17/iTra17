package navarich;

import com.triptheone.joda.Stopwatch;
import navarich.models.File;
import navarich.models.Mistake;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, IOException {
        int iterations = Integer.parseInt(args[0]);
        int mistakeCount = Integer.parseInt(args[2]);
        String location = args[1];

        Stopwatch stopwatch = Stopwatch.start();
        File.WriteGeneratedArrayToFile(new Mistake(mistakeCount, iterations), iterations, location);
        System.out.println(stopwatch.getElapsedTime());
    }
}
