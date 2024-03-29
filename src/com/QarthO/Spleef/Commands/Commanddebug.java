package com.QarthO.Spleef.Commands;

import java.util.List;

import org.bukkit.entity.Player;

import com.QarthO.Spleef.CommandsManager;
import com.QarthO.Spleef.Arena.ArenasManager;
import com.QarthO.Spleef.Game.Game;
import com.QarthO.Spleef.Game.GamesManager;
import com.QarthO.Spleef.utils.Language;

public class Commanddebug extends qCommand {

	public Commanddebug(CommandsManager cm, ArenasManager am, GamesManager gm) {
		super(cm, am, gm);
		name = "delete";
		perms = "spleef.admin.delete";
		syntax = "/spleef debug <param>";
	}

	@Override
	public void run(Player player, String[] args) {
		
		if(!player.hasPermission(perms)) {
			player.sendMessage(Language.ERROR_NO_PERMISSIONS.getMessage());
			return;
		}
//		if(args.length != 2) {
//			player.sendMessage(FormatMessage.syntax(syntax));
//			return;
//		}
		
		if(args.length == 2) {
			String arenaName = args[1];
			Game game = gm.getGame(arenaName);
			player.sendMessage(Language.CHAT_PREFIX.getMessage() + game.getPlayers());
			player.sendMessage(Language.CHAT_PREFIX.getMessage() + game.getInPlayers());
		}
		
	}

	@Override
	public List<String> getTabs(Player player, String[] args) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
