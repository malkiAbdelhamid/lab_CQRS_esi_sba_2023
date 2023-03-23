package com.example.msqueryjoin.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data @AllArgsConstructor @NoArgsConstructor
public class CompositeKey implements Serializable {
    private String editeurId;
    private String isbn;
}
