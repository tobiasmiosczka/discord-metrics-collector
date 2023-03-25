package com.github.tobiasmiosczka.discordmetricscollector.repository;

import com.github.tobiasmiosczka.discordmetricscollector.model.DiscordVoiceChannel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscordVoiceChannelRepository extends CrudRepository<DiscordVoiceChannel, Long> {
}
