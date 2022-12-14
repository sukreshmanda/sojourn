package com.sojourn.sojourn.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class DataObject {
    @Id
    private String id;
    private Map data;
//    @Indexed( name = "createdIndex", expireAfter = "30s")
    private LocalDateTime createdAt;
    private List<String> accessibleTo;
}
