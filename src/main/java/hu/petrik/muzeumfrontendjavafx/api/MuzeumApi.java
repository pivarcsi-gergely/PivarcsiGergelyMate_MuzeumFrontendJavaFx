package hu.petrik.muzeumfrontendjavafx.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import hu.petrik.muzeumfrontendjavafx.classes.Festmeny;
import hu.petrik.muzeumfrontendjavafx.classes.Szobor;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class MuzeumApi {
    private static final String BASE_URL = "http://localhost:8000";
    public static final String SZOBOR_API_URL = BASE_URL + "/api/statues";
    public static final String FESTMENY_API_URL = BASE_URL + "/api/paintings";

    public static List<Szobor> getSzobrok() throws IOException {
        Response response = RequestHandler.get(SZOBOR_API_URL);
        String json = response.getContent();
        Gson Gayson = new Gson();
        if (response.getResponseCode() >= 400) {
            System.out.println(json);
            String message = Gayson.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        } else {
            Type type = new TypeToken<List<Szobor>>() {
            }.getType();
            List<Szobor> szoborList = Gayson.fromJson(json, type);
            return szoborList;
        }
    }

    public static Szobor szoborHozzaadasa(Szobor ujSzobor) throws IOException {
        Gson GaysonAdd = new Gson();
        String szoborJson = GaysonAdd.toJson(ujSzobor);
        Response response = RequestHandler.post(SZOBOR_API_URL, szoborJson);

        String json = response.getContent();
        if (response.getResponseCode() >= 400) {
            String message = GaysonAdd.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return GaysonAdd.fromJson(json, Szobor.class);
    }

    public static Szobor szoborModositasa(Szobor modositando) throws IOException {
        Gson GaysonEdit = new Gson();
        String filmJson = GaysonEdit.toJson(modositando);
        Response response = RequestHandler.put(SZOBOR_API_URL + "/" + modositando.getId(), filmJson);

        String json = response.getContent();
        if (response.getResponseCode() >= 400) {
            String message = GaysonEdit.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return GaysonEdit.fromJson(json, Szobor.class);
    }

    public static boolean szoborTorlese(int id) throws IOException {
        Response response = RequestHandler.delete(SZOBOR_API_URL+"/"+id);

        Gson GaysonDel = new Gson();
        String json = response.getContent();
        if (response.getResponseCode() >= 400) {
            String message = GaysonDel.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return response.getResponseCode() == 204;
    }

    public static List<Festmeny> getFestmenyek() throws IOException {
        Response response = RequestHandler.get(FESTMENY_API_URL);
        String json = response.getContent();
        Gson Gayson = new Gson();
        if (response.getResponseCode() >= 400) {
            System.out.println(json);
            String message = Gayson.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        } else {
            Type type = new TypeToken<List<Festmeny>>() {
            }.getType();
            List<Festmeny> festmenyList = Gayson.fromJson(json, type);
            return festmenyList;
        }
    }

    public static Festmeny festmenyHozzaadasa(Festmeny ujFestmeny) throws IOException {
        Gson GaysonAdd = new Gson();
        String festmenyJson = GaysonAdd.toJson(ujFestmeny);
        Response response = RequestHandler.post(FESTMENY_API_URL, festmenyJson);

        String json = response.getContent();
        if (response.getResponseCode() >= 400) {
            String message = GaysonAdd.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return GaysonAdd.fromJson(json, Festmeny.class);
    }

    public static Festmeny festmenyModositasa(Festmeny modositando) throws IOException {
        Gson GaysonEdit = new Gson();
        String filmJson = GaysonEdit.toJson(modositando);
        Response response = RequestHandler.put(FESTMENY_API_URL + "/" + modositando.getId(), filmJson);

        String json = response.getContent();
        if (response.getResponseCode() >= 400) {
            String message = GaysonEdit.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return GaysonEdit.fromJson(json, Festmeny.class);
    }

    public static boolean festmenyTorlese(int id) throws IOException {
        Response response = RequestHandler.delete(FESTMENY_API_URL+"/"+id);

        Gson GaysonDel = new Gson();
        String json = response.getContent();
        if (response.getResponseCode() >= 400) {
            String message = GaysonDel.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return response.getResponseCode() == 204;
    }
}
