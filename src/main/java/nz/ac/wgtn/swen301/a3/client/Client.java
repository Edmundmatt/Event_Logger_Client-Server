package nz.ac.wgtn.swen301.a3.client;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.FileWriter;
import java.io.IOException;

public class Client{

    public static void main(String[] args) {
        HttpRequestFactory requestFactory
                = new NetHttpTransport().createRequestFactory();
        HttpRequest request;

        try {
            if(args[0].equals("excel")) {
                request = requestFactory.buildGetRequest(
                        new GenericUrl("http://localhost:8080/resthome4logs/statsxls"));

            }else if(args[0].equals("csv")){
                request = requestFactory.buildGetRequest(
                        new GenericUrl("http://localhost:8080/resthome4logs/statscsv"));
            }else{
                request = requestFactory.buildGetRequest(
                        new GenericUrl("http://localhost:8080/resthome4logs/"));
            }

            FileWriter file = new FileWriter(args[1]);
            file.write(request.execute().parseAsString());
            file.close();
        } catch (IOException e) {
            System.err.println("buildGetRequest failure");
            e.printStackTrace();
        } catch(IllegalArgumentException e) {
            System.err.println("Invalid URL request, connection failure");
            e.printStackTrace();
        }
    }
}
