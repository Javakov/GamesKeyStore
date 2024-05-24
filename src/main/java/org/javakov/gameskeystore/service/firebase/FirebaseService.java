package org.javakov.gameskeystore.service.firebase;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FirebaseService {
    String uploadImg(MultipartFile file);
    String updateImg(String id, MultipartFile file);
    void deleteImg(String id);
}