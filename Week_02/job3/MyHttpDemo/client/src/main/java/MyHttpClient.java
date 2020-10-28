import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

public class MyHttpClient {
    private static Logger logger = LoggerFactory.getLogger(MyHttpClient.class);
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String testUrl = "http://localhost:8801/test";
        try {
            Request request = new Request.Builder().url(testUrl).build();
            Response response = client.newCall(request).execute();
            ResponseBody res = response.body();
            if(res != null){
                System.out.println(res.string());
            }
           else{
                System.out.println("请求响应为空");
            }
        }catch (Exception e){
            logger.error("请求" +  testUrl + "失败: " + e);

        } finally {
            client.clone();
        }
    }
}
