package cn.edu.scujcc.duoduo;

import com.squareup.moshi.Moshi;

import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * 使用单例模式创建Retrofit对象。
 */
public class RetrofitClient {
    private static Retrofit INSTANCE = null;

    public static Retrofit getInstance() {
        if (INSTANCE == null) {
            Moshi moshi = new Moshi.Builder()
                    .add(new MyDateAdapter())
                    .build();
            INSTANCE = new Retrofit.Builder()
                    .baseUrl("http://47.112.230.178:8081")  //改为自己的阿里云服务器IP
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build();
        }
        return INSTANCE;
    }
}