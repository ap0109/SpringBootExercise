package com.telstra.codechallenge.github;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class GitRepository {
    private int watchers_count;
    private String html_url;
    private String language;
    private String description;
    private String name;
}
