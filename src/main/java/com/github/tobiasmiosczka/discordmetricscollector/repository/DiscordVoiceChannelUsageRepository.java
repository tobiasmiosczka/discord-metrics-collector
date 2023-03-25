package com.github.tobiasmiosczka.discordmetricscollector.repository;

import com.github.tobiasmiosczka.discordmetricscollector.model.DiscordVoiceChannelUsage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscordVoiceChannelUsageRepository extends CrudRepository<DiscordVoiceChannelUsage, Long> {
}
