package com.huayutech.clientdiy.web;



import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;

    protected static Logger logger= LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/login")
    protected String doGet(HttpServletRequest httpServletRequest, @RequestParam(name="code", required = false) String code, @RequestParam(name="state", required = false) String state) {

        StringBuffer stringBuffer = httpServletRequest.getRequestURL();

        if (code != null) {

            try {
                HttpHeaders headers = new HttpHeaders();

                String author = "Basic " + Base64Utils.encodeToString((clientId + ":" + clientSecret).getBytes());

                headers.add("Authorization", "Basic YWNtZTphY21lc2VjcmV0");

                MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
                postParameters.add("grant_type", "authorization_code");
                postParameters.add("code", code);
                postParameters.add("redirect_uri", stringBuffer.toString());


                HttpEntity<MultiValueMap<String, String>> requestEntity  = new HttpEntity<MultiValueMap<String, String>>(postParameters, headers);




                JsonNode result = restTemplate.postForObject("http://localhost:9000/oauth/token", requestEntity, JsonNode.class);

                logger.debug(result.toString());
            }
            catch (RestClientException ex) {
                logger.debug(ex.getMessage());
            }

            return null;
        }



        String redirectUrl = String.format("redirect:http://localhost:9000/oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code&state=nIxYc9", this.clientId, stringBuffer.toString());

        return redirectUrl;

    }
}
