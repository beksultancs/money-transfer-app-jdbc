package peaksoft;

import peaksoft.models.CardHolder;
import peaksoft.repositories.CardHolderRepository;
import peaksoft.repositories.CardRepository;
import peaksoft.services.BankService;

import java.math.BigDecimal;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        final CardHolderRepository cardHolderRepository = new CardHolderRepository();
        final CardRepository cardRepository = new CardRepository();
        final BankService bankService = new BankService();

//        cardHolderRepository.createTable();
//        cardRepository.createTable();
//
//        cardHolderRepository.save(new CardHolder("Adilet", "zhumakadyrov@gmail.com"));
//        cardHolderRepository.save(new CardHolder("Chynara", "mamatalieva@gmail.com"));
//        cardHolderRepository.save(new CardHolder("Erbol", "anarbaev@gmail.com"));
//        cardHolderRepository.save(new CardHolder("Azamat", "kasymaliev@gmail.com"));
//        cardHolderRepository.save(new CardHolder("Muhammed", "toichubaev@gmail.com"));
//
//        cardRepository.generateCard(1L);
//        cardRepository.generateCard(2L);
//        cardRepository.generateCard(3L);
//        cardRepository.generateCard(4L);
//        cardRepository.generateCard(5L);
//
//        cardHolderRepository.addCard(1L, 1L);
//        cardHolderRepository.addCard(2L, 2L);
//        cardHolderRepository.addCard(3L, 3L);
//        cardHolderRepository.addCard(4L, 4L);
//        cardHolderRepository.addCard(5L, 5L);
//
        CardHolder adilet = cardHolderRepository.findById(1L);
        CardHolder chynara = cardHolderRepository.findById(2L);
        CardHolder erbol = cardHolderRepository.findById(3L);
        CardHolder azamat = cardHolderRepository.findById(4L);
        CardHolder muhammed = cardHolderRepository.findById(5L);
//
//        bankService.addMoney(adilet, BigDecimal.valueOf(1000));
//        bankService.addMoney(chynara, BigDecimal.valueOf(10200));
//        bankService.addMoney(erbol, BigDecimal.valueOf(12000));
//        bankService.addMoney(azamat, BigDecimal.valueOf(10040));
//        bankService.addMoney(muhammed, BigDecimal.valueOf(4000));

        bankService.sendMoney(adilet, erbol, BigDecimal.valueOf(100));
    }
}
