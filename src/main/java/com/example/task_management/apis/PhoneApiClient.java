package com.example.task_management.apis;

import com.example.task_management.models.Phone;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class PhoneApiClient {
    private final OkHttpClient client;
    private final String apiUrl = "https://api.restful-api.dev/objects";
    private final ObjectMapper objectMapper;

    public PhoneApiClient(OkHttpClient client) {
        this.client = client;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public List<Phone> fetchPhones() throws IOException {
        Request request = new Request.Builder()
                .url(apiUrl)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String responseBody = response.body().string();
            return jsonResponseToPojo(responseBody);
        }
    }

    private List<Phone> jsonResponseToPojo(String response) throws JsonProcessingException {
        return objectMapper.readValue(response, objectMapper.getTypeFactory().constructCollectionType(List.class, Phone.class));
    }
}
