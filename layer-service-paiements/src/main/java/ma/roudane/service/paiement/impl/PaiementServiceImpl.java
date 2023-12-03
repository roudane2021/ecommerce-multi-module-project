package ma.roudane.service.paiement.impl;

import ma.roudane.repositories.IPaiementRepository;
import ma.roudane.service.paiement.IPaiementService;
import ma.roudane.service.paiement.mapper.IPaiementApplicationMapper;
import ma.roudane.service.paiement.models.PaiementApplication;
import org.springframework.stereotype.Service;

@Service
public class PaiementServiceImpl implements IPaiementService {

    private final IPaiementRepository paiementRepository;
    private final IPaiementApplicationMapper paiementApplicationMapper;

    public PaiementServiceImpl(IPaiementRepository paiementRepository, IPaiementApplicationMapper paiementApplicationMapper) {
        this.paiementRepository = paiementRepository;
        this.paiementApplicationMapper = paiementApplicationMapper;
    }

    @Override
    public PaiementApplication pagetPaiementID(Integer id) {
        final  PaiementApplication paiement = paiementApplicationMapper.toApp(paiementRepository.findByidCommande(id));
        return paiement;
    }

    @Override
    public PaiementApplication savePaiement(PaiementApplication paiement) {

        return paiementApplicationMapper.toApp(paiementRepository.save(paiementApplicationMapper.toEntity(paiement)));
    }
}
