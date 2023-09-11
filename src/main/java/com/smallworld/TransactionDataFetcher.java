package com.smallworld;

import com.smallworld.data.Transaction;
import com.smallworld.datasource.DataService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionDataFetcher {

    DataService dataService = new DataService();

    /**
     * Returns the sum of the amounts of all transactions
     */
    public double getTotalTransactionAmount() throws Exception {

        List<Transaction> transactions = dataService.getTransactions();
        double totalAmount =0.0;
        if (transactions.size() > 0) {
             totalAmount = transactions
                    .stream()
                    .mapToDouble(Transaction::getAmount)
                    .sum();
        } else {
            throw new Exception("No Transactions Found");
        }

         return totalAmount;
    }

    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */
    public double getAllAmountByTheSpecifiedClient(String senderFullName) throws Exception {
        List<Transaction> transactions = dataService.getTransactions();


        double totalAmount =0.0;
        if (transactions.size() > 0) {

            totalAmount = transactions
                    .stream()
                    .filter(transaction -> senderFullName.equals(transaction.getSenderFullName()))
                    .mapToDouble(Transaction::getAmount)
                    .sum();
        } else {
            throw new Exception("No Transactions Found For this Client "+ senderFullName);
        }

        return totalAmount;
    }

    /**
     * Returns the highest transaction amount getTotalTransactionAmountSentByount
     */
    public Optional<Double> getMaxTransactionAmount() {
        List<Transaction> transactions = dataService.getTransactions();
        Optional<Double> highestAmount = transactions
                .stream()
                .filter(transaction -> transaction.getIssueId() != null)
                .map(Transaction::getAmount)
                .max(Double::compare);

        if (highestAmount.isPresent()) {
            System.out.println("Highest Transaction Amount with non-null issueId: " + highestAmount.get());
        } else {
            System.out.println("No transactions with non-null issueId found.");
        }

        return highestAmount;
    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    public long countUniqueClients() {
        List<Transaction> transactions = dataService.getTransactions();
        return transactions.stream()
                .flatMap(transaction -> Stream.of(transaction.getSenderFullName(), transaction.getBeneficiaryFullName()))
                .distinct()
                .count();
    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    public boolean hasOpenComplianceIssues(String clientFullName) {
        List<Transaction> transactions = dataService.getTransactions();

        return  transactions.stream()
                .filter(transaction ->
                        (transaction.getSenderFullName().equals(clientFullName) ||
                                transaction.getBeneficiaryFullName().equals(clientFullName)) &&
                                transaction.getIssueId() != null &&
                                !transaction.getIssueSolved())
                .findFirst()
                .isPresent();
    }

    /**
     * Returns all transactions indexed by beneficiary name
     */
    public Map<String, Transaction> getTransactionsByBeneficiaryName() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the identifiers of all open compliance issues
     */
    public Set<Integer> getUnsolvedIssueIds() {
        List<Transaction> transactions = dataService.getTransactions();
        return transactions.stream()
                .filter(transaction -> transaction.getIssueSolved() != null && !transaction.getIssueSolved())
                .map(Transaction::getIssueId) // Extract issueId
                .collect(Collectors.toSet());

    }

    /**
     * Returns a list of all solved issue messages
     */
    public List<String> getAllSolvedIssueMessages() {
        List<Transaction> transactions = dataService.getTransactions();
        return transactions.stream()
                .filter(transaction -> transaction.getIssueSolved() != null && transaction.getIssueSolved())
                .map(Transaction::getIssueMessage) // Extract issueMessage
                .collect(Collectors.toList());
    }

    /**
     * Returns the 3 transactions with the highest amount sorted by amount descending
     */
    public List<Transaction> getTop3TransactionsByAmount() {
        List<Transaction> transactions = dataService.getTransactions();
        return   transactions.stream()
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    /**
     * Returns the senderFullName of the sender with the most total sent amount
     */
    public Optional<String> getTopSender() {
        List<Transaction> transactions = dataService.getTransactions();
        Map<String, Double> senderTotalAmounts = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getSenderFullName,
                        Collectors.summingDouble(Transaction::getAmount)));

        // Find the sender with the most total sent amount, if any
       return senderTotalAmounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

}
