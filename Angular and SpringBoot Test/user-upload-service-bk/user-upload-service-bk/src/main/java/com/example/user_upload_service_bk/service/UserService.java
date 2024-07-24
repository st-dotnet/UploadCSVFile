package com.example.user_upload_service_bk.service;

import com.example.user_upload_service_bk.model.User;
import com.example.user_upload_service_bk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUsersFromCsv(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            List<User> users = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                User user = new User();
                user.setName(data[0]);
                user.setEmail(data[1]);
                user.setAge(Integer.parseInt(data[2]));
                users.add(user);
            }
            userRepository.saveAll(users);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while processing CSV file", e);
        }
    }
}
