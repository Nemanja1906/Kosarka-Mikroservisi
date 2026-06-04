package rs.ac.uns.ftn.product_service.controller;

import rs.ac.uns.ftn.product_service.model.Product;
import rs.ac.uns.ftn.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // POST: Dodaj novi proizvod
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    // GET: Izlistaj sve proizvode
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    // GET: Pronađi proizvod po ID-ju
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id);
    }

    // PUT: Izmena proizvoda
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product product = productService.findById(id);
        if (product == null) {
            throw new RuntimeException("Proizvod nije pronađen sa id: " + id);
        }

        product.setName(productDetails.getName());
        product.setBrand(productDetails.getBrand());
        product.setPrice(productDetails.getPrice());
        product.setQuantity(productDetails.getQuantity());

        return productService.save(product);
    }

    // DELETE: Brisanje proizvoda
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }
}