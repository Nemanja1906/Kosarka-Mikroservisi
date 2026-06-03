package rs.ac.uns.ftn.api_gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
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
        System.out.println("Gateway salje zahtev na: http://ORDER-SERVICE" + path);
        return restTemplate.getForObject("http://ORDER-SERVICE" + path, String.class);
    }
}