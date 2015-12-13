package com.example.nortti.politrange.utils;



import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.CloseableHttpClient;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;

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
            request = new HttpGet(this.getFullUrl(param));
            CloseableHttpResponse e = httpClient.execute(request);
            if(this.getStatusRequest(e.getStatusLine().getStatusCode())) {
                result = this.getResultContent((HttpResponse) e);
            }
        } catch (URISyntaxException var9) {
            var9.printStackTrace();
        } finally {
            httpClient.close();
        }

        return result;
    }



    private String getResultContent(HttpResponse response) throws IOException {
        String result = null;
        HttpEntity entity = response.getEntity();
        if(entity != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));

            try {
                StringBuilder str = new StringBuilder();

                String line;
                while((line = reader.readLine()) != null) {
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
        return new URI("http://localhost:10101" + this.prefix + (params == null?"":params));
    }

    private boolean getStatusRequest(int statusCode) {
        return statusCode == 200;
    }
}
