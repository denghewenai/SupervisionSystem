package cn.gdut.xietong.supervisionsystem.utils;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**操作OkHttp的工具类
 * Created by mr.deng on 2016/4/24.
 */
public class OkHttpUtils {

    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();


    /**获取Request请求对象
     * @param urlString
     * @return
     */
    private static Request getRequestFromUrl(String urlString){
        Request request = new Request.Builder()
                                     .url(urlString)
                                     .build();
        return request;
    }

    /**获取Response对象
     * @param urlString
     * @return
     * @throws IOException
     */
    private static Response getResponseFromUrl(String urlString) throws IOException {
        Request request = getRequestFromUrl(urlString);
        Response response = OK_HTTP_CLIENT.newCall(request).execute();
        return response;
    }

    /**获取ResponseBody对象
     * @param urlString
     * @return
     * @throws IOException
     */
    private static ResponseBody getResonseBodyFromUrl(String urlString) throws IOException {
        Response response  = getResponseFromUrl(urlString);
        if(response.isSuccessful()) {
            return response.body();
        }
        return null;
    }

    /**
     * 获取字符串
     * @param urlString
     * @return
     * @throws IOException
     */
    public static String loadStringFromUrl(String urlString) throws IOException {
        ResponseBody responseBody = getResonseBodyFromUrl(urlString);
        if(responseBody != null){
            return responseBody.string();
        }
        return null;
    }

    /**
     * 获取字节数组
     * @param urlString
     * @return
     * @throws IOException
     */
    public static byte[] loadByteFromUrl(String urlString) throws IOException {
        ResponseBody responseBody = getResonseBodyFromUrl(urlString);
        if(responseBody != null){
            return responseBody.bytes();
        }
        return null;
    }

    /**
     * 获取输入流
     * @param urlString
     * @return
     * @throws IOException
     */
    public static InputStream loadStreamFromUrl(String urlString) throws IOException {
        ResponseBody responseBody = getResonseBodyFromUrl(urlString);
        if(responseBody != null){
            return responseBody.byteStream();
        }
        return null;
    }

}
