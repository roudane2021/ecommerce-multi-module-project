package ma.roudane.commandes;

import com.fasterxml.jackson.databind.ObjectMapper;
import ma.roudane.commandes.web.produit.ProduitController;
import ma.roudane.commandes.web.produit.dto.ProduitDto;
import ma.roudane.commandes.web.produit.mapper.ICriteriaDtoMapper;
import ma.roudane.commandes.web.produit.mapper.IProduitDtoMapper;
import ma.roudane.service.produit.IProduitService;
import ma.roudane.service.produit.models.ProduitApplication;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Disabled
@WebMvcTest(ProduitController.class)
@ContextConfiguration(classes = { ProduitController.class})
@AutoConfigureJsonTesters
public class ProduitControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProduitService produitService;
    @MockBean
    private  IProduitDtoMapper mapper;
    @MockBean
    private ICriteriaDtoMapper iCriteriaDtoMapper;
    @Autowired
    private JacksonTester<ProduitDto> jacksonTester;

    @Test
    public void saveProduitTest() throws Exception{

        when(produitService.save(any(ProduitApplication.class))).thenReturn(getProduitApp());
        when(mapper.toDto(any(ProduitApplication.class))).thenReturn(getProduitDto());
        when(mapper.toApp(any(ProduitDto.class))).thenReturn(getProduitApp());

        MockHttpServletResponse response = mockMvc.perform(post("/produits/saveProduit")
                .content(asJsonString(getProduitDto()))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn().getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals(jacksonTester.write(getProduitDto()).getJson(), response.getContentAsString());
    }

    private ProduitApplication getProduitApp() {

        return ProduitApplication.builder()
                .titre("Test Titre")
                .prix(15.5)
                .description("Description1")
                .image("Test")
                .quantite(8)
                .build();
    }

    private ProduitDto getProduitDto() {

        return ProduitDto.builder()
                .titre("Test Titre")
                .prix(15.5)
                .description("Description1")
                .image("Test").build();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
