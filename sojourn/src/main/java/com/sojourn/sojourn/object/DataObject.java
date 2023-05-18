package com.sojourn.sojourn.object;


import lombok.*;
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
@Builder
public class DataObject {
    @Id
    private String id;
    private Map data;
//    @Indexed( name = "createdIndex", expireAfter = "30s")
    private LocalDateTime createdAt;
    private List<String> accessibleTo;

    public boolean isAccessibleTo(String userAccessing) {
        return accessibleTo.contains(userAccessing);
    }
}
