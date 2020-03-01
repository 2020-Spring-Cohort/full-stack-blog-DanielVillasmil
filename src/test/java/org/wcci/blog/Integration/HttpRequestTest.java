package org.wcci.blog.Integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void categoriesEndPointReturnOk(){
        ResponseEntity<String> response = testRestTemplate.getForEntity(
                "http://localhost:" + port + "/category/all-categories", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    public void authorsEndPointReturnsOk() {
        ResponseEntity<String> response = testRestTemplate.getForEntity(
                "http://localhost:" + port + "/author/all-authors", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    public void postsEndPointReturnOk(){
        ResponseEntity<String> response = testRestTemplate.getForEntity(
                "http://localhost:" + port + "/post/all-posts", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    public void tagsEndPointReturnOk(){
        ResponseEntity<String> response = testRestTemplate.getForEntity(
                "http://localhost:" + port + "/tag/all-tags", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
}
