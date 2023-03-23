import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Find R^2 value of change in temperature from pre-pollution to post 30 minute wait, first value is control
        System.out.println(Math.pow(getR("/Users/edwinryerson/Documents/GitHub/Group-4-Project-new/untitled/src/pre_pollution_temp.csv", "/Users/edwinryerson/Documents/GitHub/Group-4-Project-new/untitled/src/post_pollution_temp.csv"), 2));

    }

    private static double getR(String dirOriginal, String dirFinal) {
        File myFile1 = new File(dirOriginal);
        File myFile2 = new File(dirFinal);
        Scanner scanner = null;
        Scanner scanner1 = null;
        try {
            scanner = new Scanner(myFile1);
            scanner.useDelimiter(",");

            scanner1 = new Scanner(myFile2);
            scanner1.useDelimiter(",");
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            e.printStackTrace();
        }

        ArrayList<String[]> data = new ArrayList<>();
        ArrayList<String[]> data1 = new ArrayList<>();

        while (scanner.hasNext()) {
            String line = scanner.next();

            String[] r = line.trim().split(",");
            data.add(r);
        }

        while(scanner1.hasNext()) {
            String line1 = scanner1.next();

            String[] r1 = line1.trim().split(",");
            data1.add(r1);
        }

        double sumx = 0;
        double sumy = 0;
        double sumxy = 0;
        double sumx2 = 0;
        double sumy2 = 0;
        double n = data.size();
        double difference = 0;


        for(int i = 0; i < data.size(); i++) {
            sumx += i;
            sumx2 += i * i;

            // Final temperature - original temperature
            difference = Double.parseDouble(data1.get(i)[0]) - Double.parseDouble(data.get(i)[0]);

            sumy += difference;
            sumy2 += Math.pow(difference, 2);
            sumxy += i * (difference);
        }

        double r = ((n * sumxy) - (sumx * sumy))/(Math.pow(((n * sumx2) - (sumx * sumx)), .5) * Math.pow(((n * sumy2) - (sumy * sumy)), .5));
        return r;

    }
}