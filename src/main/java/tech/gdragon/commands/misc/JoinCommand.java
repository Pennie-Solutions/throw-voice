package tech.gdragon.commands.misc;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import tech.gdragon.DiscordEcho;
import tech.gdragon.commands.Command;


public class JoinCommand implements Command {

  @Override
  public Boolean called(String[] args, GuildMessageReceivedEvent e) {
    return true;
  }

  @Override
  public void action(String[] args, GuildMessageReceivedEvent e) {
    if (args.length != 0) {


      return;
    }

    if (e.getGuild().getAudioManager().getConnectedChannel() != null &&
      e.getGuild().getAudioManager().getConnectedChannel().getMembers().contains(e.getMember())) {
      DiscordEcho.sendMessage(e.getChannel(), "I am already in your channel!");
      return;
    }

    if (e.getMember().getVoiceState().getChannel() == null) {
      DiscordEcho.sendMessage(e.getChannel(), "You need to be in a voice channel to use this command!");
      return;
    }

    //write out previous channel's audio if autoSave is on
    if (e.getGuild().getAudioManager().isConnected() && DiscordEcho.serverSettings.get(e.getGuild().getId()).autoSave)
      DiscordEcho.writeToFile(e.getGuild());

    DiscordEcho.joinVoiceChannel(e.getMember().getVoiceState().getChannel(), true);
  }

  @Override
  public String usage(String prefix) {
    return prefix + "join";
  }

  @Override
  public String descripition() {
    return "Force the bot to join and record your current channel";
  }

  @Override
  public void executed(boolean success, GuildMessageReceivedEvent e) {
    return;
  }
}