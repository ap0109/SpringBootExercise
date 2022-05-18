package com.telstra.codechallenge.github;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/search")
public class GithubSearchController {
    private GithubSearchService githubSearchService;

    public GithubSearchController(
            GithubSearchService githubSearchService) {
        this.githubSearchService = githubSearchService;
    }

    @RequestMapping(path = "/repos", method = RequestMethod.GET)
    public List<GitRepository> starredRepository(@RequestParam(defaultValue = "1") String offSet,
                                                 @RequestParam(defaultValue = "30") String num) {
        return githubSearchService.getStarredRepository(offSet, num);
    }
}
