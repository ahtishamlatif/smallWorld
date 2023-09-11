package com.smallworld;


import com.smallworld.datasource.DataService;

public class Application {


    public static void main(String[] args) throws Exception {
        DataService dataService = new DataService();
        dataService.getAllTransactionData();



        TransactionDataFetcher  transactionDataFetcher=new TransactionDataFetcher();

     //   System.out.println(transactionDataFetcher.getTotalTransactionAmount());


      //  System.out.println(transactionDataFetcher.getAllAmountByTheSpecifiedClient("Tom Shelby"));

     //    System.out.println(transactionDataFetcher.hasOpenComplianceIssues("Grace Burgess"));

    //    System.out.println(transactionDataFetcher.getUnsolvedIssueIds());


     //   System.out.println(transactionDataFetcher.getTop3TransactionsByAmount());

      //  System.out.println(transactionDataFetcher.getTopSender());




    }
}
