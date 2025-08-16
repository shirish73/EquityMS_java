package org.equity.service;

import org.equity.dto.TransactionRequest;
import org.equity.dto.TransactionResponse;
import org.equity.entity.BuySell;
import org.equity.entity.Position;
import org.equity.entity.Transaction;
import org.equity.entity.TransactionAction;
import org.equity.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Create a new transaction
     */
    public TransactionResponse createTransaction(TransactionRequest request) {
        Integer version = determineVersion(request.getTradeId(), request.getAction());

        Transaction transaction = new Transaction(
                request.getTradeId(),
                version,
                request.getSecurityCode().toUpperCase(),
                request.getQuantity(),
                request.getAction(),
                request.getBuySell()
        );

        transaction.setTimestamp(LocalDateTime.now());

        Transaction saved = transactionRepository.save(transaction);

        return convertToResponse(saved);
    }

    /**
     * Get all transactions ordered by timestamp
     */
    public List<TransactionResponse> getAllTransactions() {
        return transactionRepository.findAllByOrderByTimestampDesc()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Calculate current positions based on latest transactions
     */
    public List<Position> calculatePositions() {
        // Get latest version of each trade
        List<Transaction> latestTransactions = transactionRepository.findLatestVersionTransactions();

        // Group by security code and calculate positions
        Map<String, Integer> positionMap = new HashMap<>();

        for (Transaction txn : latestTransactions) {
            // Skip cancelled transactions
            if (txn.getAction() == TransactionAction.CANCEL) {
                continue;
            }

            String securityCode = txn.getSecurityCode();
            int positionChange = txn.getBuySell().name().equals("Buy") ?
                    txn.getQuantity() : -txn.getQuantity();

            positionMap.merge(securityCode, positionChange, Integer::sum);
        }

        return positionMap.entrySet().stream()
                .filter(entry -> entry.getValue() != 0) // Only show non-zero positions
                .map(entry -> new Position(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(Position::getSecurityCode))
                .collect(Collectors.toList());
    }

    /**
     * Load sample data as per requirements
     */
    public void loadSampleData() {
        // Clear existing data
        transactionRepository.deleteAll();

        // Create sample transactions as per requirements
        List<Transaction> sampleTransactions = Arrays.asList(
                new Transaction(1L, 1, "REL", 50, TransactionAction.INSERT,
                        BuySell.Buy),
                new Transaction(2L, 1, "ITC", 40, TransactionAction.INSERT,
                        BuySell.Sell),
                new Transaction(3L, 1, "INF", 70, TransactionAction.INSERT,
                        BuySell.Buy),
                new Transaction(1L, 2, "REL", 60, TransactionAction.UPDATE,
                        BuySell.Buy),
                new Transaction(2L, 2, "ITC", 30, TransactionAction.CANCEL,
                        BuySell.Buy),
                new Transaction(4L, 1, "INF", 20, TransactionAction.INSERT,
                        BuySell.Sell)
        );

        // Set timestamps with small intervals
        LocalDateTime baseTime = LocalDateTime.now().minusHours(1);
        for (int i = 0; i < sampleTransactions.size(); i++) {
            sampleTransactions.get(i).setTimestamp(baseTime.plusMinutes(i * 5));
        }

        transactionRepository.saveAll(sampleTransactions);
    }

    /**
     * Clear all transaction data
     */
    public void clearAllData() {
        transactionRepository.deleteAll();
    }

    /**
     * Determine the version number for a new transaction
     */
    private Integer determineVersion(Long tradeId, TransactionAction action) {
        if (action == TransactionAction.INSERT) {
            // INSERT should always be version 1, but check if trade already exists
            if (transactionRepository.existsByTradeId(tradeId)) {
                throw new RuntimeException("Trade ID " + tradeId + " already exists. Use UPDATE or CANCEL.");
            }
            return 1;
        }

        // For UPDATE and CANCEL, find the next version
        Optional<Integer> maxVersion = transactionRepository.findMaxVersionByTradeId(tradeId);
        if (maxVersion.isEmpty()) {
            throw new RuntimeException("Cannot " + action + " trade ID " + tradeId + ". Trade does not exist.");
        }

        return maxVersion.get() + 1;
    }

    /**
     * Convert Transaction entity to TransactionResponse DTO
     */
    private TransactionResponse convertToResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getTransactionId(),
                transaction.getTradeId(),
                transaction.getVersion(),
                transaction.getSecurityCode(),
                transaction.getQuantity(),
                transaction.getAction(),
                transaction.getBuySell(),
                transaction.getTimestamp()
        );
    }
}