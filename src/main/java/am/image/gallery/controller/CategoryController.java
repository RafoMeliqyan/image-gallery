package am.image.gallery.controller;

import am.image.gallery.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    @Value("${file.upload.dir}")
    private String uploadDir;
    private final CategoryService categoryService;


}
