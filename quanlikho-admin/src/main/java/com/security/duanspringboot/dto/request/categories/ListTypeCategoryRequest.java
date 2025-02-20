package com.security.duanspringboot.dto.request.categories;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListTypeCategoryRequest {
    @JsonProperty("type")
    private List<String> type;
}
