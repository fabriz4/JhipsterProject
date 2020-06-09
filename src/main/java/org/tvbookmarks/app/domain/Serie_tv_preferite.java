package org.tvbookmarks.app.domain;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Serie_tv_preferite.
 */
@Entity
@Table(name = "serie_tv_preferite")
public class Serie_tv_preferite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user")
    private String user;

    @Column(name = "name")
    private String name;

    @Column(name = "overview")
    private String overview;

    @Column(name = "popularity")
    private String popularity;

    @Column(name = "avg_vote")
    private Double avg_vote;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public Serie_tv_preferite user(String user) {
        this.user = user;
        return this;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public Serie_tv_preferite name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public Serie_tv_preferite overview(String overview) {
        this.overview = overview;
        return this;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPopularity() {
        return popularity;
    }

    public Serie_tv_preferite popularity(String popularity) {
        this.popularity = popularity;
        return this;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public Double getAvg_vote() {
        return avg_vote;
    }

    public Serie_tv_preferite avg_vote(Double avg_vote) {
        this.avg_vote = avg_vote;
        return this;
    }

    public void setAvg_vote(Double avg_vote) {
        this.avg_vote = avg_vote;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Serie_tv_preferite)) {
            return false;
        }
        return id != null && id.equals(((Serie_tv_preferite) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Serie_tv_preferite{" +
            "id=" + getId() +
            ", user='" + getUser() + "'" +
            ", name='" + getName() + "'" +
            ", overview='" + getOverview() + "'" +
            ", popularity='" + getPopularity() + "'" +
            ", avg_vote=" + getAvg_vote() +
            "}";
    }
}
