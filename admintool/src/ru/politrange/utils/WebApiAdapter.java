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

import org.json.simple.JSONObject;

/**
 * Created by developermsv on 08.12.2015.
 * в конструкторе, selectPrefix например "/api/stats/" или /api/persons
 * метод select, param например "1?begin=11.11.11&end=12.11.11" или просто "1"
 * метод delete, param например "1" или "2"
 */
public class WebApiAdapter {
    public static final int HTTP_200_OK = 200;
    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final String CONTENT_TYPE_JSON = "application/json; charset=" + DEFAULT_CHARSET;
    public static final String TITLE_CONNECT_TEXT = "Настройка соединения...";
    public static final String ERROR_CONNECT_TEXT = "Потеряно соединение с сервисом, введите \n новый адрес или нажмите \"Отмена\"";
    public static final String HTTP_LOCALHOST_10101 = "http://localhost:10101";
    public static final String FILE_ADMINTOOL_INI = "admintool.ini";

    private String url;
    private String selectPrefix;
    private String updatePrefix;

    public WebApiAdapter(String selectPrefix) {
        this.selectPrefix = selectPrefix;
    }

    public WebApiAdapter(String selectPrefix, String updatePrefix) {
        this.selectPrefix = selectPrefix;
        this.updatePrefix = updatePrefix;
    }

    public String getUrl() {
        if (url == null) {
            url = loadHttpAddressFromFile();
            if (url == null) {
                url = HTTP_LOCALHOST_10101;
            }
        }
        return url;
    }

    private String loadHttpAddressFromFile() {
        String result = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(FILE_ADMINTOOL_INI));
            result = reader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void setUrl(String url) {
        saveHttpAddressToFile(url);
        this.url = url;
    }

    private void saveHttpAddressToFile(String url) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_ADMINTOOL_INI);
            fileWriter.write(url);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean enterNewConnectionString() {
        String newUrl = DialogManager.showInputDialog(url, TITLE_CONNECT_TEXT, ERROR_CONNECT_TEXT);
        if (newUrl != null) {
            setUrl(newUrl);
            return true;
        }
        return false;
    }

    public String select(String param) throws IOException {
        String result = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = null;
        HttpResponse response = null;
        try {
            request = new HttpGet(getFullUrl(param));
            response = httpClient.execute(request);
            if (getStatusRequest(response.getStatusLine().getStatusCode())) {
                result = getResultContent(response);
            } else if (enterNewConnectionString()) {
                result = select(param);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            if (enterNewConnectionString()) {
                result = select(param);
            }
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
            StringEntity params = new StringEntity(json.toString(), DEFAULT_CHARSET);
            params.setContentType(CONTENT_TYPE_JSON);
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            if (getStatusRequest(response.getStatusLine().getStatusCode())) {
                result = getResultContent(response);
            } else if (enterNewConnectionString()) {
                result = insert(json);
            }
            return result;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            if (enterNewConnectionString()) {
                result = insert(json);
            }
        } finally {
            httpClient.close();
        }
        return result;
    }

    public boolean update(JSONObject json, String param) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPut request = null;
        try {
            request = new HttpPut(getFullUpdateUrl(param));
            StringEntity params = new StringEntity(json.toString(), DEFAULT_CHARSET);
            params.setContentType(CONTENT_TYPE_JSON);
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            if  (getStatusRequest(response.getStatusLine().getStatusCode())) {
                return true;
            } else if (enterNewConnectionString()) {
                return update(json, param);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            if (enterNewConnectionString()) {
                return update(json, param);
            }
        } finally {
            httpClient.close();
        }
        return false;
    }

    public boolean delete(String param) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpDelete request = new HttpDelete(getFullUpdateUrl(param));
            HttpResponse response = httpClient.execute(request);
            if (getStatusRequest(response.getStatusLine().getStatusCode())) {
                return true;
            } else if (enterNewConnectionString()) {
                return delete(param);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            if (enterNewConnectionString()) {
                return delete(param);
            }
        } finally {
            httpClient.close();
        }
        return false;
    }

    private String getResultContent(HttpResponse response) throws IOException {
        String result = null;
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), DEFAULT_CHARSET));
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
        return new URI(getUrl() + selectPrefix + (params == null ? "" : params));
    }

    private URI getFullUpdateUrl(String params) throws URISyntaxException {
        return new URI(getUrl() + (updatePrefix == null ? selectPrefix : updatePrefix) + (params == null ? "" : params));
    }

    private boolean getStatusRequest(int statusCode) {
        return (statusCode == HTTP_200_OK);
    }
}
