package com.nexus.apidemo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import static com.nexus.apidemo.common.Consts.HOST;

public class RestServiceImpl {

    private static final String PREFIX = "https://" + HOST;
    private static final ObjectMapper mapper = new ObjectMapper();

    @SuppressWarnings("rawtypes")
    public String getToken(String loginId, String password) throws IOException {
        String json = this.login(loginId, password);
        Map readValue = mapper.readValue(json, Map.class);
        return (String) ((Map) readValue.get("data")).get("token");
    }

    public String login(String loginId, String password) throws IOException {
        URL url = new URL(PREFIX + "/api/v1/operator/login?loginId=" + loginId + "&password=" + password);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        InputStream inputStream = con.getInputStream();
        return IOUtils.toString(inputStream, "utf8");
    }
}
