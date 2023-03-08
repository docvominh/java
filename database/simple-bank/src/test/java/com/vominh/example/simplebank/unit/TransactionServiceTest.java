package com.vominh.example.simplebank.unit;

import com.vominh.example.simplebank.TransactionStatus;
import com.vominh.example.simplebank.entity.AccountEntity;
import com.vominh.example.simplebank.entity.TransactionDetailEntity;
import com.vominh.example.simplebank.entity.TransactionEntity;
import com.vominh.example.simplebank.repository.IAccountRepo;
import com.vominh.example.simplebank.repository.ITransactionDetailRepo;
import com.vominh.example.simplebank.repository.ITransactionRepo;
import com.vominh.example.simplebank.request.WithDrawRequest;
import com.vominh.example.simplebank.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.*;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransactionServiceTest {

    // @Mock creates a mock implementation for the classes you need.
    // @InjectMocks creates an instance of the class and injects the mocks that are marked with the annotations @Mock into it.
    private IAccountRepo accountRepo;

    private ITransactionRepo transactionRepo;

    private ITransactionDetailRepo transactionDetailRepo;

    private TransactionService transactionService;

    private AccountEntity account;

    @BeforeEach
    void setUp() {
        account = AccountEntity.builder().accountId(5).build();

        accountRepo = Mockito.mock(IAccountRepo.class);
        transactionRepo = Mockito.mock(ITransactionRepo.class);
        transactionDetailRepo = Mockito.mock(ITransactionDetailRepo.class);
        transactionService = new TransactionService(accountRepo, transactionRepo, transactionDetailRepo);

        when(accountRepo.findById(any(Integer.class))).thenReturn(Optional.ofNullable(account));
        when(transactionRepo.save(any(TransactionEntity.class))).thenReturn(TransactionEntity.builder().transactionId(12345).build());
        when(transactionDetailRepo.save(any(TransactionDetailEntity.class))).thenReturn(TransactionDetailEntity.builder().transactionId(12345).build());
    }

    @Test
    void withdraw_not_enough_balance() {
        account.setBalance(500L);
        var request = WithDrawRequest.builder()
                .accountId(account.getAccountId())
                .amount(5000)
                .executeEmployee(null)
                .atmAddress("382 Nui Thanh")
                .build();
        Throwable exception = Assertions.assertThrows(RuntimeException.class, () -> {
            transactionService.withDraw(request);
        });

        Assertions.assertEquals("Not enough balance", exception.getMessage());

        verify(accountRepo, times(1)).findById(any(int.class));
        verifyNoMoreInteractions(transactionRepo);
        verifyNoMoreInteractions(transactionDetailRepo);

        Assertions.assertEquals(500, account.getBalance());
    }

    @Test
    void withdraw_success() {
        account.setBalance(50000L);
        int expectBalance = 50000 - 5000;

        var request = WithDrawRequest.builder()
                .accountId(account.getAccountId())
                .amount(5000)
                .executeEmployee(null)
                .atmAddress("382 Nui Thanh")
                .build();
        var status = transactionService.withDraw(request);

        verify(transactionRepo, times(2)).save(any(TransactionEntity.class));
        verifyNoMoreInteractions(transactionRepo);

        verify(transactionDetailRepo, times(1)).save(any(TransactionDetailEntity.class));
        verifyNoMoreInteractions(transactionDetailRepo);

        Assertions.assertEquals(TransactionStatus.SUCCESS, status);
        Assertions.assertEquals(expectBalance, account.getBalance());
    }
}
