package ma.roudane.commandes.controller.commande;

import com.fasterxml.jackson.databind.ObjectMapper;
import ma.roudane.commandes.CommandeController;
import ma.roudane.commandes.dto.CommandeDto;
import ma.roudane.commandes.mapper.ICommandeDtoMapper;
import ma.roudane.config.PaginationParams;
import ma.roudane.service.commande.ICommandeService;
import ma.roudane.service.commande.models.CommandeApplication;
import ma.roudane.service.proxy.MicroserviceProduitsProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@AutoConfigureJsonTesters
@ExtendWith(MockitoExtension.class)
public class CommandeControllerIsoTest {
    public static final String URL_ENDPOINT_SAVE_COMMANDE = "/commandes";
    public static final String FIRST_NAME = "Rachid";
    public static final String LAST_NAME = "Roudane";

    private MockMvc mvc;

    @InjectMocks
    private CommandeController commandeController;

    @Mock
    private MicroserviceProduitsProxy produitsProxy;

    @Mock
    private  ICommandeService commandeService;
    @Mock
    private  PaginationParams paginationParams;
    @Mock
    private  ICommandeDtoMapper commandemapper;


    @BeforeEach
    public void init() {
        this.mvc = MockMvcBuilders.standaloneSetup(commandeController).build();
    }

    @Test
    public void saveIs2XX() throws Exception {

        // GIVEN
        final CommandeDto commandeDto = CommandeDto.builder().firstName(FIRST_NAME).lastName(LAST_NAME).build();
       final CommandeApplication commandeApplication = CommandeApplication.builder().firstName(FIRST_NAME).lastName(LAST_NAME).build();
        ArgumentCaptor<CommandeApplication> argumentCaptor = ArgumentCaptor.forClass(CommandeApplication.class);

        // WHEN
        given(commandemapper.toApp(any(CommandeDto.class))).willReturn(commandeApplication);
        given(commandemapper.toDto(any(CommandeApplication.class))).willReturn(commandeDto);
        given(commandeService.saveCommande(any(CommandeApplication.class))).willReturn(commandeApplication);

        final MockHttpServletResponse response = mvc.perform(post(URL_ENDPOINT_SAVE_COMMANDE)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(commandeDto)))
                .andDo(print())
                .andReturn().getResponse();

        //verify(commandeService).saveCommande(argumentCaptor.capture());
        then(commandeService).should().saveCommande(argumentCaptor.capture());

        assertThat(argumentCaptor.getValue()).isNotNull();

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isIn(HttpStatus.CREATED.value(), HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isNotNull();
        assertThat(asJsonString(response.getContentAsString())).isNotNull().extracting("lastName").isNotNull();
    }






    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static CommandeDto asJsonString(final String json) {
        try {
            return new ObjectMapper().readValue(json, CommandeDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


