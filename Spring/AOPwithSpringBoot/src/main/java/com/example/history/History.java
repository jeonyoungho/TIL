package com.example.history;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @Column
    private long userIdx;

    @Column
    private Date updateDate;

    public History(long userIdx) {
        this.userIdx = userIdx;
        this.updateDate = new Date();
    }
}
