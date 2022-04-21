package peaksoft.repositories;

import peaksoft.exceptions.NoResultException;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Card;

import javax.swing.plaf.nimbus.State;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Beksultan
 */
public class CardRepository extends Repository {

    // create table
    public void createTable() {
        customExecute("""
                create table if not exists cards (
                id serial primary key,
                card_number bigint unique not null,
                balance numeric(10, 2) not null default 0.00,
                card_holder_id bigint references card_holders(id)
                );
                """);

        customExecute("""
                alter table card_holders add column card_id bigint references cards(id)
                """);
    }

    // insert into
    public Card generateCard(Long cardHolderId) {
        // create seq
        customExecute("""
                create sequence if not exists card_number_generator 
                as bigint 
                increment 2
                minvalue 1000
                maxvalue 9999
                start 1000
                """);

        String sqlQuery = String.format("""
                insert into cards (card_number, card_holder_id)
                values (nextval('card_number_generator'), %d)
                """, cardHolderId);

        customExecute(sqlQuery);

        String sqlQuery2 = "select * from cards where card_holder_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery2)) {
            statement.setLong(1, cardHolderId);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                throw new NoResultException(
                        "no result from query = " + sqlQuery2
                );
            }

            Card card = new Card();
            card.setId(resultSet.getLong(1));
            card.setCardNumber(resultSet.getLong(2));
            card.setBalance(resultSet.getBigDecimal("balance"));
            card.setCardHolderId(resultSet.getLong(4));

            resultSet.close();

            return card;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
