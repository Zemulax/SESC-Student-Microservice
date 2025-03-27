package com.sesc.unistudycircle.topic_service.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_topics")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long topicId;

    private String topicName;

    private String topicDate;

    @Lob
    private String topicContent;

    private String externalStudentId;

    private String studentName;
}
