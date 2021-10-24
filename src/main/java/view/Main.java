package view;

import controller.Filters;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();

        String input = "<General>\n" +
                "TC:1\n" +
                "<Mesa 1>\n" +
                "UG:2\n" +
                "RI:500000\n" +
                "<Mesa 2>\n" +
                "UG:1\n" +
                "RF:500000\n" +
                "<Mesa 3>\n" +
                "UG:3\n" +
                "TC:5\n" +
                "RF:10000\n" +
                "<Mesa 4>\n" +
                "UG:1\n" +
                "RF:100000\n" +
                "<Mesa 5>\n" +
                "UG:99\n" +
                "<Mesa 6>\n" +
                "TC:11\n" +
                "RI:10000";

        Filters filters = new Filters(input);

        filters.procesar();
        filters.printOutput();
    }

}
