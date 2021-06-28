package com.vavilon.demo.bo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "attachment")
@Data
public class Attachment {
    @Id
    @Column(name = "attachmentid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attachmentId;
    @Column(name = "fileid")
    private String fileId;
    @Column(name = "main")
    private boolean main;
    @Column(name = "productid")
    private Long productId;

}
