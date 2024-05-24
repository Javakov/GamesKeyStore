package org.javakov.gameskeystore.service.firebase;

import com.google.cloud.storage.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.javakov.gameskeystore.config.FirebaseConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class FirebaseServiceImpl implements FirebaseService {
    @Value("${firebase.storage.bucket-name}")
    private String BUCKET_NAME;

    @Value("${firebase.storage.default.image}")
    private String IMAGE_NAME;

    private final Storage storage;

    public FirebaseServiceImpl() throws IOException {
        FirebaseConfig config = new FirebaseConfig();
        storage = config.getStorage();
    }

    @Override
    @Transactional
    public String uploadImg(MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                String fileName = UUID.randomUUID() + "-" + Objects.requireNonNull(file.getOriginalFilename()).replaceAll("\\s+", "");
                Path path = Paths.get(fileName);
                Files.copy(file.getInputStream(), path);
                BufferedImage originalImage = ImageIO.read(path.toFile());
                ImageIO.write(originalImage, "png", path.toFile());

                BlobId blobId = BlobId.of(BUCKET_NAME, "img/" + fileName);
                BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();
                Blob blob = storage.create(blobInfo, Files.readAllBytes(path));
                Files.delete(path);

                return "https://firebasestorage.googleapis.com/v0/b/" + blob.getBucket() + "/o/" +
                        encodeURIComponent(blob.getName()) + "?alt=media&token=" + UUID.randomUUID();
            }
            else {
                return IMAGE_NAME;
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image", e);
        }
    }

    @Override
    @Transactional
    public String updateImg(String id, MultipartFile file) {
        deleteImg(id);
        return uploadImg(file);
    }

    @Override
    @Transactional
    public void deleteImg(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("%") + 3, fileUrl.indexOf("?"));
        Bucket bucket = storage.get(BUCKET_NAME);
        Blob blob = bucket.get("img/" + fileName);
        if (blob != null) {
            blob.delete();
        }
    }

    private static String encodeURIComponent(String s) {
        String result;
        result = URLEncoder.encode(s, StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20")
                .replaceAll("%21", "!")
                .replaceAll("%27", "'")
                .replaceAll("%28", "(")
                .replaceAll("%29", ")")
                .replaceAll("%7E", "~");
        return result;
    }
}
