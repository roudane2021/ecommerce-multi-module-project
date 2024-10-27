package ma.roudane.commandes.controller.commande;

import com.fasterxml.jackson.databind.ObjectMapper;
import ma.roudane.commandes.dto.CommandeDto;
import ma.roudane.config.exception.ExceptionResponse;
import ma.roudane.service.proxy.MicroserviceProduitsProxy;
import ma.roudane.service.proxy.dto.AutorisationCommandeProduisRequest;
import ma.roudane.service.proxy.dto.AutorisationCommandeResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.mockito.BDDMockito.given;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("h2")
public class CommandeControllerTest {
    public static final String URL_ENDPOINT_SAVE_COMMANDE = "/commandes";
    public static final String FIRST_NAME = "Rachid";
    public static final String LAST_NAME = "Roudane";
    public static final String HTTP_CODE_MESSAGE = "httpCodeMessage";
    public static final String BAD_REQUEST_VALUE = "Bad Request";
    @Autowired
    private MockMvc mvc;

    @MockBean
    private MicroserviceProduitsProxy produitsProxy;

    @Autowired
    private JacksonTester<CommandeDto> jacksonTesterCommandeDto;

    @Autowired
    private JacksonTester<ExceptionResponse> jacksonTesterExceptionResponse;

    @Test
    public void saveIs2XX() throws Exception {
       final AutorisationCommandeResponse autorisationCommandeResponse = AutorisationCommandeResponse.builder()
                .accepted(1)
                .build();
       final CommandeDto commandeDto = CommandeDto.builder().firstName(FIRST_NAME).lastName(LAST_NAME).build();

        given(produitsProxy.autorisationCommande(any(AutorisationCommandeProduisRequest.class))).willReturn(ResponseEntity.ok(autorisationCommandeResponse));

        final MockHttpServletResponse response = mvc.perform(post(URL_ENDPOINT_SAVE_COMMANDE)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(commandeDto)))
                .andDo(print())
                .andReturn().getResponse();

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isIn(HttpStatus.CREATED.value(), HttpStatus.OK.value());
        assertThat(jacksonTesterCommandeDto.parseObject(response.getContentAsString())).isNotNull().extracting("lastName").isNotNull();
    }

    @Test
    public void saveIs4XXAutorisationOk() throws Exception {
        final AutorisationCommandeResponse autorisationCommandeResponse = AutorisationCommandeResponse.builder()
                .accepted(1)
                .build();
        final CommandeDto commandeDto = CommandeDto.builder().firstName(FIRST_NAME).build();

        given(produitsProxy.autorisationCommande(any(AutorisationCommandeProduisRequest.class))).willReturn(ResponseEntity.ok(autorisationCommandeResponse));

        final MockHttpServletResponse response = mvc.perform(post(URL_ENDPOINT_SAVE_COMMANDE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(commandeDto)))
                .andDo(print())
                .andReturn().getResponse();

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(jacksonTesterExceptionResponse.parseObject(response.getContentAsString())).isNotNull().extracting(HTTP_CODE_MESSAGE).isEqualTo(BAD_REQUEST_VALUE);

    }

    @Test
    public void saveIs4XXAutorisationNonOk() throws Exception {
        final AutorisationCommandeResponse autorisationCommandeResponse = AutorisationCommandeResponse.builder()
                .accepted(0)
                .build();
        final CommandeDto commandeDto = CommandeDto.builder().firstName(FIRST_NAME).build();

        given(produitsProxy.autorisationCommande(any(AutorisationCommandeProduisRequest.class))).willReturn(ResponseEntity.ok(autorisationCommandeResponse));

        final MockHttpServletResponse response = mvc.perform(post(URL_ENDPOINT_SAVE_COMMANDE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(commandeDto)))
                .andDo(print())
                .andReturn().getResponse();

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(jacksonTesterExceptionResponse.parseObject(response.getContentAsString())).isNotNull().extracting(HTTP_CODE_MESSAGE).isEqualTo(BAD_REQUEST_VALUE);

    }





    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


