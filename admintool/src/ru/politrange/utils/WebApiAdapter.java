package ru.politrange.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.methods.HttpPost;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Created by developermsv on 08.12.2015.
 *  в конструкторе, prefix например "/api/stats/" или /api/persons
 *  метод select, param например "1?begin=11.11.11&end=12.11.11" или просто "1"
 *  метод delete, param например "1" или "2"
 */
public class WebApiAdapter {
    public static final int HTTP_200_OK = 200;
    private final String url = "http://localhost:10101";
    private String prefix;

    public WebApiAdapter(String prefix) {
        this.prefix = prefix;
    }

    public String select(String param) throws IOException {
        String result = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = null;
        try {
            request = new HttpGet(getFullUrl(param));
            HttpResponse response = httpClient.execute(request);
            if (getStatusRequest(response.getStatusLine().getStatusCode())) {
                result = getResultContent(response);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
        return result;
    }

    public String insert(JSONObject json) throws IOException {
        String result = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = null;
        try {
            request = new HttpPost(getFullUrl(null));
            StringEntity params = new StringEntity(json.toString(),"UTF-8");
            params.setContentType("application/json; charset=UTF-8");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            if (getStatusRequest(response.getStatusLine().getStatusCode())) {
                result = getResultContent(response);
            }
            return result;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
        return result;
    }
    public boolean update(JSONObject json, String param) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPut request = null;
        try {
            request = new HttpPut(getFullUrl(param));
            StringEntity params = new StringEntity(json.toString(),"UTF-8");
            params.setContentType("application/json; charset=UTF-8");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            return  (getStatusRequest(response.getStatusLine().getStatusCode()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
        return false;
    }

    public boolean delete(String param) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpDelete request = new HttpDelete(getFullUrl(param));
            HttpResponse response = httpClient.execute(request);
            return getStatusRequest(response.getStatusLine().getStatusCode());
        } catch (Exception ex) {
        } finally {
            httpClient.close();
        }
        return false;
    }

    private String getResultContent(HttpResponse response) throws IOException {
        String result = null;
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
            try {
                StringBuilder str = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    str.append(line);
                }
                result = str.toString();
            } finally {
                reader.close();
            }
        }
        return result;
    }

    private URI getFullUrl(String params) throws URISyntaxException {
        return new URI(url + prefix + (params == null ? "" : params));
    }

    private boolean getStatusRequest(int statusCode) {
        return (statusCode == HTTP_200_OK);
    }
}
