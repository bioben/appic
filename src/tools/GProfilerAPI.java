/*
* Runs in terminal as java GProfilerAPI.java
export JAVA_HOME=/usr/share/java
export PATH=$PATH:$JAVA_HOME/bin
export JSON_JAVA=/home/benjamin/Documents/CS/json
export CLASSPATH=$CLASSPATH:$JSON_JAVA/json-simple-1.1.1.jar:.

*/

package net.javaguides.network;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStream;
import java.io.DataOutputStream;

public class GProfilerAPI {

    public static void getRequest() throws IOException {
        String urly = "https://biit.cs.ut.ee/gprofiler/api/gost/profile/";
        URL obj = new URL(urly);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type","application/json");


        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes("{\"organism\": \"hsapiens\", \"query\": [\"CASQ2\", \"CASQ1\", \"GSTO1\", \"DMD\", \"GSTM2\"]}");
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("Response Code : " + responseCode);

        BufferedReader iny = new BufferedReader(
        new InputStreamReader(con.getInputStream()));
          String output;
          StringBuffer response = new StringBuffer();

          while ((output = iny.readLine()) != null) {
           response.append(output);
          }
          iny.close();

          //printing result from response
          System.out.println(response.toString());

    }

    public static void main (String args[]) throws IOException {
        System.out.println("Sending request");
        getRequest();
    }
}
