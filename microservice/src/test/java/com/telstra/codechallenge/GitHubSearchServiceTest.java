package com.telstra.codechallenge;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telstra.codechallenge.common.ServiceUtils;
import com.telstra.codechallenge.exception.RestTemplateException;
import com.telstra.codechallenge.github.GitRepository;
import com.telstra.codechallenge.github.GithubSearchService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@TestPropertySource(locations = "classpath: application.properties")
public class GitHubSearchServiceTest {

    private  final String RESPONSE_STRING = "{\n" +
            "    \"total_count\": 455192,\n" +
            "    \"incomplete_results\": false,\n" +
            "    \"items\": [\n" +
            "        {\n" +
            "            \"id\": 491911983,\n" +
            "            \"name\": \"beautify-github-profile\",\n" +
            "            \"html_url\": \"https://github.com/rzashakeri/beautify-github-profile\",\n" +
            "            \"description\": \"This repository helps you to have a more beautiful and attractive github profile, and you can access a set of tools and guides for beautifying your github profile. \uD83D\uDEA9\",\n" +
            "            \"watchers_count\": 499,\n" +
            "            \"language\": null\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 491911984,\n" +
            "            \"name\": \"beautify-github-profile\",\n" +
            "            \"html_url\": \"https://github.com/rzashakeri/beautify-github-profile\",\n" +
            "            \"description\": \"This repository helps you to have a more beautiful and attractive github profile, and you can access a set of tools and guides for beautifying your github profile. \uD83D\uDEA9\",\n" +
            "            \"watchers_count\": 499,\n" +
            "            \"language\": null\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 491911985,\n" +
            "            \"name\": \"beautify-github-profile\",\n" +
            "            \"html_url\": \"https://github.com/rzashakeri/beautify-github-profile\",\n" +
            "            \"description\": \"This repository helps you to have a more beautiful and attractive github profile, and you can access a set of tools and guides for beautifying your github profile. \uD83D\uDEA9\",\n" +
            "            \"watchers_count\": 499,\n" +
            "            \"language\": null\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 491911986,\n" +
            "            \"name\": \"beautify-github-profile\",\n" +
            "            \"html_url\": \"https://github.com/rzashakeri/beautify-github-profile\",\n" +
            "            \"description\": \"This repository helps you to have a more beautiful and attractive github profile, and you can access a set of tools and guides for beautifying your github profile. \uD83D\uDEA9\",\n" +
            "            \"watchers_count\": 499,\n" +
            "            \"language\": null\n" +
            "        }\n" +
            "    ]\n" +
            "}";
    private final String URI = "https://api.github.com/search/";
    GithubSearchService githubSearchService;

    RestTemplateBuilder restTemplateBuilder;
    @Mock
    RestTemplate restTemplate;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        restTemplateBuilder = new RestTemplateBuilder();

        githubSearchService =  new GithubSearchService(restTemplateBuilder);
    }

    @Test
    public void getGitHubRepoTest() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(RESPONSE_STRING);

        when(restTemplate.getForObject(URI, JsonNode.class, getURIVariables())).thenReturn(actualObj);

        List<GitRepository> gitList = githubSearchService.getStarredRepository("1", "4");
        assertEquals(4, gitList.size());
    }

    private Map<String, String> getURIVariables(){
        String offSet = "1";
        String num = "4";
        String pastWeekDate = ServiceUtils.getLocalDateWeekBefore();
        Map<String, String> uriVariables =  new HashMap<>();
        uriVariables.put("q","created:>"+pastWeekDate);
        uriVariables.put("page", offSet);
        uriVariables.put("per_page",num);
        uriVariables.put("sort","stars");
        uriVariables.put("order","desc");
        return uriVariables;
    }

}
