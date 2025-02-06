package Ogni.Shop.services;

import Ogni.Shop.models.Photo;
import Ogni.Shop.repositories.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
