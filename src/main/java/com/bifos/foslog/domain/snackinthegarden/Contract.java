package com.bifos.foslog.domain.snackinthegarden;

import com.bifos.foslog.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Contract extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 고객 KEY (foreign Key)
     */
    @Column(nullable = false)
    private Long customerId;

    /**
     * 연도
     */
    @Column(nullable = false)
    private String year;

    /**
     * 달
     */
    @Column(nullable = false)
    private String month;

    /**
     * 날짜
     */
    @Column(nullable = false)
    private String date;

    @Builder
    public Contract(Long customerId, String year, String month, String date) {
        this.customerId = customerId;
        this.year = year;
        this.month = month;
        this.date = date;
    }
}
