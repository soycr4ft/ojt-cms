package com.ojt.cms.search;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseDTO<T> {
    private List<T> content; // 클래스 목록
    private int currentPage;
    private int totalPages;
    private long totalElements;
}
