package dashboard.web;

import dashboard.dao.model.Product;
import dashboard.dao.repository.ProductRepository;
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

    @GetMapping(params = "action=form")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "product/form";
    }

    @GetMapping("/{id}")
    public String access(@PathVariable long id, Model model) {
        model.addAttribute("product", productRepository.findById(id).orElse(null));
        return "product/view";
    }

    @GetMapping(value = "/{id}", params = "action=form")
    public String update(@PathVariable long id, Model model) {
        model.addAttribute("product", productRepository.findById(id).orElse(null));
        return "product/form";
    }

    @PostMapping(value = "/{id}", params = "action=form")
    public String update(@PathVariable long id, Product product, Model model) {
        productRepository.save(product);
        return "redirect:/product/" + product.getId();
    }
}
