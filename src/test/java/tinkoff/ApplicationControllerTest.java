package tinkoff;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import tinkoff.entity.response.ApplicationResponse;
import tinkoff.exception.ApplicationNotFoundException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationControllerTest {

    @LocalServerPort
    private int port;

    private String latestApplicationUrl;

    @Autowired
    private TestRestTemplate template;

    @BeforeEach
    public void setUp() {
        this.latestApplicationUrl = "http://localhost:" + port + "/api/v1/application/latest?contact_id=%s";
    }

    @Test
    public void testLatestApplicationValidCaseJson() throws ParseException {
        verifyApplicationResponse(new HttpHeaders(),
                (actualContentType) -> assertThat(MediaType.APPLICATION_JSON_VALUE).isEqualTo(actualContentType));
    }

    @Test
    public void testLatestApplicationValidCaseXml() throws ParseException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_XML.toString());

        verifyApplicationResponse(headers,
                (actualContentType) -> assertThat(actualContentType).contains(MediaType.APPLICATION_XML_VALUE));
    }

    private void verifyApplicationResponse(HttpHeaders headers,
                                                                       Consumer<String> contentTypeCheck)
            throws ParseException {
        ApplicationResponse expectedResponse = new ApplicationResponse(1, 1,
                stringToDate("2020-08-03T11:00:00:00"), "iphone 6");

        ResponseEntity<ApplicationResponse> response = template.exchange(
                String.format(latestApplicationUrl, 1),
                HttpMethod.GET,
                new HttpEntity<>(headers),
                ApplicationResponse.class);

        String actualContentType = response.getHeaders().getContentType().toString();

        assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());
        contentTypeCheck.accept(actualContentType);
        assertThat(expectedResponse).isEqualTo(response.getBody());
    }

    @Test
    public void testLatestApplicationInvalidCase() {
        Integer contactId = 3;
        ApplicationNotFoundException exception = new ApplicationNotFoundException(contactId);

        ResponseEntity<String> response = template.getForEntity(
                String.format(latestApplicationUrl, contactId),
                String.class);

        assertThat(exception.getCode()).isEqualTo(response.getStatusCode());
        assertThat(exception.getMsg()).isEqualTo(response.getBody());
    }

    private Date stringToDate(String dateStr) throws ParseException {
        String dateFormat = "yyyy-MM-dd'T'HH:mm:ss";
        SimpleDateFormat requiredFormat = new SimpleDateFormat(dateFormat);
        return requiredFormat.parse(dateStr);
    }
}