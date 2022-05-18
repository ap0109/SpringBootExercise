package com.telstra.codechallenge;

import com.telstra.codechallenge.github.GithubSearchController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.util.AssertionErrors.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MicroServiceMain.class)
public class GithubSearchControllerTest {

    @Autowired
    private GithubSearchController githubSearchController;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void testApplicationLload() {
        assertNotNull("GithubSearchController is not loaded", githubSearchController);
    }

}