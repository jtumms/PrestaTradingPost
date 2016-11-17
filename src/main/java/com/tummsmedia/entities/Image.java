package com.tummsmedia.entities;

import javax.persistence.Embeddable;

/**
 * Created by john.tumminelli on 11/16/16.
 */
@Embeddable
public class Image {
    private String imageFileName;

    public Image() {
    }

    public Image(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }
}
