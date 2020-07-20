package am.image.gallery.controller;

import am.image.gallery.model.Category;
import am.image.gallery.model.Image;
import am.image.gallery.service.CategoryService;
import am.image.gallery.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    @Value("${file.upload.dir}")
    private String uploadDir;
    private final CategoryService categoryService;
    private final ImageService imageService;

    @RequestMapping("/admin")
    public String admin(Model model) {
        List<Category> all = categoryService.findAll();
        model.addAttribute("categorys", all);
        return "admin";
    }

    @RequestMapping("/addImage")
    public String addImg(@ModelAttribute Image image, @RequestParam("image") MultipartFile file) throws IOException {
        String name = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File img = new File(uploadDir,name);
        file.transferTo(img);
        image.setPicUrl(name);
        imageService.save(image);
        return "redirect:/admin";
    }

    @RequestMapping("/addCategory")
    public String addCategory(@ModelAttribute Category category, @RequestParam("image") MultipartFile file) throws IOException {
        String name = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File image = new File(uploadDir,name);
        file.transferTo(image);
        category.setPicUrl(name);
        categoryService.save(category);
        return "redirect:/admin";
    }

}
