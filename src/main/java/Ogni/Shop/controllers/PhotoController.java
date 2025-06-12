package Ogni.Shop.controllers;

import Ogni.Shop.models.Photo;
import Ogni.Shop.models.Product;
import Ogni.Shop.services.PhotoService;
import Ogni.Shop.services.ProductService;
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
    @Autowired
    private ProductService productService;

    @GetMapping("/product/{id}/photo")
    public String editPhotos(@PathVariable Long id, Model model){
        List<Photo> photos = photoService.getPhotosByProductId(id);
        model.addAttribute("photos", photos);
        model.addAttribute("mainPhoto", productService.getById(id).getMainPhoto());
        model.addAttribute("productId", id);
        return "admin/product/edit-photos";
    }

    @PostMapping("/product/{id}/main-photo")
    public String updateMainPhoto(@PathVariable Long id,
                                  @RequestParam("mainPhoto") MultipartFile file,
                                  @RequestParam("oldMainPhoto") Long oldPhoto) {
        Photo newPhoto = photoService.saveMainPhoto(id, file);
        Product product = productService.getById(id);
        product.setMainPhoto(newPhoto);
        productService.update(product);
//        if (oldPhoto!= -1){
//            photoService.deletePhotoById(oldPhoto);
//        }

        return "redirect:/admin/product/" + id + "/photo"; // или куда ты ведёшь
    }

    @PostMapping("/product/{id}/photo")
    public String updatePhotos(@PathVariable Long id,
                               @RequestParam("files") List<MultipartFile> files) {
        photoService.savePhotos(id, files);
        return "redirect:/admin/product/" + id + "/photo";
    }

    @PostMapping("/photo/{id}/delete")
    public String deletePhoto(@PathVariable Long id,
                              @RequestParam("productId") Long productId) {
        photoService.deletePhotoById(id);
        return "redirect:/admin/product/" + productId + "/photo";
    }

}
