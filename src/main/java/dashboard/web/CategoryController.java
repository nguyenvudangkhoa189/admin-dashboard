package dashboard.web;

import dashboard.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryRepository catRepo;

    public CategoryController(CategoryRepository catRepo) {
        this.catRepo = catRepo;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("cat", catRepo.findAll());
        return "category/list";
    }
}
