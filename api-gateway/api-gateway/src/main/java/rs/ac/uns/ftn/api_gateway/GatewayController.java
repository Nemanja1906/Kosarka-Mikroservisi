package rs.ac.uns.ftn.api_gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class GatewayController {

    private final RestTemplate restTemplate;

    public GatewayController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/api/products/**")
    public String forwardToProducts(HttpServletRequest request) {
        String path = request.getRequestURI().substring(request.getContextPath().length());
        return restTemplate.getForObject("http://PRODUCT-SERVICE" + path, String.class);
    }

    @GetMapping("/api/orders/**")
    public String forwardToOrders(HttpServletRequest request) {
        String path = request.getRequestURI().substring(request.getContextPath().length());
        System.out.println("Gateway salje GET zahtev na: http://ORDER-SERVICE" + path);
        return restTemplate.getForObject("http://ORDER-SERVICE" + path, String.class);
    }

    @PostMapping("/api/orders/**")
    public String forwardPostToOrders(HttpServletRequest request, @RequestBody String body) {
        String path = request.getRequestURI().substring(request.getContextPath().length());

        // Moramo naglasiti da šaljemo JSON, inače će RestTemplate to poslati kao običan tekst
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        System.out.println("Gateway salje POST zahtev na: http://ORDER-SERVICE" + path);
        return restTemplate.postForObject("http://ORDER-SERVICE" + path, entity, String.class);
    }
}