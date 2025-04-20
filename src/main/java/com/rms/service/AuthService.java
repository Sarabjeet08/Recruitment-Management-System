package com.rms.service;

import com.rms.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.io.File;
import java.io.IOException;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public boolean validateUser(String email, String password) {
        UserEntity user = userRepository.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void registerUser(String name, String email, String password, String role, String resume) {
        UserEntity user = new UserEntity(name, email, password, role, resume);
        userRepository.save(user);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
    
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }
    
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
    public boolean changePassword(String email, String oldPass, String newPass) {
    UserEntity user = userRepository.findByEmail(email);
    if (user != null && user.getPassword().equals(oldPass)) {
        user.setPassword(newPass);
        userRepository.save(user);
        return true;
    }
    return false;
}

public boolean updateRecruiterProfile(String email, String username, String oldPass, String newPass) {
    UserEntity user = userRepository.findByEmail(email);
    if (user != null && user.getPassword().equals(oldPass)) {
        user.setName(username);
        user.setPassword(newPass);
        userRepository.save(user);
        return true;
    }
    return false;
}

public boolean updateApplicantProfile(String email, String username, String oldPass, String newPass, MultipartFile resumeFile) {
    try {
        UserEntity user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(oldPass)) {
            user.setName(username);
            user.setPassword(newPass);

            if (!resumeFile.isEmpty()) {
                String fileName = resumeFile.getOriginalFilename();
                if (fileName.endsWith(".pdf") || fileName.endsWith(".doc") || fileName.endsWith(".docx")) {
                    // ✅ Create resume folder if not exists
                    File dir = new File("uploads/resumes/");
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }

                    String uploadPath = "uploads/resumes/" + System.currentTimeMillis() + "_" + fileName;
                    resumeFile.transferTo(new File(uploadPath));
                    user.setResume("/" + uploadPath);
                }
            }

            userRepository.save(user);
            return true;
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return false;
}

}
