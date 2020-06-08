package org.tvbookmarks.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.BatchSize;
import org.tvbookmarks.app.config.Constants;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;


public class DatiSerieTV  {

    private String name;
    private String overview;
    private double popularity;
    private double avg_vote;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public double getAvg_vote() {
        return avg_vote;
    }

    public void setAvg_vote(double avg_vote) {
        this.avg_vote = avg_vote;
    }

    @Override
    public String toString() {
        return "DatiSerieTV{" +
            "name='" + name + '\'' +
            ", overview='" + overview + '\'' +
            ", popularity=" + popularity +
            ", avg_vote=" + avg_vote +
            '}';
    }
}
