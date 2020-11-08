/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.jose.api_banco_local;

/**
 *
 * @author Aluno
 */
public class DadosBancoLocal {
    
    private Integer id;
    private String user_desktop;
    private String placa_mae;
    private Integer cpu_count;
    private String clock_1;
    private String clock_2;
    private String cpu_media_temperatura;
    private String cpu_media_percent;
    private String cpu_media_clock;
    private String memory_load;
    private String memory_use;
    private String memory_available;
    private String video_card;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_desktop() {
        return user_desktop;
    }

    public void setUser_desktop(String user_desktop) {
        this.user_desktop = user_desktop;
    }

    public String getPlaca_mae() {
        return placa_mae;
    }

    public void setPlaca_mae(String placa_mae) {
        this.placa_mae = placa_mae;
    }

    public Integer getCpu_count() {
        return cpu_count;
    }

    public void setCpu_count(Integer cpu_count) {
        this.cpu_count = cpu_count;
    }

    public String getClock_1() {
        return clock_1;
    }

    public void setClock_1(String clock_1) {
        this.clock_1 = clock_1;
    }

    public String getClock_2() {
        return clock_2;
    }

    public void setClock_2(String clock_2) {
        this.clock_2 = clock_2;
    }

    public String getCpu_media_temperatura() {
        return cpu_media_temperatura;
    }

    public void setCpu_media_temperatura(String cpu_media_temperatura) {
        this.cpu_media_temperatura = cpu_media_temperatura;
    }

    public String getCpu_media_percent() {
        return cpu_media_percent;
    }

    public void setCpu_media_percent(String cpu_media_percent) {
        this.cpu_media_percent = cpu_media_percent;
    }

    public String getCpu_media_clock() {
        return cpu_media_clock;
    }

    public void setCpu_media_clock(String cpu_media_clock) {
        this.cpu_media_clock = cpu_media_clock;
    }

    public String getMemory_load() {
        return memory_load;
    }

    public void setMemory_load(String memory_load) {
        this.memory_load = memory_load;
    }

    public String getMemory_use() {
        return memory_use;
    }

    public void setMemory_use(String memory_use) {
        this.memory_use = memory_use;
    }

    public String getMemory_available() {
        return memory_available;
    }

    public void setMemory_available(String memory_available) {
        this.memory_available = memory_available;
    }

    public String getVideo_card() {
        return video_card;
    }

    public void setVideo_card(String video_card) {
        this.video_card = video_card;
    }
    
    
}
