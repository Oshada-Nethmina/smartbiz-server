package com.smartbiz.smartbiz_backend.service.impl;

import com.smartbiz.smartbiz_backend.service.OpenAIService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OpenAIServiceImpl implements OpenAIService {
    @org.springframework.beans.factory.annotation.Value("${app.openai.api-key}")
    private String apiKey;
    @org.springframework.beans.factory.annotation.Value("${app.openai.model}")
    private String model;

    @Override
    public String complete(String systemPrompt, String userMessage) {
        try {
            okhttp3.OkHttpClient client = new okhttp3.OkHttpClient.Builder()
                    .connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                    .readTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
                    .build();

            String body = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(Map.of(
                    "model", model,
                    "messages", List.of(
                            Map.of("role", "system", "content", systemPrompt),
                            Map.of("role", "user", "content", userMessage)
                    ),
                    "max_tokens", 1000
            ));

            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url("https://api.openai.com/v1/chat/completions")
                    .addHeader("Authorization", "Bearer " + apiKey)
                    .addHeader("Content-Type", "application/json")
                    .post(okhttp3.RequestBody.create(body, okhttp3.MediaType.parse("application/json")))
                    .build();

            try (okhttp3.Response response = client.newCall(request).execute()) {
                okhttp3.ResponseBody responseBodyObj = response.body();

                if (responseBodyObj == null) {
                    throw new RuntimeException("Response body is null");
                }

                String responseBody = responseBodyObj.string();

                com.fasterxml.jackson.databind.JsonNode json =
                        new com.fasterxml.jackson.databind.ObjectMapper()
                                .readTree(responseBody);
                return json.at("/choices/0/message/content").asText("No response generated.");
            }
        } catch (Exception exception) {
            throw new RuntimeException("OpenAI API call failed: " + exception.getMessage());
        }
    }
}

