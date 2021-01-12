package com.example.nbascore.Model.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL="https://www.balldontlie.io/api/v1/"

object Service {
    private val retrofit by lazy{

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val gameApi: GameAPI by lazy {
        retrofit
            .create(GameAPI::class.java)
    }

    val playerApi: PlayerAPI by lazy {
        retrofit
                .create(PlayerAPI::class.java)
    }

    val statsApi: StatsAPI by lazy {
        retrofit
                .create(StatsAPI::class.java)
    }

    val SeasonAveragesApi: SeasonAveragesAPI by lazy {
        retrofit
                .create(SeasonAveragesAPI::class.java)
    }

    val TeamApi: TeamAPI by lazy {
        retrofit
                .create(TeamApi::class.java)
    }

}