package main;

import converter.UnivMorph2MSD;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by zsjanos on 2017.02.06..
 */
public class Main {

    public static void main(String[] args) {

//        System.out.println(UnivMorph2MSD.convert("második", "ADJ", "Case=Nom|NumType=Ord|Number=Sing"));
//        System.exit(0);

        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get("./data/newsml.ud"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : lines) {
            if (line.trim().length() > 0) {

                String[] split = line.split("\t");
                System.err.println(line);
                System.err.println(UnivMorph2MSD.convert(split[1], split[2], split[3]));
                //System.exit(0);
            }
        }
    }
}
