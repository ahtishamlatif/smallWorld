package com.smallworld.datasource;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smallworld.data.Transaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataService {


    public static List<Transaction> transactions = null;


    Gson gson = new Gson();

    private String readJsonFileAsString() throws IOException {
        // Specify the path to your JSON file
        String filePath = "transaction.json";
        StringBuilder jsonContent = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader("transactions.json"))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonContent.append(line);
            }
        }

        return jsonContent.toString();
    }


    public void getAllTransactionData() {

        try {
            String json = readJsonFileAsString();
            TypeToken<List<Transaction>> token = new TypeToken<>() {
            };
            transactions = gson.fromJson(json, token.getType());

        } catch (Exception ex) {
            System.out.println("Exception : " + ex.getMessage());
        }


    }


    public List<Transaction> getTransactions() {
        return transactions;
    }
}
