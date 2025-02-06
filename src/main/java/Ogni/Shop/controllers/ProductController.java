package Ogni.Shop.controllers;

import Ogni.Shop.models.Product;
import Ogni.Shop.services.PhotoService;
import Ogni.Shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    PhotoService photoService;


    @GetMapping("/soft-toys")
    public String getToys(Model model) {


        return "product/all";
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable Long id, Model model) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        model.addAttribute("variants", productService.getByGroup(product.getGroup()));
        model.addAttribute("photoPaths", photoService.getPathsByProductId(id));
        return "product";
    }
}
