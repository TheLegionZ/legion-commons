package br.com.thelegion.legioncommons.nameplate;

import org.bukkit.entity.LivingEntity;

public interface NamePlate {

    String getLine(int lineIndex);

    void addLine(String line);

    void updateLine(int lineIndex, String newLine);

    LivingEntity getOwner();

    void update();
}
