package peaksoft.models;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Beksultan
 */
public class Card {
    private Long id;

    private Long cardNumber;

    private BigDecimal balance;

    private Long cardHolderId;

    public Card() {
    }

    public Card(BigDecimal balance) {
        this.balance = balance;
    }

    public Card(Long cardHolderId) {
        this.cardHolderId = cardHolderId;
    }

    public Long getCardHolderId() {
        return cardHolderId;
    }

    public void setCardHolderId(Long cardHolderId) {
        this.cardHolderId = cardHolderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return Objects.equals(getId(), card.getId()) && Objects.equals(getCardNumber(), card.getCardNumber()) && Objects.equals(getBalance(), card.getBalance());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCardNumber(), getBalance());
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardNumber=" + cardNumber +
                ", balance=" + balance +
                '}';
    }
}
