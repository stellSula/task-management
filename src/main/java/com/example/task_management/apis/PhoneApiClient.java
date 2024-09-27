package com.example.task_management.apis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PhoneApiClient {
    private static ReqresResponse getReqresResponce(OkHttpClient client, String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        String result = response.body().string();

        return jsonResponseToPojo(result);
    }

    private static ReqresResponse jsonResponseToPojo(String response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        ReqresResponse reqresResponse = mapper.readValue(response, ReqresResponse.class);

        return reqresResponse;
    }
}
