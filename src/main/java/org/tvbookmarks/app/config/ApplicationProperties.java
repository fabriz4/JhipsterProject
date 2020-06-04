package org.tvbookmarks.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Tv Book Marks.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private final DBMOVIE dbmovie=new DBMOVIE();

    public DBMOVIE getDbmovie(){
        return dbmovie;
    }

    public static class DBMOVIE{

        private String apiKey="3166a07754e8519a061813405d20f2e7";
        private String baseURL="https://api.themoviedb.org/3/";
        private String imgURL = "https://image.tmdb.org/t/p/w500/";

        public String getApiKey(){
            return apiKey;
        }
        public String getBaseURL(){
            return baseURL;
        }

        public String getImgURL(){
            return imgURL;
        }
    }




}
