package com.github.tobiasmiosczka.discordmetricscollector;

import com.github.tobiasmiosczka.discordmetricscollector.model.DiscordGuild;
import com.github.tobiasmiosczka.discordmetricscollector.service.DiscordSynchronizationService;
import com.github.tobiasmiosczka.discordmetricscollector.service.DiscordVoiceChannelUsageTrackingService;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.channel.update.GenericChannelUpdateEvent;
import net.dv8tion.jda.api.events.guild.update.GenericGuildUpdateEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.LocalDateTime;

public class MetricsCollector extends ListenerAdapter {

    private final JDA jda;
    private final DiscordVoiceChannelUsageTrackingService discordVoiceChannelUsageTrackingService;
    private final DiscordSynchronizationService discordSynchronizationService;

    public MetricsCollector(final JDA jda, final DiscordVoiceChannelUsageTrackingService discordVoiceChannelUsageTrackingService, DiscordSynchronizationService discordSynchronizationService) {
        jda.addEventListener(this);
        this.jda = jda;
        this.discordVoiceChannelUsageTrackingService = discordVoiceChannelUsageTrackingService;
        this.discordSynchronizationService = discordSynchronizationService;
        discordSynchronizationService.updateGuilds(jda.getGuilds());
    }

    @Override
    public void onGenericChannelUpdate(GenericChannelUpdateEvent<?> event) {
        if (event.isFromGuild()) {
            event.getGuild();
            discordSynchronizationService.updateGuild(event.getGuild());
        }
    }

    @Override
    public void onGenericGuildUpdate(GenericGuildUpdateEvent event) {
        discordSynchronizationService.updateGuild(event.getGuild());
    }

    @Override
    public void onGuildVoiceUpdate(final GuildVoiceUpdateEvent event) {
        if (event.getChannelJoined() != null) {
            discordVoiceChannelUsageTrackingService.trackJoin(event.getMember().getIdLong(), event.getChannelJoined().getIdLong(), LocalDateTime.now());
        }
        if (event.getChannelLeft() != null) {
            discordVoiceChannelUsageTrackingService.trackLeft(event.getMember().getIdLong(), event.getChannelLeft().getIdLong(), LocalDateTime.now());
        }
    }
}
