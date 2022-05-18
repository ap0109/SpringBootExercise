package com.telstra.codechallenge.github;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.telstra.codechallenge.common.ServiceUtils;
import com.telstra.codechallenge.handler.RestTemplateResponseErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GithubSearchService {

    @Value("${github.base.url}")
    private String GIT_BASE_URL = "https://api.github.com/search/repositories?q={q}&page={page}&per_page={per_page}&sort={sort}&order={order}";
    public static final String DESCRIPTION = "description";
    public static final String HTML_URL = "html_url";
    public static final String LANGUAGE = "language";
    public static final String NAME = "name";
    public static final String ITEMS = "items";
    public static final String WATCHERS_COUNT = "watchers_count";

    private RestTemplate restTemplate;
    @Autowired
    public GithubSearchService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    /**
     * Returns an array of github starred repositories. Taken from https://spring.io/guides/gs/consuming-rest/.
     *
     * @return - a gitRepository list
     */
    public List<GitRepository> getStarredRepository(String offSet, String num) {
        List<GitRepository> gitRepositoryList = new LinkedList<>();

        JsonNode jsonNode = restTemplate.getForObject(GIT_BASE_URL, JsonNode.class, getURIVariables(offSet, num));
        ArrayNode node= (ArrayNode) jsonNode.get(ITEMS);

        node.forEach(obj -> {
            JsonNode jsonNode1 = (JsonNode) obj;
            GitRepository gitRepository = new GitRepository();
            gitRepository.setDescription(jsonNode1.get(DESCRIPTION).asText());
            gitRepository.setHtml_url(jsonNode1.get(HTML_URL).asText());
            gitRepository.setLanguage(jsonNode1.get(LANGUAGE).asText());
            gitRepository.setName(jsonNode1.get(NAME).asText());
            gitRepository.setWatchers_count(jsonNode1.get(WATCHERS_COUNT).asInt());
            gitRepositoryList.add(gitRepository);
        });
        return gitRepositoryList.stream().sorted(Comparator.comparingInt(GitRepository::getWatchers_count).reversed()).collect(Collectors.toList());
    }

    private Map<String, String> getURIVariables(String offSet, String num){
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

