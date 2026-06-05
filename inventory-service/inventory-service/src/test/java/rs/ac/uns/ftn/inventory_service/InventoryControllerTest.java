package rs.ac.uns.ftn.inventory_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getQuantity_ShouldReturn200() throws Exception {
        // Zameni "/api/inventory/1" sa tvojom pravom putanjom iz tvog kontrolera
        mockMvc.perform(get("/api/inventory/1"))
                .andExpect(status().isOk());
    }
}