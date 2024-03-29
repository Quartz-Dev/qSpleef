package com.QarthO.Spleef.utils;

import org.bukkit.ChatColor;

public enum Language {
		
	//Chat Prefix
	CHAT_PREFIX(false, ChatColor.AQUA + "[" + ChatColor.GRAY + "%n" + ChatColor.AQUA + "] " + ChatColor.RESET),
	
	CMD_INFO(true, ChatColor.LIGHT_PURPLE +  "%n v%v - " + ChatColor.YELLOW + "qDev" + ChatColor.LIGHT_PURPLE + " Plugin\n" 
			+ "%p" + ChatColor.LIGHT_PURPLE + "Try " + ChatColor.YELLOW + "/%c help" + ChatColor.LIGHT_PURPLE + " for more info"),
	
	
	//Chat UI Border
	BORDER_TOP(false, ChatColor.AQUA + "" + ChatColor.STRIKETHROUGH + "   " + ChatColor.AQUA + "[" + ChatColor.GRAY + " %n v%v" + ChatColor.AQUA + " ]" + ChatColor.STRIKETHROUGH + "                                        "),
	BORDER_BOTTOM(false, ChatColor.AQUA + "" + ChatColor.STRIKETHROUGH + "                                                              "),
	
	//Help
	HELP(false, ChatColor.YELLOW + "/spleef\n"
			+ "/%c join <arena_name>\n" 
			+ "/%c leave <arena_name>\n"
			+ "/%c spectate <arena_name>\n"
			+ "/%c list"),
	
	HELP_MOD(false, ChatColor.YELLOW + "/%c force <start/end> <arena_name>"),
	
	HELP_ADMIN(false, ChatColor.YELLOW + "/%c create <arena_name>\n"
			+ "/%c delete <arena_name>\n"
			+ "/%c set <zone/start/spectate>"),
	
	
	
	// Syntax
	SYNTAX_CREATE(true, ChatColor.RED + "Syntax: /spleef create <arena_name>"),
	SYNTAX_DELETE(true, ChatColor.RED + "Syntax: /spleef delete <arena_name>"),
	SYNTAX_SET(true, ChatColor.RED + "Syntax: /spleef set <join/spec/zone>"),
	SYNTAX_RESET(true, ChatColor.RED + "Syntax: /spleef reset <arena_name>"),
	
	SYNTAX(true, ChatColor.RED + "Syntax: %syntax"),
	
	
	//Errors - In game
	ERROR_CONSOLE_MESSAGE(true, ChatColor.RED + "Error: In game command only"),
	ERROR_INCORRECT_USAGE(true, ChatColor.RED + "Error: Incorrect usage"),
	ERROR_NO_PERMISSIONS(true, ChatColor.RED + "Error: Insuffictient permissions"),
	ERROR_PLAYER_NOT_FOUND(true, ChatColor.RED + "Error: Player not found"),
	ERROR_ARENA_NOT_FOUND(true, ChatColor.RED + "Error: Arena not found"),
	ERROR_UKNOWN_COMMAND(true, ChatColor.RED + "Not a valid command\n%p" + ChatColor.RED + "Try /%c help"),
	
	//CMD Responses

	
	//Succes/Errors - Plugin
	ARENA_YML(true, ChatColor.GREEN + "Generated 'qArena.yml'"),
	ERROR_FAILED_ARENA_YML_CREATE(true, ChatColor.RED + "Failed creating 'qArenas.yml'. Reload server, if problem persists contact qDeveloper"),
	ERROR_FAILED_ARENA_YML_SAVE(true, ChatColor.RED + "Failed saving 'qArenas.yml'"),
	
	LOCATION(false, "(x, y, z)");
	
	String Message;
	private PluginYML yml = new PluginYML();
	private String version = yml.getPluginVersion();
	private String project_name = yml.getProjectName();
	private String author = yml.getPluginAuthor();
	private String cmd = "spleef";
	private String msg_prefix = ChatColor.AQUA + "[" + ChatColor.GRAY + project_name + ChatColor.AQUA + "] " + ChatColor.RESET;
	
	private Language(boolean prefix, String message) {
		
		message = message.replaceAll("%a", author);
		message = message.replaceAll("%v", version);
		message = message.replaceAll("%n", project_name);
		message = message.replaceAll("%c", cmd);
		message = message.replaceAll("%p", msg_prefix);
		
		if(prefix) this.Message = msg_prefix + message;	
		else this.Message = message;
	}
	
	public String getMessage() {
		return Message;
	}

}
