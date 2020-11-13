package com.dto;

import java.util.List;

import lombok.Builder;

@Builder
public class UploadResponsePage {
    public List uploads;
    public int itemsPerPage;
    public int pageCount;
    public int page;
}
