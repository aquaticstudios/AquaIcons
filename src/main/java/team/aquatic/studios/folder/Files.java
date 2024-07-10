package team.aquatic.studios.folder;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.InvalidConfigurationException;

import team.aquatic.studios.AquaIcons;

public class Files extends YamlConfiguration {
    private File File;

    private String Path;

    private FileConfiguration FileC = null;

    public Files(String Path) {
        this.Path = Path + ".yml";
        this.File = new File(AquaIcons.getInstance().getDataFolder(), this.Path);
        SaveDefault();
        Reload();
    }

    public void Reload() {
        try {
            load(this.File);
            ReloadUFT8();
        } catch (InvalidConfigurationException|IOException ex) {
        }
    }

    public void Save() {
        try {
            save(this.File);
        } catch (IOException ex) {
        }
    }

    public void SaveDefault() {
        try {
            if (!this.File.exists())
                AquaIcons.getInstance().saveResource(this.Path, false);
        } catch (Exception ex) {
        }
    }

    public void ReloadUFT8() {
        if (this.FileC == null)
            this.File = new File(AquaIcons.getInstance().getDataFolder(), this.Path);
        this.FileC = (FileConfiguration)YamlConfiguration.loadConfiguration(this.File);
        try {
            Reader defConfigStream = new InputStreamReader(AquaIcons.getInstance().getResource(this.Path), "UTF8");
            if (defConfigStream != null) {
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                this.FileC.setDefaults((Configuration)defConfig);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}