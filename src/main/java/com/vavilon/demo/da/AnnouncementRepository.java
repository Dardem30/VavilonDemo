package com.vavilon.demo.da;

import com.vavilon.demo.bo.announcment.Announcement;
import com.vavilon.demo.da.extension.AnnouncementRepositoryExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long>, AnnouncementRepositoryExtension {
}
