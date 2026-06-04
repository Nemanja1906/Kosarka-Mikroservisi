package rs.ac.uns.ftn.api_gateway;

import org.springframework.web.bind.annotation.*;
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

    @org.springframework.web.bind.annotation.PutMapping("/api/orders/**")
    public String forwardPutToOrders(HttpServletRequest request, @RequestBody String body) {
        String path = request.getRequestURI().substring(request.getContextPath().length());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        System.out.println("Gateway salje PUT zahtev na: http://ORDER-SERVICE" + path);

        // RestTemplate nema putForObject, koristimo exchange
        return restTemplate.exchange("http://ORDER-SERVICE" + path,
                org.springframework.http.HttpMethod.PUT,
                entity,
                String.class).getBody();
    }

    @org.springframework.web.bind.annotation.DeleteMapping("/api/orders/**")
    public void forwardDeleteToOrders(HttpServletRequest request) {
        String path = request.getRequestURI().substring(request.getContextPath().length());

        System.out.println("Gateway salje DELETE zahtev na: http://ORDER-SERVICE" + path);

        restTemplate.delete("http://ORDER-SERVICE" + path);
    }

    @PostMapping("/api/products/**")
    public String forwardPostToProducts(HttpServletRequest request, @RequestBody String body) {
        String path = request.getRequestURI().substring(request.getContextPath().length());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        return restTemplate.postForObject("http://PRODUCT-SERVICE" + path, entity, String.class);
    }

    // PUT zahtev za proizvode
    @PutMapping("/api/products/**")
    public String forwardPutToProducts(HttpServletRequest request, @RequestBody String body) {
        String path = request.getRequestURI().substring(request.getContextPath().length());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        return restTemplate.exchange("http://PRODUCT-SERVICE" + path,
                org.springframework.http.HttpMethod.PUT,
                entity,
                String.class).getBody();
    }

    // DELETE zahtev za proizvode
    @DeleteMapping("/api/products/**")
    public void forwardDeleteToProducts(HttpServletRequest request) {
        String path = request.getRequestURI().substring(request.getContextPath().length());
        restTemplate.delete("http://PRODUCT-SERVICE" + path);
    }

    @GetMapping("/api/inventory/**")
    public String forwardGetToInventory(HttpServletRequest request) {
        String path = request.getRequestURI().substring(request.getContextPath().length());
        System.out.println("Gateway salje GET zahtev na: http://INVENTORY-SERVICE" + path);
        return restTemplate.getForObject("http://INVENTORY-SERVICE" + path, String.class);
    }

    @PostMapping("/api/inventory/**")
    public String forwardPostToInventory(HttpServletRequest request, @RequestBody String body) {
        String path = request.getRequestURI().substring(request.getContextPath().length());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        System.out.println("Gateway salje POST zahtev na: http://INVENTORY-SERVICE" + path);
        return restTemplate.postForObject("http://INVENTORY-SERVICE" + path, entity, String.class);
    }

    @PutMapping("/api/inventory/**")
    public void forwardPutToInventory(HttpServletRequest request) {
        String path = request.getRequestURI().substring(request.getContextPath().length());
        restTemplate.put("http://INVENTORY-SERVICE" + path, null);
    }
}