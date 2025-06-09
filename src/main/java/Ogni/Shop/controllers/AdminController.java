package Ogni.Shop.controllers;

import Ogni.Shop.models.Material;
import Ogni.Shop.models.Product;
import Ogni.Shop.models.ProductGroup;
import Ogni.Shop.models.ProductType;
import Ogni.Shop.repositories.MaterialRepo;
import Ogni.Shop.repositories.ProductGroupRepo;
import Ogni.Shop.repositories.ProductRepo;
import Ogni.Shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductGroupRepo groupRepo;
    @Autowired
    private MaterialRepo materialRepo;
    @Autowired
    private ProductRepo productRepo;

    private void returnValuesToModel(Model model){
        model.addAttribute("groups", groupRepo.findAll());
        model.addAttribute("materials", materialRepo.findAll());
        model.addAttribute("types", ProductType.values());
    }

    @GetMapping("/product")
    public String products(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "keyword", required = false) String keyword) {
        int pageSize = 10;
//        Page<Product> list = productService.getPageSpec(keyword, (PageRequest.of(page, pageSize)));
        Page<Product> list = productService.getAll(PageRequest.of(page, pageSize));
        if (!list.isEmpty()) {
            model.addAttribute("list", list);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", list.getTotalPages());
        } else {
            model.addAttribute("list", Page.empty());
        }
        model.addAttribute("keyword", keyword);
        return "admin/product/all-products";
    }

    @GetMapping("/product/new")
    public String getNewProduct(Model model, @RequestParam(name = "error", required = false) String error) {
        if (error!=null && error.equals("duplicate")) {
            model.addAttribute("error", "Такой продукт уже существует");
        }
        returnValuesToModel(model);
        model.addAttribute("nProduct", new Product());
        return "admin/product/n-product";
    }
    @PostMapping("/product")
    public String postProduct(@ModelAttribute("nProduct") Product product) {
        if (false){
            return "redirect:/admin/product?error=duplicate";
        }
        productRepo.save(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/product/{id}/edit")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        returnValuesToModel(model);
        return "admin/product/edit-product";
    }

    @PostMapping("/product/{id}")
    public String updateProduct(@ModelAttribute Product product) {
        productRepo.save(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/product/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        productRepo.deleteById(id);
        return "redirect:/admin/product";
    }

    @GetMapping("/material")
    public String material(Model model,
                         @RequestParam(name = "page", defaultValue = "0") int page) {
        int pageSize = 10;
        Page<Material> list = materialRepo.findAll(PageRequest.of(page, pageSize));
        if (!list.isEmpty()) {
            model.addAttribute("list", list);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", list.getTotalPages());
        } else {
            model.addAttribute("list", Page.empty());
        }
        return "admin/material/all-materials";
    }

    @GetMapping("/material/new")
    public String getNewMaterial(Model model, @RequestParam(name = "error", required = false) String error) {
        if (error!=null && error.equals("duplicate")) {
            model.addAttribute("error", "Такой материал уже существует");
        }
        model.addAttribute("nMaterial", new Material());
        return "/admin/material/n-material";
    }
    @PostMapping("/material")
    public String postMaterial(@ModelAttribute("nMaterial") Material material) {
        if (materialRepo.existsByName(material.getName())){
            return "redirect:/admin/material?error=duplicate";
        }
        materialRepo.save(material);
        return "redirect:/admin/material";
    }

    @GetMapping("/material/{id}/edit")
    public String editMaterial(@PathVariable Long id, Model model) {
        Material material = materialRepo.findById(id).get();
        model.addAttribute("material", material);
        returnValuesToModel(model);
        return "admin/material/edit-material";
    }

    @PostMapping("/material/{id}")
    public String updateMaterial(@ModelAttribute Material material) {
        materialRepo.save(material);
        return "redirect:/admin/material";
    }

    @GetMapping("/material/{id}/delete")
    public String deleteMaterial(@PathVariable Long id) {
        materialRepo.deleteById(id);
        return "redirect:/admin/material";
    }

    @GetMapping("/group")
    public String groups(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page) {
        int pageSize = 10;
        Page<ProductGroup> list = groupRepo.findAll(PageRequest.of(page, pageSize));
        if (!list.isEmpty()) {
            model.addAttribute("list", list);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", list.getTotalPages());
        } else {
            model.addAttribute("list", Page.empty());
        }
        return "admin/group/all-groups";
    }

    @GetMapping("/group/new")
    public String getNewGroup(Model model, @RequestParam(name = "error", required = false) String error) {
        if (error!=null && error.equals("duplicate")) {
            model.addAttribute("error", "Такая группа уже существует");
        }
        returnValuesToModel(model);
        model.addAttribute("nGroup", new ProductGroup());
        return "/admin/group/n-group";
    }
    @PostMapping("/group")
    public String postGroup(@ModelAttribute("nGroup") ProductGroup group) {
        if (groupRepo.existsByName(group.getName())){
            return "redirect:/admin/group?error=duplicate";
        }
        groupRepo.save(group);
        return "redirect:/admin/group";
    }

    @GetMapping("/group/{id}/edit")
    public String editGroup(@PathVariable Long id, Model model) {
        ProductGroup group = groupRepo.findById(id).get();
        model.addAttribute("group", group);
        returnValuesToModel(model);
        return "admin/group/edit-group";
    }

    @PostMapping("/group/{id}")
    public String updateGroup(@ModelAttribute ProductGroup group) {
        groupRepo.save(group);
        return "redirect:/admin/group";
    }

    @GetMapping("/group/{id}/delete")
    public String deleteGroup(@PathVariable Long id) {
        groupRepo.deleteById(id);
        return "redirect:/admin/group";
    }
}
