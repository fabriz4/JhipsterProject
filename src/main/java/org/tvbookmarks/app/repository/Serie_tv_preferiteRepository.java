package org.tvbookmarks.app.repository;

import org.tvbookmarks.app.domain.Serie_tv_preferite;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Serie_tv_preferite entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Serie_tv_preferiteRepository extends JpaRepository<Serie_tv_preferite, Long> {

}
