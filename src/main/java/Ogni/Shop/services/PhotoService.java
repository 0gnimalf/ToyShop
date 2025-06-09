package Ogni.Shop.services;

import Ogni.Shop.models.Photo;
import Ogni.Shop.repositories.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoService {
    @Autowired
    PhotoRepo photoRepo;
    public List<String> getPathsByProductId(Long productId) {
        return photoRepo.findByProductId(productId).stream()
            .map(Photo::getPath)
            .collect(Collectors.toList());}

    public List<Photo> getPhotosByProductId(Long productId) {
        return photoRepo.findByProductId(productId);
    }

    public void savePhotos(Long productId, List<MultipartFile> files) {
        for (MultipartFile file : files) {
            String filePath = saveFileToDisk(productId, file);

            Photo photo = new Photo();
            photo.setProductId(productId);
            photo.setPath(filePath);
            photoRepo.save(photo);
        }
    }

    public void deletePhoto(Long photoId) {
        Photo photo = photoRepo.findById(photoId).get();
        deleteFileFromDisk(photo.getPath());
        photoRepo.delete(photo);
    }

    private String saveFileToDisk(Long productId, MultipartFile file) {
        String uploadDir = "public";
        File uploadFolder = new File(uploadDir);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }
        String fileName = "p-" + productId+"-" + System.currentTimeMillis() + "-" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);

        try {
            file.transferTo(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сохранении файла", e);
        }
        return "/public/" + fileName;
    }

    void deleteFileFromDisk(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }
}
