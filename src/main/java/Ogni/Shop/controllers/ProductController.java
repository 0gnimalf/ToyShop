package Ogni.Shop.controllers;

import Ogni.Shop.models.Product;
import Ogni.Shop.models.ProductType;
import Ogni.Shop.services.PhotoService;
import Ogni.Shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
    private final int pageSize = 16;
    @Autowired
    ProductService productService;
    @Autowired
    PhotoService photoService;

    private void addPageToModel(Model model, Page<Product> list,
                                int currentPage, String keyword,
                                String pageTitle, String link) {
        if (!list.isEmpty()) {
            model.addAttribute("list", list);
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("totalPages", list.getTotalPages());
        } else {
            model.addAttribute("list", Page.empty());
        }
        model.addAttribute("keyword", keyword);

        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("link", link);
    }


    @GetMapping("/soft-toys")
    public String getToys(Model model,
                          @RequestParam(name = "page", defaultValue = "1") int page,
                          @RequestParam(name = "keyword", required = false) String keyword) {
        Page<Product> list = productService.getByType(ProductType.toy, PageRequest.of(page-1, pageSize));
        addPageToModel(model, list, page, keyword, "Мягкие игрушки", "soft-toys");
        return "showroom";
    }
    @GetMapping("/keychains")
    public String getKeychains(Model model,
                               @RequestParam(name = "page", defaultValue = "1") int page,
                               @RequestParam(name = "keyword", required = false) String keyword) {
        Page<Product> list = productService.getByType(ProductType.keychain, PageRequest.of(page - 1, pageSize));
        addPageToModel(model, list, page, keyword, "Брелоки", "keychains");
        return "showroom";
    }

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable Long id, Model model) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        model.addAttribute("variants", productService.getByGroup(product.getGroup()));
        model.addAttribute("photoPaths", photoService.getPathsByProductId(id));
        model.addAttribute("pageTitle", product.getName());
        return "product";
    }
}
