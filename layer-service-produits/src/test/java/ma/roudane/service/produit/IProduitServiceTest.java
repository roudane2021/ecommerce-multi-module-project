package ma.roudane.service.produit;

import ma.roudane.entities.ProduitEntity;
import ma.roudane.repositories.IProduitRepository;
import ma.roudane.service.produit.impl.ProduitServiceImpl;
import ma.roudane.service.produit.mapper.IProduitAppMapper;
import ma.roudane.service.produit.models.CriteriaApplication;
import ma.roudane.service.produit.models.ProduitApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class IProduitServiceTest {

    @Mock
    private IProduitRepository produitRepository;

    @Mock
    private IProduitAppMapper mapper;

    @InjectMocks
    private ProduitServiceImpl service;



    @Test
    public void saveTest() {


        when(mapper.toEntity(any(ProduitApplication.class))).thenReturn(getProduitEntity());
        when(produitRepository.save(any(ProduitEntity.class))).thenReturn(getProduitEntity());
        when(mapper.toApplication(any(ProduitEntity.class))).thenReturn(getProduitApp());

        ProduitApplication result = service.save(getProduitApp());

        assertNotNull(result);
        assertEquals(getProduitApp().getDescription(), result.getDescription());
        verify(mapper, times(1)).toEntity(any(ProduitApplication.class));
        verify(produitRepository, times(1)).save(any(ProduitEntity.class));
        verify(mapper, times(1)).toApplication(any(ProduitEntity.class));
    }
    @Test
    public void listAllProduitTest() {

        when(mapper.toApplication(any(ProduitEntity.class))).thenReturn(getProduitApp());
        when(produitRepository.findAll()).thenReturn(getProduitEntityList());

        List<ProduitApplication> result = service.listAllProduit();

        verify(produitRepository, times(1)).findAll();
        verify(mapper, times(2)).toApplication(any(ProduitEntity.class));
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void searchProduitsTest() {
        List<CriteriaApplication> criteriaApplications = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 10);
        when(mapper.toApplication(any(ProduitEntity.class))).thenReturn(getProduitApp());
        when(produitRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(createPageOfProduitEntity());

        Page<ProduitApplication> result = service.searchProduits(pageable, criteriaApplications);

        verify(produitRepository, times(1)).findAll(any(Specification.class), any(Pageable.class));
    }

    private ProduitApplication getProduitApp() {

        return ProduitApplication.builder().id(1)
                .titre("Test Titre")
                .prix(15.5)
                .description("Description1")
                .image("Test")
                .build();
    }

    private ProduitEntity getProduitEntity() {

        return ProduitEntity.builder().id(1)
                .titre("Test Titre")
                .prix(15.5)
                .description("Description1")
                .image("Test").build();
    }

    private List<ProduitApplication> getProduitAppList() {

        ProduitApplication p1 = ProduitApplication.builder().id(1)
                .titre("Test Titre")
                .prix(15.5)
                .description("Description1")
                .image("Test").build();

        ProduitApplication p2 = ProduitApplication.builder().id(2)
                .titre("Test Titre 2")
                .prix(16.5)
                .description("Description2")
                .image("Test2").build();

        return Arrays.asList(p1, p2);
    }

    private List<ProduitEntity> getProduitEntityList() {

        ProduitEntity p1 = ProduitEntity.builder().id(1)
                .titre("Test Titre")
                .prix(15.5)
                .description("Description1")
                .image("Test").build();

        ProduitEntity p2 = ProduitEntity.builder().id(2)
                .titre("Test Titre 2")
                .prix(16.5)
                .description("Description2")
                .image("Test2").build();

        return Arrays.asList(p1, p2);
    }

    private Page<ProduitEntity> createPageOfProduitEntity() {
        List<ProduitEntity> content = getProduitEntityList();
        return new PageImpl<>(content, PageRequest.of(0, 10), content.size());
    }


}
