package com.nobsrecipebook;

import com.ryanpodell.model.Datasource;

public class Main {
    public static void main(String[] args) {
        Datasource datasource = new Datasource();
        if (!datasource.open()) {
            System.out.println("\nCannot open datasource");
            return;
        } else {
            System.out.println("\nDatasource open!");
        }


        // Close connection
        datasource.close();
    }
}
