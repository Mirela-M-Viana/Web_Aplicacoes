package com.example.ImagemPecas.infra.repository;

import com.example.ImagemPecas.domain.entity.Image;
import com.example.ImagemPecas.domain.enums.ImageExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface imageRepository extends JpaRepository<Image, String>, JpaSpecificationExecutor<Image>{
    default List<Image> findByExtensionAndNameOrTagsLike(ImageExtension extension, String query){
        return findAll();
    }
}
