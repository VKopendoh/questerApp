package com.vkopendoh.questerapp.frontend.clients;

import com.vkopendoh.questerapp.frontend.ui.models.UserModel;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vladimir Kopendoh
 */
@Service
public class UserClient extends Client {
    private final Environment env;
    private final String gatewayPath;
    private final RestTemplate restTemplate;

    UserClient(Environment env, RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        this.env = env;
        this.gatewayPath = env.getProperty("quester.gateway.url");
    }

    public List<UserModel> findAll() {
        final String usersUrl = gatewayPath + env.getProperty("quester.users.url");
        ResponseEntity<List<UserModel>> albumsListResponse = restTemplate.exchange(usersUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<UserModel>>() {
        });
        List<UserModel> users = albumsListResponse.getBody();
        return users;
    }

    public List<UserModel> findAll(String filter) {
        List<UserModel> users = findAll();
        return users.stream().filter(user -> user.getFirstName().contains(filter) || user.getLastName().contains(filter)).collect(Collectors.toList());
    }


    public void delete(UserModel userModel) {

    }

    public void save(UserModel userModel) {

    }


    //commented using RestTemplate and instead this use Feign client just this: albumsServiceClient.getAlbums(userId) !!! INSTEAD ALL boilerplate below
      /*  String albumsUrl = String.format(environment.getProperty("albums.url"),userId);
        ResponseEntity<List<AlbumResponseModel>> albumsListResponse = restTemplate.exchange(albumsUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<AlbumResponseModel>>() {
        });

        List<AlbumResponseModel> albumsList = albumsListResponse.getBody();*/

}
