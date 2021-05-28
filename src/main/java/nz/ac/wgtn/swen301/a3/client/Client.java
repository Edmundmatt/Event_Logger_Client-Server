package nz.ac.wgtn.swen301.a3.client;


import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import java.io.IOException;
import java.net.Authenticator;
import java.net.CookieHandler;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class Client extends HttpClient {

    private static final HttpClient client = HttpClient.newBuilder()
            .version(Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public Client(){

    }

    public static void main(String[] args) throws IOException, InterruptedException {
//        String type = args[0];
//        String file = args[1];
        String type = "excel";
        String file = "clientOutput";

        if(type.equals("excel")){
            var request = HttpRequest.newBuilder()
                    .uri(URI.create("https://localhost:8080/resthome4logs/statsxls"))
                    .timeout(Duration.ofMinutes(2))
                    .header("Content-Type", "application/vnd.ms-excel")
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());

        }else if(type.equals("csv")){
            var request = HttpRequest.newBuilder()
                    .uri(URI.create("https://localhost:8080/resthome4logs/statscsv"))
                    .timeout(Duration.ofMinutes(2))
                    .header("Content-Type", "text/csv")
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());

        }else{

        }


    }

    @Override
    public Optional<CookieHandler> cookieHandler() {
        return Optional.empty();
    }

    @Override
    public Optional<Duration> connectTimeout() {
        return Optional.empty();
    }

    @Override
    public Redirect followRedirects() {
        return null;
    }

    @Override
    public Optional<ProxySelector> proxy() {
        return Optional.empty();
    }

    @Override
    public SSLContext sslContext() {
        return null;
    }

    @Override
    public SSLParameters sslParameters() {
        return null;
    }

    @Override
    public Optional<Authenticator> authenticator() {
        return Optional.empty();
    }

    @Override
    public Version version() {
        return null;
    }

    @Override
    public Optional<Executor> executor() {
        return Optional.empty();
    }

    @Override
    public <T> HttpResponse<T> send(HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler) throws IOException, InterruptedException {
        return null;
    }

    @Override
    public <T> CompletableFuture<HttpResponse<T>> sendAsync(HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler) {
        return null;
    }

    @Override
    public <T> CompletableFuture<HttpResponse<T>> sendAsync(HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler, HttpResponse.PushPromiseHandler<T> pushPromiseHandler) {
        return null;
    }
}
