package com.github.tobiasmiosczka.discordmetricscollector.service;

import com.github.tobiasmiosczka.discordmetricscollector.model.DiscordGuild;
import com.github.tobiasmiosczka.discordmetricscollector.model.DiscordVoiceChannel;
import com.github.tobiasmiosczka.discordmetricscollector.repository.DiscordGuildRepository;
import com.github.tobiasmiosczka.discordmetricscollector.repository.DiscordVoiceChannelRepository;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscordSynchronizationService {

    private final DiscordGuildRepository discordGuildRepository;
    private final DiscordVoiceChannelRepository discordVoiceChannelRepository;

    @Autowired
    public DiscordSynchronizationService(final DiscordGuildRepository discordGuildRepository, DiscordVoiceChannelRepository discordVoiceChannelRepository) {
        this.discordGuildRepository = discordGuildRepository;
        this.discordVoiceChannelRepository = discordVoiceChannelRepository;
    }

    public void updateGuilds(List<Guild> guilds) {
        for (Guild guild : guilds) {
            this.updateGuild(guild);
        }
    }

    public DiscordGuild updateGuild(Guild guild) {
        DiscordGuild discordGuild = discordGuildRepository.save(convert(guild));
        discordGuild.setVoiceChannels(updateVoiceChannel(guild.getVoiceChannels()));
        return discordGuild;
    }

    private List<DiscordVoiceChannel> updateVoiceChannel(List<VoiceChannel> voiceChannels) {
        Iterable<DiscordVoiceChannel> discordVoiceChannels = discordVoiceChannelRepository.saveAll(convert(voiceChannels));
        List<DiscordVoiceChannel> result = new ArrayList<>();
        for (DiscordVoiceChannel discordVoiceChannel : discordVoiceChannels) {
            result.add(discordVoiceChannel);
        }
        return result;
    }

    private static DiscordGuild convert(final Guild guild) {
        DiscordGuild discordGuild = new DiscordGuild();
        discordGuild.setId(guild.getIdLong());
        discordGuild.setName(guild.getName());
        return discordGuild;
    }

    private static List<DiscordVoiceChannel> convert(final List<VoiceChannel> voiceChannels) {
        return voiceChannels.stream()
                .map(DiscordSynchronizationService::convert)
                .toList();
    }

    private static DiscordVoiceChannel convert(final VoiceChannel voiceChannel) {
        DiscordVoiceChannel discordVoiceChannel = new DiscordVoiceChannel();
        discordVoiceChannel.setId(voiceChannel.getIdLong());
        discordVoiceChannel.setGuild(convert(voiceChannel.getGuild()));
        discordVoiceChannel.setName(voiceChannel.getName());
        return discordVoiceChannel;
    }
}
