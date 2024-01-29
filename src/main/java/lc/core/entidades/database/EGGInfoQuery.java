package lc.core.entidades.database;

import lc.core.LCCore;
import lc.core.entidades.Jugador;
import lc.core.entidades.minijuegos.EGGInfo;
import lc.core.entidades.minijuegos.CHGRank;
import lc.core.utilidades.Util;
import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static lc.core.entidades.database.Database.close;
import static lc.core.entidades.database.Database.connection;

public class EGGInfoQuery {

    public static void load_PlayerEGGInfo_ASYNC(Jugador jugador) {
        Bukkit.getScheduler().runTaskAsynchronously(LCCore.get(), () ->{
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try {
                String queryBuilder = "SELECT * FROM `EGGInfo` WHERE `Player` = ?;";
                preparedStatement = connection.prepareStatement(queryBuilder);
                preparedStatement.setString(1, jugador.getNombre());
                resultSet = preparedStatement.executeQuery();

                if (resultSet != null && resultSet.next()) {
                    jugador.setEGGInfo(new EGGInfo(
                            resultSet.getInt("level"),
                            resultSet.getInt("kills"),
                            resultSet.getInt("muertes"),
                            resultSet.getInt("ganadas"),
                            resultSet.getInt("jugadas"),
                            resultSet.getString("kit"),
                            resultSet.getInt("racha")
                    ));
                }else{
                    createPlayerEGGInfo(jugador);
                }
            } catch (SQLException Exception) {
                Util.console("&c[Core] Excepcion cargando PlayerEGGInfo de "+jugador.getNombre()+". (ASYNC)");
            } finally {
                close(resultSet);
                close(preparedStatement);
            }


        });
    }


    public static void saveEGGInfo(final Jugador jug) {
        EGGInfo info = jug.getEGGInfo();
        Bukkit.getScheduler().runTaskAsynchronously(LCCore.get(), () -> {
            PreparedStatement preparedStatement = null;
            try {
                String queryBuilder = "UPDATE `EGGInfo` SET " +
                        "`kills` = ?, `muertes` = ?, `ganadas` = ?, `racha` = ?, " +
                        "`level` = ?, `jugadas` = ?, `kit` = ?" +
                        " WHERE `Player` = ?;";

                preparedStatement = connection.prepareStatement(queryBuilder);
                preparedStatement.setInt(1, info.getKills());
                preparedStatement.setInt(2, info.getMuertes());
                preparedStatement.setInt(3, info.getWins());
                preparedStatement.setInt(4, info.getRacha());
                preparedStatement.setInt(5, info.getLevel());
                preparedStatement.setInt(6, info.getPlayeds());
                preparedStatement.setString(7, info.getKit());
                preparedStatement.setString(8, jug.getNombre());
                preparedStatement.executeUpdate();
            } catch (final Exception Exception) {
                Exception.printStackTrace();
            } finally {
                close(preparedStatement);
            }
        });
    }

    public static void load_PlayerEGGInfo(Jugador jugador) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String queryBuilder = "SELECT * FROM `EGGInfo` WHERE `Player` = ?;";
            preparedStatement = connection.prepareStatement(queryBuilder);
            preparedStatement.setString(1, jugador.getNombre());
            resultSet = preparedStatement.executeQuery();

            if (resultSet != null && resultSet.next()) {
                jugador.setEGGInfo(new EGGInfo(
                        resultSet.getInt("level"),
                        resultSet.getInt("kills"),
                        resultSet.getInt("muertes"),
                        resultSet.getInt("ganadas"),
                        resultSet.getInt("jugadas"),
                        resultSet.getString("kit"),
                        resultSet.getInt("racha")
                ));
            }else{
                createPlayerEGGInfo(jugador);
            }
        } catch (SQLException Exception) {
            Util.console("&c[Core] Excepcion cargando PlayerEGGInfo de "+jugador.getNombre()+". (SYNC)");
        } finally {
            close(resultSet);
            close(preparedStatement);
        }


    }

    private static void createPlayerEGGInfo(Jugador jugador) {
        PreparedStatement statement = null;
        try {
            String queryBuilder = "INSERT INTO `EGGInfo` (`Player`, `kills`, `muertes`, `level`, `kit`, `ganadas`, `jugadas`, `racha`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            statement = connection.prepareStatement(queryBuilder);
            statement.setString(1, jugador.getNombre());
            statement.setInt(2, 0);
            statement.setInt(3, 0);
            statement.setInt(4, 0);
            statement.setString(5, "default");
            statement.setInt(6, 0);
            statement.setInt(7, 0);
            statement.setInt(8, 0);

            jugador.setEGGInfo(new EGGInfo(0, 0, 0, 0, 0, "default",0));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }finally {
            close(statement);
        }
    }

}
