package Ogni.Shop.controllers;

import Ogni.Shop.models.Product;
import Ogni.Shop.models.ProductType;
import Ogni.Shop.services.PhotoService;
import Ogni.Shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
    @Value("${pageSize}")
    private int pageSize;
    @Autowired
    ProductService productService;
    @Autowired
    PhotoService photoService;

    static void addAuthInfoToModel(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); //[ROLE_ANONYMOUS], [ROLE_USER, ROLE_ADMIN]
        model.addAttribute("admin", auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
        model.addAttribute("anonymous", auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS")));
    }

    private void addPageToModel(Model model, Page<Product> list,
                                int currentPage, String keyword,
                                String pageTitle, String link) {
        if (!list.isEmpty()) {
            model.addAttribute("list", list);
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("totalPages", list.getTotalPages());
        } else {
            model.addAttribute("list", Page.empty());
            model.addAttribute("currentPage", 1);
            model.addAttribute("totalPages", 1);
        }
        model.addAttribute("keyword", keyword);

        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("link", link);
        addAuthInfoToModel(model);
    }

    @GetMapping("/")
    public String getAll(Model model,
                         @RequestParam(name = "page", defaultValue = "1") int page,
                         @RequestParam(name = "keyword", required = false) String keyword) {
        Page<Product> list = productService.getAllSpec(keyword, PageRequest.of(page-1, pageSize));
        addPageToModel(model, list, page, keyword, "Каталог", "");
        return "showroom";
    }

    @GetMapping("/soft-toys")
    public String getToys(Model model,
                          @RequestParam(name = "page", defaultValue = "1") int page,
                          @RequestParam(name = "keyword", required = false) String keyword) {
        Page<Product> list = productService.getByTypeSpec(keyword, ProductType.toy, PageRequest.of(page-1, pageSize));
        addPageToModel(model, list, page, keyword, "Мягкие игрушки", "soft-toys");
        return "showroom";
    }
    @GetMapping("/keychains")
    public String getKeychains(Model model,
                               @RequestParam(name = "page", defaultValue = "1") int page,
                               @RequestParam(name = "keyword", required = false) String keyword) {
        Page<Product> list = productService.getByTypeSpec(keyword, ProductType.keychain, PageRequest.of(page - 1, pageSize));
        addPageToModel(model, list, page, keyword, "Брелоки", "keychains");
        return "showroom";
    }
    @GetMapping("/sets")
    public String getSets(Model model,
                          @RequestParam(name = "page", defaultValue = "1") int page,
                          @RequestParam(name = "keyword", required = false) String keyword){
        Page<Product> list = productService.getByTypeSpec(keyword, ProductType.set, PageRequest.of(page - 1, pageSize));
        addPageToModel(model, list, page, keyword, "Наборы", "sets");
        return "showroom";
    }
    @GetMapping("/other")
    public String getOther(Model model,
                          @RequestParam(name = "page", defaultValue = "1") int page,
                          @RequestParam(name = "keyword", required = false) String keyword){
        Page<Product> list = productService.getByTypeSpec(keyword, ProductType.other, PageRequest.of(page - 1, pageSize));
        addPageToModel(model, list, page, keyword, "Другое", "other");
        return "showroom";
    }
    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable Long id, Model model) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        model.addAttribute("variants", productService.getByGroup(product.getGroup()));
        model.addAttribute("photoPaths", photoService.getPathsByProductId(id));
        model.addAttribute("pageTitle", product.getName());
        addAuthInfoToModel(model);
        return "product";
    }
}
