package Deutsche.FindAged.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

/**
 * @author Sarav on 10 Aug 2022
 * @project govtech
 * @package com.govtech.viswa.entity
 * @class GlobalLog
 */

@Entity
@Data
@NoArgsConstructor
@Log4j2
public class GlobalLog {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    Long id;
    String  message;
    String  warning;
    String  path;
    String  input;

    @CreatedBy
    String createdBy;

    @CreatedDate
    LocalDateTime createdTm;
    String  description;



    public GlobalLog(String description, String message, String warning, String path, String input) {
        this.description = description;
        this.message = message;
        this.warning = warning;
        this.path = path;
        this.input = input;
        log.info( this );
    }



    @Override
    public String toString () {

        return String.format("""
        GlobalLog { 
            id=%d, 
            message='%s', 
            warning='%s', 
            path='%s', 
            input='%s', 
            createdBy='%s', createdTm=%s,  
            description='%s' 
        }
        """, id, message, warning, path, input, createdBy, createdTm, description );
    }

}
