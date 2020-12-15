package com.vkopendoh.questerapp.frontend.clients;

import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @author Vladimir Kopendoh
 */

public abstract class Client {


    Client() {

    }

    Client(String gatewayPath){

    }

}
