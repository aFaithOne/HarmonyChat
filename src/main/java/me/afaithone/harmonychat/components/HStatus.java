package me.afaithone.harmonychat.components;

import me.afaithone.harmonychat.components.interfaces.Statusable;

import java.io.Serializable;

public final class HStatus implements Statusable, Serializable {

    private boolean status;
    private String description;

    public HStatus() {
        this.status = true;
        this.description = "";
    }

    @Override
    public boolean getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
}
