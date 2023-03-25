package com.github.tobiasmiosczka.discordmetricscollector.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class DiscordGuild {

    @Id
    private Long id;
    private String name;

    @OneToMany
    private List<DiscordVoiceChannel> voiceChannels;

    public DiscordGuild() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DiscordVoiceChannel> getVoiceChannels() {
        return voiceChannels;
    }

    public void setVoiceChannels(List<DiscordVoiceChannel> voiceChannels) {
        this.voiceChannels = voiceChannels;
    }
}
