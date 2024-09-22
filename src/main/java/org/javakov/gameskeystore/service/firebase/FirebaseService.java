package org.javakov.gameskeystore.service.firebase;

import org.springframework.web.multipart.MultipartFile;

public interface FirebaseService {
    String uploadImg(MultipartFile file);
    String updateImg(String id, MultipartFile file);
    void deleteImg(String id);
}