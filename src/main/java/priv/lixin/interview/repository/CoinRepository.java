package priv.lixin.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import priv.lixin.interview.model.Coin;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {


    Coin findByCoinType(@Param("coinType") String coinType);


}
