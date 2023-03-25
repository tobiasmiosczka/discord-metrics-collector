package com.github.tobiasmiosczka.discordmetricscollector.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class DiscordVoiceChannelUsage {

    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "voiceChannel")
    DiscordVoiceChannel voiceChannel;
    LocalDateTime timeFrom;
    LocalDateTime timeTo;

    public DiscordVoiceChannelUsage() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DiscordVoiceChannel getVoiceChannel() {
        return voiceChannel;
    }

    public void setVoiceChannel(DiscordVoiceChannel voiceChannel) {
        this.voiceChannel = voiceChannel;
    }

    public LocalDateTime getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(LocalDateTime timeFrom) {
        this.timeFrom = timeFrom;
    }

    public LocalDateTime getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(LocalDateTime timeTo) {
        this.timeTo = timeTo;
    }
}
