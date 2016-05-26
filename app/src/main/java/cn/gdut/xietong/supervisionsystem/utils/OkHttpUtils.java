package cn.gdut.xietong.supervisionsystem.utils;

import android.content.Context;
import android.util.Log;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**操作OkHttp的工具类
 * Created by mr.deng on 2016/4/24.
 */
public class OkHttpUtils {

    private static OkHttpClient okHttpClient = null;
    private static OkHttpUtils okHttpUtils = null;

    private OkHttpUtils(Context context){

        okHttpClient = getOkHttpClient();

        //开启响应缓存
        okHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
        //设置缓存目录和大小
        int cacheSize = 10 << 20;//10Mib
        Cache cache =new Cache(context.getCacheDir(),cacheSize);
        okHttpClient.setCache(cache);

        //设置合理的超时
        okHttpClient.setConnectTimeout(15, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(20,TimeUnit.SECONDS);
        okHttpClient.setWriteTimeout(20,TimeUnit.SECONDS);

    }

    public static OkHttpUtils getOkHttpUtils(Context context) {
        if(okHttpUtils == null) {
            synchronized (OkHttpUtils.class) {
                if(okHttpUtils == null){
                    okHttpUtils = new OkHttpUtils(context);
                }
            }
        }
        return okHttpUtils;
    }

    public static OkHttpClient getOkHttpClient() {
        if(okHttpClient == null){
            synchronized (OkHttpClient.class){
                okHttpClient = new OkHttpClient();
            }
        }
        return okHttpClient;
    }

    /**
     * GET方式请求网络，获取Request请求对象
     * @param urlString 网络地址
     * @param tag 为请求设置标记，关闭请求时使用
     * @return 请求对象
     */
    private  Request buildGetRequest(String urlString, Object tag){
        Request.Builder builder = new Request.Builder();
        builder.url(urlString);
        if(tag != null){
            builder.tag(tag);
        }

        return builder.build();
    }

    /**
     * 获取Response对象
     * @param urlString 网络地址
     * @param tag 标识
     * @return 响应
     * @throws IOException
     */
    private  Response buildResponse(String urlString, Object tag) throws IOException {
        Request request = buildGetRequest(urlString,tag);
        Response response = okHttpClient.newCall(request).execute();
        return response;
    }

    /**
     * 获取ResponseBody对象
     * @param urlString 网络地址
     * @param tag 标识
     * @return 响应内容
     * @throws IOException
     */
    private  ResponseBody buildResponseBody(String urlString, Object tag) throws IOException {
        Response response  = buildResponse(urlString,tag);
        if(response.isSuccessful()) {
            return response.body();
        }
        return null;
    }

    /**
     * GET方式请求网络，获取字符串
     * @param context
     * @param urlString
     * @param tag
     * @return
     * @throws IOException
     */
    public static String getStringFromUrl(Context context,String urlString,Object tag) throws IOException {
        ResponseBody responseBody = getOkHttpUtils(context).buildResponseBody(urlString,tag);
        if(responseBody != null){
            return responseBody.string();
        }
        return null;
    }

    /**
     *  GET方式请求网络，获取字节数组
     * @param context
     * @param urlString
     * @param tag
     * @return
     * @throws IOException
     */
    public static byte[] getByteFromUrl(Context context,String urlString,Object tag) throws IOException {
        ResponseBody responseBody = getOkHttpUtils(context).buildResponseBody(urlString,tag);
        if(responseBody != null){
            return responseBody.bytes();
        }
        return null;
    }

    /**
     * GET方式请求网络，获取输入流
     * @param context
     * @param urlString
     * @param tag
     * @return
     * @throws IOException
     */
    public static InputStream getStreamFromUrl(Context context,String urlString,Object tag) throws IOException {
        ResponseBody responseBody = getOkHttpUtils(context).buildResponseBody(urlString,tag);
        if(responseBody != null){
            return responseBody.byteStream();
        }
        return null;
    }


    /**
     * GET方式异步请求网络
     * @param context
     * @param urlString
     * @param callback
     * @param tag
     */
    public static void getDataAsync(Context context,String urlString , Callback callback, Object tag){
        Request request = getOkHttpUtils(context).buildGetRequest(urlString,tag);
        Log.i("info","request"+request);
        okHttpClient.newCall(request).enqueue(callback);
    }

    /**
     * POST方式请求网络,构建Request对象
     * @param urlString
     * @param requestBody
     * @param tag
     * @return
     */
    private  Request buildPostRequest(String urlString, RequestBody requestBody,Object tag){
        Request.Builder builder = new Request.Builder();
        builder.url(urlString);
        builder.post(requestBody);
        if(tag != null){
         builder.tag(tag);
        }

        return builder.build();
    }

    /**
     * 提交请求体获得响应
     * @param urlString
     * @param requestBody
     * @param tag
     * @return
     * @throws IOException
     */
    private  String postRequestBody(String urlString,RequestBody requestBody,Object tag) throws IOException {
        Request request = buildPostRequest(urlString,requestBody,tag);
        Response response = okHttpClient.newCall(request).execute();
        if(response.isSuccessful()){
            return response.body().string();
        }
        return null;
    }

    /**
     *
     * @param map
     * @return
     */
    private  RequestBody buildRequestBody(Map<String,String> map){
        FormEncodingBuilder builder = new FormEncodingBuilder();
        if(map != null && !map.isEmpty()){
            for (Map.Entry<String,String> entry:map.entrySet()){
                builder.add(entry.getKey(),entry.getValue());
            }
        }
        return builder.build();
    }

    /**
     * POST网络访问提交键值对
     * @param context
     * @param urlString
     * @param map
     * @param tag
     * @return
     * @throws IOException
     */
    public static String postKeyValuePair(Context context,String urlString,Map<String,String> map,Object tag) throws IOException {
        RequestBody requestBody = getOkHttpUtils(context).buildRequestBody(map);
        return getOkHttpUtils(context).postRequestBody(urlString,requestBody,tag);
    }

    /**
     * POST同步上传文件以及它的表单控件
     * @param urlString 网络地址
     * @param map 提交给服务器的表单信息键值对
     * @param files 提交的文件
     * @param formFileNames 每个需要提交的文件对应的input的name值
     *@param tag 标识
     * @return
     * @throws IOException
     */
    public static String postUploadFile(Context context,String urlString, Map<String,String> map, File[] files,
                                        String[] formFileNames,Object tag) throws IOException {
            RequestBody requestBody = null;
            return getOkHttpUtils(context).postRequestBody(urlString,requestBody,tag);
    }

    /**
     * POST方式异步请求网络,提交RequestBody
     * @param urlString
     * @param requestBody
     * @param callback
     * @param tag
     */
    private  void postRequestBodyAsync(String urlString,RequestBody requestBody,Callback callback,Object tag){
        Request request = buildPostRequest(urlString, requestBody,tag);
        okHttpClient.newCall(request).enqueue(callback);
    }

    /**
     * POST方式异步提交键值对
     * @param context
     * @param urlString
     * @param map
     * @param callback
     * @param tag
     */
    public static void postKeyValuePairAsync(Context context,String urlString,Map<String,String> map,
                                             Callback callback,Object tag){
        RequestBody requestBody = getOkHttpUtils(context).buildRequestBody(map);
        getOkHttpUtils(context).postRequestBodyAsync(urlString,requestBody,callback,tag);
    }

}
