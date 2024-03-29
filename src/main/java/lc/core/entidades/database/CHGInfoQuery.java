package lc.core.entidades.database;

import lc.core.LCCore;
import lc.core.entidades.Jugador;
import lc.core.entidades.minijuegos.CHGInfo;
import lc.core.entidades.minijuegos.CHGRank;
import lc.core.utilidades.Util;
import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static lc.core.entidades.database.Database.close;
import static lc.core.entidades.database.Database.connection;

public class CHGInfoQuery {

    public static void load_PlayerCHGInfo_ASYNC(Jugador jugador) {
        Bukkit.getScheduler().runTaskAsynchronously(LCCore.get(), () ->{
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try {
                String queryBuilder = "SELECT * FROM `CHGInfo` WHERE `Player` = ?;";
                preparedStatement = connection.prepareStatement(queryBuilder);
                preparedStatement.setString(1, jugador.getNombre());
                resultSet = preparedStatement.executeQuery();

                if (resultSet != null && resultSet.next()) {
                    jugador.setChgInfo(new CHGInfo(
                            CHGRank.valueOf(resultSet.getString("rango")),
                            resultSet.getInt("kills"),
                            resultSet.getInt("muertes"),
                            resultSet.getInt("ganadas"),
                            resultSet.getInt("jugadas"),
                            resultSet.getBoolean("winner"),
                            resultSet.getString("kit"),
                            resultSet.getInt("racha"),
                            resultSet.getInt("fama")
                    ));
                }else{
                    createPlayerCHGInfo(jugador);
                }
            } catch (SQLException Exception) {
                Util.console("&c[Core] Excepcion cargando PlayerCHGInfo de "+jugador.getNombre()+". (ASYNC)");
            } finally {
                close(resultSet);
                close(preparedStatement);
            }


        });
    }


    public static void saveCHGInfo(final Jugador jug) {
        CHGInfo info = jug.getChgInfo();
        Bukkit.getScheduler().runTaskAsynchronously(LCCore.get(), () -> {
            PreparedStatement preparedStatement = null;
            try {
                String queryBuilder = "UPDATE `CHGInfo` SET " +
                        "`kills` = ?, `muertes` = ?, `ganadas` = ?, `racha` = ?, " +
                        "`rango` = ?, `jugadas` = ?, `kit` = ?, `fama` = ?, `winner` = ?" +
                        " WHERE `Player` = ?;";

                preparedStatement = connection.prepareStatement(queryBuilder);
                preparedStatement.setInt(1, info.getKills());
                preparedStatement.setInt(2, info.getMuertes());
                preparedStatement.setInt(3, info.getWins());
                preparedStatement.setInt(4, info.getRacha());
                preparedStatement.setString(5, info.getRank().name());
                preparedStatement.setInt(6, info.getPlayeds());
                preparedStatement.setString(7, info.getKit());
                preparedStatement.setInt(8, info.getFama());
                preparedStatement.setBoolean(9, info.isWinner());
                preparedStatement.setString(10, jug.getNombre());
                preparedStatement.executeUpdate();
            } catch (final Exception Exception) {
                Exception.printStackTrace();
            } finally {
                close(preparedStatement);
            }
        });
    }

    public static void load_PlayerCHGInfo(Jugador jugador) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String queryBuilder = "SELECT * FROM `CHGInfo` WHERE `Player` = ?;";
            preparedStatement = connection.prepareStatement(queryBuilder);
            preparedStatement.setString(1, jugador.getNombre());
            resultSet = preparedStatement.executeQuery();

            if (resultSet != null && resultSet.next()) {
                jugador.setChgInfo(new CHGInfo(
                        CHGRank.valueOf(resultSet.getString("rango")),
                        resultSet.getInt("kills"),
                        resultSet.getInt("muertes"),
                        resultSet.getInt("ganadas"),
                        resultSet.getInt("jugadas"),
                        resultSet.getBoolean("winner"),
                        resultSet.getString("kit"),
                        resultSet.getInt("racha"),
                        resultSet.getInt("fama")
                ));
            }else{
                createPlayerCHGInfo(jugador);
            }
        } catch (SQLException Exception) {
            Util.console("&c[Core] Excepcion cargando PlayerCHGInfo de "+jugador.getNombre()+". (SYNC)");
        } finally {
            close(resultSet);
            close(preparedStatement);
        }


    }

    private static void createPlayerCHGInfo(Jugador jugador) {
        PreparedStatement statement = null;
        try {
            String queryBuilder = "INSERT INTO `CHGInfo` (`Player`, `kills`, `muertes`, `rango`, `kit`, `ganadas`, `jugadas`, `winner`, `fama`, `racha`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            statement = connection.prepareStatement(queryBuilder);
            statement.setString(1, jugador.getNombre());
            statement.setInt(2, 0);
            statement.setInt(3, 0);
            statement.setString(4, "NUEVO");
            statement.setString(5, "default");
            statement.setInt(6, 0);
            statement.setInt(7, 0);
            statement.setBoolean(8, false);
            statement.setInt(9, 0);
            statement.setInt(10, 0);

            jugador.setChgInfo(new CHGInfo(CHGRank.NUEVO, 0, 0, 0, 0, false, "default",0, 0));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }finally {
            close(statement);
        }
    }

}
