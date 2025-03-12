package priv.lixin.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import priv.lixin.interview.model.Coin;

import java.util.Optional;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {


    Optional<Coin> findByCoinType(String coinType);

}
