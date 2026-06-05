package rs.ac.uns.ftn.product_service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.uns.ftn.product_service.model.Product;
import rs.ac.uns.ftn.product_service.repository.ProductRepository;
import rs.ac.uns.ftn.product_service.service.ProductService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void testFindAll() {
        when(productRepository.findAll()).thenReturn(java.util.List.of(new Product()));
        assertNotNull(productService.findAll());
    }
}