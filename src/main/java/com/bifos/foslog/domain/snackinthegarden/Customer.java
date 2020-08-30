package com.bifos.foslog.domain.snackinthegarden;

import com.bifos.foslog.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

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
     * 상태
     */
    @Column(nullable = false)
    private CustomerState customerState;

    @Builder
    public Customer(String name, String address, String phoneNumber, String memo, CustomerState customerState) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.memo = memo;
        this.customerState = customerState;
    }
}
