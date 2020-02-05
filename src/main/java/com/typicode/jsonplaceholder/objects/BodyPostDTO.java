package com.typicode.jsonplaceholder.objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class BodyPostDTO {

    protected String title;
    protected String body;
    protected String userId;
}
