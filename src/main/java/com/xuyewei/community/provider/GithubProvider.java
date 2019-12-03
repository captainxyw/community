package com.xuyewei.community.provider;

import com.alibaba.fastjson.JSON;
import com.xuyewei.community.dto.AccessTokenDTO;
import com.xuyewei.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * ClassName:GithubProvider
 * Package:com.xuyewei.community.provider
 * Description:
 *
 * @Date:2019/12/3 19:32
 * @Author:xuyewei
 */
@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accesstoken) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accesstoken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch(IOException e) {
        }
    return null;
    }

}