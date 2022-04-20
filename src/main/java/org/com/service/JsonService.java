package org.com.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.com.model.Question;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonService {

    @Value("${source.path}")
    private String source;
    @Value("${source.filename}")
    private String sourceFileName;
    @Value("${save.path}")
    private String pathToSave;
    @Value("${result.filename}")
    private String fileName;

    public Map<Integer, Question> read() {
        if (!new File(source).exists())
            throw new RuntimeException("Does not exists path: " + source);

        File file = new File(source + sourceFileName);
        Map<Integer, Question> questions;
        try (FileReader fileReader = new FileReader(file)) {
            Gson gson = new Gson();
            Type questionsListType = new TypeToken<HashMap<Integer, Question>>(){}.getType();
            questions = gson.fromJson(fileReader, questionsListType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return questions;
    }

    public void write(List<Map<String, String>> branches) {
        if (!new File(pathToSave).exists())
            throw new RuntimeException("Does not exists path: " + pathToSave);

        File file = new File(pathToSave + fileName);

        try (FileWriter fileWriter = new FileWriter(file)) {
            if (file.createNewFile())
                throw new RuntimeException("Cannot create file: " + fileName);

            String json = new Gson().toJson(branches);
            fileWriter.write(json);

        } catch (IOException e) {
            throw new RuntimeException("Cannot save result to JSON file" + e);
        }
    }
}
