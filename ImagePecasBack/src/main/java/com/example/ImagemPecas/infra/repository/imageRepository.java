package com.example.ImagemPecas.infra.repository;

import ch.qos.logback.core.util.StringUtil;
import com.example.ImagemPecas.domain.entity.Image;
import com.example.ImagemPecas.domain.enums.ImageExtension;
import com.example.ImagemPecas.infra.repository.specs.GenericSpecs;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Locale;

import static com.example.ImagemPecas.infra.repository.ImageSpecs.*;
import static org.springframework.data.jpa.domain.Specification.anyOf;
import static org.springframework.data.jpa.domain.Specification.where;


public interface imageRepository extends JpaRepository<Image, String>, JpaSpecificationExecutor<Image>{


    default List<Image> findByExtensionAndNameOrTagsLike(ImageExtension extension, String query){

        //SELECT * FROM IMAGE WHERE 1 = 1
        Specification<Image> spec = where(GenericSpecs.conjunction());

        if(extension != null){
            //AND EXTENSION = 'PNG'
            spec = spec.and(extesionEqual(extension));
        }

        if(StringUtils.hasText(query)){
            spec = spec.and( anyOf(nameLike(query), tagsLike(query)));
        }
        return findAll(spec);
    }

}
