/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bandtec.TesteJChart;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class Dados {
    
    public Integer id;
    public String user_desktop;
    public String placa_mae;
    public Integer cpu_count;
    public String clock_1;
    public String clock_2;
    public String cpu_media_temperatura;
    public String cpu_media_percent;
    public String cpu_media_clock;
    public String memory_load;
    public String memory_use;
    public String memory_available;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCpu_count() {
        return cpu_count;
    }

    public void setCpu_count(Integer cpu_count) {
        this.cpu_count = cpu_count;
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
    public String video_card;
    
}
