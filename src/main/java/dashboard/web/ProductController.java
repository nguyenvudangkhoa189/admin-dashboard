package dashboard.web;

import dashboard.entity.Product;
import dashboard.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "product/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "product/form";
    }

    @PostMapping("/create")
    public String create(Product product) {
        productRepository.save(product);
        return "redirect:/product/" + product.getId();
    }

    @GetMapping("/{id}")
    public String access(@PathVariable long id, Model model) {
        model.addAttribute("product", productRepository.findById(id).orElse(null));
        return "product/view";
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable long id, Model model) {
        model.addAttribute("product", productRepository.findById(id).orElse(null));
        return "product/form";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable long id, Product product) {
        productRepository.save(product);
        return "redirect:/product/" + product.getId();
    }
}
