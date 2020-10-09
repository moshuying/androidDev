package com.example.moshuying.network;

import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.moshuying.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static okhttp3.MediaType.*;

/**
 * A product entry in the list of products.
 */
public class ProductEntry {
    private static final String TAG = ProductEntry.class.getSimpleName();

    public final String title;
    public final Uri dynamicUrl;
    public final String url;
    public final String price;
    public final String description;

    public ProductEntry(
            String title, String dynamicUrl, String url, String price, String description) {
        this.title = title;
        this.dynamicUrl = Uri.parse(dynamicUrl);
        this.url = url;
        this.price = price;
        this.description = description;
    }

    /**
     * Loads a raw JSON at R.raw.products and converts it into a list of ProductEntry objects
     */
    public static List<ProductEntry> initProductEntryList(Resources resources) {
        // https://exampleapp.moshuying.top/node_api/product_list
//        InputStream inputStream = resources.openRawResource(R.raw.products);
//        Writer writer = new StringWriter();
//        char[] buffer = new char[1024];
//        try {
//            Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//            int pointer;
//            while ((pointer = reader.read(buffer)) != -1) {
//                writer.write(buffer, 0, pointer);
//            }
//        } catch (IOException exception) {
//            Log.e(TAG, "Error writing/reading from the JSON file.", exception);
//        } finally {
//            try {
//                inputStream.close();
//            } catch (IOException exception) {
//                Log.e(TAG, "Error closing the input stream.", exception);
//            }
//        }
//        String jsonProductsString = writer.toString();//
        String jsonProductsString = sendByOKHttp();
        Gson gson = new Gson();
        Type productListType = new TypeToken<ArrayList<ProductEntry>>(){}.getType();
        return gson.fromJson(jsonProductsString, productListType);
    }
    public static String sendByOKHttp() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        HttpGetRequest httpGetRequest = new HttpGetRequest();
        httpGetRequest.url = "https://exampleapp.moshuying.top/node_api/product_list";
        Future<String> submit = executorService.submit(httpGetRequest);
        String data = "";
        try {
            data = submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(data);
        LinkedHashMap<String,String> jsonMap = JSON.parseObject(data,new TypeReference<LinkedHashMap<String, String>>(){});
        return jsonMap.get("data");
    }
}

class HttpGetRequest implements Callable<String> {
    public String url = null;
    @Override
    public String call() {
        String returnValue = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();//发送请求
            returnValue = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnValue;
    }
}

class HttpPostRequest implements Callable<String>{
    public String url = null;
    public String json = null;
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    @Override
    public String call(){
        String returnValue = null;
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder().url(url).post(body).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            returnValue = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnValue;
    }
}