package com.bifos.foslog.domain.snackinthegarden;

import com.bifos.foslog.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
@ToString
public class Customer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 고객 이름
     */
    @Column(nullable = false)
    private String name;

    /**
     * 고객 주소
     */
    @Column(nullable = false)
    private String address;

    /**
     * 고객 연락처
     */
    @Column
    private String phoneNumber;

    /**
     * 고객 기타 사항 (메모)
     */
    @Column(columnDefinition = "TEXT")
    private String memo;

    /**
     * 거래 종료 날짜 (샐러드 계약 만료 일자)
     *
     * {@link Contract} 를 사용할 것
     */
    @Deprecated
    @Column(nullable = false)
    private LocalDate expirationDate;

    @Column
    private Status status;

    @Builder
    public Customer(String name, String location, String phoneNumber, String memo, LocalDate expirationDate) {
        this.name = name;
        this.address = location;
        this.phoneNumber = phoneNumber;
        this.memo = memo;
        this.expirationDate = expirationDate;
    }
}
