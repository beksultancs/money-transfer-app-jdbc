package peaksoft.services;

import peaksoft.enums.MoneyOperation;
import peaksoft.models.CardHolder;
import peaksoft.repositories.CardHolderRepository;
import peaksoft.repositories.CardRepository;

import java.math.BigDecimal;

/**
 * @author Beksultan
 */
public class BankService {

    private final CardHolderRepository cardHolderRepository = new CardHolderRepository();
    private final CardRepository cardRepository = new CardRepository();

    public void sendMoney(CardHolder sender, CardHolder receiver, BigDecimal amount) {
        cardHolderRepository.operation(sender.getId(), amount, MoneyOperation.WITHDRAW);
        cardHolderRepository.operation(receiver.getId(), amount, MoneyOperation.DEPOSIT);
    }

    public void addMoney(CardHolder cardHolder, BigDecimal amount) {
        cardHolderRepository.operation(cardHolder.getId(), amount, MoneyOperation.DEPOSIT);
    }
}
