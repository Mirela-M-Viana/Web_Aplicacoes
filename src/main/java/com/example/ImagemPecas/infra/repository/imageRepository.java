package com.example.ImagemPecas.infra.repository;

import ch.qos.logback.core.util.StringUtil;
import com.example.ImagemPecas.domain.entity.Image;
import com.example.ImagemPecas.domain.enums.ImageExtension;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Locale;


public interface imageRepository extends JpaRepository<Image, String>, JpaSpecificationExecutor<Image>{


    default List<Image> findByExtensionAndNameOrTagsLike(ImageExtension extension, String query){

        //SELECT * FROM IMAGE WHERE 1 = 1
        Specification<Image> conjunction = (root, q, criteriaBuilder) -> criteriaBuilder.conjunction();
        Specification<Image> spec = Specification.where(conjunction);

        if(extension != null){
            //AND EXTENSION = 'PNG'
            Specification<Image> extensionEqual = (root, q, cb) -> cb.equal(root.get("extension"), extension);
            spec = spec.and(extensionEqual);
        }

        if(StringUtils.hasText(query)){
            Specification<Image> nameLike = (root, q, cb) -> cb.like( cb.upper(root.get("name")), "%" + query.toUpperCase() + " %");
            Specification<Image> tagsLike = (root, q, cb) ->  cb.like( cb.upper(root.get("tags")), "%" + query.toUpperCase() + " %");

            Specification<Image> nameOrTagsLike = Specification.anyOf(nameLike, tagsLike);

            spec = spec.and(nameOrTagsLike);
        }


        return findAll(spec);
    }
}
