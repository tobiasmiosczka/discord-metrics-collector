package com.github.tobiasmiosczka.discordmetricscollector.service;

import com.github.tobiasmiosczka.discordmetricscollector.model.DiscordVoiceChannel;
import com.github.tobiasmiosczka.discordmetricscollector.model.DiscordVoiceChannelUsage;
import com.github.tobiasmiosczka.discordmetricscollector.repository.DiscordVoiceChannelUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DiscordVoiceChannelUsageTrackingService {

    private final DiscordVoiceChannelUsageRepository discordVoiceChannelUsageRepository;
    private final Map<Long, LocalDateTime> cache = new HashMap();

    @Autowired
    public DiscordVoiceChannelUsageTrackingService(DiscordVoiceChannelUsageRepository discordVoiceChannelUsageRepository) {
        this.discordVoiceChannelUsageRepository = discordVoiceChannelUsageRepository;
    }

    public void trackJoin(long idUser, long idVoiceChannel, LocalDateTime time) {
        cache.put(idUser, time);
    }

    public void trackLeft(long idUser, long idVoiceChannel, LocalDateTime time) {
        if (cache.containsKey(idUser)) {
            discordVoiceChannelUsageRepository.save(create(idUser, idVoiceChannel, cache.get(idUser), time));
        }
    }

    private static DiscordVoiceChannelUsage create(long idUser, long idVoiceChannel, LocalDateTime from, LocalDateTime to) {
        DiscordVoiceChannel discordVoiceChannel = new DiscordVoiceChannel();
        discordVoiceChannel.setId(idVoiceChannel);

        DiscordVoiceChannelUsage discordVoiceChannelUsage = new DiscordVoiceChannelUsage();
        discordVoiceChannelUsage.setVoiceChannel(discordVoiceChannel);

        discordVoiceChannelUsage.setTimeFrom(from);
        discordVoiceChannelUsage.setTimeTo(to);

        return discordVoiceChannelUsage;
    }
}
