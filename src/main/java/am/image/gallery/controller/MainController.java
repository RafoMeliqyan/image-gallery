package am.image.gallery.controller;

import am.image.gallery.model.Category;
import am.image.gallery.model.Image;
import am.image.gallery.service.CategoryService;
import am.image.gallery.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    @Value("${file.upload.dir}")
    private String uploadDir;
    private final CategoryService categoryService;
    private final ImageService imageService;

    @GetMapping("/")
    public String homePage(@RequestParam(name = "id", required = false) Integer id, @AuthenticationPrincipal Principal principal, Model model) {
        String username = null;
        if (principal != null) {
            username = principal.getName();
        }
        List<Image> allByCategory_id = imageService.findAllByCategory_Id(id);
        List<Category> all = categoryService.findAll();
        model.addAttribute("category", all);
        model.addAttribute("username", username);
        model.addAttribute("allByCategory_id", allByCategory_id);
        return "index";
    }

    @GetMapping("/images")
    public String imageByCat(@RequestParam("id") int id, Model model) {
        List<Image> allByCategory_id = imageService.findAllByCategory_Id(id);
        model.addAttribute("imgByCateg", allByCategory_id);
        return "images";
    }

    @GetMapping(
            value = "/image",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody
    byte[] getImage(@RequestParam("name") String imageName) throws IOException {
        InputStream in = new FileInputStream(uploadDir + File.separator + imageName);
        return IOUtils.toByteArray(in);
    }
}
