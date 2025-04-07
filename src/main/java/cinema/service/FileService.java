
package cinema.service;

import cinema.Cinema;
import com.google.gson.Gson;
import java.io.*;

public class FileService {

    private final Gson gson = new Gson();

    public void exportToJson(Cinema cinema, String fileName) throws IOException {
        try (Writer writer = new FileWriter(fileName)) {
            gson.toJson(cinema, writer);
        }
    }

    public Cinema importFromJson(String fileName) throws IOException {
        try (Reader reader = new FileReader(fileName)) {
            return gson.fromJson(reader, Cinema.class);
        }
    }
}
