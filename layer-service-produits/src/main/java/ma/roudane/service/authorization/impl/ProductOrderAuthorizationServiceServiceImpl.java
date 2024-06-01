package ma.roudane.service.authorization.impl;

import ma.roudane.repositories.IProduitRepository;
import ma.roudane.service.authorization.IProductOrderAuthorizationService;
import ma.roudane.service.authorization.models.AutorisationCommandeProduisRequest;
import ma.roudane.service.authorization.models.AutorisationCommandeResponse;
import ma.roudane.service.authorization.models.ProduitCommandeRequest;
import ma.roudane.service.authorization.models.ProduitCommandeResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductOrderAuthorizationServiceServiceImpl implements IProductOrderAuthorizationService {

    private final IProduitRepository produitRepository;

    public ProductOrderAuthorizationServiceServiceImpl(final
                                     IProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public AutorisationCommandeResponse authorizationOrder(final AutorisationCommandeProduisRequest order) {
        if (isInvalidOrder(order)) {
            return buildResponse(Collections.emptySet(), false);
        }

        List<Integer> productIds = extractProductIds(order);

        Set<ProduitCommandeResponse> productResponses = getProductResponses(order, productIds);

        boolean allProductsAccepted = productResponses.stream().filter(p -> Objects.nonNull(p) && p.getAccepted() == 1).count() == productIds.size();

        if (allProductsAccepted) {
            Set<ProduitCommandeRequest> produitCommandes = order.getProduitCommandes();
            this.updateProductQuantites(produitCommandes);
        }

        return buildResponse(productResponses, allProductsAccepted);
    }

    private void updateProductQuantites(final Set<ProduitCommandeRequest> produitCommandes) {
        if( CollectionUtils.isEmpty(produitCommandes)){
            return;
        }
        produitCommandes.forEach(produit -> produitRepository.updateQuantite(produit.getProduitId(), produit.getQuantite()));

    }

    private boolean isInvalidOrder(AutorisationCommandeProduisRequest order) {
        return Objects.isNull(order) || CollectionUtils.isEmpty(order.getProduitCommandes());
    }

    private List<Integer> extractProductIds(final AutorisationCommandeProduisRequest order) {
        if( isInvalidOrder(order)){
            return Collections.emptyList();
        }

        return order.getProduitCommandes().stream()
                .map(ProduitCommandeRequest::getProduitId)
                .distinct()
                .collect(Collectors.toList());
    }

    private Set<ProduitCommandeResponse> getProductResponses(final AutorisationCommandeProduisRequest order, final List<Integer> productIds) {
        if(isInvalidOrder(order)) {
            Collections.emptyList();
        }
        Map<Integer, Integer> requestedQuantities = order.getProduitCommandes().stream()
                .collect(
                        Collectors.toMap(ProduitCommandeRequest::getProduitId, ProduitCommandeRequest::getQuantite, (q1, q2) -> q1, HashMap::new)
                );
        return produitRepository.findProduitQuantiteByIds(productIds).stream()
                .map(p -> {
                    int requestedQuantity = requestedQuantities.getOrDefault(p.getId(), 0);

                    boolean isAccepted = p.getQuantite() > requestedQuantity;

                    return new ProduitCommandeResponse(p.getId(), isAccepted ? 1 : 0);
                })
                .collect(Collectors.toSet());
    }

    private AutorisationCommandeResponse buildResponse(final Set<ProduitCommandeResponse> productResponses, final boolean allAccepted) {
        return AutorisationCommandeResponse.builder()
                .produitCommandeResponses(productResponses)
                .accepted(allAccepted ? 1 : 0)
                .build();
    }
}
