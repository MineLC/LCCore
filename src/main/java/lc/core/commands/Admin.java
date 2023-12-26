package lc.core.commands;

import lc.core.LCCore;
import lc.core.admin.AdminAction;
import lc.core.admin.AdminActionType;
import lc.core.entidades.Jugador;
import lc.core.entidades.database.RankInfoQuery;
import lc.core.utilidades.Util;
import lc.core.entidades.Rango;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class Admin implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player && !Jugador.getJugador(sender.getName()).isAdmin()){
            Util.sendMessage(sender, "&e&lMine&b&lLC &cDebes de ser &4&lADMIN &cpara poder ocupar este comando.");
            return true;
        }
        if(args.length == 0){
            Util.sendMessage(sender, "&e&lMine&b&lLC  &c/admin rango help");
            return true;
        }
        if(args[0].equalsIgnoreCase("cancelar")){
            if(sender instanceof Player && LCCore.adminConfirm.containsKey(sender.getName())){
                LCCore.adminConfirm.remove(sender.getName());
                Util.sendMessage(sender, "&e&lMine&b&lLC &aSolicitud cancelada.");
            }else{
                Util.sendMessage(sender, "&e&lMine&b&lLC &cNo tienes ninguna solicitud pendiente.");
                return true;
            }
        }
        if(args[0].equalsIgnoreCase("confirmar")){
            if(sender instanceof Player && LCCore.adminConfirm.containsKey(sender.getName())){
                if(args.length > 1){
                    if(args[1].equals("LCNetwork.descuido")) {
                        AdminAction adminAction = LCCore.adminConfirm.get(sender.getName());
                        if (adminAction.getType() == AdminActionType.SET_RANK) {
                            Rango rango = (Rango) adminAction.getValue();
                            if (adminAction.getTo() == null) {
                                long duration;
                                switch (rango) {
                                    case VIP:
                                    case SVIP:
                                    case ELITE:
                                    case RUBY:
                                        duration = 200;
                                        break;
                                    default:
                                        duration = 0;
                                        break;
                                }
                                adminAction.getFrom().getRankInfo().setRango(rango);
                                if (adminAction.getFrom().getRankInfo().getDuration() == 0)
                                    adminAction.getFrom().getRankInfo().setDuration(duration);
                                RankInfoQuery.savePlayerRango(adminAction.getFrom());
                                Util.sendMessage(sender, "&e&lMine&b&lLC &aCambiaste tu rango a &e"+ StringUtils.capitalize(rango.name().toLowerCase())+"&a.");
                            }else{
                                Jugador jugador = adminAction.getTo();
                                long duration;
                                switch (rango) {
                                    case VIP:
                                    case SVIP:
                                    case ELITE:
                                    case RUBY:
                                        duration = 200;
                                        break;
                                    default:
                                        duration = 0;
                                        break;
                                }
                                adminAction.getTo().getRankInfo().setRango(rango);
                                if (adminAction.getTo().getRankInfo().getDuration() == 0)
                                    adminAction.getTo().getRankInfo().setDuration(duration);
                                RankInfoQuery.savePlayerRango(jugador);
                                Util.sendMessage(sender, "&e&lMine&b&lLC &aCambiaste el rango de &e"+jugador.getNombre()+" a &e"+ StringUtils.capitalize(rango.name().toLowerCase())+"&a.");
                            }
                            LCCore.adminConfirm.remove(sender.getName());
                            return true;
                        }
                        if (adminAction.getType() == AdminActionType.SET_RANGO_ESCONDIDO) {
                            if (adminAction.getTo() == null) {
                                adminAction.getFrom().getRankInfo().setHideRank(!adminAction.getFrom().getRankInfo().isHideRank());
                                RankInfoQuery.savePlayerRango(adminAction.getFrom());
                                Util.sendMessage(sender, "&e&lMine&b&lLC &aHas "+(adminAction.getFrom().getRankInfo().isHideRank() ? "activado" : "desactivado")+" el rango escondido.");
                            }else{
                                Jugador jugador = adminAction.getTo();
                                jugador.getRankInfo().setHideRank(!adminAction.getFrom().getRankInfo().isHideRank());
                                RankInfoQuery.savePlayerRango(jugador);
                                Util.sendMessage(sender, "&e&lMine&b&lLC &aHas "+(jugador.getRankInfo().isHideRank() ? "activado" : "desactivado")+" el rango escondido de &e"+jugador.getNombre()+"&a.");
                            }
                            LCCore.adminConfirm.remove(sender.getName());
                            return true;
                        }
                        if (adminAction.getType() == AdminActionType.SET_PUNTOS) {
                            int puntos = (int) adminAction.getValue();
                            if (adminAction.getTo() == null) {
                                adminAction.getFrom().getRankInfo().setPuntos(puntos);
                                RankInfoQuery.savePlayerRango(adminAction.getFrom());
                                Util.sendMessage(sender, "&e&lMine&b&lLC &aHas establecido tus RankPoints en &e"+puntos+"&a.");
                            }else{
                                Jugador jugador = adminAction.getTo();
                                jugador.getRankInfo().setPuntos(puntos);
                                RankInfoQuery.savePlayerRango(jugador);
                                Util.sendMessage(sender, "&e&lMine&b&lLC &aHas establecido del RankPoints de &e"+jugador.getNombre()+"&a a &e"+puntos+"&a.");
                            }
                            LCCore.adminConfirm.remove(sender.getName());
                            return true;
                        }
                        if (adminAction.getType() == AdminActionType.SET_NAME_COLOR) {
                            String nameColor = (String) adminAction.getValue();
                            if (adminAction.getTo() == null) {
                                adminAction.getFrom().getRankInfo().setNameColor(nameColor);
                                RankInfoQuery.savePlayerRango(adminAction.getFrom());
                                Util.sendMessage(sender, "&e&lMine&b&lLC &aHas establecido tu NameColor en &r"+nameColor+adminAction.getFrom().getNombre()+"&a.");
                            }else{
                                Jugador jugador = adminAction.getTo();
                                jugador.getRankInfo().setNameColor(nameColor);
                                RankInfoQuery.savePlayerRango(jugador);
                                Util.sendMessage(sender, "&e&lMine&b&lLC &aHas establecido el NameColor de &e"+jugador.getNombre()+"&a en &r"+nameColor+adminAction.getFrom().getNombre()+"&a.");
                            }
                            LCCore.adminConfirm.remove(sender.getName());
                            return true;
                        }
                        if (adminAction.getType() == AdminActionType.SET_DURACION) {
                            long duration = (long) adminAction.getValue();
                            Rango rango;
                            boolean can = false;
                            if (adminAction.getTo() == null) {
                                rango = adminAction.getFrom().getRankInfo().getRango();
                                switch (rango){
                                    case ELITE:
                                    case SVIP:
                                    case VIP:
                                    case RUBY:
                                        can = true;
                                        break;
                                }
                                if(!can){
                                    Util.sendMessage(sender, "&e&lMine&b&lLC &cNo puede cambiarle la duración a tu rango, si este es permanente.");
                                    return true;
                                }
                                adminAction.getFrom().getRankInfo().setDuration(duration);
                                RankInfoQuery.savePlayerRango(adminAction.getFrom());
                                Util.sendMessage(sender, "&e&lMine&b&lLC &aCambiaste tu duracion de rango a &e"+ duration +"&a.");
                            }else{
                                rango = adminAction.getTo().getRankInfo().getRango();
                                switch (rango){
                                    case ELITE:
                                    case SVIP:
                                    case VIP:
                                    case RUBY:
                                        can = true;
                                        break;
                                }
                                if(!can){
                                    Util.sendMessage(sender, "&e&lMine&b&lLC &cNo puedes cambiarle la duración al rango de &4"+adminAction.getTo().getNombre()+"&c, si este es permanente.");
                                    return true;
                                }
                                adminAction.getTo().getRankInfo().setDuration(duration);
                                RankInfoQuery.savePlayerRango(adminAction.getTo());
                                Util.sendMessage(sender, "&e&lMine&b&lLC &aCambiaste la duracion de rango de &e"+adminAction.getTo().getNombre()+" &aa &e"+ duration +"&a.");
                            }
                            LCCore.adminConfirm.remove(sender.getName());
                            return true;
                        }

                    }else{
                        Util.sendMessage(sender, "&e&lMine&b&lLC &cClave incorrecta.");
                    }
                }else{
                    Util.sendMessage(sender, "&e&lMine&b&lLC &cFata la Clave Secreta.");
                    return true;
                }
            }else{
                Util.sendMessage(sender, "&e&lMine&b&lLC &cNo tienes ninguna solicitud pendiente.");
                return true;
            }
        }

        if(sender instanceof Player && LCCore.adminConfirm.containsKey(sender.getName())){
            Util.sendMessage(sender, "&e&lMine&b&lLC &cDebes de confirmar o cancelar tu solicitud:");
            Util.sendMessage(sender, "&e&lMine&b&lLC  &c/admin cancelar");
            Util.sendMessage(sender, "&e&lMine&b&lLC  &c/admin confirmar &a<CodigoSecreto>");
            return true;
        }

        if(args[0].equalsIgnoreCase("rango")){
            if(args.length > 1){
                if(args[1].equalsIgnoreCase("help")){
                    Util.sendMessage(sender, "&e&lMine&b&lLC &eRecuerda: &a<> &fObligatorio &7- &c[] &fOpcional");
                    Util.sendMessage(sender, "&e&lMine&b&lLC  &c/admin rango set &a<Rango> &c[Jugador]");
                    Util.sendMessage(sender, "&e&lMine&b&lLC  &c/admin rango setpuntos &a<PuntosI> &c[Jugador]");
                    Util.sendMessage(sender, "&e&lMine&b&lLC  &c/admin rango setduration &a<DurationL> &c[Jugador]");
                    Util.sendMessage(sender, "&e&lMine&b&lLC  &c/admin rango setnamecolor &a<&xColor> &c[Jugador]");
                    Util.sendMessage(sender, "&e&lMine&b&lLC  &c/admin rango togglerangoescondido &c[Jugador]");
                    return true;
                }
                if(args[1].equalsIgnoreCase("set")){
                    if(args.length == 3){
                        if(sender instanceof Player){
                            Rango rango;
                            try {
                                rango = Rango.valueOf(args[2]);
                            }catch (IllegalArgumentException e){
                                Util.sendMessage(sender, "&e&lMine&b&lLC &cRango desconocido.");
                                Util.sendMessage(sender, "&e&lMine&b&lLC &cDEFAULT, VIP, SVIP, ELITE, RUBY, MINIYT, YOUTUBER, STREAMER, HELPER, MOD, ADMIN, OWNER.");
                                return false;
                            }
                            AdminAction<Rango> adminAction = new AdminAction<>(AdminActionType.SET_RANK, Jugador.getJugador(sender.getName()), null);
                            adminAction.setValue(rango);
                            LCCore.adminConfirm.put(sender.getName(), adminAction);
                            Util.sendMessage(sender, "&e&lMine&b&lLC &aPara confirmar tu solicitudad ejecute: /admin confirmar &a<CodigoSecreto>");
                            Util.sendMessage(sender, "&e&lMine&b&lLC &ao para cancelar: /admin cancelar");
                        }else{
                            Util.sendMessage(sender, "&e&lMine&b&lLC  &cNo eres un jugador.");
                        }
                    }else{
                        Util.sendMessage(sender, "&e&lMine&b&lLC  &c/admin rango set &a<Rango> &c[Jugador]");
                    }
                    return true;
                }
                if(args[1].equalsIgnoreCase("togglerangoescondido")){
                    if(args.length == 3){
                        if(sender instanceof Player){
                            AdminAction<Object> adminAction = new AdminAction<>(AdminActionType.SET_RANGO_ESCONDIDO, Jugador.getJugador(sender.getName()), null);
                            LCCore.adminConfirm.put(sender.getName(), adminAction);
                            Util.sendMessage(sender, "&e&lMine&b&lLC &aPara confirmar tu solicitudad ejecute: /admin confirmar &a<CodigoSecreto>");
                            Util.sendMessage(sender, "&e&lMine&b&lLC &ao para cancelar: /admin cancelar");
                        }else{
                            Util.sendMessage(sender, "&e&lMine&b&lLC  &cNo eres un jugador.");
                        }
                    }else{
                        Util.sendMessage(sender, "&e&lMine&b&lLC  &c/admin rango togglerangoescondido &c[Jugador]");
                    }
                    return true;
                }
                if(args[1].equalsIgnoreCase("setpuntos")){
                    if(args.length == 3){
                        if(sender instanceof Player){
                            int puntos;
                            try {
                                puntos = Integer.parseInt(args[2]);
                            }catch (IllegalArgumentException e){
                                Util.sendMessage(sender, "&e&lMine&b&lLC &cIngresa un entero valido.");
                                return false;
                            }
                            AdminAction<Integer> adminAction = new AdminAction<>(AdminActionType.SET_PUNTOS, Jugador.getJugador(sender.getName()), null);
                            adminAction.setValue(puntos);
                            LCCore.adminConfirm.put(sender.getName(), adminAction);
                            Util.sendMessage(sender, "&e&lMine&b&lLC &aPara confirmar tu solicitudad ejecute: /admin confirmar &a<CodigoSecreto>");
                            Util.sendMessage(sender, "&e&lMine&b&lLC &ao para cancelar: /admin cancelar");
                        }else{
                            Util.sendMessage(sender, "&e&lMine&b&lLC  &cNo eres un jugador.");
                        }
                    }else{
                        Util.sendMessage(sender, "&e&lMine&b&lLC  &c/admin rango setpuntos &a<PuntosI> &c[Jugador]");
                    }
                    return true;
                }
                if(args[1].equalsIgnoreCase("setnamecolor")){
                    if(args.length == 3){
                        if(sender instanceof Player){
                            if(!args[2].startsWith("&") || args[2].length() > 4){
                                Util.sendMessage(sender, "&e&lMine&b&lLC &cUsa /admin rango setname color &XColorCode");
                                return true;
                            }
                            AdminAction<String> adminAction = new AdminAction<>(AdminActionType.SET_PUNTOS, Jugador.getJugador(sender.getName()), null);
                            adminAction.setValue(args[2]);
                            LCCore.adminConfirm.put(sender.getName(), adminAction);
                            Util.sendMessage(sender, "&e&lMine&b&lLC &aPara confirmar tu solicitudad ejecute: /admin confirmar &a<CodigoSecreto>");
                            Util.sendMessage(sender, "&e&lMine&b&lLC &ao para cancelar: /admin cancelar");
                        }else{
                            Util.sendMessage(sender, "&e&lMine&b&lLC  &cNo eres un jugador.");
                        }
                    }else{
                        Util.sendMessage(sender, "&e&lMine&b&lLC  &c/admin rango setnamecolor &a<&xColor> &c[Jugador]");
                    }
                    return true;
                }
            }else{
                Util.sendMessage(sender, "&e&lMine&b&lLC &eRecuerda: &a<> &fObligatorio &7- &c[] &fOpcional");
                Util.sendMessage(sender, "&e&lMine&b&lLC  &c/admin rango set &a<Rango> &c[Jugador]");
                Util.sendMessage(sender, "&e&lMine&b&lLC  &c/admin rango setpuntos &a<PuntosI> &c[Jugador]");
                Util.sendMessage(sender, "&e&lMine&b&lLC  &c/admin rango setduration &a<DurationL> &c[Jugador]");
                Util.sendMessage(sender, "&e&lMine&b&lLC  &c/admin rango setnamecolor &a<&xColor> &c[Jugador]");
                Util.sendMessage(sender, "&e&lMine&b&lLC  &c/admin rango togglerangoescondido &c[Jugador]");
                return true;
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> ret = new ArrayList<>();
        if(args.length == 0){
            ret.add("rango");
            return ret;
        }
        return null;
    }
}
