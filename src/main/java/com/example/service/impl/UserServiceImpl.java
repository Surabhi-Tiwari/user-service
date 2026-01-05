package com.example.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.dto.Hotel;
import com.example.entity.Ratings;
import com.example.entity.User;
import com.example.exception.ResourceNotFoundException;

import com.example.repository.UserRepository;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    //create
    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    //get alll user
    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    //get byy idd
    @Override
    public User getByIdUser(String userId) {

        //Get user
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + userId));

        // Call Rating Service
        
        String ratingUrl = "http://RATING-SERVICE/ratings/users/" + userId;
        Ratings[] ratingsArray = restTemplate.getForObject(ratingUrl, Ratings[].class);

        List<Ratings> ratingsList = Arrays.asList(ratingsArray);

        // Call Hotel Service for each rating
        
        ratingsList.forEach(rating -> {

            String hotelUrl = "http://HOTEL-SERVICE/hotels/get/" + rating.getHotelId();
            Hotel hotel = restTemplate.getForObject(hotelUrl, Hotel.class);

            rating.setHotel(hotel); 
        });

        // Set ratings to user
        user.setRatings(ratingsList);

        return user;
    }

    //delete
    @Override
    public void deleteByUserId(String userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
        userRepository.deleteById(userId);
    }

    //update
    @Override
    public User updateUser(String userId, User user) {
        User oldUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setAbout(user.getAbout());

        return userRepository.save(oldUser);
    }
}
