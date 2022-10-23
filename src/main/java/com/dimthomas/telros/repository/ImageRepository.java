package com.dimthomas.telros.repository;

import com.dimthomas.telros.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
