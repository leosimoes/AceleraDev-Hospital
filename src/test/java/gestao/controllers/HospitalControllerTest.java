package gestao.controllers;


import gestao.models.hospital.Hospital;
import gestao.services.HospitalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
//@ActiveProfiles("test")
public class HospitalControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private HospitalService hospitalService;

    private final HttpHeaders httpHeaders;



    @Mock
    private Hospital hospitalMock;

    public HospitalControllerTest() {
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
    }


    @Test
    public void index(){
        ResponseEntity<Page<Hospital>> response = restTemplate.exchange("/api/v1/hospitais", HttpMethod.GET, null, new ParameterizedTypeReference<Page<Hospital>>() {
        });
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void index1() {
        ResponseEntity<Page<Hospital>> response = restTemplate.exchange("/api/v1/hospitais", HttpMethod.GET, null, new ParameterizedTypeReference<Page<Hospital>>() {
        });
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void show() {
        ResponseEntity<Hospital> response = restTemplate.exchange("/api/v1/hospitais/111", HttpMethod.GET, null, new ParameterizedTypeReference<Hospital>() {
        });
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}