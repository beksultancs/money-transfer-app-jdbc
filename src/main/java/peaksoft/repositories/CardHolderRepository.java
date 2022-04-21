package peaksoft.repositories;

import peaksoft.enums.MoneyOperation;
import peaksoft.models.CardHolder;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Beksultan
 */
public class CardHolderRepository extends Repository {

    // create table
    public void createTable() {
        customExecute(
                """
                        create table if not exists card_holders (
                        id serial primary key,
                        name varchar not null,
                        email varchar unique check ( email like '%@%')
                        );
                        """
        );
    }

    // insert into
    public void save(CardHolder cardHolder) {
        String sqlQuery = """
                insert into card_holders (name, email) 
                values (?, ?)
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, cardHolder.getName());
            preparedStatement.setString(2, cardHolder.getEmail());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // add card
    public void addCard(Long cardHolderId, Long cardId) {
        String sqlQuery = """
                update card_holders
                set card_id = ?
                where id = ?
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setLong(1, cardId);
            preparedStatement.setLong(2, cardHolderId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void operation(Long cardHolderId, BigDecimal money, MoneyOperation moneyOperation) {
        String sqlQuery = """
                update cards
                set balance = balance + ?
                where card_holder_id = ?
                """;

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            if (moneyOperation.equals(MoneyOperation.DEPOSIT)) {
                statement.setBigDecimal(1, money);
            } else if (moneyOperation.equals(MoneyOperation.WITHDRAW)){
                BigDecimal minusOne = BigDecimal.valueOf(-1);
                statement.setBigDecimal(1, money.multiply(minusOne));
            }
            statement.setLong(2, cardHolderId);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public CardHolder findById(Long id) {
        String sqlQuery = "select * from card_holders where id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }

            return new CardHolder(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getLong(4)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
