package ru.otus.java.basic.http.server.processors;

import ru.otus.java.basic.http.server.HttpRequest;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class UnknownRequestProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest httpRequest, OutputStream output) throws IOException {
        String jsonResponse = "{\"Error\": {\"errorCode\": 404, \"errorMessage\": \"Получен неизвестный запрос\"}}";
        String response = "HTTP/1.1 404 Not found\r\nContent-Type: json/html\r\n\r\n" + jsonResponse;
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
