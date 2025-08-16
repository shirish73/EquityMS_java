package org.equity.repository;

import org.equity.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Find all transactions for a specific trade ID, ordered by version descending
     */
    List<Transaction> findByTradeIdOrderByVersionDesc(Long tradeId);

    /**
     * Find the maximum version for a specific trade ID
     */
    @Query("SELECT MAX(t.version) FROM Transaction t WHERE t.tradeId = :tradeId")
    Optional<Integer> findMaxVersionByTradeId(@Param("tradeId") Long tradeId);

    /**
     * Find all transactions ordered by timestamp descending (latest first)
     */
    List<Transaction> findAllByOrderByTimestampDesc();

    /**
     * Find the latest version of each trade (for position calculation)
     */
    @Query("""
        SELECT t FROM Transaction t 
        WHERE (t.tradeId, t.version) IN (
            SELECT t2.tradeId, MAX(t2.version) 
            FROM Transaction t2 
            GROUP BY t2.tradeId
        )
        ORDER BY t.timestamp DESC
        """)
    List<Transaction> findLatestVersionTransactions();

    /**
     * Check if a trade ID already exists
     */
    boolean existsByTradeId(Long tradeId);
}
