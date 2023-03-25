package com.github.tobiasmiosczka.discordmetricscollector;

import com.github.tobiasmiosczka.discordmetricscollector.service.DiscordSynchronizationService;
import com.github.tobiasmiosczka.discordmetricscollector.service.DiscordVoiceChannelUsageTrackingService;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.Compression;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscordConfig {

    private static final String IS_PLAYING_STRING = "you";

    @Value(value = "${discord.apikey}")
    private String apiKey;

    @Bean
    public JDA jda() throws InterruptedException {
        JDA jda = JDABuilder.createDefault(apiKey)
                .setBulkDeleteSplittingEnabled(false)
                .setCompression(Compression.ZLIB)
                .setActivity(Activity.watching(IS_PLAYING_STRING))
                .build()
                .awaitReady();
        jda.addEventListener();
        return jda;
    }

    @Bean
    public MetricsCollector metricsCollector(JDA jda, DiscordVoiceChannelUsageTrackingService discordVoiceChannelUsageTrackingService, DiscordSynchronizationService discordSynchronizationService) {
        MetricsCollector metricsCollector = new MetricsCollector(jda, discordVoiceChannelUsageTrackingService, discordSynchronizationService);
        return metricsCollector;
    }
}
