package am.image.gallery.service;

import am.image.gallery.model.Image;
import am.image.gallery.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public void save(Image image) {
        imageRepository.save(image);
    }

    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    public List<Image> findAllByCategory_Id(Integer id) {
        return imageRepository.findAllByCategory_Id(id);
    }

}
