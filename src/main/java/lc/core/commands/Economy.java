package lc.core.commands;

import lc.core.entidades.Jugador;
import lc.core.entidades.database.LCoinsQuery;
import lc.core.entidades.database.VipPointsQuery;
import lc.core.utilidades.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Economy implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof ConsoleCommandSender){
            if(args.length == 0){
                Util.sendMessage(sender, "&d&m===================================");
                Util.sendMessage(sender, "&d  /eco give <LCoins|VipPoints> <Jugador> <Cantidad>");
                Util.sendMessage(sender, "&d  /eco set <LCoins|VipPoints> <Jugador> <Cantidad>");
                Util.sendMessage(sender, "&d  /eco take <LCoins|VipPoints> <Jugador> <Cantidad>");
                Util.sendMessage(sender, "&d  /eco reset <LCoins|VipPoints> <Jugador>");
                Util.sendMessage(sender, "&d&m===================================");
                return true;
            }
            if(args[0].equalsIgnoreCase("reset")){
                if(args.length == 3){
                    if(args[1].equalsIgnoreCase("LCoins")){
                        Player player = Bukkit.getPlayer(args[2]);
                        if(player == null || !player.isOnline()){
                            Util.sendMessage(sender, "&c"+args[2] +" no esta conectado.");
                            return true;
                        }

                        Jugador j = Jugador.getJugador(player);
                        j.setCoins(0);
                        LCoinsQuery.savePlayerCoins_ASYNC(j);
                        Util.sendMessage(sender, "&fRestauraste las LCoins de &a"+j.getNombre()+"&f.");
                        return true;
                    }
                    if(args[1].equalsIgnoreCase("VipPoints")){
                        Player player = Bukkit.getPlayer(args[2]);
                        if(player == null || !player.isOnline()){
                            Util.sendMessage(sender, "&c"+args[2] +" no esta conectado.");
                            return true;
                        }

                        Jugador j = Jugador.getJugador(player);
                        j.setVippoints(0);
                        VipPointsQuery.savePlayerVipPoints_ASYNC(j);
                        Util.sendMessage(sender, "&fRestauraste las VipPoints de &a"+j.getNombre()+"&f.");
                        return true;
                    }
                }
                Util.sendMessage(sender, "&d  /eco reset <LCoins|VipPoints> <Jugador>");
                return true;
            }
            if(args[0].equalsIgnoreCase("give")){
                if(args.length == 4){

                    int give;

                    try {
                        give = Integer.parseInt(args[3]);
                    }catch (NumberFormatException e){
                        Util.sendMessage(sender, "&c"+args[3]+" No  es un numero valido.");
                        return true;
                    }

                    if(args[1].equalsIgnoreCase("LCoins")){
                        Player player = Bukkit.getPlayer(args[2]);
                        if(player == null || !player.isOnline()){
                            Util.sendMessage(sender, "&c"+args[2] +" no esta conectado.");
                            return true;
                        }

                        Jugador j = Jugador.getJugador(player);
                        j.setCoins(j.getCoins() + give);
                        LCoinsQuery.savePlayerCoins_ASYNC(j);
                        Util.sendMessage(sender, "&fAñadiste &e"+give+"&f LCoins a &a"+j.getNombre()+"&f.");
                        return true;
                    }
                    if(args[1].equalsIgnoreCase("VipPoints")){
                        Player player = Bukkit.getPlayer(args[2]);
                        if(player == null || !player.isOnline()){
                            Util.sendMessage(sender, "&c"+args[2] +" no esta conectado.");
                            return true;
                        }

                        Jugador j = Jugador.getJugador(player);
                        j.setVippoints(j.getVippoints() + give);
                        VipPointsQuery.savePlayerVipPoints_ASYNC(j);
                        Util.sendMessage(sender, "&fAñadiste &e"+give+" &fLCoins a &a"+j.getNombre()+"&f.");

                        return true;
                    }
                }
                Util.sendMessage(sender, "&d  /eco reset <LCoins|VipPoints> <Jugador>");
                return true;
            }
            if(args[0].equalsIgnoreCase("set")){
                if(args.length == 4){

                    int set;

                    try {
                        set = Integer.parseInt(args[3]);
                    }catch (NumberFormatException e){
                        Util.sendMessage(sender, "&c"+args[3]+" No  es un numero valido.");
                        return true;
                    }

                    if(args[1].equalsIgnoreCase("LCoins")){
                        Player player = Bukkit.getPlayer(args[2]);
                        if(player == null || !player.isOnline()){
                            Util.sendMessage(sender, "&c"+args[2] +" no esta conectado.");
                            return true;
                        }

                        Jugador j = Jugador.getJugador(player);
                        j.setCoins(set);
                        LCoinsQuery.savePlayerCoins_ASYNC(j);
                        Util.sendMessage(sender, "&fEstableciste en &e"+set+" &flas LCoins a &a"+j.getNombre()+"&f.");
                        return true;
                    }
                    if(args[1].equalsIgnoreCase("VipPoints")){
                        Player player = Bukkit.getPlayer(args[2]);
                        if(player == null || !player.isOnline()){
                            Util.sendMessage(sender, "&c"+args[2] +" no esta conectado.");
                            return true;
                        }

                        Jugador j = Jugador.getJugador(player);
                        j.setVippoints(set);
                        VipPointsQuery.savePlayerVipPoints_ASYNC(j);
                        Util.sendMessage(sender, "&fEstableciste en &e"+set+" &flas VipPoints a &a"+j.getNombre()+"&f.");

                        return true;
                    }
                }
                Util.sendMessage(sender, "&d  /eco reset <LCoins|VipPoints> <Jugador>");
                return true;
            }
            if(args[0].equalsIgnoreCase("take")){
                if(args.length == 4){

                    int take;

                    try {
                        take = Integer.parseInt(args[3]);
                    }catch (NumberFormatException e){
                        Util.sendMessage(sender, "&c"+args[3]+" No  es un numero valido.");
                        return true;
                    }

                    if(args[1].equalsIgnoreCase("LCoins")){
                        Player player = Bukkit.getPlayer(args[2]);
                        if(player == null || !player.isOnline()){
                            Util.sendMessage(sender, "&c"+args[2] +" no esta conectado.");
                            return true;
                        }

                        Jugador j = Jugador.getJugador(player);
                        j.setCoins(j.getCoins() - take);
                        LCoinsQuery.savePlayerCoins_ASYNC(j);
                        Util.sendMessage(sender, "&fRemoviste &e"+take+"&f LCoins a &a"+j.getNombre()+"&f.");
                        return true;
                    }
                    if(args[1].equalsIgnoreCase("VipPoints")){
                        Player player = Bukkit.getPlayer(args[2]);
                        if(player == null || !player.isOnline()){
                            Util.sendMessage(sender, "&c"+args[2] +" no esta conectado.");
                            return true;
                        }

                        Jugador j = Jugador.getJugador(player);
                        j.setVippoints(j.getVippoints() - take);
                        VipPointsQuery.savePlayerVipPoints_ASYNC(j);
                        Util.sendMessage(sender, "&fRemoviste &e"+take+" &fVipPoints a &a"+j.getNombre()+"&f.");
                        return true;
                    }
                }
                Util.sendMessage(sender, "&d  /eco reset <LCoins|VipPoints> <Jugador>");
                return true;
            }
            return true;
        }
        Util.sendMessage(sender, "&e&lECONOMIA &cSolo la consola puede ejecutar este comando.");
        return true;
    }
}
