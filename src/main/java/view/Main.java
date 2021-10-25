package view;

import controller.Filters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("entrada.txt"));
            StringBuilder builder = new StringBuilder();
            String line = reader.readLine();

            while (line != null) {
                builder.append(line);
                builder.append("\n");
                line = reader.readLine();
            }
            Filters filters = new Filters(builder.toString());
            filters.applyFilters();
            System.out.println(filters.printOutput());
            javax.swing.JOptionPane.showMessageDialog(null, filters.printOutput(), "Salida", 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
