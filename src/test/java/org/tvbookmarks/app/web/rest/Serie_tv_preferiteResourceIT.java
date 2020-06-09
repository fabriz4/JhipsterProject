package org.tvbookmarks.app.web.rest;

import org.tvbookmarks.app.TvBookMarksApp;
import org.tvbookmarks.app.domain.Serie_tv_preferite;
import org.tvbookmarks.app.repository.Serie_tv_preferiteRepository;
import org.tvbookmarks.app.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static org.tvbookmarks.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link Serie_tv_preferiteResource} REST controller.
 */
@SpringBootTest(classes = TvBookMarksApp.class)
public class Serie_tv_preferiteResourceIT {

    private static final String DEFAULT_USER = "AAAAAAAAAA";
    private static final String UPDATED_USER = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_OVERVIEW = "AAAAAAAAAA";
    private static final String UPDATED_OVERVIEW = "BBBBBBBBBB";

    private static final String DEFAULT_POPULARITY = "AAAAAAAAAA";
    private static final String UPDATED_POPULARITY = "BBBBBBBBBB";

    private static final Double DEFAULT_AVG_VOTE = 1D;
    private static final Double UPDATED_AVG_VOTE = 2D;

    @Autowired
    private Serie_tv_preferiteRepository serie_tv_preferiteRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restSerie_tv_preferiteMockMvc;

    private Serie_tv_preferite serie_tv_preferite;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final Serie_tv_preferiteResource serie_tv_preferiteResource = new Serie_tv_preferiteResource(serie_tv_preferiteRepository);
        this.restSerie_tv_preferiteMockMvc = MockMvcBuilders.standaloneSetup(serie_tv_preferiteResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Serie_tv_preferite createEntity(EntityManager em) {
        Serie_tv_preferite serie_tv_preferite = new Serie_tv_preferite()
            .user(DEFAULT_USER)
            .name(DEFAULT_NAME)
            .overview(DEFAULT_OVERVIEW)
            .popularity(DEFAULT_POPULARITY)
            .avg_vote(DEFAULT_AVG_VOTE);
        return serie_tv_preferite;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Serie_tv_preferite createUpdatedEntity(EntityManager em) {
        Serie_tv_preferite serie_tv_preferite = new Serie_tv_preferite()
            .user(UPDATED_USER)
            .name(UPDATED_NAME)
            .overview(UPDATED_OVERVIEW)
            .popularity(UPDATED_POPULARITY)
            .avg_vote(UPDATED_AVG_VOTE);
        return serie_tv_preferite;
    }

    @BeforeEach
    public void initTest() {
        serie_tv_preferite = createEntity(em);
    }

    @Test
    @Transactional
    public void createSerie_tv_preferite() throws Exception {
        int databaseSizeBeforeCreate = serie_tv_preferiteRepository.findAll().size();

        // Create the Serie_tv_preferite
        restSerie_tv_preferiteMockMvc.perform(post("/api/serie-tv-preferites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serie_tv_preferite)))
            .andExpect(status().isCreated());

        // Validate the Serie_tv_preferite in the database
        List<Serie_tv_preferite> serie_tv_preferiteList = serie_tv_preferiteRepository.findAll();
        assertThat(serie_tv_preferiteList).hasSize(databaseSizeBeforeCreate + 1);
        Serie_tv_preferite testSerie_tv_preferite = serie_tv_preferiteList.get(serie_tv_preferiteList.size() - 1);
        assertThat(testSerie_tv_preferite.getUser()).isEqualTo(DEFAULT_USER);
        assertThat(testSerie_tv_preferite.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSerie_tv_preferite.getOverview()).isEqualTo(DEFAULT_OVERVIEW);
        assertThat(testSerie_tv_preferite.getPopularity()).isEqualTo(DEFAULT_POPULARITY);
        assertThat(testSerie_tv_preferite.getAvg_vote()).isEqualTo(DEFAULT_AVG_VOTE);
    }

    @Test
    @Transactional
    public void createSerie_tv_preferiteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = serie_tv_preferiteRepository.findAll().size();

        // Create the Serie_tv_preferite with an existing ID
        serie_tv_preferite.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSerie_tv_preferiteMockMvc.perform(post("/api/serie-tv-preferites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serie_tv_preferite)))
            .andExpect(status().isBadRequest());

        // Validate the Serie_tv_preferite in the database
        List<Serie_tv_preferite> serie_tv_preferiteList = serie_tv_preferiteRepository.findAll();
        assertThat(serie_tv_preferiteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSerie_tv_preferites() throws Exception {
        // Initialize the database
        serie_tv_preferiteRepository.saveAndFlush(serie_tv_preferite);

        // Get all the serie_tv_preferiteList
        restSerie_tv_preferiteMockMvc.perform(get("/api/serie-tv-preferites?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(serie_tv_preferite.getId().intValue())))
            .andExpect(jsonPath("$.[*].user").value(hasItem(DEFAULT_USER.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].overview").value(hasItem(DEFAULT_OVERVIEW.toString())))
            .andExpect(jsonPath("$.[*].popularity").value(hasItem(DEFAULT_POPULARITY.toString())))
            .andExpect(jsonPath("$.[*].avg_vote").value(hasItem(DEFAULT_AVG_VOTE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getSerie_tv_preferite() throws Exception {
        // Initialize the database
        serie_tv_preferiteRepository.saveAndFlush(serie_tv_preferite);

        // Get the serie_tv_preferite
        restSerie_tv_preferiteMockMvc.perform(get("/api/serie-tv-preferites/{id}", serie_tv_preferite.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(serie_tv_preferite.getId().intValue()))
            .andExpect(jsonPath("$.user").value(DEFAULT_USER.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.overview").value(DEFAULT_OVERVIEW.toString()))
            .andExpect(jsonPath("$.popularity").value(DEFAULT_POPULARITY.toString()))
            .andExpect(jsonPath("$.avg_vote").value(DEFAULT_AVG_VOTE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSerie_tv_preferite() throws Exception {
        // Get the serie_tv_preferite
        restSerie_tv_preferiteMockMvc.perform(get("/api/serie-tv-preferites/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSerie_tv_preferite() throws Exception {
        // Initialize the database
        serie_tv_preferiteRepository.saveAndFlush(serie_tv_preferite);

        int databaseSizeBeforeUpdate = serie_tv_preferiteRepository.findAll().size();

        // Update the serie_tv_preferite
        Serie_tv_preferite updatedSerie_tv_preferite = serie_tv_preferiteRepository.findById(serie_tv_preferite.getId()).get();
        // Disconnect from session so that the updates on updatedSerie_tv_preferite are not directly saved in db
        em.detach(updatedSerie_tv_preferite);
        updatedSerie_tv_preferite
            .user(UPDATED_USER)
            .name(UPDATED_NAME)
            .overview(UPDATED_OVERVIEW)
            .popularity(UPDATED_POPULARITY)
            .avg_vote(UPDATED_AVG_VOTE);

        restSerie_tv_preferiteMockMvc.perform(put("/api/serie-tv-preferites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSerie_tv_preferite)))
            .andExpect(status().isOk());

        // Validate the Serie_tv_preferite in the database
        List<Serie_tv_preferite> serie_tv_preferiteList = serie_tv_preferiteRepository.findAll();
        assertThat(serie_tv_preferiteList).hasSize(databaseSizeBeforeUpdate);
        Serie_tv_preferite testSerie_tv_preferite = serie_tv_preferiteList.get(serie_tv_preferiteList.size() - 1);
        assertThat(testSerie_tv_preferite.getUser()).isEqualTo(UPDATED_USER);
        assertThat(testSerie_tv_preferite.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSerie_tv_preferite.getOverview()).isEqualTo(UPDATED_OVERVIEW);
        assertThat(testSerie_tv_preferite.getPopularity()).isEqualTo(UPDATED_POPULARITY);
        assertThat(testSerie_tv_preferite.getAvg_vote()).isEqualTo(UPDATED_AVG_VOTE);
    }

    @Test
    @Transactional
    public void updateNonExistingSerie_tv_preferite() throws Exception {
        int databaseSizeBeforeUpdate = serie_tv_preferiteRepository.findAll().size();

        // Create the Serie_tv_preferite

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSerie_tv_preferiteMockMvc.perform(put("/api/serie-tv-preferites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serie_tv_preferite)))
            .andExpect(status().isBadRequest());

        // Validate the Serie_tv_preferite in the database
        List<Serie_tv_preferite> serie_tv_preferiteList = serie_tv_preferiteRepository.findAll();
        assertThat(serie_tv_preferiteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSerie_tv_preferite() throws Exception {
        // Initialize the database
        serie_tv_preferiteRepository.saveAndFlush(serie_tv_preferite);

        int databaseSizeBeforeDelete = serie_tv_preferiteRepository.findAll().size();

        // Delete the serie_tv_preferite
        restSerie_tv_preferiteMockMvc.perform(delete("/api/serie-tv-preferites/{id}", serie_tv_preferite.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Serie_tv_preferite> serie_tv_preferiteList = serie_tv_preferiteRepository.findAll();
        assertThat(serie_tv_preferiteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Serie_tv_preferite.class);
        Serie_tv_preferite serie_tv_preferite1 = new Serie_tv_preferite();
        serie_tv_preferite1.setId(1L);
        Serie_tv_preferite serie_tv_preferite2 = new Serie_tv_preferite();
        serie_tv_preferite2.setId(serie_tv_preferite1.getId());
        assertThat(serie_tv_preferite1).isEqualTo(serie_tv_preferite2);
        serie_tv_preferite2.setId(2L);
        assertThat(serie_tv_preferite1).isNotEqualTo(serie_tv_preferite2);
        serie_tv_preferite1.setId(null);
        assertThat(serie_tv_preferite1).isNotEqualTo(serie_tv_preferite2);
    }
}
