package com.vkopendoh.questerapp.users.service;

import com.vkopendoh.questerapp.users.entity.User;
import com.vkopendoh.questerapp.users.repository.UserRepository;
import com.vkopendoh.questerapp.users.shared.UserDto;
import com.vkopendoh.questerapp.users.utils.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Vladimir Kopendoh
 */
@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    Environment environment;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, Environment environment) {
        this.userRepository = userRepository;
        this.environment = environment;
    }

    @Override
    public UserDto createUser(UserDto userDetails) {
        userDetails.setUserId(UUID.randomUUID().toString());
        userDetails.setEncryptedPassword(userDetails.getPassword());

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        User userEntity = modelMapper.map(userDetails, User.class);

        userRepository.save(userEntity);

        UserDto returnValue = modelMapper.map(userEntity, UserDto.class);

        return returnValue;
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        User userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found with email: " + email));

        return new ModelMapper().map(userEntity, UserDto.class);

    }

    @Override
    public UserDto getUserByUserId(String userId) {
        User userEntity = userRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id" + userId));

        return new ModelMapper().map(userEntity, UserDto.class);
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserDto> users = userRepository.findAll().stream().map(user-> new ModelMapper().map(user,UserDto.class)).collect(Collectors.toList());
        return users;
    }

    @PostConstruct
    public void populateTestData() {
        Stream<User> stream = Stream.of("Maria Young", "Julia Wilson", "Amy Burgess",
                "Angela Davies", "Michael Jones", "James Ferguson",
                "Anthony Dickens", "Heather James", "Joseph	Ogden",
                "Trevor	Lambert", "Simon Paige", "Donna King",
                "Karen Ross", "Pippa Davidson", "William Berry",
                "Robert	Dyer", "Cameron Ellison", "Peter Glover",
                "Brandon McDonald", "Anne Pool")
                .map(name -> {
                    String[] split = name.trim().split("\\s+");
                    User user = new User();
                    if (split[0] != null)
                        user.setFirstName(split[0]);
                    if (split[1] != null)
                        user.setLastName(split[1]);
                    user.setEncryptedPassword("password123");
                    String email = (user.getLastName() == null ? "" : user.getLastName() + "@" + "vkopendoh.com").toLowerCase();
                    user.setEmail(email);
                    user.setUserId(UUID.randomUUID().toString());
                    return user;
                });
        List<User> users = stream.collect(Collectors.toList());
        userRepository.saveAll(users);
    }

}
