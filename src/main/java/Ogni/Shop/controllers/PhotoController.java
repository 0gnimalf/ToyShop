package Ogni.Shop.controllers;

import Ogni.Shop.models.Photo;
import Ogni.Shop.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    @GetMapping("/product/{id}/upload")
    public String editPhotos(@PathVariable Long id, Model model){
        List<Photo> photos = photoService.getPhotosByProductId(id);
        model.addAttribute("photos", photos);
        model.addAttribute("productId", id);



        return "admin/product/edit-photos";
    }

    @PostMapping("/product/{id}/upload")
    public String updatePhotos(@PathVariable Long id,
                               @RequestParam("files") List<MultipartFile> files) {
        photoService.savePhotos(id, files);
        return "redirect:/admin/product/" + id + "/edit";
    }

    @PostMapping("/photo/{id}/delete")
    public String deletePhoto(@PathVariable Long id,
                              @RequestParam("productId") Long productId) {
        photoService.deletePhoto(id);
        return "redirect:/admin/product/" + productId + "/edit";
    }

}
