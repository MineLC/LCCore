package lc.core.entidades.database;

import lc.core.LCCore;
import lc.core.entidades.Jugador;
import lc.core.utilidades.Util;
import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static lc.core.entidades.database.Database.close;
import static lc.core.entidades.database.Database.connection;

public class VipPointsQuery {

    public static void savePlayerVipPoints_ASYNC(final Jugador jug) {
        Bukkit.getScheduler().runTaskAsynchronously(LCCore.get(), () -> {
            PreparedStatement preparedStatement = null;
            try {
                String queryBuilder = "UPDATE `VipPoints` SET " +
                        "`VipPoints` = ? " +
                        "WHERE `Player` = ?;";

                preparedStatement = connection.prepareStatement(queryBuilder);
                preparedStatement.setInt(1, jug.getVippoints());
                preparedStatement.setString(2, jug.getNombre());
                preparedStatement.executeUpdate();
            } catch (final Exception Exception) {
                Exception.printStackTrace();
            } finally {
                close(preparedStatement);
            }
        });
    }

    public static void savePlayerVipPoints(final Jugador jug) {
        PreparedStatement preparedStatement = null;
        try {
            String queryBuilder = "UPDATE `VipPoints` SET " +
                    "`VipPoints` = ? " +
                    "WHERE `Player` = ?;";

            preparedStatement = connection.prepareStatement(queryBuilder);
            preparedStatement.setInt(1, jug.getVippoints());
            preparedStatement.setString(2, jug.getNombre());
            preparedStatement.executeUpdate();
        } catch (final Exception Exception) {
            Exception.printStackTrace();
        } finally {
            close(preparedStatement);
        }
    }

    public static void load_PlayerVipPoints(Jugador jugador) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String queryBuilder = "SELECT * FROM `VipPoints` WHERE `Player` = ?;";
            preparedStatement = connection.prepareStatement(queryBuilder);
            preparedStatement.setString(1, jugador.getNombre());
            resultSet = preparedStatement.executeQuery();

            if (resultSet != null && resultSet.next()) {
                jugador.setVippoints(resultSet.getInt("VipPoints"));
            }else{
                createPlayerVipPoints(jugador);
            }
        } catch (SQLException Exception) {
            Util.console("&c[Core] Excepcion cargando PlayerVipPoints de "+jugador.getNombre()+".");
        } finally {
            close(resultSet);
            close(preparedStatement);
        }

    }

    public static void load_PlayerVipPoints_ASYNC(Jugador jugador) {
        Bukkit.getScheduler().runTaskAsynchronously(LCCore.get(), () ->{
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try {
                String queryBuilder = "SELECT * FROM `VipPoints` WHERE `Player` = ?;";
                preparedStatement = connection.prepareStatement(queryBuilder);
                preparedStatement.setString(1, jugador.getNombre());
                resultSet = preparedStatement.executeQuery();

                if (resultSet != null && resultSet.next()) {
                    jugador.setVippoints(resultSet.getInt("VipPoints"));
                }else{
                    createPlayerVipPoints(jugador);
                }
            } catch (SQLException Exception) {
                Util.console("&c[Core] Excepcion cargando PlayerVipPoints de "+jugador.getNombre()+". (ASYNC)");
            } finally {
                close(resultSet);
                close(preparedStatement);
            }


        });
    }

    private static void createPlayerVipPoints(Jugador jugador) {
        PreparedStatement statement = null;
        try {

            String queryBuilder = "INSERT INTO `VipPoints` (`Player`, `VipPoints`) VALUES (?, ?);";
            statement = connection.prepareStatement(queryBuilder);
            statement.setString(1, jugador.getNombre());
            statement.setInt(2, 0);

            statement.executeUpdate();
        } catch (SQLException e) {
            Util.console("&c[Core] Excepcion creando PlayerVipPoints de "+jugador.getNombre()+".");
        }finally {
            close(statement);
        }
    }
}
