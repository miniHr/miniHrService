package com.miniHr.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class HttpHelper {

    private static final String GET_METHOD = "GET";

    private static final String POST_METHOD = "POST";

    private HttpHelper() {

    }

    /**
     * Get request
     * @param url 
     * @param parameters 
     * @return responseBody
     */
    public static String get(String url, Map<String, Object> parameters) {

        url = url + "?" + parseParameters(parameters);

        HttpURLConnection connection = null;

        try {
            URL httpUrl = new URL(url);
            connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setRequestMethod(GET_METHOD);

            setRequestProperty(connection);

            connection.connect();

            return receiveResponse(connection);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return null;
    }

    /**
     * Post request
     * @param url 
     * @param parameters 
     * @return responseBody
     */
    public static String post(String url, Map<String, Object> parameters) {

        HttpURLConnection connection = null;
        BufferedWriter writer = null;

        try {
            URL httpUrl = new URL(url);
            connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setRequestMethod(POST_METHOD);

            setRequestProperty(connection);

            writer = new BufferedWriter(
                    new OutputStreamWriter(connection.getOutputStream()));
            writer.write(parseParameters(parameters));
            writer.flush();

            return receiveResponse(connection);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }

        return null;
    }

    /**
     * Set Request Properties
     * @param connection HttpConnection
     */
    private static void setRequestProperty(HttpURLConnection connection) {
        connection.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        connection.setRequestProperty("Connection", "keep-alive");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 " +
                "(Windows NT 6.1; WOW64) AppleWebKit/537.36 " +
                "(KHTML, like Gecko) Chrome/47.0.2526.111 Safari/537.36");
        if (POST_METHOD.equals(connection.getRequestMethod())) {
            connection.setDoOutput(true);
        }
        connection.setDoInput(true);
    }

    /**
     * Parse parameters
     * @param parameters 
     * @return parsed parameters
     */
    private static String parseParameters(Map<String, Object> parameters) {
        if (parameters == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        Set<Map.Entry<String, Object>> set = parameters.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = set.iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            result.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }

        return result.substring(0, result.lastIndexOf("&"));
    }

    /**
     * Read input stream
     * @param connection Http Connection
     * @return response body
     * @throws IOException
     */
    private static String receiveResponse(HttpURLConnection connection) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "UTF-8"));
        StringBuilder receivedContent = new StringBuilder(1024);
        String line = "";
        while((line = reader.readLine()) != null) {
            receivedContent.append(line);
        }
        reader.close();
        return receivedContent.toString();
    }

}
