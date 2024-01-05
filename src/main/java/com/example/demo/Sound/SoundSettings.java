package com.example.demo.Sound;

import com.example.demo.GameCore.AppConfig;
import com.example.demo.Util.Util;
import org.json.JSONObject;

//Sound settings represents the configuration for music settings of SoundPlayer class
public class SoundSettings {

    private double backgroundVolume;
    private double sfxVolume;

    //Loads previous settings when the game is created
    public SoundSettings(){
        loadFromFile(AppConfig.getAudioSettingsPathFile());
    }

    public SoundSettings(double backgroundVolume, double sfxVolume){
        this.backgroundVolume = backgroundVolume;
        this.sfxVolume = sfxVolume;
    }

    //Loads the settings from the file. If the file is empty, settings are set by default.
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

    //toString is needed to create a specific string that has json format
    @Override
    public String toString(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("backgroundVolume", backgroundVolume);
        jsonObject.put("sfxVolume", sfxVolume);
        return jsonObject.toString();
    }
}
