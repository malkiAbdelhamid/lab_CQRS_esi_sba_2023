package com.example.mscommandlibrary.Aggregate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor

public class BookAggregate {
    private String libraryId;
    private String isbn;
    private String title;
    private String editeurId;
}
