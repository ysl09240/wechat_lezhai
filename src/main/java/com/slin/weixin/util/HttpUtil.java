package com.slin.weixin.util;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

/**
 * @created on   :  2015-04-23
 * @copyright :  Copyright(c) 2013
 * @description :
 */
public class HttpUtil {

    private static final String DEFAULT_CHARSET = "UTF-8";
    /**
     * get 请求
     *
     * @param url
     * @param params
     * @param headers
     * @return
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static String get(String url, Map<String, String> params, Map<String, String> headers) throws IOException, ExecutionException, InterruptedException {
        AsyncHttpClient http = new AsyncHttpClient();
        AsyncHttpClient.BoundRequestBuilder builder = http.prepareGet(url);
        builder.setBodyEncoding(DEFAULT_CHARSET);
        if (params != null && !params.isEmpty()) {
            Set<String> keys = params.keySet();
            for (String key : keys) {
                builder.addQueryParameter(key, params.get(key));
            }
        }

        if (headers != null && !headers.isEmpty()) {
            Set<String> keys = headers.keySet();
            for (String key : keys) {
                builder.addHeader(key, params.get(key));
            }
        }
        Future<Response> f = builder.execute();
        String body = f.get().getResponseBody(DEFAULT_CHARSET);
        http.close();
        return body;
    }

    /**
     * get 请求
     *
     * @param url
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws UnsupportedEncodingException
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static String get(String url) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, IOException, ExecutionException, InterruptedException {
        return get(url, null);
    }

    /**
     *
     * @param url
     * @param params
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws UnsupportedEncodingException
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static String get(String url, Map<String, String> params) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, IOException, ExecutionException, InterruptedException {
        return get(url, params, null);
    }

    /**
     * post 请求
     * @param url
     * @param params
     * @return
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static String post(String url, Map<String, String> params) throws IOException, ExecutionException, InterruptedException {
        AsyncHttpClient http = new AsyncHttpClient();
        AsyncHttpClient.BoundRequestBuilder builder = http.preparePost(url);
        builder.setBodyEncoding(DEFAULT_CHARSET);
        if (params != null && !params.isEmpty()) {
            Set<String> keys = params.keySet();
            for (String key : keys) {
                builder.addParameter(key, params.get(key));
            }
        }
        Future<Response> f = builder.execute();
        String body = f.get().getResponseBody(DEFAULT_CHARSET);
        http.close();
        return body;
    }

    /**
     * post 请求
     * @param url
     * @param s
     * @return
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static String post(String url, String s) throws IOException, ExecutionException, InterruptedException {
        AsyncHttpClient http = new AsyncHttpClient();
        AsyncHttpClient.BoundRequestBuilder builder = http.preparePost(url);
        builder.setBodyEncoding(DEFAULT_CHARSET);
        builder.setBody(s);
        Future<Response> f = builder.execute();
        String body = f.get().getResponseBody(DEFAULT_CHARSET);
        http.close();
        return body;
    }
}
