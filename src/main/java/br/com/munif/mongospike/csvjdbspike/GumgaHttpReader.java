package br.com.munif.mongospike.csvjdbspike;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import org.relique.io.TableReader;


/**
 *
 * @author munif
 */
public class GumgaHttpReader implements TableReader {

    public static String stringUrl = "https://docs.google.com/spreadsheets/d/1uk1Jd8I0vbXC2Zc40PlNCMQBvnhOIaUZyMhCze6Df5k/pub?output=csv&name=dados.csv";

    @Override
    public Reader getReader(Statement statement, String tableName) throws SQLException {
        try {
            URL url = new URL(stringUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            return reader;
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }

    }

    @Override
    public List<String> getTableNames(Connection cnctn) throws SQLException {
        return Arrays.asList(new String[]{"Sprint"});
    }

}
