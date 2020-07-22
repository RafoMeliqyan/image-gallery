package am.image.gallery.repository;

import am.image.gallery.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Integer> {
    List<Image> findAllByCategoryId(Integer id);
}
