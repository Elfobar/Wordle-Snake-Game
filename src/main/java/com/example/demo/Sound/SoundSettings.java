package com.example.demo.Sound;

import com.example.demo.GameCore.AppConfig;
import com.example.demo.Util.Util;
import org.json.JSONObject;

public class SoundSettings {

    private double backgroundVolume;
    private double sfxVolume;

    public SoundSettings(){
        loadFromFile(AppConfig.getAudioSettingsPathFile());
    }

    public SoundSettings(double backgroundVolume, double sfxVolume){
        this.backgroundVolume = backgroundVolume;
        this.sfxVolume = sfxVolume;
    }

    private void loadFromFile(String filePath){
        JSONObject jsonObject = Util.readObjectFromFile(filePath);
        if(jsonObject.isEmpty()){
            this.backgroundVolume = 0.5;
            this.sfxVolume = 0.5;
        } else{
            this.backgroundVolume = jsonObject.getDouble("backgroundVolume");
            this.sfxVolume = jsonObject.getDouble("sfxVolume");
        }
    }

    public double getBackgroundVolume() {
        return backgroundVolume;
    }

    public double getSfxVolume() {
        return sfxVolume;
    }

    public void setBackgroundVolume(double backgroundVolume) {
        this.backgroundVolume = backgroundVolume;
    }

    public void setSfxVolume(double sfxVolume) {
        this.sfxVolume = sfxVolume;
    }

    @Override
    public String toString(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("backgroundVolume", backgroundVolume);
        jsonObject.put("sfxVolume", sfxVolume);
        return jsonObject.toString();
    }
}
