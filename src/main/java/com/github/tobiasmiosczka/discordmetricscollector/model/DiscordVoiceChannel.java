package com.github.tobiasmiosczka.discordmetricscollector.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DiscordVoiceChannel {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "guild")
    private DiscordGuild guild;
    private String name;

    public DiscordVoiceChannel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DiscordGuild getGuild() {
        return guild;
    }

    public void setGuild(DiscordGuild guild) {
        this.guild = guild;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
