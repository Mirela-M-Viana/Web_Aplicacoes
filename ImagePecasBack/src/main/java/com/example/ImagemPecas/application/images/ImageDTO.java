package com.example.ImagemPecas.application.images;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@Data
@Builder
public class ImageDTO {
    private String url;
    private String name;
    private String extension;
    private Long size;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate uploadDate;

}