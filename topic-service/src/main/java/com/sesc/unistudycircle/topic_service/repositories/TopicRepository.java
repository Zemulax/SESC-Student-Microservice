package com.sesc.unistudycircle.topic_service.repositories;

import com.sesc.unistudycircle.topic_service.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findAllByExternalStudentId(String externalStudentId);

    List<Topic> findAllByStudentName(String studentName);

    List<Topic> findAllByTopicDate(LocalDate topicDate);
}
