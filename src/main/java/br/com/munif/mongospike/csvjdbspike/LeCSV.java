package br.com.munif.mongospike.csvjdbspike;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.relique.jdbc.csv.CsvDriver;


public class LeCSV {


    public static void main(String... args) {
        try {
            String arquivo="/home/munif/csv";
            // Load the driver.
            Class.forName("org.relique.jdbc.csv.CsvDriver");

            Connection conn = DriverManager.getConnection("jdbc:relique:csv:" + arquivo);
            //Connection conn = DriverManager.getConnection("jdbc:relique:csv:class:" + GumgaHttpReader.class.getName());

            Statement stmt = conn.createStatement();

            ResultSet results = stmt.executeQuery("SELECT Sprint,sum(Logged),sum(Estimated),100.0*sum(Logged)/sum(Estimated) FROM sprint group by Sprint order by Sprint ");

            boolean append = true;
            CsvDriver.writeToCsv(results, System.out, append);

            // Clean up
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void leitor() throws Exception {

        URL u = new URL(GumgaHttpReader.stringUrl);
        URLConnection conn = u.openConnection();
        InputStream is = conn.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }
        is.close();

    }

}
