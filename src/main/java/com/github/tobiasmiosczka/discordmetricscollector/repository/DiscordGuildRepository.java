package com.github.tobiasmiosczka.discordmetricscollector.repository;

import com.github.tobiasmiosczka.discordmetricscollector.model.DiscordGuild;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscordGuildRepository extends CrudRepository<DiscordGuild, Long> {
}
