package com.example.msqueryjoin.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class EditeurJoinBook {

    @EmbeddedId
    private CompositeKey keyJoin;
    private String editeurName;
    private String bookTitle;
}
