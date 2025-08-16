package org.equity.controller;

import jakarta.validation.Valid;
import org.equity.dto.TransactionRequest;
import org.equity.dto.TransactionResponse;
import org.equity.entity.Position;
import org.equity.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * Create a new transaction
     */
    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(@Valid @RequestBody TransactionRequest request) {
        try {
            TransactionResponse response = transactionService.createTransaction(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get all transactions
     */
    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getAllTransactions() {
        List<TransactionResponse> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    /**
     * Get current positions
     */
    @GetMapping("/positions")
    public ResponseEntity<List<Position>> getPositions() {
        List<Position> positions = transactionService.calculatePositions();
        return ResponseEntity.ok(positions);
    }

    /**
     * Load sample data
     */
    @PostMapping("/sample-data")
    public ResponseEntity<String> loadSampleData() {
        try {
            transactionService.loadSampleData();
            return ResponseEntity.ok("Sample data loaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error loading sample data: " + e.getMessage());
        }
    }

    /**
     * Clear all data
     */
    @DeleteMapping("/all")
    public ResponseEntity<String> clearAllData() {
        try {
            transactionService.clearAllData();
            return ResponseEntity.ok("All data cleared successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error clearing data: " + e.getMessage());
        }
    }

    /**
     * Health check endpoint
     */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Equity Position Service is running!");
    }
}
