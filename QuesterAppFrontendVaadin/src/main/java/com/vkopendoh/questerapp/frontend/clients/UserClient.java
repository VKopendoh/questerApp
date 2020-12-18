package com.vkopendoh.questerapp.frontend.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vkopendoh.questerapp.frontend.ui.models.QuestModel;
import com.vkopendoh.questerapp.frontend.ui.models.UserCreateModel;
import com.vkopendoh.questerapp.frontend.ui.models.UserLoginModel;
import com.vkopendoh.questerapp.frontend.ui.models.UserModel;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vladimir Kopendoh
 */
@Service
public class UserClient {
    private final Environment env;
    private final String gatewayPath;
    private final RestTemplate restTemplate;

    public UserClient(Environment env, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.env = env;
        this.gatewayPath = env.getProperty("quester.gateway.url");
    }

    public List<UserModel> findAll() {
        final String usersUrl = gatewayPath + env.getProperty("quester.users.url");
        ResponseEntity<List<UserModel>> usersResponse = restTemplate.exchange(usersUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<UserModel>>() {
        });
        List<UserModel> users = usersResponse.getBody();
        return users;
    }

    public List<UserModel> findAll(String filter) {
        List<UserModel> users = findAll();
        return users.stream().filter(user -> user.getFirstName().contains(filter) || user.getLastName().contains(filter)).collect(Collectors.toList());
    }


    public void delete(UserModel userModel) {

    }



    public UserModel save(UserCreateModel user) {
        ResponseEntity<UserModel> usersResponse = restTemplate.postForEntity(env.getProperty("usersUrl"), user, UserModel.class);
        return usersResponse.getBody();
    }

    public HttpHeaders login(UserLoginModel userCreds){

        HttpHeaders headers = restTemplate.execute(
                env.getProperty("usersUrl") + "/login",
                HttpMethod.POST,
                requestCallback(userCreds),
                HttpMessage::getHeaders);
        return headers;
    }

    public void deleteQuest(QuestModel quest) {
    }

    RequestCallback requestCallback(final UserLoginModel userLoginModel) {
        return clientHttpRequest -> {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(clientHttpRequest.getBody(), userLoginModel);
            clientHttpRequest.getHeaders().add(
                    HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            clientHttpRequest.getHeaders().add(
                    HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        };
    }

    public List<QuestModel> findAllQuestsByUserId(String id) {


        final String questUrl = "http://quest-ws/user/"+id+"/quest";
        ResponseEntity<List<QuestModel>> usersResponse = restTemplate.exchange(questUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<QuestModel>>() {
        });
        List<QuestModel> quests = usersResponse.getBody();
        return quests;
    }
}
