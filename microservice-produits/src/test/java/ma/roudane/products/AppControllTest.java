package ma.roudane.products;


import com.fasterxml.jackson.databind.ObjectMapper;
import ma.roudane.products.web.produit.AppController;
import ma.roudane.products.web.produit.dto.ProduitDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@ExtendWith(MockitoExtension.class)
public class AppControllTest {

    private MockMvc mockMvc;

    @InjectMocks
    private AppController appController;

/*    @Mock
    private  IProduitService produitService;*/

    @BeforeEach
    public void step() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(appController).build();
    }


    @Test
    public void getListProduitTest() throws Exception {
        mockMvc.perform(get("/pro").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1));
    }

    @Test
    public void getProduitTest() throws Exception {
        mockMvc.perform(get("/pro/{id}", 3).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3));
    }

    @Test
    public void saveProduit() throws Exception {

        mockMvc.perform(post("/pro").content(asJsonString(ProduitDto.builder().titre("titre 1").build())).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }




    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
