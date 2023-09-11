package com.smallworld;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.smallworld.data.Transaction;
import com.smallworld.datasource.DataService;

@RunWith(SpringRunner.class)
public class TransactionDataFetcherTest {

	private List<Transaction> transacitons = new ArrayList<Transaction>();
	TransactionDataFetcher transactionDataFetcher = null;

	@Before
	public void loadData() {
		DataService dataService = new DataService();
		dataService.getAllTransactionData();

		transacitons = dataService.getTransactions();

		transactionDataFetcher = new TransactionDataFetcher();

	}

	@Test
	public void getTotalTransactionAmountTest() throws Exception {
		boolean ResponseCode = true;
		if (transacitons.size() > 0) {
			double totalAmount = transactionDataFetcher.getTotalTransactionAmount();
			if (totalAmount > 0) {
				assertTrue(ResponseCode);
			}
		} else {
			ResponseCode = false;

			assertFalse(ResponseCode);
		}

	}

	@Test
	public void getAllAmountByTheSpecifiedClientTest() throws Exception {
		boolean ResponseCode = true;
		if (transacitons.size() > 0) {
			double totalAmount = transactionDataFetcher.getAllAmountByTheSpecifiedClient("Tom Shelby");
			if (totalAmount > 0) {
				assertTrue(ResponseCode);
			}
		} else {
			ResponseCode = false;

			assertFalse(ResponseCode);
		}

	}

	@Test
	public void getMaxTransactionAmountTest() throws Exception {
		boolean ResponseCode = true;
		if (transacitons.size() > 0) {
			Optional<Double> totalAmount = transactionDataFetcher.getMaxTransactionAmount();
			if (totalAmount.get() != null && totalAmount.get() > 0) {
				assertTrue(ResponseCode);
			}
		} else {
			ResponseCode = false;

			assertFalse(ResponseCode);
		}

	}

	@Test
	public void countUniqueClientsTest() throws Exception {
		boolean ResponseCode = true;
		if (transacitons.size() > 0) {
			long count = transactionDataFetcher.countUniqueClients();
			if (count > 0) {
				assertTrue(ResponseCode);
			}
		} else {
			ResponseCode = false;

			assertFalse(ResponseCode);
		}

	}

	@Test
	public void hasOpenComplianceIssuestest() throws Exception {
		boolean ResponseCode = true;
		if (transacitons.size() > 0) {
			boolean check = transactionDataFetcher.hasOpenComplianceIssues("Grace Burgess");
			if (check) {
				assertTrue(ResponseCode);
			}
		} else {
			ResponseCode = false;

			assertFalse(ResponseCode);
		}

	}

	@Test
	public void getUnsolvedIssueIdsTest() throws Exception {
		boolean ResponseCode = true;
		if (transacitons.size() > 0) {
			Set<Integer> issusedIds = transactionDataFetcher.getUnsolvedIssueIds();
			if (issusedIds.size() > 0) {
				assertTrue(ResponseCode);
			}
		} else {
			ResponseCode = false;

			assertFalse(ResponseCode);
		}

	}

	@Test
	public void getAllSolvedIssueMessagesTest() throws Exception {
		boolean ResponseCode = true;
		if (transacitons.size() > 0) {
			List<String> issusedIds = transactionDataFetcher.getAllSolvedIssueMessages();
			if (issusedIds.size() > 0) {
				assertTrue(ResponseCode);
			}
		} else {
			ResponseCode = false;

			assertFalse(ResponseCode);
		}

	}

	@Test
	public void getTop3TransactionsByAmountTest() throws Exception {
		boolean ResponseCode = true;
		if (transacitons.size() > 0) {
			List<Transaction> transacions = transactionDataFetcher.getTop3TransactionsByAmount();
			if (transacions.size() > 0) {
				assertTrue(ResponseCode);
			}
		} else {
			ResponseCode = false;

			assertFalse(ResponseCode);
		}

	}

	@Test
	public void getTopSenderTest() throws Exception {
		boolean ResponseCode = true;
		if (transacitons.size() > 0) {
			Optional<String> transacions = transactionDataFetcher.getTopSender();
			if (transacions.get() != null) {
				assertTrue(ResponseCode);
			}
		} else {
			ResponseCode = false;

			assertFalse(ResponseCode);
		}

	}

}
