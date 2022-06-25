package ufv.dis.final2022.AJC2;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

@RestController
public class Controller {

    private static final String api = "https://swapi.dev/api/%s/%s";
    HttpRequest request;
    HttpClient client = HttpClient.newBuilder().build();
    HttpResponse<String> response;

    ArrayList<PostRequest> Busquedas = new ArrayList<PostRequest>();

    @PostMapping("/POST")
    public String BuscarPeople(@RequestBody PostRequest c){
        Busquedas.add(c);
        try {
            String id = String.valueOf(c.getId());
            String resource = String.format(api, c.getEntity(), id);
            System.out.println(resource);
            request = HttpRequest
                    .newBuilder(new URI(resource))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return response.body();
    }

    @GetMapping("/GET")
    public ArrayList<PostRequest> MostrarIpsBuscadas(){
        String json = new Gson().toJson(Busquedas);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("json/Busquedas.json"))) {
            bw.write(json);
            System.out.println("Fichero creado\n ------------ \n");
        } catch (IOException ex) {
            System.out.println("No ha sido posible crear el archivo.");
        }
        return Busquedas;
    }
}
