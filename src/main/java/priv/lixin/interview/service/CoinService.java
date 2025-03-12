package priv.lixin.interview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import priv.lixin.interview.model.Coin;
import priv.lixin.interview.repository.CoinRepository;

import java.util.Optional;

@Service
@Transactional
public class CoinService {

    @Autowired
    private CoinRepository coinRepository;

    public Optional<Coin> findByCoinType(String coinType) {
        return coinRepository.findByCoinType(coinType);
    }

}
